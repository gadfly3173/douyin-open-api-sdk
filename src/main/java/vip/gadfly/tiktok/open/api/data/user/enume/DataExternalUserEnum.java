package vip.gadfly.tiktok.open.api.data.user.enume;

import vip.gadfly.tiktok.open.api.data.user.*;

public enum DataExternalUserEnum {
    COMMENT("comment", ExternalUserCommentResult.class),
    FANS("fans", ExternalUserFansResult.class),
    ITEM("item", ExternalUserItemResult.class),
    LIKE("like", ExternalUserLikeResult.class),
    PROFILE("profile", ExternalUserProfileResult.class),
    SHARE("share", ExternalUserShareResult.class);
    // 成员变量
    private final String typeName;
    private final Class<?> aClass;

    // 构造方法
    DataExternalUserEnum(String typeName, Class<?> aClass) {
        this.typeName = typeName;
        this.aClass = aClass;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.typeName;
    }

    public String typeName() {
        return this.typeName;
    }

    public Class<?> resultClass() {
        return this.aClass;
    }
}
