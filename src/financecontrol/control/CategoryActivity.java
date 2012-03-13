package financecontrol.control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import financecontrol.android.R;
import financecontrol.model.Files;
import financecontrol.model.Functions;
import financecontrol.model.Messages;

public class CategoryActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category);
		
		Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	record();
            }
        });
	}
	
	public void record() {
        FileOutputStream outCategory = null;
        try {
        	
        	EditText categoryEdit = (EditText) findViewById(R.id.category);
        	String category = categoryEdit.getText().toString();
        	
        	outCategory = this.openFileOutput(Files.FILENAME_CATEGORIES, Context.MODE_APPEND);
        	outCategory.write(category.getBytes());
        	outCategory.write("\n".getBytes());

        	Functions.showMessage(this, Messages.CATEGORY_SUCESS);
        	
        	this.finish();
        	
        } catch (FileNotFoundException e) {
        	Functions.showMessage(this, Messages.FILE_NOT_FOUND);
		} catch (IOException e) {
        	Functions.showMessage(this, Messages.FILE_NOT_FOUND);
		} finally {
        	try {
        		if (outCategory != null) {
        			outCategory.flush();
        			outCategory.close();
        		}
        	} catch (IOException e) {}
        }
	}

}
