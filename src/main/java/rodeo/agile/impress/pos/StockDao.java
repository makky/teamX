package rodeo.agile.impress.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDao {
	
	String dbPath;
	
	public StockDao(String path) {
		this.dbPath = path;
	}

	public void insert(String name, int price) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO items (name, price) VALUES ('" + name + "', " + price + ");");
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}
