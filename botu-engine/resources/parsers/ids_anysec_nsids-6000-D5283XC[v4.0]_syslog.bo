botu("IDS/AnySec/NSIDS-6000-D5283XC[v4.0]/Syslog") {
    tem1 = replaceString(MS_SRC_DATA,"\"","");
    if(patternParse(tem1,"%(?<time>[^:]+:\\d+:\\d+) (?<host>[^\\s]+) (?<module>[^/]+)/(?<logType>[^/]+)/(?<level>\\d+): (?<detail>.*)")) {
        keyPositionSplitParse(detail, ' ', ":");
        switch(logType){
            case "LOG_CM":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit/System";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = from;
                    SBJ_NAME = user;
                    TECHNICAL = type;
                    BEHAVIOR = action;
                    STATUS = result;
                    OUTCOME = msg;
                }
            case "LOG_FW_FILTER":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit/System";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = srcip;
                    SBJ_NAME = user;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Access";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                }
            case "LOG_SESSION":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit/Session";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = srcip;
                    SBJ_NAME = user;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Access";
                    BEHAVIOR = action;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                }
            case "LOG_DAF":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = type;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = srcip;
                    SBJ_NAME = user;
                    SBJ_PORT = sport;
                    BHV_CATEGORY = "/Access";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                    REQ_URL = domain;
                }
            case "LOG_FW_BLACKLIST":
            case "LOG_APPSEC_URLFILTER":
            case "LOG_APPSEC_DATAFILTER":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Violation";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    BHV_CATEGORY = "/Access";
                    TRANS_PROTO = proto;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                }
            case "LOG_APPSEC_FILECONTROL":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Access";
                    BEHAVIOR = protodir;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                    PLD_TYPE = "/File";
                    PLD_EXT_NAME = filetype;
                }
            case "LOG_FW_DEFEND":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = type;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Attack/DDos";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                }
            case "LOG_IPS":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = eventname;
                    EVT_NAME = cve;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Attack";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    SBJ_MAC = convert2Mac(srcmac);
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    OBJ_MAC = convert2Mac(dstmac);
                    PRT_ACTION = action;
                }
            case "LOG_IDS":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = eventname;
                    EVT_NAME = cve;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Attack";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    SBJ_MAC = convert2Mac(srcmac);
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    OBJ_MAC = convert2Mac(dstmac);
                    PRT_ACTION = action;
                }
            case "LOG_AS":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = eventname;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = srcip;
                    SBJ_NAME = from;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Send";
                    OBJ_TYPE = "/User";
                    OBJ_NAME = to;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                }
            case "LOG_AV":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = virusname;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Threat/Malware";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                    PLD_TYPE = "/File";
                    PLD_NAME = virusfilename;
                }
            case "":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = virustype;
                    EVT_NAME = virusname;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Threat/Malware";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                    PLD_TYPE = "/File";
                    PLD_NAME = filename;
                }
            case "LOG_APPSEC_TROJAN":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Threat/Malware/Trojan";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                }
            case "LOG_APPSEC_BOTNET":
                 pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = logType;
                     SEVERITY = subString(level,1);
                     N_EVT_CATEGORY = "/Threat/Malware/Botware";
                     N_SEVERITY = dictMapping("severity",subString(level,1));
                     SBJ_TYPE = "/Ip";
                     SBJ_IP = srcip;
                     SBJ_NAME = srcip;
                     SBJ_PORT = sport;
                     TRANS_PROTO = proto;
                     BHV_CATEGORY = "/Attack";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dstip;
                     OBJ_IP = dstip;
                     OBJ_PORT = dport;
                     PRT_ACTION = action;
                 }
            case "LOG_APPSEC_POLICY":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = policy_name;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Access";
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = app_name;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                    OUTCOME = msg;
                }
            case "LOG_WAF":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    EVT_TYPE = eventname;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Thread/Attack";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = srcip;
                    SBJ_NAME = srcip;
                    SBJ_PORT = sport;
                    TRANS_PROTO = proto;
                    BHV_CATEGORY = "/Attack";
                    BEHAVIOR = method;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = app_name;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                    PRT_ACTION = action;
                    OUTCOME = msg;
                }
            case "LOG_CLIENTAPP":
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = clientip;
                    SBJ_NAME = user;
                    BHV_CATEGORY = "/Access";
                    BEHAVIOR = method;
                    PRT_ACTION = action;
                    STATUS = result;
                    OUTCOME = msg;
                }
            case "LOG_THREATINTELL":
                 pack("event"){
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = host;
                     DS_PROCESS_NAME = module;
                     EVT_CATEGORY = logType;
                     EVT_TYPE = type;
                     EVT_NAME = incidentname;
                     SEVERITY = subString(level,1);
                     N_EVT_CATEGORY = "/Threat/Attack";
                     N_SEVERITY = dictMapping("severity",subString(level,1));
                     SBJ_TYPE = "/Ip";
                     SBJ_IP = srcip;
                     SBJ_NAME = srcip;
                     BHV_CATEGORY = "/Attack";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dstip;
                     OBJ_IP = dstip;
                     PRT_ACTION = action;
                 }
            case "LOG_HONEYPOT":
                  pack("event"){
                      START_TIME = extractTime(time);
                      DS_DVC_NAME = host;
                      DS_PROCESS_NAME = module;
                      EVT_CATEGORY = logType;
                      EVT_TYPE = action;
                      EVT_NAME = policyname;
                      SEVERITY = subString(level,1);
                      N_EVT_CATEGORY = "/Threat/Attack";
                      N_SEVERITY = dictMapping("severity",subString(level,1));
                      SBJ_TYPE = "/Ip";
                      SBJ_IP = srcip;
                      SBJ_NAME = srcip;
                      SBJ_PORT = sport;
                      BHV_CATEGORY = "/Attack";
                      OBJ_TYPE = "/Ip";
                      OBJ_NAME = dstip;
                      OBJ_IP = dstip;
                      OBJ_PORT = dport;
                  }
            default:
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = host;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = logType;
                    SEVERITY = subString(level,1);
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",subString(level,1));
                    SBJ_TYPE = "/User";
                    SBJ_IP = srcip;
                    SBJ_NAME = user;
                    SBJ_PORT = sport;
                    BHV_CATEGORY = "/Access";
                    TRANS_PROTO = proto;
                    BEHAVIOR = action;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dstip;
                    OBJ_IP = dstip;
                    OBJ_PORT = dport;
                }
        }
    }
}