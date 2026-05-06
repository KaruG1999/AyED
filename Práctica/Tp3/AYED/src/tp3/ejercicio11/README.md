# Ejercicio 11: ParcialArboles — Árbol Creciente (Parcial)

## Restricciones del Ejercicio (tipo parcial)

1. No puede agregar más variables de instancia ni de clase a `ParcialArboles`.
2. Debe respetar la firma del método indicado.
3. Puede definir todos los métodos y variables **locales** que considere necesarios.
4. Todo método no definido en la sinopsis debe ser implementado.
5. **Debe recorrer la estructura solo 1 vez.**

---

## Enunciado

Implementar en `ParcialArboles`:

```java
public static boolean resolver(GeneralTree<Integer> arbol)
```

Devuelve `true` si el árbol es **creciente**, falso si no.

Un árbol general es **creciente** si para cada nivel la cantidad de nodos en ese nivel es exactamente **igual a la del nivel anterior + 1**.

```
nivel 0 → 1 nodo
nivel 1 → 2 nodos
nivel 2 → 3 nodos
nivel k → k+1 nodos
```

---

## Conceptos Técnicos Clave

### 1. ¿Qué recorrido usar?

**BFS (por niveles)** — es la única forma de verificar esta propiedad de manera natural, porque necesitamos conocer la cantidad de nodos **por nivel**.

### 2. Técnica: separador null en la Queue

Se usa `null` como marcador de fin de nivel:

```
encolar(raíz)
encolar(null)           ← marca fin del nivel 0
nivelEsperado = 1       (nivel 0 tiene 1 nodo, que ya encolamos)
contadorNivel = 0

mientras cola no vacía:
    actual = desencolar()
    si actual == null:              ← terminó un nivel
        si contadorNivel != nivelEsperado → return false
        nivelEsperado++
        contadorNivel = 0
        si cola no vacía → encolar(null)
    sino:
        contadorNivel++
        encolar todos los hijos de actual

return true
```

### 3. Esquema en Java

```java
Queue<GeneralTree<Integer>> cola = new LinkedList<>();
cola.add(arbol);
cola.add(null);

int nivelEsperado = 1;
int contadorNivel = 0;

while (!cola.isEmpty()) {
    GeneralTree<Integer> actual = cola.poll();
    if (actual == null) {
        if (contadorNivel != nivelEsperado) return false;
        nivelEsperado++;
        contadorNivel = 0;
        if (!cola.isEmpty()) cola.add(null);
    } else {
        contadorNivel++;
        for (GeneralTree<Integer> hijo : actual.getChildren()) {
            cola.add(hijo);
        }
    }
}
return true;
```

### 4. ¿Por qué la condición es `nivelEsperado` y no `nivelEsperado + 1`?

Antes de entrar al ciclo, el nivel 0 ya fue encolado (1 nodo = raíz). El primer separador `null` encontrado corresponde al final del nivel 0, pero `contadorNivel` al llegar al `null` cuenta los nodos del **nivel 1** que ya se encolaron. Hay que ajustar la lógica para que coincida con los nodos del nivel actual (ver el esquema con cuidado).

> Alternativa más clara: usar dos colas (una para el nivel actual, otra para el siguiente).

### 5. Traza del árbol 2 (false)

```
Nivel 0: 1 nodo  (raíz=2)            → esperado=1 ✓
Nivel 1: 2 nodos (1, 25)             → esperado=2 ✓
Nivel 2: 3 nodos (5, 4, 13)          → esperado=3 ✓
Nivel 3: 3 nodos (18, 7, 3)          → esperado=4 ✗  → return false
```

---

## Archivos
| Archivo | Descripción |
|---|---|
| `ParcialArboles.java` | Clase con el TODO a implementar |
