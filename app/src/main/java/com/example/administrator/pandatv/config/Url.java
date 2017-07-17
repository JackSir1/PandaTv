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
    public static final  String SPLENDID="http://www.ipanda.com/kehuduan/PAGE14501773768112092/index.json";
    //熊猫直播  多视角直播
    public static final  String MORELIVE=BASEURL+"PAGE14501769230331752/PAGE14501787896813312/index.json";
     //熊猫观察
    public  static  final  String GGVIEDO=" http://api.cntv.cn/apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144&serviceId=panda";


    //熊猫播报
    public  static  final  String PandaObserver=BASEURL+"xmwh/index.json";

    //直播中国
    // 天山
    public static final String LIVECHINA=BASEURL+"PAGE14501776855882402/index.json";
    //凤凰古城
    public static final String FHGC=BASEURL+"liebiao/fenghuanggucheng/index.json";

    //八达岭
    public static final String BADALING=BASEURL+"liebiao/badaling/index.json";

}
