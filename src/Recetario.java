
import java.util.*;

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

		StringBuilder sb = new StringBuilder();
		Set<String> claves = recetas.keySet();
		List<Receta> recetario = new ArrayList<>(recetas.values());
		recetario.sort(new Comparator<Receta>() {
			@Override
			public int compare(Receta o1, Receta o2) {
				return o1.getNombreReceta().compareTo(o2.getNombreReceta());
			}
		});
		for(Receta receta : recetario){
			sb.append(receta + "\n");
		}
		return sb.toString();
	}
	
	public String listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion(String ingrediente) throws RecetaException{
		StringBuilder sb = new StringBuilder();
		List<Receta> listadoRecetas = new ArrayList<>();
		boolean hayIngrediente = false;
		for(Receta receta : recetas.values()){
			if(receta.necesitaIngrediente(ingrediente)){
				listadoRecetas.add(receta);
				hayIngrediente = true;

			}
		}
		if(!hayIngrediente){
			throw new RecetaException("No hay recetas con el ingrediente indicado");
		}
		listadoRecetas.sort(new Comparator<Receta>() {
			@Override
			public int compare(Receta o1, Receta o2) {
				return Integer.compare(o1.getMinutosDePreparacion(),(o2.getMinutosDePreparacion()));
			}
		});

		for(Receta receta : listadoRecetas){
			sb.append(receta + "\n");
		}

		return sb.toString();
	}
}
