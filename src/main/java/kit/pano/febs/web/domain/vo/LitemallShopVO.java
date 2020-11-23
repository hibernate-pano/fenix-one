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
 *
 * </p>
 *
 * @author pano
 * @since 2019-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallShop对象", description = "")
public class LitemallShopVO extends Model<LitemallShopVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank(message = "店铺名称不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺编号")
    private String shopCode;

    @ApiModelProperty(value = "店铺类型1药店2月子会所3百货超市")
    private Integer shopType;

    @NotNull(message = "店主ID不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "店主ID")
    private Long shopkeeperId;

    @ApiModelProperty(value = "店主名称")
    private String shopkeeperName;

    @ApiModelProperty(value = "店铺地址")
    private String address;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "状态0休息1营业")
    private Integer status;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    @ApiModelProperty(value = "次序")
    private Integer sortOrder;

    @ApiModelProperty(value = "坐标")
    private String location;

    @ApiModelProperty(value = "商品种类")
    private Integer goodsNum;

    @ApiModelProperty(value = "Logo图")
    private String logoPic;

    @ApiModelProperty(value = "实景图")
    private String shopPic;

    @ApiModelProperty(value = "营业执照图")
    private String licensePic;

    @ApiModelProperty(value = "经营项目标签")
    private String label;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
