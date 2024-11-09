package com.xedox.paperclip.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class XFile extends File {
    public XFile(String name) {
        super(name);
    }

    public XFile(File path, String name) {
        super(path, name);
    }

    public XFile(String path, String name) {
        super(new File(path), name);
    }

    public void write(String text) {
        try (FileWriter w = new FileWriter(this)) {
            w.write(text);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public String read() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(this))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException err) {
            err.printStackTrace();
            return "";
        }
        return sb.toString();
    }
}