package models;

public class Libro {
    private String codigo;
    private String isbn;
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String codigo, String isbn, String titulo, String autor) {
        this.codigo = codigo;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true; //Se deja como true ya que al ser nuevo esta disponible por default
    }
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado";
        return "Libro [" + codigo + "] - " + titulo + " por " + autor + " (" + estado + ")";
    }
}