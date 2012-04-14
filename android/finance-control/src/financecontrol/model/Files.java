package financecontrol.model;

import java.io.File;

import android.content.Context;

public class Files {

	public static final String FILENAME_TRANSACTIONS = "finance_control_transactions.txt";
	public static final String FILENAME_BALANCE 	 = "finance_control_balance.txt";
	public static final String FILENAME_CATEGORIES 	 = "finance_control_categories.txt";

	public static void createDefaultFiles(Context context) {
		File file = context.getFilesDir();
		
		new File(file + "/" + FILENAME_BALANCE);
		new File(file + "/" + FILENAME_TRANSACTIONS);
		new File(file + "/" + FILENAME_CATEGORIES);
	}
	
}
