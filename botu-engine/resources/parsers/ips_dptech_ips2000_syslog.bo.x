botu("IPS/DPtech/IPS2000/Syslog") {
    if (patternParse(MS_SRC_DATA, "^.*(?<dev>\\d+\\.\\d+\\.\\d+\\.\\d+) (?<time>\\d+-\\d+-\\d+ \\d+:\\d+:\\d+) .*%%.*/(?<module>[^/]+)/(?<sev>\\d+)/[^\\)]+\\): (?<msg>.*)")) {
        switch(module) {
            case "DEVM":
                if (patternParse(msg, "The use of cpu is too high:\\s+(?<percent>\\d+).*")) {
                    sbjType = "/Resource/CPU";
                    nCat = "/HiddenDanger";
                } else if (patternParse(msg, "The use of memory is too high:\\s+(?<percent>\\d+).*")) {
                    sbjType = "/Resource/Memory";
                    nCat = "/HiddenDanger";
                } else if (patternParse(msg, "The .* will be full:\\s+(?<percent>\\d+).*")) {
                    sbjType = "/Resource/Disk";
                    nCat = "/HiddenDanger";
                } else if (patternParse(msg, "The temperature of CPU.*")) {
                    sbjType = "/Resource/CPU";
                    nCat = "/Audit/Status";
                } else {
                    sbjType = "/Resource/Hardware";
                    nCat = "/Audit/Status";
                }
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = "/Status";
                    N_EVT_CATEGORY = "/Audit/Status";
                    SEVERITY = sev;
                    N_EVT_CATEGORY = nCat;
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = sbjType;
                    SBJ_HOST = dev;
                    SBJ_IP = MS_SRC_ADDRESS;
                    BHV_CATEGORY = "/Is";
                    STATUS = percent;
                    OUTCOME = msg;
                }
            case "SNMP":
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = "/Audit/System";
                    N_EVT_CATEGORY = "/Audit/System";
                    SEVERITY = sev;
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Host";
                    SBJ_HOST = dev;
                    SBJ_IP = MS_SRC_ADDRESS;
                    OUTCOME = msg;
                }
            case "ifmd":
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = "/Status";
                    N_EVT_CATEGORY = "/Audit/Status";
                    SEVERITY = sev;
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Resource/Interface";
                    SBJ_HOST = dev;
                    SBJ_IP = MS_SRC_ADDRESS;
                    BHV_CATEGORY = "/Is";
                    OUTCOME = msg;
                }
            case "WEB":
                if (patternParse(msg, ".*\\(87\\):(?<result>\\d+);User \\[(?<user>[^\\]]+)\\][^\\[]+\\[(?<src>[^\\]]+).*")) {
                    bhv = "/Login";
                } else if (patternParse(msg, ".*\\(87\\):(?<result>\\d+);User: \\[(?<user>[^\\]]+)\\] \\(IP address: (?<src>[^\\s]+) \\).*")) {
                    bhv = "/Logout";
                }
                pack("event"){
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    DS_PROCESS_NAME = module;
                    EVT_CATEGORY = "/Audit/System";
                    N_EVT_CATEGORY = "/Audit/System";
                    SEVERITY = sev;
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/User";
                    SBJ_NAME = user;
                    SBJ_IP = src;
                    BHV_CATEGORY = bhv;
                    OBJ_TYPE = "/Host";
                    OBJ_HOST = dev;
                    OBJ_IP = MS_SRC_ADDRESS;
                    STATUS = result;
                    OUTCOME = msg;
                }
        }
    } else if (patternParse(MS_SRC_DATA, "^\\<(?<pri>\\d+)\\>(?<time>\\w+\\s+\\d+ \\d+:\\d+:\\d+ \\d*) (?<dev>[^\\s]+) %%.*/(?<module>[^/]+)/\\d/[^\\)]+\\): (?<msg>.*)")) {
        msg = replaceString(msg, ";``", ";");
        splitParse(msg, ';',null,null,":");
        appProto = regExtract(field("protocol-name"), "[^)]+\\)(.*)");
        logtype = field("log-type");
        switch(logtype) {
            case "attack-protect":
                code = toInt(regExtract(field("attack-name"), "\\((\\d+)\\).*"));
                codeValue = code >> 26 & 0x03;
                nSev = simpleMapping(codeValue, "{'0':2,'1':3,'2':4,'3':5}", 0);
                attackName = regExtract(field("attack-name"), "[^)]+\\)(.*)");
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    EVT_CATEGORY = logtype;
                    EVT_NAME = attackName;
                    EVT_TYPE = strFormat("%s/%s", field("attack-period"), field("attack-type"));
                    N_EVT_CATEGORY = "/Threat/Attack";
                    N_SEVERITY = nSev;
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = field("source-ip");
                    SBJ_IP = field("source-ip");
                    SBJ_PORT = field("source-port");
                    SBJ_INTERFACE = field("ifname-inside");
                    SBJ_MAC = field("src-mac");
                    BHV_CATEGORY = "/Attack";
                    TECHNICAL = attackName;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(field("ip-proto-id")));
                    APP_PROTO = appProto;
                    BHV_BODY = packet;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = field("destination-ip");
                    OBJ_IP = field("destination-ip");
                    OBJ_PORT = field("destination-port");
                    OBJ_INTERFACE = field("ifname-outside");
                    OBJ_MAC = field("dst-mac");
                    REQ_URL = uri;
                    PRT_ACTION = event;
                    PRT_STATUS = "/Solved";
                }
            case "av-protect":
                avName = regExtract(field("av-name"), "[^)]+\\)(.*)");
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    EVT_CATEGORY = logtype;
                    EVT_TYPE = avName;
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Threat/Malware";
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = field("source-ip");
                    SBJ_IP = field("source-ip");
                    SBJ_PORT = field("source-port");
                    SBJ_INTERFACE = field("ifname-inside");
                    BHV_CATEGORY = "/Attack";
                    TECHNICAL = avName;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(ip-proto-id));
                    APP_PROTO = appProto;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = field("destination-ip");
                    OBJ_IP = field("destination-ip");
                    OBJ_PORT = field("destination-port");
                    OBJ_INTERFACE = field("ifname-outside");
                    PRT_ACTION = event;
                    PRT_STATUS = "/Solved";
                }
            case "sensitive-application":
            case "sensitive-information":
                attackName = regExtract(field("attack-name"), "[^)]+\\)(.*)");
                sip = field("source-ip");
                sip = subString(sip, 2);
                sip = toLong(sip, 16);
                dip = field("destination-ip");
                dip = subString(dip, 2);
                dip = toLong(dip, 16);
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    EVT_CATEGORY = logtype;
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Threat/Attack";
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = sip;
                    SBJ_NAME = sip;
                    SBJ_PORT = field("source-port");
                    SBJ_INTERFACE = field("ifname-inside");
                    SBJ_MAC = field("src-mac");
                    BHV_CATEGORY = "/Attack";
                    TECHNICAL = attackName;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(field("ip-proto-id")));
                    APP_PROTO = appProto;
                    OBJ_TYPE = "/Ip";
                    OBJ_IP = dip;
                    OBJ_NAME = OBJ_IP;
                    OBJ_PORT = field("destination-port");
                    PRT_ACTION = event;
                    PRT_STATUS = "/Solved";
                }
            case "av-file":
                pack("event") {
                    START_TIME = extractTime(time);
                    DS_DVC_NAME = dev;
                    EVT_CATEGORY = logtype;
                    EVT_TYPE = field("virus-name");
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Threat/Malware/Virus";
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Ip";
                    SBJ_IP = field("source-ip");
                    SBJ_NAME = field("source-ip");
                    SBJ_NAME = field("mail-sender");
                    SBJ_PORT = field("source-port");
                    BHV_CATEGORY = "/Attack";
                    TRANS_PROTO = field("basic-protocol");
                    APP_PROTO = field("app-protocol");
                    OBJ_TYPE = "/Ip";
                    OBJ_IP = field("destination-ip");
                    OBJ_NAME = field("destination-ip");
                    OBJ_NAME = field("mail-receiver");
                    OBJ_PORT = field("destination-port");
                    PRT_ACTION = event;
                    CC = field("mail-cc");
                    SUBJECT = field("mail-subject");
                }
        }
    }
}