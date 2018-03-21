![Image text](https://raw.githubusercontent.com/Deepblue1996/Dove/master/%E9%B8%BD%E5%AD%90.png)

Dove is a convenient network layer framework based on Retrofit2 and RxJava2 for two development and encapsulation.

[[中文文档]](https://github.com/Deepblue1996/Dove/README_CN.md)

## How do I use Dove

One:

Create or open your Application Class, 

In onCreate ():

<pre><code>Dove.birth(Nest.build()
		.setContext(Core.getInstance().getApplicationContext())
                .setUrl(ComDef.COM_DEF)
                .setInterfaceClass(JobTask.class));
</code></pre>

Why is it called the bird's nest, because it is a basic project.

Use it build().setContext().setBaseUrl().setInterfaceClass() for

Dove.birh(?), It will give you the interface;

example:

<pre><code>JobTask jobTask = Dove.birth(Core.instance().getApplicationContext(),
                Nest.build().setBaseUrl(ComDef.BASE_COM_URL).setInterfaceClass(JobTask.class));
</code></pre>

Two:

Where you need to use the network:

<pre><code>Dove.fly( jobTask.? (Your Interface Service)
	, new Dover<?>() {
                    @Override
                    public void call(@NonNull Disposable d) {
                        // In the beginning, there will be a notice.
                    }

                    @Override
                    public void don(Disposable d, @NonNull ? thing) {
                        // The next step to receive data
                    }

                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        // Abnormality
                    }

                    @Override
                    public void end(Disposable d) {
                        // Already delivered
                    }
                });
</code></pre>

## Basic deployment

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
	        compile 'com.github.Deepblue1996:Dove:1.1'
	}
Step 3. Gradle

## LICENSE

<pre><code>Copyright 2018 Deepblue

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</code></pre>
