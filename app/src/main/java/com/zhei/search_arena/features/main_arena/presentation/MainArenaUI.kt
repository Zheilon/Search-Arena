package com.zhei.search_arena.features.main_arena.presentation
import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhei.search_arena.R
import com.zhei.search_arena.core.MyFont
import com.zhei.search_arena.core.common.ui.DEVICE_BOTTOM_PADDING
import com.zhei.search_arena.core.common.ui.WIDTH_DP_DEVICE

private val PADDING_UPPER_PART_FIRST = 20.dp
private val PADDING_UPPER_PART_SECOND = 20.dp

/**
 * Es composable debe ser publico. El resto deben ser privados
 */
@Composable fun MainArenaUI(
    vm: MainArenaVM,
    onSelectedMode: (String) -> Unit
) {

    LaunchedEffect(Unit) { vm.lazyInit() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF000412)) //0xFF000412
            .padding(bottom = DEVICE_BOTTOM_PADDING()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UpperPart(vm)

        Spacer(modifier = Modifier.weight(1f))

        BottomPart(
            onSelectedMode = { onSelectedMode(it) }
        )
    }
}

@SuppressLint("Range")
@Composable private fun UpperPart(vm: MainArenaVM) {

    val rounded = RoundedCornerShape(15.dp)
    var size by remember { mutableIntStateOf(0) }
    val heigthDp = with(LocalDensity.current) { size.toDp() + 38.dp }

    // ¿Por qué un Box, webon?
    // Porque un Box() me permite apilar.
    // Este primer Box(), va a ser un box tipo contenedor.

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF000412), shape = rounded)
            .statusBarsPadding()
            .padding(PADDING_UPPER_PART_SECOND)
            .onSizeChanged { size = it.height }
    ) {

        Column {

            TitleAndSubtitle(vm)

            Spacer(modifier = Modifier.height(15.dp))

            Stats(vm)
        }
    }
}


@Composable private fun TitleAndSubtitle(
    vm: MainArenaVM
) {

    val color = animatedGradientText()

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = vm.username.value,
            style = TextStyle(
                fontFamily = MyFont.jetBrainsMonoMedium,
                brush = color,
                fontWeight = FontWeight.Bold
            ),
        )
        
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.bolt),
                contentDescription = "bolt",
                colorFilter = ColorFilter.tint(Color.Yellow),
                modifier = Modifier.size(25.dp)
            )

            Text(
                text = "Level: ${vm.level.value}",
                fontFamily = MyFont.jetBrainsMonoMedium,
                color = Color.Yellow,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
    }
}


@Composable private fun Stats(vm: MainArenaVM) {

    // Esos 80.dp, es la sumatoria de los paddings anteriores que cotienen
    // end y start.
    // Y luego lo divido entre la cantidad de cards que quiero mostrar.
    val beforePadds = (PADDING_UPPER_PART_FIRST * 2) + (60.dp * 2)
    val finalWidthOfCards = (WIDTH_DP_DEVICE() - beforePadds) / 2

    LazyRow (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        item {
            CardsForUpperPart(
                "Partidas",
                R.drawable.target_circle,
                colorImg = Color.Cyan,
                vmText = vm.matches.value
            )
        }

        item {
            CardsForUpperPart(
                "Victorias",
                R.drawable.trophy,
                colorImg = Color.Yellow,
                vmText = vm.victories.value
            )
        }
    }
}


@Composable private fun CardsForUpperPart(
    title: String,
    image: Int,
    colorImg: Color,
    colorBorder: Color = Color.Cyan,
    vmText: String
) {

    val shape = RoundedCornerShape(15.dp)

    Row (
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .widthIn(min = 120.dp, max = 200.dp)
            .shadow(elevation = 8.dp, shape = shape)
            .background(color = Color(0xFF14141f).copy(alpha = 0.5f), shape = shape)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorImg),
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        fontSize = 9.sp,
                        color = Color(0xFFe0e0e8),
                        fontFamily = FontFamily.Monospace
                    )
                ) { append(title + "\n") }

                withStyle(
                    style = SpanStyle(
                        fontSize = 11.sp,
                        color = Color.White,
                        fontFamily = MyFont.jetBrainsMonoMedium
                    )
                ) { append(vmText) }
            },
            lineHeight = 15.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFFe0e0e8),
        )
    }
}


@Composable private fun BottomPart(
    onSelectedMode: (String) -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        
        CardsForJoinToPlay(
            text = "Solo",
            keyword = "S",
            color = Color(0xFFD1A700),
            onSelectedMode = { onSelectedMode(it) }
        )
        
        CardsForJoinToPlay(
            text = "Crear Sala",
            keyword = "CS",
            color = Color(0xFFFFCC00),
            onSelectedMode = { onSelectedMode(it) }
        )

        CardsForJoinToPlay(
            text = "Unirse a Sala",
            keyword = "US",
            color = Color(0xFFFFD52E),
            onSelectedMode = { onSelectedMode(it) }
        )
    }
}


@Composable private fun CardsForJoinToPlay(
    text: String,
    keyword: String,
    color: Color,
    onSelectedMode: (String) -> Unit
) {

    val shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)

    Button(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(WIDTH_DP_DEVICE() / 2 + 60.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = color
        ),
        shape = shape,
        contentPadding = PaddingValues(top = 5.dp, bottom = 5.dp),
        onClick = { onSelectedMode(keyword) },
        enabled = true
    ) {

        Text(
            text = text,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = MyFont.jetBrainsMonoMedium
        )
    }
}


@Composable private fun animatedGradientText(): Brush {

    val infiniteTransition = rememberInfiniteTransition(
        label = ""
    )

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = -600f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 5000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    return Brush.linearGradient(
        colors = listOf(
            Color.White,
            Color.Yellow,
            Color.Blue,
            Color.Red,
            Color.Yellow,
            Color.Blue,
            Color.Red,
            Color.White
        ),
        start = Offset(animatedOffset, 0f),
        end = Offset(animatedOffset + 600f, 0f)
    )
}