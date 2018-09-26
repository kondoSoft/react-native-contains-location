Pod::Spec.new do |s|
  s.name         = "RNContainsLocation"
  s.version      = "1.0.0"
  s.summary      = "RNContainsLocation"
  s.description  = <<-DESC
                  RNContainsLocation
                   DESC
  s.homepage     = "https://github.com/kondosoft"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNContainsLocation.git", :tag => "master" }
  s.source_files  = "ios/*.{h,m}"
  s.requires_arc = true
  # s.compiler_flags = '-fno-modules'

  s.dependency "React"
  s.dependency 'GoogleMaps', '2.5.0'
end
