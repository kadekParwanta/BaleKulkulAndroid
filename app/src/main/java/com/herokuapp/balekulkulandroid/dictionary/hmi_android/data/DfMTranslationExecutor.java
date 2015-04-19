package com.herokuapp.balekulkulandroid.dictionary.hmi_android.data;

import com.herokuapp.balekulkulandroid.dictionary.general.DictionaryException;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationExecution;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationExecutionCallback;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationParameters;
import com.herokuapp.balekulkulandroid.dictionary.translation.TranslationParametersBatch;

public class DfMTranslationExecutor implements TranslationExecutor {

	@Override
	public void setTranslationExecutionCallback(
			TranslationExecutionCallback translationResultHMIObjParam) {
		TranslationExecution.setTranslationExecutionCallback(translationResultHMIObjParam);
	}

	@Override
	public void executeTranslation(TranslationParameters translationParametersObj)
			throws DictionaryException {
		TranslationExecution.executeTranslation(translationParametersObj);
	}

	@Override
	public void executeTranslationBatch(TranslationParametersBatch translationParametersBatchObj) throws DictionaryException {
		TranslationExecution.executeTranslationBatch(translationParametersBatchObj);
	}

	@Override
	public void cancelLastTranslation() {
		TranslationExecution.cancelLastTranslation();
	}

}
