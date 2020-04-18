import org.junit.Test;

public class CompetitionTests {
//
	@Test
	public void testDijkstraConstructor() {
		CompetitionDijkstra cd = new CompetitionDijkstra("src/tinyEWD.txt", 50, 75, 100);
		cd = new CompetitionDijkstra(null, 50, 75, 100);
		cd = new CompetitionDijkstra("wrongfile", 50, 75, 100);
		cd = new CompetitionDijkstra("src/tinyEWD.txt", 110, 75, 100);
		cd = new CompetitionDijkstra("src/tinyEWD.txt", 50, 110, 100);
		cd = new CompetitionDijkstra("src/tinyEWD.txt", 50, 75, 110);
		cd = new CompetitionDijkstra("src/tinyEWD.txt", 40, 75, 100);
		cd = new CompetitionDijkstra("src/tinyEWD.txt", 50, 40, 100);
		cd = new CompetitionDijkstra("src/tinyEWD.txt", 50, 75, 40);
		cd = new CompetitionDijkstra("src/ultrasmall.txt", 50, 75, 100);
		cd = new CompetitionDijkstra("src/nodoublenode.txt", 50, 75, 100);



	}

	@Test
	public void testDijkstra() {
		CompetitionDijkstra cd = new CompetitionDijkstra("src/tinyEWD.txt", 50, 75, 100);
		cd.Dijkstra(0);	
	}

	@Test
	public void testCompletionTimeDijkstra() {
		CompetitionDijkstra cd = new CompetitionDijkstra("src/input-B.txt", 50, 75, 100);
		cd.timeRequiredforCompetition();
	}

	@Test
	public void testCompletionTimeDijkstraErr() {
		CompetitionDijkstra cd = new CompetitionDijkstra("src/input-B.txt", 40, 75, 100);
		cd.timeRequiredforCompetition();
	}

	@Test
	public void testFWConstructor() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 75, 100);
		cfw = new CompetitionFloydWarshall(null, 50, 75, 100);
		cfw = new CompetitionFloydWarshall("wrongfile", 50, 75, 100);

		cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 110, 75, 100);
		cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 110, 100);
		cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 75, 110);
		cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 40, 75, 100);
		cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 40, 100);
		cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 75, 40);
		cfw = new CompetitionFloydWarshall("src/ultrasmall.txt", 50, 75, 100);
		cfw = new CompetitionFloydWarshall("src/nodoublenode.txt", 50, 75, 100);

	}

	@Test
	public void testFWAlgorithm() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/tinyEWD.txt", 50, 75, 100);
		cfw.FloydWarshall();
	}

	@Test
	public void testCompletionTimeFW() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/input-B.txt", 50, 75, 100);
		cfw.timeRequiredforCompetition();
	}

	@Test
	public void testCompletionTimeFWErr() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/input-B.txt", 40, 75, 100);
		cfw.timeRequiredforCompetition();	
	}

	//-------webcat suggestions

	@Test
	public void inputB() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/input-B.txt", 60, 80, 50);
		CompetitionDijkstra cd = new CompetitionDijkstra("src/input-B.txt", 60, 80, 50);
		System.out.println(cfw.timeRequiredforCompetition() + " B");
		System.out.println(cd.timeRequiredforCompetition() + " B");
	}

	@Test
	public void inputI() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/input-I.txt", 72,70,65);
		CompetitionDijkstra cd = new CompetitionDijkstra("src/input-I.txt", 72,70,65);
		System.out.println(cfw.timeRequiredforCompetition() + " I");
		System.out.println(cd.timeRequiredforCompetition() + " I");
	}
	
	@Test
	public void inputD() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/input-D.txt", 50,80,60);
		CompetitionDijkstra cd = new CompetitionDijkstra("src/input-D.txt", 50,80,60);
		System.out.println(cfw.timeRequiredforCompetition() + " D");
		System.out.println(cd.timeRequiredforCompetition() + " D");
	}
	
	@Test
	public void inputK() {
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall("src/input-K.txt", 51,70,88);
		CompetitionDijkstra cd = new CompetitionDijkstra("src/input-K.txt", 51,70,88);
		System.out.println(cfw.timeRequiredforCompetition() + " K");
		System.out.println(cd.timeRequiredforCompetition() + " K");
	}
	
	

	
	//TODO - more tests

}