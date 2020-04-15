
package Processing;

public class DuchaInfo {    
    private String Fecha;
    private double Gasto;
    private double Tiempo;
    private int Costo;

    public DuchaInfo(String Fecha, double Gasto, double Tiempo) {
        this.Fecha = Fecha;
        this.Gasto = Gasto;
        this.Tiempo = Tiempo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getGasto() {
        return Gasto;
    }

    public void setGasto(double Gasto) {
        this.Gasto = Gasto;
    }

    public double getTiempo() {
        return Tiempo;
    }

    public void setTiempo(double Tiempo) {
        this.Tiempo = Tiempo;
    }
    
    public int getCosto(){
        return this.Costo;
    }
    
    public void setCosto(int Costo) {
        this.Costo = Costo;
    } 
}
