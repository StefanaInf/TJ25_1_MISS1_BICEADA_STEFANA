package com.example.laborator_1_client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DesktopClient {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/Laborator_1_war_exploded/controller");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestProperty("Text-Flag", "true");

            String param = "page=2";
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes());
            os.flush();
            os.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
