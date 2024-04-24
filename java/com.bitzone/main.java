package com.example.bitzone;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
 private Button login_btn,join_btn;
 public AlertDialog.Builder ex;
 @Override
 public void onBackPressed() {
// super.onBackPressed();
 ex.show();
 }
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);
 login_btn = (Button)findViewById(R.id.login_btn);
 join_btn = (Button)findViewById(R.id.join_btn);
PAGE 25
 login_btn.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 Intent i = new Intent(getApplicationContext(),LoginActivity.class);
 startActivity(i);
 }
 });
 join_btn.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
 startActivity(i);
 }
 });
 ex = new AlertDialog.Builder(this).setTitle("Exit ?").setMessage("Do You Want To
Exit").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
 @Override
 public void onClick(DialogInterface dialog, int which) {
 finish();
 }
 }).setNegativeButton("No", new DialogInterface.OnClickListener() {
 @Override
 public void onClick(DialogInterface dialog, int which) {
 dialog.cancel();
 }
 });
 }
 public void show_assignment_icon_toast(View v){
 TextView toast_msg = new TextView(this);
 toast_msg.setTextSize(12);
 toast_msg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
 toast_msg.setText("\uD83D\uDCCB Share assignments with your friends!
\uD83E\uDD1C\uD83E\uDD1B");
 Toast message = new Toast(this);
 message.setView(toast_msg);
 message.setDuration(Toast.LENGTH_SHORT);
 message.show();
 }
 public void show_class_icon_toast(View v){
 TextView toast_msg = new TextView(this);
 toast_msg.setTextSize(12);
 toast_msg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
 toast_msg.setText("\uD83D\uDC4F View exam results! \uD83D\uDCAF ");
 Toast message = new Toast(this);
PAGE 26
 message.setView(toast_msg);
 message.setDuration(Toast.LENGTH_SHORT);
 message.show();
 }
 public void show_calendar_icon_toast(View v){
 TextView toast_msg = new TextView(this);
 toast_msg.setTextSize(12);
 toast_msg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
 toast_msg.setText("\uD83D\uDE4B Manage class attendance!
\uD83D\uDE4B\u200D♂");
 Toast message = new Toast(this);
 message.setView(toast_msg);
 message.setDuration(Toast.LENGTH_SHORT);
 message.show();
 }
 public void show_announcement_icon_toast(View v){
 TextView toast_msg = new TextView(this);
 toast_msg.setTextSize(12);
 toast_msg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
 toast_msg.setText("\uD83D\uDCE3 Receive important notifications! \uD83D\uDCAC");
 Toast message = new Toast(this);
 message.setView(toast_msg);
 message.setDuration(Toast.LENGTH_SHORT);
 message.show();
 }}
