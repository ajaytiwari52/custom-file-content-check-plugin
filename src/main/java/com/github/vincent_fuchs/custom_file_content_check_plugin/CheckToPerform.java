package com.github.vincent_fuchs.custom_file_content_check_plugin;

public class CheckToPerform {

    private String name;

    private String filesProvider;

    private String rulesToApply;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilesProvider() {
        return filesProvider;
    }

    public void setFilesProvider(String filesProvider) {
        this.filesProvider = filesProvider;
    }

    public String getRulesToApply() {
        return rulesToApply;
    }

    public void setRulesToApply(String rulesToApply) {
        this.rulesToApply = rulesToApply;
    }
}
