package jp.takes.apps.aiueophone.util;

public class CaseConverterUtil {
	
	public static int MODE_HAN = 0;
	public static int MODE_ZEN = 1;

	private static final String[] ZENKAKU_HIRAGANA = { "ぁ", "あ", "ぃ", "い", "ぅ",
			"う", "ぇ", "え", "ぉ", "お", "か", "が", "き", "ぎ", "く", "ぐ", "け", "げ",
			"こ", "ご", "さ", "ざ", "し", "じ", "す", "ず", "せ", "ぜ", "そ", "ぞ", "た",
			"だ", "ち", "ぢ", "っ", "つ", "づ", "て", "で", "と", "ど", "な", "に", "ぬ",
			"ね", "の", "は", "ば", "ぱ", "ひ", "び", "ぴ", "ふ", "ぶ", "ぷ", "へ", "べ",
			"ぺ", "ほ", "ぼ", "ぽ", "ま", "み", "む", "め", "も", "ゃ", "や", "ゅ", "ゆ",
			"ょ", "よ", "ら", "り", "る", "れ", "ろ", "ゎ", "わ", "ゐ", "ゑ", "を", "ん",
			"ヴ", "ヵ", "ヶ", "ー" };

	private static final String[] ZENKAKU_KATAKANA = { "ァ", "ア", "ィ", "イ", "ゥ",
			"ウ", "ェ", "エ", "ォ", "オ", "カ", "ガ", "キ", "ギ", "ク", "グ", "ケ", "ゲ",
			"コ", "ゴ", "サ", "ザ", "シ", "ジ", "ス", "ズ", "セ", "ゼ", "ソ", "ゾ", "タ",
			"ダ", "チ", "ヂ", "ッ", "ツ", "ヅ", "テ", "デ", "ト", "ド", "ナ", "ニ", "ヌ",
			"ネ", "ノ", "ハ", "バ", "パ", "ヒ", "ビ", "ピ", "フ", "ブ", "プ", "ヘ", "ベ",
			"ペ", "ホ", "ボ", "ポ", "マ", "ミ", "ム", "メ", "モ", "ャ", "ヤ", "ュ", "ユ",
			"ョ", "ヨ", "ラ", "リ", "ル", "レ", "ロ", "ヮ", "ワ", "ヰ", "ヱ", "ヲ", "ン",
			"ヴ", "ヵ", "ヶ", "―" };

	private static final String[] HANKAKU_KATAKANA = { "ｧ", "ｱ", "ｨ", "ｲ", "ｩ",
			"ｳ", "ｪ", "ｴ", "ｫ", "ｵ", "ｶ", "ｶﾞ", "ｷ", "ｷﾞ", "ｸ", "ｸﾞ", "ｹ",
			"ｹﾞ", "ｺ", "ｺﾞ", "ｻ", "ｻﾞ", "ｼ", "ｼﾞ", "ｽ", "ｽﾞ", "ｾ", "ｾﾞ", "ｿ",
			"ｿﾞ", "ﾀ", "ﾀﾞ", "ﾁ", "ﾁﾞ", "ｯ", "ﾂ", "ﾂﾞ", "ﾃ", "ﾃﾞ", "ﾄ", "ﾄﾞ",
			"ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾊﾞ", "ﾊﾟ", "ﾋ", "ﾋﾞ", "ﾋﾟ", "ﾌ",
			"ﾌﾞ", "ﾌﾟ", "ﾍ", "ﾍﾞ", "ﾍﾟ", "ﾎ", "ﾎﾞ", "ﾎﾟ", "ﾏ", "ﾐ", "ﾑ", "ﾒ",
			"ﾓ", "ｬ", "ﾔ", "ｭ", "ﾕ", "ｮ", "ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ﾜ",
			"ﾜ", "ｲ", "ｴ", "ｦ", "ﾝ", "ｳﾞ", "ｶ", "ｹ", "-" };

	/**
	 * コンストラクタ
	 */
	public CaseConverterUtil() {
		super();
	}

