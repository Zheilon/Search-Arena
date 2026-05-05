package com.zhei.search_arena.feature_inital_profile.presentation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhei.search_arena.core.common.ui.DEVICE_BOTTOM_PADDING
import com.zhei.search_arena.core.common.ui.HEIGHT_DP_DEVICE
import com.zhei.search_arena.core.common.ui.UiActions


@Preview
@Composable private fun prev() {
    SetInitProfileUI()
}


@Composable fun SetInitProfileUI(
    createProfileVM: CreateProfileVM = viewModel()
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFA7B0FF))
            .padding(bottom = DEVICE_BOTTOM_PADDING()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        item { UpperPart() }

        item { Spacer(modifier = Modifier.height(25.dp)) }

        item { GetPlayerName(vm = createProfileVM) }

        item { Spacer(modifier = Modifier.height(25.dp)) }

        item { ButtonToSavePlayer(vm = createProfileVM) }
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
            text = "Ingresa tu nombre de ( Jugador )",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
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
        value = vm.text.value,
        onValueChange = { vm.setText(it) },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(30.dp)),
        colors = TextFieldDefaults.colors(
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
    vm: CreateProfileVM
) {

    val shape = RoundedCornerShape(15.dp)

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
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                disabledContainerColor = Color.Black
            ),
            shape = shape,
            onClick = {
                UiActions().safeClick(lastClick) {
                    lastClick = it // Despues de esta linea se codifica!
                    /* Lógica */
                    vm.send()
                }
            }
        ) {

            Text(
                text = "¡Ingresa!",
                color = Color.Black,
            )
        }
    }
}
