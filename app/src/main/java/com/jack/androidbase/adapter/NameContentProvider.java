package com.jack.androidbase.adapter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.jack.androidbase.tools.DBOpenHelper;

public class NameContentProvider extends ContentProvider {
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBOpenHelper dbOpenHelper;

    static {
        matcher.addURI("com.jack.androidbase.providers.myprovider", "test", 1);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(this.getContext(), "test.db", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (matcher.match(uri)) {
            case 1:
                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
                long rowId = db.insert("test", null, values);
                if (rowId > 0) {
                    //在前面已有的Uri后面追加ID
                    Uri nameUri = ContentUris.withAppendedId(uri, rowId);
                    //通知数据已经发生改变
                    getContext().getContentResolver().notifyChange(nameUri, null);
                    return nameUri;
                }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}