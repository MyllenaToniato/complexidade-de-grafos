package biblioteca;
import java.util.*;

public class Grafo<T> {
    private final ListaEncadeadaArrayList<Vertice<T>> vertices;
    public Grafo() {
        this.vertices = new ListaEncadeadaArrayList<>();
    }
    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> existente = obterVertice(valor);
        if (existente == null) {
            Vertice<T> novo = new Vertice<>(valor);
            this.vertices.adicionar(novo);
            return novo;
        }
        return existente;
    }
    public Vertice<T> obterVertice(T valor) {
        for (int i = 0; i < this.vertices.quantidadeNos(); i++) {
            Vertice<T> v = this.vertices.get(i);
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }
    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem = adicionarVertice(origem);
        Vertice<T> verticeDestino = adicionarVertice(destino);
        Aresta<T> novaAresta = new Aresta<>(verticeDestino, peso);
        verticeOrigem.adicionarAresta(novaAresta);
    }
    public ListaEncadeadaArrayList<Vertice<T>> getVertices() {
        return vertices;
    }
    public int quantidadeVertices() {
        return vertices.quantidadeNos();
    }

    public List<T> buscaEmLargura(T origemValor) {
        List<T> visitados = new ArrayList<>();
        Queue<Vertice<T>> fila = new LinkedList<>();
        Set<Vertice<T>> marcados = new HashSet<>();

        Vertice<T> inicio = obterVertice(origemValor);
        if (inicio == null) return visitados;

        fila.add(inicio);
        marcados.add(inicio);

        while (!fila.isEmpty()) {
            Vertice<T> atual = fila.poll();
            visitados.add(atual.getValor());

            ListaEncadeadaArrayList<Aresta<T>> arestas = atual.getArestas();
            for (int i = 0; i < arestas.quantidadeNos(); i++) {
                Aresta<T> aresta = arestas.get(i);
                Vertice<T> vizinho = aresta.getDestino();

                if (!marcados.contains(vizinho)) {
                    marcados.add(vizinho);
                    fila.add(vizinho);
                }
            }
        }
        return visitados;
    }

    public static class ResultadoDijkstra<T> {
        public final Map<T, Float> distancias;
        public final Map<T, T> predecessores;

        public ResultadoDijkstra(Map<T, Float> distancias, Map<T, T> predecessores) {
            this.distancias = distancias;
            this.predecessores = predecessores;
        }
    }

    public ResultadoDijkstra<T> dijkstra(T origemValor) {
        Map<T, Float> distancias = new HashMap<>();
        Map<T, T> predecessores = new HashMap<>();

        for (int i = 0; i < this.vertices.quantidadeNos(); i++) {
            Vertice<T> v = this.vertices.get(i);
            distancias.put(v.getValor(), Float.MAX_VALUE);
            predecessores.put(v.getValor(), null);
        }

        Vertice<T> inicio = obterVertice(origemValor);
        if (inicio == null) return new ResultadoDijkstra<>(distancias, predecessores);

        distancias.put(origemValor, 0f);

        PriorityQueue<Object[]> pq = new PriorityQueue<>(
                Comparator.comparingDouble(e -> (Float) e[0])
        );
        pq.offer(new Object[]{0f, inicio});

        Set<T> rotulados = new HashSet<>();

        while (!pq.isEmpty()) {
            Object[] entry = pq.poll();
            float distAtual = (Float) entry[0];

            @SuppressWarnings("unchecked")
            Vertice<T> vAtual = (Vertice<T>) entry[1];
            T valorAtual = vAtual.getValor();

            if (rotulados.contains(valorAtual)) continue;
            rotulados.add(valorAtual);


            ListaEncadeadaArrayList<Aresta<T>> arestas = vAtual.getArestas();
            for (int i = 0; i < arestas.quantidadeNos(); i++) {
                Aresta<T> aresta = arestas.get(i);

                Vertice<T> vizinho = aresta.getDestino();
                T vizinhoValor = vizinho.getValor();

                if (rotulados.contains(vizinhoValor)) continue;

                float novaDistancia = distAtual + aresta.getTempo();
                if (novaDistancia < distancias.get(vizinhoValor)) {
                    distancias.put(vizinhoValor, novaDistancia);
                    predecessores.put(vizinhoValor, valorAtual);

                    pq.offer(new Object[]{novaDistancia, vizinho});
                }
            }
        }
        return new ResultadoDijkstra<>(distancias, predecessores);
    }
}