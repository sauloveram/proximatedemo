package com.example.saulovera.proximatedemo.wsclient;

import android.os.AsyncTask;
import android.util.Log;

import com.example.saulovera.proximatedemo.vo.BaseResponseObject;
import com.google.gson.Gson;


public class WSAsyncTask extends AsyncTask<String, Integer, Object> {

    private String mWS;
    private String mParams = "";
    private int mMethod;
    private static final String TAG = WSAsyncTask.class.getName();
    private String result;
    private ServiceCallback mCallback;

    public WSAsyncTask(String WS, int method, String params) {
        mParams = params;
        mWS = WS;
        mMethod = method;


    }


    @Override
    protected Object doInBackground(String... params) {

        HTTPSWSClient ws = new HTTPSWSClient();
        try {
            return ws.WSClientConsumerBasic(mWS, mParams, mMethod);

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
        Log.wtf("Result", (String) result);
        mCallback.onServiceCallback(result);

    }

    public void setmCallback(ServiceCallback mCallback) {
        this.mCallback = mCallback;
    }
}
