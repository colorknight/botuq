botu("AntiDos/DPtech/Probe3000/Syslog") {
    if (patternParse(MS_SRC_DATA, ".* (?<time>\\d+-\\d+-\\d+ [^ ]+) (?<devname>[^ ]+) %%--[^/]+/(?<procName>[^/]+)/(?<sev>\\d+)/[^\\)]+\\): (?<detail>.*)")) {
        splitParse(detail, ';', null, null, ":");
        op = regExtract(User, "[^\\)]+\\) ([^\\.]+)");
        pack("event") {
            START_TIME = format2Time(time, "MMM d HH:mm:ss yyyy");
            DS_DVC_NAME = devname;
            DS_PROCESS_NAME = procName;
            EVT_CATEGORY = "OPERLOG";
            EVT_TYPE = field("client-type(84)");
            SEVERITY = sev;
            N_EVT_CATEGORY = "/Audit";
            N_SEVERITY = dictMapping("severity",sev);
            SBJ_TYPE = "/User";
            SBJ_NAME = field("user-name(85)");
            SBJ_IP = field("host-ip(86)");
            BEHAVIOR = op;
            BHV_CATEGORY = dictMapping("bhv_category",op);
            OBJ_TYPE = "/Ip";
            OBJ_NAME = MS_SRC_ADDRESS;
            OBJ_IP = MS_SRC_ADDRESS;
            OUTCOME = User;
        }
    } else {
	    patternParse(MS_SRC_DATA, "(?<time>.*) (?<devname>[^ ]+) %%[^/]+/(?<procName>[^/]+)/(?<sev>\\d+)/[^:]+: (?<detail>.*)");
        patternSplitParse(detail, ";``", ":");
        appProto = regExtract(field("protocol-name"), "[^)]+\\)(.*)");
        logtype = field("log-type");
        switch(logtype) {
            case "attack-protect":
                sip = regExtract(field("source-ip"), "\\d-([0-9a-fA-F]+)");
                dip = regExtract(field("destination-ip"), "\\d-([0-9a-fA-F]+)");
                sip = convert2Ip(toLong(sip,16));
                dip = convert2Ip(toLong(dip,16));
                attackName = regExtract(field("attack-name"), "[^)]+\\)(.*)");
                pack("event") {
                    START_TIME = format2Time(time, "MMM d HH:mm:ss yyyy");
                    DS_DVC_NAME = devname;
                    DS_PROCESS_NAME = procName;
                    EVT_CATEGORY = logtype;
                    EVT_TYPE = attackName;
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Threat/Attack";
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = sip;
                    SBJ_IP = sip;
                    SBJ_PORT = field("source-port");
                    SBJ_INTERFACE = field("ifname-inside");
                    BHV_CATEGORY = "/Attack";
                    TECHNICAL = attackName;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(field("ip-proto-id")));
                    APP_PROTO = appProto;
                    OBJ_TYPE = "/Ip";
                    OBJ_NAME = dip;
                    OBJ_IP = dip;
                    OBJ_PORT = field("destination-port");
                    PRT_ACTION = event;
                    PRT_STATUS = "/Solved";
                    PLD_TYPE = "/Binary";
                    PLD_CONTENT = packet;
                }
            case "av-protect":
                avName = regExtract(field("av-name"), "[^)]+\\)(.*)");
                pack("event") {
                    START_TIME = format2Time(time, "MMM d HH:mm:ss yyyy");
                    DS_DVC_NAME = devname;
                    DS_PROCESS_NAME = procName;
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
                    PRT_ACTION = event;
                    PRT_STATUS = "/Solved";
                }
        }
	}
}