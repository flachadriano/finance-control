package financecontrol.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import financecontrol.android.R;
import financecontrol.model.Files;
import financecontrol.model.Functions;
import financecontrol.model.Messages;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Files.createDefaultFiles(this);
		
		loadInitialBalance();
		loadCategories();

		ImageView categoryAdd = (ImageView) findViewById(R.id.categoryAdd);
        categoryAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
				// show route screen
				Intent intent = new Intent(v.getContext(), CategoryActivity.class);
				startActivity(intent);
            }
        });
        
        final EditText description = (EditText) findViewById(R.id.description);
        description.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					if (description.getText().toString().equals("Descrição")) {
						description.setText("");
					}
				} else if (description.getText().toString().equals("")) {
					description.setText("Descrição");
				}
			}
		});
        
		Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                recordValue();
            }
        });
	}

	private void loadInitialBalance() {
		try {

			FileInputStream input = this.openFileInput(Files.FILENAME_BALANCE);
			File file = this.getFilesDir();
			File textfile = new File(file + "/" + Files.FILENAME_BALANCE);
			byte[] buffer = new byte[(int) textfile.length()];
			input.read(buffer);
			
			EditText balance = (EditText) findViewById(R.id.balance);
			String value = new String(buffer);
			balance.setText(value.equals("") ? "0" : value);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadCategories() {
        FileOutputStream outCategory = null;
        try {

			FileInputStream input = this.openFileInput(Files.FILENAME_CATEGORIES);
			File file = this.getFilesDir();
			File textfile = new File(file + "/" + Files.FILENAME_CATEGORIES);
			byte[] buffer = new byte[(int) textfile.length()];
			input.read(buffer);
			
			String value = new String(buffer);
			
			Spinner category = (Spinner) findViewById(R.id.category);
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, value.split("\n"));
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			category.setAdapter(adapter);

        } catch (FileNotFoundException e) {
        	Functions.showMessage(this, Messages.FILE_NOT_FOUND);
		} catch (IOException e) {
        	Functions.showMessage(this, Messages.FILE_NOT_FOUND);
		} catch (IllegalArgumentException e) {
			Functions.showMessage(this, e.getMessage());
		}finally {
            try {
				if (outCategory != null) {
	            	outCategory.flush();
		            outCategory.close();
				}
			} catch (IOException e) {}
        }
	}
	
	private void recordValue() {
        FileOutputStream outBalance = null;
        FileOutputStream outTransaction = null;
        try {
        	
        	EditText valueEdit = (EditText) findViewById(R.id.value);
        	if (valueEdit.getText().toString().equals("")) {
        		throw new IllegalArgumentException(Messages.VALUE_TRANSACTION);
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

			outBalance = this.openFileOutput(Files.FILENAME_BALANCE, Context.MODE_PRIVATE);
            outBalance.write(result.toString().getBytes());
            
            outTransaction = this.openFileOutput(Files.FILENAME_TRANSACTIONS, Context.MODE_APPEND);
            outTransaction.write(DateFormat.format("dd/MM/yyyy hh:mm:ss", new Date()).toString().getBytes());
            outTransaction.write("|".getBytes());
            outTransaction.write(value.toString().getBytes());
            outTransaction.write("|".getBytes());
            outTransaction.write(description.getBytes());
            outTransaction.write("\n".getBytes());
            
        } catch (FileNotFoundException e) {
        	Functions.showMessage(this, Messages.FILE_NOT_FOUND);
		} catch (IOException e) {
        	Functions.showMessage(this, Messages.FILE_NOT_FOUND);
		} catch (IllegalArgumentException e) {
			Functions.showMessage(this, e.getMessage());
		}finally {
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