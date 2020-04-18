
package Processing;

import static Additionals.Colors.*;
import java.text.DecimalFormat;

public class Averages {
    public static void GeneralAverage(){
        int lenght = DuchaInfo.duchas.size();
        DecimalFormat format1 = new DecimalFormat("#.00");
        if (lenght>0) {
            double average[] = new double[3];
            for (int i = 0; i < lenght; i++) {
                average[0] += DuchaInfo.duchas.get(i).getGasto();
                average[1] += DuchaInfo.duchas.get(i).getTiempo();
                average[2] += DuchaInfo.duchas.get(i).getCosto();
            }
            average[0]/=lenght;
            average[1]/=lenght;
            average[2]/=lenght;
            System.out.println(CYAN+"|***** "+GREEN+"Promedios Generales "+CYAN+"*****|\n"+
                               CYAN+"| "+RESET+"Gasto(L): "+format1.format(average[0])+"L\n"+
                               CYAN+"| "+RESET+"Tiempo(m): "+format1.format(average[1])+"m\n"+
                               CYAN+"| "+RESET+"Costo: "+format1.format(average[2])+"$\n"+
                               CYAN+"|*********************************|"+RESET);
        }else{
            System.out.println(RED+"No hay datos para promediar");
        }
    }
}
