package tests;

import org.junit.Test;
import ratings.ProblemSet;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TestProblemSet {

    public static boolean compareDoubles(double num1, double num2) {
        return Math.abs(num1 - num2) < 0.001;
    }

    // TODO: Implement this method to return the average of all the numbers in the input ArrayList
    //       If the ArrayList is empty, return 0.0
    //
    // Examples
    // [1.0,2.0,3.0] returns 2.0
    // [-5.0,5.0] returns 0.0
    // [6.5,6.5,8.5,8.5] returns 7.5
    // [] returns 0.0
    @Test
    public void testAverage() {
        ArrayList<Double> array01 = new ArrayList<Double>();
        array01.add(1.0);
        array01.add(2.0);
        array01.add(3.0);
        assertTrue(compareDoubles(ProblemSet.average(array01), 2.0));

        ArrayList<Double> array02 = new ArrayList<Double>();
        array02.add(-5.0);
        array02.add(5.0);
        assertTrue(compareDoubles(ProblemSet.average(array02), 0.0));

        ArrayList<Double> array03 = new ArrayList<Double>();
        array03.add(6.5);
        array03.add(6.5);
        array03.add(8.5);
        array03.add(8.5);
        assertTrue(compareDoubles(ProblemSet.average(array03), 7.5));

        ArrayList<Double> array04 = new ArrayList<Double>();
        assertTrue(compareDoubles(ProblemSet.average(array04), 0.0));
    }

    // TODO: Write a public static method named sumOfDigits that takes an int as a parameter and
    //       returns the sum of the digits of the input as an int
    //
    // Examples
    // 123 returns 6
    // 57 returns 12
    // -36 returns 9

    @Test
    public void testSumOfDigits() {
        assertTrue(ProblemSet.sumOfDigits(123) == 6);
        assertTrue(ProblemSet.sumOfDigits(57) == 12);
        assertTrue(ProblemSet.sumOfDigits(-36) == 9);
    }

    // TODO: Write a public static method named bestKey that takes a HashMap of String to Integer
    //       as a parameter and returns a key mapping to the largest Integer. Ties can be broken arbitrarily.
    //       If the HashMap is empty, return the empty String
    //
    // Examples
    // {"CSE": 100, "MTH": 90, "MGT": 10} returns "CSE"
    // {"cat": 5, "dog": 5, "fox": 4} can return either "cat" or "dog"
    // {} returns ""

    @Test
    public void testBestKey() {
        HashMap<String, Integer> map01 = new HashMap<String, Integer>();
        map01.put("MTH", 90);
        map01.put("CSE", 100);
        map01.put("MGT", 10);
        assertTrue(ProblemSet.bestKey(map01) == "CSE");
    }


}