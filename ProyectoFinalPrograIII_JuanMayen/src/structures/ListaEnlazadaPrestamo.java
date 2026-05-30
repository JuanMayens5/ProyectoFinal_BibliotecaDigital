package structures;
import models.Prestamo;

public class ListaEnlazadaPrestamo {
    private Nodo<Prestamo> cabeza;
    private int tamaño;

    public ListaEnlazadaPrestamo() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Agregar un nuevo préstamo al inicio de la lista
    public void registrarPrestamo(Prestamo prestamo) {
        Nodo<Prestamo> nuevoNodo = new Nodo<>(prestamo);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
        tamaño++;
    }

    // Buscar un préstamo por el código del libro
    public Prestamo buscarPorCodigoLibro(String codigoLibro) {
        Nodo<Prestamo> actual = cabeza;
        while (actual != null) {
            if (actual.getData().getLibro().getCodigo().equals(codigoLibro)) {
                return actual.getData();
            }
            actual = actual.getSiguiente();
        }
        return null; // No se encontró
    }

    // Eliminar un préstamo (cuando se devuelve el libro)
    public boolean eliminarPrestamo(String codigoLibro) {
        if (cabeza == null) return false;

        if (cabeza.getData().getLibro().getCodigo().equals(codigoLibro)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }

        Nodo<Prestamo> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getData().getLibro().getCodigo().equals(codigoLibro)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void mostrarPrestamosActivos() {
        if (cabeza == null) {
            System.out.println("No hay préstamos activos en este momento.");
            return;
        }
        Nodo<Prestamo> actual = cabeza;
        System.out.println("--- Préstamos Activos ---");
        while (actual != null) {
            System.out.println(actual.getData().toString());
            actual = actual.getSiguiente();
        }
    }
}