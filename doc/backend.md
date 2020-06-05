[TOC]
## **1\. 查询指定项目属性**
### 接口功能
> 获取制定项目的分类信息

#### URL
> http://domain/api/auth/login

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|-----                               |
|usernameOrEmailOrPhone    |ture    |string|用户名/手机号 其中一个     |
|password    |true    |string   |密码|
|rememberMe   |false    |boolean   |记住我 默认为true 为false时 token的有效期为10分钟 true有效期为7天|

#### 请求示例
``` javascript
{
	"usernameOrEmailOrPhone": "admin",
	"password": "123456",
	"rememberMe": true
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|code   |int    |返回结果状态。200 正常   |
|message   |string    |返回信息   |
|data   |string    |返回数据  |
|token   |string    |jwttoken值  |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDcyODA2Mzc3NjYxMDA5OTIwIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NjY0OTEzMzciEsInJvbGVzIjpbIueuoeeQhuWRmCJdLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoicGFnZTp0ZXN0In0seyJhdXRob3JpdHkiOiJidG46dGVzdDpxdWVyeSJ9LHsiYXV0aG9yaXR5IjoiYnRuOnRlc3Q6aW5zZXJ0In0seyJhdXRob3JpdHkiOiJwYWdlOm1vbml0b3I6b25saW5lIn0seyJhdXRob3JpdHkiOiJidG46bW9uaXRvcjpvbmxpbmU6cXVlcnkifSx7ImF1dGhvcml0eSI6ImJ0bjptb25pdG9yOm9ubGluZTpraWNrb3V0In1dLCJleHAiOjE1NjcwOTYxMzF9.im73aCVAm2kkkp0pvXjkI-oqcFi4wbzICBWL5KU-tHM",
        "tokenType": "Bearer"
    }
}
```

## **2\. 获取七牛云上传token**
### 接口功能
> 获取七牛云上传token

#### URL
> http://domain/api/qiniu/getToken?bucket=

#### 支持格式
> QUERY

#### HTTP请求方式
> GET

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|bucket    |true    |string   |七牛云 命名空间|

#### 请求示例
``` javascript
{
	"bucket": "mistone"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|code   |int    |返回结果状态。200 正常   |
|message   |string    |返回信息   |
|data   |string    |返回数据  |
|token   |string    | 七牛云上传token  |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "token": "azHsCgWKqcWpMhgaSPRSyF7mxbiO6qLoiO-J1qZ8:YyAyYK4C8Xcdbet0Nv4HDw7tl9U=:eyJzY29wZSI6IiIsImRlYWRsaW5lIjoxNTY2NDk1NTkzfQ=="
    }
}
```

## **3\.所有规格列表**
### 接口功能
> 获取所有规格列表

#### URL
> http://domain/api/sepcs/all

#### 支持格式
> QUERY

#### HTTP请求方式
> GET

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |

#### 请求示例
``` javascript
{
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |规格ID   |
|name   |string    |规格名字   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": [
        {
            "id": 1,
            "name": "规格1",
            "createdAt": null,
            "updatedAt": null
        },
        {
            "id": 2,
            "name": "规格2",
            "createdAt": null,
            "updatedAt": null
        },
        {
            "id": 3,
            "name": "规格3",
            "createdAt": "2019-08-23T07:30:26.000+0000",
            "updatedAt": "2019-08-23T07:30:26.000+0000"
        }
    ]
}
```

## **4\.规格包含子项列表**
### 接口功能
> 获取规格包含子项列表

#### URL
> http://domain/api/sepcs/itemList/{id}

#### 支持格式
> QUERY

#### HTTP请求方式
> GET

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id   | true  |int    |规格ID   |
#### 请求示例
``` javascript
{
    "id": 3
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |规格子项ID   |
|name   |string    |规格子项名字   |
|specsId   |string    |规格父级ID   |
|specsName   |string    |规格父级名称  |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": [
        {
            "id": 2,
            "name": "规格子项1",
            "specsId": 3,
            "specsName": "规格3",
            "createdAt": "2019-08-23T08:05:34.000+0000",
            "updatedAt": "2019-08-23T08:05:34.000+0000"
        }
    ]
}
```

