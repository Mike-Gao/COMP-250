import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

// Zenghao (Mike) Gao

public class Sorting {
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) <0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
	public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {
		// ADD YOUR CODE HERE

		ArrayList<K> sorted = new ArrayList<K>();
		sorted.addAll(results.keySet());

		quicksortHelper(sorted, 0, sorted.size() - 1, results);
		return sorted;
	}

	private static <K, V extends Comparable> void quicksortHelper(ArrayList<K> sorted, int left, int right, HashMap<K, V> results){
		if (left >= right) {
			return;
		} else {
			K tmp2 = sorted.get(right);
			int index = (int) (Math.floor(Math.random()*(right-left+1)+left));
			sorted.set(right, sorted.get(index));
			sorted.set(index, tmp2);
			V pivot = results.get(sorted.get(right));
			int wall = left - 1;
			for (int i = left; i < right; i++){
				if (results.get(sorted.get(i)).compareTo(pivot) > 0){
					wall++;
					if (i != wall){
						K tmp = sorted.get(i);
						sorted.set(i, sorted.get(wall));
						sorted.set(wall, tmp);
					}
				}
			}
			if (wall + 1 != right){
				K tmp = sorted.get(wall + 1);
				sorted.set(wall + 1, sorted.get(right));
				sorted.set(right, tmp);
			}

			int div = wall + 1;

			quicksortHelper(sorted, left, div - 1, results);
			quicksortHelper(sorted, div + 1, right, results);
		}
	}
}