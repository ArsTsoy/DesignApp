package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InAppNotification inAppNotification = InAppNotification.make(view,"Интернет отсутствует", "Тут тост что все хорошо", R.drawable.image2, R.color.absolute_white);
                inAppNotification.setIndefinite(true);
                inAppNotification.show();
//                View custom = LayoutInflater.from(getApplicationContext()).inflate(R.layout.in_app_notification, null);
//
//                final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
//                ((ViewGroup) snackbar.getView()).removeAllViews();
//                ((ViewGroup) snackbar.getView()).addView(custom);
//                TextView titleTV = custom.findViewById(R.id.notificationTitle);
//                titleTV.setText("mTitle");
//                TextView textTV = custom.findViewById(R.id.notificationText);
//                textTV.setText("mText");
//                View close = custom.findViewById(R.id.notificationClose);
//                snackbar.getView().setPadding(50, 0,50,20);
//                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.mTransparent, null));
//                ((ViewGroup) snackbar.getView()).setClipToPadding(true);
//                close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        snackbar.dismiss();
//                    }
//                });
//                snackbar.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
