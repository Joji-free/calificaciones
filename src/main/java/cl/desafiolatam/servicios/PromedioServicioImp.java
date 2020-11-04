package cl.desafiolatam.servicios;

import java.util.List;

public class PromedioServicioImp {

	public double calcularPromedio(List<Double> notas)
	{
		return Math.floor(notas.stream().mapToDouble(nota->nota).average().getAsDouble() * 10) / 10;
	}
	
}
