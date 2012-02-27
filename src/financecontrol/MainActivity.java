package financecontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import financecontrol.android.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {
	private static final String FILENAME = "finance_control.txt";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		loadInitialBalance();
		
		Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                recordValue();
            }
        });
	}

	private void loadInitialBalance() {
		try {

			File file = this.getFilesDir();
			File textfile = new File(file + "/" + FILENAME);
			FileInputStream input = this.openFileInput(FILENAME);
			
			byte[] buffer = new byte[(int) textfile.length()];
			input.read(buffer);
			
			EditText balance = (EditText) findViewById(R.id.balance);
			String value = new String(buffer);
			balance.setText(value.equals("") ? "0" : value);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void recordValue() {
        FileOutputStream out = null;
        try {
        	
			EditText balanceEdit = (EditText) findViewById(R.id.balance);
			Float balance = Float.parseFloat(balanceEdit.getText().toString());
			
			EditText valueEdit = (EditText) findViewById(R.id.value);
			Float value = Float.parseFloat(valueEdit.getText().toString());
			
			RadioButton expense = (RadioButton) findViewById(R.id.expense);
			if (expense.isChecked())
				value *= -1;
			
			Float result = balance + value;
			balanceEdit.setText(result.toString());
			
            out = this.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            out.write(result.toString().getBytes());
            
            valueEdit.setText("");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				out.flush();
	            out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}