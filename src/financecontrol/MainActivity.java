package financecontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import financecontrol.android.R;

public class MainActivity extends Activity {
	private static final String FILENAME_TRANSACTIONS = "finance_control_transactions.txt";
	private static final String FILENAME_BALANCE = "finance_control_balance.txt";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		createDefaultFiles();
		
		loadInitialBalance();
		
		Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                recordValue();
            }
        });
	}

	private void createDefaultFiles() {
		File file = this.getFilesDir();
		
		new File(file + "/" + FILENAME_BALANCE);
		new File(file + "/" + FILENAME_TRANSACTIONS);
	}
	
	private void loadInitialBalance() {
		try {

			FileInputStream input = this.openFileInput(FILENAME_BALANCE);
			File file = this.getFilesDir();
			File textfile = new File(file + "/" + FILENAME_BALANCE);
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
        FileOutputStream outBalance = null;
        FileOutputStream outTransaction = null;
        try {
        	
        	EditText valueEdit = (EditText) findViewById(R.id.value);
        	if (valueEdit.getText().toString().equals("")) {
        		throw new IllegalArgumentException("Informe um valor para a transação.");
        	}
			Float value = Float.parseFloat(valueEdit.getText().toString());
            valueEdit.setText("");
            
            EditText descriptionEdit = (EditText) findViewById(R.id.description);
			String description = descriptionEdit.getText().toString();
			
			RadioButton expense = (RadioButton) findViewById(R.id.expense);
			if (expense.isChecked())
				value *= -1;

			EditText balanceEdit = (EditText) findViewById(R.id.balance);
			Float balance = Float.parseFloat(balanceEdit.getText().toString().equals("") ? "0" : balanceEdit.getText().toString());
			Float result = balance + value;
			balanceEdit.setText(result.toString());

			outBalance = this.openFileOutput(FILENAME_BALANCE, Context.MODE_PRIVATE);
            outBalance.write(result.toString().getBytes());
            outTransaction = this.openFileOutput(FILENAME_TRANSACTIONS, Context.MODE_APPEND);
            outTransaction.write(DateFormat.format("dd/MM/yyyy hh:mm:ss", new Date()).toString().getBytes());
            outTransaction.write("|".getBytes());
            outTransaction.write(value.toString().getBytes());
            outTransaction.write("|".getBytes());
            outTransaction.write(description.getBytes());
            outTransaction.write("\n".getBytes());
            
        } catch (Exception e) {
        	// splash screen message
        	Context context = getApplicationContext();
        	CharSequence text = e.getMessage();
        	int duration = Toast.LENGTH_SHORT;

        	Toast toast = Toast.makeText(context, text, duration);
        	toast.show();
        } finally {
            try {
				if (outBalance != null) {
	            	outBalance.flush();
		            outBalance.close();
				}
				if (outTransaction != null) {
		            outTransaction.flush();
		            outTransaction.close();
				}
			} catch (IOException e) {}
        }
	}
}