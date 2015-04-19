/*
 * Copyright (C) 2014 Mukesh Y authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.herokuapp.balekulkulandroid.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.herokuapp.balekulkulandroid.R;

/**
 * @author Mukesh Y
 */
public class CalendarAdapter extends BaseAdapter {
	private Context mContext;

	private Calendar month;
	public GregorianCalendar pmonth; // calendar instance for previous month
	/**
	 * calendar instance for previous month for getting complete view
	 */
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

	public CalendarAdapter(Context c, GregorianCalendar monthCalendar) {
		CalendarAdapter.dayString = new ArrayList<String>();
		Locale.setDefault(Locale.US);
		month = monthCalendar;
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		mContext = c;
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

	public int getCount() {
		return dayString.size();
	}

	public Object getItem(int position) {
		return dayString.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView;
		TextView pancawaraView, dwiWaraView, triWaraView, caturWaraView, sadWaraView;
		TextView angkaBulanView;
		ImageView bulanView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_griditem, null);

		}
		dayView = (TextView) v.findViewById(R.id.text_tanggal);
		
		bulanView = (ImageView) v.findViewById(R.id.image_bulan);
		pancawaraView = (TextView) v.findViewById(R.id.text_kliwon);
		dwiWaraView = (TextView)v.findViewById(R.id.text_pepet);
		triWaraView = (TextView)v.findViewById(R.id.text_pasah);
		caturWaraView = (TextView)v.findViewById(R.id.text_jaya);
		sadWaraView = (TextView)v.findViewById(R.id.text_mina);
		angkaBulanView = (TextView)v.findViewById(R.id.text_11);
		
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

		dwiWaraView.setText(getDwiWara(cal));
		triWaraView.setText(getTriWara(cal));
		caturWaraView.setText(getCaturWara(cal));
		pancawaraView.setText(getPancaWara(cal));
		sadWaraView.setText(getSadWara(cal));

		angkaBulan bulan = getBulanType(cal);
		int urutan = bulan.angka;
		angkaBulanView.setText(Integer.toString(urutan));
		switch (bulan.bulanType) {
		case NONE:
			bulanView.setVisibility(View.INVISIBLE);
			break;
		case PURNAMA:
			bulanView.setVisibility(View.VISIBLE);
			bulanView.setBackgroundResource(R.drawable.solid_red);
			break;
		case TILEM:
			bulanView.setVisibility(View.VISIBLE);
			bulanView.setBackgroundResource(R.drawable.solid_black);
			break;
		}



		// separates daystring into parts.
		String[] separatedTime = dayString.get(position).split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");
		// checking whether the day is in current month or not.
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
			// setting offdays to white color.
			dayView.setTextColor(Color.WHITE);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
			dayView.setTextColor(Color.WHITE);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else {
			// setting curent month's days in blue color.
			dayView.setTextColor(Color.BLUE);
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) dayView.setTextColor(Color.RED);
		}

		if (dayString.get(position).equals(curentDateString)) {
			setSelected(v);
			previousView = v;
		} else {
			v.setBackgroundResource(R.drawable.list_item_background);
		}
		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1) {
			date = "0" + date;
		}
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		ImageView iw = (ImageView) v.findViewById(R.id.icon_tanggal);
		if (date.length() > 0 && items != null && items.contains(date)) {
			iw.setVisibility(View.VISIBLE);
		} else {
			iw.setVisibility(View.INVISIBLE);
		}
		return v;
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
	
	public static long getTotalDays(Calendar endDate) {  

		 int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;  
		 long endInstant = endDate.getTimeInMillis();  
		 int presumedDays = (int) ((endInstant) / MILLIS_IN_DAY);  
		 return presumedDays;  
	} 
	
	public static String getPancaWara(final Calendar date) {
		int days = getDays(date);
		if (days < 0) return "";
		int seq = (int) days % 5;
		ArrayList<String> pancawara = new ArrayList<String>();
		pancawara.add("Umanis");
		pancawara.add("Paing");
		pancawara.add("Pon");
		pancawara.add("Wage");
		pancawara.add("Kliwon");
		return pancawara.get(seq);
	}

	public static String getDwiWara(final Calendar date) {
		int days = getDays(date);
		if (days < 0) return "";
		int seq = (int) days % 2;
		ArrayList<String> dwiwara = new ArrayList<String>();
		dwiwara.add("Menga");
		dwiwara.add("Pepet");
		return dwiwara.get(seq);
	}
	
	public static String getTriWara(final Calendar date) {
		int days = getDays(date);
		if (days < 0) return "";
		int seq = (int) days % 3;
		ArrayList<String> triwara = new ArrayList<String>();
		triwara.add("Pasah");
		triwara.add("Beteng");
		triwara.add("Kajeng");
		return triwara.get(seq);
	}

	public static String getCaturWara(final Calendar date) {
		int days = getDays(date);
		if (days < 0) return "";
		int seq = (int) days % 4;
		ArrayList<String> caturwara = new ArrayList<String>();
		caturwara.add("Sri");
		caturwara.add("Laba");
		caturwara.add("Jaya");
		caturwara.add("Menala");
		return caturwara.get(seq);
	}

	public static String getSadWara(final Calendar date) {
		int days = getDays(date);
		if (days < 0) return "";
		int seq = (int) days % 6;
		ArrayList<String> sadwara = new ArrayList<String>();
		sadwara.add("Wong");
		sadwara.add("Sato");
		sadwara.add("Mina");
		sadwara.add("Manuk");
		sadwara.add("Taru");
		sadwara.add("Buku");
		return sadwara.get(seq);
	}

	public static enum Bulan {
		PURNAMA,
		TILEM,
		NONE
	}

	public static class angkaBulan {
		Bulan bulanType = Bulan.NONE;
		int angka = 0;
		public angkaBulan(Bulan bulan, int urutan) {
			this.angka = urutan;
			this.bulanType = bulan;
		}
	}

	public angkaBulan getBulanType(Calendar date) {
		int days = getDays(date);
		int pengurangan = (int)Math.floor(days/62);
		int a = days % 15;
		a = (a + pengurangan) % 15;
		if (days % 62 == 0) {
			a -=1;
		}
		if (a == 0) {
			return new angkaBulan(Bulan.PURNAMA, a);
/*		} else if (a == 1) {
			return new angkaBulan(Bulan.TILEM, a);*/
		} else {
			return new angkaBulan(Bulan.NONE, a);
		}
	}

	public static int getDays(Calendar now) {
		Calendar tmp = Calendar.getInstance();
	      tmp.set(0,0,0); // init a temporary calendar.
	      int days=0;
	      // iterate from 1900 and check how many days are in the year, sum the days 
	      for (int i=1900; i < now.get(Calendar.YEAR);i++) {
	          tmp.set(Calendar.YEAR, i);
	          int daysForThatYear = tmp.getActualMaximum(Calendar.DAY_OF_YEAR);
	          days+=daysForThatYear;
	      }
	      // check the number of days for the current year, and add to the total of days
	      int daysThisYear = now.get(Calendar.DAY_OF_YEAR);
	      days+=daysThisYear;
	      return days;
	}
}