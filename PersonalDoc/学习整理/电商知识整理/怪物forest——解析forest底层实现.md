# 第一页



"怪物"弗瑞斯特一一解析fore-据包底层夢 

发布于2017-旧-20 07:3&25 

修改于2017-12-0617:28:47 

⑦ 

序 

每天夜深人静时，这个g物总会掀起一片血南羅风，饱受摧残的人们苦不堪亩，却只能默默忍受。 

"忍？为什么要忍？我们不能杀了它吗I ？” 

"当然不能！杀了它我们可部得挂！" 

想看小说的可以散了， 这是一篇技术分享。 forest, 截止至今，为集团各Bu近千个应用，共计5W+客服端提供数据包服务。使用forest数据包，能够以极高的性 能读取类目厲性数据。而类目厲性数—为商品的核心元数据，使得许多应用强依赖foreM,可以认为它是一个极其 重要的"业务中间件"。 

但是，较离的内存占用加上类目厲性数据与日剧增，令不少应用负载重重。而每夜数据包更新时，为保证更新朗间数 据的可读，foBg内部会等待新索引完全加载完成，才会释放掉旧索引，因此在这几秒之内同时存储了两份索引。如 果—的是较大的数据包，索引切換期间内存占用将大大飘升，易弓I起FUII-GG、 load親高，甚至影响业务服务。 是时候掲秘一下这个怪物了。 

第一章-"小怪兽”弗瑞斯特 

交易使用的纯类目数据包，数据包压缩包3M,解压后17M，内存占用50M,怪兽长大前都是可以做朋友的，我们先 拿这个数据包看一下结构。 for�数据包.分为索引文件、存储文件以及字典表文件三种。 

如何根据一个类目ID取到类目对象。/� foBst客户端启动时，会加载索引文件’将所有类目反序列化为对象放 在一个数组里.数组是按类目ID排好序的。根据ID窒找类目时，通过二分査找，就能快速定位到位置并取出对象，如 下图所示。 readServlce.getCategoryByldl 1512); 

� 

1 

|2) 

(3) 

oiwoni 

KCfCltl I 29(aH»|tAt| 30(111(1 

,5,21*机， 

© 

第一页

韋到一个类目对象，实际场g中可能需要取到类目的以下信息：

使用 TextGrabber 捕获 www.textgrabber.pro



# 第二页

CategoryDO { .� int categoryld; //类目ID, —般是通过ID状取g曰M累 

StdCategoryDO parent; //父类目对象 

List<StdCategoryDO> children; //子类目对象 

String name; //类目名称 

String memo; //类目描述 

String features; //类目特征，格式形如"k:v:k:v,-" int catType; //类目类型 int channelld; //渠道ID,每个类目分脚不同渠道 int sortOrder; //类目排序值，越小越靠前 int coironodityld; //品类工D 

<gr 

而这座信息，是分散存放在索引文件、存储文件以及字典表文件三者中的。 

索引文件(index} 

首先我们看下索引文件，对于只有类目的数据包来说，索引文件里很简单，就只有类目的索弓I信息。 

可以与上面的cat印0~m对比。 

(l)paBnt与children, 在索引节点中有对应的parentld字段与children数组，要获取父/子类目对象，根据其id再进行次或n次二分窒找即可。 

(2)name. memo与fmtums,在索引节点中，井没有strjng型的字段，取而代之的是nameld、 memold以及 featureld,这些Id存放的是具体字符串在字典表中的位置，字典表结构见下文。 

口)剰余的catType、 channelId, sodorder以及commodityld字段，在索引中没有对应的字段，只有一个mcordld， rwod的作用见下文存储文件的结构。 

存储文件(yoB) 

除了索引信息，其它字段大多放进了存储文件中。 

存储文件通过MappMB'0BUKer映射到埃外内存，结构如下图所示。 

Record 0 Record 1 Record 2 Record 3 Record 4 

meto head 

@ 

catType channelld sortOrder conrwodityld 

存储文件里存放的部是定长的数据，当需要取某个类目的存储信息时.从索引结构中获取到rModld,然后通���

使用 TextGrabber 捕获 www.textgrabber.pro

# 第三页

力方法即可计算出对应信息在文件中的位置。 int getpositionfint metaBeadsize, int recordld, int rec。lu…_, return metaHeadSize + recordld * recordSize; 

取到position后，根据position和reGodsize从整subBuffero 

��w��a���rfws 

<y 

Buffer subBufferfBuffer buffer, int position, 

Buffer subBuffer = buffer.duplicate(); subBuffer.position(position); subBuffer.limit(recordSize + position); return subBuffer; 

int recordSize) { 

随后即可从subBueer中获取各个字段的信息 

CatRecord getcBtRecoFd(Buffer ID) { 

CatRecord record = new GatRecord (); record.catld = in- getlnt ()'• record.catType = ln,getlnt()'• record.channelld = in.getlnt(); record.sortOrder = in.getlnt()/ record, conunodityld = in*getlnt(); return record; 

} 

!7£ 

forest单个存储文件小的上百M, 最大的已经超过1.3G, 针对大文件，内存映射读取速度比1/0流快的多。通过内存映 射交给系统层面的swap分区进行管理，能减少许多内存压力，同时保证性能。 

字典表文件{string dictionary) 

字典表文件，其实很简单，它存放了本数据包中使用到的所有字符串，比如类目名称、类目属性別名、类目属性值的 特征等等。如下图所示。 

33 

结构是长度+对应长度的字符串内容。索弓I中的nameld、 memold, featureld, 即是一个个指向字典表中的指针。 

为什么单独把字符串提取出来存放？ 

(1)如果把strl'ng型字段也放在存储文件中，会大大增加对象反序列化的耗时。 

(2)使整个存储文件中结构化极强，每个数据记录都是定长的，能够快速计算出记录对应的文件位置。 

(3)方便做到客户端的向下兼容 

(4);t�Se<]�@®ft/�@g�g�eat�e��Sffl[5]6<], ~&��\W��»\t, -ttSSr>�-t¥6<], B遍 

