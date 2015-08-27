package com.ltj.java.utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;

public class BehaviourLoader {

	@SuppressWarnings("unchecked")
	public static Behaviour<? extends Sprite> loadBehaviour(String fileName, String path) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String[] fileToCompile = new String[1];
		fileToCompile[0] =  path + "Scripts/" + fileName + ".java";
		//get compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(fileToCompile);
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null,fileObjects);
		//compile
		boolean compiled = task.call();
		
		if (compiled){
				//compile successfull
				URL[] classURL = new URL[1];
				classURL[0] = new URL("file://" + path);
				URLClassLoader loader = new URLClassLoader(classURL);
				//load class
				Class<?> c  =loader.loadClass("Scripts." + fileName);
				
				//get default constructor
				Constructor<?> cons = c.getConstructors()[0];
				//return behaviour
				return (Behaviour<? extends Sprite>) cons.newInstance((Object[])null);
		}
		return null;
	}
}
