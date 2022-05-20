package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Database {
	
	static Dotenv dotenv = Dotenv.load();
	
	private static final String url =
			"jdbc:postgresql://" + dotenv.get("AWS_RDS_URL")
			//"jdbc:postgresql://localhost/bankApp"
			//jdbc:postgresql://revature-p0.cyexqwl9xulr.ca-central-1.rds.amazonaws.com";
			;
    private static final String user = dotenv.get("AWS_DB_USERNAME");
    private static final String password = dotenv.get("AWS_DB_PASSWORD");;
    
    private static Database instance = new Database(); //load single instance
    
    // private constructor allows for Database to have one instance
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
