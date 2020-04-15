
import static Conection.Server.*;
import static Additionals.Colors.*;
import Processing.DuchaInfo;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    private static Scanner read = new Scanner(System.in);
    
    public static void main(String[] args)  {
        Menu();
    }
    
    public static void Menu(){
        System.out.print(GREEN+"          ____         ____       ____ \n"+
                         GREEN+"         / ___|       / ___|     |  _ \\ \n "+
                         GREEN+"        \\___ \\      | |         | | | |\n"+
                         GREEN+"          ___) |  _  | |___   _  | |_| | \n"+
                         GREEN+"         |____/  (_)  \\____| (_) |____/\n"+
                         CYAN+"|***********************************************|\n"+
                         CYAN+"| "+GREEN+"0) "+RESET+"Finalizar programa                         "+CYAN+"|\n"+
                         CYAN+"| "+GREEN+"1) "+RESET+"Mostrar todos los datos                    "+CYAN+"|\n"+
                         CYAN+"|***********************************************"+CYAN+"|\n"+
                         GREEN+" *.*"+RESET+" Ingresa la opcion a realizar: "+RESET);
        try {
            int answer = read.nextInt();
            System.out.println("\n");
            switch (answer){
                case 0:
                    System.out.println(CYAN+"Programa finalizado");
                    System.exit(0);
                    break;
                case 1:
                    ShowInfo();
                    break;
                default:
                    System.out.println(RED+"ERROR, opcion invalida");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println(RED+"ERROR, Debe ingresar un numero");
        }
        System.out.println("\n");
        Menu2();
    }
    
    public static void Menu2(){
        System.out.print(GREEN+"*.*"+RESET+" Ingrese 1 para continuar 0 para finalizar: ");
        try {
            int answer2 = read.nextInt();
            System.out.println("\n");
            switch (answer2){
                case 0:
                    System.out.println(CYAN+"Programa finalizado");
                    System.exit(0);
                    break;
                case 1:
                    Menu();
                    break;
                default:
                    System.out.println(RED+"ERROR, opcion invalida\n");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println(RED+"ERROR, Debe ingresar un numero\n");
        }
        Menu2();
    }
    public static void ShowInfo(){
        ArrayList<DuchaInfo> info = recibirParametros();
        String format1 = "%-10s";
        String format2 = "%-9s";
        String format3 = "%-9s";
        String formatInfo = CYAN+"| "+RESET+format1+CYAN+" | "+RESET+format2+CYAN+" | "+RESET+format3+CYAN+" |\n";
        System.out.println(CYAN+"______________________________________\n"+
                           CYAN+"|"+GREEN+"   Fecha    "+CYAN+"|"+GREEN+" Gasto(L)  "+CYAN+"|"+GREEN+" Tiempo(m) "+CYAN+"|\n"+
                           CYAN+"|____________|___________|___________|");
        for (int i = 0; i < info.size(); i++) {
            String fecha = info.get(i).getFecha();
            String Gasto = Double.toString(info.get(i).getGasto())+"L";
            String Tiempo = Double.toString(info.get(i).getTiempo())+"m";
            System.out.format(formatInfo, fecha,Gasto,Tiempo);
        }
        System.out.println(CYAN+"|____________|___________|___________|"+RESET);
    }
}