package biblioteca;

public class Vertice<T> {
    private T valor;
    private final ListaEncadeadaArrayList<Aresta<T>> arestas; // Lista contendo as arestas conectadas a este vértice

    public Vertice(T valor) {
        this.valor = valor;
        this.arestas = new ListaEncadeadaArrayList<>();
    }

    public T getValor() { // devolve o objeto do tipo T presente no vértice
        return valor;
    }

    public void setValor(T valor) { // muda o conteudo y do vértice atual para um conteúdo x
        this.valor = valor;
    }

    public ListaEncadeadaArrayList<Aresta<T>> getArestas() { // descobre as arestas conectadas a este vértice
        return arestas;
    }

}
