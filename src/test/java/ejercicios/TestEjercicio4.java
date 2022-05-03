package test.java.ejercicios;

import main.java.ejercicios.ejercicio4.DataEjercicio4;
import main.java.ejercicios.ejercicio4.EdgeEjercicio4;
import main.java.ejercicios.ejercicio4.HeuristicEjercicio4;
import main.java.ejercicios.ejercicio4.SolutionEjercicio4;
import main.java.ejercicios.ejercicio4.VertexEjercicio4;
import test.java.tools.TestAlgorithmLast;

public class TestEjercicio4 {
	
	private static final String dataPath = "data/PI6Ej4DatosEntrada";
	
	public static void main(String[] args) {
		
		String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt"};
		
		TestAlgorithmLast<VertexEjercicio4, EdgeEjercicio4, SolutionEjercicio4> algorithms = TestAlgorithmLast.of(
				DataEjercicio4::initialData, 
				VertexEjercicio4::initialVertex, 
				VertexEjercicio4.goal(),
				HeuristicEjercicio4::heuristic, 
				SolutionEjercicio4::of,
				VertexEjercicio4::weight,
				VertexEjercicio4.constraint());
		
		algorithms.testAStar(data);
		algorithms.testBT(data);
		algorithms.testPDR(data);
	}
}
