package com.sbt.javaschool.rnd.Lesson7;

import com.google.common.primitives.Bytes;
import org.springframework.asm.ClassReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class FinderClassLoader extends ClassLoader {
    private final List<String> paths;

    private final String methodName;

    FinderClassLoader(ClassLoader parent, String methodName, String... args){
        super(parent);
        this.methodName = methodName;
        this.paths = Arrays.asList(args);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (this.paths.isEmpty()) {
            System.out.println("No one set paths!");
            return null;
        }
        for (String path : this.paths) {
            try {
                byte b[] = fetchClassFromFS(path
                        + "\\"
                        + name
                        + ".class");
/**
 * Use guava util for compare bytes
 * Try find method in byte code
 * If we find its our "true" class
 */
                if (Bytes.indexOf(b, methodName.getBytes()) == -1)
                    continue;

                return defineClass(name, b, 0, b.length);

            } catch ( FileNotFoundException e) {
                super.findClass(name);
            } catch (IOException e) {
                super.findClass(name);
            }
        }
        return super.findClass(name);
    }
    /**
     * Найдено на хабре а там
     * Взято из www.java-tips.org/java-se-tips/java.io/reading-a-file-into-a-byte-array.html
     */
    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));

        // Get the size of the file
        long length = new File(path).length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+path);
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }

}
