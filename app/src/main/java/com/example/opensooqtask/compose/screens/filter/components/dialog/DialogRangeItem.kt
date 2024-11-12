package com.example.opensooqtask.compose.screens.filter.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.opensooqtask.BuildConfig
import com.example.opensooqtask.R
import com.example.opensooqtask.domain.model.option.OptionLocalResponse
import com.example.opensooqtask.ui.theme.DarkGrey
import com.example.opensooqtask.ui.theme.MainGreen

@Composable
fun DialogRangeItem(option: OptionLocalResponse) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
    ) {
        Text(option.name, fontSize = 20.sp)
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Next Icon",
            modifier = Modifier
                .padding(0.dp)
                .size(50.dp),
        )
    }
}