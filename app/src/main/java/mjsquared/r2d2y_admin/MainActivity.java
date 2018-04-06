package mjsquared.r2d2y_admin;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //public static String jsonStr;
    String url = "http://10.117.131.134/request_check.php?request_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final ListView listView = (ListView) findViewById(R.id.list_view);

        final mySQLConn mySQLConn = new mySQLConn(this,url,listView);
        mySQLConn.execute();

        final TextView txtv = (TextView)findViewById(R.id.request_title);

        txtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mySQLConn.execute();
                txtv.setClickable(false);
            }
        });
        float systime = System.currentTimeMillis();
        //while (true){
          //  mySQLConn.execute();
        //}


    }
   /* public void parseJSON(String jsonStr){

        try {
            //JSONObject object = new JSONObject(data);
            JSONArray array = new JSONArray(jsonStr);
            String email_db = "";
            String roomnum_db = "";
            String item_db = "";
            int user_id = 0;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                // user_id = object.getInt("user_id");
                email_db = (String) object.get("email");
                roomnum_db = (String) object.get("roomnum");
                item_db = (String) object.get("item");
                Log.i("Name is ", email_db);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }*/
}
