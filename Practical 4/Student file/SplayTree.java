/**
 * Name:
 * Student Number:
 */

public class SplayTree<T extends Comparable<T>> {

	protected enum SplayType {
		SPLAY,
		SEMISPLAY,
		NONE
	}	

	protected Node<T> root = null;
	
	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(boolean verbose) {
		String result;
		result = preorder(root, verbose);
		System.out.println(result);
	}
	
	protected String preorder(Node<T> node, boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.elem.toString() + " ";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	* Inserts the given element into the tree, but only if it is not already in the tree.
	* @param elem 
	* 		 	The element to be inserted into the tree
	* @return 
	*			Returns true if the element was successfully inserted into the tree. 
	*			Returns false if elem is already in the tree and no insertion took place.
	*
	*/
	public boolean insert(T elem) {
		if(root==null){
			root=new Node<T>(elem);
			return true;
		}

		if(contains(elem)){
			return false;
		}

		Node<T> current = root;
		Node<T> prev= root;
		while(current!=null){
			prev = current;
			if(current.elem.compareTo(elem)>0){
				current=current.left;
			}else{
				current=current.right;
			}
		}

	    if(prev.elem.compareTo(elem)>0){
			prev.left = new Node<T>(elem);
		}else{
			prev.right = new Node<T>(elem);
		}
		return true;
	}
	
	/**
	* Checks whether a given element is already in the tree.
	* @param elem 
	* 		 	The element being searched for in the tree
	* @return 
	*			Returns true if the element is already in the tree
	*			Returns false if elem is not in the tree
	*
	*/
	public boolean contains(T elem) {
		Node<T> current = root;
		while (current!=null){
			if(current.elem.equals(elem)){
				return true;
			}else if(current.elem.compareTo(elem)>0){
				current=current.left;
			}else{
				current=current.right;
			}
		}
		return false;
	}
	
	/**
	 * Accesses the node containing elem. 
	 * If no such node exists, the node should be inserted into the tree.
	 * If the element is already in the tree, the tree should either be semi-splayed so that 
	 * the accessed node moves up and the parent of that node becomes the new root or be splayed 
	 * so that the accessed node becomes the new root.
	 * @param elem
	 *			The element being accessed
	 * @param type
	 *			The adjustment type (splay or semi-splay or none)
	 */
	public void access(T elem, SplayType type) {
		Node<T> current = root;
		while (current!=null){
			if(current.elem.equals(elem)){
				break;
			}else if(current.elem.compareTo(elem)>0){
				current=current.left;
			}else{
				current=current.right;
			}
		}
		
		if(type==SplayType.SPLAY){
			if(contains(elem)){
				splay(current);
			}else{
				insert(elem);
			}
		}else if(type==SplayType.SEMISPLAY){
			if(contains(elem)){
				semisplay(current);
			}else{
				insert(elem);
			}
		}
	}
	
	/**
	 * Semi-splays the tree using the parent-to-root strategy
	 * @param node
	 *			The node the parent of which will be the new root
	 */
	protected void semisplay(Node<T> node) {
		Node<T> a=node;
		Node<T> p=findParent(a);
		Node<T> g=findParent(p);
		Node<T> gg=findParent(g);
		
		while(!p.equals(root)){
			p=findParent(a);
			g=findParent(p);
			gg=findParent(g);

			if(p==null || p.left==null || p.right==null){
				break;
			}

			if(p==root){
				if(p.left==a){
					rightRotation(a, p, g);
				}else{
					leftRotation(a, p, g);
				}
				root=a;
			}else if(g.left==p && p.left==a){
				rightRotation(p, g, gg);
			}else if(g.right==p && p.right==a){
				leftRotation(p, g, gg);
			}else if(p.right==a && g.left==p){
				rightRotation(p, g, gg);
			}else if(p.left==a && g.right==p){
				rightLeftRotation(a, p, g, gg);
			}
		}
	}

	/**
	 * Splays the tree using the node-to-root strategy
	 * @param node
	 *			The node which will be the new root
	 */
	protected void splay(Node<T> node) {
		Node<T> a=node;
		Node<T> p=findParent(a);
		Node<T> g=findParent(p);
		Node<T> gg=findParent(g);
		
		while(!a.elem.equals(root.elem)){
			p=findParent(a);
			g=findParent(p);
			gg=findParent(g);

			if(p==root){
				if(p.left==a){
					rightRotation(a, p, g);
				}else{
					leftRotation(a, p, g);
				}
				root=a;
			}else if(g.left==p && p.left==a){
				rightRotation(p, g, gg);
				p=findParent(a);
				g=findParent(p);
				gg=findParent(g);
				rightRotation(a, p, g);
			}else if(g.right==p && p.right==a){
				leftRotation(p, g, gg);
				p=findParent(a);
				g=findParent(p);
				gg=findParent(g);
				leftRotation(a, p, g);
			}else if(p.right==a && g.left==p){
				leftRightRotation(a, p, g, gg);
			}else if(p.left==a && g.right==p){
				rightLeftRotation(a, p, g, gg);
			}
		}
	}

	//Helper functions
	protected void rightRotation(Node<T> a, Node<T> p, Node<T> g){
		boolean notnull=true;
		boolean is=false;
		if(g==null){
			root=a;
			notnull=false;
		}else{
			is=g.left==p;
		}

		p.left=a.right;
		a.right=p;
		
		if(notnull){
			if(is){
				g.left=a;
			}else{
				g.right=a;
			}
		}
	}
	
	protected void leftRotation(Node<T> a, Node<T> p, Node<T> g){
		boolean notnull=true;
		boolean is=false;
		if(g==null){
			root=a;
			notnull=false;
		}else{
			is=g.left==p;
		}
		p.right=a.left;
		a.left=p;
		
		if(notnull){
			if(is){
				g.left=a;
			}else{
				g.right=a;
			}
		}
	}

	protected void leftRightRotation(Node<T> a, Node<T> p, Node<T> g, Node<T> gg){
		boolean notnull=true;
		boolean is=false;
		if(gg==null){
			root=a;
			notnull=false;
		}else{
			is=gg.left==g;
		}
		g.left=a.right;
		p.right=a.left;
		a.right=g;
		a.left=p;
		if(notnull){
			if(is){
				gg.left=a;
			}else{
				gg.right=a;
			}
		}
	}

	protected void rightLeftRotation(Node<T> a, Node<T> p, Node<T> g, Node<T> gg){
		boolean notnull=true;
		boolean is=false;
		if(gg==null){
			root=a;
			notnull=false;
		}else{
			is=gg.left==g;
		}
		g.right=a.left;
		p.left=a.right;
		a.left=g;
		a.right=p;
		
		if(notnull){
			if(is){
				gg.left=a;
			}else{
				gg.right=a;
			}
		}
	}

	protected Node<T> findParent(Node<T> node){
		if(node==null){
			return null;
		}
		Node<T> a=root;
		Node<T> p=null;

		while (!a.elem.equals(node.elem)){
			if(a.elem.compareTo(node.elem)>0){
				p=a;
				a=a.left;
			}else{
				p=a;
				a=a.right;
			}
		}
		return p;
	}
}