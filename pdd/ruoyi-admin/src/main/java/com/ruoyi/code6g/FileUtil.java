package com.ruoyi.code6g;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileUtil {


    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {

                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                //  System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }

    public static ArrayList<String> getFiles_short_by_time(String path) {
//        ArrayList<String> files = new ArrayList<String>();
//        File file = new File(path);
//        File[] tempList = file.listFiles();
//
//        for (int i = 0; i < tempList.length; i++) {
//            if (tempList[i].isFile()) {
//
//
//                files.add(tempList[i].getName().toString());
//            }
//            if (tempList[i].isDirectory()) {
//                //  System.out.println("文件夹：" + tempList[i]);
//            }
//        }
//        return files;


        List<File> list = getFiles_list(path, new ArrayList<File>());
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {//降序<;升序>
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }

        ArrayList<String> ArrayListFiles = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            File file = (File) list.get(i);
            if (file.isFile()) {

                ArrayListFiles.add(file.getName().toString());
            }
            if (file.isDirectory()) {
                //  System.out.println("文件夹：" + tempList[i]);
            }
        }
        return ArrayListFiles;
    }


    private static List<File> getFiles_list(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    FileUtil.getFiles_list(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = FileUtil.getFiles_short_by_time("/home/ruoyi/uploadPath/spzp/");
        for (String s : arrayList) {
            System.out.println(s);
        }
    }
}
