package jp.takes.apps.aiueophone.base;

import jp.takes.apps.aiueophone.util.Messages;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * PreferenceActivityクラスを継承して画面生成したい場合に継承する基底クラス
 * @author ict
 *
 */
public class BasePreferenceActivity extends PreferenceActivity {
	public CommonBaseActivityUtil cmnUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Activityクラス共通のUtilクラスを生成
		cmnUtil = new CommonBaseActivityUtil(this);
	}
	
	/**
	 * メッセージIDに対応するメッセージ本文を取得する 　可変文字列無し
	 * 指定した可変文字列パラメタの数が合わない場合、ただしく取得できません。
	 * @param message
	 * @return
	 */
	public String getMessage(String message) {
		return Messages.getMessage(this, message, null);
	}

	/**
	 * メッセージIDに対応するメッセージ本文を取得する 　可変文字列が１つ定義されているメッセージで使用する
	 * 指定した可変文字列パラメタの数が合わない場合、ただしく取得できません。
	 * @param message
	 * @return
	 */
	public String getMessage(String message, String arg1) {
		return Messages.getMessage(this, message, new String[] {arg1});
	}

	
	/**
	 * メッセージIDに対応するメッセージ本文を取得する 　可変文字列が２つ定義されているメッセージで使用する
	 * 指定した可変文字列パラメタの数が合わない場合、ただしく取得できません。
	 * @param message
	 * @return
	 */
	public String getMessage(String message, String arg1, String arg2) {
		return Messages.getMessage(this, message, new String[] {arg1, arg2});
	}

	/**
	 * メッセージIDに対応するメッセージ本文を取得する 　可変文字列が３つ定義されているメッセージで使用する
	 * 指定した可変文字列パラメタの数が合わない場合、ただしく取得できません。
	 * @param message
	 * @return
	 */
	public String getMessage(String message, String arg1, String arg2, String arg3) {
		return Messages.getMessage(this, message, new String[] {arg1, arg2, arg3});
	}

}
