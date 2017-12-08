/*
 * Description : this class shows all sales and pre-sales
 * Author      : Víctor Isaí Santana Machuca.
 * Date        : 08/12/2017
 */
package Administration;

import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Calendar {
    
    
    public DefaultTableModel showCalendar(DefaultTableModel Modelo)
    {       
        //Modelo = new DefaultTableModel();
        Modelo.setRowCount(0);
        try {
                
            String sql = "SELECT DISTINCT f.status,f.order_number,c.name,s.date_of_delivery,f.total\n" +
                        "FROM Finances f \n" +
                        "JOIN Sales s \n" +
                        "ON f.order_number = s.order_number\n" +
                        "JOIN Customers c\n" +
                        "ON s.customer_id = c.idCustomer\n" +
                        "ORDER BY s.date_of_delivery ASC";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
              
            Object [] Renglones = new Object[Modelo.getColumnCount()];
              
            while(Resultado.next())
            {
                Renglones[0] = Resultado.getString("f.order_number");
                Renglones[1] = Resultado.getString("c.name");
                Renglones[2] = Resultado.getString("s.date_of_delivery");
                Renglones[3] = Resultado.getDouble("f.total");
                Renglones[4] = Resultado.getString("f.status");
                Modelo.addRow(Renglones);
            }
              
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Modelo;
            }
        return Modelo;
    }
    
}
