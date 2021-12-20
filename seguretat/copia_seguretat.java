package seguretat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

public class copia_seguretat {
	
	/*Conexio al servidor*/
	static ChannelSftp conn = Servidor.conectarse();
	
	/*CONNEXIO BASE DE DADES*/
    static Connection conexio = conexio_bdades.getConnection();
    
    /*Crida constructor classe dades_enviaments*/
    static class_seguretat dades = new class_seguretat();
    
    /*Array on guardem les dades dels enviaments*/
    static class_seguretat[] copia = new class_seguretat[100];
    
    static FileWriter write;

	public static void main(String[] args) throws IOException, SftpException {
		
		copia = dades.treballadors_copia(conexio);
		
		File seguretat_copia = new File ("copies_seguritat");
		File carpeta = new File (seguretat_copia+"//copia_seguretat_"+LocalDate.now()+".txt");
		
		if (!seguretat_copia.exists()) {
			if(seguretat_copia.mkdirs()) {
				if(carpeta.createNewFile()) {
					write = new FileWriter(carpeta);
					write.write("*************");
					write.write("\n");
					write.write("TREBALLADORS");
					write.write("\n");
					write.write("*************");
					write.write("\n\n");
					write.write("------------------------------------");
					write.write("\n");
					
					conn.cd("/home/roger/copies_seguritat");
					
					for(int cont=0; cont<copia.length; cont++) {
						if (copia[cont]!=null){
							write.write("DNI: "+copia[cont].getDni_treballador());
							write.write("\n");
							write.write("NOM: "+copia[cont].getNom());
							write.write("\n");
							write.write("COGNOMS: "+copia[cont].getCognoms());
							write.write("\n");
							write.write("TELEFON: "+copia[cont].getTelefon());
							write.write("\n");
							write.write("PASSWORD: "+copia[cont].getPassword());
							write.write("\n");
							write.write("------------------------------------");
							write.write("\n");
						}
					}
					
					copia = dades.pedidos_copia(conexio);
					write.write("\n\n");
					write.write("*******************");
					write.write("\n");
					write.write("PEDIDOS REALITZATS");
					write.write("\n");
					write.write("*******************");
					write.write("\n\n");
					write.write("------------------------------------");
					write.write("\n");
					for(int cont=0; cont<copia.length; cont++) {
						if (copia[cont]!=null){
							write.write("ID_PACK: "+copia[cont].getId_pack());
							write.write("\n");
							write.write("DNI_CLIENT: "+copia[cont].getDni_client());
							write.write("\n");
							write.write("NOM: "+copia[cont].getNom());
							write.write("\n");
							write.write("COGNOMS: "+copia[cont].getCognoms());
							write.write("\n");
							write.write("DIRECCIO: "+copia[cont].getDireccio());
							write.write("\n");
							write.write("TELEFON: "+copia[cont].getTelefon());
							write.write("\n");
							write.write("------------------------------------");
							write.write("\n");
						}
					}
					
					copia = dades.enviaments_copia(conexio);
					write.write("\n\n");
					write.write("************");
					write.write("\n");
					write.write("ENVIAMENTS");
					write.write("\n");
					write.write("************");
					write.write("\n\n");
					write.write("------------------------------------");
					write.write("\n");
					for(int cont=0; cont<copia.length; cont++) {
						if (copia[cont]!=null){
							write.write("ID: "+copia[cont].getId());
							write.write("\n");
							write.write("DATA_ENTREGA: "+copia[cont].getData_entrega());
							write.write("\n");
							write.write("DNI_TREBALLADOR: "+copia[cont].getDni_treballador());
							write.write("\n");
							write.write("ID_PACK: "+copia[cont].getId_pack());
							write.write("\n");
							write.write("LATITUD: "+copia[cont].getLatitude());
							write.write("\n");
							write.write("LONGITUD: "+copia[cont].getLongitude());
							write.write("\n");
							write.write("------------------------------------");
							write.write("\n");
						}
					}
					write.close();
					
					conn.put("copies_seguritat\\copia_seguretat_"+LocalDate.now()+".txt", "copia_seguretat_"+LocalDate.now()+".txt");
					conn.exit();
			        conn.disconnect();
			        
			        System.out.println("");
			        System.out.println("Copia de la base de dades realitzada correctament.");
			        System.exit(0);
				}
			}
		}else {
			if(carpeta.exists()) {
				write = new FileWriter(carpeta);
				write.write("*************");
				write.write("\n");
				write.write("TREBALLADORS");
				write.write("\n");
				write.write("*************");
				write.write("\n\n");
				write.write("------------------------------------");
				write.write("\n");
				
				conn.cd("/home/roger/copies_seguritat");
				
				for(int cont=0; cont<copia.length; cont++) {
					if (copia[cont]!=null){
						write.write("DNI: "+copia[cont].getDni_treballador());
						write.write("\n");
						write.write("NOM: "+copia[cont].getNom());
						write.write("\n");
						write.write("COGNOMS: "+copia[cont].getCognoms());
						write.write("\n");
						write.write("TELEFON: "+copia[cont].getTelefon());
						write.write("\n");
						write.write("PASSWORD: "+copia[cont].getPassword());
						write.write("\n");
						write.write("------------------------------------");
						write.write("\n");
					}
				}
				
				copia = dades.pedidos_copia(conexio);
				write.write("\n\n");
				write.write("*******************");
				write.write("\n");
				write.write("PEDIDOS REALITZATS");
				write.write("\n");
				write.write("*******************");
				write.write("\n\n");
				write.write("------------------------------------");
				write.write("\n");
				for(int cont=0; cont<copia.length; cont++) {
					if (copia[cont]!=null){
						write.write("ID_PACK: "+copia[cont].getId_pack());
						write.write("\n");
						write.write("DNI_CLIENT: "+copia[cont].getDni_client());
						write.write("\n");
						write.write("NOM: "+copia[cont].getNom());
						write.write("\n");
						write.write("COGNOMS: "+copia[cont].getCognoms());
						write.write("\n");
						write.write("DIRECCIO: "+copia[cont].getDireccio());
						write.write("\n");
						write.write("TELEFON: "+copia[cont].getTelefon());
						write.write("\n");
						write.write("------------------------------------");
						write.write("\n");
					}
				}
				
				copia = dades.enviaments_copia(conexio);
				write.write("\n\n");
				write.write("***********");
				write.write("\n");
				write.write("ENVIAMENTS");
				write.write("\n");
				write.write("***********");
				write.write("\n\n");
				write.write("------------------------------------");
				write.write("\n");
				for(int cont=0; cont<copia.length; cont++) {
					if (copia[cont]!=null){
						write.write("ID: "+copia[cont].getId());
						write.write("\n");
						write.write("DATA_ENTREGA: "+copia[cont].getData_entrega());
						write.write("\n");
						write.write("DNI_TREBALLADOR: "+copia[cont].getDni_treballador());
						write.write("\n");
						write.write("ID_PACK: "+copia[cont].getId_pack());
						write.write("\n");
						write.write("LATITUD: "+copia[cont].getLatitude());
						write.write("\n");
						write.write("LONGITUD: "+copia[cont].getLongitude());
						write.write("\n");
						write.write("------------------------------------");
						write.write("\n");
					}
				}
				write.close();
				
				conn.put("copies_seguritat\\copia_seguretat_"+LocalDate.now()+".txt", "copia_seguretat_"+LocalDate.now()+".txt");
				conn.exit();
		        conn.disconnect();
		        
		        System.out.println("");
		        System.out.println("Copia de la base de dades realitzada correctament.");
		        System.exit(0);
		        
			}else {
				if(carpeta.createNewFile()) {
					
					write = new FileWriter(carpeta);
					write.write("*************");
					write.write("\n");
					write.write("TREBALLADORS");
					write.write("\n");
					write.write("*************");
					write.write("\n\n");
					write.write("------------------------------------");
					write.write("\n");
					
					conn.cd("/home/roger/copies_seguritat");
					
					for(int cont=0; cont<copia.length; cont++) {
						if (copia[cont]!=null){
							write.write("DNI: "+copia[cont].getDni_treballador());
							write.write("\n");
							write.write("NOM: "+copia[cont].getNom());
							write.write("\n");
							write.write("COGNOMS: "+copia[cont].getCognoms());
							write.write("\n");
							write.write("TELEFON: "+copia[cont].getTelefon());
							write.write("\n");
							write.write("PASSWORD: "+copia[cont].getPassword());
							write.write("\n");
							write.write("------------------------------------");
							write.write("\n");
						}
					}
					
					copia = dades.pedidos_copia(conexio);
					write.write("\n\n");
					write.write("*******************");
					write.write("\n");
					write.write("PEDIDOS REALITZATS");
					write.write("\n");
					write.write("*******************");
					write.write("\n\n");
					write.write("------------------------------------");
					write.write("\n");
					for(int cont=0; cont<copia.length; cont++) {
						if (copia[cont]!=null){
							write.write("ID_PACK: "+copia[cont].getId_pack());
							write.write("\n");
							write.write("DNI_CLIENT: "+copia[cont].getDni_client());
							write.write("\n");
							write.write("NOM: "+copia[cont].getNom());
							write.write("\n");
							write.write("COGNOMS: "+copia[cont].getCognoms());
							write.write("\n");
							write.write("DIRECCIO: "+copia[cont].getDireccio());
							write.write("\n");
							write.write("TELEFON: "+copia[cont].getTelefon());
							write.write("\n");
							write.write("------------------------------------");
							write.write("\n");
						}
					}
					
					copia = dades.enviaments_copia(conexio);
					write.write("\n\n");
					write.write("***********");
					write.write("\n");
					write.write("ENVIAMENTS");
					write.write("\n");
					write.write("***********");
					write.write("\n\n");
					write.write("------------------------------------");
					write.write("\n");
					for(int cont=0; cont<copia.length; cont++) {
						if (copia[cont]!=null){
							write.write("ID: "+copia[cont].getId());
							write.write("\n");
							write.write("DATA_ENTREGA: "+copia[cont].getData_entrega());
							write.write("\n");
							write.write("DNI_TREBALLADOR: "+copia[cont].getDni_treballador());
							write.write("\n");
							write.write("ID_PACK: "+copia[cont].getId_pack());
							write.write("\n");
							write.write("LATITUD: "+copia[cont].getLatitude());
							write.write("\n");
							write.write("LONGITUD: "+copia[cont].getLongitude());
							write.write("\n");
							write.write("------------------------------------");
							write.write("\n");
						}
					}
					write.close();
					
					conn.put("copies_seguritat\\copia_seguretat_"+LocalDate.now()+".txt", "copia_seguretat_"+LocalDate.now()+".txt");
					conn.exit();
			        conn.disconnect();
			        
			        System.out.println("");
			        System.out.println("Copia de la base de dades realitzada correctament.");
			        System.exit(0);
				}
			}
		}	
	}
}
