# TP4 — Guía Completa: Árboles Binarios, de Expresión y Generales

> Cubre todo lo necesario para el examen: TP1 al TP4 — la parte de Árboles.
> Cada ejercicio tiene la respuesta, el razonamiento paso a paso y la teoría que lo justifica.

---

## Conceptos clave para tener frescos

### Árbol Binario (AB)
- Cada nodo tiene **a lo sumo 2 hijos**: izquierdo y derecho.
- **Recorridos en profundidad**:
  - **PreOrden**: raíz → izquierdo → derecho
  - **InOrden**: izquierdo → raíz → derecho
  - **PostOrden**: izquierdo → derecho → raíz
- El **último nodo en PostOrden** siempre es la **raíz**.
- El **primer nodo en PreOrden** siempre es la **raíz**.
- En InOrden, la raíz divide el árbol: todo lo que está a su izquierda es el subárbol izquierdo, y todo lo de la derecha es el subárbol derecho.

### AB Lleno
- Todo nodo interno tiene **exactamente 2 hijos**.
- Todas las hojas están en el **mismo nivel** h.
- Fórmula: **N = 2^(h+1) − 1**
- Fórmula con hojas L: **N = 2L − 1** (porque L = I + 1, donde I = nodos internos)

### AB Completo
- Lleno hasta el nivel h−1, y el nivel h se rellena **de izquierda a derecha**.
- Fórmula: **2^h ≤ N ≤ 2^(h+1) − 1**
- Mínimo de nodos para altura h: **2^h** (nivel h con 1 solo nodo)
- Máximo: **2^(h+1) − 1** (árbol lleno)

### Árbol General (AG)
- Cada nodo puede tener **cualquier cantidad de hijos**.
- **Grado**: número de hijos de un nodo.
- **Grado del árbol**: máximo grado entre todos los nodos.
- **Profundidad/Nivel**: longitud del camino único desde la raíz hasta el nodo (raíz = nivel 0).
- **Altura**: longitud del camino más largo desde el nodo hasta una hoja (hojas = altura 0).
- **Recorrido InOrden en AG**: primer hijo → raíz → hijos restantes (en ese orden).

### AG Lleno (grado k)
- Todo nodo interno tiene **exactamente k hijos**.
- Todas las hojas en el mismo nivel h.
- Fórmula: **N = (k^(h+1) − 1) / (k − 1)**

### AG Completo (grado k)
- Lleno hasta nivel h−1 + al menos 1 nodo en nivel h.
- Mínimo: **(k^h + k − 2) / (k − 1)**
- Máximo: **(k^(h+1) − 1) / (k − 1)** (= árbol lleno de altura h)

### Árboles de Expresión
- Los **operadores** están en los nodos internos.
- Los **operandos** están en las hojas.
- **PreOrden** → expresión **prefija** (notación polaca)
- **InOrden** → expresión **infija** (con paréntesis)
- **PostOrden** → expresión **postfija** (notación polaca inversa)
- Para evaluar una expresión postfija: se usa una **pila** (stack).

---

## Ejercicio 1

**Enunciado**: PostOrden: `A G F E B J I H C D` — InOrden: `A B G E F D J H I C`. ¿Cuántos descendientes tiene el nodo "C"?

**Opciones**: (a) 2  (b) 1  (c) 3  (d) ninguna de las anteriores

### Resolución

**Paso 1 — encontrar la raíz**: en PostOrden el último siempre es la raíz → raíz = **D**.

**Paso 2 — dividir por InOrden**: en `A B G E F [D] J H I C`, D está en posición 5.
- Subárbol izquierdo: `A B G E F` (5 nodos)
- Subárbol derecho: `J H I C` (4 nodos)

**Paso 3 — reconstruir el subárbol derecho** (donde está C):
- PostOrden del subárbol derecho (últimos 4 antes de D): `J I H C` → raíz = **C**
- InOrden del subárbol derecho: `J H I [C]` → C no tiene subárbol derecho, solo izquierdo `J H I`
- PostOrden del subárbol izquierdo de C: `J I H` → raíz = **H**
- InOrden: `J [H] I` → H tiene izquierdo J, derecho I

El subárbol con raíz C queda:
```
    C
   /
  H
 / \
J   I
```

