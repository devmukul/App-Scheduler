package com.devmukul.appscheduler.temp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.argonremote.openappscheduler.C0491R;
import com.argonremote.openappscheduler.dao.DBHelper;
import com.argonremote.openappscheduler.dao.TemplateDAO;
import com.argonremote.openappscheduler.model.Template;
import com.argonremote.openappscheduler.receiver.AlarmReceiver;
import com.argonremote.openappscheduler.util.Constants;
import com.argonremote.openappscheduler.util.DateHelper;
import com.argonremote.openappscheduler.util.Globals;
import java.util.List;

public class ListTemplatesAdapter extends BaseAdapter {
    public static final String TAG = "ListTemplatesAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Template> mItems;
    private TemplateDAO mTemplateDao;

    /* renamed from: pm */
    PackageManager f18pm = this.mContext.getPackageManager();
    private Resources res;

    public ListTemplatesAdapter(Context context, List<Template> list, TemplateDAO templateDAO) {
        setItems(list);
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mTemplateDao = templateDAO;
        this.res = context.getResources();
    }

    public int getCount() {
        if (getItems() == null || getItems().isEmpty()) {
            return 0;
        }
        return getItems().size();
    }

    public Template getItem(int i) {
        if (getItems() == null || getItems().isEmpty()) {
            return null;
        }
        return getItems().get(i);
    }