(= 

-手■力 

\ lo|y 

Si |n�|yl~ 

"墓二乂一 

5 一根一M ■口 

一个一K 

一是一力一目， 

_这一力■奥 

一机 ly 一芋. _奥一� Itily 一核一 d 一这

使用 TextGrabber 捕获 www.textgrabber.pro



# 第四页

/ 

舌择趟过50%的重g字符串。 

合影 

l*fbt() jj'rrpftR) 

⑨ 

可以参考图中红线指向，理解索引、存储、字典表三者的联系。 第二章-长大后为什么不快乐 

以lc使用的数据包为例，压缩包540M, 解压后2.8G, 内存占用950M。（以下计算皆以IG数据包为例) 这回先看合彩，如下图所示。 

-—.—二=::: 二二二: 二：二=�■m=:=二 d.=. 

"-''——nmramfli 

��gum，"�� 

—供-，l": 

isSSiZ 

\ 

::::::::.-:-=,:aBa'。==:: 二 d 

驟gB國p園PBmBBBDg超■园 nianBignangniBiBinaiaia 

口mgp轉琴鐵■&隨50脇 

I 

g 

§ 

I 

目 

类 g 

I s 

分 

E 

\r' w !' r 

Tl!> 

s, 《+ 而

使用 TextGrabber 捕获 www.textgrabber.pro

# 第五页

/ 

ao'nii'wsniy� n KAWQ ��b� 

带上属性、属性值后，唯一变得复杂的就是索引结构。 

其实类目上除了存放本身的信息，还存放着一个厲性对象的数组.接口透出为List。数组也是按ID排好序的，通过二 分査找可快速定位到确定的属性对象。 

