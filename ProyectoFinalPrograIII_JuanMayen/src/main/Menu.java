package main;

import models.Libro;
import models.Usuario;
import service.BibliotecaService;
import java.util.Scanner;

public class Menu {
    private BibliotecaService servicio;
    private Scanner scanner;

    public Menu() {
        this.servicio = new BibliotecaService();
        this.scanner = new Scanner(System.in);
        cargarDatosPrueba();
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Ver catálogo de libros (Ordenado)");
            System.out.println("6. Ver usuarios registrados");
            System.out.println("7. Ver préstamos activos");
            System.out.println("8. Ver recomendaciones por libro");
            System.out.println("9. Ver historial de acciones");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        } while (opcion != 10);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.print("Código del libro: ");
                String codigo = scanner.nextLine();
                System.out.print("ISBN: ");
                String isbn = scanner.nextLine();
                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                servicio.registrarLibro(new Libro(codigo, isbn, titulo, autor));
                System.out.println("Libro registrado con éxito.");
                break;
            case 2:
                System.out.print("ID del Usuario: ");
                String id = scanner.nextLine();
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Correo: ");
                String correo = scanner.nextLine();
                servicio.registrarUsuario(new Usuario(id, nombre, correo));
                System.out.println("Usuario registrado con éxito.");
                break;
            case 3:
                System.out.print("ID del Usuario: ");
                String idUsuario = scanner.nextLine();
                System.out.print("Código del Libro: ");
                String codLibro = scanner.nextLine();
                System.out.print("Fecha (dd/mm/aaaa): ");
                String fecha = scanner.nextLine();
                servicio.prestarLibro(idUsuario, codLibro, fecha);
                break;
            case 4:
                System.out.print("Código del Libro a devolver: ");
                String codDevolucion = scanner.nextLine();
                servicio.devolverLibro(codDevolucion);
                break;
            case 5:
                servicio.mostrarCatalogo();
                break;
            case 6:
                servicio.mostrarUsuarios();
                break;
            case 7:
                servicio.mostrarPrestamosActivos();
                break;
            case 8:
                System.out.print("Ingrese el código del libro para ver recomendaciones: ");
                String codRec = scanner.nextLine();
                servicio.recomendarLibros(codRec);
                break;
            case 9:
                servicio.mostrarHistorial();
                break;
            case 10:
                System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void cargarDatosPrueba() {
        servicio.registrarUsuario(new Usuario("U001", "Juan Perez", "juan@mail.com"));
        servicio.registrarUsuario(new Usuario("U002", "Ana Lopez", "ana@mail.com"));

        servicio.registrarLibro(new Libro("L002", "978-B", "Estructuras de Datos", "Joyanes"));
        servicio.registrarLibro(new Libro("L001", "978-A", "Java a Fondo", "Sznajdleder"));
        servicio.registrarLibro(new Libro("L003", "978-C", "Algoritmos", "Cormen"));

        servicio.conectarLibrosSimilares("L001", "L002");
        servicio.conectarLibrosSimilares("L002", "L003");
    }
}