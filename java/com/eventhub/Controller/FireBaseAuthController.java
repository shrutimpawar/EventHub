package com.eventhub.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FireBaseAuthController {

    private static final String API_Key = "AIzaSyBRUq__-ukWR0ZgoZBfombRkHpd9G_MMWA";

    public boolean signUpwithEmailandPassword(String email, String password) {

        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_Key);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application");
            conn.setDoOutput(true);
            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email,
                    password);
            OutputStream os = null;
            os = conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true;
            } else {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                return false;
            }

        } catch (Exception ex) {
            return false;
        }

    }


    public boolean signInwithEmailandPassword(String emailvar, String password1) {

        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_Key);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application");
            conn.setDoOutput(true);
            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", emailvar,
                    password1);
            OutputStream os = null;
            os = conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true;
            } else {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                return false;
            }

        } catch (Exception ex) {
            return false;
        }

    }
}
