package com.j0rsa.cardsbuddy.integration.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.*;
import java.util.function.Consumer;

@Slf4j
public class FileExtractor {
    public static void unpackAndReadData(File file, Consumer<String[]> consumer) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        TarArchiveInputStream tarInput = new TarArchiveInputStream(new BZip2CompressorInputStream(fileInputStream));
        TarArchiveEntry currentEntry;
        while ((currentEntry = tarInput.getNextTarEntry()) != null) {
            String zipFileName = new File(currentEntry.getName()).getName();
            if (zipFileName.startsWith("._")) {
                continue;
            }
            log.info("File: ", zipFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(tarInput));
            br.lines()
                    .forEach(line -> {
                        String[] lineParts = line.split("\t");
                        consumer.accept(lineParts);
                    });
        }
    }
}
