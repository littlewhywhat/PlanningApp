package appInterface;
import com.littlewhywhat.planningapp.R;
import contentItemsLib.CalendarsDictionary;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity 
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		CalendarsDictionary dictionary = new CalendarsDictionary(getApplicationContext());
		dictionary.Fill();
		CalendarsAdapter adapter = new CalendarsAdapter(dictionary);
		Spinner calendarsSpinner = (Spinner) findViewById(R.id.spinner);
		calendarsSpinner.setAdapter(adapter);
	}
	
}
