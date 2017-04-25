/**
 * Generic Double Ended Queue using encapsulation
 * with
 * Double Ended Nodes
 * and
 * Recursive Finds
 */

package com.sorts.data;

import java.util.Iterator;


/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**
 * @author Dawson Valdes
 *
 *	Berkeley City College
 *	Data Structure and Algorithms
 *	Spring 2017
 *
 */

public class Deque<ItemType> implements Iterable<ItemType>
{

    private DoubleNode _head; // points to first node of link list
    private DoubleNode _tail; // points to last node of link list
    private int _size; // tracks how many nodes are in link

    public Deque()
    {
        //point the _head and _tail to nulls
        _tail = null;
        _head = null;
        _size = 0;    // set the _size to 0
    }

    @Override
    public Iterator<ItemType> iterator()
    {
        return new ListIterator();
    }

    public DoubleNode head() {return _head;}

    public DoubleNode tail() {return _tail;}

    public boolean not_empty() // checks if the data structure is empty
    {
        return _size > 0; // if the _size is more than 0  it returns true, otherwise it returns false
    }

    protected int size()
    {
        return _size;  // returns the number of nodes in the data structure
    }

    public void add_at_front(ItemType item) // adds a new node to the front of the data structure =  'push front'
    {
        if ( item != null )
        {

            DoubleNode new_node;

            if ( not_empty() )
            {
                new_node = new DoubleNode(item, _head, null); //creates new node
                _head.set_back(new_node);
            }
            else
            {
                new_node = new DoubleNode(item, null, null); // creates a first node
                _tail = new_node;
            }
            _head = new_node; // points _head of data structure to new node
            _size = _size + 1; // increments _size of data structure
        }
    }

    public void add_at_back(ItemType item) // adds a new node to the end of the data structure = 'push back'
    {
        if ( item != null )
        {
            DoubleNode new_node;

            if ( not_empty() )
            {
                new_node = new DoubleNode(item, null, _tail); //creates new node
                _tail.set_next(new_node);
            }
            else
            {
                new_node = new DoubleNode(item, null, null); // Creates first node
                _head = new_node;
            }
            _tail = new_node; // points _tail of data structure to new node}
            _size = _size + 1; // increments _size of data structure
        }
    }

    //   back moves towards _head so _head._back = null; back is towards tail so tail._back = null;

    public ItemType get_from_front() // removes node from front of data structure = 'pop front'
    {
        ItemType first_item;

        if ( _size > 1 ) // check if data structure is empty
        {

            DoubleNode old_head;

            first_item = _head.item(); // store the _item from the old_head
            old_head = _head;  // make the current _head the old _head
            _head = _head.next(); // point the _head to the node behind the old_head
            _head.set_back(null);

            old_head.set_next(null); // set the old _head _back to null to break references
            old_head.set_item(null); // set the old _head _item to null to break references

            _size = _size - 1; // decrement data structure _size
        }
        else if ( not_empty() )
        {
            _size = 0;
            _tail = null;
            first_item = _head.item();
            _head.set_next(null);
            _head.set_back(null);
            _head.set_item(null);
            _head = null;
        }
        else first_item = null; // if the structure is empty return a null

        return first_item; // return the front _item

    }

    public ItemType get_from_back() //removes node from back of the data structure last item = 'pop back'
    {
        ItemType last_item;

        if ( _size > 1 ) // check if data structure is empty
        {
            DoubleNode old_tail = _tail; // make the current _tail the old _tail
            last_item = old_tail.item(); // store the _tail get_item from the old  _tail

            _tail = old_tail.back(); // point the _tail to node in front of the tail
            _tail.set_next(null);

            old_tail.set_back(null); // set the old_tail _next to null to break reference
            old_tail.set_item(null); // set the old_tail _item to null to reference

            _size = _size - 1; // decrement the data structure _size
        }
        else if ( not_empty() )
        {
            _size = 0;
            _head = null;
            last_item = _tail.item();
            _tail.set_item(null);
            _tail.set_back(null);
            _tail.set_next(null);
            _tail = null;
        }
        else last_item = null; // if the structure is empty return a null

        return last_item; // return the last item

    }

    private DoubleNode find_from_front(DoubleNode cursor, final ItemType target)
    {
        // if the cursor is null return a null
        if ( cursor == null || target == null )
        {
            return null;
        }
        // if the cursor get_item is the target return the cursor
        else if ( cursor.item() == target )
        {
            return cursor;
        }
        // recurse the function moving to the get_next node in the list
        else
        {
            return find_from_front(cursor.back(), target);
        }

    }

    public boolean add_before_target(ItemType item, final ItemType target)
    {

        if ( item != null || not_empty() )// prevent null input comparison
        {

            // search the list from the front for the target starting from the _head
            DoubleNode cursor = find_from_front(_head, target);

            if ( cursor == null )// if the cursor == null the target was not found
            {
                //return false , there is nothing to add before
                return false;
            }
            else // if a cursor is found
            {
                // create a pointer to the node before the cursor
                DoubleNode back_node = cursor.back();
                // create a new node containing get_item, pointing to cursor get_next and get_back node get_back
                DoubleNode new_node = new DoubleNode(item, cursor, back_node);
                // point the get_back nodes get_next to the new node
                back_node.set_next(new_node);
                // point the cursors get_back to the new node
                cursor.set_back(new_node);
                // increment the _size of the data structure by one
                _size++;
                // return true , a successful add
                return true;
            }
        }

        return false; // if it is return false, there is nothing to add before

    }

