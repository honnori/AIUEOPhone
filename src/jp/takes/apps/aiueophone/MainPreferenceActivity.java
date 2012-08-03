package jp.takes.apps.aiueophone;

import jp.takes.apps.aiueophone.base.BasePreferenceActivity;
import jp.takes.apps.aiueophone.data.PreferenceData;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainPreferenceActivity extends BasePreferenceActivity implements OnPreferenceChangeListener{

	// ListPreference
	private ListPreference listPref = null;
	// CheckBoxPreference
	private CheckBoxPreference cbp = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.pref);
        
        // 保存データ領域へアクセス
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

		// ListPreferenceの取得
        listPref = (ListPreference)this.findPreference("caseSize");
        listPref.setOnPreferenceChangeListener(this);

		// 文字サイズ設定の初期表示
		String param = pref.getString("caseSize", "");
		listPref.setDefaultValue(param);
		this.setSummary(param);

		// デバッグモードの初期表示
		cbp = (CheckBoxPreference)this.findPreference("debugMode");
		cbp.setOnPreferenceChangeListener(this);
        boolean debugFlag = pref.getBoolean("debugMode", false);
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
		listPref.setSummary(String.format("現在の設定は「%s」です", sizes[Integer.parseInt(param) - 1]));
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
