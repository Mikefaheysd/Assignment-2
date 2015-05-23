package data_structures;

import java.util.Iterator;

/**
 * An interface for an ORDERED linked list.
 * @author Rob Edwards
 * @version $Id: OrderedListI.java,v 1.2 2013/04/14 03:36:49 masc0674 Exp $
 * @param <E> whatever you want to put in the Linked List!
 */
public interface OrderedListI<E> extends Iterable<E> {
	
	/**
	 * Adds an entry to the sorted list so that the list remains sorted.
	 * @param obj new object to add
	 * @return True if added successfully, false if unsuccessful
	 */
	public boolean add (E obj);

	/**
	 * Checks if the entry is in the list
	 * @param obj The thing to be found
	 * @return True if the thing is in the list
	 */
	public boolean contains (E obj);
	/**
	 * Find an object in the list
	 * @param obj the object to find
	 * @return the object that was found or Null if nothing found
	 */
	public E find (E obj);
	
	/**
	 * Remove an object from the list
	 * @param obj Thing to be removed
	 * @return True if the thing is removed, false if it is not found
	 */
	public boolean remove (E obj);

	/**
	 * Make the list empty
	 */
	public void makeEmpty();
	/**
	 * Checks if the list is full
	 * @return true if the list is full
	 */
	public boolean isFull();
	
	/**
	 * Check if the list is empty
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * The current size of the list.
	 * @return the size of the list.
	 */
	public int size () ;
	/**
	 * An iterator for the list
	 */
	public Iterator<E> iterator();
}
