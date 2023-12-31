package com.hussein.constraintlayoutjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.hussein.constraintlayoutjetpackcompose.ui.theme.ConstraintLayoutJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutJetPackComposeTheme {
                val constraints = ConstraintSet{
                    val greenBox = createRefFor("greenBoxId")
                    val redBox = createRefFor("redBoxId")
                    //This code make box to 50% from top screen
                    val guideLine = createGuidelineFromTop(0.5f)
                 /*   constrain(greenBox){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        //end.linkTo(parent.end)
                        //bottom.linkTo(parent.bottom)
                        width = Dimension.value(100.dp)
                        height = Dimension.value(100.dp)
                    }*/
                    constrain(greenBox){
                        top.linkTo(guideLine)
                        start.linkTo(parent.start)
                        //end.linkTo(parent.end)
                        //bottom.linkTo(parent.bottom)
                        width = Dimension.value(100.dp)
                        height = Dimension.value(100.dp)
                    }
                    constrain(redBox ){
                        top.linkTo(parent.top)
                        start.linkTo(greenBox.end) // this mean show redbox next to greenbos
                        end.linkTo(parent.end)
                        //bottom.linkTo(parent.bottom)
                        width = Dimension.value(100.dp)
                        height = Dimension.value(100.dp)
                    }
                    //This chain code to make them together in center
                    createHorizontalChain(greenBox,redBox, chainStyle = ChainStyle.Packed)
                }

                ConstraintLayout(constraintSet = constraints , modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.background(Color.Green).layoutId("greenBoxId"))//same Id above with greenbox
                    Box(modifier = Modifier.background(Color.Red).layoutId("redBoxId"))//same Id above with redbox
                }
        }
    }
  }
}