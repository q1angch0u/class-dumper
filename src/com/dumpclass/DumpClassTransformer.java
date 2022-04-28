package com.dumpclass;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author q1angch0u
 */
public class DumpClassTransformer implements ClassFileTransformer {
    private static final String DUMP_PACKAGE = System.getProperty("dump_package");
    private static final String OUT_FOLDER = System.getProperty("dump_out_folder");


    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null || className.isEmpty()) {
            return classfileBuffer;
        }

        if (classfileBuffer == null) {
            return classfileBuffer;
        }

        String tmpClassName = className.replace("/", ".");
        if (tmpClassName.startsWith(DUMP_PACKAGE)) {
            try {
                writeClass(className, classfileBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return classfileBuffer;
    }

    private void writeClass(String className, byte[] classfileBuffer) {
        File file = null;
        FileOutputStream fileOutputStream = null;

        try {
            String folder = OUT_FOLDER;
            if (!folder.endsWith(File.separator)) {
                folder = folder + File.separator;
            }

            String classPath = className.substring(0, className.lastIndexOf("/"));
            className = className.substring(className.lastIndexOf("/") + 1, className.length());

            String path = OUT_FOLDER + File.separator + classPath;
            file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(path + File.separator + className + ".class");

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(classfileBuffer);

            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    fileOutputStream = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}