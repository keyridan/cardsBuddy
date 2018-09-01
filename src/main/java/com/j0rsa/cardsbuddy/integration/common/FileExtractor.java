package com.j0rsa.cardsbuddy.integration.common;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.*;
import java.util.function.Consumer;

public class FileExtractor {
    public static void unpackAndReadData(File file, Consumer<String[]> consumer) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        TarArchiveInputStream tarInput = new TarArchiveInputStream(new BZip2CompressorInputStream(fileInputStream));
        TarArchiveEntry currentEntry;
        while ((currentEntry = tarInput.getNextTarEntry()) != null) {
            File zipFile = new File(currentEntry.getName());
            if (zipFile.getName().startsWith("._")) {
                continue;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(tarInput));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split("\t");
                consumer.accept(lineParts);
            }
        }
    }
}
