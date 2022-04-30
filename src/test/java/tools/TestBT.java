package Tests;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;

public class TestBT<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> {
	
	private Consumer<String> initDatos;
	private V inicial;
	private Predicate<V> goal;
	private Function<V,E> greedyEdge;
	private TriFunction<V,Predicate<V>,V,Double> heuristic;
	private Function<GraphPath<V,E>,S> solucion;
	
	
	
	private TestBT(Consumer<String> initDatos, V inicial, Predicate<V> goal, Function<V, E> greedyEdge,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solucion) {
		this.initDatos = initDatos;
		this.inicial = inicial;
		this.goal = goal;
		this.greedyEdge = greedyEdge;
		this.heuristic = heuristic;
		this.solucion = solucion;
	}
	
	public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
			TestBT<V, E, S> of(Consumer<String> initDatos, V inicial, Predicate<V> goal, Function<V, E> greedyEdge,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solucion) {
		return new TestBT<>(initDatos, inicial, goal, greedyEdge, heuristic, solucion);
	}

	public void testFile(String ... dataPath) {
		for (var i = 0; i < dataPath.length; i++) {
			initDatos.accept(dataPath[i]);
			var graph = SimpleVirtualGraph.sum(inicial, goal, SimpleEdgeAction::weight);
			var rr = GreedyOnGraph.of(graph, greedyEdge);
			var greedyPath = rr.path();
			var ms = BackTracking.of(graph, heuristic, solucion, BTType.Max);
			ms.withGraph = true;
			ms.bestValue = greedyPath.getWeight();
			ms.optimalPath = greedyPath;
			ms.search();
			var camino = ms.getSolution();
			if (camino.isPresent()) {
				S res = camino.get();
				System.out.println("\n#### Algoritmo Voraz ####");
				S solucionGreedy = solucion.apply(greedyPath);
				System.out.println(solucionGreedy);
				System.out.println("\n#### Algoritmo BT ####");
				System.out.println(res);
			} else {
				System.out.println("Solución no encontrada");
			}
		}
		
	}

}
