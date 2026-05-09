# REPASO COMPLETO — AyED Árboles y Heap
> Todo lo que entra en el examen. Teoría + ejemplo con pasos.

---

# PARTE 1 — ÁRBOLES BINARIOS (AB)

## 1.1 Conceptos básicos

Un **árbol binario** es una estructura recursiva: cada nodo tiene **a lo sumo 2 hijos** (izquierdo y derecho).

```
        7          ← raíz (nivel 0, profundidad 0)
       / \
      3   4        ← nivel 1
     / \ / \
    8  12 11  2    ← nivel 2
```

| Término | Definición | Ejemplo arriba |
|---|---|---|
| **Raíz** | El nodo superior, sin padre | 7 |
| **Hoja** | Nodo sin hijos | 8, 12, 11, 2 |
| **Profundidad de un nodo** | Distancia desde la raíz (raíz = 0) | Prof(3) = 1 |
| **Altura de un nodo** | Distancia al descendiente más lejano (hoja = 0) | Altura(3) = 1 |
| **Altura del árbol** | Altura de la raíz | Altura(7) = 2 |
| **Grado** | Cantidad de hijos de un nodo | Grado(7) = 2 |

> **Profundidad ≠ Altura**: profundidad mide HACIA ARRIBA (desde la raíz al nodo). Altura mide HACIA ABAJO (del nodo a su hoja más lejana).

---

## 1.2 AB Lleno vs AB Completo

### AB Lleno
- Cada nodo interno tiene **exactamente 2 hijos**
- Todas las hojas están en el **mismo nivel h**
- Fórmula: **N = 2^(h+1) − 1**
- Con L hojas: **N = 2L − 1** (porque L = I + 1)

```
     A          ← h = 2, N = 7 = 2^3 - 1 = 7 ✓
    / \
   B   C
  /\ /\
 D E F G        ← 4 hojas, N = 2*4 - 1 = 7 ✓
```

### AB Completo
- Niveles 0 a h−1 completamente llenos
- Nivel h se llena de **izquierda a derecha**
- Fórmula: **2^h ≤ N ≤ 2^(h+1) − 1**
- Mínimo de nodos con altura h: **N = 2^h** (un solo nodo en el último nivel)

```
     A          ← h = 2, 5 nodos: 2^2=4 ≤ 5 ≤ 7=2^3-1 ✓
    / \
   B   C
  / \
 D   E          ← nivel h incompleto, pero relleno de izq a der
```

| | Lleno | Completo |
|---|---|---|
| Hojas al mismo nivel | ✓ Siempre | ✗ No necesariamente |
| ¿Todo lleno es completo? | → SÍ | — |
| ¿Todo completo es lleno? | — | → NO |
| Se almacena en arreglo | ✓ | ✓ |

---

## 1.3 Los 4 recorridos

```
        C
       / \
      A   E
       \  / \
        B D   F
```

| Recorrido | Orden | Resultado |
|---|---|---|
| **PreOrden** | raíz → izq → der | C A B E D F |
| **InOrden** | izq → raíz → der | A B C D E F |
| **PostOrden** | izq → der → raíz | B A D F E C |
| **Por Niveles** | nivel a nivel (BFS, usa Cola) | C A E B D F |

### Propiedades clave para el examen:
- **Último en PostOrden = raíz SIEMPRE**
- **Primero en PreOrden = raíz SIEMPRE**
- **Por Niveles = el array del Heap** (esto aparece en el examen)

---

## 1.4 Reconstruir árbol desde dos recorridos

Con **InOrden + PostOrden** (o InOrden + PreOrden) se reconstruye el árbol unívocamente.

### Algoritmo:
1. El **último de PostOrden** = raíz (o el **primero de PreOrden**)
2. Buscar esa raíz en InOrden → todo a su izquierda = subárbol izquierdo, todo a su derecha = subárbol derecho
3. Aplicar recursivamente

### Ejemplo:
- PostOrden: `A G F E B J I H C D`
- InOrden: `A B G E F D J H I C`

**Paso 1:** Raíz = **D** (último del PostOrden)

**Paso 2:** En InOrden: `A B G E F [D] J H I C`
- Izquierdo: `A B G E F` (5 nodos)
- Derecho: `J H I C` (4 nodos)

