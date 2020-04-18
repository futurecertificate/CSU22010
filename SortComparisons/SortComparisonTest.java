import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Vsevolod Syrtsov
 *  @version HT 2020
 */

/**
 * Files		10		100		1000	1000D		1000NO	1000Rev		1000Sorted
 * \\\\\\\\\
 * Insertion	2		173		4537	3312		61		839			1
 * Quick		3		25		460		100			261		1124		429
 * I-Merge		9		60		526		233			210		179			181
 * R-Merge		2		31		353		161			130		117			114
 * Selection	2		86		2456	1940		1780	312			297
 * 
 */


/**
 * 
 * a. Which of the sorting algorithms does the order of input have an impact on? Why?
 * 
 * -> Insertion sort is affected the most due to its inefficient method of searching, constantly checking the array for the lowest element
 * 
 * b. Which algorithm has the biggest difference between the best and worst performance, based
 * on the type of input, for the input of size 1000? Why?
 * 
 * ->Insertion sort, in the case of a large unordered list, it performs badly, and on the opposite end of the list being fully sorted, it simply acts as an order checker
 * 
 * c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 * based on the input size? Please consider only input files with random order for this answer.
 * 
 * -> Recursive merge sort, has a far smaller +/- % change between input sizes
 * 
 * d. Did you observe any difference between iterative and recursive implementations of merge
 * sort?
 * 
 * ->Yes, due to a smaller amount of operations in the recursive counterpart it permutates faster
 * 
 * e. Which algorithm is the fastest for each of the 7 input files? 
 * 10: Hard to say
 * 100: Quicksort
 * 1000: Recursive Merge-Sort
 * 1000D: Quicksort
 * 1000NO: Insertion
 * 1000Rev: Recursive Merge
 * 1000Sorted: InsertionSort (King)
 *
 */






@RunWith(JUnit4.class)
public class SortComparisonTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new SortComparison();
	}
	
	@Test
	public void insert() {
		double[] numbers = SortComparison.input("numbers10.txt");
		assertFalse("Ordered 10 values", SortComparison.orderCheck(numbers));
		numbers = SortComparison.insertionSort(numbers);
		assertTrue("Ordered 10 values", SortComparison.orderCheck(numbers));
	}
	
	@Test
	public void quick() {
		double[] numbers = SortComparison.input("numbers10.txt");
		assertFalse("Ordered 10 values", SortComparison.orderCheck(numbers));
		numbers = SortComparison.quickSort(numbers);
		assertTrue("Ordered 10 values", SortComparison.orderCheck(numbers));
	}
	
	@Test
	public void mergeIterative() {
		double[] numbers = SortComparison.input("numbers10.txt");
		assertFalse("Ordered 10 values", SortComparison.orderCheck(numbers));
		numbers = SortComparison.mergeSortIterative(numbers);
		assertTrue("Ordered 10 values", SortComparison.orderCheck(numbers));
	}
	
	@Test
	public void mergeRecursive() {
		double[] numbers = SortComparison.input("numbers10.txt");
		assertFalse("Ordered 10 values", SortComparison.orderCheck(numbers));
		numbers = SortComparison.mergeSortRecursive(numbers);
		assertTrue("Ordered 10 values", SortComparison.orderCheck(numbers));
	}
	
	@Test
	public void selectionSort() {
		double[] numbers = SortComparison.input("numbers10.txt");
		assertFalse("Ordered 10 values", SortComparison.orderCheck(numbers));
		numbers = SortComparison.selectionSort(numbers);
		assertTrue("Ordered 10 values", SortComparison.orderCheck(numbers));
	}
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty()
	{
		double[] emptyArray = {};
		assertTrue("Empty is sorted", SortComparison.orderCheck(emptyArray));
		SortComparison.insertionSort(emptyArray);
		assertTrue("Empty is sorted", SortComparison.orderCheck(emptyArray));
		SortComparison.quickSort(emptyArray);
		assertTrue("Empty is sorted", SortComparison.orderCheck(emptyArray));
		SortComparison.mergeSortIterative(emptyArray);
		assertTrue("Empty is sorted", SortComparison.orderCheck(emptyArray));
		SortComparison.mergeSortRecursive(emptyArray);
		assertTrue("Empty is sorted", SortComparison.orderCheck(emptyArray));
		
		
	}

	@Test
	public void testRunTime(){
		main(null);
	}


	// ----------------------------------------------------------
	/**
	 *  Main Method.
	 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
	 *
	 */
	
	public static void main(String[] args)
	{
		String[] files = {"numbers10.txt", "numbers100.txt","numbers1000.txt","numbers1000Duplicates.txt",
				"numbersNearlyOrdered1000.txt","numbersReverse1000.txt", "numbersSorted1000.txt"};
		System.out.println("Insertion sort times");
		for (int i = 0; i < files.length; i++) {
			double[] doubles = SortComparison.input(files[i]);
			long start = System.nanoTime();
			SortComparison.insertionSort(doubles);
			long end = System.nanoTime();
			long timetaken = (end - start) / 1000;
			System.out.println(files[i] + " : " + timetaken);
		}
		System.out.println("\nQuickSort times");
		for (int i = 0; i < files.length; i++) {
			double[] doubles = SortComparison.input(files[i]);
			long start = System.nanoTime();
			SortComparison.quickSort(doubles);
			long end = System.nanoTime();
			long timetaken = (end - start) / 1000;
			System.out.println(files[i] + " : " + timetaken);
		}
		System.out.println("\nIterative MergeSort times");
		for (int i = 0; i < files.length; i++) {
			double[] doubles = SortComparison.input(files[i]);
			long start = System.nanoTime();
			SortComparison.mergeSortIterative(doubles);
			long end = System.nanoTime();
			long timetaken = (end - start) / 1000;
			System.out.println(files[i] + " : " + timetaken);
		}
		System.out.println("\nRecursive MergeSort times");
		for (int i = 0; i < files.length; i++) {
			double[] doubles = SortComparison.input(files[i]);
			long start = System.nanoTime();
			SortComparison.mergeSortRecursive(doubles);
			long end = System.nanoTime();
			long timetaken = (end - start) / 1000;
			System.out.println(files[i] + " : " + timetaken);
		}
		System.out.println("\nSelection Sort times");
		for (int i = 0; i < files.length; i++) {
			double[] doubles = SortComparison.input(files[i]);
			long start = System.nanoTime();
			SortComparison.selectionSort(doubles);
			long end = System.nanoTime();
			long timetaken = (end - start) / 1000;
			System.out.println(files[i] + " : " + timetaken);
		}
	}

}

