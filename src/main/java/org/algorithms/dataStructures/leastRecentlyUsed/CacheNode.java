package org.algorithms.dataStructures.leastRecentlyUsed;

/**
 * Cache node with key, value and link to previous and next cache element.
 *
 * @author Ayush Singhal
 */
class CacheNode {
    int key, value;
    CacheNode prev, next;

    // Constructors
    CacheNode() {}

    CacheNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "CacheNode{" + "key=" + key + ", value=" + value + '}';
    }
}
