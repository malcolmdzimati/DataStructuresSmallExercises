// Do not modify this file

public class SkipListNode<T>
{
	public T key;
	public SkipListNode<T>[] next;
	
	@SuppressWarnings("unchecked")
	SkipListNode(T i, int n)
	{
		key = i;
		next = new SkipListNode[n];

		for (int j = 0; j < n; j++)
			next[j] = null;
	}
}

/*
if(cur != null && cur.key.equals(key) ){
				if(cur == prev){
					if(root[level].next[level] == null){
						maxLevel--;
						root[level] = root[level-1].next[level-1];
					}
					root[level-1] = root[level-1].next[level-1];
					cur = root[level];
					prev = root[level];
					level--;
				}

				prev.next[level] = cur.next[level];
				if (cur.next[level] == null) {
					prev.next[level] = null;
				}
				deleted = true;
			}

			if(first().equals(key)){
			int i=0;
			while(root[i].key.equals(key)) {
				root[i] = root[i].next[i];
				i++;
			}
			return  true;
		}
*/ 