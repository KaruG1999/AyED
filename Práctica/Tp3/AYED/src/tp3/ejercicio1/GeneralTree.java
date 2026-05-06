package tp3.ejercicio1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GeneralTree<T> {

    private T data;
    private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();

    public GeneralTree(T data) {
        this.data = data;
    }

    public GeneralTree(T data, List<GeneralTree<T>> children) {
        this(data);
        this.children = children;
    }

    // ===================== GETTERS / SETTERS =====================

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<GeneralTree<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<GeneralTree<T>> children) {
        if (children != null)
            this.children = children;
    }

    // ===================== ESTRUCTURA =====================

    public void addChild(GeneralTree<T> child) {
        getChildren().add(child);
    }

    public void removeChild(GeneralTree<T> child) {
        if (this.hasChildren()) {
            children.remove(child);
        }
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public boolean isLeaf() {
        return !hasChildren();
    }

    public boolean isEmpty() {
        return data == null && !this.hasChildren();
    }

    // ===================== RECORRIDOS =====================

    // Recorre arbol a medida que imprime 
    public void printPreOrder() {
        // primero imprime
        System.out.println(getData());
        // luego recorre hijos de manera recursiva
        for (GeneralTree<T> child : this.getChildren()) {
            child.printPreOrder();
        }
    }

    // Recorre arbol a medida que almacen en lista
    public List<T> preOrder() {
        List<T> lista = new LinkedList<>();
        this.preOrder(lista); // Utiliza el método de abajo para recorrer
        return lista;
    }

    // Método interno que agrega los datos del arbol a la lista pasada por parametro
    private void preOrder(List<T> l) {
        l.add(this.getData());
        for (GeneralTree<T> child : this.getChildren()) {
            child.preOrder(l);
        }
    }

    // Retorna una lista con los datos del arbol y la raiz queda al final 
    public List<T> postOrder() {
        List<T> lista = new LinkedList<>();
        this.postOrder(lista);
        return lista;
    }

    // Método interno que recorre lista hijos y luego raiz agregando los datos
    private void postOrder(List<T> l) {
        for (GeneralTree<T> child : this.getChildren()) {
            child.postOrder(l);
        }
        l.add(this.getData());
    }

    public List<T> inOrder() {
        List<T> lista = new LinkedList<>();
        this.inOrder(lista);
        return lista;
    }

    // Recorrido por niveles(BFS) !! Importante
    public List<T> traversalLevel() {
        List<T> result = new LinkedList<>();

        Queue<GeneralTree<T>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            GeneralTree<T> aux = queue.poll();
            result.add(aux.getData());
            for (GeneralTree<T> child : aux.getChildren()) {
                queue.add(child);
            }
        }
        return result;
    }

    // ===================== EJERCICIO 3 — TODO =====================

    public int altura() {
        if (this.isEmpty()) {return -1;}

        return alturaHelper(0);
    }

    private int alturaHelper (int h){
        if (this.isLeaf()) { return h;}
        else {
            int altAct =0;
            List<GeneralTree<T>> hijos = this.getChildren();
            for (GeneralTree<T> hijo: hijos){
                altAct = Math.max(altAct, hijo.alturaHelper(h+1));
            }
            return altAct;
        }
    }

    public int nivel(T dato) {
        if (this != null && !this.isEmpty()) {
            Queue<GeneralTree<T>> cola = new LinkedList<>();
            cola.add(this);
            int nivel =0;
            boolean encontre = false;

            while (!cola.isEmpty()) {
                int tamaño = cola.size();
                int i =0;
                while (i< tamaño && !encontre) {
                    GeneralTree<T> aux = cola.remove();
                    if (aux.getData().equals(dato)) {
                        encontre = true;
                    }
                    for (GeneralTree<T> hijo : aux.getChildren()) {
                        cola.add(hijo);
                    }
                    i++;
                }
                if (encontre) {return nivel;}
                nivel ++;
            }
        }
        return -1;
    }

    public int ancho() {
        if (this != null && !this.isEmpty()){
            
        }
        GeneralTree<T> 


        return 0;
    }

    // ===================== EJERCICIO 5 — TODO =====================

    public boolean esAncestro(T a, T b) {
        // TODO: Ejercicio 5
        // "a" es ancestro de "b" si existe un camino de a hacia b
        // Buscar el nodo con dato "a"; una vez encontrado, buscar "b" en su subárbol
        return false;
    }
}
