[TOC]
## **1\. 获取可预约的时间列表（天）**
### 接口功能
> 获取可预约的时间列表（天）

#### URL
> http://domainm/wx/v2/package/getDayList

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|-----                               |

#### 请求示例
``` javascript

```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|day   |string    |日期 yyyy-MM-dd   |
|weekName   |string    |星期几   |

#### 接口示例
``` javascript
{
    "code": 200,
    "message": "操作成功！",
    "data": [
        {
            "day": "2019-10-25",
            "weekName": "星期五"
        },
        {
            "day": "2019-10-26",
            "weekName": "星期六"
        },
        {
            "day": "2019-10-28",
            "weekName": "星期一"
        },
        {
            "day": "2019-10-29",
            "weekName": "星期二"
        },
        {
            "day": "2019-10-30",
            "weekName": "星期三"
        },
        {
            "day": "2019-10-31",
            "weekName": "星期四"
        },
        {
            "day": "2019-11-01",
            "weekName": "星期五"
        },
        {
            "day": "2019-11-02",
            "weekName": "星期六"
        },
        {
            "day": "2019-11-04",
            "weekName": "星期一"
        },
        {
            "day": "2019-11-05",
            "weekName": "星期二"
        },
        {
            "day": "2019-11-06",
            "weekName": "星期三"
        },
        {
            "day": "2019-11-07",
            "weekName": "星期四"
        },
        {
            "day": "2019-11-08",
            "weekName": "星期五"
        },
        {
            "day": "2019-11-09",
            "weekName": "星期六"
        },
        {
            "day": "2019-11-11",
            "weekName": "星期一"
        },
        {
            "day": "2019-11-12",
            "weekName": "星期二"
        },
        {
            "day": "2019-11-13",
            "weekName": "星期三"
        },
        {
            "day": "2019-11-14",
            "weekName": "星期四"
        },
        {
            "day": "2019-11-15",
            "weekName": "星期五"
        },
        {
            "day": "2019-11-16",
            "weekName": "星期六"
        },
        {
            "day": "2019-11-18",
            "weekName": "星期一"
        },
        {
            "day": "2019-11-19",
            "weekName": "星期二"
        },
        {
            "day": "2019-11-20",
            "weekName": "星期三"
        },
        {
            "day": "2019-11-21",
            "weekName": "星期四"
        },
        {
            "day": "2019-11-22",
            "weekName": "星期五"
        },
        {
            "day": "2019-11-23",
            "weekName": "星期六"
        },
        {
            "day": "2019-11-25",
            "weekName": "星期一"
        },
        {
            "day": "2019-11-26",
            "weekName": "星期二"
        },
        {
            "day": "2019-11-27",
            "weekName": "星期三"
        },
        {
            "day": "2019-11-28",
            "weekName": "星期四"
        },
        {
            "day": "2019-11-29",
            "weekName": "星期五"
        },
        {
            "day": "2019-11-30",
            "weekName": "星期六"
        },
        {
            "day": "2019-12-02",
            "weekName": "星期一"
        },
        {
            "day": "2019-12-03",
            "weekName": "星期二"
        },
        {
            "day": "2019-12-04",
            "weekName": "星期三"
        },
        {
            "day": "2019-12-05",
            "weekName": "星期四"
        },
        {
            "day": "2019-12-06",
            "weekName": "星期五"
        },
        {
            "day": "2019-12-07",
            "weekName": "星期六"
        },
        {
            "day": "2019-12-09",
            "weekName": "星期一"
        },
        {
            "day": "2019-12-10",
            "weekName": "星期二"
        },
        {
            "day": "2019-12-11",
            "weekName": "星期三"
        },
        {
            "day": "2019-12-12",
            "weekName": "星期四"
        },
        {
            "day": "2019-12-13",
            "weekName": "星期五"
        },
        {
            "day": "2019-12-14",
            "weekName": "星期六"
        },
        {
            "day": "2019-12-16",
            "weekName": "星期一"
        },
        {
            "day": "2019-12-17",
            "weekName": "星期二"
        },
        {
            "day": "2019-12-18",
            "weekName": "星期三"
        },
        {
            "day": "2019-12-19",
            "weekName": "星期四"
        },
        {
            "day": "2019-12-20",
            "weekName": "星期五"
        },
        {
            "day": "2019-12-21",
            "weekName": "星期六"
        },
        {
            "day": "2019-12-23",
            "weekName": "星期一"
        },
        {
            "day": "2019-12-24",
            "weekName": "星期二"
        },
        {
            "day": "2019-12-25",
            "weekName": "星期三"
        },
        {
            "day": "2019-12-26",
            "weekName": "星期四"
        },
        {
            "day": "2019-12-27",
            "weekName": "星期五"
        },
        {
            "day": "2019-12-28",
            "weekName": "星期六"
        },
        {
            "day": "2019-12-30",
            "weekName": "星期一"
        },
        {
            "day": "2019-12-31",
            "weekName": "星期二"
        },
        {
            "day": "2020-01-01",
            "weekName": "星期三"
        },
        {
            "day": "2020-01-02",
            "weekName": "星期四"
        },
        {
            "day": "2020-01-03",
            "weekName": "星期五"
        },
        {
            "day": "2020-01-04",
            "weekName": "星期六"
        },
        {
            "day": "2020-01-06",
            "weekName": "星期一"
        },
        {
            "day": "2020-01-07",
            "weekName": "星期二"
        },
        {
            "day": "2020-01-08",
            "weekName": "星期三"
        },
        {
            "day": "2020-01-09",
            "weekName": "星期四"
        },
        {
            "day": "2020-01-10",
            "weekName": "星期五"
        },
        {
            "day": "2020-01-11",
            "weekName": "星期六"
        },
        {
            "day": "2020-01-13",
            "weekName": "星期一"
        },
        {
            "day": "2020-01-14",
            "weekName": "星期二"
        },
        {
            "day": "2020-01-15",
            "weekName": "星期三"
        },
        {
            "day": "2020-01-16",
            "weekName": "星期四"
        },
        {
            "day": "2020-01-17",
            "weekName": "星期五"
        },
        {
            "day": "2020-01-18",
            "weekName": "星期六"
        },
        {
            "day": "2020-01-20",
            "weekName": "星期一"
        },
        {
            "day": "2020-01-21",
            "weekName": "星期二"
        },
        {
            "day": "2020-01-22",
            "weekName": "星期三"
        },
        {
            "day": "2020-01-23",
            "weekName": "星期四"
        },
        {
            "day": "2020-01-24",
            "weekName": "星期五"
        }
    ]
}
```
## **2\. 获取可预约的时间某天的时间列表（时分秒）**
### 接口功能
> 获取可预约的时间某天的时间列表（时分秒）

