import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package '@matermost/react-native-turbo-mailer' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const TurboMailerModule = isTurboModuleEnabled
  ? require('./NativeTurboMailer').default
  : NativeModules.TurboMailer;

const TurboMailer = TurboMailerModule
  ? TurboMailerModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export default TurboMailer;
