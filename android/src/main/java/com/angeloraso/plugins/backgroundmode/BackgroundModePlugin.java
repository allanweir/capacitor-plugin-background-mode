package com.angeloraso.plugins.backgroundmode;

import android.content.Context;
import android.os.Build;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "BackgroundMode")
public class BackgroundModePlugin extends Plugin {

    private BackgroundMode backgroundMode;

    public void load() {
        AppCompatActivity activity = getActivity();
        Context context = getContext();
        View webView = bridge.getWebView();
        backgroundMode = new BackgroundMode(activity, context, webView);
        backgroundMode.setBackgroundModeEventListener(this::onBackgroundModeEvent);
    }

    void onBackgroundModeEvent(String event) {
        JSObject jsObject = new JSObject();
        switch (event) {
            case BackgroundMode.EVENT_APP_IN_BACKGROUND:
            case BackgroundMode.EVENT_APP_IN_FOREGROUND:
                bridge.triggerWindowJSEvent(event);
                notifyListeners(event, jsObject);
                break;
        }
    }

    @PluginMethod
    public void enable(PluginCall call) {
        if (getActivity().isFinishing()) {
            String appFinishingMsg = getActivity().getString(R.string.app_finishing);
            call.reject(appFinishingMsg);
            return;
        }

        backgroundMode.enable();

        call.resolve();
    }

    /**
     * Called when the activity is no longer visible to the user.
     */
    @Override
    public void handleOnStop() {
        backgroundMode.onStop();
    }

    /**
     * Called when the activity will start interacting with the user.
     */
    @Override
    public void handleOnResume() {
        backgroundMode.onResume();
    }

    /**
     * Called when the activity will be destroyed.
     */
    @Override
    public void handleOnDestroy() {
        backgroundMode.onDestroy();
        unsetAppListeners();
    }

    @PluginMethod
    public void disable(PluginCall call) {
        if (getActivity().isFinishing()) {
            String appFinishingMsg = getActivity().getString(R.string.app_finishing);
            call.reject(appFinishingMsg);
            return;
        }

        backgroundMode.disable();
        call.resolve();
    }

    @PluginMethod
    public void getSettings(PluginCall call) {
        BackgroundModeSettings settings = backgroundMode.getSettings();
        JSObject res = new JSObject();
        res.put("title", settings.getTitle());
        res.put("text", settings.getText());
        res.put("subText", settings.getSubText());
        res.put("bigText", settings.getBigText());
        res.put("resume", settings.getResume());
        res.put("silent", settings.getSilent());
        res.put("hidden", settings.getHidden());
        res.put("color", settings.getColor());
        res.put("icon", settings.getIcon());
        res.put("channelName", settings.getChannelName());
        res.put("channelDescription", settings.getChannelDescription());
        res.put("allowClose", settings.getAllowClose());
        res.put("closeIcon", settings.getCloseIcon());
        res.put("closeTitle", settings.getCloseTitle());
        res.put("showWhen", settings.getShowWhen());
        res.put("visibility", settings.getVisibility());
        res.put("disableWebViewOptimization", settings.isDisableWebViewOptimization());
        call.resolve(res);
    }

    @PluginMethod
    public void setSettings(PluginCall call) {
        BackgroundModeSettings currentSettings = backgroundMode.getSettings();
        BackgroundModeSettings settings = buildSettings(currentSettings, call);
        backgroundMode.setSettings(settings);
        call.resolve();
    }

    @PluginMethod
    public void checkForegroundPermission(PluginCall call) {
        boolean foregroundPermission = backgroundMode.checkForegroundPermission();
        JSObject res = new JSObject();
        res.put("enabled", foregroundPermission);
        call.resolve(res);
    }

    @PluginMethod
    public void requestForegroundPermission(PluginCall call) {
        backgroundMode.requestForegroundPermission();
        call.resolve();
    }

    @PluginMethod
    public void moveToBackground(PluginCall call) {
        backgroundMode.moveToBackground();
        call.resolve();
    }

    @PluginMethod
    public void moveToForeground(PluginCall call) {
        backgroundMode.moveToForeground();
        call.resolve();
    }

