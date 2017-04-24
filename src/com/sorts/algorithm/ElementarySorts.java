package com.sorts.algorithm;

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**@author Dawson Valdes
 *
 * Elementary Sorts
 * for Arrays of
 * Comparable Items
 *
 * Created by dawsonvaldes on 3/25/17.
 */

public class ElementarySorts
{

    private ElementarySorts(){}

    public static void SelectionSort( Comparable[] comp )
    {
        // store length for reduced access
        int len = comp.length;

        // create a space for the small index
        int small;

        // begin sorting at index 0
        for( int i = 0; i < len; i++ )
        {

            // set current index to smallest index of the array
            small = i;

            // find the smallest by iterating over the remainder of the array
            for ( int j = i + 1; j < len; j++ )
            {
                // if the the contents are smaller than the small make that the new small index
                if ( Comparison.is_less_than(comp[j], comp[small]) )
                { small = j; }
            }

            // if a smaller index was found switch the items
            if ( small != i )
            {
                swap(comp, i , small);
            }

            //  print(comp);

        }

    }

    public static void InsertionSort(Comparable[] comp)
    {
        // store length for reduced access
        int len = comp.length;

        // begin sorting at index 0 , increasing in order
        for( int i = 0; i < len; i++ )
        {

             // keep swapping [i] backwards until the [] behind it is smaller or [0] is reached
            for ( int j = i; j > 0 && Comparison.is_less_than(comp[j], comp[j - 1]); j-- )
            {
                swap(comp , j , j-1 );
            }
            //  print(comp);
        }

    }

    public static void BubbleSort(Comparable[] comp)
    {

        int len = comp.length;
        boolean sorted = false;

        while(!sorted)
        {

            sorted = true;
            for ( int i = 1; i < len; i++ )
            {
                if ( Comparison.is_less_than(comp[i], comp[i - 1]) )
                {
                    swap(comp,i-1 , i);
                    if(sorted){sorted = false;}
                }
            }
            // print(comp);
        }

    }

    public static void ShellSort(Comparable[] comp)
    {

        int len = comp.length;
        int sub = 1;

        while( sub < len/3 ) sub = (3 * sub) + 1;

        while( sub >= 1 )
        {
            for ( int i = sub; i < len ; i++ )
            {
                for ( int j = i; j >= sub && Comparison.is_less_than(comp[j], comp[j - sub]); j -= sub )
                {
                    swap(comp , j , j-sub);
                    // print(comp);
                }
             // print(comp);
            }
            sub /= 3 ;
            // print(comp);
        }

    }

    public static void TDMergeSort(Comparable[] comp)
    {
        Comparable[] temp = new Comparable[comp.length];
        TD_sort(comp,temp,0,comp.length-1);
    }

    private static void TD_sort(Comparable[] a ,Comparable[] b, int lo, int hi)
    {

        if ( lo >= hi ) return;

        int mid = lo + ( hi - lo ) / 2;

        TD_sort( a , b , lo , mid);
        TD_sort( a , b , mid + 1 , hi);

        merge( a , b , lo, mid , hi);

    }

    private static void merge( Comparable[] a, Comparable[] b, int lo , int mid , int hi)
    {
        b = a.clone();

        int i = lo;
        int j = mid+1;

        for ( int k = lo; k <= hi ; k++ )
        {
            if ( i > mid ) a[k] = b[j++];
            else if( j > hi ) a[k] = b[i++];
            else if ( Comparison.is_less_than(b[j], a[i]) ) a[k] = b[j++];
            else a[k] = b[i++];
        }

    }

    public static void BUMergeSort(Comparable[] comp)
    {
        int len = comp.length;

        Comparable[] temp = new Comparable[len];
        for(int x = 1; x < len; x *= 2)
        {
            for ( int lo = 0; lo < len - x; lo += x+x )
            {
                int mid = lo+x-1;
                int hi = Math.min(lo+x+x-1,len-1);
                merge(comp,temp,lo,mid,hi);
            }
        }
    }

    public static void QuickSort(Comparable[] comp)
    {
        //StdRandom.shuffle(comp);
        quick_sort(comp, 0, comp.length -1);
    }

    private static void quick_sort( Comparable[] comp, int lo, int hi)
    {
        if( hi > lo )
        {
            int j;
            j = partition(comp, lo, hi);

            quick_sort(comp, lo, j - 1);
            quick_sort(comp, j + 1, hi);
        }
    }

    private static int partition( Comparable[] comp, int lo, int hi) //logical optimizations
    {
        boolean done , max_i , min_j , big_i , little_j;
        Comparable value;
        int i, j;

        max_i = min_j = big_i = little_j = false;
        value = comp[lo];
        i = lo;
        j = hi;

        do
        {
            do
            {
                i++;
                if( !max_i )
                {
                    max_i = i == hi;
                    if ( !big_i ) big_i = Comparison.is_less_than(comp[i], value);
                }
            }
            while( !(big_i || max_i) );

            do
            {
                j--;
                if( !min_j )
                {
                    min_j = j == lo;
                    if ( !little_j ) little_j = Comparison.is_less_than(value, comp[j]);
                }
            }
            while( !(little_j || min_j) );

            done = i >= j;
            if( !done ) swap(comp , i , j);
        }
        while(!done);

        swap(comp , lo , j);
        return j;

    }

    /**
     private static boolean less_than(Comparable a , Comparable b)
     {
     int c = a.compareTo(b);
     return c < 0;
     }
     */
    private static void swap(Object[] a , int b , int c)
    {
        Object _swap = a[b];
        a[b] = a[c];
        a[c] = _swap;
    }

    private static boolean is_sorted(Comparable[] c, int a , int b )
    {
        for ( int i = a; i < b; i++ )
        {
            if ( Comparison.is_less_than(c[i], c[i - 1]) ) { return false; }
        }
        return true;
    }

    private static Comparable tukeys_ninether(Comparable[] c)
    {

        int len = c.length;
        float sample_rate = len / 9;

        Comparable sample[] = new Comparable[9];

        for ( int i = 0; i < 9; i++ )
        {
            int index = (int) Math.floor(i * sample_rate);
            sample[i] = c[index];
        }

        return median_of_three(median_of_three(sample[0], sample[1], sample[2]),
                               median_of_three(sample[3], sample[4], sample[5]),
                               median_of_three(sample[6], sample[7], sample[8]));

    }

    private static Comparable median_of_three(Comparable one, Comparable two, Comparable three)
    {

        int smallest;

        if ( Comparison.is_less_than(one, two) )
            smallest = Comparison.is_less_than(one, three) ? 1 : 3;
        else
            smallest = Comparison.is_less_than(two, three) ? 2 : 3;

        switch ( smallest )
        {
            case 1:
                return Comparison.is_less_than(two, three) ? two : three;

            case 2:
                return Comparison.is_less_than(one, three) ? one : three;

            case 3:
                return Comparison.is_less_than(one, two) ? one : two;

            default:
                return null;
        }

    }


    /**
private static void print( Object[] o )
{

     StdOut.println();
for ( Object i:o ) StdOut.print(i);

     }
*/

}
