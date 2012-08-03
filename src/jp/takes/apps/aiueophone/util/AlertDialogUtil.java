package jp.takes.apps.aiueophone.util;

import android.app.AlertDialog;
import android.content.Context;

public class AlertDialogUtil {

	/**
	 * アラートダイアログを表示する(リスト選択形式)
	 * @param context DialogInterface.OnClickListenerを実装したActivityクラスのオブジェクト<br>
	 *                contextのonClic()メソッドで選択時のアクションを記述する。
	 * @param list　　　表示する一覧
	 * @param title　　ダイアログのタイトル
	 */
	public static void showAlert(Context context, String title, String[] list) {
		// 候補一覧表示のダイアログ生成
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setItems(list, (android.content.DialogInterface.OnClickListener) context);
		builder.setNeutralButton(android.R.string.cancel, null);
		builder.setCancelable(true);
		AlertDialog myAlertDialog = builder.create();
		myAlertDialog.show();
	}

}