**C tiene 3 descendientes**: H, J, I.

**Respuesta: (c) 3**

---

## Ejercicio 2

**Enunciado**: Definir árbol binario completo y lleno. ¿Todo completo es lleno? ¿Todo lleno es completo?

### Resolución

**Árbol Binario Lleno**: cada nodo interno tiene exactamente 2 hijos, y todas las hojas están en el mismo nivel h. Tiene exactamente N = 2^(h+1) − 1 nodos.
- Ejemplo (h=2):
  ```
      A
     / \
    B   C
   /\ /\
  D E F G
  ```

**Árbol Binario Completo**: todos los niveles del 0 al h−1 están completamente llenos, y el nivel h se completa de izquierda a derecha. Tiene entre 2^h y 2^(h+1)−1 nodos.
- Ejemplo (h=2, con 5 nodos):
  ```
      A
     / \
    B   C
   / \
  D   E
  ```

**¿Todo completo es lleno?** **NO**. Un árbol completo puede tener el último nivel incompleto, lo que significa que tiene hojas a distintos niveles. Ejemplo: el árbol de 5 nodos arriba. B tiene 2 hijos pero C no tiene ninguno, así que no todo nodo interno tiene exactamente 2 hijos.

**¿Todo lleno es completo?** **SÍ**. En un árbol lleno, el último nivel h está **completamente** lleno, que es un caso especial de "rellenado de izquierda a derecha". Por lo tanto, todo árbol lleno satisface la definición de completo.

---

## Ejercicio 3

**Enunciado**: Si en un AB con N>1 nodos el último nodo en PostOrden es el mismo que el último en InOrden, ¿qué se puede concluir?

**Opciones**:
- (a) El subárbol izquierdo de T es vacío
- (b) El subárbol derecho de T es vacío
- (c) Ningún nodo en el árbol tiene dos hijos
- (d) Hay a lo sumo 3 nodos en el árbol

### Resolución

**Clave 1**: el último nodo en PostOrden (izq → der → raíz) es siempre la **raíz**.

**Clave 2**: el último nodo en InOrden (izq → raíz → der) es el nodo más a la derecha, es decir, el nodo al que se llega yendo siempre por el hijo derecho. Si no hay subárbol derecho, ese nodo es la propia raíz.

Si ambos últimos son el mismo nodo, ese nodo es la raíz. Para que la raíz sea el último en InOrden, no puede haber nada a su derecha, o sea: **el subárbol derecho está vacío**.

**Respuesta: (b) El subárbol derecho de T es vacío**

---

## Ejercicio 4

**Enunciado**: ¿Qué imprime la siguiente función sobre el árbol de la figura?

```java
public void traverse(ArbolBinario<T> a) {
    if (!a.esVacio()) {
        System.out.print(a.getDato());        // imprime ANTES de bajar
        if (a.tieneHijoIzquierdo())
            traverse(a.getHijoIzquierdo());
        if (a.tieneHijoDerecho())
            traverse(a.getHijoDerecho());
        System.out.print(a.getDato());        // imprime DESPUÉS de volver
    }
}
```

Árbol de la figura:
```
    C
   / \
  A   E
   \  / \
    B D   F
```

### Resolución

Cada nodo se imprime **dos veces**: una al entrar y otra al salir (después de procesar todos sus hijos). Esto combina PreOrden y PostOrden.

Traza paso a paso:
```
traverse(C):
  imprime C
  traverse(A):
    imprime A
    (A no tiene hijo izquierdo)
    traverse(B):
      imprime B
      (B es hoja)
      imprime B
    imprime A
  traverse(E):
    imprime E
    traverse(D):
      imprime D
      (D es hoja)
      imprime D
    traverse(F):
      imprime F
      (F es hoja)
      imprime F
    imprime E
  imprime C
```

**Salida: `C A B B A E D D F F E C`**

---

## Ejercicio 5

**Enunciado**: Evaluar la expresión postfija: `6 5 * 7 3 − 4 8 + * +`

**Opciones**: (a) 78  (b) 66  (c) 34  (d) 44

### Resolución

Para evaluar postfija se usa una **pila**: cuando se ve un operador, se sacan los dos últimos operandos (el primero sacado es el operando **derecho**).

