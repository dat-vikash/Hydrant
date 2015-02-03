package com.datutil.hydrant.service;

import android.util.Log;

import org.apache.commons.collections.buffer.UnboundedFifoBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;


/**
 * Created by vik on 1/30/15.
 */
public class LogcatReader {
    static final String DEFAULT_LOGCAT_ARGUMENTS = "logcat -v time";
    // unbounded FIFO buffer
    UnboundedFifoBuffer logBuffer = new UnboundedFifoBuffer();
    Thread thread;

    // default no arg constructor
    public LogcatReader() {

    }

    public void readLogcatIntoBuffer()
    {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("VIKS_STUFF"," STARTING THREAD!");
                    // attach to logcat process
                    Process readerProcess = Runtime.getRuntime().exec(DEFAULT_LOGCAT_ARGUMENTS);
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(readerProcess.getInputStream()));

                    // read each line of the log into the buffer
                    String lineBeingRead;
                    while( (lineBeingRead =bufferedReader.readLine()) != null) {
                        logBuffer.add((String) lineBeingRead);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    public UnboundedFifoBuffer getLogBuffer()
    {
        return logBuffer;
    }


}
