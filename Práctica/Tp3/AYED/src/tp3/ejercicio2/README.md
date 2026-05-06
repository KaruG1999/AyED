# Ejercicio 2: RecorridosAG — Impares mayores que N

## Enunciado

**a)** Implemente en la clase `RecorridosAG` los siguientes métodos. Cada uno retorna una lista con los elementos **impares del árbol que sean mayores al valor `n`**, usando el recorrido indicado:

```java
public List<Integer> numerosImparesMayoresQuePreOrden(GeneralTree<Integer> a, Integer n)
public List<Integer> numerosImparesMayoresQueInOrden(GeneralTree<Integer> a, Integer n)
public List<Integer> numerosImparesMayoresQuePostOrden(GeneralTree<Integer> a, Integer n)
public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree<Integer> a, Integer n)
```

**b)** ¿Qué modificaciones harías en firma e implementación si estos métodos estuvieran en `GeneralTree<T>`?

---

## Conceptos Técnicos Clave

### 1. Los cuatro recorridos — resumen

| Recorrido | Orden de procesamiento | Implementación |
|---|---|---|
| **PreOrden** | Raíz → cada hijo (recursivo) | Recursivo con `for` sobre hijos |
| **InOrden** | Primer hijo → raíz → hijos restantes | Recursivo: 1 hijo, raíz, rest |
| **PostOrden** | Todos los hijos → raíz | Recursivo: hijos, luego raíz |
| **Por niveles** | Nivel 0, nivel 1, nivel 2... | Iterativo con `Queue` |

### 2. Condición para agregar un elemento

Un dato `x` se agrega si cumple **ambas**:
```java
x % 2 != 0   // es impar
x > n         // es mayor que el umbral
```

### 3. Esquema PreOrden

```java
private void preOrden(GeneralTree<Integer> nodo, Integer n, List<Integer> resultado) {
    // 1. Procesar la raíz
    if (nodo.getData() % 2 != 0 && nodo.getData() > n)
        resultado.add(nodo.getData());
    // 2. Recursión sobre cada hijo
    for (GeneralTree<Integer> hijo : nodo.getChildren()) {
        preOrden(hijo, n, resultado);
    }
}
```

### 4. Esquema InOrden (AG)

```java
private void inOrden(GeneralTree<Integer> nodo, Integer n, List<Integer> resultado) {
    List<GeneralTree<Integer>> hijos = nodo.getChildren();
    // 1. Primer hijo
    if (!hijos.isEmpty()) inOrden(hijos.get(0), n, resultado);
    // 2. Raíz
    if (nodo.getData() % 2 != 0 && nodo.getData() > n)
        resultado.add(nodo.getData());
    // 3. Hijos restantes
    for (int i = 1; i < hijos.size(); i++) {
        inOrden(hijos.get(i), n, resultado);
    }
}
```

### 5. Esquema PostOrden

```java
private void postOrden(GeneralTree<Integer> nodo, Integer n, List<Integer> resultado) {
    // 1. Recursión sobre todos los hijos
    for (GeneralTree<Integer> hijo : nodo.getChildren()) {
        postOrden(hijo, n, resultado);
    }
    // 2. Procesar la raíz al final
    if (nodo.getData() % 2 != 0 && nodo.getData() > n)
        resultado.add(nodo.getData());
}
```

### 6. Esquema Por Niveles (BFS)

```java
Queue<GeneralTree<Integer>> cola = new LinkedList<>();
cola.add(a);
while (!cola.isEmpty()) {
    GeneralTree<Integer> actual = cola.poll();
    if (actual.getData() % 2 != 0 && actual.getData() > n)
        resultado.add(actual.getData());
    for (GeneralTree<Integer> hijo : actual.getChildren()) {
        cola.add(hijo);
    }
}
```

### 7. Parte b — ¿Cómo pasarlo a GeneralTree\<T\>?

El problema es que dentro de `GeneralTree<T>` no podemos usar `%` ni `>` directamente sobre `T`.  
Opciones:
- **Restringir T**: `public <T extends Integer>` — pero limita demasiado.
- **Usar `Predicate<T>`**: `public List<T> filtrar(Predicate<T> condicion)` — más flexible.
- La firma genérica también pierde el `Integer n` si T no es comparable.

---

## Ejemplo esperado

Para el árbol de prueba con umbral `n = 2`:
- PreOrden:    `[3, 5, 9, 7]`
- InOrden:     `[5, 9, 3, 7]`
- PostOrden:   `[5, 9, 3, 7]`
- Por niveles: `[3, 7, 5, 9]`

(Los mismos elementos, en distintos órdenes según el recorrido)

## Archivos
| Archivo | Descripción |
|---|---|
| `RecorridosAG.java` | Clase con los TODOs a implementar |
