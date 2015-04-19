package com.herokuapp.balekulkulandroid.dictionary.translation;


public interface TranslationExecutionCallback {
	void deletePreviousTranslationResult();
	void newTranslationResult(TranslationResult resultOfTranslation);
}
