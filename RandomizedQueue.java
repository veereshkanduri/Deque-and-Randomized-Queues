import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] storage;
    private int numOfElements;      // N represents the number of elements

    public RandomizedQueue() {                   // construct an empty randomized queue
        storage = (Item[]) new Object[0];
        numOfElements = 0;
    }

    public boolean isEmpty() {                   // is the randomized queue empty?
        return numOfElements == 0;
    }

    public int size() {                          // return the number of items on the randomized queue
        if (isEmpty()) {
            return 0;
        }

        return numOfElements;
    }

    public void enqueue(Item item) {             // add the item
        if (item == null) {
            throw new IllegalArgumentException();
        }
        int arraySize = storage.length;
        if (arraySize == 0) {
            reSize(1);
        } else if (arraySize == numOfElements) {
            reSize(2 * storage.length);
        }
        storage[numOfElements] = item;
        numOfElements++;
    }

    public Item dequeue() {                      // remove and return a random item
        if (numOfElements == 0) {
            throw new NoSuchElementException();
        }
        int randNum = StdRandom.uniform(numOfElements);
        Item item = storage[randNum];
        storage[randNum] = storage[numOfElements - 1];
        storage[numOfElements - 1] = null;
        numOfElements--;
        int arraySize = storage.length;
        if (numOfElements > 0 && numOfElements == arraySize / 4) {
            reSize(arraySize / 2);
        }
        return item;
    }

    public Item sample() {                       // return a random item (but do not remove it)
        if (numOfElements == 0) {
            throw new NoSuchElementException();
        }
        int randNum = StdRandom.uniform(numOfElements);
        return storage[randNum];
    }

    public Iterator<Item> iterator() {           // return an independent iterator over items in random order
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = numOfElements;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return storage[--i];
        }
    }

    private void reSize(int x) {
        Item[] tempArr = (Item[]) new Object[x];
        for (int i = 0; i < numOfElements; i++) {
            tempArr[i] = storage[i];
        }
        storage = tempArr;
    }

    public static void main(String[] args) {     // unit testing (optional)

    }

}
