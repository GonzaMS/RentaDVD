public class Miembros {


    private int miembroID;
    private String nombre, apellido, direccion, email, numeroCel;
    private boolean genero; // True hombre, False mujer


    public Miembros(int miembroID, String nombre, String apellido, String direccion, String email, String numeroCel, boolean genero) {
        this.miembroID = miembroID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.numeroCel = numeroCel;
        this.genero = genero;
    }

    public void setInfo(String nombre, String apellido, String direccion, String email, String numeroCel, boolean genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.numeroCel = numeroCel;
        this.genero = genero;
    }

    // Getters
    public int getMiembroID() {

        return miembroID;
    }

    public String getNombre() {

        return nombre;

    }

    public String getApellido() {

        return apellido;
    }

    public String getDireccion() {

        return direccion;
    }

    public String getEmail() {

        return email;
    }

    public String getNumeroCel() {

        return numeroCel;
    }

    public boolean isGenero() {

        return genero;
    }

}
