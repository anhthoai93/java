package com.andytran.java.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Optional;

class CreationDateResolverTest {
  @Test
    public void givenFile_whenGettingCreationDateTimeFromBasicAttributes_thenReturnDate() throws IOException {
      File file = File.createTempFile("createdFile", ".txt");
      Path path = file.toPath();
      CreationDateResolver creationDateResolver = new CreationDateResolver();
      Instant instant = creationDateResolver.resolveCreationTimeWithBasicAttributes(path);
      System.out.println(instant);
  }

    @Test
    public void givenFile_whenGettingCreationDateTimeFromAttribute_thenReturnDate() throws IOException {
        File file = File.createTempFile("createdFile", ".txt");
        Path path = file.toPath();
        CreationDateResolver creationDateResolver = new CreationDateResolver();
        Optional<Instant> instant = creationDateResolver.resolveCreationTimeWithAttribute(path);
        System.out.println(instant.orElse(null));
    }
}