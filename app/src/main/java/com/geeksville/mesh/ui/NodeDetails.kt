package com.geeksville.mesh.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geeksville.mesh.NodeInfo
import com.geeksville.mesh.R
import com.geeksville.mesh.model.RadioConfigViewModel
import com.geeksville.mesh.ui.components.NodeDetailsAppBar
import com.geeksville.mesh.ui.preview.NodeInfoPreviewParameterProvider
import com.geeksville.mesh.ui.theme.AppTheme
import com.geeksville.mesh.util.formatAgo
import dagger.hilt.android.AndroidEntryPoint


/* TODO move to own viewmodel
*   move modules to own files
*   follow details of rssi / snr so it's the same as the weather app (Day)
*   replace battery info with a nicer one
*   fix back / ... buttons or remove them
*   make node details viewable on own device
*   fair / bad etc. not centered.
*   make text -> strings
* */

@AndroidEntryPoint
class NodeDetailsFragment : ScreenFragment("Node Details") {

    private val model: RadioConfigViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        setFragmentResultListener("requestKey") { _, bundle ->
            val destNum = bundle.getInt("destNum")
            model.setDestNum(destNum)
        }

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorAdvancedBackground))
            setContent {
                val node by model.destNode.collectAsStateWithLifecycle()

                AppTheme {
                    NodeDetailsView(node = node)
                }
            }
        }
    }
}

@Composable
fun NodeDetailsView(node: NodeInfo?) {
    Column {
        NodeDetailsAppBar()
        NodeDetails(node = node)
    }

}

@Composable
fun NodeDetails(
    node: NodeInfo?, modifier: Modifier = Modifier
) {
//TODO

    val unknownShortName = stringResource(id = R.string.unknown_node_short_name)
    val unknownLongName = stringResource(id = R.string.unknown_username)
    val lastHeard = node?.lastHeard ?: 0

    Surface() {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = node?.user?.longName ?: unknownLongName,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "TODO",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Role",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                VerticalDivider(Modifier.height(25.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = node?.user?.id ?: "Unknown",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "User ID",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                VerticalDivider(Modifier.height(25.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = node?.user?.hwModelString ?: "Unknown Hardware",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "HW Model",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            HorizontalDivider(
                Modifier.padding(bottom = 10.dp)
            )
            //TODO Add chips here for Trace route / Direct message / Request Position

            Column(
                Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InsightsView()
            }
            Column(
                Modifier.padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Details",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = formatAgo(lastHeard),
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    MapCard(node = node, gpsFormat = 0)
                }
                item {
                    SignalCard(
                        rssi = node?.rssi ?: 0,
                        snr = node?.snr ?: Float.MAX_VALUE,
                        hopsAway = node?.hopsAway ?: 0
                    )
                }
                item {
                    ChUtilCard(node = node)
                }
                item {
                    BatteryCard(node = node)
                }
            }
            Column(Modifier.weight(1f)) {

            }
        }
    }
}


