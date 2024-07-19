package config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnect {
	
	private Properties p = null;

	public DatabaseConnect() {
		try {
			if(p == null) {
				p = new Properties();
				p.load(new FileInputStream("src/config/jdbc.properties"));
			}
			
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
