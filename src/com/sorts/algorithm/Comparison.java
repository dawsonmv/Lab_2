package com.sorts.algorithm;/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

import java.util.Comparator;

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**
 * Created by Dawson Valdes on 4/13/17.
 *
 * com.sorts.algorithm.Comparison functions for comparable objects
 *
 */
@SuppressWarnings("unchecked")
public class Comparison
{

    private static final Comparator _compare = (o1, o2) ->
    {
        int c;
        c = o1.hashCode() - o2.hashCode();
        return c;
    };

    private Comparison(){}


    @SuppressWarnings("unchecked")
    public static boolean is_less_than(Comparable a , Comparable b)
    {
        if ( a == null || b == null )
        {
            return false;
        }
        else
        {
            int c;
            c = a.compareTo(b);
            return c < 0;
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean is_less_than(Object a, Object b)
    {
        if ( a == null || b == null )
        {
            return false;
        }
        else
        {
            int c;
            c = _compare.compare(a, b);
            return c < 0;
        }
    }


    public static boolean is_equal_to(Comparable a , Comparable b)
    {

        if ( a == null && b == null )
        {
            return true;
        }
        else if ( a == null || b == null )
        {
            return false;
        }
        else
        {
            boolean c;
            c = a.equals(b);
            return c;
        }

    }

    public static boolean is_equal_to(Object a, Object b)
    {

        if ( null == a && null == b )
        {
            return true;
        }
        else if ( null == a || null == b )
        {
            return false;
        }
        else
        {
            int c;
            c = _compare.compare(a, b);
            return c == 0;
        }

    }


    @SuppressWarnings("unchecked")
    public static boolean is_greater_than(Comparable a, Comparable b)
    {
        if ( a == null )
        {
            return false;
        }
        else if ( b == null )
        {
            return true;
        }
        else
        {
            int c;
            c = a.compareTo(b);
            return c > 0;
        }
    }

    public static boolean is_greater_than(Object a, Object b)
    {
        if ( a == null )
        {
            return false;
        }
        else if ( b == null )
        {
            return true;
        }
        else
        {
            int c;
            c = _compare.compare(a, b);
            return c > 0;
        }
    }


}
