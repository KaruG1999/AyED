# PATRONES DEL EXAMEN — AyED Árboles y Heap (1ra Fecha)

> Análisis de los 4 temas de la 1ra fecha 2025 (10/05/2025)
> Tiempo: 1h 30min · Total: 10 puntos

---

## ESTRUCTURA FIJA DEL EXAMEN (siempre igual)

| Ejercicio | Puntos | Tipo |
|---|---|---|
| Ejercicio 1 | 5 pts | Código Java sobre AG |
| Ejercicio 2 | 1.5 pts | 3 preguntas teóricas/cortas |
| Ejercicio 3 | 3.5 pts | Heap paso a paso |

**Distribución de tiempo sugerida:**
- Ej1: 45 min (es el más complejo, vale la mitad)
- Ej2: 15 min (rápido si tenés las fórmulas)
- Ej3: 30 min (mecánico pero hay que mostrar cada paso)

---

## EJERCICIO 1 — PATRÓN DE CÓDIGO (siempre igual)

### Qué piden siempre
- Clase `ParcialArboles` con un **método estático**
- Recibe un `GeneralTree<Integer>` (árbol general de enteros)
- Devuelve `List<Integer>` con los nodos de UN camino raíz→hoja que cumple alguna condición de **alternancia**
- Si no existe ningún camino válido: devolver **lista vacía**

### Variantes vistas (2025)

| Tema | Método | Busca | Condición de alternancia |
|---|---|---|---|
| 1 | `caminoParidadAlternante` | MÁS LARGO (más nodos) | par ↔ impar (0=par) |
| 2 | `caminoSignoAlternante` | MAYOR COSTO (suma) | positivo ↔ negativo (0=positivo) |
| 3 y 4 | `primerCaminoAlternanteCeroNoCero` | PRIMERO válido (DFS izq→der) | termina en 0 ↔ no termina en 0 |

### Restricciones que SIEMPRE están en el enunciado
1. No agregar variables de instancia ni de clase
2. Respetar la clase y la firma del método
3. Puede definir métodos y variables locales auxiliares
4. Todo método no definido en la sinopsis debe ser implementado
5. (Solo en "primer camino") El método debe terminar ni bien encuentre el primer camino válido

### PLANTILLA BASE — siempre arrancás así

```java
public class ParcialArboles {

    public static List<Integer> NOMBRE_METODO(GeneralTree<Integer> arbol) {
        List<Integer> resultado = new LinkedList<>();
        if (arbol != null && !arbol.isEmpty()) {
            helper(arbol, resultado, new LinkedList<>() /*, parámetros extra */);
        }
        return resultado;
    }

    private static /* void o int o boolean */ helper(
            GeneralTree<Integer> nodo,
            List<Integer> resultado,
            List<Integer> camino
            /*, parámetros extra según el tipo */) {

        camino.add(nodo.getData());

        if (nodo.isLeaf()) {
            // EVALUAR si este camino es válido/mejor
            // Si sí: resultado.clear(); resultado.addAll(camino);
        } else {
            for (GeneralTree<Integer> hijo : nodo.getChildren()) {
                if (/* condición de alternancia */) {
                    helper(hijo, resultado, camino /*, params */);
                    // Si es "primero": if (helper(...)) { backtrack; return true; }
                }
            }
        }

        camino.remove(camino.size() - 1);  // BACKTRACKING — nunca olvidar
        // Si es sumaMax: return sumaMax;
        // Si es primero: return false;
    }
}
```

### Condiciones de alternancia según el tipo

```java
// PAR / IMPAR (Tema 1):
int restoNodo = Math.abs(nodo.getData() % 2);
int restoHijo = Math.abs(hijo.getData() % 2);
if (restoHijo != restoNodo) { ... }

// POSITIVO / NEGATIVO (Tema 2) — 0 es positivo:
boolean esPositivo = nodo.getData() >= 0;  // OJO: >= no >
boolean hijoPositivo = hijo.getData() >= 0;
if (esPositivo != hijoPositivo) { ... }

// TERMINA EN CERO / NO TERMINA EN CERO (Temas 3 y 4):
boolean terminaEnCero = nodo.getData() % 10 == 0;
boolean hijoTerminaEnCero = hijo.getData() % 10 == 0;
if (terminaEnCero != hijoTerminaEnCero) { ... }
```

