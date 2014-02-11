import java.util.Arrays;

/**
 * Created by FakeYou on 2/11/14.
 */
public class ArrayAdd {
    public static void main(String[] args) {
        try {
            ArrayAdd a = new ArrayAdd();

            a.addArrays(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 });
            a.addArrays(new int[] { 3, 532, 3 }, new int[] { 4, 5, 6 });
            a.addArrays(new int[] { 1, 2 }, new int[] { 4, 5, 6 });
        }
        catch(ArraySizeException e) {
            System.out.println(e);
        }
    }

    public int[] addArrays(int[] arrayOne, int[] arrayTwo) throws ArraySizeException {
        if(arrayOne.length != arrayTwo.length) {
            throw new ArraySizeException(arrayOne.length, arrayTwo.length);
        }

        int[] addedArray = new int[arrayOne.length];

        for(int i = 0; i < arrayOne.length; i++) {
            addedArray[i] = arrayOne[i] + arrayTwo[i];
        }

        System.out.println(Arrays.toString(addedArray));

        return addedArray;
    }
}
