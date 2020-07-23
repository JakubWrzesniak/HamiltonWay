package Graph;

import java.util.ArrayList;

public interface IGraph<T extends Comparable<T>> {
    void addEdge(Edge<T> edge);
    void increasingArray(int size);
    void addVertex(Vertex<T> vertex);
    void addVertex(T begin, T end, double weight);
    Vertex<T>[] getVertexList();
}
