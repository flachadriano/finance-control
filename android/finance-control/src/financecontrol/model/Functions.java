package financecontrol.model;

import android.content.Context;
import android.widget.Toast;

public class Functions {

	// splash screen message
	public static void showMessage(Context context, String text) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    	toast.show();
	}
	
}
