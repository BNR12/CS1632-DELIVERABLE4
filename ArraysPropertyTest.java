import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArraysPropertyTest{

    /* generateArrays method */
    /* Takes an integer as a parameter, specifying the number of random arrays to be generated. */
    /* Returns an ArrayList of size n, containing the random arrays. */
    public ArrayList<int[]> generateArrays(int n){

        //Initialize a new ArrayList of size n
        ArrayList<int[]> random = new ArrayList(n);

        //Initializa a new random number generator
        Random generator = new Random();
        int size;
        int number;


        for (int i = 0; i < n; i++){

            //Get the size of the next randomly generated array (between 1 - 100)
            size = generator.nextInt(100) + 1;

            //Initialize a temporary array of this size
            int[] temp = new int[size];

            for (int x = 0; x < size; x++){

                //Generate a number for the array, (between 0 - 999)
                number = generator.nextInt(1000);
                temp[x] = number;

            }

            //Add random array to the ArrayList
            random.add(temp);

        }

        return random;
    }

    /* Set up for tests. */
    /* Generate 100 random arrays. */
    int numArrays = 100;
    ArrayList<int[]> randomArrays = generateArrays(numArrays);

    /* Property Test 1: Function is pure. */
    /* Run sort on two identical arrays, test outputs are identical. */
    @Test
    public void testPure(){

        boolean isPure = true;

        for (int i = 0; i < numArrays; i++){

            int[] original = randomArrays.get(i);
            int size = original.length;

            //Make two copies of the current random array
            int[] copy1 = Arrays.copyOf(original, size);
            int[] copy2 = Arrays.copyOf(original, size);

            //Sort the arrays
            Arrays.sort(copy1);
            Arrays.sort(copy2);

            //Compare the arrays
            isPure = Arrays.equals(copy1, copy2);

        }

        //If the function is pure, isPure will still be true
        assertTrue(isPure);

    }

    /* Property Test 2: Same number of elements. */
    /* Run sort, test that input and output arrays contain the same number of elements. */
    @Test
    public void testLength(){

        boolean sameLength = true;

        for (int i = 0; i < numArrays; i++){

            int[] original = randomArrays.get(i);
            int size = original.length;

            //Make a copy of the current random array
            int[] copy = Arrays.copyOf(original, size);

            //Sort the array and get its new size
            Arrays.sort(copy);
            int sortSize = copy.length;

            //Test that the lengths are equal
            if (size != sortSize){
                sameLength = false;
            }

        }

        assertTrue(sameLength);

    }

    /* Property Test 3: Function is idempotent. */
    /* Run sort on an array and then again on the same array, test outputs are identical*/
    @Test
    public void testIdempotent(){

        boolean isIdempotent = true;

        for (int i = 0; i < numArrays; i++){

            int[] original = randomArrays.get(i);
            int size = original.length;

            //Make a copy of the current random array
            int[] copy1 = Arrays.copyOf(original, size);

            //Sort the array
            Arrays.sort(copy1);

            //Make a copy of the sorted array
            int[] copy2 = Arrays.copyOf(copy1, size);

            //Sort the array again
            Arrays.sort(copy1);

            //Test that the two copies are equal
            isIdempotent = Arrays.equals(copy1, copy2);

        }

        //If the function is idempotent, isIdempotent will still be true
        assertTrue(isIdempotent);

    }

  
  
}
