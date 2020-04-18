
import static Additionals.Colors.*;
import Conection.Server;
import Processing.Averages;
import Processing.DuchaInfo;
import Processing.Persistencia;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        Server.recibirParametros();
        Persistencia.WriteFile();
        Menu();
    }
    
    public static void Menu(){
        Scanner read = new Scanner(System.in);
        System.out.print(GREEN+"          ____         ____       ____ \n"+
                         GREEN+"         / ___|       / ___|     |  _ \\ \n "+
                         GREEN+"        \\___ \\      | |         | | | |\n"+
                         GREEN+"          ___) |  _  | |___   _  | |_| | \n"+
                         GREEN+"         |____/  (_)  \\____| (_) |____/\n"+
                         CYAN+"|***********************************************|\n"+
                         CYAN+"| "+GREEN+"0) "+RESET+"Finalizar programa                         "+CYAN+"|\n"+
                         CYAN+"| "+GREEN+"1) "+RESET+"Asignar estrato                            "+CYAN+"|\n"+
                         CYAN+"| "+GREEN+"2) "+RESET+"Mostrar todos los datos                    "+CYAN+"|\n"+
                         CYAN+"| "+GREEN+"3) "+RESET+"Cargar persistencia                        "+CYAN+"|\n"+
                         CYAN+"| "+GREEN+"4) "+RESET+"Promedios generales                        "+CYAN+"|\n"+
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
                    UpdateEstrato();
                    break;
                case 2:
                    ShowInfo();
                    if (DuchaInfo.Estrato<=0 || DuchaInfo.Estrato>6) {
                        System.out.println(RED+"Costo no calculado, actualice su estrato");
                    }
                    break;
                case 3:
                    Persistencia.LoadFile();
                    break;
                case 4:
                    Averages.GeneralAverage();
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
        Scanner read = new Scanner(System.in);
        System.out.print(GREEN+"*.*"+RESET+" Ingrese 1 para continuar 0 para finalizar: ");
        try {
            int answer2 = read.nextInt();
            System.out.println("\n");
            switch (answer2){
                case 0:
                    System.out.println(GREEN+"Programa finalizado");
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
        String format1 = "%-10s";
        String format2 = "%-9s";
        String format3 = "%-7s";
        String formatInfo = CYAN+"| "+RESET+format1+CYAN+" | "+RESET+format2+CYAN+" | "+RESET+format2+CYAN+
                                " | "+RESET+format3+CYAN+" |\n";
        System.out.println(CYAN+"________________________________________________\n"+
                           CYAN+"|"+GREEN+"   Fecha    "+CYAN+"|"+GREEN+" Gasto(L)  "+CYAN+"|"+GREEN+" Tiempo(m) "+
                           CYAN+"|"+GREEN+"  Costo  "+CYAN+"|\n"+
                           CYAN+"|____________|___________|___________|_________|");
        for (int i = 0; i < DuchaInfo.duchas.size(); i++) {
            String fecha = DuchaInfo.duchas.get(i).getFecha();
            String Gasto = Double.toString(DuchaInfo.duchas.get(i).getGasto())+"L";
            String Tiempo = Double.toString(DuchaInfo.duchas.get(i).getTiempo())+"m";
            String costo = Double.toString(DuchaInfo.duchas.get(i).getCosto())+"$";
            System.out.format(formatInfo, fecha,Gasto,Tiempo,costo);
        }
        System.out.println(CYAN+"|____________|___________|___________|_________|"+RESET);
    }
    
    public static void UpdateEstrato(){
        Scanner read = new Scanner(System.in);
        System.out.print(GREEN+" *.*"+RESET+" Ingrese su estrato: "+RESET);
        try {
            DuchaInfo.Estrato = read.nextInt();
            for (int i = 0; i < DuchaInfo.duchas.size(); i++) {
                DuchaInfo.duchas.get(i).CalcCost();
            }
        } catch (InputMismatchException e) {
            System.out.println(RED+"ERROR, el estrato tiene que estar entre el rango 1-6");
            UpdateEstrato();
        }
    }
}