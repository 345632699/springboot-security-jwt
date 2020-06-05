[TOC]
## **1\. 用户授权获取用户信息**
### 接口功能
> 用户授权获取用户信息，取用户的openid

#### URL
> http://java.mqphp.com/wx/login

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|-----                               |

#### 请求示例
``` javascript
{
    "appid": "wxf1017d0c13e86f43",
    "rawData": {"nickName":"许光富","gender":1,"language":"en","city":"","province":"Cuanza Sul","country":"Angola","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/WibfI1ZZuY9DI0HbcCXZ3OwOxH7hwib9TzhBJpHJl76aOicUq9NL8OiaMKNpqRkcHs9vJdNeCBgTHXibCdeTN9Sj5LA/132"},
    "code": "043r5Qyq17kmRi0czKuq1LpGyq1r5QyO",
    "signature": "af9466ebd5eb14ab7401d7db3014d31eba56888d",
    "iv": "keNvwDK6L1nJ80vRi5QaHw==",
    "encryptedData": "GCPt/w+Ynn/PTd9yH43SgtC5qaJWP+T6TmVmcPvdmpLSqWG97OJMnbXVbh+p1H8rqFARVSB4s2pdzW8LhADX83uRbf4EHsND4f14Kyta1+Udz2bo16dizsng+QIjxWvqHwB9Oej1vT2u850mk9uuh3l8iHsfUxXgly2la9DLGKtBo5POp3tQw3YH0qr2A+bVV6z+vi6PwhP+xd+4gJU69bjYF/4Ca/vqUTI4wvJasL1X/Wjem06gKqs5s1EyMiOqktwHDohzYWtfw7/cI/qb6T2MKw1KMu0lB9Fm5A6Tuviix2djb7SDBdBVAufcomFLZR53gna0mZK/FowQw9mVFggoE/DJHRy7CbiewM1gKhsRZnupyUQfsV+R995uxw1tqB6K69Ji07JBxSVLp8Eh6koGjm8WsHEppFqUtE0oFd2dc4oSXV8Em81sqth6OyJW0ku/Fu4al2yKOVct5hE4uPrQVrAjfZTt2e6larSFL2I="
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|appName   |int    |应用标记   |
|has_mobile   |string    |是否绑定了手机号 false 否 true 是   |
|token   |string    |token  |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
       appName: "hotel",
       has_mobile: false,
       token: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvT3VrYjVCTjRuTTA1aF9BZ3h5WXdxRW5kVDVJIiwiZXhwIjoxNTY2ODg5NjY1fQ.h4bzKxDM3UA6cggHwKb84qO5RptZKWCg_8KVllrLLKs"
    }
}
```

## **3\. 发送验证码**
### 接口功能
> 发送验证码

#### URL
> http://java.mqphp.com/wx/sms/send
#### header
```
{
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvT3VrYjVCTjRuTTA1aF9BZ3h5WXdxRW5kVDVJIiwiZXhwIjoxNTY2ODI2ODM1fQ.WwOXe0lcWvCMKEx6RvgqreI2klYm3t4MASLGiiwnb8k",
    "Content-Type": "application/json"
}
```

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
| mobile| true  | string  | 手机号 |

#### 请求示例
``` javascript
{
	"mobile":"13760264461"
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
    "data": "短信验证码发送成功"
}
```

## **3\. 完善用户信息**
### 接口功能
> 完善用户信息 绑定手机号

#### URL
> http://java.mqphp.com/wx/consumer/bindMobile
#### header
```
{
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvT3VrYjVCTjRuTTA1aF9BZ3h5WXdxRW5kVDVJIiwiZXhwIjoxNTY2ODI2ODM1fQ.WwOXe0lcWvCMKEx6RvgqreI2klYm3t4MASLGiiwnb8k",
    "Content-Type": "application/json"
}
```

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
| mobile| true  | string  | 手机号 |
| code| true  | string  | 验证码 |
| name| true  | string  | 姓名 |

#### 请求示例
``` javascript
{
	"mobile":"13760264461",
	"code":"8255",
	"name": "xuxuxxu"
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
    "data": "短信验证码发送成功"
}
```

## **4\. 房间列表**
### 接口功能
> 完善用户信息 绑定手机号

