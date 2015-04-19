package com.herokuapp.balekulkulandroid.dictionary.hmi_android.data;

import com.herokuapp.balekulkulandroid.dictionary.general.DictionaryException;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationExecutionCallback;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationParameters;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationParametersBatch;

public interface TranslationExecutor {

	public void setTranslationExecutionCallback(
            TranslationExecutionCallback translationResultHMIObjParam);

	public void executeTranslation(TranslationParameters translationParametersObj)
			throws DictionaryException;

	public void executeTranslationBatch(TranslationParametersBatch translationParametersBatchObj)
			throws DictionaryException;

	public void cancelLastTranslation();

}
