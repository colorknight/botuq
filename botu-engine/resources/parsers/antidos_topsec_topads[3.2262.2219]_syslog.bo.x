botu("AntiDos/Topsec/TopADS[3.2262.2219]/Syslog") {
	splitParse(MS_SRC_DATA, ' ', '"', '"', "=");
	switch(type) {
		case "ads_attack_flow":
			pack("event") {
				START_TIME = format2Time(time "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = attack_type;
                EVT_ID = event_id;
                EVT_NAME = "ADS攻击检测";
                SEVERITY = alarm_level;
                N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",alarm_level);
				DURATION = time_interval;
                SBJ_TYPE = "/IpGroup";
                SBJ_NAME = src_ip;
                BHV_CATEGORY = "/Attack";
				TRANS_PROTO = toUppercase(proto);
				TECHNICAL = attack_type;
				OBJ_TYPE = "/Ip";
				OBJ_NAME = dst_addr;
				OBJ_IP = dst_addr;
				OBJ_PORT = port;
				OBJ_VLAN = zone;
				STATUS = status;
			}
		case "ads flow log":
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
				EVT_TYPE = symbol;
				EVT_NAME = "ADS流量";
				SEVERITY = pri;
				N_EVT_CATEGORY = "/Audit/Flow";
                N_SEVERITY = dictMapping("severity",pri);
                DURATION = time_interval;
                SBJ_TYPE = "/IpGroup";
                SBJ_NAME = "*";
                BHV_CATEGORY = "/Access";
				TRANS_PROTO = protocol;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = obj_extend1;
				OBJ_IP = obj_extend1;
				OBJ_VLAN = zone;
			}
		case "ads_lead":
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
				EVT_TYPE = symbol;
				EVT_NAME = "ADS牵引";
				SEVERITY = pri;
				N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
                SBJ_TYPE = "/User";
                SBJ_NAME = admin;
                SBJ_IP = lead_src_addr;
                BHV_CATEGORY = "/Execute";
                TECHNICAL = lead_way;
                BEHAVIOR = lead_action;
                OBJ_TYPE = "/Ip";
                OBJ_NAME = dst_addr;
				OBJ_IP = dst_addr;
				OUTCOME = lead_msgs;
			}
		case "ads credit log":
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = attack_type;
                EVT_NAME = "ADS信誉";
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
				DURATION = time_interval;
				SBJ_TYPE = "/Ip";
				SBJ_NAME = src_ip;
				SBJ_IP = src_ip;
				SBJ_VLAN = zonename;
				SBJ_LOCATION = strFormat("%s/%s", country, city);
				BHV_CATEGORY = "/Is";
			}
		case "ads_atk_geo":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = attack_type;
                EVT_ID = event_id;
                EVT_NAME = "ADS攻击地理";
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads_learn_single_attr":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(time_begin "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads_learn_topn":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(time_begin "yyyy-MM-dd HH:mm:ss");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads_domain_topn_log":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads_alarm_log":
			pack("event") {
				START_TIME = format2Time(time, "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
				DS_PROCESS_NAME = recorder;
				EVT_CATEGORY = type;
				EVT_TYPE = symbol;
				EVT_NAME = "ADS告警";
				SEVERITY = alarm_level;
				N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",alarm_level);
			}
		case "ads src topn model log":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(time_begin "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
				DS_PROCESS_NAME = recorder;
				EVT_CATEGORY = type;
				EVT_TYPE = atk_type;
				SEVERITY = pri;
				N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads ipgeo model log":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(time_begin "yyyy-MM-dd HH:mm:ss");
				DS_DVC_NAME = dev;
				DS_PROCESS_NAME = recorder;
				EVT_CATEGORY = type;
				EVT_TYPE = atk_type;
				SEVERITY = pri;
				N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads_protect_status":
		    // 统计类日志
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = symbol;
                SEVERITY = pri;
                N_EVT_CATEGORY = "/Audit";
                N_SEVERITY = dictMapping("severity",pri);
			}
		case "ads attack flow log":
			pack("event") {
				START_TIME = format2Time(cur_t, "yyyy-MM-dd HH:mm:ss");
                DS_DVC_NAME = dev;
                DS_PROCESS_NAME = recorder;
                EVT_CATEGORY = type;
                EVT_TYPE = attack_type;
                EVT_ID = event_id;
                EVT_NAME = "ADS攻击检测";
				SEVERITY = alarm_level;
				N_EVT_CATEGORY = "/Threat/Attack";
                N_SEVERITY = dictMapping("severity",alarm_level);
				DURATION = time_interval;
                SBJ_TYPE = "/Private";
				SBJ_NAME = src_ip;
				BHV_CATEGORY = "/Attack";
				TRANS_PROTO = toUppercase(proto);
				OBJ_TYPE = "/Private";
				OBJ_NAME = dst_addr;
				OBJ_PORT = port;
				STATUS = status;
			}
		default:
			pack(){}
	}

}