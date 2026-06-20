package app.data;

import biblioteca.Grafo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {

    public void carregarMapa(String caminho, Grafo<String> grafo) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) { // Permite uma leitura mais rápida do texto por conta do buffer de memória, evitando idas desnecessárias ao disco
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) // trim = remove espaços em branco.
                    continue;

                String[] partes = linha.split(";");

                if (partes.length == 3) {
                    String origem = partes[0].trim();
                    String destino = partes[1].trim();
                    float tempo = Float.parseFloat(partes[2].trim());

                    grafo.adicionarAresta(origem, destino, tempo);
                }
            }
        }
    }
}