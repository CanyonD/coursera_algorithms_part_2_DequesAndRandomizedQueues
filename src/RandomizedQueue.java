import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        this.size = 0;
        this.items = (Item[]) new Object[1];
    }
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return size == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return this.size;
    }
    public void enqueue(Item item)           // add the item
    {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (size == items.length) {
            resizeQueue(items.length * 2);
        }
        items[size++] = item;
    }
    private void resizeQueue(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }
    private int randomIndex()
    {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return StdRandom.uniform(this.size);
    }
    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (size == items.length / 4) {
            resizeQueue(items.length / 2);
        }
        int random = randomIndex();
        Item item = items[random];
        items[random] = items[--size];
        items[size] = null;
        return item;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return items[randomIndex()];
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] random;
        private int current;

        public RandomizedQueueIterator() {
            this.random = new int[size];
            for (int i = 0; i < size; i++) {
                random[i] = i;
            }
            StdRandom.shuffle(random);
            current = 0;
        }
        public boolean hasNext() {
            return current != random.length;
        }
        public Item next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return items[ random [ current++ ] ];
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }


}