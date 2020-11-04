package cl.desafiolatam.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cl.desafiolatam.modelos.Alumno;
import cl.desafiolatam.modelos.Materia;
import cl.desafiolatam.modelos.MateriaEnum;


public class AlumnoServicioTest {

	
	//Atributo alumnoServicio, instancia de AlumnoServicioImp.
	//Atributo alumnoServicioMock, mock de AlumnoServicioImp para simular comportamiento.
	//Atributo matematicas, instancia de una nueva Materia.
	//Atributo lenguaje, instancia de una nueva Materia.
	//Atributo mapu, instancia de Alumno.
	private static AlumnoServicio alumnoServicio;
	private static AlumnoServicio alumnoServicioMock;
	private static Materia matematicas;
	private static Materia lenguaje;
	private static Alumno mapi;
	
	//Método setup, como fixture para reutilizar datos de prueba
	@BeforeAll
	static void setup()
	{
		alumnoServicio=new AlumnoServicio();
		alumnoServicioMock = mock(AlumnoServicio.class);
		List<Double> notasMate = new ArrayList<>(Arrays.asList(2.0, 3.0, 4.0));
		List<Double> notasLeng = new ArrayList<>(Arrays.asList(5.0, 6.0, 7.0));
		matematicas = new Materia(MateriaEnum.MATEMATICAS, notasMate);
		lenguaje = new Materia(MateriaEnum.LENGUAJE, notasLeng);
		List<Materia> materias= new ArrayList<Materia>(Arrays.asList(matematicas, lenguaje));
		mapi = new Alumno("1", "pepe", "doe", "dir de prueba 123", new ArrayList<Materia>());
	}
	
	
	//Método crearAlumnoTest para verificar el funcionamiento de crearAlumno
	//FELIPE: El método no tiene retorno, sin embargo cuando se crea un alumno, la lista de debe incrementarse en uno.
	@Test
	@DisplayName("test crearAlumno")
	void crearAlumnoTest()
	{
		int alumnosCreados = alumnoServicio.getListaAlumnos().size();
		alumnoServicio.crearAlumno(mapi);
		assertEquals(alumnoServicio.getListaAlumnos().size(), alumnosCreados+1);
	}

	//Método agregarMateriaTest para verificar el funcionamiento de agregarMateria.
	@Test
	@DisplayName("test agregarMateria")
	void agregarMateriaTest()
	{
		alumnoServicio.crearAlumno(mapi);
		//cuenta las materias del alumno antes de agregarle una
		int materiasPorAlumno = alumnoServicio.materiasPorAlumno("1").size();
		//se agrega la materia
		alumnoServicio.agregarMateria("1", matematicas);
		//se compara lo que tenía antes + la nueva agregada
		assertEquals(alumnoServicio.materiasPorAlumno("1").size(), materiasPorAlumno+1);
	}	
	
	//Método materiasPorAlumnosTest, usando mock para verificar el funcionamiento de materiasPorAlumnos
	@Test
	@DisplayName("test materiasPorAlumno")
	void materiasPorAlumnosTest()
	{
		List<Materia> mockRespuesta = new ArrayList<>();
		when(alumnoServicioMock.materiasPorAlumno("1")).thenReturn(mockRespuesta);
		
		List<Materia> listarRes = alumnoServicioMock.materiasPorAlumno("1");
		assertEquals(mockRespuesta, listarRes);
		
		verify(alumnoServicioMock).materiasPorAlumno("1");
	}
	
	//Método listarAlumnosTest para verificar el funcionamiento de listarAlumnosTest.
	@Test
	@DisplayName("test materiasPorAlumno")
	void listarAlumnosTest()
	{
		mapi.getMaterias().add(matematicas);
		mapi.getMaterias().add(lenguaje);
		Map<String, Alumno> mockRespuesta = new HashMap<>();
		when(alumnoServicioMock.listarAlumnos()).thenReturn(mockRespuesta);
		Map<String, Alumno> listaRes= alumnoServicioMock.listarAlumnos();
		assertEquals(mockRespuesta, listaRes);
		
		verify(alumnoServicioMock).listarAlumnos();
	}

	
	
}
