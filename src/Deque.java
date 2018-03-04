import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private final Node nullNode;
    private int size;

    private class Node {
        private Node prev;
        private Node next;
        private final Item item;

        Node(Node prev, Node next, Item item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    public Deque()                           // construct an empty deque
    {
        nullNode = new Node(null, null, null);
        first = nullNode;
        last = nullNode;
        size = 0;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return first.item == null && last.item == null;
    }
    public int size()                        // return the number of items on the deque
    {
        return size;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if (null == item) throw new java.lang.IllegalArgumentException();
        Node newFirst = new Node(nullNode, first, item);
        if (isEmpty()) {
            last = newFirst;
            first = newFirst;
        } else {
            first.prev = newFirst;
            first = newFirst;
        }
        size++;
    }
    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) throw new java.lang.IllegalArgumentException();
        Node newLast = new Node(last, nullNode, item);
        if (isEmpty()) {
            first = newLast;
            last = newLast;
        } else {
            last.next = newLast;
            last = newLast;
        }
        size++;
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        // is only one item
        if (first.item == null) {
            last = nullNode;
        } else {
            first.prev = nullNode;
        }
        size--;
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        // is only one item
        if (last.item == null) {
            first = nullNode;
        } else {
            last.next = nullNode;
        }
        size--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("111");
        deque.addFirst("222");
        deque.addFirst("333");
        deque.addFirst("444");

        for (String s : deque) {
            System.out.println(s);
        }
    }
}