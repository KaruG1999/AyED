# Resolución — Tema 2 · 1ra Fecha Árboles y Heap (10/05/2025)

> Fuentes: `parcial tema 2, 2025..pdf` + resoluciones de Mendivina Juan y Dobal Federico (10pts ambos)

---

## Ejercicio 1 (5 puntos)

### Enunciado
Implementar en `ParcialArboles`:

```java
public static List<Integer> caminoSignoAlternante(GeneralTree<Integer> arbol)
```

Devuelve los nodos del camino de **mayor costo** (suma total de valores) desde la raíz hasta una hoja, donde los valores **alternan entre positivos y negativos** en cada paso. Si varios caminos tienen el mismo costo máximo, devolver el primero encontrado de izq a der. Sin camino válido → lista vacía. **Nota: 0 es positivo.**

### Ejemplo del enunciado
```
         5
        / \
      -2   -4
      /|\    \
     7 -5  8   6
              \
              -1
```
- 5→-2→7  (+→-→+) ✓ Costo=10
- 5→-2→8→-1 (+→-→+→-) ✓ Costo=10  ← mismo costo, pero 5→-2→7 se encuentra primero
- 5→-4→6  (+→-→+) ✓ Costo=7
- 5→-2→-5 (+→-→-)  ✗ No alterna
- **Devuelve: [5, -2, 7]**

### Resolución

```java
public class ParcialArboles {

    public static List<Integer> caminoSignoAlternante(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            construirCamino(arbol, resultado, new LinkedList<>(), 0, Integer.MIN_VALUE);
        }
        return resultado;
    }

    private static int construirCamino(GeneralTree<Integer> nodo,
                                       List<Integer> resultado,
                                       List<Integer> camino,
                                       int sumaActual,
                                       int sumaMax) {
        int data = nodo.getData();
        camino.add(data);
        sumaActual += data;
        boolean esPositivo = data >= 0;  // 0 es positivo

        if (nodo.isLeaf()) {
            if (sumaActual > sumaMax) {
                sumaMax = sumaActual;
                resultado.clear();
                resultado.addAll(camino);
            }
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                boolean hijoEsPositivo = hijo.getData() >= 0;
                if (esPositivo != hijoEsPositivo) {  // alternan signo
                    sumaMax = construirCamino(hijo, resultado, camino, sumaActual, sumaMax);
                }
            }
        }

        camino.remove(camino.size() - 1);  // backtracking
        return sumaMax;
    }
}
```

**Puntos clave:**
- `>=0` (no `>0`) para que el 0 cuente como positivo — error típico en los parciales
- `sumaMax` se retorna y se acumula a lo largo de todas las ramas (no es una variable global)
- `Integer.MIN_VALUE` como valor inicial garantiza que cualquier suma válida lo reemplace
- Sólo actualiza cuando llega a **hoja** (no a un nodo sin hijos válidos)
- Backtracking igual que siempre: `camino.remove(camino.size() - 1)`

---

## Ejercicio 2 (1.5 puntos)

### a) Expresión infija de la postfija: `ABC*+AB+C*/`

**Construir el árbol desde la postfija** (pila):

| Token | Acción | Pila |
|-------|--------|------|
| A | push | [A] |
| B | push | [A, B] |
| C | push | [A, B, C] |
| * | pop C, B → nodo(*, B, C) | [A, (B*C)] |
| + | pop (B*C), A → nodo(+, A, B*C) | [(A+(B*C))] |
| A | push | [(A+(B*C)), A] |
| B | push | [(A+(B*C)), A, B] |
| + | pop B, A → nodo(+, A, B) | [(A+(B*C)), (A+B)] |
| C | push | [(A+(B*C)), (A+B), C] |
| * | pop C, (A+B) → nodo(*, A+B, C) | [(A+(B*C)), ((A+B)*C)] |
| / | pop ((A+B)*C), (A+(B*C)) → raíz(/) | [(A+(B*C))/((A+B)*C)] |

**InOrden del árbol = expresión infija: `(A+(B*C))/((A+B)*C)`**

**Respuesta: (b)** — notar que `A+B*C` = `A+(B*C)` por precedencia matemática

