package biblioteca;

public class Aresta<T> {
    private final Vertice<T> vertice1;
    private final Vertice<T> vertice2;
    private final int tempo;

    public Aresta(Vertice<T> vertice1, Vertice<T> vertice2, int tempo) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.tempo = tempo;
    }

    public Vertice<T> getVertice1() {
        return vertice1;
    }

    public Vertice<T> getVertice2() {
        return vertice2;
    }

    public int getTempo() {
        return tempo;
    }
}
