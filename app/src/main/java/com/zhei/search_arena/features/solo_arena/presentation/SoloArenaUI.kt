package com.zhei.search_arena.features.solo_arena.presentation
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.zhei.search_arena.R
import com.zhei.search_arena.core.MyFont
import com.zhei.search_arena.core.common.ui.DEVICE_BOTTOM_PADDING
import com.zhei.search_arena.core.common.ui.ResponsiveLineHeight
import com.zhei.search_arena.core.common.ui.ResponsiveText
import com.zhei.search_arena.core.common.ui.WIDTH_DP_DEVICE
import com.zhei.search_arena.core.domain.algoritms.SearchsAlgorithms
import com.zhei.search_arena.core.domain.algoritms.SortAlgorithms


@Composable fun SoloArenaUI(
    vm: SoloArenaVM,
    navigate: () -> Unit
) {

    val decision by vm.decisionIsConfirmed
    LaunchedEffect(decision) { if (decision) navigate()  }

    val withDataSort by vm.withSort
    val isShowingCode by vm.isCodeShowed

    val colorHorizontalDiv = Color(0xFFffcc00).copy(alpha = 0.5f)
    val allIsReady = vm.everythingIsReady()

    val cond = isShowingCode || allIsReady

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .blur(if (cond) 12.dp else 0.dp)
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

        CardToShowCode(vm)

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
    val algorithms = remember { SearchsAlgorithms.getNames() }
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
            fontFamily = MyFont.spaceGroteskRegular,
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            lineHeight =  ResponsiveLineHeight.headline()
        )

        if (vm.disableButton.value) {
            Text(
                text = "¡Este algoritmo requiere un ordenamiento previo obligatorio!",
                color = Color.Red,
                textAlign = TextAlign.Start,
                fontSize = ResponsiveText.body(),
                lineHeight =  ResponsiveLineHeight.body()
            )
        }

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

        Spacer(modifier = Modifier.height(15.dp))

        vm.searchAlgo.value?.let {

            SearchsAlgorithms.findByName(it)?.let { configs ->

                Text(
                    text = "Complejidad",
                    fontSize = ResponsiveText.body(),
                    lineHeight = ResponsiveLineHeight.body(),
                    color = Color.Gray,
                    fontFamily = MyFont.spaceGroteskRegular
                )

                Text(
                    text = "* Mejor Caso: ${configs.bestCase}\n" +
                            "* Promedio: ${configs.mean}\n" +
                            "* Peor Caso: ${configs.worseCase}\n" +
                            "* Espacio: ${configs.space}\n\n" +
                            "* Estrategia: ${configs.strategy}",
                    fontSize = ResponsiveText.caption(),
                    lineHeight = ResponsiveLineHeight.caption(),
                    color = Color.White,
                    fontFamily = MyFont.jetBrainsMonoMedium
                )

                Spacer(modifier = Modifier.height(15.dp))

                ButtonForShowCode(vm, configs.code)
            }
        }
    }
}


