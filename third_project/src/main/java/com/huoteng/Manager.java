package com.huoteng;

/**
 * 文件管理器
 * 对java的File中的方法进行封装
 * Created by huoteng on 6/13/15.
 */
public class Manager {

    public void order_cd(String path) {
        //cd
    }

    public void order_mkdir(String newName) {
        //mkdir newName
    }

    public void order_rm(String rmName) {
        //remove rmName
    }

    public String order_pwd() {
        //print path
        String currentPath = "admin:~ $";

        //get path


        return currentPath;
    }

    public String order_help() {
        //print help
        return "admin:~ $ help\n" +
                "order:\n" +
                "\tcd:change directory\n" +
                "\tmkdir:create a new folder\n" +
                "\trm:remove a folder or file\n" +
                "\tpwd:print current path\n" +
                "\thelp:print help\n" +
                "admin:~ $";
    }

}
