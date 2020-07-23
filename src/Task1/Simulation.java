package Task1;

import Graph.AdjacencyMatrix;
import Graph.Edge;
import Graph.Hamilton;
import Graph.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    private Vertex<String> startVertex;
    private final String[] vertices;
    private final AdjacencyMatrix<String> adjacencyMatrix;
    private ArrayList<Hamilton> hamiltonWay = new ArrayList<>();

    Simulation(String fileName) {
        ArrayList<String[]> data1 = readData(fileName);
        vertices = CreateVertices(data1);
        Edge<String>[] vertexEdge = CreateEdges(data1, vertices);
        adjacencyMatrix = new AdjacencyMatrix<>(vertices, vertexEdge,true,false);
    }

    /*
    Metoda zwraca referencje do klasy hamilotn dla wybranego wierzchołka
     */
    public Hamilton hamiltonSimulation() throws IOException {
        Hamilton<String> hamilton = new Hamilton<>(startVertex,adjacencyMatrix);
        hamilton.hamiltonWay();
        return hamilton;
    }
    /*
    Interakcja z użytkownikiem
    1-wybór wierzchołka
    0-wyjście z programu
     */
    public void menu() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("1 - wybierz miasto poczatkowe\n0 - wyjscie z programu i wuswietelnie rezultatow");
        int wyb = s.nextInt();
        if(wyb == 1){
            inputData();
            hamiltonWay.add(hamiltonSimulation()); //Dodanie do listy drogi Hamiltona dla wybranego wczesniej wierzchołka
            menu();
        }
    }
    /*
    Wybór przez uzytkownika miasta początkowego
     */
    private void inputData() {
        Scanner s = new Scanner(System.in);

        /*
        Wyświetlenie listy dostępnych miast
         */
        System.out.print("Wybierz miasto z któego chcesz wyruszć : ");
        for (String string : vertices)
            System.out.print(string+" ");
        startVertex =  new Vertex( s.next());
        for(String v : vertices) {
            if (v.equals(startVertex.getValue())) return;
        }
        /*
        Jeżli zostało wybrane nieistniejące miasto, powtarzaj wybór do skutku
         */
        System.out.println("Wybrane miasto nie istanieje !!");
        inputData();
    }

    public ArrayList<Hamilton> getHamiltonWay() {
        return hamiltonWay;
    }

    /*
        Pobranie krawędzi z pliku i zapisanie ich w tablicy
     */
    public static Edge[] CreateEdges(ArrayList<String[]> array, String[] vertices){
        /*
       utworzenie tablicy przechowującej wszytskie krawędzie.
       z n miast wychodzi (n-1) krawędzi
       ilośc krawędzi = (n-1)^2
       array przechowuje macierz pobraną z pliku
         */
        Edge[] edges = new Edge[(array.size()-1)*(array.size()-1)];
        int pos = 0;
        for(int i = 1 ; i < array.size() ;i++)
            for(int j = 1; j < array.size();j++) {
                /*
                Tworzenie nowych krawędzi : (i-1)-ty wierzchołek, (j-1) wierzchołek, waga drogi
                 */
                edges[pos++] = new Edge<>(vertices[i-1],vertices[j-1],Integer.parseInt(array.get(i)[j]));
            }
        return edges;
    }
    /*
    Utworzenie tablicy przechowującej wierzchołki pobrane z pliku
     */
    public static String[] CreateVertices(ArrayList<String []> array){
        String[] vertices =  new String[array.size()-1];
        for(int i = 1 ; i < array.size() ;i++)
            vertices[i-1] = array.get(i)[0];
        return vertices;
    }
    /*
    Odczytanie danych z pliku
     */
    public static ArrayList<String[]> readData(String fileName){
        String line = "";
        ArrayList<String[]> array = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            while(true){
                line = br.readLine();
                if(line == null )
                    break;
                array.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
