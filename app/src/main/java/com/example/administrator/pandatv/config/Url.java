package com.example.administrator.pandatv.config;

/**
 * Created by Administrator on 2017/7/11.
 */

public class Url {
    //服务器地址
    private static final String BASEURL="http://www.ipanda.com/kehuduan/";

    //首页
    public static final String HOMELIVE=BASEURL+"PAGE14501749764071042/index.json";
    //熊猫直播
    public static final  String PANDALIVE=BASEURL+"PAGE14501769230331752/index.json";
    //熊猫直播  精彩时刻
    public static final  String SPLENDID="http://api.cntv.cn/video/videolistById";
    //熊猫直播  多视角直播
    public static final  String MORELIVE=BASEURL+"PAGE14501769230331752/PAGE14501787896813312/index.json";
     //熊猫观察
    public  static  final  String GGVIEDO=" http://api.cntv.cn/apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144&serviceId=panda";

    //熊猫播报
    public  static  final  String PandaObserver=BASEURL+"xmwh/index.json";

    //熊猫播报第一个item
    public static final String PANDAOBSERVERFIRSTITEM="http://api.cntv.cn/video/videolistById?p=1&serviceId=panda&n=150&vsid=VSET100311356635";

    //视频播放
    public static final String PandaVideoPlayPATH="http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do";
    //熊猫直播top
    public static final  String PANDALIVETITLE="http://www.ipanda.com/kehuduan/PAGE14501772263221982/index.json";
    //原创
    public static final String CEHUA = BASEURL + "PAGE14501767715521482/index.json";
    //直播中国 http://www.ipanda.com/kehuduan/PAGE14501775094142282/index.json
   public static final String LIVECHINAS=BASEURL+"PAGE14501775094142282/index.json";
    //八达岭
    public static final String BADALING = BASEURL + "liebiao/badaling/index.json";

    /*  // 天山
    public static final String LIVECHINA = BASEURL + "PAGE14501776855882402/index.json";
    //凤凰古城
    public static final String FHGC = BASEURL + "liebiao/fenghuanggucheng/index.json";
    //"泰山"
    public static final String TAISHAN = BASEURL + "liebiao/taishan/index.json";
    //"黄山"
    public static final String HUANGSHAN = BASEURL + "liebiao/huangshan/index.json";
    //"峨眉山"
    public static final String EMEISHAN = BASEURL + "liebiao/emeishan/index.json";
    //"张家界"
    public static final String ZHANGJIAJIE = BASEURL + "liebiao/zhangjiajie/index.shtml";
    //"黟县"
    public static final String QIANXIAN = BASEURL + "liebiao/PAGEjQscAxoVKOjRIpboc1oA160718/index.json";
    //"中央电视塔"
    public static final String ZHONGYAN = BASEURL + "liebiao/zhongyangdianshita/index.json";
    //"恒山悬空寺"
    public static final String HENGSHANXUANKONGSI = BASEURL + "liebiao/hengshan/index.json";
    //"黄果树"
    public static final String HUANGGUOSHU = BASEURL + "liebiao/huangguoshu/index.json";
    //"黄龙"
    public static final String HUANGLONG = BASEURL + "liebiao/huanglong/index.json";
    //"武夷山"
    public static final String WUYISHAN = BASEURL + "liebiao/wuyishan/index.json";
    //"龙虎山"
    public static final String LONGHUSHAN = BASEURL + "liebiao/longhushan/index.json";
    //"嵩山少林寺"
    public static final String SONGSHAN = BASEURL + "liebiao/songshanshaolinsi/index.json";
    //"承德避暑山庄"
    public static final String CHENGDE = BASEURL + "liebiao/chengdebishushanzhuang/index.json";
    //"敦煌月牙泉"
    public static final String DUNHUANG = BASEURL + "liebiao/dunhuangyueyaquan/index.json";
    //"都江堰"
    public static final String DUJIANGYAN = BASEURL + "liebiao/dujiangyan/index.json";
*/
    //登录
    public static final String LOGIN="https://reg.cntv.cn/login/login.action";

}
