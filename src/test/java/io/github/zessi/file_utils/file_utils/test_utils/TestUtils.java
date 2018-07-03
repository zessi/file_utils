package io.github.zessi.file_utils.file_utils.test_utils;

import io.github.zessi.file_utils.file_utils.other_utils.unit_space_randomizers.UnitSpaceRandomizers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class TestUtils {

    /**
     * A method that creates a randomized directory structure based on the provided depth and width.
     *
     * @param parent The parent path at which the directory structure will be created.
     * @param depth  The maximum number of levels that will be created. Minimum level is 1 which means that one level of files/directories will be created.
     * @param width  The maximum number of created file and folders in each level. Minimum width is 1 which means that only one file or directory will be created.
     * @return paths of all of the created files/directories
     * @throws IOException
     */
    public static Set<Path> createRandomDirectoryStructure(final Path parent, final int depth, final int width) throws IOException {
        if (parent == null) throw new IllegalArgumentException("parent path is null");
        if (depth < 1) throw new IllegalArgumentException("invalid depth argument");
        if (width < 1) throw new IllegalArgumentException("invalid width argument");
        if (Files.notExists(parent)) Files.createDirectories(parent);
        final Set<Path> createdPaths = new HashSet<>();
        for (int i = 1; i <= width; i++) {
            if (new SecureRandom().nextBoolean()) {
                final Path file = parent.resolve(String.valueOf(UnitSpaceRandomizers.getRandomASCIISmallCaseLettersOnly(10)));
                Files.createFile(file);
                createdPaths.add(file);
            } else {
                final Path directory = parent.resolve(String.valueOf(UnitSpaceRandomizers.getRandomASCIISmallCaseLettersOnly(10)));
                Files.createDirectories(directory);
                createdPaths.add(directory);
                if (depth >= 2) {
                    final Set<Path> subCreatedPaths = createRandomDirectoryStructure(directory, depth - 1, width);
                    createdPaths.addAll(subCreatedPaths);
                }
            }
        }
        return createdPaths;
    }

}
