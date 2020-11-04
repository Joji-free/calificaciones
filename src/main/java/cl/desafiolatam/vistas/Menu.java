package cl.desafiolatam.vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cl.desafiolatam.modelos.Alumno;
import cl.desafiolatam.modelos.Materia;
import cl.desafiolatam.modelos.MateriaEnum;
import cl.desafiolatam.servicios.AlumnoServicio;
import cl.desafiolatam.servicios.ArchivosServicio;

public class Menu extends MenuTemplate{

	private AlumnoServicio alumnoServicio=new AlumnoServicio();
	private ArchivosServicio archivoServicio=new ArchivosServicio();
	private Scanner sc;
	
	
	
	@Override
	void exportarDatos() {
		
		
		try {
			
			if(alumnoServicio.getListaAlumnos().size() == 0)
			{
				System.out.println("Sin alumnos cargados");
				System.out.println("-------------------------------------");
			}
			else
			{
				System.out.println("--------------------------------------------- Exportar Datos");
				sc = new Scanner(System.in);
				System.out.println("Ingresa la ruta en donde se guardará el archivo promedios.txt :");
				String ruta = sc.nextLine();
				
				archivoServicio.exportarDatos(alumnoServicio.getListaAlumnos(), ruta);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Override
	void cargarDatos()
	{
		System.out.println("--------------------------------------------- Cargar Datos");
		sc = new Scanner(System.in);
		System.out.println("Ingresa la ruta en donde se encuentra el archivo notas.csv :");
		String ruta = sc.nextLine();
		
		try {
			archivoServicio.cargarDatos(ruta).forEach(alumno->{
				alumnoServicio.crearAlumno(alumno);
			});
			
			System.out.println("Datos cargados correctamente.");
			System.out.println("---------------------------------------------");
			
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Override
	void crearAlummno()
	{	
		sc = new Scanner(System.in);
		System.out.println("----------------------------- Crear Alumno");
		
		System.out.printf("Ingresa RUT: \n");
		String rut = sc.nextLine();
		
		System.out.printf("Ingrese nombre: \n");
		String nombre = sc.nextLine();
		
		System.out.printf("Ingrese apellido: \n");
		String apellido = sc.nextLine();
		
		System.out.printf("Ingrese dirección: \n");
		String direccion = sc.nextLine();
		
		List<Materia> materias = new ArrayList<Materia>();
		
		Alumno nuevoAlumno = new Alumno(rut, nombre, apellido, direccion, materias);
		alumnoServicio.crearAlumno(nuevoAlumno);
		
		System.out.println("----------------------------- ");
		
	}
	
	@Override
	void agregarMateria()
	{	
		sc = new Scanner(System.in);
		System.out.println("----------------------------- Agregar Materia");
		
		System.out.printf("Ingresa rut del alumno: \n");
		String rut = sc.nextLine();
		
		if(alumnoServicio.getListaAlumnos().containsKey(rut))
		{
			Materia materiaNueva = new Materia();
			
			int contador = 1;
			for(MateriaEnum materia:MateriaEnum.values())
			{
				System.out.println(contador+". "+materia);
				contador++;
			}
			System.out.println("Selecciona una Materia: ");
			try 
			{
				sc = new Scanner(System.in);
				int opcionElegida = sc.nextInt();
				boolean flag = true;
				switch (opcionElegida)
				{
				case 1:
					 materiaNueva.setNombre(MateriaEnum.MATEMATICAS);
					break;
				case 2:
					materiaNueva.setNombre(MateriaEnum.LENGUAJE);
					break;
				case 3:
					materiaNueva.setNombre(MateriaEnum.CIENCIA);
					break;
				case 4:
					materiaNueva.setNombre(MateriaEnum.HISTORIA);
					break;
				default:
					flag=false;
					System.out.println("Opción inválida");
					System.out.println("--------------------");
					break;
				}
				
				if(flag)
				{
					alumnoServicio.agregarMateria(rut, materiaNueva);
					System.out.println("Materia agregada");
				}
			}
			catch (Exception e)
			{
				System.out.println("Error en el formato de la opción, debe digitar un número");
				System.out.println("--------------------");
			}
		}
		else
		{
			System.out.println("Alumno no existente");
		}
		System.out.println("----------------------------- ");
	}
	
	@Override
	void agregarNotaPasoUno()
	{
		sc = new Scanner(System.in);
		System.out.println("----------------------------- Agregar Nota");
		System.out.printf("Ingresa rut del alumno: \n");
		String rut = sc.nextLine();
		
		if(alumnoServicio.getListaAlumnos().containsKey(rut))
		{
			if(alumnoServicio.getListaAlumnos().get(rut).getMaterias().size()==0)
			{
				System.out.println("Alumno no tiene materias cargadas.");
			}
			else
			{
				System.out.println("Alumno tiene las siguientes materias agregadas");
				int contador = 0;
				for (Materia materia: alumnoServicio.getListaAlumnos().get(rut).getMaterias())
				{
					contador++;
					System.out.println(contador+". "+materia.getNombre());
				}
				System.out.printf("Seleccionar materia: ");
				
				try 
				{
					sc = new Scanner(System.in);
					int opcionMateria = sc.nextInt();
					
					if(opcionMateria > 0 && opcionMateria <= contador)
					{
						try 
						{
							System.out.printf("Ingresar nota: ");
							sc = new Scanner(System.in);
							Double notaIngresada = sc.nextDouble();
							
							//descomentar en Materia el setNotas
							//alumnoServicio.getListaAlumnos().get(rut).getMaterias().get(0).setNotas(1.5);
							alumnoServicio.getListaAlumnos().get(rut).getMaterias().get(opcionMateria-1).getNotas().add(notaIngresada);
							
							System.out.println("nota agregada a "+alumnoServicio.getListaAlumnos().get(rut).getMaterias().get(opcionMateria-1).getNombre());
						}
						catch (Exception e)
						{
							System.out.println("Error en el formato de la nota, debe contener los decimales");
							System.out.println("--------------------");
						}
					}
					else
					{
						System.out.println("Opción inválida");
					}
				}
				catch (Exception e)
				{
					System.out.println("Error en el formato de la opción, debe digitar un número");
					System.out.println("--------------------");
				}
			}
		}
		else
		{
			System.out.println("Alumno no existente");
		}
		System.out.println("----------------------------- ");
	}
	
	@Override
	void listarAlummnos()
	{
		System.out.println("----------------------------- Listar Alumnos");
		Map<String, Alumno> listaAlumnos= alumnoServicio.getListaAlumnos();
		
		if(listaAlumnos.size() == 0)
		{
			System.out.println("Sin alumnos cargados");
		}
		else
		{
			listaAlumnos.forEach(
					(key, alumno) -> 
					{
						System.out.println("\nDatos Alumno"); 
						System.out.println("\tRUT: "+alumno.getRut()+"\n\tNombre: "+alumno.getNombre()+ "\n\tApellido: "+alumno.getApellido()+ "\n\tDirección: "+alumno.getDireccion());
						
						if (alumno.getMaterias() != null) 
						{ 
							System.out.println("Materias"); 
							alumno.getMaterias().forEach(
							(materia) -> 
							{ 
								System.out.println("\t"+materia.getNombre()); 
								if (materia.getNotas()!=null) 
								{
									System.out.println("\t\tNotas");
									System.out.println("\t\t"+materia.getNotas().toString());
								}
							});	
						}
					}
				);	
		}
		System.out.println("----------------------------- ");
	}
	
	@Override
	boolean terminarPrograma()
	{
		System.out.println("Saliendo del sistema...");
		System.out.println("--------------------");
		return true;
	}
	
	
	
}
