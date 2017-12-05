/*
 *  Description : This class contains log-in functionality
 *  Author      : Víctor Isaí Santana Machuca.
 *  Date        : Nov 26, 2017.
 */
package beerpos;

import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author isai
 */
public class Login {
    
    
    public Login()
    {

    }
    
    public String Access(String user_name, String password)
    {
        String response = "";
            
        try {
                
            String sql = "SELECT role FROM Users WHERE user_name = '"+ user_name +"' AND password = MD5('"+password+"') LIMIT 1";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
            System.out.println(sql);
            while(Resultado.next())
            {
                response = Resultado.getString("role");
            }
                      
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return response;
            }
        return  response;
    }  
}
