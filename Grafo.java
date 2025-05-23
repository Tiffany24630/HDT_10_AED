/*
 * Autora: Tiffany Salazar Suarez
 * Código de carnét: 24630
 * Descripción: clase grafo que representa un grafo y permite agregar ciudades como nodos y arcos entre ellas. 
 */
import java.util.*;

public class Grafo{
    private int[][] matAdy;
    private Map<String, Integer> ciudades;
    private List<String> indCiud;
    private final int INF = 99999;

    public Grafo(){
        this.ciudades = new HashMap<>();
        this.indCiud = new ArrayList<>();
        this.matAdy = new int[0][0];
    }

    //Método que agrega una nueva ciudad (nodo) al grafo.
    public void agregarCiudades(String nom){
        if (!ciudades.containsKey(nom)){
            ciudades.put(nom, indCiud.size());
            indCiud.add(nom);
            resMat();
        }
    }

    //Método que redimensiona la matriz de adyacencia al agregar nuevas ciudades.
    private void resMat(){
        int n = indCiud.size();
        int[][] nuevMat = new int[n][n];

        //Inicializar con INF y copiar datos antiguos
        for (int i = 0; i < n; i++){
            Arrays.fill(nuevMat[i], INF);
        }

        for (int i = 0; i < matAdy.length; i++){
            for (int j = 0; j < matAdy.length; j++){
                nuevMat[i][j] = matAdy[i][j];
            }
        }
        matAdy = nuevMat;
    }

    //Método que devuelve el índice interno de una ciudad.    
    private int obtIndex(String nom){
        return ciudades.getOrDefault(nom, -1);
    }

    //Método que agrega un arco dirigido entre dos ciudades con un peso específico.
    public void agregarArco(String origen, String destino, int peso){
        agregarCiudades(origen);
        agregarCiudades(destino);
        int i = obtIndex(origen);
        int j = obtIndex(destino);
        matAdy[i][j] = peso;
    }

    //Método que elimina el arco entre dos ciudades estableciendo el peso como infinito.
    public void eliminarArco(String origen, String destino){
        int i = obtIndex(origen);
        int j = obtIndex(destino);
        if (i != -1 && j != -1){
            matAdy[i][j] = INF;
        }
    }

    //Método que devuelve la matriz de adyacencia del grafo.
    public int[][] getmatAdy(){
        return matAdy;
    }

    //Método que devuelve la lista de ciudades en el grafo en el orden de sus índices.
    public List<String> getciudades(){
        return indCiud;
    }
}
