package com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme

@ExperimentalMaterialApi
@Composable
fun OpenableInfoCard(
    question: String,
    answer: String
) {
    var isOpen by remember {
        mutableStateOf(false)
    }

    val arrowRotation by animateFloatAsState(targetValue = if (isOpen) 180f else 0f)

    Card(
        onClick = { isOpen = !isOpen },
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = question,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(9f)
                )

                Icon(
                    Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(arrowRotation)
                        .weight(1f)
                )
            }

            if (isOpen) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = answer,
                    color = Color.Black.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun OpenAbleCardPreview() {
    SaveWasteTheme {
        OpenableInfoCard(
            question = stringResource(id = R.string.why_you_should_recycle_question),
            answer = stringResource(id = R.string.why_you_should_recycle_answer)
        )
    }
}