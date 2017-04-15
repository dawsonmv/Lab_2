/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**@author Dawson Valdes
 *
 *
 *
 *  Comparable Double Node
 *  Created by dawsonvaldes on 4/13/17.
 */
public class CmpDblNode<ItemType extends Comparable>
{
    private CmpDblNode _next; // points to get_next node
    private CmpDblNode _back; // points to previous node
    private ItemType _item; // points to get_item of data of ItemType

    public CmpDblNode(ItemType item, CmpDblNode next, CmpDblNode back)
    {
        _next = next;  // assigns inital value to get_next
        _back = back;  // assigns inital value to get_back
        _item = item;  // assigns inital value to get_item
    }

    public ItemType get_item()
    {
        return _item;  // access private get_item
    }

    public CmpDblNode get_next()
    {
        return _next;  // accesses private get_next pointer
    }

    public CmpDblNode get_back()
    {
        return _back;  // access private get_back pointer
    }

    public void set_next(CmpDblNode next)
    {
        _next = next;  // sets private get_next pointer
    }

    public void set_back(CmpDblNode back)
    {
        _back = back;  // sets private get_back pointer
    }

    public void set_item(ItemType item)
    {
        _item = item;  // sets private get_item
    }

}