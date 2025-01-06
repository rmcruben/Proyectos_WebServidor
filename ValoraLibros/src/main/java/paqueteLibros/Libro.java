package paqueteLibros;

public class Libro {
	private String ISBN, nombre, autor, URL_portada, puntuaciones;

	public String getISBN() {
		return ISBN;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAutor() {
		return autor;
	}

	public String getURL_portada() {
		return URL_portada;
	}

	public String getPuntuaciones() {
		return puntuaciones;
	}

	public Libro(String ISBN, String nombre, String autor, String URL_portada, String puntuaciones) {
		super();
		this.ISBN = ISBN;
		this.nombre = nombre;
		this.autor = autor;
		this.URL_portada = URL_portada;
		this.puntuaciones = puntuaciones;
	}

	public String getAverage() {

		// Lo spliteamos 3-5-4-3-1
		if (puntuaciones.length() < 1) {
			return "Sin datos.";
		}
		String[] notas = puntuaciones.split("-");
		float suma = 0;

		for (int i = 0; i < notas.length; i++) {
			int voto = Integer.parseInt(notas[i]);
			suma += voto;
		}
		float media = suma / notas.length;

		String texto = media + " ( " + notas.length + " votos )";

		return texto;
	}

	public String ToCSV() {
		String csv = nombre + "," + autor + "," + URL_portada + "," + ISBN + "," + puntuaciones+"\n";
		return csv;
	}

}
