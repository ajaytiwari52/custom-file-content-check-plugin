package com.github.vincent_fuchs.custom_file_content_check_plugin.rules_to_apply;

import java.io.File;

public interface RulesToApply {

    /**
     * Will open the file and parse the content, looking for specific violations (that you would need to implement in the class)
     * @param fileToCheck
     * @return a null or empty String if no violation has been found. A String detailing the found issue(s).
     * It is recommended to be as precise as possible (line number, explanations) so that it's easy to fix for the developer.
     */
    String performChecksOn(File fileToCheck);
}
