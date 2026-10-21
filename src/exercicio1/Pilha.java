package exercicio1;

public class Pilha<T> {
    private No<T> topo;
    private int tamanho;

    public Pilha() {
        this.topo = null;
        this.tamanho = 0;
    }

    public boolean push(T e) {
        return empilhar(e);
    }

    public boolean empilhar(T e) {
        No<T> novo = new No<>(e);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
        return true;
    }

    public T pop() {
        return desempilhar();
    }

    public T desempilhar() {
        if (estaVazia()) {
            return null;
        }
        T dado = topo.dado;
        topo = topo.proximo;
        tamanho--;
        return dado;
    }

    public T espiar() {
        if (estaVazia()) {
            return null;
        }
        return topo.dado;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int tamanho() {
        return this.tamanho;
    }

    public void printStack() {
        if (estaVazia()) {
            System.out.println("Pilha de pedidos cancelados vazia.");
            return;
        }
        System.out.println("Pedidos cancelados (do mais recente para o mais antigo):");
        No<T> atual = topo;
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
        No<T> atual = topo;
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
