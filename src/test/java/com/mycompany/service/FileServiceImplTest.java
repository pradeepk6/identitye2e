package com.mycompany.service;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.name.Names;
import com.mycompany.domain.FileInfo;
import org.apache.commons.io.FilenameUtils;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileServiceImplTest {

    static FileServiceImpl fileServiceImpl;

    @BeforeClass
    public static void setUp() {
        fileServiceImpl = Guice.createInjector(new FileServiceModule()).getInstance(FileServiceImpl.class);
    }

    @Test
    public void fileExtensionsShouldMatchFileFilter() {
        List<File> files = fileServiceImpl.getFiles("csv");
        for (File file : files) {
            assertTrue(FilenameUtils.getExtension(file.getName()).equals("csv"));
        }
    }

    @Test
    public void numFilesRetrieved_ShouldMatch() {
        List<File> files = fileServiceImpl.getFiles("csv","xls");
        assertEquals(files.size(),10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrow_IllgegalArgumentException_ForNonConfiguredFileType() {
        List<File> files = fileServiceImpl.getFiles("abc");
    }

    @Test
    public void retrieved_FileInfo_ShouldMatch() {
        List<FileInfo> filesInfoList = fileServiceImpl.getFilesInfo();
        FileInfo fileInfo = filesInfoList.stream().filter(f -> f.getFileExtension().equals("xls")).findFirst().get();
        assertEquals("cars.xls",fileInfo.getName());
        assertEquals("xls",fileInfo.getFileExtension());
        assertEquals("application/vnd.ms-excel",fileInfo.getMimeType());
        assertTrue(fileInfo.getFileSize()>0);
    }
}
