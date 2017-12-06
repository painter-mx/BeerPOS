/*
 * Description : This class contains whole the functionality for POS system
 * Author      : Víctor Isaí Santana Machuca.
 * Date        : Dec 05, 2017.
 */
package POS;

import Administration.User;
import Connection.MySQL_Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isai
 */
public class Sale {
    
    String customer;
    int quantity;
    String date_of_delivery;
    int employee;
    String product;
    String payment_method;
    String order_number;
    Double total;
    
     public DefaultTableModel showProducts(DefaultTableModel Modelo)
    {       
        //Modelo = new DefaultTableModel();
        Modelo.setRowCount(0);
        try {
           total = 0.0;     
            String sql = "SELECT p.idProduct, s.quantity, p.name, p.price, (s.quantity * p.price) as total\n" +
                        "FROM Products p\n" +
                        "JOIN Sales s\n" +
                        "ON p.idProduct = s.product_id\n" +
                        "WHERE s.order_number = 'Raldoo01';";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
              
            Object [] Renglones = new Object[Modelo.getColumnCount()];
              
            while(Resultado.next())
            {
                Renglones[0] = Resultado.getInt("p.idProduct");
                Renglones[1] = Resultado.getInt("s.quantity");
                Renglones[2] = Resultado.getString("p.name");
                Renglones[3] = Resultado.getDouble("p.price");
                Renglones[4] = Resultado.getDouble("total");
                Modelo.addRow(Renglones);
                total+=Resultado.getDouble("total");
            }
              System.out.println(total);
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Modelo;
            }
        return Modelo;
    }
     
     
    public String[] loadCustomer(String searchWord)
    {
        String [] Values = new String[2];
        int i = 0;
        try {
                
            String sql = "SELECT name FROM Customers WHERE name LIKE '%"+searchWord+"%' ORDER BY name ASC;";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
            System.out.println(sql);
            
            while(Resultado.next())
            {
                
                Values[i] = Resultado.getString("name");
                i++;
            }
              
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Values;
            }
        return Values;
    }
    
    public String[] loadProducts(String searchWord)
    {
        String [] Values = new String[2];
        int i = 0;
        try {
                
            String sql = "SELECT name FROM Products WHERE name LIKE '%"+searchWord+"%' ORDER BY name ASC LIMIT 2;";
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            ResultSet Resultado = Estancia.executeQuery(sql);
            System.out.println(sql);
            
            while(Resultado.next())
            {
                
                Values[i] = Resultado.getString("name");
                i++;
            }
              
            }catch (ClassNotFoundException | SQLException ex) {
               javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar estalecer la conexion "+ ex.getMessage());
               return Values;
            }
        return Values;
    }
    public boolean insertProduct()
    {
        String sql;
        boolean response = true;
        
        try 
        {
            sql = "INSERT INTO Sales(quantity,date_of_delivery, employee_id, customer_id, product_id, payment_method_id, order_number) "
                    + "VALUES ("+quantity+", '"+date_of_delivery+"'," + employee + ",(SELECT idCustomer FROM Customers WHERE name = '" + customer + "' LIMIT 1),(SELECT idProduct FROM Products WHERE name = '" + product + "' LIMIT 1),(SELECT idPayment_method FROM Payment_methods WHERE name = '" + payment_method + "' LIMIT 1),'" + order_number + "');"; 
            System.out.println(sql);
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.executeUpdate(sql);         
            
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    public boolean deleteProduct(int id)
    {
        boolean response = true;
        
                try 
        {
            Connection Conexion = MySQL_Conexion.getConnection();
            Statement Estancia = Conexion.createStatement();
            Estancia.execute("DELETE FROM Sales WHERE product_id = "+id);         
        } catch (ClassNotFoundException | SQLException ex) {
            response = false;
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate_of_delivery() {
        return date_of_delivery;
    }

    public void setDate_of_delivery(String date_of_delivery) {
        this.date_of_delivery = date_of_delivery;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getOrder_number() {
        return order_number;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
    
}
