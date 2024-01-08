package org.algorithms.dataStructures.leastRecentlyUsed;

import java.util.HashMap;

/**
 * Implementation of Least Recently Used (LRU) cache using a doubly linked list and a HashMap for quick lookups.
 *
 * <p>
 * Time Complexity: O(1). For insert, pop and fetch.
 * <p>
 * Auxiliary Space: O(n), where 'n' is the cache capacity.
 *
 * @author Ayush Singhal
 */
class LRUCache {
    HashMap<Integer, CacheNode> cacheMap;
    int size;
    CacheNode head, tail;

    /**
     * Constructor to initialize the cache with the given capacity.
     *
     * @param capacity The maximum capacity of the cache.
     */
    public LRUCache(int capacity) {
        this.size = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new CacheNode();
        this.tail = new CacheNode();

        // Connect head and tail to form an empty doubly linked list
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Prints the value associated with the given key.
     *
     * @param key: The key to look up in the cache.
     * @return Node associated with key.
     */
    public CacheNode fetch(int key) {
        CacheNode node = null;
        if (cacheMap.containsKey(key)) {
            node = cacheMap.get(key);
            moveToHead(node);
        }
        return node;
    }

    /**
     * Inserts or updates a key-value pair in the cache.
     *
     * @param key:   The key to insert or update.
     * @param value: The value associated with the key.
     */
    public void insert(int key, int value) {
        if (cacheMap.containsKey(key)) {
            // Update existing key's value and move it to the head
            cacheMap.get(key).value = value;
            moveToHead(cacheMap.get(key));
            return;
        }

        if (this.size == this.cacheMap.size()) {
            // If the cache is full, remove the least recently used node (tail)
            popTail();
        }

        // Create a new cache node and add it to the head
        CacheNode cache = new CacheNode(key, value);
        this.cacheMap.put(key, cache);
        addNode(cache);
    }

    /**
     * Removes a node from the doubly linked list.
     *
     * @param node The cache node to be removed.
     */
    private void removeNode(CacheNode node) {
        CacheNode prev = node.prev;
        CacheNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * Adds a node to the head of the doubly linked list.
     *
     * @param node The cache node to be added.
     */
    private void addNode(CacheNode node) {
        node.next = this.head.next;
        node.prev = this.head;

        this.head.next.prev = node;
        this.head.next = node;
    }

    /**
     * Moves a node to the head of the doubly linked list (since it's recently used).
     *
     * @param node The cache node to be moved.
     */
    private void moveToHead(CacheNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Removes the least recently used node (tail) from the cache.
     */
    private void popTail() {
        this.cacheMap.remove(this.tail.prev.key);
        removeNode(this.tail.prev);
    }

    @Override
    public String toString() {
        return "LRUCache{" + "size=" + size + '}';
    }
}