@Composable private fun QuestionDoYouWantToOrder(vm: SoloArenaVM) {

    val withSortdBro by vm.withSort
    val shape = RoundedCornerShape(20.dp)

    val fromHeigth = 40.dp
    val fromWidth = 100.dp

    // Pongo un LaunchedEffect para que no se este repitiendo cada rato
    // esta linea de código, unicamente cuando withSortdBro
    // Su funcionamiento es el siguiente: cuando withSortdBro estaba en
    // (true) y vuelva a (false) entonces el text donde se pone el algoritmo
    // de ordenamiento debe ser (null).
    LaunchedEffect(withSortdBro) { if (!withSortdBro) vm.setSortAlgo(null) }

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
            color = Color(0xFFFFCC00),
            textAlign = TextAlign.Start,
            fontFamily = MyFont.spaceGroteskRegular,
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

            Button(
                onClick = { vm.setWithSortToggle() },
                modifier = Modifier
                    .size(fromHeigth)
                    .clip(shape),
                shape = shape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD52E),
                    disabledContainerColor = Color(0xFFFFD52E)
                ),
                enabled = !vm.disableButton.value
            ) {

                Icon(
                    imageVector = if (withSortdBro) Icons.Default.CheckCircle
                    else Icons.Default.Cancel,
                    contentDescription = null,
                    tint = Color.Black
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

    val algorithms = remember { SortAlgorithms.getNames() }

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
            color = Color(0xFFFCEEBB),
            textAlign = TextAlign.Start,
            fontSize = ResponsiveText.headline(),
            fontFamily = MyFont.spaceGroteskRegular,
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
                        .background(Color(0xFFFCEEBB))
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

            val cond = vm.sortAlgo.value.isNullOrEmpty()

            Image(
                imageVector = if (cond) Icons.Default.Cancel else Icons.Default.CheckCircle,
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (cond) Color.Red else Color.Green)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        vm.sortAlgo.value?.let {

            SortAlgorithms.findByName(it)?.let { configs ->

                Text(
                    text = "Complejidad",
                    fontSize = ResponsiveText.body(),
                    lineHeight = ResponsiveLineHeight.body(),
                    color = Color.Gray,
                    fontFamily = MyFont.spaceGroteskRegular
                )

                Text(
                    text = "* Mejor Caso: ${configs.bestCase}\n" +
                            "* Promedio: ${configs.mean}\n" +
                            "* Peor Caso: ${configs.worseCase}\n" +
                            "* Espacio: ${configs.space}\n\n" +
                            "* Estrategia: ${configs.strategy}",
                    fontSize = ResponsiveText.caption(),
                    lineHeight = ResponsiveLineHeight.caption(),
                    color = Color.White,
                    fontFamily = MyFont.jetBrainsMonoMedium
                )

                Spacer(modifier = Modifier.height(15.dp))

                ButtonForShowCode(vm, configs.code)
            }
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
            text = "¿Cuantos dígitos tendra el número a buscar?",
            fontFamily = MyFont.spaceGroteskRegular,
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
                text = "Lista: $rangeDigits",
                color = Color.White,
                textAlign = TextAlign.Start,
                fontFamily = MyFont.jetBrainsMonoMedium,
                fontWeight = FontWeight.Normal,
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
            fontFamily = MyFont.spaceGroteskRegular,
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

    val focus = LocalFocusManager.current

    // BLOQUEADOR + MODAL
    if (vm.everythingIsReady()) {

        focus.clearFocus()

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
                colors = CardDefaults.cardColors(containerColor = Color(0xFF151515))
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

                    Text(
                        text = "Search:\n${vm.searchAlgo.value}\n\n" +
                                "Sort:\n${vm.sortAlgo.value}\n\n" +
                                "Digits:\n${vm.datasetDigits.value}\n\n" +
                                "Number:\n${vm.selectedNumber.value}",
                        color = Color.White,
                        fontFamily = MyFont.jetBrainsMonoMedium,
                        fontSize = ResponsiveText.caption(),
                        lineHeight = ResponsiveLineHeight.caption()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Button(
                            onClick = {
                                vm.setDesicion(true)
                            }
                        ) {

                            Text(
                                text = "Sí",
                                fontSize = ResponsiveText.button(),
                                fontFamily = MyFont.jetBrainsMonoMedium
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                vm.setDesicion(false)
                                vm.cleanAllParams()
                            }
                        ) {

                            Text("No")
                        }
                    }
                }
            }
        }
    }
}


/**
 * Ademas esta función recibirá como parametro el código que proviene
 * de la dataclass (AlgorithmsAtts) que probiene de los Enums bien sea,
 * [SearchsAlgorithms] o [SortAlgorithms].
 * */
