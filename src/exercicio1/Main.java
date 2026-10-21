package exercicio1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pilha<Pedido> cancelados = new Pilha<>();
        Fila<Pedido> pendentes = new Fila<>();
        Scanner scanner = new Scanner(System.in);
        int proximoId = 1;
        int opcao = 0;

        while (opcao != 7) {
            System.out.println();
            System.out.println("----------Menu cafeteria----------");
            System.out.println("1. Adicionar novo pedido");
            System.out.println("2. Atender pedido");
            System.out.println("3. Cancelar pedido");
            System.out.println("4. Restaurar pedido");
            System.out.println("5. Imprimir pedidos pendentes");
            System.out.println("6. Imprimir pedidos cancelados");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    System.out.print("Descrição do pedido: ");
                    String descricao = scanner.nextLine();
                    if (descricao.isBlank()) {
                        System.out.println("Descrição inválida. Pedido não adicionado.");
                        break;
                    }
                    Pedido novo = new Pedido(descricao, String.valueOf(proximoId));
                    pendentes.enqueue(novo);
                    proximoId++;
                    System.out.println("Pedido adicionado à fila: " + novo);
                    break;
                case 2:
                    Pedido atendido = pendentes.dequeue();
                    if (atendido == null) {
                        System.out.println("Não há pedidos pendentes para atender.");
                    } else {
                        System.out.println("Pedido atendido: " + atendido);
                    }
                    break;
                case 3:
                    Pedido cancelado = pendentes.dequeue();
                    if (cancelado == null) {
                        System.out.println("Não há pedidos pendentes para cancelar.");
                    } else {
                        cancelados.push(cancelado);
                        System.out.println("Pedido cancelado: " + cancelado);
                    }
                    break;
                case 4:
                    Pedido restaurado = cancelados.pop();
                    if (restaurado == null) {
                        System.out.println("Não há pedidos cancelados para restaurar.");
                    } else {
                        pendentes.enqueue(restaurado);
                        System.out.println("Pedido restaurado: " + restaurado);
                    }
                    break;
                case 5:
                    pendentes.printQueue();
                    break;
                case 6:
                    cancelados.printStack();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
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
