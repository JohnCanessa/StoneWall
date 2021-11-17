import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 
 */
public class StoneWall {


    /**
     * What is the minimum number of buildings required to produce the given skyline?
     * 
     * Given an array H of N positive integers specifying the height of the wall, 
     * return the minimum number of blocks needed to build it.
     * 
     * Observations:
     * 
     * o If the two horizontal edges are adjacent to the same stone block, 
     *   they must be at the same height. 
     * o The stone wall between the two edges cannot be lower than them.
     * 
     * Keep on a stack a sequence of horizontal edges, 
     * such that their heights form an increasing sequence.
     */
    static public int stoneWall(int[] arr) {

        // **** initialization ****
        int blockCount                  = 0;
        Stack<Integer> stack            = new Stack<>();

        // ???? ????
        HashMap<Integer, Integer> hc    = new HashMap<>();

        // **** traverse the wall counting blocks - O(n) ****
        for (int h : arr) {

            // ???? ????
            System.out.println("<<< h: " + h);

            // **** remove blocks higher than h ****
            while (!stack.isEmpty() && stack.peek() > h)
                stack.pop();

            // ???? ????
            System.out.println("<<< stack: " + stack.toString());
            
            // **** ****
            if (!stack.isEmpty() && stack.peek() == h)
                continue;
            else {

                // **** push this height ****
                stack.push(h);

                // **** count this block ****
                blockCount++;

                // ???? update the height count hashmap ????
                Integer c = hc.putIfAbsent(h, 1);
                if (c != null) hc.put(h, ++c);
            }

            // ???? ????
            System.out.println("<<< stack: " + stack.toString() + " blockCount: " + blockCount);
        }

        // ???? ????
        System.out.println("<<< hc: " + hc.toString());

        // **** return block count ****
        return blockCount;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open a buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read int[] arr ****
        int[] arr = Arrays.stream(br.readLine().trim().split(", "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** call function of interest and display result ****
        System.out.println("main <<< result: " + stoneWall(arr));
    }

}