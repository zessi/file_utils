package io.github.zessi.file_utils.file_utils;

import io.github.zessi.file_utils.file_utils.other_utils.unit_space_randomizers.UnitSpaceRandomizers;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static io.github.zessi.file_utils.file_utils.test_utils.TestUtils.*;

import static org.junit.Assert.*;

public class Test_TestUtils {

    @Test
    public void test_createRandomDirectoryStructure() throws IOException {
        final Path parent = Paths.get("E:\\tests").resolve(String.valueOf(UnitSpaceRandomizers.getRandomASCIISmallCaseLettersOnly(10)));
        final int depth = 4;
        final int width = 5;
        System.out.println("parent = " + parent);
        System.out.println("depth = " + depth);
        System.out.println("width = " + width);
        Files.createDirectories(parent);
        final Set<Path> createdPaths = createRandomDirectoryStructure(parent, depth, width);

        for (Path createdPath : createdPaths) {
            System.out.println(createdPath);
            final Path relativePath = parent.relativize(createdPath);
            System.out.println("relativePath = \"" + relativePath + "\" relativePath.getNameCount() = \"" + relativePath.getNameCount() + "\"");
            assertTrue(relativePath.getNameCount() <= depth);
            if (Files.isDirectory(createdPath)) {
                File file = createdPath.toFile();
                final File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    System.out.println("subFiles.length = " + subFiles.length);
                    assertTrue(subFiles.length <= width);
                }
            }
        }
    }

}
