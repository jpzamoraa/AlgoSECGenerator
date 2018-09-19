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
        
       executeMavenGoal('clean compile', 
				 'pom.xml', '-Xmx1024m')
		echo currentBuild.currentResult
		slackNotifier(currentBuild.currentResult)
		
    }
    stage('Cobertura') {
        echo 'Cobertura Jacoco...'
        try {
		     executeMavenGoal('clean org.jacoco:jacoco-maven-plugin:prepare-agent install -DargLine=-Xmx4096m -Dcobertura.report.format=xml -DforkCount=16 -DreuseForks=true  -Dmaven.test.failure.ignore=false', 
		                    'pom.xml', '-Xmx1024m') 
            //jacoco execPattern: '**/target/**.exec'
            jacoco( 
			      execPattern: 'target/**.exec',
			      classPattern: 'target/classes',
			      sourcePattern: 'src/main/java',
			      exclusionPattern: 'src/test*'
			)
		}
		catch(e){
		    throw e
		}
		finally{
		    junit 'target/surefire-reports/*.xml'
		}
    }
    stage('Sonar') {
        echo 'Sonar ...'
        withSonarQubeEnv('Development') {
		    executeMavenGoal('sonar:sonar', 'pom.xml', '-Xmx1024m') 
	        }

    }
    stage ('Install') {
        echo 'Build ...'
        //sh 'mvn install -Dmaven.test.skip=true'
         executeMavenGoal('install -Dmaven.test.skip=true', 
				 'pom.xml', '-Xmx1024m')
		//slackNotifier(currentBuild.currentResult)
    }
}
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
            slackNotifier(currentBuild.currentResult)
         }
         //slackNotifier(currentBuild.currentResult)
         
    }
}

def executeMavenGoal(pGoalsAndOptions, pPomFilePath, pMavenOpts){
    def mavenToolDefault = 'Maven_Local'
    def javaToolDefault = 'IBM SDK 8.0'
    def mavenSettingsDefault = 'org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig1411853262833'
    def mavenRepositoryDefault = '.m2'
    executeMavenGoal (mavenToolDefault, javaToolDefault, mavenSettingsDefault, mavenRepositoryDefault, pGoalsAndOptions, pPomFilePath, pMavenOpts)
}

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