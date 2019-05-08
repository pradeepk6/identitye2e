package com.mycompany.service;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class FileServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("searchDirPath")).toInstance("src/test/resources/searchDir");
        bind(String.class).annotatedWith(Names.named("fileTypeFilter")).toInstance("csv,xls");
    }
}
