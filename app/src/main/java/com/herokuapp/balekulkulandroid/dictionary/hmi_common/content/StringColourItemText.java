package com.herokuapp.balekulkulandroid.dictionary.hmi_common.content;

import java.util.Vector;

public class StringColourItemText {
	protected Vector itemTextParts = new Vector();  // a Vector of StringColourItemTextPart
	
	public StringColourItemTextPart getItemTextPart(int index) {
		return (StringColourItemTextPart) itemTextParts.elementAt(index);
	}
	
	public void addItemTextPart(StringColourItemTextPart itemTextPartParam) {
		itemTextParts.addElement(itemTextPartParam);
	}
	
	public int size()
	{
		return itemTextParts.size();
	}
}
