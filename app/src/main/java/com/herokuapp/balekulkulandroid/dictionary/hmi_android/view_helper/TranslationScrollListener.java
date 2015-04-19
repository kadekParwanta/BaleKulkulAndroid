package com.herokuapp.balekulkulandroid.dictionary.hmi_android.view_helper;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CheckBox;
import com.herokuapp.balekulkulandroid.dictionary.hmi_android.Preferences;
import com.herokuapp.balekulkulandroid.R;
import com.herokuapp.balekulkulandroid.dictionary.hmi_android.data.StarredWordsProvider;
import com.herokuapp.balekulkulandroid.dictionary.translation.SingleTranslationExtension;

/**
 * ScollListener that lazy-loads starred state of words from the database.
 *
 */
public class TranslationScrollListener implements OnScrollListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onScrollStateChanged(AbsListView listView, int scrollState) {
		if (scrollState != OnScrollListener.SCROLL_STATE_IDLE) {
			return;
		}
		updateView(listView);
	}

	/**
	 * Updates all visible item's starred state.
	 *
	 * @param listView
	 *            the list view to update
	 */
	private void updateView(AbsListView listView) {
		if (!Preferences.getIsStarredWordsEnabled()) {
			// no need to update if starring is disabled
			return;
		}
		final int first = listView.getFirstVisiblePosition();
		final int count = listView.getChildCount();
		for (int i = 0; i < count; i++) {
			final View item = listView.getChildAt(i);
			// TODO: save check state to view holder?
//			if (item.getTag() != null) {
//				// item has already been updated
//				continue;
//			}
			final Object object = listView.getAdapter().getItem(first + i);
			if (!(object instanceof SingleTranslationExtension)) {
				// Ignore header elements in list
				return;
			}
			final SingleTranslationExtension translation = (SingleTranslationExtension) object;
			if (!translation.isStarredLoaded()) {
				final Long itemId = StarredWordsProvider.getItemId(listView.getContext().getContentResolver(),
						translation);
				if (itemId == null) {
					translation.setStarred(false);
				} else {
					translation.setStarred(true);
				}
			}
			final CheckBox checkBox = (CheckBox) item.findViewById(R.id.checkBoxStar);
			checkBox.setChecked(translation.isStarred());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
		updateView(view);
	}
}
