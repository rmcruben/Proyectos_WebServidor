package storePackage;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ProductDAO {
	static StandardServiceRegistry s = null;
	static SessionFactory sf = null;

	public static void abrirConexion() {
		s = new StandardServiceRegistryBuilder().configure().build();
		sf = new MetadataSources(s).buildMetadata().buildSessionFactory();
	}

	public static void registerProduct(Product p) {
		abrirConexion();
		Session session = null;
		try {
			session = sf.openSession();
			session.beginTransaction();
			session.save(p);
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

	public static ArrayList<Product> getAllProducts() {
		abrirConexion();
		Session session = sf.openSession();
		List<Product> productList = session.createQuery("from Product p where p.stock > 0", Product.class).list();
		session.close();

		
		return new ArrayList<>(productList);
	}

	public static void comprarProducto(int productId) {
		abrirConexion();
		Session session = null;
		try {
			session = sf.openSession();
			session.beginTransaction();

			Product product = session.get(Product.class, productId);
			if (product != null && product.getStock() > 0) {
				product.setStock(product.getStock() - 1);
				session.update(product);
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
}
