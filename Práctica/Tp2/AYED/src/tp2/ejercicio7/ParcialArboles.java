package tp2.ejercicio7;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {

    private BinaryTree<Integer> arbol;

    public ParcialArboles(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }

    // ===================== EJERCICIO 7 =====================

    public boolean isLeftTree(int num) {
        // Primero busca el nodo
        BinaryTree<Integer> nodo = buscarNodo (this.arbol, num);

        if (nodo == null || nodo.isEmpty()) {return false;}

        // Inicializa cantidades con -1 por requisito del enunciado 
        int cantIzq= -1;
        int cantDer= -1;

        if (nodo.hasLeftChild()) {
            cantIzq = contarNodosConUnicoHijo(nodo.getLeftChild());
        }
        if (nodo.hasRightChild()){
            cantDer = contarNodosConUnicoHijo (nodo.getRightChild());
        }
        
        // Retorna true si la cantidad de la Izquierda es mayor 
        return cantIzq > cantDer;
    }


        // Recorrido PreOrden (analiza primero raiz)
     private BinaryTree<Integer> buscarNodo (BinaryTree<Integer> a, int num){
         if (a == null || a.isEmpty()){ return null;}

            // Si le nodo raiz tiene el dato, retorno ese arbol/subarbol
         if ( a.getData() == num) {return a;}
        
         BinaryTree<Integer> res = null;

         if (a.hasLeftChild()) { res = buscarNodo(a.getLeftChild(), num); }

         // Si lo encuentra no sigue bsucando por el derecho
         if (res == null && a.hasRightChild()) {res = buscarNodo (a.getRightChild(), num);}

         return res;

        }

    private int contarNodosConUnicoHijo (BinaryTree<Integer> nodo){

        if (nodo == null || nodo.isEmpty()) {return 0;}
        int aux=0;

        boolean tieneHijoIzq = nodo.hasLeftChild() && !nodo.hasRightChild();
        boolean tieneHijoDer = !nodo.hasLeftChild() && nodo.hasRightChild(); 

        if (tieneHijoIzq || tieneHijoDer) { aux=1; }

        // Recursion 
        if (nodo.hasLeftChild()) {aux += contarNodosConUnicoHijo(nodo.getLeftChild());}
        if (nodo.hasRightChild()) {aux += contarNodosConUnicoHijo(nodo.getRightChild());}

        return aux;
    }


    // ===================== MAIN =====================

    public static void main(String[] args) {

        // Árbol del ejemplo del enunciado:
        //         2
        //        / \
        //       7   -5
        //      / \    \
        //     23  6    19
        //    /  \   \    \
        //   -3  55  11    4
        //                  \
        //                  18

        BinaryTree<Integer> tree = new BinaryTree<>(2);
        BinaryTree<Integer> n7   = new BinaryTree<>(7);
        BinaryTree<Integer> nm5  = new BinaryTree<>(-5);
        BinaryTree<Integer> n23  = new BinaryTree<>(23);
        BinaryTree<Integer> n6   = new BinaryTree<>(6);
        BinaryTree<Integer> n19  = new BinaryTree<>(19);
        BinaryTree<Integer> nm3  = new BinaryTree<>(-3);
        BinaryTree<Integer> n55  = new BinaryTree<>(55);
        BinaryTree<Integer> n11  = new BinaryTree<>(11);
        BinaryTree<Integer> n4   = new BinaryTree<>(4);
        BinaryTree<Integer> n18  = new BinaryTree<>(18);

        tree.addLeftChild(n7);
        tree.addRightChild(nm5);
        n7.addLeftChild(n23);
        n7.addRightChild(n6);
        nm5.addRightChild(n19);
        n23.addLeftChild(nm3);
        n23.addRightChild(n55);
        n6.addRightChild(n11);
        n19.addRightChild(n4);
        n4.addRightChild(n18);

        ParcialArboles pa = new ParcialArboles(tree);

        System.out.println("=== Ejercicio 7 ===");
        System.out.println("isLeftTree(7):   " + pa.isLeftTree(7));    // true  (izq:1 > der:0)
        System.out.println("isLeftTree(2):   " + pa.isLeftTree(2));    // false (izq:1 > der:3 → NO)
        System.out.println("isLeftTree(-5):  " + pa.isLeftTree(-5));   // true  (izq:2 > der:-1)
        System.out.println("isLeftTree(19):  " + pa.isLeftTree(19));   // false (izq:-1 > der:1 → NO)
        System.out.println("isLeftTree(-3):  " + pa.isLeftTree(-3));   // false (-1 > -1 → NO)
        System.out.println("isLeftTree(99):  " + pa.isLeftTree(99));   // false (no existe)
    }
}
