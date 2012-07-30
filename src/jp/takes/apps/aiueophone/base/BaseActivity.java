package jp.takes.apps.aiueophone.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class BaseActivity extends Activity {
	
	public CommonActivityUtil commonUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Activityクラス共通のUtilクラスを生成
		commonUtil = new CommonActivityUtil(this);
		
		// 画面縦横を固定
		commonUtil.controlOrientationFix(true);
		
		this.setTitle("availMem  :　" + commonUtil.getAvailMemorySize() + "MB");
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		// TODO １秒ごとなどで表示しなおしたいが、とりあえず今は、タッチイベント時に空きメモリ量を再表示
		this.setTitle("availMem  :" + commonUtil.getAvailMemorySize() + "MB");
		
		return super.onTouchEvent(event);
	}
}
