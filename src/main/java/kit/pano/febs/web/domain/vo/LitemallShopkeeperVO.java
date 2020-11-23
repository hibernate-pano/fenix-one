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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 店主表
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallShopkeeper对象", description = "店主表")
public class LitemallShopkeeperVO extends Model<LitemallShopkeeperVO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "对应用户账号")
    private Long userId;

    @NotBlank(message = "用户昵称不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @NotBlank(message = "真实姓名不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @NotBlank(message = "手机号码不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotBlank(message = "身份证号码不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "状态0无效1有效")
    private Integer status;

    @ApiModelProperty(value = "密码")
    private String password;

    @NotNull(message = "有效期不能为空", groups = AddGroup.class)
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

    @ApiModelProperty(value = "店主类型 1店铺店主 2劳务店主")
    private Integer shopkeeperType;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
