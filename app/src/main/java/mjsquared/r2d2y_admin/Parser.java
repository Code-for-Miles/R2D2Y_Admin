package mjsquared.r2d2y_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.support.design.widget.Snackbar;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by Miles on 4/6/2018.
 */

public class Parser extends AsyncTask <Void,Integer,Integer>{
    private Context c;
    private ListView lv;

    private String data;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Integer> list_roomnum = new ArrayList<>();
    private ProgressDialog pd;
    Parser(Context c, String data, ListView lv){
        this.c = c;
        this.data = data;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing....Please Wait");
    }
    @Override
    protected Integer doInBackground(Void...params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer ==1){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,list);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Eventually add a dialog box to show the rest of the data.
                    Snackbar.make(view,
                            "Item: "+list.get(position)+"\nRoom: "+ list_roomnum.get(position).toString(),Snackbar.LENGTH_LONG).show();

                }
            });
        }else {
            Toast.makeText(c,"Unable to parse...",Toast.LENGTH_SHORT).show();
        }

        //pd.dismiss();
    }
    private int parse(){
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;
            list.clear();
            for (int i=0; i<ja.length(); i++){
                jo = ja.getJSONObject(i);
                String item = jo.getString("item");
                Integer roomnum = jo.getInt("roomnum");
                list.add(item);
                list_roomnum.add(roomnum);
                Log.i("roomnum ",roomnum.toString());

            }
            return 1;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }

}
