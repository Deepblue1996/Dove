![Image text](https://raw.githubusercontent.com/Deepblue1996/Dove/master/%E9%B8%BD%E5%AD%90.png)

<a href="http://developer.android.com/index.html"><img src="https://img.shields.io/badge/platform-android-green.svg"></a>
[![](https://jitpack.io/v/Deepblue1996/Dove.svg)](https://jitpack.io/#Deepblue1996/Dove)
<a href="https://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/license-apache-green.svg"></a>

Dove 是一个基于Retrofit2和RxJava2，进行二次开发封装的便捷网络层框架。

[[English Document]](https://github.com/Deepblue1996/Dove/blob/master/README.md)

## 我们怎么使用 Dove

步骤一:

创建或打开你的 Application 类文件, 

在 onCreate () 里面添加:

<pre><code>Dove.birth(Core.instance().getApplicationContext(),
                Nest.build().setBaseUrl(ComDef.BASE_COM_URL).setInterfaceClass(JobTask.class));
</code></pre>

为什么要建造一个Nest, 在这里, Nest是一个配置类.

使用它 build().setBaseUrl().setInterfaceClass() 到

Dove.birh(?), 它将返回你设置的接口对象;

示例:

<pre><code>JobTask jobTask = Dove.birth(Core.instance().getApplicationContext(),
                Nest.build()
		    .setBaseUrl(ComDef.BASE_COM_URL)
		    .setInterfaceClass(JobTask.class)
		    .addGlobalParam((String)key, (String)param) / 
		    .addGlobalParams(HashMap<String, String> maps));
</code></pre>

步骤二:

在你想获取网络数据的地方，添加一下代码:

<pre><code>Dove.fly( jobTask.? (Your Interface Service)
	, new Dover<?>() {
                    @Override
                    public void don(Disposable d, @NonNull ? thing) {
                        // 接收到数据.
                    }

                    @Override
                    public void die(Disposable d, @NonNull Throwable throwable) {
                        // 出错或者中断.
                    }
                });
</code></pre>

## 基础配置

在你的构建中得到一个Git项目:

步骤 1. 添加 JitPack repository 到你的编译文件

gradle
maven
sbt
leiningen
添加它到你的根目录 build.gradle 文件, repositories 语法最后的地方:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}Copy
步骤 2. 添加 dependency

	dependencies {
	        compile 'com.github.Deepblue1996:Dove:1.3.5'
	}
步骤 3. 构建
	
## 协议

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
