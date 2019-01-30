package DataStructures;


/**
 * Created by pryor on 1/29/2019.
 */
public class Heap<E> {
    private static class Node <E> {
        // Fields
        E element;
        int priority;

        // Constructor
        public Node(E e, int priority) {
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

    public Heap(){
        this(10, false);
    }
    public Heap(int size, boolean static_size){
        nodes = new Node[size + 1];
        nodes[0] = null;
        dynamic = !static_size;
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

    public void insertNode(E e, int priority){
        Node n = new Node(e, priority);
        if(index < nodes.length - 2){
            nodes[index] = n;
            pushUp(n, index);
            index++;
        } else if(dynamic){
            growSize();
            nodes[index] = n;
            pushUp(n, index);
            index++;
        } else{
            removeHead();
            nodes[index] = n;
            pushUp(n, index);
        }
    }

    /**
     * This moves the node up the tree if the parent is smaller than it
     * @param n The node to be moved
     * @param index the index of the node
     * @return the index that it was placed at
     */
    private int pushUp(Node n, int index){
        try {
            while (nodes[index/2].priority < n.priority){ //Will need to change the < to > for min oriented heap
                Node temp = nodes[index/2];
                nodes[index/2] = n;
                nodes[index] = temp;
                index /= 2;
            }
        }catch (NullPointerException e){
        }
        return index;
    }

    /**
     * This moves the node down the tree if either child is greater than it
     * @param n The node to be moved
     * @param index the index of the node
     * @return the index that it was placed at
     */
    private int pushDown(Node n, int index){
        try {
            while (nodes[index * 2].priority > n.priority || nodes[index * 2 + 1].priority > n.priority){ //Will need to change the > to < for min oriented heap
                if(nodes[index * 2].priority > nodes[index * 2 + 1].priority){//swap at index*2
                    Node temp = nodes[index * 2];
                    nodes[index * 2] = n;
                    nodes[index] = temp;
                    index = index * 2;
                } else{
                    Node temp = nodes[index * 2 + 1];
                    nodes[index * 2 + 1] = n;
                    nodes[index] = temp;
                    index = index * 2 + 1;
                }
            }
        }catch (NullPointerException e){
            //do nothing
        }
        return index;
    }

    /**
     * This removes the node at the given position. Returns null if index is out of bounds or if value is null
     * @param index index to be removed
     * @return the element in the removed node.
     */
    public E removeNode(int index){
        try {
            E retMe = (E) nodes[index].getElement();
            nodes[index] = nodes[--this.index];
            nodes[this.index] = null;
            index = pushUp(nodes[index], index);//checks to make sure it shouldn't be floated up
            pushDown(nodes[index], index);//checks to see if it can float down

            return retMe;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
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
            sb.append((E) nodes[i].getElement() + ", ");
        }
        sb.append(")");
        return sb.toString();
    }

}
