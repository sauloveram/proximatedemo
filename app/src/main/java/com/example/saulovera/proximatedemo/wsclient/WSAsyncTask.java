package com.example.saulovera.proximatedemo.wsclient;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.saulovera.proximatedemo.vo.BaseResponseObject;
import com.google.gson.Gson;


public class WSAsyncTask extends AsyncTask<String, Integer, Object> {

    private String mCualWS;
    private String mParams = "";
    private int mtipo;
    private static final String TAG = WSAsyncTask.class.getName();
    private String result;
    private ServiceCallback mCallback;

    public WSAsyncTask(String cualWS, int method, String params) {
        mParams = params;
        mCualWS = cualWS;
        mtipo = method;


    }


    @Override
    protected Object doInBackground(String... params) {

        HTTPSWSClient ws = new HTTPSWSClient();
        try {
            return (String) ws.WSClientConsumerBasic(mCualWS, mParams, mtipo);

        } catch (Exception e) {
            e.printStackTrace();
            BaseResponseObject bro = new BaseResponseObject();
            bro.setCodeMessage("-1");
            bro.setMessage("Ocurrio un error inesperado al contactar el servidor");
            return new Gson().toJson(bro);
        }

    }

    @Override
    protected void onPostExecute(Object result) {
        if (mCallback == null) {
            Log.wtf("Error!!", "No callback");
            return;
        }
            mCallback.onServiceCallback(result);

    }

    public void setmCallback(ServiceCallback mCallback) {
        this.mCallback = mCallback;
    }
}
