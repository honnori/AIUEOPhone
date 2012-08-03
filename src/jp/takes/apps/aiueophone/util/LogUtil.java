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
	public static void d(Activity obj, String message, Throwable e) {
		StackTraceElement[] ste = e.getStackTrace();
		
		LogUtil.d((String)obj.getLocalClassName(), message 
				+ String.format(" ： LINE = %s FUNC = %s"
						, ste[0].getLineNumber(), ste[0].getMethodName()));
	}


	/**
	 * 処理開始ログ
	 * @param obj
	 * @param e
	 */
	public static void startLog(Activity obj, Throwable e) {
		LogUtil.d((String)obj.getLocalClassName(), 
				String.format(">>>> START  FUNC = %s()", e.getStackTrace()[0].getMethodName())
				);
	}
	
	/**
	 * 処理終了ログ
	 * @param obj
	 * @param e
	 */
	public static void endLog(Activity obj, Throwable e) {
		LogUtil.d((String)obj.getLocalClassName(), 
				String.format("<<<< END    FUNC = %s()", e.getStackTrace()[0].getMethodName())
				);
	}
	
	
}
