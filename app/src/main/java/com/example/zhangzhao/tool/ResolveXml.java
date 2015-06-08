package com.example.zhangzhao.tool;


import com.example.zhangzhao.entity.Article;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by zhangzhao on 2015/1/20.
 */
public class ResolveXml {
    public List<Article> readXML(String text){
        try {
            //创建解析器
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();

            //设置解析器的相关特性，true表示开启命名空间特性
            //saxParser.setProperty("http://xml.org/sax/features/namespaces",true);
            XMLContentHandler handler = new XMLContentHandler();
            //saxParser.parse(Html.fromHtml("![CDATA[<article><title>感恩的心</title><author>陈麒凌</author><content><div class=\"articulo-contenido\" style=\"box-sizing: border-box; font-size: 14px; line-height: 1.67; margin-bottom: 40px; margin-top: 40px; color: rgb(51, 51, 51); font-family: 'Open Sans', 'Helvetica Neue', Helvetica, 'BBAlpha Sans', 'S60 Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 'WenQuanYi Micro Hei', sans-serif; background-color: rgb(255, 255, 255);\"><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹是初二下学期退学的。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">08年3月，春天，她坐最后一排，背后就是红红绿绿的迎奥运黑板报，不小心衣服便抹了几道粉笔彩。小心也没用，人太多，座位太窄，她又长得有点胖，十五岁，一百五十三斤，行动总有点笨。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">那学校叫龙凤中学，民办的，意思就是望子女成龙凤。去那儿上学的都是外二代，希望借读书改变命运的外二代，因为目标清晰迫切，学校的课程也直奔中考主题，音乐不学美术不学任何文艺演出体育比赛春游秋游都不。体育课只狂练跳绳踢毽子两百米，中考会考嘛。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹成绩还算可以，除了数学有点困难。新来的数学老师姓庄，未婚大龄男青年，鼻子上常年粉刺块垒，火气比较大。春天上课易犯困，又是数学课，姜彩虹就趴在桌子上睡着了。数学庄老师最忌别人看不起他，上他的课睡觉就是严重看不起他，于是他把姜彩虹叫起来噼里啪啦骂了一顿，其中有一句也不知是怎么想出来的，“你照照镜子看看你，奶子晃来晃去地能当妈了，还不好好听课！”同学们都配合地哄堂大笑。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹回家就说不想上学了。她爸老姜在一家五金厂里做模具师傅，她妈郭姨在五金厂做保洁工作，她哥姜国政高一读了半个学期成绩实在跟不上，去年春天刚进厂做学徒，流水线一天做足12小时，天天叫着要辞工。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">老姜劝女儿读下去，从自己年轻时进城打工吃过种种的苦，到厂里谁谁的孩子考上大学从此过得多好多好，从自己年纪越来越大身体越来越差，到全家的光荣和以后的指望。老姜苦劝，劝着劝着就成了求，他是个感性的人，自己把自己说哭了。姜彩虹也哭了，可是哭完还是那句，不想上学了，就是不想上了。郭姨比较容易认命，说龙生龙凤生凤老鼠崽子会打洞，不是读书的根种，就是进厂打工的命。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">老姜没让女儿进厂，他花了两万块盘下一家小杂货店，姜彩虹从此就坐在玻璃柜台后面，有人来的时候卖东西，没人来的时候看电视，天天就这么过，也没同学来找她，她也不去找同学。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">小店开在工业园，周围都是厂，下班时间，各家厂子的打工仔穿着各色的厂服来买烟买电池买饮料买泡面，他们吵吵闹闹地敲着柜台喊小老板，快拿开水来！小老板来点辣椒酱！姜彩虹忙得有点乱，弯腰碰倒了汽水瓶子，举手撞翻了零钱盒子，抬头撞了后脑勺，老姜有时来帮忙，就在一边笑话她：“活该，谁让你胖。”姜彩虹比读书那会儿又胖了些，只因整天不活动，吃零食又顺手。她也渐渐继承了她妈的容易认命，表现在人家说她胖的时候，她不生气，只是伸伸舌头笑一笑，最多还一句，关你什么事。老姜在小杂货店帮忙的时候，心情总是特别好，打工仔们嘴巴都甜，一声一声叫他老板。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">第一个来小店买东西的老板，真正的老板，是欧连吉香料化工厂的总经理杨怀德，不过当时没人看出他是老板。他们二十多个人说说笑笑地走来，男男女女一水穿着橙子色的厂服，那是非常漂亮的橙子色，晴朗天气里有风有香味有光泽的橙子色，姜彩虹老看他们的衣服，她觉着这厂服太好看了。橙子色厂服里有个四十多岁的白脸男人，他请客，请每个人喝红牛，而且都要银罐的。老姜说真阔气啊，几个打工仔喊起来，“那还用说，这我们杨总！”杨怀德就笑，“叫老杨叫老杨，我也是农村仔出身，番薯屎还没拉净呢总什么总！”番薯屎这句很好玩，后来姜彩虹常常拿这个来笑，她和老姜说，“爸，老杨真不像老板呵。”老姜说，“老板就是老板。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹走进欧连吉，是五月的一个周末晚上，送货。她和姜国政把两箱2.5升的饮料搬到会议室。很热闹，那些橙子色的厂服围着个大蛋糕，手拉手唱歌转圈，姜彩虹倚在门边看，姜国政不等她，先走了。这时杨怀德从后面拍拍她，示意姜彩虹进去一块儿玩，姜彩虹抱着门框笑，摇摇头又低了头。杨怀德说没关系，生日会，吃蛋糕去。姜彩虹问谁过生日，杨怀德说：“凡是五月份生的兄弟姐妹，都一起过生日，来吧。”姜彩虹慢慢地跟进去，远远地看着他们唱歌、许愿、发礼物、切蛋糕，用奶油互相糊鼻子，杨怀德的鼻子也被糊了一块，笑死了。有个香香的长发女孩分了碟蛋糕给她，问她叫什么名字，姜彩虹说了，长发女孩说，“就叫你彩虹妹吧，你叫我建英姐！”然后又有几个人——叫国玉姐志光哥丽萍妹阿荣仔的身上都香香的——拿西瓜拿饮料拿薯片拿果冻给她吃，面前的小桌子都满了。这么些好吃的，这么些哥姐弟妹地叫着，姜彩虹觉得稀奇又温暖。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">她没怎么敢吃东西，蛋糕也只是抿了一点点，后来杨怀德过来让她多吃点，她就说自己这么胖，还吃？杨怀德很正经地说，“你不能老是注意这个，胖不是缺点，只是个特点，谁还没个特点呢。譬如说我左脚有六个脚趾头，小时候我觉得很丢人，一年四季都穿袜子，后来想，我又没偷没抢，我就是六个脚趾头的老杨，哈哈哈哈又怎么样呀！”姜彩虹也笑了。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">那晚姜彩虹回去，建英姐几个一定要送她，出来的路上指指点点说那是宿舍这是食堂那是车间这是仓库的，建英姐的手臂长着些红疙瘩，她指一会儿就挠一会儿。高高的石棉瓦车棚下，停着部红色的很大的越野车，建英姐说那是老杨的车，很高级，七八十万呢。她告诉姜彩虹，“我们坐过，有一次去逛街，老杨顺路就搭我们去了。”姜彩虹说老杨真好，建英姐点头，“嗯嗯可好了，一点架子也没有，跟自家大哥一样。你来，你过来。”她挠挠手臂，把办公楼上方的红标大字指给姜彩虹看，暗暗的路灯下，仰着头依稀认出来，那字是，员工就是我们的兄弟姐妹。建英姐说，“是吧，没错吧，连我们家的口号都跟别人不一样。”姜彩虹还在那儿仰着头看，“嗯，我生日也是五月份的。”她没实说是农历五月。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">就像当初铁了心肠要退学，这次，姜彩虹铁了心肠要进欧连吉。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">郭姨还是那句，就是进厂打工的命。老姜却挺生气，女儿大了不好骂，便又费心劝了一大通，进厂很累的从早干到晚去个厕所都不痛快，就是个机器木头人，做错一点要扣钱动作慢了要扣钱请半天假也要扣钱，你要老板钱老板要你命！打工仔很苦的，你又胖你又笨，你做不来的，你吃不了那个苦的。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹不听，她进的不是一般的厂，她进的是欧连吉。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">她没把握欧连吉会要她，面试的时候老说错话，初中毕业证又是假的，谁知第二天人事部就通知她上班，简直像做梦。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">欧连吉每个新员工都要喝杨怀德一杯茶。这次入职7个人，轮到姜彩虹的时候，已经快中午了。总裁办公室的门总是打开着，好像人人都可以进去，随时都可以进去，办公室不很大，书架地图地球仪好像老师办公室。杨怀德双手敬过一杯茶，姜彩虹接过来喝了两口，还是有点小羞涩。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">杨怀德亲切地看着她，“彩虹妹，你有梦想吗？”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹说我不会，我没什么文化。杨怀德启发她梦想就是你最想做的事，最想要的东西，最想成为的人。姜彩虹想了想，还是不懂怎么说，不会说。杨怀德笑着说没关系，欧连吉没有最低学历也没有最高学历，学识外貌出身在这儿都不重要，人人都是兄弟姐妹，人人都平等友爱。他叫她彩虹妹，他像个大哥。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">“最重要的是什么，是这儿。”杨怀德指指自己的胸口，“是一颗怀抱梦想的心。怀抱梦想的心，不在乎一时的困难，不计较眼前的利益，怀抱梦想的心，有的是激情和实干，相信自己的潜能，发挥自己的天赋，实现自己的梦想——你有很多的潜力和天赋，你不知道吗？”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">“我，我哪里有。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">“有，你绝对有！欧连吉会帮你找到的，欧连吉也会助你实现梦想。”杨怀德站起来，张开手臂，“彩虹妹，从此刻开始你就是欧连吉大家庭的一员，欢迎你来到欧连吉的怀抱！”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">他使劲地拥抱了她一下，有点抱不过来却仍很努力地，这让姜彩虹十分窘迫，出来的时候都没敢抬头。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">人事部经理雪云姐正好找她，“彩虹妹，你的厂服要下周一才有，我们专门为你定做的，到时候穿上可漂亮了。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">果真周一升旗的时候厂服就穿上身了，橙子色的厂服，非常漂亮的橙子色，晴朗天气里有风有香味有光泽的橙子色。姜彩虹老是低头看衣服，不相信真的穿在自己身上了，呵，真的穿在身上了。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">杨怀德在和大家讲梦想，他说欧连吉的梦想就是让全人类享受到最优质的香味，欧连吉的每个兄弟姐妹都在为全人类造福。姜彩虹睁圆眼睛，觉得这梦想远大得不敢想，可周围的橙子色厂服那么整齐热烈地喊着，欧连吉，欧连吉，她又觉得不那么远了。接着是齐唱《感恩的心》，齐唱的歌就像大海浪，把你翻天覆地包裹着，你在唱却听不到自己的声音，听不到自己的声音却奇怪地感到放心，感到安稳，感到依靠。唱到高音部那句的时候，姜彩虹忽地鼻子酸了一下下，莫名地有种冲动，她想用整个命去爱这里，爱这里的每一个人，她什么都愿意干她什么都愿意给。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹刚上班就遇母亲节，每个员工都有一枝康乃馨，还有杨怀德亲笔签名的贺卡。姜彩虹拿回来送郭姨，读给她听，“亲爱的妈妈，祝您节日快乐！感谢您为我们培养出优秀的姜彩虹妹妹，她工作认真努力，是我们欧连吉的骄傲！杨华德携五百三十二位兄弟姐妹上”。郭姨瞅了眼问这真是你老板写的？姜彩虹说是，532张都是他亲笔签名的，你用指头擦擦都能沾到墨水。郭姨说你们老板还挺好的，姜彩虹说是啊老杨人可好了，就跟自家大哥一样，比自家大哥还要好呢，他从来不骂人他总是笑的，他也不会看不起人，他还教我们打羽毛球教我们唱歌，他跟我们穿一样的厂服，他吃饭也和我们一样在食堂排队，吃完也排成一排在水龙头底下洗盆子漱口。老姜不以为然说，算了吧，老板就是老板。郭姨却寻思另外一回事，要是我真有五百多个子女，每人过节给我一百块，我就发了。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹每天回来身上都香香的，车间里带出来的味儿，她又舍不得换下橙子色厂服，老姜鼻子敏感，于是总打喷嚏。老姜觉得这香味不好，老劝女儿辞职，说香精厂有毒，没结婚的小姑娘会中毒，不如过来五金厂跟自己学技术，将来再差也能当个师傅。姜彩虹老不听。这天早上上班，老姜又提了一遍，因为早上起床睡意未消，口气就有点冲。姜彩虹现在也敢顶嘴了，顶嘴也一套一套了，她穿上橙子色的厂服好像就把脾气鼓得足足的，“我不去，我们那儿的人跟你们不一样，我们谁也不会笑话谁谁也不会算计谁，我们那儿最累最危险的活大家都是抢着干的，连生病了都不愿意休假，我们加班也是抢着的，可不是为了加班费，不像你们眼里只盯着那点钱，加班费少一块都不干，背后净说老板坏话，下班时间一到就拍拍屁股走，还老把厂里的零件往家偷。我就在欧连吉干一辈子，我的梦想在那里，你们知道什么叫梦想吗，你们有过梦想吗？”老姜气得大骂，姜彩虹早出门了。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">工业城这段时间换电缆，错峰停电，下午轮到欧连吉这边停电，杨怀德就让工人们自由活动。六月的天，早上下了点小雨，地上还有点湿。姜彩虹和几个姑娘出来打羽毛球，她打得不太好，就自告奋勇帮大家捡球，跑得呼哧呼哧的，一身是汗，自己还乐呵呵地说我不累，我减肥。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">杨怀德揉着一边肩膀走来，“肩周不行了，必须得打几场，来来来，打完球请你们吃肯德基。”姑娘们都欢呼起来。杨怀德球打得很好，大家都不是他对手，一个一个败了阵，杨怀德很高兴，赢一次就握着拳头做个耶的手势，可爱得像个小孩。就是这个兴头上，他发了个高远球，用力过猛，羽毛球“嗖”飞出去，竟然飞上了车棚顶，大家“哇”了一声。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜彩虹赶忙去找了张凳子，摇摇晃晃踩上去，一手拿着扫帚去拨球，可是车棚顶挺高的，她踮起脚还差老大一截，杨怀德赶紧让她下来，说算了，再拿个新的就行了。也是巧，刚好整筒羽毛球都用完了，再出去买吧，来回最快也要大半个钟头。杨怀德有些扫兴，大家也觉得有些扫兴，都不肯走，仍惋惜地拿着球拍，一边空比划练姿势，一边说说笑笑着。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">突然“哗啦哗啦”，车棚掉下个什么东西，“砰”的一声，重重砸在雷克萨斯RX350的车顶，又随着石棉瓦片和玻璃碎屑重重弹落在地。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">一个人静静地趴在水泥地上，再也没动，那个胖女孩。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">欧连吉委派的律师姓宋，戴着副无框眼镜。宋律师从黑色真皮包里拿出个厚厚的信封给老姜，说是杨总转交的两万块，本来他要一起来的，怕看到姜家人又要伤心，这几天他伤心过度，都病了。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">老姜红肿着眼皮，不接信封，“一条人命就赔两万块？”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">宋律师正色地跟他解释，这两万块是慰问金，是杨总对员工不幸遭遇的同情和关怀，不是赔偿，而且人家也没有责任赔偿。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">一边的姜国政火了，“我妹妹是在他们厂出事的，敢不赔！”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">宋律师说我理解你们的心情，发生这样的事谁都不想。姜彩虹未满试用期，没签订劳动合同，也没有购买保险。而且事情发生的时候，不是工作时间，不在工作场所，也不是由于工作原因，人家欧连吉真是一点赔偿责任都没有的。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">老姜泫然，“活蹦乱跳的一个人，早上还大声和我吵。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜国政不甘心，“老板那么有钱，才给两万块！“</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">宋律师说人家没管你们要钱就不错了，杨总的车让你妹妹砸坏了，修理费用超过二十万，杨总签了权益转让书，让保险公司全赔，算厚道了。人家老板再有钱，也不是大风吹来的，换个冷血的一分钱不掏，又能怎么样？不是人家的责任。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜国政没话说，恨妹妹不争气，“她没事找事，爬车棚顶上干什么去。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">宋律师说这就不知道了，没人知道她爬上去干什么，也没人知道她怎么爬上去的，姜彩虹是成年人，能自由支配自己的行动。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">老姜硬着嗓子，“他们车棚顶为啥不搞结实点？”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">宋律师哑然失笑，“车棚顶是石棉瓦的，就是挡风遮雨的功能，承重量很轻的，没预备让人在上面活动。”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">姜国政兀自生气，“她怎么那么笨呢，爬上去找死啊，不知道自己多少斤啊！”</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">“活该，谁让她胖！”老姜也狠狠说，说完自己又哭了。</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\">&nbsp;</p><p style=\"box-sizing: border-box; margin: 0px 0px 20px;\"><strong style=\"box-sizing: border-box;\">陈麒凌，内地女作家，曾获得联合报文学奖首奖、全球华文文学星云奖、林语堂文学奖。@陈麒凌</strong></p></div><p class=\"articulo-editor\" style=\"box-sizing: border-box; margin: 60px 0px 40px; font-size: 14px; font-style: italic; line-height: 1.67; color: rgb(51, 51, 51); font-family: 'Open Sans', 'Helvetica Neue', Helvetica, 'BBAlpha Sans', 'S60 Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 'WenQuanYi Micro Hei', sans-serif; background-color: rgb(255, 255, 255);\">（责任编辑：薛诗汉）</p></content></article>]]").toString(), handler);
            saxParser.parse(text, handler);
            return handler.getArticles();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class XMLContentHandler extends DefaultHandler{
        private List<Article> articles = null;
        private Article article;
        private String tagName = null; //当前解析到的元素标签

        public List<Article> getArticles(){
            return articles;
        }


        @Override
        public void startDocument() throws SAXException {
            articles = new ArrayList<Article>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (localName.equals("article")){
                article = new Article();
            }

            this.tagName = localName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if (localName.equals("article")){
                articles.add(article);
                article = null;
            }

            this.tagName = null;
        }

        //接受字符数据的通知。该方法用来处理读到的内容，第一个参数用于存放内容
        //后面两个参数是读到的字符串在这个数组中的起始位置和长度，使用New String(ch, start, length)就可以获取内容
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            if (tagName != null){
                String data = new String(ch, start, length);
                if (tagName.equals("title")){
                    this.article.setTitle(data);
                }else if (tagName.equals("author")){
                    this.article.setArticleAuthor(data);
                }else if (tagName.equals("content")){
                    this.article.setArticle(data);
                }
            }
        }
    }
}
