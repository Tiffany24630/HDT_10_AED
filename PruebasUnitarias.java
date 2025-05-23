/*
 * Autora: Tiffany Salazar Suarez
 * Código de carnét: 24630
 * Descripción: pruebas unitarias. 
 */

import org.junit.Test;
import static org.junit.Assert.*;

class PruebasUnitarias{
    @Test
    public void testAgregarYEliminarArco(){
        Grafo g = new Grafo();
        g.agregarArco("A", "B", 10);
        int[][] mat = g.getmatAdy();
        int i = g.getciudades().indexOf("A");
        int j = g.getciudades().indexOf("B");
        assertEquals(10, mat[i][j]);

        g.eliminarArco("A", "B");
        mat = g.getmatAdy();
        assertEquals(99999, mat[i][j]);
    }

    @Test
    public void testCalcularCentro(){
        int INF = 99999;
        int[][] distancias = {
            {0, 2, INF},
            {2, 0, 3},
            {INF, 3, 0}
        };
        List<String> ciudades = Arrays.asList("A", "B", "C");
        String centro = CentroGrafo.calcularCentro(distancias, ciudades);
        assertEquals("B", centro);
    }
}