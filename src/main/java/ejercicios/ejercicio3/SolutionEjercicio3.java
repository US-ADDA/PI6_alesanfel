package main.java.ejercicios.ejercicio3;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.common.List2;
import us.lsi.common.Pair;

public class SolutionEjercicio3 {
	
	private final List<Pair<Producto, Double>> productos;
    private Double beneficio;
	
	public static SolutionEjercicio3 of(GraphPath<VertexEjercicio3, EdgeEjercicio3> path) {
		List<Integer> la = path.getEdgeList().stream().map(EdgeEjercicio3::action).toList();
		return SolutionEjercicio3.of(la);
	}
	
	public static SolutionEjercicio3 of(List<Integer> ls) {
		return new SolutionEjercicio3(ls);
	}
	
	private SolutionEjercicio3(List<Integer> ls) {
		productos = List2.empty();
        beneficio = 0.;
        for (int i = 0; i < ls.size(); i++) {
            int value = ls.get(i);
            if (value > 0) {
                Producto producto = DataEjercicio3.getProducto(i);
                productos.add(Pair.of(producto, value * 1.0));
                beneficio += producto.precio() * value;
            }
        }
	}
	
	@Override
    public String toString() {
        String cadenaProductos = productos.stream()
                .map(pair -> pair.first().id() + ": " + Math.round(pair.second()) + " unidades")
                .reduce("", (ac, nx) -> String.format("%s%s%n", ac, nx));
        return String.format("Productos seleccionados:%n%sBeneficio: %s", cadenaProductos, beneficio);
    }

}
