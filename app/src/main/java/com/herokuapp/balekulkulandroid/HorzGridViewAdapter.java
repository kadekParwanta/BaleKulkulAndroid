package com.herokuapp.balekulkulandroid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.herokuapp.balekulkulandroid.calendar.TempCalendarFragment;

public class HorzGridViewAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<DataObject> data;	
	
	//HorzGridView stuff
	private final int childLayoutResourceId = R.layout.horz_gridview_child_layout;
	private int columns;//Used to set childSize in TwoWayGridView
	private int rows;//used with TwoWayGridView
	private int itemPadding;
	private int columnWidth;
	private int rowHeight;

    private Calendar month;
    public GregorianCalendar pmonth; // calendar instance for previous month
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int lastWeekDay;
    int leftDays;
    int mnthlength;
    String itemvalue, curentDateString;
    DateFormat df;

    private ArrayList<String> items;
    public static List<String> dayString;
    private View previousView;

	public HorzGridViewAdapter(Context context,List<DataObject> data, GregorianCalendar monthCalendar){
		this.mContext = context;
		this.data = data;
		//Get dimensions from values folders; note that the value will change
		//based on the device size but the dimension name will remain the same
		Resources res = mContext.getResources();
		itemPadding = (int) res.getDimension(R.dimen.horz_item_padding);
		int[] rowsColumns = res.getIntArray(R.array.horz_gv_rows_columns);
		rows = rowsColumns[0];
		columns = rowsColumns[1];
		
		
		//Initialize the layout params
        TempCalendarFragment.horzGridView.setNumRows(rows);
		
		//HorzGridView size not established yet, so need to set it using a viewtreeobserver
		ViewTreeObserver vto = TempCalendarFragment.horzGridView.getViewTreeObserver();
		
		OnGlobalLayoutListener onGlobalLayoutListener = new OnGlobalLayoutListener() {
			
			@SuppressWarnings("deprecation")
			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				//First use the gridview height and width to determine child values
				rowHeight =(int)((float)(TempCalendarFragment.horzGridView.getHeight()/rows)-2*itemPadding);
				columnWidth = (int)((float)(TempCalendarFragment.horzGridView.getWidth()/columns)-2*itemPadding);

                TempCalendarFragment.horzGridView.setRowHeight(rowHeight);
				
				//Then remove the listener
				ViewTreeObserver vto = TempCalendarFragment.horzGridView.getViewTreeObserver();
				
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
					vto.removeOnGlobalLayoutListener(this);
				}else{
					vto.removeGlobalOnLayoutListener(this);
				}
				
				
				
			}
		};
		
		vto.addOnGlobalLayoutListener(onGlobalLayoutListener);

        this.dayString = new ArrayList<String>();
        Locale.setDefault(Locale.US);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());
        refreshDays();
	}

    public void setItems(ArrayList<String> items) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Get the data for the given position in the array
//		DataObject thisData = data.get(position);
		
		//Use a viewHandler to improve performance
		ViewHandler handler;
		
		//If reusing a view get the handler info; if view is null, create it
		if(convertView == null){
			
			//Only get the inflater when it's needed, then release it-which isn't frequently
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(childLayoutResourceId , parent, false);
			
			//User findViewById only when first creating the child view
			handler = new ViewHandler();
			handler.iv = (ImageView) convertView.findViewById(R.id.horz_gv_iv);
			handler.tv = (TextView) convertView.findViewById(R.id.horz_gv_tv);
			convertView.setTag(handler);
			
		}else{
			handler = (ViewHandler) convertView.getTag();
		}
		
		//Set the data outside once the handler and view are instantiated

        Calendar cal = Calendar.getInstance();
        Date tanggal = null;
        try {

            tanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    .parse(dayString.get(position));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        cal.setTime(tanggal);

        // separates daystring into parts.
        String[] separatedTime = dayString.get(position).split("-");
        // taking last part of date. ie; 2 from 2012-12-02
        String gridvalue = separatedTime[2].replaceFirst("^0*", "");
        // checking whether the day is in current month or not.
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            // setting offdays to white color.
            handler.tv.setTextColor(Color.WHITE);
            handler.tv.setClickable(false);
            handler.tv.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            handler.tv.setTextColor(Color.WHITE);
            handler.tv.setClickable(false);
            handler.tv.setFocusable(false);
        } else {
            // setting curent month's days in blue color.
            handler.tv.setTextColor(Color.BLUE);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) handler.tv.setTextColor(Color.RED);
        }

        if (dayString.get(position).equals(curentDateString)) {
            setSelected(convertView);
            previousView = convertView;
        } else {
            convertView.setBackgroundResource(R.drawable.list_item_background);
        }
        handler.tv.setText(gridvalue);

//		handler.iv.setBackgroundColor(thisData.getColor());
//		handler.tv.setText(thisData.getName());
		FrameLayout.LayoutParams lp 
			= new FrameLayout.LayoutParams(columnWidth, rowHeight);// convertView.getLayoutParams();
		handler.iv.setLayoutParams(lp);

		Log.d("HorzGVAdapter","Position:"+position+",children:"+parent.getChildCount());
		return convertView;
	}
	
	private class ViewHandler{
		ImageView iv;
		TextView tv;
	}
	

	@Override
	public int getCount() {
		
		return dayString.size();
	}

	@Override
	public Object getItem(int position) {
		
		return dayString.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

    public View setSelected(View view) {
        if (previousView != null) {
            previousView.setBackgroundResource(R.drawable.list_item_background);
        }
        previousView = view;
        view.setBackgroundResource(R.drawable.calendar_cel_selectl);
        return view;
    }

    public void refreshDays() {
        // clear items
        items.clear();
        dayString.clear();
        Locale.setDefault(Locale.US);
        pmonth = (GregorianCalendar) month.clone();
        // month start day. ie; sun, mon, etc
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
        // finding number of weeks in current month.
        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        // allocating maximum row number for the gridview.
        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
        /**
         * Calendar instance for getting a complete gridview including the three
         * month's (previous,current,next) dates.
         */
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        /**
         * setting the start date as previous month's required date.
         */
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

        /**
         * filling calendar gridview.
         */
        for (int n = 0; n < mnthlength; n++) {

            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            dayString.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }

}
