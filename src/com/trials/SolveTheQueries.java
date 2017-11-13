package com.trials;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SolveTheQueries {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for(int A_i = 0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
           int queryType=in.nextInt();
           switch(queryType){
           case 1: setQuery(A,in);break;
           case 2: askQuery(A,in);break;
           }
        }
        in.close();
    }

	private static void askQuery(int[] a,Scanner in) {
		int i = in.nextInt();
		int j = in.nextInt();
		int k = in.nextInt();
		int l = in.nextInt();
		int m = in.nextInt();	
		int ProductItoJ =1;
		for(int index=i-1;index<=j-1;index++){
			ProductItoJ*=a[index];
		}
		int ProductKtoL =1;
		for(int index=k-1;index<=l-1;index++){
			ProductKtoL*=a[index];
		}
		if(ProductKtoL!=0 && ProductItoJ%ProductKtoL==0 && m!=0)
			System.out.println((ProductItoJ/ProductKtoL)%m);
		else
			System.out.println(-1);
	}

	private static void setQuery(int[] a,Scanner in) {
		
		int i = in.nextInt();
		int j = in.nextInt();
		int x = in.nextInt();	
		Arrays.fill(a, i-1, j, x);
	}
}
