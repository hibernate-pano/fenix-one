package kit.pano.febs.web.domain.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城护工人员
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallNurse对象", description = "商城护工人员")
public class LitemallNurseVO extends Model<LitemallNurseVO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店主ID")
    private Long shopkeeperId;

    @ApiModelProperty(value = "状态0失效1有效")
    private Integer status;

    @ApiModelProperty(value = "上线状态0不上线1上线2审核中")
    private Integer online;

    @ApiModelProperty(value = "护工简介")
    private String nurseIntroduction;

    @ApiModelProperty(value = "护工类型")
    private Integer nurseType;

    @ApiModelProperty(value = "服务省份")
    private Integer serviceProvince;

    @ApiModelProperty(value = "服务城市")
    private Integer serviceCity;

    @ApiModelProperty(value = "服务区县")
    private Integer serviceCounty;

    @ApiModelProperty(value = "服务街道")
    private Integer serviceStreet;

    @ApiModelProperty(value = "详情介绍")
    private String nurseDetail;

    @ApiModelProperty(value = "护工图片存储地址")
    private String nursePic;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除标识")
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
