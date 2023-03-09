public class Principal {
    public static void main(String[] args) {

        Recetario recetario = new Recetario();
        Ingrediente ingrediente1 = new Ingrediente("cebolla");
        Ingrediente ingrediente2 = new Ingrediente("Perejil");
        Ingrediente ingrediente3 = new Ingrediente("Carne");
        Ingrediente ingrediente4 = new Ingrediente("cebolla");
        Receta receta1 = null;
        Receta receta2 = null;
        Receta receta3 = null;
        Receta receta4 = null;
        try {
            receta1 = new Receta("Cebollin pilpi",10,ingrediente1);
            receta2 = new Receta("Filete al perejil",5,ingrediente2);
            receta3 = new Receta("Albóndigas con tomate",30,ingrediente3);
            receta4 = new Receta("Compota bi color",3,ingrediente4);
        } catch (RecetaException e) {
            System.err.println(e.getMessage());
        }
        receta1.annadirIngrediente(ingrediente1);
        receta2.annadirIngrediente(ingrediente2);
        receta3.annadirIngrediente(ingrediente3);
        receta4.annadirIngrediente(ingrediente4);
        try {
            recetario.annadirReceta(receta1);
            recetario.annadirReceta(receta2);
            recetario.annadirReceta(receta3);
            recetario.annadirReceta(receta4);
        } catch (RecetaException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("++++++++++++++++++++ Listado Ordenado por orden de tiempo ++++++++++++++++++ ");
        try {
            System.out.println(recetario.listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion(ingrediente1.getNombreIngrediente()));
        } catch (RecetaException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("++++++++++++++++++++ Listado Ordenado Alfabéticamente ++++++++++++++++++ ");
        try {
            System.out.println(recetario.listadoRecetasOrdenadasAlfabeticamente());
        } catch (RecetaException e) {
            System.err.println(e.getMessage());
        }
    }
}