**Paso 3 (subárbol derecho):** PostOrden de los 4 últimos antes de D = `J I H C` → raíz = **C**
InOrden del subárbol derecho: `J H I [C]` → C no tiene der, izq = `J H I`

**Paso 4:** PostOrden de `J H I` = `J I H` → raíz = **H**
InOrden: `J [H] I` → H tiene izq=J, der=I

```
  D
 / \
...  C
    /
   H
  / \
 J   I
```
C tiene 3 descendientes: H, J, I → **Respuesta: (c) 3**

---

# PARTE 2 — ÁRBOLES DE EXPRESIÓN

## 2.1 Estructura

- **Nodos internos** = operadores (+, -, *, /, ^)
- **Hojas** = operandos (letras o números)
- El árbol determina la precedencia sin paréntesis

## 2.2 Notaciones ↔ Recorridos

| Notación | Recorrido | Ejemplo |
|---|---|---|
| **Prefija** (polaca) | PreOrden | `+ * a b c` |
| **Infija** (normal) | InOrden | `(a * b) + c` |
| **Postfija** (polaca inversa) | PostOrden | `a b * c +` |

---

## 2.3 Postfija → Árbol (usando Pila)

**Regla:** operando → push. Operador → pop 2 (primero = hijo DER, segundo = hijo IZQ), crear nodo, push.

### Ejemplo: `6 5 * 7 3 − 4 8 + * +`

| Token | Acción | Pila |
|---|---|---|
| 6 | push | [6] |
| 5 | push | [6,5] |
| * | pop 5(der), pop 6(izq) → nodo(*, 6, 5) | [(6*5)] |
| 7 | push | [(6*5), 7] |
| 3 | push | [(6*5), 7, 3] |
| − | pop 3(der), pop 7(izq) → nodo(−, 7, 3) | [(6*5), (7−3)] |
| 4 | push | [(6*5), (7−3), 4] |
| 8 | push | [(6*5), (7−3), 4, 8] |
| + | pop 8(der), pop 4(izq) → nodo(+, 4, 8) | [(6*5), (7−3), (4+8)] |
| * | pop (4+8)(der), pop (7−3)(izq) → nodo(*, ...) | [(6*5), ((7−3)*(4+8))] |
| + | pop resultado(der), pop (6*5)(izq) → raíz | [árbol completo] |

```
             +
            / \
           *   *
          / \ / \
         6  5 −   +
             /\ /\
            7 3 4  8
```

**Para evaluar** (sin construir el árbol, directo con pila):
- Mismo proceso pero en vez de crear nodo, calcular: `pop O2, pop O1 → push (O1 op O2)`
- Resultado del ejemplo: 30 + (4 × 12) = 30 + 48 = **78**

---

## 2.4 Prefija → Árbol (recursivo)

**Regla:** el primer token siempre es la raíz. Si es operador, toma los 2 siguientes subárboles como hijos.

### Ejemplo: `+ + a e / * - b c d f`

```
Parseo:
+  → raíz
  +  → hijo izquierdo
    a  → hoja
    e  → hoja
  /  → hijo derecho
    *  → hijo izquierdo
      -  → hijo izquierdo
        b  → hoja
        c  → hoja
      d  → hoja
    f  → hoja
```

```
           +               ← nivel 0
          / \
         +   /             ← nivel 1
        / \ / \
       a  e *   f          ← nivel 2
           / \
          -   d            ← nivel 3 (d está en profundidad 3)
         / \
        b   c              ← nivel 4
```
Profundidad de d = **3** → opción (c)

---

## 2.5 Infija → Árbol (dos pasos)

**Paso 1:** Infija → Postfija con pila (respetar precedencia)
**Paso 2:** Postfija → árbol (sección 2.3)

**Precedencia:** `^` > `* /` > `+ -`

### Ejemplo: `A/(C-D)*(E+F)` → postfija

Parseando izquierda a derecha:
```
A       → salida: A
/       → pila: [/]
(       → pila: [/, (]
C       → salida: A C
-       → pila: [/, (, -]
D       → salida: A C D
)       → desapilar hasta (: salida: A C D -
*       → / tiene igual prioridad → desapilar /: salida: A C D - /
        → pila: [*]
(       → pila: [*, (]
E       → salida: A C D - / E
+       → pila: [*, (, +]
F       → salida: A C D - / E F
)       → desapilar hasta (: salida: A C D - / E F +
fin     → desapilar todo: salida: A C D - / E F + *
```
**Postfija: `A C D - / E F + *`**

