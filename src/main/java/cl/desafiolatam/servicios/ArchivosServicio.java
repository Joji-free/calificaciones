package cl.desafiolatam.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import cl.desafiolatam.modelos.Alumno;
import cl.desafiolatam.modelos.Materia;
import cl.desafiolatam.modelos.MateriaEnum;

public class ArchivosServicio {

	private static List<Alumno> alumnosACargar=new ArrayList<Alumno>();
	static PromedioServicioImp promServicio = new PromedioServicioImp();
	
	
	public List<Alumno> cargarDatos(String ruta) throws FileNotFoundException
	{	
		//FELIPE: comprueba el archivo exista en la ruta indicada
		if(new File(ruta+"/notas.csv").exists())
		{
			//List<Alumno> alumnos = new ArrayList<Alumno>();
			BufferedReader br = new BufferedReader(new FileReader(ruta+"/notas.csv"));
			br.lines().map(line2 -> line2.split(",")).forEach(
					(fila) -> 
					{
						//la primera fila siempre se debe agregar
						if(alumnosACargar.size()==0)
						{
							alumnosACargar.add(new Alumno(fila[0], fila[1], "", "",new ArrayList<Materia>()));
						}
						else
						{
							if(alumnosACargar.stream().filter(x->x.getRut().equals(fila[0])).collect(Collectors.toList()).size()>0)
							{
								//alumno ya existe
							}
							else
							{
								alumnosACargar.add(new Alumno(fila[0], fila[1], "", "", new ArrayList<Materia>()));
							}
						}
						
						Materia materia = new Materia();
						alumnosACargar.forEach(
								(alumno)->
								{
									if(alumno.getRut().equals(fila[0]))
									{
										if(fila[2].equals("MATEMATICAS"))
										{
											materia.setNombre(MateriaEnum.MATEMATICAS);
										}
										else if(fila[2].equals("LENGUAJE"))
										{
											materia.setNombre(MateriaEnum.LENGUAJE);
										}
										else if(fila[2].equals("CIENCIA"))
										{
											materia.setNombre(MateriaEnum.CIENCIA);
										}
										else
										{
											materia.setNombre(MateriaEnum.HISTORIA);
										}
										
										if(alumno.getMaterias().stream().filter(y->y.getNombre().toString().equals(fila[2])).collect(Collectors.toList()).size()>0)
										{
											//ya tiene esa materia
											materia.getNotas().add(Double.parseDouble(fila[3]));
											
											//recorro las materias del alumno para agregar la nota a donde corresponda.
											for(Materia mat: alumno.getMaterias())
											{
												if(mat.getNombre().toString().equals(fila[2]))
												{
													mat.getNotas().add(Double.parseDouble(fila[3]));
												}
											}
										}
										else
										{
											materia.getNotas().add(Double.parseDouble(fila[3]));
											alumno.getMaterias().add(materia);
										}
									}
								}
								);	
					}
					);
			
			return alumnosACargar;
		}
		else
		{
			System.out.println("No existe archivo 'notas.csv' en la ruta indicada");
			return null;
		}

	}
	
	public void exportarDatos(Map<String, Alumno> alumnos, String ruta) throws IOException
	{
		//FELIPE: comprueba el archivo exista en la ruta indicada
		if(new File(ruta+"/").isDirectory())
		{
			File file = new File(ruta + "/Promedios.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			alumnos.forEach(
					(key, alumno) -> 
					{
						pw.write("Alumno : " + alumno.getRut() + " - " + alumno.getNombre());
						for(Materia materia : alumno.getMaterias())
						{
							pw.println("\tMateria :" + materia.getNombre() + " - Promedio : "+ promServicio.calcularPromedio(materia.getNotas()));
						}
						pw.println("");
					});
			pw.close();
			bw.close();
			
			System.out.println("Datos exportados correctamente.");
			System.out.println("---------------------------------------------");
			
		}
		else
		{
			System.out.println("No existe directorio ingresado");
		}
	}
}
