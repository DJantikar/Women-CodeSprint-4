package com.trials;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class GraphNode{
	int id;
	int data;
	int bulbsTurnedOn;
	int roomsVisited;
	List<GraphNode> adjNodes= new ArrayList<GraphNode>();
	GraphNode(int id,int data){
		this.id=id;
		this.data=data;
	}
}



public class AfraidOfTheDark {

	
	static HashSet<Integer> visited = new HashSet<>();
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            HashMap<Integer,GraphNode> graph = new HashMap<>();
            boolean allOn=true;
            for(int s_i = 1; s_i <= n; s_i++){
            	int onOffData=in.nextInt();
            	graph.put(s_i, new GraphNode(s_i,onOffData));
            	if(allOn && onOffData==0)
            		allOn=false;
            }
            
            for(int a1 = 0; a1 < n-1; a1++){
                int a = in.nextInt();
                int b = in.nextInt();
                graph.get(a).adjNodes.add(graph.get(b));
                graph.get(b).adjNodes.add(graph.get(a));
            }
            if(allOn){
            	for(int node=1;node<=n;node++){
            		System.out.println(n);
            	}
            	continue;
            }
            //printTree(graph);
            
            for(int key : graph.keySet()){
            	HashMap<Integer,GraphNode> graphCopy = new HashMap<>();
            	graphCopy.putAll(graph);
            	doDFS(key,graphCopy);
            	printTree2(graphCopy);
            	reset();
            }
        }
        in.close();
	}

	private static void doDFS(int root, HashMap<Integer, GraphNode> graph) {
		if(visited.contains(root))
			return;	
		
		visited.add(root);
		
		if(graph.get(root).adjNodes.size()==0){
			graph.get(root).roomsVisited=1;
			graph.get(root).bulbsTurnedOn= (graph.get(root).data==0) ? 1 : 0;
			return;
		}
		boolean evenBulbsOnExpected = graph.get(root).data%2==1 ? true : false;
		for(GraphNode child : graph.get(root).adjNodes){
			doDFS(child.id,graph);
		}
		for(GraphNode child : graph.get(root).adjNodes){
			if(isSafe(child,evenBulbsOnExpected,graph,root)){
				graph.get(root).roomsVisited=1+child.roomsVisited;
				graph.get(root).bulbsTurnedOn= child.bulbsTurnedOn + (evenBulbsOnExpected ? 0 : 1);
			}
		}		
	}
	private static boolean isSafe(GraphNode child, boolean evenBulbsOnExpected,HashMap<Integer, GraphNode> graph,
			int root) {
		boolean update = false;
		if(evenBulbsOnExpected && 
		   child.bulbsTurnedOn%2==0 && 
		   graph.get(root).roomsVisited < 1+child.roomsVisited ){
		   
		   update=true;
		   
		}
		else if(!evenBulbsOnExpected && 
				   child.bulbsTurnedOn%2==1 && 
				   graph.get(root).roomsVisited < 1+child.roomsVisited ){
		   
		   update=true;
		
		}
		return update;
	}

	private static void printTree(HashMap<Integer, GraphNode> graph) {
		for(GraphNode g : graph.values()){
			System.out.print(g.id+" : ");
			for(GraphNode adj : g.adjNodes)
				System.out.print(adj.id+" , ");
			System.out.println();
		}	
	}
	private static void printTree2(HashMap<Integer, GraphNode> graph) {
		for(GraphNode g : graph.values()){
			System.out.print(g.id+" : ");
			for(GraphNode adj : g.adjNodes)
				System.out.print(adj.id+" , ");
			System.out.print(" | roomsVisited : "+g.roomsVisited);
			System.out.print(" | bulbsTurnedOn : "+g.bulbsTurnedOn);
			System.out.println();
		}	
	}
	/*private static void doBFS(int root,HashMap<Integer, GraphNode> graph){
		Queue<Integer> q = new LinkedList<>();		
		q.add(root);
		visited.add(root);
		while(!q.isEmpty()){
			
			int ele = q.poll();
			visited.add(ele);
			
			if(graph.get(ele).data==0)
				bulbsTurnedOn++;

			if(bulbsTurnedOn%2==0)
				maxRoomsVisited=visited.size();
			
			for(GraphNode child : graph.get(ele).adjNodes){
				if(!visited.contains(child.id)){
					q.add(child.id);					
				}
			}
		}
	}*/
	private static void reset() {
		visited.clear();
	}
}
