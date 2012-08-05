package jp.takes.apps.aiueophone.util;

public class CaseConverterUtil {

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

	public String changeZenkakuToHankaku(String strBrefore) {
		String strAfter = "";

		if (strBrefore != null) {
			Integer strLen = strBrefore.length();
			for (Integer i = 0; i < strLen; i++) {
				String temp = strBrefore.substring(i, i + 1);
				strAfter = strAfter + this.getHankakuFromZenkaku(temp);
			}
		}
		return strAfter;
	}

	/**
	 * 全角ひらがな、全角カタカナを半角カタカナに変更します。 一文字のString型を引数として受け取りマッチングした半角文字を返す
	 * 
	 * @param strBefore
	 * @return
	 */
	public String getHankakuFromZenkaku(String strBefore) {
		String strAfter = null;

		if (strBefore != null) {
			// 全角ひらがなからの変更
			strAfter = this.comvertZenkakuToHankaku(strBefore,
					CaseConverterUtil.ZENKAKU_HIRAGANA);

			// 全角カタカナからの変更
			if (strAfter == null) {
				// まだ設定されていないので全角カタカナとマッチング
				strAfter = this.comvertZenkakuToHankaku(strBefore,
						CaseConverterUtil.ZENKAKU_KATAKANA);
			}

			// 全角ひらがな、カタカナに一致しない場合
			if (strAfter == null) {
				// 引数をそのまま返す
				strAfter = strBefore;
			}
		}
		return strAfter;
	}

	/**
	 * 一文字をマッピングした半角カナ配列に変換して返す
	 * 
	 * @param moji
	 *            　任意の一文字
	 * @param zenkaku
	 *            　マッピング用の配列
	 * @return　対応した半角カナ文字<br>
	 *         一致する文字が無い場合、nullを返す
	 * 
	 */
	private String comvertZenkakuToHankaku(String moji, String[] zenkaku) {
		String hankaku = null;
		Integer num = zenkaku.length;
		for (Integer i = 0; i < num; i++) {
			if (moji.equals(zenkaku[i])) {
				hankaku = CaseConverterUtil.HANKAKU_KATAKANA[i];
				break;
			}
		}
		return hankaku;
	}

	/**
	 * 渡された文字列が、全てかな文字であるか判定します。
	 * 
	 * @return　true：全てかな文字　false：かな文字以外を含む
	 */
	public boolean judgeWhetherKanaString(String moji) {
		boolean flag = false;
		if (moji != null) {
			if (this.comvertZenkakuToHankaku(moji,
					CaseConverterUtil.ZENKAKU_HIRAGANA) != null) {
				flag = true;
			} else if (this.comvertZenkakuToHankaku(moji,
					CaseConverterUtil.ZENKAKU_KATAKANA) != null) {
				flag = true;
			} else if (this.comvertZenkakuToHankaku(moji,
					CaseConverterUtil.HANKAKU_KATAKANA) != null) {
				flag = true;
			}
		}
		return flag;
	}

}
