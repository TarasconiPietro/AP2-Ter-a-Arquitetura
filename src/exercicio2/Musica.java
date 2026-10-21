package exercicio2;

public class Musica {
    String titulo;
    String album;
    String artista;
    String duracao;

    public Musica(String titulo, String album, String artista, String duracao) {
        this.titulo = titulo;
        this.album = album;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtista() {
        return artista;
    }

    public String getDuracao() {
        return duracao;
    }

    public String formatarDuracao() {
        try {
            int total = Integer.parseInt(duracao.trim());
            int minutos = total / 60;
            int segundos = total % 60;
            return minutos + "min " + String.format("%02d", segundos) + "s (" + total + "s)";
        } catch (NumberFormatException e) {
            return duracao + "s";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Musica outra)) {
            return false;
        }
        return titulo.equalsIgnoreCase(outra.titulo)
                && artista.equalsIgnoreCase(outra.artista)
                && album.equalsIgnoreCase(outra.album);
    }

    @Override
    public String toString() {
        return titulo + " | " + artista + " | " + album + " | " + formatarDuracao();
    }
}
