package com.dcodestar.googleplaces;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncDownload extends AsyncTask<String,Void, String> {

    private static final String TAG = "AsyncDownload";

    interface AsyncDownloadComplete{
        void onDownloadComplete(String s);
    }

    AsyncDownloadComplete callback=null;

    AsyncDownload(AsyncDownloadComplete asyncDownloadComplete){
        this.callback=asyncDownloadComplete;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(strings[0]);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while(null!=(line=reader.readLine())){
                result.append(line).append("\n");
            }
            Log.d(TAG, "doInBackground: "+result.toString());
            return result.toString();
        }catch (Exception e){
            Log.e(TAG, "doInBackground: exception in downloading from "+strings[0]+" with message "+e.getMessage() );
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callback.onDownloadComplete(s);
    }
}
