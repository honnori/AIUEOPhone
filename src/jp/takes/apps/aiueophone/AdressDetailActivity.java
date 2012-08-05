package jp.takes.apps.aiueophone;

import jp.takes.apps.aiueophone.base.BaseActivity;
import jp.takes.apps.aiueophone.data.CommonData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 電話相手先の詳細画面
 * 
 * @author ict
 *
 */
public class AdressDetailActivity extends BaseActivity {
	
	private String name = null;
	private String kana = null;
	private String number = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.adress_detail);

		this.showAdressDetail();
	}

	
	/**
	 * 電話相手先の詳細画面の表示内容を設定する
	 */
	private void showAdressDetail() {
		Intent i = this.getIntent();
		name = i.getStringExtra(CommonData.INTENT_NAME_NAME);
		kana = i.getStringExtra(CommonData.INTENT_NAME_KANA);
		number = i.getStringExtra(CommonData.INTENT_NAME_NUMBER);

		TextView vDispName = (TextView) this.findViewById(R.id.dispName);
		TextView vPhoneNum = (TextView) this.findViewById(R.id.dispPhoneNum);

		vDispName.setText(name + "\n" + kana);
		vPhoneNum.setText(number);

	}

	/**
	 * 電話するボタン押下
	 * 
	 * @param view
	 */
	public void pressedDial(View view) {
		if (number != null) {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ number));
			// Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse("tel:"
			// + number));
			this.startActivity(intent);
		}

	}

	/**
	 * 表示文字列のサイズを変更する。
	 */
	public void changeCaseSize() {
		// 文字サイズ(SP)を各部品に設定する
		((TextView)this.findViewById(R.id.dispName)).setTextSize(this.getCaseSizeSP());
		((TextView)this.findViewById(R.id.dispPhoneNum)).setTextSize(this.getCaseSizeSP());
		((Button)this.findViewById(R.id.phoneButton)).setTextSize(this.getCaseSizeSP());
	}
}
