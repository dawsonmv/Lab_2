import com.sorts.algorithm.LinkListSorts;
import com.sorts.data.SortableLinkList;

/*
 * Copyright (c) 2017.@author Dawson Valdes
 * Berkeley City College
 * CIS 27: Data Structure and Algorithms
 * Spring 2017
 *
 */

public class Main {


    public static void main(String[] args)
    {
        String[] elementary_sorts = new String[]
                                            {"I", "L", "O", "V", "E", "A", "L", "G", "O", "R", "I", "T", "H", "M", "S"};

        String[] shell_sort = new String[]
                                      {"E", "A", "S", "Y", "S", "H", "E", "L", "L", "S", "O", "R", "T", "Q", "U", "E", "S", "T", "I", "O", "N"};


        SortableLinkList<Comparable> sll;
        sll = new SortableLinkList<>();

        for( String s : elementary_sorts )
            sll.add_at_back(s);

        print_list(sll);
        StdOut.println();

        for( String s : shell_sort )
            sll.add_at_front(s);

        print_list(sll);
        StdOut.println();

        LinkListSorts.NaturalMergeSort(sll);

        print_list(sll);
        StdOut.println();

    }

    private static void print_list(SortableLinkList l)
    {
        for(Object o : l)
        {
            StdOut.print(o);
        }

    }

}