#### URL
> http://java.mqphp.com/wx/room/list
#### header
```
{
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvT3VrYjVCTjRuTTA1aF9BZ3h5WXdxRW5kVDVJIiwiZXhwIjoxNTY2ODI2ODM1fQ.WwOXe0lcWvCMKEx6RvgqreI2klYm3t4MASLGiiwnb8k",
    "Content-Type": "application/json"
}
```

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
| page| true  | string  | 页码 |
| limit| true  | string  | 条数 |
| apartmentId| false  | string  | 房源ID |
| keyword| false  | string  | 房间标题 |

#### 请求示例
``` javascript
{
	"apartmentId":1,
	"page":"1",
	"limit": "10"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |房间ID   |
|apartmentId   |int    |房源ID   |
|title   |string    |房间标题   |
|sketch   |string    |简述   |
|rentPrice   |string    |租金   |
|depositPrice   |string    |押金   |
|checkInTime   |string    |入住时间   |
|checkOutTime   |string    |退房时间   |
|address   |string    |房间地址   |
|roomPassword   |string    |房间密码   |
|createdAt   |string    |创建时间   |
|updatedAt   |string    |更新时间   |
|description   |string    |房间描述   |
|status    |string    |房间状态 0 未上架 1 已上架   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "totalCount": 2,
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
                "createdAt": "2019-08-23T09:32:00.000+0000",
                "updatedAt": "2019-08-23T09:32:05.000+0000",
                "status": 0,
                "description": null
            },
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
                "createdAt": "2019-08-23T09:32:00.000+0000",
                "updatedAt": "2019-08-25T11:28:50.000+0000",
                "status": 0,
                "description": null
            }
        ]
    }
}
```

## **5\.房间详情**
### 接口功能
> 房间详情

#### URL
> http://domain/wx/room/info

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

## **6\. 房间可预约状态查询**
### 接口功能
> 完善用户信息 绑定手机号

#### URL
> http://java.mqphp.com/wx/room/canNotReserveList
#### header
```
{
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvT3VrYjVCTjRuTTA1aF9BZ3h5WXdxRW5kVDVJIiwiZXhwIjoxNTY2ODI2ODM1fQ.WwOXe0lcWvCMKEx6RvgqreI2klYm3t4MASLGiiwnb8k",
    "Content-Type": "application/json"
}
```

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
| roomId| true  | int  | 查询的房间ID |
| startAt| true  | string  | 查询起始日期 |
| endAt| false  | string  | 查询结束日期 |

#### 请求示例
``` javascript
{
	"roomId": 2,
	"startAt": "2019-10-01",
	"endAt": "2019-11-30"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|  list |  array | 可预约时间列表   |
|  checkInTime |  string | 酒店入住时间点   |
|  checkOutTime |  string | 酒店退房时间点   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "list": {
            "2019-10-01": true,
            "2019-10-02": true,
            "2019-10-03": true,
            "2019-10-04": true,
            "2019-10-05": true,
            "2019-10-06": true,
            "2019-10-07": true,
            "2019-10-08": true,
            "2019-10-09": true,
            "2019-10-10": true,
            "2019-10-11": true,
            "2019-10-12": true,
            "2019-10-13": true,
            "2019-10-14": true,
            "2019-10-15": true,
            "2019-10-16": true,
            "2019-10-17": true,
            "2019-10-18": true,
            "2019-10-19": true,
            "2019-10-20": true,
            "2019-10-21": true,
            "2019-10-22": true,
            "2019-10-23": true,
            "2019-10-24": true,
            "2019-10-25": true,
            "2019-10-26": true,
            "2019-10-27": true,
            "2019-10-28": true,
            "2019-10-29": true,
            "2019-10-30": true,
            "2019-10-31": true,
            "2019-11-01": true,
            "2019-11-02": true,
            "2019-11-03": true,
            "2019-11-04": true,
            "2019-11-05": true,
            "2019-11-06": true,
            "2019-11-07": true,
            "2019-11-08": true,
            "2019-11-09": true,
            "2019-11-10": true,
            "2019-11-11": true,
            "2019-11-12": true,
            "2019-11-13": true,
            "2019-11-14": true,
            "2019-11-15": true,
            "2019-11-16": true,
            "2019-11-17": true,
            "2019-11-18": true,
            "2019-11-19": true,
            "2019-11-20": true,
            "2019-11-21": true,
            "2019-11-22": true,
            "2019-11-23": true,
            "2019-11-24": true,
            "2019-11-25": true,
            "2019-11-26": true,
            "2019-11-27": true,
            "2019-11-28": true,
            "2019-11-29": true,
            "2019-11-30": true
        },
        "checkInTime": "12:24",
        "checkOutTime": "12:25"
    }
}
```

