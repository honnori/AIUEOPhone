package jp.takes.apps.aiueophone.util;

import jp.takes.apps.aiueophone.data.PreferenceData;
import android.app.Activity;
import android.util.Log;

public class LogUtil {

	// TODO 出力先を個別の別ファイルに切り替える機能を付ける？
	
	public static void d(String tag, String message) {
		if (PreferenceData.isdebugMode()) {
			Log.d(tag, message);
		}
	}

	public static void d(Activity obj, String message) {
		LogUtil.d((String)obj.getLocalClassName(), message);
	}

	
	// スタックトレースを表示します。
	public static void d(Activity obj, String message, StackTraceElement[] ste) {
		LogUtil.d((String)obj.getLocalClassName(), message 
				+ String.format(" ：FILE=%s LINE=%s FUNC=%s"
						, ste[0].getFileName(), ste[0].getLineNumber(), ste[0].getMethodName()));
	}

	// デバッグモードflagが真の場合にログを出力
	public static void d(String tag, String message, boolean flag) {
		if (flag) {
			Log.d(tag, message);
		}
	}

}
