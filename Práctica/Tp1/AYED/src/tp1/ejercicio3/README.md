# Ejercicio 3: Objetos, Instancias y Debugging

## Enunciado
1. **Modelado**: Crear las clases `Estudiante` y `Profesor` con sus atributos privados, métodos **Getters** y **Setters**.
2. **Método de Instancia**: Implementar `tusDatos()` en ambas clases para retornar un `String` con la información completa del objeto.
3. **Instanciación**: Crear una clase `Test` que inicialice arreglos de objetos (`Estudiante[]` y `Profesor[]`).
4. **Asignación**: Cargar datos usando los métodos `set`.
5. **Debugging**: Utilizar Breakpoints para inspeccionar el estado de los objetos en tiempo de ejecución.

## Conceptos Técnicos Clave

### 1. El Operador `new`
Es el comando que reserva espacio en el **Heap** (memoria dinámica) para un nuevo objeto. 
- Al hacer `new Estudiante()`, se crea una instancia única con sus propios valores de nombre, apellido, etc.

### 2. Encapsulamiento (Getters y Setters)
Los atributos se definen como `private` para que no puedan ser modificados por error desde afuera.
- **Getter**: Método para leer el valor (ej. `getNombre()`).
- **Setter**: Método para modificar el valor con validación (ej. `setNombre("Karen")`).



### 3. Métodos de Instancia
A diferencia de los métodos `static`, el método `tusDatos()` **no** lleva la palabra `static`.
- **Por qué:** Porque necesita acceder a los datos específicos de "este" objeto (usando los getters internos). Cada estudiante tendrá sus propios datos.

### 4. Arreglos de Objetos
Un arreglo de objetos (`Estudiante[]`) no guarda los datos directamente, sino **referencias** (direcciones de memoria) a donde viven esos objetos.
- **Punto crítico:** Si creas el arreglo `new Estudiante[2]` pero no haces `new Estudiante()` para cada posición, obtendrás un `NullPointerException`.

### 5. Debugging y Breakpoints
- **Breakpoint**: Es una marca que detiene el programa en una línea específica.
- **Modo Debug**: Permite ver el "interior" de las variables en vivo. Es la herramienta principal para encontrar errores de lógica sin llenar el código de `System.out.println`.



## Análisis del Stack
| Elemento | Descripción |
| :--- | :--- |
| **Referencia** | La variable que apunta al objeto en memoria. |
| **Instancia** | El objeto real creado en el Heap. |
| **Heap Memory** | Lugar donde residen los objetos en Java. |