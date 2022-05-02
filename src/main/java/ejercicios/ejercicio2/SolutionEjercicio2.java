package main.java.ejercicios.ejercicio2;

import java.util.List;
import org.jgrapht.GraphPath;

import main.java.ejercicios.classes.Candidato;
import main.java.ejercicios.ejercicio1.EdgeEjercicio1;
import main.java.ejercicios.ejercicio1.VertexEjercicio1;
import us.lsi.common.List2;

public class SolutionEjercicio2 {
	
	private final List<Candidato> candidatos;
    private Double valoracionMedia, valoracionTotal, gasto;
	
	public static SolutionEjercicio2 of(GraphPath<VertexEjercicio1, EdgeEjercicio1> path) {
		List<Integer> la = path.getEdgeList().stream().map(EdgeEjercicio1::action).toList();
		return SolutionEjercicio2.of(la);
	}
	
	public static SolutionEjercicio2 of(List<Integer> ls) {
		return new SolutionEjercicio2(ls);
	}
	
	public SolutionEjercicio2(List<Integer> ls) {
		candidatos = List2.empty();
        valoracionMedia = 0.;
        valoracionTotal = 0.;
        gasto = 0.;
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == 1) {
                Candidato candidato = DataEjercicio2.getCandidato(i);
                candidatos.add(candidato);
                valoracionTotal += candidato.valoracion();
                gasto += candidato.sueldo();
            }
        }
        valoracionMedia = valoracionTotal / candidatos.size();
	}
	
	@Override
    public String toString() {
        String cadenaCandidatos = candidatos.stream().map(Candidato::toString).reduce("", (ac, nx) -> String.format("%s%s%n", ac, nx));
        return String.format("Candidatos Seleccionados:%n%sValoraci�n total: %.1f; Gasto: %.1f; V. media: %.1f", cadenaCandidatos, valoracionTotal, gasto, valoracionMedia);
    }

}
