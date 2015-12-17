package com.lakagr.oss.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Environment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.io.File;
import java.io.FileOutputStream;
import android.widget.Toast;
import android.widget.EditText;
import android.media.MediaScannerConnection;
import android.net.Uri;
public class display extends Activity{
Bitmap bitmap=null;
	@Override
    	public void onCreate(Bundle savedInstanceState)
    	{
    		
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.display2);
        	Intent intent=getIntent();
        	try{
        		bitmap=BitmapFactory.decodeStream(this.openFileInput(getIntent().getStringExtra("img")));
        	}catch(Exception e){}
        	((ImageView)findViewById(R.id.ivd)).setVisibility(0);
        	((ImageView)findViewById(R.id.ivd)).setImageBitmap(bitmap);
        	
        	Button btn_ren=(Button) findViewById(R.id.btn_ren);
       		btn_ren.setOnClickListener(new OnClickListener(){
       		 	@Override
        		public void onClick(View arg0){
        			if(Environment.getExternalStorageState ()==Environment.MEDIA_MOUNTED){
        				Toast.makeText(getApplicationContext(), "External Storage\nUnavailable",Toast.LENGTH_LONG).show();
        			}else{
    					try{
    						File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"Image Layer");
						directory.mkdirs();
						
    							File file = new File(directory, ((EditText)findViewById(R.id.et1)).getText().toString()+".jpg");
        					
        					FileOutputStream fOut = new FileOutputStream(file);
        					bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);
        					//MediaScannerConnection a=new MediaScannerConnection(getApplicationContext(),null);
        					//a.connect();
        					//a.scanFile(Environment.getExternalStoragePublicDirectory(
            //Environment.DIRECTORY_PICTURES).toString()+ ((EditText)findViewById(R.id.et1)).getText().toString()+".jpg", null);
            					sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
            + directory))); 
        					fOut.close();
        				}catch(Exception e){}
        			}
        			
        		}
        	});
        }
}
