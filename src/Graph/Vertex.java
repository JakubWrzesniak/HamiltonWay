package Graph;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>>{
    private T value;
    private int pos;
    private boolean white = true; // Oznaczenie czy wierzchołek został już odwiedzony (true - nie; false - tak)

    public Vertex(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public void setPos(int pos){
        this.pos=pos;
    }

    public int getPos() {
        return pos;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return value.compareTo(o.value);
    }

}