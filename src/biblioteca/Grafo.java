package biblioteca;

public class Grafo<T> {
    private final ListaEncadeadaArrayList<Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new ListaEncadeadaArrayList<>();
    }

    // Adiciona um novo vértice ao grafo se ele já não existir (movido de Aresta.java)
    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> existente = obterVertice(valor);
        if (existente == null) {
            Vertice<T> novo = new Vertice<>(valor);
            this.vertices.adicionar(novo);
            return novo;
        }
        return existente;
    }

    // Procura por um vértice na lista global através do seu valor único (movido de Aresta.java)
    public Vertice<T> obterVertice(T valor) {
        for (int i = 0; i < this.vertices.quantidadeNos(); i++) {
            Vertice<T> v = this.vertices.get(i);
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }

    // Interliga dois vértices criando uma aresta ponderada direcionada 
    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem = adicionarVertice(origem);
        Vertice<T> verticeDestino = adicionarVertice(destino);

        Aresta<T> novaAresta = new Aresta<>(verticeDestino, peso);
        verticeOrigem.adicionarAresta(novaAresta);
    }

    public ListaEncadeadaArrayList<Vertice<T>> getVertices() {
        return vertices;
    }
}
