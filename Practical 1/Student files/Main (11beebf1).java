public class Main{
		
	//these methods can help you test your code
	
	public static void firstKey(SkipList<Integer> skiplist) {
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else
			System.out.println("First key : " + skiplist.first());
	}

	public static void pathToLastKey(SkipList<Integer> skiplist) {
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else
			System.out.println("Path to last key : " + skiplist.getPathToLastNode());
	}

	public static void deleteKey(SkipList<Integer> skiplist, Integer key) {
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else {
			boolean result = skiplist.delete(key);
			if (result)
				System.out.println("Deleted key " + key);
			else
				System.out.println("Key " + key + " not found for deletion");
		}
	}

	public static void searchKey(SkipList<Integer> skiplist, Integer key) {
		if (skiplist.isEmpty())
			System.out.println("List is empty");
		else {
			Integer result = skiplist.search(key);
			if (result != null)
				System.out.println("Found key " + result);
			else
				System.out.println("Key " + key + " not found");
		}
	}

	public static void printList(SkipList<Integer> skiplist) {
		System.out.println();
		for (int i = 0; i < skiplist.maxLevel; i++) {
			SkipListNode<Integer> node = skiplist.root[i];
			System.out.print("Level " + i + ": ");
			while (node != null) {
				System.out.print(node.key + " ");
				node = node.next[i];
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// Some code to help you test
		SkipList<Integer> skipList = new SkipList<Integer>();
		
		skipList.insert(5);
		skipList.insert(55);
		skipList.insert(10);
//		skipList.printStructure();
		skipList.insert(19);
		skipList.insert(22);
		skipList.insert(3);
		skipList.insert(3);
		skipList.insert(60);
		skipList.insert(99);
		skipList.insert(29);

		printList(skipList);
		firstKey(skipList);
		pathToLastKey(skipList);
		searchKey(skipList, 55);
		
		deleteKey(skipList, 55);
		//printList(skipList);
		deleteKey(skipList, 3);		//first item (boundary case)
		//printList(skipList);
		deleteKey(skipList, 99);		//last item (boundary case)
		//printList(skipList);
		deleteKey(skipList, 1000);
		printList(skipList);
		searchKey(skipList, 55);
		firstKey(skipList);
		pathToLastKey(skipList);

		/*Expected output

		Level 0: 3 5 10 19 22 29 55 60 99 
		Level 1: 3 10 19 29 55 99 
		Level 2: 29 55 99 
		Level 3: 29 
		
		First key : 3
		Path to last key : [29][55][99]
		Found key 55
		Deleted key 55
		Deleted key 3
		Deleted key 99
		Key 1000 not found for deletion
		
		Level 0: 5 10 19 22 29 60 
		Level 1: 10 19 29 
		Level 2: 29 
		Level 3: 29 
		
		Key 55 not found
		First key : 5
		Path to last key : [29][60]

		//////////////////////////end of their provided main	*/

		skipList.insert(1);
		skipList.insert(2);
		skipList.insert(3);
		skipList.insert(4);
		skipList.insert(5);
		skipList.insert(6);
		skipList.insert(7);
		skipList.insert(8);
		skipList.insert(9);
		skipList.insert(10);

		skipList.insert(110);
		skipList.insert(210);
		skipList.insert(310);
		skipList.insert(410);
		skipList.insert(510);
		skipList.insert(610);
		skipList.insert(710);
		skipList.insert(810);
		skipList.insert(910);
		skipList.insert(1010);

		printList(skipList);

		deleteKey(skipList, 110);
		deleteKey(skipList,6);
		deleteKey(skipList,910);

		printList(skipList);

		deleteKey(skipList, 1010);		//last element (boundary case)

		printList(skipList);

		firstKey(skipList);

		if (skipList.isEmpty()){
			System.out.println("Empty List");
		}
		else{
			System.out.println("Not Empty");
		}

		searchKey(skipList, 510);
		pathToLastKey(skipList);

		deleteKey(skipList,1);			//first element (boundary case)
		printList(skipList);

		deleteKey(skipList, 7);
		deleteKey(skipList, 8);
		deleteKey(skipList, 9);
		deleteKey(skipList, 10);
		deleteKey(skipList, 19);
		deleteKey(skipList, 22);
		deleteKey(skipList, 29);
		deleteKey(skipList, 60);
		deleteKey(skipList, 410);
		deleteKey(skipList, 510);
		deleteKey(skipList, 810);
		deleteKey(skipList, 310);
		deleteKey(skipList, 710);
		deleteKey(skipList, 2);
		deleteKey(skipList, 5);
		deleteKey(skipList, 4);
		deleteKey(skipList, 210);
		deleteKey(skipList, 610);
		deleteKey(skipList, 3);
		printList(skipList);

		if (skipList.isEmpty()){
			System.out.println("Empty List");
		}

		firstKey(skipList);

		skipList.insert(10);
		skipList.insert(20);
		skipList.insert(30);
		skipList.insert(40);
		skipList.insert(50);

		skipList.insert(8);
		skipList.insert(32);
		skipList.insert(15);

		printList(skipList);

		System.out.println("\n\n\n___________________________________________________\n\n\n\n");

		for(int i = -10; i < 53; i += 2) {
			skipList.insert(i);
			if(i == 0 || i == 20 || i == 52) printList(skipList);
		}

		for(int i = -10; i < 53; i++) {
			searchKey(skipList, i);
		}

		System.out.println("\nPath to last key : " + skipList.getPathToLastNode());
		firstKey(skipList);


		int high = 54, low = -12;

		for(int i = 0; i < 17; i++) {
			if(i % 2 == 0) {
				deleteKey(skipList, high);
				high -= 2;
			} else {
				deleteKey(skipList, low);
				low += 2;
			}
		}
		printList(skipList);

		for(int i = 16; i < 27; i += 2) {
			deleteKey(skipList, i);
		}
		printList(skipList);

		deleteKey(skipList, 36);
		printList(skipList);
		System.out.println("Path: " + skipList.getPathToLastNode() + '\n');
		deleteKey(skipList, 34);
		printList(skipList);
		System.out.println("Path: " + skipList.getPathToLastNode() + '\n');
		deleteKey(skipList, 15);
		printList(skipList);
		System.out.println("Path: " + skipList.getPathToLastNode() + '\n');
		deleteKey(skipList, 8);
		printList(skipList);
		System.out.println("Path: " + skipList.getPathToLastNode() + '\n');

		for(int i = 0; i < 36; i++) {
			skipList.delete(i);
		}

		printList(skipList);

		System.out.println(skipList.delete(0));
		System.out.println(skipList.search(99));
		System.out.println(skipList.first());
		System.out.println("Path: " + skipList.getPathToLastNode());

		System.out.println("\nThe following will add a large amount of values: ");
		for(int i = 0; i < 100; i++) {
			skipList.insert(i);
		}

		printList(skipList);
		pathToLastKey(skipList);

	}

}