    public long getItemId(int i) {
        return (getItems() == null || getItems().isEmpty()) ? (long) i : getItems().get(i).getId();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:7|8|(4:9|10|11|12)|15|16|(1:18)(1:19)|20|(1:22)(1:23)|24|(1:26)(1:27)|28|(1:30)(1:31)|32|(1:34)|35|(1:37)|38|(1:45)(1:44)|46|(1:48)(2:49|50)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x008e */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x009d A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x009f A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b8 A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ba A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00cb A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00cd A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x010f A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0111 A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x011e A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0128 A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01ac A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01b2 A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01be A[Catch:{ Exception -> 0x01e9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01d6 A[SYNTHETIC, Splitter:B:49:0x01d6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public View getView(int r10, View r11, android.view.ViewGroup r12) {
        /*
            r9 = this;
            r0 = 0
            if (r11 != 0) goto L_0x006b
            android.view.LayoutInflater r1 = r9.mInflater     // Catch:{ Exception -> 0x01e9 }
            r2 = 2131492900(0x7f0c0024, float:1.8609265E38)
            android.view.View r11 = r1.inflate(r2, r12, r0)     // Catch:{ Exception -> 0x01e9 }
            com.argonremote.openappscheduler.adapter.ListTemplatesAdapter$ViewHolder r12 = new com.argonremote.openappscheduler.adapter.ListTemplatesAdapter$ViewHolder     // Catch:{ Exception -> 0x01e9 }
            r12.<init>()     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296454(0x7f0900c6, float:1.8210825E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x01e9 }
            r12.tName = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296447(0x7f0900bf, float:1.821081E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x01e9 }
            r12.tDescription = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296464(0x7f0900d0, float:1.8210845E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x01e9 }
            r12.tText = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296362(0x7f09006a, float:1.8210639E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.ImageView r1 = (android.widget.ImageView) r1     // Catch:{ Exception -> 0x01e9 }
            r12.iIcon = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296462(0x7f0900ce, float:1.8210841E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x01e9 }
            r12.tScheduling = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296453(0x7f0900c5, float:1.8210823E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ Exception -> 0x01e9 }
            r12.tInterval = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296379(0x7f09007b, float:1.8210673E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            r12.lInterval = r1     // Catch:{ Exception -> 0x01e9 }
            r1 = 2131296321(0x7f090041, float:1.8210555E38)
            android.view.View r1 = r11.findViewById(r1)     // Catch:{ Exception -> 0x01e9 }
            android.widget.ImageButton r1 = (android.widget.ImageButton) r1     // Catch:{ Exception -> 0x01e9 }
            r12.bScheduling = r1     // Catch:{ Exception -> 0x01e9 }
            r11.setTag(r12)     // Catch:{ Exception -> 0x01e9 }
            goto L_0x0071
        L_0x006b:
            java.lang.Object r12 = r11.getTag()     // Catch:{ Exception -> 0x01e9 }
            com.argonremote.openappscheduler.adapter.ListTemplatesAdapter$ViewHolder r12 = (com.argonremote.openappscheduler.adapter.ListTemplatesAdapter.ViewHolder) r12     // Catch:{ Exception -> 0x01e9 }
        L_0x0071:
            com.argonremote.openappscheduler.model.Template r1 = r9.getItem((int) r10)     // Catch:{ Exception -> 0x01e9 }
            if (r1 == 0) goto L_0x01ed
            java.lang.String r2 = r1.getPackageName()     // Catch:{ Exception -> 0x01e9 }
            r3 = 0
            android.content.pm.PackageManager r4 = r9.f18pm     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = r1.getPackageName()     // Catch:{ Exception -> 0x008d }
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo(r5, r0)     // Catch:{ Exception -> 0x008d }
            android.content.pm.PackageManager r5 = r9.f18pm     // Catch:{ Exception -> 0x008e }
            java.lang.CharSequence r2 = r5.getApplicationLabel(r4)     // Catch:{ Exception -> 0x008e }
            goto L_0x008e
        L_0x008d:
            r4 = r3
        L_0x008e:
            android.widget.TextView r5 = r12.tName     // Catch:{ Exception -> 0x01e9 }
            r5.setText(r2)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r5 = r12.tName     // Catch:{ Exception -> 0x01e9 }
            boolean r2 = com.argonremote.openappscheduler.util.Globals.isValidValue((java.lang.CharSequence) r2)     // Catch:{ Exception -> 0x01e9 }
            r6 = 8
            if (r2 == 0) goto L_0x009f
            r2 = r0
            goto L_0x00a0
        L_0x009f:
            r2 = r6
        L_0x00a0:
            r5.setVisibility(r2)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r2 = r12.tDescription     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r5 = r1.getDescription()     // Catch:{ Exception -> 0x01e9 }
            r2.setText(r5)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r2 = r12.tDescription     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r5 = r1.getDescription()     // Catch:{ Exception -> 0x01e9 }
            boolean r5 = com.argonremote.openappscheduler.util.Globals.isValidValue((java.lang.String) r5)     // Catch:{ Exception -> 0x01e9 }
            if (r5 == 0) goto L_0x00ba
            r5 = r0
            goto L_0x00bb
        L_0x00ba:
            r5 = r6
        L_0x00bb:
            r2.setVisibility(r5)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r2 = r12.tText     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r5 = r1.getPackageName()     // Catch:{ Exception -> 0x01e9 }
            r2.setText(r5)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r2 = r12.tText     // Catch:{ Exception -> 0x01e9 }
            if (r4 == 0) goto L_0x00cd
            r5 = r0
            goto L_0x00ce
        L_0x00cd:
            r5 = r6
        L_0x00ce:
            r2.setVisibility(r5)     // Catch:{ Exception -> 0x01e9 }
            com.argonremote.openappscheduler.dao.TemplateDAO r2 = r9.mTemplateDao     // Catch:{ Exception -> 0x01e9 }
            long r7 = r1.getId()     // Catch:{ Exception -> 0x01e9 }
            com.argonremote.openappscheduler.model.Template r2 = r2.getTemplate(r7)     // Catch:{ Exception -> 0x01e9 }
            long r7 = r2.getSchedulingDateStart()     // Catch:{ Exception -> 0x01e9 }
            r1.setSchedulingDateStart(r7)     // Catch:{ Exception -> 0x01e9 }
            int r2 = r2.getSchedulingStatus()     // Catch:{ Exception -> 0x01e9 }
            r1.setSchedulingStatus(r2)     // Catch:{ Exception -> 0x01e9 }
            long r7 = r1.getSchedulingDateStart()     // Catch:{ Exception -> 0x01e9 }
            r2 = 2
            r5 = 3
            java.lang.String r2 = com.argonremote.openappscheduler.util.DateHelper.getDateTime(r7, r2, r5)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r5 = r12.tScheduling     // Catch:{ Exception -> 0x01e9 }
            r5.setText(r2)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r5 = r12.tScheduling     // Catch:{ Exception -> 0x01e9 }
            int r7 = r1.getSchedulingStatus()     // Catch:{ Exception -> 0x01e9 }
            android.content.Context r8 = r9.mContext     // Catch:{ Exception -> 0x01e9 }
            int r7 = r9.getTextColor(r7, r8)     // Catch:{ Exception -> 0x01e9 }
            r5.setTextColor(r7)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r5 = r12.tScheduling     // Catch:{ Exception -> 0x01e9 }
            boolean r2 = com.argonremote.openappscheduler.util.Globals.isValidValue((java.lang.String) r2)     // Catch:{ Exception -> 0x01e9 }
            if (r2 == 0) goto L_0x0111
            r2 = r0
            goto L_0x0112
        L_0x0111:
            r2 = r6
        L_0x0112:
            r5.setVisibility(r2)     // Catch:{ Exception -> 0x01e9 }
            android.view.View r2 = r12.lInterval     // Catch:{ Exception -> 0x01e9 }
            int r5 = r1.getSchedulingRepeat()     // Catch:{ Exception -> 0x01e9 }
            r7 = 1
            if (r5 != r7) goto L_0x011f
            r6 = r0
        L_0x011f:
            r2.setVisibility(r6)     // Catch:{ Exception -> 0x01e9 }
            int r2 = r1.getSchedulingRepeat()     // Catch:{ Exception -> 0x01e9 }
            if (r2 != r7) goto L_0x016a
            android.content.res.Resources r2 = r9.res     // Catch:{ Exception -> 0x01e9 }
            r5 = 2130903040(0x7f030000, float:1.7412887E38)
            java.lang.String[] r2 = r2.getStringArray(r5)     // Catch:{ Exception -> 0x01e9 }
            long r5 = r1.getSchedulingIntervalMultiplier()     // Catch:{ Exception -> 0x01e9 }
            int r5 = com.argonremote.openappscheduler.AddTemplateActivity.getSpinnerSelectionByIntervalMultiplier(r5)     // Catch:{ Exception -> 0x01e9 }
            r2 = r2[r5]     // Catch:{ Exception -> 0x01e9 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e9 }
            r5.<init>()     // Catch:{ Exception -> 0x01e9 }
            int r6 = r1.getSchedulingInterval()     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x01e9 }
            r5.append(r6)     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r6 = " "
            r5.append(r6)     // Catch:{ Exception -> 0x01e9 }
            r5.append(r2)     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r2 = r5.toString()     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r5 = r12.tInterval     // Catch:{ Exception -> 0x01e9 }
            r5.setText(r2)     // Catch:{ Exception -> 0x01e9 }
            android.widget.TextView r2 = r12.tInterval     // Catch:{ Exception -> 0x01e9 }
            int r5 = r1.getSchedulingStatus()     // Catch:{ Exception -> 0x01e9 }
            android.content.Context r6 = r9.mContext     // Catch:{ Exception -> 0x01e9 }
            int r5 = r9.getTextColor(r5, r6)     // Catch:{ Exception -> 0x01e9 }
            r2.setTextColor(r5)     // Catch:{ Exception -> 0x01e9 }
        L_0x016a:
            android.widget.ImageButton r2 = r12.bScheduling     // Catch:{ Exception -> 0x01e9 }
            int r5 = r1.getSchedulingStatus()     // Catch:{ Exception -> 0x01e9 }
            android.content.Context r6 = r9.mContext     // Catch:{ Exception -> 0x01e9 }
            int r5 = r9.getBackgroundResource(r5, r6)     // Catch:{ Exception -> 0x01e9 }
            r2.setBackgroundResource(r5)     // Catch:{ Exception -> 0x01e9 }
            android.widget.ImageButton r2 = r12.bScheduling     // Catch:{ Exception -> 0x01e9 }
            com.argonremote.openappscheduler.adapter.ListTemplatesAdapter$ChangeSchedulingStatusListener r5 = new com.argonremote.openappscheduler.adapter.ListTemplatesAdapter$ChangeSchedulingStatusListener     // Catch:{ Exception -> 0x01e9 }
            r5.<init>(r1, r10)     // Catch:{ Exception -> 0x01e9 }
            r2.setOnClickListener(r5)     // Catch:{ Exception -> 0x01e9 }
            android.widget.ImageButton r10 = r12.bScheduling     // Catch:{ Exception -> 0x01e9 }
            int r2 = r1.getSchedulingRepeat()     // Catch:{ Exception -> 0x01e9 }
            android.content.Context r3 = r9.mContext     // Catch:{ Exception -> 0x01e9 }
            int r2 = r9.getImageResource(r2, r3)     // Catch:{ Exception -> 0x01e9 }
            r10.setImageResource(r2)     // Catch:{ Exception -> 0x01e9 }
            int r10 = r1.getSchedulingRepeat()     // Catch:{ Exception -> 0x01e9 }
            if (r10 != 0) goto L_0x01b2
            int r10 = r1.getSchedulingStatus()     // Catch:{ Exception -> 0x01e9 }
            if (r10 != 0) goto L_0x01b2
            long r2 = com.argonremote.openappscheduler.util.DateHelper.getCurrentDate()     // Catch:{ Exception -> 0x01e9 }
            long r5 = r1.getSchedulingDateStart()     // Catch:{ Exception -> 0x01e9 }
            boolean r10 = com.argonremote.openappscheduler.util.DateHelper.isValidSchedulingDateStart(r2, r5)     // Catch:{ Exception -> 0x01e9 }
            if (r10 != 0) goto L_0x01b2
            android.widget.ImageButton r10 = r12.bScheduling     // Catch:{ Exception -> 0x01e9 }
            r10.setClickable(r0)     // Catch:{ Exception -> 0x01e9 }
            goto L_0x01b7
        L_0x01b2:
            android.widget.ImageButton r10 = r12.bScheduling     // Catch:{ Exception -> 0x01e9 }
            r10.setClickable(r7)     // Catch:{ Exception -> 0x01e9 }
        L_0x01b7:
            android.widget.ImageButton r10 = r12.bScheduling     // Catch:{ Exception -> 0x01e9 }
            r10.setFocusable(r0)     // Catch:{ Exception -> 0x01e9 }
            if (r4 != 0) goto L_0x01d6
            android.widget.ImageView r10 = r12.iIcon     // Catch:{ Exception -> 0x01e9 }
            android.content.res.Resources r12 = r9.res     // Catch:{ Exception -> 0x01e9 }
            r0 = 0
            java.lang.String r0 = androidx.arch.core.util.IRY.RHIrAtSSNe.JqZAUHnafpM     // Catch:{ Exception -> 0x01e9 }
            r1 = 0
            java.lang.String r1 = kotlinx.coroutines.flow.internal.p009TL.uWuhdrcVKgv.vfudQNhdtbB     // Catch:{ Exception -> 0x01e9 }
            android.content.Context r2 = r9.mContext     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ Exception -> 0x01e9 }
            int r12 = r12.getIdentifier(r0, r1, r2)     // Catch:{ Exception -> 0x01e9 }
            r10.setImageResource(r12)     // Catch:{ Exception -> 0x01e9 }
            goto L_0x01ed
        L_0x01d6:
            android.widget.ImageView r10 = r12.iIcon     // Catch:{ Exception -> 0x01e4 }
            android.content.pm.PackageManager r12 = r9.f18pm     // Catch:{ Exception -> 0x01e4 }
            java.lang.String r0 = r4.packageName     // Catch:{ Exception -> 0x01e4 }
            android.graphics.drawable.Drawable r12 = r12.getApplicationIcon(r0)     // Catch:{ Exception -> 0x01e4 }
            r10.setImageDrawable(r12)     // Catch:{ Exception -> 0x01e4 }
            goto L_0x01ed
        L_0x01e4:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ Exception -> 0x01e9 }
            goto L_0x01ed
        L_0x01e9:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01ed:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.argonremote.openappscheduler.adapter.ListTemplatesAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public List<Template> getItems() {
        return this.mItems;
    }

    public void setItems(List<Template> list) {
        this.mItems = list;
    }

    private final class ChangeSchedulingStatusListener implements View.OnClickListener {
        private final int position;
        private final Template template;

        private ChangeSchedulingStatusListener(Template template2, int i) {
            this.template = template2;
            this.position = i;
        }

        public void onClick(View view) {
            if (view.getId() != C0491R.C0342id.bScheduling) {
                return;
            }
            if (this.template.getSchedulingStatus() == 1) {
                ListTemplatesAdapter.this.showChangeSchedulingStatusConfirmation(this.template, this.position);
            } else {
                ListTemplatesAdapter.this.changeSchedulingStatus(this.template, this.position);
            }
        }
    }

    /* access modifiers changed from: private */
    public void changeSchedulingStatus(Template template, int i) {
        int i2;
        Resources resources;
        int i3 = template.getSchedulingStatus() == 1 ? 0 : 1;
        long schedulingDateStart = template.getSchedulingDateStart();
        if (i3 == 1 && !DateHelper.isValidSchedulingDateStart(DateHelper.getCurrentDate(), template.getSchedulingDateStart())) {
            if (template.getSchedulingRepeat() == 0) {
                Globals.showToastMessage(this.res.getString(C0491R.string.invalid_date), this.mContext);
                return;
            } else {
                schedulingDateStart = AlarmReceiver.getFutureDate(DateHelper.getCurrentDate(), template.getSchedulingDateStart(), (long) template.getSchedulingInterval(), template.getSchedulingIntervalMultiplier());
                this.mTemplateDao.updateTemplate(schedulingDateStart, template.getId(), DBHelper.COLUMN_TEMPLATE_SCHEDULING_DATE_START);
            }
        }
        long j = schedulingDateStart;
        if (i3 == 1) {
            AlarmReceiver.setAlarm(this.mContext, j, template.getSchedulingDateEnd(), template.getSchedulingInterval(), template.getSchedulingIntervalMultiplier(), template.getSchedulingConfirmTask(), template.getSchedulingRepeat(), template.getSchedulingId(), template.getId());
        } else {
            AlarmReceiver.cancelAlarms(this.mContext, template.getSchedulingId());
        }
        this.mTemplateDao.updateTemplate(i3, template.getId(), DBHelper.COLUMN_TEMPLATE_SCHEDULING_STATUS);
        if (i3 == 1) {
            resources = this.res;
            i2 = C0491R.string.scheduling_on;
        } else {
            resources = this.res;
            i2 = C0491R.string.scheduling_off;
        }
        Globals.showToastMessage(resources.getString(i2), this.mContext);
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION_SERVICE_STATUS_CHANGED);
        intent.putExtra("STATUS", i3);
        intent.putExtra("POSITION", i);
        this.mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void showChangeSchedulingStatusConfirmation(final Template template, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle((CharSequence) this.res.getString(C0491R.string.disable));
        builder.setMessage((CharSequence) this.res.getString(C0491R.string.disable_service_confirmation));
        builder.setPositiveButton(17039379, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ListTemplatesAdapter.this.changeSchedulingStatus(template, i);
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton(17039369, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    class ViewHolder {
        ImageButton bScheduling;
        ImageView iIcon;
        View lInterval;
        TextView tDescription;
        TextView tInterval;
        TextView tName;
        TextView tScheduling;
        TextView tText;

        ViewHolder() {
        }
    }

    public int getBackgroundResource(int i, Context context) {
        try {
            return this.res.getIdentifier(i == 0 ? "black_circle_drawable" : "pink_500_circle_drawable", "drawable", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getImageResource(int i, Context context) {
        try {
            return this.res.getIdentifier(i == 1 ? "ic_repeat_white_18dp" : "ic_access_time_white_18dp", "mipmap", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTextColor(int i, Context context) {
        if (i != 0) {
            return this.res.getColor(C0491R.color.pink_500);
        }
        try {
            return this.res.getColor(C0491R.color.blue_grey_400);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
