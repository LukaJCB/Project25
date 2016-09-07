package com.ltj.shared.engine;

import java.lang.reflect.Field;

public class BehaviourManipulator {

	
	public static Class<?>[] getTypes(Behaviour<? extends Sprite> behaviour){
		Field[] fields = behaviour.getClass().getFields();
		Class<?>[] classes = new Class<?>[fields.length];
		for (int i = 0;i <fields.length;i++){
			classes[i] = fields[i].getType();
		}
		return classes;
	}
	
	public static Field[] getFields(Behaviour<? extends Sprite> behaviour){
		return behaviour.getClass().getFields();
	}
	
	
	public static void manipulateField(Behaviour<? extends Sprite> behaviour, String name , Object value){
		try {
			behaviour.getClass().getField(name).set(behaviour, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
