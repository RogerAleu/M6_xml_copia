import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class dades_enviaments {
	
	private String data_entrega;
	private String dni_treballador;
	private String id_pack;
	private String dni_client;
	private String nom;
	private String cognoms;
	private String direccio;
	private String telefon;
	private String latitude;
	private String longitude;
	
	/*CONNEXIO BASE DE DADES*/
    Connection conexio = conexio_bd.getConnection();

    /*Array on guardem les dades dels enviaments*/
    dades_enviaments[] enviaments = new dades_enviaments[100];
    dades_enviaments[] dades_envios = new dades_enviaments[100];
    dades_enviaments[] envio = new dades_enviaments[100];
    
    /*Constructor amb dades entrada*/
	public dades_enviaments (String data_entrega, String dni_treballador, String id_pack, String dni_client, String nom, String cognoms, String direccio, String telefon, String latitude, String longitude) {
	        this.data_entrega = data_entrega;
	        this.dni_treballador = dni_treballador;
	        this.id_pack = id_pack;
	        this.dni_client = dni_client;
	        this.nom = nom;
	        this.cognoms = cognoms;
	        this.direccio = direccio;
	        this.telefon = telefon;
	        this.latitude = latitude;
	        this.longitude = longitude;
	}
	/*Constructor taula enviaments*/
	public dades_enviaments(String data_entrega, String dni_treballador, String id_pack) {
		this.data_entrega = data_entrega;
        this.dni_treballador = dni_treballador;
        this.id_pack = id_pack;
	}
	/*Constructor sense dades entrada*/
	public dades_enviaments() {
		
	}
	/*Getters and Setters*/
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
	/*Metode on guardem els enviaments*/
	public dades_enviaments[] enviament (Connection conexio) {
		
		String data_entrega;
		String dni_treballador;
		String id_pack;
		
		int i = 0;

		try {
			/* CONSULTA */
			String consulta = "SELECT * FROM public.enviaments WHERE data_entrega ='"+LocalDate.now()+"'";
			/* CREEM SENTENCIA */
			Statement sentencia = conexio.createStatement();
			/* EXECUTA CONSULTA */
			ResultSet resultat = sentencia.executeQuery(consulta);

			while (resultat.next()) {
				data_entrega = resultat.getString("data_entrega");
				dni_treballador = resultat.getString("dni");
				id_pack = resultat.getString("id_pack");

				enviaments[i] = new dades_enviaments (data_entrega, dni_treballador, id_pack);
				
				i++;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return enviaments;
	}
	
	/*Metode on guardem els enviaments amb dades complertes*/
	public dades_enviaments[] envios(Connection conexio) {
		
		dades_envios = enviament(conexio);
		
		int i = 0;
		
		for ( int cont = 0; cont<dades_envios.length; cont++) {
		
			if(dades_envios[cont]!=null) {
				String data_entrega;
				String dni_treballador;
				String id_pack;
				String dni_client;
				String nom;
				String cognoms;
				String direccio;
				String telefon;
				String latitude;
				String longitude;
		
				try {
					/* CONSULTA */
					String consulta = "SELECT public.enviaments.data_entrega, public.enviaments.dni,"
									+ " public.enviaments.id_pack, public.enviaments.latitude, public.enviaments.longitude, public.pedidos.dni_client, public.pedidos.nom,"
									+ " public.pedidos.cognoms, public.pedidos.direccio, public.pedidos.telefon"
									+ " FROM public.enviaments, public.pedidos WHERE public.enviaments.id_pack ="
									+ " '"+dades_envios[cont].getId_pack()+"' and public.enviaments.id_pack = public.pedidos.id_pack";
					
					/* CREEM SENTENCIA */
					Statement sentencia = conexio.createStatement();
					/* EXECUTA CONSULTA */
					ResultSet resultat = sentencia.executeQuery(consulta);
		
					resultat.next();
						data_entrega = resultat.getString("data_entrega");
						dni_treballador = resultat.getString("dni");
						id_pack = resultat.getString("id_pack");
						dni_client = resultat.getString("dni_client");
						nom = resultat.getString("nom");
						cognoms = resultat.getString("cognoms");
						direccio = resultat.getString("direccio");
						telefon = resultat.getString("telefon");
						latitude = resultat.getString("latitude");
						longitude = resultat.getString("longitude");
						
						String pack = id_pack.substring(1, (id_pack.length()-1));
						String nombre = nom.substring(1, (nom.length()-1));
						String cognom = cognoms.substring(2, (cognoms.length()-2));
						String direcci = direccio.substring(2, (direccio.length()-2));
						String lati = latitude.substring(1, (latitude.length()-1));
						String longi = longitude.substring(1, (longitude.length()-1));
	
						envio[i] = new dades_enviaments( data_entrega, dni_treballador, pack, dni_client, nombre, cognom, direcci, telefon, lati, longi);
		
						i++;
		
				} catch (SQLException ex) {
					ex.printStackTrace();
				}				
			}
		}		
		return envio;
	}
}