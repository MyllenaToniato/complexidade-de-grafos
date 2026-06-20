package biblioteca;

public class Aresta<T> {
    private final Vertice<T> destino;
    private final float tempo;

    public Aresta(Vertice<T> destino, float tempo) {
        this.destino = destino;
        this.tempo = tempo;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    public float getTempo() {
        return tempo;
    }
}