package com.solutions;

import java.util.Arrays;

public class LazySegmentTree {

	public static void main(String[] args) {	
		int[] input = new int[]{-1,3,4,10,2,1};
		int  n = input.length;
		int nextPowerOf2 =0;
		do{
			nextPowerOf2++;
			n/=2;
		}while(n>0);
		//System.out.println(nextPowerOf2);
		int lengthOfSegTree = (int) (((Math.pow(2, nextPowerOf2)) * 2) -1) ;
		int[] segmentTree = new int[lengthOfSegTree];
		Arrays.fill(segmentTree, Integer.MAX_VALUE);
		constructSegmentTree(input,segmentTree,0,input.length-1,0);
		for(int i : segmentTree)
			System.out.print(i+" , ");
		System.out.println("\n*******");
		updateSegmentTree(input,segmentTree,2,4 ,-8);
//		System.out.println("\n*** Updated ****");
//		for(int i : segmentTree)
//			System.out.print(i+" , ");
		System.out.println(rangeMinQueryLazily(segmentTree,new int[segmentTree.length],2,5,0,input.length-1,0));
	}
	
	private static int rangeMinQueryLazily(int[] segmentTree, int[] lazy,int queryStartRange, int queryEndRange, int low,int high,
											int pos) {
		if(low>high)
			return Integer.MAX_VALUE;
		
		if(lazy[pos]!=0){
			segmentTree[pos]=lazy[pos];
			if(low!=high){
				lazy[2*pos+1]+=lazy[pos];
				lazy[2*pos+2]+=lazy[pos];
			}
			lazy[pos]=0;
		}
		//Complete Overlap
		if(queryStartRange <= low && high <= queryEndRange)
			return segmentTree[pos];
		//No Overlap
		if(low > queryEndRange || high < queryStartRange)
			return Integer.MAX_VALUE;
		//Partial Overlap
		int mid = (low+high)/2;
		int leftMin = rangeMinQueryLazily(segmentTree,lazy,queryStartRange,queryEndRange,low,mid,2*pos+1);
		int RightMin = rangeMinQueryLazily(segmentTree,lazy,queryStartRange,queryEndRange,mid+1,high,2*pos+2);
		return Math.min(leftMin, RightMin);
	}

	private static void updateSegmentTree(int[] input, int[] segmentTree,int startRange,int endRange, int delta) {
		for(int i=startRange;i<=endRange;i++)
			input[i]+=delta;
		int[] lazy = new int[segmentTree.length];
		updateSegmentTreeLazily(segmentTree,lazy,startRange,endRange,0,input.length-1,0,delta);
	}

	private static void constructSegmentTree(int[] input, int[] segmentTree, int low, int high, int pos) {
		if(low==high){
			segmentTree[pos]=input[low];
			return;
		}
		int mid = (low+high)/2;
		constructSegmentTree(input,segmentTree,low,mid,2*pos+1);
		constructSegmentTree(input,segmentTree,mid+1,high,2*pos+2);
		segmentTree[pos]=Math.min(segmentTree[2*pos+1], segmentTree[2*pos+2]);
	}
	private static void updateSegmentTreeLazily(int[] segmentTree,int[] lazy, int startRange,int endRange, int low, int high, int pos,int delta) {
		if(low>high)
			return;
		if(lazy[pos]!=0){
			segmentTree[pos]+=lazy[pos];
			if(low!=high){
				lazy[2*pos+1]+=lazy[pos];
				lazy[2*pos+2]+=lazy[pos];
			}
			lazy[pos]=0;
		}
		//Total Overlap
		if(low>=startRange && high<=endRange){
			segmentTree[pos]+=delta;
			if(low!=high){
				lazy[2*pos+1]+=delta;
				lazy[2*pos+2]+=delta;
			}
			return;
		}
		//No Overlap
		if(low>endRange || high<startRange)
			return;
		//Partial Overlap
		int mid = (low+high)/2;
		updateSegmentTreeLazily(segmentTree,lazy,startRange,endRange,low,mid,2*pos+1,delta);
		updateSegmentTreeLazily(segmentTree,lazy,startRange,endRange,mid+1,high,2*pos+2,delta);
		segmentTree[pos]=Math.min(segmentTree[2*pos+1], segmentTree[2*pos+2]);
	}

	private static boolean isInRange(int index,int startRange,int endRange) {		
		return index>=startRange && index<=endRange;
	}

}
