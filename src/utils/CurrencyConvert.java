package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CurrencyConvert {
    private static final String API_BASE_URL = "https://api-currency.onrender.com/currency/api/convert/";

    public static String getCurrency(String currencys) throws IOException {
        URL url = new URL(API_BASE_URL + currencys);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // in this method i going to establish a petitions GET request
        connection.setRequestMethod("GET");

        // Read the response of API request
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // close the connection in API
        connection.disconnect();

        return response.toString();
    }
}
