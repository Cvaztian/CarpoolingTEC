package database;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.tec.salsas.CarpoolingREST.model.Driver;
import org.tec.salsas.CarpoolingREST.model.Student;


import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class handler {

    public static void main(String argv[]) throws IOException {
        //h.WriteXML();
        //h.ReadXML();
    }
    
    /**
     * Busca el nombre de un archivo en la base de datos
     * @param type Tipo de usuario que se quiere buscar
     * @param email Email del usuario a encontrar
     * @return Archivo del usuario
     * @throws Exception si no se encontro la base de datos exacta
     * */
    public static File findFile(String type, String email) throws Exception{
    	String databaseLocation = "./src/database";
    	String specificDatabase;
    	
    	if(type.equals("student")) {
    		specificDatabase = databaseLocation + "/students";
    	}else if(type.equals("driver")) {
    		specificDatabase = databaseLocation+"/drivers";
    	}else {
    		throw new Exception("Database not found");
    	}
    	

        File f = new File(specificDatabase);
    	File[] matching= f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return true;
            }});
    	for(File file:matching){
        	if(file.getName().equalsIgnoreCase(email)) {
        		return file;
        	}
        }
    	
    	return null;
    	
    	
    }
    
    /**
     * Recibe un objeto y lo convierte a XML
     * @param type Tipo de base de datos a guardar el archivo
     * @param usuario Driver o Student segun el tipo
     * @throws Exception si no existe la base de datos de type
     * */
    public static void WriteXML(String type, Object usuario) throws Exception {
    	if(type.equals("student")){
	        Student estudiante = (Student)usuario;
	
	        Element root=new Element("PASAJERO");
	        Document doc=new Document();
	
	        Element child1=new Element("NAME");
	        child1.addContent(estudiante.getName());
	        Element child2=new Element("EMAIL");
	        child2.addContent(estudiante.getEmail());
	
	        root.addContent(child1);
	        root.addContent(child2);
	
	        doc.setRootElement(root);
	
	        XMLOutputter outter=new XMLOutputter();
	        outter.setFormat(Format.getPrettyFormat());
	        outter.output(doc, new FileWriter(new File(estudiante.getEmail())));
        }else if(type.equals("driver")) {
        	Driver driver = (Driver) usuario;
        }else {
        	throw new Exception("No existe la base de datos especificada");
        }

    }
    
    /**
     * Lee un archivo XML (proximamente lo convierte a objeto)
     * @param File Archivo a leer
     * @returns Not yet, pero retorna el objeto del archivo leido
     * */
    public static void ReadXML(File file){
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = file;
        try{
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            System.out.print(rootNode.getChildText("EMAIL"));  // Agarra el texto de EMAIL
        }catch (IOException e){
            System.out.println(e);
        }catch (JDOMException e){
            System.out.println(e);
        }

    }
}