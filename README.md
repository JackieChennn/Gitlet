# Gitlet

Gitlet is a version control system implemented in Java by Jiacheng (Jackie) Chen. It is inspired by Git, a popular distributed version control system used by developers worldwide.

## About Gitlet

Gitlet is designed to track changes in files over time, allowing you to easily manage and collaborate on projects. It provides functionality for creating branches, committing changes, merging branches, and more. Gitlet follows a similar workflow and command structure to Git, making it familiar to users already experienced with Git.

## Usage

To use Gitlet, follow these steps:

1. Make sure you have Java Development Kit (JDK) installed on your system.
2. Clone the Gitlet repository: `git clone https://github.com/JackieChennn/Gitlet.git`
3. Navigate to the project directory: `cd Gitlet`
4. Initialize Gitlet: `java gitlet.Main init`
5. Use Gitlet: `java gitlet.Main add <file>` or others commands.

Here are some common Gitlet commands:

- `init`: Initializes a new Gitlet repository in the current directory.
- `add <file>`: Adds a file to the staging area.
- `commit <message>`: Creates a new commit with the changes in the staging area.
- `rm <file>`: Unstage the file if it is currently staged for addition. If the file is tracked in the current commit, stage it for removal.
- `branch <branch-name>`: Creates a new branch with the given name.
- `rm-branch <branch-name>`: Deletes the branch with the given name.
- `checkout <branch-name>`: Switches to the specified branch.
- `reset <commit id>`: Checks out all the files tracked by the given commit.
- `merge <branch-name>`: Merges changes from the specified branch into the current branch.
- `log`: Displays the commit history.
- `find <commit message>`: Prints out the ids of all commits that have the given commit message, one per line.
- `status`: Shows the current status of the repository.

Refer to the Gitlet documentation or run `java Gitlet help` for a complete list of commands and their descriptions.

## Features

Gitlet offers the following features:

- Initialization of a new Gitlet repository
- Staging changes and creating commits
- Switching between branches
- Merging changes from one branch into another
- Tracking file history and changes
- Basic branching and merging strategies
- Rollback to previous commits
- Retrieving previous versions of files

## Contributing

Contributions to Gitlet are welcome. If you find any issues or have suggestions for improvements, please create a new issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to modify and distribute it as per the terms of the license.

## Acknowledgements

Gitlet is inspired by the design and functionality of Git, which is developed by Linus Torvalds and the Git community.

Special thanks to the CS61B course at UC Berkeley for providing guidance and inspiration.

## Contact

If you have any questions, suggestions, or feedback, you can reach me at [jackie.jiachengchen@gmail.com].

## Enjoy using Gitlet for version control!