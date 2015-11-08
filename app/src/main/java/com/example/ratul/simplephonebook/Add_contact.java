package com.example.ratul.simplephonebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Add_contact extends AppCompatActivity {


    EditText etName ;
    EditText etPhone;
    DB_Helper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        etName = (EditText)findViewById(R.id.editText);
        etPhone = (EditText)findViewById(R.id.editText2);
        dbHelper = new DB_Helper(getApplicationContext());
    }

    public void saveContact(View btn){
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        ContactHolder values = new ContactHolder(name,phone);
        if(dbHelper.insertData(values) != -1) {
            setResult(RESULT_OK);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
