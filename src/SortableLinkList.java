/**
 * 
 * Sortable LinkedList
 * with
 * Double Ended Nodes 
 * and
 * Recursive Finds
 *
 */


import java.util.Iterator;

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**@author Dawson Valdes
 *
 *	Berkeley City College
 *	Data Structure and Algorithms
 *	Spring 2017
 *
 */

public class SortableLinkList<ItemType extends Comparable> implements Iterable<ItemType>
{


	private class ListIterator implements Iterator<ItemType>
	{
		private CmpDblNode<ItemType> cursor = head;

		@Override
		public boolean hasNext()
		{
			boolean c = cursor != null;
			return c;
		}

		@Override
		public void remove(){}

		@Override
		public ItemType next()
		{
			ItemType item;
			item = cursor.get_item();
			cursor = cursor.get_next();
			return item;
		}

	}


	private CmpDblNode<ItemType> head; // points to first node of link list
	private CmpDblNode<ItemType> tail; // points to last node of link list
	private int size; // tracks how many nodes are in link
	private boolean sorted;

	public SortableLinkList()
	{
		// point the head to the tail, point the tail to a null
		tail = head;
		head = tail;
		size = 0;		// set the size to 0
		sorted = false; // set value of being sorted to false
	}

	@Override
	public Iterator<ItemType> iterator()
	{
		return new ListIterator();
	}

	public boolean is_sorted()
	{

		if(!sorted)
		{
			ItemType a , b;
			sorted = true;

			b = null;
			for( ItemType it : this )
			{
				a = b;
				b = it;

				if( a != null )
				{
					if ( !(Comparison.is_less_than(a, b) || Comparison.is_equal_to(a,b)) )
					{
						sorted = false;
					}
				}
			}

		}

		return sorted;

	}

	public boolean is_empty() // checks if the data structure is empty
	{
		return size > 0; // if the size is more than 0  it returns true, otherwise it returns false
	}
	
	public int size()
	{
		return size;  // returns the number of nodes in the data structure
	}
	
	public void add_at_front( ItemType item ) // adds a new node to the front of the data structure
	{	//creates new node
		CmpDblNode<ItemType> new_head = head==null ?
			new CmpDblNode<ItemType>(item,tail,null):
			new CmpDblNode<ItemType>(item, head, null);

		if( tail == head ){
			if ( tail != null ) {
				tail.set_back(new_head);
			}
			else{
				tail = new_head;
			}

		}
		else if( head!=null ){
			head.set_back(new_head);
		}

		head = new_head; // points head of data structure to new node
		size++; // increments size of data structure
	}
		
	public void add_at_back( ItemType item) // adds a new node to the back of the data structure
	{
		//creates new node after tail
		CmpDblNode<ItemType> new_tail = tail==null ?
			new CmpDblNode<ItemType>(item, null, head ):
			new CmpDblNode<ItemType>(item,null,tail);

		if( head == tail){
			if(head!= null){
				head.set_next(new_tail);
			}
			else{
				head = new_tail;
			}
		}
		else if(tail != null){
			tail.set_next(new_tail);
		}

		tail = new_tail; // points tail of data structure to new node
		size++; // increments size of data structure
	}
	
	public ItemType get_from_front( ) // removes node from front of data structure
	{
		if ( is_empty() ) // check if data structure is empty
		{
			return null; // if the structure is empty return a null
		}		
		else
		{	
			CmpDblNode<ItemType> old_head = head; // make the current head the old head
			ItemType front_item = old_head.get_item(); // store the front get_item from the old head
		
			head = head.get_next(); // point the head to the get_next head
			
			old_head.set_next(null); // set old_head get_next to null to break references
			old_head.set_item(null); // set old_head get_item to null to break references
			head.set_back(null); // set head back to null to remove reference to old_head
			size--; // decrement data structure size
			
			return front_item; // return the front get_item
		}					
	}
		
	public ItemType get_from_back( )
	{
		if ( is_empty() ) // check if data structure is empty
		{
			return null; // if the structure is empty return null
		}
		else
		{	
			CmpDblNode<ItemType> old_tail = tail; // make the current tail the old tail
			ItemType back_item = old_tail.get_item(); // store the tail get_item from the old  tail
			
			tail = tail.get_back(); // point the tail to the previous get_item
			
			old_tail.set_back(null); // set the old_tail to null to break reference
			old_tail.set_item(null); // set the old_tail get_item to null to reference
			tail.set_next(null); // set tail next to null to remove reference to old_tail
			size--; // decrement the data structure size
			
			return back_item; // return the get_back get_item
		}
	}	
	
	private CmpDblNode find_from_front(CmpDblNode cursor , ItemType target)
	{
		// if the cursor is null return a null
		if( cursor == null )
		{
			return null;
		}
		// if the cursor get_item is the target return the cursor
		else if( Comparison.is_equal_to(cursor.get_item() , target) )
		{
			return cursor;
		}
		// recurse the function moving to the get_next node in the list
		else 
		{
			return find_from_front(cursor.get_next(), target);
		}
		
	}
		