    @PluginMethod
    public void isScreenOff(PluginCall call) {
        boolean isScreenOff = backgroundMode.isScreenOff();
        JSObject res = new JSObject();
        res.put("isScreenOff", isScreenOff);
        call.resolve(res);
    }

    @PluginMethod
    public void isEnabled(PluginCall call) {
        boolean isEnabled = backgroundMode.isEnabled();
        JSObject res = new JSObject();
        res.put("enabled", isEnabled);
        call.resolve(res);
    }

    @PluginMethod
    public void isActive(PluginCall call) {
        boolean isActive = backgroundMode.isActive();
        JSObject res = new JSObject();
        res.put("activated", isActive);
        call.resolve(res);
    }

    @PluginMethod
    public void wakeUp(PluginCall call) {
        backgroundMode.wakeUp();
        call.resolve();
    }

    @PluginMethod
    public void unlock(PluginCall call) {
        backgroundMode.unlock();
        call.resolve();
    }

    @PluginMethod
    public void isIgnoringBatteryOptimizations(PluginCall call) {
        boolean isIgnoring = backgroundMode.isIgnoringBatteryOptimizations();
        JSObject res = new JSObject();
        res.put("isIgnoring", isIgnoring);
        call.resolve(res);
    }

    @PluginMethod
    public void disableBatteryOptimizations(PluginCall call) {
        backgroundMode.disableBatteryOptimizations();
        call.resolve();
    }

    @PluginMethod
    public void enableWebViewOptimizations(PluginCall call) {
        backgroundMode.enableWebViewOptimizations();
        call.resolve();
    }

    @PluginMethod
    public void disableWebViewOptimizations(PluginCall call) {
        backgroundMode.disableWebViewOptimizations();
        call.resolve();
    }

    @PluginMethod
    public void sdkVersion(PluginCall call) {
        JSObject res = new JSObject();
        res.put("version", Build.VERSION.SDK_INT);
        call.resolve(res);
    }

    private BackgroundModeSettings buildSettings(BackgroundModeSettings settings, PluginCall call) {
        if (call.hasOption("title")) {
            settings.setTitle((call.getString("title")));
        }

        if (call.hasOption("text")) {
            settings.setText((call.getString("text")));
        }

        if (call.hasOption("subText")) {
            settings.setSubText((call.getString("subText")));
        }

        if (call.hasOption("bigText")) {
            settings.setBigText((call.getBoolean("bigText")));
        }

        if (call.hasOption("resume")) {
            settings.setResume((call.getBoolean("resume")));
        }

        if (call.hasOption("silent")) {
            settings.setSilent((call.getBoolean("silent")));
        }

        if (call.hasOption("hidden")) {
            settings.setHidden((call.getBoolean("hidden")));
        }

        if (call.hasOption("color")) {
            settings.setColor((call.getString("color")));
        }

        if (call.hasOption("icon")) {
            settings.setIcon((call.getString("icon")));
        }

        if (call.hasOption("channelName")) {
            settings.setChannelName((call.getString("channelName")));
        }

        if (call.hasOption("channelDescription")) {
            settings.setChannelDescription((call.getString("channelDescription")));
        }

        if (call.hasOption("allowClose")) {
            settings.setAllowClose((call.getBoolean("allowClose")));
        }

        if (call.hasOption("closeIcon")) {
            settings.setCloseIcon((call.getString("closeIcon")));
        }

        if (call.hasOption("closeTitle")) {
            settings.setCloseTitle((call.getString("closeTitle")));
        }

        if (call.hasOption("showWhen")) {
            settings.setShowWhen((call.getBoolean("showWhen")));
        }

        if (call.hasOption("visibility")) {
            settings.setVisibility((call.getString("visibility")));
        }

        if (call.hasOption("disableWebViewOptimization")) {
            settings.setDisableWebViewOptimization((call.getBoolean("disableWebViewOptimization")));
        }

        return settings;
    }

    @Override
    @PluginMethod(returnType = PluginMethod.RETURN_NONE)
    public void removeAllListeners(PluginCall call) {
        super.removeAllListeners(call);
        unsetAppListeners();
    }

    private void unsetAppListeners() {
        bridge.getApp().setStatusChangeListener(null);
        bridge.getApp().setAppRestoredListener(null);
    }
}