	/**
	 * 
	 * @param category 変換種別　半角カナへ変換：0　　全角カタカナへ変換：1
	 * @param strBrefore
	 * @return
	 */
	public String changeKanaCode(Integer category, String strBrefore) {
		String strAfter = "";

		if (strBrefore != null) {
			Integer strLen = strBrefore.length();
			for (Integer i = 0; i < strLen; i++) {
				String temp = strBrefore.substring(i, i + 1);
				if (category == 1) {
					// 全角カタカナへの変換時は、2文字目が濁点・半濁点の場合、一文字目と1セットとみなす
					// 文字が半角カナで濁点、半濁点がつく可能性がある文字の場合、処理を実施する
					if ("ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎ".indexOf(temp) >= 0) {
						// 次の文字が濁点／半濁典がそれ以外かを判断する。
						String nextCase = strBrefore.substring(i + 1, i + 2);
						if ("ﾞﾟ".indexOf(nextCase) >= 0) {
							// 濁点、半濁点があった場合
							temp += nextCase;
							i++;
						}
					}
				}
				temp = convertToHankakuKana(category, temp);
				
				strAfter = strAfter + temp;
			}
		}
		return strAfter;
	}
	

	/**
	 * 全角ひらがな、全角カタカナを半角カタカナに変更します。
	 * 一文字のString型を引数として受け取りマッチングした全角カタカナ文字を返す
	 * 
	 * @param strBefore
	 * @return
	 */
	public String convertToHankakuKana(Integer category, String strBefore) {
		String strAfter = null;

		if (strBefore != null) {
			
			if (category == 0) {	// 半角カナへの変更
				// 全角ひらがな→半角カナの変更
				strAfter = this.convertOneCaseKanamoji(strBefore, CaseConverterUtil.ZENKAKU_HIRAGANA, CaseConverterUtil.HANKAKU_KATAKANA);
				if (strAfter == null) {
					// 全角カタカナから→半角カナの変更
					strAfter = this.convertOneCaseKanamoji(strBefore, CaseConverterUtil.ZENKAKU_KATAKANA, CaseConverterUtil.HANKAKU_KATAKANA);
				}
			}
			else if (category == 1) {	// 全角カナへの変更
				// 半角カナ→全角カタカナの変更
				strAfter = this.convertOneCaseKanamoji(strBefore, CaseConverterUtil.HANKAKU_KATAKANA, CaseConverterUtil.ZENKAKU_KATAKANA);
				if (strAfter == null) {
					// 全角ひらがな→全角カタカナの変更
					strAfter = this.convertOneCaseKanamoji(strBefore, CaseConverterUtil.ZENKAKU_HIRAGANA, CaseConverterUtil.ZENKAKU_KATAKANA);
				}
			}

			if (strAfter == null) {
				// 変換されなかった場合、変換前の値を設定
				strAfter = strBefore;
			}

		}
		return strAfter;
	}


	/**
	 * 一文字を指定したマッピングで変換して返す
	 * 
	 * @param before
	 *            　任意の一文字
	 * @param from
	 *            　マッピング用の配列
	 * @return　対応したカナ文字<br>
	 *         一致する文字が無い場合、nullを返す
	 * 
	 */
	private String convertOneCaseKanamoji(String before, String[] from, String[] to) {
		String after = null;
		for (Integer i = 0; i < from.length; i++) {
			if (before.equals(from[i])) {
				after = to[i];
				break;
			}
		}
		return after;
	}
	
	/**
	 * 渡された文字列が、全てかな文字であるか判定します。
	 * 
	 * @return　true：全てかな文字　false：かな文字以外を含む
	 */
	public boolean judgeWhetherKanaString(String mojiretu) {
		
		boolean flag = false;
		if (mojiretu != null) {
			Integer length = mojiretu.length();
			for (int i = 0; i < length; i++) {
				// フラグ初期化
				flag = false;
				String moji = mojiretu.substring(i, i+1);
				
				if (this.convertOneCaseKanamoji(moji,
						CaseConverterUtil.ZENKAKU_HIRAGANA, CaseConverterUtil.HANKAKU_KATAKANA) != null) {
					flag = true;
				} else if (this.convertOneCaseKanamoji(moji,
						CaseConverterUtil.ZENKAKU_KATAKANA, CaseConverterUtil.HANKAKU_KATAKANA) != null) {
					flag = true;
				} else if (this.convertOneCaseKanamoji(moji,
						CaseConverterUtil.HANKAKU_KATAKANA, CaseConverterUtil.HANKAKU_KATAKANA) != null) {
					flag = true;
				}
				
				if (flag == false) {
					// かな以外が存在する。
					break;
				}
			}
		}
		return flag;
	}

}
