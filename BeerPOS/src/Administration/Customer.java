
package Administration;

import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Customer {
   private String Nombrec,Direccion;
   private int Telefono;
   
  public Customer(){
      Nombrec="";
   
      Direccion="";
      Telefono=0;
  } 
    public boolean InsertCustomer()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO customers(name, address, phone)"+
                                  "VALUES('"+Nombrec+"','"+Direccion+"','"+Telefono+"')");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
   
   public boolean Validadt(String a){
      int b=0;
       try{
          b=Integer.parseInt(a);
          return true;
      } catch(NumberFormatException ex){
          javax.swing.JOptionPane.showMessageDialog(null,"error con el formato de telefono"+"/n"+"Inserte el numero nuevamente");
          return false;
      }
       
   }

    public String getNombrec() {
        return Nombrec;
    }

    public void setNombrec(String Nombrec) {
        this.Nombrec = Nombrec;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }
   
}
