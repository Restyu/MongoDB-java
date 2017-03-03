import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		MongoDao mongo = new MongoDao();
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = 6;
		
		do{
		
			System.out.println("-----------Menu------------");
			System.out.println();
			System.out.println("-1 conexion");
			System.out.println("-2 insertar evento");
			System.out.println("-3 Consultar ciudades");
			System.out.println("-4 Borrar ciudad");
			System.out.println("-5 borrar ciudades");
			System.out.println("-6 Actualizar eventos");
			System.out.println("-0 Salir");
			System.out.println();
			
			System.out.println("Introduzca su opcion");
			opcion = sc.nextInt();
			
			String nombre,partido,nom_alcalde;
			int poblacion;
			String ultimo_censo;
			String famosa_por;
			
			
			switch (opcion) {
			
			case 0: // desconectar
				
				mongo.salir();
				
				
				break;
				
			case 1: //  conexion 
				
				System.out.println(mongo.conexion());
				
				
				break;
				
			case 2: // insertar
				
				System.out.println("Nombre de la ciudad");
				nombre = sc.next();
				
				System.out.println("poblacion de la ciudad");
				poblacion = sc.nextInt();
				
				System.out.println("ultimo censo dd/MM/yyyy");
				ultimo_censo = sc.next();
				
				System.out.println("famosa por :");
				famosa_por = sc.next();
				
				System.out.println("nombre del alcalde");
				nom_alcalde = sc.next();
				
				System.out.println("partido del alcalde");
				partido = sc.next();
				
				System.out.println(mongo.insertar(nombre, poblacion, ultimo_censo, famosa_por, nom_alcalde, partido));
				
				break;
				
			case 3: // consulta ciudades
				
				Scanner asc = new Scanner(System.in);
				
				System.out.println("Nombre de la ciudad");
				nombre = asc.nextLine();
				
				System.out.println(mongo.consultar(nombre));
				
				break;
				
			case 4: // borrar ciudad
				
				Scanner bor = new Scanner(System.in);
				System.out.println("Nombre de la ciudad");
				nombre = bor.nextLine();
				
				System.out.println(mongo.borrarCiudad(nombre));
				break;
				
			case 5: // borrar ciudades
				
				Scanner bs = new Scanner(System.in);	
				System.out.println("Nombre de la ciudad");
				nombre = bs.nextLine();
				
				System.out.println(mongo.borrarCiudades(nombre));
				break;
				
			case 6: // Actualizar ciudades
				
				Scanner actu = new Scanner(System.in);	
				Scanner num = new Scanner(System.in);	
				
				System.out.println("Nombre de la ciudad");
				nombre = actu.nextLine();
				System.out.println("Introduzca fecha dd/MM/yyyy");
				Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(actu.nextLine());
				System.out.println("poblacion");
				poblacion = num.nextInt();
				
				System.out.println(mongo.actualizaMuchos(nombre , fecha ,poblacion));
				
				break;
			
			}
				
		}while(opcion != 0);

	}

}
