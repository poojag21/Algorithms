import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Banyan_Batcher {
	
	static class Graph { 
        int noOfVertex; 
        LinkedList<Integer> adjList[]; 
        Graph(int noOfVertex) { 
            this.noOfVertex = noOfVertex; 
            adjList = new LinkedList[noOfVertex]; 
            for(int i = 0; i < noOfVertex; i++){ 
            	adjList[i] = new LinkedList<>(); 
            } 
        } 
    } 
      
    static void addEdgeBetweenNodes(Graph graph, int src, int dest) { 
        graph.adjList[src].add(dest); 
    }
	
	private static boolean isPowerOfTwo(int n) { 
		return (int)(Math.ceil((Math.log(n) / Math.log(2)))) ==  
				(int)(Math.floor(((Math.log(n) / Math.log(2))))); 
	} 
	
	private static int getPowerOfTwo(int network) {
		
		int power = 0;
		if (isPowerOfTwo(network)){
			while (network != 1){
				network = network/2;
				power++;
			}
		}
		return power;
		
	}
	
	private static void createEdges(Graph graph, int numberOfRows) {
		
		int iterativeLength = getPowerOfTwo(numberOfRows);
		int maxVertices = graph.noOfVertex;
		int iterativeArray[] = new int[iterativeLength];
		int vertex = 0;
		for (int i=0; i<iterativeLength; i++){
			iterativeArray[i] = (int) Math.pow(2, i);
		}
		
		for (int i=iterativeLength-1; i>=0; i--){
			int value = iterativeArray[i];
			int j=0;
			
			while (j < numberOfRows){
				int interval = 0;
				while (interval < value){
					addEdgeBetweenNodes(graph, vertex, vertex+numberOfRows);
					addEdgeBetweenNodes(graph, vertex, vertex+numberOfRows+value);
					interval++;
					j++;
					vertex++;
				}
				
				int copyInterval = vertex;
				int upperLimit = vertex+value;
				while(copyInterval < upperLimit){
					for (Integer node : graph.adjList[vertex-value]){
						addEdgeBetweenNodes(graph, vertex, node);
					}
					vertex++;
					copyInterval++;
					j++;
				}
				
			}
		}
		int outputPort = 0;
		for (int i=vertex; i<maxVertices; i++){
			addEdgeBetweenNodes(graph, i, outputPort++);
			addEdgeBetweenNodes(graph, i, outputPort++);
		}
	}
	
	private static String appendZeros(String binaryNumer, int maxDigitLength) {
		
		StringBuilder str = new StringBuilder(binaryNumer);
		while (str.length() != maxDigitLength){
			str.insert(0, "0");
		}
		return str.toString();
	}
	
	private static String convertDecimalToBinary(int input, int powerOfTwo) {
		
		String binaryNumber = Integer.toString(input,2);
		if (binaryNumber.length() < powerOfTwo){
			binaryNumber = appendZeros(binaryNumber,powerOfTwo);
		}
		return binaryNumber;
	}
	
	private static void dfsTraverse(Graph graph, int source, int input, int powerOfTwo, int index, LinkedList<Integer>[] path) {
		
		path[input].add(source);
		String binaryNumer = convertDecimalToBinary(input, powerOfTwo);
		if (index < powerOfTwo){
			int neibhourIndex = Character.getNumericValue(binaryNumer.charAt(index));
			int neibhour = graph.adjList[source].get(neibhourIndex);
			dfsTraverse(graph, neibhour, input, powerOfTwo, index+1, path);
		}
		
	}
	
	private static int getRandomNumberInRange(int min, int max) {
		
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
		
	}
	
	private static Integer[] getRandomInput(int network) {
		ArrayList<Integer> randomList = new ArrayList<>();
		for (int i=0; i<network; i++){
			randomList.add(i);
		}
		Collections.shuffle(randomList);
		Integer[] randomInput = new Integer[randomList.size()]; 
		randomInput = randomList.toArray(randomInput);
		return randomInput;
	}
	
	private static void exchange(Integer[] sortedInput, int i, int j) {
		int t=sortedInput[i];
        sortedInput[i]=sortedInput[j];
        sortedInput[j]=t;
	}
	
	private static void oddEvenMerge(Integer[] sortedInput, int i, int length, int j) {
		int mid=j*2;
        if (mid<length) {
            oddEvenMerge(sortedInput,i, length, mid);      
            oddEvenMerge(sortedInput,i+j, length, mid);    
            for (int k=i+j; k+j<i+length; k+=mid){
            	if (sortedInput[k]>sortedInput[k+j])
                    exchange(sortedInput,k, k+j);
            }
        }
        else
        	if (sortedInput[i]>sortedInput[i+j])
                exchange(sortedInput,i, i+j);
	}
	

	private static void mergeSort(Integer[] sortedInput, int i, int length) {
		if (length>1)
        {
            int mid=length/2;
            mergeSort(sortedInput,i, mid);
            mergeSort(sortedInput,i+mid, mid);
            oddEvenMerge(sortedInput,i, length, 1);
        }
	}

	private static Integer[] batcherSort(Integer[] randomInput) {
		Integer[] sortedInput = new Integer[randomInput.length];
		sortedInput = randomInput.clone();
		mergeSort(sortedInput,0,sortedInput.length);
		return sortedInput;
	}
	
	private static void performBatcherBanyanSimulation(int network) {
		Integer[] randomInput = getRandomInput(network);
		Integer[] input = batcherSort(randomInput);
		LinkedList<Integer> path[] = new LinkedList[network];
		for(int i = 0; i < path.length; i++){ 
			path[i] = new LinkedList<>(); 
        }
		int powerOfTwo = getPowerOfTwo(network);
		int noOfSwitches = powerOfTwo * (network/2);
		Graph graph = new Graph(noOfSwitches); 
		createEdges(graph,network/2);
		
		for (int i=0; i<input.length; i++){
			int source = i%(network/2);
			int packet = input[i];
			dfsTraverse(graph, source, packet, powerOfTwo, 0, path);
		}
		
		System.out.printf("Random Input Array: %s \n", Arrays.toString(randomInput));
		System.out.println();
		System.out.printf("Sorted Input Array: %s \n", Arrays.toString(input));
		System.out.println();
		for (int i=0; i<input.length; i++){
			LinkedList<Integer> currentPath = path[input[i]];
			System.out.println("Path for Packet " + input[i] + " (Tag: " + convertDecimalToBinary(input[i], powerOfTwo) + "):");
			StringBuilder str = new StringBuilder();
			for (int j=0; j<currentPath.size(); j++){
				if (j != currentPath.size()-1){
					str.append(currentPath.get(j));
					str.append("->");					
				} else {
					str.append("Output-Port ");
					str.append(currentPath.get(j));
				}
			}
			System.out.println(str.toString());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int network = 8;
		performBatcherBanyanSimulation(network);
	}

}
