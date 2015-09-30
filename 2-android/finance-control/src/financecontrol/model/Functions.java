package financecontrol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.widget.Toast;

public class Functions {

	// splash screen message
	public static void showMessage(Context context, String text) {
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    	toast.show();
	}
	
	// open file and obtain the content, needs the file name
	public static String openFileAndObtainContent(Context context, String filename) throws IOException {
		FileInputStream input = context.openFileInput(filename);
		File file = context.getFilesDir();
		File textfile = new File(file+"/"+filename);
		byte[] buffer = new byte[(int) textfile.length()];
		input.read(buffer);
		return new String(buffer);
	}
	
}
