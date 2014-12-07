package com.maniastence.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wawamaniac.demo.R;
import com.wawamaniac.maniastence.FieldPersistency;
import com.wawamaniac.maniastence.PersistentEditText;
import com.wawamaniac.maniastence.PersistentSpinner;

/**
 * @author Kévin Langles
 */
public class DemoActivity extends Activity
{

	@PersistentSpinner
	private Spinner spinner;

	@PersistentEditText
	private EditText editText;

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(null);
		this.setContentView(R.layout.demo);

		this.spinner = (Spinner) this.findViewById(R.id.spinner);
		this.editText = (EditText) this.findViewById(R.id.edittext);
		this.button = (Button) this.findViewById(R.id.button);

		this.spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, ITEMS));
		this.spinner.setSelection(0);

		this.button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				spinner.setSelection(0);
				editText.setText("");
				FieldPersistency.purge(DemoActivity.this);
			}
		});
	}

	private static final String[] ITEMS = new String[]{"Item 1", "Item 2"};

	@Override
	protected void onResume()
	{
		super.onResume();
		FieldPersistency.restore(DemoActivity.this);
	}

	@Override
	public void onBackPressed()
	{
		FieldPersistency.save(DemoActivity.this);
		finish();
	}

}
