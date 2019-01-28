package com.filk.loganalyzer;

import com.filk.http.HttpMethod;
import com.filk.io.ReaderWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogAnalyzer {
    private static final String DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";

    public static List<LogToken> getLogs(String path, LocalDateTime timeFrom, LocalDateTime timeTo) throws IOException {
        ReaderWriter readerWriter = new ReaderWriter();
        List<String> logStrings = readerWriter.readLines(new FileInputStream(path));

        List<LogToken> logTokens = new ArrayList<>();

        for (String logString : logStrings) {
            LogToken logToken = parseString(logString);
            if(logToken.getTime().isAfter(timeFrom) && logToken.getTime().isBefore(timeTo)) {
                logTokens.add(logToken);
            }
        }

        return logTokens;
    }

    // 07/Mar/2004:16:10:02 -0800
    public static LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDateTime.parse(dateString, formatter);
    }

    private static LogToken parseString(String logString) {
        String[] strings = logString.split(" ");

        String stringDate = strings[3] + " " + strings[4];
        stringDate = stringDate.substring(1, stringDate.length() - 1);
        LocalDateTime date = parseDate(stringDate);

        String stringRequest = strings[5].substring(1);
        HttpMethod httpMethod = HttpMethod.getByName(stringRequest);

        return new LogToken(date, httpMethod, strings[6]);
    }
}
