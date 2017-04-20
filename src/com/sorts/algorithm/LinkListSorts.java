package com.sorts.algorithm;

import com.sorts.data.SortableLinkList;

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**
 * @author Dawson Valdes
 * <p>
 * Berkeley City College
 * Data Structure and Algorithms
 * Spring 2017
 * <p>
 * Created by dawsonvaldes on 4/13/17.
 * <p>
 * Natural-Merge-Sort
 * algorithm for Linked Lists
 * based on
 * Magnetic-Tape-Merge
 * sorting algorithm for magnetic tape storage
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
            //      StdOut.println("Outer Loop");
            while ( a.not_empty() )
            {
                //            StdOut.println("Inner Loop A");
                nm_merge(a, sorted_list, scratch_a);
                nm_merge(a, sorted_list, scratch_b);
            }

            while ( scratch_a.not_empty() || scratch_b.not_empty() )
            {
                //          StdOut.println("Inner Loop B");
                nm_merge(scratch_a, scratch_b, sorted_list);
                nm_merge(scratch_a, scratch_b, sorted_list);
            }

        }

        //StdOut.println(" ..... Exiting Sort ..... ");
        return sorted_list;

    }

    private static void nm_merge(SortableLinkList<Comparable> left,
                                 SortableLinkList<Comparable> right,
                                 SortableLinkList<Comparable> merged_list)
    {
        //StdOut.println("Initiating Merge .. ");
        Comparable left_item, left_item_next, right_item, right_item_next;
        left_item = left.get_from_front();
        left_item_next = left.get_from_front();
        right_item = right.get_from_front();
        right_item_next = right.get_from_front();


        do
        {
            if ( Comparison.is_less_than(left_item, right_item) || Comparison.is_equal_to(left_item, right_item) )
            {
                //StdOut.println(" . merge left . . ");
                merged_list.add_at_back(left_item);
                left_item = left_item_next;
                left_item_next = left.get_from_front();
            }
            else
            {
                // StdOut.println(" . . merge right . ");
                merged_list.add_at_back(right_item);
                right_item = right_item_next;
                right_item_next = right.get_from_front();
            }
            // StdOut.println(" . Do Complete .");
        }
        while ( Comparison.is_less_than(left_item, left_item_next)
                        &&
                        Comparison.is_less_than(right_item, right_item_next)
                );


        nm_sub_merge(left_item, left_item_next, merged_list, left);
        nm_sub_merge(right_item, right_item_next, merged_list, right);

    }

    private static void nm_sub_merge(Comparable a, Comparable b,
                                     SortableLinkList<Comparable> m,
                                     SortableLinkList<Comparable> s)
    {

        s.add_at_front(b);
        if( Comparison.is_less_than(a, b) )
        {
            //StdOut.println(" Sub-merge merge");
            m.add_at_back(a);
        }
        else
        {
            //StdOut.println(" Sub-merge sort");
            s.add_at_front(a);
        }

    }

}
