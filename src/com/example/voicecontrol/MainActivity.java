package com.example.voicecontrol;

import java.util.ArrayList;

import android.R.drawable;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;

public class MainActivity extends Activity {

    private BaiduASRDigitalDialog mDialog = null;

    private DialogRecognitionListener mRecognitionListener;

    private int mCurrentTheme = Config.DIALOG_THEME;

    private Button button;
    private ImageView iw;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button)findViewById(R.id.VoiceControl);
        iw = (ImageView)findViewById(R.id.Iv);
        
        mRecognitionListener = new DialogRecognitionListener() {

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> rs = results != null ? results
                        .getStringArrayList(RESULTS_RECOGNITION) : null;
                if (rs != null && rs.size() > 0) {
                   //setBackgroundColor(rs.get(0));
                    setPicture(rs.get(0));
                }

            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void ClickButton(View v){
    	//Toast.makeText(getApplicationContext(), "����",  Toast.LENGTH_SHORT).show();
        mCurrentTheme = Config.DIALOG_THEME;
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Bundle params = new Bundle();
        params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, "QRrYgx7bcO11NWUFFG61jtBN");
        params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, "iRNUTBM9Tli0px1HqqyYLtdMdy95ChGz");
        params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, Config.DIALOG_THEME);
        mDialog = new BaiduASRDigitalDialog(this, params);
        mDialog.setDialogRecognitionListener(mRecognitionListener);
        
       
        mDialog.show();
    }
    
    
    /*
     * ��������Ӧ�ĺ�������ť��ɫ�ı仯
     */
    
    /*public void setBackgroundColor(String color){
    	if(color.contains("��ɫ")){
    		button.setBackgroundColor(Color.BLUE);
    	}
    	else if(color.contains("��ɫ")){
    		button.setBackgroundColor(Color.GREEN);
    	}
    	else if(color.contains("��ɫ")){
    		button.setBackgroundColor(Color.WHITE);
    	}
    }*/
    
    /*
     * ��������Ӧ�ĺ�����ͼƬ�仯
     */
    public void setPicture(String fruit){
    	if(fruit.contains("ƻ��")){
    		iw.setImageDrawable(getResources().getDrawable(R.drawable.apple));
    	}
    	else if(fruit.contains("��")){
    		iw.setImageDrawable(getResources().getDrawable(R.drawable.pear));
    	}
    	else if(fruit.contains("�㽶")){
    		iw.setImageDrawable(getResources().getDrawable(R.drawable.banana));
    	}
    	else if(fruit.contains("��ݮ")){
    		iw.setImageDrawable(getResources().getDrawable(R.drawable.strawberry));
    	}
    	else {
    		iw.setImageDrawable(getResources().getDrawable(R.drawable.questionmark));
    	}
    	
    }
    
    
}
