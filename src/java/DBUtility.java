import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
    public static final String url = "jdbc:derby://localhost:1527/dummy";
    public static final String username = "app";
    public static final String password = "app";
    private static Connection connection ;
        
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	// System.out.println("Driver registered!!");
	connection = DriverManager.getConnection(url, username, password);
	// System.out.println("Connection established!!");
	return connection;
    }
    
    public static void getDBConnectionClose() throws SQLException {
	connection.close();
    }    
}
