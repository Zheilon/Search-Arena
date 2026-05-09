package com.zhei.search_arena.features.create_profile.presentation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FaceRetouchingNatural
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.search_arena.core.MyFont
import com.zhei.search_arena.core.common.ui.DEVICE_BOTTOM_PADDING
import com.zhei.search_arena.core.common.ui.HEIGHT_DP_DEVICE
import com.zhei.search_arena.core.common.ui.UiActions
import com.zhei.search_arena.core.di.LocalDeps


@Composable fun CreateProfileUI(
    createProfileVM: CreateProfileVM = viewModel(factory = LocalDeps.current.depSA2),
    navigate: () -> Unit
) {
    val focus = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFA7B0FF))
            .padding(bottom = DEVICE_BOTTOM_PADDING())
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focus.clearFocus() }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        item { UpperPart() }

        item { Spacer(modifier = Modifier.height(25.dp)) }

        item { GetPlayerName(vm = createProfileVM) }

        item { Spacer(modifier = Modifier.height(25.dp)) }

        item { ButtonToSavePlayer(vm = createProfileVM, navigate = { navigate() }) }
    }
}

/**
 * Este Box() {}, será el componente principal que irá
 * en la parte superior de la escena. Despues de él, seguirá lo demás.
 *
 * Notas:
 * 1). Se utilizo .statusBarsPadding(), más idiomático y practico.
 *  Esto reemplaza la solución .padding(top=DEVICE_TOP_PADDING())
 * */
@Composable private fun UpperPart() {

    val roundShape = RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp)

    // Componente principal
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(HEIGHT_DP_DEVICE() / 2)
            .shadow(elevation = 8.dp, shape = roundShape)
            .background(color = Color(0xFFffffff), shape = roundShape)
            .statusBarsPadding()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleIntoBox()
    }
}


@Composable private fun TitleIntoBox() {

    // Notas:  Se deja el fontsize que tiene por default el dispositivo.
    Text(
        text = "Search\nArena",
        color = Color(0xFF000000),
        fontFamily = MyFont.jetBrainsMonoMedium,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 35.sp,
        lineHeight = 35.sp
    )
}


/**
 * Nota de codificación:
 * Utilizar un color en los bordes del componente principal
 * permite apreciar más como se estan adaptando sus componentes interiores
 * */
@Composable private fun GetPlayerName(
    vm: CreateProfileVM
) {

    // Esta será la columna principal, dentro de ella estará
    // toda la codificación pertinente.
    Column (
        modifier = Modifier
            .fillMaxWidth()
            //.border(width = 1.dp, color = Color.Red)
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Ingresa tu nombre de (Jugador)",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MyFont.jetBrainsMonoMedium
        )

        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                imageVector = Icons.Default.FaceRetouchingNatural,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                colorFilter = ColorFilter.tint(color = Color.Yellow)
            )

            Spacer(modifier = Modifier.width(10.dp))

            TextFieldForGetPlayerName(vm = vm)
        }
    }
}


@Composable private fun TextFieldForGetPlayerName(
    vm: CreateProfileVM
) {

    TextField(
        value = vm.name.value,
        onValueChange = { vm.setText(it) },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(30.dp)),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}


@Composable private fun ButtonToSavePlayer(
    vm: CreateProfileVM,
    navigate: () -> Unit
) {

    val succes by vm.success
    LaunchedEffect(succes) { if (succes) navigate() }

    val focusCursor = LocalFocusManager.current
    val shape = RoundedCornerShape(15.dp)

    var buttonPressed by remember { mutableStateOf(false) }

    // val inteactionSource = remember { MutableInteractionSource() }
    // val isPressed by inteactionSource.collectIsPressedAsState()
    // val background = if (isPressed) Color.Yellow else Color.Transparent

    // Esta variable se utiliza para evitar que se generen multiples
    // eventos, producidos por multiples interacciones en el botón.
    var lastClick = 0L

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.buttonColors(
                contentColor = if (!buttonPressed) Color.Black else Color.White,
                containerColor = if (!buttonPressed) Color.Yellow else Color.Red,
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.White
            ),
            shape = shape,
            onClick = {
                UiActions().safeClick(lastClick) {
                    lastClick = it // Despues de esta linea se codifica!
                    /* Lógica */
                    if (vm.name.value.isNotEmpty()) buttonPressed = !buttonPressed
                    focusCursor.clearFocus()
                    vm.send()
                }
            },
            enabled = true
        ) {

            Text(
                text = if (!buttonPressed) "¡Guardar!" else "Guardando ...",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = MyFont.jetBrainsMonoMedium
            )
        }
    }
}
