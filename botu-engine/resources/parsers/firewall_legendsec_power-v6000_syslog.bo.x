botu("Firewall/LegendSec/Power-V6000/Syslog") {
    logData = MS_SRC_DATA;
    inx = indexString(logData, "devid=");
    if (inx != 0) {
        logData = subString(logData, inx);
    }
	splitParse(logData, ' ', '"', '"', "=");
	switch(logtype) {
        case "1":
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = mod;
                EVT_TYPE = "丢弃的数据包信息";
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = sa;
                SBJ_IP = sa;
                SBJ_PORT = sport;
                SBJ_MAC = convert2Mac(smac);
                BHV_CATEGORY = "/Access";
                TRANS_PROTO = proto;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = da;
                OBJ_IP = da;
                OBJ_PORT = dport;
                OBJ_MAC = convert2Mac(dmac);
                PRT_ACTION = policy;
                PACKETS_IN = sent;
                PACKETS_OUT = rcvd;
            }
        case "2":
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = mod;
                EVT_TYPE = "代理日志";
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = sa;
                SBJ_IP = sa;
                SBJ_PORT = sport;
                SBJ_MAC = convert2Mac(smac);
                BHV_CATEGORY = "/Access";
                BEHAVIOR = act;
                TRANS_PROTO = proto;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = da;
                OBJ_IP = da;
                OBJ_PORT = dport;
                OBJ_MAC = convert2Mac(dmac);
                REQ_URL = url;
                OUTCOME = msg;
                STATUS = result;
                PRT_ACTION = policy;
                PACKETS_IN = sent;
                PACKETS_OUT = rcvd;
            }
        case "3":
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = mod;
                EVT_TYPE = "代理日志";
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                BEHAVIOR = act;
                OUTCOME = dsp_msg;
                STATUS = result;
            }
        case "5":
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = mod;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = username;
                SBJ_IP = ip;
                BHV_CATEGORY = act;
                BEHAVIOR = act;
                OUTCOME = dsp_msg;
                STATUS = result;
            }
        case "7":
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = eventtype;
                EVT_TYPE = eventname;
                SEVERITY = severity;
                N_EVT_CATEGORY = "/Threat/Malware/Virus";
                N_SEVERITY = dictMapping("severity",severity);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = srcaddr;
                SBJ_IP = srcaddr;
                SBJ_PORT = srcport;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = protocol;
                APP_PROTO = app;
                BEHAVIOR = act;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = destaddr;
                OBJ_IP = destaddr;
                OBJ_PORT = destport;
                OUTCOME = dsp_msg;
                STATUS = result;
                PRT_ACTION = action;
            }
        case "12":
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = eventtype;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Threat/Info/Spam";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = srcip;
                SBJ_IP = srcip;
                SBJ_PORT = srcport;
                BHV_CATEGORY = "/Access";
                TRANS_PROTO = protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dstip;
                OBJ_IP = dstip;
                OBJ_PORT = destport;
                OUTCOME = dsp_msg;
            }
            case "14":
            case "23":
            case "32":
            case "33":
                pack("event") {
                    DS_DVC_NAME = dname;
                    START_TIME = extractTime(date);
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = eventtype;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Threat/Malware/Virus";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = srcaddr;
                    SBJ_IP = srcaddr;
                    SBJ_PORT = srcport;
                    BHV_CATEGORY = "/Attack";
                    TRANS_PROTO = protocol;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = destaddr;
                    OBJ_IP = destaddr;
                    OBJ_PORT = destport;
                    OUTCOME = dsp_msg;
                }
            case "16":
            case "24":
            case "27":
            case "29":
                pack("event") {
                    DS_DVC_NAME = dname;
                    START_TIME = extractTime(date);
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = eventtype;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Threat/Attack";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = srcaddr;
                    SBJ_IP = srcaddr;
                    SBJ_PORT = srcport;
                    BHV_CATEGORY = "/Attack";
                    TRANS_PROTO = protocol;
                    APP_PROTO = app;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = destaddr;
                    OBJ_IP = destaddr;
                    OBJ_PORT = destport;
                    OUTCOME = dsp_msg;
                    PRT_ACTION = action;
                }
            case "18":
                pack("event") {
                    DS_DVC_NAME = dname;
                    START_TIME = extractTime(date);
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = eventtype;
                    EVT_TYPE = eventname;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Violation";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = srcaddr;
                    SBJ_IP = srcaddr;
                    SBJ_PORT = srcport;
                    BHV_CATEGORY = "/Attack";
                    TRANS_PROTO = protocol;
                    APP_PROTO = app;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = destaddr;
                    OBJ_IP = destaddr;
                    OBJ_PORT = destport;
                    OUTCOME = dsp_msg;
                    PRT_ACTION = action;
                }
            case "20":
                pack("event") {
                    DS_DVC_NAME = dname;
                    START_TIME = extractTime(date);
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = eventtype;
                    EVT_TYPE = eventname;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Threat/Attack/Scan";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = srcaddr;
                    SBJ_IP = srcaddr;
                    SBJ_PORT = srcport;
                    BHV_CATEGORY = "/Attack";
                    TRANS_PROTO = protocol;
                    APP_PROTO = app;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = destaddr;
                    OBJ_IP = destaddr;
                    OBJ_PORT = destport;
                    OUTCOME = dsp_msg;
                    PRT_ACTION = action;
                }
            case "22":
                pack("event") {
                    DS_DVC_NAME = dname;
                    START_TIME = extractTime(date);
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = level;
                    EVT_TYPE = family;
                    EVT_ID = pluginid;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Vulnerability";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = hostip;
                    SBJ_IP = hostip;
                    BHV_CATEGORY = "/Exists";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = destaddr;
                    OBJ_IP = destaddr;
                    OBJ_PORT = destport;
                    OUTCOME = dsp_msg;
                    PRT_ACTION = action;
                }
        default:
            pack("event") {
                DS_DVC_NAME = dname;
                START_TIME = extractTime(date);
                DS_PROCESS_NAME = module;
                EVT_CATEGORY = mod;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                OUTCOME = dsp_msg;
            }
        }
}