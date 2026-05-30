package structures;
import models.Usuario;

public class ColaEspera {
    private Nodo<Usuario> frente;
    private Nodo<Usuario> fin;
    private int tamaño;

    public ColaEspera() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Método para agregar un usuario a la cola (Encolar)
    public void encolar(Usuario usuario) {
        Nodo<Usuario> nuevoNodo = new Nodo<>(usuario);
        if (estaVacia()) {
            frente = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
        }
        fin = nuevoNodo;
        tamaño++;
    }

    // Método para atender al usuario que lleva más tiempo esperando (Desencolar)
    public Usuario desencolar() {
        if (estaVacia()) {
            System.out.println("La cola de espera está vacía.");
            return null;
        }
        Usuario usuarioAtendido = frente.getData();
        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null; // Si la cola quedó vacía, el fin también es null
        }
        tamaño--;
        return usuarioAtendido;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamaño() {
        return tamaño;
    }
}