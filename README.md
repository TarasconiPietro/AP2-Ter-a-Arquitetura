AP2: Arquitetura e Estruturas de Dados

3.1. Identificação
Pietro Tarasconi
Turma de terça
---

## 3.2 Introdução

Implementar estruturas de dados manualmente é fundamental para compreender o que ocorre por trás das classes prontas da linguagem. Ao construir pilhas, filas e listas ligadas com nós e referências, o programador passa a controlar explicitamente o encadeamento dos elementos, o gerenciamento da memória e os ponteiros de início, fim e topo. Essa prática evidencia operações críticas — inserção, remoção e atualização de ligações — e evita a dependência de bibliotecas como `java.util.Stack`, `java.util.Queue` e `java.util.LinkedList`, cujo uso foi proibido neste trabalho. O resultado é um código que demonstra o comportamento clássico das estruturas e a habilidade de aplicá-las em problemas reais: uma fila de pedidos de cafeteria e um player de músicas baseado em lista duplamente encadeada.

---

## 3.3 Implementação

O projeto está organizado em dois pacotes:

- `src/exercicio1` — cafeteria (pilha e fila com lista simplesmente ligada)
- `src/exercicio2` — player de músicas (lista duplamente ligada)

### Estrutura do nó

No exercício 1, cada nó possui o dado (`Pedido`) e um único ponteiro `proximo`, formando uma lista simplesmente ligada:

```java
class No<T> {
    T dado;
    No<T> proximo;
}
```

A **fila** usa dois ponteiros (`inicio` e `fim`): `enqueue` liga o novo nó ao fim; `dequeue` avança o início. A **pilha** usa apenas `topo`: `push` insere na frente; `pop` remove o primeiro nó.

No exercício 2, o nó tem dois ponteiros, permitindo navegação nos dois sentidos:

```java
class No<T> {
    T dado;
    No<T> anterior;
    No<T> proximo;
}
```

A lista guarda `inicio`, `fim`, `atual` (música em reprodução) e `tamanho`. Cada música armazena título, artista, álbum e duração em segundos.

### Casos críticos

**Remoção em estrutura vazia.** `dequeue`, `pop` e as remoções da lista verificam se o início/topo é nulo e devolvem `null` ou interrompem a operação, exibindo mensagem ao usuário. Não há tentativa de acessar `proximo` ou `anterior` em referência nula.

**Atualização de ponteiros (início/fim).**

- Fila com um único elemento: após `dequeue`, `inicio` e `fim` voltam a `null`.
- Fila vazia no `enqueue`: o novo nó passa a ser início e fim.
- Lista dupla: inserção no início religa `inicio.anterior`; inserção no fim religa `fim.proximo`; remoção no meio conecta o vizinho anterior ao próximo. Se a música removida era a atual, o ponteiro `atual` migra para a próxima (ou para a anterior, se não houver próxima).

**Cancelar e restaurar (exercício 1).** Cancelar faz `dequeue` na fila e `push` na pilha. Restaurar faz `pop` na pilha e `enqueue` na fila, devolvendo o pedido ao final dos pendentes.

**Ordenação (exercício 2).** Bubble sort troca apenas os dados entre nós vizinhos, comparando título ou artista. Depois da ordenação, o ponteiro `atual` é reposicionado na mesma música.

Não foram utilizadas classes prontas de estrutura (`Stack`, `Queue`, `LinkedList`, `ArrayList`). Apenas `Scanner` para leitura do teclado.

Como executar no IntelliJ

1. Abra o projeto `AP2`.
2. Execute `exercicio1.Main` para a cafeteria.
3. Execute `exercicio2.Main` para o player.

---

3.4 Evidências de Execução


exercicio1:

adicionar e atender pedido:
![img_2.png](img_2.png)

Cancelar o pedido:
![img.png](img.png)

Restaurar pedido:
![img_1.png](img_1.png)


exercicio2:

Reproduzir musica:
![img_3.png](img_3.png)

Listar:
![img_4.png](img_4.png)

Proxima musica:
![img_5.png](img_5.png)


---

4. Conclusão

A principal dificuldade foi garantir a atualização correta dos ponteiros em todos os extremos da lista (lista vazia, um único nó, inserção/remoção no início, no fim e no meio) sem usar coleções prontas. Isso foi resolvido isolando a remoção em um método que trata cada caso e ajusta também o ponteiro da música atual. Outro ponto foi adaptar a pilha e a fila, inicialmente pensadas com vetor para lista simplesmente ligada, conforme o enunciado.

O trabalho reforçou a diferença prática entre FIFO e LIFO, o papel dos ponteiros `anterior`/`proximo` na navegação e a importância de tratar estruturas vazias antes de qualquer acesso. Também mostrou que operações de mais alto nível (cancelar pedido, restaurar, ordenar playlist) são apenas combinações das operações básicas das estruturas implementadas à mão.
# AP2-Ter-a-Arquitetura
