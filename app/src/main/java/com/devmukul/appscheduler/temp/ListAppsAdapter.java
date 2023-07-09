package com.devmukul.appscheduler.temp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.argonremote.openappscheduler.model.App;
import java.util.List;

public class ListAppsAdapter extends BaseAdapter {
    public static final String TAG = "ListAppsAdapter";
    int icon_disabled = this.res.getIdentifier("com.argonremote.openappscheduler:mipmap/ic_add_circle_outline_black_18dp", (String) null, (String) null);
    int icon_enabled = this.res.getIdentifier("com.argonremote.openappscheduler:mipmap/ic_add_circle_black_18dp", (String) null, (String) null);
    private Context mContext;
    private LayoutInflater mInflater;
    private List<App> mItems;

    /* renamed from: pm */
    PackageManager f68pm = this.mContext.getPackageManager();
    private Resources res;

    public ListAppsAdapter(Context context, List<App> list) {
        setItems(list);
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.res = context.getResources();
    }

    public int getCount() {
        if (getItems() == null || getItems().isEmpty()) {
            return 0;
        }
        return getItems().size();
    }

    public App getItem(int i) {
        if (getItems() == null || getItems().isEmpty()) {
            return null;
        }
        return getItems().get(i);
    }

    public long getItemId(int i) {
        return (getItems() == null || getItems().isEmpty()) ? (long) i : getItems().get(i).get_id();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:7|8|9|10|11|12|(1:14)(3:15|16|17)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0069 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0076 A[Catch:{ Exception -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0099 A[Catch:{ Exception -> 0x00b7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r5, android.view.View r6, android.view.ViewGroup r7) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L_0x0045
            android.view.LayoutInflater r1 = r4.mInflater     // Catch:{ Exception -> 0x00b7 }
            r2 = 2131492899(0x7f0c0023, float:1.8609263E38)
            android.view.View r6 = r1.inflate(r2, r7, r0)     // Catch:{ Exception -> 0x00b7 }
            com.argonremote.openappscheduler.adapter.ListAppsAdapter$ViewHolder r7 = new com.argonremote.openappscheduler.adapter.ListAppsAdapter$ViewHolder     // Catch:{ Exception -> 0x00b7 }
            r7.<init>()     // Catch:{ Exception -> 0x00b7 }
            r1 = 2131296390(0x7f090086, float:1.8210695E38)
            android.view.View r1 = r6.findViewById(r1)     // Catch:{ Exception -> 0x00b7 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x00b7 }
            android.widget.TextView unused = r7.name = r1     // Catch:{ Exception -> 0x00b7 }
            r1 = 2131296400(0x7f090090, float:1.8210716E38)
            android.view.View r1 = r6.findViewById(r1)     // Catch:{ Exception -> 0x00b7 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x00b7 }
            android.widget.TextView unused = r7.packageName = r1     // Catch:{ Exception -> 0x00b7 }
            r1 = 2131296368(0x7f090070, float:1.821065E38)
            android.view.View r1 = r6.findViewById(r1)     // Catch:{ Exception -> 0x00b7 }
            android.widget.ImageView r1 = (android.widget.ImageView) r1     // Catch:{ Exception -> 0x00b7 }
            android.widget.ImageView unused = r7.icon = r1     // Catch:{ Exception -> 0x00b7 }
            r1 = 2131296441(0x7f0900b9, float:1.8210799E38)
            android.view.View r1 = r6.findViewById(r1)     // Catch:{ Exception -> 0x00b7 }
            android.widget.ImageView r1 = (android.widget.ImageView) r1     // Catch:{ Exception -> 0x00b7 }
            android.widget.ImageView unused = r7.status = r1     // Catch:{ Exception -> 0x00b7 }
            r6.setTag(r7)     // Catch:{ Exception -> 0x00b7 }
            goto L_0x004b
        L_0x0045:
            java.lang.Object r7 = r6.getTag()     // Catch:{ Exception -> 0x00b7 }
            com.argonremote.openappscheduler.adapter.ListAppsAdapter$ViewHolder r7 = (com.argonremote.openappscheduler.adapter.ListAppsAdapter.ViewHolder) r7     // Catch:{ Exception -> 0x00b7 }
        L_0x004b:
            com.argonremote.openappscheduler.model.App r5 = r4.getItem((int) r5)     // Catch:{ Exception -> 0x00b7 }
            if (r5 == 0) goto L_0x00bb
            android.content.res.Resources r1 = r4.res     // Catch:{ Exception -> 0x00b7 }
            r2 = 2131689665(0x7f0f00c1, float:1.9008352E38)
            r1.getString(r2)     // Catch:{ Exception -> 0x00b7 }
            r1 = 0
            android.content.pm.PackageManager r2 = r4.f68pm     // Catch:{ Exception -> 0x0069 }
            java.lang.String r3 = r5.getPackage_name()     // Catch:{ Exception -> 0x0069 }
            android.content.pm.ApplicationInfo r1 = r2.getApplicationInfo(r3, r0)     // Catch:{ Exception -> 0x0069 }
            android.content.pm.PackageManager r0 = r4.f68pm     // Catch:{ Exception -> 0x0069 }
            r0.getApplicationLabel(r1)     // Catch:{ Exception -> 0x0069 }
        L_0x0069:
            android.widget.TextView r0 = r7.name     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r2 = r5.getApp_name()     // Catch:{ Exception -> 0x00b7 }
            r0.setText(r2)     // Catch:{ Exception -> 0x00b7 }
            if (r1 != 0) goto L_0x0099
            android.widget.TextView r0 = r7.packageName     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r5 = r5.getPackage_name()     // Catch:{ Exception -> 0x00b7 }
            r0.setText(r5)     // Catch:{ Exception -> 0x00b7 }
            android.widget.ImageView r5 = r7.icon     // Catch:{ Exception -> 0x00b7 }
            android.content.res.Resources r7 = r4.res     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r0 = "ic_block_black_48dp"
            java.lang.String r1 = "mipmap"
            android.content.Context r2 = r4.mContext     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ Exception -> 0x00b7 }
            int r7 = r7.getIdentifier(r0, r1, r2)     // Catch:{ Exception -> 0x00b7 }
            r5.setImageResource(r7)     // Catch:{ Exception -> 0x00b7 }
            goto L_0x00bb
        L_0x0099:
            android.widget.TextView r5 = r7.packageName     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r0 = r1.packageName     // Catch:{ Exception -> 0x00b7 }
            r5.setText(r0)     // Catch:{ Exception -> 0x00b7 }
            android.widget.ImageView r5 = r7.icon     // Catch:{ Exception -> 0x00b2 }
            android.content.pm.PackageManager r7 = r4.f68pm     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r0 = r1.packageName     // Catch:{ Exception -> 0x00b2 }
            android.graphics.drawable.Drawable r7 = r7.getApplicationIcon(r0)     // Catch:{ Exception -> 0x00b2 }
            r5.setImageDrawable(r7)     // Catch:{ Exception -> 0x00b2 }
            goto L_0x00bb
        L_0x00b2:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ Exception -> 0x00b7 }
            goto L_0x00bb
        L_0x00b7:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00bb:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.argonremote.openappscheduler.adapter.ListAppsAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public List<App> getItems() {
        return this.mItems;
    }

    public void setItems(List<App> list) {
        this.mItems = list;
    }

    class ViewHolder {
        /* access modifiers changed from: private */
        public ImageView icon;
        /* access modifiers changed from: private */
        public TextView name;
        /* access modifiers changed from: private */
        public TextView packageName;
        /* access modifiers changed from: private */
        public ImageView status;

        ViewHolder() {
        }
    }
}
