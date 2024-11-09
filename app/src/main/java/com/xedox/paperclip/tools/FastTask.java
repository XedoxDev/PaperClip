package com.xedox.paperclip.tools;

public class FastTask {
    public static void execute(Task task) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                	task.execute(this);
                } catch(Exception err) {
                	err.printStackTrace();
                }
                
            }
        };
        thread.start();
    }

    public static interface Task {
        public void execute(Thread thread);
    }
}
