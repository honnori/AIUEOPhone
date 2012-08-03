package jp.takes.apps.aiueophone.base;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class BasePreferenceActivity extends PreferenceActivity {
	public CommonBaseActivityUtil cmnUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Activityクラス共通のUtilクラスを生成
		cmnUtil = new CommonBaseActivityUtil(this);
	}
}
