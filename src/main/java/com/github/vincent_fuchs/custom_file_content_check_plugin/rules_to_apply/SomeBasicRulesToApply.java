package com.github.vincent_fuchs.custom_file_content_check_plugin.rules_to_apply;

import java.io.File;

public class SomeBasicRulesToApply implements RulesToApply {
    @Override
    public String performChecksOn(File fileToCheck) {
        return null;
    }
}
