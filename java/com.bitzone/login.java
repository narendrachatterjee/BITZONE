package com.example.bitzone;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
PAGE 28
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
 Spinner login_spin;
 public AlertDialog.Builder ex;
 EditText email, pass;
 String emailv, passv, log_typ;
 Button log_btn, goreg;
 private ProgressDialog mLoginProgress;
 private FirebaseAuth mAuth;
 private FirebaseDatabase db = FirebaseDatabase.getInstance();
 private DatabaseReference rootRef = db.getReference();
 @Override
 public void onBackPressed() {
// super.onBackPressed();
 ex.show();
 }
 @Override
 protected void onCreate(@Nullable Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.login_activity);
 mAuth = FirebaseAuth.getInstance();
PAGE 29
 mLoginProgress = new ProgressDialog(this);
 login_spin = (Spinner) findViewById(R.id.login_spin);
 log_btn = (Button) findViewById(R.id.loginbutton);
 goreg = (Button) findViewById(R.id.registerpage);
 email = (EditText) findViewById(R.id.login_email);
 pass = (EditText) findViewById(R.id.login_pass);
 String type[] = {"Staff", "Student"};
 login_spin.setOnItemSelectedListener(LoginActivity.this);
 ArrayAdapter<String> typelist = new ArrayAdapter<String>(this,
android.R.layout.simple_spinner_item, type);
 typelist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 login_spin.setAdapter(typelist);
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
PAGE 30
 }
 public void gotoforget(View v) {
 Intent i = new Intent(this, Forgetpassword.class);
 startActivity(i);
 }
 public void gotoreg(View v) {
 Intent i = new Intent(this, RegisterActivity.class);
 startActivity(i);
 }
 @Override
 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
 log_typ = parent.getItemAtPosition(position).toString();
 }
 @Override
 public void onNothingSelected(AdapterView<?> parent) {
 }
 public final boolean isInternetOn() {
 // get Connectivity Manager object to check connection
 ConnectivityManager connec = (ConnectivityManager)
getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
 // Check for network connections
PAGE 31
 if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
 connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
 connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
 // if connected with internet
 return true;
 } else if (
 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
 connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
 return false;
 }
 return false;
 }
 public void doLogin(View v) {
 if (isInternetOn()) {
 emailv = email.getText().toString();
 passv = pass.getText().toString();
 final ProgressDialog pdlg = new ProgressDialog(this);
 pdlg.setTitle("Loging In");
 pdlg.setMessage("Please Wait");
 if(!TextUtils.isEmpty(emailv) || !TextUtils.isEmpty(passv)){
 mLoginProgress.setTitle("Logging In");
 mLoginProgress.setMessage("Please wait while we check your credentials.");
 mLoginProgress.setCanceledOnTouchOutside(false);
 mLoginProgress.show();
 loginUser(emailv, passv);
 }
 if ((emailv.matches("")) && (passv.matches(""))) {
 //blank text field toast
 Toast.makeText(this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
 } else if ((emailv != null) && (passv != null)) {
 pdlg.show();
 if(log_typ.equals("Staff")) {
 rootRef.child(log_typ).addChildEventListener(new ChildEventListener() {
 @Override
 public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 String email = dataSnapshot.child("email").getValue().toString();
 String password = dataSnapshot.child("password").getValue().toString();
 if (emailv.equals(email) && passv.equals(password)) {
 pdlg.cancel();
 Toast.makeText(LoginActivity.this, "Login Succesfully",
Toast.LENGTH_SHORT).show();
 Intent intent = new Intent(LoginActivity.this, StaffDashboard.class);
 startActivity(intent);
 }
 else{
 pdlg.cancel();
 Toast.makeText(LoginActivity.this, "Login UnSuccesfull",
Toast.LENGTH_SHORT).show();
 }
 }
PAGE 33
 @Override
 public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 }
 @Override
 public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
 }
 @Override
 public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 }
 @Override
 public void onCancelled(@NonNull DatabaseError databaseError) {
 }
 });
 }
 if(log_typ.equals("Student")){
 rootRef.child(log_typ).addChildEventListener(new ChildEventListener() {
 @Override
 public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 String email = dataSnapshot.child("email").getValue().toString();
 String password = dataSnapshot.child("password").getValue().toString();
 if (emailv.equals(email) && passv.equals(password)) {
 pdlg.cancel();
PAGE 34
 Toast.makeText(LoginActivity.this, "Login Succesfully",
Toast.LENGTH_SHORT).show();
 Intent intent = new Intent(LoginActivity.this, StudentDashboard.class);
 startActivity(intent);
 }
 else{
 pdlg.cancel();
 Toast.makeText(LoginActivity.this, "Login UnSuccesfull",
Toast.LENGTH_SHORT).show();
 }
 }
 @Override
 public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 }
 @Override
 public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
 }
 @Override
 public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
 }
 @Override
 public void onCancelled(@NonNull DatabaseError databaseError) {
 }
 });
 }
 }
 } else {
 Toast.makeText(this, "Please Check Internet Connection ", Toast.LENGTH_LONG).show();
 }
 }
 private void loginUser(String email, String password) {
 mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new
OnCompleteListener<AuthResult>() {
 @Override
 public void onComplete(@NonNull Task<AuthResult> task) {
 if(task.isSuccessful()){
 mLoginProgress.dismiss();
 String current_user_id = mAuth.getCurrentUser().getUid();
 String deviceToken = FirebaseInstanceId.getInstance().getToken();

rootRef.child("Users").child(current_user_id).child("device_token").setValue(deviceToken).addOnSuccessLis
tener(new OnSuccessListener<Void>() {
 @Override
 public void onSuccess(Void aVoid) {
// Intent mainIntent = new Intent(LoginActivity.this, Main2Activity.class);
// mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
Intent.FLAG_ACTIVITY_CLEAR_TASK);
// startActivity(mainIntent);
// finish();
 }
 });
 } else {
 mLoginProgress.hide();
 String task_result = task.getException().getMessage().toString();
 Toast.makeText(LoginActivity.this, "Error : " + task_result, Toast.LENGTH_LONG).show();
 }
 }
 });
 }
