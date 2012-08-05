package jp.takes.apps.aiueophone.base;

import jp.takes.apps.aiueophone.data.PreferenceData;
import jp.takes.apps.aiueophone.util.LogUtil;
import jp.takes.apps.aiueophone.util.Messages;
import android.app.Activity;
import android.os.Bundle;

/**
 * Activityクラスを継承して画面生成したい場合に継承する基底クラス
 * @author ict
 *
 */
public abstract class BaseActivity extends Activity {
	
	/* ユーティリティとして利用する共通部品のオブジェクト */
	public CommonBaseActivityUtil cmnUtil = null;

	/**
	 * アプリの起動時に一度だけ呼ばれる初期処理用メソッド
	 * TODO 現状、onCreateで呼ぶようにしてあるため、厳密には一度だけではないのでどうにかしたい。
	 */
	public void init() {
		// デバッグモードを初期値を設定
		PreferenceData.takeDebugModeFromSharedPreferences(this);
		
		// 文字サイズの初期値を設定
		PreferenceData.takeCaseSizeFromSharedPreferences(this);
	}

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

	/**
	 * ログ出力1
	 * @param message
	 */
	public void log(String message) {
		LogUtil.d(this, message);
	}

	/**
	 * ログ出力2
	 * @param message
	 * @param e
	 */
	public void log(String message, Throwable e) {
		LogUtil.d(this, message, e);
	}
	
	/**
	 * 処理開始ログ
	 * @param e
	 */
	public void startLog(Throwable e) {
		LogUtil.startLog(this, e);
	}
	
	/**
	 * 処理終了ログ
	 * @param e
	 */
	public void endLog(Throwable e) {
		LogUtil.endLog(this, e);
	}
	

	/**
	 * 表示する文字のサイズをSPで取得する
	 * @param act
	 * @return 文字サイズ
	 */
	public Integer getCaseSizeSP() {
		return PreferenceData.getCaseSizeSP(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		// 文字サイズを設定
		this.changeCaseSize();
	}

	/**
	 *  文字サイズを設定する。<br>
	 *  変更対象の部品のテキストサイズを設定する処理を実装する。<br>
	 *  onResume()時にCALLされる<br>
	 *  サブクラスで文字サイズ変更対象の部品の文字サイズ変更処理を実装すること<br>
	 */
	public abstract void changeCaseSize();

}
