package service;

import models.*;
import structures.*;
import java.util.UUID;

public class BibliotecaService {

    //INSTANCIAS DE TODAS LAS ESTRUCTURAS NECESARIAS
    private ArbolLibroBST catalogoLibros;
    private MapaUsuarios usuariosRegistrados;
    private ListaEnlazadaPrestamo prestamosActivos;
    private ColaEspera colaReservas;
    private PilaHistorial historialAcciones;
    private GrafoRelacionesLibros sistemaRecomendaciones;

    public BibliotecaService() {
        this.catalogoLibros = new ArbolLibroBST();
        this.usuariosRegistrados = new MapaUsuarios();
        this.prestamosActivos = new ListaEnlazadaPrestamo();
        this.colaReservas = new ColaEspera();
        this.historialAcciones = new PilaHistorial();
        this.sistemaRecomendaciones = new GrafoRelacionesLibros();
    }

    // --- 1. GESTIÓN DE LIBROS (Árbol y Grafo) ---
    public void registrarLibro(Libro libro) {
        catalogoLibros.insertar(libro);
        sistemaRecomendaciones.agregarLibro(libro);
        historialAcciones.apilar("Se registró un nuevo libro: " + libro.getTitulo());
    }

    public void mostrarCatalogo() {
        catalogoLibros.mostrarCatalogo();
    }

    public void conectarLibrosSimilares(String cod1, String cod2) {
        sistemaRecomendaciones.conectarLibros(cod1, cod2);
        historialAcciones.apilar("Se vincularon los libros " + cod1 + " y " + cod2 + " para recomendaciones.");
    }

    // --- 2. GESTIÓN DE USUARIOS (Tabla Hash) ---
    public void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.insertar(usuario);
        historialAcciones.apilar("Se registró el usuario: " + usuario.getNombre());
    }

    public void mostrarUsuarios() {
        usuariosRegistrados.mostrarUsuarios();
    }

    // --- 3. GESTIÓN DE PRÉSTAMOS Y DEVOLUCIONES (Lista Enlazada y Cola) ---
    public void prestarLibro(String idUsuario, String codigoLibro, String fecha) {
        Usuario usuario = usuariosRegistrados.buscar(idUsuario);
        Libro libro = catalogoLibros.buscar(codigoLibro);

        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }
        if (libro == null) {
            System.out.println("Error: Libro no encontrado en el catálogo.");
            return;
        }

        if (libro.isDisponible()) {
            //El libro está libre, se procede al préstamo
            libro.setDisponible(false);
            String idPrestamo = UUID.randomUUID().toString().substring(0, 8); //Genera un ID corto
            Prestamo nuevoPrestamo = new Prestamo(idPrestamo, libro, usuario, fecha);
            prestamosActivos.registrarPrestamo(nuevoPrestamo);

            System.out.println("Préstamo exitoso: " + libro.getTitulo() + " entregado a " + usuario.getNombre());
            historialAcciones.apilar("Préstamo: " + libro.getTitulo() + " a " + usuario.getNombre());
        } else {
            //El libro no está disponible, lo mandamos a la cola de espera
            System.out.println("El libro está prestado. Añadiendo a " + usuario.getNombre() + " a la cola de espera.");
            colaReservas.encolar(usuario);
            historialAcciones.apilar("Reserva: " + usuario.getNombre() + " en cola por " + libro.getTitulo());
        }
    }

    public void devolverLibro(String codigoLibro) {
        Libro libro = catalogoLibros.buscar(codigoLibro);
        if (libro == null) {
            System.out.println("Error: Libro no reconocido.");
            return;
        }

        boolean eliminado = prestamosActivos.eliminarPrestamo(codigoLibro);
        if (eliminado) {
            libro.setDisponible(true);
            System.out.println("Devolución exitosa del libro: " + libro.getTitulo());
            historialAcciones.apilar("Devolución: " + libro.getTitulo());

            //Revisar si hay alguien en la cola esperando este libro u otro
            if (!colaReservas.estaVacia()) {
                Usuario siguiente = colaReservas.desencolar();
                System.out.println("Notificación: El usuario " + siguiente.getNombre() + " estaba en espera. ¡Puede pasar por un libro!");
                historialAcciones.apilar("Notificación de espera enviada a: " + siguiente.getNombre());
            }
        } else {
            System.out.println("El libro no se encuentra registrado como prestado actualmente.");
        }
    }

    public void mostrarPrestamosActivos() {
        prestamosActivos.mostrarPrestamosActivos();
    }

    // --- 4. AUDITORÍA Y RECOMENDACIONES (Pila y Grafo) ---
    public void mostrarHistorial() {
        historialAcciones.mostrarHistorial();
    }

    public void recomendarLibros(String codigoLibro) {
        sistemaRecomendaciones.recomendarLibros(codigoLibro);
    }
}