import java.util.Date;

public class DVD {


    private int dvdID, categoriaID, price;
    private String nombre;
    private boolean type; // True nuevo, False viejo


    private int miembroID, rentaDia, rentaMes, rentaAño;

    // Constructor
    public DVD(int dvdID, String nombre, int categoriaID, boolean type, int precio) {
        this.dvdID = dvdID;
        this.nombre = nombre;
        this.categoriaID = categoriaID;
        this.type = type;
        this.price = precio;
        this.miembroID = -1;
    }


    public void setRenta(int miembroID, int rentaDia, int rentaMes, int rentaAño) {
        this.miembroID = miembroID;
        this.rentaDia = rentaDia;
        this.rentaMes = rentaMes;
        this.rentaAño = rentaAño;
    }

    public void setInfo(String nombre, int categoriaID, boolean type, int precio) {
        this.nombre = nombre;
        this.categoriaID = categoriaID;
        this.type = type;
        this.price = precio;
    }

    // Compara la fecha de la renta con la de devolucion
    public int compararRentaDevolucion(int returnDay, int returnMonth, int returnYear) {
        Date startDate = new Date(this.rentaAño -2000,this.rentaMes -1,this.rentaDia);
        Date endDate = new Date(returnYear-2000,returnMonth-1,returnDay);
        return (int)((endDate.getTime() - startDate.getTime()) /  1000 / 3600 / 24);
    }

    // Getter

    public int getDvdID() {

        return dvdID;
    }

    public String getNombre() {

        return nombre;
    }

    public int getCategoriaID() {

        return categoriaID;
    }

    public boolean getType() {

        return type;
    }

    public int getPrice() {

        return price;
    }

    public int getMiembroID() {

        return miembroID;

    }

    public boolean isType() {

        return type;
    }

    public int getRentaDia() {

        return rentaDia;
    }

    public int getRentaMes() {

        return rentaMes;
    }

    public int getRentaAño() {

        return rentaAño;
    }

    public String toString() {

        return "{" + dvdID + "} " + nombre;
    }
}
