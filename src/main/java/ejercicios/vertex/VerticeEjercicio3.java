package main.java.ejercicios.vertex;

import java.util.List;
import java.util.function.Predicate;

import main.java.ejercicios.data.DatosEjercicio3;
import main.java.ejercicios.edge.AristaEjercicio3;
import us.lsi.graphs.virtual.VirtualVertex;

public record VerticeEjercicio3(Integer indice, Integer tiempoProduccionRestante, Integer tiempoManualRestante) implements VirtualVertex<VerticeEjercicio3, AristaEjercicio3, Integer> {

	public static VerticeEjercicio3 initialVertex() {
		return of(0, DatosEjercicio3.getMaxTiempoEnProduccion(), DatosEjercicio3.getMaxTiempoEnManual());
	}
	
	public static VerticeEjercicio3 copy(VerticeEjercicio3 v) {
		return of(v.indice, v.tiempoProduccionRestante, v.tiempoManualRestante);
	}
	
	public static VerticeEjercicio3 of(Integer indice, Integer tiempoProduccionRestante, Integer tiempoManualRestante) {
		return new VerticeEjercicio3(indice, tiempoProduccionRestante, tiempoManualRestante);
	}
	
	public static VerticeEjercicio3 lastVertex() {
		return of(DatosEjercicio3.getNumProductos(), 0, 0);
	}
	
	public static Predicate<VerticeEjercicio3> goal() {
		return v -> v.indice == DatosEjercicio3.getNumProductos();
	}
	
	@Override 
	public Boolean isValid() {
		return indice >= 0 && indice <= DatosEjercicio3.getNumProductos();
	}
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VerticeEjercicio3 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public AristaEjercicio3 edge(Integer a) {
		return AristaEjercicio3.of(this, neighbor(a), a, a*1.0);
	}
}
