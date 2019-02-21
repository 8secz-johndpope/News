进入工程根目录，运行gradle check，检测完成后，会在build/reports下生成相应的检测报告文件。当然也可以按照每个插件的使用方式单独使用。

binary包上传
//添加
publish {
    userOrg = 'heavenisme'//bintray.com用户名
    groupId = 'com.heaven.data'//jcenter上的路径
    artifactId = 'datasource'//项目名称
    publishVersion = '1.0.2'//版本号
    desc = 'android data source aar'//描述，不重要
    website = 'https://github.com/heavenisme/Jcenter.git'//网站，不重要；尽量模拟github上的地址，例如我这样的；当然你有地址最好了
}

publish {
    userOrg = 'heavenisme'//bintray.com用户名
    groupId = 'com.heaven.mode'//jcenter上的路径
    artifactId = 'datamode'//项目名称
    publishVersion = '1.0.0'//版本号
    desc = 'android data mode aar'//描述，不重要
    website = 'https://github.com/heavenisme/Jcenter.git'//网站，不重要；尽量模拟github上的地址，例如我这样的；当然你有地址最好了
}
1.chmod a+x gradlew
2.sudo ./gradlew clean build bintrayUpload -PbintrayUser=heavenisme -PbintrayKey=7cc9544665f4481cd5dc9290148f12f638f2963a -PdryRun=false

问题解决
com.novoda.gradle.release.AndroidLibrary$LibraryUsage.getDependencyConstraints()Ljava/util/Set;
解决方案：
1.修改gradle/wrapper/ gradle-wrapper.properties 版本号  我先前是4.6.改为4.4.  distributionUrl=https://services.gradle.org/distributions/gradle-4.4-all.

2.升级bintray 插件版本 。先前是0.5  改为0.8    classpath 'com.novoda:bintray-release:0.8.0'
3.降级gradle 版本。   显示是3.2.1 改为 3.0.3  classpath 'com.android.tools.build:gradle:3.0.1'
