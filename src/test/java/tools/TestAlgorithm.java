package test.java.tools;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.jgrapht.GraphPath;

import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;

public class TestAlgorithm<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> {
	
	private Consumer<String> initialData;
	private Supplier<V> initialVertex;
	private Predicate<V> goal;
	private TriFunction<V,Predicate<V>,V,Double> heuristic;
	private Function<GraphPath<V,E>,S> solution;


	private TestAlgorithm(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution) {
		this.initialData = initialData;
		this.initialVertex = initialVertex;
		this.goal = goal;
		this.heuristic = heuristic;
		this.solution = solution;
	}

	public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> 
			TestAlgorithm<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
			TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution) {
		return new TestAlgorithm<>(initialData, initialVertex, goal, heuristic, solution);
	}
	
	public void testAStar(String ... dataPath) {
		for (var i = 0; i < dataPath.length; i++) {
			initialData.accept(dataPath[i]);
			EGraph<V, E> graph = SimpleVirtualGraph.sum(initialVertex.get(), goal, SimpleEdgeAction::weight);
			AStar<V, E> algorithm = AStar.of(graph, heuristic, AStarType.Max);
			Optional<GraphPath<V, E>> posibleSolution = algorithm.search();
			if (posibleSolution.isPresent()) {
				S res = solution.apply(posibleSolution.get());
				System.out.println("\n#### Algoritmo A* ####");
				System.out.println("Solución para el fichero: " + dataPath[i]);
				System.out.println(res);
			} else
				System.out.println("Solución no encontrada.");
		}
	}
	
	public void testBT(String ... dataPath) {
		for (var i = 0; i < dataPath.length; i++) {
			initialData.accept(dataPath[i]);
			EGraph<V, E> graph = SimpleVirtualGraph.sum(initialVertex.get(), goal, SimpleEdgeAction::weight);
			BackTracking<V, E, S> algorithm = BackTracking.of(graph, heuristic, solution, BTType.Max);
			algorithm.search();
			Optional<S> posibleSolution = algorithm.getSolution();
			
			if (posibleSolution.isPresent()) {
				System.out.println("\n#### Algoritmo BT ####");
				System.out.println("Solución para el fichero: " + dataPath[i]);
				System.out.println(posibleSolution.get());
				
			} else
				System.out.println("Solución no encontrada");
		}	
	}
	
	public void testPDR(String ... dataPath) {
		for (var i = 0; i < dataPath.length; i++) {
			initialData.accept(dataPath[i]);
			EGraph<V, E> graph = SimpleVirtualGraph.sum(initialVertex.get(),goal,SimpleEdgeAction::weight);
			DynamicProgrammingReduction<V, E> algorithm = DynamicProgrammingReduction.of(graph, heuristic, PDType.Max);
			Optional<GraphPath<V, E>> posibleSolution = algorithm.search();
			
			if (posibleSolution.isPresent()) {
				System.out.println("\n#### Algoritmo PD ####");
				System.out.println("Solución para el fichero: " + dataPath[i]);
				System.out.println(solution.apply(posibleSolution.get()));
			}
			else
				System.out.println("Solución no encontrada.");
		}
	}
}
