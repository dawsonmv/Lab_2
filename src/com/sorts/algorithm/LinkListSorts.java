package com.sorts.algorithm;

import com.sorts.data.SortableLinkList;
import edu.princeton.cs.algs4.StdOut;

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**
 * @author Dawson Valdes
 *         <p>
 *         Berkeley City College
 *         Data Structure and Algorithms
 *         Spring 2017
 *         <p>
 *         Created by dawsonvaldes on 4/13/17.
 *         <p>
 *         Natural-Merge-Sort
 *         algorithm for Linked Lists
 *         based on
 *         Magnetic-Tape-Merge
 *         sorting algorithm for magnetic tape storage
 */


public class LinkListSorts
{
    public LinkListSorts() {}

    public static void NaturalMergeSort(SortableLinkList<Comparable> a)
    {
        a = a.is_sorted() ? a : nm_sort(a);
    }

    private static SortableLinkList<Comparable> nm_sort(SortableLinkList<Comparable> a)
    {

        SortableLinkList<Comparable> sorted_list, scratch_a, scratch_b;

        sorted_list = new SortableLinkList<>();
        scratch_a = new SortableLinkList<>();
        scratch_b = new SortableLinkList<>();

        while ( a.not_empty() )
        {
            StdOut.println("Outer Loop");
            while ( a.not_empty() )
            {
                StdOut.println("Inner Loop A");
                nm_merge(a, sorted_list, scratch_a);
                nm_merge(a, sorted_list, scratch_b);
            }

            while ( scratch_a.not_empty() || scratch_b.not_empty() )
            {
                StdOut.println("Inner Loop B");
                nm_merge(scratch_a, scratch_b, sorted_list);
                nm_merge(scratch_a, scratch_b, sorted_list);
            }

        }

        StdOut.println(" ..... Exiting Sort ..... ");
        return sorted_list;

    }

    private static void nm_merge(SortableLinkList<Comparable> old_list,
                                 SortableLinkList<Comparable> temp_list,
                                 SortableLinkList<Comparable> merged_list)
    {
        StdOut.println("Initiating Merge .. ");

        while ( old_list.not_empty() )
        {
            Comparable old_item, old_item_next, temp_item, temp_item_next;
            old_item = null;
            old_item_next = null;
            temp_item = null;
            temp_item_next = null;

            _load(old_item, old_item_next , old_list);
            while ( Comparison.is_less_than(old_item, old_item_next) )
            {
                temp_list.add_at_back(old_item); // write
                _read(old_item, old_item_next, old_list);
            }

            temp_list.add_at_back(old_item);

            while ( temp_list.not_empty() )
            {
                _load(temp_item, temp_item_next, temp_list);
                while ( temp_item != null )
                {
                    if ( Comparison.is_less_than(temp_item, old_item) || old_item == null )
                    {
                        merged_list.add_at_back(temp_item);
                        _read(temp_item, temp_item_next, temp_list);
                    }
                    else
                    {
                        merged_list.add_at_back(old_item);
                        _read(old_item, old_item_next, old_list);
                    }

                }

            }

        }

    }

    private static void _read(Comparable val, Comparable buf, SortableLinkList<Comparable> tape)
    {
        val = buf;
        if ( buf != null )
            buf = tape.get_from_front();

    }

    private static void _load(Comparable v, Comparable b, SortableLinkList<Comparable> t)
    {
        v = t.get_from_front();
        b = t.get_from_front();
    }

    /** moved null check to add_at_back method
     private static void _write( Comparable val , SortableLinkList<Comparable> tape)
     {
     if ( val != null )
     tape.add_at_back( val );

     }
     */

/** replaced , will erase later
    private static void nm_sub_merge(Comparable a, Comparable b,
                                     SortableLinkList<Comparable> m,
                                     SortableLinkList<Comparable> s)
 {

 s.add_at_front(b);
 if( Comparison.is_less_than(a, b) )
        {
            StdOut.println(" Sub-merge merge");
 m.add_at_back(a);
        }
        else
        {
 StdOut.println(" Sub-merge sort");
            s.add_at_front(a);
        }

    }
*/

}
