package com.github.vincent_fuchs.custom_file_content_check_plugin;

import com.github.vincent_fuchs.custom_file_content_check_plugin.files_provider.FilesProvider;
import com.github.vincent_fuchs.custom_file_content_check_plugin.rules_to_apply.RulesToApply;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomFileContentCheckerTest {

    @InjectMocks
    CustomFileContentChecker customFileContentChecker=new CustomFileContentChecker();

    @Mock
    FilesProvider filesProvider;

    @Mock
    RulesToApply rulesToApply;

    @Mock
    private File file1;

    @Mock
    private File file2;

    @Before
    public void configureCustomFileContentChecker(){

        CheckToPerform checkToPerform=new CheckToPerform("someDummyCheckForUnitTesting",filesProvider,rulesToApply);

        customFileContentChecker.setChecksToPerform(Arrays.asList(checkToPerform));

        List<File> foundFiles= Arrays.asList(file1,file2);

        when(filesProvider.findFiles()).thenReturn(foundFiles);
    }

    @Test
    public void shouldAnalyzeTheFilesThatAreFound() throws MojoFailureException, MojoExecutionException {



        customFileContentChecker.execute();

        verify(rulesToApply).performChecksOn(file1);
        verify(rulesToApply).performChecksOn(file2);
    }

    @Test(expected = MojoFailureException.class)
    public void shouldFailWhenOneFileIsNotCompliant() throws MojoFailureException, MojoExecutionException {

        when(rulesToApply.performChecksOn(file1)).thenReturn("Issue with file1");

        customFileContentChecker.execute();

    }

    @Test
    public void shouldAggregateErrorMessagesWhenMultipleFailures() throws MojoFailureException, MojoExecutionException {

        when(rulesToApply.performChecksOn(file1)).thenReturn("Issue with file1");
        when(rulesToApply.performChecksOn(file2)).thenReturn("Issue with file2");

        try {
            customFileContentChecker.execute();
            fail("An exception should have been thrown !");
        } catch (MojoFailureException e) {

            assertThat(e).hasMessageContaining("file1");
            assertThat(e).hasMessageContaining("file2");
        }
    }


}
