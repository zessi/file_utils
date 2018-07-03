package io.github.zessi.file_utils.file_utils;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static io.github.zessi.file_utils.file_utils.test_utils.TestUtils.*;
import static io.github.zessi.file_utils.file_utils.test_config.TestConfig.*;
import static io.github.zessi.file_utils.file_utils.other_utils.unit_space_randomizers.UnitSpaceRandomizers.*;

import static org.junit.Assert.*;

public class Test_DeleteUtils {

    @Test
    public void test_deleteAll() throws IOException {
        final Path parentPath = USER_PATH.resolve(String.valueOf(getRandomASCIISmallCaseLettersOnly(10)));
        final Set<Path> createdPaths = createRandomDirectoryStructure(parentPath, 5, 6);
        for (Path createdPath : createdPaths) {
            assertTrue(Files.exists(createdPath));
            System.out.println("Path: \"" + createdPath + "\" exists");
        }
        final Set<FailedFileOperationResult<? extends Throwable>> failedFileOperationResults = DeleteUtils.deleteAll(parentPath);
        System.out.println();
        for (FailedFileOperationResult<? extends Throwable> failedFileOperationResult : failedFileOperationResults) {
            System.out.println("failedFileOperationResult = " + failedFileOperationResult);
        }
        System.out.println();
        if (failedFileOperationResults.isEmpty()) {
            for (Path createdPath : createdPaths) {
                assertTrue(Files.notExists(createdPath));
                System.out.println("Path: \"" + createdPath + "\" not exists");
            }
        }
    }

}
