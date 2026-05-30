package structures;

public class PilaHistorial {
    private Nodo<String> cima;
    private int tamaño;

    public PilaHistorial() {
        this.cima = null;
        this.tamaño = 0;
    }

    // Método para registrar una nueva acción (Apilar)
    public void apilar(String accion) {
        Nodo<String> nuevoNodo = new Nodo<>(accion);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        tamaño++;
    }

    // Método para sacar la última acción, útil si quieres hacer un "Deshacer" (Desapilar)
    public String desapilar() {
        if (estaVacia()) {
            System.out.println("El historial está vacío.");
            return null;
        }
        String ultimaAccion = cima.getData();
        cima = cima.getSiguiente();
        tamaño--;
        return ultimaAccion;
    }

    // Método para ver la acción más reciente sin borrarla
    public String verUltimaAccion() {
        if (estaVacia()) {
            return "No hay acciones recientes.";
        }
        return cima.getData();
    }

    public boolean estaVacia() {
        return cima == null;
    }

    // Método extra para mostrar todo el historial (Navegación requerida)
    public void mostrarHistorial() {
        if (estaVacia()) {
            System.out.println("El historial está vacío.");
            return;
        }
        Nodo<String> actual = cima;
        System.out.println("--- Historial de Acciones Recientes ---");
        while (actual != null) {
            System.out.println("- " + actual.getData());
            actual = actual.getSiguiente();
        }
    }
}