package vip.gadfly.tiktok.open.api.data.discovery.enume;

public enum TiktokOpenDiscoveryEntEnum {

    /**
     * 达人热榜类型
     * 1 - 电影
     * 2 - 电视剧
     * 3 - 综艺
     * Available values : 1, 2, 3, 4, 5, 6
     */

    ONE(1, "电影"),
    TWO(2, "电视剧"),
    THREE(3, "综艺");


    private Integer id;
    private String name;

    // 构造方法
    TiktokOpenDiscoveryEntEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
