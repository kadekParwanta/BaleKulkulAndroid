package com.herokuapp.balekulkulandroid.calendar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herokuapp.balekulkulandroid.DataObject;
import com.herokuapp.balekulkulandroid.HorzGridViewAdapter;
import com.herokuapp.balekulkulandroid.R;
import com.herokuapp.balekulkulandroid.VertGridViewAdapter;
import com.jess.ui.TwoWayGridView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * Created by mitrais on 4/20/15.
 */
public class TempCalendarFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private final int NUMBER_SOURCE_ITEMS = 30;
    private final int OPAQUE = 255;
    private HorzGridViewAdapter horzGridViewAdapter;
    private VertGridViewAdapter vertGridViewAdapter;
    private Context mContext;

    public static TwoWayGridView horzGridView;
    public static TwoWayGridView vertGridView;

    //Screen dims
    public final static int COLUMN_PORT = 0;
    public final static int COLUMN_LAND = 1;
    public static int column_selected;
    public static int[] displayWidth;
    public static int[] displayHeight;

    public static TempCalendarFragment newInstance(int sectionNumber) {
        TempCalendarFragment cf = new TempCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        cf.setArguments(args);
        return cf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_demo, container,
                false);

        //As always, get the context
        mContext = getActivity();

        //Get handles to views that will be used
        horzGridView
                = (TwoWayGridView) rootView.findViewById(R.id.horz_gridview);
        vertGridView
                = (TwoWayGridView) rootView.findViewById(R.id.vert_gridview);

        //Create the data for use in the vert gridview-same data will be passed to horz gridview
        List<DataObject> horzData = generateGridViewObjects();
        List<DataObject> vertData = generateGridViewObjects();


        GregorianCalendar month = (GregorianCalendar) GregorianCalendar.getInstance();
        vertGridViewAdapter = new VertGridViewAdapter(mContext,vertData);
        horzGridViewAdapter = new HorzGridViewAdapter(mContext,horzData, month);

        //Set the adapter for the gridviews
        vertGridView.setAdapter(vertGridViewAdapter);
        horzGridView.setAdapter(horzGridViewAdapter);

        return rootView;
    }

    private List<DataObject> generateGridViewObjects(){

        List<DataObject> allData = new ArrayList<DataObject>();

        String name;
        int color;
        int red;
        int green;
        int blue;
        Random rn = new Random();

        for(int i=0; i < NUMBER_SOURCE_ITEMS; i++){
            //Get random data for use during testing

            red = rn.nextInt();
            blue = rn.nextInt();
            green = rn.nextInt();

            //Generate data from random info
            color = Color.argb(255, red, green, blue);
            name = ""+ i;

            DataObject singleObject = new DataObject(name, color);
            allData.add(singleObject);
        }

        return allData;
    }
}
