package org.algorithms.dataStructures.leastRecentlyUsed;

/**
 * The Application class serves as the entry point for the LRUCache demonstration.
 *
 * @author Ayush Singhal
 */
public class Application {

    /**
     * The main method for the LRUCache demonstration.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an LRUCache instance with a capacity of 3
        LRUCache lruCache = new LRUCache(3);

        // Insert key-value pairs into the LRUCache
        lruCache.insert(1, 12);
        lruCache.insert(2, 13);
        lruCache.insert(3, 14);

        // Print the value associated with key 2, promoting it to the front of the cache
        System.out.println(lruCache.fetch(2));

        // Insert additional key-value pairs
        lruCache.insert(4, 15);
        lruCache.insert(5, 16);

        // Print the value associated with key 3, promoting it to the front of the cache
        System.out.println(lruCache.fetch(3));

        // Update the value associated with key 5 and print the value associated with key 4
        lruCache.insert(5, 17);
        System.out.println(lruCache.fetch(4));

        System.out.println(lruCache);
    }
}
