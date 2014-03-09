package dynProg.solvers;

import dynProg.Solver;

/**
 * Created by FakeYou on 3/5/14.
 */
public class TopDownSolver implements Solver {

    @Override
    public boolean solve(int[] numbers, int sum) {

        int[][] matrix = new int[numbers.length][sum];

        int tempSum = 0;

        for(int i = 0; i < numbers.length; i++) {
            int sumTest = 0;

            for(int j = 0; j <= i; j++) {

            }
        }

        return false;
    }
}
