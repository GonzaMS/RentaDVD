import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Controlador {

    // Base de datos
    private Map<Integer,DVD> dbDVD;
    private int idDVD;
    private Map<Integer, Miembros> dbMiembro;
    private Map<Integer,String> dbCategoria;
    private int idCategoria;

    //Tarifa
    private int nuevosTiposDias = 1;
    private int viejosTiposDias = 3;
    private int rentaPrecio = 20;
    private int bienPorDias = 10;



    public Controlador() {

        dbDVD = new HashMap<Integer,DVD>();
        idDVD = 0;
        dbMiembro = new HashMap<Integer, Miembros>();
        dbCategoria = new HashMap<Integer,String>();
        idCategoria = 0;
        baseDatos();
        new Programa(this);
    }

    // Agregar DVD
    public void addDVD(String nombre, int categoria, boolean tipo, int precio) {
        dbDVD.put(idDVD, new DVD(idDVD, nombre, categoria, tipo, precio));
        idDVD++;
    }


    // Editar DVD
    public void editarDVD(int dvdID, String nombre, int categoria, boolean tipo, int precio) {
        DVD dvd = buscarDVD(dvdID);
        if(dvd != null) {
            dvd.setInfo(nombre,categoria,tipo,precio);
        }
    }

    // Remover DVD
    public void removerDVD(int dvdID) {
        dbDVD.remove(dvdID);
    }

    // Buscar DVD
    public DVD buscarDVD(int dvdID) {
        DVD dvd = dbDVD.get(dvdID);
        return dvd;
    }

    // Rentar DVD
    // -1 rentado | -2 no encontrado
    public int rentarDVD(int dvdID, int miembroID, int rentaDias, int rentaMes, int rentaAño) {
        DVD dvd = buscarDVD(dvdID);
        if(dvd != null) {
            if(dvd.getMiembroID() == -1)
                dvd.setRenta(miembroID, rentaDias, rentaMes, rentaAño);
            else
                return -1;
            return rentaPrecio;
        }
        return -2;
     }

    // Devolver DVD
    // -1 no fue rentado | -2 no encontrado | -3 fecha incorrecta
    public int devolverDVD(int dvdID, int devolverDia, int devolverMes, int devolverAño, boolean estaRoto) {
        DVD dvd = buscarDVD(dvdID);
        if(dvd != null) {
            if(dvd.getMiembroID() != -1) {
                int totalDay = dvd.compararRentaDevolucion(devolverDia, devolverMes, devolverAño);
                if(totalDay < 0) {
                    return -3;
                }
                int totalBalance = 0;
                if(dvd.getType())
                    totalDay -= nuevosTiposDias;
                else
                    totalDay -= viejosTiposDias;
                if(totalDay > 0) {
                    totalBalance = totalDay * bienPorDias;
                }
                if(estaRoto) {
                    totalBalance += dvd.getPrice() * 2;
                }
                dvd.setRenta(-1,0,0,0);
                return totalBalance;
            }
            else {
                return -1;
            }
        }
        return -2;
    }

    // Agregar Categoria
    public void addCategoria(String nombreCategoria) {
        dbCategoria.put(idCategoria, nombreCategoria);
        idCategoria++;
    }

    // Buscar Categoria
    public String buscarCategoria(int categoriaID) {

        return dbCategoria.get(categoriaID);
    }

    public Map<Integer,String> getCategory() {

        return this.dbCategoria;
    }

    // Borrar Categoria
    public void borrarCategoria(int categoriaID) {

        dbCategoria.remove(categoriaID);
    }

    // Agregar Miembro
    public void addMiembro(String nombre, String apellido, String direccion, String email, String numeroCel, boolean genero) {
        dbMiembro.put(dbMiembro.size(), new Miembros(dbMiembro.size(), nombre, apellido, direccion, email, numeroCel, genero));
    }

    // Editar Miembro
    public void editarMiembro(int miembroID, String nombre, String apellido, String direccion, String email, String numeroCel, boolean genero) {
        Miembros miembros = buscarMiembro(miembroID);
        if(miembros != null) {
            miembros.setInfo(nombre, apellido, direccion,email, numeroCel, genero);
        }
    }

    // Buscar Miembro
    public Miembros buscarMiembro(int miembroID) {
        Miembros miembros = dbMiembro.get(miembroID);
        return miembros;
    }
    //Cantidad de dvd
    public int contarDVD()  {
        return dbDVD.size();
    }

    // Cantidad e miembros
    public int contarMiembros() {
        return dbMiembro.size();
    }

    // Lista de DVD
    public ArrayList<String> showDVDList() {
        ArrayList<String> list = new ArrayList<String>();
        for(DVD dvd : dbDVD.values()) {
            list.add("ID : " + dvd.getDvdID() + " | " + dvd.getNombre());
        }
        return list;
    }



    // Lista miembros
    public ArrayList<String> showDVDList_M(int miembroID) {
        ArrayList<String> list = new ArrayList<String>();
        for(DVD dvd : dbDVD.values()) {
            if(dvd.getMiembroID() == miembroID) {
                list.add("ID : " + dvd.getDvdID() + " | " + dvd.getNombre());
            }
        }
        return list;
    }

    // Tarifa
    public void cambiarTarifa(int nuevosTiposDias, int viejosTiposDias, int rentaPrecio, int bienPorDias) {
        this.nuevosTiposDias = nuevosTiposDias;
        this.viejosTiposDias = viejosTiposDias;
        this.rentaPrecio = rentaPrecio;
        this.bienPorDias = bienPorDias;
    }



    // Mensaje de error
    public void showError(String encabezado, String mensaje) {
        JOptionPane.showMessageDialog(null, "Error : " + mensaje, encabezado, JOptionPane.ERROR_MESSAGE);
    }






    private void baseDatos() {
        addCategoria("Accion");
        addDVD("Avatar", 0, true, 200);
        addCategoria("Anime");
        addDVD("Naruto", 1, true, 2000);
        addMiembro("Cristhian", "Marecos", "Mujer Paraguaya", "prueba@example.com", "0971211597", true);
        addMiembro("Gonza", "Marecos", "Mujer Paraguaya", "prueba@example.com", "1231231", false);
        rentarDVD(0,0,1,1,2001);
        rentarDVD(1,1,1,2,2002);
    }

    public Collection<DVD> getListDVD(int categoriaID) {
        if(categoriaID >= 0) {
            Collection<DVD> collectionDVD = new ArrayList<DVD>();
            for(DVD dvd : dbDVD.values()) {
                if(dvd.getCategoriaID() == categoriaID) {
                    collectionDVD.add(dvd);
                }
            }
            return collectionDVD;
        }
        return dbDVD.values();
    }

}
