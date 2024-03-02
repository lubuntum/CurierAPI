package com.example.curierplus.api;

import com.example.curierplus.local.LocalStoreHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class UserAuth {
    public static final String SUC_STATUS = "Добро пожаловать";
    public static final String AUTH_ERROR = "Пользователь не найден";
    public static final String NETWORK_ERROR = "Ошибка соединения";
    public static String authWithLoginAndPass(String login, String pass) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody requestBody = null;
        requestBody = new FormBody.Builder()
                .add("login", login)
                .add("pass", generateMD5(pass))
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/%s", OrdersAPI.URL, "auth"))
                .post(requestBody)
                .header("Content-Type","application/json")
                .build();
        try(Response res = client.newCall(request).execute()){
            JSONObject json = new JSONObject(res.body().string());
            LocalStoreHelper.saveEmployeeToLocalBase(json.get("employee").toString());
            return SUC_STATUS;
        } catch (JSONException | IOException e) {
            return AUTH_ERROR;
        }
    }
    public static String generateMD5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    /*
    private  String generateMD5(String pass) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(pass.getBytes());
        byte[] messageDigest = digest.digest();

        StringBuffer sB = new StringBuffer();
        for(int i = 0; i < messageDigest.length; i++)
            sB.append(Integer.toHexString(0xFF & messageDigest[i]));
        return  sB.toString();
    }

     */
}
