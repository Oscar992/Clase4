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
            var itemName = itemParts.get(2);
            var itemPrice = Double.parseDouble(itemParts.get(0).substring(1, itemParts.get(0).indexOf("U")));
            var itemStock = Integer.parseInt(itemParts.get(1));
            mapPrecio.put(itemName, itemPrice);
            mapStock.put(itemName, itemStock);
        }
    }

    static void escribirResultadoStock(HashMap<String, Integer> mapStock) {
        try {
            var fileWriter = new FileWriter("src/ejercicio4/stock.txt");

            for (var set : mapStock.entrySet()) {
                var name = set.getKey();
                var stock = set.getValue();
                var finalString = String.format("Item: %s Stock: %d \n", name, stock);
                fileWriter.write(finalString);
            }

            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }

    static void escribirResultadoPrecio(HashMap<String, Double> mapPrecio) {
        try {
            var fileWriter = new FileWriter("src/ejercicio4/precio.txt");

            for (var set : mapPrecio.entrySet()) {
                var name = set.getKey();
                var price = set.getValue();
                var finalString = String.format("Item: %s Precio: %f \n", name, price);
                fileWriter.write(finalString);
            }

            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }

    static void escribirResultadoTienda(HashMap<String, Integer> mapStock, HashMap<String, Double> mapPrecio) {
        try {
            var fileWriter = new FileWriter("src/ejercicio4/tienda.txt");

            for (var set : mapPrecio.entrySet()) {
                var name = set.getKey();
                var price = set.getValue();
                var stock = mapStock.get(name);
                var finalString = String.format("Item: %s Precio: %f Stock:%d \n", name, price, stock);
                fileWriter.write(finalString);
            }

            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }
}
