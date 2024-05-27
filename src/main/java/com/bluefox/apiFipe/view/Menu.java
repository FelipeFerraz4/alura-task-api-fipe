package com.bluefox.apiFipe.view;

import java.util.List;
import java.util.Scanner;

import com.bluefox.apiFipe.service.ConsumeAPI;
import com.bluefox.apiFipe.service.ConvertAPI;
import com.bluefox.model.VehicleModelData;

public class Menu {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String vehicle = getVehicle(scanner);
        
        System.out.println( vehicle);
        
        String vehicleModel = getVehicleModel(scanner, vehicle);

        System.out.println(vehicleModel);

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

    private static String getVehicleModel(Scanner scanner, String vehicle) {
        ConsumeAPI consumeAPI = new ConsumeAPI();
        String json = consumeAPI.getdata("https://parallelum.com.br/fipe/api/v1/" + vehicle +"/marcas");


        ConvertAPI convertAPI = new ConvertAPI();
        List<VehicleModelData> vehicleModels = convertAPI.getListData(json, VehicleModelData.class);

        System.out.println("====== Opções de Modelos ======");

        for (int i = 0; i < vehicleModels.size(); i++) {
            System.out.println((i+1) + " - " + vehicleModels.get(i).vehicleModel());
        }

        System.out.println("Digite o Código do Modelo: ");

        int vehicleModel = -1;
        do {
            vehicleModel = scanner.nextInt();

            if (vehicleModel < 1 || vehicleModel > vehicleModels.size()) {
                System.out.println("Dados informados invalidos");
            }

        }while(vehicleModel < 1 || vehicleModel > vehicleModels.size());

        return vehicleModels.get(vehicleModel-1).vehicleModel();

    }
}
