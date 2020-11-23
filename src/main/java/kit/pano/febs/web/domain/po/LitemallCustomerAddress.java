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
 * 顾客收货地址表
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallCustomerAddress对象", description = "顾客收货地址表")
public class LitemallCustomerAddress extends Model<LitemallCustomerAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "收货人名称")
    private String name;

    @ApiModelProperty(value = "用户表的用户ID")
    private Long customerId;

    @ApiModelProperty(value = "行政区域表的省ID")
    private String province;

    @ApiModelProperty(value = "行政区域表的市ID")
    private String city;

    @ApiModelProperty(value = "行政区域表的区县ID")
    private String county;

    @ApiModelProperty(value = "详细收货地址")
    private String addressDetail;

    @ApiModelProperty(value = "地区编码")
    private String areaCode;

    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "是否默认地址")
    private Integer isDefault;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
