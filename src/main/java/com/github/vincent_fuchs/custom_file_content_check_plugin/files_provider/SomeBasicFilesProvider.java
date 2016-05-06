package com.github.vincent_fuchs.custom_file_content_check_plugin.files_provider;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class SomeBasicFilesProvider implements FilesProvider{


    @Override
    public List<File> findFiles() {

        System.out.println("dummy impl returning empty list");

        return Collections.emptyList();
    }
}
