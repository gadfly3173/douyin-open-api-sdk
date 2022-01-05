package vip.gadfly.tiktok.core.exception;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * 字节跳动错误
 * 不知道字节怎么想的，要定义两套不同的错误码
 * TtOpError 跟 ByteDancePayError 是平级的，并不是父子关系
 * ByteDanceError的部分错误码可以在<code>TtOpErrorMsgEnum</code>中找到, 完整的请去官网查看
 *
 * @author yangyidian
 * @date 2020/06/28
 **/
@Data
@Accessors(chain = true)
public class TtOpError implements ITtOpError, Serializable {

    private static final long serialVersionUID = 8757224149770373443L;
    /**
     * 错误代码.
     */
    @JSONField(name = "error_code")
    @JsonAlias("error_code")
    @JsonProperty("error_code")
    @SerializedName("error_code")
    private Integer errorCode;

    /**
     * 错误信息.
     */
    @JSONField(name = "description")
    @JsonAlias("description")
    @JsonProperty("description")
    @SerializedName("description")
    private String description;

    /**
     * 封装结果判断
     *
     * @return
     */
    @Override
    public Boolean checkSuccess() {
        return Objects.equals(this.errorCode, 0);
    }

    @Override
    public String toString() {
        return "错误代码：" + this.errorCode + ", 错误信息：" + this.description;
    }
}
