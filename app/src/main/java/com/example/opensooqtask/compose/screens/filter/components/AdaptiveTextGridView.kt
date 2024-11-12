import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opensooqtask.ui.theme.DarkGrey


@Composable
fun AdaptiveSelectGrid(textList: List<String>) {
    DynamicTextRows(textList)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DynamicTextRows(
        listOf(
            "Any",
            "Air Conditioning long lomg lomg asd asd asd",
            "Heating",
            "Balcony",
            "Elevator",
            "Garden",
            "Garage/Parking",
            "Maid Room",
            "Laundry Room",
            "Nearby Facilities",
            "Security",
            "Built in Wardrobes",
            "Built in Wardrobes",
            "Built in Wardrobes",
            "Swimming Pool",
            "Solar Panels",
            "Doublepane Windows"
        )
    )
}

@Composable
fun DynamicTextRows(textList: List<String>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current
    val dynamicList = textList.toMutableList()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (text in textList) {
            if (dynamicList.isNotEmpty())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (dynamicList.size > 2) {
                        if (textWidthInDp(dynamicList[0], density) +
                            textWidthInDp(dynamicList[1], density) +
                            textWidthInDp(dynamicList[2], density) <
                            screenWidth * 0.7f
                        ) {
                            for (index in dynamicList.take(3).indices) {
                                if (index !== 1)
                                    selectItem(dynamicList[index], Modifier.weight(1.0f))
                                else
                                    selectItem(dynamicList[index], Modifier, horizontalPadding = 20)
                            }
                            repeat(3) { dynamicList.removeAt(0) }
                        } else if (textWidthInDp(dynamicList[0], density) +
                            textWidthInDp(dynamicList[1], density) <
                            screenWidth * 0.7f
                        ) {
                            for (index in dynamicList.take(2).indices) {
                                selectItem(dynamicList[index], Modifier.weight(1.0f))
                            }
                            repeat(2) { dynamicList.removeAt(0) }
                        } else {
                            selectItem(dynamicList[0], Modifier.weight(1.0f))
                            dynamicList.removeAt(0)
                        }
                    } else if (dynamicList.size > 1) {
                        if (textWidthInDp(dynamicList[0], density) +
                            textWidthInDp(dynamicList[1], density) <
                            screenWidth * 0.9f
                        ) {
                            for (index in dynamicList.take(2).indices) {
                                selectItem(dynamicList[index], Modifier.weight(1.0f))
                            }
                            repeat(2) { dynamicList.removeAt(0) }
                        } else {
                            selectItem(dynamicList[0], Modifier.weight(1.0f))
                            dynamicList.removeAt(0)
                        }
                    } else if (dynamicList.isNotEmpty()) {
                        selectItem(dynamicList[0], Modifier.weight(1.0f))
                        dynamicList.removeAt(0)
                    }
                } else break
        }
    }
}

@Composable
private fun textWidthInDp(text: String, density: Density): Dp {
    val textMeasurer = rememberTextMeasurer()
    val textStyle = TextStyle(fontSize = 16.sp)
    val textLayoutResult = textMeasurer.measure(text = text, style = textStyle)
    val textWidthPx = textLayoutResult.size.width.toFloat()
    return with(density) { textWidthPx.toDp() }
}

@Composable
private fun selectItem(text: String, modifier: Modifier = Modifier, horizontalPadding: Int = 0) {
    Text(
        text = text,
        modifier = modifier
            .border(1.dp / 2, DarkGrey)
            .padding(horizontalPadding.dp, 10.dp),
        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(500), color = DarkGrey),
        textAlign = TextAlign.Center,
    )
}




