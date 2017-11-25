/*
 * Description : this class contains attributes for Employees
 * Author      : Arturo Mejía Fonseca
 * Date        : 25/11/2017
 */
package Administration;

import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Employee {
    
    private int id;
    private String name;
    private String surnames;
    private String address;
    private String phone;
    
    public Employee()
    {
        name = "";
        surnames = "";
        address = "";
        phone = "";
    }
    
    public boolean InsertEmployee()
    {
        boolean response = true;
        try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate("INSERT INTO Employees(name, surnames, address, phone, user_id)"+
                                  "VALUES('"+name+"','"+surnames+"','"+address+"','"+phone+"',1)");         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return response;      
    }
    
    public boolean ShowEmployee()
    {
        boolean response = true;
        
        return response;  
    }
    
    public boolean UpdateEmployee()
    {
        boolean response = true;
        
        return response;
    }
    
    public boolean DeleteEmployee()
    {
        boolean response = true;
        
        return response;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
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
