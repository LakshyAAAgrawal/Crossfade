/*
    Crossfade(Image Layering), Android App which lets users crossfade 2 different images in a specified weightage.>
    Copyright (C) 2015  Lakshya A Agrawal

    This file is part of Crossfade.

    Crossfade is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Crossfade is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses.
    
    The Author:
    Lakshya A Agarawal 2015
    lakshya.aagrawal@gmail.com */
package com.lakagr.oss.Crossfade;

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
        				Toast.makeText(getApplicationContext(), R.string.ext_unavailable,Toast.LENGTH_LONG).show();
        			}else{
    					try{
    						File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"Crossfade");
						directory.mkdirs();
						
    							File file = new File(directory, ((EditText)findViewById(R.id.et1)).getText().toString()+".jpg");
        					
        					FileOutputStream fOut = new FileOutputStream(file);
        					bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);
        					Toast.makeText(getApplicationContext(), " File Saved in \n "+ directory.toString(),Toast.LENGTH_LONG).show();
            					Toast.makeText(getApplicationContext(), "Thank You \n For using Crossfade",Toast.LENGTH_LONG).show();
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
