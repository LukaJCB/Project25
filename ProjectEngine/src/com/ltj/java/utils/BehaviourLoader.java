package com.ltj.java.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.utils.BasicIO;

public class BehaviourLoader {

	private static URLClassLoader loader;
	
	
	
	public static URLClassLoader getLoader() {
		return loader;
	}


	@SuppressWarnings("unchecked")
	public static Behaviour<? extends Sprite> loadBehaviour(String path, String name) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		
		String[] fileToCompile = new String[1];
		fileToCompile[0] =  path + File.separatorChar + "scripts"+ File.separatorChar + name + ".java";
		//get compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(fileToCompile);
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null,fileObjects);
		//compile
		boolean compiled = task.call();
		
		if (compiled){
			moveToBinFolder(path,name);
			
			//load class
			Class<?> c  = loadFromBinary(path, name);

			//get default constructor
			Constructor<?> cons = c.getConstructors()[0];
			//return behaviour
			return (Behaviour<? extends Sprite>) cons.newInstance();
		}
		return null;
	}
	
	
	private static void moveToBinFolder(String path, String name) throws IOException{
		BasicIO.copy(new File(path +File.separatorChar + "scripts" + File.separatorChar + name +  ".class"), 
				new File(path + File.separatorChar + "scripts"+ File.separatorChar + "bin" + File.separatorChar + name + ".class"));
		new File(path +File.separatorChar + "scripts" + File.separatorChar + name + ".class").delete();
	}
	
	public static Class<?> loadFromBinary(String path,String name) throws MalformedURLException, ClassNotFoundException{
		
		if (loader == null){
			URL[] classURL = new URL[1];
			classURL[0] = new File(path + File.separatorChar + "scripts"+ File.separatorChar + "bin" + File.separatorChar ).toURI().toURL();
			
			loader = new URLClassLoader(classURL);
		}
		
		//load class
		return loader.loadClass(name);
	}
}