### b) BuildHeap Min-Heap con `10 20 8 3 2 7 24 35`

Array (0-indexed): `[10, 20, 8, 3, 2, 7, 24, 35]`  N=8

```
i=3: A[3]=3, hijos: A[7]=35. 3<35 → sin cambio
i=2: A[2]=8, hijos: A[5]=7, A[6]=24. mínimo=7 → swap(2,5)
     [10, 20, 7, 3, 2, 8, 24, 35]
i=1: A[1]=20, hijos: A[3]=3, A[4]=2. mínimo=2 → swap(1,4)
     [10, 2, 7, 3, 20, 8, 24, 35]
     Sift-down desde 4: sin hijos → fin
i=0: A[0]=10, hijos: A[1]=2, A[2]=7. mínimo=2 → swap(0,1)
     [2, 10, 7, 3, 20, 8, 24, 35]
     Sift-down desde 1: hijos A[3]=3, A[4]=20. mínimo=3 → swap(1,3)
     [2, 3, 7, 10, 20, 8, 24, 35]
     Sift-down desde 3: hijo A[7]=35. 10<35 → fin
```

**Min-Heap final (= recorrido por niveles): `2 3 7 10 20 8 24 35`**

**Respuesta: (b) `2 3 7 10 20 8 24 35`**

### c) Sentencias sobre AG y AB

- **(i) La altura de un AG es el máximo nivel de alguna de sus hojas** → **VERDADERO**
  (Altura = longitud del camino más largo desde raíz hasta hoja = máximo nivel de hojas)
- **(ii) Un AB lleno tiene hojas en más de un nivel** → **FALSO**
  (En un AB lleno, TODAS las hojas están en el mismo nivel h)
- **(iii) Un AB completo puede almacenarse en un arreglo** → **VERDADERO**
  (Propiedad fundamental del AB completo: se mapea perfectamente a un array)

**Respuesta: (d) (i) y (iii)**

---

## Ejercicio 3 (3.5 puntos)

### a) Min-Heap insertando uno a uno: `150 100 20 16 70 85 140 54 139`

```
Insert(150): [150]
Insert(100): [150,100] → sift-up: 100<150 → swap → [100,150]
Insert(20):  [100,150,20] → sift-up: 20<100 → swap → [20,150,100]
Insert(16):  [20,150,100,16] → sift-up: 16<150→swap→[20,16,100,150]→16<20→swap→[16,20,100,150]
Insert(70):  [16,20,100,150,70] → sift-up: 70>20 → stop. Sin cambio
Insert(85):  [16,20,100,150,70,85] → sift-up: 85<100→swap→[16,20,85,150,70,100]→85>20→stop
Insert(140): [16,20,85,150,70,100,140] → sift-up: 140>85 → stop. Sin cambio
Insert(54):  [16,20,85,150,70,100,140,54] → sift-up: 54<150→swap→[16,20,85,54,70,100,140,150]→54>20→stop
Insert(139): [16,20,85,54,70,100,140,150,139] → sift-up: 139>54 → stop. Sin cambio
```

**Min-Heap final: `[16, 20, 85, 54, 70, 100, 140, 150, 139]`**

```
              16
            /    \
          20      85
         /  \    /  \
        54   70 100  140
       / \
     150  139
```

### b) DeleteMin

1. Sacar raíz (16), poner último (139) en la raíz, reducir tamaño:
   `[139, 20, 85, 54, 70, 100, 140, 150]`
2. Sift-down desde 0: hijos A[1]=20, A[2]=85. mínimo=20 → swap(0,1)
   `[20, 139, 85, 54, 70, 100, 140, 150]`
3. Sift-down desde 1: hijos A[3]=54, A[4]=70. mínimo=54 → swap(1,3)
   `[20, 54, 85, 139, 70, 100, 140, 150]`
4. Sift-down desde 3: hijo A[7]=150. 139<150 → stop

**Min-Heap final después de DeleteMin: `[20, 54, 85, 139, 70, 100, 140, 150]`**

```
              20
            /    \
          54      85
         /  \    /  \
       139   70 100  140
       /
     150
```
