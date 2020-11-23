package kit.pano.febs.web.domain.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kit.pano.febs.common.validator.group.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallAssistant对象", description = "")
public class LitemallAssistantVO extends Model<LitemallAssistantVO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "店员昵称不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "店员昵称")
    private String nickname;

    @NotBlank(message = "手机号码不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @NotBlank(message = "真实姓名不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @NotBlank(message = "身份证号码不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "对应用户账号")
    private Long userId;

    @ApiModelProperty(value = "状态0无效1有效")
    private Integer status;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "有效期")
    private LocalDateTime expireTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次登录时间")
    private LocalDateTime lastLoginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "查询条件")
    private String condition;

    @ApiModelProperty(value = "对应店主ID")
    private Long shopkeeperId;

    @ApiModelProperty(value = "对应店铺ID")
    private Long shopId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
