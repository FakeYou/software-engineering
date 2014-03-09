package dynProg.solvers;

import dynProg.Solver;

import java.util.Arrays;

/**
 * Created by FakeYou on 3/5/14.
 */
public class RecursiveSolver implements Solver {

    @Override
    public boolean solve(int[] numbers, int sum) {

        if(numbers.length == 0 && sum == 0) {
            return true;
        }

        for(int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            int sumTest = current;

            for(int j = i + 1; j < numbers.length; j++) {
                sumTest += numbers[j];

                if(sumTest == sum) {
                    return true;
                }

                if(sumTest - numbers[j - 1] == sum) {
                    return true;
                }
            }
        }

        return false;
    }
}
