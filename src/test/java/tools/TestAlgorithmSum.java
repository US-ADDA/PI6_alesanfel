package test.java.tools;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.jgrapht.GraphPath;

import us.lsi.common.TriFunction;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;

public class TestAlgorithmSum<V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
        extends TestAlgorithm<V, E, S> {

    public TestAlgorithmSum(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                            TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution,
                            Predicate<V> constraints) {
        super(initialData, initialVertex, goal, heuristic, solution, constraints);
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithmSum<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                                 TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution) {
        return new TestAlgorithmSum<>(initialData, initialVertex, goal, heuristic, solution, null);
    }

    public static <V extends VirtualVertex<V, E, Integer>, E extends SimpleEdgeAction<V, Integer>, S>
    TestAlgorithmSum<V, E, S> of(Consumer<String> initialData, Supplier<V> initialVertex, Predicate<V> goal,
                                 TriFunction<V, Predicate<V>, V, Double> heuristic, Function<GraphPath<V, E>, S> solution, Predicate<V> constraints) {
        return new TestAlgorithmSum<>(initialData, initialVertex, goal, heuristic, solution, constraints);
    }

    public EGraph<V, E> getGraph() {
        return (constraints == null) ?
                SimpleVirtualGraph.sum(initialVertex.get(), goal, SimpleEdgeAction::weight) :
                SimpleVirtualGraph.sum(initialVertex.get(), goal, SimpleEdgeAction::weight, constraints);
    }
}
