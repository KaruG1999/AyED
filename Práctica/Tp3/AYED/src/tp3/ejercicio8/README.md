# Ejercicio 8: Navidad — esAbetoNavidenio()

## Enunciado

Retomando el ejercicio **abeto navideño** visto en teoría, crear una clase `Navidad` con:
- Una variable de instancia `GeneralTree` que representa el abeto (ya construido en el constructor).
- El método: `public String esAbetoNavidenio()`

---

## ¿Qué es un Abeto Navideño?

En teoría se construyó un árbol general con **forma de abeto/pino navideño**: cada nivel tiene exactamente **un nodo más que el nivel anterior**.

```
Nivel 0:    *          → 1 nodo
Nivel 1:   * *         → 2 nodos
Nivel 2:  * * *        → 3 nodos
Nivel 3: * * * *       → 4 nodos
```

Formalmente: **en el nivel k, hay exactamente k+1 nodos**.

---

## Conceptos Técnicos Clave

### 1. Verificar la forma de abeto — BFS con contador

Para verificar si un árbol cumple la propiedad, se hace un recorrido por niveles (BFS) contando los nodos de cada nivel:

```
nivel_esperado = 1   (en el nivel 0 debe haber 1 nodo)
para cada nivel en BFS:
    si cantidad_de_nodos_en_este_nivel != nivel_esperado → NO es abeto
    nivel_esperado++
```

Técnica: usar `null` como marcador de fin de nivel:
```java
Queue<GeneralTree<String>> cola = new LinkedList<>();
cola.add(abeto);
cola.add(null);
int nivelEsperado = 1;
int contadorActual = 0;

while (!cola.isEmpty()) {
    GeneralTree<String> actual = cola.poll();
    if (actual == null) {
        if (contadorActual != nivelEsperado) return false;
        nivelEsperado++;
        contadorActual = 0;
        if (!cola.isEmpty()) cola.add(null);
    } else {
        contadorActual++;
        for (GeneralTree<String> hijo : actual.getChildren()) cola.add(hijo);
    }
}
return true;
```

### 2. Generar representación visual — BFS nivel por nivel

Si el árbol ES un abeto, se construye un String con cada nivel en una línea (centrado):

```java
// Recoger los datos de cada nivel
// Por cada nivel k: la línea tiene los datos separados por espacios
// Para centrar: (altura - nivel) espacios al inicio
```

Ejemplo de salida:
```
      *
     * *
    * * *
   * * * *
```

### 3. Estructura del abeto en el código

El abeto se construye en `construirAbeto()` dentro del constructor. Ejemplo para 4 niveles:
- Raíz (nivel 0): 1 nodo → tiene 2 hijos
- Nivel 1: 2 nodos → entre todos tienen 3 hijos (ej: uno tiene 2, otro tiene 1)
- Nivel 2: 3 nodos → entre todos tienen 4 hijos (ej: 2, 1, 1)
- Nivel 3: 4 nodos hojas

### 4. El método devuelve String

- Si es abeto: devuelve la **representación visual** del árbol (fila por fila).
- Si no es abeto: devuelve `"No es un abeto navideño"`.

---

## Archivos
| Archivo | Descripción |
|---|---|
| `Navidad.java` | Clase con el árbol ya construido, dos TODOs y el main |
