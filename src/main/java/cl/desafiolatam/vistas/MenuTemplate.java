package cl.desafiolatam.vistas;

import java.util.Scanner;

import cl.desafiolatam.modelos.Alumno;

public abstract class MenuTemplate {

	private Scanner sc;
	
	public void iniciarMenu()
	{
		boolean salir = false;
		while(salir == false)
		{
			Menu menu = new Menu();
			
			System.out.println("1. Crear alumnos");
			System.out.println("2. Listar alumnos");
			System.out.println("3. Agregar materias");
			System.out.println("4. Agregar notas");
			System.out.println("5. Cargar datos");
			System.out.println("6. Exportar datos");
			System.out.println("7. Salir");
			System.out.println("Selección: ");
			
			try 
			{
				sc = new Scanner(System.in);
				int opcionElegida = sc.nextInt();
				switch (opcionElegida) {
				case 1:
					menu.crearAlummno();
					break;
				case 2:
					menu.listarAlummnos();
					break;
				case 3:
					menu.agregarMateria();
					break;
				case 4:
					menu.agregarNotaPasoUno();
					break;
				case 5:
					menu.cargarDatos();
					break;
				case 6:
					menu.exportarDatos();
					break;
				case 7:
					salir = menu.terminarPrograma();
					break;
				default:
					System.out.println("Opción inválida");
					System.out.println("--------------------");
					break;
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Error en el formato de la opción, debe digitar un número");
				System.out.println("--------------------");
			}
		}
		
	}
	
	abstract void cargarDatos();
	abstract void exportarDatos();
	abstract void crearAlummno();
	abstract void agregarMateria();
	abstract void agregarNotaPasoUno();
	abstract void listarAlummnos();
	abstract boolean terminarPrograma();
	
}
