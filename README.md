# myknowledge
记录日常工作中的技术点


##### 关于github的使用
* 1.创建远程仓库
* 2.创建本地仓库，git init
* 3.本地仓库和远程仓库关联 git remote add oirgin master
* 4.push 到远程仓库 git push - u origin master

##### 关于数字格式的字符串格式化的问题
* 1  对于保留几位小数的问题：String.format("%.1f"); %表示小数点前任意数字，1表示保留几位小数
     - 例如 <3.1415926> 保留两位小数
    ```java
        String.format("%.2f","3.1415926");
    ```
* 2 数字保留小数问题：利用BigDecimal.setScale();

##### 时间格式化
*
    ```java
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");//yyyy-MM-dd HH:mm:ss  年月日 时分秒
    ````

##### 给纯色背景添加颜色
-----
 `
    image.setColorFilter(Color color);
 `

### 关于编程的一些想法
---
    后台返回的错误码和错误信息即errorCode 和errorMessage应该是属于同一层而不应该分划到数据结构中的两个部分, 如下:

```
    {
            errorCode:10001,
            errorMessage:"密码错误",
            "data":{
                ...
                }
    }
```




