/*
DictionaryForMIDs - a free multi-language dictionary for mobile devices.
Copyright (C) 2005, 2006 Gert Nuber (dict@kugihan.de)

GPL applies - see file COPYING for copyright statement.
*/

package com.herokuapp.balekulkulandroid.dictionary.translation.normation;
import com.herokuapp.balekulkulandroid.dictionary.general.Util;

public class NormationCyr1 extends Normation {

	public StringBuffer normateWord(StringBuffer nonNormatedWord,
                                    boolean      fromUserInput) {
		StringBuffer defaultNormatedWord = NormationLib.defaultNormation(nonNormatedWord, fromUserInput);
		StringBuffer normatedWord = new StringBuffer();
		for (int charPos = 0; charPos < defaultNormatedWord.length(); ++charPos) {
			if (defaultNormatedWord.charAt(charPos) == 'а') {
				normatedWord.append("a");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'б') {
				normatedWord.append("b");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'в') {
				normatedWord.append("v");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'г') {
				normatedWord.append("g");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'д') {
				normatedWord.append("d");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'е') {
				normatedWord.append("e");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ё') {
				normatedWord.append("jo");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ж') {
				normatedWord.append("zh");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'з') {
				normatedWord.append("z");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'и') {
				normatedWord.append("i");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'й') {
				normatedWord.append("j");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'к') {
				normatedWord.append("k");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'л') {
				normatedWord.append("l");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'м') {
				normatedWord.append("m");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'н') {
				normatedWord.append("n");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'о') {
				normatedWord.append("o");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'п') {
				normatedWord.append("p");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'р') {
				normatedWord.append("r");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'с') {
				normatedWord.append("s");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'т') {
				normatedWord.append("t");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'у') {
				normatedWord.append("u");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ф') {
				normatedWord.append("f");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'х') {
				normatedWord.append("h");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ц') {
				normatedWord.append("c");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ч') {
				normatedWord.append("ch");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ш') {
				normatedWord.append("sh");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'щ') {
				normatedWord.append("shh");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ы') {
				normatedWord.append("y");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ь') {
				normatedWord.append("x");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'э') {
				normatedWord.append("eh");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ю') {
				normatedWord.append("ju");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'я') {
				normatedWord.append("ja");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'і') {
				normatedWord.append("ij");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ґ') {
				normatedWord.append("gj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ѓ') {
				normatedWord.append("gj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'є') {
				normatedWord.append("ye");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ї') {
				normatedWord.append("yi");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ѕ') {
				normatedWord.append("dz");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ј') {
				normatedWord.append("jj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'љ') {
				normatedWord.append("lj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'њ') {
				normatedWord.append("nj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ќ') {
				normatedWord.append("kj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'џ') {
				normatedWord.append("dj");
			}
			else if (defaultNormatedWord.charAt(charPos) == 'ў') {
				normatedWord.append("uj");
			}
			else {
				normatedWord.append(defaultNormatedWord.charAt(charPos));
			}
		}
		return normatedWord;
	}


}