## **5\.规格创建**
### 接口功能
> 规格创建

#### URL
> http://domain/api/sepcs/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|name | true   |string    |规格名称   |
#### 请求示例
``` javascript
{
	"name":"规格3"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |创建成功后的规格ID   |
|name   |string    |规格名字   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 3,
        "name": "规格3",
        "createdAt": "2019-08-23T07:30:26.494+0000",
        "updatedAt": "2019-08-23T07:30:26.494+0000"
    }
}
```

## **6\.规格更新**
### 接口功能
> 规格更新

#### URL
> http://domain/api/sepcs/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |规格ID   |
|name | true   |string    |需要修改的名称字段   |
#### 请求示例
``` javascript
{
	"id": 3,
	"name": "规格333"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```

## **6\.规格删除**
### 接口功能
> 规格删除

#### URL
> http://domain/api/sepcs/delete

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |规格ID   |
#### 请求示例
``` javascript
{
	"id": 3
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例 成功删除返回
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```
####  存在关联返回
``` javascript
{
    "code": 400,
    "message": "存在依赖关系，不允许执行删除操作！",
    "data": null
}
```


## **8\.规格子项创建**
### 接口功能
> 规格子项创建

#### URL
> http://domain/api/sepcs/createItem

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|name  | true   |string    |规格子项名称   |
|specsId | true    |int    |规格父级ID   |
|specsName | true    |string    |规格父级名称   |
#### 请求示例
``` javascript
{
	"name": "规格子项1",
	"specsId": 3,
	"specsName": "规格3"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |创建成功后的规格子项ID   |
|name   |string    |规格子项名字   |
|specsId   |string    |规格父级ID   |
|specsName   |string    |规格父级名称  |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 2,
        "name": "规格子项1",
        "specsId": 3,
        "specsName": "规格3",
        "createdAt": "2019-08-23T08:05:34.231+0000",
        "updatedAt": "2019-08-23T08:05:34.231+0000"
    }
}
```
## **9\.规格子项更新**
### 接口功能
> 规格子项更新

#### URL
> http://domain/api/sepcs/updateItem

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |规格子项ID   |
|name | true   |string    |需要修改的名称字段   |
#### 请求示例
``` javascript
{
	"name": "规格子项1222",
	"id": 1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```

## **10\.规格子项删除**
### 接口功能
> 规格子项删除

#### URL
> http://domain/api/sepcs/deleteItem

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |规格子项ID   |
#### 请求示例
``` javascript
{
	"id": 1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例 成功删除返回
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```
####  存在关联返回
``` javascript
{
    "code": 400,
    "message": "存在依赖关系，不允许执行删除操作！",
    "data": null
}
```

## **11\.房源创建**
### 接口功能
> 房源创建

#### URL
> http://domain/api/apartment/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|name  | true   |string    |房源名字   |
|address | true    |string    |房源地址   |
#### 请求示例
``` javascript
{
	"name": "房源3",
	"address": "罗湖区"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |创建成功后的房源ID   |
|name   |string    |房源名字   |
|address   |string    |房源地址   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 3,
        "name": "房源3",
        "address": "罗湖区",
        "createdAt": "2019-08-23T09:26:57.568+0000",
        "updatedAt": "2019-08-23T09:26:57.568+0000"
    }
}
```

## **12\.房源更新**
### 接口功能
> 房源更新

#### URL
> http://domain/api/apartment/update

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |房源ID   |
|name | true |string    |房源名字   |
|address | true   |string    |房源地址   |
#### 请求示例
``` javascript
{
	"id": "1",
	"name": "房源1",
	"address": "南山区时代饺子大厦"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```

## **13\.房源删除**
### 接口功能
> 房源删除

