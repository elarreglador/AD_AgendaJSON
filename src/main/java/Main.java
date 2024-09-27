import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
	
	/*
 	Crear un programa que muestre datos de contacto (Nombre, Teléfono y e-mail) de un fichero.
	También se deben poder añadir contactos al fichero de agenda.
	Usar serialización de XML
	*/

	public static void main(String[] args) {
		
		File archivo = new File("agenda.json");
		Agenda miAgenda;
		if (archivo.exists()) {
			miAgenda = leeJSON(archivo);
		}else {
			miAgenda = new Agenda();
		}
		int opcion;
		boolean power = true;
		while (power) {
			
			muestraMenu();
			Scanner lee = new Scanner(System.in);
			try {
				opcion = lee.nextInt();
				
				if (opcion == 0) { // Cieerra la app
					power = !power;
				}
				
				if (opcion == 1) { // Muestra toda la agenda
					for (Persona contacto : miAgenda.getAgenda()) {
						System.out.println();
						System.out.println("Nombre: " + contacto.getNombre());
						System.out.println("Telf.: " + contacto.getTelefono());
						System.out.println("mail: " + contacto.getMail());
					}
				}
				
				if (opcion == 2) { // Agrega persona
					agregarPersonaA(miAgenda);
					guardaJSON(miAgenda, archivo);
				}
			} catch (Exception e) {
				System.out.println("Excepcion: " + e);
			}
			
		}
	}
	
	
	
	private static void muestraMenu() {
		System.out.println();
		System.out.println("Selecciona la opcion que desees:");
		System.out.println();
		System.out.println("1) Leer agenda completa");
		System.out.println("2) Agregar persona");
		System.out.println();
		System.out.println("0) Salir");
		System.out.print(":");
	}
	
	
	private static void agregarPersonaA(Agenda miAgenda) {
		String nombre, telf, mail;
		Scanner lee = new Scanner(System.in);
		
		System.out.print("Nombre: ");
		nombre = lee.nextLine();
		System.out.print("Telf.: ");
		telf = lee.nextLine();
		System.out.print("mail: ");
		mail = lee.nextLine();
		
		Persona nuevoContacto = new Persona(nombre,telf, mail);
		miAgenda.add(nuevoContacto);
	}
	
	
	private static void guardaJSON(Agenda miAgenda, File archivo) {
		try {
			// pasa de ser un objeto Agenda a un String en formato JSON
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(miAgenda);
			
			// Generamos stream
			FileWriter fileWriter = new FileWriter(archivo);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			// Guarda el JSON
			bufferedWriter.write(json);
			
			// Cierra archivo y stream
			bufferedWriter.close();
			fileWriter.close();
						
		} catch (JsonProcessingException e) {
			System.out.println("Excepcion: " + e);
		} catch (IOException e) {
			System.out.println("Excepcion: " + e);
		}

	}
	

	private static Agenda leeJSON(File archivoALeer) {
		try {
            // Creamos un StringBuilder para almacenar el contenido
            StringBuilder jsonContent = new StringBuilder();
            
            // Creamos el stream
            FileReader fileReader = new FileReader(archivoALeer);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Leemos el archivo línea a línea
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                jsonContent.append(linea);
            }

            // Cerramos el string
            bufferedReader.close();
            fileReader.close();

            // Convertimos el String (JSON) a objeto Agenda usando ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            Agenda miAgenda = mapper.readValue(jsonContent.toString(), Agenda.class);

            return miAgenda;

        } catch (JsonProcessingException e) {
            System.out.println("Excepción en el procesamiento del JSON: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Excepción de entrada/salida: " + e.getMessage());
        }
		
        return null;
	}

	
}
