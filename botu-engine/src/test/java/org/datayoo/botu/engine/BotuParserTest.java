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
      String data = "<188>Jun 27 15:21:44 WAF :{\"syslog\":{\"r_time_i\":1687850504,\"r_time_s\":\"2023-06-27 15:21:44\",\"r_sip_i\":2886861245,\"r_sip_s\":\"172.18.1.189\",\"r_xip_i\":2886861245,\"r_xip\":\"172.18.1.189\",\"r_dip_i\":2886861052,\"r_dip_s\":\"172.18.0.252\",\"r_sport\":29508,\"r_dport\":10012,\"r_policyid\":4294967295,\"r_ruleid\":2,\"r_itemid\":0,\"r_type\":52,\"r_type_name\":\"OS Injection Protection\",\"r_protection\":2,\"r_protection_name\":\"WEB\",\"r_severity\":2,\"r_severity_name\":\"HIGH\",\"r_protocol\":6,\"r_protocol_name\":\"TCP\",\"r_layer\":4,\"r_layer_name\":\"HTTP\",\"extra0\":0,\"extra1\":0,\"r_code\":14010,\"r_match_ofs\":0,\"r_match_len\":5,\"r_atk\":\"|echo\",\"r_action\":1,\"r_actiondetails\":\"PASS\",\"wafid\":\"0\",\"r_device\":\"waf\",\"userid\":0,\"g_serverid\":0,\"r_serverid\":4294967294,\"c_country\":\"\",\"c_province\":\"local area network\",\"r_city\":\"\",\"r_area\":\"\",\"r_hp\":\"0\",\"r_hp_name\":\"Requset Header\",\"r_attackfiled\":\"5\",\"r_method\":\"1\",\"r_method_name\":\"GET\",\"r_hostname\":\"172.18.0.252:10012\",\"r_url\":\"/cgi-bin/test-cgi.bat\",\"r_args\":\"|echo\",\"r_hdr\":\"Host: 172.18.0.252:10012\\r\\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36\\r\\nAccept-Encoding: gzip, deflate\\r\\nAccept: */*\\r\\nConnection: keep\",\"r_post\":\"\",\"r_rsp_hdr\":\"\",\"r_rsp_body\":\"\",\"r_xffip\":\" \",\"r_cdnip\":\" \",\"equipment\":\"2\",\"os\":\"7\",\"browser\":\"2\"}}";
      parse(botuEngine, data);
      //      data = "<52>AuditLog - WARNING - 2021-05-19 15:24:22-:1 admin through 1 from 192.168.32.11 1 100001 6 | User [\"admin\"] logout |\n";
      //      parse(botuEngine, data);
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
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_IDS_0700R0400B20160825151405\",\"level\":30,\"id\":\"152329550\",\"type\":\"Alert Log\",\"time\":1472284770413,\"source\":{\"ip\":\"192.168.10.33\",\"port\":1120,\"mac\":\"00-0c-29-79-f2-f7\"},\"destination\":{\"ip\":\"192.168.10.189\",\"port\":1405,\"mac\":\"00-11-d8-67-5c-c2\"},\"protocol\":\"TCP\",\"subject\":\"TCP_后门_网络红娘_正向连接\",\"message\":\"nic=1;数据长度=28;TCP数据内容=41 3a 00 00 d8 00 00 00 02 00 00 00 30 2e 30 30 47 00 00 00 1f 46 40 00 78 01 00 00;\",\"securityid\":\"5\",\"attackid\":\"7003\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_CS_0700R0600B20171018144640\",\"virus_name\":\"Virus/Win32.Parite.gh\",\"virus_file_name\":\"Backdoor.Win32.Agent.hj.(3FB0E2520D73EB7C589D8F56D2EED964).cpl\",\"protocol\":\"FTP\",\"src_ip\":\"fa11:1234:1234:1234:1234:1234:1234:27\",\"dst_ip\":\"fa11:1234:1234:1234:1234:1234:1234:26\",\"src_mac\":\"E005C5F37B20\",\"dst_mac\":\"0021CCB9DC4B\",\"src_port\":\"1266\",\"dst_port\":\"21\",\"start_time\":\"1508759104\",\"msg\":\" \",\"event_level\":\"4\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"dt\":\"VENUS_CS_0700R0605B20201029230950\",\"name\":\"MTAwMDAwMjkwLmRvYw==\",\"type\":\"1\",\"protocol\":\"FTP\",\"content\":\"{\\\"server_name\\\":\\\"MTkyLjE2OC4xNC4yMDc=\\\", \\\"user_name\\\":\\\"cm9vdA==\\\", \\\"file_direct\\\":\\\"0\\\", \\\"unzip_src\\\":\\\"\\\", \\\"unzip_layer\\\":\\\"0\\\"}\",\"file_type\":\"rtf\",\"md5\":\"b28c19037bf7ff38902287081ca24b93\",\"src_ip\":\"192.168.13.20\",\"dst_ip\":\"192.168.14.207\",\"src_port\":\"60583\",\"dst_port\":\"14849\",\"start_time\":\"1604310722\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : {\"ipGroupStr\":\"测试组\", \"statusStr\":\"初次发生\", \"level\":\"高\",\"ip\":\"10.51.52.142\", \"sceneName\":\"IP扫描\", \"time\":\"2020-11-05\", \"deviceName\":\"N/A\", \"threatStr\":\"低\"}";
      parse(botuEngine, data);
      data = "<12>Jun 22 12:40:33 (none) : CPU使用率4.00%，超过设定值1.00%";
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
