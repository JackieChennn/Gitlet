package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import static gitlet.Repository.*;
import static gitlet.Utils.*;

public class MyUtils {
    /** get file id by using filename and file content(as string) */
    public static String hashFile(File file) {
        return sha1(serialize(file.getName()), serialize(readContentsAsString(file)));
    }

    /** Set the first and second characters of blob id as name of folder of blobs. */
    public static String getDirID(String ID) {
        return ID.substring(0, 2);
    }

    public static void saveDir(File FOLDER, String dirID) {
        File dir = join(FOLDER, dirID);
        dir.mkdir();
    }

    public static void saveDirAndObjInBlobs(Serializable SerObj, File FOLDER, String ID) {
        Commit parentCommit = getCurrentCommit();
        List<String> parentBlobIDs = parentCommit.getBlobIDs();
        // if this blobID is equal to one of the blobID in parentCommit, there's NO need to create duplicate blobFile
        if (parentBlobIDs.size() != 0) {
            for (String parentBlobID : parentBlobIDs) {
                if (ID.equals(parentBlobID)) {
                    return;
                }
            }
        }
        List<String> dirIDList = plainFilenamesIn(FOLDER);
        String dirID = getDirID(ID);
        if (!dirIDList.contains(dirID)) {
            saveDir(FOLDER, dirID);
        }
    }

    /** save file by FOLDER */
    public static void saveObj(File FOLDER, String fileName, Serializable SerObj) {
        File file = join(FOLDER, fileName);
        writeObject(file, SerObj);
    }

    /** save file by FOLDER */
    public static void saveContent(File FOLDER, String name, String content) {
        File file = join(FOLDER, name);
        writeContents(file, content);
    }
    /** validate filesystem */
    public static boolean validateDir(){
        return GITLET_DIR.exists();
    }


}
