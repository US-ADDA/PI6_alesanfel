package main.java.ejercicios.vertex;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import main.java.ejercicios.data.DatosEjercicio4;
import main.java.ejercicios.edge.AristaEjercicio4;
import us.lsi.graphs.virtual.VirtualVertex;

public record VerticeEjercicio4(Integer indice, List<Integer> capacidadRestante) implements VirtualVertex<VerticeEjercicio4, AristaEjercicio4, Integer> {

	public static VerticeEjercicio4 initialVertex() {
		return of(0, IntStream.range(0, DatosEjercicio4.getNumContenedores()).boxed().map(DatosEjercicio4::getCapacidadContenedor).toList());
	}
	
	public static VerticeEjercicio4 copy(VerticeEjercicio4 v) {
		return of(v.indice, v.capacidadRestante);
	}
	
	public static VerticeEjercicio4 of(Integer indice, List<Integer> capacidadRestante) {
		return new VerticeEjercicio4(indice, capacidadRestante);
	}
	
	public static VerticeEjercicio4 lastVertex() {
		return of(DatosEjercicio4.getNumElementos(), IntStream.range(0, DatosEjercicio4.getNumContenedores()).boxed().map(x -> 0).toList());
	}
	
	public static Predicate<VerticeEjercicio4> goal() {
		return v -> Objects.equals(v.indice, DatosEjercicio4.getNumElementos());
	}
	
	public static Predicate<VerticeEjercicio4> constraints() {
		// TODO
		return null;
	}
	
	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= DatosEjercicio4.getNumElementos();
	} 
	
	@Override
	public List<Integer> actions() {
		// TODO
		return null;
	}

	@Override
	public VerticeEjercicio4 neighbor(Integer a) {
		// TODO
		return null;
	}

	@Override
	public AristaEjercicio4 edge(Integer a) {
		return AristaEjercicio4.of(this, neighbor(a), a, a*1.0);
	}
}
