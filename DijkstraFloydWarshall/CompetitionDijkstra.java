import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/*
 * 
 * 
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
 * This class implements the competition using Dijkstra's algorithm
 * 
 * @author Vsevolod Syrtsov - 18323202
 * @github https://github.com/futurecertificate/CSU22010/tree/master/DijkstraFloydWarshall
 *
 *
 */

public class CompetitionDijkstra {

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */

	class RoutingTable{

		ArrayList<Integer> nodeIDs;
		ArrayList<Double> cumCosts;
		ArrayList<Integer> neighbourIDs;

		RoutingTable(){
			nodeIDs = new ArrayList<Integer>();
			cumCosts = new ArrayList<Double>();
			neighbourIDs = new ArrayList<Integer>();
		}
		
		public boolean contains(int id) {
			return nodeIDs.contains(id);
		}
		public int getNodeID(int index) {
			return nodeIDs.get(index);
		}

		public double getCumCost(int index) {
			return cumCosts.get(index);
		}

		public void addNode(int nodeID, double cumCost, int neighbourID) {
			nodeIDs.add(nodeID);
			cumCosts.add(cumCost);
			neighbourIDs.add(neighbourID);

		}
		
		public int maxTime() {
			ArrayList<Double> sortedCosts = cumCosts;
			Collections.sort(sortedCosts);
			return (int) Math.ceil(sortedCosts.get(sortedCosts.size()-1)*1000/slowest);
		}
		
		public int size() {
			return nodeIDs.size();
		}
	}

	
	private class TentativeNodesList{
		
		ArrayList<double[]> nodeList; 
		
		TentativeNodesList(){
			nodeList = new ArrayList<>();
		}
		
		public void add(double[] node) {
			nodeList.add(node);
		}
		
		public double[] getLowest() {
			double min = Double.MAX_VALUE;
			double[] minNode = {};
			for(double[] e : nodeList) {
				if(min > e[1]) {
					min = e[1];
					minNode = e;
				}
			}
			this.remove(minNode);
			return minNode;
		}
		
		public void remove(double[] node) {
			nodeList.remove(node);
		}
		
		public void removeById(int id) {
			ArrayList<double[]> clone = (ArrayList<double[]> )nodeList.clone();
			for(double[] e : clone) {
				if((int)e[0] == id)this.remove(e);
			}
		}
	}
	
	private int fastest, mid, slowest;
	private int edgeNum;
	private int nodeNum;
	private int errorFlag;
	//Associates node id with neighbours of that node, inner hashmap associates neighbouring id nodes with distance to source node
	//Will be how I store the graph
	HashMap<Integer, HashMap<Integer, Double>> nodes = new HashMap<Integer, HashMap<Integer, Double>>();



	CompetitionDijkstra (String filename, int sA, int sB, int sC){
		errorFlag = 0;
		if((50>sA || sA>100) || (50>sB || sB>100) ||(50>sC || sC>100)) {
			errorFlag = 1;
		}
		if (filename!=null)
		{
			try
			{
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
				if(from.size() != to.size()) {
					errorFlag = 1;
				}
				reader.close();
			}
			catch(Exception e){
			}
		}

		//TODO
	}

	/**
	 * @param int: Node ID
	 * @return Routing Table
	 */
	public RoutingTable Dijkstra(int source){
		RoutingTable permNodes = new RoutingTable();
		TentativeNodesList bag = new TentativeNodesList();
		permNodes.addNode(source, 0, -1);
		for(int i=0; i<nodeNum; i++) {
			HashMap<Integer,Double> distances = nodes.get(permNodes.getNodeID(permNodes.size()-1));//get all associated nodes of last entry
			for(int id : distances.keySet()) {
				if(!permNodes.contains(id)) {//if not already a permanent node
					double[] nodeData = {id, permNodes.getCumCost(permNodes.size()-1) + distances.get(id), permNodes.getNodeID(permNodes.size()-1)};
					bag.add(nodeData);
				}
			}
			double[] newLPN = bag.getLowest();
			if(newLPN.length == 0) {
				break;
			}
			permNodes.addNode((int)newLPN[0], newLPN[1], (int)newLPN[2]);
			bag.removeById((int)newLPN[0]);
			//remove node with id newLPN[0] from bag
			
		}

		return permNodes;
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){
		int timeRequired = 0;
		
		if(errorFlag == 0) {
			
			for(int source : nodes.keySet()) {
				RoutingTable rt = Dijkstra(source);
				if(timeRequired < rt.maxTime()) timeRequired = rt.maxTime();
			}
			
			return timeRequired;
		}
		else{
			return -1;
		}
	}

}