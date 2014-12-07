package com.wawamaniac.maniastence;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * This class can be used to store values in the Android Key-Value persistency system. <br>
 * It automatically declare SharedPreferences android classes to store the values.
 *
 * @author Kévin Langles
 */
public class MappingManager
{

	/**
	 * This function store all the couples <key, value> in the Android Key-Value persistency system.
	 *
	 * @param context Context to get SharedPreferences from
	 * @param map     List of couple <key, value> to be stored
	 * @return True if the values has been commited
	 */
	public boolean setValues(Context context, Map<String, Object> map)
	{
		String packageName = context.getPackageName();
		int mode = Context.MODE_PRIVATE;

		SharedPreferences sharedPref = context.getSharedPreferences(packageName, mode);
		SharedPreferences.Editor editor = sharedPref.edit();

		for (String key : map.keySet())
		{
			editor.putString(key, map.get(key).toString());
		}

		return editor.commit();
	}

	/**
	 * This function store a couple <key, value> in the Android Key-Value persistency system.
	 *
	 * @param context Context to get SharedPreferences from
	 * @param key     Unique key for your value
	 * @param value   Value to be stored
	 * @return True if the value has been commited
	 */
	public boolean setValue(Context context, String key, String value)
	{
		String packageName = context.getPackageName();
		int mode = Context.MODE_PRIVATE;

		SharedPreferences sharedPref = context.getSharedPreferences(packageName, mode);
		SharedPreferences.Editor editor = sharedPref.edit();

		editor.putString(key, value);

		return editor.commit();
	}

	/**
	 * This function returns a value regarding which key you send to it, from the Android Key-Value persistency system.
	 *
	 * @param context Context to get SharedPreferences from
	 * @param key     Unique key for your value
	 * @return The value related to the given key, empty String otherwise.
	 */
	public String getValue(Context context, String key)
	{
		String packageName = context.getPackageName();
		int mode = Context.MODE_PRIVATE;

		SharedPreferences sharedPref = context.getSharedPreferences(packageName, mode);

		String value = sharedPref.getString(key, "");

		return value;
	}
}
