package com.acefreesoft.CourtBot.service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RaedJsonData {

    public JSONArray readJson(String file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(new FileReader(file, StandardCharsets.UTF_8));
    }
}
