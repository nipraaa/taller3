import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmparejamientoImpl {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        cargarUsuariosDesdeArchivo("Usuario.txt");
        mostrarMenuPrincipal();
    }
    public static void cargarUsuariosDesdeArchivo(String nombreArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("/");
                if (datos.length == 8) {
                    String nombreUsuario = datos[0];
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String contrasena = datos[3];
                    int edad = Integer.parseInt(datos[4]);
                    String descripcion = datos[5];
                    String buzon = datos[6];

                    Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, contrasena, edad, descripcion, buzon);
                    usuarios.add(usuario);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void guardarUsuariosEnArchivo(String nombreArchivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
            for (Usuario usuario : usuarios) {
                String linea = usuario.getNombreUsuario() + "," + usuario.getContrasena(" ") + "," +
                        usuario.getDescripcion() + "," + usuario.getEdad();
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }
    public static void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (usuarioActual == null) {
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        iniciarSesion();
                        break;
                    case 2:
                        guardarUsuariosEnArchivo("Usuario.txt");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } else {
                System.out.println("1. Ajustes de usuario");
                System.out.println("2. Mostrar todos los usuarios");
                System.out.println("3. Mostrar usuarios por edad");
                System.out.println("4. Solicitudes de emparejamiento");
                System.out.println("5. Cerrar sesión");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        ajustesDeUsuario();
                        break;
                    case 2:
                        mostrarUsuariosDisponibles();
                        break;
                    case 3:
                        mostrarUsuariosPorEdad();
                        break;
                    case 4:
                        solicitudesDeEmparejamiento();
                        break;
                    case 5:
                        usuarioActual = null;
                        guardarUsuariosEnArchivo("Usuario.txt");
                        System.out.println("Sesión cerrada.");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        }
    }

    private static void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorNombre(nombre);

        if (usuario != null && usuario.getContrasena("").equals(contrasena)) {
            usuarioActual = usuario;
            System.out.println("Sesión iniciada como " + usuarioActual.getNombreUsuario());
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private static void ajustesDeUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Cambiar descripción");
        System.out.println("2. Cambiar contraseña");
        System.out.println("3. Cambiar edad");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        switch (opcion) {
            case 1:
                System.out.print("Nueva descripción: ");
                String nuevaDescripcion = scanner.nextLine();
                usuarioActual.setDescripcion(nuevaDescripcion);
                System.out.println("Descripción actualizada.");
                break;
            case 2:
                System.out.print("Contraseña actual: ");
                String contrasenaActual = scanner.nextLine();
                String nuevaContrasena = null;
                if (usuarioActual.getContrasena("").equals(contrasenaActual)) {
                    System.out.print("Nueva contraseña: ");
                    nuevaContrasena = scanner.nextLine();
                    usuarioActual.getContrasena("");
                    System.out.println("Contraseña actualizada. Por seguridad, se ha cerrado la sesión.");
                    usuarioActual = null;
                } else {
                    System.out.println("Contraseña actual incorrecta.");
                }
                break;
            case 3:
                System.out.print("Nueva edad: ");
                int nuevaEdad = scanner.nextInt();
                if (nuevaEdad > 0) {
                    usuarioActual.getEdad();
                    System.out.println("Edad actualizada.");
                } else {
                    System.out.println("Edad no válida.");
                }
                break;
            case 4:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void mostrarUsuariosDisponibles() {
        boolean usuariosDisponibles = false;
        for (Usuario usuario : usuarios) {
            if (!usuario.isEmparejado()) {
                System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
                System.out.println("Nombre: " + usuario.getDescripcion());
                System.out.println("Edad: " + usuario.getEdad());
                System.out.println();
                usuariosDisponibles = true;
            }
        }
        if (!usuariosDisponibles) {
            System.out.println("No hay usuarios disponibles sin emparejamiento.");
        }
    }

    private static void mostrarUsuariosPorEdad() {
        usuarios.sort((u1, u2) -> u1.getEdad() - u2.getEdad());

        for (Usuario usuario : usuarios) {
            if (!usuario.isEmparejado()) {
                System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
                System.out.println("Nombre: " + usuario.getDescripcion());
                System.out.println("Edad: " + usuario.getEdad());
                System.out.println();
            }
        }
    }

    private static void solicitudesDeEmparejamiento() {
        if (usuarioActual.isEmparejado()) {
            System.out.println("Ya tienes un emparejamiento activo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Solicitudes de emparejamiento enviadas");
        System.out.println("2. Solicitudes de emparejamiento recibidas");
        System.out.println("3. Realizar solicitud de emparejamiento");
        System.out.println("4. Aceptar solicitud de emparejamiento");
        System.out.println("5. Rechazar solicitud de emparejamiento");
        System.out.println("6. Cancelar emparejamiento");
        System.out.println("7. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        switch (opcion) {
            case 1:
                mostrarSolicitudesEnviadas();
                break;
            case 2:
                mostrarSolicitudesRecibidas();
                break;
            case 3:
                realizarSolicitudEmparejamiento();
                break;
            case 4:
                aceptarSolicitudEmparejamiento();
                break;
            case 5:
                rechazarSolicitudEmparejamiento();
                break;
            case 6:
                cancelarEmparejamiento();
                break;
            case 7:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void mostrarSolicitudesEnviadas() {
        System.out.println("Solicitudes de emparejamiento enviadas por " + usuarioActual.getNombreUsuario() + ":");
        for (Usuario usuario : usuarios) {
            if (usuario.isEmparejado() && usuarioActual.getNombreUsuario().equals(usuario.getNombreUsuario())) {
                System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
                System.out.println("Nombre: " + usuario.getDescripcion());
                System.out.println("Edad: " + usuario.getEdad());
                System.out.println("Estado: " + "Pendiente");
                System.out.println();
            }
        }
    }

    private static void mostrarSolicitudesRecibidas() {
        System.out.println("Solicitudes de emparejamiento recibidas por " + usuarioActual.getNombreUsuario() + ":");
        for (Usuario usuario : usuarios) {
            if (usuario.isEmparejado() && !usuarioActual.getNombreUsuario().equals(usuario.getNombreUsuario())) {
                System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
                System.out.println("Nombre: " + usuario.getDescripcion());
                System.out.println("Edad: " + usuario.getEdad());
                System.out.println("Estado: " + "Pendiente");
                System.out.println();
            }
        }
    }

    private static void realizarSolicitudEmparejamiento() {
        if (usuarioActual.isEmparejado()) {
            System.out.println("Ya tienes un emparejamiento activo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de usuario a quien desea enviar la solicitud: ");
        String nombreUsuarioReceptor = scanner.nextLine();

        Usuario usuarioReceptor = buscarUsuarioPorNombre(nombreUsuarioReceptor);

        if (usuarioReceptor == null) {
            System.out.println("El usuario no existe.");
            return;
        }

        if (usuarioReceptor.isEmparejado()) {
            System.out.println("El usuario ya está emparejado.");
            return;
        }

        System.out.println("Solicitud de emparejamiento enviada a " + nombreUsuarioReceptor);
        // Puedes agregar lógica para actualizar el estado de las solicitudes aquí
    }

    private static void aceptarSolicitudEmparejamiento() {
        if (usuarioActual.isEmparejado()) {
            System.out.println("Ya tienes un emparejamiento activo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de usuario a aceptar la solicitud: ");
        String nombreUsuarioEmisor = scanner.nextLine();

        Usuario usuarioEmisor = buscarUsuarioPorNombre(nombreUsuarioEmisor);

        if (usuarioEmisor == null) {
            System.out.println("El usuario no existe.");
            return;
        }

        // Puedes agregar lógica para aceptar la solicitud aquí
    }

    private static void rechazarSolicitudEmparejamiento() {
        if (usuarioActual.isEmparejado()) {
            System.out.println("Ya tienes un emparejamiento activo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de usuario a rechazar la solicitud: ");
        String nombreUsuarioEmisor = scanner.nextLine();

        Usuario usuarioEmisor = buscarUsuarioPorNombre(nombreUsuarioEmisor);

        if (usuarioEmisor == null) {
            System.out.println("El usuario no existe.");
            return;
        }

        // Puedes agregar lógica para rechazar la solicitud aquí
    }

    private static void cancelarEmparejamiento() {
        if (usuarioActual.isEmparejado()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("¿Estás seguro de que deseas cancelar tu emparejamiento? (S/N): ");
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("S")) {
                usuarioActual.desemparejar();
                System.out.println("Emparejamiento cancelado.");
            }
        } else {
            System.out.println("No tienes un emparejamiento activo.");
        }
    }

}
