package Homework_1;

/**
 * @author Elliott Pryor on 1/30/2019.
 * @project CSCI_232
 */
public class Point {
    int i;
    int j;
    public Point(int i, int j){
        this.i = i;
        this.j = j;
    }
    @Override
    public String toString(){
        return "(" + (Math.pow(i,3) + Math.pow(j,3)) + ", " + i + ", " + j + ")";
    }
}
