package vip.gadfly.tiktok.open.api.data.star.enume;

public enum TiktokOpenStarHostEnum {

    /**
     * 达人热榜类型 * 1 - 星图指数榜 * 2 - 涨粉指数榜 * 3 - 性价比指数榜 * 4 - 种草指数榜 * 5 - 精选指数榜 * 6 - 传播指数榜
     * <p>
     * Available values : 1, 2, 3, 4, 5, 6
     */

    ONE(1, "星图指数榜"),
    TWO(2, "涨粉指数榜"),
    THREE(3, "性价比指数榜"),
    FOUR(4, "种草指数榜"),
    FIVE(5, "精选指数榜"),
    SEX(6, "传播指数榜");


    private Integer id;
    private String name;

    // 构造方法
    TiktokOpenStarHostEnum(Integer id, String name) {
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
