package seguretat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexio_bdades {

	// Atributs de la connexió
		private static Connection conn = null;
		private String driver;
		private String url;
		private String usuario;
		private String password;

		// Constructor de la connexió per accedir als mètodes
		public conexio_bdades() {

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
				new conexio_bdades();
			}

			return conn;
		} // Fin getConnection
	}
