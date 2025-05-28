# Botu简介

​	Botu是一款专用于数据解析与封装的脚本语言。为了降低使用者的学习成本，该语言大量借鉴了传统语言的语法，如：If关键字、Switch关键字等。同时，由于Botu是用于特定领域的语言，为提升使用效率，Botu也规定了特殊的语法形式，这些特殊语法对于熟悉各类开发语言的使用者来说需要稍加适应，如： ifo、pack关键字等。这些关键字是该语言区别于其它语言的主要特征，是为数据解析工作定制设计的。

​	Botu的Maven坐标

```
<dependency>
    <groupId>org.datayoo.botu</groupId>
    <artifactId>botu-engine</artifactId>
    <version>1.0.0</version>
</dependency>
```



# Botu语法

## 程序结构

```
//天融信防火墙
botu("Firewall/Topsec/TOS") {
split(ORIGINAL_DATA, ' ', '"', '"', "=");
  type = trim(type, "\"");
  //subType = trim(subType, "\"");
  proto = trim(proto, "\"");
  ftime = format2Time(trim(time,"\""), "yyyy-MM-dd HH:mm:ss");
  switch(type) {
    case "user_auth":
    case "mgmt":
    case "system":
             pack(){};
    case "ips":
    case "ar":
             pack(){};
    case "conn":
             pack("simpleEvent") {
               DVC_RECEIPT_TIME = ftime;
               START_TIME = ftime;
               END_TIME = ftime;
               DVC_NAME = trim(fw,"\"");
               SEVERITY = pri;
               PRIORITY = dictMapping("topsec-firewall-category",pri);
               DVC_EVENT_CATEGORY=type;
               DVC_ADDRESS=DATASOURCEIP;
               DVC_TYPE=BOTU_NAME;
        };
  }
}
```

​	以上代码是一个使用botu语言编写的日志解析脚本，该脚本用于解析天融信的防火墙日志。

脚本的开始是解析脚本的主函数botu，类似开发语言的main函数。每个解析脚本都以botu函数作为解析的入口函数。botu函数内定义了botu的名字以及版本信息，一般而言，botu会以其解析的目标数据的应用的名字被命名，如示例中botu被命名为“Firewall/Topsec/TOS”，表示该脚本是用来解析天融信的tos日志的。

