package DataStructures;

/**
 * This is a maximum oriented priority queue.
 * This uses a doubly linked list as the data structure
 * Created by Elliott Pryor on 1/22/2019.
 *
 * @param <E> The type of object to be held within the queue
 */
public class DLLPriorityQueue<E> {
	
	private static class Node <E> {
		// Fields
		E element;
		int priority;
		Node<E> prev;
		Node<E> next;
		
		// Constructor
		public Node(E e, int priority, Node<E> p, Node<E> n) {
			this.element = e;
			this.priority = priority;
			this.prev = p;
			this.next = n;
		}
		
		// Methods
		public E getElement() {
			return element;
		}
		public Node<E> getPrev() {
			return this.prev;
		}
		public Node<E> getNext() {
			return this.next;
		}
		public void setPrev(Node<E> p) {
			this.prev = p;
		}
		public void setNext(Node<E> n) {
			this.next = n;
		}

	}
	
	// Fields
	private Node<E> header;
	private Node<E> trailer;
	private int size;
	
	// Constructor
	public DLLPriorityQueue() {
		this.header = new Node<>(null, Integer.MAX_VALUE, null, null);
		this.trailer = new Node<>(null, Integer.MIN_VALUE,this.header, null);
		this.header.setNext(this.trailer);
	}
	
	// Methods

    /**
     * Returns the size of the priority queue
     * @return the number of elements in the priority queue
     */
	public int size() {
		return size;
	}

    /**
     * Returns the first element of the queue. It will return null if the queue is empty.
     * @return The element at the top of the queue
     */
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return this.header.next.getElement();
	}

    /**
     * Returns the last element in the queue. It will return null if the queue is empty.
     * @return The element at the bottom of the queue
     */
	public E last () {
		if (isEmpty()) {
			return null;
		}
		return this.trailer.prev.getElement();
	}


    /**
     * Returns a boolean value determining if the queue is empty or not
     * @return True if Empty, False if not empty.
     */
	public boolean isEmpty() {
		return size == 0;
	}

    /**
     * Creates a new node in between two existing nodes
     * @param e The element to be added into the new node
     * @param priority The priority of the new node
     * @param predecessor The node that comes before this node
     * @param successor The node that comes after this node
     */
	private void addBetween(E e, int priority, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, priority, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		this.size++;
	}

    /**
     * This adds a new element into the priority queue at the location corresponding to it's priority.
     * This is a max oriented priority queue --> Higher priority values get placed at the top of the queue
     * @param e Element to be added into the queue
     * @param priority The priority of the element in the queue
     */
	public void enqueue(E e, int priority){
	    Node n = header;
		if(size == 0){ //If the queue is empty, add it between the header and the trailer
			addBetween(e, priority, n, n.getNext());
		} else {
			for (int i = 0; i <= size; i++) {
			    /*
			    Place the new node if the priority of the next node is less then the priority of the new node
			    This places new nodes behind those of equal priority
			     */
				if (n.getNext().priority < priority) {
					addBetween(e, priority, n, n.getNext());
					break;
				}
				n = n.getNext();
			}
		}
    }

    /**
     * This removes and returns the top element of the queue
     * @return the first element of the queue
     */
    public E dequeue() {
        return removeFirst();
    }

    /**
     * This removes and returns the top element of the queue.
     * This is the same as @link dequeue()
     * @return the first element of the queue
     */
	public E removeFirst() {
		if (this.isEmpty()) {
			return null;
		}
		return this.remove(header.getNext());
	}

    /**
     * This removes and returns the bottom element of the queue. Will return null if the queue is empty
     * @return returns the last element of the queue
     */
	public E removeLast() {
		if (this.isEmpty()) {
			return null;
		}
		return this.remove(trailer.getPrev());
	}

    /**
     * Removes a node
     * @param e The node to be removed
     * @return the element inside the removed node
     */
	private E remove(Node<E> e) {
		e.next.setPrev(e.prev);
		e.prev.setNext(e.next);
		this.size--;
		return e.getElement();
	}

    /**
     * Converts the priority queue into a string
     * @return A string representation of the priority queue.
     */
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = this.header.next;
		while (walk != this.trailer) {
			sb.append(walk.element);
			if (walk.next != this.trailer)
				sb.append(", ");
			walk = walk.next;
		}
		sb.append(")");
		return sb.toString();
	}

}
