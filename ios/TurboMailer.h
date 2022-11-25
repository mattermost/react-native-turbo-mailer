
#ifdef RCT_NEW_ARCH_ENABLED
#import <MessageUI/MessageUI.h>
#import "RNTurboMailerSpec.h"
@interface TurboMailer : NSObject <NativeTurboMailerSpec, MFMailComposeViewControllerDelegate>
#else
#import <MessageUI/MessageUI.h>
#import <React/RCTBridgeModule.h>
@interface TurboMailer : NSObject <MFMailComposeViewControllerDelegate, RCTBridgeModule>
#endif

@end
