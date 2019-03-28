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

        int n = 10; //number of trials
        int edges = 20;
        r.appendToFile("p sp " + n + " " + edges + "\n", f);

        for (int i = 0; i < edges; i++) {
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);
            int weight = rand.nextInt(10) + 1;
            writeme = "a " + x + " " + y + " " + weight + " " +"\n";
            r.appendToFile(writeme,f);
        }

    }
}
