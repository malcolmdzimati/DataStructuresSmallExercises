/**
 * Name: Batsirai malcolm Dzimati
 * Student Number: u20450678
 */

abstract class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }
    
    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }

    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    
    //Recursive functions

    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        if(node.next!=null){
            return (node.key+sumRec(node.next));
        }
        return node.key;
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        if(node.next!=null){
            node.data=(node.key*sumRec(node)-dataRec(node.next));
            return node.data;
        }
        node.data=(node.key);
        return node.key;
    }


    //Organising List functions

    /**
     * Retrieve the node with the specified key, move the node as required and recalculate all data fields.
     * @return The node with its data value before it has been moved, otherwise 'null' if the key does not exist.
     * Implement only in specific subclass!
     */
    public abstract ListNode searchNode(Integer key);

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public boolean contains(Integer key) {
        boolean out=false;
        if(root!=null){
            if(root.key==key){
                calculateData();
                return true;
            }
            ListNode temp = root;
            while(temp!=null){
                if(temp.key.equals(key)){
                    return true;
                }
                temp=temp.next;
            }
        }
        return out;
    }

    /**
     * Insert a new key into the linked list.
     * New nodes should be inserted at the back.
     * calculateData() should be called after insertion.
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        if(root!=null){
            if(contains(key)){
                return;
            }

            ListNode temp = root;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next = new ListNode(key);
        }else{
            root = new ListNode(key);
        }
        calculateData();
    }
	
    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        if(root!=null){
            if(root.key==key){
                root=root.next;
                calculateData();
            }

            ListNode temp = root;
            while(temp.next!=null){
                if(temp.next.key.equals(key)){
                    temp.next=temp.next.next;
                    break;
                }
                temp=temp.next;
            }
        }
        calculateData();
    }


    //Helper functions

    


}