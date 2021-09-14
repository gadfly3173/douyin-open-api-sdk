package vip.gadfly.tiktok.open.conf;

import java.util.ArrayList;
import java.util.List;

public class DouyinConf {

    public static String SCOPE_USER_INFO = "user_info";
    public static String SCOPE_FANS_LIST = "fans.list";
    public static String SCOPE_FANS_DATA = "fans.data";
    public static String SCOPE_FOLLOWING_LIST = "following.list";

    public static String SCOPE_VIDEO_CREATE = "video.create";
    public static String SCOPE_VIDEO_LIST = "video.list";
    public static String SCOPE_VIDEO_DATA = "video.data";
    public static String SCOPE_VIDEO_DELETE = "video.delete";
    public static String SCOPE_VIDEO_COMMENT = "video.comment";

    public static String SCOPE_TOUTIAO_VIDEO_CREATE = "toutiao.video.create";
    public static String SCOPE_TOUTIAO_VIDEO_DATA = "toutiao.video.data";

    public static String SCOPE_IM = "im";

    public static String SCOPE_POI_SEARCH = "poi.search";
    public static String SCOPE_POI_PRODUCT = "poi.product";

    public static String SCOPE_AWEME_SHARE = "aweme.share";

    public static String SCOPE_ENTERPRISE_DATA = "enterprise.data";

    public static String SCOPE_DATA_EXTERNAL_USER = "data.external.user";
    public static String SCOPE_DATA_EXTERNAL_ITEM = "data.external.item";

    public static String SCOPE_HOT_SEARCH = "hotsearch";

    public static String SCOPE_STAR_TOPS = "star_tops";

    public static String SCOPE_STAR_TOPS_DISPLAY = "star_top_score_display";


    public static List<String> scopeAllList = new ArrayList<String>();

    public static List<String> scopeDataList = new ArrayList<String>();
    public static List<String> scopeQueryList = new ArrayList<String>();
    public static List<String> scopeModifyList = new ArrayList<String>();

    static {
        scopeDataList.add(SCOPE_USER_INFO);
        scopeDataList.add(SCOPE_FANS_DATA);
        scopeDataList.add(SCOPE_VIDEO_DATA);
//        scopeDataList.add(SCOPE_TOUTIAO_VIDEO_DATA);
//        scopeDataList.add(SCOPE_ENTERPRISE_DATA);
        scopeDataList.add(SCOPE_AWEME_SHARE);
//        scopeDataList.add(SCOPE_DATA_EXTERNAL_USER);
//        scopeDataList.add(SCOPE_DATA_EXTERNAL_ITEM);
//        scopeDataList.add(SCOPE_STAR_TOPS);
//        scopeDataList.add(SCOPE_STAR_TOPS_DISPLAY);
        scopeDataList.add(SCOPE_IM);

        scopeQueryList.add(SCOPE_FANS_LIST);
        scopeQueryList.add(SCOPE_FOLLOWING_LIST);
        scopeQueryList.add(SCOPE_VIDEO_LIST);
//        scopeQueryList.add(SCOPE_POI_SEARCH);
//        scopeQueryList.add(SCOPE_VIDEO_PRODUCT);
//        scopeQueryList.add(SCOPE_HOT_SEARCH);

        scopeModifyList.add(SCOPE_VIDEO_CREATE);
        scopeModifyList.add(SCOPE_VIDEO_DELETE);
//        scopeModifyList.add(SCOPE_TOUTIAO_VIDEO_CREATE);
        scopeModifyList.add(SCOPE_VIDEO_COMMENT);

        scopeAllList.addAll(scopeDataList);
        scopeAllList.addAll(scopeQueryList);
        scopeAllList.addAll(scopeModifyList);
    }


}
