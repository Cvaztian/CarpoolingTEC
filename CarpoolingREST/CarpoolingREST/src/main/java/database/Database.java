package database;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.Content;
import org.jdom2.output.XMLOutputter;
import org.tec.salsas.CarpoolingREST.model.Driver;
import org.tec.salsas.CarpoolingREST.model.Student;


import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Manipulador de base de datos
 * */
public class Database {
	
	private static String databaseLocation = "./Documentos/DatosI/Proyectos/3/CarpoolingTEC/CarpoolingREST/CarpoolingREST/src/database";
	
	/**
	 * Fiuncion que chequea el login
	 * @param type tipo de base
	 * @param email email a buscar
	 * @param pass contrasenna a buscar
	 * */
	public static Object checkLogin(String type, String email, String pass) throws Exception {
		if(type == "student") {
			File userFile = findFile(type, email);
			if(userFile == null) {
				System.out.println(email);
				System.out.println(userFile);
				return new Student("none","","","","", null,0D,"");
			}
			return (Student)ReadXML(type, userFile);
		}else if(type == "driver") {
			Driver result;
		}else {
			throw new Exception("Tipo de base de datos no soportado");
		}
		return null;
		
	}
	
	/**
	 * Dado un tipo de usuario retorna la direccion de la base de datos indicada
	 * @param type Tipo de usuario
	 * @throws Exception Si no existe base de datos indicada
	 * @returns Direccion de base de datos indicada
	 * */
	private static String databaseFocus(String type) throws Exception {
		String specificDatabase;
		if(type.equals("student")) {
    		specificDatabase = databaseLocation + "/students";
    	}else if(type.equals("driver")) {
    		specificDatabase = databaseLocation+"/drivers";
    	}else {
    		throw new Exception("Database not found");
    	}
		return specificDatabase;
	}
    
    /**
     * Busca el nombre de un archivo en la base de datos
     * @param type Tipo de usuario que se quiere buscar
     * @param email Email del usuario a encontrar
     * @return Archivo del usuario
     * @throws Exception si no se encontro la base de datos exacta
     * */
    public static File findFile(String type, String email) throws Exception{
    	String specificDatabase = databaseFocus(type);
        File f = new File(specificDatabase);
    	File[] matching= f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return true;
            }});
    	
    	if(matching == null){
    		return null;
    	}else{
    		for(File file:matching){
    			if(file.getName().equals(email)) {
    				System.out.println(file.getName());
        			return file;
    			}
    		}
    		return null;
    	}
    	
    }
    
    /**
     * Recibe un objeto y lo convierte a XML
     * @param type Tipo de base de datos a guardar el archivo
     * @param usuario Driver o Student segun el tipo
     * @throws Exception si no existe la base de datos de type
     * */
    public static void WriteXML(String type, Object usuario) throws Exception {
    	String specificDatabase = databaseFocus(type);
    	if(type.equals("student")){
	        Student estudiante = (Student)usuario;
	        
	        Element root=new Element("STUDENT");
	        Document doc=new Document();
	
	        Element child1=new Element("NAME");
	        child1.addContent(estudiante.getName());
	        Element child2=new Element("EMAIL");
	        child2.addContent(estudiante.getEmail());
	        Element child3 = new Element("PASS");
	        child3.addContent(estudiante.getPass());
	        Element child4 = new Element("ID");
	        System.out.println(estudiante.getCarne());
	        child4.addContent(estudiante.getCarne());
	        Element child5 = new Element("HOME");
	        child5.addContent(estudiante.getNodoResidencia());
	        Element child6 = new Element("RATE");
	        child6.addContent(estudiante.getRate().toString());
	        Element grandChild4 = new Element("SIZE");
	        if(estudiante.getAmigos()==null) {
	        	estudiante.setAmigos(new LinkedList<Driver>());
	        }
	        grandChild4.addContent(Integer.toString(estudiante.getAmigos().size()));
	        Element e = new Element("FRIENDS");
            root.addContent(e);
            Element size = new Element("SIZE");
            size.setText(Integer.toString(estudiante.getAmigos().size()));
            e.addContent(size);
	        for(Driver amigo:estudiante.getAmigos()){
	            Element ae1 = new Element("FRIEND");

	            Element e2 = new Element("NAME");
	            e2.setText(amigo.getName());
	            ae1.addContent(e2);

	            Element e3 = new Element("RATE");
	            e3.setText(amigo.getRate().toString());
	            ae1.addContent(e3);

	            List l = new ArrayList();
	            l.add(ae1);
	            e.addContent(l);
	        }
	        root.addContent(child1);
	        root.addContent(child2);
	        root.addContent(child3);
	        root.addContent(child4);
	        root.addContent(child5);
	        root.addContent(child6);
	
	        doc.setRootElement(root);
	
	        XMLOutputter outter=new XMLOutputter();
	        outter.setFormat(Format.getPrettyFormat());
	        outter.output(doc, new FileWriter(new File(specificDatabase+"/"+estudiante.getEmail())));
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
    public static Object ReadXML(String type, File file) throws Exception{
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = file;
        Object result;
        try{
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            if(type.equals("student")) {
            	List amigos = rootNode.getChildren("FRIENDS");
            	System.out.println(amigos.toString());
            	Element node = (Element) amigos.get(0);
            	LinkedList<Driver> amigosDriver = new LinkedList<>();
            	for(int i=0;i<Integer.parseInt(node.getChildText("SIZE"));i++) {
            		Driver friendDriver = new Driver();
            		Element friend = (Element)node.getChildren("FRIEND").get(i);
            		friendDriver.setName(friend.getChildText("NAME"));
            		friendDriver.setRate(Double.parseDouble(friend.getChildText("RATE")));
            		amigosDriver.add(friendDriver);
            	}
            	result = (Student)new Student(rootNode.getChildText("ID"), rootNode.getChildText("NAME"), rootNode.getChildText("EMAIL"), rootNode.getChildText("HOME"), rootNode.getChildText("PASS"), amigosDriver, (Double)Double.parseDouble(rootNode.getChildText("RATE")),"all");
            	System.out.println(((Driver)((Student)result).getAmigos().get(0)).getRate());
            }else if(type.equals("driver")) {
            	result = new Driver();
            }else {
            	throw new Exception("Not supported");
            }
            return result;
        }catch (IOException e){
            System.out.println(e);
        }catch (JDOMException e){
            System.out.println(e);
        }
        return null;

    }
}