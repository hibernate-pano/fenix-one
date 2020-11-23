package kit.pano.febs.common.domain;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019-09-23
 * Time: 14:41
 *
 * @author Pano
 */
public interface FebsOrderConst {

    /**
     * 1	Pending Payment	待支付	订单费用待支付
     * 2	Pending	支付确认中	订单费用到账待确认
     * 3	Processing	处理中	订单已分配到相应（采购/退换货）工作人员处
     * 4	Purchased	已订购	商品已订购
     * 5	Awaiting Confirmation	待确认	订单有信息需要和您确认
     * 6	Merchant Shipped	已发货	商品卖家已发出，正在等待商品到达CNstorm仓库
     * 7	Arrived	已到货	商品验货、称重完毕，等待入库
     * 8	In stock	已入库	商品已进入CNstorm仓库，您可以提交运送（请在商品保管期内提交寄送）
     * 9	Freight Pending	待支付运费	包裹已提交，费用待支付
     * 10	Shipment Processing	包裹处理中	订单已分配到相应（打包/单证）工作人员处
     * 11	Processed	已打包	您订购的货品已经按选项整合到包裹中
     * 12	Shipped	已寄送	该商品已为您寄出
     * 13	Complete	已完成	商品已送达
     * 14	Partially_refunded	部分退款	订单部分退款
     * 15	Refunded	已退款	订单退货
     * 16	Expired	超期	商品超过保管期，系统自动设置超期并销毁
     * 17	Parcel Returned	包裹退回	因海关安检不通过或未妥投等原因而退包
     * 18	Voided	无效	商品无法采购，或存在支付问题等，均视为无效订单/包裹
     * 19	Ship Direct 	直接邮寄	购物车中的商品生成订单的时候，同时生成包裹，不用再次手动提交运送
     * 20	Paid Freight	已支付运费	包裹费用已支付，准备寄送
     * 21	Canceled 	已取消	包裹取消寄送，包裹中的订单可再次提交运送
     */
    int PENDING_PAYMENT = 1;
    int PENDING = 2;
    int PROCESSING = 3;
    int PURCHASED = 4;
    int AWAITING_CONFIRMATION = 5;
    int MERCHANT_SHIPPED = 6;
    int ARRIVED = 7;
    int IN_STOCK = 8;
    int FREIGHT_PENDING = 9;
    int SHIPMENT_PROCESSING = 10;
    int PROCESSED = 11;
    int SHIPPED = 12;
    int COMPLETE = 13;
    int PARTIALLY_REFUNDED = 14;
    int REFUNDED = 15;
    int EXPIRED = 16;
    int PARCEL_RETURNED = 17;
    int VOIDED = 18;
    int SHIP_DIRECT = 19;
    int PAID_FREIGHT = 20;
    int CANCELED = 21;

    /**
     * 订单类型: 1普通订单（单独购买）2团购订单3秒杀团订单4推荐订单
     */
    int COMMON_ORDER = 1;
    int GROUP_ORDER = 2;
    int SECKILL_ORDER = 3;
    int RECOMMEND_ORDER = 4;

}
