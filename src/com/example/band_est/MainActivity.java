package com.example.band_est;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.*;


public class MainActivity extends Activity implements OnClickListener {
	
	Button mButton;
	EditText mEdit;
	TextView mText;
	ProgressBar pb;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button1);
        mEdit   = (EditText)findViewById(R.id.editText1);
        mText = (TextView)findViewById(R.id.textView1);
        pb = (ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);
        mButton.setOnClickListener(this);
          
    }
    
    public void onClick(View view) {
        Pattern p = Pattern.compile("((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[\\-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9\\.\\-]+|(?:www\\.|[\\-;:&=\\+\\$,\\w]+@)[A-Za-z0-9\\.\\-]+)((?:\\/[\\+~%\\/\\.\\w\\-_]*)?\\??(?:[\\-\\+=&;%@\\.\\w_]*)#?(?:[\\.\\!\\/\\\\\\w]*))?)");
        Matcher m = p.matcher(mEdit.getText().toString());
        if (m.find())
        {

        	 pb.setVisibility(View.VISIBLE);
        	 new MyAsyncTask().execute(mText.getText().toString());      
        	 
        } else {
        	Toast.makeText(this, "please enter a valid URL", Toast.LENGTH_LONG).show();
        }
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

private class MyAsyncTask extends AsyncTask<String, Integer, Double>{
 
          @Override
          protected Double doInBackground(String... params) {
               // TODO Auto-generated method stub
               postData(params[0]);
               return null;
          }
 
          protected void onPostExecute(Double result){
               pb.setVisibility(View.GONE);
               Toast.makeText(getApplicationContext(), "URL sent", Toast.LENGTH_LONG).show();
          }
          protected void onProgressUpdate(Integer... progress){
               pb.setProgress(progress[0]);
          }
 
          public void postData(String url) {
               // Create a new HttpClient and Post Header
               HttpClient httpclient = new DefaultHttpClient();
               HttpPost httppost = new HttpPost("http://facebook.com/index.php");
 
               try {
                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("myHttpData", url));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
                    // Execute HTTP Post Request
                    ResponseHandler<String> responseHandler=new BasicResponseHandler();
                    String responseBody = httpclient.execute(httppost, responseHandler);
                    mText.setText(responseBody);
                    try {
						JSONObject response=new JSONObject(responseBody);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
 
               } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
               } catch (IOException e) {
                    // TODO Auto-generated catch block
               }
          }
 
     }
}
