package com.github.gripsack.android.utils;

/**
 * Created by tuze on 11/28/16.
 */


import java.io.File;

abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}