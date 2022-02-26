# Scaffold Archetype
Scaffold Archetype is a maven archetype meant for the purpose of creating new Scaffold projects quickly and easily. It uses 
`maven-archetype-plugin` and `maven-resources-plugin` 3.x to build a new project's directory structure , from `archtype-metadata.xml` 
in `src/main/resources/META-INF/maven`, and create all required files for a Scaffold project based on templates included 
in `src/main/resources/archetype-resources`.  

For additional documentation on how to run Scaffold testing or setting up additional test configurations, [visit the Scaffold repository here](https://github.com/kgress/scaffold/README.md).

# Current Version
To view the most current version, [visit the Central Repository](https://search.maven.org/search?q=g:io.github.kgress.scaffold-archetype).

# Links
- [Contributing Guide](https://github.com/kgress/scaffold-archetype/blob/master/CONTRIBUTING.md)
- [Code of Conduct](https://github.com/kgress/scaffold-archetype/blob/master/CODE_OF_CONDUCT.md)
- [License](https://github.com/kgress/scaffold-archetype/blob/master/LICENSE.txt)
    
# Required Tools for Dev
* Java 11
* Maven 3.x

# CI
The build can be found [on the Github Actions page](https://github.com/kgress/scaffold-archetype/actions).

# Running the Archetype
Currently, the best way to use this archetype is to invoke an archetype:generate command after cloning this project. 
In the near future, the artifacts will be available through maven central and usable through a new project wizard through eclipse or intellij. This documentation
will be updated to reflect that.

## Local - Archetype:Generate
1. Clone the repo `git clone git@github.com:kgress/scaffold-archetype.git` in a directory where you would like the scaffold-archetype to be
2. Switch directory to `scaffold-archetype` and run a `mvn install`
3. Switch directory back to where you would like to create the new project with the archetype. A new folder will automatically be created for you based on the artifact name provided to the archetype generator.
4. Run the following command: `mvn archetype:generate -DarchetypeGroupId=io.github.kgress.scaffold-archetype -DarchetypeArtifactId=scaffold-archetype -DarchetypeVersion=DEV`
5. Follow the prompts for entering your groupId and artifactId (along with version and package if you want something different than the defaults)
6. The archetype will generate the new project for you.

## IDE - New Project Wizard
IntelliJ
1. In IntelliJ, create a new project using the new project wizard
2. Select maven as the project type, java 11 as the JDK, and check the "create from archetype" checkbox
3. Click the "add archetype" button
4. Enter the groupId of `io.github.kgress.scaffold-archetype`
5. Enter the artifactId of `scaffold-archetype`
6. Enter the current version of `scaffold-archetype` by checking out [the Central Repository](https://search.maven.org/search?q=g:io.github.kgress.scaffold-archetype)
7. Click okay