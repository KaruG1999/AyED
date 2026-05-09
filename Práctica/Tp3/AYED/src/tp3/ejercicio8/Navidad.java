package tp3.ejercicio8;

import tp3.ejercicio1.GeneralTree;
import java.util.LinkedList;
import java.util.Queue;

public class Navidad {

    // Variable de instancia: el abeto ya construido
    private GeneralTree<String> abeto;

    public Navidad() {
        this.abeto = construirAbeto();
    }

    // ===================== EJERCICIO 8 =====================

        public String esAbetoNavidenio() {
        if (this.abeto == null || this.abeto.isEmpty()) return "No es un abeto navideño";

        // 1. Primero calculamos la altura para saber cuántos espacios máximos necesitamos
        int altura = calcularAltura(this.abeto);
        String visual = "";
        Queue<GeneralTree<String>> cola = new LinkedList<>();
        cola.add(this.abeto);
        
        int nivel = 0;

        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            if (nodosEnNivel != (nivel + 1)) return "No es un abeto navideño";

            // 2. Agregamos espacios iniciales para centrar (Padding)
            // A medida que bajamos de nivel, restamos espacios
            String filaActual = "";
            for (int s = 0; s < (altura - nivel); s++) {
                filaActual += " ";
            }

            for (int i = 0; i < nodosEnNivel; i++) {
                GeneralTree<String> aux = cola.remove();
                filaActual += aux.getData() + " ";

                for (GeneralTree<String> hijo : aux.getChildren()) {
                    cola.add(hijo);
                }
            }
            
            visual += filaActual + "\n";
            nivel++;
        }
        return visual;
    }

    // Método auxiliar para conocer la profundidad total
    private int calcularAltura(GeneralTree<String> nodo) {
        if (nodo.isLeaf()) return 1;
        int maxChildHeight = 0;
        for (GeneralTree<String> hijo : nodo.getChildren()) {
            maxChildHeight = Math.max(maxChildHeight, calcularAltura(hijo));
        }
        return 1 + maxChildHeight;
    }

    private GeneralTree<String> construirAbeto() {
        // Estructura de ejemplo que cumple la regla k+1
        GeneralTree<String> raiz = new GeneralTree<>("*");

        GeneralTree<String> h1 = new GeneralTree<>("*");
        GeneralTree<String> h2 = new GeneralTree<>("*");
        //nivel 1 debe tener 2 nodos. 
        
        raiz.addChild(h1);
        raiz.addChild(h2);

        // Nivel 2 debe tener 3 nodos
        h1.addChild(new GeneralTree<>("*"));
        h1.addChild(new GeneralTree<>("*"));
        h2.addChild(new GeneralTree<>("*"));

        return raiz;
    }

    public static void main(String[] args) {
        Navidad nav = new Navidad();
        System.out.println(nav.esAbetoNavidenio());
    }
}