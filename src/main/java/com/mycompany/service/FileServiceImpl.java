package com.mycompany.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mycompany.domain.FileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private String searchDirPath;
    private String fileTypeFilter;
    private File searchDir;
    private String[] configuredFileTypes;

    @Inject
    public void FileService(@Named("searchDirPath") String searchDirPath, @Named("fileTypeFilter") String fileTypeFilter) {
        this.searchDirPath = searchDirPath;
        this.fileTypeFilter = fileTypeFilter;
        searchDir = new File(searchDirPath);
        configuredFileTypes = fileTypeFilter.split(",");
    }

    /**
     * gets info of all the files in the configured directory
     * @return
     */

    public List<FileInfo> getFilesInfo() {
        List<File> files = (List<File>) FileUtils.listFiles(searchDir, configuredFileTypes, false);
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (File file : files) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(file.getName());
            fileInfo.setFileExtension(FilenameUtils.getExtension(file.getName()));
            try {
                fileInfo.setMimeType(Files.probeContentType(file.toPath()));
            } catch (IOException ioe) {
                logger.error("Unable to get mimetype of file : " + file.getName(), ioe);
            }
            fileInfo.setFileSize(file.length());
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }

    /**
     * gets
     * @param fileTypeFilterArray
     * @return
     */
    public List<File> getFiles(String... fileTypeFilterArray) {
        if(!Arrays.asList(configuredFileTypes).containsAll(Arrays.asList(fileTypeFilterArray)))
            throw new IllegalArgumentException("One or more of these file extensions not supported : " + Arrays.toString(fileTypeFilterArray));
        if(fileTypeFilterArray.length==0) fileTypeFilterArray = configuredFileTypes;

        return (List<File>)FileUtils.listFiles(searchDir, fileTypeFilterArray, false);
    }
}
