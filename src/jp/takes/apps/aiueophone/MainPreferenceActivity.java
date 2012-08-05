package jp.takes.apps.aiueophone;

import jp.takes.apps.aiueophone.base.BasePreferenceActivity;
import jp.takes.apps.aiueophone.data.PreferenceData;
import jp.takes.apps.aiueophone.util.Messages;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * 設定画面のActivity
 * @author ict
 *
 */
public class MainPreferenceActivity extends BasePreferenceActivity implements OnPreferenceChangeListener{

	/* ListPreference 文字サイズ選択用のリスト */
	private ListPreference listPref = null;
	
	/* CheckBoxPreference デバッグモード設定用のチェックボックス */
	private CheckBoxPreference cbp = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.pref);

		// 保存データ領域へアクセス
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

		// 文字サイズ設定オブジェクトの取得
		listPref = (ListPreference) this.findPreference(this.getString(R.string.caseSize));

		// 文字サイズ設定オブジェクトののリスナーを設定
		listPref.setOnPreferenceChangeListener(this);

		// 文字サイズ設定の初期表示
		String param = pref.getString(listPref.getKey(), PreferenceData.DEFAULT_CASE_SIZE);
		listPref.setDefaultValue(param);
		listPref.setValueIndex(Integer.parseInt(param) -1);
		this.setSummary(param);

		// デバッグモードの設定オブジェクトの取得
		cbp = (CheckBoxPreference)this.findPreference(this.getString(R.string.debugMode));

		// デバッグモードの設定オブジェクトのリスナーを設定
		cbp.setOnPreferenceChangeListener(this);

		// デバッグモードの初期表示
		boolean debugFlag = pref.getBoolean(cbp.getKey(), false);
		cbp.setDefaultValue(debugFlag);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		
		// 変更されたのがどのPreference項目なのか判定する
		if (preference instanceof CheckBoxPreference) {
			// デバッグモードを設定
			if (newValue != null) {
				PreferenceData.setdebugMode((Boolean)newValue);
				return true;
			}
		}
		else if (preference instanceof ListPreference) {
			// 文字サイズを設定
			if (newValue != null) {
				this.setSummary((String) newValue);
				PreferenceData.setCaseSize((String) newValue);
				return true;
			}
		}
		
		return false;
	}
	
	// Summaryを設定
	public void setSummary(String param) {
		CharSequence[] sizes = listPref.getEntries();
		listPref.setSummary(this.getMessage(Messages.I0004, sizes[Integer.parseInt(param) - 1].toString()));
	}

	// Activity破棄時に実行
	public void onDestroy() {
		super.onDestroy();
		listPref.setOnPreferenceChangeListener(null);
		listPref = null;
		cbp.setOnPreferenceChangeListener(null);
		cbp = null;
	}

	// Activityの再開時に実行
	public void onRestart() {
		super.onRestart();
		listPref.setEnabled(true);
		cbp.setEnabled(true);
	}

	// Activityの停止時に実行
	public void onStop() {
		super.onStop();
		listPref.setEnabled(false);
		cbp.setEnabled(false);
	}

}
