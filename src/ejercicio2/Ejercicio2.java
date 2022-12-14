package ejercicio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        var mapNumeros = leerNumeros("src/Ejercicio2/numeros.txt");
        var listaResultado = new ArrayList<String>();
        int opcion;
        var scanner = new Scanner(System.in);

        do {
            imprimirMenu(mapNumeros);

            System.out.print("Ingrese opcion");
            opcion = Integer.parseInt(scanner.nextLine());

            listaResultado.add(buscarNumero(opcion, mapNumeros));
        } while (opcion != -1);

        scanner.close();
        escribirResultado("src/Ejercicio2/resultado2.txt", listaResultado);
    }

    static void imprimirMenu(HashMap<Integer, String> map) {
        for (var set : map.entrySet()) {
            System.out.printf("%d %s", set.getKey(), set.getValue());
        }
    }

    static HashMap<Integer, String> leerNumeros(String path) {
        var map = new HashMap<Integer, String>();
        var file = new File(path);

        try {
            var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                var linea = scanner.nextLine();
                var number = Arrays.asList(linea.split(","));
                var numberPart1 = number.get(0);
                var numberPart2 = number.get(1);
                map.put(Integer.valueOf(numberPart1), numberPart2);
            }

            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No hay archivo");
        }

        return map;
    }

    static String buscarNumero(int n, HashMap<Integer, String> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            return "No existe";
        }
    }

    static void escribirResultado(String path, ArrayList<String> lista) {
        try {
            var fileWriter = new FileWriter(path);

            for (var string : lista) {
                fileWriter.write(string + "\n");
            }

            fileWriter.close();
        } catch (IOException ioException) {
        }
    }
}
