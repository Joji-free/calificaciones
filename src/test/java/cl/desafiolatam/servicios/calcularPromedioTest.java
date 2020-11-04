package cl.desafiolatam.servicios;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cl.desafiolatam.servicios.PromedioServicioImp;

public class calcularPromedioTest {

	//void testCalcularPromedio()
	
	@Test
	@DisplayName("Test calcularPromedio")
	void testCalcularPromedio()
	{
	    List<Double> notas = new ArrayList<>(); 
        notas.add(1.0); 
        notas.add(2.0); 
        notas.add(3.0); 
        notas.add(4.0); 
        notas.add(5.0); 
  
		PromedioServicioImp promServImp = new PromedioServicioImp();
		assertEquals(3, promServImp.calcularPromedio(notas));
	}
}
