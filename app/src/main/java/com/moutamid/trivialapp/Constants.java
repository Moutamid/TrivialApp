package com.moutamid.trivialapp;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Constants {
    public static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuGON09lEd5dPytQ9E/fNjuTbB60ECYbulp6lfTuJ0gOFREDoc1zjAulFHvM5OQ1CqfjGyerNUBYPKqCFNdMWynXEaqq7fCKf2PwXbczgx11MtJK/8ukRfa572jJnSsQoY6qk9ebHoGswRfU9yTaz6ldVzJaV4V2D58YSKmOtJO8jzr4yf/j9VffSvOvBzGCwi8K5r/UKi4OtH4KK53Ij9CN0Uf6kpnpaSIzUJWgD+rhWTPNH9GHd/D39670Oxz012b3PBgPwOIl7GrKF4WpEIS1UnhQOWHYLKOl7UfgoVonE7ey3/8gqR0mGeOH4KgE8EYgvMgiSHmtjgArhWfqTwIDAQAB";
    public static final String FIVE_DOLLAR_PRODUCT = "five.com.moutamid.trivialapp";
    public static final String TEN_DOLLAR_PRODUCT = "ten.com.moutamid.trivialapp";
    public static final String TWENTY_DOLLAR_PRODUCT = "twenty.com.moutamid.trivialapp";
    public static final String FIFTY_DOLLAR_PRODUCT = "fifty.com.moutamid.trivialapp";
    public static final String HUNDRED_DOLLAR_PRODUCT = "hundred.com.moutamid.trivialapp";
    public static final String TWO_HUNDRED_DOLLAR_PRODUCT = "two.hundred.com.moutamid.trivialapp";
    public static final String THREE_HUNDRED_DOLLAR_PRODUCT = "three.hundred.com.moutamid.trivialapp";


    public static void checkApp(Activity activity) {
        String appName = "trivialapp";

        new Thread(() -> {
            URL google = null;
            try {
                google = new URL("https://raw.githubusercontent.com/Moutamid/Moutamid/main/apps.txt");
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String input = null;
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                try {
                    if ((input = in != null ? in.readLine() : null) == null) break;
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(input);
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String htmlData = stringBuffer.toString();

            try {
                JSONObject myAppObject = new JSONObject(htmlData).getJSONObject(appName);

                boolean value = myAppObject.getBoolean("value");
                String msg = myAppObject.getString("msg");

                if (value) {
                    activity.runOnUiThread(() -> {
                        new AlertDialog.Builder(activity)
                                .setMessage(msg)
                                .setCancelable(false)
                                .show();
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
