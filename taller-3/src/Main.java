import model.Usuario;
import services.EmparejamientoImpl;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static List<model.Usuario> Usuario = new ArrayList<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        EmparejamientoImpl.cargarUsuariosDesdeArchivo("model.Usuario.txt");
        EmparejamientoImpl.mostrarMenuPrincipal();
    }
}


