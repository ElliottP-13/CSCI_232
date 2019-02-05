package JobScheduler;

import Assets.ReadWrite;
import DataStructures.DLLPriorityQueue;
import DataStructures.Heap;

import java.io.File;
import java.util.Random;

/**
 * Created by pryor on 1/28/2019.
 */
public class MakeInputs {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 50; //number of trials
        ReadWrite r = new ReadWrite();
        File f = r.createFileIfNotExists("input.txt");
        String writeme = "";
        r.overwriteFileWithString("", f);
        for (int i = 0; i < n; i++) {
            writeme = "" + i;//job name
            writeme += " " + rand.nextInt(n);//priority
            writeme += " " + rand.nextInt(10 * n);//arrival time
            writeme += " " + rand.nextInt(100);//duration
            writeme += "\n";
            r.appendToFile(writeme,f);
        }
    }
}
