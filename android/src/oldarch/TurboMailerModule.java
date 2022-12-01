package com.turbomailer;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Promise;

public class TurboMailerModule extends ReactContextBaseJavaModule {
  public static final String NAME = TurboMailerModuleImpl.NAME;
  private final ReactApplicationContext reactContext;

  TurboMailerModule(ReactApplicationContext context) {
    super(context);
    this.reactContext = context;
  }

  @Override
  @NonNull
  public String getName() {
    return TurboMailerModuleImpl.NAME;
  }

  @ReactMethod
  public void sendMail(ReadableMap options, Promise promise) {
    TurboMailerModuleImpl.sendMail(this.reactContext, options, promise);
  }
}
