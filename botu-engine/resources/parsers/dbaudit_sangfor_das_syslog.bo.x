botu("DBAudit/Sangfor/DAS/Syslog"){
    if(patternParse(MS_SRC_DATA, "^\\<(?<pri>\\d+)\\>(?<time>([^:]+):\\d+:\\d+) .* (?<dvc>[^\\s]+)\\[\\d+\\]: (?<detail>.*)")){
        data = replaceString(detail,"\"","");
        splitParse(data, ',', null, null, ":");
        sev = parseSyslogSeverity(pri);
        switch(log_type ){
            case "risk_db_audit":
                if(db_name == null){
                    objName = db_type;
                }else{
                    objName = strFormat("%s/%s/%s", db_name,data_base_manage, table_name);
                }
                pack("event") {
                  START_TIME = format2Time(time, "MM-dd-yyyy HH:mm:ss");
                  DS_DVC_NAME = dvc;
                  DS_PROCESS_NAME = log_type;
                  EVT_CATEGORY = r_type0;
                  EVT_TYPE = r_rule0;
                  SEVERITY = sev;
                  N_EVT_CATEGORY = "/HiddenDanger";
                  N_SEVERITY = dictMapping("severity",sev);
                  SBJ_TYPE = "/User";
                  SBJ_NAME = db_usr;
                  SBJ_IP = src_ip;
                  SBJ_PORT = src_port;
                  BHV_CATEGORY = "/Access";
                  BHV_BODY = sql;
                  OBJ_TYPE = db_type;
                  OBJ_NAME = objName;
                  OBJ_IP = dst_ip;
                  OBJ_PORT = dst_port;
                }
            case "db_audit":
                if(db_name == null){
                    objName = db_type;
                }else{
                    objName = strFormat("%s/%s/%s", db_name,data_base_manage, table_name);
                }
                pack("event") {
                  START_TIME = format2Time(time, "MMM-dd-yyyy HH:mm:ss");
                  DS_DVC_NAME = dvc;
                  DS_PROCESS_NAME = log_type;
                  EVT_CATEGORY = log_type;
                  SEVERITY = sev;
                  N_EVT_CATEGORY = "/Audit";
                  N_SEVERITY = dictMapping("severity",sev);
                  SBJ_TYPE = "/User";
                  SBJ_NAME = db_usr;
                  SBJ_IP = src_ip;
                  SBJ_PORT = src_port;
                  BHV_CATEGORY = "/Access";
                  BHV_BODY = sql;
                  OBJ_TYPE = db_type;
                  OBJ_NAME = objName;
                  OBJ_IP = dst_ip;
                  OBJ_PORT = dst_port;
                }
        }
    }
}