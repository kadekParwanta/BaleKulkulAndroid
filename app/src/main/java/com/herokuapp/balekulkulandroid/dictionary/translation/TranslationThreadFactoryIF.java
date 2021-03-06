/*
DictionaryForMIDs - a free multi-language dictionary for mobile devices.
Copyright (C) 2005 - 2013 Gert Nuber (dict@kugihan.de)

GPL applies - see file COPYING for copyright statement.
*/
package com.herokuapp.balekulkulandroid.dictionary.translation;

public interface TranslationThreadFactoryIF {
	TranslationThreadIF getTranslationThread(TranslationThreadCallbackIF translationThreadCallbackObj,
                                             TranslationParameters translationParametersObj);
}
