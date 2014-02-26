import java.awt.*;

public class SnelheidOefening {

    public SnelheidOefening() {
        test('A', 10000);
        test('B', 10000);
        test('C', 10000, true);
        test('D', 10000, true);

//        GetalRij rij = new GetalRij(10, 20);
//        rij.sorteer();
//        rij.print();
//
//        System.out.println(rij.zitErinD(10));
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

    private static void test(char zitErin, int aantal) {
        test(zitErin, aantal, false);
    }

    private static void test(char zitErin, int aantal, boolean sort) {
        long totaal;

        GetalRij rij = new GetalRij(50000, 80000);

        if(sort) {
            rij.sorteer();
        }

        long start = tijd();
        for(int i = 0; i < aantal; i++) {

            int getal = (int) Math.round(Math.random() * 50000);
            boolean result = false;
            switch(zitErin) {
                case 'A':
                    result = rij.zitErinA(getal);
                    break;
                case 'B':
                    result = rij.zitErinB(getal);
                    break;
                case 'C':
                    result = rij.zitErinC(getal);
                    break;
                case 'D':
                    result = rij.zitErinD(getal);
                    break;
            }

            if(result) {
                System.out.print('1');
            }
            else {
                System.out.print('0');
            }
        }
        long end = tijd();
        totaal = end - start;

        System.out.println("\n testZitErin" + zitErin + " * " + aantal + " in " + totaal + "ms (gemiddeld: " + ((float) totaal / (float) aantal) + "ms)");
    }
}

