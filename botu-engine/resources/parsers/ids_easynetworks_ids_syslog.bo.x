botu("IDS/Easynetworks/IDS/Syslog") {
	if (patternParse(MS_SRC_DATA, "\\<\\d+\\>(?<detail>.*)")) {
	    splitParse(detail, ' ','"','"',"=");
	    switch(type){
	        case "traffic":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
                     DURATION = duration;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit/Flow";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = srcname;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     SBJ_MAC = convert2Mac(src_mac);
                     TRANS_PROTO = dictMapping("trans_protocol",proto);
                     BHV_CATEGORY = "/Access";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dstname;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     OBJ_MAC = convert2Mac(dst_mac);
                     PRT_ACTION = status;
                     PACKETS_IN = sent_pkt;
                     PACKETS_OUT = rcvd_pkt;
                     BYTES_IN = sent;
                     BYTES_OUT = rcvd;
                }
            case "ips":
                if(subtype == "signature") {
                    entType = "基于漏洞的攻击";
                } else {
                    entType = "DDos";
                }
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
                     EVT_CATEGORY = entType;
                     EVT_TYPE = attack_id;
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
            case "icproto":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
                     EVT_CATEGORY = type;
                     EVT_TYPE = icp_eventname;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = icp_visip;
                     SBJ_IP = icp_visip;
                     BHV_CATEGORY = icp_funcid;
                     PRT_ACTION = action;
                }
            case "event":
                pack("event") {
                     START_TIME = extractTime(strFormat("%s %s", date, time));
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
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
	    }
	}else if(patternParse(MS_SRC_DATA, "(?<time>[^:]+:\\d+:\\d+) (?<detail>.*)")){
        splitParse(detail, ' ','"','"',"=");
	    switch(type){
	        case "traffic":
                pack("event") {
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
                     DURATION = duration;
                     EVT_CATEGORY = type;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit/Flow";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = srcname;
                     SBJ_IP = src;
                     SBJ_PORT = src_port;
                     SBJ_MAC = convert2Mac(src_mac);
                     TRANS_PROTO = dictMapping("trans_protocol",proto);
                     BHV_CATEGORY = "/Access";
                     OBJ_TYPE = "/Ip";
                     OBJ_NAME = dstname;
                     OBJ_IP = dst;
                     OBJ_PORT = dst_port;
                     OBJ_MAC = convert2Mac(dst_mac);
                     PRT_ACTION = status;
                     PACKETS_IN = sent_pkt;
                     PACKETS_OUT = rcvd_pkt;
                     BYTES_IN = sent;
                     BYTES_OUT = rcvd;
                }
            case "ips":
                if(subtype == "signature") {
                    entType = "基于漏洞的攻击";
                } else {
                    entType = "DDos";
                }
                pack("event") {
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
                     EVT_CATEGORY = entType;
                     EVT_TYPE = attack_id;
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
            case "icproto":
                pack("event") {
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
                     EVT_CATEGORY = type;
                     EVT_TYPE = icp_eventname;
                     SEVERITY = pri;
                     N_EVT_CATEGORY = "/Audit";
                     N_SEVERITY = dictMapping("severity",pri);
                     SBJ_TYPE = "/Ip";
                     SBJ_NAME = icp_visip;
                     SBJ_IP = icp_visip;
                     BHV_CATEGORY = icp_funcid;
                     PRT_ACTION = action;
                }
            case "event":
                pack("event") {
                     START_TIME = extractTime(time);
                     DS_DVC_NAME = devname;
                     DS_PROCESS_NAME = type;
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
        }
	}
}