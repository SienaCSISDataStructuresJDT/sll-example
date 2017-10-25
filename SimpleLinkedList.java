/*
SimpleLinkedList.java

Implementation of a very simple singly-linked list structure using 
Java 1.5 generics

Jim Teresco, The College of Saint Rose, CSC 523, Summer 2014
Updated for CSIS 210, Siena College, Fall 2016

Based on previous implementations from
CSC 136, Williams College, COMSC 211, Mount Holyoke College, and CSC
501, The College of Saint Rose
 */

import java.util.Iterator;

/*
The list node: a class which is not public -- only the other classes
within this file will be able to construct one.
 */

class SimpleListNode<E> {
    /** Object of type E to be stored in this node */
    protected E value;
    /** reference to next node, null if this is the last one */
    protected SimpleListNode<E> next;

    /**
    construct a new list object

    @param value value to be stored in this list node
    @param next reference to next node in the list
     */
    public SimpleListNode(E value, SimpleListNode<E> next) {
        this.value = value;
        this.next = next;
    }

    /**
    get data value from this list object

    @return data object stored
     */
    public E value() {

        return value;
    }

    /**
    get next reference from this list object

    @return reference to next list object
    @return null if this is the last object in the list
     */
    public SimpleListNode<E> next() {

        return next;
    }

    /**
    set the data value of this object to a new value.  Old value is returned

    @param value new data value to be stored
    @return old stored data value
     */
    public E setValue(E value) {
        E prev = this.value;
        this.value = value;
        return prev;
    }

    /**
    set the next reference of this object to a new value.  
    Old next reference is returned

    @param next new next reference to be stored
    @return old stored next reference
     */
    public SimpleListNode<E> setNext(SimpleListNode<E> next) {
        SimpleListNode<E> prev = this.next;
        this.next = next;
        return prev;
    }
}

/**
Simple singly-linked list structure's iterator.

 */
class SimpleListIterator<E> implements java.util.Iterator<E> {
    /** Reference to currently considered element within list */
    protected SimpleListNode<E> current;
    /** The head of list for reset */
    protected SimpleListNode<E> head;

    /**
    Construct an iterator that traverses list beginning at t.

    @post returns an iterator that traverses a linked list
    @param t The first element of list to be traversed.
     */
    public SimpleListIterator(SimpleListNode<E> t) {
        head = t;
        current = head;
    }

    /**
    Determine if the iteration is finished.

    @post returns true if there is more structure to be viewed:
    i.e., if value (next) can return a useful value.

    @return True if the iterator has more elements to be considered.
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
    Return current value and increment Iterator.

    @pre traversal has more elements
    @post returns current value and increments iterator

    @return The current value, before increment.
     */
    public E next() {
        E temp = current.value();
        current = current.next();
        return temp;
    }

    /**
    Remove operation not supported by this Iterator
     */
    public void remove() {

        throw new UnsupportedOperationException();
    }
}

/**
Generic singly-linked list structure.  This has only simple nodes,
and no element count or tail reference is included.
 */
public class SimpleLinkedList<E> implements Iterable<E> {

    /** reference to the node that contains the first value in the list */
    protected SimpleListNode<E> head;

    /**
    construct a new, empty list
     */
    public SimpleLinkedList() {

        head = null;
    }

    /**
    add a new item as the pos'th object in the list

    @param pos desired position of the object in the list
    @param obj data object to insert
    @pre the list must have between 0 and pos-1 entries
    @post the list is modified to have obj at position pos, subseqent items slide down
     */
    public void add(int pos, E obj) {

        // negative positions not allowed
        if (pos < 0) {
            throw new IndexOutOfBoundsException("Attempt to add at negative position " + pos);
        }

        // if we are adding to an empty list it must be at position 0
        if ((head == null) && (pos != 0)) {
            throw new IndexOutOfBoundsException("Attempt to add at position " + pos + " in empty list");
        }

        // are we adding at the front?  note that if head is null, this
        // inserts a first element, if not, it inserts at the head of
        // an existing list
        if (pos == 0) {
            head = new SimpleListNode<E>(obj, head);
            return;
        }

        // we are adding somewhere else, find entry after which we will 
        // insert our item
        int i = 0;
        SimpleListNode<E> finger = head;
        while (i < pos-1) {
            i++;
            finger = finger.next();
            if (finger == null) {
                throw new IndexOutOfBoundsException("Attempt to add at position " + pos + " in list of size " + i);
            }
        }
        // finger points at the node after which we want to add
        // so create the new object with finger's next as its next
        // and set finger's next to the new node.
        // note that this also works for the case when we are adding
        // to the end
        finger.setNext(new SimpleListNode<E>(obj, finger.next()));
    }

    /**
    add at 0 is the default kind of add, and it simply calls the
    general purpose add

    @param the item to be added to the start of the list
     */
    public void add(E obj) {

        add(0, obj);
    }

