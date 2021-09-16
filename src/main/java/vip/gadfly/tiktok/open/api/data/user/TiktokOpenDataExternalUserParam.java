package vip.gadfly.tiktok.open.api.data.user;

import vip.gadfly.tiktok.open.api.data.user.enume.TiktokOpenDataExternalUserEnum;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;

public class TiktokOpenDataExternalUserParam extends TiktokOpenBaseParam {
    /**
     * 近7/15天；输入7代表7天、15代表15天
     */
    private Integer DateType = 15;


    private TiktokOpenDataExternalUserEnum dataType = TiktokOpenDataExternalUserEnum.COMMENT;


    public Integer getDateType() {
        return DateType;
    }

    public void setDateType(Integer dateType) {
        DateType = dateType;
    }


    public TiktokOpenDataExternalUserEnum getDataType() {
        return dataType;
    }

    public void setDataType(TiktokOpenDataExternalUserEnum dataType) {
        this.dataType = dataType;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getUrlParam() {
        String params = super.getNoPageUrlParam() + "&date_type=" + this.DateType;
        return params;
    }


}

