/*
 * Autora: Tiffany Salazar Suarez
 * Código de carnét: 24630
 * Descripción: clase main. 
 */
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        Grafo grafo = new Grafo();

        try{
            BufferedReader br = new BufferedReader(new FileReader("grafo.txt"));
            String linea;
            while ((linea = br.readLine()) != null){
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String origen = partes[0].trim();
                    String destino = partes[1].trim();
                    int tiempo = Integer.parseInt(partes[2].trim());
                    grafo.agregarArco(origen, destino, tiempo);
                }
            }
            br.close();
        } catch (IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        int[][] distancias = FloydWarshall.calcularDistancias(grafo.getmatAdy());

        while (true) {
            System.out.println("Ingrese la opción que desea realizar:");
            System.out.println("1. Ruta más corta entre dos ciudades");
            System.out.println("2. Ciudad en el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");
            int op = Integer.parseInt(tec.nextLine());

            switch (op) {
                case 1:
                    System.out.print("Ingrese ciudad origen: ");
                    String origen = tec.nextLine();
                    System.out.print("Ingrese ciudad destino: ");
                    String destino = tec.nextLine();
                    int i = grafo.getciudades().indexOf(origen);
                    int j = grafo.getciudades().indexOf(destino);
                    if (i != -1 && j != -1) {
                        System.out.println("Tiempo mínimo: " + distancias[i][j]);
                    } else {
                        System.out.println("Ciudad no encontrada.");
                    }
                    break;

                case 2:
                    String centro = CentroGrafo.calcularCentro(distancias, grafo.getciudades());
                    System.out.println("La ciudad en el centro del grafo es: " + centro);
                    break;

                case 3:
                    System.out.println("1. Interrupción de tráfico entre dos ciudades");
                    System.out.println("2. Nueva conexión entre dos ciudades");
                    int subop = Integer.parseInt(tec.nextLine());
                    System.out.print("Ciudad 1: ");
                    String c1 = tec.nextLine();
                    System.out.print("Ciudad 2: ");
                    String c2 = tec.nextLine();
                    if (subop == 1) {
                        grafo.eliminarArco(c1, c2);
                    } else if (subop == 2) {
                        System.out.print("Ingrese tiempo: ");
                        int tiempo = Integer.parseInt(tec.nextLine());
                        grafo.agregarArco(c1, c2, tiempo);
                    }
                    distancias = FloydWarshall.calcularDistancias(grafo.getmatAdy());
                    break;

                case 4:
                    System.out.println("Ha seleccionado salir...");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}