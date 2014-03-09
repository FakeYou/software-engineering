package benchmark;

import huffman.Hzip;
import sun.org.mozilla.javascript.internal.EcmaError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by FakeYou on 3/9/14.
 */
public class Benchmark {

    public static void main(String[] args) {
        new Benchmark();
    }

    public Benchmark() {

        String path = "src/benchmark/files/";
        String[] files = {
                "test1 (short natural text).txt",
                "test2 (long natural text).txt",
                "test3 (short natural text).txt",
                "test4 (long random text).txt",
                "test5 (long single character).txt",
                "test6 (long double character).txt",
                "test7 (short single character).txt",
                "test8 (short double character).txt",
                "test9 (moby dick).txt"
        };

        for(int i = 0; i < files.length; i++) {
            String file = path + files[i];

            try {
                Hzip.compress(file);

                long before = fileSize(file);
                long after = fileSize(file + ".huf");
                float compression = (float) after / (float) before * 100.0f;

                System.out.println("File: " + file);
                System.out.println("Before: " + before + " bytes");
                System.out.println("After: " + after + " bytes");
                System.out.println("Compression: " + compression + "%");
                System.out.println();
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }

    private long fileSize(String filename) {
        File file = new File(filename);

        return file.length();
    }
}
