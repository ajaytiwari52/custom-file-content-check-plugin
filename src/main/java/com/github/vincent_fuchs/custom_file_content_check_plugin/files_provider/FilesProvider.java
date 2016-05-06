package com.github.vincent_fuchs.custom_file_content_check_plugin.files_provider;

import java.io.File;
import java.util.List;

public interface FilesProvider {

    List<File> findFiles();

}
