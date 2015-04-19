package com.herokuapp.balekulkulandroid.dictionary.general;

public class DictionaryClassNotLoadedException extends DictionaryException {
	public DictionaryClassNotLoadedException(Throwable t) {
		super(t);
	}

	public DictionaryClassNotLoadedException(String message) {
		super(message);
	}
}
