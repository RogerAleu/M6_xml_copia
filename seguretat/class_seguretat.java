package seguretat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class class_seguretat {
	
	private String data_entrega;
	private String dni_treballador;
	private String id_pack;
	private String dni_client;
	private String nom;
	private String cognoms;
	private String direccio;
	private String telefon;
	private String password;
	private int id;
	private String latitude;
	private String longitude;
	
	/*CONNEXIO BASE DE DADES*/
    Connection conexio = conexio_bdades.getConnection();
    
    class_seguretat[] copia_seguretat = new class_seguretat[100];
    
    public class_seguretat() {
    }
	
	/*Constructor taula enviaments*/
	public class_seguretat(int id, String data_entrega, String dni_treballador, String id_pack, String latitude, String longitude) {
		this.id = id;
		this.data_entrega = data_entrega;
        this.dni_treballador = dni_treballador;
        this.id_pack = id_pack;
        this.latitude = latitude;
        this.longitude = longitude;
	}
    
    /*Constructor dades treballadors*/
	public class_seguretat (String dni_treballador, String nom, String cognoms, String telefon, String password) {
	        this.dni_treballador = dni_treballador;
	        this.nom = nom;
	        this.cognoms = cognoms;
	        this.telefon = telefon;
	        this.password = password;
	}
	
	/*Constructor amb dades dels pedidos*/
	public class_seguretat (String id_pack, String dni_client, String nom, String cognoms, String direccio, String telefon) {
		    this.id_pack = id_pack;
		    this.dni_client = dni_client;
		    this.nom = nom;
		    this.cognoms = cognoms;
		    this.direccio = direccio;
		    this.telefon = telefon;
	}
		
	public String getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(String data_entrega) {
		this.data_entrega = data_entrega;
	}

	public String getDni_treballador() {
		return dni_treballador;
	}

	public void setDni_treballador(String dni_treballador) {
		this.dni_treballador = dni_treballador;
	}

	public String getId_pack() {
		return id_pack;
	}

	public void setId_pack(String id_pack) {
		this.id_pack = id_pack;
	}

	public String getDni_client() {
		return dni_client;
	}

	public void setDni_client(String dni_client) {
		this.dni_client = dni_client;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public String getDireccio() {
		return direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Connection getConexio() {
		return conexio;
	}

	public void setConexio(Connection conexio) {
		this.conexio = conexio;
	}

	public class_seguretat[] getCopia_seguretat() {
		return copia_seguretat;
	}

	public void setCopia_seguretat(class_seguretat[] copia_seguretat) {
		this.copia_seguretat = copia_seguretat;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

		/*Metode on guardem les dades dels treballadors*/
		public class_seguretat[] treballadors_copia (Connection conexio) {
			
			String treballador_dni;
			String treballador_nom;
			String treballador_cognoms;
			String treballador_telefon;
			String treballador_password;

			try {
				/* CONSULTA */
				String consulta = "SELECT * FROM public.treballadors";
				/* CREEM SENTENCIA */
				Statement sentencia = conexio.createStatement();
				/* EXECUTA CONSULTA */
				ResultSet resultat = sentencia.executeQuery(consulta);
				
				int i = 0;

				while (resultat.next()) {
					treballador_dni = resultat.getString("dni");
					treballador_nom = resultat.getString("nom");
					treballador_cognoms = resultat.getString("cognoms");
					treballador_telefon = resultat.getString("telefon");
					treballador_password = resultat.getString("password");
					
					String tre_dni = treballador_dni.substring(1, (treballador_dni.length()-1));
					String tre_nom = treballador_nom.substring(1, (treballador_nom.length()-1));
					String tre_cognoms = treballador_cognoms.substring(2, (treballador_cognoms.length()-2));
					String tre_tel = treballador_telefon.substring(1, (treballador_telefon.length()-1));
					String tre_password = treballador_password.substring(1, (treballador_password.length()-1));

					copia_seguretat[i] = new class_seguretat (tre_dni, tre_nom, tre_cognoms, tre_tel, tre_password);
					
					i++;
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return copia_seguretat;
		}
		
		/*Metode on guardem les dades dels pedidos*/
		public class_seguretat[] pedidos_copia (Connection conexio) {
			
			String id_pack;
			String dni_client;
			String nom;
			String cognoms;
			String direccio;
			String telefon;

			try {
				/* CONSULTA */
				String consulta = "SELECT * FROM public.pedidos";
				/* CREEM SENTENCIA */
				Statement sentencia = conexio.createStatement();
				/* EXECUTA CONSULTA */
				ResultSet resultat = sentencia.executeQuery(consulta);
				
				int i = 0;

				while (resultat.next()) {
					id_pack = resultat.getString("id_pack");
					dni_client = resultat.getString("dni_client");
					nom = resultat.getString("nom");
					cognoms = resultat.getString("cognoms");
					direccio = resultat.getString("direccio");
					telefon = resultat.getString("telefon");
					
					String pack = id_pack.substring(1, (id_pack.length()-1));
					String cli_dni = dni_client.substring(1, (dni_client.length()-1));
					String nombre = nom.substring(1, (nom.length()-1));
					String cog = cognoms.substring(2, (cognoms.length()-2));
					String dir = direccio.substring(2, (direccio.length()-2));

					copia_seguretat[i] = new class_seguretat (pack, cli_dni, nombre, cog, dir, telefon);
					
					i++;
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return copia_seguretat;
		}
		
		/*Metode on guardem les dades de enviaments*/
		public class_seguretat[] enviaments_copia (Connection conexio) {
			
			int id;
			String data_entrega;
			String dni_treballador;
			String id_pack;
			String latitude;
			String longitude;

			try {
				/* CONSULTA */
				String consulta = "SELECT * FROM public.enviaments";
				/* CREEM SENTENCIA */
				Statement sentencia = conexio.createStatement();
				/* EXECUTA CONSULTA */
				ResultSet resultat = sentencia.executeQuery(consulta);
				
				int i = 0;

				while (resultat.next()) {
					id = resultat.getInt("id");
					data_entrega = resultat.getString("data_entrega");
					dni_treballador = resultat.getString("dni");
					id_pack = resultat.getString("id_pack");
					latitude = resultat.getString("latitude");
					longitude = resultat.getString("longitude");
					
					String tre_dni = dni_treballador.substring(1, (dni_treballador.length()-1));
					String pack = id_pack.substring(1, (id_pack.length()-1));
					String lati = latitude.substring(1, (latitude.length()-1));
					String longi = longitude.substring(1, (longitude.length()-1));

					copia_seguretat[i] = new class_seguretat (id, data_entrega, tre_dni, pack, lati, longi);
					
					i++;
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return copia_seguretat;
		}
}
