package avi.telstra.app.sample.myapplication.asynctaskpackage;

import java.util.ArrayList;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import avi.telstra.app.sample.myapplication.datamodel.Rows;
import avi.telstra.app.sample.myapplication.interfaces.GetDataInterface;
import avi.telstra.app.sample.myapplication.util.JSONParser;

public class PraseJson extends AsyncTask<String, Void, ArrayList<Rows>> {

    public PraseJson(Context context, String linktoHit)
    {
        progressDialog = new ProgressDialog(context);

        this.linkToHit = linktoHit ;
        this.context   = context ;

    }
    JSONObject jsonObject;
    ProgressDialog progressDialog ;
    Context context ;
    String linkToHit ;
    Rows rows ;
    String apptitle ;
    GetDataInterface getDataInterface = null;
    ArrayList<Rows> rowsArrayList = new ArrayList<>();
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setMessage("Fetching Data");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected ArrayList<Rows> doInBackground(String... strings) {

        jsonObject = JSONParser.getJSONFromUrl(linkToHit);



        JSONArray jeus;
        try {
            apptitle = jsonObject.getString("title");


            JSONArray rowsJsonArray = jsonObject.optJSONArray("rows");


            rowsArrayList.clear();

            if(rowsJsonArray != null){

                for (int i = 0; i < rowsJsonArray.length(); i++) {
                    rows = new Rows();
                    JSONObject rowsObject = rowsJsonArray.optJSONObject(i);
                    rows.setDescription(rowsObject.getString("description"));
                    rows.setImageHref(rowsObject.getString("imageHref"));
                    rows.setTitle(rowsObject.getString("title"));



                    rowsArrayList.add(rows);
                }

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowsArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Rows> result) {

        super.onPostExecute(result);

        progressDialog.dismiss();

        getDataInterface.getJsonData(result , apptitle);

    }

    public void init(Context myactiActivity)
    {
        if(getDataInterface == null)

        {
            getDataInterface = (GetDataInterface) myactiActivity;
        }
    }
}
