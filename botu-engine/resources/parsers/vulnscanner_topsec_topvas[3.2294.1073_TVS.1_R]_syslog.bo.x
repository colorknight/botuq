botu("VulnScanner/Topsec/TopVAS[3.2294.1073_TVS.1_R]/Syslog") {
    splitParse(MS_SRC_DATA, ' ', '"', '"', "=");
    if(type == null){
        id = replaceString($1, "\"","");
        time = replaceString($3, "\"","");
        dev = replaceString($4, "\"","");
        pri = replaceString($5, "\"","");
        type = replaceString($6, "\"","");
        recorder = replaceString($7, "\"","");
    }
    time = extractTime(time);
    switch(type) {
        case "管理员配置日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/System";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/User";
				SBJ_NAME = user;
                SBJ_IP = src;
                BHV_CATEGORY = "/Update";
                OBJ_TYPE = "/Resource/Configuration";
                OBJ_NAME = "Configuration";
                OBJ_IP = MS_SRC_ADDRESS;
                OBJ_HOST = dev;
                OUTCOME = msg;
			}
        case "管理员登录日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/System";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/User";
				SBJ_NAME = user;
                SBJ_IP = src;
                BHV_CATEGORY = dictMapping("bhv_category", op);
                BEHAVIOR = op;
                TECHNICAL = method;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = MS_SRC_ADDRESS;
                OBJ_IP = MS_SRC_ADDRESS;
                OBJ_HOST = dev;
                OUTCOME = description;
			}
        case "管理员密码修改日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/System";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/User";
				SBJ_NAME = user;
                BHV_CATEGORY = "/Update";
                OBJ_TYPE = "/Resource/Account";
                OBJ_NAME = "Password";
                OBJ_IP = MS_SRC_ADDRESS;
                OBJ_HOST = dev;
                OUTCOME = description;
			}
        case "管理员增删日志":
            patternParse(description, "(?<method>[^ ]+) (?<role>[^ ]+) name (?<account>[^ ]+) success");
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/System";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/User";
				SBJ_NAME = user;
                BHV_CATEGORY = "/Update";
                BEHAVIOR = method;
                OBJ_TYPE = "/Resource/Account";
                OBJ_NAME = account;
                OBJ_IP = MS_SRC_ADDRESS;
                OBJ_HOST = dev;
                OBJ_ROLE = role;
                OUTCOME = description;
			}
        case "固件升级":
            objType = "/Resource/Fireware";
            objName = "Firmware";
        case "license升级":
            if (objName == null) {
                objType = "/Resource/License";
                objName = "License";
            }
        case "规则库升级":
            if (objName == null) {
                objType = "/Resource/Configuration";
                objName = "Rule";
            }
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/System";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/Host";
				SBJ_NAME = dev;
				SBJ_IP = MS_SRC_ADDRESS;
                BHV_CATEGORY = "/Update";
                OBJ_TYPE = objType;
                OBJ_NAME = objName;
                OUTCOME = msg;
			}
        case "CPU占用率日志":
            sbjType = "/Resource/CPU";
        case "内存占用率日志":
            if (sbjType == null) {
                sbjType = "/Resource/Memory";
            }
        case "磁盘占用率日志":
            if (sbjType == null) {
                sbjType = "/Resource/Disk";
            }
            percent = regExtract(msg, ".* (\\d+)%!$");
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/Status";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = sbjType;
				SBJ_HOST = dev;
				SBJ_IP = MS_SRC_ADDRESS;
                BHV_CATEGORY = "/Is";
                STATUS = percent;
                OUTCOME = msg;
			}
        case "服务监控日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit/Status";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/Host";
				SBJ_NAME = dev;
				SBJ_IP = MS_SRC_ADDRESS;
                BHV_CATEGORY = "/Is";
                OUTCOME = msg;
			}
        case "主机扫描日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = scanType;
                EVT_NAME = scanID;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                DURATION = scanTime;
                SBJ_TYPE = "/User";
                SBJ_NAME = userName;
                SBJ_IP = srcIPv4;
                SBJ_PORT = srcPort;
                BHV_CATEGORY = "/Scan/execute";
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dstIPv4;
                OBJ_IP = dstIPv4;
                OBJ_HOST = dstDomainName;
                OBJ_PORT = dstPort;
                STATUS = opStatus;
                OUTCOME = opsDesc;
			}
			//webray调整
        case "漏洞信息日志":
            SEVERITY_TEMP = toInt(toDouble(VulnCost));
            if(SEVERITY_TEMP >= 7 && SEVERITY_TEMP <= 10){
                temp = 4;
            }else if(SEVERITY_TEMP >= 4 && SEVERITY_TEMP <= 7){
                temp = 3;
            }else{
                temp = 2;
            }
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = VulnName;
                EVT_NAME = TVID;
                SEVERITY = pri;
                N_SEVERITY = temp;
                SBJ_TYPE = "/Ip";
                SBJ_NAME = srcIPv4;
                SBJ_IP = srcIPv4;
                SBJ_PORT = srcPort;
                BHV_CATEGORY = "/Scan";
                PHRASE = simpleMapping(scanType,"{系统扫描:sysvuln,web扫描:webvuln,口令猜测:crack}",null);
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dstIPv4;
                OBJ_IP = dstIPv4;
                OBJ_HOST = dstDomainName;
                OBJ_PORT = dstPort;
                STATUS = VulnCost;
                OUTCOME = VulnDesc;
                SOLUTION = VulnRepair;
			}
        case "黑名单日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_NAME = TaskName;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Violation";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/User";
				SBJ_NAME = UserName;
                BHV_CATEGORY = "/Update";
                OBJ_TYPE = "/Resource/Configuration";
                OUTCOME = comment;
			}
        case "域名解析日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_NAME = TaskName;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/User";
				SBJ_NAME = UserName;
                BHV_CATEGORY = "/Access";
                OBJ_TYPE = "/Service/Web";
                OBJ_HOST = host;
                OUTCOME = comment;
			}
        case "任务站点日志":
            patternParse(scanStieName, "(?<appProto>[^:]+)://(?<domainName>[^/]+)/");
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_NAME = scanTaskName;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/Host";
                SBJ_NAME = dev;
                SBJ_IP = MS_SRC_ADDRESS;
                SBJ_HOST = dev;
                BHV_CATEGORY = "/Scan/execute";
                APP_PROTO = appProto;
                TECHNICAL = templet;
                OBJ_TYPE = "/Service/Web";
                OBJ_HOST = domainName;
                OUTCOME = scanMessage;
                REQ_URL = scanUrl;
			}
        case "任务管理日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = scanType;
                EVT_NAME = scanName;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/User";
                SBJ_NAME = UserName;
                BHV_CATEGORY = strFormat("%s%s", operation, "/Audit");
                OUTCOME = message;
            }
        case "分布式配置日志":
            pack("event") {
                START_TIME = adjustTime(time,"+8H");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
				SBJ_TYPE = "/Host";
				SBJ_NAME = dev;
				SBJ_HOST = dev;
				SBJ_IP = host;
                BHV_CATEGORY = "/Update";
                OBJ_TYPE = "/Resource/Configuration";
                OUTCOME = message;
			}
        default:
            pack(){}
    }
}