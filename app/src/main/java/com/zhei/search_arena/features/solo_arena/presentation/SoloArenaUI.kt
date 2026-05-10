package com.zhei.search_arena.features.solo_arena.presentation
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.search_arena.core.MyFont
import com.zhei.search_arena.core.common.ui.DEVICE_BOTTOM_PADDING
import com.zhei.search_arena.core.common.ui.ResponsiveLineHeight
import com.zhei.search_arena.core.common.ui.ResponsiveText
import com.zhei.search_arena.core.common.ui.WIDTH_DP_DEVICE
import com.zhei.search_arena.core.di.LocalDeps
import java.text.NumberFormat
import java.util.Locale


@Composable fun SoloArenaUI(
    vm: SoloArenaVM = viewModel(factory = LocalDeps.current.depSA3)
) {

    val withDataSort by vm.withSort
    val colorHorizontalDiv = Color(0xFFffcc00).copy(alpha = 0.7f)

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF000412))
                .statusBarsPadding()
                .padding(bottom = DEVICE_BOTTOM_PADDING(), start = 20.dp, end = 20.dp)
        ) {

            item { QuestionSearchAlgorithm(vm) }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item { HorizontalDivider(color = colorHorizontalDiv) }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item { QuestionDoYouWantToOrder(vm) }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item { HorizontalDivider(color = colorHorizontalDiv) }

            item {
                if (withDataSort) {
                    Spacer(modifier = Modifier.height(20.dp))
                    QuestionWhatAlgoritmToSort(vm)
                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(color = colorHorizontalDiv)
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item { QuestionHowManyDigitsDoHave(vm) }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item { HorizontalDivider(color = colorHorizontalDiv) }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item { QuestionSelectTheNumberIntoTheRange(vm) }
        }

        ArraivalCardd(vm)
    }


}


@Composable private fun QuestionSearchAlgorithm(
    vm: SoloArenaVM
) {

    //  "Jump Search",
    //  "Interpolation Search",
    //  "Exponential Search",
    //  "Fibonacci Search",
    //  "Ternary Search"

    val algorithmName by vm.searchAlgo

    val algorithms = remember {
        listOf(
            "Binary Search",
            "Linear Search"
        )
    }

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¡Selecciona tu algoritmo de búsqueda!",
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            lineHeight =  ResponsiveLineHeight.headline()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box {

                // Botón
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFD52E))
                        .clickable { expanded = true }
                        .padding(start = 12.dp, end = 8.dp),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = algorithmName ?: "Presioname...",
                        color = Color.Black
                    )
                }

                // Menú desplegable
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    algorithms.forEach { algorithm ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = algorithm,
                                    fontSize = ResponsiveText.title()
                                )
                            },
                            onClick = {
                                vm.setSearchAlgo(algorithm)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                imageVector = if (vm.searchAlgo.value.isNullOrEmpty()) Icons.Default.Cancel else Icons.Default.CheckCircle,
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (vm.searchAlgo.value.isNullOrEmpty()) Color.Red else Color.Green)
            )
        }
    }
}


@Composable private fun QuestionDoYouWantToOrder(vm: SoloArenaVM) {

    val withSortdBro by vm.withSort
    val shape = RoundedCornerShape(20.dp)

    val fromHeigth = 40.dp
    val fromWidth = 100.dp

    val movement = with(LocalDensity.current) { (fromWidth - fromHeigth).toPx() }

    val animatedTranslation by animateFloatAsState(
        targetValue = if (withSortdBro) movement else 0f,
        animationSpec = tween(500),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¿Quieres ordenar tus datos?",
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            lineHeight = ResponsiveLineHeight.headline()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .width(fromWidth)
                .height(fromHeigth)
                .background(color = Color.White, shape)
                .graphicsLayer { translationX = if (withSortdBro) animatedTranslation else 0f }

        ) {

            Box(
                modifier = Modifier
                    .size(fromHeigth)
                    .clip(shape)
                    .background(color = Color(0xFFFFD52E), shape)
                    .clickable { vm.setWithSort() },
                contentAlignment = Alignment.Center
            ) {

                Image(
                    imageVector = if (withSortdBro) Icons.Default.CheckCircle else Icons.Default.Cancel,
                    contentDescription = null
                )
            }
        }
    }
}


