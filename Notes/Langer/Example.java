package example_project;

//import java.util.Arrays;

public class Example {

	public static void swap(int[] arr, int ind1, int ind2){
	    final int temp = arr[ind1];
	    arr[ind1] = arr[ind2];
	    arr[ind2] = temp;
	}
	
	public static void main(String[] args)
	{
	    int[] arr = {10, 20, 30, 40, 50};
	    
	    swap(arr, 2, 3);
	    
	    // System.out.println(arr);
	    //System.out.println(Arrays.toString(arr));
	}
}
