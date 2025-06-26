botu("EDR/HuorongSec/EDR/Warn") {
    if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<time>[^:]+:\\d+:\\d+) (?<host>[^\\s]+) (?<type>[^:]+): \\d+: (?<descript>[^:]+) (?<detail>.*)")) {
        keyPositionSplitParse(detail, ' ', ": ");
        sev = parseSyslogSeverity(pri);
        pack("event"){
            START_TIME = extractTime(time);
            DS_DVC_NAME = host;
            EVT_CATEGORY = "/Audit";
            EVT_TYPE = type;
            N_EVT_CATEGORY = "/Audit";
            SEVERITY = sev;
            N_SEVERITY = dictMapping("severity",sev);
            OUTCOME = descript;
        }
    }else if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<time>[^\\s]+) (?<host>[^\\s]+) (?<type>[^:]+): (?<detail>.*)")) {
        jsonParse(detail,true);
        sev = parseSyslogSeverity(pri);
        switch(event_type):
            case "病毒查杀":
            case "U盘保护":
            case "下载保护":
            case "Web扫描":
                 pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.recname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Thread/Malware/Vurus";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.client_name");
                     SBJ_IP = field("event.ip_addr");
                     SBJ_MAC = convert2Mac(field("event.mac"));
                     SBJ_OS = field("event.os_type");
                     BHV_CATEGORY = "/Exists";
                     OBJ_TYPE = "/Malware/Virus";
                     OBJ_NAME = field("evnet.recname");
                     PRT_ACTION = field("event.result_view");
                     PRT_ACTION = field("event.result");
                     OUTCOME = field("event.product");
                 }
            case "黑客入侵拦截":
            case "横向渗透防护":
            case "Web服务保护":
            case "僵尸网络防护":
                dst = strSplit(field("event.laddr"), ":");
                src = strSplit(field("event.raddr"), ":");
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.detection");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Thread/Attack";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = src[0];
                     SBJ_IP = src[0];
                     BHV_CATEGORY = "/Attack";
                     TRANS_PROTO = protocol;
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dst[0];
                     OBJ_IP = dst[0];
                     OBJ_PORT = dst[1];
                     PRT_ACTION = field("event.blocked");
                }
            case "文件实时监控":
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.recname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Thread/Malware";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.ip_addr");
                     SBJ_IP = field("event.ip_addr");
                     BHV_CATEGORY = "/Exists";
                     OBJ_TYPE = "/Malware/Virus";
                     OBJ_NAME = field("evnet.recname");
                     PRT_ACTION = field("event.result");
                }
            case "恶意行为监控":
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.recname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Thread/Malware";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.ip_addr");
                     SBJ_IP = field("event.ip_addr");
                     BHV_CATEGORY = "/Execute";
                     OBJ_TYPE = "/Malware/Virus";
                     OBJ_NAME = field("evnet.recname");
                     PRT_ACTION = field("event.result_view");
                }
            case "恶意网站拦截":
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.clsname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Threat/MalResource";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.ip_addr");
                     SBJ_IP = field("event.ip_addr");
                     BHV_CATEGORY = "/Access";
                     TECHNICAL = field("event.procname");
                     OBJ_TYPE = "/Resources";
                     OBJ_NAME = field("evnet.url");
                     PRT_ACTION = field("event.blocked");
                }
            case "IP黑名单":
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.clsname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Threat/MalResource";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.ip_addr");
                     SBJ_IP = field("event.ip_addr");
                     BHV_CATEGORY = "/Access";
                     TECHNICAL = field("event.procname");
                     OBJ_TYPE = "/Resources";
                     OBJ_NAME = field("evnet.raddr");
                     OBJ_IP = field("evnet.raddr");
                     PRT_ACTION = field("event.blocked");
                }
            case "IP协议控制":
                src = strSplit(field("event.laddr"), ":");
                dst = strSplit(field("event.raddr"), ":");
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.clsname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Threat/MalResource";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.client_name");
                     SBJ_IP = src[0];
                     SBJ_PORT = src[1];
                     BHV_CATEGORY = "/Threat/Attack";
                     TRANS_PROTO = field("event.protocol");
                     TECHNICAL = field("event.procname");
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dst[0];
                     OBJ_IP = dst[0];
                     OBJ_PORT = dst[1];
                     PRT_ACTION = field("event.blocked");
                }
            case "邮件监控":
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.clsname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Audit";
                     SBJ_TYPE = "/User";
                     SBJ_NAME = field("event.mail_from");
                     SBJ_IP = field("event.ip_addr");
                     SBJ_MAC = field("event.mac");
                     BHV_CATEGORY = "/Send";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = field("evnet.mail_to");
                     PRT_ACTION = field("event.result_view");
                }
            case "对外攻击拦截":
                dst = strSplit(field("event.raddr"), ":");
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.clsname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Threat/Attack";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = field("event.client_name");
                     SBJ_IP = field("event.ip_addr");
                     SBJ_MAC = field("event.mac");
                     BHV_CATEGORY = "/Attack";
                     TRANS_PROTO = field("event.protocol");
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dst[0];
                     OBJ_IP = dst[0];
                     OBJ_PORT = dst[1];
                     PRT_ACTION = field("event.blocked");
                }
            case "暴破攻击防护":
                dst = strSplit(field("event.laddr"), ":");
                src = strSplit(field("event.raddr"), ":");
                pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     SEVERITY = sev;
                     EVT_CATEGORY = event_type;
                     EVT_TYPE = field("event.clsname");
                     N_SEVERITY = dictMapping("severity",sev);
                     N_EVT_CATEGORY = "/Threat/Attack";
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = src[0];
                     SBJ_IP = src[0];
                     SBJ_PORT = src[1];
                     BHV_CATEGORY = "/Attack";
                     TRANS_PROTO = field("event.protocol");
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = field("event.client_name");
                     OBJ_IP = dst[0];
                     OBJ_PORT = dst[1];
                     PRT_ACTION = field("event.blocked");
                }
            case "系统加固":
            case "应用加固":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    SEVERITY = sev;
                    N_SEVERITY = dictMapping("severity",sev);
                    N_EVT_CATEGORY = "/Audit/System";
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = ip_addr;
                    SBJ_NAME = client_name;
                    SBJ_MAC = convert2Mac(mac);
                    BHV_CATEGORY = "/Update";
                    OBJ_TYPE = "/Resources";
                    TECHNICAL = procname;
                    PRT_ACTION = field("event.result_view");
                }
            case "软件安装":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    SEVERITY = sev;
                    N_SEVERITY = dictMapping("severity",sev);
                    N_EVT_CATEGORY = "/Audit/System";
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = ip_addr;
                    SBJ_NAME = client_name;
                    SBJ_MAC = convert2Mac(mac);
                    BHV_CATEGORY = "/Install";
                    OBJ_TYPE = "/Resources";
                    OBJ_NAME = recname;
                    PRT_ACTION = field("event.result_view");
                }
            case "升级日志":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    SEVERITY = sev;
                    N_SEVERITY = dictMapping("severity",sev);
                    N_EVT_CATEGORY = "/Audit/System";
                    PRT_ACTION = field("event.result");
                    PRT_ACTION = field("event.result_view");
                    OUTCOME = field("event.product");
                }
    }else  if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<time>[^:]+:\\d+:\\d+) (?<host>[^\\s]+) (?<detail>.*)")) {
         pack("event"){
             START_TIME = extractTime(time);
             DS_DVC_NAME = host;
             EVT_CATEGORY = "/Audit";
             N_EVT_CATEGORY = "/Audit";
             SEVERITY = sev;
             N_SEVERITY = dictMapping("severity",sev);
             OUTCOME = detail;
         }
    }
}