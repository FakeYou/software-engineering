import java.util.Arrays;
import java.util.concurrent.Callable;

public class SnelheidOefening {

    public SnelheidOefening() {
        testZitErinA(10);
    }

    /**
     * @param args
     */
    public static void main( String[] args){
        new SnelheidOefening();
    }

    // Hulpmethode voor tijdsbepaling
    private static long tijd(){
        return System.currentTimeMillis();
    }

    private static void testZitErinA(int aantal) {
        long[] tijden = new long[aantal];
        long totaal = 0;

        GetalRij rij = new GetalRij(80000, 200000);

        for(int i = 0; i < aantal; i++) {
            long start = tijd();

            rij.zitErinA(30000);

            long end = tijd();

            tijden[i] = end - start;
            totaal += end - start;
        }

        System.out.println(Arrays.toString(tijden));
        System.out.println("gemiddelde: " + (totaal / aantal));
    }

    private static long[] gemiddeldeTijd(int aantal, Callable<Boolean> test) {
        long[] tijden = new long[aantal];
        long totaal = 0;

        for(int i = 0; i < aantal; i++) {
            long start = tijd();
            boolean result = false;

            try {
                result = test.call();
            }
            catch(Exception e) {
                System.out.println(e);
            }

            long end = tijd();
            System.out.println(end - start);
            System.out.println("Result: " + result);
            tijden[i] = end - start;
            totaal += end - start;
        }


        System.out.println(Arrays.toString(tijden));
        System.out.println("gemiddelde: " + (totaal / aantal));

        return tijden;
    }
}

