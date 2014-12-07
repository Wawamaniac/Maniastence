Maniastence
===========

Persistency module for basics Android UI view contents

This project is directly inspired by the ButterKnife project. It's basically utilities for your Activity classes.

##Description

Use the Maniastence module to make your EditText content and Spinner selected indexes persistant throught annotations and static method calls. 

##Supported

At this moment, only EditText and Spinner view components are supported by this project.

##Usage

```
@PersistentSpinner
Spinner spinner;

@PersistentEditText
EditText editText;
```

...

```
@Override
protected void onResume()
{
	super.onResume();
	// this call will restore the spinner selected index and the editText content from the Key-Value Android persistency system
	FieldPersistency.restore(DemoActivity.this);
}

@Override
public void onBackPressed()
{
	// this call will save the spinner selected index and the editText content to the Key-Value Android persistency system
	FieldPersistency.save(DemoActivity.this);
	finish();
}
```

##TODO

1) Support more Android view component

2) Create a smarter conceptual data model

3) Push the library on Maven Central Repository

##Author

Kevin Langles

IT student

Montpellier, France

Please be free to email me for any suggestion : kevin.langles@gmail.com
