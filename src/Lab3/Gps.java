package Lab3;

import DataStructures.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Elliott Pryor on 3/27/2019.
 * @project CSCI_232
 */
public class Gps {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        int size = 10;

        //Reads the data
        try {
            Scanner readFile = new Scanner(new File("input.txt"));//makes scanner

            //Finds the size
            String line = readFile.findInLine("p sp ");
            String[] data = readFile.nextLine().split(" ");
            int[] points = new int[data.length];
            for (int i = 0; i < 2; i++){
                points[i] = Integer.parseInt(data[i]);
            }
            graph = new Graph(points[0]);
            size = points[0];

            //reads all the edges
            line = readFile.findInLine("a ");
            while (line != null){
                data = readFile.nextLine().split(" ");
                points = new int[data.length];
                for (int i = 0; i < 3; i++){
                    points[i] = Integer.parseInt(data[i]);
                }
                graph.addEdge(points[0], points[1], points[2]);
                line = readFile.findInLine("a ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops, file not found");
        }

        System.out.println(graph.toString());


    }
    private void dijsktra(int startNode, Graph graph){
        int[] dist = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];

        for (int i = 0; i < dist.length; i++) { //initialize arrays
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        for(int i = 0; i < graph.size(); i++){
            int curr = minIndex(dist, visited);
            visited[curr] = true;

            int[] edges = graph.getEdges(curr);
            for (int j = 0; j < edges.length; j++) {
                if(edges[j] > 0 && dist[j] > dist[curr] + edges[j]){
                    dist[j] = dist[curr] + edges[j];
                }
            }

        }

    }
    private int minIndex(int[] distances, boolean[] visited){
        int index = 0;
        int val = Integer.MIN_VALUE;
        for (int i = 0; i < distances.length; i++) {
            if(distances[i] != Integer.MAX_VALUE && distances[i] > val && !visited[i]){
                val = distances[i];
                index = i;
            }
        }
        return index;
    }
}
