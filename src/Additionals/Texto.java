package Additionals;


public class Texto {

    public static String WINDOW_TITLE;
    
    public static String BTN1;
    public static String BTN2;
    public static String BTN3;
    public static String BTN4;
    public static String BTN5;
    public static String BTN6;
    public static String BTN7;
    public static String BTN8;
    public static String BTN9;
    public static String AV1;
    public static String AV2;
    public static String AV3;
    public static String AV4;
    public static String AV5;
    public static String AV6;
    public static String DATE1;
    public static String DATE2;
    public static String DATE3;
    public static String DATE4;
    
    
    
    
    public static void language() {
        String len = System.getProperty("user.language");
        if(len.equalsIgnoreCase("es")){
        
            WINDOW_TITLE="SCD";
            BTN1="Asignar estrato";
            BTN2="Mostrar datos";
            BTN3="Cargar Persistencia";
            BTN4="Ingresar a página";
            BTN5="Salir";
            BTN6="Guardar";
            BTN7="Actualizar";
            BTN8="Atrás";
            BTN9="Promedios";
            AV1="Costo no calculado, actualice su estrato";
            AV2="Persistencia cargada con éxito";
            AV3="No hay datos para promediar";
            AV4="Error, no pude conectarme al servidor";
            AV5="ERROR, No pude acceder a toda la información";
            AV6="ERROR, persistencia no pudo ser generada";
            DATE1="Fecha";
            DATE2="Gasto";
            DATE3="Tiempo";
            DATE4="Costo";
        }else{
             WINDOW_TITLE="SCS";//Shower Control System
            BTN1="Assign stratum";
            BTN2="Show data";
            BTN3="Load persistence";
            BTN4="Enter page";
            BTN5="Exit";
            BTN6="Save";
            BTN7="Update";
            BTN8="Back";
            BTN9="Averages";
            AV1="Cost not calculated, update your stratum";
            AV2= "Persistence loaded successfully"; 
            AV3="There is no data to average";
            AV4="Error, I could not connect to the server";
            AV5="ERROR, I could not access all the information";
            AV6="ERROR, persistence could not be generated";
            DATE1="Date";
            DATE2="Expenditure";
            DATE3="Time";
            DATE4="Cost";
        }
        
        
 
    }
}
