package com.zhei.search_arena.core.domain.algoritms
import com.zhei.search_arena.core.domain.models.AlgorithmAtts

enum class SearchsAlgorithms (
    private val configs: AlgorithmAtts
) {

    LINEAR(
        AlgorithmAtts(
            name = "Linear",
            strategy = "Recorre uno por uno",
            bestCase = "O(1)",
            mean = "O(n)",
            worseCase = "O(n)",
            space = "O(1)",
            code = """
                def linear_search(arr, target):

                    for index, value in enumerate(arr):
                        if value == target:
                            return index
                
                    return -1
            """.trimIndent()
        )
    ),

    BINARY(
        AlgorithmAtts(
            name = "Binary",
            strategy = "Divide en mitades",
            bestCase = "O(1)",
            mean = "O(log n)",
            worseCase = "O(log n)",
            space = "O(1)",
            code = """
                def binary_search(arr, target):
                
                    left = 0
                    right = len(arr) - 1
                
                    while left <= right:
                        mid = (left + right) // 2
                
                        # Encontrado
                        if arr[mid] == target:
                            return mid
                
                        # Buscar izquierda
                        elif arr[mid] > target:
                            right = mid - 1
                
                        # Buscar derecha
                        else:
                            left = mid + 1
                
                    return -1
            """.trimIndent()
        )
    ),

    JUMP(
        AlgorithmAtts(
            name = "Jump",
            strategy = "Hace saltos",
            bestCase = "O(1)",
            mean = "O(√n)",
            worseCase = "O(√n)",
            space = "O(1)",
            code = """
                import math

                def jump_search(arr, target):
                
                    n = len(arr)
                
                    # Tamaño óptimo del salto
                    step = int(math.sqrt(n))
                    prev = 0
                
                    # Saltar bloques
                    while prev < n and arr[min(step, n) - 1] < target:
                    
                        prev = step
                        step += int(math.sqrt(n))
                
                        if prev >= n:
                            return -1
                
                    # Búsqueda lineal en el bloque
                    while prev < min(step, n):
                    
                        if arr[prev] == target:
                            return prev
                
                        prev += 1
                
                    return -1
            """.trimIndent()
        )
    ),

    INTERPOLATION(
        AlgorithmAtts(
            name = "Interpolation",
            strategy = "Estima posición matemática",
            bestCase = "O(1)",
            mean = "O(log log n)",
            worseCase = "O(n)",
            space = "O(1)",
            code = """
                def interpolation_search(arr, target):
                
                    left = 0
                    right = len(arr) - 1
                
                    while (
                        left <= right
                        and target >= arr[left]
                        and target <= arr[right]
                    ):
                
                        # Evitar división por cero
                        if arr[left] == arr[right]:
                            if arr[left] == target:
                                return left
                            return -1
                
                        # Posición estimada
                        pos = left + (
                            (target - arr[left]) * (right - left)
                        ) // (arr[right] - arr[left])
                
                        # Encontrado
                        if arr[pos] == target:
                            return pos
                
                        # Buscar derecha
                        elif arr[pos] < target:
                            left = pos + 1
                
                        # Buscar izquierda
                        else:
                            right = pos - 1
                
                    return -1
            """.trimIndent()
        )
    ),

    EXPONENTIAL(
        AlgorithmAtts(
            name = "Exponential",
            strategy = "Busca rango + Binary Search",
            bestCase = "O(1)",
            mean = "O(log n)",
            worseCase = "O(log n)",
            space = "O(1)",
            code = """
                def binary_search(arr, target, left, right):

                    while left <= right:
                        mid = (left + right) // 2
                
                        if arr[mid] == target:
                            return mid
                
                        elif arr[mid] < target:
                            left = mid + 1
                
                        else:
                            right = mid - 1
                
                    return -1
                    
                    
                def exponential_search(arr, target):
                
                    n = len(arr)

                    # Lista vacía
                    if n == 0:
                        return -1

                    # Primer elemento
                    if arr[0] == target:
                        return 0

                    # Encontrar rango exponencial
                    i = 1
                    while i < n and arr[i] <= target:
                        i *= 2

                    # Binary Search en el rango
                    left = i // 2
                    right = min(i, n - 1)

                    return binary_search(arr, target, left, right)
            """.trimIndent()
        )
    ),

    FIBONACCI(
        AlgorithmAtts(
            name = "Fibonacci",
            strategy = "Divide con Fibonacci",
            bestCase = "O(1)",
            mean = "O(log n)",
            worseCase = "O(log n)",
            space = "O(1)",
            code = """
                def fibonacci_search(arr, target):
                
                    n = len(arr)
                
                    # Inicializar Fibonacci
                    fib2 = 0   # F(n-2)
                    fib1 = 1   # F(n-1)
                    fib = fib1 + fib2  # F(n)
                
                    # Encontrar el Fibonacci >= n
                    while fib < n:
                        fib2 = fib1
                        fib1 = fib
                        fib = fib1 + fib2
                
                    offset = -1
                
                    while fib > 1:
                
                        # Índice válido
                        i = min(offset + fib2, n - 1)
                
                        # Encontrado
                        if arr[i] < target:
                            fib = fib1
                            fib1 = fib2
                            fib2 = fib - fib1
                            offset = i
                
                        elif arr[i] > target:
                            fib = fib2
                            fib1 = fib1 - fib2
                            fib2 = fib - fib1
                
                        else:
                            return i
                
                    # Último elemento
                    if fib1 and offset + 1 < n and arr[offset + 1] == target:
                        return offset + 1
                
                    return -1
            """.trimIndent()
        )
    ),

    TERNARY(
        AlgorithmAtts(
            name = "Ternary",
            strategy = "Divide en 3 partes",
            bestCase = "O(1)",
            mean = "O(log₃ n)",
            worseCase = "O(log₃ n)",
            space = "O(1)",
            code = """
                def ternary_search(arr, target):
                
                    left = 0
                    right = len(arr) - 1
                
                    while left <= right:
                
                        # Calcular tercios
                        third = (right - left) // 3
                
                        mid1 = left + third
                        mid2 = right - third
                
                        # Encontrado
                        if arr[mid1] == target:
                            return mid1
                
                        if arr[mid2] == target:
                            return mid2
                
                        # Buscar izquierda
                        if target < arr[mid1]:
                            right = mid1 - 1
                
                        # Buscar derecha
                        elif target > arr[mid2]:
                            left = mid2 + 1
                
                        # Buscar en el tercio central
                        else:
                            left = mid1 + 1
                            right = mid2 - 1
                
                    return -1
            """.trimIndent()
        )
    );

    companion object {

        fun getNames(): List<String> = SearchsAlgorithms.entries.map { it.configs.name }

        fun findByName(name: String): AlgorithmAtts? =
            SearchsAlgorithms.entries.find { it.configs.name == name }?.configs
    }

}