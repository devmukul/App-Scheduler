package com.devmukul.appscheduler.database

import android.database.Cursor
import androidx.annotation.Keep
import androidx.room.*
import com.devmukul.appscheduler.model.App
import com.devmukul.appscheduler.model.Template

@Keep
@Dao
interface IDaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTemplate(template: Template)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApp(app: App)

    @Query("SELECT * FROM Templates")
    fun getTemplates(): List<Template>

    @Query("SELECT * FROM Apps")
    fun getApps(): List<App>



//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertFeedbackProduct(productOrder: List<FeedbackProductOrder>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertFeedbackResponse(feebackResponse: FeedbackTextResponse)
//
//    @Transaction
//    fun insertOrder(productOrder: ProductOrder, isFromSameShop: Boolean): Boolean {
//
//        if (isFromSameShop) {
//            insertProductOrder(productOrder)
//        } else {
//            clearOrderTable()
//            insertProductOrder(productOrder)
//        }
//        return true
//    }
//
//    @Transaction
//    fun insertAddon(mutableAddonList: MutableList<AddonItem>) {
//        for (addOnItem in mutableAddonList) {
//            addOnItem?.let { insertAddOnItem(it) }
//        }
//    }
//
//    @Query("DELETE FROM Addons WHERE productId =:productId")
//    fun deleteAddOnItem(productId: String)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAddOnItem(item: AddonItem)
//
//    @Query("SELECT * FROM Addons WHERE productId =:productId")
//    fun getAllAddOnsByProductId(productId: String): List<AddonItem>
//
//    @Query("DELETE FROM ProductOrder")
//    fun clearOrderTable()
//
//    @Query("DELETE FROM `Order`")
//    fun clearLiveOrderTable()
//
//    @Query("DELETE FROM `BaseOrders`")
//    fun clearLiveBaseOrderTable()
//
//    @Query("DELETE FROM FeedbackProductOrder")
//    fun clearFeedbackOrderTable()
//
//    @Query("DELETE FROM FeedbackTextResponse")
//    fun clearFeedbackResponseTable()
//
//    @Query("SELECT * FROM FeedbackProductOrder")
//    fun getFeedbackProductOrders(): List<FeedbackProductOrder>
//
//    @Query("SELECT count(*) FROM ProductOrder WHERE shopId = :shopID")
//    fun getProductByShopID(shopID: String): Int
//
//    @Query("SELECT count(*) FROM ProductOrder WHERE hubId = :hubId")
//    fun getProductByHubID(hubId: String): Int
//
//    @Query("SELECT count(*) FROM ProductOrder WHERE orderModule = :type")
//    fun getProductByType(type: String): Int
//
//    @Query("SELECT count(*) FROM ProductOrder WHERE product = :productId AND shopId = :shopID")
//    fun getProductByProductIDByShop(productId: String, shopID: String): Int
//    @Query("SELECT count(*) FROM ProductOrder WHERE size =:size AND variationId =:variationId")
//    fun getProductByProductIDByShop2(size: String, variationId: String): Int
//
//
////    new added
//    @Query("SELECT count(*) FROM ProductOrder WHERE product = :productId AND shopId = :shopID AND size = :size")
//    fun getProductByProductIDByShopBySize(productId: String, shopID: String, size:String): Int
//
//    @Query("SELECT size FROM ProductOrder WHERE product = :productId AND shopId = :shopID ")
//    fun getSizeByProductIDByShopID(productId: String, shopID: String): String
//
//
//
//
//
//    @Query("SELECT * FROM ProductOrder WHERE product = :productId LIMIT 1")
//    fun getProductByProductID(productId: String): ProductOrder
//
//    @Query("SELECT count(*) FROM ProductOrder WHERE product = :productId AND hubId = :hubId")
//    fun getProductByProductIDByHub(productId: String, hubId: String): Int
//
//    @Query("SELECT response FROM FeedbackTextResponse WHERE feedbackId = :feedbackId")
//    fun getFeedbackTextResponse(feedbackId: String): String
//
//    @Query("SELECT checked FROM FeedbackTextResponse WHERE feedbackId = :feedbackId")
//    fun getFeedbackBoolResponse(feedbackId: String): String
//
//    @Query("SELECT * FROM FeedbackTextResponse WHERE feedbackId = :feedbackId")
//    fun getFeedbackBoolResponseAll(feedbackId: String): FeedbackTextResponse?
//
//    @Query("SELECT * FROM FeedbackTextResponse ")
//    fun getFeedbackBoolResponseList(): List<FeedbackTextResponse>
//
//    @Query("SELECT productId, productName FROM FeedbackProductOrder WHERE feedbackId = :feedbackId")
//    fun getFeedbackProductList(feedbackId: String): List<Product>
//
//
//    @Query("SELECT count(*) FROM ProductOrder")
//    fun getProductOrdersSize(): Int
//
//    //new
//
//
//
//    @Query("DELETE FROM ProductOrder")
//    fun deleteTable()
//
//
//    @Transaction
//    fun isInsertionApplicable(shopID: String): Boolean {
//        var isSameShopID = false
//        isSameShopID = if (getProductOrdersSize() > 0) {
//            getProductByShopID(shopID) > 0
//        } else {
//            true
//        }
//        return isSameShopID
//    }
//
//    @Transaction
//    fun isInsertionApplicableByHubId(hubId: String): Boolean {
//        var isSameHubID = false
//        isSameHubID = if (getProductOrdersSize() > 0) {
//            getProductByHubID(hubId) > 0
//        } else {
//            true
//        }
//        return isSameHubID
//    }
//
//    @Transaction
//    fun isInsertionApplicableForServiceType(type: String): Boolean {
//        var isSameType: Boolean = false
//        isSameType = if (getProductOrdersSize() > 0) {
//            getProductByType(type) > 0
//        } else {
//            true
//        }
//        return isSameType
//    }
//
//    @Query("SELECT SUM(price * quantity) FROM ProductOrder")
//    fun totalCost(): Double
//
////    @Query("UPDATE ProductOrder SET quantity = :itemQty WHERE product = :id AND variationId = :variationId ;")
////    fun updateProductQuantity(itemQty: Int, id: String, variationId: String)
//
//
//    @Query("UPDATE ProductOrder SET quantity = :itemQty, vatAmount=:vatAmount, sdAmount=:sdAmount WHERE product = :id AND variationId = :variationId ;")
//    fun updateProductQuantityWithOthers(itemQty: Int, id: String, variationId: String, sdAmount: Double, vatAmount: Double)
//    @Query("UPDATE ProductOrder SET quantity = :itemQty, vatAmount=:vatAmount, sdAmount=:sdAmount WHERE size =:size AND variationId = :variationId ;")
//    fun updateProductQuantityWithOthers2(itemQty: Int, size: String, variationId: String, sdAmount: Double, vatAmount: Double)
//
//    @Transaction
//    fun updateProductQuantity(itemQty: Int, id: String, variationId: String){
//        var productOrder = getProductOrderById2(id, variationId)
//        productOrder = getProductOrderWithVatAndSDAmount(productOrder.vat, itemQty, productOrder)
////        productOrder.quantity?.let { updateProductQuantityWithOthers(it,productOrder.product,productOrder.variationId, productOrder.sdAmount, productOrder.vatAmount) }
//        productOrder.quantity?.let {
//            updateProductQuantityWithOthers2(it,productOrder.size,productOrder.variationId, productOrder.sdAmount, productOrder.vatAmount)
//        }
//    }
//
//    fun getProductOrderWithVatAndSDAmount(vat: Float, itemQty: Int, productOrder: ProductOrder): ProductOrder {
//        productOrder.quantity = itemQty
//        val tSubTotal = productOrder.discountedPrice?.times(itemQty)
//        val vatAmount: Double = (tSubTotal?.times(vat))?.div(100)?: 0.0
//        if (vatAmount != null) {
//            productOrder.vatAmount = vatAmount
//        }
//        if (productOrder.isSDApplicable){
//            val tSubTotalWithVat = tSubTotal?.plus(vatAmount) ?: 0.0
//            val sdAmount = (tSubTotalWithVat * productOrder.sd) / 100
//            productOrder.sdAmount = sdAmount
//        }else{
//            productOrder.sdAmount = 0.0
//        }
//        return productOrder
//    }
//
//    @Query("UPDATE FeedbackProductOrder SET feedbackId = :feedbackId, isChecked = :isChecked WHERE productId = :id ;")
//    fun updateFeedbackProduct(isChecked: Boolean, feedbackId: String, id: String)
//
//    @Query("DELETE FROM ProductOrder where product=:id AND variationId=:variationId")
//    fun deleteCartProducts(id: String, variationId: String)
//
//    @Query("DELETE FROM ProductOrder where size=:id AND variationId=:variationId")
//    fun deleteCartProducts2(id: String, variationId: String)
//
//    @Query("SELECT SUM(price * quantity) FROM ProductOrder")
//    fun getProductOrderSubtotal(): Double
//
//    @Transaction
//    fun getSubtotalPrice(): Double {
//        return getProductOrders().sumOf {
//            getProductSubTotal(it)
//        }
//    }
//
//    @Transaction
//    fun getProductSubTotal(product: ProductOrder): Double {
//        var productPrice = 0.0
//        val productOrder = getProductOrderById2(product.size, product.variationId)
////        val productOrder = getProductOrderById(product.product, product.variationId)
//        productPrice = productOrder.price?.toDouble() ?: 0.0
//        getAllAddOnsByProductId(product.product).forEach {
//            productPrice += it.amount?.toDouble() ?: 0.0
//        }
//        return productPrice.times(productOrder.quantity?: 0)
//    }
//
//    @Query("SELECT * FROM ProductOrder WHERE size =:size AND variationId =:variationId")
//    fun getProductOrderById2(size: String, variationId: String): ProductOrder
//
//    @Query("SELECT * FROM ProductOrder WHERE product =:product AND variationId =:variationId")
//    fun getProductOrderById(product: String, variationId: String): ProductOrder
//
//    @Query("SELECT SUM(discountedPrice * quantity) FROM ProductOrder")
//    fun getProductOrderDiscountedSubtotal(): Double
//
//    @Query("SELECT quantity FROM ProductOrder WHERE product =:id")
//    fun getOrderCount(id: String): Int
//
//    @Query("SELECT SUM(quantity) FROM ProductOrder WHERE product =:id")
//    fun getProductOrderCount(id: String): Int
//
//    @Query("SELECT SUM(quantity) FROM ProductOrder WHERE product =:id AND variationId =:variationId")
//    fun getProductWithVariationOrderCount(id: String, variationId: String): Int
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertFavouriteProduct(fProductsItem: List<FProductsItem>)
//
//    @Query("DELETE FROM FavouriteProduct WHERE productId =:id")
//    fun deleteFavouriteProduct(id: String)
//
//
//    @Query("SELECT * FROM FavouriteProduct WHERE productId =:id")
//    fun getFavouriteProductById(id: String): List<FProductsItem>
//
//
//    @Query("SELECT * FROM FavouriteProduct")
//    fun getAllFavouriteProducts(): List<FProductsItem>
//
//
//    @Transaction
//    fun insertOngoingOrder(order: Order): Boolean {
//        insertOrder(order)
//        order.deliveryMan?.let {
//            order.deliveryMan!!.orderId = order.orderId
//            insertDeliveryMain(it)
//
//        }
//        order.shop?.let {
//            order.shop!!.orderId = order.orderId
//            insertShop(it)
//        }
//        order.shippingLocation?.let {
//            order.shippingLocation!!.orderId = order.orderId
//            insertShippingLocation(it)
//        }
//        return true
//    }
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertOrder(order: Order)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertBaseOrder(baseOrder: BaseOrder)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertDeliveryMain(deliveryMan: DeliveryMan)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertShop(shop: Shop)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertShippingLocation(shippingLocation: ShippingLocation)
//
//    @Query("SELECT COUNT(*) FROM `BaseOrders`")
//    fun getOnGoingOrderCount(): Int
//
//    @Transaction
//    fun getLastOnGoingOrder(): Order {
//        val order = getLastOrder()
//        order.deliveryMan = getLastOrderDeliveryMan(orderId = order.orderId)
//        order.shippingLocation = getLastOrderShippingLocation(orderId = order.orderId)
//        order.shop = getLastOrderShop(orderId = order.orderId)
//
//        return order
//    }
//
//
//    @Query("SELECT * FROM `Order` LIMIT 1")
//    fun getLastOrder(): Order
//
//    @Query("SELECT * FROM `BaseOrders` LIMIT 1")
//    fun getLastBaseOrder(): BaseOrder
//
//    @Query("SELECT * FROM Shop WHERE orderId= :orderId")
//    fun getLastOrderShop(orderId: String): Shop
//
//    @Query("SELECT * FROM ShippingLocation WHERE orderId= :orderId")
//    fun getLastOrderShippingLocation(orderId: String): ShippingLocation
//
//    @Query("SELECT * FROM DeliveryMan WHERE orderId= :orderId")
//    fun getLastOrderDeliveryMan(orderId: String): DeliveryMan
//
////    @Transaction
////    fun geDiscountPrice(): Double {
////        return getProductOrderDiscountedSubtotal() - getProductOrderSubtotal()
////    }
////    @Transaction
////    fun geDiscountPrice(): Double {
////        return getProductOrderDiscountedSubtotal() - getSubtotalPrice()
////    }
//
//    @Transaction
//    fun geDiscountPrice(): Double {
//        return getSubtotalDiscountPrice() - getSubtotalPrice()
//    }
//    @Transaction
//    fun getSubtotalDiscountPrice(): Double {
//        return getProductOrders().sumOf {
//            getProductDiscountedSubTotal(it)
//        }
//    }
//
//    @Transaction
//    fun getProductDiscountedSubTotal(product: ProductOrder): Double {
//        var productDiscountPrice = 0.0
//        val productOrder = getProductOrderById2(product.size, product.variationId)
////        val productOrder = getProductOrderById(product.product, product.variationId)
//        productDiscountPrice = productOrder.discountedPrice?.toDouble() ?: 0.0
//        getAllAddOnsByProductId(product.product).forEach {
//            productDiscountPrice += it.amount?.toDouble() ?: 0.0
//        }
//        return productDiscountPrice.times(productOrder.quantity?: 0)
//    }
//
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSearchKeyword(items: SearchHistoryItem)
//
//    @Query("SELECT * FROM SearchHistory  ORDER BY updated_at DESC")
//    fun getSearchHistory(): List<SearchHistoryItem>
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertContactItem(item: ContactItem)
//
//
//    @Query("SELECT * FROM contact_table ORDER BY LOWER(contactName) ASC")
//    fun getAllUserContacts(): PagingSource<Int, ContactItem>
//
//
//    @Query("SELECT COUNT(*) FROM contact_table")
//    fun getUserContactCount(): Int
//
//    @Query("SELECT orderModule FROM ProductOrder LIMIT 1")
//    fun getCartServiceType(): String
//
//    @Query("SELECT SUM(vatAmount) FROM ProductOrder")
//    fun getTotalVatAmount(): Float
//
//    @Query("SELECT SUM(sdAmount) FROM ProductOrder")
//    fun getTotalSDAmount(): Float
//





}