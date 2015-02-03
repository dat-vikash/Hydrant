package com.datutil.hydrant.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vik on 1/30/15.
 * This class is responsible for converting the String representation of the logcat log line into
 * a machine readable format
 */
public class LogcatParser
{
    private final static String LOGCAT_WITH_TIME_PATTERN = "^(\\d\\d-\\d\\d\\s\\d\\d:\\d\\d:\\d\\d\\.\\d+):*"
            + "\\s([VDIWEAF])/(.*?)\\((\\s*\\d+)\\):\\s+(.*)$";

    private static Pattern logcatPattern = Pattern.compile(LOGCAT_WITH_TIME_PATTERN);

    // default no args constructor
    public LogcatParser() {
    }

    // converts string to JSON
    public static JSONObject toJson(String line)
    {
        // test if line matches our expected pattern
        JSONObject jsonLog = new JSONObject();
        Matcher logcatMatcher = logcatPattern.matcher(line);
        if(logcatMatcher.find())
        {
            // build JSON object for the wire
            try {
                jsonLog.put("timestamp",logcatMatcher.group(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finally {
                return jsonLog;
            }
        } else {
            return jsonLog;
        }
    }
}
