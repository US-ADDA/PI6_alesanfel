package test.java.tools;

import org.jgrapht.GraphPath;
import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.DynamicProgramming;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.VirtualVertex;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestAlgorithm<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S> {

    private Consumer<String> initialData;
    protected Supplier<V> initialVertex;
    protected Predicate<V> goal;
    private TriFunction<V, Predicate<V>, V, Double> heuristic;
    private Function<GraphPath<V, E>, S> solution;
    protected Predicate<V> constraints;

    public TestAlgorithm(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                         TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution,
                         Predicate<V> constraints) {
        this.initialData = initialData;
        this.initialVertex = initialVertex;
        this.goal = goal;
        this.heuristic = heuristic;
        this.solution = solution;
        this.constraints = constraints;
    }


    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithm<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                              TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution) {
        return new TestAlgorithm<>(initialData, initialVertex, goal, heuristic, solution, null);
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithm<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                              TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution, Predicate<V> constraints) {
        return new TestAlgorithm<>(initialData, initialVertex, goal, heuristic, solution, constraints);
    }

    public EGraph<V, E> getGraph() {
        return null;
    }

    public void testAStar(String... dataPath) {
        for (String file : dataPath)
            testAStar(file);
    }

    public void testAStar(String file) {
        initialData.accept(file);
        EGraph<V, E> graph = getGraph();
        AStar<V, E> algorithm = AStar.of(graph, heuristic, AStar.AStarType.Max);
        Optional<GraphPath<V, E>> posibleSolution = algorithm.search();
        if (posibleSolution.isPresent()) {
            S res = solution.apply(posibleSolution.get());
            System.out.println("\n#### Algoritmo A* ####");
            System.out.println("Solución para el fichero: " + file);
            System.out.println(res);
        } else
            System.out.println("Solución no encontrada.");
    }

    public void testBT(String... dataPath) {
        for (String file : dataPath)
            testBT(file);
    }


    public void testBT(String file) {
        initialData.accept(file);
        EGraph<V, E> graph = getGraph();
        BackTracking<V, E, S> algorithm = BackTracking.of(graph, heuristic, solution, BackTracking.BTType.Max);
        algorithm.search();
        Optional<S> posibleSolution = algorithm.getSolution();

        if (posibleSolution.isPresent()) {
            System.out.println("\n#### Algoritmo BT ####");
            System.out.println("Solución para el fichero: " + file);
            System.out.println(posibleSolution.get());

        } else
            System.out.println("Solución no encontrada");
    }



    public void testPDR(String... dataPath) {
        for (String file : dataPath)
            testPDR(file);
    }

    public void testPDR(String file) {
        initialData.accept(file);
        EGraph<V, E> graph = getGraph();
        DynamicProgrammingReduction<V, E> algorithm = DynamicProgrammingReduction.of(graph, heuristic, DynamicProgramming.PDType.Max);
        Optional<GraphPath<V, E>> posibleSolution = algorithm.search();

        if (posibleSolution.isPresent()) {
            System.out.println("\n#### Algoritmo PD ####");
            System.out.println("Solución para el fichero: " + file);
            System.out.println(solution.apply(posibleSolution.get()));
        } else
            System.out.println("Solución no encontrada.");
    }
}
