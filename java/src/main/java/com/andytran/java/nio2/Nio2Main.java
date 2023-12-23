package com.andytran.java.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Nio2Main {
    public static void main(String[] args) throws IOException {
        Path startingDir = Paths.get("C:\\Users\\ThoaiTran\\Desktop\\test");
        FileSearchExample visitor = new FileSearchExample("response.txt", startingDir);
        Files.walkFileTree(startingDir, visitor);
    }
}
