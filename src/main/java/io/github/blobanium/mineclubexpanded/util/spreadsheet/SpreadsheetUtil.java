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

    public static void test() throws IOException, GeneralSecurityException {
        // The ID of the spreadsheet to retrieve data from.
        String spreadsheetId = "1EvqhTx3f2m1yzF5-MbgW5QvP7zEe_M_pCWBs3c2f0D0";

        // The A1 notation of the values to retrieve.
        String range = "B3";

        // How values should be represented in the output.
        // The default render option is ValueRenderOption.FORMATTED_VALUE.
        String valueRenderOption = "UNFORMATTED_VALUE";

        // How dates, times, and durations should be represented in the output.
        // This is ignored if value_render_option is
        // FORMATTED_VALUE.
        // The default dateTime render option is [DateTimeRenderOption.SERIAL_NUMBER].
        String dateTimeRenderOption = "FORMATTED_STRING";

        Sheets sheetsService = createSheetsService();
        Sheets.Spreadsheets.Values.Get request =
                sheetsService.spreadsheets().values().get(spreadsheetId, range);
        request.setValueRenderOption(valueRenderOption);
        request.setDateTimeRenderOption(dateTimeRenderOption);
        request.setKey("AIzaSyCncY4J8Me3y1lf1KdCsLWi11t1A6o6Emw");

        ValueRange response = request.execute();

        System.out.println(response);
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

    public static void testInternal(){
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

}
