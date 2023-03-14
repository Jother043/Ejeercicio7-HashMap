import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

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

    /**
     * Este método es el que se encarga de añadir un ingrediente a la lista de ingredientes.
     * Utilizamos la condición para ver si la lista contiene el nuevo ingrediente si no lo tiene
     * se añade y si lo tiene se le suma uno al actual.
     * @param ingredienteNuevo
     */
    public void annadirIngrediente(Ingrediente ingredienteNuevo) {
        Iterator<Ingrediente> it=ingredientes.iterator();
        boolean encontrado=false;
        while(it.hasNext() && !encontrado) {
            Ingrediente i=it.next();
            if(i.equals(ingredienteNuevo)) {
                i.setCantidad(i.getCantidad()+ingredienteNuevo.getCantidad());
                encontrado=true;
            }
        }

        if(!encontrado) {
            ingredientes.add(ingredienteNuevo);
        }
//        if (ingredientes.contains(ingredienteNuevo)) {
//            for (Ingrediente i : ingredientes) {
//                if(i.equals(ingredienteNuevo)) {
//                    i.setCantidad(i.getCantidad() + ingredienteNuevo.getCantidad());
//                }
//            }
//        } else {
//            ingredientes.add(ingredienteNuevo);
//        }
    }

    public void annadirPaso(String pasoExistente) throws RecetaException {
        if (pasos.contains(pasoExistente)) {
            throw new RecetaException("No puedes añadir a la receta un paso existente");
        } else {
            pasos.add(pasoExistente);
        }
    }

    /**
     * Este método te dice si la receta necesita un ingrediente.
     * @param nombreIngrediente
     * @return
     */
    public boolean necesitaIngrediente(String nombreIngrediente) {
        return ingredientes.contains(new Ingrediente(nombreIngrediente));
    }

    public void borrarIngrediente(Ingrediente ingredienteABorrar) throws RecetaException {

        boolean encontrado = false;

        if(!ingredientes.contains(ingredienteABorrar)){
            throw new RecetaException("No se ha encontrado el ingrediente.");
        }

        ingredientes.remove(ingredienteABorrar);

        int index = 0;
        for (String paso : pasos) {
            if (paso.equals(ingredienteABorrar.getNombreIngrediente())) {
                pasos.remove(index);
                encontrado = true;
            }
            index++;
        }

        if (!encontrado){
            throw new RecetaException("No se ha encontrado el paso que contiene al ingrediente.");
        }
    }

    public void annadirPasoDetrasDe(String pasoNuevo, String pasoExistente) throws RecetaException {
        int index = 0;
        boolean existepaso = false;
        //Se podria hacer más eficiente con el indexOf.
        for (String paso : pasos) {
            if (pasos.contains(pasoExistente)) {
                pasos.add(index + 1, pasoNuevo);
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

    public int compareTo(Receta o2) {
        return this.getNombreReceta().compareTo(o2.getNombreReceta());
    }

    @Override
    public String toString() {
        return "Receta: " +
                " nombreReceta: " + nombreReceta + "," +
                " minutosDePreparacion: " + minutosDePreparacion + "," +
                " ingredientes: " + ingredientes + "," +
                " pasos: " + pasos ;
    }
}
