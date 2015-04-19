package com.herokuapp.balekulkulandroid.dictionary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herokuapp.balekulkulandroid.R;

public class DictionaryFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for mActivity
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	/**
	 * Returns a new instance of mActivity fragment for the given section number.
	 */
	public static DictionaryFragment newInstance(int sectionNumber) {
		DictionaryFragment cf = new DictionaryFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		cf.setArguments(args);
		return cf;
	}
	
	public DictionaryFragment() {
	}

    public static final String BUNDLE_DICTIONARY_ABOUT_TEXT = "dictionaryAboutText";


	
	private FragmentActivity mActivity;
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.dict_main, container,
				false);
		return rootView;
	}
	
}
