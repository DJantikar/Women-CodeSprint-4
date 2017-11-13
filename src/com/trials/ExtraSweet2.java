package com.trials;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ExtraSweet2 {

    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        
        long n = in.nextLong();
        int s = in.nextInt();
        Set<Long> chocsTaken = new HashSet<>();
        
        for(int a0 = 0; a0 < s; a0++){
            long l = in.nextLong();
            long r = in.nextLong();

            long sv= LongStream.rangeClosed(l, r).sum();
            LongStream.rangeClosed(l, r).forEach(chocsTaken::add);
            //Extra Left
            long leftPointer = l;
            do{
            	leftPointer--;
            }while(leftPointer>=0 && chocsTaken.contains(leftPointer));
            
            if(leftPointer>=0){
                sv+=leftPointer;
                chocsTaken.add(leftPointer);
            }
            //Extra Right
            long rightPointer = r;
            
            do{
            	rightPointer++;
            }while(rightPointer < n && chocsTaken.contains(rightPointer));
            
            if(rightPointer<n){
                sv+=rightPointer;
                chocsTaken.add(rightPointer);
            }
            
            System.out.println(sv);
        }
        in.close();
    }

	public static boolean isSafe(long n,long r,long val,Set<Long> chocsTaken){
		return val>=0 && val<n && val<=r && !chocsTaken.contains(val);
	}
}