package config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnect {
	Properties p = new Properties();
	private DatabaseConnect() {
		try {
			p.load(new FileInputStream("src/config/jdbc.properties"));
			
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static DatabaseConnect instance;
	public static DatabaseConnect getInstance() {
		if(instance == null) instance = new DatabaseConnect();
		return instance;
	}
	
	public Properties getProperties() {
		return p;
	}
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}
	
	public void close(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		close(ps, conn);
	}
	
}
