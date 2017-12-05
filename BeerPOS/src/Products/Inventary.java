/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;

import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai
 */
public class Inventary {
    
    
    
    Inventary()
    {
        
    }
    
    public DefaultTableModel ShowInventary(DefaultTableModel Modelo)
    {       
        //Modelo = new DefaultTableModel();
        Modelo.setRowCount(0);
        try {
                
            String sql = "SELECT p.idProduct, p.name, p.price, i.stock, i.minimum_stock, (i.stock * p.price) as total "
                    + "FROM Products p "
                    + "JOIN Inventary i ON i.product_id = p.IdProduct "
                    + "ORDER BY p.created_at DESC;";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
              
            Object [] Renglones = new Object[Modelo.getColumnCount()];
              
            while(Resultado.next())
            {
                Renglones[0] = Resultado.getInt("p.idProduct");
                Renglones[1] = Resultado.getString("p.name");
                Renglones[2] = Resultado.getInt("i.stock");
                Renglones[3] = Resultado.getDouble("p.price");          
                Renglones[4] = Resultado.getDouble("total");
                Modelo.addRow(Renglones);
            }
              
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Modelo;
            }
        return Modelo;
    }
}
