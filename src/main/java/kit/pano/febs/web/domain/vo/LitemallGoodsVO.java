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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallGoods对象", description = "商品基本信息表")
public class LitemallGoodsVO extends Model<LitemallGoodsVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank(message = "商品编号不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "商品编号")
    private String goodsSn;

    @NotBlank(message = "商品名称不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "商品名称")
    private String name;

    @NotNull(message = "商品所属类目不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "商品所属类目ID")
    private Long categoryId;

    @ApiModelProperty(value = "商品品牌")
    private Long brandId;

    @ApiModelProperty(value = "商品宣传图片列表，采用JSON数组格式")
    private String gallery;

    @ApiModelProperty(value = "商品关键字，采用逗号间隔")
    private String keywords;

    @ApiModelProperty(value = "商品简介")
    private String brief;

    @ApiModelProperty(value = "是否上架")
    private Boolean isOnSale;

    @ApiModelProperty(value = "商品页面商品图片")
    private String picUrl;

    @ApiModelProperty(value = "商品分享朋友圈图片")
    private String shareUrl;

    @ApiModelProperty(value = "是否新品首发，如果设置则可以在新品首发页面展示")
    private Boolean isNew;

    @ApiModelProperty(value = "是否人气推荐，如果设置则可以在人气推荐页面展示")
    private Boolean isHot;

    @ApiModelProperty(value = "商品单位，例如件、盒")
    private String unit;

    @ApiModelProperty(value = "专柜价格")
    private BigDecimal counterPrice;

    @ApiModelProperty(value = "零售价格")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "商品详细介绍，是富文本格式")
    private String detail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    @NotNull(message = "所属店铺ID不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "所属店铺ID")
    private Long shopId;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
