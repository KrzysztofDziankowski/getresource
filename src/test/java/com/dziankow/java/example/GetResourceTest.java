package com.dziankow.java.example;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static java.util.Objects.nonNull;

public class GetResourceTest {

    private void printResource(String name) throws IOException {
        System.out.println();
        for (String prefix : Arrays.asList("./")) {
            String resourceName = prefix + name;
            System.out.println("--- " + resourceName);
            printFileContentOrNull(this.getClass().getResource(resourceName));
            printFileContentOrNull(GetResourceTest.class.getResource(resourceName));
            printFileContentOrNull(ClassLoader.class.getResource(resourceName));
            printFileContentOrNull(ClassLoader.getSystemClassLoader().getResource(resourceName));
            printFileContentOrNull(Thread.currentThread().getContextClassLoader().getResource(resourceName));
        }
    }

    private void printFileContentOrNull(URL resource) throws IOException {
        System.out.println((nonNull(resource)) ? IOUtils.toString(resource, "UTF-8") : null);
    }

    @Test
    public void getResourceTest() throws IOException {
        for (String resourceName : Arrays.asList(
                "fileOnlyInTest.txt",
                "fileOnlyInMain.txt",
                "fileInBothMainAndTest.txt",
                "dirInBothMainAndTest/fileOnlyInTest.txt",
                "dirInBothMainAndTest/fileInBothMainAndTest.txt",
                "dirInBothMainAndTest/fileOnlyInMain.txt",
                "dirOnlyInTest/fileOnlyInTest.txt",
                "dirOnlyInMain/fileOnlyInMain.txt"
        )) {
            printResource(resourceName);
        }
    }


    @Test
    public void resourcesListTest() throws IOException {
        for (String resourceName : Arrays.asList(
                "/",
                ".",
                "dirInBothMainAndTest",
                "dirOnlyInTest",
                "dirOnlyInMain"
        )) {
            printResources(resourceName);
        }
    }

    private void printResources(String resourceName) throws IOException {
        System.out.println();
        System.out.println("--- " + resourceName);
        printFiles(this.getClass().getResource(resourceName));
        printFiles(GetResourceTest.class.getResource(resourceName));
        printFiles(ClassLoader.class.getResource(resourceName));
        printFiles(ClassLoader.getSystemClassLoader().getResource(resourceName));
        printFiles(Thread.currentThread().getContextClassLoader().getResource(resourceName));
    }

    private void printFiles(URL resource) {
        if (nonNull(resource)) {
            String path = resource.getPath();
            System.out.println(Arrays.asList(new File(path).listFiles()));
        } else {
            System.out.println("null");
        }
    }
}
