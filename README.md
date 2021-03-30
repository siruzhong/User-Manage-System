> 前后端分离员工管理系统，后端采用`SpringBoot`+`MyBatis`，前端采用`Vue`+`ElementUI`，数据库为`MySQL`;
>
> 实现员工信息的CURD及分页查询显示，可选择每页显示的数据量

# 图片演示

**首页**

![image-20210330093600458](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330093600458.png)

**用户管理页**

![image-20210330093609323](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330093609323.png)

![image-20210330093619536](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330093619536.png)

![image-20210330093530500](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330093530500.png)



---

<br>



# 项目结构

> 前端为`8081`端口，后端为`8082`端口，两者之间通过异步请求交互，后端给前端传送json数据并显示

## 前端：8081端口

**项目名**：`employee_system`

![image-20210330011101406](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330011101406.png)

## 后端：8082端口

**项目名**：`user_manager_system`

![image-20210330011207068](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330011207068.png)

---

<br>




# 数据库sql文件

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : MySQL数据库
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : users

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/03/2021 01:14:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 312313 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '高楚楚', '女', '2021-03-21 08:00:00', '福建省福州市');
INSERT INTO `user` VALUES (2, '钟嗣儒', '男', '2021-03-21 08:00:00', '湖北省武汉市');
INSERT INTO `user` VALUES (4, 'bareth', '男', '2021-03-23 08:00:00', '湖南省长沙市');
INSERT INTO `user` VALUES (5, 'zsr', '男', '2021-03-16 08:00:00', '浙江省杭州市');
INSERT INTO `user` VALUES (6, 'gcc', '女', '2021-03-16 08:00:00', '四川省重庆市');

SET FOREIGN_KEY_CHECKS = 1;

```

---

<br>

# 启动方法

clone两项目到本地，用`IDEA`分两个窗口打开

+ **前端项目**：`employee_system`
+ **后端项目**：`user_manager_system`，修改`application.properties`中的数据库配置信息为自己

## 方式一：前后端分开启动

### 启动后端

点击主启动类`UserManagerSystemApplication`启动

![image-20210330012124076](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330012124076.png)

然后访问`localhost:8082`

### 启动前端

在终端输入如下命令启动项目，然后打开`localhost:8081端口`

```shell
npm start 
```

![image-20210330012502699](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330012502699.png)

然后访问`localhost:8081`即可！

![image-20210330012527574](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330012527574.png)

## 方式二：前端打包后端启动

在终端输入如下命令进行打包

```shell
npm run build
```

![image-20210330012812837](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330012812837.png)

然后会在项目根目录下生成`dist`目录，复制其中的`static`目录和`index.html`

![image-20210330012910165](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330012910165.png)

复制到后端项目的`/resourse/static`目录下

![image-20210330013023629](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330013023629.png)

然后直接启动后端主启动类`UserManagerSystemApplication`即可进入到管理界面

![image-20210330013100283](https://gitee.com/zhong_siru/images/raw/master//img/image-20210330013100283.png)
