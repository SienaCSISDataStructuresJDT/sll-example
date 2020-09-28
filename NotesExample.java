
/**
 * A sample usage of the SimpleLinkedList that is explained
 * in some painful detail in the topic notes for linked lists.
 * 
 * @author Jim Teresco
 */

public class NotesExample {

    public static void main(String args[]) {

        // we begin by creating an empty list of Double
        // values
        SimpleLinkedList<Double> dlist = new SimpleLinkedList<Double>();
        
        // let's add a value, which obviously becomes the first element
        dlist.add(17.1);
        
        // our second value, which we will add also at the default position
        // which for these lists is an add at position 0
        dlist.add(23.0);
        
        // now we consider the other cases of add, where we specify a position
        // this one adding in the middle
        dlist.add(1, -3.5);
        
        // and adding at the end
        dlist.add(3, 1.1);
        
        // now, let's retrieve an element by position
        double val = dlist.get(2);
        System.out.println("Value at position 2 is " + val);
        
        // we can also modify the element at a position
        dlist.set(1, -7.0);
        
        // and let's see if a value is in the list (one that is, one that isn't)
        boolean hasVal = dlist.contains(23.0);
        System.out.println("Has a 23.0? " + hasVal);
        hasVal = dlist.contains(0.5);
        System.out.println("Has a 0.5? " + hasVal);
        
        // how many elements in our list?
        System.out.println("dlist contains " + dlist.size() + " elements.");
        
        // let's put another values before we try out the remove cases
        dlist.add(0.1);
        
        // remove examples -- note our remove is by position, not value
        
        // first, the simple case of removal of the first element of a list
        double removed = dlist.remove(0);
        System.out.println("Removed from position 0: " + removed);
        
        // now remove the last (remaining) element
        removed = dlist.remove(3);
        System.out.println("Removed from position 3: " + removed);
        
        // remove from the middle of the list
        removed = dlist.remove(1);
        System.out.println("Removed from position 1: " + removed);
        
        // remove the last of two
        removed = dlist.remove(1);
        System.out.println("Removed from position 1: " + removed);

        // and remove the only remaining item
        removed = dlist.remove(0);
        System.out.println("Removed from position 0: " + removed);

    }
}
