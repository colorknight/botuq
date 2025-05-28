package org.datayoo.botu.engine;

import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotuPatternTest extends TestCase {

  public void testWebrayRegEx() {
    String data = "2021-02-23 17:26:13 local7.warning WAF :2021-02-23 17:26:13 WAF: 192.168.13.32:8469->192.168.13.32 devicename=waf url=/ method=GET args=ip=1%20and%201=1 flag_field=  block_time=0 http_type=  attack_field=5 profile_id=-2 rule_id=1 type=SQL Injection Protection severity=2  action=2  referer=  useragent=  post=  xip=192.168.13.32 code=10010 country=  province=local area network";
    String pattern = "";
    parse(pattern, data);
  }

  public void testWebrayRegEx2() {
    String data = "<188>Apr 20 13:32:44 WAF :2021-04-20 13:32:44 DDOS: devicename=waf sip=172.18.0.52 sport=53 dip=172.18.6.16 dport=6044 ipproto=UDP attack=DNS Cache_Poison url=  host=  cdnip=  xff=172.18.0.52 grade=1 ecount=1 country=  province=local area network";
    String pattern = "^\\<(?<pri>\\d+)\\>.*WAF :(?<time>.*) DDOS: devicename=(.*) sip=(?<sip>.*) sport=(?<sport>.*) dip=(?<dip>.*) dport=(?<dport>.*) ipproto=(?<proto>.*) attack=(?<attack>.*) url=(?<url>.*) host=(?<host>.*) cdnip=(?<cdnip>.*) xff=(?<xff>.*) grade=.*";
    parse(pattern, data);
  }

  public void testWebrayRegEx3() {
    String data = "<188>Apr 21 09:34:59 WAF :2021-04-21 09:34:59 WAF: 172.18.1.37:138->172.18.255.255 devicename=waf url=  method=UNKNOWN args=  flag_field=  block_time=0 http_type=  attack_field=0 profile_id=0 rule_id=0 type=Black IP severity=1  action=2  referer=  useragent=  post=  xip=172.18.1.37 code=0 country=  province=local area network |";
    String pattern = "^\\<(?<pri>\\d+)\\>.*WAF :(?<time>.*) WAF: (?<sip>[^:]*):(?<sport>\\d+)-\\>(?<dip>[^ ]*) devicename=(?<devicename>.*) url=(?<url>.*) method=(?<method>.*) args=(?<args>.*) flag_field=(?<flagfield>.*) block_time=(?<blocktime>.*) http_type=(?<httptype>.*) attack_field=(?<attackfield>.*) profile_id=(?<profileid>\\d*) rule_id=(?<ruleid>\\d*) type=(?<type>.*) severity=(?<severity>.*) action=(?<action>.*)\\s+referer=(?<referer>.*) useragent=(?<useragent>.*) post=(?<post>.*) xip=(?<xff>.*) code=.*";
    parse(pattern, data);
  }

  public void testInfoGoRegEx() {
    String data = "Nov 25 13:49:51 asm processor_server: classid:304, level:1, devid:11, devip:192.168.44.50, devname:, content:[192.168.44.50]非法外联";
    String pattern = "^(?<time>.*)\\s[^\\s]+\\s[^:]+: classid:(?<classid>[^,]*),\\slevel:(?<level>[^,]*),\\sdevid:(?<devid>[^,]*),\\sdevip:(?<devip>[^,]*),\\sdevname:(?<devname>[^,]*),\\scontent:(?<content>.*)";
    parse(pattern, data);
  }

  public void testQZSecRegEx1() {
    String data = "Dec 27 18:20:13 node01 node1:login(WEB)(INFORMATIONAL)(service=native,identity=admin,from=10.10.67.15,login authorize success)";
    String pattern = "^(.*)\\s(?<devname>[^\\s]+)\\s[^:]+:(?<evtcat>[^\\(]+)\\((?<evttype>[^\\)]+)\\)\\(([^\\)]+)\\)\\(service=([^,]+),identity=([^,]+),from=([^,]+),([^\\)]+).*";
    parse(pattern, data);
  }

  public void testQZSecRegEx2() {
    String data = "Dec 27 18:20:13 node01 node1:access(INFORMATIONAL)(id=S01AIA8C8X3QZV,service=tuilogin,server=Centos(10.10.33.30),account=root,identity=admin(admin),from=10.10.67.15)";
    String pattern = "^(.*)\\s(?<devname>[^\\s]+)\\s[^:]+:(?<evtcat>[^\\(]+)\\((?<severity>[^\\)]+)\\)\\(id=(?<id>[^,]+),service=(?<service>[^,]+),server=(?<server>[^\\(]+)\\((?<srvip>[^\\)]+)\\),account=([^,]+),identity=(?<identity>[^\\(]+)\\([^\\(]+\\),from=(\\d+\\.\\d+\\.\\d+\\.\\d+)\\)$";
    parse(pattern, data);
  }

  public void testQZSecRegEx3() {
    String data = "Dec 27 18:36:39 node01 node1:cmd(NOTICE)(id=SPR9ADXQE020K7,service=cmdcheck,action=confirm(pass),server=Centos(10.10.33.30),account=root,identity=admin(admin),from=10.10.67.15,command=ls-a)";
    String pattern = "^(?<time>.*)\\s(?<devname>[^\\s]+)\\s[^:]+:(?<evtcat>[^\\(]+)\\((?<severity>[^\\)]+)\\)\\(id=(?<id>[^,]+),service=(?<service>[^,]+),action=(?<action>[^,]+),server=(?<server>[^\\(]+)\\((?<srvip>[^\\)]+)\\),account=([^,]+),identity=(?<identity>[^\\(]+)\\([^\\(]+\\),from=(?<cliip>[^,]+),command=(?<command>[^\\)]+).*";
    parse(pattern, data);
  }

  public void testQZSecRegEx4() {
    String data = "Dec 27 15:58:04 node01 node1:session(WARNING)(id=S2TGD1JJY69K4P,service=sessionReview,server=Centos(10.10.33.30),account=root,identity=test(test),from=10.10.67.15,authorizer=admin,wait for reviewing)";
    String pattern = "^(?<time>.*)\\s(?<devname>[^\\s]+)\\s[^:]+:(?<evtcat>[^\\(]+)\\((?<severity>[^\\)]+)\\)\\(id=(?<id>[^,]+),service=(?<service>[^,]+),server=(?<server>[^\\(]+)\\((?<srvip>[^\\)]+)\\),account=([^,]+),identity=(?<identity>[^\\(]+)\\([^\\(]+\\),from=(?<cliip>[^,]+),authorizer=(?<authorizer>[^,]+),(?<msg>[^\\)]+).*";
    parse(pattern, data);
  }

  public void testQZSecRegEx5() {
    String data = "Jun 27 14:33:42 node01 node1:TUILOG(INFORMATIONAL)(id=S0E56FWNPTLV66,service=tuilog,server=10.10.33.30(CentOS),account=root,identity=admin(admin),from=10.10.67.15,action=allow,command=ls)";
    String pattern = "^(?<time>.*)\\s(?<devname>[^\\s]+)\\s[^:]+:(?<evtcat>[^\\(]+)\\((?<severity>[^\\)]+)\\)\\(id=(?<id>[^,]+),service=(?<service>[^,]+),server=(?<srvip>[^\\(]+)\\((?<server>[^\\)]+)\\),account=([^,]+),identity=(?<identity>[^\\(]+)\\([^\\(]+\\),from=(?<cliip>[^,]+),action=(?<action>[^,]+),command=([^\\)]+)\\)$";
    parse(pattern, data);
  }

  public void testMsWindows() {
    String data = "<12>Aug 4 16:59:11 DESKTOP-V3AFPHN wmi_forward_ip=10.36.2.7 TimeWritten=20200804085858.397642-000 Category=12545 EventIdentifier=4634 TimeGenerated=20200804085858.397642-000 Message=已注销帐户。\\n\\n使用者:\\n\\t安全ID:\\t\\tS-1-5-21-587548196-918034470-1600176980-500\\n\\t帐户名:\\t\\tAdministrator\\n\\t帐户域:\\t\\tWIN-H193FDU7KH9\\n\\t登录ID:\\t\\t0x1321C9FE1\\n\\n登录类型:\\t\\t\\t3\\n\\n在登录会话被破坏时生成此事件。可以使用登录ID值将它和一个登录事件准确关联起来。在同一台计算机上重新启动的区间中，登录ID是唯一的。 EventType=4 SourceName=Microsoft-Windows-Security-Auditing EventCode=4634 Type=审核成功 ComputerName=WIN-H193FDU7KH9 InsertionStrings={S-1-5-21-587548196-918034470-1600176980-500,Administrator,WIN-H193FDU7KH9,0x1321c9fe1,3} CategoryString=注销 RecordNumber=15818377 Logfile=Security";
    String pattern = "^\\<(?<pri>\\d+)\\>(?<time>\\w+\\s+\\d+\\s+\\d+:\\d+:\\d+)\\s+(?<host>\\S+)\\s+(?<detail>.*)";
    parse(pattern, data);
  }

  public void testDPtechFw1() {
    String data = "<142> 1 10.199.199.22 2017 Dec 28 16:16:00 FW 350560417 NAT444:SessionW 1514448960|1514448961|172.17.16.212|172.17.16.212|63995|172.17.19.23|80|6";
    String pattern = "^\\<(?<pri>\\d+)\\> \\d+ (?<devip>[^\\s]+) (?<time>\\d+\\s+\\w+\\s+\\d+\\s+\\d+:\\d+:\\d+) (?<devname>\\w+) (?<eid>\\d+) (?<module>[^:]+):(?<cat>[^ ]+) (?<detail>.*)$";
    parse(pattern, data);
  }

  public void testBastionLegendSec() {
    String data = "<174>2020-10-29T16:50:04.099207+08:00 SecFox baoleiji logType=YAB_CMD_OPS_LOG, time=2020-10-29 16:50:04, user=linbin, sourceIP=10.10.100.243, resource=¨¨????????????¡è¡ì???webA, targetIP=10.10.103.1, command=mkdir videos, action=permit";
    String pattern = "^\\<(?<pri>\\d+)\\>(?<time>[^\\s]+)\\s[^\\s]+\\s(?<dev>[^\\s]+)\\s(?<msg>.*)";
    parse(pattern, data);
  }

  public void testIpsLeadSec() {
    String data = "<214>Sep  3 16:29:10 ips IPS: SerialNum=2313401809279998 GenTime=\"2019-09-03 16:29:10\" SrcIP=10.155.243.54 SrcIP6= SrcIPVer=4 DstIP=10.155.243.53 DstIP6= DstIPVer=4 Protocol=ICMP SrcPort=0 DstPort=0 InInterface=xge1/3 OutInterface=xge1/1 SMAC=00:00:5e:00:01:64 DMAC=dc:da:80:d0:80:25 FwPolicyID=1 EventName=ICMP_PING_事件 EventID=152320139 EventLevel=0 EventsetName=All SecurityType=协议分析 SecurityID=10 ProtocolType=ICMP ProtocolID=7 Action=PASS Vsysid=0 Content=\"类型=8;\" CapToken= EvtCount=1";
    String pattern = "^\\<(?<pri>\\d+)\\>(?<time>\\w+\\s+\\d+\\s.\\d+:\\d+:\\d+)\\s(?<devname>[^\\s]+)\\s(?<evtcat>\\w+):(?<msg>.*)";
    parse(pattern, data);
  }

  public void testADDptech() {
    String data = "Jul 22 08:57:04 2012 DPTECH %%IPS/ATTACK/2/SRVLOG(l): log-type:attack-protect;``event:block;``attack-name:(402653194)Apache CGI Byterange Request¾Ü¾ø·þÎñÂ©¶´;``protocol-name:(1375731729)ÍøÒ³ä¯ÀÀ;`` ip-proto-id:6;``source-ip:4-0a010164;``source-port:7590;``destination-ip:4-0a0101c8;``destination-port:80;``ifname-inside:eth2_12;``packet:////////AFI00NHVCABFAABElXdAADMGiRDawXZNynENrBW6ABVYKcIUgLozVYAY+p7T/wAAAQEICgAAmbsAR3jeVVNFUiBhbm9ueW1vdXMNCg==;``summary-count:1;``summary-offset:0;``";
    String pattern = "(?<time>.*) (?<devname>[^ ]+) %%[^/]+/[^/]+/(?<sev>\\d+)/[^\\)]+\\): (?<detail>.*)";
    parse(pattern, data);
    data = "Dec 04 12:10:29 10.11.3.33 2012-01-06 01:35:32 DPTECH %%--IPS/WEB/3/OPERLOG(l): client-type(84):web;user-name(85):admin;host-ip(86):10.11.100.75;error-code(87):0;User: [admin] (IP address: 10.11.100.75 ) logged out.";
    pattern = ".* (?<time>\\d+-\\d+-\\d+ [^ ]+) (?<devname>[^ ]+) %%[^/]+/[^/]+/(?<sev>\\d+)/[^\\)]+\\): (?<detail>.*)";
    parse(pattern, data);
    data = "4-0a0101c8";
    pattern = "\\d-([0-9a-fA-F]+)";
    parse(pattern, data);
  }

  public void testAvRisingEsm() {
    String data = "<0> 2020-07-23 13:24:09 rx01 AV 0 PUF.GameHack!1.B5BD 病毒 删除成功 自定义查杀 SAMP(8).VIR C:\\Users\\xcb\\Desktop\\样本11\\样本 2020-04-10 11:13:36 WIN-7PFQL9NTGNS % GUID ={Client:IP}% 00-0C-29-68-0B-D7";
    String pattern = "^\\<\\d+\\>(?<time>\\w+\\s+\\d+\\s.\\d+:\\d+:\\d+)\\s(?<devname>[^\\s]+)\\s(?<evtcat>\\w+)";
    parse(pattern, data);
  }

  public void testQianxinEsm() {
    String data = "<6>2019-10-23T10:47:50+08:00 WIN-9LK33364IP6 skylar_log[33740]: {\"log_name\":\"logcenter:skylar-client_process_running\",\"create_time\":\"2019-10-23 10:47:50\",\"ip\":\"192.168.0.210\",\"report_ip\":\"192.168.0.210\",\"mac\":\"94c691586fa6\",\"gid\":\"1\",\"content\":{\"cmdline\":\"/??/C:/Windows/Sysnative/conhost.exe \\\"1695405018-3698715-1486375948400536693230671744-18745854181237369874-1100526991\",\"gid\":1,\"group_name\":\"默认分组\",\"mid\":\"e15d9a0a01324cafad310e56406850f7\",\"operate\":\"stop\",\"pid\":6024,\"process_company\":\"Microsoft Corporation\",\"process_copyright\":\"© Microsoft Corporation. All rights reserved.\",\"process_description\":\"Console Window Host\",\"process_file_version\":\"6.1.7601.23677 (win7sp1_ldr.170209-0600)\",\"process_guid\":\"347CBBF7F8950CDA50B9163FA3B65F7C\",\"process_integrity\":3,\"process_internal_name\":\"ConHost\",\"process_md5\":\"ce476f23405aadc46039ac13127df473\",\"process_name\":\"conhost.exe\",\"process_original_name\":\"CONHOST.EXE\",\"process_parent_guid\":\"77180C75EC5DDF081852FDA20D74DADF\",\"process_parent_md5\":\"\",\"process_parent_path\":\"\",\"process_parent_pid\":648,\"process_path\":\"c:/windows/sysnative/conhost.exe\",\"process_product_name\":\"Microsoft® Windows® Operating System\",\"process_product_version\":\"6.1.7601.23677\",\"process_signature\":\"Microsoft Windows\",\"process_user\":\"\",\"serial_num\":\"5e3dbf41-c660-401d-895e-2d75460599b6\",\"sip\":\"192.168.0.210\",\"starttime\":\"2019-10-23 10:37:57\",\"terminal\":\"THTF-PC\",\"time_event\":\"2019-10-23 10:37:57\"}}";
    String pattern = "^\\<(?<pri>\\d+)\\>(?<time>[^\\s]+)\\s(?<devname>[^\\s]+)\\s[^\\[]+\\[\\d+\\]:\\s(.*)";
    parse(pattern, data);
  }

  public void testVulnScannerTopsec() {
    String data = "https://10.26.24.181/";
    String pattern = "(?<appProto>[^:]+)://(?<domainName>[^/]+)/";
    parse(pattern, data);
  }
  public void testIpsDptech() {
    String data = "<97>Jul 25 16:36:06 2020 DPTECH %%DPX/AV/1/SRVLOG(l): log-type:av-protect;``event:alert;``av-name:(67109046)Virus.Eicar-Test-String;``protocol-name:(1375731732)SMTP.TCP;``ip-proto-id:6;``source-ip:172.30.177.168;``source-port:50302;``destination-ip:192.168.2.254;``destination-port:25;``ifname-inside:gige3_16;``vsys-id:0;``summary-count:1;``summary-offset:0;``policy-id:1;``policy-name:1;``src-mac:44:37:e6:1b:b7:01;``dst-mac:00:24:ac:85:11:13;``";
    String pattern = "^\\<(?<pri>\\d+)\\>(?<time>\\w+\\s+\\d+ \\d+:\\d+:\\d+ \\d*) (?<dev>[^\\s]+) %%.*/(?<module>[^/]+)/\\d/[^\\)]+\\): (?<msg>.*)";
    parse(pattern, data);
  }

  public void testGuardDptech() {
    String data = "<57>2016-06-15 14:29:21 10.12.5.200 %%--PROBE/DETECT/1/ALARM: Anomaly ID:415; Creation Time:Sun Jan 18 07:12:39 1970; Update Time:Wed Jun 15 14:29:21 2016; Type:Traffic Anomaly; Sub-type:UDP Flood; Severity:Red; Status:Ongoing; Direction:Incoming; Resource:; Resource ID:; Importance:High; Current:; Threshold:; Unit:; DIP1:10.12.1.1; DIP2:; DIP3:; DPort1:53; DPort2:23; SIP1:1.1.1.1; SIP2:2.2.2.2; SIP3:3.3.3.3; SPort1:123; SPort2:456; Protocol:17; URL to Link the Report:www.dptechnology.net";
    String pattern = "^\\<(?<pri>\\d+)\\>(?<time>[^\\s]+ \\d+:\\d+:\\d+) (?<dev>[^\\s]+) %%.*/[^/]+/\\d/(?<cat>[^:]+): (?<msg>.*)";
    parse(pattern, data);
  }

  public void testOspDptech() {
    String data = "{auth} Fri Nov 22 16:48:10 GMT+08:00 2019 ALERT fort i: xjy(通过账号xjy登录)，在2019-11-22 16:48:09通过172.16.8.73做添加[1]操作，操作成功。日志级别：[INFO]。 172.16.8.19";
    String pattern = ".*\\s(?<user>\\w+)\\(通过账号(?<role>\\w+)登录\\)，在(?<time>[^通]+)通过(?<sip>[^做]+)做(?<action>[^\\[]+)\\[(?<content>[^]]+).*，(?<result>[^。]+).*\\[(?<sev>[^\\]]+)\\].*";
    parse(pattern, data);
  }

  public void testOspDptech2() {
    String data = "[张三]通过账号[zhangsan]在[2015-03-17 17:49:49.0]访问[单点登录]时，触发[命令越权]告警，越权命令：rm。日志级别：[Alert]";
    String pattern = "^\\[(?<user>[^\\]]+)\\].*\\[(?<account>[^\\]]+)\\]在\\[(?<time>[^\\]]+)\\].*\\[(?<action>[^\\]]+)\\].*\\[(?<cat>[^\\]]+)\\].*\\[(?<sev>[^\\]]+)\\].*";
    parse(pattern, data);
  }

  public void testOspDptech3() {
    String data = "[张三](通过账号[zhangsan]登录)在[2015-03-17 17:49:49.0]到[2015-03-17 17:49:56.0]通过[192.168.23.17]访问[192.168.23.223]，用[root]账号通过[ssh]协议访问[redhat服务器](资源类型[common linux])。日志级别：[INFO]";
    String pattern = ".*\\[(?<user>[^\\]]+)\\]\\(通过账号\\[(?<account>[^\\]]+)\\]登录\\)在\\[(?<bTime>[^\\]]+)\\]到\\[(?<eTime>[^\\]]+)\\].*\\[(?<sip>[^\\]]+)\\]访问\\[(?<dip>[^\\]]+)\\].*\\[(?<role>[^\\]]+)\\]账号通过\\[(?<appProto>[^\\]]+)\\]协议访问\\[(?<name>[^\\]]+)\\].*\\[(?<sev>[^\\]]+)\\].*";
    parse(pattern, data);
  }

  public void testSangFor1000() {
    String data = "<134>May 19 02:49:07 WIN-FNAOU24F2MK BA[5444]: [log_type:flux] [record_time:2023-05-18 18:01:15] [user:10.65.237.30] [group:/] [host_ip:10.65.237.30] [dst_ip:::] [serv:访问网站] [app:新闻门户] [site:未定义位置] [tm_type:/移动终端/windows系统移动终端] [up_flux:1719] [down_flux:1629]";
    String pattern = "^\\<(?<pri>\\d+)\\>.* (?<dev>[^\\s]+) \\w+\\[\\d+\\]: (?<msg>.*)";
    parse(pattern, data);
  }


  protected void parse(String pattern, String log) {
    Pattern patt = Pattern.compile(pattern);
    Matcher matcher = patt.matcher(log);
    if (matcher.matches()) {
      for (int i = 1; i <= matcher.groupCount(); i++) {
        System.out.println(String.format("%d:%s", i, matcher.group(i)));
      }
      System.out.println("--------------------------");
    }
  }
}
