package Task1;

import Graph.Edge;
import Graph.Hamilton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Task1 {
    public static void main(String[] args) throws IOException {
        Simulation simulation = new Simulation("z5data2.csv");
        simulation.menu();
        showResults(simulation.getHamiltonWay());
    }

    /*
    Wyświetlenie wszystkich dróg Hamiltona przchowywanych w tablicy
     */
    public static void showResults(ArrayList<Hamilton> hamiltons){
        Iterator<Hamilton> iterator = hamiltons.iterator();
        while (iterator.hasNext()){
            Hamilton hamilton = iterator.next();
            System.out.println("Miasto poczatkowe: " + hamilton.getStartVertex());
            System.out.print("Trasa : ");
            for(Edge edge : hamilton.getPath())
                System.out.print(edge.getBegin()+" ");
            System.out.print("\nDlugosci kazdej z drog: ");
            for(Edge edge : hamilton.getPath())
                System.out.print(edge.getWeight()+" ");
            System.out.println("\nLaczny przebyty dystans: "+hamilton.getWay());
            System.out.print("\n");
        }
    }
}
