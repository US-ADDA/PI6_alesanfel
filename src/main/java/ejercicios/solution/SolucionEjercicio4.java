package main.java.ejercicios.solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;

import main.java.ejercicios.classes.Contenedor;
import main.java.ejercicios.classes.Elemento;
import main.java.ejercicios.data.DatosEjercicio4;
import main.java.ejercicios.edge.AristaEjercicio4;
import main.java.ejercicios.vertex.VerticeEjercicio4;
import us.lsi.common.List2;

public class SolucionEjercicio4 {
	
	private final Map<Contenedor, List<Elemento>> elementosPorContenedor;
	
	public static SolucionEjercicio4 of(GraphPath<VerticeEjercicio4, AristaEjercicio4> path) {
		List<Integer> la = path.getEdgeList().stream().map(AristaEjercicio4::action).toList();
		return SolucionEjercicio4.of(la);
	}
	
	public static SolucionEjercicio4 of(List<Integer> ls) {
		return new SolucionEjercicio4(ls);
	} 
	
	private SolucionEjercicio4(List<Integer> ls) {
		elementosPorContenedor = new HashMap<>();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) < DatosEjercicio4.getNumContenedores()) {
                Elemento value = DatosEjercicio4.getElemento(i);
                Contenedor key = DatosEjercicio4.getContenedor(ls.get(i));
                if (elementosPorContenedor.containsKey(key))
                    elementosPorContenedor.get(key).add(value);
                else
                    elementosPorContenedor.put(key, List2.of(value));
            }
        }
	}
	
	@Override
    public String toString() {
        var cadenaContenedores = elementosPorContenedor.entrySet().stream()
                .map(entry -> entry.getKey().id() + ": " + entry.getValue().stream().map(Elemento::id).toList())
                .reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
        return String.format("Reparto obtenido:\n%sNúmero elementos: %s", cadenaContenedores, elementosPorContenedor.values().stream().mapToLong(List::size).sum());
    }
}
