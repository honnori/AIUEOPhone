package jp.takes.apps.aiueophone.base;

import jp.takes.apps.aiueophone.data.PreferenceData;
import jp.takes.apps.aiueophone.util.LogUtil;
import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	
	public CommonBaseActivityUtil cmnUtil = null;

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
	 *  文字サイズを設定する。
	 *  変更対象の部品のテキストサイズを設定する処理を実装する。
	 *  onResume()時にCALLされる
	 */
	public abstract void changeCaseSize();

}