---

# PARTE 3 — ÁRBOLES GENERALES (AG)

## 3.1 Conceptos básicos

Un **árbol general** permite **cualquier cantidad de hijos** por nodo.

```
              A                ← nivel 0, raíz
           / | \
          B  C   D             ← nivel 1
         /\      /|\
        E  F    G  H  I        ← nivel 2
          /|\   |  |\
         J K L  M  N  O        ← nivel 3
                |\
               P  Q            ← nivel 4
```

| Término | Valor en el árbol |
|---|---|
| Raíz | A |
| Padre de B, C, D | A |
| Hermanos | B, C, D son hermanos. E, F son hermanos. |
| Hojas | E, C, J, K, L, I, N, O, P, Q |
| Profundidad de C | 1 |
| Altura de C | 0 (es hoja) |
| Altura de D | 3 (D→G→M→P) |
| Altura del árbol | 4 (A→B→F→J→... o A→D→G→M→P) |
| Grado de D | 3 (tiene 3 hijos) |
| Grado del árbol | 3 (máximo grado entre todos los nodos) |

---

## 3.2 Recorridos en AG

### PreOrden: raíz → hijos de izquierda a derecha (recursivo)
`A B E F J K L C D G M P Q H N O I`

### InOrden en AG: **primer hijo → raíz → hijos restantes**
> Este es el que más confunde. La regla: visitar el subárbol del **primer hijo**, luego la **raíz**, luego los subárboles de **los demás hijos**.

Para A (hijos B, C, D):
- InOrden(B), luego A, luego InOrden(C), InOrden(D)

Para B (hijos E, F):
- InOrden(E), luego B, luego InOrden(F) = E, B, (J, F, K, L)

Resultado completo: `E B J F K L A C P M Q G D N H O I`

### PostOrden: todos los hijos recursivos → raíz
`E J K L F B C P Q M G N O H I D A`

### Por Niveles (BFS): nivel 0, nivel 1, etc.
`A B C D E F G H I J K L M N O P Q`

---

## 3.3 AG Lleno de grado k

- Todo nodo interno tiene **exactamente k hijos**
- Todas las hojas en el **mismo nivel h**
- **N = (k^(h+1) − 1) / (k − 1)**
- Nodos en nivel L: **k^L**
- Nodos internos con L hojas: **I = (L − 1) / (k − 1)**

### Ejemplo: AG lleno de grado 4 con 21 nodos. ¿Cuál es la altura?
```
21 = (4^(h+1) - 1) / (4-1)
21 × 3 = 4^(h+1) - 1
63 + 1 = 64 = 4^3 → h+1 = 3 → h = 2
```
**Altura = 2** (nivel 0: 1 nodo, nivel 1: 4 nodos, nivel 2: 16 nodos → total 21 ✓)

### Ejemplo: AG lleno de grado 5 con 125 hojas. ¿Cuántos nodos internos?
```
Hojas = k^h → 125 = 5^h → 5^3 = 125 → h = 3
I = (L-1)/(k-1) = (125-1)/(5-1) = 124/4 = 31
```
**31 nodos internos**

---

## 3.4 AG Completo de grado k

- Niveles 0 a h−1 completamente llenos
- Nivel h con al menos 1 nodo (puede estar incompleto)
- **Mínimo:** árbol lleno de h-1 + exactamente 1 nodo en nivel h:
  **N_min = (k^h + k − 2) / (k − 1)**
- **Máximo:** árbol lleno de altura h:
  **N_max = (k^(h+1) − 1) / (k − 1)**

### Ejemplo: AG completo de grado 3, altura 4. ¿Cuántos nodos mínimos?
```
N_min = (3^4 + 3 - 2) / (3-1) = (81 + 1) / 2 = 82/2 = 41
```
**Respuesta: (b) 41**

