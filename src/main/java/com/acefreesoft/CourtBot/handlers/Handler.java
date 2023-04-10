package com.acefreesoft.CourtBot.handlers;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;

public interface Handler<T> {

    void choose(T t) throws ParseException, java.text.ParseException, FileNotFoundException;
}