	public boolean add_before_target( ItemType item , ItemType target )
	{		
		if ( is_empty() ) // check to is if the structure is empty
		{
			return false; // if it is return false, there is nothing to add before
		}
		else
		{
			// search the list from the front for the target starting from the head
			CmpDblNode cursor = find_from_front(head , target);
			
			if (cursor == null)// if the cursor == null the target was not found
			{
				//return false , there is nothing to add before
				return false;
			}
			else // if a cursor is found
			{
				// create a pointer to the node before the cursor
				CmpDblNode back_node = cursor.get_back();
				// create a new node containing get_item, pointing to cursor get_next and get_back node get_back
				CmpDblNode new_node = new CmpDblNode(item , cursor , back_node );
				// point the get_back nodes get_next to the new node
				back_node.set_next(new_node);
				// point the cursors get_back to the new node
				cursor.set_back(new_node);
				// increment the size of the data structure by one
				size++;
				// return true , a successful add
				return true;
			}						
		}		
	}
	
	private CmpDblNode find_from_back(CmpDblNode cursor , ItemType target)
	{
		// if the cursor is null return a null
		if( cursor == null )
		{
			return null;
		}
		// if the cursor get_item is the target return the cursor
		else if ( Comparison.is_equal_to(cursor.get_item() , target) )
		{
			return cursor;
		}
		// recurse the function moving to the previous node in the list
		else 
		{
			return find_from_back(cursor.get_back(), target);
		}		
	}
	
	public boolean add_after_target( ItemType item , ItemType target )
	{
		if ( is_empty() ) // check to is if the structure is empty
		{
			return false; // if it is return false, there is nothing to add after
		}
		else
		{	
			// search the list from the get_back for the target starting from the tail
			CmpDblNode cursor = find_from_back(tail , target);
			
			// if cursor is == to null
			if (cursor == null)
			{
				return false; // return false no target was found
			}
			else
			{
				// make a pointer to the node after the cursor
				CmpDblNode next_node = cursor.get_next();
				// create a new node containing an get_item pointing the get_next node get_next, and the cursor  for get_back
				CmpDblNode new_node = new CmpDblNode(item , next_node , cursor );
				// point the next_nodes get_back to the new node
				next_node.set_back(new_node);
				// point the cursor get_next to the new node
				cursor.set_next(new_node);
				// increment the size of the data structure
				size++;
				// return true , a succesful add
				return true;
			}
		}
	}
	
	public boolean remove_target( ItemType item )
	{
		if ( is_empty() ) // if the structure is empty
		{
			return false; // return false there is nothing to remove
		}
		else
		{
			// search the list from the front for the target starting from the head 
			CmpDblNode cursor = find_from_front(head , item);
			if (cursor == null) // if the cursor is null
			{
				return false; // return false, no target to remove
			}
			else
			{
				CmpDblNode next_node = cursor.get_next(); // create a pointer to the get_back node
				CmpDblNode back_node = cursor.get_back(); // create a pointer to the get_next node
				
				back_node.set_next(next_node); // set the get_back nodes get_next to the get_next node
				next_node.set_back(back_node); // set the get_next nodes get_back to the get_back node
				
				cursor.set_next(null); // remove reference to get_next node
				cursor.set_back(null); // remove reference to get_back node
				cursor.set_item(null); // remove reference to get_item
				
				return true; // return true, successful removal
			}
		}
	}
	
	public boolean move_to_front( ItemType item )
	{
		if ( is_empty() ) // if the structure is empty
		{
			return false; // return false , there is nothing to move
		}
		else
		{	
			// find the get_item from the front, make a pointer to it
			CmpDblNode cursor = find_from_front(head , item);
			
			if (cursor == null) // if the cursor is null
			{
				return false; // return false , the get_item was not found
			}
			else
			{
				CmpDblNode next_node = cursor.get_next(); // create a pointer to the get_next node
				CmpDblNode back_node = cursor.get_back(); // create a pointer to the get_back node
				
				next_node.set_back(back_node); // set the get_next nodes get_back to the get_back node
				back_node.set_next(next_node); // set the get_back nodes get_next to the get_next node
				
				cursor.set_back(null); // set the cursors get_back to null
				cursor.set_next(head); // set the cursors get_next to the head
				
				head.set_back(cursor); // set the heads get_back to the cursor
				head = cursor; // set the head to the cursor
				
				return true; // return true , a successful move to front
			}				
		}
	}
	
	public boolean move_to_back( ItemType item )
	{
		
		if ( is_empty() ) // if the structure is empty
		{
			return false; // return false, there is nothing to move
		}
		else
		{
			// find the get_item starting from the tail , make a pointer to it
			CmpDblNode cursor = find_from_back(tail , item);
			
			if (cursor == null) // if the cursor == null
			{
				return false; // return false , the get_item was not found
			}
			else
			{
				CmpDblNode next_node = cursor.get_next(); // create a pointer to the get_next node
				CmpDblNode back_node = cursor.get_back(); // create a pointer to the get_back node
				
				next_node.set_back(back_node); // set the get_next node get_back to the get_back node
				back_node.set_next(next_node); // set the get_back node get_next to the get_back node
				
				cursor.set_back(tail); // set the cursor get_back to the tail
				cursor.set_next(null); // set the cursor get_next to null
				
				tail.set_next(cursor); // set the tail get_next to the cursor
				tail = cursor; // set the tail to the cursor
				
				return true; // return true, a successful move
			}				
		}
	}

}
