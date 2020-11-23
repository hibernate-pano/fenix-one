package kit.pano.febs.web.domain.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城订单表
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LitemallOrder对象", description = "商城订单表")
public class LitemallOrderVO extends Model<LitemallOrderVO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "订单类型1普通订单（单独购买）2团购订单3秒杀团订单4推荐订单")
    private Integer orderType;

    @ApiModelProperty(value = "顾客ID")
    private Long customerId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 订单号：按系统规则生成订单号，对订单进行分类，类别不同显示相应的标注；
     * <p>
     * 订单号生成规则：订单号由16位数字组成，按下单时间取年月日8位+8位随机数，保证订单号不重复；
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "订单状态0待付款1待发货2待配送3待核销4待评价5已完成6已取消9已退款10无效")
    private Integer orderStatus;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "订单手机号码")
    private String phone;

    @ApiModelProperty(value = "订单收货地址")
    private String address;

    @ApiModelProperty(value = "备注信息")
    private String message;

    @ApiModelProperty(value = "商品费用")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "配送费用")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "优惠券减免")
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "用户积分减免")
    private BigDecimal integralPrice;

    @ApiModelProperty(value = "团购优惠价减免")
    private BigDecimal grouponPrice;

    @ApiModelProperty(value = "订单价格")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "实际价格")
    private BigDecimal actualPrice;

    @ApiModelProperty(value = "微信付款编号")
    private String payId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "微信付款时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "付款状态0未支付1支付中2已支付9已退款")
    private Integer payStatus;

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "商品货品ID")
    private Long goodsProductId;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "发货编号")
    private String shipSn;

    @ApiModelProperty(value = "发货渠道")
    private String shipChannel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发货开始时间")
    private LocalDateTime shipTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "用户确认收货时间")
    private LocalDateTime confirmTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "订单关闭时间")
    private LocalDateTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识0未删除1已删除")
    private Integer deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "查询起始时间")
    private LocalDateTime searchStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "查询结束时间")
    private LocalDateTime searchEndTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
