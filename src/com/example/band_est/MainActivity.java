package com.example.band_est;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.*;


public class MainActivity extends Activity {
	
	Button mButton;
	EditText mEdit;
	TextView mText;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button1);
        
        mButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
        	
        	
            mEdit   = (EditText)findViewById(R.id.editText1);
            mText = (TextView)findViewById(R.id.textView1);
            Pattern p = Pattern.compile("((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[\\-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9\\.\\-]+|(?:www\\.|[\\-;:&=\\+\\$,\\w]+@)[A-Za-z0-9\\.\\-]+)((?:\\/[\\+~%\\/\\.\\w\\-_]*)?\\??(?:[\\-\\+=&;%@\\.\\w_]*)#?(?:[\\.\\!\\/\\\\\\w]*))?)");
            Matcher m = p.matcher(mEdit.getText().toString());
            if (m.find())
            {
            	 mText.setText("URL matched"); 
            } else {
            	mText.setText("URL not matched");
            }
          }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
