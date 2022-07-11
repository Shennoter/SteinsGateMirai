# SteinsGateMirai
一个Mirai-Console的石头门相关插件

## 指令
- 本插件权限ID为 `pers.shennoter.steinsgate:*`  
- 详见用户手册[PermissionCommand](https://github.com/mamoe/mirai/blob/dev/mirai-console/docs/BuiltInCommands.md#permissioncommand)  
- 如果输了命令没反应请在控制台输入`/permission add <想要给予权限的对象> pers.shennoter.steinsgate:<想要开启的指令>`  
- 如给予所有对象关于此插件的所有权限，请输入`/permission add * pers.shennoter.steinsgate:*`  

|指令(`<>`必填项，`[]`选填项)|功能|  
|:---|:---|  
|`/div` `[divergence]`| 生成指定世界线变动率图片，若无参数则随机生成，有概率生成特殊世界线变动率图片，概率可在config设置|  
|`/udiv`|生成一个特殊世界线变动率图片|  
|`/timeline` `/年表` `[year]`|发送一个年份的事件，无参数则随机发送|  
|`dqq` `<message> [time]`|生成一条D-QQ；time基准为当前时间，默认为5天，单位为秒，可为负数；可在config修改指令|  

## Todo
电话微波炉计时功能


## 示例
![1](https://github.com/Shennoter/SteinsGateMirai/blob/master/pics/1.png)
![2](https://github.com/Shennoter/SteinsGateMirai/blob/master/pics/2.png)
![3](https://github.com/Shennoter/SteinsGateMirai/blob/master/pics/3.png)
![4](https://github.com/Shennoter/SteinsGateMirai/blob/master/pics/4.png)
![5](https://github.com/Shennoter/SteinsGateMirai/blob/master/pics/5.png)
![6](https://github.com/Shennoter/SteinsGateMirai/blob/master/pics/6.png)
