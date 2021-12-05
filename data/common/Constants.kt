package com.rozatorii_bulbucasi.savewaste.data.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory

object Constants {

    const val TIMISOARA_RECYCLE_API_BASE_URL = "https://data.primariatm.ro/api/3/action/"

    val allWasteCategoriesTitle = listOf(
        "sticlă",
        "baterii",
        "haine",
        "deșeuri voluminoase",
        "becuri",
        "medicamente",
        "ulei",
        "hârtie",
        "pet",
        "polistiren"
    )

    val allWasteCategories = listOf<WasteCategory>(
        WasteCategory(
            nameId = R.string.glass,
            iconId = R.drawable.ic_glass,
            advatanges = listOf(
                R.string.glass_advantage_1,
                R.string.glass_advantage_2,
                R.string.glass_advantage_3,
                R.string.glass_advantage_4
            )
        ),

        WasteCategory(
            nameId = R.string.batteries,
            iconId = R.drawable.ic_battery,
            advatanges = listOf(
                R.string.batteries_advantage_1,
                R.string.batteries_advantage_2,
                R.string.batteries_advantage_3
            )
        ),

        WasteCategory(
            nameId = R.string.clothes,
            iconId = R.drawable.ic_clothes,
            advatanges = listOf(
                R.string.clothes_advantage_1,
                R.string.clothes_advantage_2,
                R.string.clothes_advantage_3,
                R.string.clothes_advantage_4,
                R.string.clothes_advantage_5,
            )
        ),

        WasteCategory(
            nameId = R.string.furniture,
            iconId = R.drawable.ic_furniture,
            advatanges = listOf(
                R.string.furniture_advantage_1,
                R.string.furniture_advantage_2,
            )
        ),

        WasteCategory(
            nameId = R.string.light_bulbs,
            iconId = R.drawable.ic_light_bulb,
            advatanges = listOf(
                R.string.light_bulbs_advantage_1,
                R.string.light_bulbs_advantage_2,
            )
        ),

        WasteCategory(
            nameId = R.string.meds,
            iconId = R.drawable.ic_medical_pill,
            advatanges = listOf(
                R.string.meds_advantage_1,
                R.string.meds_advantage_2,
            )
        ),


        WasteCategory(
            nameId = R.string.oil,
            iconId = R.drawable.ic_oil,
            advatanges = listOf(
                R.string.oil_advantage_1,
                R.string.oil_advantage_2,
                R.string.oil_advantage_3,
            )
        ),

        WasteCategory(
            nameId = R.string.paper,
            iconId = R.drawable.ic_paper,
            advatanges = listOf(
                R.string.paper_advantage_1,
                R.string.paper_advantage_2,
                R.string.paper_advantage_3,
                R.string.paper_advantage_4,
                R.string.paper_advantage_5,
                R.string.paper_advantage_6,
                R.string.paper_advantage_7,
            )
        ),

        WasteCategory(
            nameId = R.string.plastic,
            iconId = R.drawable.ic_plastic_bottle,
            advatanges = listOf(
                R.string.plastic_advantage_1,
                R.string.plastic_advantage_2,
                R.string.plastic_advantage_3,
                R.string.plastic_advantage_4,
            )
        ),

        WasteCategory(
            nameId = R.string.polystyrene,
            iconId = R.drawable.ic_polystyrene,
            advatanges = listOf(
                R.string.polystyrene_advantage_1,
                R.string.polystyrene_advantage_2,
                R.string.polystyrene_advantage_3,
                R.string.polystyrene_advantage_4,
                R.string.polystyrene_advantage_5,
            )
        )
    )
}