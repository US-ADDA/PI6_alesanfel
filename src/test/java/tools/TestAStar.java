package Tests;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;

public class TestAStar<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> {
	
	private Consumer<String> initDatos;
	private V inicial;
	private Predicate<V> goal;
	private TriFunction<V,Predicate<V>,V,Double> heuristic;
	private Function<GraphPath<V,E>,S> solucion;

	private TestAStar(Consumer<String> initDatos, V inicial, Predicate<V> goal,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solucion) {
		this.initDatos = initDatos;
		this.inicial = inicial;
		this.goal = goal;
		this.heuristic = heuristic;
		this.solucion = solucion;
	}

	public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> 
			TestAStar<V, E, S> of(Consumer<String> initDatos, V inicial, Predicate<V> goal,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solucion) {
		return new TestAStar<>(initDatos, inicial, goal, heuristic, solucion);
	}


	public void testFile(String ... dataPath) {
		for (var i = 0; i < dataPath.length; i++) {
			initDatos.accept(dataPath[i]);
			var graph = SimpleVirtualGraph.sum(inicial, goal, SimpleEdgeAction::weight);
			var ms = AStar.of(graph, heuristic, AStarType.Max);
			var camino = ms.search();
			if (camino.isPresent()) {
				S res = solucion.apply(camino.get());
				System.out.println("\n#### Algoritmo A* ####");
				System.out.println(res);
			} else {
				System.out.println("Solución no encontrada");
			}
		}
	}

}