| Paso | Token | Acción | Pila |
|------|-------|--------|------|
| 1 | 6 | push | [6] |
| 2 | 5 | push | [6, 5] |
| 3 | * | pop 5 (der), pop 6 (izq) → 6×5=30 | [30] |
| 4 | 7 | push | [30, 7] |
| 5 | 3 | push | [30, 7, 3] |
| 6 | − | pop 3 (der), pop 7 (izq) → 7−3=4 | [30, 4] |
| 7 | 4 | push | [30, 4, 4] |
| 8 | 8 | push | [30, 4, 4, 8] |
| 9 | + | pop 8 (der), pop 4 (izq) → 4+8=12 | [30, 4, 12] |
| 10 | * | pop 12 (der), pop 4 (izq) → 4×12=48 | [30, 48] |
| 11 | + | pop 48 (der), pop 30 (izq) → 30+48=78 | [78] |

**Respuesta: (a) 78**

---

## Ejercicio 6

**Enunciado**: ¿Cuál es la expresión algebraica almacenada en el árbol?

El árbol de la figura:
```
          +
         / \
        /   *
       / \  / \
      -   c d   e
     / \
    a   b
```

**Opciones**:
- (a) (a − b / c) + d * e
- (b) (((a − b) / (c + d)) + d * e
- (c) ((a − b / c) + (d * e)
- **(d) (((a − b) / c) + (d * e))**

### Resolución

Para leer un árbol de expresión se usa InOrden (izq → raíz → der) y se pone paréntesis en cada subárbol:

- Nodo `-` : izq=a, der=b → **(a − b)**
- Nodo `/` : izq=(a−b), der=c → **((a−b) / c)**
- Nodo `*` : izq=d, der=e → **(d * e)**
- Raíz `+` : izq=((a−b)/c), der=(d*e) → **(((a−b) / c) + (d * e))**

**Respuesta: (d)**

---

## Ejercicio 7

**Enunciado**: ¿Cuántos nodos mínimos tiene un árbol binario **completo** de altura 4?

**Opciones**: (a) 10  (b) 15  (c) 12  (d) 31  (e) 16

### Resolución

Un AB completo de altura h tiene **mínimo** cuando el último nivel (nivel h) tiene **solo 1 nodo**.

- Niveles 0 a 3 completamente llenos: 1 + 2 + 4 + 8 = **15 nodos**
- Nivel 4 con el mínimo: **1 nodo** adicional
- Total mínimo: 15 + 1 = **16 nodos**

Fórmula directa: mínimo = 2^h = 2^4 = **16**

**Respuesta: (e) 16**

---

## Ejercicio 8

**Enunciado**: Construir el árbol de expresión de la postfija: `6 5 * 7 3 − 4 8 + * +`

### Resolución

Se usa la misma técnica que para evaluar (pila), pero en lugar de calcular números, se crean nodos del árbol:

- `6 5 *` → nodo `*` con hijos 6 y 5
- `7 3 −` → nodo `−` con hijos 7 y 3
- `4 8 +` → nodo `+` con hijos 4 y 8
- `[7-3] [4+8] *` → nodo `*` con hijos (7−3) y (4+8)
- `[6*5] [[7-3]*[4+8]] +` → raíz `+`

Árbol resultante:
```
             +
            / \
           *   *
          / \ / \
         6  5 −   +
             /\ /\
            7 3 4  8
```

Expresión infija: **(6 × 5) + ((7 − 3) × (4 + 8))**  
Verificación: 30 + (4 × 12) = 30 + 48 = **78** ✓ (coincide con Ej5)

---

## Ejercicio 9

**Enunciado**: Construir el árbol de expresión de la infija: `(A + (B * C)) * (D − E)`

### Resolución

Se construye de arriba hacia abajo siguiendo la precedencia (el operador de menor precedencia fuera de paréntesis es la raíz):

- El `*` del medio (fuera de todo paréntesis) es la **raíz**
- Subárbol izquierdo: `(A + (B * C))`
  - `+` es la raíz del subárbol izquierdo
  - `A` es el hijo izquierdo
  - `(B * C)` es el hijo derecho → nodo `*` con B y C
- Subárbol derecho: `(D − E)`
  - `−` es la raíz del subárbol derecho
  - D e izquierdo, E derecho

Árbol:
```
         *
        / \
       +   −
      / \ / \
     A  * D  E
       / \
      B   C
```

---

## Ejercicio 10

**Enunciado**: Construir el árbol de expresión de la **prefija**: `+ + a e / * - b c d f`. ¿Cuál es la profundidad del nodo d?

**Opciones**: (a) 1  (b) 2  (c) 3  (d) 4

### Resolución

Para parsear prefija: cada operador toma los **siguientes dos subárboles** como hijos.

Parseando `+ + a e / * - b c d f`:
```
+ →
  + →
    a  (hoja)
    e  (hoja)
  / →
    * →
      - →
        b  (hoja)
        c  (hoja)
      d  (hoja)
    f  (hoja)
```

Árbol:
```
           +           ← nivel 0
          / \
         +   /         ← nivel 1
        / \ / \
       a  e *   f      ← nivel 2
           / \
          -   d        ← nivel 3 (d está aquí)
         / \
        b   c          ← nivel 4
```

El nodo `d` está en el **nivel 3** (raíz=nivel 0, `/`=nivel 1, `*`=nivel 2, `d`=nivel 3).

**Respuesta: (c) 3**

---

## Ejercicio 11

**Enunciado**: Obtener la expresión **prefija** de la postfija: `A B C * D - E F / G / - *`

### Resolución

**Paso 1 — construir el árbol desde la postfija** (igual que Ej8):

| Paso | Token | Pila (árbol) |
|------|-------|------|
| | A | [A] |
| | B | [A, B] |
| | C | [A, B, C] |
| | * | pop C, pop B → nodo(*,B,C) → [A, (B*C)] |
| | D | [A, (B*C), D] |
| | - | pop D, pop (B*C) → nodo(-,B*C,D) → [A, ((B*C)-D)] |
| | E | [A, ((B*C)-D), E] |
| | F | [A, ((B*C)-D), E, F] |
| | / | pop F, pop E → nodo(/,E,F) → [A, ((B*C)-D), (E/F)] |
| | G | [A, ((B*C)-D), (E/F), G] |
| | / | pop G, pop (E/F) → nodo(/,E/F,G) → [A, ((B*C)-D), ((E/F)/G)] |
| | - | pop ((E/F)/G), pop ((B*C)-D) → nodo(-,(B*C-D),(E/F)/G) → [A, (((B*C)-D)-((E/F)/G))] |
| | * | pop resultado anterior, pop A → raíz(*) |

Árbol:
```
                  *
                 / \
                A   -
                   / \
                  -   /
                 / \ / \
                *  D /  G
               /\  /\
              B  C E  F
```

**Paso 2 — leer el árbol en PreOrden** (raíz → izquierdo → derecho):

`* → A → - → - → * → B → C → D → / → / → E → F → G`

**Expresión prefija: `* A - - * B C D / / E F G`**

---

## Ejercicio 12

**Enunciado**: ¿Cuál árbol binario tiene InOrden BCAD y PreOrden ABCD?

### Resolución

**PreOrden**: el primer nodo es la raíz → raíz = **A**

**InOrden** `B C [A] D`: todo lo que está a la izquierda de A en InOrden es su subárbol izquierdo:
- Subárbol izquierdo: `B C` (2 nodos)
- Subárbol derecho: `D` (1 nodo)

**Siguiente en PreOrden** (luego de A): `B C D`. Los primeros 2 son para el subárbol izquierdo:
- Subárbol izquierdo (PreOrden `B C`): raíz = **B**
  - InOrden del subárbol izquierdo: `B C` → B es el primero → B no tiene hijo izquierdo → C es el hijo derecho
- Subárbol derecho: **D** (hoja)

Árbol:
```
  A
 / \
B   D
 \
  C
```

Verificación:
- PreOrden: A → B → C → D = **ABCD** ✓
- InOrden: B → C → A → D = **BCAD** ✓

**Respuesta: Opción D** (A en raíz, B a la izquierda con C como hijo derecho de B, D a la derecha de A)

---

## Ejercicio 13

**Enunciado**: Reconstruir el árbol T con PreOrden `2 5 3 9 7 1 6 4 8` e InOrden `9 3 7 5 1 2 6 8 4`.

### Resolución

Se aplica el mismo proceso del Ejercicio 1: el primero en PreOrden es siempre la raíz, y en InOrden divide izquierdo/derecho.

**Raíz = 2** (primero en PreOrden)
InOrden: `9 3 7 5 1 [2] 6 8 4`
- Subárbol izquierdo: `9 3 7 5 1` (5 nodos)
- Subárbol derecho: `6 8 4` (3 nodos)

**Subárbol izquierdo** (PreOrden: `5 3 9 7 1`, InOrden: `9 3 7 5 1`):
- Raíz = **5** | InOrden: `9 3 7 [5] 1` → izq={9,3,7}, der={1}
  - Raíz del izq = **3** | InOrden: `9 [3] 7` → izq={9}, der={7}
    - **9** (hoja), **7** (hoja)
  - Raíz del der = **1** (hoja)

**Subárbol derecho** (PreOrden: `6 4 8`, InOrden: `6 8 4`):
- Raíz = **6** | InOrden: `[6] 8 4` → izq=vacío, der={8,4}
  - Raíz del der = **4** | InOrden: `8 [4]` → izq={8}, der=vacío
    - **8** (hoja)

Árbol reconstruido:
```
        2
       / \
      5   6
     / \   \
    3   1   4
   / \     /
  9   7   8
```

---

## Ejercicio 14

**Enunciado**: En un árbol binario lleno con L hojas, el total de nodos N es:

**Opciones**: a) N=2L  b) N=L+1  c) N=L−1  d) N=2L−1

