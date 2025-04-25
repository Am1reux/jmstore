# JM Store
集美电商平台（JM Store） 是一个面向多用户的智能商店平台，旨在提供一个便捷、高效的购物体验。平台支持用户注册、登录、商品浏览、购物车管理、订单支付等功能，帮助用户轻松购物。平台还结合了智能推荐算法，为用户提供个性化的商品推荐，提升购物效率。



'
src/                            # 项目源代码根目录
├── main/                       # 主代码目录
│   ├── java/                   # Java 源代码
│   │   └── cn/                 # 包含组织结构
│   │       └── tedu/           # 项目公司或组织名
│   │           └── jmstore/    # 项目名称
│   │               ├── config/                 # 配置文件目录
│   │               │   └── InterceptorConfigurer.java
│   │               ├── controller/             # 控制层（处理请求和响应）
│   │               │   ├── AddressController.java
│   │               │   ├── BaseController.java
│   │               │   ├── DistrictController.java
│   │               │   ├── ProductController.java
│   │               │   └── UserController.java
│   │               ├── entity/                 # 实体类目录（与数据库交互）
│   │               │   ├── Address.java
│   │               │   ├── BaseEntity.java
│   │               │   ├── District.java
│   │               │   ├── Product.java
│   │               │   └── User.java
│   │               ├── interceptor/            # 请求拦截器目录
│   │               │   └── ...
│   │               ├── mapper/                 # 数据库映射层（CRUD）
│   │               │   ├── AddressMapper.java
│   │               │   ├── DistrictMapper.java
│   │               │   ├── ProductMapper.java
│   │               │   └── UserMapper.java
│   │               ├── service/                # 服务层（业务逻辑）
│   │               │   ├── ex/                 # 异常处理模块
│   │               │   ├── impl/               # 具体服务实现类
│   │               │   │   ├── IAddressService.java
│   │               │   │   ├── IDistrictService.java
│   │               │   │   ├── IProductService.java
│   │               │   │   └── IUserService.java
│   │               │   └── JsonResult.java     # 响应封装类
│   │               ├── util/                   # 工具类（如 JSON 处理等）
│   │               │   └── JsonResult.java
│   │               └── jmstoreApplication.java # 应用启动入口类
│   └── resources/               # 资源文件目录（配置文件、静态文件等）
└── pom.xml                      # Maven 配置文件（如果是 Maven 项目）
'
## 📖 功能概述

1 用户系统的注册以及登录功能：用户可以创建账户并通过电子邮件/手机号登录。

2 用户端添加收货地址功能：用户可以管理多个收货地址，简化结算流程。

3 用户查看购物车功能：用户可以查看已添加至购物车的商品，方便管理和修改。

4 首页热销商品推荐功能：智能推荐热销商品，帮助用户快速找到受欢迎商品。

5 用户搜索商品功能：支持根据商品名称、类别等多种条件进行商品搜索。

## 🚀 快速开始

1 克隆项目：
'
git clone https://github.com/Am1reux/jmstore.git
cd jmstore
'
2 安装环境:
根据pom.xml利用maven快速构建环境
java 版本选择 jdk1.8
springboot 
mysql 8.0.21
mybatis 1.3.2
其余具体版本见pom.xml

3 启动项目：
打开命令行，输入以下命令
'
cd jmstore
'

