package test.java.ejercicios;

import main.java.ejercicios.ejercicio3.DataEjercicio3;
import main.java.ejercicios.ejercicio3.EdgeEjercicio3;
import main.java.ejercicios.ejercicio3.HeuristicEjercicio3;
import main.java.ejercicios.ejercicio3.SolutionEjercicio3;
import main.java.ejercicios.ejercicio3.VertexEjercicio3;
import test.java.tools.TestAlgorithmSum;

public class TestEjercicio3 {
	
	private static final String dataPath = "data/PI6Ej3DatosEntrada";
	
	public static void main(String[] args) {
		
		String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt"};
		
		TestAlgorithmSum<VertexEjercicio3, EdgeEjercicio3, SolutionEjercicio3> algorithms = TestAlgorithmSum.of(
				DataEjercicio3::initialData, 
				VertexEjercicio3::initialVertex, 
				VertexEjercicio3.goal(),
				HeuristicEjercicio3::heuristic, 
				SolutionEjercicio3::of);
		
		algorithms.testAStar(data);
		algorithms.testBT(data);
		algorithms.testPDR(data);
	}

}
