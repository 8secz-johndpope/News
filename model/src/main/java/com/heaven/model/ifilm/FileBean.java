package com.heaven.model.ifilm;


/**
 * FileName: com.ifilmo.photography.activities.MetarialManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2016-12-12 21:10
 *
 * @version V1.0 文件模型
 */

public class FileBean implements Comparable<FileBean> {
    private String name;
    private String path;
    private boolean isFile;

    public FileBean(String name, String path, boolean isFile) {
        this.name = name;
        this.path = path;
        this.isFile = isFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    @Override
    public int compareTo(FileBean another) {
        if (!isFile && another.isFile) {
            return -2333;
        } else if (isFile && !another.isFile) {
            return 2333;
        }
        return this.name.compareTo(another.getName());
    }
}
