package com.devmukul.appscheduler.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RwW.ycfKydbagLfQqs;
import com.argonremote.openappscheduler.util.Constants;
import com.argonremote.openappscheduler.util.Globals;
import com.pairip.licensecheck3.LicenseClientV3;

public class SystemAlertWindowPermissionActivity extends Activity implements View.OnClickListener, ActivityDynamics {
    public static final String MAIN_CONTEXT = "MAIN";
    public static final String WELCOME_CONTEXT = "WELCOME";
    private boolean actionSystemAlertWindowSettings = false;
    private Activity activity;
    ImageView iWelcomeActionIndicator;
    ImageView iWelcomeIndicator;
    Resources res;
    TextView tWelcomeAction;
    TextView tWelcomeMessage;
    TextView tWelcomeSuggestion;
    TextView tWelcomeTitle;
    ImageButton vClose;
    View vWelcome;
    View vWelcomeAction;
    View vWelcomeHelp;
    String welcomeContext = MAIN_CONTEXT;

    public void releaseResources() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle extras;
        LicenseClientV3.onActivityCreate(this);
        super.onCreate(bundle);
        setContentView(C0491R.layout.activity_permission);
        this.activity = this;
        this.res = getResources();
        initViews();
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.welcomeContext = extras.getString("WELCOME_CONTEXT", MAIN_CONTEXT);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refreshViews();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        releaseResources();
    }

    private void initViews() {
        this.vWelcome = findViewById(C0491R.C0342id.vWelcome);
        this.iWelcomeIndicator = (ImageView) findViewById(C0491R.C0342id.iWelcomeIndicator);
        this.tWelcomeTitle = (TextView) findViewById(C0491R.C0342id.tWelcomeTitle);
        this.tWelcomeMessage = (TextView) findViewById(C0491R.C0342id.tWelcomeMessage);
        this.tWelcomeSuggestion = (TextView) findViewById(C0491R.C0342id.tWelcomeSuggestion);
        View findViewById = findViewById(C0491R.C0342id.vWelcomeAction);
        this.vWelcomeAction = findViewById;
        findViewById.setOnClickListener(this);
        this.iWelcomeActionIndicator = (ImageView) findViewById(C0491R.C0342id.iWelcomeActionIndicator);
        this.tWelcomeAction = (TextView) findViewById(C0491R.C0342id.tWelcomeAction);
        View findViewById2 = findViewById(C0491R.C0342id.vWelcomeHelp);
        this.vWelcomeHelp = findViewById2;
        findViewById2.setOnClickListener(this);
        ImageButton imageButton = (ImageButton) findViewById(C0491R.C0342id.vClose);
        this.vClose = imageButton;
        imageButton.setOnClickListener(this);
        this.vClose.setVisibility(4);
    }

    private void refreshViews() {
        boolean canDrawOverlays = Globals.canDrawOverlays(this.activity);
        this.actionSystemAlertWindowSettings = false;
        if (canDrawOverlays) {
            this.vWelcome.setBackgroundColor(this.res.getColor(C0491R.color.green_500));
            this.iWelcomeIndicator.setImageResource(this.res.getIdentifier("ic_check_circle_white_48dp", "mipmap", getPackageName()));
            this.tWelcomeTitle.setText(this.res.getString(C0491R.string.permission_enabled_title));
            this.tWelcomeMessage.setText(this.res.getString(C0491R.string.app_name));
            this.tWelcomeSuggestion.setText(this.res.getString(C0491R.string.permission_enabled_action));
            this.tWelcomeAction.setText(this.res.getString(C0491R.string.next_action));
            this.iWelcomeActionIndicator.setImageResource(this.res.getIdentifier("ic_arrow_forward_black_36dp", "mipmap", getPackageName()));
            return;
        }
        this.actionSystemAlertWindowSettings = true;
        this.vWelcome.setBackgroundColor(this.res.getColor(C0491R.color.blue_500));
        this.iWelcomeIndicator.setImageResource(this.res.getIdentifier("ic_launch_white_48dp", "mipmap", getPackageName()));
        if (this.welcomeContext.equals(WELCOME_CONTEXT)) {
            this.tWelcomeTitle.setText(this.res.getString(C0491R.string.enable_permission));
        } else {
            this.tWelcomeTitle.setText(this.res.getString(C0491R.string.problem_detected));
        }
        this.tWelcomeMessage.setText(this.res.getString(C0491R.string.enable_overlay_permission_message).replace("#APP_NAME#", this.res.getString(C0491R.string.app_name)));
        this.tWelcomeSuggestion.setText(this.res.getString(C0491R.string.enable_overlay_permission_action).replace("#APP_NAME#", this.res.getString(C0491R.string.app_name)));
        this.tWelcomeAction.setText(this.res.getString(C0491R.string.enable_permission_action));
        this.iWelcomeActionIndicator.setImageResource(this.res.getIdentifier("ic_touch_app_black_36dp", "mipmap", getPackageName()));
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        int id = view.getId();
        if (id == C0491R.C0342id.vWelcomeAction) {
            if (this.actionSystemAlertWindowSettings) {
                startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())));
                return;
            }
            Globals.startGenericActivity((Context) this.activity, (Bundle) null, 268435456, (Class<?>) MainActivity.class);
            finish();
        } else if (id == C0491R.C0342id.vWelcomeHelp) {
            bundle.putString("HELP_TITLE", this.res.getString(C0491R.string.app_name));
            bundle.putString(ycfKydbagLfQqs.oWQvreGUxKNuJJb, this.res.getString(C0491R.string.app_help_info));
            bundle.putString("HELP_TROUBLESHOOTING_LINK", Constants.TROUBLESHOOTING_LINK);
            bundle.putString("HELP_TUTORIALS_PLAYLIST_LINK", Constants.TUTORIALS_PLAYLIST_LINK);
            Globals.startGenericActivity((Context) this.activity, bundle, 268435456, (Class<?>) HelpActivity.class);
        } else if (id == C0491R.C0342id.vClose) {
            finish();
        }
    }
}
