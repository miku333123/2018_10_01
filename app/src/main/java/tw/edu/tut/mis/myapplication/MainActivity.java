package tw.edu.tut.mis.myapplication;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFLPClient = LocationServices.getFusedLocationProviderClient(activity:this);
    }
}

LocationCallback mLocCB = new LocationCallback(){
    @Override
    public void onLocationResult(LocationResult locationResult) {

        List<Location> location = locationResult.getLocations();
        if( location.size()>0){
            Location loc = location.get(location.size()-1);
            double lat,lon;
            lat = loc.getLatitude();
            lon = loc.getLongitude();

            Log.d(tag:"MyAPP",msg:"location: ("+lat","+lon+")");
            ((TextView)findViewById(R.id.test)).setText( lat + "," + lon );

        }

    }
    //啟動定位
    void startLoc(){
        LocationRequest req = new LocationRequest();
        req.setInterval(10000);
        req.setFastestInterval(5000);
        req.setPriority( LocationRequest.PRIORITY_HIGH_ACCURACY );

        mFLPClient.requestLocationUpdates(req,mLocCB, looper:null);

    }
    //關掉定位
    void stopLoc(){
        mFLPlient.removeLocationUpdates(mLocCB);

    }
    //app從暫停變執行
    @Override
    protected void onResume(){
        super.onResume();
        startLoc();
    }
    //app從執行變暫停
    @Override
    protered void onPause(){
        super.onPause();
        stopLoc();
    }

}
