import java.util.Arrays;
@SuppressWarnings("unchecked")
public class Sort {

	////// Implement the functions below this line //////

	/********** HEAP **********/
	public static <T extends Comparable<? super T>> void heapsort(T[] data, boolean debug) {
		for(int i=(data.length/2)-1; i >= 0; --i){
			movedown(data, i, data.length-1, debug);
		}

		for(int i=data.length-1; i>=1; --i){
			T temp = data[0];
			data[0]=data[i];
			data[i]=temp;
			movedown(data, 0, i-1, debug);
		}
	}

	private static <T extends Comparable<? super T>> void movedown(T[] data, int first, int last, boolean debug) {
		int largest=2*first+1;
		while(largest <= last){
			if(largest < last && (data[largest].compareTo(data[largest+1])<0)){
				largest++;
			}
			if(data[first].compareTo(data[largest])<0){
				T temp = data[first];
				data[first]=data[largest];
				data[largest]=temp;
				first=largest;
				largest=2*first+1;
			}else{
				largest=last+1;
			}
		}

		if (debug)
			System.out.println(Arrays.toString(data));
	}

	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug) {
		if(first<last){
			int mid=(first+last)/2;
			mergesort(data, first, mid, debug);
			mergesort(data, mid+1, last, debug);
			merge(data, first, last, debug);
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug) {
		int r=last;
		int l=first;
		int m=l+(r-l)/2;
		// Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        Comparable L[] = new Comparable[n1];
        Comparable R[] = new Comparable[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = data[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = data[m + 1 + j];
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j])<0) {
                data[k] = (T) L[i];
                i++;
            }
            else {
                data[k] = (T)R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            data[k] = (T)L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            data[k] = (T)R[j];
            j++;
            k++;
        }


		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}