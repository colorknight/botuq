botu("VulnScanner/DataCloudSec/VulnScanner/Syslog") {
    if(patternParse(MS_SRC_DATA,"\\<(?<pri>\\d+)\\>(?<time>[^\\s]+) (?<host>[^\\s]+) (?<type>.*)\\[\\d+\\]:\\s*(?<detail>.*) LogType=(?<logType>\\d)")){
        sev = parseSyslogSeverity(pri);
        switch(logType){
            case "0":
                patternParse(detail,"Timestamp=(?<Timestamp>[^:]+:\\d+:\\d+) VulType=(?<VulType>[^\\s]+) Dest=(?<Dest>[^\\s]+) VulName=(?<VulName>[^\\s]+) VulLevel=(?<VulLevel>[^\\s]+) OWASP=.* CVE=(?<CVE>.*) CNNVD.*");
                pack("event"){
                    START_TIME = extractTime(Timestamp);
                    DS_DVC_NAME = host;
                    EVT_CATEGORY = VulType;
                    EVT_TYPE = VulName;
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/Ip";
                    SBJ_NAME = Dest;
                    SBJ_IP = Dest;
                    BHV_CATEGORY = "/Exists";
                    OBJ_TYPE = "/Resource/Vulnerability";
                    OBJ_NAME = CVE;
                }
            case "1":
                patternParse(detail,"UserName=(?<UserName>[^\\s]+) Ip=(?<Ip>[^\\s]+) Event=(?<Event>[^\\s]+) Date=(?<Date>.*) Status=(?<Status>\\d) Desct=(?<Desct>[^\\s]+)");
                pack("event"){
                    START_TIME = extractTime(Date);
                    DS_DVC_NAME = host;
                    EVT_CATEGORY = "操作日志";
                    SEVERITY = sev;
                    N_EVT_CATEGORY = "/Audit";
                    N_SEVERITY = dictMapping("severity",sev);
                    SBJ_TYPE = "/User";
                    SBJ_NAME = UserName;
                    SBJ_IP = convert2Ip(Ip);
                    BEHAVIOR = Event;
                    STATUS = Status;
                    OUTCOME = strFormat("%s%s%s", Ip, Event,Desct);
                }
        }
    }
}