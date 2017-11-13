package com.trials;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ExtraSweet {

    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        
        long n = in.nextLong();
        int s = in.nextInt();
       
        List<Long>  chocolateAvailable  = new ArrayList<>();
        LongStream.range(0, n).forEach(chocolateAvailable::add);
        
        for(int a0 = 0; a0 < s; a0++){
            long l = in.nextLong();
            long r = in.nextLong();
            long sv=0;
            long val=l;
            for(;isSafe(n,r,val,chocolateAvailable) ;val++){
                sv+=val;
                chocolateAvailable.set(chocolateAvailable.indexOf(val),-1L);             
            }
            //Extra Left
            do{
                l--;
            }while(l>=0 && chocolateAvailable.indexOf(l)==-1L);
            
            if(l>=0){
                sv+=l;
                chocolateAvailable.set(chocolateAvailable.indexOf(l),-1L);  
            }
            //Extra Right
            long rightPointer = (val==r+1) ? r : val;
            
            do{
            	rightPointer++;
            }while(rightPointer < n && chocolateAvailable.indexOf(rightPointer)==-1L);
            
            if(rightPointer<n){
                sv+=rightPointer;
                chocolateAvailable.set(chocolateAvailable.indexOf(rightPointer),-1L); 
            }
            
            System.out.println(sv);
        }
        in.close();
    }

	public static boolean isSafe(long n,long r,long val,List<Long> chocolateAvailable){
		return val>=0 && val<n && val<=r && chocolateAvailable.get(chocolateAvailable.indexOf(val))!=-1L;
	}
}