### Ejemplo: AG completo de grado 4, altura 3. ¿Cuántos nodos?
```
N_min = (4^3 + 4 - 2) / 3 = (64 + 2) / 3 = 66/3 = 22
N_max = (4^4 - 1) / 3 = 255/3 = 85
```
**Respuesta: entre 22 y 85 → opción (b)**

---

# PARTE 4 — HEAP (Cola de Prioridad)

## 4.1 ¿Qué es un Heap?

Un **Heap** es un **árbol binario completo** con **propiedad de orden**:
- **Min-Heap:** cada nodo es ≤ que sus hijos → la **raíz es el mínimo**
- **Max-Heap:** cada nodo es ≥ que sus hijos → la **raíz es el máximo**

Se almacena en un **arreglo** (por ser AB completo). Relaciones (0-indexed):
```
left(i)   = 2i + 1
right(i)  = 2i + 2
parent(i) = (i - 1) / 2
```

```
Min-Heap:          Array: [1, 3, 2, 7, 5, 4, 6]
        1
       / \
      3   2
     / \ / \
    7  5 4  6
```

---

## 4.2 Insert (insertar uno a uno)

1. Agregar el nuevo elemento al **final del arreglo**
2. **Sift-up:** comparar con el padre, swap si viola la propiedad, repetir hacia arriba

### Ejemplo: Min-Heap. Insertar 54, 70 y 85 en `[16, 20, 100, 150]`

**Insert(54):**
```
Array: [16, 20, 100, 150, 54]
       índice 4, padre = (4-1)/2 = 1 → A[1]=20
       54 > 20 → stop (Min-Heap: hijo debe ser mayor)
Array final: [16, 20, 100, 150, 54]
```

**Insert(70):** no cambia (70 > 20 al comparar con padre)

**Insert(85):**
```
Array: [16, 20, 100, 150, 54, 70, 85]... wait, inserto en posición 6
       índice 6, padre = (6-1)/2 = 2 → A[2]=100
       85 < 100 → swap → [16, 20, 85, 150, 54, 70, 100]
       índice 2, padre = (2-1)/2 = 0 → A[0]=16
       85 > 16 → stop
```

### Truco mental para Sift-Up (Min-Heap):
> El elemento SUBE mientras sea **MENOR** que su padre.

### Truco mental para Sift-Up (Max-Heap):
> El elemento SUBE mientras sea **MAYOR** que su padre.

---

## 4.3 DeleteMin / DeleteMax

1. Guardar la raíz (es lo que se elimina y devuelve)
2. Mover el **último elemento** del arreglo a la **raíz**
3. Reducir el tamaño en 1
4. **Sift-down:** comparar con los hijos, swap con el más chico (Min) o más grande (Max), repetir hacia abajo

### Ejemplo completo: DeleteMin en Min-Heap `[16, 20, 85, 54, 70, 100, 140, 150, 139]`

```
Paso 1: Sacar raíz (16). Poner último (139) en la raíz.
        [139, 20, 85, 54, 70, 100, 140, 150]  (N=8)

Paso 2: Sift-down desde índice 0:
        Hijos: A[1]=20, A[2]=85. Mínimo=20 (índice 1). 20 < 139 → swap(0,1)
        [20, 139, 85, 54, 70, 100, 140, 150]

Paso 3: Sift-down desde índice 1:
        Hijos: A[3]=54, A[4]=70. Mínimo=54 (índice 3). 54 < 139 → swap(1,3)
        [20, 54, 85, 139, 70, 100, 140, 150]

Paso 4: Sift-down desde índice 3:
        Hijo: A[7]=150. 150 > 139 → stop.
```

**Resultado: `[20, 54, 85, 139, 70, 100, 140, 150]`**

---

## 4.4 BuildHeap (construir desde un array desordenado)

**Más eficiente** que insertar uno a uno: O(n) en vez de O(n log n).

### Algoritmo:
1. Tomar el array tal cual (ya está "en el árbol", pero sin propiedad de orden)
2. Empezar desde el **último nodo interno**: `i = N/2 - 1` (0-indexed)
3. Hacer **sift-down** en i, luego i-1, i-2, ... hasta llegar a la raíz (i=0)

### Ejemplo: BuildHeap Min-Heap con `[10, 20, 8, 3, 2, 7, 24, 35]` N=8

