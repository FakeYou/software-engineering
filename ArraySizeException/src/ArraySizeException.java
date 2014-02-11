/**
 * Created by FakeYou on 2/11/14.
 */
public class ArraySizeException extends Exception {
    private int[] sizes = new int[2];

    public ArraySizeException(int sizeOne, int sizeTwo) {
        super("The given arrays don't match in length, lengths: " + sizeOne + ", " + sizeTwo);

        sizes[0] = sizeOne;
        sizes[1] = sizeTwo;
    }

    public int[] getSizes() {
        return sizes;
    }
}
