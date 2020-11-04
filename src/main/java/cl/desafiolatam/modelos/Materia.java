package cl.desafiolatam.modelos;

import java.util.ArrayList;
import java.util.List;

public class Materia {

	private MateriaEnum nombre;
	private List<Double> notas = new ArrayList<Double>();
	
	public Materia(MateriaEnum nombre, List<Double> notas) {
		super();
		this.nombre = nombre;
		this.notas = notas;
	}
	
	public Materia(){}

	public MateriaEnum getNombre() {
		return nombre;
	}

	public void setNombre(MateriaEnum nombre) {
		this.nombre = nombre;
	}

	public List<Double> getNotas() {
		return notas;
	}

	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}

	//descomentar para agregar nota de la otra forma comentada en Menu.
//	public void setNotas(Double notas) {
//		this.notas.add(notas);
//	}
	
	@Override
	public String toString() {
		return "Materia [nombre=" + nombre + ", notas=" + notas + "]";
	}

	
	
	
	
}
