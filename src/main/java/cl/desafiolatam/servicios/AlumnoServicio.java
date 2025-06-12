package cl.desafiolatam.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.desafiolatam.modelos.Alumno;
import cl.desafiolatam.modelos.Materia;

public class AlumnoServicio {

	//para mantener la persistencia, el atributo debe ser static.
	private static Map<String, Alumno> listaAlumnos = new HashMap<>();
	
	//getter y setter
	public Map<String, Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(Map<String, Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	//fin getter y setter
	

	public void crearAlumno(Alumno alumno)
	{
		listaAlumnos.put(alumno.getRut(), alumno);
	}
	
	public void agregarMateria(String rutAlumno, Materia currentMate)
	{
		listaAlumnos.get(rutAlumno).getMaterias().add(currentMate);		
	}
	
	public List<Materia> materiasPorAlumno(String rutAlumno)
	{
		return listaAlumnos.get(rutAlumno).getMaterias();
	}
	
	public Map<String, Alumno> listarAlumnos()
	{
		return listaAlumnos;
	}

	@Override
	public String toString() {
		return "AlumnoServicio [listaAlumnos=" + listaAlumnos + "]";
	}
	
	
	
	
}
