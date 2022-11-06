package ejercicio4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        var listaTexto = llamarData("src/ejercicio4/items.txt");
        var mapPrecio = new HashMap<String, Double>();
        var mapStock = new HashMap<String, Integer>();

        organizarData(mapStock, mapPrecio, listaTexto);
        escribirResultadoStock(mapStock);
        escribirResultadoPrecio(mapPrecio);
        escribirResultadoTienda(mapStock, mapPrecio);

    }

    static ArrayList<String> llamarData(String path) {
        var listaItems = new ArrayList<String>();

        var file = new File(path);

        try {
            var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                listaItems.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No hay archivo");
        }

        return listaItems;
    }

    static void organizarData(HashMap<String, Integer> mapStock, HashMap<String, Double> mapPrecio, ArrayList<String>
            lista) {
        for (var item : lista) {
            var itemParts = Arrays.asList(item.split(","));
            mapPrecio.put(itemParts.get(2), Double.parseDouble(itemParts.get(0).split("U")[0]));
            mapStock.put(itemParts.get(2), Integer.parseInt(itemParts.get(1)));
        }
    }

    static void escribirResultadoStock(HashMap<String, Integer> mapStock) {
        try {
            var fileWriter = new FileWriter("src/ejercicio4/stock.txt");

            for (var set : mapStock.entrySet()) {
                fileWriter.write(String.format("Item: %s Stock: %d \n", set.getKey(), set.getValue()));
            }

            fileWriter.close();
        } catch (IOException ioException) {
        }

    }

    static void escribirResultadoPrecio(HashMap<String, Double> mapPrecio) {
        try {
            var fileWriter = new FileWriter("src/ejercicio4/precio.txt");

            for (var set : mapPrecio.entrySet()) {
                fileWriter.write(String.format("Item: %s Precio: %f \n", set.getKey(), set.getValue()));
            }

            fileWriter.close();
        } catch (IOException ioException) {
        }
    }

    static void escribirResultadoTienda(HashMap<String, Integer> mapStock, HashMap<String, Double> mapPrecio) {
        try {
            var fileWriter = new FileWriter("src/ejercicio4/tienda.txt");

            for (var set : mapPrecio.entrySet()) {
                fileWriter.write(String.format("Item: %s Precio: %f Stock:%d \n", set.getKey(), set.getValue(),
                        mapStock.get(set.getKey())));
            }

            fileWriter.close();
        } catch (IOException ioException) {
        }
    }
}
