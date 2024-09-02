package com.hello.expensetrackerbyshreyansh.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hello.expensetrackerbyshreyansh.ui.theme.GreenSplash
import com.hello.expensetrackerbyshreyansh.ui.theme.InterFont
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenSplash)
    ) {
        val (mono) = createRefs()
        Text(
            text = "mono",
            fontFamily = InterFont,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 48.sp,
            modifier = Modifier.constrainAs(mono){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("/home")
    }
}

@Preview
@Composable
private fun SplashPreview() {
    SplashScreen(navController = rememberNavController())
}