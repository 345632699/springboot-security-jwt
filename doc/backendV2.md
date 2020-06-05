[TOC]
## **1\. 套餐列表**
### 接口功能
> 套餐列表

#### URL
> http://domainm/api/v2/package/list

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|page | true | int  | 页码|
|limit | true | int  | 分页条数|
|keyword | true | int  | 查询关键字 名字 |

#### 请求示例
``` javascript
{
	"page": 1,
	"limit": 10,
	"keyword": ""
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    | 套餐ID  |
|name   |string    | 套餐名称  |
|sketch   |string    | 简述  |
|bannerImage   |string    | 背景图片  |
|price   |string    | 价格  |
|specialPrice   |string    | 特价价格  |
|publishStatus   |string    | 发布状态 0 未发布 1 已发布  |
|description   |string    | 详细描述  |

#### 接口示例
```$xslt
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
                "name": "套餐1",
                "sketch": "房间1 向阳",
                "config": "",
                "price": 100.00,
                "specialPrice": 300.00,
                "createdAt": null,
                "updatedAt": null,
                "description": null
            }
        ]
    }
}
```

## **2\. 套餐详情**
### 接口功能
> 套餐详情

#### URL
> http://domainm/api/v2/package/detail

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true | int  | list 返回的套餐ID 字段|

#### 请求示例
``` javascript
{
	"id":"12"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    | 套餐ID  |
|name   |string    | 套餐名称  |
|sketch   |string    | 简述  |
|bannerImage   |string    | 背景图片  |
|price   |string    | 价格  |
|specialPrice   |string    | 特价价格  |
|publishStatus   |string    | 发布状态 0 未发布 1 已发布  |
|description   |string    | 详细描述  |
|configVo   |string    | 套餐的配置  |
|imgs   |array    | 详情图片数组集合  |

#### configVo
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|needPay  | bool  | 是否需要支付金额|
|needTimePicker    | bool  | 是否选择日期 |
|needTimeDetail    | bool  | 是否需要选择日期以及时分秒 |

#### items 套餐子项
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|packageId  | bool  | 套餐ID|
|itemName    | bool  | 子项目名称 |
|itemNumber    | bool  | 子项目使用次数 |


#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 12,
        "name": "1",
        "sketch": "房间1 向阳",
        "config": "{\"needPay\":true,\"needTimePicker\":true,\"needTimeDetail\":true}",
        "price": 100.00,
        "specialPrice": 300.00,
        "createdAt": null,
        "updatedAt": null,
        "description": null,
        "configVo": {
            "needPay": true,
            "needTimePicker": true,
            "needTimeDetail": true
        },
        "packageItems": [
            {
                "id": 12,
                "packageId": 12,
                "itemName": "333",
                "itemNumber": 11
            }
        ],
        "imgs": [
            "1",
            "2",
            "3"
        ]
    }
}
```

## **3\. 创建套餐**
### 接口功能
> 创建套餐

#### URL
> http://domainm/api/v2/package/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|name | true | string  | 套餐名字|
|sketch | true | string  | 简述|
|price | true | string  | 价格 当needPay为true 需要设置|
|specialPrice | true | string  | 价格 当needPay为true 需要设置|
|description | true | string  | 详细描述|
|imgs | true | array  | 图片数组|
|bannerImage | true | array  | 背景图片|
|configVo | true | string  | 配置数组|
|needPay | true | string  | 是否需要支付|
|needTimePicker | true | boolean  | 是否需要选择日期|
|needTimeDetail | true | boolean  | 是否需要选择日期详情|

#### 请求示例
``` javascript
{
    "name": 1,
    "config": "",
    "sketch": "房间1 向阳",
    "price": 100.00,
    "specialPrice": 300.00,
    "description": null,
    "imgs": [1,2,3],
	"configVo":{
		"needPay": true,
		"needTimePicker": true,
		"needTimeDetail": true
	},
    "packageItems": [
    	{
    		"itemName": "333",
    		"itemNumber": 11
    	}
    ]
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
    }
}
```

## **4\. 套餐商家下架**
### 接口功能
> 创建套餐

#### URL
> http://domainm/api/v2/package/publishOrDown

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|id | true | int  | 订单ID|
|operateType | true | int  | 操作类型 0 下架 1 上架|

#### 请求示例
``` javascript
{
    "id": 1,
    "operateType": 0
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
    }
}
```

## **5\. 创建或者更新时间设置**
### 接口功能
> 创建或者更新时间设置

#### URL
> http://domainm/api/v2/setting/time/createOrUpdate

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|month | true | int  | 月份范围 单位/月|
|weekdays | true | array  | 可选择的星期几,默认为空时,表示无限制,取值如下：<br> 0-星期日<br> 1-星期一 <br>2-星期二 <br>3-星期三<br> 4-星期四<br> 5-星期五 <br>6-星期六|
|startTime | true | string  | 日期起始时间 格式：'HH:mm:ss'|
|endTime | true | string  | 日期结束时间 格式：'HH:mm:ss'|
|timeSpace | true | int  | 时间间隔 单位/分钟|
|limit | true | array  | 每个时间段限制认识 单位/人|
|occupyNumber | true | array  | 暂时时间段 单位/timeSpace|

#### 请求示例
``` javascript
{
    "month": 1,
    "weekdays": [1,2,3,4],
    "startTime": "12:00:00",
    "endTime": "18:00:00",
    "timeSpace": 10,
    "limit": 1,
    "occupyNumber": 3
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
    }
}
```

## **6\. 订单列表**
### 接口功能
> 订单列表

#### URL
> http://domainm/api/v2/order/list

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|page | true | int  | 页码|
|limit | true | int  | 分页条数|
|sort | true | int  | 排序 0 由新到旧 1 由旧到新 |
|orderStatus | true | int  | 订单状态 <br> -1 全部<br> 0 待支付<br> 1 未完成<br> 2 已完成<br> 3 已取消<br> 4 已退款 |
|packageId | true | int  | 套餐ID -1 全部 |

#### 请求示例
``` javascript
{
   "page": 1,
   "limit": 10,
   "sort": 0,
   "orderStatus": -1,
   "packageId": -1
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|outTradeNo| string |  商户订单号 |
|packageId| int |  套餐ID |
|packageName| int |  套餐名字 |
|price| int |  订单价格 |
|realPrice| int |  实际支付价格 |
|startTime| int |  预约开始时间 |
|endTime| string |  用户姓名 |
|mobile| string |  用户手机 |
|status| int |  订单状态 |
|payStatus| int |  支付状态 |
|memo| string |  用户留言 |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "totalCount": 8,
        "currentPage": 1,
        "limit": 10,
        "data": [
            {
                "id": 8,
                "outTradeNo": "PO_1572080619705",
                "packageId": 12,
                "packageName": "1",
                "openId": "oOukb5BN4nM05h_AgxyYwqEndT5I",
                "userId": null,
                "price": 100.00,
                "realPrice": 100.00,
                "startTime": "2019-10-24T04:00:00.000+0000",
                "endTime": "2019-10-24T04:30:00.000+0000",
                "unionId": null,
                "userName": "1234",
                "mobile": "13760264461",
                "status": 0,
                "payStatus": null,
                "memo": "备注",
                "createdAt": "2019-10-26T09:03:40.000+0000",
                "updatedAt": null
            },
            {
                "id": 7,
                "outTradeNo": "PO_1572080539935",
                "packageId": 12,
                "packageName": "1",
                "openId": "oOukb5BN4nM05h_AgxyYwqEndT5I",
                "userId": null,
                "price": 100.00,
                "realPrice": 100.00,
                "startTime": "2019-10-24T04:00:00.000+0000",
                "endTime": "2019-10-24T04:30:00.000+0000",
                "unionId": null,
                "userName": "1234",
                "mobile": "13760264461",
                "status": 0,
                "payStatus": null,
                "memo": "备注",
                "createdAt": "2019-10-26T09:02:20.000+0000",
                "updatedAt": null
            },
            {
                "id": 6,
                "outTradeNo": "PO_1572080518788",
                "packageId": 12,
                "packageName": "1",
                "openId": "oOukb5BN4nM05h_AgxyYwqEndT5I",
                "userId": null,
                "price": 100.00,
                "realPrice": 100.00,
                "startTime": "2019-10-24T04:00:00.000+0000",
                "endTime": "2019-10-24T04:30:00.000+0000",
                "unionId": null,
                "userName": "1234",
                "mobile": "13760264461",
                "status": 0,
                "payStatus": null,
                "memo": "备注",
                "createdAt": "2019-10-26T09:01:59.000+0000",
                "updatedAt": null
            },
            {
                "id": 5,
                "outTradeNo": "PO_1572000899742",
                "packageId": 12,
                "packageName": "1",
                "openId": "oOukb5BN4nM05h_AgxyYwqEndT5I",
                "userId": null,
                "price": 100.00,
                "realPrice": 100.00,
                "startTime": "2019-10-24T04:00:00.000+0000",
                "endTime": "2019-10-24T04:30:00.000+0000",
                "unionId": null,
                "userName": "1234",
                "mobile": "13760264461",
                "status": 0,
                "payStatus": null,
                "memo": "备注",
                "createdAt": "2019-10-25T10:55:00.000+0000",
                "updatedAt": null
            },
            {
                "id": 4,
                "outTradeNo": "PO_1572000811951",
                "packageId": 12,
                "packageName": "1",
                "openId": "oOukb5BN4nM05h_AgxyYwqEndT5I",
                "userId": null,
                "price": 100.00,
                "realPrice": 100.00,
                "startTime": "2019-10-24T04:00:00.000+0000",
                "endTime": "2019-10-24T04:30:00.000+0000",
                "unionId": null,
                "userName": "1234",
                "mobile": "13760264461",
                "status": null,
                "payStatus": null,
                "memo": "备注",
                "createdAt": "2019-10-25T10:53:32.000+0000",
                "updatedAt": null
            },
            {
                "id": 3,
                "outTradeNo": null,
                "packageId": null,
                "packageName": null,
                "openId": null,
                "userId": null,
                "price": null,
                "realPrice": null,
                "startTime": "2019-10-24T05:50:00.000+0000",
                "endTime": "2019-10-24T06:20:00.000+0000",
                "unionId": null,
                "userName": null,
                "mobile": null,
                "status": null,
                "payStatus": null,
                "memo": null,
                "createdAt": null,
                "updatedAt": null
            },
            {
                "id": 2,
                "outTradeNo": null,
                "packageId": null,
                "packageName": null,
                "openId": null,
                "userId": null,
                "price": null,
                "realPrice": null,
                "startTime": "2019-10-24T05:50:00.000+0000",
                "endTime": "2019-10-24T06:20:00.000+0000",
                "unionId": null,
                "userName": null,
                "mobile": null,
                "status": null,
                "payStatus": null,
                "memo": null,
                "createdAt": null,
                "updatedAt": null
            },
            {
                "id": 1,
                "outTradeNo": "1111",
                "packageId": 1,
                "packageName": "套餐1",
                "openId": "111",
                "userId": 1,
                "price": 1.00,
                "realPrice": 1.00,
                "startTime": "2019-10-24T05:00:00.000+0000",
                "endTime": "2019-10-24T05:30:00.000+0000",
                "unionId": null,
                "userName": null,
                "mobile": null,
                "status": 1,
                "payStatus": 1,
                "memo": null,
                "createdAt": null,
                "updatedAt": null
            }
        ]
    }
}
```

## **7\. 取消订单**
### 接口功能
> 取消订单

#### URL
> http://domainm/api/v2/order/cancel

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|orderId | true | string  | 订单ID|
|refundPrice | true | decimal  | 退款金额 needRefund 为true时 必填|
|needRefund | true | boolean  | 是否需要退款 指定退款金额时 为true |

#### 请求示例
``` javascript
{
    "id": "订单ID",
    "refundPrice": "10.22",
    "needRefund": true
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
    }
}
```
## **8\. 确认核销订单**
### 接口功能
> 确认核销订单

#### URL
> http://domainm/api/v2/order/confirmOrder

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|orderId | true | string  | 订单ID|

#### 请求示例
``` javascript
{
    "id": "订单ID"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
    }
}
```
## **8\. 时间配置详情**
### 接口功能
> 时间配置详情

#### URL
> http://domainm/api/v2/setting/info

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
|month  | int  | 月份范围 单位/月|
|weekdays  | array  | 可选择的星期几,默认为空时,表示无限制,取值如下：<br> 0-星期日<br> 1-星期一 <br>2-星期二 <br>3-星期三<br> 4-星期四<br> 5-星期五 <br>6-星期六|
|startTime | string  | 日期起始时间 格式：'HH:mm:ss'|
|endTime | string  | 日期结束时间 格式：'HH:mm:ss'|
|timeSpace  | int  | 时间间隔 单位/分钟|
|limit  | array  | 每个时间段限制认识 单位/人|
|occupyNumber | array  | 暂时时间段 单位/timeSpace|
|packageId | int  |  套餐ID|
|storeId | int  |  门店ID 预留字段 目前为空|
#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 4,
        "packageId": 10,
        "month": 3,
        "weekdays": "1,2,3,4,5,6",
        "startTime": "12:00-00",
        "endTime": "18:00-00",
        "timeSpace": 10,
        "limitNumber": 2,
        "occupyNumber": 3,
        "storeId": null
    }
}
```









