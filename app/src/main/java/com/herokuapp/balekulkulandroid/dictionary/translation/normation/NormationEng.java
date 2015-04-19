/*
DictionaryForMIDs - a free multi-language dictionary for mobile devices.
Copyright (C) 2005, 2006 Gert Nuber (dict@kugihan.de)

GPL applies - see file COPYING for copyright statement.
*/

package com.herokuapp.balekulkulandroid.dictionary.translation.normation;
import com.herokuapp.balekulkulandroid.dictionary.general.Util;

public class NormationEng extends Normation {
	public StringBuffer normateWord(StringBuffer nonNormatedWord,
                                    boolean      fromUserInput) {
		StringBuffer defaultNormatedWord = NormationLib.defaultNormation(nonNormatedWord, fromUserInput);
		return defaultNormatedWord;
	}
}
