package com.demo.testapi.controllersstapi.controllers;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is Down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage (@RequestParam String url){
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2000);
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println(conn.getResponseCode());
            if (responseCodeCategory != 2 && responseCodeCategory != 3){
                returnMessage = SITE_IS_DOWN;
                System.out.println("Down");
            }
            else {
                returnMessage = SITE_IS_UP;
                System.out.println("Else up");
            }    
        }   catch (MalformedURLException e){
            System.out.println("Marformed Exception");
            returnMessage = INCORRECT_URL;  
        }   catch (IOException e) {
            System.out.println("IO Exception");
            returnMessage = SITE_IS_DOWN; 
        }

        return returnMessage;
    }
}
