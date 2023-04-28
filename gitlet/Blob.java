package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;
import static gitlet.MyUtils.*;

public class Blob implements Serializable {
    /** The id(hash code) of the Blob. */
    private String blobID;

    /** The file id(hash code) of the Blob. */
    private String fileID;

    /** The name of this file. */
    private String fileName;

    /** the entire contents of this file as a String */
    private String fileContents;

    public Blob(File file) {
        this.fileName = file.getName();
        this.fileID = hashFile(file);
        this.fileContents = readContentsAsString(file);
        this.blobID = sha1(serialize(this));
    }

    public String getBlobID() {
        return blobID;
    }

    public String getFileID() {
        return fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileContents() {
        return fileContents;
    }
}