```
Árbol inicial:
           10
          /   \
        20     8
       /  \   / \
      3    2 7  24
     /
    35

Último nodo interno: i = 8/2 - 1 = 3
```

**i=3:** A[3]=3, hijo A[7]=35. 3 < 35 → sin cambio.
```
[10, 20, 8, 3, 2, 7, 24, 35]
```

**i=2:** A[2]=8, hijos A[5]=7, A[6]=24. Mínimo=7. 7 < 8 → swap(2,5).
```
[10, 20, 7, 3, 2, 8, 24, 35]
Sift-down desde 5: sin hijos (5*2+1=11 > 7). Fin.
```

**i=1:** A[1]=20, hijos A[3]=3, A[4]=2. Mínimo=2. 2 < 20 → swap(1,4).
```
[10, 2, 7, 3, 20, 8, 24, 35]
Sift-down desde 4: hijo A[9] no existe. Fin.
```

**i=0:** A[0]=10, hijos A[1]=2, A[2]=7. Mínimo=2. 2 < 10 → swap(0,1).
```
[2, 10, 7, 3, 20, 8, 24, 35]
Sift-down desde 1: hijos A[3]=3, A[4]=20. Mínimo=3. 3 < 10 → swap(1,3).
[2, 3, 7, 10, 20, 8, 24, 35]
Sift-down desde 3: hijo A[7]=35. 10 < 35 → stop.
```

**Min-Heap final: `[2, 3, 7, 10, 20, 8, 24, 35]`**

> El examen pregunta "¿cómo queda el recorrido preorden/por niveles?". La respuesta ES el array: `2 3 7 10 20 8 24 35` → opción **(b)**.

---

## 4.5 HeapSort

| Ordenar | Primera fase | Después |
|---|---|---|
| **Ascendente** | Construir **Max-Heap** | Extraer máximo → va al final → queda ordenado ↑ |
| **Descendente** | Construir **Min-Heap** | Extraer mínimo → va al final → queda ordenado ↓ |

> Primera fase de HeapSort para ordenar **descendentemente** = **Convertir en Min-Heap**

---

# PARTE 5 — API de GeneralTree (para el código del Ej1)

```java
// Métodos disponibles de GeneralTree<T>:
nodo.getData()                // devuelve el valor del nodo
nodo.isLeaf()                 // true si no tiene hijos
nodo.isEmpty()                // true si el árbol está vacío
nodo.getChildren()            // List<GeneralTree<T>> con los hijos, de izq a der
arbol.isEmpty()               // true si el árbol entero está vacío
```

**El for sobre los hijos siempre es:**
```java
for (GeneralTree<Integer> hijo : nodo.getChildren()) {
    // hijo.getData(), hijo.isLeaf(), etc.
}
```

---

# PARTE 6 — CÓDIGO DEL EJERCICIO 1: PLANTILLA COMPLETA

El examen SIEMPRE pide el mismo esqueleto. Solo cambia:
1. El nombre del método
2. La condición de alternancia
3. El criterio de selección (más largo / mayor suma / primero)

## 6.1 Variante A: Más Largo (Tema 1 — `caminoParidadAlternante`)

```java
public class ParcialArboles {

    public static List<Integer> caminoParidadAlternante(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            helper(arbol, resultado, new LinkedList<>());
        }
        return resultado;
    }

    private static void helper(GeneralTree<Integer> nodo,
                                List<Integer> resultado,
                                List<Integer> camino) {
        camino.add(nodo.getData());
        int paridad = Math.abs(nodo.getData() % 2);  // 0=par, 1=impar

        if (nodo.isLeaf()) {
            if (camino.size() > resultado.size()) {   // ← criterio: MÁS LARGO
                resultado.clear();
                resultado.addAll(camino);
            }
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                if (Math.abs(hijo.getData() % 2) != paridad) {  // ← condición: PAR/IMPAR
                    helper(hijo, resultado, camino);
                }
            }
        }

        camino.remove(camino.size() - 1);  // ← BACKTRACKING, nunca olvidar
    }
}
```

## 6.2 Variante B: Mayor Costo (Tema 2 — `caminoSignoAlternante`)

