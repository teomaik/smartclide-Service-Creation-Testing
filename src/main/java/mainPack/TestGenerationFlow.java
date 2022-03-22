package mainPack;

import java.io.File;
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class TestGenerationFlow {

	
	public ResultObject start(String pathToProject, String pathToPomXML) throws MavenInvocationException {
		
		ResultObject mavenCmd = mavenCleanInstall(pathToProject, pathToPomXML);
		
		
		
		return new ResultObject(0, "***ola kala!!!");
	}
	
	private ResultObject mavenCleanInstall(String pathToProject, String pathToPomXML) throws MavenInvocationException {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile( new File( pathToPomXML ) );
		request.setGoals( Arrays.asList( "clean", "install" ) );

		Invoker invoker = new DefaultInvoker();
		invoker.setLocalRepositoryDirectory(new File(pathToProject));
		invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
		InvocationResult invRes = invoker.execute( request );

		if(invRes.getExitCode()!=0) {
			return new ResultObject(1, invRes.getExecutionException().getMessage());
		}
		
		return new ResultObject(0, "maven clean install completed");
	}
}