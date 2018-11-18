/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoGrabacion {

    private BufferedWriter out;
    
    public ArchivoGrabacion (String nombre) throws IOException {
        try {
            out = new BufferedWriter(new FileWriter(nombre));
        }
        catch (IOException e) {
            throw new IOException("No se pudo crear el archivo.");
        }
    }
    
    public boolean grabarLinea (String linea) throws IOException {
        boolean ok = true;
        try {
            out.write(linea);
            out.newLine();
            
        }
        catch (IOException e) {
            throw new IOException("Error de escritura");
        }
        return ok;
    }
    
    public boolean cerrar () throws Exception{
        boolean ok = true;
        try {
            out.flush();
            out.close();
        }
        catch (Exception e) {
            throw new Exception("Error al cerrar el archivo.");
        }
        return ok;
    }
}