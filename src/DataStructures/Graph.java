package DataStructures;

import java.util.Arrays;

/**
 * @author Elliott Pryor on 3/27/2019.
 * @project CSCI_232
 */
public class Graph{
    int[][] matrix;
    public Graph(int size){
        matrix = new int[size][size];
    }
    public void addEdge(int head, int tail, int weight){
        matrix[head][tail] = weight;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                sb.append(matrix[i][j]); sb.append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public int size(){
        return matrix.length;
    }

    public int[] getEdges(int index){
        return matrix[index];
    }

}
