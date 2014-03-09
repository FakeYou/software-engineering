package dynProg.solvers;

import dynProg.Solver;

/**
 * Created by FakeYou on 3/5/14.
 */
public class BottomUpSolver implements Solver {

    @Override
    public boolean solve(int[] numbers, int sum) {

        int[][] matrix;
        int tempSum = 0;

        for(int i = 0; i < numbers.length; i++) {
            tempSum += numbers[i];
        }

        matrix = new int[numbers.length][tempSum];

        for(int i = 0; i < numbers.length; i++) {
            int sumTest = 0;

            for(int j = 0; j <= i; j++) {
                matrix[i][numbers[j] - 1] = 1;

                sumTest += numbers[j];

                matrix[i][sumTest - 1] = 1;

                if(j > 0) {
                    tempSum = sumTest - numbers[j - 1];
                    matrix[i][tempSum - 1] = 1;
                }
            }
        }

        if(sum <= matrix[0].length) {
            for(int i = 0; i < matrix.length; i++) {
                if(matrix[i][sum - 1] != 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
