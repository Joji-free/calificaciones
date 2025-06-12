package cl.desafiolatam.modelos;

import java.util.List;

public class Alumno {

	private Integer id;
	private String nombre;
	private String apellido;
	private String direccion;
	private List<Materia> materias;
	
	public Alumno(Integer id, String nombre, String apellido, String direccion, List<Materia> materias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.materias = materias;
	}
	public Alumno() {}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(String rut) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", materias=" + materias + "]";
	}


}
