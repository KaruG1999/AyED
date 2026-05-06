package tp2.ejercicio9;

import tp2.ejercicio1.BinaryTree;

class DobleInteger {
    private int suma;
    private int dif;

    public DobleInteger(int suma, int dif) {
        this.suma = suma;
        this.dif = dif;
    }

    public int getSuma() {
        return suma;
    }

    public int getDif() {
        return dif;
    }

    @Override
    public String toString() {
        return "DobleInteger{suma=" + suma + ", dif=" + dif + "}";
    }
}

public class ParcialArboles {

    // ===================== EJERCICIO 9 =====================

    /*  1. si el recorrido no es por niveles, usar helper */

    public static BinaryTree<DobleInteger> sumAndDif(BinaryTree<Integer> arbol) {

        BinaryTree<DobleInteger> nuevoArbol = new BinaryTree<DobleInteger>();

        if(!arbol.isEmpty())

            sumAndDif(arbol,nuevoArbol,0,0);

        return nuevoArbol;

    }

    

    private void sumAndDif(BinaryTree<Integer> arbol, BinaryTree<DobleInteger> nuevoArbol, Integer sum, Integer padre) {

        int suma = sum + arbol.getData();

        int dif = arbol.getData() - padre;

        

        nuevoArbol.setData(new DobleInteger(suma,dif));

        

        if(arbol.hasLeftChild()) {

            nuevoArbol.addLeftChild(new BinaryTree<DobleInteger>());

            sumAndDif(arbol.getLeftChild(),nuevoArbol.getLeftChild(),suma,arbol.getData());

        }

        if(arbol.hasRightChild()) {

            nuevoArbol.addRightChild(new BinaryTree<DobleInteger>());

            sumAndDif(arbol.getRightChild(),nuevoArbol.getRightChild(),suma,arbol.getData());

        }

    }

    // ===================== MAIN =====================

    public static void main(String[] args) {

        BinaryTree<Integer> arbol = new BinaryTree();

        

        arbol.setData(8);

        arbol.addRightChild(new BinaryTree<>(10));

        arbol.addLeftChild(new BinaryTree<>(15));

        

        arbol.inOrder();

        System.out.println("");

        ParcialArboles.sumAndDif(arbol).inOrder();

    }
        if (resultado != null) {
            System.out.println("Raíz: " + resultado.getData()); // esperado: 20|20
            // Árbol esperado:
            //        20|20
            //       /      \
            //    25|-15    50|10
            //    /   \     /    \
            // 20|-10 35|5 100|20 41|-39
            //        /    \
            //      36|-9  104|-46
            //                \
            //               110|2
        }
    }
}
