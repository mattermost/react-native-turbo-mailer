# @mattermost/react-native-turbo-mailer

An adaptation of [react-native-mail](https://github.com/chirag04/react-native-mail/blob/master/android/src/main/java/com/chirag/RNMail/RNMailModule.java) that supports Turbo Module

## Installation

```sh
npm install @mattermost/react-native-turbo-mailer
```


##### Android specific

Create a file under `android/src/main/res/xml` called `provider_paths.xml` and add the following content
```xml
<?xml version='1.0' encoding='utf-8'?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <files-path name="files" path="." />
    <external-files-path name="external_files" path="." />
    <external-path name="external" path="." />
    <cache-path name="cache" path="." />
    <root-path name="root" path="." />
</paths>
```

## Usage

```js
import TurboMailer from '@mattermost/react-native-turbo-mailer';

// ...
 await TurboMailer.sendMail({
    subject: "subject here",
    recipients: ["support@gmail.com"],
    body: "mail body here",
    attachments: [{
        path: '',
        mimeType: ''
    }],
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---