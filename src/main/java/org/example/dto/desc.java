package org.example.dto;

/**
 * @author lizhen created on 2021-07-07 14:57
 */
public class desc {

    /**
     * 1.添加商品
     *
     * 参数GoodsDTO 转换成实体对象 GoodsDO
     *
     * 需要新增的数据：添加商品，添加商品参数，添加商品SKU信息，添加相册，添加积分换购商品
     *
     * 发送增加商品消息 MQ
     * this.messageSender.send() 引入 ApplicationEventPublisher事件发布，
     * 然后通过 TransactionalMessageListener
     * 对ApplicationEventPublisher的publishEvent的监听，默认在事务提交后执行，
     * 然后从新发布事件
     *
     * 之后通过 @RabbitListener 对应绑定的交换机 处理相应的事件
     *
     *
     *
     *
     */

    /**
     * 本地代码接口
     *
     * 添加商品
     * com.enation.app.javashop.api.seller.goods.DraftGoodsSellerController#add
     *
     * 编辑商品
     * 查询商品详情 com.enation.app.javashop.api.seller.goods.GoodsSellerController#get
     * 编辑商品 com.enation.app.javashop.api.seller.goods.GoodsSellerController#edit    也是走了MQ.shend()
     *
     * 商品下家
     * com.enation.app.javashop.api.seller.goods.GoodsSellerController#underGoods
     */




    /**
     * 区域购买限制 ，分装成接口，传参调用
     *
     * 商品图片接口
     *
     * 我们没有的需要和产品确认的接口：
     * 1.赠品信息
     * 2.延保信息
     *
     *
     * 京东4及联动地址，本地保存。  本地3及联动修改表
     *
     * 和产品确认京东的商品是否可以参加活动（给商品加一个活动属性）
     *
     *
     * 商品字段属性映射对应本地问题，前端传ID，查询商品详情，然后做数据映射本地处理
     *
     *
     * 商品字段保存问题，skuID  supID什么的
     *
     * 还有产品名称，价格，品怕更新神的的
     *
     *
     * 和产品确认京东价格问题： 市场价，京东价，京东售价  对应我们平台的 ： 市场价，成本价，售价
     *
     *
     * 来源
     *
     *
     *
     */


    /**
     * 2021-07-22
     *
     * 1.查询地址信息，4级联动
     *
     * 2.查询分类信息
     *
     * 3.拼装搜索条件，搜索商品，获得京东商品列表
     *
     * 4.加入本地时，调用查看详情接口（查询商品图片和查询商品价格）
     *
     *
     *
     */


    /**
     *
     *
     *
     * @date: 2021/7/29-9:53
     *
     * ES检索，就像Redis 一样，只有通过编辑，或者更新，才会更新索引index字段， 
     *
     *
     *
     *
     *
     */



}
