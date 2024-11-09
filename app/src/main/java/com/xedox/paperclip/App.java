package com.xedox.paperclip;

import android.app.Application;
import android.content.Context;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xedox.paperclip.projects.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static List<Project> getProjects() {
        List<Project> data = new ArrayList<>();
        try {
            File projectsFolder = getProjectsFolder();
            for (File file : projectsFolder.listFiles()) {
                if (file.isDirectory()) data.add(new Project(file));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return data;
    }

    public static File getProjectsFolder() {
        File path = new File(context.getExternalFilesDir(null), "projects/");
        if (!path.exists()) path.mkdirs();
        return path;
    }

    public static String getExpansion() {
        return ".xdoc";
    }

    public static float getDisplayPixelDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        return density; 
    }
    
    public static int toDP(int pixelValue) {
        return (int) (pixelValue / getDisplayPixelDensity());
    }
}
