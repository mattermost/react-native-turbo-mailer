package com.turbomailer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TurboMailerModuleImpl {
  public static final String NAME = "TurboMailer";

  /**
   * Converts a ReadableArray to a String array
   *
   * @param r the ReadableArray instance to convert
   *
   * @return array of strings
   */
  private static String[] readableArrayToStringArray(ReadableArray r) {
    int length = r.size();
    String[] strArray = new String[length];

    for (int keyIndex = 0; keyIndex < length; keyIndex++) {
      strArray[keyIndex] = r.getString(keyIndex);
    }

    return strArray;
  }

  public static void sendMail(ReactApplicationContext reactContext, ReadableMap options, Promise promise) {
    Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
    i.setData(Uri.parse("mailto:"));
    i.setType("message/rfc822");

    if (options.hasKey("subject") && !options.isNull("subject")) {
      i.putExtra(Intent.EXTRA_SUBJECT, options.getString("subject"));
    }

    if (options.hasKey("body") && !options.isNull("body")) {
      String body = options.getString("body");
      i.putExtra(Intent.EXTRA_TEXT, body);
    }

    if (options.hasKey("recipients") && !options.isNull("recipients")) {
      ReadableArray recipients = options.getArray("recipients");
      i.putExtra(Intent.EXTRA_EMAIL, readableArrayToStringArray(recipients));
    }

    if (options.hasKey("ccRecipients") && !options.isNull("ccRecipients")) {
      ReadableArray ccRecipients = options.getArray("ccRecipients");
      i.putExtra(Intent.EXTRA_CC, readableArrayToStringArray(ccRecipients));
    }

    if (options.hasKey("bccRecipients") && !options.isNull("bccRecipients")) {
      ReadableArray bccRecipients = options.getArray("bccRecipients");
      i.putExtra(Intent.EXTRA_BCC, readableArrayToStringArray(bccRecipients));
    }

    if (options.hasKey("attachments") && !options.isNull("attachments")) {
      ReadableArray r = options.getArray("attachments");
      int length = r.size();

      List<ResolveInfo> resolvedIntentActivities = reactContext.getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);

      ArrayList < Uri > uris = new ArrayList< Uri >();
      for (int keyIndex = 0; keyIndex < length; keyIndex++) {
        ReadableMap clip = r.getMap(keyIndex);
        Uri uri;
        try {
          if (clip.hasKey("path") && !clip.isNull("path")) {
            String path = clip.getString("path");
            File file = new File(path);
            uri = FileProvider.getUriForFile(reactContext, "com.turbomailer.rnfileprovider", file);
          } else {
            promise.reject("file path not supplied");
            return;
          }
        } catch (Exception e) {
          promise.reject(e);
          return;
        }

        uris.add(uri);

        for (ResolveInfo resolvedIntentInfo: resolvedIntentActivities) {
          String packageName = resolvedIntentInfo.activityInfo.packageName;
          reactContext.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
      }

      i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
    }

    PackageManager manager = reactContext.getPackageManager();

    List < ResolveInfo > list = manager.queryIntentActivities(i, 0); // returns a list of all activities that can handle the Intent

    if (list == null || list.size() == 0) {
      promise.reject("No email app found!");
      return;
    }

    if (list.size() == 1) {
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      try {
        reactContext.startActivity(i);
      } catch (Exception ex) {
        promise.reject("error", ex);

      }
    } else {
      String chooserTitle = "Send Mail";

      if (options.hasKey("customChooserTitle") && !options.isNull("customChooserTitle")) {
        chooserTitle = options.getString("customChooserTitle");
      }

      Intent chooser = Intent.createChooser(i, chooserTitle);
      chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      try {
        reactContext.startActivity(chooser);
        promise.resolve(null);
      } catch (Exception ex) {
        promise.reject("error", ex);
      }
    }
  }
}
