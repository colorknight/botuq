botu("WAF/DPtech/WAF3000/Syslog") {
	if (patternParse(MS_SRC_DATA, "(\\<\\d+\\>)*(?<logTime>.*) (?<host>[^\\s]+) %%(?<deviceType>[^/]+)/(?<module>[^/]+)/(?<sev>\\d+)/(?<logType>[^()]+)[^:]+: (?<detail>.*)")) {
        switch(logType){
            case "SYSLOG":
                pack("event") {
                    START_TIME = format2Time(logTime, "yyyy-MM-dd HH:mm:ss");
                    DS_DVC_NAME = deviceType;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = "/Audit";
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Audit/System";
                    N_SEVERITY = dictMapping("severity",sev);
                    OUTCOME = detail;
                }
            case "OPERLOG":
                splitParse(detail, ';', null, null, ":");
                if(module == "WEB"){
                    pack("event"){
                        START_TIME = format2Time(logTime, "yyyy-MM-dd HH:mm:ss");
                        DS_DVC_NAME = deviceType;
                        DS_PROCESS_NAME = module;
                        EVT_CATEGORY = "/Audit";
                        EVT_TYPE = field("client-type(84)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("user-name(85)");
                        SBJ_IP = field("host-ip(86)");
                        STATUS = field("error-code(87)");
                    }
                }else{
                    pack("event") {
                        START_TIME = format2Time(logTime, "yyyy-MM-dd HH:mm:ss");
                        DS_DVC_NAME = deviceType;
                        DS_PROCESS_NAME = module;
                        EVT_CATEGORY = "/Audit";
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit/System";
                        N_SEVERITY = dictMapping("severity",sev);
                        OUTCOME = detail;
                    }
                }
            case "SRVLOG":
                patternSplitParse(detail, ";``", ":");
                if(module == "AUDIT"){
                    pack("event") {
                        START_TIME = format2Time(logTime, "yyyy-MM-dd HH:mm:ss");
                        DS_DVC_NAME = deviceType;
                        DS_PROCESS_NAME = module;
                        EVT_CATEGORY = "/Audit";
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = field("source-ip");
                        SBJ_IP = field("source-ip");
                        SBJ_HOST = host;
                        SBJ_PORT = field("source-port");
                        BHV_CATEGORY = "/Access";
                        BEHAVIOR = method;
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("destination-ip");
                        OBJ_IP = field("destination-ip");
                        OBJ_PORT = field("destination-port");
                        REQ_URL = url;
                        REQ_USER_AGENT = useragent;
                        REQ_REFERER = referer;
                        REQ_COOKIE = cookies;
                    }
                }else if(module == "WEBPAGE-DEFEND"){
                    patternSplitParse(detail, ";``", ":");
                    pack("event") {
                        START_TIME = format2Time(logTime, "yyyy-MM-dd HH:mm:ss");
                        DS_DVC_NAME = deviceType;
                        DS_PROCESS_NAME = module;
                        EVT_CATEGORY = "/Threat/Info/Interpolation";
                        EVT_TYPE = field("event-type");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Info/Interpolation";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Uri";
                        SBJ_NAME = url;
                        BHV_CATEGORY = "/Changed";
                    }
                }else if(module == "WEBATTACK"){
                    patternSplitParse(detail, ";``", ":");
                    pack("event") {
                        START_TIME = format2Time(logTime, "yyyy-MM-dd HH:mm:ss");
                        DS_DVC_NAME = deviceType;
                        DS_PROCESS_NAME = module;
                        EVT_CATEGORY = dictMapping("attack_type_dptech",att_level2);
                        EVT_TYPE = dictMapping("attack_type_dptech",att_level3);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = field("source-ip");
                        SBJ_IP = field("source-ip");
                        SBJ_HOST = host;
                        SBJ_PORT = field("source-port");
                        BHV_CATEGORY = "/Attack";
                        BEHAVIOR = method;
                        TRANS_PROTO = protocol;
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("destination-ip");
                        OBJ_IP = field("destination-ip");
                        OBJ_PORT = field("destination-port");
                        REQ_URL = url;
                        PRT_ACTION = simpleMapping(action, "{alert:告警,block:阻断,push:推送,redirect:重定向,other:其他}", null);
                        OUTCOME = description;
                    }
                }

        }
	}
}