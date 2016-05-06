package com.github.vincent_fuchs.custom_file_content_check_plugin;

import com.github.vincent_fuchs.custom_file_content_check_plugin.files_provider.FilesProvider;
import com.github.vincent_fuchs.custom_file_content_check_plugin.rules_to_apply.RulesToApply;

public class CheckToPerform {

    private String name;

    private FilesProvider filesProvider;

    private RulesToApply rulesToApply;

    /*
        default constructor used at runtime by Maven plugin
     */
    public CheckToPerform(){

    }

    public CheckToPerform(String name, FilesProvider filesProvider, RulesToApply rulesToApply) {
        this.name=name;
        this.filesProvider=filesProvider;
        this.rulesToApply=rulesToApply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FilesProvider getFilesProvider() {
        return filesProvider;
    }

    public void setFilesProvider(FilesProvider filesProvider) {
        this.filesProvider = filesProvider;
    }

    public RulesToApply getRulesToApply() {
        return rulesToApply;
    }

    public void setRulesToApply(RulesToApply rulesToApply) {
        this.rulesToApply = rulesToApply;
    }
}
