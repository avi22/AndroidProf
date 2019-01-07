package avi.telstra.app.sample.myapplication.interfaces;

import java.util.ArrayList;
import avi.telstra.app.sample.myapplication.datamodel.Rows;

public interface GetDataInterface {
    public void getJsonData(ArrayList<Rows> displayData , String apptitle);
}
