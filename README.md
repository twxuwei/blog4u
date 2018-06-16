# SpringBoot+Vue前后端分离博客系统介绍
- - - 
		本文为大家带来使用SpringBoot+Vue+Docker的前后端分离博客系统，后期会持续优化，并使用Docker Compose改造，作者的渣渣前端
	希望大家海涵，项目演示地址为：https://www.bobblog.top
## 技术栈介绍
+ 后端

	SpringBoot作为基础

	数据库存储使用MySQL

	数据库操作使用jpa

	缓存使用Redis

	日志存储及访问统计使用MongoDB

	日志框架slf4j+logback

	权限控制使用shiro加上Aop层使用自定义注解

	接口展示使用swagger-ui

+ 前端

    Vue框架

	Nginx服务器

## 代码库地址
	博客系统：https://github.com/cylover945/blog4u
	
## 使用说明

+ **运行环境搭建**

	Linux系统: CentOS7.4
	
	Java运行环境: JDK1.8

	Docker环境

+ **Docker镜像下载**

	地址：https://hub.daocloud.io/repos

	需要下载以下镜像：

	1.MySQL

	2.Redis

	3.MongoDB

	4.Nginx

+ **后端打包运行**

	下载代码后，使用maven命令进行打包“mvn clean package”


	打包完成后，使用“nohup java -jar jar包名 > tmp.txt &” 运行jar包
	
+ **前端打包运行**

	1.使用“npn run build”打包前端工程，打包完成生产dist文件夹，如下图

	2.制作docker镜像

		首先使用WinScp等工具，将dist文件夹下的内容(static文件夹和index.html)移动到服务器上，前端工程的文件夹如blog4u文件夹

		创建一个Dockerfile，内容如下：

			FROM hub.c.163.com/library/nginx
			COPY blog4u/  /usr/share/nginx/html/
			RUN rm /etc/nginx/conf.d/default.conf
			ADD default.conf /etc/nginx/conf.d/

		主要作用：
			1.基于nginx来制作镜像  
			2.将当前目录下blog4u文件夹(包含了static文件夹和index.html)拷贝到nginx服务器的html目录下
			3.移除nginx原来的配置文件，添加自定义的配置文件
		

		自定义的default.conf配置文件

			server {
			    listen       80;
			    server_name  localhost;
			    location / {
			        root   /usr/share/nginx/html;
			        index  index.html index.htm;
			    }
			    location /blog/{
			        proxy_pass  http://你的服务器地址:8081;
			        proxy_set_header Host $host;
			        proxy_set_header X-Real-IP $remote_addr;
			        proxy_set_header REMOTE-HOST $remote_addr;
			        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			    }
			    location /favicon.ico {
			        root /usr/share/nginx/html;
			    }
			    location = /50x.html {
			        root   html;
			    }
			
			}
			server {
			        listen 443;
			        server_name localhost;
			        ssl on;
			        root html;
			        index index.html index.htm;
			        ssl_certificate  /usr/share/nginx/html/cert/cert.pem;
			        ssl_certificate_key /usr/share/nginx/html/cert/cert.key;
			        ssl_session_timeout 5m;
			        ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;
			        ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
			        ssl_prefer_server_ciphers on;
			
			       location / {
			        root   /usr/share/nginx/html;
			        index  index.html index.htm;
			            }
			       # set site favicon
			       location /favicon.ico {
			        root /usr/share/nginx/html;
			       }
			
			       location /blog/ {
			         proxy_pass  http://你的服务器地址:8081;
			         proxy_set_header Host $host;
			         proxy_set_header X-Real-IP $remote_addr;
			         proxy_set_header REMOTE-HOST $remote_addr;
			         proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			 }

		主要作用：
				1.将/blog/的请求(包括真实的ip地址通过X-Real-IP)转发到指定的服务器
				2./favicon.ico为你网页的logo
				3.https的配置，本文中使用的是阿里云的证书，如果是其他证书，请使用对应https配置方式

	3.生成及运行镜像

		生成：docker build -t 镜像名 .

		运行：docker run --name 容器名 -d -p 443:443 镜像名

# 总结

+ 此博客系统还存在很多不足的地方，后续会不断改进，如使用ES进行搜索，使用docker compose等
+ 鉴于作者的前端水平有限，只能扣扣补补，希望大家海涵
