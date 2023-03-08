import java.util.HashSet;
import java.util.LinkedList;

public class Receta {
    private String nombreReceta;
    private int minutosDePreparacion;
    private HashSet<Ingrediente> ingredientes;
    private LinkedList<String> pasos;

    public Receta(String nombreReceta, int minutosPreparacion) throws RecetaException {
        this.nombreReceta = nombreReceta.toUpperCase();
        setMinutosDePreparacion(minutosPreparacion);
        ingredientes = new HashSet<Ingrediente>();
        pasos = new LinkedList<String>();
    }

    public int getMinutosDePreparacion() {
        return minutosDePreparacion;
    }

    public void setMinutosDePreparacion(int minutosDePreparacion) throws RecetaException {
        if (minutosDePreparacion <= 0) {
            throw new RecetaException("Minutos incorrectos");
        }
        this.minutosDePreparacion = minutosDePreparacion;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void annadirPasoAlFinal(String paso) {
        pasos.add(paso.toUpperCase());
    }

    public void annadirIngrediente(Ingrediente ingredienteNuevo) {
        if (ingredientes.contains(ingredienteNuevo)) {
            for (Ingrediente i : ingredientes) {
                i.setCantidad(i.getCantidad() + ingredienteNuevo.getCantidad());
            }
        } else {
            ingredientes.add(ingredienteNuevo);
        }
    }

    public boolean necesitaIngrediente(String nombreIngrediente) {
        return ingredientes.contains(new Ingrediente(nombreIngrediente));
    }

    public void borrarIngrediente(Ingrediente ingredienteABorrar) throws RecetaException {

        boolean encontrado = false;

        for (String paso : pasos) {
            if (pasos.contains(ingredienteABorrar.getNombreIngrediente())) {
                ingredientes.remove(ingredienteABorrar);
                pasos.remove(paso);
                encontrado = true;
                break;
            }
        }
        if (!encontrado){
            throw new RecetaException("No se ha encontrado el ingrediente ni el paso que lo contiene.");
        }
    }

    public void annadirPasoDetrasDe(String pasoNuevo, String pasoExistente) throws RecetaException {
        int index = 0;
        boolean existepaso = false;
        //Se podria hacer más eficiente con el indexOf.
        for (String paso : pasos) {
            if (pasos.contains(pasoExistente)) {
                pasos.add(index + 1, paso);
                existepaso = true;
                break;
            }
            index++;
        }
        if(!existepaso){
            throw new RecetaException("No existe el paso");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreReceta == null) ? 0 : nombreReceta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Receta other = (Receta) obj;
        if (nombreReceta == null) {
            if (other.nombreReceta != null)
                return false;
        } else if (!nombreReceta.equals(other.nombreReceta))
            return false;
        return true;
    }


}
