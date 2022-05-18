package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String url = 
			"jdbc:postgresql://localhost/bankApp"
			//jdbc:postgresql://revature-p0.cyexqwl9xulr.ca-central-1.rds.amazonaws.com";
			;
    private static final String user = "postgres";
    private static final String password = "password";
    
    private static Database instance = new Database(); //load single instance
    
    // private construct allows for Database to have one instance
    private Database() {} 
    
    public static Database getInstance() {
    	return instance;
    }
    
    public static Connection conn;
    
    public Connection connect() throws SQLException{
    	 
    	conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    
    
    

}
