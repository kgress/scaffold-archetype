# Contributing
The development of the Scaffold-Archetype is intended to be a community driven effort; because of this, all contributions from the community are important! Regardless of a bug or a major feature addition/change, please file an issue for it. Ticket tracking allows everyone to ensure we're not duplicating efforts and obtaining the highest quality of work prior to merging.

## Code of Conduct
Be sure to view our [Code of Conduct](https://github.com/kgress/scaffold-archetype/CODE_OF_CONDUCT.md) before contributing.

## Filing Bugs
* First ensure the bug has not been filed yet under our Issues.
* If the issue does not exist, open a new issue with a clear and concise title. Please include a detailed description of the problem along with any accompanying code and reproduction steps.

When writing the description of the issue for a new bug, please follow the template/example below.

### Bug Template 
<title of ticket> archetype:generate not creating the core directories correctly </title of ticket>

h1. Bug:
When archetype:generate is invoked, the core directories are not being created correctly. This is due to the metadata file being improperly formatted. See code below:

Code blurb:
```
A fancy code example where the bug is happening
```

h1. Expected:
The core directories should be created correctly.   

h1. Repro:
* Do a thing
* Then do another thing
* Then do this last thing
* Observe the bug

## New Feature / Feature Improvement Proposals
Do you have a brand new feature idea? Do you think an existing feature could be updated? File an issue for it! Having a formal issue filed will allow for further discussion from other contributors. There is almost always the potential for risks or other considerations that our peers may see. Use this to your advantage! 

* First ensure your feature idea or improvement has not been filed yet under our Issues.
* If the issue does not exist, open a new issue with a clear and concise title. Please include a detailed description of why you think the new feature should be added and how this would be implemented along with relevant Acceptance Criteria. If it's an update to an existing feature, be sure to include the shortcomings of the existing feature and why the update is necessary.

When writing the description for the proposal, please follow the template/example below.

### New Feature or improvement Template
<title of ticket> Add more sample page objects and tests </title of ticket>

h1. Summary
Currently, we only have 1 test and 1 page object file. We should add some more examples to showcase additional use cases.

h1. Details
The files and types of tests we should add are....

<all of the implementation details. Where the files will live, new directories, etc etc>

h1. A/C
* Scaffold Archetype should be able to ...
* Scaffold Archetype should be able to ...
* User should be able to ...
* Implementing projects should be able to ...

## Submitting Work
Interested in fixing a bug or adding/updating a feature? Thank you! Your contributions are incredibly valuable to our community. Please follow the below development process for submitting your PR to our repo.

1. Fork the repository and create your branch from master.
2. Do the work! 
3. Run a `mvn clean install` to ensure everything compiles and the tests pass.
4. Squash your commits from your branch to a single commit and make the commit message the title of the ticket. Do *NOT* rebase and squash off of scaffold-archetype master.
5. Create a Pull Request with your forked repo against the core repo's `master` branch.
6. Review process 
7. Code merged into master
8. +100 points to awesomeness for contributing!

## License
By contributing to Scaffold Archetype, you agree that your contributions will be licensed. For more information regarding our open source licensing, please view the [license](https://github.com/kgress/scaffold-archetype/LICENSE.txt) document.
