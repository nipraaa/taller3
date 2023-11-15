package model;

public class Usuario {
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String contrasena;
    private int edad;
    private String descripcion;
    private String buzon;
    private boolean emparejado;

    public Usuario(String nombreUsuario, String nombre, String apellido, String contrasena,int edad, String descripcion,String buzon) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.edad = edad;
        this.descripcion = descripcion;
        this.buzon = buzon;
        this.emparejado = false;
    }

    public String getNombreUsuario () {
            return nombreUsuario;
        }

        public String getNombre () {
            return nombre;
        }

        public String getApellido () {
            return apellido;
        }

        public String getContrasena (String contrasena){
            return this.contrasena;
        }

        public String getDescripcion () {
            return descripcion;
        }

        public void setDescripcion (String descripcion){
            this.descripcion = descripcion;
        }

        public int getEdad () {
            return edad;
        }

        public String getBuzon () {
            return buzon;
        }

        public boolean isEmparejado () {
            return emparejado;
        }

        public void emparejar () {
            emparejado = true;
        }

        public void desemparejar () {
            emparejado = false;
        }


}


