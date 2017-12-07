
package Administration;

import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Customer {
   private String name,address, phone;
   private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
  public Customer(){
      name = "";
      address = "";
      phone = "";
  } 
    public boolean InsertCustomer()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO Customers(name, address, phone)"+
                                  "VALUES('"+name+"','"+address+"','"+phone+"')");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
   
    public boolean DeleteCustomer(int id)
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("DELETE FROM Customers WHERE idCustomer = "+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    public boolean UpdateCustomer()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("UPDATE Customers SET name = '"+name+"', address = '"+address+"', phone = '"+phone+"' WHERE idCustomer = "+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return response;
    }

        public DefaultTableModel showCustomers(DefaultTableModel Modelo)
    {       
        //Modelo = new DefaultTableModel();
        Modelo.setRowCount(0);
        try {
                
            String sql = "SELECT idCustomer, name, address, phone FROM Customers ORDER BY created_at DESC;";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
              
            Object [] Renglones = new Object[Modelo.getColumnCount()];
              
            while(Resultado.next())
            {
                Renglones[0] = Resultado.getInt("idCustomer");
                Renglones[1] = Resultado.getString("name");
                Renglones[2] = Resultado.getString("address");
                Renglones[3] = Resultado.getString("phone");
                Modelo.addRow(Renglones);
            }
              
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Modelo;
            }
        return Modelo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  
   
}
