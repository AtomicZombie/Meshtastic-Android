package com.geeksville.mesh.ui

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineStops
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.SignalCellularAlt2Bar
import androidx.compose.material.icons.rounded.AirlineStops
import androidx.compose.material.icons.rounded.WifiOff
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geeksville.mesh.NodeInfo
import com.geeksville.mesh.R
import com.geeksville.mesh.ui.preview.NodeInfoPreviewParameterProvider
import com.geeksville.mesh.ui.theme.AppTheme
import com.geeksville.mesh.util.formatAgo

@Composable
fun NodeDetails(
    thisNodeInfo: NodeInfo?,
    thatNodeInfo: NodeInfo, modifier: Modifier = Modifier
) {
//TODO

    val unknownShortName = stringResource(id = R.string.unknown_node_short_name)
    val unknownLongName = stringResource(id = R.string.unknown_username)

    Surface {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = formatAgo(thatNodeInfo.lastHeard),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = thatNodeInfo.user?.longName ?: unknownShortName,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "!db94Msj",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = thatNodeInfo.user?.hwModelString ?: "Unknown",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
BatteryInfo(modifier = Modifier,batteryLevel = thatNodeInfo.batteryLevel, voltage = thatNodeInfo.voltage)
                    Text(
                        text = "Battery",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                VerticalDivider(Modifier.height(25.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Client",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Role",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            HorizontalDivider(
                Modifier.padding(5.dp)
            )

            Column(
                Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InsightsView()
            }
            Column(
                Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Details",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelLarge
                )


            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item { MapView() }
                item { SignalView() }
            }

        }

    }
}


@Composable
fun InsightsView() {
    Card() {
        Row(
            Modifier.padding(
                horizontal = 5.dp, vertical = 2.dp
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Info,
                //tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
            Text(

                text = "This node has restarted 17 times in the past 12 hours.",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MapView() {
    Card() {
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                Modifier.align(Alignment.Center)
            ) {
                Icon(
                    Icons.Default.Map,
                    contentDescription = null,
                    Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "3.6KM NW of you",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun SignalView() {
    Card() {
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                Modifier.align(Alignment.Center)
            ) {
                Icon(
                    Icons.Default.SignalCellularAlt,
                    contentDescription = null,
                    Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Signal Good",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
@Preview
fun NodeDetailsPreview1() {
    AppTheme {
        val thisNodeInfo = NodeInfoPreviewParameterProvider().values.first()

        val thatNodeInfo = NodeInfoPreviewParameterProvider().values.last()
        NodeDetails(
            thatNodeInfo = thatNodeInfo,
            thisNodeInfo = thisNodeInfo
        )
    }
}