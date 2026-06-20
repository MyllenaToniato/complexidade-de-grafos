package app.service;

import biblioteca.Grafo;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorEntrega {
    private final Grafo<String> grafo;

    public GerenciadorEntrega(Grafo<String> grafo) {
        this.grafo = grafo;
    }

    public String obterRelatorioRota(String origem, String destino) {
        Grafo.ResultadoDijkstra<String> resultado = grafo.dijkstra(origem);
        Float tempoTotal = resultado.distancias.get(destino);

        if (tempoTotal == null || tempoTotal == Float.MAX_VALUE) {
            return "Não foi possível encontrar uma rota para este destino";
        }

        List<String> caminho = new ArrayList<>();
        String atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            atual = resultado.predecessores.get(atual);
        }

        return "\n-> Tempo total estimado: " + tempoTotal + " minutos.\n-> Rota recomendada: " + caminho;
    }
}