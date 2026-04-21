# Ejercicio 6: Estructuras de Listas en Java

## Enunciado
Análisis comparativo de rendimiento, memoria y casos de uso entre `ArrayList` y `LinkedList` basándose en la API de Java.

## Conceptos Técnicos Clave

### 1. Estructura Interna
- **ArrayList**: Basada en arreglos dinámicos. Los datos están juntos en memoria.
- **LinkedList**: Basada en nodos independientes conectados por punteros. Los datos pueden estar dispersos.

### 2. Complejidad Computacional (Big O)
- **Acceso por índice**: `ArrayList` $O(1)$ vs `LinkedList` $O(n)$.
- **Insertar al inicio**: `ArrayList` $O(n)$ (debe mover todo) vs `LinkedList` $O(1)$.



### 3. Overhead de Memoria
`LinkedList` consume significativamente más memoria por elemento debido a la necesidad de almacenar punteros `next` y `prev` además del dato real.

## Diagnóstico de Aplicación
- **Viabilidad:** 🟢 Usar `ArrayList` por defecto a menos que la aplicación sea una cola intensiva o requiera inserciones masivas en el frente.