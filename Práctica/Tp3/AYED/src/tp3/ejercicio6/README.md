# Ejercicio 6: RedDeAguaPotable — Mínimo Caudal

## Enunciado

Una red de agua potable comienza en un caño maestro y se va dividiendo sucesivamente. Al dividirse, el caudal se reparte en **partes iguales** entre todos los hijos del nodo.

Ejemplo:
- Raíz recibe 1000 litros con 4 hijos → cada hijo recibe 250 litros.
- Si uno de esos hijos tiene 5 hijos → cada nieto recibe 50 litros.

Implementar en `RedDeAguaPotable` (con variable de instancia `GeneralTree<Character>`):

```java
public double minimoCaudal(double caudal)
```

Que calcule el caudal de cada nodo y devuelva el **mínimo caudal** que recibe una casa (hoja).

Con el árbol del enunciado, `minimoCaudal(1000.0)` debe retornar **25.0**.

---

## Conceptos Técnicos Clave

### 1. La Clave: Caudal Proporcional

Cada nodo recibe un caudal del padre. Cuando lo distribuye entre sus hijos:

```
caudalParaCadaHijo = caudalRecibido / cantidadDeHijos
```

Una **hoja** (casa) no distribuye nada, simplemente **recibe** el caudal.

### 2. Estrategia Recursiva (PostOrden inverso / top-down)

La información fluye **de arriba hacia abajo** (el padre determina cuánto le da al hijo), así que el caudal se pasa como **parámetro** de la recursión:

```
minimoCaudalAux(nodo, caudal):
    Si es hoja:
        return caudal           ← esta casa recibe "caudal" litros
    
    caudal_hijo = caudal / nodo.getChildren().size()
    
    min = +infinito
    para cada hijo:
        resultado = minimoCaudalAux(hijo, caudal_hijo)
        si resultado < min → min = resultado
    
    return min
```

### 3. Esquema en Java

```java
private double minimoCaudalAux(GeneralTree<Character> nodo, double caudal) {
    if (nodo.isLeaf()) return caudal;

    double caudalHijo = caudal / nodo.getChildren().size();
    double min = Double.MAX_VALUE;

    for (GeneralTree<Character> hijo : nodo.getChildren()) {
        double res = minimoCaudalAux(hijo, caudalHijo);
        if (res < min) min = res;
    }
    return min;
}
```

### 4. Traza del ejemplo del enunciado

```
raíz recibe 1000, tiene 4 hijos → cada hijo recibe 250
  A recibe 250, tiene 2 hijos → E y F reciben 125
  B recibe 250, tiene 2 hijos → G y H reciben 125
  C recibe 250, tiene 3 hijos → I, J, K reciben ~83.33
  D recibe 250, tiene 1 hijo  → L recibe 250

  I recibe 83.33, tiene 2 hijos → M y N reciben ~41.67

  → Mínimo es el valor más pequeño entre todas las hojas
```

En el árbol exacto del PDF donde `minimoCaudal(1000.0)` = 25.0, la cadena más larga de divisiones llega a ese resultado.

---

## Archivos
| Archivo | Descripción |
|---|---|
| `RedDeAguaPotable.java` | Clase con variable de instancia, TODO y main de prueba |
