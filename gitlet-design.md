# Gitlet Design Document

**Name**: Jackie Chen

## Persistence

Describe your strategy for ensuring that you don’t lose the state of your program across multiple runs. Here are some tips for writing this section:

- This section should be structured as a list of all the times you will need to record the state of the program or files. For each case, you must prove that your design ensures correct behavior. For example, explain how you intend to make sure that after we call java gitlet.Main add wug.txt, on the next execution of java gitlet.Main commit -m “modify wug.txt”, the correct commit will be made.
- A good strategy for reasoning about persistence is to identify which pieces of data are needed across multiple calls to Gitlet. Then, prove that the data remains consistent for all future calls.
- This section should also include a description of your .gitlet directory and any files or subdirectories you intend on including there.

The directory structure looks like this:

```java
/**
CWD                             <==== Whatever the current working directory is.
└── .gitlet                     <==== top level folder for all persistent data in GitLet project folder
    ├── HEAD                    <==== file containing the persistent object for head
    ├── addition                <==== folder containing all of the staging file for addition
    │   ├── file1               <==== A single file for addition
    │   ├── file2
    │   ├── ...
    │   └── fileN
    ├── blobs                   <==== folder containing all the persistent folder for folder of blobs
    │   ├── blobDir1(00)        <==== A directory of blobs (two characters of hex)
    │   │   ├── blob1           <==== A single Bolb instance stored to a file
    │   │   ├── ...
    │   │   └── blobN
    │   ├── blobDir2(01)
    │   │   ├── blob1
    │   │   ├── ...
    │   │   └── blobN
    │   ├── ...
    │   └── blobDirN(ff)
    │       ├── blob1
    │       ├── ...
    │       └── blobN
    ├── branch                  <==== folder containing all of the persistent object for branch
    │   ├── branch1             <==== A single Pointer(Branch) stored to a file
    │   ├── branch2
    │   ├── ...
    │   └── branchN
    ├── commits                 <==== All the commits are stored in this directory
    │   ├── commit1             <==== A single Commit instance stored to a file
    │   ├── commit2
    │   ├── ...
    │   └── commitN
    ├── remote                  <==== All the remotes are stored in this directory
    │   ├── remote1             <==== A single Remote instance stored to a file
    │   ├── remote2
    │   ├── ...
    │   └── remoteN
    └── removed                 <==== folder containing all the staging file for removal
        ├── file1               <==== A single file for removal
        ├── file2
        ├── ...
        └── fileN
*/
```

The `Repository` will set up all persistence. It will:

1. Create the `.gitlet` folder
2. Create the `commits` folder
3. Create the `addition` folder
4. Create the `removed` folder
5. Create the `branch` folder
6. Create the `HEAD`  of instance of `Pointer`
7. Create the `initcommit`  of instance of `Commit` in the `commits` folder