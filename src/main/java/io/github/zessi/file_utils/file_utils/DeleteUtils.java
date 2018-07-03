package io.github.zessi.file_utils.file_utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DeleteUtils {

    /**
     * Deletes the passed file/directory.
     * If the passed path is referring to a file. then that an attempt to delete the file will be made.
     * If the passed path is referring to a directory. then the an attempt will be made to delete the directory and all of its content.
     *
     * @param path The path of the file/directory to delete.
     * @return a set of paths containing the paths where the delete operation have failed.
     */
    public static Set<FailedFileOperationResult<? extends Throwable>> deleteAll(Path path) {
        final HashSet<FailedFileOperationResult<? extends Throwable>> fails = new HashSet<>();
        if (Files.notExists(path)) throw new IllegalArgumentException("Passed path refers to a non-existent file/directory");
        if (Files.isRegularFile(path)) {
            try {
                Files.deleteIfExists(path); //End point for files
            } catch (IOException e) {
                fails.add(new FailedFileOperationResult<>(path, e));
                return fails;
            }
        }
        if (Files.isDirectory(path)) {
            try {
                Files.walkFileTree(path, new FileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        fails.addAll(deleteAll(file));
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        fails.add(new FailedFileOperationResult<>(file, exc));
                        fails.add(new FailedFileOperationResult<>(file.getParent(), new IOException(exc)));
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        try {
                            Files.deleteIfExists(dir); //End point for directory
                        } catch (IOException e) {
                            fails.add(new FailedFileOperationResult<>(dir, e));
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                fails.add(new FailedFileOperationResult<>(path, e));
            }
        }
        for (FailedFileOperationResult<? extends Throwable> fail : fails) {
            if (Files.notExists(fail.getFailedPath())) fails.remove(fail);
        }
        return fails;
    }

}
