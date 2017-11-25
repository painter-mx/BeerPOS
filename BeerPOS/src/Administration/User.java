/*
 * Description  :   This class contains User attributes
 * Author       :   Víctor Isaí Santana Machuca
 * Date         :   Nov 24, 2017.
 */
package Administration;
import Connection.MySQL_Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User {
    
    private String role;
    private String user_name;
    private String password;
    private int id;
    //public DefaultTableModel Modelo;
    
    public User()
    {
        role = "";
        user_name = "";
        password = "";
       
    }
    
    public DefaultTableModel showUsers(DefaultTableModel Modelo)
    {       
        //Modelo = new DefaultTableModel();
        Modelo.setRowCount(0);
        try {
                
            String sql = "SELECT idUser, user_name, password, role FROM Users ORDER BY created_at DESC;";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
              
            Object [] Renglones = new Object[Modelo.getColumnCount()];
              
            while(Resultado.next())
            {
                Renglones[0] = Resultado.getInt("idUser");
                Renglones[1] = Resultado.getString("user_name");
                Renglones[2] = Resultado.getString("password");
                Renglones[3] = Resultado.getString("role");
                Modelo.addRow(Renglones);
            }
              
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Modelo;
            }
        return Modelo;
    }
    public boolean InsertUser()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO Users(role, user_name, password)"+
                                  "VALUES('"+role+"','"+user_name+"',MD5('"+password+"'))");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public boolean DeleteUser(int id)
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("DELETE FROM Users WHERE idUser = "+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public boolean UpdateUser()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("UPDATE Users SET role = '"+role+"', user_name = '"+user_name+"', password = MD5('"+password+"') WHERE idUser = "+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response; 
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
