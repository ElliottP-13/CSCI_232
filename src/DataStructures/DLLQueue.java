package DataStructures;

public class DLLQueue<E>  {

    private DoublyLinkedList<E> self;//is the doubly linked list that contains the data for the queue

    /**
     * Constructs a Queue with a doubly linked list as the container
     */
    public DLLQueue (){
        self = new DoublyLinkedList<>();
    }

    /**
     *
     * @return the int size of the queue
     */
    public int size() {
        return self.size();
    }

    /**
     *
     * @return boolean value of true if the queue is empty, and false if it has any elements
     */
    public boolean isEmpty() {
        return self.isEmpty();
    }

    /**
     * Adds elements to the queue
     * @param e the element to be added to the bottom of the queue
     */
    public void enqueue(E e) {
        self.addLast(e);
    }

    /**
     *
     * @return The element at the top of the queue
     */
    public E first() {
        return self.first();
    }

    /**
     * Removes the first element in the queue from the list and returns it
     * @return The element at the top of the queue
     */
    public E dequeue() {
        return self.removeFirst();
    }

    /**
     *
     * @return A string representation of the data within the queue
     */
    public String toString() {
        return self.toString();
    }
}
