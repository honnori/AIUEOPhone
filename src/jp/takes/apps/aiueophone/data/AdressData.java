package jp.takes.apps.aiueophone.data;

/**
 * 電話帳から取得したデータを格納します。
 * @author take
 *
 */
public class AdressData implements Comparable<Object> {
	
	/* 電話相手の名前 */
	public String displayName = null;
	/* 電話番号 */
	public String phoneNum = null;
	/* フリガナ（全角カタカナ） */
	public String kanaNameZen = null;
	/* フリガナ（半角カタカナ） */
	public String kanaNameHan = null;

	/** 比較メソッドです。
	 * かな順にソートしたい場合に使用する
	 * @param object 比較対象のAdressDataオブジェクト
	 */
	@Override
	public int compareTo(Object object) {
		AdressData operand = (AdressData) object;
		return (this.kanaNameZen.compareTo(operand.kanaNameZen));
	}
}
