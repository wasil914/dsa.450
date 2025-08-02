import java.util.*;

public class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int minEl = Integer.MAX_VALUE;

        // Step 1: Count elements in basket1 and find min element
        for (int x : basket1) {
            mp.put(x, mp.getOrDefault(x, 0) + 1);
            minEl = Math.min(minEl, x);
        }

        // Step 2: Subtract elements in basket2 from the map and update minEl
        for (int x : basket2) {
            mp.put(x, mp.getOrDefault(x, 0) - 1);
            minEl = Math.min(minEl, x);
        }

        // Step 3: Prepare list of unbalanced elements that need to be swapped
        ArrayList<Integer> finalList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int cost = entry.getKey();
            int count = entry.getValue();

            if (count == 0) {
                continue;
            }

            if (count % 2 != 0) {
                return -1;  // If imbalance is odd, cannot balance with swaps
            }

            // Add half of the imbalance (number of swaps needed for this item)
            for (int c = 1; c <= Math.abs(count) / 2; c++) {
                finalList.add(cost);
            }
        }

        // Step 4: Sort the list to start with the cheapest swaps
        Collections.sort(finalList);
        long result = 0;

        // Step 5: Use only the first half (other half is implied from opposite basket)
        for (int i = 0; i < finalList.size() / 2; i++) {
            // Minimum of direct swap or using the smallest element twice
            result += Math.min(finalList.get(i), minEl * 2);
        }

        return result;
    }

    // Optional main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] basket1 = {1, 2, 2, 3};
        int[] basket2 = {1, 3, 3, 2};

        long cost = sol.minCost(basket1, basket2);
        System.out.println("Minimum cost to make baskets identical: " + cost);
    }
}
