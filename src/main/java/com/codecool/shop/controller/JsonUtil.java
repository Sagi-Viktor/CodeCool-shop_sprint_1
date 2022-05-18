package com.codecool.shop.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtil {

    public static JsonObject getRequestJsonObject(HttpServletRequest req) throws IOException {
        BufferedReader bufferedReader = req.getReader();
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            content.append(inputLine);
        }
        String cartItemString = content.toString();
        return new Gson().fromJson(cartItemString, JsonObject.class);
    }
}