​    在botu的主函数内可以根据日志的格式，编写对应的处理脚本。脚本支持的语法包括if、switch、函数、表达式、赋值语句、pack等。可根据实际需要自由组合这些语法。当脚本解析日志成功，希望退出解析流程时，有两种方式：一是执行到了整个解析脚本的尾部，自然退出解析过程；二是解析成功，调用pack语法，将解析后的日志打包，发送，并退出。pack关键字等同于开发语言中的return关键字，每当程序需要主动返回时就可调用pack关键字。pack关键字的详细解释见[Pack表达式](#_Pack表达式)，当pack不填写参数及打包规则时表示退出脚本并且不打包。

## 数据类型

​	Botu支持整型、浮点型、布尔、字符、字符串等基本的数据类型。其表现形式与大部分开发语言相同。

​	整型支持十进制与十六进制两种常用的表达方式，如：-123(十进制)、0x123(十六进制)；当希望用长整型表示数据时，可在数据值的后面加上字符’l’或’L’。如：123L(长整型)。

​	浮点数支持一般小数和科学计数两种表达方式，如：123.25(一般小数)、2.29E+103(科学计数)

​	布尔数为小写的”true”和”false”，分别表示真和假

​	字符为字母两端加单引号，如: ’c’、’f’等

​	字符串为两端加双引号的字母及数字的组合，如：”this is a test”。当字符串内容中需要出现’”’时，在’”’前，加转义字符’\’，如：”this is a \”test\””

​	Botu没有变量声明的相关语法，所以不支持为变量指定数据类型。所有变量可视为数据值的容器，在为变量赋不同的数据值时，不对变量的类型进行校验，这意味着只有值有保留有数据类型的信息。如：a = 123; a=”123”;

## 表达式

​	Botu语法的语法结构比较简单，借鉴了常用开发语言中的If和Switch表达式，除此外Botu结合日志解析的特点又加入了Botu以及Pack表达式。所有这些表达式的名字皆为语法保留字，在进行解析脚本编写时一定要注意，在变量及函数命名时，不要与这些保留字产生冲突。

​	Botu的保留字包括：botu、if、else、switch、case、default、break以及pack。这些保留字皆为小写。

### Botu表达式

```
//天融信防火墙
botu("Firewall/Topsec/TOS") {
……
}
```

​	botu表达式类似于开发语言的main函数，它是所有运用botu语言编写的解析脚本的主结构，其它解析逻辑全部包括在它的代码块中。其可接受一个参数，即解析脚本的名字。该名字一般与被解析的日志的名字一致。如代码示例中，解析脚本是用来解析天融信Tos防火墙日志的，故该botu被命名为了“Firewall/Topsec/TOS”。

### If与Ifo表达式

```
//天融信防火墙
if(patternParse(ORIGINAL_DATA, "\"id=(\\w+) time=\"([^\"]+)+\"(.*)")) {
……
} else if (patternParse(ORIGINAL_DATA, "\"id=(\\w+) name=\"([^\"]+)+\" (.*)")) {
……
} else {
……
}
```

​	If表达式是开发语言中常见的语法表达式。Botu中的if表达式与所有开发语言一样，支持if…else if…else的语法结构。Ifo表达式与If表达式的逻辑语言含义一致，但其在运行态会与一般的条件分支语句执行逻辑不同。它会计算每个条件分支的命中频率，并动态调整分支语句在执行时的匹配顺序，优化数据处理的效率。

### Switch表达式

```
switch(type) {
    case "user_auth":
        ……
        break;
default:
    ……
    pack(){}
}
```

​	Switch表达式是开发语言中常见的语法表达式。Botu中的switch表达式与所有开发语言一样，支持switch…case…default…的语法结构。Botu的switch语法接受整数、浮点数以及字符串作为分支条件。

### For表达式

```
for(i=0; i < 5; i++) {
    if (i==2)
        break;
if (i==3)
    continue;
    ……
}
// 或者
for(seg : segs) {
    ……
}
```

​	For表达式是开发语言中常见的语法表达式。Botu中的for表达式与所有开发语言一样，用于完成循环控制。Botu如示例中所示，支持两种语法结构。由于Botu是数据解析专用语言，而数据中可能出现的循环场景，循环次数有限，故Botu内置了循环保护，循环保护缺省为1000次，即当脚本循环次数超过1000次时，脚本报异常。循环保护的次数可通过Botu解析引擎的接口进行修改。Botu内置的循环保护可有效避免死循环逻辑的产生。

### While表达式

```
i = 0;
while(i++ < 5) {
    if (i==2)
        break;
if (i==3)
    continue;
    ……
}
```

​	While表达式是开发语言中常见的语法表达式。Botu中的while表达式与所有开发语言一样，用于完成循环控制。语法结构如示例中所示。由于Botu是数据解析专用语言，而数据中可能出现的循环场景，循环次数有限，故Botu内置了循环保护，循环保护缺省为1000次，即当脚本循环次数超过1000次时，脚本报异常。循环保护的次数可通过Botu解析引擎的接口进行修改。Botu内置的循环保护可有效避免死循环逻辑的产生。

### Pack表达式

pack(\[schemaName\]\[, mapMode\])

```
pack("simpleEvent",true) {
            DVC_RECEIPT_TIME = ftime;
            START_TIME = ftime;
            ……
}
```

​	Pack表达式是Botu特有的语法表达式，其主要承担了两个功能。一是打包解析后的数据，并通过数据的监听端口，将该数据发送出去；二是返回功能，等同于开发语言中的continue表达式。

​    如代码示例所示pack表达式可接受两个参数，并包含一个代码块。两个参数在使用时，均可省略。参数一为字符串参数，该参数表示打包数据遵循的数据规范的名字；参数二为布尔参数，该参数表示是否将数据打包成map格式，该值缺省为true。若该值为false，数据会被打包为数组格式，数组格式在进行数据处理时会带来更高的访问效率；代码块为一组赋值表达式，变量名为数据规范中定义的字段名，数据值需满足数据规范中对该字段的类型定义。数据规范格式如下：

```
{
  DVC_TYPE: ["String"],
  DVC_NAME: ["String"],
  DVC_ADDRESS: ["Long", "Ip"],
  DVC_RECEIPT_TIME: ["Datetime"],
  START_TIME: ["Datetime"],
  END_TIME: ["Datetime"],
  DVC_EVENT_CATEGORY: ["String"],
  ……
```

​	数据规范定义了被打包数据的数据schema，包括字段名及字段类型。

​	Botu语言中没有return表达式，故当其需要返回时可通过调用无参数及映射规则的pack语法进行返回，如：pack(){}，该表达式表示return。因为，日志解析的最后一步都是以打包结束，顾省略了return表达式。

### 赋值表达式

```
type = trim(type, "\"");
proto = trim(proto, "\"");
```

​	Botu语言支持”=”、”+=”、” -=”、”*=”、”/=”、”&=”、”|=”、 ”^=”、”>>=”、”<<=”、”%=”等赋值运算符。

​	赋值表达式中type、proto为变量。Botu语言在解释执行时，会维护一个堆栈用于存放所有的变量。Botu解析引擎在接受待解析数据时，实际接受的为一个键值对组成的Map对象。该对象中的键值对会以变量及其值的方式压入解析引擎的堆栈，解析脚本可以像访问变量一样访问这些传入引擎待解析的数据。

### 成员表达式

​	Botu语言支持成员运算符’.’。像所有面向对象的语言一样，可通过成员运算符访问对象中的属性与方法。如：对象A在Botu引擎堆栈中的对象名为a，其含有属性name，那么在Botu中要访问A对象的name属性，可写为”a.name”。

### 数组表达式

​	Botu语言支持数组运算符‘[]’以下标的方式访问数组对象。若对象的类型为数组或链表，可使用‘[]’运算符加整数下标的方式访问数组或链表的元素，如： array[2]、list[2]；若对象类型为Map，可使用‘[]’运算符加Key的方式访问Map中的元素，如：map[‘key1’]等。

### 运算符表达

​	Botu引擎支持逻辑运算符、位运算符、比较运算符、算数运算符等多种运算符。

Ø 逻辑运算符：||(或)、&&(与)、^(异或)、!(非)

Ø 位运算符： |(按位或)、&(按位与)、>>(右位移)、<<(左位移)

Ø 比较运算符：==(等于)、!=(不等于)、<=(小于等于)、>=(大于等于)、>(大于)、<(小于)

Ø 算数运算符：+(加)、-(减)、*(乘)、/(除)、%(模)

### 函数表达式

```
proto = trim(proto, "\"");
ftime = format2Time(trim(time,"\""), "yyyy-MM-dd HH:mm:ss");
```

​	Botu支持函数表示式，其函数的组成与调用方式与开发语言相同。Botu内置函数见[函数库](#_函数库)。同时Botu允许使用者在使用过程中不断扩展函数库，函数的开发实现参见[Botu函数开发](#_Botu函数开发)。

# Botu函数开发

​	Botu语言可以支持通过扩充函数来增强语言的日志解析能力。由于Botu采用了Java开发语言，故Botu中的函数只支持Java实现。Botu中的函数在开发时需实现Function接口，该接口引用自开源项目MOQL。其接口定义如下：

```
public interface Function extends Operand {
    // 获得函数参数个数
int getParameterCount();
	// 获得函数类型
FunctionType getFunctionType();
	// 获得函数参数列表
List<Operand> getParameters();
}
```

​	Function是Operand类型的一种，关于Operand的详细介绍可以参见[MOQL](https://github.com/colorknight/moql)的相关介绍。MOQL为Function实现提供了名为AbstractFunction的模板类。函数实现时可直接继承此类，可省去不必要的重复开发，提升函数开发的效率。

​    以下给出一个字符串处理函数--Trim的代码实现，通过该代码可以详细了解如何开发一个Botu函数。代码如下：

```
public class Trim extends AbstractFunction {
  // 函数的名字，语法将通过该名字调用函数
	public static final String FUNCTION_NAME = "trim";
	// 待处理的变量
	protected Operand var;
	// 需被剪切掉的字符串特征
	protected String pattern;
  public Trim(List<Operand> parameters) {
    // 初始化函数，数值2表示该参数接受2个参数，若参数个数不等于2则初始化失败
  super(FUNCTION_NAME, 2, parameters);
      // 从参数列表中获取第一个操作数，该操作数的含义为待处理的包含字符串的变量
  var = parameters.get(0);
  // 判断参数2的类型是否为字符串常量
      if (!(parameters.get(1) instanceof StringConstant)) {
        throw new IllegalArgumentException(
            "The second parameter should be a string constant in trim function!");
  }
  // 调用计算接口，获得模式字符串。当操作数为常量时，可通过调用传入值为null的
  // operate方法获得常量数值
		pattern = (String) parameters.get(1).operate(null);
  }

  @Override
  protected Object innerOperate(EntityMap entityMap) {
		// 从输入的上下文中获取待处理变量的值
		Object obj = var.operate(entityMap);
    if (obj == null)
      return null;
    String src = obj.toString();
    int begin = 0, end = 0, index = 0;
    int len = pattern.length();
    int offset = 0;
		// 剔除变量var中头部和尾部，所有符合pattern特征的字符，并最终返回处理后的字符串
		//search the destination string at source string's head
    while (true) {
      index = src.indexOf(pattern, offset);
      if (index != offset)
        break;
      offset += len;
    }
    begin = offset;
    //  search the destination string at source string's tail
    offset = src.length() - 1;
    while (true) {
      index = src.lastIndexOf(pattern, offset);
      if (index != offset)
        break;
      offset -= len;
    }
    end = offset + 1;
    if (begin >= end)
      return "";
    return src.substring(begin, end);
  }
}
```

​    Botu函数在继承了AbstractFunction模板类后，只要实现函数构造方法及innerOperate方法就可完成一个Botu函数的实现。函数构造方法用于准备函数运行所需的属性环境，innerOperate方法则用来实现具体的处理逻辑，如代码示例中Trim函数所示。

# 函数库

## 解析

​	解析函数是一类特殊的函数，用于将输入的日志，按照参数设定，拆解为不同的键值对，并将这些键值对设置到Botu引擎的堆栈中，供后续处理。

### SplitParse

#### 声明

```
void splitParse(String  field, char separator, char beginEnclosure, char endEnclosure, String  kvSeparator) 
```

#### 说明

​	按照separator字符将字符串拆解为多个字符串段。若每个字符串段符合特征”key*value”，其中‘*’为kvSeparator设置的值。用kvSeparator拆解每段为一个key/value对，将key/value对插入引擎的堆栈。若特征不符合”key*value”特征，则不对字符串段进行二次拆解，并以字符‘$’+字符串段序号的格式命名字符串段（如：$1，$2等），并将名字与字符串段以key/value的形式插入引擎的堆栈。若输入字符串格式为csv格式，如：id=“1”,name=“test”,ip=“127.0.0.1”，其除了分隔符为‘,’号外，所有的字符串段均被‘”’号所包围。需要注意，若‘,’号被‘”’号包围，则被‘”’号包围的‘,’号将不会被视为分隔符。beginEnclosure与endEnclosure两个参数用于描述字符串段两侧的包围符，在csv的示例中，这两个参数都是字符‘”’号。当指定了包围符后，被拆解的字符串段为包围符内的字符串，不包括字符串段两侧的包围符。如csv格式的示例日志，其采用函数表达式splitParse(sample, ’,’ , ‘”’, ‘”’, “=”)进行拆解。按分隔符和包围符拆解后的字符串段分别为：id=1、name=test、ip=127.0.0.1。再经kvSeparator拆解后得到id:1,name:test,ip:127.0.0.1三个key/value对。  

#### 参数

| 名称           | 类型   | 说明                                                         |
| -------------- | ------ | ------------------------------------------------------------ |
| field          | string | 待拆解的字符串                                               |
| separator      | char   | 字符串的分隔符                                               |
| beginEnclosure | char   | 字符串的前包围符，若无包围符，可填0                          |
| endEnclosure   | char   | 字符串的后包围符，若无包围符，可填0                          |
| kvSeparator    | string | 若字符串为key/value格式，kvSeparator为key/value对的分隔字符串，若非key/value格式，填null |

 

### PatternSplitParse

#### 声明

```
 void  patternSplitParse(String field, String separator, String kvSeparator) 
```

#### 说明

 	按照separator字符串将字符串拆解为多个字符串段。若每个字符串段符合特征”key*value”，其中‘*’为kvSeparator设置的值。用kvSeparator拆解每段为一个key/value对，将key/value对插入引擎的堆栈。若特征不符合”key*value”特征，则不对字符串段进行二次拆解，并以字符‘$’+字符串段序号的格式命名字符串段（如：$1，$2等），并将名字与字符串段以key/value的形式插入引擎的堆栈。若输入字符串格式如：id=1##name=test##ip=127.0.0.1，其分隔字符串为“##”， kvSeparator为“=”。按分隔字符串拆解后的字符串段分别为：id=1、name=test、ip=127.0.0.1。再经kvSeparator拆解后得到id:1,name:test,ip:127.0.0.1三个key/value对。  

#### 参数

| 名称        | 类型   | 说明                                                         |
| ----------- | ------ | ------------------------------------------------------------ |
| field       | string | 待拆解的字符串                                               |
| separator   | string | 字符串的分隔字符串，该字符串为正则表达式                     |
| kvSeparator | string | 若字符串为key/value格式，kvSeparator为key/value对的分隔字符串，若非key/value格式，填null |

 

### PatternParse

#### 声明

```
  void patterParse(String  field, String pattern)  
```

#### 说明

​	按照pattern所描述的正则表达式拆解字符串。若正则表达式配置有被捕获字段的名字，则将该名字作为key，将被捕获字段作为value插入引擎堆栈。若正则表达式未配置被捕获字段名，则以字符‘$’+字符串段序号的格式命名字符串段（如：$1，$2等），并将该名字与被捕获字段以key/value的格式插入引擎堆栈。 

#### 参数

| 名称    | 类型   | 说明           |
| ------- | ------ | -------------- |
| field   | string | 待拆解的字符串 |
| pattern | string | 正则表达       |

### JsonParse

#### 声明

```
  void jsonParse(String  field[, boolean flat])  
```

#### 说明

 将Json字符串转换为对象并插引擎堆栈，Json字符串被序列化为多层嵌套的Map对象或Map数组对象。 若Json字符串为Json数组或flat参数值为false，则将从Json转换后的对象以field+”Jo”的名字插入引擎堆栈。如：field名为log，则堆栈中会被插名为logJo的对象。若flat参数为true，则Json对象会被展开，如Json数据{  “ip”:”127.0.0.1”,”port”:”80”,”request”:{“url”:”/test”}}，将会被展开为ip=”127.0.0.1”;  port=80; request.url=”/test”的三个键值对并插入引擎堆栈。展平的Json数据，其key为数据在Json中的路径以’.’连接在一起的字符串。由于‘.’是成员运算符，不能通过变量名request.url访问到“/test”值，因为request.url表示的是访问request对象的url属性。故要访问被展平的数据需通过field函数，将“request.url”作为字符串传给该函数，该函数可从引擎堆栈中访问到名为“request.url”的变量值。

#### 参数

| 名称    | 类型   | 说明           |
| ------- | ------ | -------------- |
| field   | string | 待拆解的字符串 |
| pattern | string | 正则表达式     |

 



## 字符串

### Trim

#### 声明

```
String trim(String  field, String pattern)  
```

#### 说明

​	去除字符串两端符合pattern特征的字符。如字符串“---test---”，将pattern设置为“-”，经函数处理后得到的字符串为“test”。  

#### 参数

| 名称    | 类型   | 说明                                                  |
| ------- | ------ | ----------------------------------------------------- |
| field   | string | 待处理的字符串                                        |
| pattern | string | 特征字符串，如：“###test###”, “#”或“###”即pattern的值 |

### StrFormat

#### 声明

```
String strFormat(String  pattern, Object… objects)  
```

#### 说明

​	将多个输入对象按照pattern所描述的格式进行字符串格式化。如：字符串  a =“str”和整数 b=1按照模式”%s:%d”进行格式化，输出结果为：“str:1”。函数表达式为：strFormat(“%s:%d”, a, b)。 

#### 参数

| 名称    | 类型    | 说明                                                         | 说明 |
| ------- | ------- | ------------------------------------------------------------ | ---- |
| pattern | string  | 字符串格式化的模式。模式中允许出现的转换符如下：             转换符            说明             示例                  %s            字符串类型            "mingrisoft"                  %c            字符类型            'm'                  %b            布尔类型            true                  %d            整数类型（十进制）            99                  %x            整数类型（十六进制）            FF                  %o            整数类型（八进制）            77                  %f            浮点类型            99.99                  %a            十六进制浮点类型            FF.35AE                  %e            指数类型            9.38e+5                  %g            通用浮点类型（f和e类型中较短的）                               %h            散列码                               %%            百分比类型            ％                  %n            换行符                               %tx            日期与时间类型（x代表不同的日期与时间转换符 |      |
| objects | Object… | 需要被格式化拼装的对象，其数目与顺序需要与格式化模式中的转换符保持一致。 |      |

 

### SubString

#### 声明

```
String subString(String  field, int start [, int end])  
```

#### 说明

​	 对字符串取子字符串，start表示子串的起始位置，若无end参数，表示取到字符串结尾。若指定end参数，则取到end所在位置的字符，但不包括该字符。  

#### 参数

| 名称  | 类型   | 说明                 |
| ----- | ------ | -------------------- |
| field | string | 字符串字段           |
| start | int    | 子串的起始位置       |
| end   | int    | 可选，字串的结束位置 |

### IndexString

#### 声明

```
int indexString(String  field, String pattern[,Boolean reversed])
```

#### 说明

​	  正向或反向在字符串中定位子串的位置。  

#### 参数

| 名称     | 类型    | 说明                        |
| -------- | ------- | --------------------------- |
| field    | string  | 字符串字段                  |
| pattern  | string  | 子字符串                    |
| reversed | boolean | 可选，是否反向，缺省为false |

### ReplaceString

#### 声明

```
String replaceString(String field, String regex, String replacement)
```

#### 说明

​	   使用replacement替换掉字符串中的regex。 

#### 参数

| 名称        | 类型   | 说明           |
| ----------- | ------ | -------------- |
| field       | string | 字符串字段     |
| regex       | string | 被替换子字符串 |
| replacement | string | 替换字符串     |

### RegExtract

#### 声明

```
String regExtract(String field, String regex)
```

#### 说明

​	   返回字符串匹配中正则表达式的第一个子字符串  

#### 参数

| 名称  | 类型   | 说明       |
| ----- | ------ | ---------- |
| field | string | 字符串字段 |
| regex | string | 正则表达式 |

### DecodeBase64

#### 声明

```
String decodeBase64(String field)
```

#### 说明

 	返回经过base64解码的字符串  

#### 参数

| 名称  | 类型   | 说明                     |
| ----- | ------ | ------------------------ |
| field | string | 被Base64编码的字符串字段 |

### StrSplit

#### 声明

```
String[] strSplit(String field, String separator)
```

#### 说明

​	   按separator做分隔符，将field字段分解为一个字符串数组。如：字符串  str = ”a,b,c”,对其进行分解strSplit(str,  “,”)，将得到数组[“a”,”b”,”c”]    

#### 参数

| 参数      | 名称   | 类型                     |
| --------- | ------ | ------------------------ |
| field     | string | 待被字符串字段           |
| separator | string | 用正则表达式描述的分隔符 |

 

## 网络对象

### ParseSyslogFacility

#### 声明

```
 long parseSyslogFacility(String  priority) 
```

#### 说明

​	从Syslog的priority中提取facility信息  

#### 参数

| 名称     | 类型   | 说明                 |
| -------- | ------ | -------------------- |
| priority | string | Syslog的priority信息 |

### ParseSyslogSeverity

#### 声明

```
 long parseSyslogSeverity(String  priority)  
```

#### 说明

​	从Syslog的priority中提取severity信息  

#### 参数

| 名称     | 类型   | 说明                 |
| -------- | ------ | -------------------- |
| priority | string | Syslog的priority信息 |

### Convert2Ip

#### 声明

```
IpAddress  convert2Ip(Object field)  
```

#### 说明

​	将给定的field对象转换为IpAddress对象，支持Ipv4与Ipv6两种格式。convert2Ip函数支持的field对象类型包括字符串和长整型。当field为字符串时，其需是IP地址的点分结构如：“192.168.3.2”；当field为长整型时，其需是IP地址的整型表示。

#### 参数

| 名称  | 类型   | 说明                 |
| ----- | ------ | -------------------- |
| field | Object | 待转换为IP地址的对象 |

### Convert2Mac

#### 声明

```
MacAddress  convert2Mac(String field)  
```

#### 说明

​	将给定的字符串转换为MacAddress。Mac地址常见格式如下："HH:HH:HH:HH:HH:HH"，"HH-HH-HH-HH-HH-HH"，"HHHH.HHHH.HHHH"，"HHHH-HHHH-HHHH"。

#### 参数

| 名称  | 类型   | 说明                    |
| ----- | ------ | ----------------------- |
| field | string | Mac地址的字符串表达形式 |

## 时间 

### Format2Time

#### 声明

```
 Date format2Time(String  field, String format[,String lang])  
```

#### 说明

​	将字符串按照给定的格式转换为日期类型。当extractTime函数无法正常抽取日期信息时，可采用此函数对日期进行有效转换。  

#### 参数

| 名称   | 类型   | 说明                                                         |
| ------ | ------ | ------------------------------------------------------------ |
| field  | string | 待进行时间转换的字符串                                       |
| format | string | 时间格式由一组字符组成，如：“yyyy-MM-dd HH:mm:ss”，每个字符都有特定的含义，如下：  Ø   ‘y’：年份，一般用 yy 表示两位年份，yyyy 表示 4 位年份。使用 yy 表示的年份，如 11；使用 yyyy 表示的年份，如 2011。  Ø   ‘M’：月份，一般用 MM 表示月份，如果使用 MMM，则会根据语言环境显示不同语言的月份。使用 MM 表示的月份，如 05；   使用 MMM 表示月份，在  Locale.CHINA   语言环境下，如“十月”；在  Locale.US   语言环境下，如 Oct  Ø   ‘d’：月份的天数。一般用 dd 表示天数。使用 dd 表示的天数，如 10  Ø   ‘D’：年份的天数。表示当天是当年的第几天，  一般用 DDD 表示，如 295  Ø   ‘E’：星期几。用 E 表示，会根据语言环境的不同， 显示不同语言的星期几  Ø   ‘H’：一天中的小时数（0~23)。一般用 HH 表示小时数，如 18  Ø   ‘h’：一天中的小时数（1~12)。一般使用 hh 表示小时数，如 10   Ø   ‘m’：分钟数。一般使用 mm 表示分钟数，如 29  Ø   ‘s’: 秒数。一般使用 ss 表示秒数，如 38  Ø   ‘S’： 毫秒数。一般使用 SSS 表示毫秒数，如 156 |
| lang   | string | 时间字符串所使用的语言，当时间字符串中含有星期，月份的文本型描述时，需要指定此字段为对应的语言才能正确解析时间。 |

### ToTime

#### 声明

```
int toTime(Object  field)
```

#### 说明

​	将给定的数据转换为时间，数据为时间的整数，长整数，或二者对应的字符串形式  

#### 参数

| 名称  | 类型   | 说明                   |
| ----- | ------ | ---------------------- |
| field | object | 待进行转换的整数型时间 |

 

## 文件

### GetFileName

#### 声明

```
String getFileName(String  filePath)
```

#### 说明

​	抽取filePath中的文件名  

#### 参数

| 名称     | 类型   | 说明     |
| -------- | ------ | -------- |
| filePath | String | 文件路径 |

### GetFileNameExtension

#### 声明

```
String  getFileNameExtension(String filePath)  
```

#### 说明

​	抽取filePath的文件扩展名  

#### 参数

| 名称     | 类型   | 说明     |
| -------- | ------ | -------- |
| filePath | String | 文件路径 |

### GetFileNameWithoutExtension

#### 声明

```
String getFileNameWithoutExtension(String  filePath)  
```

#### 说明

​	抽取filePath中的文件名，不包含文件扩展名  

#### 参数

| 名称     | 类型   | 说明     |
| -------- | ------ | -------- |
| filePath | String | 文件路径 |

### GetFilePath

#### 声明

```
String getFilePath(String  filePath)  
```

#### 说明

​	抽取filePath中的文件路径，不含文件名  

#### 参数

| 名称     | 类型   | 说明     |
| -------- | ------ | -------- |
| filePath | String | 文件路径 |

## 内部

​	内部函数是Botu提供的一组与Botu环境紧密结合的函数，它们能帮助使用者更好的使用Botu提供的各种编程便捷。

### DictMapping

#### 声明

```
String dictMapping(String  dictName, String srcField…)  
```

#### 说明

​	字典映射是一种值匹配映射，字典中预制了源值与目标值之间的映射关系，源值可以是联合值，即它由多个值组成，多个值之间是“与”的关系，即多个值同时匹配时，将目标值输出。当源值为联合值时，意味着源值对应多个源字段，即函数调用时需传入多个srcField，且需保证传入的srcField序与字典中定义的源值序对应。字典采用json描述，格式如下：  {  version:"0.1",      items:[{keys:["1"], value:"low"},  {keys:["6"],value:"high"}]  }  items为一个数组，数组的每个条目设置了一组映射规则，keys数组中描述了源值序列，value字段中描述了目标值。当输入的源字段的值与源值序列的某项匹配时，将对应的目标值输出。当未匹配任何字典规则时，输出null。    

#### 参数

| 名称      | 类型   | 说明                           |
| --------- | ------ | ------------------------------ |
| fieldName | String | 变量的字符串                   |
| srcField  | object | 需要进行字典映射的源字段的名字 |

### 1.6.2.  Field

#### 声明

```
Object field(String  fieldName)  
```

#### 说明

​	  以给定的字符串作为Botu引擎堆栈中的变量名，返回该变量的值。该函数用于特定的场景中的变量访问。一般由于某种解析函数在解析带有层级的数据时会将数据展开为点分格式命名的变量，如：某变量以a.b.c命名；由于‘.’运算符是成员运算符，故无法直接使用a.b.c的名字直接访问到变量。此时用此函数，将a.b.c作为字符串传入，如：field(“a.b.c”)，即可访问到名为a.b.c的变量。  

#### 参数

| 名称      | 类型   | 说明           |
| --------- | ------ | -------------- |
| fieldName | String | 变量的字符串名 |

## 对象转换

### ToInt

#### 声明

```
 int toInt(Object field)
```

#### 说明

​	将给定的数据转换为整型  

#### 参数

| 名称  | 类型   | 说明                                       |
| ----- | ------ | ------------------------------------------ |
| field | object | 对象缺省状态下会被转换为字符串然后进行转换 |

### ToLong

#### 声明

```
int toLong(Object  field) 
```

#### 说明

​	将给定的数据转换为长整型  

#### 参数

| 名称  | 类型   | 说明                                       |
| ----- | ------ | ------------------------------------------ |
| field | object | 对象缺省状态下会被转换为字符串然后进行转换 |

### ToFloat

#### 声明

```
int toFloat(Object  field) 
```

#### 说明

​	将给定的数据转换为浮点型  

#### 参数

| 名称  | 类型   | 说明                                       |
| ----- | ------ | ------------------------------------------ |
| field | object | 对象缺省状态下会被转换为字符串然后进行转换 |

### ToDouble

#### 声明

```
int toDouble(Object  field)  
```

#### 说明

​	将给定的数据转换为双浮点型  

#### 参数

| 名称  | 类型   | 说明                                       |
| ----- | ------ | ------------------------------------------ |
| field | object | 对象缺省状态下会被转换为字符串然后进行转换 |

### ToLowercase

#### 声明

```
int toLowercase(String  field)  
```

#### 说明

​	将给定的字符串小写  

#### 参数

| 名称  | 类型   | 说明           |
| ----- | ------ | -------------- |
| field | string | 待转换的字符串 |

### ToUppercase

#### 声明

```
int toUppercase(String  field)  
```

#### 说明

​	将给定的字符串大写  

#### 参数

| 名称  | 类型   | 说明           |
| ----- | ------ | -------------- |
| field | string | 待转换的字符串 |

##  XML

### ToXmlElement

#### 声明

```
Element  toXmlElement(String text)
```

#### 说明

​	将给定的文本转换为Element结构  

#### 参数

| 名称 | 类型   | 说明              |
| ---- | ------ | ----------------- |
| text | string | Xml结构的文本数据 |

### XPath

#### 声明

```
List<Node> xPath(Element  element, String xPath)  
```

#### 说明

​	在xml结构中抽取xpath描述的节点

#### 参数

| 名称    | 类型    | 说明        |
| ------- | ------- | ----------- |
| element | Element | Xml结构对象 |
| xPath   | String  | XPath       |

##  其它

### Len

#### 声明

```
 int len(Object  field)
```

#### 说明

​	返回对象的长度，支持的对象类型有String，Array，Collection以及Map等，返回对象中的元素数  

#### 参数

| 名称  | 类型   | 说明 |
| ----- | ------ | ---- |
| field | object |      |

### SimpleMapping

#### 声明

```
String simpleMapping(String  field, String mappingRules, Object defaultValue)  
```

#### 说明

​	简单映射是一种值匹配映射，与字典映射逻辑相同，但其映射规则通过参数传递，便于规则较少，不需要维护字典的应用。其映射规则采用json描述，格式如下：  { “INFORMATIONAL”:1,”NOTICE”:2,”WARNING”:3}。简单映射使用字段中的值与规则中的key进行匹配，返回匹配后的映射值。若映射规则中无法找到相应的映射规则，则返回函数中设置的缺省值。 

#### 参数

| 名称         | 类型   | 说明                       |
| ------------ | ------ | -------------------------- |
| field        | string | 进行值映射的字段           |
| mappingRules | string | 映射规则，采用json格式描述 |
| defaultValue | object | 缺省值                     |

# 附录

## 数据采集器保留字段

| 字段名           | 字段说明                                                     |
| ---------------- | ------------------------------------------------------------ |
| MS_SRC_ID        | 数据源的ID                                                   |
| MS_SRC_NAME      | 数据源的名字                                                 |
| MS_SRC_TYPE      | 数据源的设备类型，如：Firewall/Topsec/Topsec 4000            |
| MS_COLLECT_TYPE  | 数据源的采集类型，如：Syslog、File等                         |
| MS_SRC_DATA_TYPE | 数据源的数据类型，如：Config、Log等                          |
| MS_SRC_ADDRESS   | 数据源的IP地址                                               |
| MS_SRC_PORT      | 数据源开放的端口，主动采集时，数据源开放的端口，如进行数据库采集时，数据库开放的端口。 |
| MS_SRC_DATA      | 原始数据                                                     |
| MS_SRC_OBJ_NAME  | 数据对象的名字，如：文件采集时指文件名；数据库采集时指表名。 |
| MS_ROLE          | 角色，在进行主动数据采集时所用的采集帐号的角色               |
| MS_USER_NAME     | 用户名，在进行主动数据采集时所用的采集帐号的名字             |
| MS_MODIFIED_TIME | 文件的最后修改时间                                           |

 