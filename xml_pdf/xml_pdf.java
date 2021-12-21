package xml_pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aspose.pdf.internal.imaging.internal.Exceptions.IO.IOException;


public class xml_pdf {
	
	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) throws SAXException, java.io.IOException, ParserConfigurationException {
		
		String ruta = "";
		File fitxer;
		int num =0;
		
		do {
			System.out.println("***************************APLICACIÓ XML A PDF***************************");
			System.out.println("");
			System.out.println("Introdueixi el la ruta absoluta del fitxer XML que desitja passar a PDF:");
			System.out.print("---> ");
			ruta = read.nextLine();
			
			fitxer = new File(ruta);
			
			num = 1;
			
			if (!fitxer.exists()) {
				System.out.println("ERROR: El fitxer seleccionat no existeix.");
				num = 0;
			}else if (!fitxer.isFile()){
				System.out.println("ERROR: El fitxer seleccionat correspon a un directori.");
				num = 0;
			}
		
		}while (num == 0);
		
		try {
            //Per llegir document xml i agafar les dades
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fitxer);
            document.getDocumentElement().normalize();
            //Crrem nou document PDF
            PDDocument documento = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A3);
            documento.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(documento, page);
            //agafem element arrel i escribim al pdf
            String arrel = document.getDocumentElement().getNodeName();
            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52);
            contentStream.showText(arrel);
            //agafem informacio dels enviaments i les passem al pdf
            NodeList nList = document.getElementsByTagName("informacio");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
            	
                Node nNode = nList.item(temp);
                String nodePrincipal = nNode.getNodeName();
                contentStream.showText(nodePrincipal);
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                	//Agafem elements del xml
                    Element eElement = (Element) nNode;
                    String id_pack = eElement.getElementsByTagName("id_pack").item(0).getTextContent();
                    String nom_client = eElement.getElementsByTagName("nom_client").item(0).getTextContent();
                    String cognoms_client = eElement.getElementsByTagName("cognoms_client").item(0).getTextContent();
                    String direccio_client = eElement.getElementsByTagName("direccio_client").item(0).getTextContent();
                    String telefon_client = eElement.getElementsByTagName("telefon_client").item(0).getTextContent();
                    String latitude = eElement.getElementsByTagName("latitude").item(0).getTextContent();
                    String longitude = eElement.getElementsByTagName("longitude").item(0).getTextContent();
                    //Escribim els elements al pdf
                    contentStream.showText(id_pack);
                    contentStream.showText(nom_client);
                    contentStream.showText(cognoms_client);
                    contentStream.showText(direccio_client);
                    contentStream.showText(telefon_client);
                    contentStream.showText(latitude);
                    contentStream.showText(longitude);                    
                }
            }
            //Marquem que s'ha acabat el text i tanquem pdf
            contentStream.endText();
            contentStream.close();
            
            /*obtenim el nom del fitxer sense el (.) i extensio*/
			String nom = fitxer.getName();
			String [] parts = nom.split("\\.");
			String nombre = parts[0];
			
			//guardem el pdf a la ruta especificada
            String ruta_pdf = fitxer.getParent()+"//"+nombre+".pdf";
            documento.save(ruta_pdf);
            
            //eliminem xml
            if(fitxer.delete()) {
            	System.out.println("El fitxe XML s'ha esborrat correctament.");            }
        }
        catch(IOException e) {
            System.out.println(e);
        } 	
	}
}

