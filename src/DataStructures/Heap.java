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

    private Node[] nodes;//The heap
    private boolean dynamic;//Whether or not the heap should scale --> saves memory
    private int index; //The bottom of the heap
    private boolean maxOriented; //if true --> Max priority on top if false --> min priority on top

    /**
     * Creates a max oriented heap with an initial size of 10
     */
    public Heap(){
        this(10, true,false);
    }

    /**
     * Creates a heap with an initial size of 10
     * @param isMaxOriented Max orientation places highest priority items at the top of the node
     */
    public Heap(boolean isMaxOriented){
        this(10, isMaxOriented, false);
    }

    /**
     * Creates a heap of the given size and orientation
     * @param size Initial size of the heap
     * @param isMaxOriented Max orientation places highest priority items at the top of the node
     * @param static_size Determines whether or not the heap should increase in size to fit new data
     */
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

    /**
     * Inserts an element into the heap based on a given priority
     * @param e The element to be inserted
     * @param priority The priority of the element
     */
    public void insertNode(E e, long priority){

        Node n = new Node(e, maxOriented ? priority: -priority);//if not max oriented, set priority to negative priority
        if(index < nodes.length){ //The heap does not need to grow to fit the new data
            nodes[index] = n;
            pushUp(index);//Float the new data up based on its priority
            index++;//increment the index to change where the tail is
        } else if(dynamic){//The heap needs to grow to fit the new data, so increase the size then add the node
            growSize();//doubles the size of the array
            nodes[index] = n;
            pushUp(index);
            index++;
        } else{//Remove the top element then add the data
            //NOTE: to store max values it needs to be min oriented heap because the smallest number needs to be removed

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
        while(i * 2 + 1 < index){//Makes sure that child 2 is within the heap
           if(nodes[i * 2].priority > nodes[i].priority ||
                    nodes[i * 2 + 1].priority > nodes[i].priority){ //If either child 1 or child 2 are greater
               if(nodes[i * 2].priority > nodes[i * 2 + 1].priority){//swap at child 1
                    Node temp = nodes[i * 2];
                    nodes[i * 2] = nodes[i];
                    nodes[i] = temp;
                    i = i * 2;
                } else{ //swap at child 2
                    Node temp = nodes[i * 2 + 1];
                    nodes[i * 2 + 1] = nodes[i];
                    nodes[i] = temp;
                    i = i * 2 + 1;
                }
            } else{
               break;
           }
        }
        if(i * 2 < index){ //It is possible that only child 1 is in the heap and not child 2 so make sure that child 1 isn't greater
            if(nodes[i*2].priority > nodes[i].priority){
                Node temp = nodes[i * 2];
                nodes[i * 2] = nodes[i];
                nodes[i] = temp;
            }
        }

        return i;//return where the element was placed
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
            i = pushUp(i);//checks to make sure it shouldn't be floated up because the node could be in the middle of the heap
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

    /**
     * Returns the size of the heap
     * Returns zero if nothing is in the heap
     * @return The size of the heap
     */
    public int size(){
        return index - 1;
    }

    /**
     * Converts the heap to a string of form "(E, E, E, ..., E)"
     * @return a String representation of the heap
     */
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
