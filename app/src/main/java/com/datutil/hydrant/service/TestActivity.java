package com.datutil.hydrant.service;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.apache.commons.collections.buffer.UnboundedFifoBuffer;

import java.util.Iterator;

/**
 * Created by vik on 2/3/15.
 */
public class TestActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("VIKS_STUFF","STARTING.....");
        doStuff();
    }

    public void doStuff()
    {
        LogcatReader reader = new LogcatReader();
        reader.readLogcatIntoBuffer();
        Iterator bufferIterator = reader.getLogBuffer().iterator();

        while(true){
            if(bufferIterator.hasNext()){
                Log.i("VIKS_STUFF", LogcatParser.toJson((String)reader.getLogBuffer().get()).toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
