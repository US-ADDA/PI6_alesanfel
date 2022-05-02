package main.java.ejercicios.ejercicio3;

import java.util.List;
import java.util.function.Predicate;

import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio3(Integer indice, Integer tiempoProduccionRestante, Integer tiempoManualRestante) implements VirtualVertex<VertexEjercicio3, EdgeEjercicio3, Integer> {

	public static VertexEjercicio3 initialVertex() {
		return of(0, DataEjercicio3.getMaxTiempoEnProduccion(), DataEjercicio3.getMaxTiempoEnManual());
	}
	
	public static VertexEjercicio3 copy(VertexEjercicio3 v) {
		return of(v.indice, v.tiempoProduccionRestante, v.tiempoManualRestante);
	}
	
	public static VertexEjercicio3 of(Integer indice, Integer tiempoProduccionRestante, Integer tiempoManualRestante) {
		return new VertexEjercicio3(indice, tiempoProduccionRestante, tiempoManualRestante);
	}
	
	public static VertexEjercicio3 lastVertex() {
		return of(DataEjercicio3.getNumProductos(), 0, 0);
	}
	
	public static Predicate<VertexEjercicio3> goal() {
		return v -> v.indice == DataEjercicio3.getNumProductos();
	}
	
	@Override 
	public Boolean isValid() {
		return indice >= 0 && indice <= DataEjercicio3.getNumProductos();
	}
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VertexEjercicio3 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public EdgeEjercicio3 edge(Integer a) {
		return EdgeEjercicio3.of(this, neighbor(a), a, a*1.0);
	}
}
