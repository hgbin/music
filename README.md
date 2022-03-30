# Music

#### 介绍
利用vue+element+servlet+axios搭建的关于模仿网易云pc端的前后端项目。

# 项目启动前准备

## 1.本项目启动前需要做以下修改

（1）打开Music项目，修改‘Music/util/Getconn’路径下的数据库用户名以及密码。
如'jdbc:mysql://localhost:3306/music?user=你的数据库用户名&password=你的数据库密码'

（2）打开Music项目，修改‘Music/util/Getinterface’路径下的端口号。
如'return "http://localhost:你的端口号/Music/assets/image/";'

（3）打开网易云项目，修改'Vue.config.js'里面办理的端口号
如'target:'http://localhost:你的tomcat服务器端口号/Music','

（4）打开网易云项目，修改'网易云/src/assets/js/Myapi.js'路径下的tomcat端口号。
export const returnpost = function(){//返回tomcat服务器的端口号（为了--更新图像
    return 'http://localhost:你的tomcat服务器端口号/Music/assets/image/'
}

注: Music项目指的是后端项目, 网易云项目指的是Vue前端项目。

## 2.导入数据库

（1）启动navicat，创建music数据库后，运行sql存储文件。

## 3.项目运行

（1）打开网易云项目，进入终端模式，输入命令：npm install

（2）下载完成后，继续终端模式，输入命令：npm run serve

## 4.项目jdk

本项目的后端是利用eclipse搭建的项目，jdk版本为15。

## 5.项目运行结果

![输入图片说明](%E5%9B%BE%E7%89%87.png)


## 6.项目总结

本项目为实训项目，效果一般，可以供参考。
