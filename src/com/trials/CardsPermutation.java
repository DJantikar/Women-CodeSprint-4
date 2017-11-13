package com.trials;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class CardsPermutation {

	static long solve(int[] x) {
		
		int n = x.length;
		
		StringBuilder sb = new StringBuilder();
		IntStream.rangeClosed(1, n).forEach(num -> sb.append(num));
		String input = new String(sb);
		
		ArrayList<String> permutations = new ArrayList<>();
		
		findPermutations(input,permutations,"",n);
		
		long sum=0;
		
		for(int i=1;i<=permutations.size();i++){
			String permutation = permutations.get(i-1);
			boolean isSafe=true;
			for(int j=0;j<x.length;j++){
				int chkVal = x[j];
				if(chkVal==0)
					continue;
				int permVal = Integer.parseInt(permutation.substring(j, j+1));
				if(chkVal!=permVal){
					isSafe=false;
					break;
				}
			}
			if(isSafe)
				sum+=i;
		}
		//System.out.println(permutations);
		return sum;
    }

    private static void findPermutations(String input, ArrayList<String> permutations, String prefix,int r) {
		if(r==0){
			permutations.add(prefix);
			return;
		}
		for(int i=0;i<input.length();i++){
			char ch = input.charAt(i);
			String newPrefix = prefix+ch;
			String newInput = input.substring(0, i)+input.substring(i+1);
			findPermutations(newInput,permutations,newPrefix,r-1);
		}
	}

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        long result = solve(a);
        System.out.println(result);
        in.close();
    }
}
