package paqueteLibros;

import java.util.ArrayList;

public class PintaHTML {

	public static String crearDesplegablePuntuacion() {

		String combo = "<select name=puntuacion>";

		combo += "<option value='' selected>No quiero puntuar ahora</option>";

		for (int i = 0; i <= 5; i++) {
			combo += "<option value="+i+">"+i+"</option>";			
		}
		combo+="</select>";
		return combo;
	}

	public static String crearTabla(ArrayList<Libro> libros) {

		String combo = "<select name = puntuacion>\n";
		combo += "<option value=1>1</option>\n";
		combo += "<option value=2>2</option>\n";
		combo += "<option value=3>3</option>\n";
		combo += "<option value=4>4</option>\n";
		combo += "<option value=5>5</option>\n";
		combo += "</select>";

		String tabla = "<table border=1 solid>";

		for (int i = 0; i < libros.size(); i++) {
			Libro l = libros.get(i);

			tabla += "<tr><td><img 	src = " + l.getURL_portada() + " width=100px></td>";
			tabla += "<td>" + l.getNombre() + "(" + l.getAutor() + ")<br>";
			tabla += "<form action =ServletLibros method= POST>";
			tabla += "<input type = hidden name= ISBN value=" + l.getISBN() + ">";
			tabla += "<input type = hidden name=action value=puntuar_libro>";
			tabla += combo;
			tabla += "<input type = submit>";
			tabla += "</form>\n";
			tabla += "<br>";
			tabla += l.getAverage();
			tabla += "</td></tr>";
		}

		return tabla;
	}
}
