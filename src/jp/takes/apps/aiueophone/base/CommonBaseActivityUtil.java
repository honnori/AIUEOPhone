package jp.takes.apps.aiueophone.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

/**
 * 基底クラスと連携してアプリ全体に反映させる表示や処理を実装するためのクラス
 * また、全体で使用するUtilityメソッドを実装する
 * @author take
 *
 */
public class CommonBaseActivityUtil {
	// 現在Activity
	private Activity currentActivity = null;

	public CommonBaseActivityUtil(Activity activity) {
		super();
		
		// 対象のActivityを設定
		this.currentActivity = activity;
	}

	/**
	 * 縦横固定の設定をActivityに適用する
	 * @param fixOrient 固定するならtrue，回転するように戻すならfalse
	 */
	public void controlOrientationFix(boolean fixOrient) {
		if (fixOrient) {
			int ori = currentActivity.getResources().getConfiguration().orientation;
			if (ori == Configuration.ORIENTATION_LANDSCAPE) {
				currentActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			} else {
				currentActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
		} else {
			currentActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED );
		}
	}

	
	/**
	 * 	空きメモリ量取得
	 * @return 使用可能メモリ量（MB）
	 */
	public long getAvailMemorySize() {
		long size = 0;

		ActivityManager activityManager = ((ActivityManager) currentActivity.getSystemService(Context.ACTIVITY_SERVICE));

		//メモリ情報の取得
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		size = memoryInfo.availMem;		// 使用可能メモリ
		
		return size/1000000;
	}
}

