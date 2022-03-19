package io.github.blobanium.mineclubexpanded.util.spreadsheet;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SpreadsheetUtil {

    private static final String importedID = "1dZ-EL_uU2a2KEMLo6ztGjnWSJTSf_z1caNLP5sUk-I0";
    private static final String defaultKey = "AIzaSyCncY4J8Me3y1lf1KdCsLWi11t1A6o6Emw";

    public static String test(String range) throws IOException, GeneralSecurityException {
        String valueRenderOption = "UNFORMATTED_VALUE";
        String dateTimeRenderOption = "FORMATTED_STRING";
        String majorDimension = "ROWS";

        Sheets sheetsService = createSheetsService();
        Sheets.Spreadsheets.Values.Get request =
                sheetsService.spreadsheets().values().get(importedID, range);
        request.setValueRenderOption(valueRenderOption);
        request.setDateTimeRenderOption(dateTimeRenderOption);
        request.setMajorDimension(majorDimension);
        request.setKey(getOrDefaultKey());

        ValueRange response = request.execute();


        return response.get("values").toString();
    }

    public static String getOrDefaultKey(){
        if(ConfigReader.overrideKey.equals("")){
            return defaultKey;
        } else {
            return ConfigReader.overrideKey;
        }
    }

    public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        GoogleClientSecrets.Details secret = new GoogleClientSecrets.Details();
        secret.setClientId("347965176614-f3sp9g6bba9q7geo8pl82ml9tsbb9qj1.apps.googleusercontent.com");

        @SuppressWarnings("deprecation")
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(new GoogleClientSecrets().setWeb(secret))
                .build();
        //TODO get the token.

        return new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google-SheetsSample/0.1")
                .build();
    }

    public static String testInternal(String range){
        try {
            return test(range);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

}
