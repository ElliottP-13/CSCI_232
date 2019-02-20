package Assets;

import java.io.File;
import java.util.Random;

/**
 * Created by pryor on 1/28/2019.
 */
public class MakeInputs {
    public static void main(String[] args) {
        Random rand = new Random();
        ReadWrite r = new ReadWrite();
        File f = r.createFileIfNotExists("input.txt");
        String writeme = "";
        r.overwriteFileWithString("", f);

        int n = 100; //number of trials
        int capacity = 50;

        for (int i = 0; i < n; i++) {
            writeme = "" + rand.nextInt(capacity) + "\n";
            r.appendToFile(writeme,f);
        }

    }
}
