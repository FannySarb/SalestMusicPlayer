package salest.com.salest;

public class ArchivosMusica {
    private String ruta;
    private String titulo;
    private String artista;
    private String album;
    private String duracion;

    public ArchivosMusica(String ruta, String titulo, String artista, String album, String duracion)
    {
        this.ruta=ruta;
        this.titulo=titulo;
        this.artista=artista;
        this.album=album;
        this.duracion=duracion;
    }

    public ArchivosMusica()
    {}

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }


    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
