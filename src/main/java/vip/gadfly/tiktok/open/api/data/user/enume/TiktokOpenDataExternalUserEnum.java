package vip.gadfly.tiktok.open.api.data.user.enume;

import vip.gadfly.tiktok.open.api.data.user.*;

public enum TiktokOpenDataExternalUserEnum {
    COMMENT("comment", TiktokOpenExternalUserCommentResult.class),
    FANS("fans", TiktokOpenExternalUserFansResult.class),
    ITEM("item", TiktokOpenExternalUserItemResult.class),
    LIKE("like", TiktokOpenExternalUserLikeResult.class),
    PROFILE("profile", TiktokOpenExternalUserProfileResult.class),
    SHARE("share", TiktokOpenExternalUserShareResult.class);
    // 成员变量
    private final String typeName;
    private final Class<?> aClass;

    // 构造方法
    TiktokOpenDataExternalUserEnum(String typeName, Class<?> aClass) {
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
