package com.github.vincent_fuchs.custom_file_content_check_plugin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.github.vincent_fuchs.custom_file_content_check_plugin.rules_to_apply.RulesToApply;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name="checkFilesContent", defaultPhase = LifecyclePhase.VALIDATE)
public class CustomFileContentChecker extends AbstractMojo {

	@Parameter(  defaultValue = "${project.basedir}" )
	private File baseDirectory;

	@Parameter
	private List<CheckToPerform> checksToPerform= new ArrayList<>();

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		if(getLog().isInfoEnabled()){
			getLog().info("baseDirectory:" + baseDirectory);
		}

		StringBuilder rulesCheckResults=new StringBuilder();

		for(CheckToPerform checkToPerform : checksToPerform){

			getLog().info("performing this check :" + checkToPerform.getName()+"...");

			List<File> filesToCheck=checkToPerform.getFilesProvider().findFiles();

			getLog().info("\t"+filesToCheck.size()+" file(s) found...");

			RulesToApply rulesToApply=checkToPerform.getRulesToApply();

			if(!filesToCheck.isEmpty()){
				getLog().info("\tperforming checks implemented in "+rulesToApply.getClass().getName());
			}

			for(File fileToCheck : filesToCheck){
				getLog().info("\n\t\tperforming check on "+fileToCheck.getName());

				String resultForThatFile=rulesToApply.performChecksOn(fileToCheck);

				if(StringUtils.isBlank(resultForThatFile)){
					getLog().info("\t\t\tOK, file is compliant");
				}
				else{
					getLog().warn("\t\t\tSome issues with the file : "+ resultForThatFile);
					rulesCheckResults.append(resultForThatFile).append("\n");
				}
			}
		}

		if(!rulesCheckResults.toString().isEmpty()){
			throw new MojoFailureException("some custom files content check have failed - see above logs for details");
		}
		else{
			getLog().info("All files are compliant with the custom checks defined");
		}


	}



	public List<CheckToPerform> getChecksToPerform() {
		return checksToPerform;
	}

	public void setChecksToPerform(List<CheckToPerform> checksToPerform) {
		this.checksToPerform = checksToPerform;
	}

}
