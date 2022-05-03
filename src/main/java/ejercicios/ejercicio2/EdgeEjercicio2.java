package main.java.ejercicios.ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio2(VertexEjercicio2 source, VertexEjercicio2 target, Integer action, Double weight)
		implements SimpleEdgeAction<VertexEjercicio2, Integer>{
	
	/**
	 * Método de factoría de la clase {@code EdgeEjercicio2}.
	 * 
	 * @param source el vértice inicial de la arista.
	 * @param target el vértice final de la arista.
	 * @param action la acción que se va a realizar (entre 0 y 1).
	 * @return una instancia del tipo {@code EdgeEjercicio1}.
	 */
	public static EdgeEjercicio2 of(VertexEjercicio2 source, VertexEjercicio2 target, Integer action) {
		// Contamos la valoración si y solo si el empleado ha sido contratado (entre 0 y 5).
		Double weight = action*DataEjercicio2.getValoracion(source.indice())*1.0;
		return new EdgeEjercicio2(source, target, action, weight);
	}
}
