botu("Firewall/H3C/F5030-D/Syslog"){
	if (patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<time>[^:]+:\\d+:\\d+ \\d+).*(?<host>[^%\\s]+) %%\\d*(?<type>[^/]+)/\\d+/(?<subType>[^:]+):\\s*(?<detail>.*);{0,}.{0,}")) {
            sev = parseSyslogSeverity(pri);
            switch(type){
                case "FILTER":
                    patternSplitParse(detail, "; ?", "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = type;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        EVT_ID = field("RuleID(1078)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = field("SrcIPAddr(1003)");
                        SBJ_NAME = field("SrcIPv6Addr(1036)");
                        SBJ_IP = convert2Ip(field("SrcIPAddr(1003)"));
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        SBJ_MAC = convert2mac(field("SrcMacAddr(1021)"));
                        SBJ_VLAN = field("SrcZoneName(1025)");
                        BHV_CATEGORY = "/Access";
                        TRANS_PROTO = field("Protocol(1001)");
                        APP_PROTO = field("Application(1002)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        OBJ_VLAN = field("DstZoneName(1035)");
                        PRT_ACTION = field("Event(1048)");
                    }
                case "ATK":
                     if(indexString(subType, "ICMP") != -1){
                        transProto = "ICMP";
                     }
                     direction = dictMapping("attack_direction_h3c",subType);
                     evtType = dictMapping("evt_type_h3c",subType);
                     patternSplitParse(detail, "; ?", "=");
                     if(direction == 0){
                       srcIp = field("SrcIPAddr(1003)");
                       srcName = field("SrcIPAddr(1003)");
                       dstIp =  field("DstIPAddr(1007)");
                       dstName = field("DstIPAddr(1007)");
                     }else{
                       dstIp = field("SrcIPAddr(1003)");
                       dstName = field("SrcIPAddr(1003)");
                       srcIp =  field("DstIPAddr(1007)");
                       srcName = field("DstIPAddr(1007)");
                     }
                     if(srcIp != null){
                        srcType = "/Ip";
                     }
                     if(dstIp != null){
                        dstType = "/Ip";
                     }
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = evtType;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = srcType;
                        SBJ_NAME = srcName;
                        SBJ_IP = srcIp;
                        BHV_CATEGORY = "/Attack";
                        TRANS_PROTO = transProto;
                        OBJ_TYPE = dstType;
                        OBJ_NAME = dstName;
                        OBJ_IP = dstIp;
                        PRT_ACTION = field("Action(1053)");
                    }
                case "ANTI-VIR":
                    if(subType == "ANTIVIRUS_IPV4_INTERZONE"){
                        patternSplitParse(detail, "; ?", "=");
                        direction = field("HitDirection(1115)");
                        if(direction == "original"){
                           srcIp = convert2Ip(field("SrcIPAddr(1003)"));
                           srcPort = field("SrcPort(1004)");
                           srcMac = convert2mac(field("SrcMacAddr(1021)"));
                           srcLoc = field("SrcLocation(1209)");
                           dstIp = convert2Ip(field("DstIPAddr(1007)"));
                           dstPort = field("DstPort(1008)");
                           dstMac = convert2Mac(field("DstMacAddr(1022)"));
                           dstLoc = field("DstLocation(1214)");
                        }else{
                           dstIp = convert2Ip(field("SrcIPAddr(1003)"));
                           dstPort = field("SrcPort(1004)");
                           dstMac = convert2mac(field("SrcMacAddr(1021)"));
                           dstLoc = field("SrcLocation(1209)");
                           srcIp = convert2Ip(field("DstIPAddr(1007)"));
                           srcPort = field("DstPort(1008)");
                           srcMac = convert2Mac(field("DstMacAddr(1022)"));
                           srcLoc = field("DstLocation(1214)");
                        }
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = field("VirusCategory(1182)");
                            EVT_TYPE = field("VirusName(1085)");
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Threat/Attack/Malware";
                            N_SEVERITY = dictMapping("severity",sev);
                            SBJ_TYPE = "/User";
                            SBJ_NAME = srcIp;
                            SBJ_IP = srcIp;
                            SBJ_PORT = srcPort;
                            SBJ_MAC = srcMac;
                            SBJ_LOCATION = srcLoc;
                            BHV_CATEGORY = "/Attack";
                            TRANS_PROTO = field("Protocol(1001)");
                            APP_PROTO = field("Application(1002)");
                            OBJ_TYPE = "/Ip";
                            OBJ_NAME = dstIp;
                            OBJ_IP = dstIp;
                            OBJ_PORT = dstPort;
                            OBJ_MAC = dstMac;
                            OBJ_LOCATION = dstLoc;
                            PRT_ACTION = field("Action(1053)");
                        }
                    } else if(subType == "ANTIVIRUS_IPV6_INTERZONE"){
                        patternSplitParse(detail, "; ?", "=");
                        direction = field("HitDirection(1115)");
                        if(direction == "original"){
                           srcIp = convert2Ip(field("SrcIPv6Addr(1036)"));
                           srcPort = field("SrcPort(1004)");
                           srcMac = convert2mac(field("SrcMacAddr(1021)"));
                           srcLoc = field("SrcLocation(1209)");
                           dstIp = convert2Ip(field("DstIPv6Addr(1037)"));
                           dstPort = field("DstPort(1008)");
                           dstMac = convert2Mac(field("DstMacAddr(1022)"));
                           dstLoc = field("DstLocation(1214)");
                        }else{
                           dstIp = convert2Ip(field("SrcIPv6Addr(1036)"));
                           dstPort = field("SrcPort(1004)");
                           dstMac = convert2mac(field("SrcMacAddr(1021)"));
                           dstLoc = field("SrcLocation(1209)");
                           srcIp = convert2Ip(field("DstIPv6Addr(1037)"));
                           srcPort = field("DstPort(1008)");
                           srcMac = convert2Mac(field("DstMacAddr(1022)"));
                           srcLoc = field("DstLocation(1214)");
                        }
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = field("VirusCategory(1182)");
                            EVT_TYPE = field("VirusName(1085)");
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Threat/Attack/Malware";
                            N_SEVERITY = dictMapping("severity",sev);
                            SBJ_TYPE = "/User";
                            SBJ_NAME = srcIp;
                            SBJ_IP = srcIp;
                            SBJ_PORT = srcPort;
                            SBJ_MAC = srcMac;
                            SBJ_LOCATION = srcLoc;
                            BHV_CATEGORY = "/Attack";
                            TRANS_PROTO = field("Protocol(1001)");
                            APP_PROTO = field("Application(1002)");
                            OBJ_TYPE = "/Ip";
                            OBJ_NAME = dstIp;
                            OBJ_IP = dstIp;
                            OBJ_PORT = dstPort;
                            OBJ_MAC = dstMac;
                            OBJ_LOCATION = dstLoc;
                            PRT_ACTION = field("Action(1053)");
                        }
                    }else{
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = type;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Audit";
                            N_SEVERITY = dictMapping("severity",sev);
                            OUTCOME = detail;
                        }
                    }
                case "ASPF":
                    if(subType == "ASPF_IPV4_DNS"){
                        patternSplitParse(detail, "; ?", "=");
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = subType;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Threat/Attack";
                            N_SEVERITY = dictMapping("severity",sev);
                            SBJ_TYPE = "/Ip";
                            SBJ_NAME = field("SrcIPAddr(1003)");
                            SBJ_IP = field("SrcIPAddr(1003)");
                            BHV_CATEGORY = "/Attack";
                            TRANS_PROTO = field("Protocol(1001)");
                            APP_PROTO = field("Application(1002)");
                            OBJ_TYPE = "/Ip";
                            OBJ_NAME = field("DstIPAddr(1007)");
                            OBJ_IP = field("DstIPAddr(1007)");
                            PRT_ACTION = field("Action(1053)");
                            OUTCOME = field("Reason(1056)");
                        }
                    }else if(subType == "ASPF_IPV6_DNS"){
                        patternSplitParse(detail, "; ?", "=");
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = subType;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Threat/Attack";
                            N_SEVERITY = dictMapping("severity",sev);
                            SBJ_TYPE = "/Ip";
                            SBJ_NAME = field("SrcIPAddr(1003)");
                            SBJ_IP = field("SrcIPAddr(1003)");
                            BHV_CATEGORY = "/Attack";
                            TRANS_PROTO = field("Protocol(1001)");
                            APP_PROTO = field("Application(1002)");
                            OBJ_TYPE = "/Ip";
                            OBJ_NAME = field("DstIPAddr(1007)");
                            OBJ_IP = field("DstIPAddr(1007)");
                            PRT_ACTION = field("Action(1053)");
                            OUTCOME = field("Reason(1056)");
                        }
                    }
                case "SYSLOG":
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = dictMapping("evt_type_h3c",subType);
                        EVT_TYPE = subType;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        OUTCOME = detail;
                    }
                case "AVC":
                    patternSplitParse(detail, "; ?", "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        EVT_NAME = PolicyName(1079);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Violation";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("UserName(1113)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = field("SrcIPv6Addr(1036)");
                        SBJ_PORT = field("SrcPort(1004)");
                        SBJ_LOCATION = field("SrcLocation(1209)");
                        BHV_CATEGORY = "/Access";
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        OBJ_LOCATION = field("DstLocation(1214)");
                        PRT_ACTION = field("Action(1053)");
                    }
                case "BLS":
                    if(indexString(subType,"_BLOCK") != -1){
                        patternSplitParse(detail, "; ?", "=");
                        if(indexString(subType,"BLS_DIP") != -1){
                           dstType = "/Ip";
                           dstName = field("DstIPAddr(1007)");
                           dstName = field("DstIPv6Addr(1037)");
                           dstIp = field("DstIPAddr(1007)");
                           dstIp = convert2Ip(field("DstIPv6Addr(1037)"));
                        }else if(indexString(subType,"BLS_IP") != -1){
                           srcType = "/Ip";
                           srcName = field("DstIPAddr(1007)");
                           srcName = field("DstIPv6Addr(1037)");
                           srcIp = field("DstIPAddr(1007)");
                           srcIp = convert2Ip(field("DstIPv6Addr(1037)"));
                        }else if(indexString(subType,"USER_IP") != -1){
                           srcType = "/User";
                           srcName = field("User(1098)");
                           srcIp = field("SrcIPAddr(1003)");
                           srcIp = convert2Ip(field("SrcIPv6Addr(1036)"));
                        }
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = subType;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Threat/MalResource";
                            N_SEVERITY = dictMapping("severity",sev);
                            SBJ_TYPE = srcType;
                            SBJ_NAME = srcName;
                            SBJ_IP = srcIp;
                            OBJ_TYPE = dstType;
                            OBJ_NAME = dstName;
                            OBJ_IP = dstIp;
                        }
                    }else if(indexString(subType,"BLS_ENTRY") != -1){
                        patternSplitParse(detail, "; ?", "=");
                        if(indexString(subType,"ADD") != -1){
                            bhv = "/Add";
                        }else{
                            bhv = "/Delete";
                        }
                        if(indexString(subType,"USER") != -1){
                           dstType = "/User";
                           dstName = field("User(1098)");
                        }else{
                           dstType = "/Ip";
                           dstName = field("SrcIPAddr(1003)");
                           dstName = field("SrcIPv6Addr(1036)");
                           dstIp = field("SrcIPAddr(1003)");
                           dstIp = convert2Ip(field("SrcIPv6Addr(1036)"));
                        }
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = subType;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Audit/System";
                            N_SEVERITY = dictMapping("severity",sev);
                            SBJ_TYPE = "/Ip";
                            SBJ_NAME = MS_SRC_ADDRESS;
                            SBJ_IP = MS_SRC_ADDRESS;
                            BHV_CATEGORY = bhv;
                            OBJ_TYPE = dstType;
                            OBJ_NAME = dstName;
                            OBJ_IP = dstIp;
                            OUTCOME = field("Reason(1056)");
                        }
                    }
                case "CC-DEFENSE":
                    patternSplitParse(detail, "; ?", "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = field("RuleName(1080)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = field("SrcIPAddr(1003)");
                        SBJ_NAME = field("SrcIPv6Addr(1036)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        BHV_CATEGORY = "/Attack";
                        TRANS_PROTO = field("Protocol(1001)");
                        APP_PROTO = field("Application(1002)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        PRT_ACTION = field("Action(1053)");
                    }
                case "DFILTER":
                    patternSplitParse(detail, "; ?", "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = field("RuleName(1080)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Violation";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("UserName(1113)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        BHV_CATEGORY = "/Access";
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        PRT_ACTION = field("Action(1053)");
                    }
                case "FFILTER":
                    patternSplitParse(detail, "; ?", "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = field("RuleName(1080)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Violation";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("UserName(1113)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        BHV_CATEGORY = "/Access";
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        PRT_ACTION = field("Action(1053)");
                        PLD_TYPE = "/File";
                        PLD_NAME = field("FileName(1097)");
                    }
                case "IPS":
                    patternSplitParse(detail, "; ?", "=");
                    if(subType == "IPS_WARNING"){
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = type;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Audit";
                            N_SEVERITY = dictMapping("severity",sev);
                            OUTCOME = detail;
                        }
                    }
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = field("AttackName(1088)");
                        EVT_ID = field("AttackID(1089)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("UserName(1113)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        SBJ_VLAN = field("SrcZoneName(1025)");
                        SBJ_MAC = convert2Mac(field("SrcMacAddr(1021)"));
                        BHV_CATEGORY = "/Attack";
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        OBJ_VLAN = field("DstZoneName(1035)");
                        OBJ_MAC = convert2Mac(field("DstMacAddr(1022)"));
                        PRT_ACTION = field("Action(1053)");
                        PLD_TYPE = "/File";
                        PLD_NAME = field("FileName(1097)");
                    }
                case "SANDBOX":
                    patternSplitParse(detail, "; ?", "=");
                    trtType = field("TrtType(1144)");
                    if(trtType == "NORMAL"){
                        bhvCat = "/Access";
                        nCat = "/Audit";
                    }else{
                       bhvCat = "/Attack";
                       nCat = "/Threat/Attack";
                    }
                    attackName = field("AttackName(1088)");
                    if(attackName != null){
                       attackType = dictMapping("attack_type_h3c",field("ThreatAct(1171)"));
                       attackCat = dictMapping("attack_cat_h3c",field("ThreatFmly(1172)"));
                    }
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_CATEGORY = attackType;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        EVT_TYPE = attackCat;
                        SEVERITY = sev;
                        N_EVT_CATEGORY = nCat;
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("UserName(1113)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        SBJ_VLAN = field("SrcZoneName(1025)");
                        SBJ_MAC = convert2Mac(field("SrcMacAddr(1021)"));
                        BHV_CATEGORY = bhvCat;
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        OBJ_VLAN = field("DstZoneName(1035)");
                        OBJ_MAC = convert2Mac(field("DstMacAddr(1022)"));
                        PRT_ACTION = field("Action(1053)");
                        PLD_TYPE = "/File";
                        PLD_NAME = field("FileName(1097)");
                    }
                case "SCD":
                    patternSplitParse(detail, "; ?", "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Violation";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = field("ServerIPAddr(1003)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        BHV_CATEGORY = "/Access";
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_PORT = field("DstPort(1008)");
                    }
                case "UFLT":
                    patternSplitParse(detail, "; ?", "=");
                    if(subType == "UFLT_WARNING"){
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = type;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Audit";
                            N_SEVERITY = dictMapping("severity",sev);
                            OUTCOME = detail;
                        }
                    }
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Violation";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/Ip";
                        SBJ_NAME = field("SrcIPAddr(1003)");
                        SBJ_NAME = field("SrcIPv6Addr(1036)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        BHV_CATEGORY = "/Access";
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                    }
                case "WAF":
                    patternSplitParse(detail, "; ?", "=");
                    if(subType == "WAF_WARNING"){
                        pack("event"){
                            START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                            DS_PROCESS_NAME = type;
                            EVT_CATEGORY = type;
                            EVT_TYPE = subType;
                            EVT_TYPE = dictMapping("evt_type_h3c",subType);
                            SEVERITY = sev;
                            N_EVT_CATEGORY = "/Audit";
                            N_SEVERITY = dictMapping("severity",sev);
                            OUTCOME = detail;
                        }
                    }
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = subType;
                        EVT_CATEGORY = attackType;
                        EVT_TYPE = field("AttackName(1088)");
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Threat/Attack";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = field("UserName(1113)");
                        SBJ_IP = field("SrcIPAddr(1003)");
                        SBJ_IP = convert2Ip(field("SrcIPv6Addr(1036)"));
                        SBJ_PORT = field("SrcPort(1004)");
                        SBJ_VLAN = field("SrcZoneName(1025)");
                        SBJ_MAC = convert2Mac(field("SrcMacAddr(1021)"));
                        BHV_CATEGORY = "/Attack";
                        BEHAVIOR = field("HttpMethod(1206)");
                        TRANS_PROTO = field("Protocol(1001)");
                        OBJ_TYPE = "/Ip";
                        OBJ_NAME = field("DstIPAddr(1007)");
                        OBJ_NAME = field("DstIPv6Addr(1037)");
                        OBJ_IP = field("DstIPAddr(1007)");
                        OBJ_IP = convert2Ip(field("DstIPv6Addr(1037)"));
                        OBJ_PORT = field("DstPort(1008)");
                        OBJ_VLAN = field("DstZoneName(1035)");
                        OBJ_MAC = convert2Mac(field("DstMacAddr(1022)"));
                        PRT_ACTION = field("Action(1053)");
                        PLD_TYPE = "/File";
                        PLD_NAME = field("FileName(1097)");
                    }
                case "AAA":
                    contents = strSplit(detail, ";");
                    splitParse(contents[0], '-', null, null, "=");
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = AAAType;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        SBJ_TYPE = "/User";
                        SBJ_NAME = UserName;
                        BHV_CATEGORY = Service;
                        OUTCOME = detail;
                    }
                default:
                    pack("event"){
                        START_TIME = format2Time(time, "MMM dd HH:mm:ss yyyy");
                        DS_PROCESS_NAME = type;
                        EVT_CATEGORY = type;
                        EVT_TYPE = subType;
                        EVT_TYPE = dictMapping("evt_type_h3c",subType);
                        SEVERITY = sev;
                        N_EVT_CATEGORY = "/Audit";
                        N_SEVERITY = dictMapping("severity",sev);
                        OUTCOME = detail;
                    }
            }
    }
}