package com.turbomailer;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Promise;

abstract class TurboMailerSpec extends ReactContextBaseJavaModule {
  TurboMailerSpec(ReactApplicationContext context) {
    super(context);
  }

  public abstract void sendMail(ReadableMap options, Promise promise);
}
