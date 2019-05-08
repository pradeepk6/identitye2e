package com.mycompany.service;

import com.mycompany.domain.FileInfo;

import java.io.File;
import java.util.List;

public interface FileService {
    List<FileInfo> getFilesInfo();
    List<File> getFiles(String... fileTypeFilterArray);
}