#### URL
> http://domain/api/apartment/delete

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |房源ID   |
#### 请求示例
``` javascript
{
	"id": 1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例 成功删除返回
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```
####  存在关联返回
``` javascript
{
    "code": 400,
    "message": "存在依赖关系，不允许执行删除操作！",
    "data": null
}
```

## **14\.房源列表**
### 接口功能
> 获取房源列表

#### URL
> http://domain/api/apartment/list

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |房源ID   |
#### 请求示例
``` javascript
{
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |房源主键ID   |
|name   |string    |房源名字   |
|address   |string    |房源地址   |


#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": [
        {
            "id": 1,
            "name": "房源1",
            "address": "南山区",
            "createdAt": "2019-08-19T06:18:40.000+0000",
            "updatedAt": "2019-08-23T09:25:35.000+0000"
        },
        {
            "id": 2,
            "name": "房源2",
            "address": "福田区",
            "createdAt": "2019-08-23T09:25:40.000+0000",
            "updatedAt": "2019-08-23T09:25:45.000+0000"
        }
    ]
}
```

## **15\.房间列表**
### 接口功能
> 获取房源列表

#### URL
> http://domain//api/room/list

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|page | true |int    |页码   |
|limit | true |int    |每页条数   |
|apartmentId | true |int    |房源ID   |
|keyword | false |int    |关键字查询   |
#### 请求示例
``` javascript
{
	"page": 1,
	"limit": 10,
	"keyword": "",
	"apartmentId" : 1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |房间ID   |
|apartmentId   |int    |房源ID   |
|title   |string    |房间标题   |
|sketch   |string    |描述   |
|rentPrice   |string    |租金   |
|depositPrice   |string    |押金   |
|checkInTime   |string    |入住时间   |
|checkOutTime   |string    |退房时间   |
|address   |string    |房间地址   |
|roomPassword   |string    |房间密码   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |
|description   |string    |房间描述   |
|status    |string    |房间状态 0 暂停出租 1 在租   |



#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "totalCount": 1,
        "currentPage": 1,
        "limit": 10,
        "data": [
            {
                "id": 1,
                "apartmentId": 1,
                "title": "房间1",
                "sketch": "房间1 向阳",
                "rentPrice": 100.00,
                "depositPrice": 300.00,
                "checkInTime": "1970-01-01T09:27:39.000+0000",
                "checkOutTime": "1969-12-31T16:27:41.000+0000",
                "address": "详细地址",
                "roomPassword": "12345678",
                "status": 0,
                "createdAt": "2019-08-23T09:32:00.000+0000",
                "updatedAt": "2019-08-23T09:32:05.000+0000",
                "description": null
            }
        ]
    }
}
```

## **16\.房间创建**
### 接口功能
> 房间创建

#### URL
> http://domain//api/room/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|apartmentId  | true  |int    |房源ID   |
|title | true    |string    |房间标题   |
|sketch  | true   |string    |描述   |
|rentPrice  | true  |string    |租金   |
|depositPrice | true   |string    |押金   |
|checkInTime | true   |string    |入住时间   |
|checkOutTime | true   |string    |退房时间   |
|address | true   |string    |房间地址   |
|roomPassword | true   |string    |房间密码   |
|description | true   |string    |房间描述   |
|settingIds | true   |array    |房间配置选中ID数组   |
|specItemIds | true   |array    |房间规格选中ID数组   |
|imagesIds | true   |array    |图片七牛ID数组   |
#### 请求示例
``` javascript
{
    "apartmentId": 1,
    "title": "房间1",
    "sketch": "房间1 向阳",
    "rentPrice": 100.00,
    "depositPrice": 300.00,
    "checkInTime": "1970-01-01T09:27:39.000+0000",
    "checkOutTime": "1969-12-31T16:27:41.000+0000",
    "address": "详细地址",
    "roomPassword": "12345678",
    "description": null,
    "settingIds": [1,2,3],
    "specItemIds": [2],
    "imagesIds": ["a", "b"]
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "totalCount": 1,
        "currentPage": 1,
        "limit": 10,
        "data": null
    }
}
```

