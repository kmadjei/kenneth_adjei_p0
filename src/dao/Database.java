package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String url = "jdbc:postgresql://localhost/bankApp";
    private static final String user = "postgres";
    private static final String password = "password";
    private static final String driver = "org.postgresql.Driver";
    
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
