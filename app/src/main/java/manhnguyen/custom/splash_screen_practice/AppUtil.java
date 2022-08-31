package manhnguyen.custom.splash_screen_practice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class AppUtil {
    public static boolean isNetworkConnect(Context context) {
        // step1 : check context
        if (context == null) {
            return false;
        } else {
            //step 2: check connectivityManager
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            } else {
                //step3: check SDK VERSION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // step4 : check network
                    Network network = connectivityManager.getActiveNetwork();
                    if (network == null) {
                        return false;
                    } else {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                        return networkCapabilities != null &&
                                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                                );
                    }
                } else {
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    return networkInfo != null && networkInfo.isConnected();
                }
            }
        }
    }
}
