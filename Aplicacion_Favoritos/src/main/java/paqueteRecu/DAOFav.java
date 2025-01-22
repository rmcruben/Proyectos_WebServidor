package paqueteRecu;

import static com.mongodb.client.model.Sorts.ascending;
import java.util.ArrayList;

import org.bson.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;



public class DAOFav {

	private static StandardServiceRegistry s = null;
	private static SessionFactory sf = null;
	
	//Esto no lo necesito por que al final la persistencia de los datos lo hago con MongoDB
	/*private static String url = "jdbc:mysql://localhost:3306/bd_examen";
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

	}*/

	public static void abrirConexionHibernate() {
		s = new StandardServiceRegistryBuilder().configure().build();
		sf = new MetadataSources(s).buildMetadata().buildSessionFactory();
	}
	

	public static void guardarTematica(Tematica t) {

		try {
			ConnectionString c = new ConnectionString("mongodb://localhost:27017/");
			MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();
			MongoClient m = MongoClients.create(s);
			MongoDatabase db = m.getDatabase("local");

			MongoCollection<Document> collection = db.getCollection("tematicas");

			Document document = new Document();
			document.put("nombre", t.getNombre());
			collection.insertOne(document);

		} catch (MongoException e) {

			e.printStackTrace();
		}
	}

	public static ArrayList<String> importarTematicas() {
		ArrayList<String> temas = new ArrayList<String>();

		try {
			ConnectionString c = new ConnectionString("mongodb://localhost:27017/");

			MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();

			MongoClient m = MongoClients.create(s);
			MongoDatabase db = m.getDatabase("local");

			MongoCollection<Document> collection = db.getCollection("tematicas");
			// db.collection_name.find().sort({tematica: 1})
			FindIterable<Document> docs = collection.find().sort(ascending("tematica"));

			for (Document document : docs) {

				String nombre = document.getString("nombre");

				temas.add(nombre);
			}

		} catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return temas;
	}

	public static void guardarFAV(Favorito f) {
		try {
			ConnectionString c = new ConnectionString("mongodb://localhost:27017/");
			MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();
			MongoClient m = MongoClients.create(s);
			MongoDatabase db = m.getDatabase("local");

			MongoCollection<Document> collection = db.getCollection("favoritos");

			Document document = new Document();
			document.put("nombre", f.getNombre());
			document.put("URL", f.getURL());
			document.put("tematica", f.getTematica());

			collection.insertOne(document);

		} catch (MongoException e) {

			e.printStackTrace();
		}
	}

	public static ArrayList<Favorito> listarFavoritos(String tematica) {
		ArrayList<Favorito> favs = new ArrayList<Favorito>();

		if ("Sin tematica".equals(tematica)) {
			favs = getAllFavs();
			return favs;
		} else {
			try {
				ConnectionString c = new ConnectionString("mongodb://localhost:27017/");
				MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();
				MongoClient m = MongoClients.create(s);
				MongoDatabase db = m.getDatabase("local");
				MongoCollection<Document> collection = db.getCollection("favoritos");

				FindIterable<Document> docs = collection.find(Filters.regex("tematica", tematica, "i"));

				for (Document document : docs) {
					String nombre = document.getString("nombre");
					String URL = document.getString("URL");
					String tematicaFav = document.getString("tematica");

					Favorito fav = new Favorito(nombre, URL, tematicaFav);

					favs.add(fav);
				}

			} catch (MongoException e) {
				e.printStackTrace();
			}

			return favs;
		}

	}

	/*
	 * public static ArrayList<Favorito> listarFavoritos(String tematica) {
	 * 
	 * ArrayList<Favorito> searchedFav = new ArrayList<Favorito>(); abrirConexion();
	 * 
	 * if ("Sin tematica".equals(tematica)) { String sql =
	 * "SELECT * FROM favoritos"; try { PreparedStatement psmt =
	 * c.prepareStatement(sql); ResultSet rs = psmt.executeQuery(); while
	 * (rs.next()) { String nombre = rs.getString("nombre"); String URL =
	 * rs.getString("URL"); String tematicaFav = rs.getString("tematica");
	 * 
	 * Favorito f = new Favorito(nombre, URL, tematicaFav); searchedFav.add(f); }
	 * rs.close(); c.close();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return searchedFav; } else { String sql =
	 * "SELECT * FROM favoritos WHERE tematica = ? "; try { PreparedStatement psmt =
	 * c.prepareStatement(sql); psmt.setString(1, tematica);
	 * 
	 * ResultSet rs = psmt.executeQuery(); while (rs.next()) { String nombre =
	 * rs.getString("nombre"); String URL = rs.getString("URL"); String tematicaFav
	 * = rs.getString("tematica");
	 * 
	 * Favorito f = new Favorito(nombre, URL, tematicaFav); searchedFav.add(f); }
	 * rs.close(); c.close();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return searchedFav; }
	 * 
	 * }
	 */

	public static void exportHibernate(ArrayList<Favorito> fav_list) {
		abrirConexionHibernate();
		Session session = null;
		try {
			session = sf.openSession();
			session.beginTransaction();
			for(Favorito f : fav_list) {
				session.save(f);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public static ArrayList<Favorito> getAllFavs() {
		ArrayList<Favorito> fav_list = new ArrayList<Favorito>();

		try {
			ConnectionString c = new ConnectionString("mongodb://localhost:27017/");

			MongoClientSettings s = MongoClientSettings.builder().applyConnectionString(c).build();

			MongoClient m = MongoClients.create(s);
			MongoDatabase db = m.getDatabase("local");

			MongoCollection<Document> collection = db.getCollection("favoritos");
			// db.collection_name.find().sort({tematica: 1})
			FindIterable<Document> docs = collection.find().sort(ascending("tematica"));

			for (Document document : docs) {

				String nombre = document.getString("nombre");
				String URL = document.getString("URL");
				String tematica = document.getString("tematica");

				Favorito fav = new Favorito(nombre, URL, tematica);

				fav_list.add(fav);
			}

		} catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return fav_list;

	}

}
