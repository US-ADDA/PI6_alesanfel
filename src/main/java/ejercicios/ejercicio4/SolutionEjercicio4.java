package main.java.ejercicios.ejercicio4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.jgrapht.GraphPath;

import us.lsi.common.List2;

public class SolutionEjercicio4 {
	
	private final Map<Contenedor, List<Elemento>> elementosPorContenedor;
	
	public static SolutionEjercicio4 of(GraphPath<VertexEjercicio4, EdgeEjercicio4> path) {
		List<Integer> la = path.getEdgeList().stream().map(EdgeEjercicio4::action).toList();
		return SolutionEjercicio4.of(la);
	}
	
	public static SolutionEjercicio4 of(List<Integer> ls) {
		return new SolutionEjercicio4(ls);
	} 
	
	private SolutionEjercicio4(List<Integer> ls) {
		elementosPorContenedor = new HashMap<>();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) < DataEjercicio4.getNumContenedores()) {
                Elemento value = DataEjercicio4.getElemento(i);
                Contenedor key = DataEjercicio4.getContenedor(ls.get(i));
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
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .reduce("", (ac, nx) -> String.format("%s%s%n", ac, nx));
        return String.format("Reparto obtenido:%n%sNúmero elementos: %s", cadenaContenedores, contenedoresLLenos());
    }
	
	public Integer contenedoresLLenos() {
		Integer c = 0;
		for (Entry<Contenedor, List<Elemento>> entry: elementosPorContenedor.entrySet()) {
			Integer consumido = entry.getValue().stream().mapToInt(Elemento::tamano).sum();
			if (Objects.equals(entry.getKey().capacidad(), consumido))
				c++;
		}
		return c;
	}
}
