# Ejercicio 9: ParcialArboles — esDeSeleccion (Parcial)

## Restricciones del Ejercicio (tipo parcial)

1. No puede agregar más variables de instancia ni de clase a `ParcialArboles`.
2. Debe respetar la firma del método indicado.
3. Puede definir todos los métodos y variables **locales** que considere necesarios.
4. Todo método no definido en la sinopsis debe ser implementado.
5. **Debe recorrer la estructura solo 1 vez.**

---

## Enunciado

Implementar en la clase `ParcialArboles` el método:

```java
public static boolean esDeSeleccion(GeneralTree<Integer> arbol)
```

Devuelve `true` si el árbol es **de selección**, falso si no.

Un árbol general es **de selección** si cada nodo tiene en su raíz el valor del **menor de sus hijos directos**.

Las hojas cumplen la propiedad vacuamente (no tienen hijos para comparar).

---

## Conceptos Técnicos Clave

### 1. La Propiedad

Para cada nodo `n` con hijos `h1, h2, ..., hk`:
```
n.getData() == min(h1.getData(), h2.getData(), ..., hk.getData())
```

Ojo: se compara con los valores de los **hijos directos**, no con todos los descendientes.

### 2. ¿Qué recorrido usar?

**PostOrden** — se verifica la condición al volver de los hijos:

```
esDeSeleccionAux(nodo):
    Si es hoja → true
    
    minHijo = +infinito
    para cada hijo:
        si !esDeSeleccionAux(hijo) → return false   // hijo ya falla
        si hijo.getData() < minHijo → minHijo = hijo.getData()
    
    return nodo.getData() == minHijo
```

Se recorre el árbol exactamente una vez (cada nodo se visita una sola vez).

### 3. Esquema en Java

```java
private static boolean esDeSeleccionAux(GeneralTree<Integer> nodo) {
    if (nodo.isLeaf()) return true;

    int minHijo = Integer.MAX_VALUE;
    for (GeneralTree<Integer> hijo : nodo.getChildren()) {
        if (!esDeSeleccionAux(hijo)) return false;
        if (hijo.getData() < minHijo) minHijo = hijo.getData();
    }
    return nodo.getData() == minHijo;
}
```

### 4. Traza del ejemplo que retorna false

El nodo con valor `18` tiene hijos `[35, 14, 18, 33]`. El mínimo de sus hijos es `14`.
Pero `18.getData() = 18 ≠ 14` → la propiedad **no se cumple** → retorna `false`.

---

## Tabla de resultados

| Árbol | Resultado | Razón |
|---|---|---|
| Árbol 1 del enunciado | `true` | Cada nodo contiene el mínimo de sus hijos |
| Árbol 2 del enunciado | `false` | El nodo con raíz 18 tiene hijo mínimo 14 |

## Archivos
| Archivo | Descripción |
|---|---|
| `ParcialArboles.java` | Clase con el TODO a implementar |
