package main.java.ejercicios.ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio3(VertexEjercicio3 source, VertexEjercicio3 target, Integer action, Double weight)
        implements SimpleEdgeAction<VertexEjercicio3, Integer> {

    public static EdgeEjercicio3 of(VertexEjercicio3 source, VertexEjercicio3 target, Integer action) {
        // Los ingresos de los productos que producimos.
        Double weight = DataEjercicio3.getIngresos(source.id()) * action * 1.0;
        return new EdgeEjercicio3(source, target, action, weight);
    }
}
