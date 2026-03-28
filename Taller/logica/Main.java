/*Integrante 1: Rocío Azucena Rojas Robledo - 21.694.049 - 0 - rarojas25
  https://github.com/rarojas25/Taller1RocioRojasR 
 */

package logica;

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
			
	}

