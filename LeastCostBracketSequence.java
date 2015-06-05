import java.util.PriorityQueue;
import java.util.Scanner;

/** let ( = +1  and )= -1, cumulatively calculate the balance
 * For  a regular bracket sequence, balance should never be negative, 
 * at any point in the sequence , and should be zero at the end
 * 
 * If the ith scanned character is '?', 
 * ==> To enforce balance, substitute  with a ')'
 * 		balance += -1
 * 		cost += associated cost of ')'
 * 
 * ==> If balance < 0, (trouble in paradise)
 *   *** Greedy approach *** 
 *   		Remove the costliest ')' (will automatically make it positive- convinced)
 *   			balance += 2 (+1 for removing ) and +1 for adding ()
 *   			cost += cost of '(' - cost of ')'
 *   
 *   Now to get the costliest ')', maintain maxHeap
 *   
 *   If we reach the end of string, and balance is not 0, no solution is possible
				
*/
public class LeastCostBracketSequence {
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		
		char[] arr = input.toCharArray();
		
		int balance =0;
		int cost =0;
		PriorityQueue<Entry> maxHeap = new PriorityQueue<Entry>();
		
		for(int i=0; i < arr.length; i++){
			if(arr[i] == '(') balance++;
			else if(arr[i] == ')') balance--;
			else{
				arr[i] = ')';
				int openCost = in.nextInt();
				int closeCost = in.nextInt();
				cost += closeCost;
				balance--;
				maxHeap.add(new Entry(i,openCost-closeCost));
			}
			
			if(balance < 0){
				if(maxHeap.isEmpty()) {
					break;
				}
				
				Entry toRemove = maxHeap.poll();
				arr[toRemove.position] = '(';
				cost += toRemove.diffCost;
				balance += 2;
			}
		}
		
		if(balance == 0){
			System.out.println(cost);
			for(int l =0; l < arr.length; l++)
				System.out.print(arr[l]);
			System.out.println();
		}
		else System.out.println("-1");
		
	}
	
	
}

class Entry implements Comparable<Entry> {
    int position;
	int diffCost;
	
	Entry(int position, int diffCost){
		this.position = position;
		this.diffCost = diffCost;
	}
	
	@Override
	public int compareTo(Entry e1) {
		// TODO Auto-generated method stub
		return this.diffCost - e1.diffCost;
	}
	
}