package multiformat;

/**
 * Created by FakeYou on 2/11/14.
 */
public class NumberBaseException extends Exception {
    char character;

    public NumberBaseException(char character) {
        super("character '" + character + "' not allowed (in this base)");
        this.character = character;
    }
}