### Resolución

En un árbol binario lleno hay dos tipos de nodos: hojas (L) y nodos internos (I).

**Propiedad clave**: en todo árbol donde cada nodo interno tiene **exactamente 2 hijos**, la cantidad de hojas es siempre uno más que la de nodos internos:
- L = I + 1 → I = L − 1

Esto se demuestra contando las aristas: cada nodo excepto la raíz tiene exactamente 1 padre → aristas = N − 1. Cada nodo interno aporta 2 aristas (sus hijos) → 2I aristas = N − 1 → 2(L−1) = (I + L) − 1 → esto da L = I + 1.

Total: N = I + L = (L − 1) + L = **2L − 1**

**Respuesta: (d) N = 2L − 1**

---

## Ejercicio 15

**Enunciado**: Árbol general de la figura.

Árbol:
```
             A                ← nivel 0
           / | \
          B  C   D            ← nivel 1
         /\      /|\
        E  F    G  H  I       ← nivel 2
          /|\   |  |\
         J K L  M  N  O       ← nivel 3
                |\
               P  Q           ← nivel 4
```

### (a) Completar los blancos

**i.** **A** es la raíz del árbol.

**ii.** **A** es padre de B, C y D.

**iii.** **E** y **F** son hermanos (ambos son hijos de B).

