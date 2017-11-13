package com.solutions;

import java.util.Arrays;

public class SegmentTree {

	public static void main(String[] args) {	
		int[] input = new int[]{-1,3,4,0,2,1};
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
//		for(int i : segmentTree)
//			System.out.print(i+" , ");
//		updateSegmentTree(input,segmentTree,2,4 ,-8);
//		System.out.println("\n*** Updated ****");
//		for(int i : segmentTree)
//			System.out.print(i+" , ");
		System.out.println(rangeMinQuery(segmentTree,2,5,0,input.length-1,0));
	}
	
	private static int rangeMinQuery(int[] segmentTree, int queryStartRange, int queryEndRange, int low,int high,int pos) {
		//Complete Overlap
		if(queryStartRange <= low && high <= queryEndRange)
			return segmentTree[pos];
		//No Overlap
		if(low > queryEndRange || high < queryStartRange)
			return Integer.MAX_VALUE;
		//Partial Overlap
		int mid = (low+high)/2;
		int leftMin = rangeMinQuery(segmentTree,queryStartRange,queryEndRange,low,mid,2*pos+1);
		int RightMin = rangeMinQuery(segmentTree,queryStartRange,queryEndRange,mid+1,high,2*pos+2);
		return Math.min(leftMin, RightMin);
	}

	private static void updateSegmentTree(int[] input, int[] segmentTree,int startRange,int endRange, int delta) {
		for(int i=startRange;i<=endRange;i++)
			input[i]+=delta;
		updateSegmentTree(segmentTree,startRange,endRange,0,input.length-1,0,delta);
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
	private static void updateSegmentTree(int[] segmentTree,int startRange,int endRange, int low, int high, int pos,int delta) {
		if(low==high){
			if(isInRange(low,startRange,endRange))
				segmentTree[pos]+=delta;
			return;
		}
		int mid = (low+high)/2;
		updateSegmentTree(segmentTree,startRange,endRange,low,mid,2*pos+1,delta);
		updateSegmentTree(segmentTree,startRange,endRange,mid+1,high,2*pos+2,delta);
		segmentTree[pos]=Math.min(segmentTree[2*pos+1], segmentTree[2*pos+2]);
	}

	private static boolean isInRange(int index,int startRange,int endRange) {		
		return index>=startRange && index<=endRange;
	}

}
