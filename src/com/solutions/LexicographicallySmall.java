package com.solutions;

import java.util.Scanner;

public class LexicographicallySmall {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] z = new String[n];
        for(int z_i = 0; z_i < n; z_i++){
            z[z_i] = in.next();
        }
        String[] st=constructSegTree(z);
//        System.out.println("*****");
//      for(String string : st)
//      	System.out.print(string+" ");
//      System.out.println("\n*****");
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int l = in.nextInt();
            int r = in.nextInt();
            String s = in.next();
            System.out.println(rangeMinQuery(st,0,z.length-1,l-1,r-1,0,s));
        }
        in.close();
	}

	private static int rangeMinQuery(String[] segTree, int low, int high, 
									  int startRange, int endRange, int pos,String s) {
		if(low>high)
			return 0;
		//no overlap
		if(startRange>high || endRange < low)
			return 0;
		//complete overlap
		if(low>=startRange && high<=endRange){
			if(segTree[pos]==null || (segTree[pos].compareTo(s) >0))
				return 0;	
			if(segTree[pos]!=null){
				int newPos = (segTree[pos].equals(segTree[2*pos+1])) ? 2*pos+2 : 2*pos+1;
				int mid = (low+high)/2;
				if(newPos==2*pos+2)
					return 1+rangeMinQuery(segTree, mid+1, high, startRange, endRange, 2*pos+2,s);
				else
					return 1+rangeMinQuery(segTree, low, mid, startRange, endRange, 2*pos+1,s);
			}
			else
				return 0;
		}
		//partial overlap
		int mid=(low+high)/2;
		return rangeMinQuery(segTree, low, mid, startRange, endRange, 2*pos+1,s)
			   + rangeMinQuery(segTree, mid+1, high, startRange, endRange, 2*pos+2,s);
	}

	private static String[] constructSegTree(String[] input) {
		int nextPowerOf2 = 0;
		int zLen = input.length;
		do{
			zLen/=2;
			nextPowerOf2++;
		}while(zLen>0);
		int segLen = (int) (((Math.pow(2, nextPowerOf2)) * 2) -1);
		String[] segTree = new String[segLen];
		constructSegTree(input,0,input.length-1,segTree,0);
		return segTree;
	}

	private static void constructSegTree(String[] input, int low, int high, String[] segTree, int pos) {
		if(low>high)
			return;
		if(low==high){
			segTree[pos]= input[low];
			return;
		}
		int mid = (low+high)/2;
		constructSegTree(input,low,mid,segTree,2*pos+1);
		constructSegTree(input,mid+1,high,segTree,2*pos+2);
		segTree[pos]=segTree[2*pos+1].compareTo(segTree[2*pos+2])<=0 ? segTree[2*pos+1] : segTree[2*pos+2];
	}

}
