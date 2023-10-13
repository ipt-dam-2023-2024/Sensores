package ipt.dam.fragmentosversaob.Fragmentos

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import ipt.dam.fragmentosversaob.R



/**
 * A simple [Fragment] subclass.
 * Use the [Fragmento1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmento1 : Fragment, LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var fragmentActivity: FragmentActivity
    private val locationPermissionCode = 2
    private lateinit var tvGpsLocation: TextView
    private lateinit var view:View;


    constructor(fragmentActivity: FragmentActivity):super() {
        this.fragmentActivity = fragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_fragmento1, container, false);
        val button: Button = view.findViewById(R.id.getLocation)
        button.setOnClickListener {
            getLocation()
        }
        return view;
    }



    private fun getLocation() {
        locationManager = fragmentActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(fragmentActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions( arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {
        tvGpsLocation = view.findViewById(R.id.textView)
        tvGpsLocation.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(fragmentActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(fragmentActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    }
}