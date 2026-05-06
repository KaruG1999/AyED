# Ejercicio 4: AnalizadorArbol — Mayor promedio por nivel

## Enunciado

El esquema de comunicación de una empresa está organizado en una estructura jerárquica. Cada nodo contiene el tiempo que tarda en transmitir el mensaje.

Se debe devolver el **mayor promedio** entre todos los promedios de los niveles.

Para el árbol del enunciado:
- Nivel 0: promedio = 14
- Nivel 1: promedio = (13 + 25 + 10) / 3 = **16**
- Nivel 2: promedio = (4+7+5+6+10+18+9+12+19) / 9 = 10

Debe retornar **16**.

**a)** Indicar y justificar qué tipo de recorrido se usa.

**b)** Implementar en `AnalizadorArbol`:
```java
public double devolverMaximoPromedio(GeneralTree<AreaEmpresa> arbol)
```

Donde `AreaEmpresa` contiene:
- `String identificacion` — nombre del área
- `int tardanza` — tiempo de transmisión

---

## Conceptos Técnicos Clave

### 1. ¿Qué recorrido usar? (parte a)

**Recorrido por niveles (BFS)** — justificación:
- Necesitamos agrupar nodos **por nivel** para calcular el promedio de cada uno.
- BFS procesa exactamente todos los nodos del nivel k antes de pasar al nivel k+1.
- Los recorridos DFS (pre/post/in) mezclan nodos de distintos niveles y no permiten agruparlos fácilmente.

### 2. Técnica: separador null en la Queue

La forma más directa de saber cuándo termina un nivel en BFS es encolar `null` como marcador:

```
encolar(raíz)
encolar(null)          ← fin del nivel 0

mientras cola no vacía:
    actual = desencolar()
    si actual == null:              ← terminó un nivel
        calcular promedio del nivel
        si cola no vacía → encolar(null)   ← marcar fin del siguiente nivel
    sino:
        acumular tardanza, contar nodo
        encolar hijos de actual
```

### 3. Esquema en Java

```java
Queue<GeneralTree<AreaEmpresa>> cola = new LinkedList<>();
cola.add(arbol);
cola.add(null);

double maxPromedio = Double.MIN_VALUE;
double sumaActual  = 0;
int    contActual  = 0;

while (!cola.isEmpty()) {
    GeneralTree<AreaEmpresa> actual = cola.poll();
    if (actual == null) {
        double promedio = sumaActual / contActual;
        if (promedio > maxPromedio) maxPromedio = promedio;
        sumaActual = 0;
        contActual = 0;
        if (!cola.isEmpty()) cola.add(null);
    } else {
        sumaActual += actual.getData().getTardanza();
        contActual++;
        for (GeneralTree<AreaEmpresa> hijo : actual.getChildren()) {
            cola.add(hijo);
        }
    }
}
return maxPromedio;
```

### 4. Alternativa: Map\<Integer, List\<Integer\>\>

Otra estrategia es usar un mapa `nivel → lista de tardanzas` haciendo BFS y llevando el nivel actual como parámetro. Al final se recorre el mapa para calcular promedios y encontrar el máximo.

---

## Archivos
| Archivo | Descripción |
|---|---|
| `AreaEmpresa.java` | Clase que representa un área con identificación y tardanza |
| `AnalizadorArbol.java` | Clase con el TODO a implementar y el main de prueba |
