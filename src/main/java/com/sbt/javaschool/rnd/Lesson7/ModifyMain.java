package com.sbt.javaschool.rnd.Lesson7;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Сделать свой класслоадер который из имеющегося списка путей на диске C:\ (2шт) ищет  класс SayHello
 * с методом sayHello().
 * К примеру в одном каталоге есть SayHello#say() в другом каталоге класс SayHello#sayHello()
 * Фактически необходима обертка в виде своего класслоадера над кодом в этом примере.
 * Свой класслоадер, перебрав имеющиеся классы, выбирает правильный и выдает его.
 *
 */
public class ModifyMain {

    private static final String DIR_NAME_OLD = System.getProperty("java.io.tmpdir") + "old";
    private static final String DIR_NAME_NEW = System.getProperty("java.io.tmpdir") + "new";
    private static final String CLASS_NAME = "SayHello";
    public static final String METHOD_NAME_INCORRECT = "say";
    public static final String METHOD_NAME_CORRECT = "sayHello";

    public static void main(String[] args) throws MalformedURLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        ModifyMain main = new ModifyMain();
        main.checkDirectory(DIR_NAME_OLD);
        main.checkDirectory(DIR_NAME_NEW);

        try {
            Class<?> aClass = Class.forName(CLASS_NAME);
            System.out.println(aClass.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс " + CLASS_NAME + " отсутсвует в ClassPath  ");
        }

        main.generateClass(DIR_NAME_OLD, CLASS_NAME, METHOD_NAME_INCORRECT);


        main.generateClass(DIR_NAME_NEW, CLASS_NAME, METHOD_NAME_CORRECT);

        ClassLoader classLoader = new FinderClassLoader(ClassLoader.getSystemClassLoader(), METHOD_NAME_CORRECT, DIR_NAME_OLD, DIR_NAME_OLD);

        Class<?> clazz = classLoader.loadClass(CLASS_NAME);
        Method method = clazz.getDeclaredMethod(METHOD_NAME_CORRECT);
        System.out.println("Вызываем метод " + method.getName() + " из загруженного класса " + clazz.getName());

        try {
//Use java11 and java 11 Class.newInstance is deprecated in java 9
            method.invoke(clazz.getDeclaredConstructor().newInstance());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void main2(String[] args) throws MalformedURLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        ModifyMain main = new ModifyMain();
        main.checkDirectory(DIR_NAME_OLD);
        main.checkDirectory(DIR_NAME_NEW);

        try {
            Class<?> aClass = Class.forName(CLASS_NAME);
            System.out.println(aClass.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс " + CLASS_NAME + " отсутсвует в ClassPath  ");
        }

        main.generateClass(DIR_NAME_OLD, CLASS_NAME, METHOD_NAME_INCORRECT);

        URLClassLoader classLoaderByDir1 = main.getClassLoaderByDir(DIR_NAME_OLD);

        Class<?> clazzFirst = loadClass(classLoaderByDir1, CLASS_NAME);

        if(main.searchMethod(clazzFirst, METHOD_NAME_CORRECT)){
            System.exit(0);
        }

        main.generateClass(DIR_NAME_NEW, CLASS_NAME, METHOD_NAME_CORRECT);

        URLClassLoader classLoaderByDir2 = main.getClassLoaderByDir(DIR_NAME_NEW);
        Class<?> clazzSecond = loadClass(classLoaderByDir2, CLASS_NAME);

        if(main.searchMethod(clazzSecond, METHOD_NAME_CORRECT)){
            Method method = clazzSecond.getDeclaredMethod(METHOD_NAME_CORRECT);
            System.out.println("Вызываем метод " + method.getName() + " из загруженного класса " + clazzSecond.getName());

            try {
//Use java11 and java 11 Class.newInstance is deprecated in java 9
                method.invoke(clazzSecond.getDeclaredConstructor().newInstance());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkDirectory(String dirName) {
        File directory = new File(dirName);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    private static Class<?> loadClass(URLClassLoader classLoader, String clazzName) {
        Class<?> clazzSecond = null;
        try {
            clazzSecond = classLoader.loadClass(clazzName);
            System.out.println("Из " + Arrays.asList(classLoader.getURLs()).get(0).getPath() + " загрузили класс " + clazzSecond.getName() +"\n");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось загрузить класс " + clazzName + " из класслоадера " + classLoader.toString());
        }
        return clazzSecond;
    }

    private void generateClass(String dirName, String clazzName, String methodName){
        try {
            File tempFile = new File (dirName, clazzName +".java");
            tempFile.deleteOnExit();
            String className = tempFile.getName().split("\\.")[0];
            String sourceCode = "public class " + className + " { public void "+ methodName + "() { System.out.println(\"Hello everybody !\"); } }";
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(sourceCode);
            fileWriter.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);

            File parentDirectory = tempFile.getParentFile();
            manager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
            Iterable<? extends JavaFileObject> compilationUnits = manager.getJavaFileObjectsFromFiles(Arrays.asList(tempFile));
            compiler.getTask(null, manager, null, null, null, compilationUnits).call();
            manager.close();
            System.out.println("Сгенерирован класс " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean searchMethod(Class<?> clazz, String methodName) {
        try {
            Method say = clazz.getMethod(methodName);
            System.out.println("В классе " + clazz.getName()  + " загрузчика " + clazz.getClassLoader() + " найден метод " + say.getName());
            return true;
        } catch (NoSuchMethodException e) {
            System.out.println("В классе " + clazz.getName() + " загрузчика " + clazz.getClassLoader() + " метод " + methodName + " не найден!");
        }
        return false;
    }

    public URLClassLoader getClassLoaderByDir(String directoryName) throws MalformedURLException {
        File dir = new File(directoryName);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{dir.toURI().toURL()});
        return urlClassLoader;
    }
}