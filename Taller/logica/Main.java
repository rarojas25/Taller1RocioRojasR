//Integrante 1: Rocío Azucena Rojas Robledo - 21.694.049-0 - rarojas25
//https://github.com/rarojas25/Taller1RocioRojasR 

package logica;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main{
	
		static String[] nombres = new String[50];
		static String[] claves = new String[50];
		static int totalUsuarios = 0;
		static String[] usuarioReg = new String[300];
		static String[] fechaReg = new String[300];
		static int[] horasReg = new int[300];
		static String[] actividadReg = new String[300];
		static int totalReg = 0;
		static Scanner teclado = new Scanner(System.in); 
		
		public static void main(String[]args) {
			cargarUsuarios();
			cargarRegistros();
			
			int opcion = 0;
			while(opcion != 3) {
				System.out.println("1) Menú Usuarios ");
				System.out.println("2) Menú Análisis ");
				System.out.println("3) Salir ");
				
				opcion = leerNumero();
				
				if(opcion < 1 || opcion > 3) {
					System.out.println("Opción inválida.");
					continue;
				}
				
				if(opcion == 1)menuUsuarios();
				else if(opcion == 2)menuAnalisis();				
			}
			System.out.println("Sistema terminado con éxito!");
		}
		
		public static void cargarRegistros( ){
			try {
				File archivo = new File("Registros.txt");
				Scanner lector = new Scanner(archivo);
				
				while(lector.hasNextLine()) {
					String[] partes = lector.nextLine().split(";");
					
					usuarioReg[totalReg] = partes[0];
					fechaReg[totalReg] = partes[1];
					horasReg[totalReg] = Integer.parseInt(partes[2]);
					actividadReg[totalReg] = partes[3];
					totalReg++;
					
				}
				lector.close();
			} catch (Exception e) {
				System.out.println("Error al leer registros.");
				}
		}
		public static void cargarUsuarios() {
			try {
				File archivo = new File("Usuarios.txt"); 
				Scanner lector = new Scanner(archivo);
				
				while(lector.hasNextLine()) {
					String[] partes = lector.nextLine().split(";");
					
					nombres[totalUsuarios] = partes[0];
					claves[totalUsuarios] = partes[1];
					totalUsuarios++;
					
				}
				lector.close();
			} catch (Exception e) {
				System.out.println("Error al leer usuarios.");
			}
		}
		
		public static void menuUsuarios() {
			System.out.print("Usuario: ");
			String user = teclado.nextLine();
			System.out.print("Contraseña: ");
			String pass = teclado.nextLine();
			
			int posicion = -1;
			
			for(int i = 0; i < totalUsuarios; i++) {
				if(nombres[i].equals(user) && claves[i].equals(pass)) {
					posicion = i;
				}
			}
			
			if(posicion == -1) {
				System.out.println("Usuario o contraseña incorrectos.");
				return;
			}
			
			System.out.println("Acceso correcto!");
			System.out.println("Bienvenido/a " + user + "!");
			
			int opcion = 0;
			while(opcion != 5) {
				
				System.out.println("Qué deseas realizar?" );
				System.out.println("1) Registrar actividad ");
				System.out.println("2) Modificar actividad ");
				System.out.println("3) Eliminar actividad ");
				System.out.println("4) Cambiar contraseña ");
				System.out.println("5) Salir");
				
				opcion = leerNumero();
				
				if(opcion < 1 || opcion > 5) {
					System.out.println("Opción inválida.");
					continue;
				}
				
				if(opcion == 1)registrar(user);
				else if(opcion == 2)modificar(user);
				else if(opcion == 3)eliminar(user);
				else if(opcion == 4)cambiarClave(posicion);
			}
			System.out.println("Sesión cerrada con éxito!");
		}
		
		public static void registrar(String user) {
			if(totalReg >= 300) {
				System.out.println("Límite de registros alcanzado.");
				return;
			}
			System.out.print("Fecha: ");
			String fecha = leerFecha();
			
			System.out.print("Duración: ");
			int horas = leerNumero();
			
			System.out.print("Actividad: ");
			String actividad = teclado.nextLine();
			
			usuarioReg[totalReg] = user;
			fechaReg[totalReg] = fecha;
			horasReg[totalReg] = horas;
			actividadReg[totalReg] = actividad;
			totalReg++;
			
			guardarRegistros();
			System.out.println("Actividad registrada con éxito!");
		}
		
		public static void mostrarActividadesUsuario(String user, int[] posiciones, int[] cantidad) {
			int contador = 0;
			
			for(int i = 0; i < totalReg; i++) {
				if(usuarioReg[i].equals(user)) {
					System.out.println((contador + 1) + ") " + usuarioReg[i] + ", Fecha:" + fechaReg[i] + ", Duración:" + horasReg[i]);
					System.out.println("    " + actividadReg[i]);
					posiciones[contador] = i;
					contador++;
				}
			}
			
			cantidad[0] = contador;	
		}
		
		public static void modificar(String user) {
			int[]posiciones = new int[300];
			int[]cantidad = new int[1];
			
			System.out.println("Cuál actividad deseas modificar?");
			mostrarActividadesUsuario(user, posiciones, cantidad);
			
			if(cantidad[0] == 0) {
				System.out.println("No tienes actividades registradas.");
				return;
			}
			
			System.out.println("0) Regresar ");
			int opcion = leerNumero();
			
			if(opcion <= 0 || opcion > cantidad[0])return;
			int real = posiciones[opcion - 1];
			
			System.out.println("Qué deseas modificar? ");
			System.out.println("0) Regresar ");
			System.out.println("1) Fecha ");
			System.out.println("2) Duración ");
			System.out.println("3) Tipo de actividad ");
			
			int campo = leerNumero();
			
			if(campo == 0)return;
			
			switch(campo) { // Aquí intenté usar un switch en vez de else if :)
				case 1:
					System.out.print("Ingrese nueva fecha: ");
					fechaReg[real] = leerFecha();
					break;
				case 2:
					System.out.print("Ingrese nueva duración: ");
					horasReg[real] = leerNumero();
					break;
				case 3:
					System.out.print("Ingrese nuevo tipo actividad: ");
					actividadReg[real] = teclado.nextLine();
					break;
				default:
					System.out.println("Opción invalida");
					return;
			}
			guardarRegistros();
			System.out.println("Actividad modificada con éxito!");
			
		}
		
		public static void eliminar(String user) {
			int[]posiciones = new int[300];
			int[] cantidad = new int[1];
			
			mostrarActividadesUsuario(user, posiciones, cantidad);
			
			if(cantidad[0] == 0) {
				System.out.println("No tienes actividades registradas.");
				return;
			}
			
			System.out.println("0) Regresar");
			int opcion = leerNumero();
			if(opcion <= 0 || opcion > cantidad[0])return;
			
			int borrar = posiciones[opcion - 1];
			System.out.println("¿Seguro que deseas eliminar? (1 = Sí / 0 = No): ");
			int confirmar = leerNumero();
			
			if(confirmar != 1)return;
			
			for(int e = borrar; e < totalReg - 1; e++) {
				usuarioReg[e] = usuarioReg[e + 1];
				fechaReg[e] = fechaReg[e + 1];
				horasReg[e] = horasReg[e + 1];
				actividadReg[e] = actividadReg[e + 1];
			}
			totalReg--;
			usuarioReg[totalReg] = "";
			fechaReg[totalReg] = "";
			horasReg[totalReg] = 0;
			actividadReg[totalReg] = "";
			guardarRegistros();
			System.out.println("Actividad eliminada con éxito! ");
		}
		
		public static void cambiarClave(int pos) {
			System.out.print("Nueva contraseña: ");
			claves[pos] = teclado.nextLine();
			guardarUsuarios();
			System.out.println("Contraseña cambiada con éxito! ");
		}
		
		public static void menuAnalisis() {
			int opcion = 0;
			System.out.println("Bienvenido al menú de análisis! ");
			
			while(opcion != 5) {
				
				System.out.println("Qué deseas realizar? ");
				System.out.println("1) Actividad más realizada ");
				System.out.println("2) Actividad más realizada por cada usuario ");
				System.out.println("3) Usuario con mayor procrastinación ");
				System.out.println("4) Ver todas las actividades ");
				System.out.println("5) Salir ");
				
				opcion = leerNumero();
				if(opcion < 1 || opcion > 5) {
					System.out.println("Opción inválida");
					continue;
				}
				
				if(opcion == 1)actividadTop();
				if(opcion == 2)actividadPorUsuario();
				if(opcion == 3)usuarioMayorProcras();
				if(opcion == 4)mostrarTodo();
			}
		}
		
		public static void actividadTop() {
			String[] actividades = new String[300];
			int[] suma = new int[300];
			int contador = 0;
			
			for(int a= 0; a < totalReg; a++) {
				int pos = -1;
				for(int b = 0; b < contador;b++ ) {
					if(actividades[b].equals(actividadReg[a])) {
						pos = b;
					}
				}
				
				if(pos == -1) {
					actividades[contador] = actividadReg[a];
					suma[contador] = horasReg[a];
					contador++;
				}else {
					suma[pos] += horasReg[a]; 
				}
			}
			
			int mayor = 0;
			for(int a = 1; a < contador; a++) {
				if(suma[a] > suma[mayor]) {
					mayor = a;
				}
			}
			
			if(contador > 0) {
				System.out.println("Actividad más realizada: " + actividades[mayor] + " con " + suma[mayor] + " horas registradas" );
			}
			if(contador == 0) {
				System.out.println("No hay actividades registradas.");
			}
		}
		
		public static void actividadPorUsuario() {
			for(int p = 0; p < totalUsuarios; p++) {
				String user = nombres[p];
				
				String[] actividades = new String[300];
				int[] suma = new int[300];
				int contador = 0;
				
				for(int c = 0; c < totalReg; c++) {
					if(usuarioReg[c].equals(user)) {
						int pos = -1;
						for(int d = 0; d < contador; d++) {
							if(actividades[d].equals(actividadReg[c])) {
								pos = d;
							}
						}
						
						if(pos == -1) {
							actividades[contador] = actividadReg[c];
							suma[contador] = horasReg[c];
							contador++;
						}else {
							suma[pos] += horasReg[c];
						}
					}
				}
				
				if(contador > 0) {
					int mayor = 0;
					for(int c = 1; c < contador; c++) {
						if(suma[c] > suma[mayor]) {
							mayor = c;
						}
					}
					
					System.out.println(user + " -> " + actividades[mayor] + " -> con " + suma[mayor] + " horas registradas");
				}
			}
		}
		
		public static void usuarioMayorProcras() {
			int[]suma = new int[50];
			
			for(int d = 0; d < totalReg; d++) {
				for(int f = 0; f < totalUsuarios; f++) {
					if(nombres[f].equals(usuarioReg[d])) {
						suma[f] += horasReg[d];
					}
				}
			}
			
			int mayor = 0;
			
			for(int d = 1; d < totalUsuarios; d++) {
				if(suma[d] > suma[mayor]) {
					mayor = d;
				}
			}
			System.out.println("Usuario con más procrastinación: " + nombres[mayor] + " con " + suma[mayor] + " horas registradas");
		}
		public static void mostrarTodo() {
			for(int t= 0; t < totalReg; t++) {
				System.out.println("Usuario:" + usuarioReg[t] + ", Fecha:" + fechaReg[t] + ", Duración:" + horasReg[t]);
				System.out.println("Descripción: " + actividadReg[t] + "\n");
			}
			if(totalReg == 0) {
				System.out.println("No hay actividades registradas.");
				return;
			}
		}
		
		public static void guardarRegistros() {
			try {
				FileWriter escritor = new FileWriter("Registros.txt");
				
				for(int r = 0; r < totalReg; r++) {
					escritor.write(usuarioReg[r] + ";" + fechaReg[r] + ";" + horasReg[r] + ";" + actividadReg[r] + "\n");
				}escritor.close();
				
			}catch(Exception e){
				System.out.println("Error guardando archivos ");
			}
		}
		public static void guardarUsuarios() {
			try {
				FileWriter escritor = new FileWriter("Usuarios.txt");
				
				for(int u = 0; u < totalUsuarios; u++) {
					escritor.write(nombres[u] + ";" + claves[u] + "\n");
				}escritor.close();
				
			}catch (Exception e) {
				System.out.println("Error guardando usuarios ");
			}
		}

			public static int leerNumero() {
				while(true) {
					try {
						return Integer.parseInt(teclado.nextLine());
					} catch (Exception e) {
						System.out.print("Ingrese un numero válido: ");
					}
				}
			}

			public static String leerFecha() {
				while(true) {
					String fecha = teclado.nextLine();

					String[] partes = fecha.split("/");
					if(partes.length != 3) {
						System.out.print("Formato inválido. Use dd/mm/yyyy: ");
						continue;
					}

					int dia;
					int mes;
					try {
						dia = Integer.parseInt(partes[0]);
						mes = Integer.parseInt(partes[1]);
						Integer.parseInt(partes[2]);
					} catch (Exception e) {
						System.out.print("La fecha debe tener solo números. Use dd/mm/yyyy: ");
						continue;
					}

					if(partes[0].length() != 2 || partes[1].length() != 2 || partes[2].length() != 4) {
						System.out.print("Formato inválido. Use dd/mm/yyyy: ");
						continue;
					}

					if(mes < 1 || mes > 12) {
						System.out.print("Mes inválido. Use dd/mm/yyyy: ");
						continue;
					}

					if(dia < 1 || dia > 31) {
						System.out.print("Día inválido. Use dd/mm/yyyy: ");
						continue;
					}

					return fecha;
				}
			}

			
	}