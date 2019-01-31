package DataStructures;


/**
 * Created by pryor on 1/29/2019.
 */
public class Heap<E> {
    private static class Node <E> {
        // Fields
        E element;
        long priority;

        // Constructor
        public Node(E e, long priority) {
            this.element = e;
            this.priority = priority;
        }

        // Methods
        public E getElement() {
            return element;
        }
    }

    private Node[] nodes;
    private boolean dynamic;
    private int index;
    private boolean maxOriented;

    public Heap(){
        this(10, true,false);
    }
    public Heap(boolean isMaxOriented){
        this(10, isMaxOriented, false);
    }
    public Heap(int size, boolean isMaxOriented, boolean static_size){
        nodes = new Node[size + 1];
        nodes[0] = null;
        dynamic = !static_size;
        maxOriented = isMaxOriented;
        index = 1;
    }//four way heap is fastest

    /**
     * Doubles the size of the node array
     * Grows in Amortized time
     */
    private void growSize(){
        Node[] newNodes = new Node[nodes.length * 2];
        for (int i = 0; i < nodes.length; i++) {
            newNodes[i] = nodes[i];
        }
        nodes = newNodes;
    }

    public void insertNode(E e, long priority){

        Node n = new Node(e, maxOriented ? priority: -priority);//if not max oriented, set priority to negative priority
        if(index < nodes.length){
            nodes[index] = n;
            pushUp(index);
            index++;
        } else if(dynamic){
            growSize();
            nodes[index] = n;
            pushUp(index);
            index++;
        } else{
            removeHead();
            nodes[index] = n;
            pushUp(index);
        }
    }

    /**
     * This moves the node up the tree if the parent is smaller than it
     * @param i the index of the node
     * @return the index that it was placed at
     */
    private int pushUp(int i){
        while (i > 1){
            if(nodes[i/2].priority < nodes[i].priority){ //Will need to change the < to > for min oriented heap
                Node temp = nodes[i/2];
                nodes[i/2] = nodes[i];
                nodes[i] = temp;
                i /= 2;
            } else{
                break;
            }
        }
        return i;
    }

    /**
     * This moves the node down the tree if either child is greater than it
     * @param i the index of the node
     * @return the index that it was placed at
     */
    private int pushDown(int i){
        while(i * 2 + 1 < index){
           if(nodes[i * 2].priority > nodes[i].priority ||
                    nodes[i * 2 + 1].priority > nodes[i].priority){
                //System.out.println("Bring down");
                if(nodes[i * 2].priority > nodes[i * 2 + 1].priority){//swap at child 1
                    //System.out.println("Switch Child 1");
                    Node temp = nodes[i * 2];
                    nodes[i * 2] = nodes[i];
                    nodes[i] = temp;
                    i = i * 2;
                } else{ //swap at child 2
                    //System.out.println("Change Child 2");
                    Node temp = nodes[i * 2 + 1];
                    nodes[i * 2 + 1] = nodes[i];
                    nodes[i] = temp;
                    i = i * 2 + 1;
                }
            } else{
               break;
           }
        }
        if(i * 2 < index){
            if(nodes[i*2].priority > nodes[i].priority){
                Node temp = nodes[i * 2];
                nodes[i * 2] = nodes[i];
                nodes[i] = temp;
            }
        }

        return i;
    }

    /**
     * This removes the node at the given position. Returns null if index is out of bounds or if value is null
     * @param i index to be removed
     * @return the element in the removed node.
     */
    public E removeNode(int i){
        try {
            E retMe = (E) nodes[i].getElement();
            nodes[i] = nodes[--index];
            nodes[index] = null;
            i = pushUp(i);//checks to make sure it shouldn't be floated up
            pushDown(i);//checks to see if it can float down

            return retMe;
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Removes and returns the element at the top of the heap
     * Returns null if value is null
     * @return The element at the top of the heap
     */
    public E removeHead(){
        return removeNode(1);
    }

    public int size(){
        return index - 1;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 1; i < index; i++){
            sb.append((E) nodes[i].getElement().toString() + ", ");
        }
        sb.append(")");
        return sb.toString();
    }

}
