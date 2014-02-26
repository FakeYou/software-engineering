import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GetalRij {
    private int[] getallen;

    public GetalRij( int aantal, int max ){
        // Belangrijke aanname: aantal < max, anders kunnen de getallen niet uniek zijn.
        getallen = new int[aantal];
        vulArrayMetUniekeWaarden( aantal, max );
    }

    private void vulArrayMetUniekeWaarden(int aantal, int max) {
        // Vul een hulplijst met getallen 0, ..., max
        ArrayList hulpLijst = new ArrayList( max );
        for ( int i=0; i<max; i++){
            hulpLijst.add( i );
        }

        // Stop 'aantal' random waarden in getallen
        Random r = new Random();
        for ( int i=0; i<aantal; i++){
            // Het omzetten van Integer naar int gaat sinds Java 1.5 automatisch (unboxing).
            int getal = (Integer) (hulpLijst.remove( r.nextInt( hulpLijst.size())));
            getallen[i] = getal;
        }
    }

    public boolean zitErinA( int zoekWaarde ){
        int i = 0;
        boolean zitErin = false;

        while(i < getallen.length) {
            int getal = getallen[i];

            if(getal == zoekWaarde) {
                zitErin = true;
            }

            i++;
        }

        return zitErin;
    }

    public boolean zitErinB( int zoekWaarde ){
        int i = 0;
        boolean zitErin = false;

        while(i < getallen.length) {
            int getal = getallen[i];

            if(getal == zoekWaarde) {
                zitErin = true;
                break;
            }

            i++;
        }

        return zitErin;
    }

    public boolean zitErinC( int zoekWaarde ){
        int i = 0;
        boolean zitErin = false;

        while(i < getallen.length) {
            int getal = getallen[i];

            // Uitgaand van een gesoortde getallen reeks kunnen we zeggen dat
            // als het huidige getal meer is dan zoekWaarde dat de zoekWaarde
            // niet meer kan voorkomen in de array
            if(getal > zoekWaarde) {
                break;
            }

            if(getal == zoekWaarde) {
                zitErin = true;
                break;
            }

            i++;
        }

        return zitErin;
    }

    public boolean zitErinD( int zoekWaarde ){
        int bottom = 0;
        int top = getallen.length - 1;

        while(true) {

            if(top < bottom) {
                return false;
            }

            int middle = (int) Math.floor((top + bottom) / 2);
            int getal = getallen[middle];

            if(getal == zoekWaarde) {
                return true;
            }
            else if(getal > zoekWaarde) {
                bottom = middle + 1;
            }
            else if(getal < zoekWaarde) {
                top = middle - 1;
            }
        }
    }

    public void sorteer(){
        Arrays.sort( getallen);
    }

    public void print(){
        for( int i=0; i<getallen.length; i++)
            System.out.println(getallen[i]);
    }

}
