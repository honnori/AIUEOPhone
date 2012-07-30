package jp.takes.apps.aiueophone.data;

/**
 * 電話帳から取得したデータを格納します。
 * @author take
 *
 */
public class AdressData implements Comparable<Object> {
	
	public String displayName = null;
	public String kanaName = null;
	public String phoneNum = null;

	/** 比較メソッドです。
	 * かな順にソートしたい場合に使用する
	 * @param object 比較対象のAdressDataオブジェクト
	 */
	@Override
	public int compareTo(Object object) {
		AdressData operand = (AdressData) object;
		return (this.kanaName.compareTo(operand.kanaName));
	}
}
