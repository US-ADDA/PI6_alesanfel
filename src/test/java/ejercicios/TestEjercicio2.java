package test.java.ejercicios;

import main.java.ejercicios.ejercicio2.DataEjercicio2;
import main.java.ejercicios.ejercicio2.EdgeEjercicio2;
import main.java.ejercicios.ejercicio2.HeuristicEjercicio2;
import main.java.ejercicios.ejercicio2.SolutionEjercicio2;
import main.java.ejercicios.ejercicio2.VertexEjercicio2;
import test.java.tools.TestAlgorithmSum;

public class TestEjercicio2 {
	
	private static final String dataPath = "data/PI6Ej2DatosEntrada";
	
	public static void main(String[] args) {
		
		String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt"};
		
		TestAlgorithmSum<VertexEjercicio2, EdgeEjercicio2, SolutionEjercicio2> algorithms = TestAlgorithmSum.of(
				DataEjercicio2::initialData, 
				VertexEjercicio2::initialVertex, 
				VertexEjercicio2.goal(),
				HeuristicEjercicio2::heuristic, 
				SolutionEjercicio2::of,
				VertexEjercicio2.constraints());
		
		algorithms.testAStar(data);
		algorithms.testBT(data);
		algorithms.testPDR(data);
	}
}