### Diferencia crítica: MÁS LARGO vs MAYOR COSTO vs PRIMERO

```java
// MÁS LARGO: comparar tamaño de camino
if (camino.size() > resultado.size()) {
    resultado.clear(); resultado.addAll(camino);
}

// MAYOR COSTO: comparar suma (pasar sumaActual y sumaMax como parámetros)
if (sumaActual > sumaMax) {
    sumaMax = sumaActual;
    resultado.clear(); resultado.addAll(camino);
}
// → el helper retorna int (sumaMax), se inicializa con Integer.MIN_VALUE

// PRIMERO: el helper retorna boolean y corta la búsqueda
resultado.addAll(camino);
return true;  // en la hoja
// En el nodo: if (helper(hijo, ...)) { backtrack; return true; }
```

---

## EJERCICIO 2 — PATRÓN DE PREGUNTAS TEÓRICAS (siempre igual)

### Parte a) — Conversión de expresiones (SIEMPRE)

**Regla única: construir el árbol de expresión y recorrerlo.**

| Para obtener | Recorrido del árbol |
|---|---|
| Postfija | PostOrden (izq → der → raíz) |
| Prefija | PreOrden (raíz → izq → der) |
| Infija | InOrden (izq → raíz → der) |

**Postfija → árbol:** usar pila, al ver operador pop 2 nodos (primero popeado = hijo derecho)

**Prefija → árbol:** recursivo, el operador toma los 2 siguientes subárboles como hijos

**Error común:** confundir el orden de los hijos al construir desde postfija (el 1er pop = hijo DER, el 2do pop = hijo IZQ)

### Parte b) — BuildHeap (SIEMPRE)

**Siempre preguntan: ¿cómo queda el recorrido (preorden/niveles) del heap?**

La respuesta correcta = **el array resultante** = que es el recorrido por niveles del árbol.

**Algoritmo BuildHeap:**
```
1. Tomar el array tal cual (índices 0 a N-1)
2. i = N/2 - 1  (último nodo interno)
3. Mientras i >= 0: sift-down(i), luego i--
```

**Sift-down (Min-Heap):** si algún hijo es menor que el nodo, swap con el hijo más pequeño, repetir hacia abajo.
**Sift-down (Max-Heap):** si algún hijo es mayor que el nodo, swap con el hijo más grande, repetir hacia abajo.

**Relaciones de índices (0-indexed):**
- left(i) = 2i+1
- right(i) = 2i+2
- parent(i) = (i-1)/2

### Parte c/d) — Sentencias V/F (SIEMPRE)

**Sentencias que SÍ o SÍ aparecen:**

| Sentencia | Verdad |
|---|---|
| AB completo: todos los niveles hasta H-1 están completamente llenos | ✓ VERDADERO |
| AB completo: puede almacenarse en un arreglo | ✓ VERDADERO |
| AB completo: puede tener hojas en más de un nivel | ✓ VERDADERO |
| AB completo: cada nodo no-hoja tiene exactamente 2 hijos | ✗ FALSO (el último nodo puede tener 1) |
| AB lleno: hojas en más de un nivel | ✗ FALSO (todas las hojas en nivel H) |
| AG lleno grado K: k^L nodos en nivel L | ✓ VERDADERO |
| AG altura = máximo nivel de sus hojas | ✓ VERDADERO |
| H = log_k(N+1)-1 para AG lleno de grado K cualquiera | ✗ FALSO (solo vale para k=2) |
| HeapSort ASCENDENTE → primera fase: Max-Heap | ✓ VERDADERO |
| HeapSort DESCENDENTE → primera fase: Min-Heap | ✓ VERDADERO |

