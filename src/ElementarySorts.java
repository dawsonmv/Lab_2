//package com.sorts;


/**
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

            // find the smallest by interating over the remainder of the array
            for ( int j = i + 1; j < len; j++ )
            {
                // if the the contents are smaller than the small make that the new small index
                if ( less_than( comp[j] , comp[small] ) )
                { small = j; }
            }

            // if a smaller index was found switch the items
            if ( small != i )
            {
                swap(comp, i , small);
            }

            print(comp);

        }

    }

    public static void InsertionSort(Comparable[] comp)
    {
        // store length for reduced access
        int len = comp.length;

        // begin sorting at index 0 , incresing in order
        for( int i = 0; i < len; i++ )
        {

             // keep swapping [i] backwards until the [] behind it is smaller or [0] is reached
            for ( int j = i; j > 0 && less_than(comp[j] , comp[j-1]); j-- )
            {
                swap(comp , j , j-1 );
            }
            print(comp);
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
                if ( less_than( comp[i] , comp[i-1] ) )
                {
                    swap(comp,i-1 , i);
                    if(sorted){sorted = false;}
                }
            }
            print(comp);
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
                for ( int j = i; j >= sub && less_than( comp[j] , comp[j-sub] ); j-=sub)
                {
                    swap(comp , j , j-sub);
                    // print(comp);
                }
             // print(comp);
            }
            sub /= 3 ;
            print(comp);

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
        for(int k = lo; k <= hi; k++)
        {
            b[k] = a[k];
        }

        int i = lo;
        int j = mid+1;

        for ( int k = lo; k <= hi ; k++ )
        {
            if ( i > mid ) a[k] = b[j++];
            else if( j > hi ) a[k] = b[i++];
            else if( less_than(b[j] , a[i]) ) a[k] = b[j++];
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


    public static void Quicksort(Comparable[] comp)
    {
        StdRandom.shuffle(comp);
        quick_sort(comp, 0, comp.length -1);
    }

    private static void quick_sort( Comparable[] comp, int lo, int hi)
    {

    }



    private static boolean less_than(Comparable a , Comparable b)
    {
        return ( a.compareTo(b) < 0 );
    }

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
            if ( less_than( c[i] , c[i-1] ) ) { return false; }
        }
        return true;
    }

    private static void print( Object[] o )
    {
        int length = o.length;
        StdOut.println();
        for ( int i = 0; i < length; i++ )
        {
            StdOut.print(o[i]);
        }
    }





}
