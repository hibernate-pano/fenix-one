package kit.pano.febs.web.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value = "LitemallShop对象", description = "")
public class LitemallShop extends Model<LitemallShop> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺编号")
    private String shopCode;

    @ApiModelProperty(value = "店铺类型1药店2月子会所3百货超市")
    private Integer shopType;

    @ApiModelProperty(value = "店主ID")
    private Long shopkeeperId;

    @ApiModelProperty(value = "店主名称")
    private String shopkeeperName;

    @ApiModelProperty(value = "店铺地址")
    private String address;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "状态0休息1营业")
    private Integer status;

    @ApiModelProperty(value = "次序")
    private Integer sortOrder;

    @ApiModelProperty(value = "坐标")
    private String location;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

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
