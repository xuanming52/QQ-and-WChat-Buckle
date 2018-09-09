package com.xm.qwboom;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button bu=(Button) findViewById(R.id.mainButton1);
		Button bu1=(Button) findViewById(R.id.mainButton2);
		Button bu2=(Button) findViewById(R.id.mainButton3);
		final EditText edt1=(EditText) findViewById(R.id.mainEditText1);
		final EditText edt2=(EditText) findViewById(R.id.mainEditText2);
		bu.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					Intent intent=new Intent(MainActivity.this,service.class);
					intent.putExtra("body",edt1.getText().toString());
					if(edt2.getText().toString().equals("")){
						edt2.setText("0");
					}
					intent.putExtra("num",Integer.parseInt(edt2.getText().toString()));
					startService(intent);
					startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
					
					}
			}); 

		bu1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=3510088586")));
					
				}
			});

		bu2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method

					startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=241449466&card_type=group&source=qrcode")));
				}
			});
		
    }
}
