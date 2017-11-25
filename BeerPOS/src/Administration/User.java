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
    public User()
    {
        role = "";
        user_name = "";
        password = "";
       
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
            Estancia.execute("DELETE FROM Users"+
                              "WHERE id ='"+id+"'");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
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
