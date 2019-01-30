package JobScheduler;

/**
 * Created by pryor on 1/29/2019.
 */
public class HeapPriorityQueue<E> {
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

    public HeapPriorityQueue(){
        this(10, false);
    }
    public HeapPriorityQueue(int size, boolean static_size){
        nodes = new Node[size + 1];
        nodes[0] = null;
        dynamic = !static_size;
    }//four way heap is fastest
}
