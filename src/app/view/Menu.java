package app.view;

import app.data.LeitorArquivo;
import biblioteca.Grafo;
import java.io.IOException;
import java.util.Scanner;
import app.service.GerenciadorEntrega;


public class Menu {

    private final Grafo<String> grafo;
    private final LeitorArquivo leitor;
    private final Scanner scanner;
    private final GerenciadorEntrega gerenciador;

    public Menu() {
        this.grafo = new Grafo<>();
        this.leitor = new LeitorArquivo();
        this.scanner = new Scanner(System.in);
        this.gerenciador = new GerenciadorEntrega(this.grafo);
    }

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE ENTREGAS RÁPIDAS ===");
            System.out.println("1. Carregar mapa inicial (Arquivo)");
            System.out.println("2. Adicionar nova rua/cruzamento");
            System.out.println("3. Calcular rota de entrega mais rápida");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        carregarMapa();
                        break;

                    case 2:
                        adicionarNovaRua();
                        break;

                    case 3:
                        calcularRotaRapida();
                        break;

                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private void carregarMapa() {

        System.out.print("Digite o caminho do arquivo (ex: cidade.txt): ");
        String caminho = scanner.nextLine();

        try {
            leitor.carregarMapa(caminho, this.grafo);
            System.out.println("Mapa carregado com sucesso!");
            System.out.println("Total de locais (vértices) cadastrados: " + grafo.quantidadeVertices());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private void adicionarNovaRua() {

        System.out.print("Digite o local de origem: ");
        String origem = scanner.nextLine();

        System.out.print("Digite o local de destino: ");
        String destino = scanner.nextLine();

        System.out.print("Digite o tempo do percurso (em minutos): ");
        try {
            float tempo = Float.parseFloat(scanner.nextLine());

            grafo.adicionarAresta(origem, destino, tempo);
            System.out.println("Rua adicionada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: O tempo digitado deve ser um número válido.");
        }
    }


    private void calcularRotaRapida() {
        System.out.print("Digite o local de partida: ");
        String origem = scanner.nextLine();

        System.out.print("Digite o destino final: ");
        String destino = scanner.nextLine();

        // A camada de negócio resolve e a View apenas imprime
        String relatorio = gerenciador.obterRelatorioRota(origem, destino);
        System.out.println(relatorio);
    }

}
