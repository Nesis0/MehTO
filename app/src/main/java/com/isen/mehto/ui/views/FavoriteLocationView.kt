package com.isen.mehto.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isen.mehto.R
import com.isen.mehto.data.models.Position
import com.isen.mehto.ui.theme.DashedDivider
import com.isen.mehto.ui.theme.DoubleBorderContainer
import com.isen.mehto.viewmodels.FavoriteLocationViewModel
import org.koin.androidx.compose.get
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import kotlin.math.roundToInt


@Composable
fun FavoriteLocationScreen() {
    val viewModel = viewModel<FavoriteLocationViewModel>(
        factory = FavoriteLocationViewModel.ViewModelFactory(get(), get())
    )

    LaunchedEffect(viewModel.isAddFavoriteView.value) {
        viewModel.loadFavoriteLocations()
    }

    if (viewModel.isAddFavoriteView.value)
        AddFavoriteLocationScreen(viewModel)
    else
        ManageFavoriteLocation(viewModel)
}

@Composable
fun ManageFavoriteLocation(viewModel: FavoriteLocationViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text("Favorite locations", fontSize = 24.sp)

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = viewModel.isAllLocationsSelected(),
                    onCheckedChange = { viewModel.selectAllItems() }
                )

                TextButton(
                    onClick = { viewModel.deleteSelectedLocations() },
                    content = { Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete Icon"
                    ) },
                )
            }

            DoubleBorderContainer(Modifier.fillMaxHeight(1f)) {
                viewModel.favoriteLocations.value.forEachIndexed { index, item ->
                    Box(modifier = Modifier.height(50.dp).fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Checkbox(
                                checked = item.isSelected.value,
                                onCheckedChange = { checked -> viewModel.toggleSelf(checked, index) }
                            )
                            Text(item.location.display_name)
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Text("${String.format("%.2f", item.forecast.temperature.toCelsius())} °C")
                            Image(
                                modifier = Modifier.fillMaxHeight(0.7f),
                                painter = painterResource(id = item.forecast.weatherConditions.image),
                                contentDescription = "Weather Condition Icon",
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text("${item.forecast.humidity.value.roundToInt()}%")
                            Image(
                                modifier = Modifier.fillMaxHeight(0.7f),
                                painter = painterResource(id = R.drawable.ic_humidity),
                                contentDescription = "Humidity Icon",
                            )
                        }
                    }

                    DashedDivider()
                }
            }
        }

        Button (
            onClick = { viewModel.isAddFavoriteView.value = true },
            content = { Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add Icon"
            ) },
            modifier = Modifier.align(Alignment.BottomEnd),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFavoriteLocationScreen(viewModel: FavoriteLocationViewModel) {
    val context = LocalContext.current

    val cameraPositionState = viewModel.position
    LaunchedEffect(cameraPositionState.value) {
        viewModel.position.value = Position(
            cameraPositionState.value.lat,
            cameraPositionState.value.lon,
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(20.dp))

        SearchBar(
            query = viewModel.userInput.value,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = { viewModel.onValidate() },
            active = viewModel.isSearchActive.value,
            onActiveChange = { viewModel.onSearchToggle() },
            placeholder = { Text("Search location") },
            trailingIcon = {
                TextButton(
                    onClick = { viewModel.onSearchIconClick() },
                    modifier = Modifier.background(Color.Transparent)
                ) {
                    if (viewModel.isSearchActive.value)
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close Icon"
                        )
                    else
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Icon"
                        )
                }
            },
            shape = SearchBarDefaults.fullScreenShape,
        ) {
            Column {
                for (location in viewModel.locations.value) {
                    TextButton(
                        onClick = { viewModel.saveLocation(location) },
                        content = { Text("${location.name} (${location.country})") },
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        AndroidView(
            factory = {
                MapView(context).apply {
                    setTileSource(TileSourceFactory.OpenTopo)
                    setMultiTouchControls(true)
                    setBuiltInZoomControls(true)
                    controller.setZoom(15.0)
                    controller.setCenter(GeoPoint(cameraPositionState.value.lat.toDouble(), cameraPositionState.value.lon.toDouble()))
                    println("test=" + cameraPositionState.value)
                }
            },
            update = { mapView ->
                mapView.controller.setCenter(GeoPoint(cameraPositionState.value.lat.toDouble(), cameraPositionState.value.lon.toDouble()))
                mapView.overlays.clear()
            }
        )
    }
}