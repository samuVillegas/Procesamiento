
package Processing;

import static Additionals.Colors.*;
import static Processing.DuchaInfo.duchas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Persistencia {
    
    public static String setUrl(){
        String system = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        String url = "";
        if (system.startsWith("Linux")) {
                url = "/home/"+user+"/Documents/Persistencia.txt";
        }else if (system.startsWith("Windows")){
            url = "/Users/"+user+"/Documents/Persistencia.txt";
        }
        return url;
    }
    
    public static void WriteFile(){
        try {
            int lenght=0;
            File file = new File(setUrl());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter write = new FileWriter(file,true);
            FileReader reader = new FileReader(file);
            
            BufferedWriter bw = new BufferedWriter(write);
            BufferedReader bf = new BufferedReader(reader);
            while ((bf.readLine())!=null) {
                lenght++;
            }
            
            for (int i = lenght; i < DuchaInfo.duchas.size(); i++) {
                bw.append(DuchaInfo.duchas.get(i).getFecha()+" "+
                         DuchaInfo.duchas.get(i).getGasto()+" "+
                         DuchaInfo.duchas.get(i).getTiempo()+"\r\n");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(RED+"ERROR, persistencia no pudo ser generada");
            System.out.println(e);
        }
    }
    
    public static void LoadFile(){
        File file = new File(setUrl());
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bf = new BufferedReader(reader);
            String l;
            while ((l = bf.readLine())!=null) {
                String line[] = l.split(" ");
                String fecha = line[0];
                double gasto = Double.parseDouble(line[1]);
                double tiempo = Double.parseDouble(line[2]);
                
                duchas.add(new DuchaInfo(fecha, gasto, tiempo));
            }
            System.out.println(GREEN+"Persistencia cargada con exito");
        } catch (Exception e) {
            System.out.println(RED+"ERROR, persistencia no pudo ser cargada");
        }
    }
}
