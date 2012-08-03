package jp.takes.apps.aiueophone;

import java.util.ArrayList;
import java.util.HashMap;

import jp.takes.apps.aiueophone.base.BaseActivity;
import jp.takes.apps.aiueophone.data.AdressData;
import jp.takes.apps.aiueophone.data.PreferenceData;
import jp.takes.apps.aiueophone.util.CaseConverterUtil;
import jp.takes.apps.aiueophone.util.LogUtil;
import jp.takes.apps.aiueophone.util.ToastUtil;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class AdressListActivity extends BaseActivity {
	
	public	AdressData[] adressList = null;
	public	String selectCase = null;
	public	Integer position = 0;
	private	Integer displayBarNum = 4;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adress_list);
        
        Intent i = this.getIntent();
        selectCase = i.getStringExtra("dan");
        
        this.showAdressList();
    }
    
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	/**
	 * 電話相手の名前が押下された場合
	 * 電話相手の詳細画面へ遷移
	 * @param view
	 */
	public void pressedNameButton(View view) {

		Integer pos = -1;
		Button btn = (Button)view;
		Integer id = btn.getId();
		
		if (id == R.id.Button01) {
			pos = 0;
		}
		else if (id == R.id.Button02) {
			pos = 1;
		}
		else if (id == R.id.Button03) {
			pos = 2;
		}
		else if (id == R.id.Button04) {
			pos = 3;
		}
		
		// 電話相手の詳細画面へ遷移
		Intent i = new Intent(this, AdressDetailActivity.class);
		i.putExtra("name", adressList[position + pos].displayName);
		i.putExtra("kana", adressList[position + pos].kanaName);
		i.putExtra("number", adressList[position + pos].phoneNum);
		this.startActivityForResult(i, 0);
		
	}
	
	/** 
	 * startActivityForResultで起動したアクティビティから復帰した場合に呼ばれるメソッド
	 * @author take
	 * */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
			case 0:	// 電話相手の詳細画面から復帰
				break;
			case 1:	// 画面から復帰
				break;
			case 2:	// 画面から復帰
				break;
			}
		}

	}


	private AdressData[] getAddressData() {
		
        //アドレス情報の取得
        ContentResolver cr = this.getContentResolver();

        /* MIMETYPE=vnd.android.cursor.item/phone_v2の場合、電話番号レコードが取得できる。
         * 電話番号レコードの場合、data1 には電話番号が格納されている。
         */
		Cursor phoneNumberCursor = cr.query(ContactsContract.Data.CONTENT_URI,
				null,
				ContactsContract.Data.MIMETYPE + " = ?",
				new String[] { "vnd.android.cursor.item/phone_v2" },
				null);

		// ハッシュ変数にアドレスを格納(id, phoneNumber)
		HashMap<String, String> mailHash = new HashMap<String, String>();
		while (phoneNumberCursor.moveToNext()) {
			mailHash.put(phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex(ContactsContract.Data.CONTACT_ID)), // ID
					phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex(ContactsContract.Data.DATA1))); // 電話番号
		}
		
		if (mailHash.size() == 0) {
			ToastUtil.showLong(this, "電話帳の情報がありません。");
			// 表示できないので戻る
			this.finish();
			return null;
		}
        
     // ソート文字を格納（連絡先一覧を "ふりがな" 順でソート）
        String order_str =
          " CASE" +
          " WHEN IFNULL(" + ContactsContract.Data.DATA9 + ", '') = ''" + 	// Data.DATA9がNULLの場合は空文字を代入
          " THEN 1 ELSE 0" + 												// Data.DATA9が空文字のレコードを最後にする
          " END, " + ContactsContract.Data.DATA9 + " ," +
          " CASE" +
          " WHEN IFNULL(" + ContactsContract.Data.DATA7 + ", '') = ''" +
          " THEN 1 ELSE 0" +
          " END, " + ContactsContract.Data.DATA7;


        // DATA表から連絡先名を全て取得
        /* MIMETYPE=vnd.android.cursor.item/nameの場合、連絡先名レコードが取得できる。
         * 連絡先名レコードの場合、data1 には表示名（姓名）、data2 には表示名（名）、data3 には表示名（姓）、data7 にはふりがな（名）、data9 にはふりがな（姓）が格納されている。
         */
		Cursor dataNamecursor = cr.query(ContactsContract.Data.CONTENT_URI,
				null,
				ContactsContract.Data.MIMETYPE + " = ?",
				new String[] { "vnd.android.cursor.item/name" },
				order_str);

		// メールアドレスが存在する連絡先だけを名前格納用リストに格納
		ArrayList<String> listNames = new ArrayList<String>(); // 名前格納用リスト
		ArrayList<String> listKanaNames = new ArrayList<String>(); // ふりがな格納用リスト
		ArrayList<String> listPhoneNums = new ArrayList<String>(); // 電話番号格納用リスト
		
		while (dataNamecursor.moveToNext()) {
			String id = dataNamecursor.getString(dataNamecursor.getColumnIndex(ContactsContract.Data.CONTACT_ID));

			/* CONTACT_IDが一致した場合、名前を格納する*/
			if (mailHash.containsKey(id)) {
				listNames.add(dataNamecursor.getString(dataNamecursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
				
				// data9 にはふりがな（姓）　data7 にはふりがな（名）を連結してふりがなとして格納
				String kanaTemp = "";
				String tmp = dataNamecursor.getString(dataNamecursor.getColumnIndex(ContactsContract.Data.DATA9));
				if (tmp != null) {
					kanaTemp = tmp;
				}

				tmp = dataNamecursor.getString(dataNamecursor.getColumnIndex(ContactsContract.Data.DATA7));
				if (tmp != null) {
					kanaTemp = kanaTemp + tmp;
				}
				
				// ふりがなを全角かなを半角に変更してから格納する。
				listKanaNames.add(new CaseConverterUtil().changeZenkakuToHankaku(kanaTemp));

				// 電話番号を格納する
				listPhoneNums.add(mailHash.get(id));
			}
		}
		
		Integer num = listKanaNames.size();
		AdressData[] adressData = new AdressData[num];
		for (Integer i = 0; i < num; i++) {
			adressData[i] = new AdressData();
			adressData[i].displayName = listNames.get(i);
			adressData[i].kanaName = listKanaNames.get(i);
			adressData[i].phoneNum = listPhoneNums.get(i);
		}
		
		// adressListをかな順にソートする。
		java.util.Arrays.sort(adressData);
		
		return adressData;

	}
	
	
	/**
	 * 電話帳からデータを取得しアドレス情報一覧を表示する。
	 */
	private void showAdressList() {
		
		// 電話帳からデータを取得
		adressList = this.getAddressData();
		if (adressList == null) {
			// データ取得できない場合、画面表示不可のため処理終了
			return;
		}

		// 検索キーを半角カナに変換
		this.selectCase = new CaseConverterUtil().changeZenkakuToHankaku(this.selectCase);
		LogUtil.d(this, "selectCase = " + this.selectCase);
		
		this.position = this.matchCase(this.adressList, this.selectCase);
		LogUtil.d(this, "position = " + this.position);

		this.showList(0);
	}
	
	/**
	 * 第2引数で指定した文字列が第一引数の配列のカナ文字列と比較し
	 * 第2引数文字列が配列に挿入される場合の位置をを返す。
	 * @param strList ソート済み前提
	 * @return　position　AdressData[]の該当位置を返却する。
	 */
	private Integer matchCase(AdressData[] strList, String matchCase) {

		Integer listNum = strList.length;
		Integer position = 0;
		// 引数の配列分ループ
		for (Integer i = 0; i < listNum; i++) {
			if (strList[i].kanaName.compareTo(matchCase) >= 0) {
				position = i;
				break;
			}
		}
		
		return position;
	}
	
	
	/**
	 * アドレスリストの情報を表示する。
	 * @param cnt アドレスリストの配列の現在位置からのオフセット値を設定
	 */
	private void showList(int cnt) {
		// 初期化
		((Button)this.findViewById(R.id.Button05)).setEnabled(true);
		((Button)this.findViewById(R.id.Button06)).setEnabled(true);
		
		// ポジションをカウントアップする。
		this.position = this.position + cnt;
		
		if (this.position + this.displayBarNum >= this.adressList.length) {
			// ポジションが最後尾に来たらポジションにを設定
			this.position = this.adressList.length - this.displayBarNum;
			// 「次へ」ボタンを無効にする
			((Button)this.findViewById(R.id.Button06)).setEnabled(false);		// Viewを無効
		}

		if (this.position <= 0) {
			// ポジションが先頭に来たらポジションに0を設定
			this.position = 0;
			// 「前へ」ボタンを無効にする
			((Button)this.findViewById(R.id.Button05)).setEnabled(false);		// Viewを無効
		}
		
		Button[] buttonList = new Button[4];
		buttonList[0] = (Button)this.findViewById(R.id.Button01);
		buttonList[1] = (Button)this.findViewById(R.id.Button02);
		buttonList[2] = (Button)this.findViewById(R.id.Button03);
		buttonList[3] = (Button)this.findViewById(R.id.Button04);

		for(int i = 0; i < this.displayBarNum; i++) {
			if ((i + this.position) < this.adressList.length) {
				buttonList[i].setText(this.adressList[this.position + i].displayName + "\n" + this.adressList[this.position + i].kanaName);
			}
			else {
				buttonList[i].setText("");
				buttonList[i].setEnabled(false);
			}
		}
	}
	
	/**
	 * 「前へ」ボタン押下時に呼ばれる。
	 * @param view
	 */
	public void prevList(View view) {
		this.showList(-this.displayBarNum);
	}

	/**
	 * 「次へ」ボタン押下時に呼ばれる。
	 * @param view
	 */
	public void nextList(View view) {
		this.showList(this.displayBarNum);
	}
	
	/**
	 * 表示文字列のサイズを変更する。
	 */
	public void changeCaseSize() {
		// 文字サイズ(SP)を各部品に設定する
		((Button)this.findViewById(R.id.Button01)).setTextSize(PreferenceData.getCaseSizeSP());
		((Button)this.findViewById(R.id.Button02)).setTextSize(PreferenceData.getCaseSizeSP());
		((Button)this.findViewById(R.id.Button03)).setTextSize(PreferenceData.getCaseSizeSP());
		((Button)this.findViewById(R.id.Button04)).setTextSize(PreferenceData.getCaseSizeSP());
		((Button)this.findViewById(R.id.Button05)).setTextSize(PreferenceData.getCaseSizeSP());
		((Button)this.findViewById(R.id.Button06)).setTextSize(PreferenceData.getCaseSizeSP());
	}
	
}
