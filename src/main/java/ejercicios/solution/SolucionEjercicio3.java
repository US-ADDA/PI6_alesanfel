package main.java.ejercicios.solution;

import java.util.List;

import org.jgrapht.GraphPath;

import main.java.ejercicios.classes.Producto;
import main.java.ejercicios.data.DatosEjercicio3;
import main.java.ejercicios.edge.AristaEjercicio3;
import main.java.ejercicios.vertex.VerticeEjercicio3;
import us.lsi.common.List2;
import us.lsi.common.Pair;

public class SolucionEjercicio3 {
	
	private final List<Pair<Producto, Double>> productos;
    private Double beneficio;
	
	public static SolucionEjercicio3 of(GraphPath<VerticeEjercicio3, AristaEjercicio3> path) {
		List<Integer> la = path.getEdgeList().stream().map(AristaEjercicio3::action).toList();
		return SolucionEjercicio3.of(la);
	}
	
	public static SolucionEjercicio3 of(List<Integer> ls) {
		return new SolucionEjercicio3(ls);
	}
	
	private SolucionEjercicio3(List<Integer> ls) {
		productos = List2.empty();
        beneficio = 0.;
        for (int i = 0; i < ls.size(); i++) {
            int value = ls.get(i);
            if (value > 0) {
                Producto producto = DatosEjercicio3.getProducto(i);
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
