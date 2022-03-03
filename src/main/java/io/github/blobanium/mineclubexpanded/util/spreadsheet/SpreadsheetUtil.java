package io.github.blobanium.mineclubexpanded.util.spreadsheet;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class SpreadsheetUtil {

    private static final String importedID = "1dZ-EL_uU2a2KEMLo6ztGjnWSJTSf_z1caNLP5sUk-I0";
    private static final String mcbetsID = "1EvqhTx3f2m1yzF5-MbgW5QvP7zEe_M_pCWBs3c2f0D0";

    public static String test(String range) throws IOException, GeneralSecurityException {
        String spreadsheetId = importedID;
        String valueRenderOption = "UNFORMATTED_VALUE";
        String dateTimeRenderOption = "FORMATTED_STRING";
        String majorDimension = "ROWS";

        Sheets sheetsService = createSheetsService();
        Sheets.Spreadsheets.Values.Get request =
                sheetsService.spreadsheets().values().get(spreadsheetId, range);
        request.setValueRenderOption(valueRenderOption);
        request.setDateTimeRenderOption(dateTimeRenderOption);
        request.setMajorDimension(majorDimension);
        request.setKey("AIzaSyCncY4J8Me3y1lf1KdCsLWi11t1A6o6Emw");

        ValueRange response = request.execute();


        return response.get("values").toString();
    }

    public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        GoogleClientSecrets.Details secret = new GoogleClientSecrets.Details();
        secret.setClientId("347965176614-f3sp9g6bba9q7geo8pl82ml9tsbb9qj1.apps.googleusercontent.com");

        // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
        //
        // Authorize using one of the following scopes:
        //   "https://www.googleapis.com/auth/drive"
        //   "https://www.googleapis.com/auth/drive.file"
        //   "https://www.googleapis.com/auth/drive.readonly"
        //   "https://www.googleapis.com/auth/spreadsheets"
        //   "https://www.googleapis.com/auth/spreadsheets.readonly"
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
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

}
