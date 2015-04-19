/*
DictionaryForMIDs - a free multi-language dictionary for mobile devices.
Copyright (C) 2005 - 2013 Gert Nuber (dict@kugihan.de)

GPL applies - see file COPYING for copyright statement.
*/
package com.herokuapp.balekulkulandroid.dictionary.translation;

import com.herokuapp.balekulkulandroid.dictionary.general.DictionaryException;

public interface TranslationThreadIF {
	public void startTranslation() throws DictionaryException;
	public void cancelTranslation();
	public boolean translationIsCancelled();
}
