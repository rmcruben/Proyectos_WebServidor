package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataAccess {

	public static void registerContact(Contact contact) {
		// TODO: Coger el contacto, separarlo y meterlo en la base de datos
	    try {
	        // Configuración de la conexión a MongoDB
	        ConnectionString c = new ConnectionString("mongodb://localhost:27017/");
	        MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();
	        MongoClient m = MongoClients.create(s);
	        MongoDatabase db = m.getDatabase("local");
	        
	        // Acceder a la colección 'agenda'
	        MongoCollection<Document> collection = db.getCollection("agenda");
	        
	        // Crear un documento con los datos del contacto
	        Document document = new Document();
	        document.put("name", contact.getName());  // Establece el nombre
	        document.put("tel", contact.getTel());    // Establece el teléfono
	        
	        // Insertar el documento en la colección
	        collection.insertOne(document);
	        
	    } catch (MongoException e) {
	        // Manejo de excepciones
	        e.printStackTrace();
	    }
        
	}

	public static ArrayList<Contact> getAllContacts() {
		ArrayList<Contact> contact_list=new ArrayList<Contact>();
		
		try {
			ConnectionString c = new ConnectionString("mongodb://localhost:27017/");
			
			MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();
			
			MongoClient m = MongoClients.create(s);
			MongoDatabase db = m.getDatabase("local");
			
			MongoCollection<Document> collection = db.getCollection("agenda");
			
			FindIterable<Document> docs = collection.find();
			
			for (Document document : docs) {
				 // Extraer valores de los campos necesarios
	            String name = document.getString("name"); // Cambia "name" por el nombre real del campo en MongoDB
	            String tel = document.getString("tel");   // Cambia "tel" por el nombre real del campo en MongoDB
	            
	            // Crear objeto Contact
	            Contact contact = new Contact(name, tel);
	            
	            // Agregar el contacto a la lista
	            contact_list.add(contact);
			}
			
			}catch (MongoException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		
		return contact_list;
	}
	
	public static ArrayList<Contact> searchByName(String name) {
        ArrayList<Contact> contact_list = getAllContacts();//Lo suyo es usar find
        ArrayList<Contact> contactFind = new ArrayList<>();

        for (Contact contact : contact_list) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
            	contactFind.add(contact);
            }
        }

        return contactFind;
    }
}