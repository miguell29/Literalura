package com.myapp.literalura.views;

import java.util.Scanner;

import com.myapp.literalura.services.ApiService;

public class Menu {

    private Scanner teclado = new Scanner(System.in);

    public void show() {

        try {
            Thread.sleep(1500); // Espera 1.5 segundo para que terminen los logs y recien comience nuestra
                                // aplicacion
            int opcion = -1;
            var menu = """
                    1. Buscar libros
                    0. Salir
                            """;
            while (opcion != 0) {
                System.out.println(menu);
                System.out.print("Selecciona una opcion: ");
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer del scanner
                switch (opcion) {
                    case 1 -> buscarLibros();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opcion no valida, intenta de nuevo.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void buscarLibros() {
        System.out.println("Escribe tu busqueda:");
        var busqueda = teclado.nextLine();
        var data =ApiService.searchBooks(busqueda);
        data.getResults().forEach(libro -> {
            System.out.println("Id:" + libro.getId() + "\nTitulo: " + libro.getTitle());
            System.out.println("Autor: " + libro.getAuthors().get(0).getName());
            System.out.println("-----------------------------");
        });
    }
}