## **7\. 房间可预约订单创建**
### 接口功能
> 完善用户信息 绑定手机号

#### URL
> http://java.mqphp.com/wx/order/create
#### header
```
{
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcGVuSWQiOiJvT3VrYjVCTjRuTTA1aF9BZ3h5WXdxRW5kVDVJIiwiZXhwIjoxNTY2ODI2ODM1fQ.WwOXe0lcWvCMKEx6RvgqreI2klYm3t4MASLGiiwnb8k",
    "Content-Type": "application/json"
}
```

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|name |true   |string    |下单人姓名   |
|mobile  |true  |string    |下单人手机   |
|idNo  |true  |string    |下单人身份证号   |
|roomId  |true  |int    |房间ID   |
|roomName |true   |string    |房间姓名   |
|checkInTime |true   |string    |入住时间  |
|checkOutTime |true  |string    |退房时间  |
|users  |true  |array    |入住人信息列表  |
|userName  |true  |array    |入住人姓名  |
|userIdno  |true  |array    |入住人身份证号码  |

#### 请求示例
``` javascript
{
	"name": "1111",
	"idNo": "111",
	"roomId": "2",
	"roomName": "333",
	"checkInTime": "2001-11-11 10:22:00",
	"checkOutTime": "2001-11-12 00:00:00",
	"mobile": "133",
	"users": [
		{
			"userName": "1",
			"userIdno": "333"
		}	
	]
}
```

#### 返回字段
|返回字段|字段类型|说明      |
|:-----   |:------|:---------   |
|id |int |订单ID |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 1
    }
}
```

## **9\.预定订单详情**
### 接口功能
> 房间详情

#### URL
> http://domain/wx/order/info

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
|realPayRentPrice   |decimal    |实际应支付租金  |
|depositPrice   |decimal    |押金  |
|realPayDepositPrice   |decimal    |实际应支付押金  |
|users   |array    |入住人信息列表  |

#### users 
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    |入住人列表ID   |
|roomId   |int    |入住人列表ID   |
|orderId   |int    |入住人列表ID   |
|userName   |string     |入住人用户名   |
|userIdno   |string     |入住人身份证号   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
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
        "createdAt": "2019-09-03T07:43:55.000+0000",
        "updatedAt": "2019-09-03T07:43:55.000+0000",
        "users": [
            {
                "id": 1,
                "roomId": 2,
                "userName": "1",
                "userIdno": "333",
                "orderId": 4
            }
        ]
    }
}
```
## **10\.获取手机号**
### 接口功能
> 房间详情

#### URL
> http://domain/wx/getMobile

#### 支持格式
> POST

#### HTTP请求方式
> GET

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|appid  | true  |string    |小程序APPID   |
|signature  | true  |string    |wx.getUserInfo()返回字段   |
|rawData  | true  |string    |wx.getUserInfo()返回字段   |
|encryptedData  | true  |string    |wx.getUserInfo()返回字段   |
|iv  | true  |string    |wx.getUserInfo()返回字段   |
|code  | true  |string    |wx.login()返回字段   |
#### 请求示例
``` javascript
{
    "appid": "",
    "signature": "",
    "rawData": "",
    "encryptedData": "",
    "iv": "",
    "code": "",
}
```

#### 返回字段

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
    }
}
```
## **11\.获取支付配置**
### 接口功能
> 获取支付配置

#### URL
> http://domain/wx/pay/getPayConfig

#### 支持格式
> POST

#### HTTP请求方式
> GET

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id  | true  |int    |房间订单ID   |
#### 请求示例
``` javascript
{
	"id": 55
}
```

#### 返回字段

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "appId": "wx092f89864f7afb50",
        "timeStamp": "1568693128",
        "nonceStr": "1568693128470",
        "packageValue": "prepay_id=wx171205308047142c5c879fb91828905500",
        "signType": "MD5",
        "paySign": "20304DE3C962837B9FCBF3F570F64F8B"
    }
}
```
