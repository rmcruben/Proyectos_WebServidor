package paqueteRecu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Favorito {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nombre;
	private String URL;
	private String tematica;
	public Favorito(String nombre, String uRL, String tematica) {
		super();
		this.nombre = nombre;
		URL = uRL;
		this.tematica = tematica;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	@Override
	public String toString() {
		return "Favorito [id=" + id + ", nombre=" + nombre + ", URL=" + URL + ", tematica=" + tematica + "]";
	}
	
}
