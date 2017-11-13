package com.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.LongStream;

public class ExtraSweetFinal {
	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    
	    long n = in.nextLong();
	    int s = in.nextInt();
	    
	    Map<Long,Long> brokenPiecesLeftIndices = new HashMap<>();
	    Map<Long,Long> brokenPiecesRightIndices = new HashMap<>();
//	    brokenPiecesLeftIndices.put(1L, n);
//	    brokenPiecesRightIndices.put(n, 1L);
	    
	    for(int a0 = 0; a0 < s; a0++){
	        long l = in.nextLong();
	        long r = in.nextLong();
	
	        assert(l>=0 && l<=r);
	        assert(r>=l && r<=1000000000);

	        //long dif = r-l+1;
	       // long sv = dif*l + dif*(dif-1)/2;
	        
	        long sv= LongStream.rangeClosed(l, r).sum();
	        
	        //Find Left Bonus

	        long nl = l, nr = r, extra_l, extra_r;
	        if(!brokenPiecesRightIndices.containsKey(l-1)){
	        	extra_l=Math.max(0, l-1);
	        	nl=extra_l;
	        }
	        else{
	        	nl=brokenPiecesRightIndices.get(l-1);
	        	extra_l=Math.max(0, nl-1);
	        	nl=extra_l;
	        }
	        if(brokenPiecesRightIndices.containsKey(nl-1)){
	        	nl=brokenPiecesRightIndices.get(nl-1);
	        }
	        
	      //Find right Bonus
	        if(!brokenPiecesLeftIndices.containsKey(r+1)){
	        	extra_r=(r+1<= n-1) ? r+1 : 0;
	        	nr=Math.min(r+1, n-1);
	        }
	        else{
	        	nr=brokenPiecesLeftIndices.get(r+1);
	        	extra_r=(nr+1<= n-1) ? nr+1 : 0;
	        	nr=Math.min(nr+1, n-1);
	        }
	        if(brokenPiecesLeftIndices.containsKey(nr+1)){
	        	nr=brokenPiecesLeftIndices.get(nr+1);
	        }
	        brokenPiecesLeftIndices.put(nl, nr);
	        brokenPiecesRightIndices.put(nr, nl);
	        System.out.println(sv+extra_l+extra_r);
	    }
	    in.close();
	}

	public static boolean isSafe(long n,long r,long val,Set<Long> chocsTaken){
		return val>=0 && val<n && val<=r && !chocsTaken.contains(val);
	}
}

