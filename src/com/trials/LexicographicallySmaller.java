package com.trials;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LexicographicallySmaller {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] z = new String[n];
        for(int z_i = 0; z_i < n; z_i++){
            z[z_i] = in.next();
        }
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int l = in.nextInt();
            int r = in.nextInt();
            String s = in.next();
            int count=0;
            for(int index=l-1;index<=r-1;index++){
            	if(z[index].compareTo(s)<=0)
            		count++;
            }
            System.out.println(count);
        }
        in.close();
    }
}
