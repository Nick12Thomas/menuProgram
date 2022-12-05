package com.example.menu_program;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Object Menu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "Hello", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(channel);
        }


    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.sample_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i = new Intent(getApplicationContext(), Calculator.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Uri call = Uri.parse("tel:9811729169");
                Intent i1 = new Intent(Intent.ACTION_DIAL, call);
                startActivity(i1);
                return true;
            case R.id.item3:
                Uri website = Uri.parse("https://www.google.com");
                Intent i2 = new Intent(Intent.ACTION_VIEW, website);
                startActivity(i2);
                return true;
            case R.id.item4:
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Enter the number: ");
                alert.setCancelable(false);
                final EditText number = new EditText(this);
                alert.setView(number);


                alert.setPositiveButton("Check", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int n = Integer.parseInt(number.getText().toString());
                        if (n % 2 == 0) {
                            Toast.makeText(MainActivity.this, "The number is even", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "The number is odd", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                alert.create().show();
                return true;
            case R.id.item5:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "1")
                        .setSmallIcon(R.drawable.cart)
                        .setContentTitle("Click on cart")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                Intent i3 = new Intent(MainActivity.this, Cart.class);
                i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i3, PendingIntent.FLAG_IMMUTABLE);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                NotificationManagerCompat nManager = NotificationManagerCompat.from(MainActivity.this);
                nManager.notify(0, builder.build());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
