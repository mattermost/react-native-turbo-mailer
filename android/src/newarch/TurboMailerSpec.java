package com.turbomailer;

import com.facebook.react.bridge.ReactApplicationContext;

abstract class TurboMailerSpec extends NativeTurboMailerSpec {
  TurboMailerSpec(ReactApplicationContext context) {
    super(context);
  }

  public abstract void sendMail(ReadableMap options, Promise promise);

}
