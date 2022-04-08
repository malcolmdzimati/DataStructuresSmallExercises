/**
 * Name:
 * Student Number:
 */

public class MoveToFrontOrganisingList extends OrganisingList {

    ////// Implement the function below this line //////

    /**
     * Retrieve the node with the specified key and move the accessed node
     * to the front, then recalculate all data fields.
     * @return The node with its data value before it has been moved to the front,
     * otherwise 'null' if the key does not exist.
     */
    @Override
    public ListNode searchNode(Integer key) {
        if(!contains(key)){
            return null;
        }
        
        if(root.key==key){
            calculateData();
            return new ListNode(key, root.data);
        }

        ListNode out = null,temp = root.next, prev=root;
        if(root!=null){
            while(temp!=null){
                out =  new ListNode(key, temp.data);
                if(temp.key.equals(key)){
                    prev.next=temp.next;
                    temp.next=root;
                    root=temp;
                    calculateData();
                    return out;
                }
                prev=temp;
                temp=temp.next;
            }
        }
        calculateData();
        return out;
    }


}