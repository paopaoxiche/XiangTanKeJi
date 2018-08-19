package com.xtkj.paopaoxiche.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

public class UriUtils {

    /**
     * @return 失败返回null，成功返回图片真实路径
     * @function 获取图片真实路径
     * @Author Money
     */
    @SuppressLint("NewApi")
    public static String getImagePath(Context context, Uri uri) {
        String path = null;

        if (uri == null) {
            return null;
        }

        boolean isKitkat = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);

        if (isKitkat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                path = getPathFromExternalStorageDocument(uri);
            } else if (isDownloadDocument(uri)) {
                path = getPathForDownloadDocument(context, uri);
            } else if (isMediaDocument(uri)) {
                path = getPathFromMediaDocument(context, uri);
            }
        } else {
            String scheme = uri.getScheme();

            if (scheme.equalsIgnoreCase("content")) {
                if (isGooglePhotosUri(uri)) {
                    path = uri.getLastPathSegment();
                } else {
                    String[] projections = {MediaStore.Images.Media.DATA};
                    path = getPathContentResolver(context, uri, projections, null, null, MediaStore.Images.Media.DATA);
                }
            } else if (scheme.equalsIgnoreCase("file")) {
                path = uri.getPath();
            }
        }

        return path;
    }

    /**
     * @return true 是，false 不是
     * @function Uri是存储类型
     * @Author Money
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return uri.getAuthority().equals("com.android.externalstorage.documents");
    }

    /**
     * @return true 是，false 不是
     * @function Uri是下载类型
     * @Author Money
     */
    private static boolean isDownloadDocument(Uri uri) {
        return uri.getAuthority().equals("com.android.providers.downloads.documents");
    }

    /**
     * @return true 是，false 不是
     * @function Uri是媒体类型
     * @Author Money
     */
    private static boolean isMediaDocument(Uri uri) {
        return uri.getAuthority().equals("com.android.providers.media.documents");
    }

    /**
     * @return 失败返回null，成功返回图片的绝对路径
     * @function 从存储类型的Uri获取真实的图片绝对路径
     * @Author Money
     */
    @SuppressLint("NewApi")
    private static String getPathFromExternalStorageDocument(Uri uri) {
        String path = null;

        String documentId = DocumentsContract.getDocumentId(uri);
        String[] split = documentId.split(":");
        String type = split[0];

        if ("primary".equalsIgnoreCase(type)) {
            path = Environment.getExternalStorageDirectory() + "/" + split[1];
        }

        return path;
    }

    /**
     * @return 失败返回null，成功返回图片的绝对路径
     * @function 从下载类型的Uri获取真实的图片绝对路径
     * @Author Money
     */
    @SuppressLint("NewApi")
    private static String getPathForDownloadDocument(Context context, Uri uri) {
        String path;

        String documentId = DocumentsContract.getDocumentId(uri);
        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                Long.valueOf(documentId));

        String[] projection = {MediaStore.Images.Media.DATA};

        path = getPathContentResolver(context, contentUri, projection, null, null, MediaStore.Images.Media.DATA);

        return path;
    }

    /**
     * @return 失败返回null，成功返回图片的绝对路径
     * @function 从媒体类型的Uri获取真实的图片绝对路径
     * @Author Money
     */
    @SuppressLint("NewApi")
    private static String getPathFromMediaDocument(Context context, Uri uri) {
        String path;

        String documentId = DocumentsContract.getDocumentId(uri);
        String[] split = documentId.split(":");
        String type = split[0];

        Uri contentUri = null;

        String projection = null;
        String selection = null;

        if (type.equals("image")) {
            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            projection = MediaStore.Images.Media.DATA;
            selection = MediaStore.Images.Media._ID + "=?";
        } else if (type.equals("video")) {
            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            projection = MediaStore.Video.Media.DATA;
            selection = MediaStore.Video.Media._ID + "=?";
        } else if (type.equals("audio")) {
            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            projection = MediaStore.Audio.Media.DATA;
            selection = MediaStore.Audio.Media._ID + "=?";
        }

        String[] projections = {projection};
        String[] selectionArgs = {split[1]};

        path = getPathContentResolver(context, contentUri, projections, selection, selectionArgs, projection);

        return path;
    }

    /**
     * @return 失败返回null，成功返回图片的绝对路径
     * @function 从内容提供者的Uri获取真实的图片绝对路径
     * @Author Money
     */
    private static String getPathContentResolver(Context context, Uri uri, String[] projections, String selection,
                                                 String[] selectionArgs, String projection) {

        String path = null;

        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, projections, selection, selectionArgs, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int index = cursor.getColumnIndexOrThrow(projection);
                path = cursor.getString(index);

                if (TextUtils.isEmpty(path)) {
                    continue;
                }

                cursor.close();
                break;
            } while (cursor.moveToNext());
        }

        return path;
    }

    /**
     * @return true 是，false 不是
     * @function Uri是谷歌相册类型
     * @Author Money
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return uri.getAuthority().equals("com.google.android.apps.photos.content");
    }
}