**iv.** Las hojas (nodos sin hijos) son: **E, C, J, K, L, I, N, O, P, Q**.

**v.** El camino desde A hasta J: **A → B → F → J** — lo conforman los nodos A, B, F, J y es de largo **3**.

**vi.** **D** es ancestro de P (D→G→M→P), y por lo tanto **P** es descendiente de D.

**vii.** L no es descendiente de C porque no existe ningún **camino** desde C hasta L (C es hoja).

**viii.** La profundidad/nivel de C es **1**, de F es **2** y de **P** (o Q) es **4**.

**ix.** La altura de C es **0** (es hoja), la de F es **1** (F→J) y la de D es **3** (D→G→M→P).

**x.** La altura del árbol es 4 (el camino más largo va de la **raíz A** hasta las hojas **P** o **Q**).

### (b) Recorridos

**i. PreOrden** (raíz, luego hijos de izquierda a derecha, recursivo):
```
A  B  E  F  J  K  L  C  D  G  M  P  Q  H  N  O  I
```

**ii. InOrden en AG** (primer hijo, raíz, hijos restantes):

Para cada nodo: primero el subárbol del primer hijo, luego la raíz, luego los demás hijos en orden.

- A tiene hijos B, C, D → InOrden(B), A, InOrden(C), InOrden(D)
- B tiene hijos E, F → InOrden(E), B, InOrden(F) = E, B, J, F, K, L
- InOrden(C) = C (es hoja)
- D tiene hijos G, H, I → InOrden(G), D, InOrden(H), InOrden(I)
  - G tiene hijo M → InOrden(M), G = (P, M, Q), G → P M Q G
  - InOrden(H) = N H O
  - InOrden(I) = I

