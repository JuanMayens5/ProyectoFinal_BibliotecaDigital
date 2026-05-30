package structures;
import models.Libro;

public class NodoArbol {
    Libro libro;
    NodoArbol izquierdo;
    NodoArbol derecho;
    public NodoArbol(Libro libro) {
        this.libro = libro;
        this.izquierdo = null;
        this.derecho = null;
    }

}
