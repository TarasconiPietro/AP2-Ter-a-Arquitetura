package exercicio1;

public class Pedido {
    String id;
    String descrição;

    public Pedido(String descrição, String id) {
        this.descrição = descrição;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Descrição: " + descrição;
    }
}
