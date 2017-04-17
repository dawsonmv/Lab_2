package com.sorts.data;

/**
 *
 * Sortable LinkedList
 * with
 * Double Ended Nodes 
 * and
 * Recursive Finds
 *
 */

import com.data.Deque;
import com.sorts.algorithm.Comparison;

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

public class SortableLinkList<ItemType extends Comparable> extends Deque<ItemType>
{


	private boolean sorted;

	public SortableLinkList()
	{
		super();
		sorted = false;
	}

	public boolean is_sorted()
	{

		if(!sorted)
		{
			if ( not_empty() )
			{
				ItemType a, b;
				sorted = true;

				b = null;
				for ( ItemType it : this )
				{
					a = b;
					b = it;

					if ( a != null )
					{
						if ( !(Comparison.is_less_than(a, b) || Comparison.is_equal_to(a, b)) )
						{
							sorted = false;
						}
					}
				}
			}
		}
		else
		{sorted = not_empty();}

		return sorted;

	}

}
