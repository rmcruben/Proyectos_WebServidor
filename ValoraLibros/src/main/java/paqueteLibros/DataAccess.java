package paqueteLibros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mysql.cj.protocol.Resultset;

public class DataAccess {

	private static String ruta_fichero="C:\\xampp\\htdocs\\libros.csv";
	private static String url = "jdbc:mysql://localhost:3306/bd_examen";
	private static String usuario = "root";
	private static String password = "";
	private static Connection c = null;

	private static void abrirConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, usuario, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int getRol(String usr, String pwd) {
		// Este metodo devuelve el metodo del ususario:
		// 0 si no existe
		// 1 si es un usuario "normal"
		// 2 si es admin

		abrirConexion();

		String sql = "SELECT rol from t_usuarios where usr = ? and pwd=?";

		try {

			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, usr);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() == false) {
				return 0;
			}
			String rol = rs.getString("rol");
			rs.close();
			c.close();
			return (Integer.parseInt(rol));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<Libro> getLibros() {
		abrirConexion();
		String sql = "SELECT * FROM t_libros";
		ArrayList<Libro> libros = new ArrayList<>();
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String ISBN = rs.getString("ISBN");
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				String URL_portada = rs.getString("URL_portada");
				String puntuaciones = rs.getString("puntuaciones");

				Libro l = new Libro(ISBN, nombre, autor, URL_portada, puntuaciones);
				libros.add(l);
			}
			rs.close();
			c.close();
			System.out.println("Libros recuperados: " + libros.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libros;
	}

	public static void updateReviews(String ISBN, String puntuacion) {
	    abrirConexion();

	    // Comprobamos si puntuaciones está vacío o ya empieza con un guion
	    String sql = "update t_libros set puntuaciones = concat(case when puntuaciones = '' then '' else concat(puntuaciones, '-') end, ?) where ISBN = ?";

	    try {
	        PreparedStatement pstmt = c.prepareStatement(sql);
	        pstmt.setString(1, puntuacion); // Solo añadimos la puntuación sin guion, el SQL se encarga de añadirlo si es necesario
	        pstmt.setString(2, ISBN);
	        pstmt.execute(); // usampos execute en vez del result set rs = pstmt.executeQuery porque no devuelve datos el update ni el delete

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public static void exportMongo() {
		// TODO Auto-generated method stub
		try {
			// Configuración de la conexión a MongoDB
			ConnectionString c = new ConnectionString("mongodb://localhost:27017/");
			MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();
			MongoClient m = MongoClients.create(s);
			MongoDatabase db = m.getDatabase("local");

			// Acceder a la colección 'libros'
			MongoCollection<Document> collection = db.getCollection("libros");

			//Recuperamos el array list de libros
			ArrayList<Libro>libros = getLibros();
			
			for(int i = 0; i < libros.size();i++) {
				HashMap<String, Object> mapa = new HashMap();
				Libro l = libros.get(i);
				mapa.put("nombre",l.getNombre());
				mapa.put("autor", l.getAutor());
				mapa.put("ISBN", l.getISBN());
				mapa.put("URL_portada", l.getURL_portada());
				mapa.put("puntuaciones", l.getPuntuaciones());
				
				//Creamos el document a partir del mapa
				Document d = new Document(mapa);
				collection.insertOne(d);
				
			}

		} catch (MongoException e) {
			// Manejo de excepciones
			e.printStackTrace();
		}
		
	}

	public static void insertarLibro(Libro l) {
		
		abrirConexion();
		String sql = "INSERT INTO t_libros VALUES(?,?,?,?,?);";
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, l.getISBN());
			pstmt.setString(2, l.getURL_portada());
			pstmt.setString(3, l.getNombre());
			pstmt.setString(4, l.getAutor());
			pstmt.setString(5, l.getPuntuaciones());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public static void importarCSV() {
		//nombre,autor,URL_portada,ISBN,puntuaciones
		
		try {
			FileReader fr = new FileReader(ruta_fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea=br.readLine();
			ArrayList<Libro> libros = new ArrayList<Libro>();
			while(linea !=null ) {
				String[] datos = linea.split(",");
				String nombre = datos[0];
				String autor = datos[1];
				String URL_portada = datos[2];
				String ISBN = datos[3];
				String puntuaciones = datos[4];
				
				Libro l = new Libro(ISBN,nombre,autor,URL_portada,puntuaciones);
				libros.add(l);
				linea=br.readLine();
			}
			insertarEnBD(libros);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void insertarEnBD(ArrayList<Libro> libros) {
		// TODO Auto-generated method stub
		for(int i = 0;i<libros.size();i++) {
			insertarLibro(libros.get(i));
		}
	}

	public static void exportCSV() {
		ArrayList<Libro> libros=getLibros();
		try {
			FileWriter fw= new FileWriter(ruta_fichero,false);//Añadimos el false para que machaque todo el contenido de dentro del archivo y lo escriba de nuevo
			for(int i=0;i<libros.size();i++) {
				fw.write(libros.get(i).ToCSV());
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
