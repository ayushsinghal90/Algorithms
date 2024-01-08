package org.algorithms.dataStructures.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There is a web scrapper that provides a map of scrapped website url -> [url-1...url-N] in the web page.
 * Write a program that takes a source url and provides all the urls that can be visited from that source url.
 * Example:
 * U1 -> [ u1 , u2, u2 ,u3, u4 , u5 ]
 * U3 -> [ U1 , U6 , U7 ]
 * U7 -> [ U8 ]
 * source: U1
 * Output:
 * U5 U6 U7 U8 U1 U2 U3 U4
 * Can be visited from U1 url.
 * <p>
 * Time Complexity: O(N). where N is the number of overall urls.
 * <p>
 * Auxiliary Space: O(N).
 *
 * @author Ayush Singhal
 */
public class ReachableUrls {
    // Map to store the relationships between addresses
    public static Map<String, List<String>> mapOfAddress;

    /**
     * The main function demonstrates the usage of the address mapping and
     * prints the visited addresses starting from "U1".
     *
     * @param args Command line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Sample mapping of addresses
        mapOfAddress = Map.of("U1", List.of("U1", "U2", "U2", "U3", "U4", "U5"), "U3", List.of("U1", "U6", "U7"), "U7", List.of("U8"));

        // Print the visited addresses starting from "U1"
        for (String add : getVisitedAddress("U1")) {
            System.out.print(add + " ");
        }
    }

    /**
     * Retrieves the list of all linked addresses for a given address.
     *
     * @param address The address to find linked addresses for
     * @return List of linked addresses
     */
    public static List<String> getAllLinkedAddress(String address) {
        return mapOfAddress.getOrDefault(address, Collections.emptyList());
    }

    /**
     * Retrieves the list of visited addresses starting from a given address.
     *
     * @param address The starting address
     * @return Set of visited addresses
     */
    public static Set<String> getVisitedAddress(String address) {
        // Set to store visited addresses
        Set<String> visitedAddress = new HashSet<>();
        // Recursive function to find linked addresses and update visitedAddress Set
        findLinkedAddress(address, visitedAddress);
        return visitedAddress;
    }

    /**
     * Recursive function to find linked addresses starting from a given address.
     *
     * @param address         The current address
     * @param visitedAddress  Set to store visited addresses
     */
    public static void findLinkedAddress(String address, Set<String> visitedAddress) {
        // Mark the current address as visited
        visitedAddress.add(address);
        // Get the list of linked addresses for the current address
        List<String> childAddress = getAllLinkedAddress(address);

        // Iterate through linked addresses
        for (String child : childAddress) {
            // Recursively call the function for unvisited addresses
            if (!visitedAddress.contains(child)) {
                findLinkedAddress(child, visitedAddress);
            }
        }
    }
}
