package com.xedox.paperclip.projects;

import com.xedox.paperclip.App;
import com.xedox.paperclip.tools.XDoc;
import com.xedox.paperclip.tools.XFile;
import java.io.File;

public class Project {

    private String name;
    private File path;
    private XFile main;

    public Project(File path) {
        this.path = path;
        this.name = path.getName();
        create();
    }

    public Project(String name) {
        this.name = name;
        this.path = new File(App.getProjectsFolder(), name);
        create();
    }

    public String getName() {
        return name;
    }

    public File getPath() {
        return path;
    }

    public void create() {
        if (!path.exists()) {
            try {
                path.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        main = new XFile(path, "main" + App.getExpansion());
        if (!main.exists()) {
            try {
                main.createNewFile();
                main.write(XDoc.pageExample);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        try {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            path.delete();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public XFile getMain() {
        return this.main;
    }

    public void setMain(XFile main) {
        this.main = main;
    }
}
