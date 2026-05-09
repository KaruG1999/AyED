# Resolución — Tema 4 · 1ra Fecha Árboles y Heap (10/05/2025)

> Fuente: `tema 4 1er fecha 2025.pdf`

---

## Ejercicio 1 (5 puntos)

### Enunciado
Implementar en `ParcialArboles`:

```java
public static List<Integer> primerCaminoAlternanteCeroNoCero(GeneralTree<Integer> arbol)
```

**Idéntico al Tema 3.** Devuelve el **primer camino** válido DFS de izq a der, donde los valores **alternan entre terminar en cero y no terminar en cero**. **Terminar ni bien encuentre el primero.** Sin camino → lista vacía.

### Ejemplo del enunciado (Tema 4)
```
        20
       /  \
      3    7
     /|\    \
   42 10  6  50
         \
         80
```
- 20→3→42: cero→no→no ✗
- **20→3→10: cero→no→cero ✓ → PRIMER CAMINO → [20, 3, 10]**
- 20→3→6→80: cero→no→no→cero ✗ (6 y 3 mismo estado)
- 20→7→50: cero→no→cero ✓ (válido, pero 20→3→10 ya fue encontrado primero)

### Resolución (igual a Tema 3)

```java
public class ParcialArboles {

    public static List<Integer> primerCaminoAlternanteCeroNoCero(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            construirCamino(arbol, resultado, new LinkedList<>());
        }
        return resultado;
    }

    private static boolean construirCamino(GeneralTree<Integer> nodo,
                                           List<Integer> resultado,
                                           List<Integer> camino) {
        camino.add(nodo.getData());
        boolean terminaEnCero = nodo.getData() % 10 == 0;

        if (nodo.isLeaf()) {
            resultado.addAll(camino);
            camino.remove(camino.size() - 1);
            return true;
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                boolean hijoTerminaEnCero = hijo.getData() % 10 == 0;
                if (terminaEnCero != hijoTerminaEnCero) {
                    if (construirCamino(hijo, resultado, camino)) {
                        camino.remove(camino.size() - 1);
                        return true;
                    }
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }
}
```

---

## Ejercicio 2 (1.5 puntos)

### a) Expresión postfija de: `A+(B-C)*D/(E*F)`

Igual que Tema 3. **Respuesta: (b) `ABC-D*EF*/+`**

### b) BuildHeap Min-Heap con `G P C B A E H I`

Igual que Tema 3. **Respuesta: (a) `A B C G P E H I`**

### d) Sentencias

Igual que Tema 3. **Respuesta: (e) (ii) y (iv)**

- (ii) Un AB completo de altura H tiene 2^L nodos en cada nivel L hasta H-1 ✓
- (iv) Un AG lleno de grado K tiene k^L nodos en el nivel L ✓

---

## Ejercicio 3 (3.5 puntos)

### a) Max-Heap insertando uno a uno: `12 33 1 21 74 81 28 88 99`

Igual que Tema 3. **Max-Heap final: `[99, 88, 74, 81, 21, 1, 28, 12, 33]`**

```
              99
            /    \
          88      74
         /  \    /  \
        81  21   1   28
       / \
      12  33
```

### b) DeleteMax

Igual que Tema 3. **Max-Heap final: `[88, 81, 74, 33, 21, 1, 28, 12]`**

```
              88
            /    \
          81      74
         /  \    /  \
        33  21   1   28
       /
      12
```

---

## Diferencias entre Temas 1, 2, 3 y 4

| | Tema 1 | Tema 2 | Tema 3 | Tema 4 |
|---|---|---|---|---|
| **Método Ej1** | `caminoParidadAlternante` | `caminoSignoAlternante` | `primerCaminoAlternanteCeroNoCero` | `primerCaminoAlternanteCeroNoCero` |
| **Criterio** | camino MÁS LARGO | MAYOR COSTO (suma) | PRIMERO válido | PRIMERO válido |
| **Alternancia** | par / impar | positivo / negativo | termina en 0 / no termina en 0 | termina en 0 / no termina en 0 |
| **Nota especial** | 0=par | 0=positivo | terminar ni bien encuentre | terminar ni bien encuentre |
| **Heap Ej3** | Min-Heap Build + Insert | Min-Heap Insert + Delete | Max-Heap Insert + Delete | Max-Heap Insert + Delete |
