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
 * 商品参数表
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallGoodsAttribute对象", description = "商品参数表")
public class LitemallGoodsAttributeVO extends Model<LitemallGoodsAttributeVO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @NotNull(message = "商品ID不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "商品表的商品ID")
    private Integer goodsId;

    @NotBlank(message = "商品参数名称不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "商品参数名称")
    private String attribute;

    @NotBlank(message = "商品参数值不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "商品参数值")
    private String value;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