```java
public class ParcialArboles {

    public static List<Integer> caminoSignoAlternante(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            helper(arbol, resultado, new LinkedList<>(), 0, Integer.MIN_VALUE);
        }
        return resultado;
    }

    // ← el helper RETORNA int (sumaMax)
    private static int helper(GeneralTree<Integer> nodo,
                               List<Integer> resultado,
                               List<Integer> camino,
                               int sumaActual,
                               int sumaMax) {
        int data = nodo.getData();
        camino.add(data);
        sumaActual += data;
        boolean esPositivo = data >= 0;  // ← OJO: >= no >, el 0 es positivo

        if (nodo.isLeaf()) {
            if (sumaActual > sumaMax) {              // ← criterio: MAYOR SUMA
                sumaMax = sumaActual;
                resultado.clear();
                resultado.addAll(camino);
            }
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                boolean hijoPositivo = hijo.getData() >= 0;
                if (esPositivo != hijoPositivo) {    // ← condición: POSITIVO/NEGATIVO
                    sumaMax = helper(hijo, resultado, camino, sumaActual, sumaMax);
                }
            }
        }

        camino.remove(camino.size() - 1);  // backtracking
        return sumaMax;                    // ← devolver sumaMax actualizado
    }
}
```

## 6.3 Variante C: Primero válido (Temas 3 y 4 — `primerCaminoAlternanteCeroNoCero`)

```java
public class ParcialArboles {

    public static List<Integer> primerCaminoAlternanteCeroNoCero(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            helper(arbol, resultado, new LinkedList<>());
        }
        return resultado;
    }

    // ← el helper RETORNA boolean (encontró o no)
    private static boolean helper(GeneralTree<Integer> nodo,
                                   List<Integer> resultado,
                                   List<Integer> camino) {
        camino.add(nodo.getData());
        boolean terminaEnCero = nodo.getData() % 10 == 0;  // ← condición: TERMINA EN 0

        if (nodo.isLeaf()) {
            resultado.addAll(camino);
            camino.remove(camino.size() - 1);
            return true;                                    // ← ENCONTRADO, cortar
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                boolean hijoTerminaEnCero = hijo.getData() % 10 == 0;
                if (terminaEnCero != hijoTerminaEnCero) {  // ← alterna
                    if (helper(hijo, resultado, camino)) { // ← si encontró
                        camino.remove(camino.size() - 1);
                        return true;                       // ← propagar y cortar
                    }
                }
            }
        }

        camino.remove(camino.size() - 1);  // backtracking
        return false;                      // ← no encontró en esta rama
    }
}
```

---

# PARTE 7 — TABLA MAESTRA DE FÓRMULAS

| Estructura | Fórmula | Cuándo usarla |
|---|---|---|
| AB lleno, altura h | **N = 2^(h+1) − 1** | Dado h, hallar N |
| AB lleno, L hojas | **N = 2L − 1** | Dado L, hallar N |
| AB completo, altura h (mínimo) | **N = 2^h** | Cuántos nodos mínimos |
| AB completo, altura h (máximo) | **N = 2^(h+1) − 1** | Cuántos nodos máximos |
| AG lleno, grado k, altura h | **N = (k^(h+1)−1) / (k−1)** | Dado k y h, hallar N |
| AG lleno, nodos en nivel L | **k^L** | ¿Cuántos nodos hay en el nivel L? |
| AG lleno, nodos internos | **I = (L−1) / (k−1)** | Dado L hojas, hallar internos |
| AG completo (mínimo) | **(k^h + k−2) / (k−1)** | Dado k y h, mínimo |
| AG completo (máximo) | **(k^(h+1)−1) / (k−1)** | Dado k y h, máximo |

---

# PARTE 8 — SENTENCIAS V/F QUE SIEMPRE APARECEN

