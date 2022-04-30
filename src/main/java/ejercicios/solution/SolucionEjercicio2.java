package main.java.ejercicios.solution;

import java.util.List;
import org.jgrapht.GraphPath;

import main.java.ejercicios.classes.Candidato;
import main.java.ejercicios.data.DatosEjercicio2;
import main.java.ejercicios.edge.AristaEjercicio1;
import main.java.ejercicios.vertex.VerticeEjercicio1;
import us.lsi.common.List2;

public class SolucionEjercicio2 {
	
	private final List<Candidato> candidatos;
    private Double valoracionMedia, valoracionTotal, gasto;
	
	public static SolucionEjercicio2 of(GraphPath<VerticeEjercicio1, AristaEjercicio1> path) {
		List<Integer> la = path.getEdgeList().stream().map(AristaEjercicio1::action).toList();
		return SolucionEjercicio2.of(la);
	}
	
	public static SolucionEjercicio2 of(List<Integer> ls) {
		return new SolucionEjercicio2(ls);
	}
	
	public SolucionEjercicio2(List<Integer> ls) {
		candidatos = List2.empty();
        valoracionMedia = 0.;
        valoracionTotal = 0.;
        gasto = 0.;
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == 1) {
                Candidato candidato = DatosEjercicio2.getCandidato(i);
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
