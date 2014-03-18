/**
 * Created by FakeYou on 10-3-14.
 */
public class Multi {
    public static void main(String[] args) {
        new Multi();
    }

    private static int current = 1;


    public Multi() {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println('1');
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println('2');
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println('3');
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println('4');
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
