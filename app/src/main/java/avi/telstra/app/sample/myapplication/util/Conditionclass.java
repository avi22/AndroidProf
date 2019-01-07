package avi.telstra.app.sample.myapplication.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
public class Conditionclass {

    public static boolean isNetworkAvailable(Context context)
    {


        ConnectivityManager connectivityManager

                = (ConnectivityManager) context.getSystemService

                (Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

}
