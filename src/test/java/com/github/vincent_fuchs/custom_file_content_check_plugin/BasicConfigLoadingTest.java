package com.github.vincent_fuchs.custom_file_content_check_plugin;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


public class BasicConfigLoadingTest extends AbstractMojoTestCase
{

    protected void setUp() throws Exception
    {
        // required
        super.setUp();

    }

    protected void tearDown() throws Exception
    {
        // required
        super.tearDown();

    }

    public void testSomething() throws Exception {
        File pomFile = getTestFile( "src/test/resources/basicConfig.xml" );
        assertThat(pomFile).exists();

        CustomFileContentChecker myCustomFileContentChecker = (CustomFileContentChecker) lookupMojo( "checkFilesContent", pomFile );
        assertNotNull( myCustomFileContentChecker );
        myCustomFileContentChecker.execute();

        assertThat(myCustomFileContentChecker.getChecksToPerform()).isNotEmpty();

        CheckToPerform myCheck=myCustomFileContentChecker.getChecksToPerform().get(0);

        assertThat(myCheck.getFilesProvider()).isNotNull();
        assertThat(myCheck.getRulesToApply()).isNotNull();
    }
}
