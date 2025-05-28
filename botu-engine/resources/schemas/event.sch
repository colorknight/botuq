{
  // 节点+日志源
  NODE_ADDRESS: ["Object","Ip"],// 接收日志的节点的IP地址,必填,系统填充
  DS_ID: ["String"],            // 日志源ID,必填,系统填充
  DS_TYPE: ["String"],          // 日志源的产品类型,必填,系统填充
  DS_DATA_TYPE: ["String"],     // 日志源的数据类型,必填,系统填充
  DS_NAME: ["String"],          // 日志源的名字,必填,系统填充
  DS_IP: ["Object", "Ip"],      // 日志源的IP地址,必填,系统填充
  DS_DVC_NAME: ["String"],      // 日志源所在的设备名
  DS_PROCESS_NAME: ["String"],  // 日志源生成日志的模块的名字
  // 事件基本信息
  RECEIPT_TIME: ["Datetime"],   // 日志接收时间,必填,系统填充
  UUID: ["String"],             // 事件的全局唯一ID,必填,系统填充
  EVT_ID: ["String"],           // 事件的ID
  START_TIME: ["Datetime"],     // 事件产生的时间,必填
  DURATION: ["Long"],           // 事件持续时间
  EVT_NAME: ["String"],         // 事件的名称
  EVT_CATEGORY: ["String"],     // 事件的原始分类，粗类型,必填
  EVT_TYPE: ["String"],         // 事件的原始类型，细类型，可以是规则ID
  SEVERITY: ["String"],         // 事件的严重级别,必填
  N_EVT_CATEGORY: ["String"],   // 归一化后的事件分类,多层结构
  N_SEVERITY: ["Integer"],      // 归一化后的严重级别,必填
  PHRASE: ["String"],           // 杀伤链阶段
  RAW_DATA: ["String"],         // 原始数据
  // 主体信息
  SBJ_TYPE: ["String"],         // 主体类型, 帐号,IP,进程等必填
  SBJ_NAME: ["String"],         // 主体名字
  SBJ_URI: ["String"],          // 主体URI
  SBJ_ROLE: ["String"],         // 主体角色
  SBJ_IP: ["Object", "Ip"],     // 主体IP
  SBJ_PORT: ["Integer"],        // 主体端口
  SBJ_HOST: ["String"],         // 主体的主机
  SBJ_OS: ["String"],           // 主体的操作系统
  SBJ_TRANS_IP: ["Object", "Ip"],// 主体地址转换后IP
  SBJ_TRANS_PORT: ["Integer"],  // 主体地址转换后端口
  SBJ_INTERFACE: ["String"],    // 主体的接口名
  SBJ_MAC: ["Object","Mac"],    // 主体的MAC
  SBJ_VLAN: ["String"],         // 主体VLAN,Zone等
  SBJ_SUBO_TYPE: ["String"],    // 主体的子对象类型，可以是CPU,Disk,Memory等
  SBJ_SUBO: ["String"],         // 主体的子对象，CPU序号，磁盘名,等
  SBJ_LONGITUDE:["Long"],       // 主体所在经度，若SBJ_IP不为NUL，系统自动填充
  SBJ_LATITUDE:["Long"],        // 主体所在纬度，若SBJ_IP不为NUL，系统自动填充
  SBJ_LOCATION: ["String"],     // 主体所在位置，若SBJ_IP不为NUL，系统自动填充，该值为多层结构
  SBJ_ISP: ["String"],          // 主体所在运营商，若SBJ_IP不为NUL，系统自动填充
  SBJ_TAG: ["String"],          // 主体标签
  // 行为
  BHV_CATEGORY: ["String"],     // 行为分类，多层结构
  BEHAVIOR: ["String"],         // 行为
  TRANS_PROTO: ["String"],      // 传输协议
  APP_PROTO: ["String"],        // 应用协议
  TECHNICAL: ["String"],        // 技术手段、原理
  BHV_BODY: ["String"],         // 行为内容,如：SQL语句等
  // 客体信息
  OBJ_TYPE: ["String"],         // 客体类型, 帐号,IP,进程,文件,域名等
  OBJ_NAME: ["String"],         // 客体名字
  OBJ_URI: ["String"],          // 客体的URI
  OBJ_ROLE: ["String"],         // 客体的角色
  OBJ_IP: ["Object", "Ip"],     // 客体的IP地址
  OBJ_PORT: ["Integer"],        // 客体的端口
  OBJ_HOST: ["String"],         // 客体的主机
  OBJ_OS: ["String"],           // 客体的操作系统
  OBJ_TRANS_IP: ["Object", "Ip"],// 客体地址映射后IP地址
  OBJ_TRANS_PORT: ["Integer"],  // 客体地址映射后端口
  OBJ_INTERFACE: ["String"],    // 客体接口名
  OBJ_MAC: ["Object","Mac"],    // 客体MAC地址
  OBJ_VLAN: ["String"],         // 客体VLAN,Zone等
  OBJ_SUBO_TYPE: ["String"],    // 客体的子对象类型，可以是CPU,Disk,Memory等
  OBJ_SUBO: ["String"],         // 客体的子对象，可以是CPU序号,Disk名等
  OBJ_LONGITUDE:["Long"],       // 客体所在经度，若OBJ_IP不为NUL，系统自动填充
  OBJ_LATITUDE:["Long"],        // 客体所在纬度，若OBJ_IP不为NUL，系统自动填充
  OBJ_LOCATION: ["String"],     // 客体所在位置，若OBJ_IP不为NUL，系统自动填充，多层结构
  OBJ_ISP: ["String"],          // 客体所在运营商，若OBJ_IP不为NUL，系统自动填充
  OBJ_TAG: ["String"],          // 客体标签
  // 主客体行为结果
  STATUS: ["String"],           // 结果状态，状态码值
  OUTCOME: ["String"],          // 结果描述
  // 防护
  PRT_ACTION: ["String"],       // 防护动作
  PRT_STATUS: ["String"],       // 防护效果状态
  PRT_EFFECT: ["String"],       // 防护效果描述
  CONFIDENCE: ["String"],       // 效果的置信度
  SOLUTION: ["String"],         // 防护建议，解决方案
  EVIDENCE: ["String"],         // 防护产生结果的证据
  // HTTP信息
  REQ_URL: ["String"],          // 请求的URL
  REQ_PARAMS: ["String"],       // 请求的参数
  REQ_USER_AGENT: ["String"],   // 请求所用浏览器
  REQ_COOKIE: ["String"],       // 请求时的COOKIE
  REQ_REFERER: ["String"],      // 请求引用
  REQ_XFF: ["String"],          // X-Forwarded-For,可以多个值
  REP_HTTP_SERVER: ["String"],  // 应答服务器的信息
  REP_CONTENT_TYPE: ["String"], // 应答内容的媒体类型
  REP_CONTENT_LENGTH: ["Integer"],  // 应答内容的长度
  // 邮件信息
  CC: ["String"],               // 抄送人
  BCC: ["String"],              // 密送人
  SUBJECT: ["String"],          // 主题
  ATTACHE: ["String"],          // 附件
  // Payload
  PLD_TYPE: ["String"],         // 负载的类型，恶意代码，文件，文本块，二进制块等
  PLD_NAME: ["String"],         // 负载的名字
  PLD_EXT_NAME: ["String"],     // 负载的扩展名
  PLD_VERSION: ["String"],      // 负载的版本
  PLD_SIZE: ["Integer"],        // 负载的大小
  PLD_CONTENT: ["String"],      // 负载的内容
  PLD_SIG:  ["String"],         // 签名、特征
  // 流量信息
  PACKETS_IN: ["Long"],         // 流入包的数量
  PACKETS_OUT: ["Long"],        // 流出包的数量
  BYTES_IN: ["Long"],           // 流入字节数
  BYTES_OUT: ["Long"]           // 流出字节数
}