import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

public class convertir_bd {
	/*Conexio al servidor*/
	static ChannelSftp conn = Servidor.conectarse();

	/*CONNEXIO BASE DE DADES*/
    static Connection conexio = conexio_bd.getConnection();
    
    /*Crida constructor classe dades_enviaments*/
    static dades_enviaments dades = new dades_enviaments();
    
    /*Array on guardem les dades dels enviaments*/
    static dades_enviaments[] enviaments = new dades_enviaments[100];
	
	public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SftpException {
				
		File carpeta_envios = new File ("Enviaments");
		File carpeta_fitxers = new File (carpeta_envios+"\\Envios_"+LocalDate.now());
		if (!carpeta_envios.exists()){
			if(carpeta_envios.mkdir()) {
				carpeta_fitxers.mkdir();
				
				conn.cd("/home/roger/enviaments");
				conn.mkdir("envios_"+LocalDate.now()+"");
				
				enviaments = dades.envios(conexio);
				conn.cd("/home/roger/enviaments/envios_"+LocalDate.now());
				
				for(int cont=0; cont<enviaments.length; cont++) {
					if(enviaments[cont]!=null) {
						/*Creem document que tindra estrucutra xml*/
						DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); 
						DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
						Document doc = docBuilder.newDocument();
						
						/*Afegir informacio al document*/
						/*Creem element arrel*/
						Element elementArrel = doc.createElement("envio");
						doc.appendChild(elementArrel);
						/*Creem el element treballador*/
						Element treballador = doc.createElement("informacio");
						elementArrel.appendChild(treballador);
						/*Creem la informacio del envio*/
						Element id_pack = doc.createElement("id_pack");
						id_pack.appendChild(doc.createTextNode(enviaments[cont].getId_pack()));
						treballador.appendChild(id_pack);
						
						Element nom_client = doc.createElement("nom_client");
						nom_client.appendChild(doc.createTextNode(enviaments[cont].getNom()));
						treballador.appendChild(nom_client);
						
						Element cognoms_client = doc.createElement("cognoms_client");
						cognoms_client.appendChild(doc.createTextNode(enviaments[cont].getCognoms()));
						treballador.appendChild(cognoms_client);
						
						Element direccio_client = doc.createElement("direccio_client");
						direccio_client.appendChild(doc.createTextNode(enviaments[cont].getDireccio()));
						treballador.appendChild(direccio_client);
						
						Element telefon_client = doc.createElement("telefon_client");
						telefon_client.appendChild(doc.createTextNode(enviaments[cont].getTelefon()));
						treballador.appendChild(telefon_client);
						
						Element latitude = doc.createElement("latitude");
						latitude.appendChild(doc.createTextNode(enviaments[cont].getLatitude()));
						treballador.appendChild(latitude);
						
						Element longitude = doc.createElement("longitude");
						longitude.appendChild(doc.createTextNode(enviaments[cont].getLongitude()));
						treballador.appendChild(longitude);
						
						/*Escriure el contingut del document a un fitxer xml*/
						TransformerFactory tFactory = TransformerFactory.newInstance();
						Transformer transformer = tFactory.newTransformer();
						/*el transformer necessita un origen i un desti*/
						DOMSource source = new DOMSource(doc);
						StreamResult resultat = new StreamResult(carpeta_fitxers+"\\envio_"+enviaments[cont].getId_pack()+".xml");
						transformer.transform(source, resultat);
						
						conn.put("Enviaments\\Envios_"+LocalDate.now()+"\\envio_"+enviaments[cont].getId_pack()+".xml", "envio_"+enviaments[cont].getId_pack()+".xml");
					}
				}
				System.out.println("");
		        System.out.println("XML d'enviaments pujats al servidor correctament.");
		        conn.exit();
		        conn.disconnect();
			}
		}else {
			if(!carpeta_fitxers.exists()) {
				carpeta_fitxers.mkdir();
				
				conn.cd("/home/roger/enviaments");
				conn.mkdir("envios_"+LocalDate.now());
				conn.cd("/home/roger/enviaments/envios_"+LocalDate.now());
				
				enviaments = dades.envios(conexio);
				
				for(int cont=0; cont<enviaments.length; cont++) {
					if(enviaments[cont]!=null) {
						/*Creem document que tindra estrucutra xml*/
						DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); 
						DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
						Document doc = docBuilder.newDocument();
						
						/*Afegir informacio al document*/
						/*Creem element arrel*/
						Element elementArrel = doc.createElement("envio");
						doc.appendChild(elementArrel);
						/*Creem el element treballador*/
						Element treballador = doc.createElement("informacio");
						elementArrel.appendChild(treballador);
						/*Creem la informacio del envio*/
						Element id_pack = doc.createElement("id_pack");
						id_pack.appendChild(doc.createTextNode(enviaments[cont].getId_pack()));
						treballador.appendChild(id_pack);
						
						Element nom_client = doc.createElement("nom_client");
						nom_client.appendChild(doc.createTextNode(enviaments[cont].getNom()));
						treballador.appendChild(nom_client);
						
						Element cognoms_client = doc.createElement("cognoms_client");
						cognoms_client.appendChild(doc.createTextNode(enviaments[cont].getCognoms()));
						treballador.appendChild(cognoms_client);
						
						Element direccio_client = doc.createElement("direccio_client");
						direccio_client.appendChild(doc.createTextNode(enviaments[cont].getDireccio()));
						treballador.appendChild(direccio_client);
						
						Element telefon_client = doc.createElement("telefon_client");
						telefon_client.appendChild(doc.createTextNode(enviaments[cont].getTelefon()));
						treballador.appendChild(telefon_client);
						
						Element latitude = doc.createElement("latitude");
						latitude.appendChild(doc.createTextNode(enviaments[cont].getLatitude()));
						treballador.appendChild(latitude);
						
						Element longitude = doc.createElement("longitude");
						longitude.appendChild(doc.createTextNode(enviaments[cont].getLongitude()));
						treballador.appendChild(longitude);
						
						/*Escriure el contingut del document a un fitxer xml*/
						TransformerFactory tFactory = TransformerFactory.newInstance();
						Transformer transformer = tFactory.newTransformer();
						/*el transformer necessita un origen i un desti*/
						DOMSource source = new DOMSource(doc);
						StreamResult resultat = new StreamResult(carpeta_fitxers+"\\envio_"+enviaments[cont].getId_pack()+".xml");
						transformer.transform(source, resultat);
						
						conn.put("Enviaments\\Envios_"+LocalDate.now()+"\\envio_"+enviaments[cont].getId_pack()+".xml", "envio_"+enviaments[cont].getId_pack()+".xml");
					}
				}
				System.out.println("");
		        System.out.println("XML d'enviaments pujats al servidor correctament.");
		        conn.exit();
		        conn.disconnect();
			}else {
				System.out.println("");
				System.out.println("Aquests enviaments ja estan guardats al Servidor en els arxius XML.");		
			}
		}
        System.exit(0);
	}
}