CategoryDO { int categoryId; //类目ID, —般是通过ID获取类目对象 

StdCategoryDO parent; //父类目对象 

List<StdCategoryDO> children; //子类目对象 

List<CategoryPropertyDO> catPropList; //类目厲性对象 

厲性对象是下面这幅模样。 

CategoryPropertyDO{ int categoryld; //类目ID int propertyld; //厲性ID 

String name; //类目属性名称 

String memo; //类目属性描述 

String features; //类目属性特征 int sortOrder; //类目厲性排序值，越小越靠前 boolean isKeyProp; //是否是关键属性 booIean isRequired; //是否是必填属性 

List<CategoryPropertyValueDO> catPropValueList; } 

//类目厲性值对象 

但通过类目属性取类目属性值，又有所不同。整个数据包里，数量最多的就是类目厲性值 (1800W+) , 针对这部分 数据，for�中对其做了去对象化存储。在索引内存区中，属〒类目属性值的只有两个基本类型的数组。一个是long 型的，�个元素都是一个属性值ID, 对应合影中的keYstorageD;另一个是int型的，每一个元素都是该类目属性值 的rModld, 对应合影中的Bcordstoragea。因为除了这两个信息，其它字段都放在堆外了， BGOrdld就是信息在堆 外的地址。两个数组是严格对齐的。 keYstomge数组中，可以看到图中下标从101到105之间的属性值是按ID排好序的，从159到164之间的属性值是按ID 排好序的，因为101 ~105这5个属性值是挂载品牌属性下的，而159〜164这6个属性值是挂载颜色分类属性下的。即 在同类目厲性范围内，是按厲性值ID排好序的，目的还是为了二分査找。 

为什么类目厲性（CP)不做去对象化？其实三四年前的设计思路里是打算将GP、 GPV—同去对象化的，现在数据包 中cp数量是200W+,是当时的5倍左右，现在CP占用内存150M，估算在当时只占30M, 做了去对象优化收益也不 大。但放在现在，这个收益还是很大的，优化B列入后续的Action中。 

到此为止’索引结构我们介缀的差不多了，但在forest中，像这样的索引，最多会有三个。合彫中的是后台类目-属 性-属性值索引’包含前台类目的数据包还会有一个前台类目-厲性-属性值索引，结构是完全一样的’除此之外’还有 一个基础属性属性值的索引，和前后台类目索引略有不同，不过原理类似，就不在此过多细挖。

使用 TextGrabber 捕获 www.textgrabber.pro

# 第六页

放上一个比较全面的结构图。 

,� 

Cm/5td CPV index 

�y • �* * �* i �� * |�B B*� • �» • I�B • �� • a 

--tl_L 

CotProplndexer 

CatProplndexef 

CatProplndexer 

capi C3P2 H 

、 � 、 、. i 

|V) I V2| Vl| VS 

CatPropVoiuelndexer 

�;' I .n I p3.Ji 

/ pro(j' 

±—�r 

E21 

�-�7Z-h 〜 /'\ ' 〜 二〜',7\& \— ‘ 

Valuelnd&xef 

»"—'.•—"—>" 

0 

一个IG使用全量数据包，需要950M左右的常驻内存，切換索引时，内存还要增加750M, 总计1.7G。听，机器哭的声 音。（|0机器堆内10个6， 听，穷机器哭的声音） 

第三章-全量之外，谱量应运而生 

for�已经两三年没有在白天推包了，为什么昵？简单说，就是推了要出事。 

不简单的说，原因有二。 

(1)索引切換过程中的双份索弓I对内存的占用过高，白天业务在高峰期，推送极有可能影响各个链路的服务。 

(2)是多年来与各行业类目运营小二达成的共识，类目数据一天一变更，白天的操作到凌晨推包后才会生效。而白天是 运营同学記置的时间，如果推包，难免会出现运营同学配置到一半，导致半成品推到线上，影响商品的发布与编辑。 它就像一个几百人维扩的代码库，每次配置都是一次代码提交，而只有每天凌晨，才会正式发布一次。 

但一次错误类目数据的上线，就有可能导致客服投诉电话排队上百、客户商品无法发布甚至大型促销无法进行等情 况。forest能做什么？它什么都做不了， 推包的风除巨大，大到我们只能告诉运营同学，"不好意思，这边推不了， 等 到明天凌晨推包就解决了。” 

类目厲性每天的数据变更量是非常有旧的，如果能够每天以增堡的方式更新数据包，就能让这个怪兽安安静静，面3 I? 放障，也能够快速购应。 

但是forMt的内存架构、索引结构如此复杂，如何做到增置更新呢？即使破釜沉舟把索引改了，万一要回滚，SP£|» 

''…�bw.'.��。 *� r-i *<*i. I . >-Lj . It t. �.+t- -J- II t. J.+* nn n nn I %.�� Tl'," I" ,•".'• "

使用 TextGrabber 捕获 www.textgrabber.pro

# 第七页

/. 

迁付卻KM翻。热以~卻刘垣，几阳具玲里g'aju状，..他通1±1迅独，滴W讲山閃；1B懷出滔愤，明g照入)工。-Xl. 必組结于全置的内存架构。 g终，我们另辟踢径，决定开辟一块新的内存区存放增量数据。所有接口调用，先从增量内存区里窒找一次，有数据 则直接返回，没有则再到全量内存区中访问。这样不会影响全量索引结构.需要回滚时把增量区释放干净即可。 而增量数据通过对接精卫，监听MYSQL的binlog获取，整体架构如下， 

g—m分■—'1 

S-binl°9*��»-»a»J 

I ami I I Clenl I I Client I I Ctont I I dent I I Clant I 

淘系的类目体系是公认的复杂，在这样到处充斥了业务逻辑的生态里做增量，嘴上说说是简单的。 

比如有个类目想当富二代，今天換个了父类目，但是它继父可不知道自己多了个娃（因为父类目本身是没有变化的， 变的是子类目的parentld), 总不能在调用getchildren接口时，遍历所有类目问一遍"你爹是我不？". 

