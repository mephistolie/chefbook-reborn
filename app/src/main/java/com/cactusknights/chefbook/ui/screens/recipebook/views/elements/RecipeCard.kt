package com.cactusknights.chefbook.ui.screens.recipebook.views.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cactusknights.chefbook.R
import com.cactusknights.chefbook.common.Utils
import com.cactusknights.chefbook.core.ui.RecipeEncryptionProvider
import com.cactusknights.chefbook.domain.entities.recipe.RecipeInfo
import com.cactusknights.chefbook.domain.entities.recipe.encryption.EncryptionState
import com.cactusknights.chefbook.ui.themes.ChefBookTheme
import com.cactusknights.chefbook.ui.themes.Red
import com.cactusknights.chefbook.ui.themes.Shapes
import com.cactusknights.chefbook.ui.views.images.EncryptedImage
import com.mephistolie.compost.extensions.Shading
import com.mephistolie.compost.modifiers.scalingClickable

@Composable
fun RecipeCard(
    recipe: RecipeInfo,
    modifier: Modifier = Modifier,
    onRecipeClick: (RecipeInfo) -> Unit,
) {
    val context = LocalContext.current

    val colors = ChefBookTheme.colors
    val typography = ChefBookTheme.typography

    val pressed = remember { mutableStateOf(false) }

    RecipeEncryptionProvider(recipe.encryptionState) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .scalingClickable(pressed) { onRecipeClick(recipe) }
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                EncryptedImage(
                    data = recipe.preview ?: R.drawable.ic_broccy,
                    placeholder = painterResource(R.drawable.ic_broccy),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colors.backgroundSecondary
                        )
                )
                if (recipe.isFavourite) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x00000000),
                                        Color(0x00000000),
                                        Color(0x33000000)
                                    ),
                                    start = Offset(0F, Float.POSITIVE_INFINITY),
                                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                                ),
                                shape = Shapes.RoundedCornerShape12
                            ),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_favourite),
                            modifier = Modifier
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Red.copy(alpha = 0.4F),
                                            Red.copy(alpha = 0.1F),
                                            Color.Transparent,
                                        ),
                                    ),
                                )
                                .padding(12.dp)
                                .size(24.dp),
                            contentDescription = "Favourite",
                            tint = Red
                        )
                    }
                }
                Shading(pressed.value)
            }
            Text(
                text = recipe.name,
                style = typography.body1,
                maxLines = 2,
                color = colors.foregroundPrimary,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (recipe.encryptionState !is EncryptionState.Standard) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        modifier = Modifier.size(12.dp),
                        contentDescription = "Encrypted",
                        tint = colors.foregroundSecondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                if (recipe.time != null) {
                    Text(
                        text = Utils.minutesToTimeString(recipe.time, context.resources),
                        style = typography.subhead1,
                        color = colors.foregroundSecondary
                    )
                }
                if (recipe.calories != null) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${recipe.calories} ${stringResource(R.string.common_general_kcal)}",
                        style = typography.subhead1,
                        color = colors.tintPrimary
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewDetailedRecipeCard() {
    ThemedRecipeCard(
        recipe = RecipeInfo(
            id = 0,
            name = "Cupcakes",
            calories = 800,
            time = 60,
            isFavourite = true,
            encryptionState = EncryptionState.Encrypted
        ),
        isDarkTheme = false
    )
}

@Composable
@Preview
fun PreviewMinimalRecipeCard() {
    ThemedRecipeCard(
        recipe = RecipeInfo(
            id = 0,
            name = "Cupcakes",
            calories = 0,
            time = 60,
        ),
        isDarkTheme = true
    )
}

@Composable
private fun ThemedRecipeCard(
    recipe: RecipeInfo,
    isDarkTheme: Boolean
) {
    ChefBookTheme(darkTheme = isDarkTheme) {
        Surface(
            color = ChefBookTheme.colors.backgroundPrimary
        ) {
            RecipeCard(
                recipe = recipe,
                onRecipeClick = {},
            )
        }
    }
}