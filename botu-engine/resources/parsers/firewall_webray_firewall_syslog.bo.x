botu("Firewall/Webray/Firewall/Syslog"){
    if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<time>[^:]+:\\d\\d:\\d\\d) (?<host>\\S+) (?<type>\\S+): (?<detail>.*)")) {
        splitParse(detail, ' ', '"', '"', "=");
        sev = parseSyslogSeverity(pri);
        switch(type){
         case "FILTER":
            if(Action == "PERMIT"){
                nCat = "/Audit";
            }else{
                nCat = "/Violation";
            }
            pack("event"){
                START_TIME = extractTime(time);
                EVT_CATEGORY = type;
                EVT_TYPE = PolicyID;
                SEVERITY = sev;
                N_EVT_CATEGORY = nCat;
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                SBJ_PORT = SrcPort;
                BHV_CATEGORY = "/Access";
                TRANS_PROTO = Protocol;
                OBJ_NAME = DstIP;
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_PORT = DstPort;
                PRT_ACTION = Action;
                OUTCOME = strFormat("%s %s", $14, $15);
            }
         case "SYSTEM_INFO":
         case "WARNING_INFO":
         case "IF_INFO":
         case "HA":
         case "OSPF":
         case "RIP":
         case "DHCP":
         case "CONFIG":
         case "DNS_PROXY":
         case "NAT":
         case "WEB_AUTH":
         case "IPSECVPN":
         case "L2TPVPN":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Audit/System";
                N_SEVERITY = dictMapping("severity",sev);
                OUTCOME = Content;
            }
         case "QOS":
         case "APPCONTROL":
         case "URL":
         case "SESSIONPOLICY":
         case "LOCAL_POLICY":
         case "CONNTRACK":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                SBJ_PORT = SrcPort;
                TRANS_PROTO = Protocol;
                BHV_CATEGORY = "/Access";
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = DstIP;
                OBJ_PORT = DstPort;
                OUTCOME = Content;
                PRT_ACTION = Action;
            }
         case "FLOOD":
         case "DDOS":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack/DOS";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                SBJ_PORT = SrcPort;
                TRANS_PROTO = Protocol;
                BHV_CATEGORY = "/Attack";
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = DstIP;
                OBJ_PORT = DstPort;
                OUTCOME = Content;
                PRT_ACTION = Action;
            }
         case "SCAN":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack/Scan";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                BHV_CATEGORY = "/Scan";
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = DstIP;
                OUTCOME = Content;
            }
         case "WEAK_PASSWORD":
         case "WAF":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                SBJ_PORT = SrcPort;
                BHV_CATEGORY = "/Attack";
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = UserName;
                OBJ_PORT = DstPort;
                OUTCOME = Content;
                PRT_ACTION = ACTION;
            }
         case "AV":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Malware";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = Protocol;
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = DstIP;
                OBJ_PORT = DstPort;
                OUTCOME = Content;
                PRT_ACTION = Action;
            }
         case "IPS":
         case "WEB_SECURITY":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = Protocol;
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = DstIP;
                OBJ_PORT = DstPort;
                OUTCOME = Content;
                PRT_ACTION = Action;
            }
         case "ANTIARP":
            pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                SBJ_PORT = SrcPort;
                SBJ_MAC = convert2Mac(SMAC);
                BHV_CATEGORY = "/Attack";
                TRANS_PROTO = Protocol;
                OUTCOME = Content;
                PRT_ACTION = Action;
            }
         case "BLACKLIST":
             pack("event"){
                 START_TIME = extractTime(time);
                 DS_DVC_NAME = host;
                 EVT_CATEGORY = type;
                 SEVERITY = sev;
                 N_EVT_CATEGORY = "/Violation";
                 N_SEVERITY = dictMapping("severity",sev);
                 SBJ_TYPE = "/Ip";
                 SBJ_NAME = SrcIP;
                 SBJ_IP = SrcIP;
                 SBJ_PORT = SrcPort;
                 TRANS_PROTO = Protocol;
                 BHV_CATEGORY = "/Access";
                 OBJ_TYPE = "/Ip";
                 OBJ_IP = DstIP;
                 OBJ_NAME = DstIP;
                 OBJ_PORT = DstPort;
                 OUTCOME = Content;
                 PRT_ACTION = Action;
             }
         default:
           pack("event"){
                START_TIME = extractTime(time);
                DS_DVC_NAME = host;
                EVT_CATEGORY = type;
                SEVERITY = sev;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",sev);
                SBJ_TYPE = "/Ip";
                SBJ_NAME = SrcIP;
                SBJ_IP = SrcIP;
                SBJ_PORT = SrcPort;
                TRANS_PROTO = Protocol;
                BHV_CATEGORY = "/Access";
                OBJ_TYPE = "/Ip";
                OBJ_IP = DstIP;
                OBJ_NAME = DstIP;
                OBJ_PORT = DstPort;
                OUTCOME = Content;
                PRT_ACTION = Action;
            }
        }
    }else  if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<detail>.*)")) {
        if(indexString(detail,",") != -1){
            splitParse(detail, ',', '"', '"', "=");
        }else{
            splitParse(detail, ' ', null, null, "=");
        }
        logTime = strFormat("%s %s",date,time);
        switch(type){
            case "traffic":
                pack("event"){
                    START_TIME = extractTime(logTime);
                    DS_DVC_NAME = devname;
                    EVT_CATEGORY = type;
                    EVT_TYPE = subtype;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Audit/Flow";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = src;
                    SBJ_IP = src;
                    SBJ_PORT = src_port;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(proto));
                    BHV_CATEGORY = "/Access";
                    OBJ_TYPE = "/Ip";
                    OBJ_IP = dst;
                    OBJ_NAME = dst;
                    OBJ_PORT = dst_port;
                    PACKETS_IN = sent_pkt;
                    PACKETS_OUT = rcvd_pkt;
                    BYTES_IN = sent;
                    BYTES_OUT = rcvd;
                    STATUS = status;
                }
            case "webfilter":
                pack("event"){
                    START_TIME = extractTime(logTime);
                    DS_DVC_NAME = devname;
                    EVT_CATEGORY = type;
                    EVT_TYPE = subtype;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = src;
                    SBJ_IP = src;
                    SBJ_PORT = src_port;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(proto));
                    BHV_CATEGORY = "/Access";
                    OBJ_TYPE = "/Ip";
                    OBJ_IP = dst;
                    OBJ_NAME = dst;
                    OBJ_PORT = dst_port;
                    PACKETS_IN = sent_pkt;
                    PACKETS_OUT = rcvd_pkt;
                    BYTES_IN = sent;
                    BYTES_OUT = rcvd;
                    STATUS = status;
                }
            case "ips":
                pack("event"){
                    START_TIME = extractTime(logTime);
                    DS_DVC_NAME = devname;
                    EVT_CATEGORY = type;
                    EVT_TYPE = attack_name;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Threat/Attack";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = src;
                    SBJ_IP = src;
                    SBJ_PORT = src_port;
                    TRANS_PROTO = dictMapping("trans_protocol",toInt(proto));
                    BHV_CATEGORY = "/Attack";
                    OBJ_TYPE = "/Ip";
                    OBJ_IP = dst;
                    OBJ_NAME = dst;
                    OBJ_PORT = dst_port;
                    STATUS = status;
                    OUTCOME = msg;
                }
            case "icproto":
                pack("event"){
                    START_TIME = extractTime(logTime);
                    DS_DVC_NAME = devname;
                    EVT_CATEGORY = subType;
                    EVT_TYPE = icp_eventname;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = icp_visip;
                    SBJ_IP = icp_visip;
                    SBJ_PORT = icp_viport;
                    BHV_CATEGORY = "/Access";
                    OBJ_TYPE = "/Ip";
                    OBJ_IP = icp_servip;
                    OBJ_NAME = icp_servip;
                    OBJ_PORT = icp_servport;
                    PRT_ACTION = action;
                }
            case "event":
                pack("event"){
                    START_TIME = extractTime(logTime);
                    DS_DVC_NAME = devname;
                    EVT_CATEGORY = type;
                    EVT_TYPE = subType;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",pri);
                    SBJ_TYPE = "/User";
                    SBJ_NAME = user;
                    PRT_ACTION = action;
                    OUTCOME = msg;
                }
            default:
                pack("event"){
                    START_TIME = extractTime(logTime);
                    DS_DVC_NAME = devname;
                    EVT_CATEGORY = type;
                    EVT_TYPE = subType;
                    SEVERITY = pri;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",pri);
                    OUTCOME = msg;
                }
        }

    }
}