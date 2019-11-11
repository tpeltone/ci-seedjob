# Continuous Integration Seed Job As Code

## Prerequisites

### Jobs

[List of the jobs](jobs/README.md)

### Global Security

Configure Global Security add  

- "View/Read:anonymous"
- "Job/Read:anonymous"

### Plugins needed

[Job DSL Plugin](https://wiki.jenkins.io/display/JENKINS/Job+DSL+Plugin)

[Build Monitor Plugin](https://wiki.jenkins.io/display/JENKINS/Build+Monitor+Plugin)

### Create The seed job

Create a Pipeline seed job **name-seed-job**  
```New Item -> Pipeline```

**Build Triggers**  
Check the box *Build periodically* if you want to use this 
In *Schedule* add ```@midnight```  

**Pipeline**  
*Definition* ```Pipeline script```  

Copy and paste the script below:

- Use preferred **branch:** *"master"*
- Use the SSH key login for the Service User in **credentialsId:** *"my-key"*
- Use GIT repo url where the seed job is stored **url:** *"ssh://git@github.com:tpeltone/ci-seedjob.git"*  

```groovy
import javaposse.jobdsl.plugin.*
node {
    git branch: "master", credentialsId: "cs-ws-s-jen-ziplback-key" ,url: "ssh://git@github.com:tpeltone/ci-seedjob.git"
    step([
        $class: 'ExecuteDslScripts',
        targets: "jobs/*.groovy",
        ignoreMissingFiles: true,
        ignoreExisting: false,
        removedJobAction: RemovedJobAction.DELETE,
        removedViewAction: RemovedViewAction.DELETE,
        lookupStrategy: LookupStrategy.JENKINS_ROOT,
        additionalClasspath: ""
    ])
}
```

To run the seed job you need to set **Number of executors** to 1 on master:

- Jenkins -> Build Executor Status
- Configure master (the cog wheel)
- Set *#* of executors to ```1``` and save
- Now run the seed job manually to verify you changes on the Jenkins master

Every time you add or edit a script file you will get the error below.  
*ERROR: script not yet approved for use*

How to fix this:

- Goto Jenkins -> Manage Jenkins -> In-process Script Approval
- Approve the script
- Now run the seed job again and verify your changes on the Jenkins master
