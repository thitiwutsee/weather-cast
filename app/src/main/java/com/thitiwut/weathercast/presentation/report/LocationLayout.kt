package com.thitiwut.weathercast.presentation.report

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationLayout(location:LatLng){

    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }
    Card(Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(8.dp),) {

        GoogleMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxWidth().height(200.dp),
            onMapClick = { }
        )
    }
}