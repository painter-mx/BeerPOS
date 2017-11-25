
package Products;

import Administration.User;
import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Product {
    String Nombre,Descripcion;
    int precio,id;
    
    public Product(){
        Nombre="";
        Descripcion="";
        precio=0;
    }
    public DefaultTableModel showProduct(DefaultTableModel Modelo)
    {       
        //Modelo = new DefaultTableModel();
        Modelo.setRowCount(0);
        try {
                
            String sql = "SELECT idProduct,name, description, price FROM products ORDER BY created_at DESC;";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
              
            Object [] Renglones = new Object[Modelo.getColumnCount()];
              
            while(Resultado.next())
            {
                Renglones[0] = Resultado.getInt("idProduct");
                Renglones[1] = Resultado.getString("name");
                Renglones[2] = Resultado.getString("description");
                Renglones[3] = Resultado.getInt("price");
            
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
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
     public boolean InsertProduct()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO product(name, description, price)"+
                                  "VALUES('"+Nombre+"','"+Descripcion+"',MD5('"+precio+"'))");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public boolean DeleteProduct(int id)
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("DELETE FROM products WHERE idProduct = "+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public boolean UpdateProduct()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("UPDATE products SET name = '"+Nombre+"', description = '"+Descripcion+"', price = '"+precio+"' WHERE idUser = '"+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response; 
    }
}
