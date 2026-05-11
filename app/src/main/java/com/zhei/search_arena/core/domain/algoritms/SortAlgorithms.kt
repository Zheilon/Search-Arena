package com.zhei.search_arena.core.domain.algoritms
import com.zhei.search_arena.core.domain.models.AlgorithmAtts

enum class SortAlgorithms(
    private val confgs: AlgorithmAtts
) {

    BUBBLE(
        AlgorithmAtts(
            name = "Bubble",
            strategy = "Intercambia vecinos repetidamente",
            bestCase = "O(n)",
            mean = "O(n²)",
            worseCase = "O(n²)",
            space = "O(1)",
            code = """
                    def bubble_sort(arr):
                    
                        n = len(arr)
                    
                        for i in range(n):
                            for j in range(0, n - i - 1):
                    
                                if arr[j] > arr[j + 1]:
                                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                    
                        return arr
            """.trimIndent()
        )
    ),

    SELECTION(
        AlgorithmAtts(
            name = "Selection",
            strategy = "Selecciona el mínimo y lo mueve",
            bestCase = "O(n²)",
            mean = "O(n²)",
            worseCase = "O(n²)",
            space = "O(1)",
            code = """
                    def selection_sort(arr):
                    
                        n = len(arr)
                    
                        for i in range(n - 1):
                            min_index = i
                    
                            # Buscar el índice del menor elemento
                            
                            for j in range(i + 1, n):
                                if arr[j] < arr[min_index]:
                                    min_index = j
                    
                            # Solo intercambiar si realmente cambió
                            
                            if min_index != i:
                                arr[i], arr[min_index] = arr[min_index], arr[i]
                    
                        return arr
            """.trimIndent()
        )
    ),

    INSERTION(
        AlgorithmAtts(
            name = "Insertion",
            strategy = "Inserta elementos en orden",
            bestCase = "O(n)",
            mean = "O(n²)",
            worseCase = "O(n²)",
            space = "O(1)",
            code = """
                    def insertion_sort(arr):
                    
                        n = len(arr)
                    
                        for i in range(1, n):
                        
                            key = arr[i]  # Elemento actual
                            j = i - 1
                    
                            # Mover elementos mayores una posición adelante
                            
                            while j >= 0 and arr[j] > key:
                            
                                arr[j + 1] = arr[j]
                                j -= 1
                    
                            # Insertar en la posición correcta
                  
                            arr[j + 1] = key
                    
                        return arr
            """.trimIndent()
        )
    ),

    MERGE(
        AlgorithmAtts(
            name = "Merge",
            strategy = "Divide y fusiona sublistas",
            bestCase = "O(n log n)",
            mean = "O(n log n)",
            worseCase = "O(n log n)",
            space = "O(n)",
            code = """
                def merge(left, right):
                
                    merged = []
                    i = 0
                    j = 0
                
                    # Comparar elementos de ambas listas
                    
                    while i < len(left) and j < len(right):
                    
                        if left[i] <= right[j]:
                            merged.append(left[i])
                            i += 1
                            
                        else:
                            merged.append(right[j])
                            j += 1
                
                    # Agregar sobrantes
                    merged.extend(left[i:])
                    merged.extend(right[j:])
                
                    return merged
                
                
                def merge_sort(arr):
                
                    # Caso base
                    if len(arr) <= 1:
                        return arr
                
                    mid = len(arr) // 2
                
                    # Dividir
                    left = merge_sort(arr[:mid])
                    right = merge_sort(arr[mid:])
                
                    # Unir ordenadamente
                    return merge(left, right)
                
            """.trimIndent()
        )
    ),

    QUICK(
        AlgorithmAtts(
            name = "Quick",
            strategy = "Usa pivote y particiona",
            bestCase = "O(n log n)",
            mean = "O(n log n)",
            worseCase = "O(n²)",
            space = "O(log n)",
            code = """
                def quick_sort(arr):
                
                    # Caso base
                    if len(arr) <= 1:
                        return arr
                
                    # Elegir pivote (más estable que usar el primero)
                    pivot = arr[len(arr) // 2]
                
                    # Particionar
                    left = [x for x in arr if x < pivot]
                    middle = [x for x in arr if x == pivot]
                    right = [x for x in arr if x > pivot]
                
                    # Ordenar recursivamente
                    return quick_sort(left) + middle + quick_sort(right)
            """.trimIndent()
        )
    ),

    HEAP(
        AlgorithmAtts(
            name = "Heap",
            strategy = "Construye un heap binario",
            bestCase = "O(n log n)",
            mean = "O(n log n)",
            worseCase = "O(n log n)",
            space = "O(1)",
            code = """
                def heapify(arr, n, i):
                
                    largest = i
                    left = 2 * i + 1
                    right = 2 * i + 2
                
                    # Hijo izquierdo mayor
                    if left < n and arr[left] > arr[largest]:
                        largest = left
                
                    # Hijo derecho mayor
                    if right < n and arr[right] > arr[largest]:
                        largest = right
                
                    # Si cambió el mayor
                    if largest != i:
                        arr[i], arr[largest] = arr[largest], arr[i]
                
                        # Heapify recursivo
                        heapify(arr, n, largest)
                        
                        
                def heap_sort(arr):
                
                    n = len(arr)
                
                    # Construir Max Heap
                    for i in range(n // 2 - 1, -1, -1):
                        heapify(arr, n, i)
                
                    # Extraer elementos uno por uno
                    for i in range(n - 1, 0, -1):
                    
                        # Mover raíz al final
                        arr[0], arr[i] = arr[i], arr[0]
                
                        # Restaurar heap
                        heapify(arr, i, 0)
                
                    return arr
            """.trimIndent()
        )
    ),

    SHELL(
        AlgorithmAtts(
            name = "Shell",
            strategy = "Ordena usando gaps decrecientes",
            bestCase = "O(n log n)",
            mean = "~O(n^1.5)",
            worseCase = "O(n²)",
            space = "O(1)",
            code = """
                def shell_sort(arr):
                
                    n = len(arr)
                
                    # Gap inicial
                    gap = n // 2
                
                    while gap > 0:
                
                        # Insertion Sort con saltos
                        for i in range(gap, n):
                            temp = arr[i]
                            j = i
                
                            # Comparar elementos separados por gap
                            while j >= gap and arr[j - gap] > temp:
                                arr[j] = arr[j - gap]
                                j -= gap
                
                            arr[j] = temp
                
                        # Reducir gap
                        gap //= 2
                
                    return arr
            """.trimIndent()
        )
    );

    companion object {

        fun getNames(): List<String> = entries.map { it.confgs.name }

        fun findByName(name: String): AlgorithmAtts? = entries.find { it.confgs.name == name }?.confgs
    }

}