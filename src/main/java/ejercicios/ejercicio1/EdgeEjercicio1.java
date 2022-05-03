package main.java.ejercicios.ejercicio1;

import java.util.Objects;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio1(VertexEjercicio1 source, VertexEjercicio1 target, Integer action, Double weight)
		implements SimpleEdgeAction<VertexEjercicio1, Integer>{
	
	/**
     * Método de factoría de la clase {@code EdgeEjercicio1}.
     *
     * @param source el vértice inicial de la arista.
     * @param target el vértice final de la arista.
     * @param action la acción que se va a realizar (entre 0 y el número de memorias).
     * @return una instancia del tipo {@code EdgeEjercicio1}.
     */
	public static EdgeEjercicio1 of(VertexEjercicio1 source, VertexEjercicio1 target, Integer action) {
		// Si el fichero se encuentra en una memoria 1, en caso contrario 0.
		Double weight = Objects.equals(action, DataEjercicio1.getNumMemoria()) ? 0.: 1.;
		return new EdgeEjercicio1(source, target, action, weight);
	}
}
