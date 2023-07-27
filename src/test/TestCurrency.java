package test;

import utils.CurrencyConvert;
import java.lang.Object;
import java.math.BigDecimal;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;

import netscape.javascript.JSObject;

public class TestCurrency {
    public static void main(String[] args) {
        CurrencyConvert apiConsumer = new CurrencyConvert();
        String[] currencys = {"USD-COP", "EUR-COP"};
        try {

            for (String currencyPair: currencys) {
                String data = apiConsumer.getCurrency("USD-COP");
                System.out.println(data);
    
                // Parsear la respuesta JSON
                JSONObject jsonObject = new JSONObject(data);
    
                // Obtener cada valor por clave
                String message = jsonObject.getString("message");
                String currency = jsonObject.getString("currency");
                String disclaimer = jsonObject.getString("disclaimer");
                String name = jsonObject.getString("name");
                double base = jsonObject.getDouble("base");
                double to = jsonObject.getDouble("To");
    
                // Imprimir los valores obtenidos
                System.out.println("Message: " + message);
                System.out.println("Currency: " + currency);
                System.out.println("Disclaimer: " + disclaimer);
                System.out.println("Name: " + name);
                System.out.println("Base: " + base);
                System.out.println("To: " + to);

                System.out.println("-------------------------------------------------------");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
