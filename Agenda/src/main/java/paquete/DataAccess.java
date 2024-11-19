package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccess {

	public static void registerContact(Contact contact) {
		// TODO Auto-generated method stub
		// Coger el contacto separarlo y meterlo en el fichero
		// Guardar el contacto en el archivo
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Define la URL de conexión, el usuario y la contraseña para la base de datos MySQL.
        String url = "jdbc:mysql://localhost:3306/bd_agenda";  // URL de conexión a la base de datos 'bd_agenda' en localhost.
        String usuario = "root";  // Usuario de MySQL.
        String password = "";     // Contraseña de MySQL, en este caso está vacía.
        
        try {
        	// Intenta establecer la conexión con la base de datos usando la URL, usuario y contraseña.
			Connection c =  DriverManager.getConnection(url, usuario, password);
			// Crea un objeto Statement que permite ejecutar consultas SQL sobre la conexión establecida.
			Statement stmt = c.createStatement();
			
			String queryAdd="insert into contactos values('"+contact.getName()+"', '"+contact.getTel()+"')";
			
			stmt.execute(queryAdd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	public static ArrayList<Contact> getAllContacts() {
		ArrayList<Contact> contact_list=new ArrayList<Contact>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Define la URL de conexión, el usuario y la contraseña para la base de datos MySQL.
        String url = "jdbc:mysql://localhost:3306/bd_agenda";  // URL de conexión a la base de datos 'bd_agenda' en localhost.
        String usuario = "root";  // Usuario de MySQL.
        String password = "";     // Contraseña de MySQL, en este caso está vacía.
		
		try {
			Connection c = DriverManager.getConnection(url,usuario,password);
			
			Statement stmt = c.createStatement();
			
			String queryGet = "SELECT nombre, telefono FROM contactos";
			
			ResultSet resultado = stmt.executeQuery(queryGet);
			
			while(resultado.next()) {
				String nombre = resultado.getString(1);
				String telefono = resultado.getString(2);
				
				Contact contact = new Contact(nombre,telefono);
				
				contact_list.add(contact);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contact_list;
	}
	
	public static ArrayList<Contact> searchByName(String name) {
        ArrayList<Contact> contact_list = getAllContacts();
        ArrayList<Contact> contactFind = new ArrayList<>();

        for (Contact contact : contact_list) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
            	contactFind.add(contact);
            }
        }

        return contactFind;
    }
}