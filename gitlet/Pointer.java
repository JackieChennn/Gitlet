package gitlet;

import java.io.Serializable;

import static gitlet.Repository.*;
import static gitlet.MyUtils.*;

public class Pointer implements Serializable {
     /** HEAD point to active branch,
     branch point to a commit.
         -- master
         -- other branch */
    private String commitID;
    private String branchName;
    private String initCommitID;
    private String activeBranchName;

    public Pointer(boolean isHead, String branchName, String ID) {
        if (isHead) {
            this.initCommitID = ID;
            this.activeBranchName = branchName;
        } else {
            this.commitID = ID;
            this.branchName = branchName;
        }
    }

    /** save branch by branchName */
    public void saveBranchFile() {
        saveObj(BRANCH_FOLDER, this.branchName, this);
    }

    /** save HEAD by headName */
    public void saveHEADFile() {
        saveObj(GITLET_DIR, headName, this);
    }

    /** get ActiveBranchName in HEAD */
    public String getActiveBranchName() {
        return this.activeBranchName;
    }

    /** get initCommitID in HEAD */
    public String getInitCommitID() {
        return this.initCommitID;
    }

    /** get CommitID in branch */
    public String getCommitID() {
        return this.commitID;
    }
}
