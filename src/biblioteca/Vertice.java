package biblioteca;

public class Vertice<T> {
    private T valor;
    private final ListaEncadeadaArrayList<Aresta<T>> arestas; // Lista contendo as arestas conectadas a este vértice
    private final ListaEncadeadaArrayList<Vertice<T>> vertices;

    public Vertice(T valor) {
        this.valor = valor;
        this.arestas = new ListaEncadeadaArrayList<>();
        this.vertices = new ListaEncadeadaArrayList<>();
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) { // muda o conteudo y do vértice atual para um conteúdo x
        this.valor = valor;
    }

    public ListaEncadeadaArrayList<Aresta<T>> getArestas() { return arestas;
    }

    public Vertice<T> adicionarVertice (T valor) {
        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.adicionar(novo);
        return novo;
    }

    private Vertice<T> ObterVertice(T valor){
        Vertice<T> v;
        for (int i=0; i<this.vertices.quantidadeNos();i++) {
            v= this.vertices.get(i);
            if(v.getValor().equals(valor)) { return v;}
        }
        return null;
    }
}
