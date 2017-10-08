/*
 * Copyright (C) 2016 Angad Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.angads25.filepicker.model;

/* <p>
 * Created by Angad Singh on 09-07-2016.
 * </p>
 */

import java.util.Locale;

/**
 * The model/container class holding file list data.
 */
public class FileListItem implements Comparable<FileListItem> {
    private String filename,location;
    private boolean directory,marked;
    private long time;
    private int fileOrder;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public void setOrder(int fileOrder) {
        this.fileOrder = fileOrder;
    }

    @Override
    public int compareTo(FileListItem fileListItem) {
        if (fileListItem.isDirectory() && !isDirectory()) {
            /* If the comparison is between a directory and a file, return the directory. */
            return 1;
        }
        if (!fileListItem.isDirectory() && isDirectory()) {
            /* Same as above but order of occurrence is different. */
            return -1;
        }

        /* Below is compare between two files or two directories. */
        switch (this.fileOrder) {
            default:
            case DialogConfigs.FILE_ORDER_NAME_ASCENDING:
                return filename.toLowerCase().compareTo(fileListItem.getFilename().toLowerCase(Locale.getDefault()));
            case DialogConfigs.FILE_ORDER_NAME_DESCENDING:
                return -1 * filename.toLowerCase().compareTo(fileListItem.getFilename().toLowerCase(Locale.getDefault()));
            case DialogConfigs.FILE_ORDER_DATE_ASCENDING:
                return time - fileListItem.time > 0 ? 1 : -1;
            case DialogConfigs.FILE_ORDER_DATE_DESCENDING:
                return time - fileListItem.time <= 0 ? 1 : -1;
        }
    }
}