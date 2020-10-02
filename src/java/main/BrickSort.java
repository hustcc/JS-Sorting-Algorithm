// Java Program to implement 
// Odd-Even / Brick Sort 
import java.io.*; 

class GFG 
{ 
	public static void oddEvenSort(int arr[], int n) 
	{ 
		boolean isSorted = false; // Initially array is unsorted 

		while (!isSorted) 
		{ 
			isSorted = true; 
			int temp =0; 

			// Perform Bubble sort on odd indexed element 
			for (int i=1; i<=n-2; i=i+2) 
			{ 
				if (arr[i] > arr[i+1]) 
				{ 
					temp = arr[i]; 
					arr[i] = arr[i+1]; 
					arr[i+1] = temp; 
					isSorted = false; 
				} 
			} 

			// Perform Bubble sort on even indexed element 
			for (int i=0; i<=n-2; i=i+2) 
			{ 
				if (arr[i] > arr[i+1]) 
				{ 
					temp = arr[i]; 
					arr[i] = arr[i+1]; 
					arr[i+1] = temp; 
					isSorted = false; 
				} 
			} 
		} 

		return; 
	} 
	public static void main (String[] args) 
	{ 
		int arr[] = {34, 2, 10, -9}; 
		int n = arr.length; 

		oddEvenSort(arr, n); 
		for (int i=0; i < n; i++) 
			System.out.print(arr[i] + " "); 

		System.out.println(" "); 
	} 
} 
