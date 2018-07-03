package io.github.zessi.file_utils.file_utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class FileDeleteOperationResult {

    private final Path parentPathToDelete;
    private Map<Class<? extends Throwable>, FailedFileOperationResult<? extends Throwable>> mappedFails;

    public FileDeleteOperationResult(Path parentPathToDelete) {
        this.parentPathToDelete = parentPathToDelete;
    }

    public void delete() throws IOException {
        final Set<FailedFileOperationResult<? extends Throwable>> failedFileOperationResults = DeleteUtils.deleteAll(this.parentPathToDelete);
        for (FailedFileOperationResult<? extends Throwable> failedFileOperationResult : failedFileOperationResults) {
            mappedFails.put(failedFileOperationResult.getError().getClass(), failedFileOperationResult);
        }
    }

    public Path getParentPathToDelete() {
        return parentPathToDelete;
    }

    public Map<Class<? extends Throwable>, FailedFileOperationResult<? extends Throwable>> getMappedFails() {
        return mappedFails;
    }
}
