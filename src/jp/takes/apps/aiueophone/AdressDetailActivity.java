package jp.takes.apps.aiueophone;

import jp.takes.apps.aiueophone.base.BaseActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdressDetailActivity extends BaseActivity {
	
    public String name = null;
    public String kana = null;
    public String number = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adress_detail);
        
        this.showAdressDetail();
    }
    
    public void showAdressDetail() {
        Intent i = this.getIntent();
        name = i.getStringExtra("name");
        kana = i.getStringExtra("kana");
        number = i.getStringExtra("number");
        
        TextView vDispName = (TextView)this.findViewById(R.id.dispName);
        TextView vPhoneNum = (TextView)this.findViewById(R.id.dispPhoneNum);
        
        vDispName.setText(name + "\n" + kana);
        vPhoneNum.setText(number);
        
    }
    
    
    /**
     * 電話するボタン押下
     * @param view
     */
    public void pressedDial(View view) {
    	if (number != null) {
        	Intent intent = new Intent( Intent.ACTION_CALL, Uri.parse("tel:" + number));
//        	Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        	startActivity(intent);
    	}
    	
    }
    
    

}
