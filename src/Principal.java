public class Principal {
    public static void main(String[] args) {

        Recetario recetario = new Recetario();
        Ingrediente ingrediente1 = new Ingrediente("cebolla");
        Ingrediente ingrediente2 = new Ingrediente("Perejil");
        Ingrediente ingrediente3 = new Ingrediente("Carne");
        Ingrediente ingrediente4 = new Ingrediente("cebolla", 1);
        Receta receta1 = null;
        Receta receta2 = null;
        Receta receta3 = null;
        Receta receta4 = null;
        try {
            receta1 = new Receta("Cebollin pilpil", 10);
            receta2 = new Receta("Filete al perejil", 5);
            receta3 = new Receta("Albóndigas con tomate", 30);
            receta4 = new Receta("Compota bi color", 3);

            receta1.annadirIngrediente(ingrediente1);
            receta2.annadirIngrediente(ingrediente2);
            receta3.annadirIngrediente(ingrediente3);
            receta4.annadirIngrediente(ingrediente4);
            receta4.annadirIngrediente(ingrediente4);
            receta4.annadirIngrediente(ingrediente4);
            receta4.annadirIngrediente(ingrediente4);





            recetario.annadirReceta(receta1);
            recetario.annadirReceta(receta2);
            recetario.annadirReceta(receta3);
            recetario.annadirReceta(receta4);

            System.out.println("++++++++++++++++++++ Listado Ordenado por orden de tiempo ++++++++++++++++++ ");

            System.out.println(recetario.listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion(ingrediente1.getNombreIngrediente()));

            System.out.println("++++++++++++++++++++ Listado Ordenado Alfabéticamente ++++++++++++++++++ ");

            System.out.println(recetario.listadoRecetasOrdenadasAlfabeticamente());

            receta1.annadirPaso("a");
            receta1.annadirPasoDetrasDe("CEBOLLA", "a");

            receta1.borrarIngrediente(ingrediente1);

            System.out.println("++++++++++++++++++++ Listado Ordenado Alfabéticamente con borrado de ingrediente hecho ++++++++++++++++++ ");

            System.out.println(recetario.listadoRecetasOrdenadasAlfabeticamente());

        } catch (RecetaException e) {

            System.err.println(e.getMessage());
        }
    }
}
