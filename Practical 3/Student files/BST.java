

@SuppressWarnings("unchecked")
public class BST<T extends Comparable<? super T>> {

	protected BSTNode<T> root = null;

	public BST() {
	}

	public void clear() {
		root = null;
	}

	// returns a verbose inorder string of the BST
	public String inorder(BSTNode<T> node) {
		boolean verbose = true;
		if (node != null) {
			String result = "";
			result += inorder(node.left);
			if (verbose) {
				result += node.toString() + "\n";
			} else {
				result += node.element.toString() + " ";
			}
			result += inorder(node.right);
			return result;
		}
		return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	public boolean isEmpty() {
		return root==null;
	}

	public BSTNode<T> clone() {
		if(root==null){
			return null;
		}
		BST<T> rslt= new BST<T>();
		rslt.root=new BSTNode<T>(root.element);
		inorderinsert(root, false, rslt);
		return rslt.root;
	}

	public BSTNode<T> mirror() {
		if(root==null){
			return null;
		}
		BST<T> rslt= new BST<T>();
		rslt.root=new BSTNode<T>(root.element);
		inorderinsert(root, true, rslt);
		return rslt.root;
	}

	public void insert(T element) {
		if(root==null){
			root=new BSTNode<T>(element);
			return;
		}

		if(search(element)!=null){
			return;
		}

		BSTNode<T> current = root;
		BSTNode<T> prev=root;
		while(current!=null){
			prev = current;
			if(current.element.compareTo(element)>0){
				current=current.left;
			}else{
				current=current.right;
			}
		}
		
		if(prev.element.compareTo(element)>0){
			prev.left = new BSTNode<T>(element);
		}else{
			prev.right = new BSTNode<T>(element);
		}
	}

	public boolean deleteByMerge(T element) {
		boolean isin= search(element)!=null;
		if(isin==false){
			return isin;
		}

		BSTNode<T> temp, node, cur=root, prev=null;
		while(cur != null && cur.element.equals(element)){
			prev=cur;
			if(cur.element.compareTo(element)>0){
				cur=cur.right;
			}else{
				cur=cur.left;
			}

			node = cur;
			if(cur!=null && cur.element.equals(element)){
				if(node.right==null){
					node=node.left;
				}else if(node.left==null){
					node=node.right;
				}else{
					temp=node.left;
					while(temp.right!=null){
						temp=temp.right;
					}
					temp.right=node.right;
					node = node.left;
				}

				if(cur==root){
					root=node;
				}else if(prev.left==cur){
					prev.left = node;
				}else{
					prev.right=node;
				}
			}
		}

		return false;
	}

	public boolean deleteByCopy(T element) {
		boolean isin= search(element)!=null;
		if(isin==false){
			return isin;
		}

		BSTNode<T> node, p=root, prev=null;
		while(p!=null && !p.element.equals(element)){
			prev=p;
			if(p.element.compareTo(element)>0){
				p=p.right;
			}else{
				p=p.left;
			}

			node=p;

			if(p!=null && p.equals(element)){
				if(node.right==null){
					node=node.left;
				}else if(node.left==null){
					node=node.right;
				}else{
					BSTNode<T> temp = node.left;
					BSTNode<T> previous = node;

					while(temp.right!=null){
						previous=temp;
						temp=temp.right;
					}
					node.element=temp.element;
				}

				if(p==root){
					root=node;
				}else if(prev.left==p){
					prev.left=node;
				}else{
					prev.right=node;
				}
			}
		}
		return isin;
	}

	public T search(T element) {
		T output=null;

		BSTNode<T> current = root;

		while (current!=null){
			if(current.element.equals(element)){
				return element;
			}else if(current.element.compareTo(element)>0){
				current=current.left;
			}else{
				current=current.right;
			}
		}

		return output;
	}

	public T getPredecessor(T element) {
		if(root==null || search(element)==null){
			return null;
		}

		T result=null;
		BSTNode<T> cur =root;
		while(cur.left!=null){
			cur=cur.left;
		}
		return findPreSec(root, cur, element).element;
	}

	public T getSuccessor(T element) {
		if(root==null || search(element)==null){
			return null;
		}

		T result=null;
		BSTNode<T> cur =root;
		while(cur.left!=null){
			cur=cur.left;
		}
		return findPreSec(root, cur, element).element;
	}

	public void reverseinsert(T element){
		BSTNode<T> current = root;
		BSTNode<T> prev=root;

		if(search(element)!=null){
			return;
		}

		while(current!=null){
			prev = current;
			if(current.element.compareTo(element)<0){
				current=current.left;
			}else{
				current=current.right;
			}
		}
			
		if(prev.element.compareTo(element)<0){
			prev.left = new BSTNode<T>(element);
		}else{
			prev.right = new BSTNode<T>(element);
		}
	}

	public void inorderinsert(BSTNode<T> og, boolean reverse, BST<T> cpy){
		if(og!=null){
			if(reverse){
				cpy.reverseinsert(og.element);
			}else{
				cpy.insert(og.element);
			}
			inorderinsert(og.left, reverse, cpy);
			inorderinsert(og.right, reverse, cpy);
		}
	}

	public BSTNode<T> findPreSec(BSTNode<T> cur, BSTNode<T> result,T el){
		if(cur!=null){
			if(cur==result){
				return result;
			}
			findPreSec(cur.left, result, el);
			findPreSec(cur.right, result, el);
			if(cur.element.compareTo(el)<0 && cur.element.compareTo(result.element)>0){
				result= cur;
			}
		}
		return result;
	}

	//public BSTNode<T>
}