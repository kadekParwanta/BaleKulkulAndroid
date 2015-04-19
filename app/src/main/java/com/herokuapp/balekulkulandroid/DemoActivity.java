/*
 * The layout specifications are in the adapters for each gridview
 */
package com.herokuapp.balekulkulandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jess.ui.TwoWayGridView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

public class DemoActivity extends Activity {
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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		//As always, get the context
		mContext = getApplicationContext();
		
		//Get handles to views that will be used
		horzGridView 
			= (TwoWayGridView) findViewById(R.id.horz_gridview);
		vertGridView 
			= (TwoWayGridView) findViewById(R.id.vert_gridview);
		
		//Create the data for use in the vert gridview-same data will be passed to horz gridview
		List<DataObject> horzData = generateGridViewObjects();
		List<DataObject> vertData = generateGridViewObjects();

		
		//Create the adapters for the gridviews
		vertGridViewAdapter = new VertGridViewAdapter(mContext,vertData);
		horzGridViewAdapter = new HorzGridViewAdapter(mContext,horzData);
		
		//Set the adapter for the gridviews
		vertGridView.setAdapter(vertGridViewAdapter);
		horzGridView.setAdapter(horzGridViewAdapter);
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
			color = Color.argb(255,red,green,blue);
			name = ""+ i;
			
			DataObject singleObject = new DataObject(name, color);
			allData.add(singleObject);			
		}
		
		return allData;
	}
	
}