```
E  B  J  F  K  L  A  C  P  M  Q  G  D  N  H  O  I
```

**iii. PostOrden** (primero todos los hijos recursivos, luego la raíz):
```
E  J  K  L  F  B  C  P  Q  M  G  N  O  H  I  D  A
```

**iv. Por niveles (BFS)**:
```
A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q
```

---

## Ejercicio 16

**Enunciado**: ¿Cuál es el número mínimo y máximo de nodos de un árbol general completo de altura h y grado k?

### Resolución

Un **AG completo** de grado k y altura h tiene:
- Niveles 0 a h−1 completamente llenos (cada nodo tiene k hijos)
- El nivel h tiene al menos 1 nodo (caso mínimo) y como máximo k^h nodos (caso máximo = árbol lleno de altura h)

**Mínimo** = árbol lleno de altura h−1 + exactamente 1 nodo en nivel h:
```
Min = (k^h - 1)/(k-1) + 1 = (k^h - 1 + k - 1)/(k-1) = (k^h + k - 2)/(k-1)
```

**Máximo** = árbol lleno de altura h:
```
Max = (k^(h+1) - 1)/(k - 1)
```

**Respuesta:**
- **Mínimo: (k^h + k − 2) / (k − 1)**
- **Máximo: (k^(h+1) − 1) / (k − 1)**

---

## Ejercicio 17

**Enunciado**: El recorrido InOrden en un árbol general visita:

**Opciones**:
- a) Primero la mitad de los subárboles hijos, luego la raíz y luego los restantes
- b) Primero la raíz y luego los subárboles hijos
- c) Primero los subárboles hijos y luego la raíz
- d) Primero el subárbol hijo más izquierdo, luego la raíz y luego los restantes subárboles hijos

### Resolución

- (b) es **PreOrden**
- (c) es **PostOrden**
- (a) "la mitad" no corresponde a ningún recorrido estándar
- (d) describe exactamente el **InOrden en AG**: se visita primero el subárbol del **primer hijo** (el más izquierdo), luego la **raíz**, y luego los subárboles de los **hijos restantes** en orden.

**Respuesta: (d)**

---

## Ejercicio 18

**Enunciado**: En un árbol general, la profundidad de un nodo n1 es…

**Opciones**:
- a) La longitud del único camino que existe entre la raíz y el nodo n1
- b) La longitud del camino más largo que existe entre el nodo n1 y una hoja
- c) La cantidad de nodos hijos del nodo n1
- d) Ninguna de las otras opciones

### Resolución

- (b) describe la **altura** de un nodo
- (c) describe el **grado** de un nodo
- (a) describe la **profundidad** (también llamada **nivel**): la raíz tiene profundidad 0, y cada vez que bajamos un nivel, la profundidad aumenta en 1. Como en un árbol hay un único camino entre cualesquiera dos nodos, esta definición es unívoca.

**Respuesta: (a)**

---

## Ejercicio 19

**Enunciado**: Un árbol general **lleno** de grado 4 tiene 21 nodos. ¿Cuál es la altura?

### Resolución

Fórmula para AG lleno de grado k y altura h:
```
N = (k^(h+1) - 1) / (k - 1)
```

Sustituir k=4, N=21:
```
21 = (4^(h+1) - 1) / (4 - 1)
21 = (4^(h+1) - 1) / 3
21 × 3 = 4^(h+1) - 1
63 = 4^(h+1) - 1
64 = 4^(h+1)
4^3 = 64  →  h + 1 = 3  →  h = 2
```

**La altura del árbol es 2.**

Verificación visual:
- Nivel 0: 1 nodo (raíz)
- Nivel 1: 4 nodos (raíz tiene 4 hijos)
- Nivel 2: 16 nodos (cada uno de los 4 nodos tiene 4 hijos)
- Total: 1 + 4 + 16 = **21** ✓

---

## Ejercicio 20

**Enunciado**: ¿Cuántos nodos mínimos tiene un AG completo de grado 3 y altura 4?

**Opciones**: a) 40  b) 41  c) 121  d) 122

### Resolución

