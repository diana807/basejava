package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {


        String filePath = "/Users/gareevaa/git/basejava2/.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("ERROR", e);
        }
        File dir = new File("./src/com/urise/webapp");
        printDirectoryDeeply(dir);
        //       System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String element : list) {
//                System.out.println(element);
//            }
//        }
        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void printDirectoryDeeply(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("    " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(file.getName());
                    printDirectoryDeeply(file);

                }
            }
        }
    }
}

