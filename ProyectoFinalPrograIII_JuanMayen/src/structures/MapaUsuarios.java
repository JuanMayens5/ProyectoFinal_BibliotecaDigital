package structures;
import models.Usuario;

public class MapaUsuarios {
    private static final int CAPACIDAD_INICIAL = 16; // Tamaño base del arreglo
    private NodoHash[] tabla;
    private int tamaño;

    public MapaUsuarios() {
        tabla = new NodoHash[CAPACIDAD_INICIAL];
        tamaño = 0;
    }

    // Función Hash: Convierte el ID (String) en un índice numérico del arreglo
    private int obtenerIndice(String key) {
        int hash = key.hashCode();
        return Math.abs(hash) % tabla.length; // Asegura que el índice esté dentro del arreglo
    }

    // Metodo para insertar o actualizar un usuario
    public void insertar(Usuario usuario) {
        String key = usuario.getIdUsuario();
        int indice = obtenerIndice(key);
        NodoHash actual = tabla[indice];

        // 1. Revisar si el usuario ya existe para actualizarlo (Manejo de colisiones)
        while (actual != null) {
            if (actual.key.equals(key)) {
                actual.usuario = usuario; // Actualiza
                return;
            }
            actual = actual.next;
        }

        // 2. Si no existe, lo insertamos al inicio de la lista enlazada en ese índice
        NodoHash nuevoNodo = new NodoHash(key, usuario);
        nuevoNodo.next = tabla[indice];
        tabla[indice] = nuevoNodo;
        tamaño++;
    }

    // Metodo ultrarrápido para buscar un usuario por su ID
    public Usuario buscar(String key) {
        int indice = obtenerIndice(key);
        NodoHash actual = tabla[indice];

        while (actual != null) {
            if (actual.key.equals(key)) {
                return actual.usuario;
            }
            actual = actual.next;
        }
        return null; // No se encontró el usuario
    }

    // Metodo para imprimir todos los usuarios (útil para auditoría)
    public void mostrarUsuarios() {
        System.out.println("--- Usuarios Registrados ---");
        boolean hayUsuarios = false;

        for (int i = 0; i < tabla.length; i++) {
            NodoHash actual = tabla[i];
            while (actual != null) {
                System.out.println(actual.usuario.toString());
                hayUsuarios = true;
                actual = actual.next;
            }
        }

        if (!hayUsuarios) {
            System.out.println("No hay usuarios registrados aún.");
        }
    }
}