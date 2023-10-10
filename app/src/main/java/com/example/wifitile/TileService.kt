package com.example.wifitile
import android.app.PendingIntent
import android.content.Intent
import android.net.wifi.WifiManager
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startActivityForResult


class MyTileService: TileService(){
    private var clicks = 0
    @RequiresApi(34)
    override fun onClick() {
        super.onClick()
        clicks++

        qsTile.label =" ${getString(R.string.of_wifi_tile_name)} $clicks"
        val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
        startActivityAndCollapse(PendingIntent.getActivity(this, 0, panelIntent,  PendingIntent.FLAG_IMMUTABLE))
        //startActivity( Intent(WifiManager.ACTION_PICK_WIFI_NETWORK))
        qsTile.updateTile()
        // Called when the user click the tile
    }

    override fun onTileRemoved() {
        super.onTileRemoved()

        // Do something when the user removes the Tile
    }

    override fun onTileAdded() {
        super.onTileAdded()

        // Do something when the user add the Tile
    }

    override fun onStartListening() {
        super.onStartListening()
        val wifiManager = this.getSystemService(WIFI_SERVICE) as WifiManager
        val state = !wifiManager.isWifiEnabled

        qsTile.state = if (state) Tile.STATE_INACTIVE else Tile.STATE_ACTIVE

        qsTile.updateTile()
    }

    override fun onStopListening() {
        super.onStopListening()
        super.onStartListening()
        val wifiManager = this.getSystemService(WIFI_SERVICE) as WifiManager
        val state = !wifiManager.isWifiEnabled

        qsTile.state = if (state) Tile.STATE_INACTIVE else Tile.STATE_ACTIVE

        qsTile.updateTile()
        // Called when the tile is no longer visible
    }
}