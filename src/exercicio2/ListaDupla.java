package exercicio2;

public class ListaDupla<T> {
    private No<T> inicio;
    private No<T> fim;
    private No<T> atual;
    private int tamanho;

    public ListaDupla() {
        this.inicio = null;
        this.fim = null;
        this.atual = null;
        this.tamanho = 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public T getAtual() {
        if (atual == null) {
            return null;
        }
        return atual.dado;
    }

    public void adicionarNoFim(T dado) {
        No<T> novoNo = new No<>(dado);
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
            atual = novoNo;
        } else {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        }
        tamanho++;
    }

    public void adicionarNoInicio(T dado) {
        No<T> novoNo = new No<>(dado);
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
            atual = novoNo;
        } else {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        }
        tamanho++;
    }

    public boolean adicionarEmPosicao(int posicao, T dado) {
        if (posicao < 1 || posicao > tamanho + 1) {
            return false;
        }
        if (posicao == 1) {
            adicionarNoInicio(dado);
            return true;
        }
        if (posicao == tamanho + 1) {
            adicionarNoFim(dado);
            return true;
        }
        No<T> existente = obterNo(posicao);
        No<T> novoNo = new No<>(dado);
        novoNo.proximo = existente;
        novoNo.anterior = existente.anterior;
        existente.anterior.proximo = novoNo;
        existente.anterior = novoNo;
        tamanho++;
        return true;
    }

    public void remover(T dado) {
        if (inicio == null) {
            return;
        }
        No<T> alvo = inicio;
        while (alvo != null) {
            if (alvo.dado.equals(dado)) {
                removerNo(alvo);
                return;
            }
            alvo = alvo.proximo;
        }
    }

    public boolean removerPorPosicao(int posicao) {
        No<T> alvo = obterNo(posicao);
        if (alvo == null) {
            return false;
        }
        removerNo(alvo);
        return true;
    }

    public boolean removerPorTitulo(String titulo) {
        if (inicio == null || !(inicio.dado instanceof Musica)) {
            return false;
        }
        No<T> alvo = inicio;
        while (alvo != null) {
            Musica musica = (Musica) alvo.dado;
            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                removerNo(alvo);
                return true;
            }
            alvo = alvo.proximo;
        }
        return false;
    }

    public boolean contem(T dado) {
        No<T> cursor = inicio;
        while (cursor != null) {
            if (cursor.dado.equals(dado)) {
                return true;
            }
            cursor = cursor.proximo;
        }
        return false;
    }

    public T proxima() {
        if (atual == null) {
            return null;
        }
        if (atual.proximo != null) {
            atual = atual.proximo;
        } else {
            System.out.println("Já está na última música.");
        }
        return atual.dado;
    }

    public T anterior() {
        if (atual == null) {
            return null;
        }
        if (atual.anterior != null) {
            atual = atual.anterior;
        } else {
            System.out.println("Já está na primeira música.");
        }
        return atual.dado;
    }

    public void ordenarPorTitulo() {
        ordenarMusicas(true);
    }

    public void ordenarPorArtista() {
        ordenarMusicas(false);
    }

    public void imprimirLista() {
        if (estaVazia()) {
            System.out.println("Playlist vazia.");
            return;
        }
        No<T> cursor = inicio;
        int posicao = 1;
        while (cursor != null) {
            String marca = cursor == atual ? "  tocando" : "";
            System.out.println(posicao + ". " + cursor.dado + marca);
            cursor = cursor.proximo;
            posicao++;
        }
    }

    private void ordenarMusicas(boolean porTitulo) {
        if (tamanho < 2 || !(inicio.dado instanceof Musica)) {
            return;
        }
        Musica musicaAtual = atual == null ? null : (Musica) atual.dado;
        boolean trocou;
        do {
            trocou = false;
            No<T> cursor = inicio;
            while (cursor != null && cursor.proximo != null) {
                Musica a = (Musica) cursor.dado;
                Musica b = (Musica) cursor.proximo.dado;
                String esquerda = porTitulo ? a.getTitulo() : a.getArtista();
                String direita = porTitulo ? b.getTitulo() : b.getArtista();
                if (esquerda.compareToIgnoreCase(direita) > 0) {
                    T aux = cursor.dado;
                    cursor.dado = cursor.proximo.dado;
                    cursor.proximo.dado = aux;
                    trocou = true;
                }
                cursor = cursor.proximo;
            }
        } while (trocou);

        if (musicaAtual != null) {
            No<T> cursor = inicio;
            while (cursor != null) {
                if (cursor.dado.equals(musicaAtual)) {
                    atual = cursor;
                    break;
                }
                cursor = cursor.proximo;
            }
        }
    }

    private No<T> obterNo(int posicao) {
        if (posicao < 1 || posicao > tamanho) {
            return null;
        }
        No<T> cursor = inicio;
        for (int i = 1; i < posicao; i++) {
            cursor = cursor.proximo;
        }
        return cursor;
    }

    private void removerNo(No<T> alvo) {
        if (alvo == inicio) {
            inicio = alvo.proximo;
            if (inicio != null) {
                inicio.anterior = null;
            } else {
                fim = null;
            }
        } else if (alvo == fim) {
            fim = alvo.anterior;
            if (fim != null) {
                fim.proximo = null;
            }
        } else {
            alvo.anterior.proximo = alvo.proximo;
            alvo.proximo.anterior = alvo.anterior;
        }

        if (atual == alvo) {
            if (alvo.proximo != null) {
                atual = alvo.proximo;
            } else if (alvo.anterior != null) {
                atual = alvo.anterior;
            } else {
                atual = null;
            }
        }
        tamanho--;
    }
}
