node {

    def server
    def rtMaven
    
    stage('Checkout') {
        echo 'Bajando cambios de repositorio ...'
        checkout scm
        echo currentBuild.currentResult
    }
    
    stage('Compile') {
       echo 'Compilando ...'
        
       executeMavenGoal('clean compile','pom.xml', '-Xmx1024m')
	   echo currentBuild.currentResult
		
    }
    
    stage ('Install') {
        echo 'Build ...'
        executeMavenGoal('install -Dmaven.test.skip=true','pom.xml', '-Xmx1024m')
		
    }
}

/* Ejecuta comandos de maven */
def executeMavenGoal (pMavenToolName, pJdkToolName, pMavenSettingsId, pMavenRepositoryPath, pGoalsAndOptions, pPomFilePath, pMavenOpts) {
    withMaven(
         maven: pMavenToolName,
         mavenSettingsConfig: pMavenSettingsId,
         mavenLocalRepo: pMavenRepositoryPath, 
         jdk: pJdkToolName,
         mavenOpts: pMavenOpts
         ) {
         
         // Run the maven build
         def mavenCommand = "mvn "
         
         if(null != pPomFilePath){
             mavenCommand = mavenCommand + " -f " + pPomFilePath
         }
         
         sh 'echo ' + mavenCommand + " " + pGoalsAndOptions
         try {
         	sh mavenCommand + " " + pGoalsAndOptions
         } catch (Exception err) {
            echo 'Maven clean install failed'
            currentBuild.result = 'FAILURE'
            throw err
         }
         //slackNotifier(currentBuild.currentResult)
         
    }
}

/* Ejecuta comandos de maven */
def executeMavenGoal(pGoalsAndOptions, pPomFilePath, pMavenOpts){
    def mavenToolDefault = 'Maven3'
    def javaToolDefault = 'JDK8'
    def mavenSettingsDefault = 'org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig1411853262833'
    def mavenRepositoryDefault = '.m2'
    executeMavenGoal (mavenToolDefault, javaToolDefault, mavenSettingsDefault, mavenRepositoryDefault, pGoalsAndOptions, pPomFilePath, pMavenOpts)
}

/* Notificaciones de Slack */
def slackNotifier(String buildResult) {
  if ( buildResult == "SUCCESS" ) {
    slackSend color: "good", message: "${env.JOB_NAME}:${STAGE_NAME} with buildnumber ${env.BUILD_NUMBER} was SUCCESSFUL"
  }
  else if( buildResult == "FAILURE" ) { 
    slackSend color: "danger", message: "${env.JOB_NAME}:${STAGE_NAME} with buildnumber ${env.BUILD_NUMBER} was FAILED: ${env.BUILD_URL}"
  }
  else if( buildResult == "UNSTABLE" ) { 
    slackSend color: "warning", message: "${env.JOB_NAME}:${STAGE_NAME} with buildnumber ${env.BUILD_NUMBER} was UNSTABLE"
  }
  else {
    slackSend color: "danger", message: "${env.JOB_NAME}:${STAGE_NAME} with buildnumber ${env.BUILD_NUMBER} its resulat was UNCLEAR"	
  }
}