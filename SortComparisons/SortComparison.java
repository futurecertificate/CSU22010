import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Vsevolod Syrtsov 18323202
 *  @version HT 2020
 *  @help Bayir Buyirchib - pivot selection
 *  @help James Rowland - helped w file reader
 */

class SortComparison {

	
	/**
	 * 
	 * AUXILLIARY METHODS 
	 * 
	 */
	
	
	
	/**
	 * Swaps two elements in an array
	 * @param array: any array
	 * @param a: index of first element to swap with second
	 * @param b: index of second element to swap with first
	 */
	private static void swap(double[] array, int a, int b) {
		double d = array[a];
		array[a] = array[b];
		array[b] = d;
	}

	
	/**
	 * Checks if an array is sorted in order left to right
	 * @param array: array to check for orderedness
	 * @return
	 */
	public static boolean orderCheck(double[] d) {
    	for (int i = 1; i < d.length; i++) {
    		if (d[i - 1] > d[i]) {
    			return false; 
    		}
    	}
    	return true;
    }
	
	
	/**
	 * 
	 * MAIN SORTING ALGORITHMS
	 * 
	 */
	
	
	
	/**
	 * Sorts an array of doubles using InsertionSort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double [] insertionSort (double a[]){
		for (int m = 1; m < a.length; m++) {
			int n = m;
			while (n > 0 && a[n - 1] > a[n]) {
				swap(a, n - 1, n);
				n--;
			}
		}
		return a;
	}//end insertionsort

	/**
	 * Sorts an array of doubles using Selection Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] selectionSort (double a[]){
    	for (int m = 0; m < a.length - 1; m++) {
    		int lo = m;
    		for (int j = m + 1; j < a.length; j++) {
    			if (a[j] < a[lo]) {
    				lo = j;
    			}
    		}
    		swap(a, m, lo);
    	}
    	return a;
    }//end selectionsort

	/**
	 * Sorts an array of doubles using Quick Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] quickSort (double a[]){
    	quickSort(a, 0, a.length - 1);
    	return a;
    }
    
    private static void quickSort(double[] a, int l, int r) {
    	if (l < r) {
    		int p = pivot(a, l, r);
    		quickSort(a, l, p - 1);
    		quickSort(a, p + 1, r);
    	}
    }
    
    private static int pivot(double[] a, int l, int r) {
    	double p = a[r];
    	int i = l - 1;
    	for (int j = l; j < r; j++) {
    		if (a[j] < p) {
    			swap(a, ++i, j);
    		}
    	}
    	swap(a, i + 1, r);
    	return i + 1;
    }//end quicksort

    
    
	/**
	 * Sorts an array of doubles using Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */

    private static void merge(double[] a, int l, int m, int r) {
    	int n1 = m - l + 1;
    	int n2 = r - m;
    	
    	double[] L = new double[n1];
    	double[] R = new double[n2];
    	
    	for (int i = 0; i < n1; ++i) {
    		L[i] = a[l + i];
    	}
    	for (int j = 0; j < n2; ++j) {
    		R[j] = a[m + 1 + j];
    	}
    	
    	int i = 0, j = 0, k = l;
 
    	while (i < n1 && j < n2) {
    		if (L[i] <= R[j]) {
    			a[k++] = L[i++];
    		}
    		else {
    			a[k++] = R[j++];
    		}
    	}
    	while (i < n1) {
    		a[k++] = L[i++];
    	}
    	while (j < n2) {
    		a[k++] = R[j++];
    	}
    }
    
    static double[] mergeSortIterative (double a[]) {
    	int n = a.length;
    	for (int m = 1; m <= n - 1; m *= 2) {
    		for (int l = 0; l < n - 1; l += 2 * m) {
    			int mid = Integer.min(l + m - 1, n - 1);
    			int r = Integer.min(l + 2 * m - 1, n - 1);
    			merge(a, l, mid, r);
    		}
    	}
    	return a;
    }//end mergesortIterative



	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */
    static double[] mergeSortRecursive (double a[]) {
    	mergeSortRecursive(a, 0, a.length - 1);
    	return a;
   }
    
    private static void mergeSortRecursive(double[] a, int l, int r) {
    	if (l < r) {
    		int m = (l + r) / 2;
    		
    		mergeSortRecursive(a, l, m);
    		mergeSortRecursive(a, m + 1, r);
    		
    		merge(a, l, m, r);
    	}
    }//end mergeSortRecursive

	
	//no main function since all the rigorous tests are done in SortComparisonTest.java
	
	public static double[] input(String fileName) {
    	ArrayList<Double> nums = new ArrayList<Double>();
    	try {
    		FileReader reader = new FileReader(fileName);
    		BufferedReader buffer = new BufferedReader(reader);
    		boolean end = false;
    		while (!end) {
    			String value = buffer.readLine();
    			if (value != null) {
    				nums.add(Double.parseDouble(value));
    			}
    			else {
    				end = true;
    			}
    		}
    		buffer.close();
    		reader.close();
    	} catch (Exception e){}
    	double[] numstoarray = new double[nums.size()];
    	for (int i = 0; i < nums.size(); i++) {
    		numstoarray[i] = nums.get(i);
    	}
    	return numstoarray;
    }
}//end class

