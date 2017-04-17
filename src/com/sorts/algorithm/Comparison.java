package com.sorts.algorithm;/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

/**
 * Created by dawsonvaldes on 4/13/17.
 *
 * com.sorts.algorithm.Comparison functions for comparable objects
 *
 */
public class Comparison
{

    private Comparison(){}

    public static boolean is_less_than(Comparable a , Comparable b)
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
            int c = a.compareTo(b);
            return c < 0;
        }
    }

    public static boolean is_equal_to(Comparable a , Comparable b)
    {
        int c = a.compareTo(b);
        return c == 0;
    }

}
