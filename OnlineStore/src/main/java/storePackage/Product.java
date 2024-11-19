package storePackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private double price;
	private String image;
	private int stock;
	public Product() {
		
	}
	public Product(String name, double price, String image, int stock) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.stock = stock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Product(int id, String nombre, double price, String image, int stock) {
		super();
		this.id = id;
		this.name = nombre;
		this.price = price;
		this.image = image;
		this.stock = stock;
	}
	
	
	
	
}
