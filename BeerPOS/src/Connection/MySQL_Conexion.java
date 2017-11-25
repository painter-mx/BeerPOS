/*
 * Description : Esta clase realiza la conexion a la base de datos
 * Date        : 24 November 2017
 * Author      : Víctor Isaí Santana Machuca.
 */

package Connection;
import java.sql.*;
public class MySQL_Conexion {
    
    private static String dbName = "BeerPOS",
                          URL = "localhost:3306", 
                          User = "root",
                          Password = "";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Connection Response;
        Class.forName("com.mysql.jdbc.Driver");
        Response = DriverManager.getConnection("jdbc:mysql://" + URL + "/"+ dbName, User, Password);        
        return Response;        
    }
    
}