    /**
    retrieve an item in the list

    @param pos position at which to find the element
    @pre the list has at least pos+1 elements
    @return the item at position pos
     */
    public E get(int pos) {

        // negative positions not allowed
        if (pos < 0) {
            throw new IndexOutOfBoundsException("Attempt to get from a negative position " + pos);
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("Attempt to get from an empty list");
        }

        SimpleListNode<E> finger = head;
        int i = 0;
        while (i < pos) {
            i++;
            finger = finger.next();
            if (finger == null) {
                throw new IndexOutOfBoundsException("Attempt to get element " + pos + " from a " + i + " element list");
            }
        }
        return finger.value();
    }

    /**
    set an item in the list by position

    @param pos position at which to replace an element
    @pre the list has at least pos+1 elements
    @return the previous item at position pos
     */
    public E set(int pos, E obj) {

        // negative positions not allowed
        if (pos < 0) {
            throw new IndexOutOfBoundsException("Attempt to set at negative position " + pos);
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("Attempt to set in an empty list");
        }

        SimpleListNode<E> finger = head;
        int i = 0;
        while (i < pos) {
            i++;
            finger = finger.next();
            if (finger == null) {
                throw new IndexOutOfBoundsException("Attempt to set element " + pos + " from a " + i + " element list");
            }
        }
        return finger.setValue(obj);    
    }

    /**
    search for an item in the list, return whether it is found

    @param obj object to search for in list
    @return true if the item is found
    @return false if the items is not in the list
     */
    public boolean contains(E obj) {

        // easy when the list is empty
        if (head == null) return false;

        // otherwise look for it
        SimpleListNode<E> finger = head;
        while (finger != null) {
            if (finger.value().equals(obj)) return true;
            finger = finger.next();
        }
        return false;
    }

    /**
    return the number of elements in the list

    @return the number of elements in the list
     */
    public int size() {

        SimpleListNode<E> finger = head;
        int count = 0;
        while (finger != null) {
            count++;
            finger = finger.next();
        }
        return count;
    }

    /**
    remove an item from the list, by position

    @param pos the position whose element is to be removed
    @return the object removed
     */
    public E remove(int pos) {

        // negative positions not allowed
        if (pos < 0) {
            throw new IndexOutOfBoundsException("Attempt to remove from negative position " + pos);
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("Attempt to remove from an empty list");
        }

        // check for removal of the first item in the list
        // this works for all cases including the case of a 
        // one-element list, as head gets set to null
        if (pos == 0) {
            E retval = head.value();
            head = head.next();
            return retval;
        }

        // remove an item at a non-first position
        SimpleListNode<E> finger = head;
        int count = 0;
        // find the item before the one we want to remove
        while (count < pos-1) {
            count++;
            finger = finger.next();
            if (finger == null) {
                throw new IndexOutOfBoundsException("Attempt to remove element at index " + pos + " in a list with " + count + " elements");
            }
        }
        // finger is pointing to item pos-1
        // make sure there is something at pos
        if (finger.next() == null) {
            throw new IndexOutOfBoundsException("Attempt to remove element at index " + pos + " in a list with " + count + " elements");
        }
        E retval = finger.next().value();
        finger.setNext(finger.next().next());
        return retval;
    }

    /**
    remove all items from the list

    @post all items are removed from the list
     */
    public void clear() {

        head = null;
    }

    /**
    create iterator for the list

    @return iterator over SimpleLinkedList
     */
    public Iterator<E> iterator() {

        return new SimpleListIterator<E>(head);
    }

    /**
    generate a string representation of a linked list 

    @return a representation of the list items as a String
     */
    public String toString() {

        StringBuffer s = new StringBuffer();
        s.append("<SimpleLinkedList:");
        Iterator<E> li = iterator();
        while (li.hasNext())
        {
            s.append(" "+li.next());
        }
        s.append(">");
        return s.toString();
    }

    public static void main(String[] args) {

        // build a list and do some things with it
        SimpleLinkedList<Integer> list = new SimpleLinkedList<Integer>();

        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(4);
        list.add(2, 5);
        list.set(3, 6);

        System.out.println(list);

        System.out.println("Size: " + list.size());
        System.out.println("Item at position 2: " + list.get(2));
        System.out.println("contains(3): " + list.contains(3));
        System.out.println("contains(4): " + list.contains(4));
        System.out.println("contains(5): " + list.contains(5));
        System.out.println("contains(23): " + list.contains(23));

        System.out.println("remove(3) removed : " + list.remove(3));
        System.out.println(list);

        System.out.println("remove(0) removed : " + list.remove(0));
        System.out.println(list);

        int first = list.get(0);
        int second = list.get(1);

        // look, an enhanced for loop!  we can do this because our class
        // implements the Iterable interface.
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println("Sum of entries: " + sum);
    }
}

