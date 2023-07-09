package com.devmukul.appscheduler.temp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.unusedapprestrictions.lzo.PulQqFCokNqFC;
import androidx.emoji2.text.xEU.NjQiLa;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.argonremote.openappscheduler.dao.TemplateDAO;
import com.argonremote.openappscheduler.model.Template;
import com.argonremote.openappscheduler.receiver.AlarmReceiver;
import com.argonremote.openappscheduler.util.Constants;
import com.argonremote.openappscheduler.util.DateHelper;
import com.argonremote.openappscheduler.util.Globals;
import com.pairip.licensecheck3.LicenseClientV3;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddTemplateActivity extends AppCompatActivity implements ActivityDynamics, View.OnClickListener {
    public static final String TAG = "AddTemplateActivity";
    public static boolean alarmChanged = false;
    public static boolean alarmSet;
    public static ImageButton bEnable;
    public static CheckBox cConfirmTask;
    public static CheckBox cRepeat;
    public static Calendar calendar = Calendar.getInstance();
    private static int currentSchedulingStatus;
    public static TextView tDate;
    public static TextView tHour;
    public static TextView tStatus;
    private Activity activity;
    public ArrayList<Integer> allAlarmIds;
    public ArrayList<Integer> allNotificationIds;
    private String currentDescription = "";
    private String currentName = "";
    private int currentNotificationId = 0;
    private String currentPackageName = "";
    private int currentSchedulingConfirmTask = 0;
    private long currentSchedulingDateEnd = 0;
    private long currentSchedulingDateStart = 0;
    private int currentSchedulingId = -1;
    private int currentSchedulingInterval = 1;
    /* access modifiers changed from: private */
    public long currentSchedulingIntervalMultiplier = 86400000;
    private int currentSchedulingRepeat = 0;
    private String currentText = "";
    boolean dataChanged = false;
    private EditText eDescription;
    EditText eInterval;
    private EditText eName;
    private EditText eText;
    private ImageView iAppIcon;
    private View lAppSelector;
    View lDate;
    View lHour;
    View lInterval;
    private int mListSize;
    private Template mTemplate;
    private TemplateDAO mTemplateDao;
    private long mTemplateId = -1;
    boolean newService = true;

    /* renamed from: pm */
    PackageManager f15pm;
    Resources res;
    Spinner sIntervals;
    private TextView tAppName;
    private TextView tAppPackageName;
    private Toolbar tTopBar;

    public static long getIntervalMultiplierBySpinnerSelection(int i) {
        long j = 86400000;
        long j2 = i == 0 ? Constants.ALARM_MANAGER_INTERVAL_MINUTE : 86400000;
        if (i == 1) {
            j2 = 3600000;
        }
        if (i != 2) {
            j = j2;
        }
        if (i == 3) {
            j = Constants.ALARM_MANAGER_INTERVAL_MONTH;
        }
        return i == 4 ? Constants.ALARM_MANAGER_INTERVAL_YEAR : j;
    }

    public static int getSpinnerSelectionByIntervalMultiplier(long j) {
        int i = 2;
        int i2 = j == Constants.ALARM_MANAGER_INTERVAL_MINUTE ? 0 : 2;
        if (j == 3600000) {
            i2 = 1;
        }
        if (j != 86400000) {
            i = i2;
        }
        if (j == Constants.ALARM_MANAGER_INTERVAL_MONTH) {
            i = 3;
        }
        if (j == Constants.ALARM_MANAGER_INTERVAL_YEAR) {
            return 4;
        }
        return i;
    }

    public boolean hasAlarm() {
        return alarmSet || alarmChanged;
    }

    public boolean configurationChanged() {
        return this.dataChanged || alarmChanged;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LicenseClientV3.onActivityCreate(this);
        super.onCreate(bundle);
        setContentView((int) C0491R.layout.activity_add_template);
        this.activity = this;
        this.res = getResources();
        this.f15pm = getPackageManager();
        Toolbar toolbar = (Toolbar) findViewById(C0491R.C0342id.tTopBar);
        this.tTopBar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mTemplateDao = new TemplateDAO(this);
        initViews();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.mTemplate = (Template) intent.getSerializableExtra(MainActivity.EXTRA_TEMPLATE);
            this.mListSize = extras.getInt(MainActivity.EXTRA_LIST_SIZE, 0);
        }
        if (this.mTemplate != null) {
            getWindow().setSoftInputMode(3);
            this.activity.setTitle(this.res.getString(C0491R.string.edit_template));
            this.mTemplateId = this.mTemplate.getId();
            this.eName.setText(this.mTemplate.getName());
            this.eDescription.setText(this.mTemplate.getDescription());
            this.currentPackageName = this.mTemplate.getPackageName();
            currentSchedulingStatus = this.mTemplate.getSchedulingStatus();
            this.currentSchedulingDateStart = this.mTemplate.getSchedulingDateStart();
            this.currentSchedulingDateEnd = this.mTemplate.getSchedulingDateEnd();
            this.currentSchedulingConfirmTask = this.mTemplate.getSchedulingConfirmTask();
            this.currentSchedulingRepeat = this.mTemplate.getSchedulingRepeat();
            this.currentSchedulingInterval = this.mTemplate.getSchedulingInterval();
            this.currentSchedulingIntervalMultiplier = this.mTemplate.getSchedulingIntervalMultiplier();
            this.currentSchedulingId = this.mTemplate.getSchedulingId();
            this.newService = false;
        } else {
            this.activity.setTitle(this.res.getString(C0491R.string.add_new_template));
        }
        initAlarmView(this.activity);
        refreshAppView(this.currentPackageName);
        setListeners();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("PACKAGE_NAME");
            if (Globals.isValidValue(string)) {
                this.currentPackageName = string;
                refreshAppView(string);
                this.dataChanged = true;
            }
        }
    }

    public void setListeners() {
        this.eName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AddTemplateActivity.this.dataChanged = true;
            }
        });
        this.eDescription.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AddTemplateActivity.this.dataChanged = true;
            }
        });
        this.eText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AddTemplateActivity.this.dataChanged = true;
            }
        });
        this.eInterval.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AddTemplateActivity.alarmChanged = true;
            }
        });
        this.sIntervals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != AddTemplateActivity.getSpinnerSelectionByIntervalMultiplier(AddTemplateActivity.this.currentSchedulingIntervalMultiplier)) {
                    AddTemplateActivity.alarmChanged = true;
                }
            }
        });
    }

    public void refreshAppView(String str) {
        CharSequence charSequence;
        try {
            if (Globals.isValidValue(str)) {
                ApplicationInfo applicationInfo = null;
                try {
                    applicationInfo = this.f15pm.getApplicationInfo(str, 0);
                    charSequence = this.f15pm.getApplicationLabel(applicationInfo);
                } catch (Exception unused) {
                    charSequence = str;
                }
                this.tAppName.setText(charSequence);
                this.tAppPackageName.setText(str);
                if (applicationInfo == null) {
                    this.iAppIcon.setImageResource(this.res.getIdentifier("ic_block_black_48dp", "mipmap", getPackageName()));
                    return;
                }
                try {
                    this.iAppIcon.setImageDrawable(this.f15pm.getApplicationIcon(applicationInfo.packageName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void initAlarmView(Context context) {
        int i = 1;
        if (this.currentSchedulingId <= 0 || this.currentSchedulingDateStart <= 0) {
            calendar.setTime(new Date());
        } else {
            alarmSet = true;
            calendar.setTime(new Date(this.currentSchedulingDateStart));
            tHour.setText(DateHelper.getTime(this.currentSchedulingDateStart, 3));
        }
        tDate.setText(DateHelper.getDate(calendar.getTimeInMillis(), 2));
        int i2 = 0;
        cConfirmTask.setChecked(this.currentSchedulingConfirmTask == 1);
        cRepeat.setChecked(this.currentSchedulingRepeat == 1);
        View view = this.lInterval;
        if (this.currentSchedulingRepeat != 1) {
            i2 = 8;
        }
        view.setVisibility(i2);
        EditText editText = this.eInterval;
        int i3 = this.currentSchedulingInterval;
        if (i3 > 0) {
            i = i3;
        }
        editText.setText(String.valueOf(i));
        this.sIntervals.setSelection(getSpinnerSelectionByIntervalMultiplier(this.currentSchedulingIntervalMultiplier));
        refreshAlarmView(context);
    }

    public static void refreshAlarmView(Context context) {
        int i = currentSchedulingStatus;
        String str = NjQiLa.QLmXjQBVofm;
        if (i == 1) {
            bEnable.setBackgroundResource(context.getResources().getIdentifier("pink_500_circle_drawable", str, context.getPackageName()));
            bEnable.setImageResource(context.getResources().getIdentifier("com.argonremote.openappscheduler:mipmap/ic_alarm_on_white_24dp", (String) null, (String) null));
        } else {
            bEnable.setBackgroundResource(context.getResources().getIdentifier("blue_grey_500_circle_drawable", str, context.getPackageName()));
            bEnable.setImageResource(context.getResources().getIdentifier("com.argonremote.openappscheduler:mipmap/ic_alarm_off_white_24dp", (String) null, (String) null));
        }
        cConfirmTask.setEnabled(alarmSet);
        cRepeat.setEnabled(alarmSet);
    }

    public void setAlarm() {
        AlarmReceiver.setAlarm(this.activity, this.currentSchedulingDateStart, this.currentSchedulingDateEnd, this.currentSchedulingInterval, this.currentSchedulingIntervalMultiplier, this.currentSchedulingConfirmTask, this.currentSchedulingRepeat, this.currentSchedulingId, this.mTemplateId);
    }

    public void cancelAlarm() {
        AlarmReceiver.cancelAlarms(this.activity, this.currentSchedulingId);
    }

    public void refreshAlarm() {
        cancelAlarm();
        setAlarm();
    }

    public static void enableAlarm(Context context, boolean z) {
        currentSchedulingStatus = z ? 1 : 0;
        alarmChanged = true;
        alarmSet = true;
        refreshAlarmView(context);
        if (currentSchedulingStatus == 1) {
            Globals.showToastMessage(Globals.getStringFromResources(C0491R.string.scheduling_set, context), context);
        } else {
            Globals.showToastMessage(Globals.getStringFromResources(C0491R.string.scheduling_off, context), context);
        }
    }

    public void registerAlarm(boolean z) {
        if (currentSchedulingStatus == 1) {
            if (alarmChanged || z) {
                refreshAlarm();
            }
        } else if (alarmChanged && !z) {
            cancelAlarm();
        }
        alarmChanged = false;
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            Calendar instance = Calendar.getInstance();
            return new TimePickerDialog(getActivity(), this, instance.get(11), instance.get(12), DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            if (timePicker.isShown()) {
                AddTemplateActivity.calendar.set(11, i);
                AddTemplateActivity.calendar.set(12, i2);
                AddTemplateActivity.calendar.set(13, 0);
                AddTemplateActivity.calendar.set(14, 0);
                AddTemplateActivity.tHour.setText(DateHelper.getTime(AddTemplateActivity.calendar.getTimeInMillis(), 3));
                AddTemplateActivity.enableAlarm(getActivity(), true);
                AddTemplateActivity.showDatePickerDialog(getParentFragmentManager());
            }
        }
    }

    public static void showTimePickerDialog(FragmentManager fragmentManager) {
        new TimePickerFragment().show(fragmentManager, "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public Dialog onCreateDialog(Bundle bundle) {
            Calendar instance = Calendar.getInstance();
            return new DatePickerDialog(getActivity(), this, instance.get(1), instance.get(2), instance.get(5));
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            if (datePicker.isShown()) {
                AddTemplateActivity.calendar.set(1, i);
                AddTemplateActivity.calendar.set(2, i2);
                AddTemplateActivity.calendar.set(5, i3);
                AddTemplateActivity.tDate.setText(DateHelper.getDate(AddTemplateActivity.calendar.getTimeInMillis(), 2));
                AddTemplateActivity.enableAlarm(getActivity(), true);
            }
        }
    }

    public static void showDatePickerDialog(FragmentManager fragmentManager) {
        new DatePickerFragment().show(fragmentManager, "datePicker");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        releaseResources();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        onKeyDownAction();
        return true;
    }

    public void onKeyDownAction() {
        if (!configurationChanged()) {
            finish();
        } else if (this.newService) {
            showExitDialogConfirmation(this.res.getString(C0491R.string.wizard_exit));
        } else {
            showExitDialogConfirmation(this.res.getString(C0491R.string.service_changed_exit));
        }
    }

    private void showExitDialogConfirmation(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) this.res.getString(C0491R.string.exit));
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton(17039379, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                AddTemplateActivity.this.finish();
            }
        });
        builder.setNeutralButton(17039369, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void initViews() {
        this.eName = (EditText) findViewById(C0491R.C0342id.eName);
        this.eDescription = (EditText) findViewById(C0491R.C0342id.eDescription);
        this.eText = (EditText) findViewById(C0491R.C0342id.eText);
        View findViewById = findViewById(C0491R.C0342id.lAppSelector);
        this.lAppSelector = findViewById;
        findViewById.setOnClickListener(this);
        this.iAppIcon = (ImageView) findViewById(C0491R.C0342id.iAppIcon);
        this.tAppName = (TextView) findViewById(C0491R.C0342id.tAppName);
        this.tAppPackageName = (TextView) findViewById(C0491R.C0342id.tAppPackageName);
        ImageButton imageButton = (ImageButton) findViewById(C0491R.C0342id.bEnable);
        bEnable = imageButton;
        imageButton.setOnClickListener(this);
        tStatus = (TextView) findViewById(C0491R.C0342id.tStatus);
        View findViewById2 = findViewById(C0491R.C0342id.lHour);
        this.lHour = findViewById2;
        findViewById2.setOnClickListener(this);
        tHour = (TextView) findViewById(C0491R.C0342id.tHour);
        View findViewById3 = findViewById(C0491R.C0342id.lDate);
        this.lDate = findViewById3;
        findViewById3.setOnClickListener(this);
        tDate = (TextView) findViewById(C0491R.C0342id.tDate);
        CheckBox checkBox = (CheckBox) findViewById(C0491R.C0342id.cConfirmTask);
        cConfirmTask = checkBox;
        checkBox.setOnClickListener(this);
        CheckBox checkBox2 = (CheckBox) findViewById(C0491R.C0342id.cRepeat);
        cRepeat = checkBox2;
        checkBox2.setOnClickListener(this);
        this.lInterval = findViewById(C0491R.C0342id.lInterval);
        this.eInterval = (EditText) findViewById(C0491R.C0342id.eInterval);
        this.sIntervals = (Spinner) findViewById(C0491R.C0342id.sIntervals);
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, C0491R.array.intervals_array, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        this.sIntervals.setAdapter(createFromResource);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(C0491R.C0344menu.menu_add_template, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onKeyDownAction();
            return false;
        } else if (itemId == C0491R.C0342id.runTemplate) {
            if (!Globals.isValidValue(this.currentPackageName)) {
                return false;
            }
            Globals.openApp(this.activity, this.currentPackageName);
            return false;
        } else if (itemId != C0491R.C0342id.saveTemplate) {
            return false;
        } else {
            if (this.mTemplate != null) {
                showConfirmation(Globals.getStringFromResources(C0491R.string.save, this.activity));
                return false;
            }
            addTemplate(this.newService);
            return false;
        }
    }

    public void resetFields() {
        String str = PulQqFCokNqFC.wkgACguXFy;
        this.currentName = str;
        this.currentDescription = str;
        this.currentText = str;
        this.currentSchedulingDateStart = 0;
        this.currentSchedulingDateEnd = 0;
        this.currentSchedulingConfirmTask = 0;
        this.currentSchedulingRepeat = 0;
        this.currentSchedulingInterval = 1;
        this.currentSchedulingIntervalMultiplier = 86400000;
    }

    private boolean validate(boolean z) {
        resetFields();
        Editable text = this.eName.getText();
        Editable text2 = this.eDescription.getText();
        Editable text3 = this.eText.getText();
        Editable text4 = this.eInterval.getText();
        if (!Globals.isValidValue(this.currentPackageName)) {
            Toast.makeText(this, C0491R.string.empty_fields_message, 1).show();
            return false;
        } else if (!cRepeat.isChecked() || !TextUtils.isEmpty(text4)) {
            try {
                int intValue = Globals.isValidValue((CharSequence) text4) ? Integer.valueOf(text4.toString()).intValue() : 1;
                if (cRepeat.isChecked() && intValue <= 0) {
                    Toast.makeText(this, C0491R.string.invalid_value, 1).show();
                    return false;
                } else if (!hasAlarm() || currentSchedulingStatus != 1 || calendar.getTimeInMillis() > DateHelper.getCurrentDate()) {
                    this.currentName = text.toString();
                    this.currentDescription = text2.toString();
                    this.currentText = text3.toString();
                    if (hasAlarm()) {
                        this.currentSchedulingDateStart = calendar.getTimeInMillis();
                    }
                    this.currentSchedulingConfirmTask = cConfirmTask.isChecked() ? 1 : 0;
                    this.currentSchedulingRepeat = cRepeat.isChecked() ? 1 : 0;
                    if (cRepeat.isChecked()) {
                        this.currentSchedulingInterval = intValue;
                        this.currentSchedulingIntervalMultiplier = getIntervalMultiplierBySpinnerSelection(this.sIntervals.getSelectedItemPosition());
                    }
                    return true;
                } else {
                    Toast.makeText(this, C0491R.string.set_future_date, 1).show();
                    return false;
                }
            } catch (Exception unused) {
                Toast.makeText(this, C0491R.string.invalid_value, 1).show();
                return false;
            }
        } else {
            Toast.makeText(this, C0491R.string.empty_fields_message, 1).show();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void addTemplate(boolean z) {
        try {
            if (!Globals.isPremiumUser(this.activity) && this.mListSize >= 3) {
                startPremiumActivity();
            } else if (validate(z)) {
                ArrayList<Integer> schedulingIds = this.mTemplateDao.getSchedulingIds();
                this.allAlarmIds = schedulingIds;
                int uniqueId = Globals.getUniqueId(schedulingIds);
                this.currentSchedulingId = uniqueId;
                Template createTemplate = this.mTemplateDao.createTemplate(this.currentName, this.currentDescription, this.currentPackageName, this.currentText, 1, currentSchedulingStatus, this.currentSchedulingDateStart, this.currentSchedulingDateEnd, this.currentSchedulingRepeat, this.currentSchedulingInterval, this.currentSchedulingIntervalMultiplier, this.currentSchedulingConfirmTask, uniqueId);
                this.mTemplateId = createTemplate.getId();
                registerAlarm(z);
                Bundle bundle = new Bundle();
                bundle.putLong(MainActivity.EXTRA_NEW_TEMPLATE, createTemplate.getId());
                Globals.startGenericActivity((Context) this.activity, bundle, 67108864, (Class<?>) MainActivity.class);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void updateTemplate() {
        try {
            if (validate(this.newService)) {
                TemplateDAO templateDAO = this.mTemplateDao;
                String str = this.currentName;
                String str2 = this.currentDescription;
                String str3 = this.currentPackageName;
                String str4 = this.currentText;
                int i = currentSchedulingStatus;
                long j = this.currentSchedulingDateStart;
                long j2 = this.currentSchedulingDateEnd;
                int i2 = this.currentSchedulingRepeat;
                int i3 = this.currentSchedulingInterval;
                long j3 = this.currentSchedulingIntervalMultiplier;
                int i4 = this.currentSchedulingConfirmTask;
                int i5 = i;
                long j4 = j3;
                int i6 = i4;
                templateDAO.updateTemplate(str, str2, str3, str4, 1, i5, j, j2, i2, i3, j4, i6, this.currentSchedulingId, this.mTemplateId);
                registerAlarm(this.newService);
                Bundle bundle = new Bundle();
                bundle.putLong(MainActivity.EXTRA_NEW_TEMPLATE, this.mTemplateId);
                Globals.startGenericActivity((Context) this.activity, bundle, 67108864, (Class<?>) MainActivity.class);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPremiumActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("PREMIUM_TITLE", this.res.getString(C0491R.string.unlimited_services));
        bundle.putString("PREMIUM_MESSAGE", this.res.getString(C0491R.string.unlimited_services_detail));
        Globals.startGenericActivity((Context) this.activity, bundle, 268435456, (Class<?>) PremiumActivity.class);
    }

    public void showConfirmation(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) str);
        builder.setItems(new CharSequence[]{Globals.getStringFromResources(C0491R.string.update, this.activity), Globals.getStringFromResources(C0491R.string.add_new_template, this.activity)}, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    AddTemplateActivity.this.updateTemplate();
                } else if (i == 1) {
                    AddTemplateActivity.this.addTemplate(true);
                }
            }
        });
        builder.show();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(View r4) {
        /*
            r3 = this;
            int r4 = r4.getId()
            r0 = 2131296374(0x7f090076, float:1.8210663E38)
            if (r4 != r0) goto L_0x0015
            android.app.Activity r4 = r3.activity
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            java.lang.Class<com.argonremote.openappscheduler.ListAppsActivity> r1 = com.argonremote.openappscheduler.ListAppsActivity.class
            r2 = 0
            com.argonremote.openappscheduler.util.Globals.startGenericActivity((android.content.Context) r4, (android.os.Bundle) r2, (int) r0, (java.lang.Class<?>) r1)
            goto L_0x0082
        L_0x0015:
            r0 = 2131296320(0x7f090040, float:1.8210553E38)
            r1 = 0
            r2 = 1
            if (r4 != r0) goto L_0x0034
            boolean r4 = alarmSet
            if (r4 == 0) goto L_0x002c
            android.app.Activity r4 = r3.activity
            int r0 = currentSchedulingStatus
            if (r0 != r2) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r1 = r2
        L_0x0028:
            enableAlarm(r4, r1)
            goto L_0x0082
        L_0x002c:
            androidx.fragment.app.FragmentManager r4 = r3.getSupportFragmentManager()
            showTimePickerDialog(r4)
            goto L_0x0082
        L_0x0034:
            r0 = 2131296378(0x7f09007a, float:1.821067E38)
            if (r4 != r0) goto L_0x0041
            androidx.fragment.app.FragmentManager r4 = r3.getSupportFragmentManager()
            showTimePickerDialog(r4)
            goto L_0x0082
        L_0x0041:
            r0 = 2131296376(0x7f090078, float:1.8210667E38)
            if (r4 != r0) goto L_0x0063
            boolean r4 = r3.hasAlarm()
            if (r4 == 0) goto L_0x0054
            androidx.fragment.app.FragmentManager r4 = r3.getSupportFragmentManager()
            showDatePickerDialog(r4)
            goto L_0x0082
        L_0x0054:
            r4 = 2131689582(0x7f0f006e, float:1.9008183E38)
            android.app.Activity r0 = r3.activity
            java.lang.String r4 = com.argonremote.openappscheduler.util.Globals.getStringFromResources(r4, r0)
            android.app.Activity r0 = r3.activity
            com.argonremote.openappscheduler.util.Globals.showToastMessage(r4, r0)
            goto L_0x0082
        L_0x0063:
            r0 = 2131296327(0x7f090047, float:1.8210568E38)
            if (r4 != r0) goto L_0x006b
            alarmChanged = r2
            goto L_0x0082
        L_0x006b:
            r0 = 2131296328(0x7f090048, float:1.821057E38)
            if (r4 != r0) goto L_0x0082
            android.view.View r4 = r3.lInterval
            android.widget.CheckBox r0 = cRepeat
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x007b
            goto L_0x007d
        L_0x007b:
            r1 = 8
        L_0x007d:
            r4.setVisibility(r1)
            alarmChanged = r2
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.argonremote.openappscheduler.AddTemplateActivity.onClick(android.view.View):void");
    }

    public void releaseResources() {
        calendar.clear();
        alarmChanged = false;
        alarmSet = false;
        this.dataChanged = false;
        resetFields();
        currentSchedulingStatus = 0;
        this.mTemplateDao.close();
    }
}
