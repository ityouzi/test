//package org.example.dto;
//
//import com.enation.app.javashop.model.goods.dos.GoodsGalleryDO;
//import com.enation.app.javashop.model.goods.dos.GoodsParamsDO;
//import com.enation.app.javashop.model.goods.vo.GoodsMobileIntroVO;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.Valid;
//import javax.validation.constraints.*;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * 商品vo
// *
// * @author fk
// * @version v2.0
// * @since v7.0.0
// * 2018年3月21日 上午11:25:10
// */
//@ApiModel
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//public class GoodsDTO implements Serializable {
//
//    private static final long serialVersionUID = 3922135264669953741L;
//
//    // es_goods
//    @ApiModelProperty(hidden = true)
//    private Long goodsId;
//
//    // es_goods
//    @ApiModelProperty(name = "category_id", value = "分类id", required = true)
//    private Long categoryId;
//
//    /**
//     * 用作显示
//     */
//    @ApiModelProperty(name = "category_name", value = "分类名称", required = true)
//    private String categoryName;
//
//    @ApiModelProperty(name = "shop_cat_id", value = "店铺分类id", required = true)
//    @Min(value = 0, message = "店铺分类值不正确")
//    private Long shopCatId;
//
//    // es_goods
//    @ApiModelProperty(name = "brand_id", value = "品牌id", required = false)
//    private Long brandId;
//
//    // es_goods
//    @ApiModelProperty(name = "goods_name", value = "商品名称", required = true)
//    @NotEmpty(message = "商品名称不能为空")
//    private String goodsName;
//
//    @ApiModelProperty(name = "sn", value = "商品编号", required = true)
//    @Length(max = 30,message = "商品编号太长，不能超过30个字符")
//    private String sn;
//
//    // es_goods
//    @ApiModelProperty(name = "price", value = "商品价格", required = true)
//    @NotNull(message = "商品价格不能为空")
//    @Min(value = 0, message = "商品价格不能为负数")
//    @Max(value = 99999999, message = "商品价格不能超过99999999")
//    private Double price;
//
//    // es_goods
//    @ApiModelProperty(name = "cost", value = "成本价格", required = true)
//    @NotNull(message = "成本价格不能为空")
//    @Min(value = 0, message = "成本价格不能为负数")
//    @Max(value = 99999999, message = "成本价格不能超过99999999")
//    private Double cost;
//
//    @ApiModelProperty(name = "mktprice", value = "市场价格", required = true)
//    @NotNull(message = "市场价格不能为空")
//    @Min(value = 0, message = "市场价格不能为负数")
//    @Max(value = 99999999, message = "市场价格不能超过99999999")
//    private Double mktprice;
//
//    // es_goods
//    @ApiModelProperty(name = "weight", value = "重量", required = true)
//    @NotNull(message = "商品重量不能为空")
//    @Min(value = 0, message = "重量不能为负数")
//    @Max(value = 99999999,message = "重量不能超过99999999")
//    private Double weight;
//
//    @ApiModelProperty(name = "goods_transfee_charge", value = "谁承担运费0：买家承担，1：卖家承担", required = true)
//    @NotNull(message = "承担运费不能为空")
//    @Min(value = 0, message = "承担运费值不正确")
//    @Max(value = 1, message = "承担运费值不正确")
//    private Integer goodsTransfeeCharge;
//
//    // es_goods
//    @ApiModelProperty(name = "intro", value = "详情", required = false)
//    private String intro;
//
//    // es_goods
//    @ApiModelProperty(name = "have_spec", value = "是否有规格0没有1有", hidden = true)
//    private Integer haveSpec;
//
//    @ApiModelProperty(name = "quantity", value = "库存", required = false)
//    @Max(value = 99999999, message = "库存不能超过99999999")
//    private Integer quantity;
//
//    @ApiModelProperty(name = "market_enable", value = "是否上架，1上架 0下架", required = true)
//    @Min(value = 0, message = "是否上架值不正确")
//    @Max(value = 1, message = "是否上架值不正确")
//    private Integer marketEnable;
//
//    @ApiModelProperty(name = "goods_params_list", value = "商品参数", required = false)
//    @Valid
//    private List<GoodsParamsDO> goodsParamsList;
//
//    @ApiModelProperty(name = "goods_gallery_list", value = "商品相册", required = true)
//    @NotNull(message = "商品相册图片不能为空")
//    @Size(min = 1,message = "商品相册图片不能为空")
//    private List<GoodsGalleryDO> goodsGalleryList;
//
//    @ApiModelProperty(name = "exchange", value = "积分兑换对象，不是积分兑换商品可以不传", required = false)
//    private ExchangeVO exchange;
//
//    @ApiModelProperty(name = "has_changed", value = "sku数据变化或者组合变化判断 0:没变化，1：变化", required = true)
//    private Integer hasChanged;
//
//    @ApiModelProperty(name = "page_title", value = "seo标题", required = false)
//    private String pageTitle;
//
//    @ApiModelProperty(name = "meta_keywords", value = "seo关键字", required = false)
//    private String metaKeywords;
//
//    @ApiModelProperty(name = "meta_description", value = "seo描述", required = false)
//    private String metaDescription;
//
//    @ApiModelProperty(value = "商品的评论数量", hidden = true)
//    private Integer commentNum;
//
//    @ApiModelProperty(name = "template_id", value = "运费模板id,不需要运费模板时值是0", required = true)
//    private Long templateId;
//
//    @ApiModelProperty(value = "商品是否审核", hidden = true)
//    private Integer isAuth;
//
//    @ApiModelProperty(value = "可用库存", hidden = true)
//    private Integer enableQuantity;
//
//    @ApiModelProperty(name = "intro_list", value = "商品移动端详情数据集合", required = false)
//    private List<GoodsMobileIntroVO> introList;
//
//    @ApiModelProperty(name = "goods_video", value = "商品视频", required = false)
//    private String goodsVideo;
//
//
//}
