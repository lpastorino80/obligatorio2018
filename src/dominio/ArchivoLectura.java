/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;
import java.io.*;

public class ArchivoLectura {
    private String linea;
    private BufferedReader in;

    public String linea() {
        return linea;
    }
    
    public ArchivoLectura(String nombre) throws FileNotFoundException, Exception {
        try {
            in = new BufferedReader(new FileReader(nombre));
            
        }
        catch (FileNotFoundException e) {
            throw new Exception("El archivo no existe.");
        }
    }
    
    public boolean hayMasLineas () {
        try {
            linea = in.readLine();
        }
        catch (IOException e) {
            linea = null;
        }
        return (linea != null);
    }
    
    public boolean cerrar () throws Exception {
        boolean ok = true;
        try {
            in.close();
        }
        catch (Exception e) {
            throw new Exception("Error al cerrar el archivo.");            
        }
        return ok;        
    }
}