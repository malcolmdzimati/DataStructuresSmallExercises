import java.util.Random;

@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>> {

	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i) {
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(202003); // do not modify this line
	}

	SkipList() {
		this(4);
	}

	public void choosePowers() {
		powers[maxLevel - 1] = (2 << (maxLevel - 1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i + 1] - (2 << j);
	}

	public int chooseLevel() {
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel - 1] + 1;
		for (i = 1; i < maxLevel; i++) {
			if (r < powers[i])
				return i - 1;
		}
		return i - 1;
	}

	public boolean isEmpty() {
		return root[0]==null;
	}

	public void insert(T key) {

		int level = maxLevel-1;

		SkipListNode<T>[] cur = new SkipListNode[maxLevel];
		SkipListNode<T>[] prev = new SkipListNode[maxLevel];

		cur[level] = root[level];
		prev[level] = null;

		for(; level > -1; level--){
			while(cur[level] != null && cur[level].key.compareTo(key) < 0){
				prev[level] = cur[level];
				cur[level] = cur[level].next[level];
			}

			if(cur[level] != null &&  cur[level].key.equals(key)){
				return;
			}

			if(level > 0){
				if(prev[level] == null){
					cur[level-1] = root[level-1];
					prev[level-1] = null;
				}else {
					cur[level-1] = prev[level].next[level-1];
					prev[level-1] = prev[level];
				}
			}
		}
		level = chooseLevel();

		SkipListNode<T> newNode = new SkipListNode<T>(key, level+1);
		for(int i = 0; i <= level; i++){
			newNode.next[i] = cur[i];
			if(prev[i] == null) {
				root[i] = newNode;
			}else{
				prev[i].next[i] = newNode;
			}
		}
	}

	public boolean delete(T key) {
		if(isEmpty()){
			return false;
		}

		boolean deleted = false;
		int level = maxLevel-1;

		SkipListNode<T>[] cur = new SkipListNode[maxLevel];
		SkipListNode<T>[] prev = new SkipListNode[maxLevel];

		cur[level] = root[level];
		prev[level] = null;

		for(; level > -1; level--){
			while(cur[level] != null && cur[level].key.compareTo(key) < 0){
				prev[level] = cur[level];
				cur[level] = cur[level].next[level];
			}

			if(cur[level] != null &&  cur[level].key.equals(key)){
				if(prev[level] != null){
					prev[level].next[level] = cur[level].next[level];
				}else if(cur[level].next[level] == null){
					cur[level].next[level] = null;
					maxLevel--;
				}else{
					root[level]=cur[level].next[level];
				}
				deleted = true;
			}

			if(level > 0){
				if(prev[level] == null){
					cur[level-1] = root[level-1];
					prev[level-1] = null;
				}else {
					cur[level-1] = prev[level].next[level-1];
					prev[level-1] = prev[level];
				}
			}
		}
		return deleted;
	}

	public T first() {
		return root[0].key;
	}

	public T search(T key) {
		if(isEmpty()){
			return null;
		}

		int level = maxLevel-1;

		SkipListNode<T>[] cur = new SkipListNode[maxLevel];
		SkipListNode<T>[] prev = new SkipListNode[maxLevel];

		cur[level] = root[level];
		prev[level] = null;

		for(; level > -1; level--){
			while(cur[level] != null && cur[level].key.compareTo(key) < 0){
				prev[level] = cur[level];
				cur[level] = cur[level].next[level];
			}

			if(cur[level] != null &&  cur[level].key.equals(key)){
				return key;
			}

			if(level > 0){
				if(prev[level] == null){
					cur[level-1] = root[level-1];
					prev[level-1] = null;
				}else {
					cur[level-1] = prev[level].next[level-1];
					prev[level-1] = prev[level];
				}
			}
		}
		return null;
	}
	
	public String getPathToLastNode() {
		if (isEmpty()) {
			return null;
		}

		String output = "";
		int level = maxLevel - 1;

		SkipListNode<T> cur;
		SkipListNode<T> prev;

		prev = root[level];
		cur = root[level];
		while (level >= 0) {


			while (cur != null) {
				prev = cur;
				cur = cur.next[level];
				output += "[" + prev.key + "]";
			}

			if(cur == null){
				if(level > 0) {
					level--;
					cur = prev.next[level];
				}else{
					return  output;
				}
			}
		}
		return output;
	}

}