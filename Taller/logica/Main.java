/*Integrante 1: Rocío Azucena Rojas Robledo - 21.694.049 - 0 - rarojas25
  https://github.com/rarojas25/Taller1RocioRojasR 
 */

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
				System.out.println("2) Menú Analisis ");
				System.out.println("3) Salir ");
				
				opcion = leerNumero();
				
				if(opcion == 1)menuUsuarios();
				if(opcion == 2)menuAnalisis();
								
				}
			}
			public static void cargarRegistros( ){
				try {
					File archivo = new File("Registros.txt");
					Scanner lector = new Scanner(archivo);
					
					while(lector.hasNextLine()) {
						String linea = lector.nextLine();
						String[] partes = linea.split(";");
						
						usuarioReg[totalReg] = partes[0];
						fechaReg[totalReg] = partes[1];
						horasReg[totalReg] = Integer.parseInt(partes[2]);
						actividadReg[totalReg] = partes[3];
						totalReg++;
					}lector.close();
				}catch (Exception e) {
					System.out.println("Error al leer registros");
					}
			}
			public static void cargarUsuarios() {
				try {
					File archivo = new File("Usuarios.txt"); 
					Scanner lector = new Scanner(archivo);
					
					while(lector.hasNextLine()) {
						String linea = lector.nextLine();
						String[] partes = linea.split(";");
						
						nombres[totalUsuarios] = partes[0];
						claves[totalUsuarios] = partes[1];
						totalUsuarios++;
					}lector.close();
				}catch (Exception e) {
					System.out.println("Error al leer usuarios");
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
					System.out.println("Datos incorrectos");
					return;
				}
				System.out.println("Acceso correcto!");
				System.out.println("Bienvenido" + user + "!");
				
				int opcion = 0;
				while(opcion != 5) {
					System.out.println("Que deseas realizar?" );
					System.out.println("1) Registrar actividad ");
					System.out.println("2) Modificar actividad ");
					System.out.println("3) Eliminar actividad ");
					System.out.println("4) Cambiar contraseña ");
					System.out.println("5) Salir");
					
					opcion = leerNumero();
					
					if(opcion == 1)registrar(user);
					if(opcion == 2)modificar(user);
					if(opcion == 3)eliminar(user);
					if(opcion == 4)cambiarClave(posicion);
				}
			}
			public static void registrar(String user) {
				if(totalReg >= 300) {
					System.out.println("Limite de resgistros alcanzados");
					return;
				}
				System.out.print("Fecha: ");
				String fecha = teclado.nextLine();
				
				System.out.print("Duracion: ");
				int horas = leerNumero();
				
				System.out.print("Tipo de actividad: ");
				String actividad = teclado.nextLine();
				
				usuarioReg[totalReg] = user;
				fechaReg[totalReg] = fecha;
				horasReg[totalReg] = horas;
				actividadReg[totalReg] = actividad;
				totalReg++;
				
				guardarRegistros();
				System.out.println("Actividad registrada con exito! ");
			}
			public static void mostrarActividadesUsuario(String user, int[] posiciones, int[] cantidad) {
				int contador = 0;
				for(int i = 0; i < totalReg; i++) {
					if(usuarioReg[i].equals(user)) {
						System.out.println((contador + 1) + ")" + usuarioReg[i] + ";" + fechaReg[i] + ";" + fechaReg[i] + ";" + horasReg[i] + ";" + actividadReg[i]);
						posiciones[contador] = i;
						contador++;
					}
				}
				cantidad[0] = contador;	
			}
			public static void modificar(String user) {
				int[]posiciones = new int[300];
				int[]cantidad = new int[1];
				
				mostrarActividadesUsuario(user, posiciones, cantidad);
				
				System.out.println("0) Regresar ");
				int opcion = leerNumero();
				
				if(opcion == 0)return;
				if(opcion <= 0 || opcion > cantidad[0])return;
				int real = posiciones[opcion - 1];
				
				System.out.println("0) Regresar ");
				System.out.println("1) Fecha ");
				System.out.println("2) Duracion ");
				System.out.println("3) Tipo de actidad ");
				
				int campo = leerNumero();
				
				if(campo == 0)return;
				if(campo ==  1) {
					System.out.print("Nueva fecha: ");
					fechaReg[real] = teclado.nextLine();
				}
				if(campo == 2) {
					System.out.print("Nueva Duracion: ");
					horasReg[real] = leerNumero();
				}
				if(campo ==3) {
					System.out.print("Nuevo tipo de actividad: ");
					actividadReg[real] = teclado.nextLine();
				}
				guardarRegistros();
				System.out.println("Actividad modificada con exito!");
				
			}
			public static void eliminar(String user) {
				int[]posiciones = new int[300];
				int[] cantidad = new int[1];
				
				mostrarActividadesUsuario(user, posiciones, cantidad);
				
				int opcion = leerNumero();
				if(opcion <= 0 || opcion > cantidad[0])return;
				
				int borrar = posiciones[opcion - 1];
				
				for(int e = borrar; e < totalReg - 1; e++) {
					usuarioReg[e] = usuarioReg[e + 1];
					fechaReg[e] = fechaReg[e + 1];
					actividadReg[e] = actividadReg[e + 1];
				}
				totalReg--;
				guardarRegistros();
			}
			public static void cambiarClave(int pos) {
				System.out.print("Nueva contraseña: ");
				claves[pos] = teclado.nextLine();
				guardarUsuarios();
				System.out.println("Contraseña cambiada con exito! ");
			}
			public static void menuAnalisis() {
				int opcion = 0;
				System.out.println("Bienvenido al menu de analisis! ");
				
				while(opcion != 5) {
					System.out.println("Que deseas realizar? ");
					System.out.println("1) Actividad más realizada ");
					System.out.println("2) Actividad más realizada por cada usuario ");
					System.out.println("3) Usuario con mayor procastinación ");
					System.out.println("4) Ver todas las actividades ");
					System.out.println("5) Salir ");
					opcion = leerNumero();
					
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
					System.out.println("Actividad más realizada: " + actividades[mayor] + "con" + suma[mayor] + "horas" );
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
								if(actividades[d].equals(actividadReg[d])) {
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
						System.out.println(user + "->" + actividades[mayor] + "-> con " + suma[mayor] + "horas registradas");
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
				System.out.println("Usuario con más procrastinación: " + nombres[mayor] + "con" + suma[mayor] + "horas registradas");
			}
			public static void mostrarTodo() {
				for(int t= 0; t < totalReg; t++) {
					System.out.println(usuarioReg[t] + ";" + fechaReg[t] + ";" + horasReg[t] + ";" + actividadReg[t]);
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
			
	}