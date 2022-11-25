import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

type MailOptions = {
  subject?: string;
  recipients?: string[];
  ccRecipients?: string[];
  bccRecipients?: string[];
  body?: string;
  customChooserTitle?: string;
  attachments?: Array<{
    path?: string; // Specify either 'path' or 'uri'
    uri?: string;
    type?: string; // Specify either 'type' or 'mimeType'
    mimeType?: string;
    name?: string;
  }>;
};

export interface Spec extends TurboModule {
  sendMail(options: MailOptions): Promise<string | undefined>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('TurboMailer');

