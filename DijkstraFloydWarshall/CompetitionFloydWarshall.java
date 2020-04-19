import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	private double distances[][];
	private int fastest, mid, slowest;
	private int edgeNum;
	private int nodeNum;
	private int errorFlag;
	HashMap<Integer, HashMap<Integer, Double>> nodes = new HashMap<Integer, HashMap<Integer, Double>>();
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
    	errorFlag = 0;
		if((50>sA || sA>100) || (50>sB || sB>100) ||(50>sC || sC>100)) {
			errorFlag = 1;
		}
		
		if(filename != null) {
			try {
				Scanner reader = new Scanner(new FileReader(filename));
				ArrayList<Integer> speeds= new ArrayList<>();
				speeds.add(sA);speeds.add(sB);speeds.add(sC);
				this.fastest = Math.max(Math.max(sA,sB),sC);
				speeds.remove(Integer.valueOf(fastest));
				this.slowest = Math.min(Math.min(sA,sB),sC);;
				speeds.remove(Integer.valueOf(slowest));
				this.mid = speeds.get(0);
				
				int a = -1;
				int b = -1;
				double c = 0.0;
				this.nodeNum = reader.nextInt();
				this.edgeNum = reader.nextInt();

				if(nodeNum < 3 || edgeNum < 2) errorFlag = 1;
				ArrayList<Integer> from = new ArrayList<Integer>();
				ArrayList<Integer> to = new ArrayList<Integer>();

				while(reader.hasNext()) {
					a = reader.nextInt();
					b = reader.nextInt();
					c = reader.nextDouble();

					if(!from.contains(a)) {
						from.add(a);
					}
					if(!to.contains(b)) {
						to.add(b);
					}

					//check if node doesn't exist already
					if(!nodes.containsKey(a)) {
						nodes.put(a, new HashMap<Integer, Double>());
					}
					if(!nodes.containsKey(b)) {
						nodes.put(b, new HashMap<Integer, Double>());
					}
					HashMap<Integer, Double> neighbours = nodes.get(a);
					neighbours.put(b,c);
					nodes.replace(a, neighbours);
					//redirects every path into the other direction for this implementation
				}
				if(from.size() != to.size()) errorFlag = 1;
				
				reader.close();
			}catch(Exception e) {
				
			}
		}
        //TODO
    }

    
    void FloydWarshall() {
    	distances = new double[nodeNum][nodeNum];
    	for(int i = 0; i< nodeNum; i++) {
    		for(int j = 0; j<nodeNum; j++) {
    			distances[i][j] = Double.POSITIVE_INFINITY;
    		}
    	}
    	for(int i = 0; i<nodeNum; i++) {
    		distances[i][i] = 0;
    	}
    	for(int id1 : nodes.keySet()) {
    		HashMap<Integer, Double> neighbours = nodes.get(id1);
    		for(int id2 : neighbours.keySet()) {
    			distances[id1][id2] = neighbours.get(id2);
    		}
    	}

    	for(int k =0; k<nodeNum; k++) {
    		for(int i =0; i<nodeNum;i++) {
    			for(int j =0;j<nodeNum;j++) {
    				if(distances[i][k] + distances[k][j] < distances[i][j])
    					distances[i][j] = distances[i][k] + distances[k][j];
    			}
    		}
    	}
    	
    }
    
    
    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	int timeRequired = 0;
    	FloydWarshall();
		if(errorFlag == 0) {
			
			for(int source : nodes.keySet()) {
				double[] temp = distances[source].clone();
				for(int i =0; i<temp.length;i++) {
					if(temp[i] ==Double.POSITIVE_INFINITY) temp[i] = 0;
				}
				Arrays.sort(temp);
				int tempMax = (int) Math.ceil(temp[temp.length-1]*1000/slowest);
				if(timeRequired < tempMax) timeRequired = tempMax;
			}
			
			return timeRequired;
		}
		else{
			return -1;
		}
    }

}