package com.andytran.java.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Optional;

public class CreationDateResolver {
    public Instant resolveCreationTimeWithBasicAttributes(Path path) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        return attributes.creationTime().toInstant();
    }

    public Optional<Instant> resolveCreationTimeWithAttribute(Path path) throws IOException {
        FileTime fileTime = (FileTime) Files.getAttribute(path, "creationTime");
        return Optional.ofNullable(fileTime).map(FileTime::toInstant);
    }
}
