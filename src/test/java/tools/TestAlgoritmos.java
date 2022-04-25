package test.java.tools;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.TriFunction;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.graphs.virtual.VirtualVertex;
import us.lsi.mochila.datos.DatosMochila;

public class TestAlgoritmos<V extends VirtualVertex<V,E,A>, E extends SimpleEdgeAction<V,A>, A, S> {

	public String gvPath;
	private Consumer<String> init;
	private Integer c;
	private V initialVertex, lastVertex;
	private Predicate<V> goal;
	private Function<E, Double> weight;
	private TriFunction<V,Predicate<V>,V,Double> heuristic;
	private Consumer<GraphPath<V, E>> solution;
	// private Function<V, String> text;
	
	// BTT
	private Function<V, E> greedyEdge;
	private Function<GraphPath<V,E>,S> soltionOf;
	// private Function<GraphPath<V, E>, S> solutionGet;
	
	
	public void AStart(AStarType type, String ... files) {
		Locale.setDefault(new Locale("en", "US"));
		for(var i = 0; i < files.length; i++) {
			init.accept(files[i]);
			
			EGraph<V, E> graph = 
				SimpleVirtualGraph.sum(initialVertex,goal,weight,lastVertex);		
		
			AStar<V, E> ms = AStar.of(graph, heuristic, type);
			
			if (ms.search().isPresent()) {
				GraphPath<V, E> path = ms.search().get();
				solution.accept(path);
				// SimpleDirectedGraph<V, E> r = ms.graph();
				// GraphColors.toDot(r,gvPath + "AStart" +c+".gv",
				//		text::apply,
				//		e->e.action().toString(),
				//		v->GraphColors.colorIf(Color.red,path.getVertexList().contains(v)),
				//		e->GraphColors.colorIf(Color.red,path.getEdgeList().contains(e))
				// );
				c++;
			} else {
				System.out.println("No se ha encontrado solución.");
			}
		}
	}
	
	public void BTT(BTType type, String ... files) {
		Locale.setDefault(new Locale("en", "US"));
		for(var i = 0; i < files.length; i++) {
			DatosMochila.iniDatos("ficheros/objetosMochila.txt");
		
			EGraph<V, E> graph = SimpleVirtualGraph.sum(initialVertex,goal,weight,lastVertex);		
		
	
			GreedyOnGraph<V, E> rr = GreedyOnGraph.of(graph,greedyEdge);
		
			GraphPath<V, E> path = rr.path();
		
			BackTracking<V, E, S> ms = BackTracking.of(
					graph,
					heuristic,
					soltionOf,
					type);		
		
			ms.withGraph = true;
			ms.bestValue = path.getWeight();
			ms.optimalPath = path;
		
			ms.search();
			if (ms.optimalPath().isPresent()) {
				solution.accept(ms.optimalPath().get());
				
				// GraphPath<V, E> sp = ms.optimalPath().get();
				// GraphColors.toDot(ms.graph(),"BTT" +c+".gv",
				//		text::apply,
				//		e->e.action().toString(),
				//		v->GraphColors.colorIf(Color.red,v.equals(lastVertex)),
				//		e->GraphColors.colorIf(Color.red,sp.getEdgeList().contains(e))
				// );
			} else
				System.out.println("No se ha encontrado solución.");
		}
	}
	
	public void PDR(PDType type, String ... files) {
		Locale.setDefault(new Locale("en", "US"));
		for(var i = 0; i < files.length; i++) {
			DatosMochila.iniDatos(files[0]);
		
			EGraph<V, E> graph = SimpleVirtualGraph.sum(initialVertex,goal,weight,lastVertex);	
		
			GreedyOnGraph<V, E> rr = GreedyOnGraph.of(graph,greedyEdge::apply);
		
			GraphPath<V, E> path = rr.path();	
			Double bv = path.getWeight();
	
			DynamicProgrammingReduction<V, E> ms = DynamicProgrammingReduction.of(graph, heuristic,type);
		
			ms.bestValue = bv;
			ms.optimalPath = path;
		
			ms.withGraph = true;
			Optional<GraphPath<V, E>>  sp = ms.search();
			if (sp.isPresent()) {
				solution.accept(sp.get());
				
				// Predicate<V> pv = v->ms.optimalPath().get().getVertexList().contains(v);
				// Predicate<E> pe= e->ms.optimalPath().get().getEdgeList().contains(e);
		
				// GraphColors.toDot(ms.outGraph,gvPath + "PDR" +c+".gv",
				//		text::apply,
				//		e->e.action().toString(),
				//		v->GraphColors.all(GraphColors.colorIf(Color.red,pv.test(v)),GraphColors.styleIf(Style.bold,pv.test(v))),
				//		e->GraphColors.all(GraphColors.colorIf(Color.red,pe.test(e)),GraphColors.styleIf(Style.bold,pe.test(e)))
				// );
			} else
				System.out.println("No se ha enconstrado solución.");
		}
	}
	
	
	
	
}
