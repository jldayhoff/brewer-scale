# README #

The following describes the steps for building and deploying the Brewers Scale app to Cytoscape3

### Building the project ###

* make sure mvn is installed either as a plugin in your IDE or you have the binary. I'm running with version 3.3.9
* If running in IDE, run the install phase. If running from command line run `mvn clean install`
* You should then see a `target` directory in your project's root directory

### Deploying the app to Cytoscape ###

* Copy the war file from `[work directory]/brewer-scale/target/brewer-scale-1.0-SNAPSHOT.jar` to your home directory `CytoscapeConfiguration/3/apps/installed/`. If using Unix you can create a symbolic link. More detailed instructions here for Windows and Mac: http://wiki.cytoscape.org/Cytoscape_3/AppDeveloper/Cytoscape_App_Ladder/BuildAndRunSampleApp

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* UMUC SWEN-670