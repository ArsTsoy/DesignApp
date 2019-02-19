package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.ColorUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public final class InAppNotification {
    static Snackbar snackbar;
    static InAppNotification inAppNotification = new InAppNotification();
    private static View mView;

    private InAppNotification() {
    }

    public static InAppNotification getInstance() {
        return inAppNotification;
    }

    public static InAppNotification make(@NonNull View view, @NonNull String title, @NonNull String text, @DrawableRes int drawableImageID, @ColorRes int colorResId) {
        Resources resources = view.getResources();
        mView = view;
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);

        View custom = LayoutInflater.from(view.getContext()).inflate(R.layout.in_app_notification, null);
        ((ViewGroup) snackbar.getView()).removeAllViews();
        ((ViewGroup) snackbar.getView()).addView(custom);

        TextView titleTV = custom.findViewById(R.id.notificationTitle);
        titleTV.setText(title);
        TextView textTV = custom.findViewById(R.id.notificationText);
        textTV.setText(text);

        CircleImageView image = custom.findViewById(R.id.notificationImage);
        image.setImageResource(drawableImageID);
        CircleImageView close = custom.findViewById(R.id.notificationClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        ViewGroup notification = custom.findViewById(R.id.in_app_notification);

        StateListDrawable shapeDrawable = (StateListDrawable) notification.getBackground();
        int colorBackground = resources.getColor(colorResId, null);
        shapeDrawable.setColorFilter(colorBackground, PorterDuff.Mode.MULTIPLY);

        if (isColorDark(colorBackground)) {
            titleTV.setTextColor(resources.getColor(R.color.absolute_white, null));
            textTV.setTextColor(resources.getColor(R.color.absolute_white, null));
            close.setColorFilter(resources.getColor(R.color.absolute_white, null));
            close.setBorderColor(resources.getColor(R.color.absolute_white, null));
        }

        configSnackDesign();
//        notification.setElevation(8);
        return getInstance();
    }


    public void show() {
        snackbar.show();
    }

    public void setIndefinite(boolean isIndefinite){
        if(isIndefinite) {
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }else{
            snackbar.setDuration(Snackbar.LENGTH_SHORT);
        }
    }

    private static boolean isColorDark(int color) {
        return ColorUtils.calculateLuminance(color) < 0.7;
    }

    private static void configSnackDesign() {

//                ((ViewGroup) snackbar.getView()).setClipToPadding(true);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(dpToPx(16), dpToPx(0), dpToPx(16), dpToPx(78));
        (snackbar.getView()).setBackgroundResource(R.drawable.foreground_notification);
//        snackbar.getView().setPadding(dpToPx(1), dpToPx(1), dpToPx(1), dpToPx(5));
        snackbar.getView().setPadding(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(0));
        Log.i("mElevation", "" + snackbar.getView().getElevation());
        snackbar.getView().setElevation(10);
        Log.i("mElevation2", "" + snackbar.getView().getElevation());
        snackbar.getView().setLayoutParams(params);
    }

    private static int dpToPx(@Dimension(unit = Dimension.DP) int dip) {
        final Resources r = mView.getResources();
        final DisplayMetrics displayMetrics = r.getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }
}
