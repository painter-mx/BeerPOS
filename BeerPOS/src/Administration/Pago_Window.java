
package Administration;

import Connection.MySQL_Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pago_Window {

private String Nombre;
private String Domicilio;
private int telefono;

public Pago_Window(){
    
    Nombre="";
    Domicilio="";
    telefono=0;
            
}
    
public boolean Insertar(){
      boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO customers (name, address, phone)"+
                                  "VALUES('"+Nombre+"','"+Domicilio+"','"+telefono+"')");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
}

public boolean Tele(int a){
    try{ 
        int b=a;
   
    return true;
    }catch(NumberFormatException ex){
    
     
        return false;
    }
}
        
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void Mpago(String n ){
       try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO payment_methods (name, address, phone)"+
                                  "VALUES('"+Nombre+"','"+Domicilio+"','"+telefono+"')");         
        } catch (ClassNotFoundException | SQLException ex) {
           
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
