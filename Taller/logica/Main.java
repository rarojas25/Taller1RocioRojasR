/*Integrante 1: Rocío Azucena Rojas Robledo - 21.694.049 - 0 - rarojas25
  https://github.com/rarojas25/Taller1RocioRojasR 
 */

package logica;

import java.io.File;
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
			while(opcion != 3){
				System.out.println("1) Menú Usuarios");
				System.out.println("2) Menú Analisis");
				System.out.println("3) Salir");
				opcion = leerNumero();
				if(opcion == 1)menuUsuarios();
				if(opcion == 2)menuAnalisis();
								
				}
			}
			public static void cargarRegistros(){
				try{
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
				}catch (Exception e){
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
				}catch (Exception e){
					System.out.println("Error al leer usuarios");
				}
			}
			public static void menuUsuarios() {
				System.out.println("Usuario: ");
				String user = teclado.nextLine();
				System.out.println("Contraseña: ");
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
				int opcion = 0;
				while(opcion != 5) {
					System.out.println("1) Registrar actividad");
					System.out.println("2) Modificar actividad");
					System.out.println("3) Eliminar actividad");
					System.out.println("4) Cambiar contraseña");
					System.out.println("5) Salir");
					
					opcion = leerNumero();
					
					if(opcion == 1)registrar(user);
					if(opcion == 2)modificar(user);
					if(opcion == 3)eliminar(user);
					if(opcion == 4)cambiarClave(posicion);
				}
			}
			public static void registrar(String user){
				if(totalReg >= 300) {
					System.out.println("Limite de resgistros alcanzados");
					return;
				}
				System.out.println("Fecha: ");
				String fecha = teclado.nextLine();
				
				System.out.println("Horas: ");
				int horas = leerNumero();
				
				System.out.println("Actividad: ");
				String actividad = teclado.nextLine();
				
				usuarioReg[totalReg] = user;
				fechaReg[totalReg] = fecha;
				horasReg[totalReg] = horas;
				actividadReg[totalReg] = actividad;
				totalReg++;
				
				guardarRegistros();
				}
			public static void mostrarActividades(String user, int[] posiciones, int[] cantidad) {
				int contador = 0;
				for(int i; i < totalReg; i++) {
					if(usuarioReg[i].equals(user)) {
						System.out.println((contador + 1) + ")" + usuarioReg[i] + ";" + fechaReg[i] + ";" + fechaReg[i] + ";" + horasReg[i] + ";" + actividadReg[i]);
						posiciones[contador] = i;
						contador++;
					}
				}
				cantidad[0] = contador;	
			}
			
	}

