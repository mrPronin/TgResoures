package com.arish;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class GPSTestAction extends Activity implements LocationListener{
	
	private LocationManager myManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Связываем менеджер и сервис
        myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Назначаем слушателя
        myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		TextView tx = (TextView) findViewById(R.id.tv1);
		tx.setText("Широта: "	+ location.getLatitude()  + "\nДолгота: " + location.getLongitude() + 
				   "\nСкорость: " + location.getSpeed() + "\n");
	}

	public void onProviderDisabled(String provider) {}

	public void onProviderEnabled(String provider) {}

	public void onStatusChanged(String provider, int status, Bundle extras) {}
}