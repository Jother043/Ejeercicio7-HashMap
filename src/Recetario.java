import java.util.HashMap;
import java.util.Set;

public class Recetario {

	private HashMap<String, Receta> recetas;
	
	public Recetario() {
		recetas=new HashMap<String,Receta>();
	
	}
	
	public void annadirReceta( Receta nuevaReceta) throws RecetaException {

		if(recetas.containsKey(nuevaReceta.getNombreReceta())){
			throw new RecetaException("Error al añadir la receta, ya existe esa receta en el recetario.");
		}
		recetas.put(nuevaReceta.getNombreReceta(), nuevaReceta);
	}
	
	public String listadoRecetasOrdenadasAlfabeticamente() throws RecetaException {
		return null;
	}
	
	public String listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion(String ingrediente) throws RecetaException{
		return null;
	}
}
