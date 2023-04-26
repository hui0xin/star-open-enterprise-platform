
package com.star.project.generator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 删除指定文件夹下文件
     * @param filePath
     */
    public static void deleteFolders(String filePath) {

        Path path = Paths.get(filePath);
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir,IOException exc) throws IOException {
                    Files.delete(dir);
                    log.info("文件夹被删除: {}", dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            log.info("FileUtils deleteFolders exception , e = {}",e);
        }
    }
}