@Composable private fun QuestionWhatAlgoritmToSort(vm: SoloArenaVM) {

    // "Bubble Sort",
    // "Selection Sort",
    // "Insertion Sort",
    // "Merge Sort",
    // "Quick Sort",
    // "Heap Sort",
    // "Shell Sort",
    // "Radix Sort",
    // "Counting Sort",
    // "Bucket Sort"

    val algorithmName by vm.sortAlgo

    val algorithms = remember {
        listOf(
            "Bubble Sort",
            "Insertion Sort",
            "Merge Sort"
        )
    }

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¡Selecciona tu algoritmo de ordenamiento!",
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            lineHeight = ResponsiveLineHeight.headline()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box {

                // Botón
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFD52E))
                        .clickable { expanded = true }
                        .padding(start = 12.dp, end = 8.dp),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = algorithmName ?: "Presioname...",
                        color = Color.Black
                    )
                }

                // Menú desplegable
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    algorithms.forEach { algorithm ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = algorithm,
                                    fontSize = ResponsiveText.title()
                                )
                            },
                            onClick = {
                                vm.setSortAlgo(algorithm)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                imageVector = if (vm.sortAlgo.value.isNullOrEmpty()) Icons.Default.Cancel else Icons.Default.CheckCircle,
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (vm.sortAlgo.value.isNullOrEmpty()) Color.Red else Color.Green)
            )
        }
    }
}


@Composable private fun QuestionHowManyDigitsDoHave(vm: SoloArenaVM) {

    val rangeDigits = vm.showDataRange()
    val focus = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¿Cuantos DÍGITOS tendra tu set de datos de prueba?",
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            lineHeight = ResponsiveLineHeight.headline()
        )

        if (!vm.digitsForDatasetIsMorethanZero()) {
            Text(
                text = "¡Ingresa un valor mayor que (cero)!",
                color = Color.Red,
                textAlign = TextAlign.Start,
                lineHeight = ResponsiveLineHeight.caption(),
                fontSize = ResponsiveText.caption()
            )
        }

        if (rangeDigits != "") {
            Text(
                text = "Rango: $rangeDigits",
                color = Color.White,
                textAlign = TextAlign.Start,
                lineHeight = ResponsiveLineHeight.caption(),
                fontSize = ResponsiveText.caption()
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = vm.datasetDigits.value,
                onValueChange = { vm.setDataSetDigits(it) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focus.clearFocus() }
                ),
                modifier = Modifier.width(100.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color(0xFFFFCC00),
                    unfocusedTextColor = Color.Black,
                    unfocusedContainerColor = Color(0xFFFFCC00),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    fontFamily = MyFont.jetBrainsMonoMedium,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            val cond = vm.datasetDigits.value.isEmpty() || (vm.datasetDigits.value.toIntOrNull() ?: 0) == 0

            Image(
                imageVector = if (cond) Icons.Default.Cancel else Icons.Default.CheckCircle,
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (cond) Color.Red else Color.Green)
            )
        }
    }
}


@Composable private fun QuestionSelectTheNumberIntoTheRange(vm: SoloArenaVM) {

    val focus = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¡Escoge tú número a buscar dentro del rango establecido!",
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            lineHeight = ResponsiveLineHeight.headline()
        )

        if (!vm.isIntoTheRange()) {
            Text(
                text = "¡Número fuera del rango!",
                color = Color.Red,
                textAlign = TextAlign.Start,
                fontSize = ResponsiveText.caption(),
                lineHeight = ResponsiveLineHeight.caption()
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            TextField(
                value = vm.selectedNumber.value,
                onValueChange = { vm.setSelectedNumber(it) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focus.clearFocus() }
                ),
                modifier = Modifier.width(WIDTH_DP_DEVICE() / 2),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color(0xFFFFCC00),
                    unfocusedTextColor = Color.Black,
                    unfocusedContainerColor = Color(0xFFFFCC00),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            val cond =
                vm.selectedNumber.value.isEmpty() || vm.selectedNumber.value.length != (vm.datasetDigits.value.toIntOrNull() ?: 0)

            Image(
                imageVector = if (cond) Icons.Default.Cancel else Icons.Default.CheckCircle,
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (cond) Color.Red else Color.Green)
            )
        }
    }
}


@Composable private fun ArraivalCardd(vm: SoloArenaVM) {


    // BLOQUEADOR + MODAL
    if (vm.everythingIsReady()) {

        // Captura todos los clicks
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.35f))
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            awaitPointerEvent()
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF151515)
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "¿Listo?",
                        fontSize = ResponsiveText.headline(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Button(
                            onClick = {}
                        ) {

                            Text("Sí")
                        }

                        OutlinedButton(
                            onClick = {}
                        ) {

                            Text("No")
                        }
                    }
                }
            }
        }
    }
}