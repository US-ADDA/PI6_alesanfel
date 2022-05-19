package main.java.ejercicios.ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio4(VertexEjercicio4 source, VertexEjercicio4 target, Integer action, Double weight)
        implements SimpleEdgeAction<VertexEjercicio4, Integer> {

    /**
     * Método de factoría de la clase {@code EdgeEjercicio4}.
     *
     * @param source el vértice inicial de la arista.
     * @param target el vértice final de la arista.
     * @param action la acción que se va a realizar (entre 0 y 1).
     * @return una instancia del tipo {@code EdgeEjercicio}.
     */
    public static EdgeEjercicio4 of(VertexEjercicio4 source, VertexEjercicio4 target, Integer action) {
        // Si hemos logrado llenar un contenedor, es el camino correcto.
        Integer cantidadLlenosSource=DataEjercicio4.getNumeroContenedoresLLenos(source.capacidadRestante());
        Integer cantidadLlenosTarget=DataEjercicio4.getNumeroContenedoresLLenos(target.capacidadRestante());

        double weight=(cantidadLlenosTarget>cantidadLlenosSource) ? 1. : 0.;
        return new EdgeEjercicio4(source, target, action, weight);
    }
}