## **17\.房间详情**
### 接口功能
> 房间详情

#### URL
> http://domain//api/room/info/{id}

#### 支持格式
> QUERY

#### HTTP请求方式
> GET

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id  | true  |int    |房间ID   |
#### 请求示例
``` javascript

```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |房间ID   |
|apartmentId   |int    |房源ID   |
|title   |string    |房间标题   |
|sketch   |string    |描述   |
|rentPrice   |string    |租金   |
|depositPrice   |string    |押金   |
|checkInTime   |string    |入住时间   |
|checkOutTime   |string    |退房时间   |
|address   |string    |房间地址   |
|roomPassword   |string    |房间密码   |
|status    |string    |房间状态 0 未上架 1 已上架   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |
|description   |string    |房间描述   |
|roomImages   |string    |房间图片列表   |
|roomSettings   |string    |房间配置列表   |
|specsItemList   |string    |房间规格列表   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 1,
        "apartmentId": null,
        "title": "房间1",
        "sketch": "房间1 向阳",
        "rentPrice": 100.00,
        "depositPrice": 300.00,
        "checkInTime": "1970-01-01T09:27:39.000+0000",
        "checkOutTime": "1969-12-31T16:27:41.000+0000",
        "address": "详细地址",
        "roomPassword": "12345678",
        "createdAt": "2019-08-23T09:32:00.000+0000",
        "updatedAt": "2019-08-23T09:32:05.000+0000",
        "description": "描述",
        "roomImages": [
            {
                "id": 1,
                "roomId": 1,
                "imageUrl": null
            }
        ],
        "roomSettings": [
            {
                "id": 1,
                "name": "房间设置1",
                "createdAt": "2019-08-23T10:43:45.000+0000",
                "updatedAt": "2019-08-23T10:43:50.000+0000"
            }
        ],
        "specsItemList": [
            {
                "id": 2,
                "name": "规格子项1",
                "specsId": 3,
                "specsName": "规格3",
                "createdAt": "2019-08-23T08:05:34.000+0000",
                "updatedAt": "2019-08-23T08:05:34.000+0000"
            }
        ]
    }
}
```

## **18\.房间信息更新**
### 接口功能
> 房间创建

#### URL
> http://domain//api/room/update

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id  | true  |int    |房间ID   |
|apartmentId  | false  |int    |房源ID   |
|title | false    |string    |房间标题   |
|sketch  | false   |string    |描述   |
|rentPrice  | false  |string    |租金   |
|depositPrice | false   |string    |押金   |
|checkInTime | false   |string    |入住时间   |
|checkOutTime | false   |string    |退房时间   |
|address | false   |string    |房间地址   |
|roomPassword | false   |string    |房间密码   |
|status | false   |string    |房间状态 0 未上架 1 已上架   |
|description | false   |string    |房间描述   |
|settingIds | false   |array    |房间配置选中ID数组   |
|specItemIds | false   |array    |房间规格选中ID数组   |
|imagesIds | false   |array    |图片七牛ID数组   |
#### 请求示例
``` javascript
{
	"id": 2,
    "apartmentId": 1,
    "title": "房间2",
    "sketch": "房间2 向阳",
    "rentPrice": 100.00,
    "depositPrice": 300.00,
    "checkInTime": "1970-01-01T09:27:39.000+0000",
    "checkOutTime": "1969-12-31T16:27:41.000+0000",
    "address": "详细地址",
    "roomPassword": "12345678",
    "description": null,
    "settingIds": [1,2],
    "specItemIds": [1,2,3],
    "imagesIds": ["a", "b", "c", "d"]
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```

## **19\.房间设置创建**
### 接口功能
> 房间设置创建

#### URL
> http://domain/api/setting/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|name  | true   |string    |房间配置名字   |
#### 请求示例
``` javascript
{
	"name": "房间设置2"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |房间配置的房源ID   |
|name   |string    |房间配置名字   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 2,
        "name": "房间设置2",
        "createdAt": null,
        "updatedAt": null
    }
}
```

## **20\.房间设置更新**
### 接口功能
> 房间设置更新

#### URL
> http://domain/api/setting/update

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |房源ID   |
|name | true |string    |房源名字   |
|address | true   |string    |房源地址   |
#### 请求示例
``` javascript
{
	"id": "1",
	"name": "房源1",
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```

## **21\.房间设置删除**
### 接口功能
> 房间设置删除

#### URL
> http://domain/api/setting/delete/{id}

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |房源ID   |
#### 请求示例
``` javascript
{
	"id": 1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例 成功删除返回
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```
####  存在关联返回
``` javascript
{
    "code": 400,
    "message": "存在依赖关系，不允许执行删除操作！",
    "data": null
}
```

## **22\.房间设置列表**
### 接口功能
> 房间设置列表

#### URL
> http://domain/api/setting/all

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
#### 请求示例
``` javascript
{
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |房间设置主键ID   |
|name   |string    |房间设置名字   |


#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": [
        {
            "id": 1,
            "name": "房间设置1",
            "createdAt": "2019-08-23T10:43:45.000+0000",
            "updatedAt": "2019-08-23T10:43:50.000+0000"
        },
        {
            "id": 2,
            "name": "房间设置2",
            "createdAt": null,
            "updatedAt": null
        }
    ]
}
```


## **23\.发送验证码**
### 接口功能
> 发送验证码

#### URL
> http://domain/sms/send

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
#### 请求示例
``` javascript
{
	"mobile": "13760264461"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |


#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": ""
}
```

## **24\.重置密码**
### 接口功能
> 重置密码

#### URL
> http://domain/api/auth/resetPassword

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
#### 请求示例
``` javascript
{
	"phone": "13760264461",
	"password": "12345678",
	"confirmPassword": "12345678",
	"code": "6544"
}
```

#### 返回字段
|返回字段|字段类型|说明   |
|:-----   |:------|:-------   |


#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": ""
}
```

## **22\.房间删除**
### 接口功能
> 房间设置删除

#### URL
> http://domain/api/room/delete

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true |int    |房源ID   |
#### 请求示例
``` javascript
{
	"id": 1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例 成功删除返回
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": null
}
```

## **23\.房间预定订单列表**
### 接口功能
> 房间预定订单列表

#### URL
> http://domain/api/order/list

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|page | true |int    |页码   |
|limit | true |int    |每页条数   |
|payStatus | true |int    | 订单支付状态  * -1 全部 * 0 未支付 * 1 已支付 * 2 退款中 * 3 已退款   |
|orderStatus | true |int    |订单入住状态     * -1 全部 * 0 未入住 * 1 已入住 * 2 待退房 * 3 已完成 * 4 已取消  |
|sort | true |int    | 排序  * 0 退房时间由就到新 * 1 入住时间由旧到新  * 2 退房时间由新到旧 * 3 入住时间由新到旧   |
|apartmentId | true |int    | 房源ID  * -1 全部   |
#### 请求示例
``` javascript
{
	"page": 1,
	"limit": 10,
	"payStatus": -1,
	"orderStatus": -1,
	"sort": 0,
	"apartmentId" : -1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|totalCount|int |总条数 |
|currentPage|int | 当前页码|
|limit| int| 每页条数|
|id   |int    |房间ID   |
|orderSn   |string    |订单编号   |
|name   |string    |下单人姓名   |
|mobile   |string    |下单人手机   |
|idNo   |string    |下单人身份证号   |
|roomId   |int    |房间ID   |
|roomName   |string    |房间姓名   |
|status   |string    |订单状态 0 未入住 1 已入住 2 待退房 3 已完成 4 已取消   |
|checkInTime   |string    |入住时间  |
|checkOutTime   |string    |退房时间  |
|rentPrice   |decimal    |租金/天  |
|countDay   |int    |入住天数  |
|realPayRentPrice   |decimal    |实际应支付租金  |
|depositPrice   |decimal    |押金  |
|realPayDepositPrice   |decimal    |实际应支付押金  |
|users   |array    |入住人信息列表  |
|payStatus   |int    |0 未支付 1 已支付 2 退款中 3 已退款  |

#### 接口示例 成功删除返回
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "totalCount": 53,
        "currentPage": 1,
        "limit": 10,
        "data": [
            {
                "id": 4,
                "orderSn": "RO_201909031543547378quS",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 2,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-11T02:22:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": null,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:43:55.000+0000",
                "updatedAt": "2019-09-03T07:43:55.000+0000"
            },
            {
                "id": 5,
                "orderSn": "RO_201909031545562120Ig2",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 2,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-12T02:22:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": null,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:45:56.000+0000",
                "updatedAt": "2019-09-03T07:45:56.000+0000"
            },
            {
                "id": 7,
                "orderSn": "RO_201909031547206335YGV",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 2,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-13T02:22:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": null,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:47:21.000+0000",
                "updatedAt": "2019-09-03T07:47:21.000+0000"
            },
            {
                "id": 8,
                "orderSn": "RO_20190903154834916y1PK",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 57,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-13T02:22:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 200.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:48:35.000+0000",
                "updatedAt": "2019-09-04T08:04:34.000+0000"
            },
            {
                "id": 9,
                "orderSn": "RO_20190903154846326pdpl",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 57,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-13T02:23:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 200.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:48:46.000+0000",
                "updatedAt": "2019-09-04T08:04:35.000+0000"
            },
            {
                "id": 10,
                "orderSn": "RO_201909031549028919NSW",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 57,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-11T16:00:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 100.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:49:03.000+0000",
                "updatedAt": "2019-09-04T08:04:32.000+0000"
            },
            {
                "id": 11,
                "orderSn": "RO_201909031552011557fnX",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 57,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-11T16:00:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 100.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-03T07:52:01.000+0000",
                "updatedAt": "2019-09-04T08:04:30.000+0000"
            },
            {
                "id": 12,
                "orderSn": "RO_20190905165308597Oy78",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 2,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-11T16:00:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 100.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-05T08:53:09.000+0000",
                "updatedAt": "2019-09-05T08:53:09.000+0000"
            },
            {
                "id": 13,
                "orderSn": "RO_20190905182159185boJT",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 2,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-11T16:00:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 100.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-05T10:21:59.000+0000",
                "updatedAt": "2019-09-05T10:21:59.000+0000"
            },
            {
                "id": 14,
                "orderSn": "RO_20190905182215506e2Xe",
                "name": "1111",
                "mobile": "133",
                "idNo": "111",
                "roomId": 2,
                "roomName": "333",
                "status": 0,
                "checkInTime": "2001-11-11T02:22:00.000+0000",
                "checkOutTime": "2001-11-11T16:00:00.000+0000",
                "rentPrice": 100.00,
                "realPayRentPrice": 100.00,
                "depositPrice": 300.00,
                "realPayDepositPrice": 300.00,
                "countDay": null,
                "payStatus": 0,
                "createdAt": "2019-09-05T10:22:16.000+0000",
                "updatedAt": "2019-09-05T10:22:16.000+0000"
            }
        ]
    }
}
```


## **24\.取消房间订单**
### 接口功能
> 取消订单

#### URL
> http://domain/api/order/cancelOrder

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|roomOrderId | true |int    |房间订单ID   |
|returnPrice | true |int    |退款金额   |
|password | true |int    | 新密码   |
#### 请求示例
``` javascript
{
	"roomOrderId": 14,
	"returnPrice": 100.00,
	"password": "123456"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |


## **25\.退房**
### 接口功能
> 取消订单

#### URL
> http://domain/api/order/checkout

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|roomOrderId | true |int    |房间订单ID   |
|returnPrice | true |int    |退款金额   |
|password | true |int    | 新密码   |
#### 请求示例
``` javascript
{
	"roomOrderId": 14,
	"returnPrice": 100.00,
	"password": "123456"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |