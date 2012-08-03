/**
 * copyright 2012
 */
package jp.takes.apps.aiueophone.data;

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
	private static final Integer CASE_SIZE_BIG = 36;
	// 表示文字サイズ　中
	private static final Integer CASE_SIZE_MIDDLE = 32;
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
		PreferenceData.setCaseSize(pref.getString("caseSize", ""));
	}

	/**
	 * 設定値（文字サイズ）を取得
	 * @return 文字サイズ（sp）を返却
	 */
	public static Integer getCaseSizeSP() {
		Integer caseSizeSP = 0;
		
		String size = (caseSize == null) ? DEFAULT_CASE_SIZE : caseSize;
		
		if (size.equals("1")) {
			caseSizeSP = CASE_SIZE_BIG;
		}
		else if (size.equals("2")) {
			caseSizeSP = CASE_SIZE_MIDDLE;
		}
		else if (size.equals("3")) {
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