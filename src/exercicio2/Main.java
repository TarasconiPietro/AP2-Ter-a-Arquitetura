package exercicio2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaDupla<Musica> playlist = new ListaDupla<>();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 8) {
            System.out.println();
            System.out.println("----------Menu----------");
            System.out.println("1. Tocar Música");
            System.out.println("2. Adicionar");
            System.out.println("3. Remover");
            System.out.println("4. Próxima");
            System.out.println("5. Anterior");
            System.out.println("6. Ordenar");
            System.out.println("7. Listar");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    tocarMusica(playlist);
                    break;
                case 2:
                    adicionarMusica(scanner, playlist);
                    break;
                case 3:
                    removerMusica(scanner, playlist);
                    break;
                case 4:
                    avancar(playlist);
                    break;
                case 5:
                    voltar(playlist);
                    break;
                case 6:
                    ordenar(scanner, playlist);
                    break;
                case 7:
                    playlist.imprimirLista();
                    break;
                case 8:
                    System.out.println("Encerrando o player...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void tocarMusica(ListaDupla<Musica> playlist) {
        Musica atual = playlist.getAtual();
        if (atual == null) {
            System.out.println("Nenhuma música na playlist.");
            return;
        }
        System.out.println(">>> Reproduzindo agora:");
        System.out.println("Título : " + atual.getTitulo());
        System.out.println("Artista: " + atual.getArtista());
        System.out.println("Álbum  : " + atual.getAlbum());
        System.out.println("Duração: " + atual.formatarDuracao());
    }

    private static void adicionarMusica(Scanner scanner, ListaDupla<Musica> playlist) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        System.out.print("Álbum: ");
        String album = scanner.nextLine();
        System.out.print("Duração em segundos: ");
        String duracao = scanner.nextLine();

        if (titulo.isBlank() || artista.isBlank()) {
            System.out.println("Título e artista são obrigatórios.");
            return;
        }

        Musica nova = new Musica(titulo, album, artista, duracao);
        System.out.println("Onde inserir? 1-Início  2-Fim  3-Posição específica");
        int local = lerInteiro(scanner);
        if (local == 1) {
            playlist.adicionarNoInicio(nova);
            System.out.println("Música adicionada no início.");
        } else if (local == 3) {
            System.out.print("Informe a posição (1 até " + (playlist.tamanho() + 1) + "): ");
            int posicao = lerInteiro(scanner);
            if (playlist.adicionarEmPosicao(posicao, nova)) {
                System.out.println("Música adicionada na posição " + posicao + ".");
            } else {
                System.out.println("Posição inválida. Música não adicionada.");
            }
        } else {
            playlist.adicionarNoFim(nova);
            System.out.println("Música adicionada no fim.");
        }
    }

    private static void removerMusica(Scanner scanner, ListaDupla<Musica> playlist) {
        if (playlist.estaVazia()) {
            System.out.println("Playlist vazia. Nada para remover.");
            return;
        }
        System.out.println("Remover por: 1-Posição  2-Título");
        int tipo = lerInteiro(scanner);
        if (tipo == 1) {
            System.out.print("Posição: ");
            int posicao = lerInteiro(scanner);
            if (playlist.removerPorPosicao(posicao)) {
                System.out.println("Música removida da posição " + posicao + ".");
            } else {
                System.out.println("Posição inválida.");
            }
        } else {
            System.out.print("Título da música: ");
            String titulo = scanner.nextLine();
            if (playlist.removerPorTitulo(titulo)) {
                System.out.println("Música \"" + titulo + "\" removida.");
            } else {
                System.out.println("Música não encontrada.");
            }
        }
    }

    private static void avancar(ListaDupla<Musica> playlist) {
        if (playlist.estaVazia()) {
            System.out.println("Playlist vazia.");
            return;
        }
        Musica musica = playlist.proxima();
        System.out.println("Música atual: " + musica);
    }

    private static void voltar(ListaDupla<Musica> playlist) {
        if (playlist.estaVazia()) {
            System.out.println("Playlist vazia.");
            return;
        }
        Musica musica = playlist.anterior();
        System.out.println("Música atual: " + musica);
    }

    private static void ordenar(Scanner scanner, ListaDupla<Musica> playlist) {
        System.out.println("Ordenar por: 1-Título  2-Artista");
        int criterio = lerInteiro(scanner);
        if (criterio == 2) {
            playlist.ordenarPorArtista();
            System.out.println("Playlist ordenada por artista.");
        } else {
            playlist.ordenarPorTitulo();
            System.out.println("Playlist ordenada por título.");
        }
        playlist.imprimirLista();
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número: ");
            }
        }
    }
}
