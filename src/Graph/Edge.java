package Graph;

public class Edge<T> {
    private T begin;
    private T end;
    private double weight;

    public Edge(T begin, T end, double weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }


    public T getBegin() {
        return begin;
    }

    public T getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.valueOf("(" + begin + "' " + end + " : " + weight + ")");
    }
}
