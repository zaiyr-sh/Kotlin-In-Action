package ch_6;

import java.io.File;
import java.util.List;

interface FileContentProcessor {
    void processContents(File path, byte[] binaryContents, List<String> textContents);
}

// A Kotlin implementation of this interface needs to make the following choices:
// The list will be nullable, because some files are binary and their contents can’t be represented as text.
// The elements in the list will be non-null, because lines in a file are never null.
// The list will be read-only, because it represents the contents of a file, and those contents aren’t going to be modified.