    private DoubleNode find_from_back(DoubleNode cursor, final ItemType target)
    {
        // if the cursor is null return a null
        if ( cursor == null || target == null )
        {
            return null;
        }
        // if the cursor get_item is the target return the cursor
        else if ( cursor.item() == target )
        {
            return cursor;
        }
        // recurse the function moving to the previous node in the list
        else
        {
            return find_from_back(cursor.back(), target);
        }


    }

    public boolean add_after_target(ItemType item, final ItemType target)
    {
        if ( item != null && not_empty() ) // check to is if the structure is empty
        {
            // search the list from the get_back for the target starting from the _tail
            DoubleNode cursor = find_from_back(_tail, target);

            // if cursor is == to null
            if ( cursor == null )
            {
                return false; // return false no target was found
            }
            else
            {
                // make a pointer to the node after the cursor
                DoubleNode next_node = cursor.next();
                // create a new node containing an get_item pointing the get_next node get_next, and the cursor  for get_back
                DoubleNode new_node = new DoubleNode(item, next_node, cursor);
                // point the next_nodes get_back to the new node
                next_node.set_back(new_node);
                // point the cursor get_next to the new node
                cursor.set_next(new_node);
                // increment the _size of the data structure
                _size++;
                // return true , a successful add
                return true;
            }

        }
        else
        {

            return false; // if it is return false, there is nothing to add after
        }
    }

    public boolean remove_target(ItemType item)
    {
        if ( item != null && not_empty() ) // if the structure is empty
        {
            // search the list from the front for the target starting from the _head
            DoubleNode cursor = find_from_front(_head, item);
            if ( cursor == null ) // if the cursor is null
            {
                return false; // return false, no target to remove
            }
            else
            {
                DoubleNode next_node = cursor.next(); // create a pointer to the get_back node
                DoubleNode back_node = cursor.back(); // create a pointer to the get_next node

                back_node.set_next(next_node); // set the get_back nodes get_next to the get_next node
                next_node.set_back(back_node); // set the get_next nodes get_back to the get_back node

                cursor.set_next(null); // remove reference to get_next node
                cursor.set_back(null); // remove reference to get_back node
                cursor.set_item(null); // remove reference to get_item

                return true; // return true, successful removal
            }

        }
        else
        {

            return false; // return false there is nothing to remove
        }
    }

    public boolean move_to_front(ItemType item)
    {
        if ( item != null && not_empty() ) // if the structure is empty
        {
            // find the get_item from the front, make a pointer to it
            DoubleNode cursor = find_from_front(_head, item);

            if ( cursor == null ) // if the cursor is null
            {
                return false; // return false , the get_item was not found
            }
            else
            {
                DoubleNode next_node = cursor.next(); // create a pointer to the get_next node
                DoubleNode back_node = cursor.back(); // create a pointer to the get_back node

                next_node.set_back(back_node); // set the get_next nodes get_back to the get_back node
                back_node.set_next(next_node); // set the get_back nodes get_next to the get_next node

                cursor.set_back(null); // set the cursors get_back to null
                cursor.set_next(_head); // set the cursors get_next to the _head

                _head.set_back(cursor); // set the heads get_back to the cursor
                _head = cursor; // set the _head to the cursor

                return true; // return true , a successful move to front
            }

        }
        else
        {

            return false; // return false , there is nothing to move
        }
    }

    public boolean move_to_back(ItemType item)
    {
        if ( item != null && not_empty() )
        {
            // find the get_item starting from the _tail , make a pointer to it
            DoubleNode cursor = find_from_back(_tail, item);

            if ( cursor == null ) // if the cursor == null
            {
                return false; // return false , the get_item was not found
            }
            else
            {
                DoubleNode next_node = cursor.next(); // create a pointer to the get_next node
                DoubleNode back_node = cursor.back(); // create a pointer to the get_back node

                next_node.set_back(back_node); // set the get_next node get_back to the get_back node
                back_node.set_next(next_node); // set the get_back node get_next to the get_back node

                cursor.set_back(_tail); // set the cursor get_back to the _tail
                cursor.set_next(null); // set the cursor get_next to null

                _tail.set_next(cursor); // set the _tail get_next to the cursor
                _tail = cursor; // set the _tail to the cursor

                return true; // return true, a successful move
            }

        }
        else
        {

            return false; // return false, there is nothing to move
        }
    }

    private class ListIterator implements Iterator<ItemType>
    {
        private DoubleNode _cursor;

        public ListIterator()
        {
            _cursor = head();
        }

        @Override
        public boolean hasNext()
        {
            return _cursor != null;
        }

        @Override
        public void remove() {}

        @Override
        public ItemType next()
        {
            ItemType item;
            item = _cursor.item();
            _cursor = _cursor.next();
            return item;
        }

    }

    protected class DoubleNode
    {
        private DoubleNode _next; // points to get_next node
        private DoubleNode _back; // points to previous node
        private ItemType _item; // points to get_item of data of ItemType

        public DoubleNode(ItemType item, DoubleNode next, DoubleNode back)
        {
            _next = next;  // assigns initial value to get_next
            _back = back;  // assigns initial value to get_back
            _item = item;  // assigns initial value to get_item
        }

        public ItemType item()
        {
            return _item;  // access private get_item
        }

        public DoubleNode next()
        {
            return _next;  // accesses private get_next pointer
        }

        public DoubleNode back()
        {
            return _back;  // access private get_back pointer
        }

        public void set_next(DoubleNode next)
        {
            _next = next;  // sets private get_next pointer
        }

        public void set_back(DoubleNode back)
        {
            _back = back;  // sets private get_back pointer
        }

        public void set_item(ItemType item)
        {
            _item = item;  // sets private get_item
        }

    }

}
