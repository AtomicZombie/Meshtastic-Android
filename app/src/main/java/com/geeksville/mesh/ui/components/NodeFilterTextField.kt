package com.geeksville.mesh.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geeksville.mesh.R
import com.geeksville.mesh.ui.theme.AppTheme

@Composable
fun NodeFilterTextField(
    filterText : String = "",
    onTextChanged : (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 48.dp)
            .background(MaterialTheme.colorScheme.background),
        value = filterText,
        placeholder = {
            Text(
                text = stringResource(id = R.string.node_filter_placeholder),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35F)
            )
        },
        onValueChange = onTextChanged,
        trailingIcon = {
            if (filterText.isNotEmpty()) {
                Icon(
                    rememberCancel(),
                    contentDescription = stringResource(id = R.string.desc_node_filter_clear),
                    modifier = Modifier.clickable { onTextChanged("") }
                )
            }
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Composable
fun rememberCancel(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "cancel",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12.958f, 27.042f)
                quadToRelative(0.375f, 0.375f, 0.917f, 0.375f)
                reflectiveQuadToRelative(0.917f, -0.375f)
                lineTo(20f, 21.833f)
                lineToRelative(5.25f, 5.25f)
                quadToRelative(0.333f, 0.334f, 0.875f, 0.313f)
                quadToRelative(0.542f, -0.021f, 0.917f, -0.354f)
                quadToRelative(0.375f, -0.375f, 0.375f, -0.917f)
                reflectiveQuadToRelative(-0.375f, -0.917f)
                lineTo(21.833f, 20f)
                lineToRelative(5.25f, -5.25f)
                quadToRelative(0.334f, -0.333f, 0.313f, -0.875f)
                quadToRelative(-0.021f, -0.542f, -0.354f, -0.917f)
                quadToRelative(-0.375f, -0.375f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                lineTo(20f, 18.167f)
                lineToRelative(-5.25f, -5.25f)
                quadToRelative(-0.333f, -0.334f, -0.875f, -0.313f)
                quadToRelative(-0.542f, 0.021f, -0.917f, 0.354f)
                quadToRelative(-0.375f, 0.375f, -0.375f, 0.917f)
                reflectiveQuadToRelative(0.375f, 0.917f)
                lineTo(18.167f, 20f)
                lineToRelative(-5.25f, 5.25f)
                quadToRelative(-0.334f, 0.333f, -0.313f, 0.875f)
                quadToRelative(0.021f, 0.542f, 0.354f, 0.917f)
                close()
                moveTo(20f, 36.375f)
                quadToRelative(-3.458f, 0f, -6.458f, -1.25f)
                reflectiveQuadToRelative(-5.209f, -3.458f)
                quadToRelative(-2.208f, -2.209f, -3.458f, -5.209f)
                quadToRelative(-1.25f, -3f, -1.25f, -6.458f)
                reflectiveQuadToRelative(1.25f, -6.437f)
                quadToRelative(1.25f, -2.98f, 3.458f, -5.188f)
                quadToRelative(2.209f, -2.208f, 5.209f, -3.479f)
                quadToRelative(3f, -1.271f, 6.458f, -1.271f)
                reflectiveQuadToRelative(6.438f, 1.271f)
                quadToRelative(2.979f, 1.271f, 5.187f, 3.479f)
                reflectiveQuadToRelative(3.479f, 5.188f)
                quadToRelative(1.271f, 2.979f, 1.271f, 6.437f)
                reflectiveQuadToRelative(-1.271f, 6.458f)
                quadToRelative(-1.271f, 3f, -3.479f, 5.209f)
                quadToRelative(-2.208f, 2.208f, -5.187f, 3.458f)
                quadToRelative(-2.98f, 1.25f, -6.438f, 1.25f)
                close()
                moveTo(20f, 20f)
                close()
                moveToRelative(0f, 13.75f)
                quadToRelative(5.667f, 0f, 9.708f, -4.042f)
                quadTo(33.75f, 25.667f, 33.75f, 20f)
                reflectiveQuadToRelative(-4.042f, -9.708f)
                quadTo(25.667f, 6.25f, 20f, 6.25f)
                reflectiveQuadToRelative(-9.708f, 4.042f)
                quadTo(6.25f, 14.333f, 6.25f, 20f)
                reflectiveQuadToRelative(4.042f, 9.708f)
                quadTo(14.333f, 33.75f, 20f, 33.75f)
                close()
            }
        }.build()
    }
}

@Composable
@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
fun NodeFilterTextFieldPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NodeFilterTextField(
                filterText = "Filter text",
                onTextChanged = { }
            )
        }
    }
}