package Tests;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;

public class TestPDR<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> {
	
	private Consumer<String> initDatos;
	private V inicial;
	private Predicate<V> goal;
	private Function<V,E> greedyEdge;
	private TriFunction<V,Predicate<V>,V,Double> heuristic;
	private Function<GraphPath<V,E>,S> solucion;
	
	private TestPDR(Consumer<String> initDatos, V inicial, Predicate<V> goal, Function<V, E> greedyEdge,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solucion) {
		this.initDatos = initDatos;
		this.inicial = inicial;
		this.goal = goal;
		this.greedyEdge = greedyEdge;
		this.heuristic = heuristic;
		this.solucion = solucion;
	}
	
	public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> 
			TestPDR<V, E, S> of(Consumer<String> initDatos, V inicial, Predicate<V> goal, Function<V, E> greedyEdge,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solucion) {
		return new TestPDR<>(initDatos, inicial, goal, greedyEdge, null, solucion);
	}

	public void testFile(String ... dataPath) {
		for (var i = 0; i < dataPath.length; i++) {
			initDatos.accept(dataPath[i]);
			var graph = SimpleVirtualGraph.sum(inicial, goal, SimpleEdgeAction::weight);
			var rr = GreedyOnGraph.of(graph, greedyEdge);
			var greedyPath = rr.path();
			var ms = DynamicProgrammingReduction.of(graph, heuristic, PDType.Max);
			ms.optimalPath = greedyPath;
			ms.withGraph = true;
			var opcional = ms.search();
			if (opcional.isPresent()) {
				S solucionGreedy = solucion.apply(opcional.get());
				System.out.println("\n#### Algoritmo PD ####");
				System.out.println(solucionGreedy);
			} else {
				System.out.println("Solución no encontrada");
			}
		}
	}
}
