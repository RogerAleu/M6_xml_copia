	import java.sql.*;

public class conexio_bd {
	    
	// Atributs de la connexió
	private static Connection conn = null;
	private String driver;
	private String url;
	private String usuario;
	private String password;

	// Constructor de la connexió per accedir als mètodes
	public conexio_bd() {

		this.url = "jdbc:postgresql://192.168.138.139:5432/Projecte1";
		this.driver = "org.postgresql.Driver";
		this.usuario = "odoo";
		this.password = "odoo";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	// Mètode per realitzar la connexió
	public static Connection getConnection() {

		if (conn == null) {
			new conexio_bd();
		}

		return conn;
	} // Fin getConnection
}
