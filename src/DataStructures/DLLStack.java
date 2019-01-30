package DataStructures;

public class DLLStack <E>{
    private DoublyLinkedList<E> self;//the list that is the container for the objects

    /**
     * Constructs a Stack with a doubly linked list as the container
     */
    public DLLStack (){
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
     * Adds an element to the top of the stack
     * @param e The element to be added to the top of the stack
     */
    public void push(E e) {
        self.addFirst(e);
    }

    /**
     * Returns the value of the element at the top of the stack but does not remove it from the stack
     * @return The element at the top of the stack
     */
    public E top() {
        return self.first();//It errors if I don't have self even though it is a DoublyLinkedList it says that it is an Object instead of E
    }

    /**
     * Removes the first element in the stack from the list and returns it
     * @return The element at the top of the stack
     */
    public E pop() {
        return self.removeFirst();
    }

    /**
     * @return A string representation of the data within the queue
     */
    public String toString() {
        return self.toString();
    }
}
