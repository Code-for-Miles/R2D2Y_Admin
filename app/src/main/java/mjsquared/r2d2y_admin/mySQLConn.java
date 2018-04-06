package mjsquared.r2d2y_admin;

/**
 * Created by Miles on 3/28/2018.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class mySQLConn extends AsyncTask<String,Void,String>{
    //private String data = "";
    private Context context;
    private String address;
    private ListView lv;

    private ProgressDialog pd;
    public mySQLConn(Context c, String address, ListView lv){
        this.context = c;
        this.address = address;
        this.lv = lv;

    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data...Please wait");
        pd.show();
    }
    @Override
    protected String doInBackground(String...params){
        address = downloadData();
    return address;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(!result.isEmpty()){
            Parser p = new Parser(context,result,lv);
            p.execute();

        }else {
            Toast.makeText(context,"Unable to download data",Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();

    }
    private String downloadData(){
        InputStream inputStream = null;
        String line = "";//httpURLConnection.getInputStream();
        try {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            if (bufferedReader!=null) {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            }else {
                return null;
            }
            return sb.toString();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
