package ejercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        var listaMenu = leerMenu("src/Ejercicio1/opcionesMenu.txt");
        var scanner = new Scanner(System.in);
        var listaResultado = new ArrayList<String>();
        String opcion;

        do {
            imprimirMenu(listaMenu);

            System.out.print("Ingrese opcion:");
            opcion = scanner.nextLine();

            listaResultado.add(ejecutarOpcion(opcion));

        } while (!opcion.equals("Despedirse"));

        scanner.close();
        escribirResultado("src/Ejercicio1/resultado1.txt", listaResultado);
    }

    static ArrayList<String> leerMenu(String path) {
        var file = new File(path);
        var lista = new ArrayList<String>();

        try {
            var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                lista.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No hay");
            System.out.println(fileNotFoundException.getLocalizedMessage());
        }

        return lista;
    }

    static void imprimirMenu(ArrayList<String> lista) {
        for (var str : lista) {
            System.out.println(str);
        }
    }

    static String ejecutarOpcion(String opcion) {
        switch (opcion) {
            case "Saludarme":
                return "Hola como estas";
            case "Hora":
                return "Ok, te dire la hora";
            case "Comida":
                return "Es hora de comer";
            case "Despedirse":
                return "Adios";
            default:
                return "Opcion Incorrecta";
        }
    }

    static void escribirResultado(String path, ArrayList<String> lista) {
        try {
            var fileWriter = new FileWriter(path);

            for (var str : lista) {
                fileWriter.write(String.format("%s \n", str));
            }

            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }
}