@Composable private fun CardToShowCode(
    vm: SoloArenaVM
) {

    val code by vm.code
    val brush = animatedGradientText()

    // Estas variables se utilizan para hacer zoom y desplazarme en la pantalla
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val transformableState = rememberTransformableState { zoomChange, panChange, _ ->
        scale = (scale * zoomChange).coerceIn(1f, 3f)
        offset += panChange
    }

    val styledCode = deployMyOwnEditorTextColor(code)

    // BLOQUEADOR + MODAL
    if (vm.isCodeShowed.value) {

        // Captura todos los clicks
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.35f))
                .transformable(transformableState)
                .padding(20.dp)
                .pointerInput(Unit) {

                    // ¡OJO! esto debe ir primero antes de ese carechimba
                    // awaitPointerEventScope {}, o sino no se ejecuta ni nada!!!.
                    // + Esto me sirve para resetear el Zoom y desplazamiento de pantalla
                    detectTapGestures(
                        onDoubleTap = {
                            scale = 1f
                            offset = Offset.Zero
                        }
                    )

                    awaitPointerEventScope {
                        while (true) {
                            awaitPointerEvent()
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, brush = brush),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF151515))
            ) {

                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    )  {

                        Button (
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            ),
                            onClick = {
                                vm.setIsCodeShowed(false)
                                vm.setCode("")
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = styledCode,
                        fontSize = ResponsiveText.minimal(),
                        lineHeight = ResponsiveLineHeight.minimal(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = MyFont.jetBrainsMonoMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                            }
                    )
                }
            }
        }
    }
}


/**
 * Esto se muestra si las variables _searchAlgo ó _sortAlgo son
 * diferentes de nulo.
 * */
@Composable private fun ButtonForShowCode(
    vm: SoloArenaVM,
    code: String
) {

    val color = animatedGradientText()

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .clickable {
                vm.setCode(code)
                vm.setIsCodeShowed(true)
            }
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {

        Text(
            text = "Code",
            fontSize = ResponsiveText.body(),
            lineHeight = ResponsiveLineHeight.body(),
            fontWeight = FontWeight.ExtraBold,
        )

        Icon(
            painter = painterResource(id = R.drawable.terminalv1),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            brush = color,
                            // Aquí en lo último que un chimba con:
                            // .Plus
                            // .Screen
                            blendMode = BlendMode.Plus
                        )
                    }
                }
        )
    }
}


@Composable private fun animatedGradientText(): Brush {

    val infiniteTransition = rememberInfiniteTransition(
        label = ""
    )

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = -900f,
        targetValue = 900f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 6000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    return Brush.linearGradient(
        colors = listOf(
            Color.Black,
            Color.Blue,
            Color.Red,
            Color.Magenta,
            Color.Blue,
            Color.Red,
            Color.Magenta,
            Color.Black
        ),
        start = Offset(animatedOffset, 0f),
        end = Offset(animatedOffset + 900f, 0f)
    )
}


@Composable private fun deployMyOwnEditorTextColor(code: String): AnnotatedString {

    return remember(code) {

        buildAnnotatedString {

            code.split(" ").forEach { word ->

                when (word) {

                    "def", "return" -> {
                        withStyle(
                            style = SpanStyle(color = Color(0xFF569CD6))
                        ) { append("$word ") }
                    }

                    "for", "while" -> {
                        withStyle(
                            style = SpanStyle(color = Color(0xFFE91E63))
                        ) { append("$word ") }
                    }

                    "if", "elif", "else:", "in", "and", "or" -> {
                        withStyle(
                            style = SpanStyle(color = Color(0xFF00BCD4))
                        ) { append("$word ") }
                    }

                    "break", "import" -> {
                        withStyle(
                            style = SpanStyle(color = Color(0xFF673AB7))
                        ) { append("$word ") }
                    }

                    "=", "==", "!=", "+", "/", "//", "-", "*", "<", "<=", ">", ">=", "-=", "+=","//=" -> {
                        withStyle(
                            style = SpanStyle(color = Color(0xFFFF9800))
                        ) { append("$word ") }
                    }

                    else -> {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFFFECA6)
                            )
                        ) {
                            append("$word ")
                        }
                    }
                }
            }
        }
    }
}