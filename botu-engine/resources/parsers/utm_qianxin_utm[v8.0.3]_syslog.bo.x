botu("UTM/Qianxin/UTM[v8.0.3]/Syslog") {
    if(patternParse(MS_SRC_DATA, "\\<(?<pri>\\d+)\\>(?<logTime>[^:]+:\\d+:\\d++) (?<hostName>[^\\s]+) (?<detail>.*)")){
        ary = strSplit(detail, "\\|");
        patternSplitParse(ary[7], "\\t", "=");
        if(malwaretype != null) {
          pack("event"){
              START_TIME = extractTime(logTime);
              DS_DVC_NAME = hostName;
              EVT_CATEGORY = simpleMapping(malwaretype, "{1:间谍软件,2:广告软件,3:拨号器,4:玩笑病毒,5:宏病毒,6:IRC木马,7:网页病毒,8:木马,9:蠕虫,10:后门,100:病毒}",1);
              EVT_TYPE = malwarename;
              SEVERITY = ary[6];
              N_SEVERITY = dictMapping("severity",ary[6]);
              N_EVT_CATEGORY = "/Threat/Malware";
              SBJ_TYPE = "/Ip";
              SBJ_NAME = ip;
              SBJ_IP = ip;
              SBJ_OS = os;
              BHV_CATEGORY = "/Exists";
              OBJ_TYPE = "/File";
              OBJ_NAME = filename;
              OUTCOME = description;
              PRT_ACTION = simpleMapping(action,"{0:允许,1:监控,2:阻止}",0);
          }
        }else if(attacktype != null){
          pack("event"){
              START_TIME = extractTime(logTime);
              DS_DVC_NAME = hostName;
              EVT_CATEGORY = logtype;
              EVT_TYPE = dictMapping("attack_type_qianxin",attacktype);
              SEVERITY = ary[6];
              N_SEVERITY = dictMapping("severity",ary[6]);
              N_EVT_CATEGORY = "/Threat/Attack";
              SBJ_TYPE = "/Ip";
              SBJ_NAME = sourceip;
              SBJ_IP = sourceip;
              SBJ_PORT = sourceport;
              SBJ_MAC = mac;
              BHV_CATEGORY = "/Attack";
              TRANS_PROTO = dictMapping("trans_protocol",protocol);
              OBJ_TYPE = "/Ip";
              OBJ_NAME = destip;
              OBJ_IP = destip;
              OBJ_PORT = destport;
              OUTCOME = description;
              PRT_ACTION = simpleMapping(action,"{0:阻止,1:检测}",0);
          }
        }else if(logtype == 1){
          pack("event"){
              START_TIME = extractTime(logTime);
              DS_DVC_NAME = hostName;
              EVT_CATEGORY = logtype;
              EVT_TYPE = rulename;
              SEVERITY = ary[6];
              N_SEVERITY = dictMapping("severity",ary[6]);
              N_EVT_CATEGORY = "/Audit";
              SBJ_TYPE = "/Ip";
              SBJ_NAME = sourceip;
              SBJ_IP = sourceip;
              SBJ_PORT = sourceport;
              SBJ_MAC = mac;
              BHV_CATEGORY = "/Access";
              TRANS_PROTO = dictMapping("trans_protocol",protocol);
              OBJ_TYPE = "/Ip";
              OBJ_NAME = destip;
              OBJ_IP = destip;
              OBJ_PORT = destport;
              OUTCOME = description;
              PRT_ACTION = simpleMapping(action,"{0:阻止,1:强制允许,2:允许}",0);
          }
        }else if(malwareip != null){
          pack("event"){
              START_TIME = extractTime(logTime);
              DS_DVC_NAME = hostName;
              EVT_CATEGORY = malwarefamilies;
              EVT_TYPE = malwaretype;
              SEVERITY = ary[6];
              N_SEVERITY = dictMapping("severity",ary[6]);
              N_EVT_CATEGORY = "/Threat/Malware";
              SBJ_TYPE = "/Ip";
              SBJ_NAME = malwareip;
              OUTCOME = description;
              STATUS = simpleMapping(isolated,"{0:未失陷,1:已失陷-未隔离,2:已失陷-已隔离}",0);
          }
        }else{
          pack("event"){
              START_TIME = extractTime(logTime);
              DS_DVC_NAME = hostName;
              EVT_CATEGORY = "/Audit";
              EVT_TYPE = ary[5];
              SEVERITY = ary[6];
              N_SEVERITY = dictMapping("severity",ary[6]);
              N_EVT_CATEGORY = "/Audit";
              OUTCOME = description;
              OUTCOME = content;
          }
        }
    }

}