![Image text](https://raw.githubusercontent.com/Deepblue1996/Dove/master/%E9%B8%BD%E5%AD%90.png)

## How to

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}Copy
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Deepblue1996:Dove:1.0'
	}
