package Graph;


public class Hamilton<T extends Comparable<T>> {

    private AdjacencyMatrix<T> graph;                              //graf
    private Vertex startVertex;                                     //Wierzchołek startoway
    private double way;                                             //Dlugosc drogi
    private Edge[] path;

    public Hamilton(Vertex<T> startVertex, AdjacencyMatrix<T> graph){
        this.graph = graph;
        this.startVertex = startVertex;
        way = 0;
    }

    public <T extends Comparable<T>> void hamiltonWay(){

        Vertex<T>[] vertices = (Vertex<T>[]) graph.getVertexList(); //Tablica przechowująca wieszchołki
        path = new Edge[vertices.length];                    //Tablica przechowująca kolejne krawędzie
        int pathPos=0;                                              //Pozycja w tablicy krawędzi


        /* Ustawienie wartości white - > true - wierzchołek nie został jeszcze odwiedzony
           Pobranie z tablicy wierzchołka początkowego.
         */
        for (Vertex<T> tVertex : vertices) {
            tVertex.setWhite(true);
            if (startVertex.compareTo(tVertex) == 0)
                startVertex= tVertex;
        }

        Vertex<T> actVertex = startVertex;                           //wierzchołek w któym się znajdujemy
        while (true) {
            actVertex.setWhite(false);                              //Ustawienie wartości false - wierzchołek został odwiedzony
            double min = Double.POSITIVE_INFINITY;                  //wartość minimalnej krawędzi wychodzącej od wierzchołka
            int pos=0;                                              //Pozyja w tablicy wierzchołka do któego jest krawędź o najmniejszej wadze

            /*
            Znalezienie najmniejszej wagi drogi
            Zapamiętanie pozycji wierzhołka do któego ona prowadzi
             */
            for(int i = 0 ; i < vertices.length; i++){

                //Warunek: waga drogi musi być mniejsza niż wcześniejsza najmniesza i wierzchołek nie był jeszcze odwiedzany
                if(graph.getWeight(actVertex.getPos(),i)<min && vertices[i].isWhite()) {
                    min = graph.getWeight(actVertex.getPos(), i);
                    pos = i;
                }

            }
            /*Sprawdzenie czy został znaleziony jakiś nieodwiedzony wierzchołek
                W przeciwnym wypadku, gdy zostały odwiedzone wszystkie wierzchołki
                dodaj krawędź łączocą ostatni wierzchołek z początkowym
             */
            if(min != Double.POSITIVE_INFINITY) {
                way+=min;                                                                           //dodanie wagi krawędzi do długośici drogi
                path[pathPos++] = new Edge<>(actVertex.getValue(), vertices[pos].getValue(), min);  //Dodanie krawędzi do tablicy
                vertices[pos].setWhite(false);                                                      //Ustawienie wartości false - wierzchołek został odwiedzony
                actVertex = vertices[pos];                                                          //ustaweinie wierzchołka, w którym się znajdujemy
            }else{
                way+=graph.getWeight(actVertex.getPos(),startVertex.getPos());
                path[pathPos] = new Edge<>(actVertex.getValue(),startVertex.getValue(),graph.getWeight(actVertex.getPos(),startVertex.getPos()));
                return ;                                                                            //Przerwij wykonywanie pętli
            }
        }
    }

    public double getWay() {
        return way;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Edge[] getPath() {
        return path;
    }
}
