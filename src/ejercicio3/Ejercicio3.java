package ejercicio3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        var listaPeliculas = leerPeliculas("src/ejercicio3/peliculas.txt");
        var resultadoPeliculas = manipularPeliculas(listaPeliculas);

        escribirResultado(resultadoPeliculas);
    }

    static ArrayList<String> leerPeliculas(String path) {
        var listaPeliculas = new ArrayList<String>();
        var file = new File(path);

        try {
            var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                listaPeliculas.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No hay archivo");
        }

        return listaPeliculas;
    }

    static ArrayList<String> manipularPeliculas(ArrayList<String> lista) {
        var listaActualizada = new ArrayList<String>();

        for (var i = 0; i < lista.size(); i++) {
            var mensaje = String.format("%d. %s", i + 1, lista.get(i).toUpperCase());
            listaActualizada.add(mensaje);
        }

        return listaActualizada;
    }

    static void escribirResultado(ArrayList<String> lista) {
        try {
            var fileWriter = new FileWriter("src/ejercicio3/resultado3.txt");

            for (var str : lista) {
                fileWriter.write(String.format("%s \n", str));
            }

            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }
}
