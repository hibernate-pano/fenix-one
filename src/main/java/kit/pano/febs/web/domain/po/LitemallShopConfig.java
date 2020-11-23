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

/**
 * <p>
 *
 * </p>
 *
 * @author Pano
 * @since 2019-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallShopConfig对象", description = "")
public class LitemallShopConfig extends Model<LitemallShopConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "院内只送0未开启1开启")
    private Integer directDeliveryStatus;

    @ApiModelProperty(value = "配送模式0未开启1开启")
    private Integer logisticsDeliveryStatus;

    @ApiModelProperty(value = "自提模式0未开启1开启")
    private Integer selfDeliveryStatus;

    @ApiModelProperty(value = "货到付款0未开启1开启")
    private Integer cashOnDeliveryStatus;

    @ApiModelProperty(value = "配送时间")
    private String deliveryTime;

    @ApiModelProperty(value = "配送标签")
    private String deliveryLabel;

    @ApiModelProperty(value = "自动接单0未开启1开启")
    private Integer autoOrderTaking;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
