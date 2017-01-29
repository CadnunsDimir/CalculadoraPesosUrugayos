package com.cadnunsdev.direccionescongespanhol;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Tiago Silva on 17/01/2017.
 */

public class GeoCodeJsonService {
    String urlService = "https://maps.googleapis.com/maps/api/geocode/json?address=";

//    public static LatLng getByAdress(String adress){
//        DefaultHttpClient defaultClient = new JsonObjectRequest;
//        // Setup the get request
//        HttpGet httpGetRequest = new HttpGet("http://example.json");
//
//        // Execute the request in the client
//        HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
//        // Grab the response
//        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
//        String json = reader.readLine();
//
//        // Instantiate a JSON object from the request response
//        JSONObject jsonObject = new JSONObject(json);
//
//    }
}
