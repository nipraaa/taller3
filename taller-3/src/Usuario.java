

public class Usuario {
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String contrasena;
    private int edad;
    private String descripcion;
    private String buzon;
    private boolean emparejado;

    public Usuario(String nombreUsuario, String contrasena,int edad, String descripcion,String buzon) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.descripcion = descripcion;
        this.edad = edad;
        this.buzon = buzon;
        this.emparejado = false;
    }

    public Usuario(String nombreUsuario, String nombre, String apellido, String contrasena,int edad, String descripcion,String buzon) {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena(String nuevaContrasena) {
        return contrasena;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isEmparejado() {
        return emparejado;
    }

    public void emparejar() {
        emparejado = true;
    }

    public void desemparejar() {
        emparejado = false;
    }
}


