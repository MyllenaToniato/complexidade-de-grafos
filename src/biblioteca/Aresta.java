package biblioteca;

public class Aresta<T> {
    private final Vertice<T> origem, destino;
    private final int tempo;

    public Aresta(Vertice<T> origem, Vertice<T> destino, int tempo) {
        this.origem = origem;
        this.destino = destino;
        this.tempo = tempo;
    }

    public Vertice<T> getVertice1() {
        return origem;
    }

    public Vertice<T> getVertice2() {
        return destino;
    }

    public int getTempo() {
        return tempo;
    }
}
