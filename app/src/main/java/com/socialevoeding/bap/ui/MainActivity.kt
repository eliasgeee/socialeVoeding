package com.socialevoeding.bap.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.ActivityMainBinding
import com.socialevoeding.bap.location.GPSTracker
import org.koin.android.viewmodel.ext.android.viewModel

const val REQUESTCODE_LOCATION = 1

class MainActivity() : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private var locationPermissionOldApi: Int = -1
    private val gpsTrackerViewModel : GPSTrackerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        navController = this.findNavController(R.id.nav_host_fragment)

        checkFineLocationPermission()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    private fun checkFineLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUESTCODE_LOCATION
                )
            }
            else{
               locationPermissionOldApi = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
                if(locationPermissionOldApi == 0)
                    gpsTrackerViewModel.startGpsTracker()
            }
        }
        else{
            gpsTrackerViewModel.startGpsTracker()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUESTCODE_LOCATION -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    gpsTrackerViewModel.startGpsTracker()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO stop GPS
    }
}
