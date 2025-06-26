botu("UTM/DBAPPSecurity/DAS-GateWay/Syslog") {
	if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<logTime>[^:]+:\\d+:\\d+) (?<host>\\S+);\\d+;\\S+;\\d+;\\s*(?<logType>[^:]+):(?<detail>.*)")) {
	   sev = parseSyslogSeverity(pri);
	   splitParse(detail, ';', null, null, "=");
       switch(logType){
          case "operate":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = logType;
                EVT_TYPE = "操作日志";
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/User";
                SBJ_NAME = operator_name;
                SBJ_IP = operate_ip;
                BHV_CATEGORY = managestyle;
                STATUS = result;
                OUTCOME = content;
             }
          case "ips":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = logType;
                EVT_TYPE = event_name;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/User";
                SBJ_NAME = user_name;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                SBJ_MAC = convert2Mac(src_mac);
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(dst_mac);
                STATUS = simpleMapping(attack_success, "{0:正在尝试攻击,1:攻击成功}", 0);
                PRT_ACTION = simpleMapping(action, "{drop:阻断,pass:放行}", "drop");
             }
          case "AV":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "AV日志";
                EVT_TYPE = virus_name;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack/Malware";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/User";
                SBJ_NAME = user_name;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                SBJ_MAC = convert2Mac(src_mac);
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                TECHNICAL = app_name;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(dst_mac);
                PRT_ACTION = simpleMapping(action, "{block:阻断,pass:允许}", "block");
             }
          case "security_ipmac":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "IP-MAC日志";
                EVT_TYPE = type;
                EVT_NAME = name;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
             }
          case "security_scan":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "扫描攻击防御日志";
                EVT_TYPE = type;
                EVT_NAME = name;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
             }
          case "security_flood":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "Flood攻击防御日志";
                EVT_TYPE = type;
                EVT_NAME = name;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
             }
          case "security_abnormal_pkt":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "异常报文攻击日志";
                EVT_TYPE = type;
                EVT_NAME = name;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
             }
          case "waf_ruledefend":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "Web规则防护日志";
                EVT_TYPE = defend_type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                BEHAVIOR = waf_method;
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
                REQ_URL = url;
                PRT_ACTION = action;
                OUTCOME = msg;
             }
          case "waf_advdefend":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "Web高级防护日志";
                EVT_TYPE = defend_type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                BEHAVIOR = waf_method;
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
                REQ_URL = url;
                PRT_ACTION = action;
                OUTCOME = msg;
             }
          case "wpd":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "弱密码防护日志";
                EVT_TYPE = pwd_type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/User";
                SBJ_NAME = login;
                SBJ_IP = src;
                BHV_CATEGORY = "/Attack";
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst;
                OBJ_IP = dst;
             }
          case "bfd":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "防爆力破解日志";
                EVT_TYPE = pwd_type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/User";
                SBJ_NAME = login;
                SBJ_IP = src;
                BHV_CATEGORY = "/Attack";
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst;
                OBJ_IP = dst;
                STATUS = simpleMapping(crack_success, "{0:未破解成功,1:破解成功}", 0);
                PRT_ACTION = simpleMapping(action, "{blist:加入黑名单,ignore:不加入黑名单}", "ignore");
             }
          case "servconn_policy":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "非法外联防护日志";
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Violation";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = server_addr;
                SBJ_IP = server_addr;
                BHV_CATEGORY = "/Access";
                TRANS_PROTO = proto;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = out_addr;
                OBJ_IP = out_addr;
                PRT_ACTION = simpleMapping(action, "{0:拒绝,1:允许}", 0);
             }
          case "behavior_model":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "行为模型日志";
                EVT_TYPE = defend_type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                BHV_CATEGORY = "/Attack";
                BEHAVIOR = waf_method;
                TRANS_PROTO = protocol;
                APP_PROTO = app_protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(mac);
                REQ_URL = url;
                PRT_ACTION = action;
                OUTCOME = msg;
             }
          case "ti":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "威胁情报日志";
                EVT_TYPE = ti_threat;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
             }
          default:
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "/Audit";
                EVT_TYPE = logType;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",sev);
                OUTCOME = detail;
             }
       }
	}else 	if (patternParse(MS_SRC_DATA, "(?<logTime>[^:]+:\\d+:\\d+) ([^\\s]+) ([^:]+:\\d+:\\d+)\\s*(?<host>\\S+);\\d+;\\S+;\\d+;\\s*(?<logType>[^:]+):(?<detail>.*)")) {
       splitParse(detail, ';', null, null, "=");
       switch(logType){
          case "edr_spread":
             pack("event"){
                START_TIME = extractTime(logTime);
                DS_DVC_NAME = host;
                DS_PROCESS_NAME = logType;
                EVT_CATEGORY = "EDR推广日志";
                SEVERITY = level;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",level);
                SBJ_TYPE = "/IP";
                SBJ_NAME = src_ip;
                SBJ_IP = src_ip;
                SBJ_PORT = src_port;
                SBJ_MAC = convert2Mac(src_mac);
                BHV_CATEGORY = "/Access";
                TRANS_PROTO = protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_ip;
                OBJ_IP = dst_ip;
                OBJ_PORT = dst_port;
                OBJ_MAC = convert2Mac(dst_mac);
                PRT_ACTION = simpleMapping(action, "{0:允许,1:拒绝}", 0);
                OUTCOME = simpleMapping(desc_id, "{0:丢弃报文,1:丢弃报文并重定向}", 0);
             }
       }

	}
}