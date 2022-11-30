# @mattermost/react-native-turbo-mailer

An adaptation of [react-native-mail](https://github.com/chirag04/react-native-mail/blob/master/android/src/main/java/com/chirag/RNMail/RNMailModule.java) that supports Turbo Module

## Installation

```sh
npm install @mattermost/react-native-turbo-mailer
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