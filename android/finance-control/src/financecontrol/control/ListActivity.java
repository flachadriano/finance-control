package financecontrol.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import financecontrol.android.R;
import financecontrol.model.Files;
import financecontrol.model.Functions;
import financecontrol.model.Messages;

public class ListActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		try {
			
			String transactionList = Functions.openFileAndObtainContent(this, Files.FILENAME_TRANSACTIONS);
			EditText transactions = (EditText) findViewById(R.id.transactions);
			transactions.setText(transactionList);
			
		} catch (Exception e) {
			Functions.showMessage(this, Messages.LIST_ERROR + e.getMessage());
		}
	}
}
