package com.wawamaniac.maniastence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/**
 * Use this annotation on an EditText to persist its content in the Key-Value Android persistency system.<br>
 * Do not forget to call FieldPersistency.save(Activity) and FieldPersistency.restore(Activity) when creating / closing your activity.
 * 
 * @author Kevin Langles
 *
 */
public @interface PersistentEditText
{

}
