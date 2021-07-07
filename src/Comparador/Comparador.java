package Comparador;

public class Comparador {
    public static boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public static boolean esAñoBisiesto(int año) {
        if(( ((año % 4) == 0) && ((año % 100) != 0)) || ((año % 400) == 0) )
            return true;
        else
            return false;
    }
    public static int ultimoDiaDelMes(int mes, int año) {
        if(mes == 2) {
            if(esAñoBisiesto(año))
                return 29;
            else
                return 28;
        }
        else if((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12)) {
            return 31;
        }
        else {
            return 30;
        }
    }
}
