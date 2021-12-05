package com.rozatorii_bulbucasi.savewaste.presentation.ui.maps.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.presentation.theme.Green400
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WasteCategoryToggle(
    wasteCategory: WasteCategory,
    selectedCategory: String,
    onClick: (Int) -> Unit,
    color: Color
) {
    val categoryName = stringResource(id = wasteCategory.nameId)

    val isChecked = selectedCategory == categoryName

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(wasteCategory.nameId)
            }
            .padding(
                horizontal = 20.dp, vertical = 20.dp
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (iconRef, textRef, checkedRef) = createRefs()

            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(color)
                    .constrainAs(iconRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = categoryName,
                modifier = Modifier.constrainAs(textRef) {
                    start.linkTo(iconRef.end, margin = 10.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )

            AnimatedVisibility(
                visible = isChecked,
                modifier = Modifier.constrainAs(checkedRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Icon(
                    Icons.Rounded.Done,
                    contentDescription = null,
                    tint = Green400,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun WasteCategoryTogglePreview() {
    SaveWasteTheme {
        WasteCategoryToggle(
            wasteCategory = WasteCategory(),
            selectedCategory = "",
            {},
            color = Green400
        )
    }
}