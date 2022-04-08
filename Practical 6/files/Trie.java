public class Trie {	
	protected char[] letters;
	protected Node root = null;
	private int numPtrs;


	public Trie(char[] letters) {
		this.letters = letters;
		this.numPtrs = letters.length + 1;
	}


	//Provided Helper functions
	
	private int index(char c) {
		for (int k = 0; k < letters.length; k++) {
			if (letters[k] == (c)) {
				return k+1;
			}
		}
		return -1;
	}
	
	private char character(int i) {
		if (i == 0) {
			return '#';
		} else {
			return letters[i-1];
		}
	}
	
	private String nodeToString(Node node, boolean debug) {
		if (node.isLeaf) {
			return node.key;
		}
		else {
			String res = "";
			for (int k = 0; k < node.ptrs.length; k++) {
				if (node.ptrs[k] != null) {
					res += " (" + character(k) + ",1) ";
				} else if (debug) {
					res += " (" + character(k) + ",0) ";
				}
			}
			return res;
		}
	}

	public void print(boolean debug) {
		Queue queue = new Queue();
		Node n = root;
		if (n != null) {
			n.level = 1;
			queue.enq(n);
			while (!queue.isEmpty()){
				n = queue.deq();
				System.out.print("Level " + n.level + " [");
				System.out.print(nodeToString(n, debug));
				System.out.println("]");
				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null) {
						n.ptrs[k].level = n.level+1;
						queue.enq(n.ptrs[k]);
					}
				}
			}
		}
	}


	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	
	// Function to insert the given key into the trie at the correct position.
	public void insert(String key) {
		if(root==null){
			root=new Node(numPtrs);
			Node kid=new Node(key, numPtrs);
			root.ptrs[index(key.charAt(0))]=kid;
			return;
		}

		Node n=root;
		int level=0;
		boolean isNull=false;
		boolean isLeaf=false;
		boolean isend=false;
		for(level=0; level<key.length()&&!n.endOfWord&&!n.isLeaf; level++){
			if(n.ptrs[index(key.charAt(level))]==null){
				isNull=true;
				break;
			}else if(n.ptrs[index(key.charAt(level))].isLeaf){
				isLeaf=true;
				break;
			}else if(level+1==key.length()){
				isend=true;
				break;
			}
			n=n.ptrs[index(key.charAt(level))];
		}

		if(isend){
			n.ptrs[index(key.charAt(level))]=new Node(numPtrs);
			n=n.ptrs[index(key.charAt(level))];
			n.ptrs[0]=new Node(key, numPtrs);
		}else if(isLeaf){
			String temp=n.ptrs[index(key.charAt(level))].key;

			for(; level<temp.length() && level<key.length(); level++){
				if(temp.charAt(level)==key.charAt(level)){
					n.ptrs[index(key.charAt(level))]=new Node(numPtrs);
					n=n.ptrs[index(key.charAt(level))];
				}else{
					break;
				}
			}
			if(level<temp.length() && level<key.length()){
				n.ptrs[index(temp.charAt(level))]=new Node(temp, numPtrs);
				n.ptrs[index(key.charAt(level))]=new Node(key, numPtrs);
			}else if(level+1==temp.length()){
				n.ptrs[0]=new Node(key, numPtrs);
				n.ptrs[index(temp.charAt(level))]=new Node(temp, numPtrs);
			}else if(level+1==key.length()){
				n.ptrs[0]=new Node(temp, numPtrs);
				n.ptrs[index(key.charAt(level))]=new Node(key, numPtrs);
			}
		}else if(isNull){
			n.ptrs[index(key.charAt(level))]=new Node(numPtrs);
			Node kid=new Node(key, numPtrs);
			n.ptrs[index(key.charAt(level))]=kid;
		}
	}
	

	// Function to determine if a node with the given key exists.
	public boolean contains(String key) {
		Node n=root;
		int level=0;
		boolean isNull=false;
		boolean isLeaf=false;
		boolean isend=false;
		for(level=0; level<key.length()&&!n.endOfWord&&!n.isLeaf; level++){
			if(index(key.charAt(level))==-1){
				break;
			}
			if(n.ptrs[index(key.charAt(level))]==null){
				isNull=true;
				break;
			}else if(n.ptrs[index(key.charAt(level))].isLeaf){
				return n.ptrs[index(key.charAt(level))].key==key;
			}
			n=n.ptrs[index(key.charAt(level))];
		}
		if(isend){
			return key==n.ptrs[index(key.charAt(level))].key;
		}
		return false;
	}

	
	// Function to print all the keys in the trie in alphabetical order.
	public void printKeyList() {
		Queue queue = new Queue();
		Node n = root;
		if (n != null) {
			n.level = 1;
			queue.enq(n);
			while (!queue.isEmpty()){
				if(n.isLeaf){
					System.out.print(n.key+" ");
				}
				n = queue.deq();
				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null) {
						n.ptrs[k].level = n.level+1;
						queue.enq(n.ptrs[k]);
					}
				}
			}
		}
	}

	
	//Helper functions

}