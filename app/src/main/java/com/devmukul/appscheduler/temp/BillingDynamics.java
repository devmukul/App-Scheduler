package com.devmukul.appscheduler.temp;

import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.QueryProductDetailsParams;
import java.util.List;

public interface BillingDynamics {
    void acknowledgePurchase(String str, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener);

    void consumePurchase(String str);

    void endBillingConnection();

    void executeBillingRequest(Runnable runnable);

    void getBillingProducts();

    void handlePurchase(Purchase purchase);

    void initBillingClient();

    void initiatePurchaseFlow(String str);

    void queryProductDetailsAsync(List<QueryProductDetailsParams.Product> list);

    void queryPurchases();

    void startBillingConnection(Runnable runnable);
}
