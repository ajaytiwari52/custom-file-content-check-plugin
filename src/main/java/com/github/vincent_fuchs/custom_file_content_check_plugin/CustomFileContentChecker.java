package com.github.vincent_fuchs.custom_file_content_check_plugin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name="checkFilesContent", defaultPhase = LifecyclePhase.VALIDATE)
public class CustomFileContentChecker extends AbstractMojo {

	@Parameter(  defaultValue = "${project.basedir}" )
	private File baseDirectory;

	/**
	 * @parameter
	 */
	private List<CheckToPerform> checksToPerform= new ArrayList<>();

	public void execute() throws MojoExecutionException {
		
		if(getLog().isInfoEnabled()){
			getLog().info("baseDirectory:" + baseDirectory);
		}

		for(CheckToPerform checkToPerform : checksToPerform){

			if(getLog().isInfoEnabled()){
				getLog().info("performing this check :" + checkToPerform.getName()+"...");
			}

			//List<File> filesToCheck=checkToPerform.getFilesProvider().findFiles();




		}


	}


}