| Sentencia | Respuesta | Por qué |
|---|---|---|
| AB completo: todos los niveles hasta h-1 están llenos | ✓ V | Definición de AB completo |
| AB completo: puede almacenarse en un arreglo | ✓ V | Propiedad exclusiva del completo |
| AB completo: puede tener hojas en más de un nivel | ✓ V | Si el nivel h está incompleto |
| AB completo: cada nodo interno tiene exactamente 2 hijos | ✗ F | El último nodo puede tener solo 1 |
| AB lleno: hojas en más de un nivel | ✗ F | Todas las hojas en el nivel h |
| Todo completo es lleno | ✗ F | Puede tener nivel h incompleto |
| Todo lleno es completo | ✓ V | El nivel h lleno es caso especial del completo |
| AG altura = máximo nivel de sus hojas | ✓ V | Definición de altura |
| AG lleno grado k: k^L nodos en nivel L | ✓ V | Cada nodo tiene exactamente k hijos |
| H = log_k(N+1)−1 para AG lleno de grado k cualquiera | ✗ F | Solo vale para k=2 (AB lleno) |
| HeapSort ascendente → primera fase: Max-Heap | ✓ V | Se extrae el máximo al final |
| HeapSort descendente → primera fase: Min-Heap | ✓ V | Se extrae el mínimo al final |
| Un AB completo de altura H tiene 2^L nodos en nivel L (hasta H-1) | ✓ V | Niveles 0..h-1 completamente llenos |
| InOrden AG: primero la raíz, luego los hijos | ✗ F | Eso es PreOrden |
| InOrden AG: primero los hijos, luego la raíz | ✗ F | Eso es PostOrden |
| InOrden AG: primer hijo → raíz → hijos restantes | ✓ V | Definición de InOrden en AG |

---

# PARTE 9 — ERRORES TÍPICOS QUE HAY QUE EVITAR

### En código (Ej1):

| Error | Cómo evitarlo |
|---|---|
| Usar `data > 0` en vez de `data >= 0` para "positivo" | El 0 es positivo según el enunciado |
| Olvidar `camino.remove(camino.size() - 1)` | Siempre la última línea antes de cerrar el helper |
| No retornar `sumaMax` en la variante de mayor costo | El helper devuelve int y se acumula |
| No retornar `true/false` en la variante del primero | Si no cortás, seguís buscando aunque ya encontraste |
| Actualizar `resultado` cuando el nodo no tiene hijos válidos | Solo actualizar en la hoja (`isLeaf()`) |
| Usar `Integer.MIN_VALUE` como sumaMax inicial → pero poner 0 | Si todos los valores son negativos, 0 como inicial nunca se actualiza |

### En Heap (Ej3):

| Error | Cómo evitarlo |
|---|---|
| Confundir sift-up (Insert) con sift-down (Delete/Build) | Up = subir el elemento nuevo. Down = bajar la raíz reemplazada. |
| En DeleteMin: no mover el último a la raíz | Es obligatorio: sacar raíz → poner último → sift-down |
| En BuildHeap: empezar desde i=N/2 en vez de N/2-1 | Para 0-indexed: i = N/2 - 1 |
| No mostrar cada paso | El enunciado siempre dice "muestre cada uno de los pasos" |

### En expresiones (Ej2a):

| Error | Cómo evitarlo |
|---|---|
| Al construir árbol desde postfija: primer pop = hijo IZQ | INCORRECTO. Primer pop = hijo **DER**, segundo = hijo **IZQ** |
| No parentizar la expresión infija | La infija del árbol siempre lleva paréntesis en cada suboperación |

---

# RESUMEN PARA TENER FRESCO EN EL EXAMEN

```
RECORRIDOS:
  PreOrden  → Prefija   (raíz primero)
  InOrden   → Infija    (raíz en el medio)
  PostOrden → Postfija  (raíz al final)
  Niveles   → BFS (cola) = array del heap

REGLAS DE ORO:
  Último en PostOrden = RAÍZ
  Primero en PreOrden = RAÍZ
  InOrden AG = primer hijo → raíz → resto de hijos

HEAP:
  Insert: poner al final → sift-UP (comparar con padre)
  Delete: poner último en raíz → sift-DOWN (comparar con hijos)
  BuildHeap: empezar en N/2-1, sift-DOWN hasta raíz

HEAPSORT:
  ASCENDENTE  → Max-Heap (la raíz máx va al final)
  DESCENDENTE → Min-Heap (la raíz mín va al final)

CÓDIGO EJ1:
  Siempre: null check → helper con backtracking
  Variante largo: void helper, comparar camino.size()
  Variante suma: int helper (retorna sumaMax), Integer.MIN_VALUE inicial
  Variante primero: boolean helper, return true corta todo
  OJO: 0 siempre es positivo y par. Usar >= 0, Math.abs(% 2)
```
