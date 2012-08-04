/**
 * copyright 2012
 */
package jp.takes.apps.aiueophone.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * アプリ設定画面で設定した情報をクラス変数として保持するための静的クラス
 * @author take
 */
public class PreferenceData {

	// 文字サイズのデフォルト値　中
	public static final String DEFAULT_CASE_SIZE = "2";
	// 表示文字サイズ　大
	private static final Integer CASE_SIZE_BIG = 34;
	// 表示文字サイズ　中
	private static final Integer CASE_SIZE_MIDDLE = 30;
	// 表示文字サイズ　小
	private static final Integer CASE_SIZE_SMALL = 26;

	
	private static boolean debugMode = false;
	private static String caseSize = null;
	
	/**
	 * データ保存領域からデバッグモード設定を取得
	 * @param context　カレントのコンテキストクラス
	 */
	public static void takeDebugModeFromSharedPreferences(Context context) {
		// 
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		PreferenceData.setdebugMode(pref.getBoolean("debugMode", false));
		
	}
	
	/**
	 * データ保存領域から文字サイズ設定を取得
	 * @param context　カレントのコンテキストクラス
	 */
	public static void takeCaseSizeFromSharedPreferences(Context context) {
		// データ保存領域から文字サイズ設定を取得
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		String size = pref.getString("caseSize", DEFAULT_CASE_SIZE);
		
		// 文字列が空の場合、デフォルト値を設定
		if (size.equals("")) {
			size = DEFAULT_CASE_SIZE;
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("caseSize", DEFAULT_CASE_SIZE);
			editor.commit();
		}
		
		PreferenceData.setCaseSize(size);
	}

	/**
	 * 設定値（文字サイズ）を取得
	 * @return 文字サイズ（sp）を返却
	 */
	public static Integer getCaseSizeSP(Activity act) {
		// デフォルト値は中サイズ
		Integer caseSizeSP = CASE_SIZE_MIDDLE;
		
		String size = (caseSize == null) ? DEFAULT_CASE_SIZE : caseSize;
		
		if (size.equals(act.getString(jp.takes.apps.aiueophone.R.string.caseSizeBig))) {
			caseSizeSP = CASE_SIZE_BIG;
		}
		else if (size.equals(act.getString(jp.takes.apps.aiueophone.R.string.caseSizeMiddle))) {
			caseSizeSP = CASE_SIZE_MIDDLE;
		}
		else if (size.equals(act.getString(jp.takes.apps.aiueophone.R.string.caseSizeSmall))) {
			caseSizeSP = CASE_SIZE_SMALL;
		}
		
		return caseSizeSP;
	}
	
	public static boolean isdebugMode() {
		return debugMode;
	}

	public static void setdebugMode(boolean debugFlag) {
		PreferenceData.debugMode = debugFlag;
	}
	
	public static String getCaseSize() {
		return caseSize;
	}
	
	public static void setCaseSize(String caseSize) {
		PreferenceData.caseSize = caseSize;
	}

}