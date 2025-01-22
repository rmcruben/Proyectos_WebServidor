package paqueteRecu;

import java.util.ArrayList;

public class PintaHTMLRecu {

	public static String crearFormularioTematica() {
		String combo = "<form action=FavServlet?action=crearTematica method='POST'>"
				+ "<label for=nombre>Nombre tematica:</label>" + "<input type='text' id=nombre name=nombre>"
				+ "<input type='submit' value='Guardar'>" + "</form>";

		return combo;
	}

	public static String formularioInsertarFav() {
		ArrayList<String> temas = DAOFav.importarTematicas();
		String combo = "<select name='tematica' id=''tematica>";
		for (int i = 0; i < temas.size(); i++) {
			combo += "<option value=" + temas.get(i) + ">" + temas.get(i) + "</option>\n";
		}
		combo += "</select><br>";

		String form = "<form action='FavServlet?action=insertarFav' method='POST'>"
				+ "<label for='nombre'>Nombre:</label>" + "<input type='text' id='nombre'name='nombre'><br>"
				+ "<label for='URL'>URL: </label>" + "<input type='text' id='URL' name='URL'><br>"
				+ "<label for='tematica'>Temática: </label>" + combo + "<input type = submit value='Guardar'>"
				+ "</form>";

		return form;
	}

	public static String busquedaFormulario() {

		ArrayList<String> temas = DAOFav.importarTematicas();
		String combo = "<select name='tematicaBuscar' id='tematicaBuscar'>";
		combo+="<option value='Sin tematica'>Sin tematica</option>\n";
		for (int i = 0; i < temas.size(); i++) {
			combo += "<option value=" + temas.get(i) + ">" + temas.get(i) + "</option>\n";
		}
		combo += "</select><br>";
		
		String form ="<form action=FavServlet?action=listarFavoritos method='POST'>"
				+ "<label for='tematicaBuscar'>Selecciona una temática:</label>"
				+ combo
				+ "<input type = 'submit' value='Buscar'></form>";

		return form;
	}
	public static String crearTabla(ArrayList<Favorito> favlist) {
		String table = "<table border=1px solid>";
		
		for(int i= 0;i<favlist.size();i++) {
			Favorito f = favlist.get(i);
			table+="<tr><td>"+f.getNombre()+"</td><td>"+f.getURL()+"</td><td>"+f.getTematica()+"</td></tr>";
		}
		table+="</table>";
		return table;
	}
}
