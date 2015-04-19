/*
DictionaryForMIDs - a free multi-language dictionary for mobile devices.
Copyright (C) 2005, 2006 Gert Nuber (dict@kugihan.de)

GPL applies - see file COPYING for copyright statement.
*/

package com.herokuapp.balekulkulandroid.dictionary.general;

import com.herokuapp.balekulkulandroid.dictionary.dataaccess.DictionaryDataFile;

public class CouldNotOpenPropertyFileException extends CouldNotOpenFileException {

	public CouldNotOpenPropertyFileException() {
		super("Property file could not be opened: " + DictionaryDataFile.propertyFileName);
	}
}
