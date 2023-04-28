package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.*;
import static gitlet.MyUtils.*;
import static gitlet.Repository.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Jackie Chen
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    /** The timestamp of this Commit. */
    private Date timestamp;
    /** The id(hash code) of blobs. */
    private List<String> blobIDs;
    /** The id(hash code) of parent Commits, a Commit has at most two parents. */
    private List<String> parentIDs;
    /** The id(hash code) of parent Commits, a Commit has at most two parents. */
    private String commitID;
    /** The name of files in blobs. */
    private List<String> fileNames;
    /** The id(hash code) of files. */
    private List<String> fileIDs;
    /** the marked count in commit */
    private int markedCount;
    /** the distance in commit */
    private int distance;

    public Commit(String message, Date timestamp, List<String> parentIDs) {
        this.message = message;
        this.timestamp = timestamp;
        this.parentIDs = parentIDs;
        this.fileNames = new LinkedList<>();
        this.blobIDs = new LinkedList<>();
        this.fileIDs = new LinkedList<>();

        this.markedCount = 0;
        this.distance = 0;
        this.commitID = sha1(serialize(this));
    }

    public Blob makeBlob(File file) {
        Blob blob = new Blob(file);
        String blobID = blob.getBlobID();
        saveDirAndObjInBlobs(blob, BLOB_FOLDER, blobID);
        return blob;
    }

    private void getInfoFromOnlyParent() {
        if (this.parentIDs.size() == 1) {
            Commit parentCommit = readObject(join(COMMITS_FOLDER, this.parentIDs.get(0)), Commit.class);
            this.fileNames.addAll(parentCommit.getFileNames());
            this.blobIDs.addAll(parentCommit.getBlobIDs());
            this.fileIDs.addAll(parentCommit.getFileIDs());
        }
    }

    private void getInfoFromStaging() {
        // In addition folder
        for (String fileName : plainFilenamesIn(ADDITION_FOLDER)) {
            File file = join(ADDITION_FOLDER, fileName);
            String fileID = hashFile(file);
            // different filename and different fileID, we should add file from staging
            if (!this.fileNames.contains(fileName) && !this.fileIDs.contains(fileID)) {
                this.fileNames.add(fileName);
                this.fileIDs.add(fileID);
                this.blobIDs.add(makeBlob(file).getBlobID());
            // same filename but different fileID, we should use file from staging
            } else if (this.fileNames.contains(fileName) && !this.fileIDs.contains(fileID)) {
                // delete file from parent
                for (String blobID : this.blobIDs) {
                    Blob blob = readObject(join(BLOB_FOLDER, getDirID(blobID), blobID), Blob.class);
                    if (blob.getFileName().equals(fileName)) {
                        this.fileNames.remove(blob.getFileName());
                        this.fileIDs.remove(blob.getFileID());
                        this.blobIDs.remove(blob.getBlobID());
                        break;
                    }
                }
                // add the file from staging
                this.fileIDs.add(fileID);
                this.blobIDs.add(makeBlob(file).getBlobID());
            }
            // same filename AND same fileID, do nothing.
        }
        // In removed folder
        List<String> removedBlobIDs = new LinkedList<>();
        for (String fileName : plainFilenamesIn(REMOVED_FOLDER)) {
            File file = join(REMOVED_FOLDER, fileName);
            String fileID = hashFile(file);
            // same filename and same fileID, remove it from new commit
            if (this.fileNames.contains(fileName) && this.fileIDs.contains(fileID)) {
                this.fileNames.remove(fileName);
                this.fileIDs.remove(fileID);
                for (String blobID : this.blobIDs) {
                    Blob blob = readObject(join(BLOB_FOLDER, getDirID(blobID), blobID), Blob.class);
                    if (fileID.equals(blob.getFileID())) {
                        removedBlobIDs.add(blobID);
                    }
                }
            }
        }
        this.blobIDs.removeAll(removedBlobIDs);
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<String> getBlobIDs() {
        return blobIDs;
    }

    public List<String> getParentIDs() {
        return parentIDs;
    }

    public String getCommitID() {
        return commitID;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public List<String> getFileIDs() {
        return fileIDs;
    }

    public void resetMarkedCount() {
        this.markedCount = 0;
    }

    public void updatedMarkedCount() {
        this.markedCount += 1;
    }

    public int getMarkCount() {
        return markedCount;
    }

    public void resetDistance() {
        this.distance = 0;
    }

    public void updatedDistance(int distance) {
        this.distance += distance;
    }

    public int getDistance() {
        return distance;
    }

    public HashSet<Blob> getBlobs() {
        HashSet<Blob> blobs = new HashSet<>();
        for (String ID : blobIDs) {
            Blob blob = readObject(join(BLOB_FOLDER, getDirID(ID), ID), Blob.class);
            blobs.add(blob);
        }
        return blobs;
    }
}
