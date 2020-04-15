
package Conection;

import Processing.DuchaInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static ArrayList<DuchaInfo> recibirParametros() {
        ArrayList<DuchaInfo> info = new ArrayList<DuchaInfo>();
        try {
            JSONObject obj = new JSONObject(leerPaginaWeb());
            JSONArray arr = obj.getJSONArray("feeds");            
            
            for (int i = 0; i < arr.length(); i++) {
                String fecha = arr.getJSONObject(i).getString("created_at").substring(0,10);
                double gasto = arr.getJSONObject(i).getDouble("field1");
                double tiempo = arr.getJSONObject(i).getDouble("field2");
                
                info.add(new DuchaInfo(fecha, gasto, tiempo));
            }
         } catch (JSONException e) {
             System.out.println("No pude acceder a esta informacion");
         }
            return info;
    }    
}
