
package Conection;

import static Processing.DuchaInfo.duchas;
import static Additionals.Colors.*;
import Processing.DuchaInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {
    
    public static String leerPaginaWeb() {
        String code = "";
        try {
            StringBuffer codeBuffered = new StringBuffer();

            URL url = new URL("https://thingspeak.com/channels/1004093/feed.json");
            InputStream in = url.openStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = read.readLine()) != null) {
                codeBuffered.append(line).append("\n");
            }

            code = codeBuffered.toString(); // Este es el código de la página

            //cerramos los streams
            in.close();
            read.close();
        } catch (UnknownHostException e) {
            System.out.println(RED+"ERROR, no me pude conectar al servidor");
        } catch (IOException e) {
            System.out.println(RED+"ERROR, no me pude conectar al servidor");
        }
        return code;
    }

    public static void recibirParametros() {
        try {
            JSONObject obj = new JSONObject(leerPaginaWeb());
            JSONArray arr = obj.getJSONArray("feeds");            
            
            for (int i = 0; i < arr.length(); i++) {
                String fecha = arr.getJSONObject(i).getString("created_at").substring(0,10);
                double gasto = arr.getJSONObject(i).getDouble("field1");
                double tiempo = arr.getJSONObject(i).getDouble("field2");
                
                duchas.add(new DuchaInfo(fecha, gasto, tiempo));
            }
         } catch (JSONException e) {
             System.out.println(RED+"ERROR, No pude acceder a toda la informacion");
         }
    }    
}
