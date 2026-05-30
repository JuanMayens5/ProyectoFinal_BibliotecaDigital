package models;

public class Prestamo {
    private String idPrestamo;
    private Libro libro;
    private Usuario usuario;
    private String fechaPrestamo;

    public Prestamo(String idPrestamo, Libro libro, Usuario usuario, String fechaPrestamo) {
        this.idPrestamo = idPrestamo;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getIdPrestamo() { return idPrestamo; }
    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }
    public String getFechaPrestamo() { return fechaPrestamo; }

    @Override
    public String toString() {
        return "Prestamo [" + idPrestamo + "] - Libro: " + libro.getTitulo() +
                " | Usuario: " + usuario.getNombre() + " | Fecha: " + fechaPrestamo;
    }
}