package Graph;

import java.util.ArrayList;

public class AdjacencyMatrix<T extends Comparable<T>> implements IGraph<T> {
    private Double[][] adjacencyMatrix;
    private Vertex<T>[] vertices;
    private final boolean digraph;
    private final boolean directed;

    public AdjacencyMatrix(int numOfVertices, boolean digraph, boolean directed){
        adjacencyMatrix = new Double[numOfVertices][numOfVertices];
        vertices = new Vertex[numOfVertices];
        if(digraph) {
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    if (i != j) adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
                    else adjacencyMatrix[i][j] = 0.0;
                }
            }
        }
        this.digraph = digraph;
        this.directed = directed;
    }

    public AdjacencyMatrix(T[] vertices, Edge<T>[] edges, boolean digraph, boolean directed){
        this(vertices.length,digraph,directed);
        for(int i = 0 ; i < vertices.length ;i++)
            this.vertices[i] = new Vertex<T>(vertices[i]);
        int pos = 0;
        while (pos<vertices.length) this.vertices[pos].setPos(pos++);
        for(int i = 0 ; i < edges.length;i++){
            addEdge(edges[i]);
        }
    }

    public double getWeight(int i , int j){
        return adjacencyMatrix[i][j];
    }


    @Override
    public void addVertex(T begin, T end, double weight) throws IndexOutOfBoundsException{
        addVertex(new Vertex<T>(begin));
        addVertex(new Vertex<T>(end));
        int posB=-1;
        int posE=-1;
        for(int i = 0 ; i < vertices.length ;i++) {
            if (vertices[i].getValue().equals(begin)) posB = i;
            if (vertices[i].getValue().equals(end)) posE = i;
        }
        if(digraph) adjacencyMatrix[posB][posE] = weight;
        else adjacencyMatrix[posB][posE] = 1.0;
    }

    @Override
    public Vertex<T>[] getVertexList() {
        return vertices;
    }

    @Override
    public void increasingArray(int size) {
        if(adjacencyMatrix.length<size) {
            Double[][] newArray = new Double[size][size];
            Vertex[] newVertexArray = new Vertex[size];
            for (int i = 0; i < adjacencyMatrix.length; i++){
                for(int j = 0 ; j < adjacencyMatrix[i].length;j++)
                    newArray[i][j] = adjacencyMatrix[i][j];
            }
            for (int i = 0 ; i < vertices.length;i++) newVertexArray[i] = vertices[i];
            adjacencyMatrix = newArray;
            vertices = newVertexArray;
        }
    }

    public boolean containVertex(T value) {
        for(int i = 0 ; i < vertices.length ;i++) if(vertices[i].getValue().equals(value)) return true;
        return false;
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertex.setPos(vertices.length-1);
        if(!containVertex(vertex.getValue())){
            increasingArray(adjacencyMatrix.length+1);
            vertices[vertices.length-1] = vertex;
        }

    }
    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.getBegin(), edge.getEnd(), edge.getWeight());
        if(!isDirected()) addVertex(edge.getEnd(),edge.getBegin(),edge.getWeight());
    }

    public boolean isDigraph() {
        return digraph;
    }

    public boolean isDirected() {
        return directed;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("    ");
        for(int i = 0 ; i < adjacencyMatrix.length;i++)
            s.append(vertices[i].getValue()).append("     ");
        s.append("\n");
        for(int i = 0 ; i < adjacencyMatrix.length ;i++){
            s.append(vertices[i].getValue()).append("| ");
            for(int j = 0 ; j < adjacencyMatrix[i].length; j++){
                s.append(" ");
                if(adjacencyMatrix[j][i] == null)
                    s.append("0.0");
                else if(adjacencyMatrix[j][i]==Double.POSITIVE_INFINITY)
                    s.append("INF");
                else
                    s.append(adjacencyMatrix[j][i]);
                s.append("  ");
            }
            s.append("\n");
        }
        return String.valueOf(s);
    }
}