---

## EJERCICIO 3 — PATRÓN DE HEAP (siempre igual)

### Parte a) — SIEMPRE: insertar uno a uno + mostrar pasos

**Proceso Insert (Min-Heap):**
1. Agregar al final del array
2. Sift-up: mientras el nodo sea MENOR que su padre → swap

**Proceso Insert (Max-Heap):**
1. Agregar al final del array
2. Sift-up: mientras el nodo sea MAYOR que su padre → swap

**Cómo mostrar los pasos en el examen:**
- Dibujar el árbol en cada inserción
- Mostrar cada swap con flechas
- Dibujar el estado final después del sift-up

### Parte b) — SIEMPRE: DeleteMin o DeleteMax

**Proceso Delete:**
1. Guardar la raíz (es el resultado que se elimina)
2. Mover el **último elemento** del array a la raíz
3. Reducir tamaño en 1
4. Sift-down desde la raíz:
   - Min-Heap: swap con el hijo **mínimo** si es menor que el nodo
   - Max-Heap: swap con el hijo **máximo** si es mayor que el nodo
5. Repetir hasta que el nodo esté en su lugar o sea hoja

---

## FÓRMULAS QUE SIEMPRE PREGUNTAN

| Estructura | Fórmula |
|---|---|
| AB lleno con L hojas | N = 2L − 1 |
| AB completo altura h (mínimo nodos) | N = 2^h |
| AB completo altura h (máximo nodos) | N = 2^(h+1) − 1 |
| AG lleno, grado k, altura h | N = (k^(h+1) − 1) / (k−1) |
| AG completo (mínimo) | N = (k^h + k − 2) / (k−1) |
| AG completo (máximo) | N = (k^(h+1) − 1) / (k−1) |
| AG lleno: nodos internos con L hojas | I = (L−1) / (k−1) |
| AG lleno: nodos en nivel L | k^L |

---

## ERRORES TÍPICOS QUE CUESTAN PUNTOS

### En el código (Ej1):
1. **Usar `> 0` en vez de `>= 0` para verificar positivo** → 0 no se clasifica como positivo
2. **Olvidar el backtracking** (`camino.remove(camino.size()-1)`) → el camino acumula nodos incorrectos
3. **No retornar sumaMax en el helper** cuando se busca mayor costo → la recursión pierde el estado
4. **No cortar la búsqueda cuando es "primero"** → el método sigue buscando después de encontrar
5. **Actualizar resultado al llegar a un nodo sin hijos válidos** (en vez de solo en hojas) → incluye caminos incompletos

### En el heap (Ej3):
1. **Confundir sift-up (Insert) con sift-down (Delete/BuildHeap)**
2. **En Delete: olvidar mover el ÚLTIMO elemento a la raíz** (en vez de sift-down del segundo)
3. **En BuildHeap: empezar desde i=N/2 en vez de i=N/2-1** (índice 0) o desde i=N/2 (índice 1)
4. **No mostrar cada paso** → en Ej3 piden "muestre cada uno de los pasos"

### En expresiones (Ej2a):
1. **Confundir el orden de los hijos** al construir árbol desde postfija (primer pop = hijo DER)
2. **No parentizar al escribir la infija** → da expresión ambigua
3. **Confundir PreOrden con PostOrden** al leer el árbol

---

## RESUMEN DE RECORRIDOS (para el examen)

```
AB y AG:
  PreOrden  : raíz → izq → der      (primero en PreOrden = raíz)
  InOrden AB: izq → raíz → der      (último en InOrden = nodo más derecho)
  InOrden AG: primer hijo → raíz → hijos restantes
  PostOrden : izq → der → raíz      (último en PostOrden = raíz SIEMPRE)
  Por niveles: BFS (cola)

Expresiones:
  PreOrden  = prefija (notación polaca)
  InOrden   = infija (con paréntesis)
  PostOrden = postfija (notación polaca inversa)
```
