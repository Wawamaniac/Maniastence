package com.wawamaniac.maniastence;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Field;

/**
 * Use this class from your activity in order to make your annotated EditText contents and Spinners indexes persistent. <br>
 * This class uses a MappingManager instance to make the related values persist in the Android Key-Value persistency system.
 *
 * @author Kévin Langles
 */
public class FieldPersistency
{

	/**
	 * Delete from the Key-Value persistency system all the values related to the annotated fields
	 *
	 * @param activity Activity from where this method is called
	 */
	public static void purge(Activity activity)
	{
		MappingManager mappingManager = new MappingManager();
		String prefix = activity.getClass().getSimpleName();

		for (Field field : activity.getClass().getDeclaredFields())
		{
			field.setAccessible(true);

			if (field.isAnnotationPresent(PersistentEditText.class) || field.isAnnotationPresent(PersistentSpinner.class))
			{
				mappingManager.setValue(activity.getApplicationContext(), prefix + "$" + field.getName(), "");
			}
		}
	}

	/**
	 * Save in the Key-Value persistency system all the values related to the annotated fields
	 *
	 * @param activity Activity from where this method is called
	 */
	public static void save(Activity activity)
	{
		MappingManager mappingManager = new MappingManager();
		String prefix = activity.getClass().getSimpleName();

		EditText editText;
		Spinner spinner;

		for (Field field : activity.getClass().getDeclaredFields())
		{
			field.setAccessible(true);

			if (field.isAnnotationPresent(PersistentEditText.class))
			{
				try
				{
					editText = (EditText) field.get(activity);

					mappingManager.setValue(activity.getApplicationContext(), prefix + "$" + field.getName(), editText.getText().toString());
				} catch (Exception e)
				{
					Log.e(Constants.PROJECT_NAME, "Could not save " + field.getName() + " EditText content");
					e.printStackTrace();
				}
			} else if (field.isAnnotationPresent(PersistentSpinner.class))
			{
				try
				{
					spinner = (Spinner) field.get(activity);

					mappingManager.setValue(activity.getApplicationContext(), prefix + "$" + field.getName(), "" + spinner.getSelectedItemPosition());
				} catch (Exception e)
				{
					Log.e(Constants.PROJECT_NAME, "Could not save " + field.getName() + " Spinner index");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Restore from the Key-Value persistency system all the values related to the annotated fields
	 *
	 * @param activity Activity from where this method is called
	 */
	public static void restore(Activity activity)
	{
		MappingManager mappingManager = new MappingManager();
		String prefix = activity.getClass().getSimpleName();

		EditText editText;
		String value;
		Spinner spinner;
		int index;

		for (Field field : activity.getClass().getDeclaredFields())
		{
			field.setAccessible(true);

			if (field.isAnnotationPresent(PersistentEditText.class))
			{
				try
				{
					editText = (EditText) field.get(activity);

					value = mappingManager.getValue(activity.getApplicationContext(), prefix + "$" + field.getName());

					editText.setText(value);
				} catch (Exception e)
				{
					Log.e(Constants.PROJECT_NAME, "Could not restore " + field.getName() + " EditText content");
					e.printStackTrace();
				}
			} else if (field.isAnnotationPresent(PersistentSpinner.class))
			{
				try
				{
					spinner = (Spinner) field.get(activity);

					index = Integer.parseInt(mappingManager.getValue(activity.getApplicationContext(), prefix + "$" + field.getName()));

					if (index != Spinner.INVALID_POSITION && index >= 0 && index < spinner.getAdapter().getCount())
					{
						spinner.setSelection(index);
					}
				} catch (NumberFormatException e1)
				{
				} catch (Exception e)
				{
					Log.e(Constants.PROJECT_NAME, "Could not restore " + field.getName() + " Spinner index");
					e.printStackTrace();
				}
			}
		}
	}
}
