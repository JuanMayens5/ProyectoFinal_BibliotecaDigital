package structures;
import models.Libro;

public class GrafoRelacionesLibros {

    // Clase interna para representar cada Vértice (un Libro en el grafo)
    private class Vertice {
        Libro libro;
        Vertice siguienteVertice; // Para recorrer todos los vértices del grafo
        Arista primeraArista;     // La cabeza de la lista de conexiones de este libro

        Vertice(Libro libro) {
            this.libro = libro;
            this.siguienteVertice = null;
            this.primeraArista = null;
        }
    }

    // Clase interna para representar las conexiones (Aristas)
    private class Arista {
        Libro libroDestino;
        Arista siguienteArista;

        Arista(Libro libroDestino) {
            this.libroDestino = libroDestino;
            this.siguienteArista = null;
        }
    }

    private Vertice primerVertice;

    public GrafoRelacionesLibros() {
        this.primerVertice = null;
    }

    // 1. Agregar un libro al grafo (Agregar Vértice)
    public void agregarLibro(Libro libro) {
        Vertice nuevoVertice = new Vertice(libro);
        if (primerVertice == null) {
            primerVertice = nuevoVertice;
        } else {
            Vertice actual = primerVertice;
            while (actual.siguienteVertice != null) {
                actual = actual.siguienteVertice;
            }
            actual.siguienteVertice = nuevoVertice;
        }
    }

    // 2. Conectar dos libros (Agregar Arista no dirigida)
    public void conectarLibros(String codigo1, String codigo2) {
        Vertice v1 = buscarVertice(codigo1);
        Vertice v2 = buscarVertice(codigo2);

        if (v1 != null && v2 != null) {
            // Conexión de v1 hacia v2
            Arista nuevaArista1 = new Arista(v2.libro);
            nuevaArista1.siguienteArista = v1.primeraArista;
            v1.primeraArista = nuevaArista1;

            // Conexión de v2 hacia v1 (es una relación mutua)
            Arista nuevaArista2 = new Arista(v1.libro);
            nuevaArista2.siguienteArista = v2.primeraArista;
            v2.primeraArista = nuevaArista2;
        }
    }

    // Metodo auxiliar para encontrar el vértice de un libro
    private Vertice buscarVertice(String codigo) {
        Vertice actual = primerVertice;
        while (actual != null) {
            if (actual.libro.getCodigo().equals(codigo)) {
                return actual;
            }
            actual = actual.siguienteVertice;
        }
        return null;
    }

    // 3. Recomendar libros basados en un libro específico
    public void recomendarLibros(String codigoLibro) {
        Vertice v = buscarVertice(codigoLibro);
        if (v == null) {
            System.out.println("Libro no encontrado en el sistema de recomendaciones.");
            return;
        }

        System.out.println("--- Recomendaciones basadas en '" + v.libro.getTitulo() + "' ---");
        Arista actual = v.primeraArista;
        boolean hayRecomendaciones = false;

        while (actual != null) {
            System.out.println("- " + actual.libroDestino.getTitulo() + " (por " + actual.libroDestino.getAutor() + ")");
            hayRecomendaciones = true;
            actual = actual.siguienteArista;
        }

        if (!hayRecomendaciones) {
            System.out.println("No hay recomendaciones directas para este libro aún.");
        }
    }
}