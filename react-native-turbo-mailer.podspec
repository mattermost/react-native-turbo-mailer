require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "mattermost-react-native-turbo-mailer"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => "11.0" }
  s.source       = { :git => "https://github.com/mattermost/react-native-turbo-mailer.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,mm}"

  # React Native Core dependency
  install_modules_dependencies(s)
end
