package exercicio1;

public class Fila<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public Fila() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public boolean enqueue(T e) {
        return enfileirar(e);
    }

    public boolean enfileirar(T e) {
        No<T> novo = new No<>(e);
        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
        tamanho++;
        return true;
    }

    public T dequeue() {
        return desenfileirar();
    }

    public T desenfileirar() {
        if (estaVazia()) {
            return null;
        }
        T dado = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return dado;
    }

    public T espiar() {
        if (estaVazia()) {
            return null;
        }
        return inicio.dado;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int tamanho() {
        return this.tamanho;
    }

    public void printQueue() {
        if (estaVazia()) {
            System.out.println("Fila de pedidos pendentes vazia.");
            return;
        }
        System.out.println("Pedidos pendentes:");
        No<T> atual = inicio;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.dado);
            atual = atual.proximo;
            posicao++;
        }
    }

    @Override
    public String toString() {
        String s = "[";
        No<T> atual = inicio;
        while (atual != null) {
            s += atual.dado;
            if (atual.proximo != null) {
                s += ", ";
            }
            atual = atual.proximo;
        }
        s += "]";
        return s;
    }
}