Fórmula mínimo: **(k^h + k − 2) / (k − 1)**

Con k=3, h=4:
```
(3^4 + 3 - 2) / (3 - 1)
= (81 + 3 - 2) / 2
= 82 / 2
= 41
```

Interpretación: el mínimo ocurre cuando los niveles 0 a 3 están completamente llenos (árbol lleno de h=3) y el nivel 4 tiene exactamente **1 nodo**:
- Árbol lleno de h=3: (3^4 − 1)/(3−1) = 80/2 = 40 nodos
- +1 nodo en nivel 4: **41 nodos total**

**Respuesta: (b) 41**

---

## Ejercicio 21

**Enunciado**: Un AG lleno de grado 5 tiene 125 hojas. ¿Cuántos nodos internos tiene?

### Resolución

**Paso 1 — encontrar la altura**: en un AG lleno de grado k, las hojas están todas en el nivel h, y hay k^h hojas.
```
125 = 5^h  →  5^3 = 125  →  h = 3
```

**Paso 2 — calcular nodos internos**:

Método 1 (con fórmula):
```
N total = (5^4 - 1) / (5 - 1) = (625 - 1) / 4 = 624 / 4 = 156
Internos = N - hojas = 156 - 125 = 31
```

Método 2 (con propiedad de AG lleno):
En un AG lleno de grado k, I × (k − 1) = L − 1:
- Cada nodo interno aporta k hijos; contando aristas: k×I = N−1 = I+L−1 → I(k−1) = L−1
```
I = (L - 1) / (k - 1) = (125 - 1) / (5 - 1) = 124 / 4 = 31
```

**La cantidad de nodos internos es 31.**

Verificación: 31 internos × 5 hijos = 155 = N − 1 = 156 − 1 ✓

---

## Ejercicio 22

**Enunciado**: ¿Cuál es la cantidad de nodos en un AG **completo** de grado 4 y altura 3?

**Opciones**: a) entre 16 y 21  b) entre 22 y 85  c) entre 22 y 64  d) entre 16 y 64

### Resolución

Se aplica la fórmula del Ejercicio 16 con k=4, h=3:

**Mínimo**:
```
(k^h + k - 2) / (k - 1) = (4^3 + 4 - 2) / (4 - 1) = (64 + 2) / 3 = 66 / 3 = 22
```

**Máximo** (= árbol lleno de altura 3):
```
(k^(h+1) - 1) / (k - 1) = (4^4 - 1) / (4 - 1) = (256 - 1) / 3 = 255 / 3 = 85
```

**Respuesta: (b) entre 22 y 85**

---

## Resumen de Fórmulas Clave

| Estructura | Fórmula |
|---|---|
| AB lleno, altura h | N = 2^(h+1) − 1 |
| AB lleno, con L hojas | N = 2L − 1 |
| AB completo, altura h (mínimo) | N = 2^h |
| AB completo, altura h (máximo) | N = 2^(h+1) − 1 |
| AG lleno, grado k, altura h | N = (k^(h+1) − 1) / (k − 1) |
| AG completo, grado k, altura h (mínimo) | N = (k^h + k − 2) / (k − 1) |
| AG completo, grado k, altura h (máximo) | N = (k^(h+1) − 1) / (k − 1) |
| AG lleno, nodos internos con L hojas | I = (L − 1) / (k − 1) |

## Resumen de Recorridos

| Recorrido | AB | AG |
|---|---|---|
| PreOrden | raíz → izq → der | raíz → hijos (izq a der) |
| InOrden | izq → raíz → der | primer hijo → raíz → hijos restantes |
| PostOrden | izq → der → raíz | hijos (izq a der) → raíz |
| Último en PostOrden | siempre la raíz | siempre la raíz |
| Primero en PreOrden | siempre la raíz | siempre la raíz |

## Expresiones y Árboles de Expresión

| Notación | Cómo construir el árbol | Cómo leer el árbol |
|---|---|---|
| Postfija → árbol | pila: al ver operador, pop dos operandos | — |
| Prefija → árbol | recursión: operador toma los dos siguientes subárboles | — |
| Árbol → Prefija | — | PreOrden |
| Árbol → Infija | — | InOrden (con paréntesis) |
| Árbol → Postfija | — | PostOrden |
