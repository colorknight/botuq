botu("Firewall/Easynetworks/Firewall/Syslog") {
    if (patternParse(MS_SRC_DATA, "^\\<(?<pri>\\d+)\\>\\s*(?<detail>.*)")) {
        splitParse(detail, ' ', null, null, "=");
        switch(type){
            case "traffic":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     DURATION = duration;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit/Flow";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = srcname;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     TRANS_PROTO = dictMapping("trans_protocol",proto);
                     BHV_CATEGORY = "/Access";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dstname;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = status;
                     PLD_SIZE = sent;
                     PLD_CONTENT = sent_pkt;
                }
            case "event":
                pack("event") {
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/User";
                     SBJ_NAME = user;
                     TRANS_PROTO = dictMapping("trans_protocol",proto);
                     BHV_CATEGORY = "/Access";
                     BEHAVIOR = action;
                     STATUS = status;
                     OUTCOME = msg;
                }
            case "dlp":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = type;
                     EVT_TYPE = subtype;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Threat/Info/Leakage";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = src;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     TRANS_PROTO = dictMapping("trans_protocol",proto);
                     BHV_CATEGORY = "/Access";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dst;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = status;
                     PLD_SIZE = sent;
                     PLD_CONTENT = sent_pkt;
                     OUTCOME = msg;
                }
            case "app-ctrl":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     DURATION = duration;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = srcname;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     TRANS_PROTO = dictMapping("trans_protocol",proto);
                     BHV_CATEGORY = "/Access";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dstname;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = action;
                }
            case "virus":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     DURATION = duration;
                     EVT_CATEGORY = type;
                     EVT_TYPE = dtype;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Threat/Malware";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = src;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     BHV_CATEGORY = "/Attack";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dst;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = status;
                }
            case "ips":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     DURATION = duration;
                     EVT_CATEGORY = type;
                     EVT_TYPE = simpleMapping(subtype, "{signature:基于漏洞的攻击,anomaly:DDos}", signature);;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Threat/Attack";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = src;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     BHV_CATEGORY = "/Attack";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dst;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = status;
                     OUTCOME = msg;
                }
            case "email-filter":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = from;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     BHV_CATEGORY = "/Send";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = to;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = status;

                }
            case "web-filter":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = from;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     BHV_CATEGORY = "/Access";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = to;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     PRT_ACTION = status;

                }
            default:
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     OUTCOME = Message;
                }
        }

    }
}