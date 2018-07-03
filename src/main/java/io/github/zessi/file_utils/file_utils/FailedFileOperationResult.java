package io.github.zessi.file_utils.file_utils;

import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

public class FailedFileOperationResult<T extends Throwable> {

    private final Path failedPath;
    private final T error;
    private final LocalDateTime utc_operation_time;

    public FailedFileOperationResult(Path failedPath, T error, LocalDateTime utc_operation_time) {
        this.failedPath = failedPath;
        this.error = error;
        this.utc_operation_time = utc_operation_time;
    }

    public FailedFileOperationResult(Path failedPath, T error) {
        this.failedPath = failedPath;
        this.error = error;
        this.utc_operation_time = LocalDateTime.now(Clock.systemUTC());
    }

    public Path getFailedPath() {
        return failedPath;
    }

    public T getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FailedFileOperationResult<?> that = (FailedFileOperationResult<?>) o;
        return Objects.equals(failedPath, that.failedPath) &&
                Objects.equals(error, that.error) &&
                Objects.equals(utc_operation_time, that.utc_operation_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(failedPath, error, utc_operation_time);
    }

    @Override
    public String toString() {
        return "FailedFileOperationResult{failedPath=" + failedPath + ", error=" + error + '}';
    }
}