@Composable
fun InsightsView() {
    val hasInsight: Boolean = false
    if (hasInsight) {

        Row(
            Modifier.padding(
                horizontal = 5.dp, vertical = 2.dp
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Outlined.Info, contentDescription = null, Modifier.padding(2.dp)
            )
            Text(
                text = "This node has restarted 17 times in the past 12 hours",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
fun MapCard(node: NodeInfo?, gpsFormat: Int) {
    Card(Modifier.height(100.dp)) {
        Box(
            Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Column(
                Modifier.align(Alignment.Center)
            ) {
                Icon(
                    painterResource(id =R.drawable.map),
                    contentDescription = null,
                    Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                )
                if (node?.position != null) {
                    LinkedCoordinates(
                        position = node.position,
                        format = gpsFormat,
                        nodeName = node.user?.longName ?: "unknownLongName",
                        style = MaterialTheme.typography.labelSmall.toSpanStyle()
                    )
                } else {
                    Text(
                        text = "No GPS Data",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun SignalCard(rssi: Int, snr: Float, hopsAway: Int) {
    val hopsAwayIcon = painterResource(id = R.drawable.hops_away)
    val rssiQuality = when (rssi) {
        in -200..-126 -> "Bad"
        in -125..-116 -> "Fair"
        in -115..0 -> "Good"
        else -> "Unknown"
    }
    val snrQuality = when (snr) {
        in -40f..-15f -> "Bad"
        in -15f..-7f -> "Fair"
        in -7f..20f -> "Good"
        else -> "Unknown"
    }

    val rssiSnrResult = when {
        rssiQuality == "Good" && snrQuality == "Good" -> "Good"
        rssiQuality == "Good" && snrQuality == "Fair" -> "Fair"
        rssiQuality == "Good" && snrQuality == "Bad" -> "Bad"
        rssiQuality == "Fair" && snrQuality == "Good" -> "Fair"
        rssiQuality == "Fair" && snrQuality == "Fair" -> "Fair"
        rssiQuality == "Fair" && snrQuality == "Bad" -> "Bad"
        rssiQuality == "Bad" && snrQuality == "Good" -> "Bad"
        rssiQuality == "Bad" && snrQuality == "Fair" -> "Fair"
        rssiQuality == "Bad" && snrQuality == "Bad" -> "Bad"
        else -> "Unknown"

    }

    val rssiIcon = when (rssiSnrResult) {
        "Bad" -> painterResource(id = R.drawable.signal_cellular_1_bar)
        "Fair" -> painterResource(id = R.drawable.signal_cellular_3_bar)
        "Good" -> painterResource(id = R.drawable.signal_cellular_4_bar)
        else -> painterResource(id = R.drawable.question_mark)
    }
    Card(Modifier.height(100.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            if (hopsAway >= 1) {
                Column(Modifier.align(Alignment.CenterVertically)) {
                    Icon(
                        hopsAwayIcon,
                        contentDescription = null,
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Hops Away: $hopsAway",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
            } else if (rssi != Int.MAX_VALUE) {
                Column(Modifier.align(Alignment.CenterVertically)) {
                    Icon(
                        rssiIcon,
                        contentDescription = null,
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = rssiSnrResult,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "RSSI: ${rssi}dB ",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = "SNR: ${snr}dB",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Column(Modifier.align(Alignment.CenterVertically)) {
                    Icon(
                        painterResource(id = R.drawable.question_mark),
                        contentDescription = null,
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "No Signal Data",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

@Composable
fun ChUtilCard(node: NodeInfo?) {
    Card(Modifier.height(100.dp)) {
        Box(
            Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                if (node?.deviceMetrics?.channelUtilization != null) {
                    Text(
                        text = String.format("Ch Util: %.1f%%", node.deviceMetrics?.channelUtilization),
                        Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = String.format("Air Time: %.1f%%", node.deviceMetrics?.airUtilTx),
                        Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Icon(
                        painterResource(id = R.drawable.question_mark),
                        contentDescription = null,
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "No Ch Util Data",
                        Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }


        }
    }
}

@Composable
fun BatteryCard(node: NodeInfo?) {
    Card(Modifier.height(100.dp)) {
        Box(
            Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
        ) {
            Column(Modifier.align(Alignment.Center)) {
                BatteryInfo(
                    modifier = Modifier, batteryLevel = node?.batteryLevel, voltage = node?.voltage
                )
            }


        }
    }
}

@Preview
@Composable
fun FullNodeDetailsPage() {
    AppTheme {
        val node = NodeInfoPreviewParameterProvider().values.first()
        val thatNodeInfo = NodeInfoPreviewParameterProvider().values.last()
        NodeDetailsView(node = node)
    }

}

@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:pixel_8"
)
fun NodeDetailsPreviewLight() {
    AppTheme {
        val node = NodeInfoPreviewParameterProvider().values.first()
        NodeDetails(
            node = node
        )
    }
}

@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:pixel_8"
)
fun NodeDetailsPreviewDark() {
    AppTheme {
        val node = NodeInfoPreviewParameterProvider().values.first()
        NodeDetails(
            node = node
        )
    }
}