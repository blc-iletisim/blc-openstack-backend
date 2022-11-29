package com.blc.customerInterface.pem;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.security.Key;

public class PemFile {
    private PemObject pemObject;

    public PemFile (Key key, String description) {
        this.pemObject = new PemObject(description, key.getEncoded());
    }

    public void write(File file) throws FileNotFoundException, IOException {
        PemWriter pemWriter = new PemWriter(new FileWriter(file));
        try {
            pemWriter.writeObject(this.pemObject);
        } finally {
            pemWriter.close();
        }
    }
}
