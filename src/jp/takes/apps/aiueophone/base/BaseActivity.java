package jp.takes.apps.aiueophone.base;

import jp.takes.apps.aiueophone.util.LogUtil;
import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	
	public CommonBaseActivityUtil cmnUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Activityクラス共通のUtilクラスを生成
		cmnUtil = new CommonBaseActivityUtil(this);
		
		LogUtil.d(this, "baseActivity create ", (new Throwable()).getStackTrace());

		//		this.setTitle("availMem  :　" + commonUtil.getAvailMemorySize() + "MB");
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
