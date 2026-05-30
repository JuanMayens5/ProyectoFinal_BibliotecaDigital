package structures;
import models.Usuario;

public class NodoHash {
    String key;
    Usuario usuario;
    NodoHash next; // Para manejar colisiones con una lista enlazada

    public NodoHash(String key, Usuario usuario) {
        this.key = key;
        this.usuario = usuario;
        this.next = null;
    }
}