同理，給某个类目加属性（类目本身不知道自己多了属性），给某个属性加属性值（属性本身也不知道自己多了属性 值），都会碰到此类问题。 

在此，引入两个概念，第一个是Action, 即变更动作本身，Action类型分为INSERTWPDATEmELRE; 第二个是 Relation, 即由Action引起的关系变更，Relation的类型分为ADD/DELEm。 

举例如，D类目的父类目从B換成G, 这是一条AGtion, 它将产生两条Aelation: 

(1)B与D断绝父子关系。 

(2)G喜得贵子D。 

怎么生成两条Relation的？我们可以先看一下增量Action在DB中的格式，如下图所示。 

ActtonlD iinaa (lanPayload ftaiePoyload 

11926.5 lclfcaLid-ao fIsJear'''1"cot_fMme""j!BO"."catJd":'300"."cat_type":"3".1eo Ps_lear"l';'cat_nam8':"!liBO'.'catja":"300,"cat_type""3~"taa in tL»ai'''*:bom!n8:1 ;''."parenl_kT:" 100',"gm1_modifiecfJ'201 7-09-27 tu-es"/l;boming:l:""porent_icT':"2CC';gTi* n-t)dried':'20]7-CT.27 

]600:]3.a',"del8ted"'U-;'amLaeate-:'2017W-27 15:&i:57.0'.-io 16:05:29.»'.-deleted-:-0-,-gmt aeale"~Xll7-OS-27 15:5<:57.(r,-so iL»a«i""4M1 d_adw<", 

我们会把一次UPDATE的操作按上图格式存储，其中包含变更修改前与修改后的paYload,看到这条Action,修改前是 {"catjd":"300","parent_id":"100",...}, 修改后是{"catjd":"300",l'parentjd":l'200",...}， 据此我们能够很容易地算出 父类目的变化。针对父类目变化的情况，产生一条DELRE的Rslation记录''类目B少了D这个子类目,,；再产生一条 ADD的Relation记录"类目G多了 D这个子类目"。

使用 TextGrabber 捕获 www.textgrabber.pro

# 第八页

■产生两条RelQtbn 

工7-,厂&7,.,.、 

ES33a�iiB�S3I3;!£?TWBa1 

这样，无论B还是G在获取所有子类目时，都有据可依，只需要从Relation中査询一下是否有关系的变更，并根据 Relation进行一次合并即可计算出最新的子类目信息。 

那么在增量内存区中，其实分为两个部分，一部分存放各种Actjon， 另一部分存放各种Relation， 如下图。 

可以看到除了类目还有许多类型的Relation, 不一一在此细说。 

这么多业务逻辑，任意一处出错，都可能引起蝴蝶效应，如何保证增量和以前全量的逻辑一致昵？ 

在每天凌晨构建增量数据包时，会以全量数据的结构为参照，同时构建出当天的全量包与增量包，并在服务端启用两 个客户端一个读当天的全量包，另一个读当天的增量包加上前一天的全量包’两个客户端对所有类目所有属性所有 属性值的所有字段做一一对比，如果出现不一致则马上切換为全量包推送，保证了增量数据包的可靠。 

线上一天的增量Action通常在3W到10W条之间，打出的增量包大概是3M到10M,这样一个数据包即使推给类目重度 用户，对客户端造成的抖动完全可以忽略。但是，线上整个数据包构建+推送过程耗时在2小时上下，依旧需要等到我 心憶俾。

使用 TextGrabber 捕获 www.textgrabber.pro

# 第九页

»n, Rg环明粗找推一把数g 88 

F@ 

n-."

變■._mg俯 

Or 

•t 

最媳旭的事莫过如此。 

小墙量 

"只改了个类目特征，想日常顺发验证一下，还得等那么久？” 

有句话说得好--”青春可不能耗费在等forest推包啊！", 为了解这个问题，我们开发了小增量功能。顾名思义，就 是只打一个很小的增量包，里面有哪些数据，任君挑选，秒秒钟完成打包+推包。操作手册在此，欢迎自取。 终章 

fore轨数据包的相关优化仍在持续进行中，增量是针对现有架构做的一次革命，是解决线上故障、提高效率的一大利 器。 

而本人作为fomst受害者之一，私以为forest全量结构的设计还是相当巧妙的，而forest几年来产生的业务价值’绝不 逊于'‘怪物"之称，—向前辈们致敬。但随着类目属性数据的膨胀以及业务的高速发展，如果forest只有一个全量 包，那么恐怕是要变成g正的"怪物"。听过温水煮青娃故事的人，恐怕也常常没有意识到自己就是那只青娃，希望我 们这几只青娃，反应的还算快。 

附录 

t1] lc使用的数据包cairocatForlcDO heap dump见下图。 

