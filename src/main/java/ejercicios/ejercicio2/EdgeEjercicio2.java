package main.java.ejercicios.ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio2(VertexEjercicio2 source, VertexEjercicio2 target, Integer action, Double weight)
		implements SimpleEdgeAction<VertexEjercicio2, Integer>{
	
	public static EdgeEjercicio2 of(VertexEjercicio2 source, VertexEjercicio2 target, Integer action) {
		return new EdgeEjercicio2(source, target, action, action*DataEjercicio2.getValoracion(source.indice())*1.0);
	}
}
