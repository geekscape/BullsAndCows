package com.dansdev.bulls.and.cows;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText guessText;
	public SecretNumbers numbersModel;
	public TextView gameText;
	public InputMethodManager mgr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guessText = (EditText)findViewById(R.id.editGuess);
        gameText = (TextView)findViewById(R.id.gameText);
        numbersModel = new SecretNumbers();
        gameText.setMovementMethod(new ScrollingMovementMethod());
        mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.toggleSoftInput(0, 0);
        guessText.setOnKeyListener(new OnKeyListener(){
        	public boolean onKey(View view, int keyCode, KeyEvent event){
        		if (event.getAction() == KeyEvent.ACTION_DOWN){
        			switch (keyCode)
        			{
        			case KeyEvent.KEYCODE_DPAD_CENTER:
        			case KeyEvent.KEYCODE_ENTER:
        				onSubmit(view);
        				mgr.showSoftInput(guessText, 0);
        				return true;
        			default:
        				break;
        			}
        		}
        	return false;
        	}
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void resetGame(View view)
    {
    	gameText.setText("");
    	guessText.setText("");
    	numbersModel.startGame();
    }
    
    public void onSubmit(View view)
    {
    	if (guessText.length() < 4)
    	{
    		Context context = getApplicationContext();
    		String lengthError = "Your guess must contain 4 numbers";
    		int duration = Toast.LENGTH_LONG;
    		
    		Toast toast = Toast.makeText(context, lengthError, duration);
    		toast.show();
    	} else 
	    	{
	    	String guessString = guessText.getText().toString();
	    	Log.v("guessText: ", guessString); // For debug purposes
	    	guessText.setText("");
	    	
	    	if (numbersModel.guess(guessString))
	    	{
	    		gameText.setText("Well done, you won the game!!");
	    		numbersModel.startGame();
	    	} else
	    	{
	    		String gameTextBuffer;
	    		gameTextBuffer = gameText.getText().toString();
	    		//Log.v("Bulls: ", (numbersModel.getBulls()));
	    		//Log.v("Cows ", (numbersModel.getCows()));
	    		gameText.setText("Guess: " + guessString + "     Bulls: " + numbersModel.fBulls + ", Cows: " + numbersModel.fCows + "\n" + gameTextBuffer);
	    	}
    	}
    	if ((TextView)findViewById(view.getId()) != ((TextView)findViewById(R.id.editGuess)))
    	{
    		((TextView)findViewById(R.id.editGuess)).requestFocus();
    		//mgr.showSoftInput(guessText, InputMethodManager.SHOW_IMPLICIT);
    	}
    	
    }
}
