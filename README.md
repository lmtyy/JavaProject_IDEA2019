# 环保公众监督系统

![项目截图](https://cdn.nlark.com/yuque/0/2024/png/12702213/1733898825745-a68709e8-3eea-48e8-a8a4-5e91ef3bd767.png)

## 项目简介

环保公众监督系统是一个用于建立环保公众监督平台的软件解决方案，旨在拓宽监督渠道，增加环保工作透明度，完善公众监督机制，增强环境保护实效。

系统主要功能包括：
- 公众监督员提交空气质量反馈
- 系统管理员指派网格员进行实地检测
- 网格员提交污染物实测数据
- AQI数据统计与分析

## 功能模块

### 1. 公众监督员端(NEPS)
- 用户注册与登录
- 提交AQI反馈信息
- 查询历史反馈记录

### 2. 系统管理员端(NEPM)
- 管理员登录
- 查看AQI反馈数据
- 指派网格员处理反馈
- 查询实测数据
- 数据导出Excel

### 3. 网格员端(NEPG)
- 网格员登录
- 查看指派任务
- 提交污染物实测数据
- 动态计算AQI等级

## 技术栈

- **开发语言**: Java
- **图形界面**: JavaFX
- **数据存储**: 文件系统/MySQL数据库(可选)
- **开发工具**: Eclipse + JavaFX Scene Builder
- **其他技术**: Git版本控制、日志系统、Excel导出

## 项目结构

```
com.nep
├── controller      # 控制器类
├── dto            # 数据传输对象
├── entity         # 实体类
├── io             # 文件IO操作
├── service        # 业务层接口
├── service.impl   # 业务层实现
├── test           # 测试类
├── util           # 工具类
├── view           # 界面文件
image              # 图片资源
logs               # 日志文件
excel              # Excel导出文件
```

## 安装与运行

### 环境要求
- JDK 8+
- Eclipse IDE (推荐)
- JavaFX Scene Builder 2.0

### 安装步骤
1. 克隆本项目到本地:
   ```
   git clone https://github.com/yourusername/neusoft-environmental.git
   ```
2. 导入Eclipse:
   - File → Import → Existing Projects into Workspace
   - 选择项目目录

3. 配置运行环境:
   - 确保JDK 8+已安装
   - 安装JavaFX Scene Builder并配置到Eclipse

4. 初始化数据文件:
   - 运行`com.nep.test.Test`类初始化基础数据

5. 启动项目:
   - 三个端分别有对应的主类:
     - 公众监督员端: `com.nep.NepsMain`
     - 系统管理员端: `com.nep.NepmMain`
     - 网格员端: `com.nep.NepgMain`

## 使用说明

1. **公众监督员**:
   - 注册账号后登录
   - 填写AQI反馈信息并提交
   - 可查看历史反馈记录

2. **系统管理员**:
   - 使用预置管理员账号登录(如:1001/111)
   - 查看未指派的反馈信息
   - 指派网格员处理反馈
   - 导出数据到Excel

3. **网格员**:
   - 使用预置网格员账号登录(如:2024001/111)
   - 查看被指派的任务
   - 填写实测数据并提交

## 项目特色

1. **完善的日志系统**：记录所有关键操作和异常
2. **数据导出功能**：支持Excel格式导出
3. **实时计算**：动态计算AQI等级并显示
4. **MVC架构**：清晰的代码结构
5. **多端协同**：三端数据实时同步

## 贡献指南

欢迎提交Pull Request或Issue。提交前请确保:
1. 代码符合项目风格
2. 已通过基本测试
3. 更新相关文档

## 许可证

MIT License
