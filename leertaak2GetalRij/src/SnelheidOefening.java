public class SnelheidOefening {

    public SnelheidOefening() {
        GetalRij rij = new GetalRij(40000, 80000);
        long start;
        long duration;
        boolean result;

        start = tijd();
        result = rij.zitErinA(30000);
        duration = tijd() - start;
        System.out.println("zitErinA duration: " + duration + "ms");
        System.out.println("zitErinA result: " + result);

        start = tijd();
        result = rij.zitErinB(30000);
        duration = tijd() - start;
        System.out.println("zitErinB duration: " + duration + "ms");
        System.out.println("zitErinB result: " + result);

        start = tijd();
        result = rij.zitErinC(30000);
        duration = tijd() - start;
        System.out.println("zitErinC duration: " + duration + "ms");
        System.out.println("zitErinC result: " + result);

        start = tijd();
        result = rij.zitErinD(30000);
        duration = tijd() - start;
        System.out.println("zitErinD duration: " + duration + "ms");
        System.out.println("zitErinD result: " + result);
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
}
