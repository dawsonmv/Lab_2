/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**@author Dawson Valdes
 *
 * 	Berkeley City College
 *	Data Structure and Algorithms
 *	Spring 2017
 *
 * Created by dawsonvaldes on 4/13/17.
 *
 * Natural-Merge-Sort algorithm
 * based on
 * Magnetic-Tape-Merge sorting algorithm *
 *
 */



public  class LinkListSorts
{
    private void LinkListSorts(){}

    public static void NaturalMergeSort(SortableLinkList a)
    {
        if( !a.is_sorted() )
        {
            a = nm_sort(a);
        }
    }

    private static SortableLinkList nm_sort(SortableLinkList a)
    {

        SortableLinkList sorted_list, scratch_a, scratch_b;

        sorted_list = new SortableLinkList();
        scratch_a = new SortableLinkList();
        scratch_b = new SortableLinkList();

        while( !a.is_empty() )
        {
            while( !a.is_empty() )
            {
                nm_merge( a, sorted_list, scratch_a );
                nm_merge( a, sorted_list, scratch_b );
            }

            while ( !scratch_a.is_empty() || !scratch_b.is_empty() )
            {
                nm_merge( scratch_a, scratch_b, sorted_list );
                nm_merge( scratch_a, scratch_b, sorted_list );
            }

        }

        return sorted_list;

    }

    private static void nm_merge(SortableLinkList left, SortableLinkList right, SortableLinkList merged_list)
    {
        Comparable left_item, left_item_next, right_item, right_item_next;
        left_item = left.get_from_front();
        left_item_next = left.get_from_front();
        right_item = right.get_from_front();
        right_item_next = right.get_from_front();
        do
        {
            if ( Comparison.is_less_than(left_item, right_item) || Comparison.is_equal_to(left_item, right_item) )
            {
                merged_list.add_at_back(left_item);
                left_item = left_item_next;
                left_item_next = left.get_from_front();
            }
            else
            {
                merged_list.add_at_back(right_item);
                right_item = right_item_next;
                right_item_next = right.get_from_front();
            }
        }
        while ( Comparison.is_less_than(left_item, left_item_next)
                        &&
                        Comparison.is_less_than(right_item, right_item_next)
                );

        if ( Comparison.is_less_than(left_item, left_item_next) )
        {
            merged_list.add_at_back(left_item);
            left.add_at_front(left_item_next);
        }
        else
        {
            left.add_at_front(left_item_next);
            left.add_at_front(left_item);
        }
        if ( Comparison.is_less_than(right_item, right_item_next) )
        {
            merged_list.add_at_back(right_item);
            right.add_at_front(right_item_next);
        }
        else
        {
            right.add_at_front(right_item_next);
            right.add_at_front(right_item);
        }

        return;
    }

}
