package jp.takes.apps.aiueophone;

import jp.takes.apps.aiueophone.base.BaseActivity;
import jp.takes.apps.aiueophone.util.CaseConverterUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AIUEOPhoneDanActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dan_case_list);
        this.showDanList();
        
    }
    
	@Override
	protected void onStop() {
		super.onStop();
	}


	/**
	 * あ-わ行のボタンが押下された場合
	 * 各行の文字選択画面へ遷移
	 * @param view
	 */
	public void pressedDanButton(View view) {

		/* 押下されたボタンの判別 */
		String dispName = ((TextView)view).getText().toString();
		
		// アドレスの名前一覧表示画面へ遷移
		Intent i = new Intent(this, AdressListActivity.class);
		i.putExtra("dan", dispName);
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
			case 0:	// アドレスの名前一覧表示画面から復帰
				break;
			case 1:	// 画面から復帰
				break;
			case 2:	// 画面から復帰
				break;
			}
		}
	}


	public void setAnyDanSetToTextView(TextView[] views, Integer dimension1) {
		CaseConverterUtil danCase = new CaseConverterUtil();
		for (Integer i = 0; i < 5; i++) {
			danCase.setSetToTextView(views[i], dimension1, i);
		}
	}

	/**
	 * 前画面で選択された行を判別し適切なボタンを表示する。
	 */
	private void showDanList() {
        Intent i = this.getIntent();
		String dispName = i.getStringExtra("gyo");
		
		Button buttonList[] = new Button[5];

		buttonList[0] = (Button)this.findViewById(R.id.Button01);
		buttonList[1] = (Button)this.findViewById(R.id.Button02);
		buttonList[2] = (Button)this.findViewById(R.id.Button03);
		buttonList[3] = (Button)this.findViewById(R.id.Button04);
		buttonList[4] = (Button)this.findViewById(R.id.Button05);

		/* 押下されたボタンの判別 */
		Integer dimension1 = 0;
		/**
		 * dimension1 = 0  あ行
		 * dimension1 = 1　か行
		 * dimension1 = 2　さ行
		 * dimension1 = 3　た行
		 * dimension1 = 4　な行
		 * dimension1 = 5　は行
		 * dimension1 = 6　ま行
		 * dimension1 = 7　や行
		 * dimension1 = 8　ら行
		 * dimension1 = 9　わ行
		 */
		if(dispName.equals(this.getString(R.string.A_gyou))){
			dimension1 = 0;		// あ行
		}
		else if(dispName.equals(this.getString(R.string.K_gyou))){
			dimension1 = 1;		// か行
		}
		else if(dispName.equals(this.getString(R.string.S_gyou))){
			dimension1 = 2;		// さ行
		}
		else if(dispName.equals(this.getString(R.string.T_gyou))){
			dimension1 = 3;		// た行
		}
		else if(dispName.equals(this.getString(R.string.N_gyou))){
			dimension1 = 4;		// な行
		}
		else if(dispName.equals(this.getString(R.string.H_gyou))){
			dimension1 = 5;		// は行
		}
		else if(dispName.equals(this.getString(R.string.M_gyou))){
			dimension1 = 6;		// ま行
		}
		else if(dispName.equals(this.getString(R.string.Y_agyou))){
			dimension1 = 7;		// や行
		}
		else if(dispName.equals(this.getString(R.string.R_gyou))){
			dimension1 = 8;		// や行
		}
		else if(dispName.equals(this.getString(R.string.W_gyou))){
			dimension1 = 9;		// や行
		}
		this.setAnyDanSetToTextView(buttonList, dimension1);
	}
	
}
