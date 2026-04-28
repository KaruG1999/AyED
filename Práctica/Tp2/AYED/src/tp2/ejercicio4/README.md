# Ejercicio 4: RedBinariaLlena — Mayor Retardo de Reenvío

## Enunciado

Una **red binaria** es una red con topología de árbol binario **lleno** (cada nodo tiene exactamente 0 o 2 hijos).

Cada nodo conoce su **retardo de reenvío**: el tiempo entre que recibe un mensaje y lo envía a sus dos hijos.

**Tarea:** Calcular el **mayor retardo posible** en el camino que realiza un mensaje desde la raíz hasta llegar a las hojas.

En el ejemplo del enunciado, el resultado es `10+3+9+12 = 34`.
Si hay más de un máximo, retornar el **último valor hallado**.

**a)** Indicar qué estrategia (profundidad o por niveles) se utilizará.

**b)** Implementar en clase `RedBinariaLlena` el método `retardoReenvio():int`.

---

## Conceptos Técnicos Clave

### 1. ¿Profundidad (DFS) o Anchura (BFS)?

**La respuesta es DFS (Profundidad).**

Para calcular la suma de un **camino completo raíz → hoja**, necesitamos acumular valores a lo largo de ese camino. DFS es natural para esto porque la recursión mantiene el estado del camino en el stack de llamadas.

BFS visita nodo por nodo por niveles, sin "memoria" de qué camino se está siguiendo, lo que haría más complejo acumular la suma.

### 2. Estrategia DFS con acumulador

```
retardoMaximo(nodo, sumaActual):
    sumaActual += nodo.getData()
    
    Si nodo es hoja:
        retornar sumaActual
    
    Sino:
        retornar max(
            retardoMaximo(hijo_izquierdo, sumaActual),
            retardoMaximo(hijo_derecho, sumaActual)
        )
```

### 3. Manejo del "último máximo"

La consigna pide el **último** hallado si hay empate. Esto afecta el orden en que se comparan los subárboles:
- Si usás `Math.max(izq, der)` → devuelve el primero cuando empatan.
- Para el último: comparar con `>=` en lugar de `>` al actualizar el máximo.

### 4. Red Binaria Llena vs Árbol Binario normal

En un árbol lleno, **todos los nodos internos tienen exactamente 2 hijos**. Las hojas son los nodos terminales. Esto simplifica el caso base: si `isLeaf()` entonces estamos en el final del camino.

---

## Caminos del Ejemplo

```
              10
            /    \
           2      3
          / \    / \
         5   4  9   8
        /\ /\ /\   /\
       7 8 5 6 12 8  2 1

Camino 1: 10→2→5→7  = 24
Camino 2: 10→2→5→8  = 25
Camino 3: 10→2→4→5  = 21
Camino 4: 10→2→4→6  = 22
Camino 5: 10→3→9→12 = 34  ← MÁXIMO
Camino 6: 10→3→9→8  = 30
Camino 7: 10→3→8→2  = 23
Camino 8: 10→3→8→1  = 22
```