~肖|||.— 

@ 

lh"s 

» Q aani.tldbNJWNi.NBnJWt.brt.waifticiOiNwndNi • ONBMSWOOI � D �..—�������—��n�—� � 一■■."—

使用 TextGrabber 捕获 www.textgrabber.pro

# 第十页

'"'"""'■�"'n'n�"~<~«WTO.»»»,, J •»<-««<»» •n.h>w»tB6B»hr • Otffcuao 一 

二=tD,x@Dh ■&■■斜I, .»»M�. O.�.C.�U,,.. 挪60M$$; —ielndex■■蜀■gl, �130M� •~"1""'~"'™[2]截止至2017年9月20日,数据包中类目相关数量统计如下表。 

数据类型 ：数量 

类目 32246 

基础厲性 491152 

•C3 

基础属性值 3215229 CP I 2251232 CPV 18097446 

PV 

531 7929 

p]本文大部分数据都是使用cairocatForlcDO类型的数据包计算出来的（计算时间：2017年9月20日），如果各位想 了解其它数据包的大致数据，可参考下表。比如sell使用GairostdGatDO包，在各项IC数据包数据基础上，乘以 

86.2%, 即是大致估算值。 

数据包类型 

CairoCatForlCDO CairoAIIDO 

CairoVerticalD0542 CairoUstDO 

CairoPaiMaiDO 

’CairoRleDO 

较■c数振包占比 100力％ 

15L0% 

107.1% 

107.1% 104.0% 102.9% 

CairoVerticalD02 

CairoDO 

CairoVerticalD014 

CairoFoodCatDO 

CairoVerticalDO11 

; CairoTradeAndVirtualDO 

100.4% 99.6% 95.5% 91.3% 90.6% 90.6% 

! CairoAndMarketGTStdDO 90.6% , CairoTmallStdCatDO 89.8% CairoTeMaiDO 89.3% 

: CairoAndGTStdDO 87.3% CairoStdCatDO 86.2% CairoCatMap12DO : 37.0% CaimCatMannn .1fi7% 

fe.t-A; - >"

使用 TextGrabber 捕获 www.textgrabber.pro

# 第十一页

■蓋1—2 S%»a� 

CairoPropertyDO CairoUfeTuanDO other 

34.1% 23.4% <21.2% 

<�XT 

【41如对文本类目相关名词不太了解，可阅读：类目体系高级手册 

旧如想了解Fomst设计思路，可阅读：The New Forest:解析Forest 3力的基本原理和设计思路 

发表〒國子：共享_商品平台 

知识体系：索引 存健 El 

文韋标孩：Forest 类目 Ll 

文章类型： B 

酎加揉作：内部资料请匆外传 作者原创 

it 

■春參数⑩⑤◎ ◎參參—⑦翁 ②◎◎參參◎◎参★⑩⑩◎象參參 1卷春◎卷參◎鬱參 

L—g: 1—期-来自g进的百技心得 

下一篇：趁若FOBSt与SQUte的因缘际会，来… 

写下你的评论 

@ 评论 

精彩评论（6条） 

问

使用 TextGrabber 捕获 www.textgrabber.pro
