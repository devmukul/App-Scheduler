package com.devmukul.appscheduler.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.p002UJ.bwPDd;
import androidx.arch.core.util.IRY.RHIrAtSSNe;
import androidx.core.app.unusedapprestrictions.lzo.PulQqFCokNqFC;
import androidx.core.graphics.drawable.PjFy.baJoPPWAC;
import androidx.lifecycle.runtime.CroO.uGUpBRkcAnpFwU;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.argonremote.openappscheduler.util.Constants;
import com.argonremote.openappscheduler.util.Globals;
import com.pairip.licensecheck3.LicenseClientV3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlinx.coroutines.scheduling.p012kL.iZIhtHbj;

public class PremiumActivity extends Activity implements PurchasesUpdatedListener, BillingDynamics, View.OnClickListener, ActivityDynamics {
    public static final String TAG = "PremiumActivity";
    /* access modifiers changed from: private */
    public Activity activity;
    ImageView iPremiumControlAction;
    ImageView iPremiumControlIndicator;
    ImageView iPremiumHeader;
    private AcknowledgePurchaseResponseListener mAcknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
        public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
        }
    };
    /* access modifiers changed from: private */
    public BillingClient mBillingClient;
    /* access modifiers changed from: private */
    public boolean mIsBillingServiceConnected;
    /* access modifiers changed from: private */
    public List<ProductDetails> mProductDetailsList;
    String premiumMessage;
    String premiumTitle;
    Resources res;
    TextView tPremiumControlAction;
    TextView tPremiumControlDetail;
    TextView tPremiumControlMessage;
    TextView tPremiumControlTitle;
    TextView tPremiumDetail;
    TextView tPremiumMessage;
    TextView tPremiumTitle;
    ImageButton vClose;
    View vPremium;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle extras;
        LicenseClientV3.onActivityCreate(this);
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0491R.layout.activity_premium);
        this.activity = this;
        this.res = getResources();
        initViews();
        Intent intent = getIntent();
        if (!(intent == null || (extras = intent.getExtras()) == null)) {
            this.premiumTitle = extras.getString("PREMIUM_TITLE", "");
            this.premiumMessage = extras.getString("PREMIUM_MESSAGE", "");
        }
        refreshViews(Globals.isPremiumUser(this.activity));
        initBillingClient();
    }

    public void initBillingClient() {
        this.mBillingClient = BillingClient.newBuilder(this.activity).enablePendingPurchases().setListener(this).build();
        startBillingConnection(new Runnable() {
            public void run() {
                Log.d(PremiumActivity.TAG, RHIrAtSSNe.xleaJEZiGdk);
                PremiumActivity.this.getBillingProducts();
            }
        });
    }

    public void startBillingConnection(final Runnable runnable) {
        this.mBillingClient.startConnection(new BillingClientStateListener() {
            public void onBillingSetupFinished(BillingResult billingResult) {
                Log.d(PremiumActivity.TAG, "Setup finished. Response code: " + billingResult.getResponseCode());
                if (billingResult.getResponseCode() == 0) {
                    boolean unused = PremiumActivity.this.mIsBillingServiceConnected = true;
                    Runnable runnable = runnable;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            }

            public void onBillingServiceDisconnected() {
                boolean unused = PremiumActivity.this.mIsBillingServiceConnected = false;
            }
        });
    }

    public void executeBillingRequest(Runnable runnable) {
        if (this.mIsBillingServiceConnected) {
            runnable.run();
        } else {
            startBillingConnection(runnable);
        }
    }

    public void getBillingProducts() {
        QueryProductDetailsParams.Product build = QueryProductDetailsParams.Product.newBuilder().setProductId(Constants.ITEM_SKU_PREMIUM).setProductType("inapp").build();
        QueryProductDetailsParams.Product.newBuilder().setProductId(Constants.ITEM_SKU_TEST).setProductType("inapp").build();
        queryProductDetailsAsync(Collections.unmodifiableList(new ArrayList<QueryProductDetailsParams.Product>(build) {
            final /* synthetic */ QueryProductDetailsParams.Product val$product1;

            {
                this.val$product1 = r2;
                add(r2);
            }
        }));
    }

    public void queryProductDetailsAsync(final List<QueryProductDetailsParams.Product> list) {
        this.tPremiumControlAction.setText(this.res.getString(C0491R.string.loading));
        executeBillingRequest(new Runnable() {
            public void run() {
                PremiumActivity.this.mBillingClient.queryProductDetailsAsync(QueryProductDetailsParams.newBuilder().setProductList(list).build(), new ProductDetailsResponseListener() {
                    public void onProductDetailsResponse(BillingResult billingResult, List<ProductDetails> list) {
                        if (billingResult.getResponseCode() == 0) {
                            List unused = PremiumActivity.this.mProductDetailsList = list;
                            for (ProductDetails next : list) {
                                String productId = next.getProductId();
                                String formattedPrice = ((ProductDetails.OneTimePurchaseOfferDetails) Objects.requireNonNull(next.getOneTimePurchaseOfferDetails())).getFormattedPrice();
                                if (Constants.ITEM_SKU_PREMIUM.equals(productId)) {
                                    PremiumActivity.this.tPremiumControlAction.setText(formattedPrice);
                                } else {
                                    Constants.ITEM_SKU_TEST.equals(productId);
                                }
                            }
                            PremiumActivity.this.queryPurchases();
                            return;
                        }
                        PremiumActivity.this.tPremiumControlAction.setText(PremiumActivity.this.res.getString(C0491R.string.error));
                    }
                });
            }
        });
    }

    public void queryPurchases() {
        executeBillingRequest(new Runnable() {
            public void run() {
                Globals.savePreferences(Constants.ITEM_SKU_PREMIUM, false, "inapp", (Context) PremiumActivity.this.activity);
                PremiumActivity.this.mBillingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType("inapp").build(), (PurchasesResponseListener) new PurchasesResponseListener() {
                    public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> list) {
                        if (billingResult.getResponseCode() == 0) {
                            for (Purchase next : list) {
                                List<String> products = next.getProducts();
                                int purchaseState = next.getPurchaseState();
                                if (products.contains(Constants.ITEM_SKU_PREMIUM)) {
                                    boolean z = true;
                                    if (purchaseState != 1) {
                                        z = false;
                                    }
                                    Globals.savePreferences(Constants.ITEM_SKU_PREMIUM, z, PulQqFCokNqFC.JIMgnOJRblFvF, (Context) PremiumActivity.this.activity);
                                } else {
                                    products.contains(Constants.ITEM_SKU_TEST);
                                }
                            }
                        }
                        PremiumActivity.this.refreshViews(Globals.isPremiumUser(PremiumActivity.this.activity));
                    }
                });
            }
        });
    }

    public void initiatePurchaseFlow(final String str) {
        List<ProductDetails> list = this.mProductDetailsList;
        if (list != null && !list.isEmpty()) {
            executeBillingRequest(new Runnable() {
                public void run() {
                    ProductDetails productDetails;
                    Iterator it = PremiumActivity.this.mProductDetailsList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            productDetails = null;
                            break;
                        }
                        productDetails = (ProductDetails) it.next();
                        if (str.equals(productDetails.getProductId())) {
                            break;
                        }
                    }
                    PremiumActivity.this.mBillingClient.launchBillingFlow(PremiumActivity.this.activity, BillingFlowParams.newBuilder().setProductDetailsParamsList(Collections.unmodifiableList(new ArrayList<BillingFlowParams.ProductDetailsParams>(productDetails) {
                        final /* synthetic */ ProductDetails val$productDetails;

                        {
                            this.val$productDetails = r2;
                            add(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(r2).build());
                        }
                    })).build());
                }
            });
        } else if (!Globals.isConnected(this.activity)) {
            Globals.showToastMessage(this.res.getString(C0491R.string.init_billing_client_connection_error), this.activity);
        } else {
            initBillingClient();
        }
    }

    public void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == 1) {
            if (!purchase.isAcknowledged()) {
                acknowledgePurchase(purchase.getPurchaseToken(), this.mAcknowledgePurchaseResponseListener);
            }
            List<String> products = purchase.getProducts();
            if (products.contains(Constants.ITEM_SKU_PREMIUM)) {
                Globals.savePreferences(Constants.ITEM_SKU_PREMIUM, true, "inapp", (Context) this.activity);
            } else {
                products.contains(Constants.ITEM_SKU_TEST);
            }
            refreshViews(Globals.isPremiumUser(this.activity));
            Globals.showToastMessage(this.res.getString(C0491R.string.thank_you), this.activity);
        }
    }

    public void consumePurchase(final String str) {
        executeBillingRequest(new Runnable() {
            public void run() {
                PremiumActivity.this.mBillingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(str).build(), new ConsumeResponseListener() {
                    public void onConsumeResponse(BillingResult billingResult, String str) {
                        billingResult.getResponseCode();
                    }
                });
            }
        });
    }

    public void acknowledgePurchase(final String str, final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        executeBillingRequest(new Runnable() {
            public void run() {
                PremiumActivity.this.mBillingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(str).build(), acknowledgePurchaseResponseListener);
            }
        });
    }

    public void endBillingConnection() {
        BillingClient billingClient = this.mBillingClient;
        if (billingClient != null && billingClient.isReady()) {
            this.mBillingClient.endConnection();
            this.mBillingClient = null;
        }
    }

    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
        if (billingResult.getResponseCode() != 0 || list == null) {
            billingResult.getResponseCode();
            return;
        }
        for (Purchase handlePurchase : list) {
            handlePurchase(handlePurchase);
        }
    }

    private void initViews() {
        this.iPremiumHeader = (ImageView) findViewById(C0491R.C0342id.iPremiumHeader);
        this.tPremiumTitle = (TextView) findViewById(C0491R.C0342id.tPremiumTitle);
        this.tPremiumDetail = (TextView) findViewById(C0491R.C0342id.tPremiumDetail);
        this.tPremiumMessage = (TextView) findViewById(C0491R.C0342id.tPremiumMessage);
        View findViewById = findViewById(C0491R.C0342id.vPremium);
        this.vPremium = findViewById;
        findViewById.setOnClickListener(this);
        this.iPremiumControlIndicator = (ImageView) findViewById(C0491R.C0342id.iPremiumControlIndicator);
        this.iPremiumControlAction = (ImageView) findViewById(C0491R.C0342id.iPremiumControlAction);
        this.tPremiumControlTitle = (TextView) findViewById(C0491R.C0342id.tPremiumControlTitle);
        this.tPremiumControlDetail = (TextView) findViewById(C0491R.C0342id.tPremiumControlDetail);
        this.tPremiumControlMessage = (TextView) findViewById(C0491R.C0342id.tPremiumControlMessage);
        this.tPremiumControlAction = (TextView) findViewById(C0491R.C0342id.tPremiumControlAction);
        ImageButton imageButton = (ImageButton) findViewById(C0491R.C0342id.vClose);
        this.vClose = imageButton;
        imageButton.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void refreshViews(boolean z) {
        this.iPremiumHeader.setImageResource(getImageResource(this.activity, z, "ic_verified_user_black_48dp", "ic_lock_black_48dp"));
        if (z) {
            this.tPremiumTitle.setText(this.res.getString(C0491R.string.premium_user));
            this.tPremiumDetail.setText(this.res.getString(C0491R.string.premium_benefits));
        } else {
            this.tPremiumTitle.setText(this.premiumTitle);
            this.tPremiumDetail.setText(this.res.getString(C0491R.string.go_premium_detail).replace("#APP_NAME#", this.res.getString(C0491R.string.app_name)).replace(baJoPPWAC.VtQ, this.res.getString(C0491R.string.premium_benefits)));
        }
        this.tPremiumMessage.setText(this.premiumMessage);
        int i = 8;
        this.tPremiumMessage.setVisibility(z ? 8 : 0);
        this.vPremium.setBackgroundResource(getBackgroundResource(this.activity, z, iZIhtHbj.FzOjvcHZdjZB, uGUpBRkcAnpFwU.wSlXamt));
        this.iPremiumControlIndicator.setImageResource(getImageResource(this.activity, z, "ic_favorite_white_18dp", "ic_verified_user_white_18dp"));
        if (z) {
            this.tPremiumControlTitle.setText(this.res.getString(C0491R.string.premium_user));
            this.tPremiumControlMessage.setText(this.res.getString(C0491R.string.thank_you_premium_upgrade));
            this.tPremiumControlDetail.setText(this.res.getString(C0491R.string.unlimited_access_premium_features));
        } else {
            this.tPremiumControlTitle.setText(this.res.getString(C0491R.string.go_premium_be_happy));
            this.tPremiumControlMessage.setText(this.res.getString(C0491R.string.go_premium_one_purchase_detail));
            this.tPremiumControlDetail.setText(this.res.getString(C0491R.string.go_premium_one_purchase_note));
        }
        this.tPremiumControlAction.setVisibility(z ? 8 : 0);
        ImageView imageView = this.iPremiumControlAction;
        if (!z) {
            i = 0;
        }
        imageView.setVisibility(i);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        releaseResources();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == C0491R.C0342id.vPremium) {
            if (!Globals.isPremiumUser(this.activity)) {
                initiatePurchaseFlow(Constants.ITEM_SKU_PREMIUM);
            } else {
                Globals.showToastMessage(Globals.getStringFromResources(C0491R.string.you_are_already_a_premium_user, this.activity), this.activity);
            }
        } else if (id == C0491R.C0342id.vClose) {
            finish();
        }
    }

    public void releaseResources() {
        endBillingConnection();
    }

    public int getBackgroundResource(Context context, boolean z, String str, String str2) {
        if (!z) {
            str = str2;
        }
        try {
            return this.res.getIdentifier(str, bwPDd.WAoYTbnraJy, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getImageResource(Context context, boolean z, String str, String str2) {
        if (!z) {
            str = str2;
        }
        try {
            return this.res.getIdentifier(str, "mipmap", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
