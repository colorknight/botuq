// 天翼云StoneOS
botu("Firewall/ChinaTelecom/StoneOS[v5.5]/Syslog") {
	if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<time>[^:]+:\\d+:\\d+)\\s(?<dev>\\S+)\\s(?<id>\\S+)\\s(?<type>[^\\s]+):\\s{0,}(?<detail>.*)")) {
        sev = parseSyslogSeverity(pri);
        typeAry = strSplit(type,"@");
        infoType = typeAry[0];
        eventType = typeAry[1];
        switch(infoType){
            case "Event":
            case "Configuration":
            case "Operate":
            case "Debug":
            case "Sandbox":
            case "NetWork":
                pack("event") {
                    DS_DVC_NAME = dev;
                    DS_PROCESS_NAME = eventType;
                    START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                    EVT_CATEGORY = "/Audit";
                    EVT_TYPE = type;
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",sev);
                    OUTCOME = detail;
                }
            case "Security":
                if(patternParse(detail,"(?<attackType>[^:]+):(?<action>.*)!.* (?<sourceIp>\\d+.\\d+.\\d+.\\d+)-\\>\\s*(?<destinationIp>\\d+.\\d+.\\d+.\\d+).*")){
                    pack("event") {
                        DS_DVC_NAME = dev;
                        DS_PROCESS_NAME = eventType;
                        START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                        EVT_CATEGORY = "/Attack";
                        EVT_TYPE = type;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = sourceIp;
                        SBJ_IP = sourceIp;
                        BHV_CATEGORY = "/Attack";
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = destinationIp;
                        OBJ_IP = destinationIp;
                        PRT_ACTION = action;
                        OUTCOME = detail;
                    }
                }else if(patternParse(detail,"(?<attackType>[^:]+):(?<action>.*)! Source IP (?<srcIp>\\d+.\\d+.\\d+.\\d+).*")){
                    pack("event") {
                        DS_DVC_NAME = dev;
                        DS_PROCESS_NAME = eventType;
                        START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                        EVT_CATEGORY = "/Attack";
                        EVT_TYPE = type;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME= srcIp;
                        SBJ_IP = srcIp;
                        BHV_CATEGORY = "/Attack";
                        OBJ_TYPE = "/Host";
                        OBJ_HOST= "n/a";
                        OBJ_IP = convert2Ip("0.0.0.0");
                        PRT_ACTION = action;
                        OUTCOME = detail;
                    }
                }else if(patternParse(detail,"(?<attackType>[^:]+):(?<action>.*)! Destination IP (?<dstIp>\\d+.\\d+.\\d+.\\d+).*")){
                     pack("event") {
                         DS_DVC_NAME = dev;
                         DS_PROCESS_NAME = eventType;
                         START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                         EVT_CATEGORY = "/Attack";
                         EVT_TYPE = type;
                         SEVERITY = sev;
                         N_EVT_CATEGORY = "/Threat/Attack";
                         N_SEVERITY = dictMapping("severity",sev);
                         SBJ_TYPE = "/Host";
                         SBJ_NAME= "n/a";
                         SBJ_IP = convert2Ip("0.0.0.0");
                         BHV_CATEGORY = "/Attack";
                         OBJ_TYPE = "/Ip";
                         OBJ_NAME= dstIp;
                         OBJ_IP = dstIp;
                         PRT_ACTION = action;
                         OUTCOME = detail;
                     }
                }else if(patternParse(detail,"Failed to allocate memory for (?<reason>.*), the configuration can not take effect.")){
                    pack("event") {
                        DS_DVC_NAME = dev;
                        DS_PROCESS_NAME = eventType;
                        START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                        EVT_CATEGORY = "/Audit";
                        EVT_TYPE = type;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        OUTCOME = detail;
                    }
                }else if(patternParse(detail,"IP address (?<ip>\\d+.\\d+.\\d+.\\d+) on .*")){
                    pack("event") {
                        DS_DVC_NAME = dev;
                        DS_PROCESS_NAME = eventType;
                        START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                        EVT_CATEGORY = "/Audit";
                        EVT_TYPE = type;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        OUTCOME = detail;
                    }
                }else{
                    pack("event") {
                        DS_DVC_NAME = dev;
                        DS_PROCESS_NAME = eventType;
                        START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                        EVT_CATEGORY = "/Audit";
                        EVT_TYPE = type;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        OUTCOME = detail;
                    }
                }
            case "Threat":
                if(patternParse(detail,"From (?<srcIp>\\d+.\\d+.\\d+.\\d+):(?<srcPort>\\d+).* to (?<dstIp>\\d+.\\d+.\\d+.\\d+):(?<dstPort>\\d+).*, threat name: (?<threatName>.*), threat type: (?<threatType>.*), threat subtype: (?<subType>.*), App/Protocol: (?<app>.*)/(?<protocol>.*), action: (?<action>.*), defender: (?<defender>.*), severity: (?<logSeverity>[^,]+).*")){
                    pack("event") {
                        DS_DVC_NAME = dev;
                        DS_PROCESS_NAME = eventType;
                        START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                        EVT_CATEGORY = threatType;
                        EVT_TYPE = dictMapping("threat_type_hillstone",subType);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME= srcIp;
                        SBJ_IP = srcIp;
                        SBJ_PORT = srcPort;
                        BHV_CATEGORY = "/Attack";
                        TRANS_PROTO = protocol;
                        APP_PROTO = app;
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME= dstIp;
                        OBJ_IP = dstIp;
                        OBJ_PORT = dstPort;
                        PRT_ACTION = action;
                    }
                }else{
                     pack("event") {
                         DS_DVC_NAME = dev;
                         DS_PROCESS_NAME = eventType;
                         START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                         EVT_CATEGORY = "/Audit";
                         EVT_TYPE = type;
                         SEVERITY = sev;
                         N_EVT_CATEGORY = "/Audit";
                         N_SEVERITY = dictMapping("severity",sev);
                         OUTCOME = detail;
                     }
                }
            case "Traffic":
                 pack("event") {
                     DS_DVC_NAME = dev;
                     DS_PROCESS_NAME = eventType;
                     START_TIME = format2Time(time, "MMM dd HH:mm:ss");
                     EVT_CATEGORY = "/Audit";
                     EVT_TYPE = type;
                     SEVERITY = sev;
                     N_EVT_CATEGORY = "/Audit/Flow";
                     N_SEVERITY = dictMapping("severity",sev);
                     OUTCOME = detail;
                 }
            default:
                pack("event") {
                     DS_DVC_NAME = dev;
                     DS_PROCESS_NAME = eventType;
                     START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
                     EVT_CATEGORY = "/Audit";
                     EVT_TYPE = type;
                     SEVERITY = sev;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",sev);
                     OUTCOME = detail;
                }
        }
	}
}