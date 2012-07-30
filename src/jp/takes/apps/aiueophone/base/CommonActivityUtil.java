package jp.takes.apps.aiueophone.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

public class CommonActivityUtil {
	Activity currentActivity = null;

	public CommonActivityUtil(Activity activity) {
		super();
		
		currentActivity = activity;
	}

	/**
	 * アラートダイアログを表示する(リスト選択形式)
	 * @param context OnClickListenerを実装したActivityクラスのオブジェクト<br>
	 *                contextのonClic()メソッドで選択時のアクションを記述する。
	 * @param list　　　表示する一覧
	 * @param title　　ダイアログのタイトルOnClickListener
	 */
	public void showAlert(Context context, String title, String[] list) {
        // 候補一覧表示のダイアログ生成
        AlertDialog.Builder myDialogBuilder = new AlertDialog.Builder(context);
        myDialogBuilder.setTitle(title);
        myDialogBuilder.setItems(list, (android.content.DialogInterface.OnClickListener)context);
        myDialogBuilder.setNeutralButton(android.R.string.cancel, null);
        myDialogBuilder.setCancelable(true);
        AlertDialog myAlertDialog = myDialogBuilder.create();
        myAlertDialog.show();
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