#### URL
> http://domainm/wx/v2/package/getTimeList

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|checkStartTime | true | int  | 查询日期 'yyyy-MM-dd' |

#### 请求示例
``` javascript
{
	"checkStartTime":"2019-10-25"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|timeList   |string    | 预约时间的时间戳  |

#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": [
        "1571981400",
        "1571988600",
        "1571992800",
        "1571996400",
        "1571994600",
        "1571991600",
        "1571985000",
        "1571984400",
        "1571982000",
        "1571989200",
        "1571993400",
        "1571986200",
        "1571982600",
        "1571989800",
        "1571977200",
        "1571987400",
        "1571976000",
        "1571983800",
        "1571977800",
        "1571983200",
        "1571992200",
        "1571978400",
        "1571986800",
        "1571988000",
        "1571980200",
        "1571979600",
        "1571995200",
        "1571976600",
        "1571997000",
        "1571995800",
        "1571994000",
        "1571991000",
        "1571980800",
        "1571990400",
        "1571985600",
        "1571979000"
    ]
}
```

## **3\. 查询套餐列表）**
### 接口功能
> 查询套餐列表）

#### URL
> http://domainm/wx/v2/package/list

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|checkStartTime | true | int  | 页码|
|limit | true | int  | 分页条数|
|keyword | true | int  | 查询关键字 名字 |

#### 请求示例
``` javascript
{
	"page": 1,
	"limit": 1,
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
        "limit": 1,
        "data": [
            {
                "id": 12,
                "name": "1",
                "sketch": "房间1 向阳",
                "bannerImage": null,
                "config": "{\"needPay\":true,\"needTimePicker\":true,\"needTimeDetail\":true}",
                "price": 100.00,
                "specialPrice": 300.00,
                "publishStatus": 1,
                "createdAt": null,
                "updatedAt": null,
                "description": null
            }
        ]
    }
}
```

## **4\. 套餐详情**
### 接口功能
> 套餐详情

#### URL
> http://domainm/wx/v2/package/detail

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
        "items": [
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


## **5\. 创建套餐类型的预约订单**
### 接口功能
> 套餐详情

#### URL
> http://domainm/wx/v2/package/order/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |
|packageId | true | int  | 套餐ID|
|name | true | int  | 预订人名字|
|mobile | true | int  | 预订人电话|
|startTime | true | int  | 开始时间|
|endTime | true | int  | 结束时间|
|memo | true | int  | 备注留言|

#### 请求示例
``` javascript
{
	"packageId":"12",
	"name":"1234",
	"mobile": "13760264461",
	"startTime": "2019-10-24 12:00:00",
	"endTime": "2019-10-24 12:30:00",
	"memo": "备注"
}
```

#### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|id   |int    | 订单ID  |


#### 接口示例
```$xslt
{
    "code": 200,
    "message": "操作成功！",
    "data": {
        "id": 5
    }
}
```


## **6\. 批量收集用户的点击提交FORMId**
### 接口功能
> 批量收集用户的点击提交FORMId

#### URL
> http://domainm/wx/v2/wxFrom/create

#### 支持格式
> JSON

#### HTTP请求方式
> POST

#### 请求参数
|参数|必选|类型|说明|
|:-----  |:-------|:-----|----- |

|formId |true  |int    | 提交表单formId  |

#### 请求示例
``` javascript
[
    {
        "formId": "f9dd3jddsfsd9sd8s"
    }
]
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