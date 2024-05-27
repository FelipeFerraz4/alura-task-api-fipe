package com.bluefox.apiFipe.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bluefox.apiFipe.service.ConsumeAPI;
import com.bluefox.apiFipe.service.ConvertAPI;
import com.bluefox.model.Data;
import com.bluefox.model.Models;
import com.bluefox.model.Vehicle;

public class Menu {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String vehicle = getVehicle(scanner);

        System.out.println( vehicle);
        
        String brand = getBrand(scanner, vehicle);

        getVehicleDate(scanner, vehicle, brand);
    }


    private static String getVehicle(Scanner scanner) {
        String option = String.format("====== Opções de Veiculos ======%n" +
        "1 - Carros%n" +
        "2 - Motos%n" +
        "3 - Caminhões");

        int vehicle = -1;
        do {
            System.out.println(option);

            vehicle = scanner.nextInt();

            if (vehicle < 1 || vehicle > 3) {
                System.out.println("Dados informados invalidos");
            }

        }while(vehicle < 1 || vehicle > 3);

        switch (vehicle) {
            case 1:
                return "carros";
            case 2:
                return "motos";
            default:
                return "caminhoes";
        }
    }

    private static String getBrand(Scanner scanner, String vehicle) {
        ConsumeAPI consumeAPI = new ConsumeAPI();
        String json = consumeAPI.getdata("https://parallelum.com.br/fipe/api/v1/" + vehicle +"/marcas");


        ConvertAPI convertAPI = new ConvertAPI();
        List<Data> brands = convertAPI.getListData(json, Data.class);

        System.out.println("====== Opções de Marca ======");

        for (int i = 0; i < brands.size(); i++) {
            System.out.println((i+1) + " - " + brands.get(i).name());
        }

        System.out.println("Digite o Código do Modelo: ");

        int brand = -1;
        do {
            brand = scanner.nextInt();

            if (brand < 1 || brand > brands.size()) {
                System.out.println("Dados informados invalidos");
            }

        }while(brand < 1 || brand > brands.size());

        return brands.get(brand-1).id();

    }

    private static void getVehicleDate(Scanner scanner, String vehicle, String brand) {
        ConsumeAPI consumeAPI = new ConsumeAPI();
        String address = "https://parallelum.com.br/fipe/api/v1/" + vehicle +"/marcas/" + brand + "/modelos/";
        String json = consumeAPI.getdata(address);
        ConvertAPI convertAPI = new ConvertAPI();
        Models models = convertAPI.getData(json, Models.class);
        models.model().stream()
                    .sorted(Comparator.comparing(Data::id))
                    .forEach(System.out::println);

        System.out.println("Digite o modelo: ");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        String modelName = scanner.nextLine();

        List<Data> filtedModels = models.model().stream().filter(model -> model.name().toLowerCase().contains(modelName.toLowerCase()))
                                        .collect(Collectors.toList());

        filtedModels.stream().forEach(System.out::println);

        System.out.println("Digite o id do modelo: ");
        String modelId = scanner.nextLine();

        address = address + modelId + "/anos";
        json = consumeAPI.getdata(address);
        List<Data> year = convertAPI.getListData(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < year.size(); i++) {
            String urlYear = address + "/" + year.get(i).id();
            json = consumeAPI.getdata(urlYear);
            Vehicle vehicleData = convertAPI.getData(json, Vehicle.class);
            vehicles.add(vehicleData);
        }

        System.out.println("======== Veiculos ========");
        vehicles.forEach(System.out::println);
        

    }
}
