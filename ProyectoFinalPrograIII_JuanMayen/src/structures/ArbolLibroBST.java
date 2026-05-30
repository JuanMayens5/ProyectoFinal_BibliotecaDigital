package structures;
import models.Libro;

public class ArbolLibroBST {
    private NodoArbol raiz;

    public ArbolLibroBST() {
        this.raiz = null;
    }

    //Metodo publico para insertar
    public void insertar(Libro libro) {
        raiz = insertarRecursivo(raiz, libro);
    }

    //Funcion ecursiva de inserción comparando los códigos (Strings)
    private NodoArbol insertarRecursivo(NodoArbol raiz, Libro libro) {
        if (raiz == null) {
            raiz = new NodoArbol(libro);
            return raiz;
        }

        //compareTo devuelve < 0 si es menor, > 0 si es mayor
        if (libro.getCodigo().compareTo(raiz.libro.getCodigo()) < 0) {
            raiz.izquierdo = insertarRecursivo(raiz.izquierdo, libro);
        } else if (libro.getCodigo().compareTo(raiz.libro.getCodigo()) > 0) {
            raiz.derecho = insertarRecursivo(raiz.derecho, libro);
        }
        return raiz;
    }

    //Metodo público para buscar un libro por código
    public Libro buscar(String codigo) {
        NodoArbol resultado = buscarRecursivo(raiz, codigo);
        return resultado != null ? resultado.libro : null;
    }

    private NodoArbol buscarRecursivo(NodoArbol raiz, String codigo) {
        if (raiz == null || raiz.libro.getCodigo().equals(codigo)) {
            return raiz;
        }

        if (codigo.compareTo(raiz.libro.getCodigo()) < 0) {
            return buscarRecursivo(raiz.izquierdo, codigo);
        }
        return buscarRecursivo(raiz.derecho, codigo);
    }

    // Recorrido In-Order (Izquierda -> Raíz -> Derecha) para mostrar libros ordenados
    public void mostrarCatalogo() {
        System.out.println("--- Catálogo de Libros ---");
        recorridoInOrder(raiz);
    }

    private void recorridoInOrder(NodoArbol raiz) {
        if (raiz != null) {
            recorridoInOrder(raiz.izquierdo);
            System.out.println(raiz.libro.toString());
            recorridoInOrder(raiz.derecho);
        }
    }
}