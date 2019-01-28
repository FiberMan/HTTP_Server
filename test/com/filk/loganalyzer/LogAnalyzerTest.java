package com.filk.loganalyzer;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LogAnalyzerTest {
    @Test
    public void logAnalyzer() throws IOException {
        List<LogToken> filteredLogs = LogAnalyzer.getLogs("test.log", LogAnalyzer.parseDate("07/Mar/2004:16:47:12 -0800"), LogAnalyzer.parseDate("07/Mar/2004:17:01:53 -0800"));

        assertEquals(8, filteredLogs.size());
        assertEquals("/twiki/bin/view/Main/TWikiGroups?rev=1.2", filteredLogs.get(1).getMessage());

        for (LogToken logToken : filteredLogs) {
            System.out.println(logToken);
        }
    }
}
