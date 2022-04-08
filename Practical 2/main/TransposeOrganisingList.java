/**
 * Name:
 * Student Number:
 */

public class TransposeOrganisingList extends OrganisingList {

    ////// Implement the function below this line //////

    /**
     * Retrieve the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate all data fields.
     * @return The node with its data value before it has been moved,
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

        ListNode out = null,temp = root.next, prev=root, prevprev=root;
        if(root!=null){
            while(temp!=null){
                if(temp.key.equals(key)){
                    out =  new ListNode(key, temp.data);
                    prevprev.next=temp;
                    prev.next=temp.next;
                    temp.next=prev;
                    //prev=temp;
                    break;
                }
                prevprev=prev;
                prev=temp;
                temp=temp.next;
            }
        }
        calculateData();
        return out;
    }


}