
import java.util.*;

public class Recetario {

	private HashMap<String, Receta> recetas;
	
	public Recetario() {
		recetas=new HashMap<String,Receta>();
	
	}

	/**
	 * Método que añade una receta al recetario.
	 * Este método puede lanzar una excepción que la evaluamos con un containsKey, ya que con los hashMap
	 * tiene que mirarse por la key.
	 * y añadimos al mapa con el método put (poner)
	 * @param nuevaReceta
	 * @throws RecetaException
	 */
	public void annadirReceta( Receta nuevaReceta) throws RecetaException {
		//Si recetas contiene el nombre de la receta ya existe.
		if(recetas.containsKey(nuevaReceta.getNombreReceta())){
			throw new RecetaException("Error al añadir la receta, ya existe esa receta en el recetario.");
		}
		// si no se lanza la excepción es que aún no está esa receta y la ponemos.
		recetas.put(nuevaReceta.getNombreReceta(), nuevaReceta);
	}

	/**
	 * Este método nos devuelve un String del listado de recetas ordenadas alfabéticamente por su nombre.
	 * Utilizando para ello una clase anónima con el método .sort y un compareTo para comprar los nombres de las recetas.
	 * @return
	 * @throws RecetaException
	 */
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
		/*
		Para devolver el listado tenemos que hacer un for que nos añada el contenido del mapa
		 a un StringBuilder y poder mostrarlo mediante el return.
		 */
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
