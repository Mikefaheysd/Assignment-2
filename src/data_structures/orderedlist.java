package data_structures;
import java.util.Iterator;
import java.util.NoSuchElementException;

	/**
	 * A list of data stored within Nodes
	 * the linked list holds Objects and is used for manipulation within search methods.
	 * This linked list will create new nodes in order,
	 * remove nodes in order, and return the current size of the list.
	 * Also the linked list can make the linked list empty and find an item in the list.
	 * 
	 * 
	 * @author michaelfahey
	 *
	 * @param <E> the type of element in the Linked list
	 */
public class orderedlist<E> implements OrderedListI<E> {

	protected class Node<E> {
		E data;
		Node<E> next;

		public Node(E obj) {
			data = obj;
			next = null;
		}
	}//end node

	int currentSize;
	Node<E> head;
	Node<E> tail;
	
	//creates new linked list
	public orderedlist() {
		head = tail = null;
		currentSize = 0;
	}
	
	/**
	 * Adds an object to the beginning of the list.
	 * Increments size of list
	 * @param obj the object to be added to the list.
	 */
	public void addFirst(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (head==null)
			head = tail = newNode;
			
		else{
			newNode.next = head;
			head = newNode;
		}
		currentSize++;
	}	
	
	/**
	 * Adds an object to the end of the list.
	 * @param obj the object to be added to the list.
	 * Increases size of list counter
	 */
	public void addLast(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (head==null)
			head = tail = newNode;
		else{
			tail.next = new Node<E>(obj);
			tail = tail.next;
		}
		currentSize++;
	}
	
	/**
	 * Adds an entry to the sorted list so that the list remains sorted.
	 * @param obj new object to add
	 * @return True if added successfully, false if unsuccessful
	 */
	public boolean add (E obj){
		if (head==null  || (  ((Comparable<E>) obj).compareTo (head.data)<0 ) ){
			addFirst(obj);
			
		}
		else{ 
			Node <E> tmp = head;
			Node <E> previous = head;
			while (tmp != null)
			{
				if(((Comparable<E>) obj).compareTo (tmp.data)>=0){
					previous = tmp;
					tmp = tmp.next;
				}
				
				else{
					break;
				}
			}
			Node<E> newNode = new Node<E>(obj);
			newNode.next=tmp;
			previous.next=newNode;
		}
		currentSize++;
		return true;
		
	}
	
	/**
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeFirst(){
		if (head==null)	{
			return null;
		}
		E tmp = head.data;
		head = head.next;
		currentSize--;
		return  tmp;
	}
	
	/**
	 * Removes the last Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeLast(){
		if (isEmpty()){
			return null;
		}
		Node<E> current = head, previous=null;
		while(current.next != null){
			previous = current;
			current = current.next;
		}
		if (previous != null){
			previous.next = null;
			tail = previous;
			currentSize--;
		}
		return current.data;
	}
	
	/**
	 * Returns the first Object in the list, but does not remove it.
	 * Returns null if the list is empty.
	 * @return the object at the beginning of the list.
	 */
	public E peekFirst (){
		if (head.data==null){
			return null;	
		}
		return head.data;
	}
	
	/**
	 * Returns the last Object in the list, but does not remove it. 
	 * Returns null if the list is empty.
	 * @return the object at the end of the list.
	 */
	public E peekLast(){
		if (tail == null){
			return null;
		}
		return tail.data;
	}
	
	/**
	 * Return the list to an empty state.
	 * This should generally be a constant time operation.
	 */
	public void makeEmpty(){
		head = tail = null;
		currentSize=0;
	}
	
	/**
	 * Test whether the list is empty.
	 * @return true if the list is empty, otherwise false
	 */
	public boolean isEmpty()
	{
		if (head == null){
			return true;
		}
		return false;
	}
	
	/**
	 * Find an object in the list
	 * @param obj the object to find
	 * @return the object that was found or Null if nothing found
	 */
	public E find (E obj){
		Node <E> tmp = head;
		while (tmp != null){
			if(((Comparable<E>) obj).compareTo(tmp.data)==0)
				return tmp.data;
			if(((Comparable<E>) obj).compareTo(tmp.data)<0)
				return null;
			tmp = tmp.next;
		}
		return null;
	}

	/**
	 * Remove an object from the list
	 * @param obj Thing to be removed
	 * @return True if the thing is removed, false if it is not found
	 */
	public boolean remove (E obj){ 
		Node <E> tmp = head;
		Node <E> previous = head;
		if( head!=null &&  ( ((Comparable<E>) obj).compareTo (head.data)==0) )
		{
			removeFirst();
			return true;
		}
		while (tmp != null)
		{
			if(((Comparable<E>) obj).compareTo (tmp.data)==0)
				{
				 	previous.next = tmp.next;
				 	currentSize--;
				 	return true;
				}
			
			previous = tmp;
			tmp = tmp.next;
		}
		
		return false;
	}
	
	/**
	 * Test whether the list is full.
	 * @return true if the list is full, otherwise false
	 */
	public boolean isFull()
	{
		return false;
	}
	
	/**
	 * Returns the number of Objects currently in the list.
	 * @return the number of Objects currently in the list.
	 */
	public int size()
	{
		return currentSize;
	}
	
	/**
	 * Test whether the list contains an object. This will use the object's
	 * compareTo method to determine whether two objects are the same.
	 * 
	 * @param obj The object to look for in the list
	 * @return true if the object is found in the list, false if it is not found
	 */
	public boolean contains(E obj){
		Node <E> tmp = head;
		while (tmp != null){
			if(((Comparable<E>) obj).compareTo (tmp.data)==0)
				return true;
			tmp = tmp.next;
		}
		return false;
	}
	
	/**
	 * Returns an Iterator of the values in the list, presented in
	 * the same order as the list.
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

	/**
	 * Assists iterator method by creating helper Node
	 * 
	 *
	 */
	class IteratorHelper implements Iterator<E> {
		Node<E> helper;
		
		public IteratorHelper() {
			helper = head;
		}
		
		/**
		 * Checks to see if there is another element to iterate
		 * @return 
		 */
		public boolean hasNext() {
			return helper != null;
		}
		
		/**
		 * returns the next object in the list, if no object exists
		 * throws NoSuchElementException
		 * @return next item in list
		 */
		public E next() {
			if ( !hasNext())
				throw new NoSuchElementException();
			E tmp = helper.data;
			helper = helper.next;
			return tmp;
		}
		
		/**
		 * Removes objects while iterating (not supported)
		 * throws UnsupportedOperationException
		 */
		public void remove() {
			throw new UnsupportedOperationException();		
		}
	}

}