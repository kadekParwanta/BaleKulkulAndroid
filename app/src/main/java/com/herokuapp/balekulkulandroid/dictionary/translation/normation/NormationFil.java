/*
DictionaryForMIDs - a free multi-language dictionary for mobile devices.
Copyright (C) 2005, 2006 Gert Nuber (dict@kugihan.de)

GPL applies - see file COPYING for copyright statement.
*/

package com.herokuapp.balekulkulandroid.dictionary.translation.normation;
import com.herokuapp.balekulkulandroid.dictionary.general.Util;

public class NormationFil extends Normation {

	NormationLat normationLatObj = null;
	
	NormationLat getNormationLatObj() {
		if (normationLatObj == null)
			normationLatObj = new NormationLat();
		return normationLatObj;
	}
	
	public StringBuffer normateWord(StringBuffer nonNormatedWord,
                                    boolean      fromUserInput) {
		// apply NormationLat, because some Filipino use accents
		StringBuffer defaultNormatedWord = getNormationLatObj().normateWord(nonNormatedWord, fromUserInput);
		StringBuffer normatedWord = new StringBuffer();
		for (int charPos = 0; charPos < defaultNormatedWord.length(); ++charPos) {
			if (defaultNormatedWord.charAt(charPos) == 'b') {
				normatedWord.append("v");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'i') {
				normatedWord.append("e");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'o') {
				normatedWord.append("u");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'f') {
				normatedWord.append("p");
			}
			else {
				normatedWord.append(defaultNormatedWord.charAt(charPos));
			}
		}
		return normatedWord;
	}
}
